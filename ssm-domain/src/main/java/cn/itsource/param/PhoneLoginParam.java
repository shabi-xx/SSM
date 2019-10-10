package cn.itsource.param;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class PhoneLoginParam implements Serializable {

    @NotBlank(message = "手机号码不能为空")
    @Min(value = 11,message = "手机号码不能低于11位")
    private String phone;
    @NotBlank(message = "验证码不能为空")
    private String vcode;
}
