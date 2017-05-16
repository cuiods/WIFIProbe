package com.codingfairy.utils.data;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * Created by cuihao on 2017-05-16.
 * Map object attribute array to class instance
 */
public class ObjectMapper {

    public static Object map(Class clazz, Object objectArray) {
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

}
