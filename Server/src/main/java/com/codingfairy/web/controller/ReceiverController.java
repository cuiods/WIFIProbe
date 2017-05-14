package com.codingfairy.web.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Receive stat result from receiver
 * @author cuihao
 */
@RequestMapping("/api/v1/result")
@Api(value = "/result",description = "Receive Statistic Result API")
@RestController
public class ReceiverController {
}
