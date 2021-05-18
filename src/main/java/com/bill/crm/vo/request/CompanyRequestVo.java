package com.bill.crm.vo.request;

import com.bill.crm.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CompanyRequestVo  {

    private Long id;
    private String name;
    private String address;
}
