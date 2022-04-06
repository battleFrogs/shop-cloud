package org.example.system.web;

import org.example.common.core.constants.ResultData;
import org.example.system.application.UserApplication;
import org.example.system.param.LoginParam;
import org.example.system.param.LoginRegisterParam;
import org.example.system.vo.LoginVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {


    @Resource
    private UserApplication userService;

    /**
     * 用户登录
     *
     * @param param 参数
     * @return resultData
     */
    @PostMapping("/login")
    public ResultData<LoginVO> login(@RequestBody @Validated LoginParam param) {
        LoginVO result = userService.login(param);
        return ResultData.ok(result, "登录成功");
    }


    /**
     * 用户注册
     *
     * @param param 参数
     * @return resultData
     */
    @PostMapping("/register")
    public ResultData<Object> register(@RequestBody @Validated LoginRegisterParam param) {
        userService.register(param);
        return ResultData.ok("注册成功");
    }


}
