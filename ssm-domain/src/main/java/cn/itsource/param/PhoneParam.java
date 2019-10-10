package cn.itsource.param;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class PhoneParam implements Serializable {

    @NotBlank(message = "手机号码不能为空")
    @Min(value = 11,message = "手机号码不能低于11位")

    private String phone;
}
