package com.bill.crm.entity;

import com.bill.crm.bo.ClientBo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Client extends BaseEntity {

    private Long companyId;

    private String name;

    @Column(unique = true)
    private String email;

    private String phone;

    public static Client valueOf(ClientBo clientBo) {
        return Client.builder()
                .id(clientBo.getId())
                .companyId(clientBo.getCompanyId())
                .name(clientBo.getName())
                .email(clientBo.getEmail())
                .phone(clientBo.getPhone())
                .createdBy(clientBo.getCreatedBy())
                .createdAt(clientBo.getCreatedAt())
                .updatedBy(clientBo.getUpdatedBy())
                .updatedAt(clientBo.getUpdatedAt())
                .build();
    }
}
