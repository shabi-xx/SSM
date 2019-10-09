package cn.itsource.entity;


import cn.itsource.FastJsonTools;

public class EmployeeTest {

    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setUsername("嘻嘻嘻嘻");

        String s = FastJsonTools.toJSONString(employee);
        System.out.println(s);

        Employee json = FastJsonTools.getJson(s, Employee.class);
        System.out.println(json);
    }
}