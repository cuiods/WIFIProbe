package com.codingfairy.web.controller;

import com.codingfairy.bl.service.*;
import com.codingfairy.bl.vo.*;
import com.codingfairy.web.json.analysis.element.*;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Receive stat result from receiver
 * @author cuihao
 */
@RequestMapping("/api/v1/result")
@Api(value = "/result",description = "Receive Statistic Result API")
@RestController
public class ReceiverController {

    @Resource
    private ActivenessService activenessService;

    @Resource
    private FlowService flowService;

    @Resource
    private NewOldService newOldService;

    @Resource
    private StoreHoursService storeHoursService;

    @Resource
    private VisitCircleService visitCircleService;

    @ApiOperation(value = "Post activeness statistic data", notes = "Add activeness statistic data to server",
            response = ActivenessVo.class, produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/activeness" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ActivenessVo> activeness(@RequestBody List<CustomerActivenessElement> elements) {
        List<ActivenessVo> resultList = Lists.newArrayList();
        for (CustomerActivenessElement element: elements) {
            resultList.add(activenessService.save(element));
        }
        return resultList;
    }

    @ApiOperation(value = "Post flow statistic data", notes = "Add flow statistic data to server",
            response = FlowVo.class, produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/flow" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<FlowVo> flow(@RequestBody List<CustomerFlowElement> elements) {
        List<FlowVo> resultList = Lists.newArrayList();
        for (CustomerFlowElement element: elements) {
            resultList.add(flowService.save(element));
        }
        return resultList;
    }

    @ApiOperation(value = "Post new old statistic data", notes = "Add new old customer statistic data to server",
            response = NewOldVo.class, produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/newOld" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<NewOldVo> newOld(@RequestBody List<NewOldCustomElement> elements) {
        List<NewOldVo> resultList = Lists.newArrayList();
        for (NewOldCustomElement element: elements) {
            resultList.add(newOldService.save(element));
        }
        return resultList;
    }

    @ApiOperation(value = "Post in store hours statistic data", notes = "Add in store hour statistic data to server",
            response = StoreHoursVo.class, produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/storeHours" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public StoreHoursVo storeHours(@RequestBody InStoreHoursElement element) {
        return storeHoursService.save(element);
    }

    @ApiOperation(value = "Post visit circle statistic data", notes = "Add visit circle statistic data to server",
            response = VisitCircleVo.class, produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/visitCircle" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public VisitCircleVo visitCircle(@RequestBody VisitingCycleElement element) {
        return visitCircleService.save(element);
    }
}
