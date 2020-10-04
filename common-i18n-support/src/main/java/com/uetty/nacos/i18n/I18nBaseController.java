package com.uetty.nacos.i18n;

import com.uetty.nacos.resp.BaseResponse;
import com.uetty.nacos.resp.ResponseCode;
import com.uetty.nacos.util.I18nUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public abstract class I18nBaseController<K extends ResponseCode> {

    @Autowired
    protected HttpServletRequest httpServletRequest;
    @Autowired
    protected HttpServletResponse httpServletResponse;

    /**
     * 国际化
     */
    protected Locale getLocale() {
        return httpServletRequest.getLocale();
    }

    /**
     * 获取session
     */
    protected HttpSession getSession() {
        return httpServletRequest.getSession();
    }

    protected abstract K getSuccessBizCodified();

    /**
     * 错误情况下的响应结果，指定错误代码
     */
    protected BaseResponse<Object> errorResult(K bizCodified) {
        return bizCodifiedToResponse(bizCodified);
    }

    protected <T> BaseResponse<T> bizCodifiedToResponse(K bizCodified) {
        String responseCode = bizCodified.getResponseCode();
        String responseMessage = I18nUtil.getMessageByCode(getLocale(), responseCode);

        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(responseCode);
        baseResponse.setMessage(responseMessage);
        return baseResponse;
    }

    protected BaseResponse<Object> successResult() {
        K successBizCodified = getSuccessBizCodified();
        return bizCodifiedToResponse(successBizCodified);
    }

    /**
     * 成功情况下的响应结果
     */
    protected <T> BaseResponse<T> successResult(T resultData) {
        K successBizCodified = getSuccessBizCodified();
        BaseResponse<T> baseResponse = bizCodifiedToResponse(successBizCodified);
        baseResponse.setData(resultData);
        return baseResponse;
    }
}
