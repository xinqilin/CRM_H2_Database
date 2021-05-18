package com.bill.crm.vo.response;

import com.bill.crm.bo.ClientBo;
import com.bill.crm.vo.BaseVo;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ClientResponseVo extends BaseVo {
    private Long companyId;
    private String name;
    private String email;
    private String phone;

    public static ClientResponseVo valueOf(ClientBo bo) {
        return ClientResponseVo.builder()
                .id(bo.getId())
                .companyId(bo.getCompanyId())
                .name(bo.getName())
                .email(bo.getEmail())
                .phone(bo.getPhone())
                .createdBy(bo.getCreatedBy())
                .createdAt(bo.getCreatedAt())
                .updatedBy(bo.getUpdatedBy())
                .updatedAt(bo.getUpdatedAt())
                .build();
    }
}
