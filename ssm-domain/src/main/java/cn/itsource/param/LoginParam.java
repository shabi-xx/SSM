package cn.itsource.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class LoginParam implements Serializable{

    @NotBlank(message = "密码不能为空")
    @Length(min = 3,message = "密码长度不能小于3")
    private String password;

    @NotBlank(message = "账户不能为空")
    @Length(min = 5,message = "账户长度不能小于5")
    private String username;
}
