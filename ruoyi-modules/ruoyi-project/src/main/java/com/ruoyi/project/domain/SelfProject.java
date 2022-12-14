package com.ruoyi.project.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 项目信息对象 self_project
 *
 * @author ruoyi
 * @date 2022-07-06
 */
@Data
public class SelfProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 根据type判断获取count */
    @ApiModelProperty(value = "根据type判断获取count")
    private Integer type;

    /** 创建时间 */
    @ApiModelProperty(value = "创建时间")
    private String start;

    /** 结束时间 */
    @ApiModelProperty(value = "结束时间")
    private String end;

    /** ID */
    private String projectId;

    /** 项目编号 */
    @ApiModelProperty(value = "项目编号")
    private String projectCode;

    /** 项目名称 */
    @ApiModelProperty(value = "项目名称")
    private String projectName;

    /** 所属行业 */
    @ApiModelProperty(value = "所属行业")
    private String projectTrade;

    /** 项目主体(乙方) */
    @ApiModelProperty(value = "乙方(个体户编号)")
    private String projectOwner;

    /** 个体户名 */
    @ApiModelProperty(value = "个体户名")
    private String selfName;

    /** 采购方公司（甲方） */
    @ApiModelProperty(value = "采购方公司 甲方")
    private String purchCompany;

    /** 项目总金额 */
    @ApiModelProperty(value = "项目总金额(含税销售额)")
    private BigDecimal projectTotalAmount;

    /** 项目负责人(业务经理) */
    @ApiModelProperty(value = "项目负责人(业务经理)")
    private String projectLeader;

    /** 项目毛利率(%) */
    @ApiModelProperty(value = "项目毛利率(%)")
    private BigDecimal projectGrossMargin;

    /** 项目毛利润 */
    @ApiModelProperty(value = "项目毛利润")
    private BigDecimal projectGrossProfit;

    /** 项目净利润 */
    @ApiModelProperty(value = "项目净利润")
    private BigDecimal projectNetProfit;

    /** 分包总金额 */
    @ApiModelProperty(value = "应收账款")
    private BigDecimal projectPackageAmount;

    /** 项目描述(开票内容） */
    @ApiModelProperty(value = "项目描述(开票内容）")
    private String projectDesc;

    /** 项目状态,0:;1:;2:;3: 0进行 1异常 2完结 */
    @ApiModelProperty(value = "项目状态,0:;1:;2:;3: 0进行 1异常 2完结")
    private Long projectStatus;

    /** 是否删除;1:删除,0:未删除 */
    @ApiModelProperty(value = "0：作废 1：审核 完  2：审核中")
    private String isDeleted;

    /** 审核意见（驳回原因） */
    @ApiModelProperty(value = "审核意见 驳回原因")
    private String checkContent;

    /** 开票内容 */
    @ApiModelProperty(value = "开票内容")
    private String fileName;

    /** 项目时间开始 */
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "项目时间开始")
    private String projectTimeStart;

    /** 项目时间结束 */
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "项目时间结束")
    private String projectTimeEnd;

    /** 审核状态 0否 1是 */
    @ApiModelProperty(value = "审核状态 0否 1是")
    private Long projectCheckStatus;

    /** 开票状态 0否 1是 */
    @ApiModelProperty(value = "开票状态 0否 1是")
    private Long projectTicketStatus;

    /** 合同状态 0否 1是 */
    @ApiModelProperty(value = "合同状态 0否 1是")
    private Long projectContractStatus;

    /** 验收状态 0否 1是 */
    @ApiModelProperty(value = "验收状态 0否 1是")
    private Long projectAcceptanceStatus;

    /** 完税状态 0否 1是 */
    @ApiModelProperty(value = "完税状态 0否 1是")
    private Long projectDutypaidStatus;

    /** 渠道商编码 */
    @ApiModelProperty(value = "渠道商编码")
    private String placeCode;

    /** 渠道商编码 */
    @ApiModelProperty(value = "甲方纳税人识别号")
    private String purchCompanyTaxid;

    /** 发票类型 */
    @ApiModelProperty(value = "发票类型")
    private Long ticketType;

    /** 发票税率 */
    @ApiModelProperty(value = "发票税率")
    private BigDecimal ticketTax;

    /** 乙方纳税人识别号 */
    @ApiModelProperty(value = "乙方纳税人识别号")
    private String projectOwnerTaxid;

    /** 剩余可用金额 */
    @ApiModelProperty(value = "剩余可用金额")
    private BigDecimal projectRemainAmount;

    /** 票据审核不通过备注 */
    @ApiModelProperty(value = "票据审核不通过备注")
    private String ticketRemark;

    /** 合同审核不通过备注 */
    @ApiModelProperty(value = "合同审核不通过备注")
    private String contractRemark;

    /** 验收审核不通过备注 */
    @ApiModelProperty(value = "验收审核不通过备注")
    private String checkRemark;

    /** 完税审核不通过备注 */
    @ApiModelProperty(value = "完税审核不通过备注")
    private String taxRemark;

    /** 合同 */
    @ApiModelProperty(value = "合同")
    private String fileName1;

    /** 验收 */
    @ApiModelProperty(value = "验收")
    private String fileName2;

    /** 完税 */
    @ApiModelProperty(value = "完税")
    private String fileName3;

    /** 业务员（登录用户id） */
    @ApiModelProperty(value = "业务员 登录用户id")
    private Long userId;

    /** 完结状态 0未完结 1已完结 2未通过（异常） */
    @ApiModelProperty(value = "完结状态 0未完结 1已完结 2未通过 异常")
    private Integer endStatus;

    /** 完结时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "完结时间")
    private Date endTime;

    /** 发票备注 */
    @ApiModelProperty(value = "发票备注")
    private String ticketRemark2;

    /** 缴税凭证 */
    @ApiModelProperty(value = "缴税凭证")
    private String fileName4;

    /** 是否上传合同资料 */
    @ApiModelProperty(value = "是否上传合同资料")
    private Long isUpContract;

    /** 是否上传验收资料 */
    @ApiModelProperty(value = "是否上传验收资料（结算单）")
    private Long isUpAcceptance;

    /** 是否上传缴税凭证 */
    @ApiModelProperty(value = "是否上传缴税凭证")
    private Long isUpRate;

    /** 是否上传完税凭证 */
    @ApiModelProperty(value = "是否上传完税凭证")
    private Long isUpDutypaid;

    /** 普票个体户代办费 */
    @ApiModelProperty(value = "普票个体户代办费")
    private BigDecimal ordinarySelfFee;

    /** 专票个体户代办费(元） */
    @ApiModelProperty(value = "专票个体户代办费(元）")
    private BigDecimal specialSelfMoney;

    /** 个体户服务费是否含税(普票) 0 是 1否 */
    @ApiModelProperty(value = "个体户服务费是否含税(普票) 0 是 1否")
    private Long isSelfTax;

    /** 注册服务费 1500(同个体户代办费) */
    @ApiModelProperty(value = "注册服务费 1500(同个体户代办费)")
    private BigDecimal registerMoney;

    /** 普票税率(增值税普通发票） */
    @ApiModelProperty(value = "普票税率(增值税普通发票）")
    private BigDecimal ordinaryTax;

    /** 普票税率(增值税专用发票） */
    @ApiModelProperty(value = "普票税率(增值税专用发票）")
    private BigDecimal ordinarySpecialTax;

    /** 分润方式(增值税普通发票）百分比 */
    @ApiModelProperty(value = "分润方式(增值税普通发票）百分比")
    private BigDecimal ordinaryShare;

    /** 分润方式(增值税普通发票）定额 */
    @ApiModelProperty(value = "分润方式(增值税普通发票）定额")
    private BigDecimal ordinaryShareMoney;

    /** 分润方式(增值税专用发票）百分比 */
    @ApiModelProperty(value = "分润方式(增值税专用发票）百分比")
    private BigDecimal specialShare;

    /** 分润方式(增值税专用发票）定额 */
    @ApiModelProperty(value = "分润方式(增值税专用发票）定额")
    private BigDecimal specialShareMoney;

    /** 普票价税分离（与服务费含税一样，是，否；默认选择否）
     */
    @ApiModelProperty(value = "普票价税分离 与服务费含税一样，是，否；默认选择否")
    private Long isOrdinaryTax;

    /** 专票价税分离（与服务费含税一样，是，否；默认选择否） */
    @ApiModelProperty(value = "专票价税分离 与服务费含税一样，是，否；默认选择否")
    private Long isSpecialTax;

    /** 个体户服务费是否含税(专票) 0 是 1否 */
    @ApiModelProperty(value = "个体户服务费是否含税(专票) 0 是 1否")
    private Long isSpecialSelfTax;

    /** 是否单独结算 0 是 1否 */
    @ApiModelProperty(value = "是否单独结算 0 是 1否")
    private Long isSelfCount;

    /** 普票平台服务费是否定额 */
    @ApiModelProperty(value = "普票平台服务费是否定额")
    private Long ordinaryProxyIsmoney;

    /** 专票平台服务费是否定额 */
    @ApiModelProperty(value = "专票平台服务费是否定额")
    private Long specialProxyIsmoney;

    /** 普票分润方式是否定额 */
    @ApiModelProperty(value = "普票分润方式是否定额")
    private Long ordinaryShareIsmoney;

    /** 专票分润方式是否定额 */
    @ApiModelProperty(value = "专票分润方式是否定额")
    private Long specialShareIsmoney;

    /** 是否开启分润 0是 1否(普票) */
    @ApiModelProperty(value = "是否开启分润 0是 1否(普票)")
    private Long isOrdinaryShare;

    /** 是否开启分润 0是 1否(专票) */
    @ApiModelProperty(value = "是否开启分润 0是 1否(专票)")
    private Long isSpecialShare;

    /** 滑块0是 1否(专票) */
    @ApiModelProperty(value = "滑块0是 1否(专票)")
    private Long isSlider;

    /** 滑块0是 1否(普票) */
    @ApiModelProperty(value = "滑块0是 1否(普票)")
    private Long isSliderOrdinary;

    /** 分润方式(增值税普通发票) */
    @ApiModelProperty(value = "分润方式(增值税普通发票)")
    private BigDecimal selfShare;

    /** 是否开启分润 0是 1否 */
    @ApiModelProperty(value = "是否开启分润 0是 1否")
    private Long isSelfShare;

    /** 个体户注册费是否定额 1 百分比 0定额 */
    @ApiModelProperty(value = "个体户注册费是否定额 1 百分比 0定额")
    private Long selfShareIsmoney;

    /** 专票个体户代办费 */
    @ApiModelProperty(value = "专票个体户代办费")
    private BigDecimal specialSelfFee;

    /** 普票个体户代办费(元） */
    @ApiModelProperty(value = "普票个体户代办费(元）")
    private BigDecimal ordinarySelfMoney;

    /** 缴税状态 0否 1是 */
    @ApiModelProperty(value = "缴税状态 0否 1是")
    private Long projectRateStatus;

    /** 0收款中 1完成 2异常 */
    @ApiModelProperty(value = "0收款中 1完成 2异常")
    private Long projectReceiveStatus;

    /** 0出款中 1完成 2异常 */
    @ApiModelProperty(value = "0出款中 1完成 2异常")
    private Long projectPayStatus;

    /** 已收账款 */
    @ApiModelProperty(value = "已收账款")
    private BigDecimal receiveMoneys;

    /** 未收账款 */
    @ApiModelProperty(value = "未收账款")
    private BigDecimal receiveRemainMoneys;

    /** 应出账款 */
    @ApiModelProperty(value = "应出账款")
    private BigDecimal payTotalMoneys;

    /** 已出账款 */
    @ApiModelProperty(value = "已出账款")
    private BigDecimal payMoneys;

    /** 未出账款 */
    @ApiModelProperty(value = "未出账款")
    private BigDecimal payRemainMoneys;

    /** 是否收取注册服务费 */
    @ApiModelProperty(value =  "是否收取注册服务费")
    private Long isRegisterMoney;

    /** 是否开启一次性收取费用 0是 1否 */
    @ApiModelProperty(value =  "是否开启一次性收取费用 0是 1否")
    private Long isDisposable;

    /** 分润值(一次性收取费用) */
    @ApiModelProperty(value =  "分润值(一次性收取费用)")
    private BigDecimal disposableShare;

    /** 分润方式是否定额 1 百分比 0定额(一次性收取费用) */
    @ApiModelProperty(value =  "分润方式是否定额 1 百分比 0定额(一次性收取费用)")
    private Long disposableShareIsmoney;

    /** 是否开启分润 0是 1否(一次性收取费用) */
    @ApiModelProperty(value =  "是否开启分润 0是 1否(一次性收取费用)")
    private Long isDisposableShare;

    /** 一次性收取费用值 */
    @ApiModelProperty(value =  "一次性收取费用值")
    private BigDecimal disposableFee;

    /** 一次性收取费用是否定额 1 百分比 0定额 */
    @ApiModelProperty(value =  "一次性收取费用是否定额 1 百分比 0定额")
    private Long disposableFeeIsmoney;

    /** 收款不通过备注 */
    @ApiModelProperty(value = "收款不通过备注")
    private String receiveRemark;

    /** 出款不通过备注 */
    @ApiModelProperty(value = "出款不通过备注")
    private String payRemark;

    /** 应收账款 */
    @ApiModelProperty(value = "应收账款")
    private BigDecimal receiveTotalMoneys;

    /** 电话  甲方 */
    @ApiModelProperty(value = "电话  甲方")
    private String purchCompanyPhone;

    /** 地址  甲方 */
    @ApiModelProperty(value = "地址  甲方")
    private String purchCompanyAddress;

    /** 开户行  甲方 */
    @ApiModelProperty(value = "开户行  甲方")
    private String bankName;

    /** 开户行帐号  甲方 */
    @ApiModelProperty(value = "开户行帐号  甲方")
    private String bankCode;

    /** 一次性收取费用备注 */
    @ApiModelProperty(value = "一次性收取费用备注")
    private String disposableRemark;

    /** 项目款是否往来 0否 1是 */
    @ApiModelProperty(value = "项目款是否往来 0否 1是")
    private Long isDealings;

    /** 是否增加购方列表 */
    @ApiModelProperty(value = "是否增加购方列表")
    private Long isAddBuyer;

    /** 是否上传作业考勤出工记录 */
    @ApiModelProperty(value = "是否上传作业考勤出工记录")
    private Long isUpCheckwork;

    /** 是否上传项目图片 */
    @ApiModelProperty(value = "是否上传项目图片")
    private Long isUpImage;

    /** 是否上传购买方营业执照 */
    @ApiModelProperty(value = "是否上传购买方营业执照")
    private Long isUpLicense;

    /** 是否上传银行回单及对账单 */
    @ApiModelProperty(value = "是否上传银行回单及对账单")
    private Long isUpStatement;

    /** 是否上传企业资质证书 */
    @ApiModelProperty(value = "是否上传企业资质证书")
    private Long isUpCertification;

    /** 作业考勤出工记录 */
    @ApiModelProperty(value = "作业考勤出工记录")
    private String fileName5;

    /** 项目图片 */
    @ApiModelProperty(value = "项目图片")
    private String fileName6;

    /** 购买方营业执照 */
    @ApiModelProperty(value = "购买方营业执照")
    private String fileName7;

    /** 银行回单及对账单 */
    @ApiModelProperty(value = "银行回单及对账单")
    private String fileName8;

    /** 企业资质证书 */
    @ApiModelProperty(value = "企业资质证书")
    private String fileName9;

    /** 税率 */
    @ApiModelProperty(value = "税率")
    private BigDecimal projectTax;

    /** 项目往来款 1是 0否 */
    @ApiModelProperty(value = "项目往来款 1是 0否")
    private Long isProjectComeback;

    /** 不含税金额 含税销售额/（1+税率） */
    @ApiModelProperty(value = "不含税金额 含税销售额/（1+税率）")
    private BigDecimal projectAmountAftertax;

    /** 增值税税额 不含税金额*税率 */
    @ApiModelProperty(value = "增值税税额 不含税金额*税率")
    private BigDecimal projectTaxMoney;

    /** 城市维护建设税 增值税税额*0.07*0.5 */
    @ApiModelProperty(value = "城市维护建设税 增值税税额*0.07*0.5")
    private BigDecimal cityTaxMoney;

    /** 教育费附加税 增值税税额*0.03*0.5 */
    @ApiModelProperty(value = "教育费附加税 增值税税额*0.03*0.5")
    private BigDecimal eduTaxMoney;

    /** 地方教育附加税 增值税税额*0.03*0.5 */
    @ApiModelProperty(value = "地方教育附加税 增值税税额*0.03*0.5")
    private BigDecimal localeduTaxMoney;

    /** 个人所得税 */
    @ApiModelProperty(value = "个人所得税")
    private BigDecimal personalTaxMoney;

    /** 水利建设专项收入  不含税金额*0.009 */
    @ApiModelProperty(value = "水利建设专项收入  不含税金额*0.009")
    private BigDecimal waterbuildMoney;

    /** 印花税 */
    @ApiModelProperty(value = "印花税")
    private BigDecimal stampTaxMoney;

    /** 项目利润 应收-应出 */
    @ApiModelProperty(value = "项目利润 应收-应出")
    private BigDecimal projectProfitMoney;

    /** 投入金额 */
    @ApiModelProperty(value = "投入金额")
    private BigDecimal projectPutintoMoney;

    /** 投入产出比 项目利润/投入金额 */
    @ApiModelProperty(value = "投入产出比 项目利润/投入金额")
    private BigDecimal projectPutintoOutRatio;

    /** 奖励佣金 项目利润*0.9*15% */
    @ApiModelProperty(value = "奖励佣金 项目利润*0.9*15%")
    private BigDecimal projectAwardMoney;

    /** 注册服务费金额 */
    @ApiModelProperty(value = "注册服务费金额")
    private BigDecimal registerServeMoney;

    /** 专票服务费金额 */
    @ApiModelProperty(value = "专票服务费金额")
    private BigDecimal specialServeMoney;

    /** 普票服务费金额 */
    @ApiModelProperty(value = "普票服务费金额")
    private BigDecimal ordinaryServeMoney;

    /** 普票分润金额 */
    @ApiModelProperty(value = "普票分润金额")
    private BigDecimal ordinaryShareServeMoney;

    /** 专票分润金额 */
    @ApiModelProperty(value = "专票分润金额")
    private BigDecimal specialShareServeMoney;

    /** 一次性金额 */
    @ApiModelProperty(value = "一次性金额")
    private BigDecimal disposableServeMoney;

    /** 计算投入金额用(不映射） */
    @ApiModelProperty(value = "计算投入金额用")
    private BigDecimal putintoMoney;

    /** 一次性分润金额 */
    @ApiModelProperty(name = "一次性分润金额")
    private BigDecimal disposableShareServeMoney;

    /** 发票张数 */
    @ApiModelProperty(name = "发票张数")
    private Long ticketNum;
}
