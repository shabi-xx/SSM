package cn.itsource.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentQueryParam implements Serializable{
    private String productName;
    private String brand[];
}
