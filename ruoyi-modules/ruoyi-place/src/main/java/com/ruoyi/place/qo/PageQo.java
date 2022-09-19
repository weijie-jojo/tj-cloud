package com.ruoyi.place.qo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageQo {
    @ApiModelProperty(value = "开始时间")
    private String startTime;
    @ApiModelProperty(value = "结束时间")
    private String endTime;
    @ApiModelProperty(value = "单据日期")
    private String invoiceDate;
    @ApiModelProperty(value = "当前页")
    private Integer currentPage;
    @ApiModelProperty(value = "limit")
    private Integer limit;
    @ApiModelProperty(value = "页码")
    private Integer page;
    @ApiModelProperty(value = "页数")
    private Integer size;
}
