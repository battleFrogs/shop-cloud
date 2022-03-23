package org.example.system.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginParam {

    @NotBlank(message = "信息不能为空")
    private String userName;
    @NotBlank(message = "信息不能为空")
    private String password;


}
