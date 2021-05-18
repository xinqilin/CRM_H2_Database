package com.bill.crm.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PageVo {

    private Long currentPage;
    private Long totalPage;
    private Long totalRecords;
    private Long pageSize;
}
