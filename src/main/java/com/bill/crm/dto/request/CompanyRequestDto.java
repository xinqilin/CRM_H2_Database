package com.bill.crm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CompanyRequestDto {

    private Long id;
    private String name;
    private String address;
}
