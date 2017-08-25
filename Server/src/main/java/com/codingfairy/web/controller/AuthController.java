package com.codingfairy.web.controller;

import com.codingfairy.bl.service.UserService;
import com.codingfairy.bl.vo.ResultVo;
import com.codingfairy.bl.vo.UserVo;
import com.codingfairy.exception.ServerException;
import com.codingfairy.utils.constant.ServerCode;
import com.codingfairy.web.json.LoginJson;
import com.codingfairy.web.json.PasswordJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by cuihao on 2017-05-17.
 * Login auth controller
 */
@Slf4j
@Api(value = "/auth", description = "Auth API")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "User login auth",notes = "If succeed, return user info.",
            response = UserVo.class, produces = "application/json;charset=UTF-8")
    @PostMapping(value = "",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<UserVo> login(@Valid @RequestBody LoginJson loginJson)
            throws ServerException {
        UserVo userVo = userService.login(loginJson.getUsername(), loginJson.getPassword());
        return new ResultVo<>(ServerCode.SUCCESS,userVo);
    }

    @ApiOperation(value = "User logout auth",notes = "User logout.",
            response = ResultVo.class, produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<Void> logout(HttpServletRequest request) throws ServletException {
        request.getSession(true).invalidate();
        return new ResultVo<>(ServerCode.SUCCESS,null);
    }

    @ApiOperation(value = "User register auth",notes = "User register.",
            response = ResultVo.class, produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<UserVo> register(@Valid @RequestBody LoginJson registerJson) throws ServerException {
        UserVo userVo = userService.register(registerJson.getUsername(),registerJson.getPassword());
        return new ResultVo<>(ServerCode.SUCCESS,userVo);
    }

    @ApiOperation(value = "User change password auth", notes = "User change password.",
    response =  ResultVo.class, produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/changePassword", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<UserVo> changePassword(@Valid @RequestBody PasswordJson passwordJson) throws ServerException{
        log.info("start change password={}",passwordJson);
        UserVo userVo = userService.changePassword(passwordJson.getUsername(), passwordJson.getOldPassword(),
                passwordJson.getNewPassword());
        return new ResultVo<>(ServerCode.SUCCESS,userVo);
    }

}
