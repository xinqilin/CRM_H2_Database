package com.bill.crm.controller;

import com.bill.crm.vo.response.ResponseOneVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class})
    public final ResponseEntity<ResponseOneVo> handleDemoException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body(ResponseOneVo.error());
    }
}
