package com.codingfairy.web.controller;

import com.codingfairy.bl.service.ReceiverService;
import com.codingfairy.bl.tool.GsonTool;
import com.codingfairy.web.json.ProbeJson;
import com.codingfairy.web.json.RealTimeJson;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Receive json from device
 */
@RestController
@RequestMapping("/api/v1")
public class ReceiveController {

    @Resource
    private ReceiverService receiverService;

    @PostMapping(value = "/receive")
    public void receive(@RequestParam(value = "data")String probeJsonStr) {
        ProbeJson probeJson = GsonTool.convertJsonToObject(ProbeJson.class, probeJsonStr);
        receiverService.receive(probeJson);
    }

    @GetMapping(value = "/latest")
    public RealTimeJson latest() {
        return receiverService.statLatest();
    }
}
