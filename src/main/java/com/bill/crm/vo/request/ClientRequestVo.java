package com.bill.crm.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ClientRequestVo {

    private Long id;
    private Long companyId;
    private String name;
    private String email;
    private String phone;

}