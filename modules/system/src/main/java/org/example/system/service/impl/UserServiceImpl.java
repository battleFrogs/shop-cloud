package org.example.system.service.impl;

import org.example.common.core.constants.Constants;
import org.example.common.core.exception.ServiceException;
import org.example.common.core.utils.JwtUtils;
import org.example.system.dao.repository.UserRepository;
import org.example.system.model.User;
import org.example.common.redis.token.*;
import org.example.system.param.LoginParam;
import org.example.system.param.LoginRegisterParam;
import org.example.system.service.UserService;
import org.example.system.vo.LoginVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {


    @Resource
    private UserRepository userRepository;
    @Resource
    private TokenUtils tokenUtils;


    @Override
    public void register(LoginRegisterParam param) {
        User user = new User();
        user.setUserName(param.getUserName());
        user.setPassword(param.getPassword());
        user.setPhone(param.getPhone());
        userRepository.save(user);
    }

    @Override
    public LoginVO login(LoginParam param) {

        User user = userRepository.getByUserNameAndPassword(param.getUserName(), param.getPassword());
        if (user == null) {
            throw new ServiceException(Constants.FAIL, "账号或密码错误");
        }

        tokenUtils.refreshToken(user.getUserId());

        // 创建token值
        String token = JwtUtils.createAccessToken(user.getUserId(), user.getUserName());

        LoginVO loginVO = new LoginVO();
        loginVO.setToken(token);

        return loginVO;

    }
}
