package org.example.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.system.model.User;

public interface UserService extends IService<User> {


    /**
     * 通过用户名和密码查询用户
     *
     * @param userName 用户名
     * @param password 密码
     * @return 用户
     */
    User getByUserNameAndPassword(String userName, String password);


}
