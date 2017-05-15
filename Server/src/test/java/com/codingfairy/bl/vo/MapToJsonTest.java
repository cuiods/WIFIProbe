package com.codingfairy.bl.vo;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * test map to json
 */
public class MapToJsonTest {

    @Test
    public void jsonTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(System.out, JsonEncoding.UTF8);
        List<Map> mapList = new ArrayList<>();
        Map map = new HashMap();
        map.put("name","hello");
        map.put("k",1);
        mapList.add(map);
        jsonGenerator.writeObject(mapList);
    }

}
