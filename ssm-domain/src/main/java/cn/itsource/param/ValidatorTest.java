package cn.itsource.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel(description="前台传输的校验类")
public class ValidatorTest implements Serializable{

    @Min(value = 9,message = "id不能小于9")
    @ApiModelProperty(name = "id",notes = "校验的id字段",dataType = "int")
    private Integer id;
    @NotBlank(message = "名字不能为空")
    @ApiModelProperty(name = "name",notes = "校验的name字段",dataType = "Spring")
    private String name;

}
