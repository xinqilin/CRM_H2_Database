package com.bill.crm.bo;

import com.bill.crm.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@SuperBuilder
public class CompanyBo extends BaseBo {

    private String name;
    private String address;

}
