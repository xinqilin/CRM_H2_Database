package com.bill.crm.vo.response;

import com.bill.crm.bo.CompanyBo;
import com.bill.crm.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CompanyResponseVo extends BaseVo {

    private String name;
    private String address;

    public static CompanyResponseVo valueOf(CompanyBo bo) {
        return CompanyResponseVo.builder()
                .id(bo.getId())
                .name(bo.getName())
                .address(bo.getAddress())
                .createdBy(bo.getCreatedBy())
                .createdAt(bo.getCreatedAt())
                .updatedBy(bo.getUpdatedBy())
                .updatedAt(bo.getUpdatedAt())
                .build();
    }

}
