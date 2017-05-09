package com.codingfairy.web.controller;

import com.codingfairy.bl.service.ReceiverService;
import com.codingfairy.web.json.ProbeJson;
import com.codingfairy.web.json.RealTimeJson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Receive json from device
 */
@RestController
@RequestMapping("/api/v1")
public class ReceiveController {

    @Resource
    private ReceiverService receiverService;

    @PostMapping(value = "/receive")
    public ProbeJson receive(@RequestBody ProbeJson probeJson) {
        receiverService.receive(probeJson);
        return probeJson;
    }

    @GetMapping(value = "/latest")
    public RealTimeJson latest() {
        return receiverService.statLatest();
    }
}
