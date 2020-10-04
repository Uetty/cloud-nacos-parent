package com.uetty.nacos.exception;

import com.uetty.nacos.resp.BaseResponse;
import com.uetty.nacos.util.ExceptionTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
public class CommonExceptionAdvice {


    @ExceptionHandler(NoHandlerFoundException.class)
    public BaseResponse<Object> handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return BaseResponse.error(e.getMessage());
    }

//    @ExceptionHandler(DuplicateKeyException.class)
//    public BaseResponse<Object> handleDuplicateKeyException(DuplicateKeyException e){
//        log.error(e.getMessage(), e);
//        return BaseResponse.error(e.getMessage());
//    }

//    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
//    public BaseResponse<Object> handleAuthorizationException(AuthorizationException e){
//        log.error(e.getMessage(), e);
//        return BaseResponse.error("no permission");
//    }

    @ExceptionHandler(Exception.class)
    public BaseResponse<Object> handleException(Exception e){
        log.error(e.getMessage(), e);
        return BaseResponse.error(e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<Object> handleBusinessException(Exception e){
        String stackTraceOnPackage = ExceptionTool.getStackTraceOnPackage("com.uetty.nacos", e);
        log.error(stackTraceOnPackage);
        return BaseResponse.error(e.getLocalizedMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public BaseResponse<Object> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        StringBuilder sb = new StringBuilder();
        sb.append("http method: ");
        sb.append(e.getMethod());
        sb.append("not supported,");
        sb.append("supported http method is: ");
        String [] methods = e.getSupportedMethods();
        if(methods!=null){
            for(String str:methods){
                sb.append(str);
                sb.append(",");
            }
        }
        log.error(sb.toString(), e);
        return BaseResponse.error(sb.toString());
    }

    /**
     * spring默认上传大小100MB 超出大小捕获异常MaxUploadSizeExceededException
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public BaseResponse<Object> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.error(e.getMessage(), e);
        return BaseResponse.error("file over limit");
    }

//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public BaseResponse<Object> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
//        log.error(e.getMessage(), e);
//        return BaseResponse.error("field to long");
//    }
//
//    @ExceptionHandler(PoolException.class)
//    public BaseResponse<Object> handlePoolException(PoolException e) {
//        log.error(e.getMessage(), e);
//        return BaseResponse.error("Redis Error");
//    }
}
