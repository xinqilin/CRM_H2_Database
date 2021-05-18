package com.bill.crm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ClientRequestDto {

    private Long id;
    private Long companyId;
    private String name;
    private String email;
    private String phone;

}