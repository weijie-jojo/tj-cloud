package com.ruoyi.company.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 个体户名字审核对象 self_name_review
 * 
 * @author ruoyi
 * @date 2022-06-16
 */
@Data
public class SelfNameReview extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 个体户名字审核是否通过 0未通过 1已通过  */
    private Integer nameStatus;

    /** 个体户id */
    private Long selfId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String username;

    /** 个体户编码 */
    @Excel(name = "个体户编码")
    private String selfCode;

    /** 冠名类型 */
    @Excel(name = "冠名类型")
    private String titleType;

    /** 行政区划 */
    @Excel(name = "行政区划")
    private String administrativeDivision;

    /** 行业 */
    @Excel(name = "行业")
    private String industry;

    /** 组织形式 */
    @Excel(name = "组织形式")
    private String organizationalForm;

    /** 所在行政区划 */
    @Excel(name = "所在行政区划")
    private String administrativeRegion;

    /** 是否随机字号 */
    @Excel(name = "是否随机字号")
    private String random;

    /** 登记机关 */
    @Excel(name = "登记机关")
    private String registrationAuthority;

    /** 字号1 */
    @Excel(name = "字号1")
    private String fontSize1;

    /** 拟设名称1 */
    @Excel(name = "拟设名称1")
    private String poposedName1;

    /** 字号2 */
    @Excel(name = "字号2")
    private String fontSize2;

    /** 拟设名称2 */
    @Excel(name = "拟设名称2")
    private String poposedName2;

    /** 字号3 */
    @Excel(name = "字号3")
    private String fontSize3;

    /** 拟设名称3 */
    @Excel(name = "拟设名称3")
    private String poposedName3;

    /** 字号4 */
    @Excel(name = "字号4")
    private String fontSize4;

    /** 拟设名称4 */
    @Excel(name = "拟设名称4")
    private String poposedName4;

    /** 字号5 */
    @Excel(name = "字号5")
    private String fontSize5;

    /** 拟设名称5 */
    @Excel(name = "拟设名称5")
    private String poposedName5;

    /** 字号6 */
    @Excel(name = "字号6")
    private String fontSize6;

    /** 拟设名称6 */
    @Excel(name = "拟设名称6")
    private String poposedName6;

    /** 字号7 */
    @Excel(name = "字号7")
    private String fontSize7;

    /** 拟设名称7 */
    @Excel(name = "拟设名称7")
    private String poposedName7;

    /** 字号8 */
    @Excel(name = "字号8")
    private String fontSize8;

    /** 拟设名称8 */
    @Excel(name = "拟设名称8")
    private String poposedName8;

    /** 字号9 */
    @Excel(name = "字号9")
    private String fontSize9;

    /** 拟设名称9 */
    @Excel(name = "拟设名称9")
    private String poposedName9;

    /** 字号10 */
    @Excel(name = "字号10")
    private String fontSize10;

    /** 拟设名称10 */
    @Excel(name = "拟设名称10")
    private String poposedName10;

    public void setSelfId(Long selfId) 
    {
        this.selfId = selfId;
    }

    public Long getSelfId() 
    {
        return selfId;
    }

    public void setSelfCode(String selfCode)
    {
        this.selfCode = selfCode;
    }

    public String getSelfCode()
    {
        return selfCode;
    }

    public void setTitleType(String titleType) 
    {
        this.titleType = titleType;
    }

    public String getTitleType() 
    {
        return titleType;
    }
    public void setAdministrativeDivision(String administrativeDivision) 
    {
        this.administrativeDivision = administrativeDivision;
    }

    public String getAdministrativeDivision() 
    {
        return administrativeDivision;
    }
    public void setIndustry(String industry) 
    {
        this.industry = industry;
    }

    public String getIndustry() 
    {
        return industry;
    }
    public void setOrganizationalForm(String organizationalForm) 
    {
        this.organizationalForm = organizationalForm;
    }

    public String getOrganizationalForm() 
    {
        return organizationalForm;
    }
    public void setAdministrativeRegion(String administrativeRegion) 
    {
        this.administrativeRegion = administrativeRegion;
    }

    public String getAdministrativeRegion() 
    {
        return administrativeRegion;
    }
    public void setRandom(String random) 
    {
        this.random = random;
    }

    public String getRandom() 
    {
        return random;
    }
    public void setRegistrationAuthority(String registrationAuthority) 
    {
        this.registrationAuthority = registrationAuthority;
    }

    public String getRegistrationAuthority() 
    {
        return registrationAuthority;
    }
    public void setFontSize1(String fontSize1) 
    {
        this.fontSize1 = fontSize1;
    }

    public String getFontSize1() 
    {
        return fontSize1;
    }
    public void setPoposedName1(String poposedName1) 
    {
        this.poposedName1 = poposedName1;
    }

    public String getPoposedName1() 
    {
        return poposedName1;
    }
    public void setFontSize2(String fontSize2) 
    {
        this.fontSize2 = fontSize2;
    }

    public String getFontSize2() 
    {
        return fontSize2;
    }
    public void setPoposedName2(String poposedName2) 
    {
        this.poposedName2 = poposedName2;
    }

    public String getPoposedName2() 
    {
        return poposedName2;
    }
    public void setFontSize3(String fontSize3) 
    {
        this.fontSize3 = fontSize3;
    }

    public String getFontSize3() 
    {
        return fontSize3;
    }
    public void setPoposedName3(String poposedName3) 
    {
        this.poposedName3 = poposedName3;
    }

    public String getPoposedName3() 
    {
        return poposedName3;
    }
    public void setFontSize4(String fontSize4) 
    {
        this.fontSize4 = fontSize4;
    }

    public String getFontSize4() 
    {
        return fontSize4;
    }
    public void setPoposedName4(String poposedName4) 
    {
        this.poposedName4 = poposedName4;
    }

    public String getPoposedName4() 
    {
        return poposedName4;
    }
    public void setFontSize5(String fontSize5) 
    {
        this.fontSize5 = fontSize5;
    }

    public String getFontSize5() 
    {
        return fontSize5;
    }
    public void setPoposedName5(String poposedName5) 
    {
        this.poposedName5 = poposedName5;
    }

    public String getPoposedName5() 
    {
        return poposedName5;
    }
    public void setFontSize6(String fontSize6) 
    {
        this.fontSize6 = fontSize6;
    }

    public String getFontSize6() 
    {
        return fontSize6;
    }
    public void setPoposedName6(String poposedName6) 
    {
        this.poposedName6 = poposedName6;
    }

    public String getPoposedName6() 
    {
        return poposedName6;
    }
    public void setFontSize7(String fontSize7) 
    {
        this.fontSize7 = fontSize7;
    }

    public String getFontSize7() 
    {
        return fontSize7;
    }
    public void setPoposedName7(String poposedName7) 
    {
        this.poposedName7 = poposedName7;
    }

    public String getPoposedName7() 
    {
        return poposedName7;
    }
    public void setFontSize8(String fontSize8) 
    {
        this.fontSize8 = fontSize8;
    }

    public String getFontSize8() 
    {
        return fontSize8;
    }
    public void setPoposedName8(String poposedName8) 
    {
        this.poposedName8 = poposedName8;
    }

    public String getPoposedName8() 
    {
        return poposedName8;
    }
    public void setFontSize9(String fontSize9) 
    {
        this.fontSize9 = fontSize9;
    }

    public String getFontSize9() 
    {
        return fontSize9;
    }
    public void setPoposedName9(String poposedName9) 
    {
        this.poposedName9 = poposedName9;
    }

    public String getPoposedName9() 
    {
        return poposedName9;
    }
    public void setFontSize10(String fontSize10) 
    {
        this.fontSize10 = fontSize10;
    }

    public String getFontSize10() 
    {
        return fontSize10;
    }
    public void setPoposedName10(String poposedName10) 
    {
        this.poposedName10 = poposedName10;
    }

    public String getPoposedName10() 
    {
        return poposedName10;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("selfId", getSelfId())
            .append("SelfCode", getSelfCode())
            .append("titleType", getTitleType())
            .append("administrativeDivision", getAdministrativeDivision())
            .append("industry", getIndustry())
            .append("organizationalForm", getOrganizationalForm())
            .append("administrativeRegion", getAdministrativeRegion())
            .append("random", getRandom())
            .append("registrationAuthority", getRegistrationAuthority())
            .append("fontSize1", getFontSize1())
            .append("poposedName1", getPoposedName1())
            .append("fontSize2", getFontSize2())
            .append("poposedName2", getPoposedName2())
            .append("fontSize3", getFontSize3())
            .append("poposedName3", getPoposedName3())
            .append("fontSize4", getFontSize4())
            .append("poposedName4", getPoposedName4())
            .append("fontSize5", getFontSize5())
            .append("poposedName5", getPoposedName5())
            .append("fontSize6", getFontSize6())
            .append("poposedName6", getPoposedName6())
            .append("fontSize7", getFontSize7())
            .append("poposedName7", getPoposedName7())
            .append("fontSize8", getFontSize8())
            .append("poposedName8", getPoposedName8())
            .append("fontSize9", getFontSize9())
            .append("poposedName9", getPoposedName9())
            .append("fontSize10", getFontSize10())
            .append("poposedName10", getPoposedName10())
            .toString();
    }
}
