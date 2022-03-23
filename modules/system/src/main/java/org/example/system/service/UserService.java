package org.example.system.service;

import org.example.system.param.LoginParam;
import org.example.system.param.LoginRegisterParam;
import org.example.system.vo.LoginVO;

public interface UserService {


    void register(LoginRegisterParam param);

    LoginVO login(LoginParam param);
}
