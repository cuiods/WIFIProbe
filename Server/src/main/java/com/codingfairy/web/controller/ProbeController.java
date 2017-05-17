package com.codingfairy.web.controller;

import com.codingfairy.bl.service.ProbeService;
import com.codingfairy.bl.vo.ProbeVo;
import com.codingfairy.bl.vo.ResultVo;
import com.codingfairy.utils.constant.ServerCode;
import com.codingfairy.web.json.PageJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by cuihao on 2017-05-17.
 * probe id controller
 */
@Api(value = "/probe", description = "Probe api")
@RestController
@RequestMapping("/api/v1/probe")
public class ProbeController {

    @Resource
    private ProbeService probeService;

    @ApiOperation(value = "Get all probe",notes = "Get all probe ids page by page",
            response = Page.class, produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/all",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<Page<ProbeVo>> getAll(@Valid @RequestBody PageJson page) {
        return new ResultVo<>(ServerCode.SUCCESS,probeService.findAll(page.getPage(),page.getSize()));
    }

    @ApiOperation(value = "Get a probe",notes = "Get a probe by id",
            response = ProbeVo.class, produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/detail",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<ProbeVo> getOne(@RequestParam int id) {
        return new ResultVo<>(ServerCode.SUCCESS,probeService.findById(id));
    }
}
