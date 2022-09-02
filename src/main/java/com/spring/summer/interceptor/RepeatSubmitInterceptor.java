//package com.spring.summer.interceptor;
//
//
//import com.alibaba.fastjson2.JSONObject;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.spring.summer.admin.common.AjaxResult;
//import com.spring.summer.annotation.RepeatSubmit;
//import com.spring.summer.common.utils.ServletUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.lang.reflect.Method;
//
///**
// * @Author CXB
// * @ClassName PepeatSubmitInterceptor
// * @date 2022/9/2 17:37
// * @Description 防止重复提交拦截器
// */
////@Component
////@Slf4j
//public abstract class RepeatSubmitInterceptor implements HandlerInterceptor {
//
//
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (handler instanceof HandlerMethod){
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            Method method = handlerMethod.getMethod();
////            log.info("RepeatSubmitInterceptor 中 HandlerMethod: {} "+ method);
//            RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
//            if (annotation != null){
//                if(this.isRepeatSubmit(request,annotation)){
//                    AjaxResult ajaxResult = AjaxResult.error(annotation.message());
//
//                    ObjectMapper objectMapper = new ObjectMapper();
//                    String jsonString = objectMapper.writeValueAsString(ajaxResult);
//
////                    String jsonString = JSONObject.toJSONString(ajaxResult);
//                    ServletUtils.responseResult(response,jsonString);
//                    return false;
//
//                }
//            }
//            return true;
//        }else {
//            return  true;
//        }
//    }
//
//    /**
//     * 验证是否重复提价有子类实习那就提的防重复规则
//     * @param request
//     * @param annotation
//     * @return
//     */
//    public abstract boolean isRepeatSubmit(HttpServletRequest request, RepeatSubmit annotation);
//
//}
