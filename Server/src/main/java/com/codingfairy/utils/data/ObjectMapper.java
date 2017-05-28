package com.codingfairy.utils.data;

import com.codingfairy.utils.enums.ConvertType;
import com.codingfairy.web.json.Tuple;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cuihao on 2017-05-16.
 * Map object
 */
public class ObjectMapper {

    private static final String[] STORE_RANGES = {"0-0.5min","0.5-2min","2-5min","5-15min","15-30min",
                                                  "30-60min","1-2h","2-4h","4-6h",">6h"};
    private static final String[] VISIT_RANGES = {"0-5min","5-30min","0.5-1h","1-6h","6-24h",
                                                  "1-3day","3-7day","7-30day","30-180day",">180day"};
    private static final String DATA_PREFIX = "data";

    /**
     * Map object attr array to object
     * @param clazz {@code Class<T>}
     * @param objectArray attr array
     * @return instance of clazz
     */
    public static Object arrayToObject(Class clazz, Object objectArray) {
        Object[] objects;
        try {
            objects = (Object[]) objectArray;
        } catch (ClassCastException e) {
            return null;
        }
        Object object = null;
        try {
            object = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length-1 && i < objects.length; i++) {
                //ignore id attr
                Field field = fields[i+1];
                Object attrObj = objects[i];
                field.setAccessible(true);
                if (field.getType().equals(attrObj.getClass())
                        || (field.getType().equals(int.class) && attrObj.getClass().equals(Integer.class))) {
                    field.set(object, attrObj);
                } else if (field.getType().equals(Integer.class) && attrObj.getClass().equals(BigDecimal.class)) {
                    field.set(object, ((BigDecimal)attrObj).intValue());
                } else if (field.getType().equals(Double.class) && attrObj.getClass().equals(BigDecimal.class)) {
                    field.set(object, ((BigDecimal)attrObj).doubleValue());
                }
                field.setAccessible(false);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * Map number attr to {@code Tuple<String,Number>}
     * @param object any object with Number attribute
     * @return list of {@link Tuple}
     */
    public static List<Tuple<String,Number>> convertToChartData(Object object) {
        List<Tuple<String, Number>> result = new LinkedList<>();
        if (object==null) return result;
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field: fields) {
            if (Number.class.isAssignableFrom(field.getType())) {
                Tuple<String,Number> tuple = new Tuple<>();
                tuple.setX(field.getName());
                try {
                    field.setAccessible(true);
                    tuple.setY((Number) field.get(object));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } finally {
                    field.setAccessible(false);
                }
                result.add(tuple);
            }
        }
        return result;
    }

    public static List<Tuple<String,Number>> convertToViewData(List<Tuple<String,Number>> chartDatas, ConvertType type) {
        assert STORE_RANGES.length>=chartDatas.size() && VISIT_RANGES.length >= chartDatas.size();
        String[] convertStrs;
        switch (type) {
            case store:
                convertStrs = STORE_RANGES;
                break;
            case circle:
                convertStrs = VISIT_RANGES;
                break;
            default:
                return null;
        }
        return chartDatas.stream()
                .filter(tuple -> tuple.getX().contains(DATA_PREFIX))
                .map(tuple -> new Tuple<>(
                        convertStrs[Integer.valueOf(tuple.getX().charAt(tuple.getX().length() - 1) + "")],
                        tuple.getY()
                ))
                .collect(Collectors.toList());
    }

}
