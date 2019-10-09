package cn.itsource.param;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@ApiModel(description = "登录时候验证的对象")
@Data
public class EmployeeParam {

    private Long id;
    @NotBlank(message = "用户名不能为空")
    @Length(min=5,message = "用户名长度不能小于5")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "邮箱不能为空")
    private String email;

    private String headimage;

    private Integer age;

    private Long departmentId;

}
