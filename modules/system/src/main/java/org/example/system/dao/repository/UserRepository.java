package org.example.system.dao.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.system.model.User;

public interface UserRepository extends IService<User> {


    User getByUserNameAndPassword(String userName, String password);


}
