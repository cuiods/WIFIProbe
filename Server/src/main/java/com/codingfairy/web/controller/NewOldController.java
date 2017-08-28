package com.codingfairy.web.controller;

import com.codingfairy.bl.service.NewOldService;
import com.codingfairy.bl.service.PredictService;
import com.codingfairy.bl.vo.NewOldVo;
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
 * new old controller
 */
@Api(value = "/newOld", description = "New old customer API")
@RestController
@RequestMapping("/api/v1/newOld")
public class NewOldController {

    @Resource
    private NewOldService service;

    @Resource
    private PredictService predictService;

    @ApiOperation(value = "NewOld Statistic", notes = "Query new old customer statistic data",
            response = NewOldVo.class, responseContainer = "list",produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/stat",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<List<NewOldVo>> getStatInfo(@Valid @RequestBody StatQueryJson queryJson)
            throws ParamException {
        List<NewOldVo> newOldVos = service.getNewOldStat(queryJson.getStartHour(),
                QueryThreshold.valueOf(queryJson.getThreshold()),queryJson.getStartRange(),queryJson.getProbeId());
        return new ResultVo<>(ServerCode.SUCCESS, predictService.predictNewOldVos(newOldVos,
                QueryThreshold.valueOf(queryJson.getThreshold())));
    }

    @ApiOperation(value = "NewOld Statistic Detail", notes = "Query detail newOld statistic data",
            response = Tuple.class, responseContainer = "list",produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/detail",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<List<Tuple<String, Number>>> getDetails(@Valid @RequestBody QueryJson queryJson) {
        return new ResultVo<>(ServerCode.SUCCESS,
                service.findByHourAndProbe(queryJson.getHour(),queryJson.getProbeId()));
    }

    @ApiOperation(value = "NewOld Statistic Detail", notes = "Query detail new old statistic data by id",
            response = NewOldVo.class,produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/id",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<NewOldVo> getById(@RequestParam int id) throws ServerException {
        return new ResultVo<>(ServerCode.SUCCESS, service.findById(id));
    }
}
