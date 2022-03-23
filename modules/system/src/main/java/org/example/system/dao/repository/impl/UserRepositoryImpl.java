package org.example.system.dao.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.system.dao.mapper.UserMapper;
import org.example.system.dao.repository.UserRepository;
import org.example.system.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends ServiceImpl<UserMapper, User>
        implements UserRepository {


    @Override
    public User getByUserNameAndPassword(String userName, String password) {
        return this.lambdaQuery()
                .eq(User::getUserName, userName)
                .eq(User::getPassword, password)
                .one();
    }
}
