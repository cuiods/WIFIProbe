package com.codingfairy.web.controller;

import com.codingfairy.bl.service.ReceiverService;
import com.codingfairy.web.json.ProbeJson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Receive json from device
 */
@Controller
@RequestMapping("/api/v1")
public class ReceiveController {

    @Resource
    private ReceiverService receiverService;

    @PostMapping(value = "/receive")
    public ResponseEntity receive(@RequestBody ProbeJson probeJson) {
        receiverService.receive(probeJson);
        return new ResponseEntity(HttpStatus.OK);
    }
}
