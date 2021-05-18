package com.bill.crm.bo;

import com.bill.crm.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@SuperBuilder
public class ClientBo extends BaseBo {

    private Long companyId;
    private String name;
    private String email;
    private String phone;

}
