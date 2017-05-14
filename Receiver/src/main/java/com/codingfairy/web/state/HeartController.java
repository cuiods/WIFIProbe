package com.codingfairy.web.state;

import com.codingfairy.bl.service.ReceiverService;
import com.codingfairy.web.json.ProbeJson;
import com.codingfairy.web.json.RealTimeJson;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Receive json from device
 */
@RestController
@RequestMapping("/api/v1")
public class HeartController {

    @GetMapping(value = "/alive")
    public Integer state() {
        return 0;
    }
}
