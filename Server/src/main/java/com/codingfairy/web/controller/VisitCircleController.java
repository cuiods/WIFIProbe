package com.codingfairy.web.controller;

import com.codingfairy.bl.service.VisitCircleService;
import com.codingfairy.bl.vo.ResultVo;
import com.codingfairy.bl.vo.VisitCircleVo;
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
 * visit circle stat controller
 */
@Api(value = "/visitCircle", description = "Visit circle statistic API")
@RestController
@RequestMapping("/api/v1/visitCircle")
public class VisitCircleController {

    @Resource
    private VisitCircleService service;

    @ApiOperation(value = "VisitCircle Statistic", notes = "Query visitCircle statistic data",
            response = VisitCircleVo.class, responseContainer = "list",produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/stat",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<List<VisitCircleVo>> getStatInfo(@Valid @RequestBody StatQueryJson queryJson)
            throws ParamException {
        List<VisitCircleVo> visitCircleStat = service.getVisitCircleStat(queryJson.getStartHour(),
                QueryThreshold.valueOf(queryJson.getThreshold()),queryJson.getStartRange(),queryJson.getProbeId());
        return new ResultVo<>(ServerCode.SUCCESS, visitCircleStat);
    }

    @ApiOperation(value = "VisitCircle Statistic Detail", notes = "Query detail visitCircle statistic data",
            response = Tuple.class, responseContainer = "list",produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/detail",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<List<Tuple<String, Number>>> getDetails(@Valid @RequestBody QueryJson queryJson) {
        return new ResultVo<>(ServerCode.SUCCESS,
                service.findByHourAndProbe(queryJson.getHour(),queryJson.getProbeId()));
    }

    @ApiOperation(value = "VisitCircle Statistic Detail", notes = "Query detail visitCircle statistic data by id",
            response = VisitCircleVo.class,produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/id",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<VisitCircleVo> getById(@RequestParam int id) throws ServerException {
        return new ResultVo<>(ServerCode.SUCCESS, service.findById(id));
    }

}
