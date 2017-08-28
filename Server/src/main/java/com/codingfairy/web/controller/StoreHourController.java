package com.codingfairy.web.controller;

import com.codingfairy.bl.service.PredictService;
import com.codingfairy.bl.service.StoreHoursService;
import com.codingfairy.bl.vo.ResultVo;
import com.codingfairy.bl.vo.StoreHoursVo;
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
 * in store hours controller
 */
@Api(value = "/storeHour", description = "In store hour statistic API")
@RestController
@RequestMapping("/api/v1/storeHour")
public class StoreHourController {

    @Resource
    private StoreHoursService service;

    @Resource
    private PredictService predictService;

    @ApiOperation(value = "StoreHours Statistic", notes = "Query storeHour statistic data",
            response = StoreHoursVo.class, responseContainer = "list",produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/stat",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<List<StoreHoursVo>> getStatInfo(@Valid @RequestBody StatQueryJson queryJson)
            throws ParamException {
        List<StoreHoursVo> storeHoursStat = service.getStoreHoursStat(queryJson.getStartHour(),
                QueryThreshold.valueOf(queryJson.getThreshold()),queryJson.getStartRange(),queryJson.getProbeId());
        return new ResultVo<>(ServerCode.SUCCESS, predictService.predictStoreHours(storeHoursStat,
                QueryThreshold.valueOf(queryJson.getThreshold())));
    }

    @ApiOperation(value = "StoreHours Statistic Detail", notes = "Query detail storeHour statistic data",
            response = Tuple.class, responseContainer = "list",produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/detail",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<List<Tuple<String, Number>>> getDetails(@Valid @RequestBody QueryJson queryJson) {
        return new ResultVo<>(ServerCode.SUCCESS,
                service.findByHourAndProbe(queryJson.getHour(),queryJson.getProbeId()));
    }

    @ApiOperation(value = "StoreHours Statistic Detail", notes = "Query detail storeHour statistic data by id",
            response = StoreHoursVo.class,produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/id",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<StoreHoursVo> getById(@RequestParam int id) throws ServerException {
        return new ResultVo<>(ServerCode.SUCCESS, service.findById(id));
    }
}
