package cn.itsource.controller.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
public class UserParam implements Serializable{

    private Integer id;
    @NotBlank
    @Length(min = 3,message = "密码长度不能小于3")
    private String password;

    @NotBlank
    @Length(min = 5,message = "账户长度不能小于5")
    private String username;

    private Boolean sex;
    private Integer age;
    private Date birthday;
    private String address;
}
