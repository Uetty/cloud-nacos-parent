package com.uetty.nacos.blogfetcher.controller;

import com.uetty.nacos.i18n.I18nBaseController;
import com.uetty.nacos.resp.ResponseCode;

public class BaseController extends I18nBaseController<ResponseCode> {


    @Override
    protected ResponseCode getSuccessBizCodified() {
        return ResponseCode.SUCCESS;
    }
    
}
