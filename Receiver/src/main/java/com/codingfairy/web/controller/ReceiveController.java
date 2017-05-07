package com.codingfairy.web.controller;

import com.codingfairy.web.json.ProbeJson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Receive json from device
 */
@Controller
@RequestMapping("/api/vi")
public class ReceiveController {

    @PostMapping(value = "/receive")
    public ResponseEntity receive(@RequestBody ProbeJson probeJson) {
        return null;
    }
}
