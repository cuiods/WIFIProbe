package com.codingfairy.utils.predict;

/**
 * Created by darxan on 2017/8/26.
 */
public class ExpSmooth {

    /**
     * 时间序列预测
     * @param data 预测的基础数据
     * @param numForecasts 往后预测的期数
     * @return
     */
    public static double[] forecase(double[] data, int numForecasts) {
        return _doubleExponentialForecast(data, 0.4, 0.4, 0, numForecasts);
    }

    /**
     * http://www.itl.nist.gov/div898/handbook/pmc/section4/pmc431.htm
     * http://www.itl.nist.gov/div898/handbook/pmc/section4/pmc432.htm
     * @param data - input data
     * @param alpha - good value between 0.1-0.9
     * @param numForecasts - ahead forecasts
     * @return
     */
    private static double[] _singleExponentialForecast(double[] data, double alpha, int numForecasts) {
        double[] y = new double[data.length + numForecasts];
        y[0] = 0;
        y[1] = data[0];
        int i = 2;
        for (i = 2; i < data.length; i++) {
            y[i] = alpha * data[i - 1] + (1 - alpha) * y[i - 1];
        }

        for (int j = 0; j < numForecasts; j++, i++) {
            y[i] = alpha * data[data.length - 1] + (1 - alpha) * y[i - 1];
        }
        return y;
    }

    /**
     * http://www.itl.nist.gov/div898/handbook/pmc/section4/pmc433.htm
     * http://www.itl.nist.gov/div898/handbook/pmc/section4/pmc434.htm
     * @param data
     * @param alpha
     * @param gamma
     * @param initializationMethod
     * @param numForecasts
     * @return
     */
    private static double[] _doubleExponentialForecast(double[] data, double alpha, double gamma, int initializationMethod, int numForecasts) {
        double[] y = new double[data.length + numForecasts];
        double[] s = new double[data.length];
        double[] b = new double[data.length];
        s[0] = y[0] = data[0];

        if(initializationMethod==0) {
            b[0] = data[1]-data[0];
        } else if(initializationMethod==1 && data.length>4) {
            b[0] = (data[3] - data[0]) / 3;
        } else if(initializationMethod==2) {
            b[0] = (data[data.length - 1] - data[0])/(data.length - 1);
        }

        int i = 1;
        y[1] = s[0] + b[0];
        for (i = 1; i < data.length; i++) {
            s[i] = alpha * data[i] + (1 - alpha) * (s[i - 1]+b[i - 1]);
            b[i] = gamma * (s[i] - s[i - 1]) + (1-gamma) * b[i-1];
            y[i+1] = s[i] + b[i];
        }

        for (int j = 0; j < numForecasts ; j++, i++) {
            y[i] = s[data.length-1] + (j+1) * b[data.length-1];
        }

        return y;
    }

    private static double _TSAError(double[] data, double[] forecast) {
        double mad = 0.0;
        double mse = 0.0;
        double diff = 0.0;

        for (int i = 0; i < data.length; i++) {
            diff = data[i] - forecast[i];
            mad += Math.abs(diff);
            mse += Math.pow(Math.abs(diff), 2.0);
        }
        return mse/data.length;
    }


}
