package com.codingfairy.mock;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by cuihao on 2017-05-21.
 * Generator
 */
public class MockGenerator {

    public static void main(String[] args) {
        MockData mockData = new MockData();
        long currentHour = System.currentTimeMillis()/(3600*1000);
        String json = GsonTool.convertObjectToJson(
                mockData.mockProbeJsonList( currentHour*3600000,(currentHour+2)*3600000,60000));
        try {
            FileWriter fileWriter = new FileWriter(new File("mock.txt"));
            fileWriter.write(json);
            fileWriter.write("\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
