package com.bill.crm.vo.response;

import lombok.Getter;

public enum CodeMessageVo {

    SUCCESS("0000", "SUCCESS"),
    FAIL("0001", "FAIL"),
    ERROR("9999", "ERROR");

    CodeMessageVo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Getter
    private String code;
    @Getter
    private String message;
}
