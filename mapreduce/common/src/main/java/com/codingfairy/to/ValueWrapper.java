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
public class ValueWrapper implements Writable, Cloneable{


    private Writable value;


    public ValueWrapper() {
    }

    public ValueWrapper(CustomerFlowElement customerFlowElement) {
        this.value = customerFlowElement;
    }

    public ValueWrapper(NewOldCustomElement newOldCustomElement) {
        this.value = newOldCustomElement;
    }

    public ValueWrapper(IntWritable intWritable) {
        this.value = intWritable;
    }


    @Override
    public ValueWrapper clone() throws CloneNotSupportedException {
        return (ValueWrapper)super.clone();
    }

    public void write(DataOutput dataOutput) throws IOException {
        new Text(this.value.getClass().getSimpleName()).write(dataOutput);
        this.value.write(dataOutput);
    }

    public void readFields(DataInput dataInput) throws IOException {
        Text text = new Text();
        text.readFields(dataInput);

        Logger.println("value wrapper read class:"+text.toString());
        String className = text.toString();

        if (className.equals(IntWritable.class.getSimpleName())) {
            value = new IntWritable();
        } else if (className.equals(NewOldCustomElement.class.getSimpleName())) {
            value = new NewOldCustomElement();
        } else if (className.equals(CustomerFlowElement.class.getSimpleName())) {
            value = new CustomerFlowElement();
        } else {
           throw new IOException("can not read fields "+className);
        }
        value.readFields(dataInput);
    }
}
