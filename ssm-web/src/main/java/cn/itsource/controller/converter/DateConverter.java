package cn.itsource.controller.converter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Slf4j
public class DateConverter implements Converter<String,Date>{
    @Override
    public Date convert(String s) {
        if(StringUtils.isEmpty(s)){
            log.info("传入的字符为null/空串：",s);
            return  null;
        }
        //SimpleDateFormat:线程不安全
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simpleDateFormat.parse(s);
        } catch (ParseException e) {
            log.info("传入的字符格式不正常：",s);
            e.printStackTrace();
        }
        return null;
    }
}
