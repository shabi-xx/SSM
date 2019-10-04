package cn.itsource.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Student implements Serializable{

    private int id;

    private String name;

    private Date date;

    private String address;
}
