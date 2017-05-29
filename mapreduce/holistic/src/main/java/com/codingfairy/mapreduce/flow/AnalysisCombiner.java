package com.codingfairy.mapreduce.flow;

import com.codingfairy.to.KeyWrapper;
import com.codingfairy.to.ValueWrapper;
import java.io.IOException;

/**
 * Created by darxan on 17-5-20.
 */
public class AnalysisCombiner extends AbstractReducer<KeyWrapper, ValueWrapper> {

    protected void write(Context context, KeyWrapper key, ValueWrapper value)
            throws InterruptedException, IOException {
        context.write(key, value);
    }

}
