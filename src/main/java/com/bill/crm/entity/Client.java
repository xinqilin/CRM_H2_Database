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

}
