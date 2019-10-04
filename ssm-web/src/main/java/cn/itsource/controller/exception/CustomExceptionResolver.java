package cn.itsource.controller.exception;

import cn.itsource.common.Constant;
import cn.itsource.common.CustomException;
import cn.itsource.common.ResultEnum;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理器
 */

@Slf4j
public class CustomExceptionResolver implements HandlerExceptionResolver{
    //1.需要判断是哪种返回值  页面  json
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("code", ResultEnum.FAIL.getCode());
        if(e instanceof CustomException){
            CustomException customException = (CustomException)e;
            log.debug(Constant.CUSTOMEXCPETIONMESSAGE,e.getMessage());
            attributes.put("message", e.getMessage());
        }else{
             //为了给后台人员看
            log.error("异常:" + e.getMessage(),e);
            attributes.put("message", e.getMessage());
            //给用户看
           //attributes.put("message","系统忙");
        }
        ModelAndView mv = new ModelAndView();
        //json视图  code  message
        FastJsonJsonView view = new FastJsonJsonView();
        view.setAttributesMap(attributes);
        mv.setView(view);
        return mv;
    }
}
