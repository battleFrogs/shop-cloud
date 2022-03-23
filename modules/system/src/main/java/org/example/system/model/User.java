package org.example.system.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {

    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;
    private String userName;
    private String password;
    private String phone;


}
