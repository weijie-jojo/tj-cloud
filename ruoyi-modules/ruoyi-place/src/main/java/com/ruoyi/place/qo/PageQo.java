package com.ruoyi.place.qo;

import lombok.Data;

@Data
public class PageQo {
    private String startTime;
    private String endTime;
    private String invoiceDate;
    private Integer currentPage;
    private Integer limit;
    private Integer page;
    private Integer size;
}
