package cn.itsource.entity;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable{
    private Integer id;
    private String username;
    private String name;
    private String password;
    private String sex;
    private Integer age;

    private Date birthday;
    private String address;

}
