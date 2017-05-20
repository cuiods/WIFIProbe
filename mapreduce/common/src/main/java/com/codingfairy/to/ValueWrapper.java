package com.codingfairy.to;

import com.codingfairy.tool.Logger;
import com.codingfairy.vo.analysis.CustomerFlow;
import com.codingfairy.vo.analysis.NewOldCustom;
import com.codingfairy.vo.analysis.element.CustomerFlowElement;
import com.codingfairy.vo.analysis.element.NewOldCustomElement;
import lombok.Data;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by darxan on 17-5-20.
 */
@Data
public class ValueWrapper implements Writable{


    private Writable value;
    private Class writableClass;


    public ValueWrapper(CustomerFlowElement customerFlowElement) {

        this.value = customerFlowElement;
        this.writableClass = CustomerFlowElement.class;
    }

    public ValueWrapper(NewOldCustomElement newOldCustomElement) {
        this.value = newOldCustomElement;
        this.writableClass = NewOldCustomElement.class;
    }

    public ValueWrapper(IntWritable intWritable) {
        this.value = intWritable;
        this.writableClass = IntWritable.class;
    }



    public void write(DataOutput dataOutput) throws IOException {
        new Text(this.writableClass.getSimpleName()).write(dataOutput);
        this.value.write(dataOutput);
    }

    public void readFields(DataInput dataInput) throws IOException {
        Text text = new Text();
        text.readFields(dataInput);

        if (text.equals(IntWritable.class.getSimpleName())) {
            value = new IntWritable();
        } else if (text.equals(NewOldCustomElement.class.getSimpleName())) {
            value = new IntWritable();
        } else if (text.equals(CustomerFlowElement.class.getSimpleName())) {
            value = new IntWritable();
        } else {
            Logger.println("error: unrecognized class.");
            return;
        }
        writableClass = value.getClass();
        value.readFields(dataInput);
    }
}
