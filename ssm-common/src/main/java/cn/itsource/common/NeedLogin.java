package cn.itsource.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//指放在哪里 type放在类上  method放在方法上  FIELD放在字段上
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
//生命周期 存在的时间
@Retention(RetentionPolicy.RUNTIME)
//生成文档的时候是否包含当前注解
@Documented
public @interface NeedLogin {
}
