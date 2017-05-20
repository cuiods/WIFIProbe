package com.codingfairy.mapreduce.flow;

import com.codingfairy.config.MapKeyConfig;
import com.codingfairy.to.KeyWrapper;
import com.codingfairy.to.ValueWrapper;
import com.codingfairy.vo.analysis.element.CustomerFlowElement;
import com.codingfairy.vo.analysis.element.NewOldCustomElement;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by darxan on 17-5-20.
 */
public class AnalysisReducer extends Reducer<KeyWrapper, ValueWrapper, Text, Text> {

    @Override
    protected void reduce(KeyWrapper key, Iterable<ValueWrapper> values, Context context)
            throws IOException, InterruptedException {

        String type = key.getType().toString();
        Iterator<ValueWrapper> iterator = values.iterator();
        if (!iterator.hasNext()) {
            return;
        }
        ValueWrapper valueWrapper = iterator.next();

        if (type.equals(MapKeyConfig.CUSTOMER_FLOW_KEY)) {
            CustomerFlowElement first = (CustomerFlowElement)valueWrapper.getValue();
            while (iterator.hasNext()) {
                CustomerFlowElement next = (CustomerFlowElement)iterator.next().getValue();
                first.addAnother(next);
            }
        }
        else if (type.equals(MapKeyConfig.NEW_OLD_CUSTOMER)){
            NewOldCustomElement first = (NewOldCustomElement)valueWrapper.getValue();
            while (iterator.hasNext()) {
                NewOldCustomElement next = (NewOldCustomElement)iterator.next().getValue();
                first.addAnother(next);
            }
        }
        else if (type.equals(MapKeyConfig.IN_STORE_HOUR) || type.equals(MapKeyConfig.CYCLE)){
            int first = ((IntWritable)valueWrapper.getValue()).get();
            while (iterator.hasNext()) {
                int next =  ((IntWritable)iterator.next().getValue()).get();
                first+=next;
            }
            ((IntWritable)valueWrapper.getValue()).set(first);
        }
    }
}
