//package com.koron.common.exception;
//
//import javax.validation.ValidationException;
//
//import com.koron.common.bean.Result;
//import com.koron.common.type.ErrorCode;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//
//@ControllerAdvice
//public class ExceptionHandler {
//    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public String exceptionGet(Throwable e){
//        if(e instanceof ServiceException){
//            ServiceException myException = (ServiceException)e;
//            return Result.error(myException.getCode(), myException.getMessage()).toJson();
//        }
//
//        if(e instanceof ValidationException){
//            ValidationException myException = (ValidationException)e;
//            return Result.error(ErrorCode.PARAM_INVALID.getCode(), myException.getMessage()).toJson();
//        }
//
//        //RuntimeException统一提示“未知错误”
////        log.error("【系统异常】", e);
//        return Result.error(ErrorCode.UNKNOWN_ERROR.getCode(), e.getMessage()).toJson();
//    }
//
//}
