package org.example.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.system.mapper.UserMapper;
import org.example.system.model.User;
import org.example.system.service.UserService;
import org.springframework.stereotype.Repository;

@Repository
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {


    @Override
    public User getByUserNameAndPassword(String userName, String password) {
        return this.lambdaQuery()
                .eq(User::getUserName, userName)
                .eq(User::getPassword, password)
                .one();
    }
}
