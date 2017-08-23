package com.codingfairy.mapreduce.flow;

import com.codingfairy.to.KeyWrapper;
import com.codingfairy.to.ValueWrapper;
import com.codingfairy.tool.Logger;
import com.google.gson.Gson;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import java.io.IOException;

/**
 * Created by darxan on 17-5-20.
 */
public class AnalysisReducer extends AbstractReducer<LongWritable, Text> {

    private Gson gson ;
    private Text text ;
    private MultipleOutputs<LongWritable, Text> multipleOutputs;


    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        super.setup(context);
        gson = new Gson();
        text = new Text();
        multipleOutputs = new MultipleOutputs<LongWritable, Text>(context);
    }

    @Override
    public void cleanup(Context cxt)throws IOException,InterruptedException{
        multipleOutputs.close();
    }


    @Override
    protected void write(Context context, KeyWrapper key, ValueWrapper value)
            throws InterruptedException, IOException {
        text.set(gson.toJson(value.getValue()));
        Logger.println("[reducer key:]  " + key.getType().toString());
        Logger.println("[reducer value] " + text.toString());
        Logger.println("");
        multipleOutputs.write(key.getType().toString(), key.getMillisTime(), text);
    }
}
