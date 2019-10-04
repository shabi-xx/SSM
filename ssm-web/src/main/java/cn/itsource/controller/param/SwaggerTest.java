package cn.itsource.controller.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description="用于接受前台的用户信息实体类")
public class SwaggerTest implements Serializable{

    @ApiModelProperty(name = "id",notes = "用户的id字段",dataType = "int")
    private Integer id;
    @ApiModelProperty(name = "name",notes = "用户的名字",dataType = "int")
    private String name;

    private String email;
}
