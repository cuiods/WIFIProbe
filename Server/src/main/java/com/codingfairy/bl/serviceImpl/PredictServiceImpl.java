package com.codingfairy.bl.serviceImpl;

import com.codingfairy.bl.service.PredictService;
import com.codingfairy.bl.vo.*;
import com.codingfairy.utils.data.ThresholdUtil;
import com.codingfairy.utils.enums.QueryThreshold;
import com.codingfairy.utils.predict.ExpSmooth;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * predict service impl
 * @author cuihao
 */
@Service
public class PredictServiceImpl implements PredictService {

    @Override
    public List<ActivenessVo> predictActiveness(List<ActivenessVo> activenessVos, QueryThreshold threshold) {
        return predict(activenessVos, threshold, 4, "numOfHighActive",
                "numOfMedianActive", "numOfLowActive","numOfSleepActive");
    }

    @Override
    public List<FlowVo> predictFlow(List<FlowVo> flowVos, QueryThreshold threshold) {
        return predict(flowVos, threshold, 3, "inNoOutWifi","inNoOutStore","outNoInWifi","outNoInStore",
                "inAndOutWifi","intAndOutStore","stayInWifi","stayInStore","jumpRate","deepVisit","inStoreRate");
    }

    @Override
    public List<NewOldVo> predictNewOldVos(List<NewOldVo> newOldVos, QueryThreshold threshold) {
        return predict(newOldVos, threshold, 3, "newCustomer","oldCustomer");
    }

    @Override
    public List<StoreHoursVo> predictStoreHours(List<StoreHoursVo> storeHoursVos, QueryThreshold threshold) {
        return predict(storeHoursVos, threshold, 3, "data0","data1","data2","data3","data4",
                "data5","data6","data7","data8","data9");
    }

    @Override
    public List<VisitCircleVo> predictVisitCircles(List<VisitCircleVo> visitCircleVos, QueryThreshold threshold) {
        return predict(visitCircleVos,threshold,3,"data0","data1","data2","data3","data4",
                "data5","data6","data7","data8","data9");
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> predict(List<T> data, QueryThreshold threshold, int num, String... attrNames) {
        if (data == null) return null;
        if (data.isEmpty()) return data;
        int attrNum = attrNames.length;

        Field[] fields = new Field[attrNum];
        Class clazz = data.get(0).getClass();
        for (int i = 0; i < attrNum; i++) {
            String attr = attrNames[i];
            try {
                fields[i] = clazz.getDeclaredField(attr);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        double[][] predictData = new double[attrNum][data.size()];
        double[][] resultData = new double[attrNum][];
        for (int i = 0; i < attrNum; i++) {
            for (int j = 0; j < data.size(); j++) {
                predictData[i][j] = convertToDouble(data.get(j), fields[i]);
            }
            resultData[i] = ExpSmooth.forecase(predictData[i],num);
        }

        T lastData = data.get(data.size() - 1);
        DateFormat format = new SimpleDateFormat(ThresholdUtil.convertToPattern(threshold));
        for (int i = 0; i < num; i++) {
            try {
                Object object = clazz.newInstance();
                if (object.getClass().getName().equals(clazz.getName())) {
                    T t = (T) object;
                    for (int j = 0; j < fields.length; j++) {
                        convertToObject(resultData[j][i], t, fields[j]);
                    }
                    setField("id", 0, t);
                    setField("wifiProb", getField("wifiProb", lastData), t);
                    Date date = format.parse((String) getField("hour", lastData));
                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(date);
                    switch (threshold) {
                        case HOUR: calendar.add(Calendar.HOUR, i+1); break;
                        case DAY: calendar.add(Calendar.DATE, i+1); break;
                        case WEEK: calendar.add(Calendar.DATE, (i+1)*7); break;
                        case MONTH: calendar.add(Calendar.MONTH, i+1); break;
                        case YEAR: calendar.add(Calendar.YEAR,i+1); break;
                    }
                    date = calendar.getTime();
                    setField("hour", format.format(date) ,t);
                    data.add(t);
                }
            } catch (InstantiationException | IllegalAccessException | ParseException e) {
                e.printStackTrace();
            }
        }

        return data;
    }

    private double convertToDouble(Object object, Field field) {
        double result = 0;
        boolean access = field.isAccessible();
        field.setAccessible(true);
        try {
            Object data = field.get(object);
            if (data == null) return 0;
            if (data instanceof Integer) {
                result = ((Integer) data).doubleValue();
            } else if (data instanceof Double) {
                result = (double) data;
            } else if (data.getClass().getName().contains("int")) {
                result = 1.0 * (int)data;
            } else if (data.getClass().getName().contains("double")) {
                result = (double) data;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        field.setAccessible(access);
        return result;
    }

    private void convertToObject(double value, Object object, Field field) {
        boolean access = field.isAccessible();
        field.setAccessible(true);
        Class clazz = field.getType();
        try {
            if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
                field.set(object, (int)value);
            } else if (clazz.equals(Double.class) || clazz.equals(double.class)) {
                field.set(object, value);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        field.setAccessible(access);
    }

    private void setField(String fieldName, Object fieldValue, Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            boolean access = field.isAccessible();
            field.setAccessible(true);
            field.set(object, fieldValue);
            field.setAccessible(access);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Object getField(String fieldName, Object object) {
        Object result = null;
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            boolean access = field.isAccessible();
            field.setAccessible(true);
            result = field.get(object);
            field.setAccessible(access);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }
}
