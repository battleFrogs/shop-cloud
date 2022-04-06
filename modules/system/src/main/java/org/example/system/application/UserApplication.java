package org.example.system.application;

import org.example.system.param.LoginParam;
import org.example.system.param.LoginRegisterParam;
import org.example.system.vo.LoginVO;

public interface UserApplication {


    void register(LoginRegisterParam param);

    LoginVO login(LoginParam param);
}
