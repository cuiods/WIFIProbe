package com.codingfairy.web.controller;

import com.codingfairy.bl.service.ActivenessService;
import com.codingfairy.bl.vo.ActivenessVo;
import com.codingfairy.bl.vo.ResultVo;
import com.codingfairy.exception.ParamException;
import com.codingfairy.exception.ServerException;
import com.codingfairy.utils.constant.ServerCode;
import com.codingfairy.utils.enums.QueryThreshold;
import com.codingfairy.web.json.QueryJson;
import com.codingfairy.web.json.StatQueryJson;
import com.codingfairy.web.json.Tuple;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by cuihao on 2017-05-17.
 * get activeness data controller
 */
@Api(value = "/activeness")
@RestController
@RequestMapping("/api/v1/activeness")
public class ActivenessController {

    @Resource
    private ActivenessService service;

    @ApiOperation(value = "Activeness Statistic", notes = "Query activeness statistic data",
            response = ActivenessVo.class, responseContainer = "list",produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/stat",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<List<ActivenessVo>> getStatInfo(@Valid @RequestBody StatQueryJson queryJson)
            throws ParamException {
        List<ActivenessVo> activenessVos = service.getActivenessStat(queryJson.getStartHour(),
                QueryThreshold.valueOf(queryJson.getThreshold()),queryJson.getStartRange(),queryJson.getProbeId());
        return new ResultVo<>(ServerCode.SUCCESS, activenessVos);
    }

    @ApiOperation(value = "Activeness Statistic Detail", notes = "Query detail activeness statistic data",
            response = Tuple.class, responseContainer = "list",produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/detail",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<List<Tuple<String, Number>>> getDetails(@Valid @RequestBody QueryJson queryJson) {
        return new ResultVo<>(ServerCode.SUCCESS,
                service.findByHourAndProbe(queryJson.getHour(),queryJson.getProbeId()));
    }

    @ApiOperation(value = "Activeness Statistic Detail", notes = "Query detail activeness statistic data by id",
            response = ActivenessVo.class,produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/id",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<ActivenessVo> getById(@RequestParam int id) throws ServerException {
        return new ResultVo<>(ServerCode.SUCCESS, service.findById(id));
    }

}
