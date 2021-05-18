package com.bill.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PageDto {

    private Long currentPage;
    private Long totalPage;
    private Long totalRecords;
    private Long pageSize;
}
