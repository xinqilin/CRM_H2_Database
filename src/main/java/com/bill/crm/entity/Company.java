package com.bill.crm.entity;

import com.bill.crm.bo.CompanyBo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Company extends BaseEntity {

    private String name;

    private String address;

    public static Company valueOf(CompanyBo bo) {
        return Company.builder()
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
