package com.codingfairy.utils.data;

import com.codingfairy.web.json.Tuple;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cuihao on 2017-05-16.
 * Map object
 */
public class ObjectMapper {

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
            for (int i = 0; i < fields.length && i < objects.length; i++) {
                Field field = fields[i];
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

    public static List<Tuple<String,Number>> convertToChartData(Object object) {
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        List<Tuple<String, Number>> result = new LinkedList<>();
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

}
