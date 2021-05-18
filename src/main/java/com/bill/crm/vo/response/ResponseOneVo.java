package com.bill.crm.vo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
@Builder
public class ResponseOneVo {
    private String message;
    private String code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public static ResponseOneVo success(Object data) {
        Objects.requireNonNull(data);

        return ResponseOneVo.builder()
                .data(data)
                .code(CodeMessageVo.SUCCESS.getCode())
                .message(CodeMessageVo.SUCCESS.getMessage())
                .build();
    }

    public static ResponseOneVo success() {
        return ResponseOneVo.builder()
                .code(CodeMessageVo.SUCCESS.getCode())
                .message(CodeMessageVo.SUCCESS.getMessage())
                .build();
    }

    public static ResponseOneVo empty() {
        return success();
    }

    public static ResponseOneVo fail() {
        return ResponseOneVo.builder()
                .code(CodeMessageVo.FAIL.getCode())
                .message(CodeMessageVo.FAIL.getMessage())
                .build();
    }

    public static ResponseOneVo error() {
        return ResponseOneVo.builder()
                .code(CodeMessageVo.ERROR.getCode())
                .message(CodeMessageVo.ERROR.getMessage())
                .build();
    }
}
