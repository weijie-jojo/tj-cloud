package com.ruoyi.place.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.ruoyi.place.entity.SelfEmployed;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 平台渠道商 Mapper 接口
 * </p>
 *
 * @author lcc
 * @since 2022-06-03
 */
public interface SelfEmployedTgMapper extends  MPJBaseMapper<SelfEmployed>{
    @Select("select self_id, self_code, place_code, user_id, tax_id, self_address, self_name, legal_person_name, id_card_num, password, status, place_name, username, exp_status, maximum, register_time, industry_type, industry_tax, organizational_form, number_employees, contribution_amount, city, county, electronic_commerce, free_trade_zone, free_trade_area, property_right, nature_business, account_type, account_type2, private_deposit_bank, private_account_number, public_deposit_bank_1, public_account_number_1, public_deposit_bank_2, public_account_number_2, account_name, public_deposit_bank_3, public_account_number_3, create_by, update_by, create_time, update_time, business_status, file_name1, file_name2, info_status, tax_status, bank_status, file_name3, file_name4, end_time, end_status, remark_info, file_name5, business_term, is_active, remark_name, file_name6, file_name7, realname_status, special_self_fee, ordinary_self_fee, special_self_money, ordinary_self_money, is_self_tax, register_money, file_name8, ordinary_tax, ordinary_special_tax, ordinary_share, ordinary_share_money, special_share, special_share_money, is_ordinary_tax, is_special_tax, remark_register, is_special_self_tax, is_self_count, ordinary_proxy_ismoney, special_proxy_ismoney, ordinary_share_ismoney, special_share_ismoney, is_ordinary_share, is_special_share, place_alias_name, is_slider, is_slider_ordinary, is_public_user from self_employed_tg where place_code=#{placeCode}")
    List<SelfEmployed> selectEmployedByPlaceCode(@Param("placeCode") String placeCode);
}
