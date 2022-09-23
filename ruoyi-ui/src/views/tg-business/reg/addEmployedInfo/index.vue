<template>
  <div>
    <el-form
      ref="elForm"
      :model="formData"
      :rules="rules"
      size="medium"
      label-width="auto"
      label-position="right"
    >
      <el-steps
        align-center
        :active="actives"
        finish-status="success"
        style="padding-left: 2%; margin-top: 30px; margin-bottom: 10px"
      >
        <el-step title="个体户名称"></el-step>
        <el-step title="申请信息"></el-step>
        <el-step title="基本情况"></el-step>
        <el-step title="经营者信息"></el-step>
        <el-step title="结算信息"></el-step>
        <el-step title="提交审核"></el-step>
      </el-steps>
      <div v-show="actives == 0">
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="21">
            <el-form-item label="填报提示">
              <div style="color: red; font-size: 16px">
                1.个体工商户名称包含行政区划、乡镇街道社区、字号、行业、组织形式五个组成部分，例如“福州市鼓楼区中山社区天天服装店”中，行政区划为“福州市鼓楼区”，乡镇街道社区为“中山社区”，字号为“天天”，行业为“服装”，组织形式为“店”。
              </div>
              <div style="color: red; font-size: 16px">
                2.请使用谷歌chrome浏览器或360等浏览器的极速模式访问本页面进行填报。
              </div>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="个体户编号">
              <el-input
                v-model="formData.selfCode"
                placeholder="请输入个体户编号"
                :disabled="true"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9"></el-col>
        </el-row>

        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="冠名类型">
              <el-input
                v-model="formData.titleType"
                placeholder="请输入行政区划"
                disabled
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="行政区划">
              <el-input
                v-model="formData.administrativeDivision"
                placeholder="请输入行政区划"
                disabled
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="行业" prop="industry">
              <div style="display: flex; align-items: center">
                <el-input
                  style="width: 100%; margin-right: -25px"
                  v-model="formData.industry"
                  placeholder="请输入行业"
                  clearable
                >
                </el-input>
                <el-tooltip
                  style="position: relative; left: 30px;font-size: 20px;"
                  class="item"
                  effect="dark"
                  content="个体工商户名称,例如福州市鼓楼区中山社区天天服装店，行业为服装"
                  placement="top-start"
                >
                  <i class="header-icon el-icon-info"></i>
                </el-tooltip>
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <!-- <el-form-item label="组织形式" prop="organizationalForm">
            <el-select style="width:100%" v-model="formData.organizationalForm" placeholder="请选择组织形式" clearable
              @change="changeOrganizational">
              <el-option v-for="(item, index) in organizationalFormOptions" :key="index" :label="item.label"
                :value="item.value" :disabled="item.disabled"></el-option>
            </el-select>
          </el-form-item> -->
          </el-col>
        </el-row>

        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="所在行政区划">
              <el-input
                v-model="formData.administrativeRegion"
                placeholder="请输入所在行政区划"
                disabled
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="登记机关">
              <el-input
                v-model="formData.registrationAuthority"
                placeholder="请输入登记机关"
                disabled
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="字号" :required="true">
              <el-checkbox v-model="formData.random" @change="isRandom"
                >随机字号</el-checkbox
              >
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="拟设名称"> 随机名称 </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item prop="fontSize1">
              <el-input
                v-if="isDisable"
                v-model="formData.fontSize1"
                :disabled="isDisable"
                clearable
              >
              </el-input>
              <el-input
                ref="fontSize1"
                v-else
                placeholder="请输入字号一"
                v-model="formData.fontSize1"
                :disabled="isDisable"
                clearable
              >
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item>
              <el-input v-model="formData.poposedName1" disabled></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item prop="fontSize2">
              <el-input
                v-if="isDisable"
                v-model="formData.fontSize2"
                :disabled="isDisable"
                clearable
              >
              </el-input>
              <el-input
                v-else
                ref="fontSize2"
                placeholder="请输入字号二"
                v-model="formData.fontSize2"
                :disabled="isDisable"
                clearable
              >
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item>
              <el-input v-model="formData.poposedName2" disabled></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item prop="fontSize3">
              <el-input
                v-if="isDisable"
                v-model="formData.fontSize3"
                :disabled="isDisable"
                clearable
              >
              </el-input>
              <el-input
                v-else
                ref="fontSize3"
                placeholder="请输入字号三"
                v-model="formData.fontSize3"
                :disabled="isDisable"
                clearable
              >
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item>
              <el-input v-model="formData.poposedName3" disabled></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item prop="fontSize4">
              <el-input
                v-if="isDisable"
                v-model="formData.fontSize4"
                :disabled="isDisable"
                clearable
              >
              </el-input>
              <el-input
                v-else
                ref="fontSize4"
                placeholder="请输入字号四"
                v-model="formData.fontSize4"
                :disabled="isDisable"
                clearable
              >
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item>
              <el-input v-model="formData.poposedName4" disabled></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item prop="fontSize5">
              <el-input
                v-if="isDisable"
                v-model="formData.fontSize5"
                :disabled="isDisable"
                clearable
              >
              </el-input>
              <el-input
                v-else
                ref="fontSize5"
                placeholder="请输入字号五"
                v-model="formData.fontSize5"
                :disabled="isDisable"
                clearable
              >
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item>
              <el-input v-model="formData.poposedName5" disabled></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="8"></el-col>
          <el-col :span="8" class="flexs">
            <el-button type="danger" @click="resetForm" class="btn"
              >关闭</el-button
            >
            <el-button type="primary" @click="submitFormName" class="btn"
              >下一步</el-button
            >
          </el-col>
          <el-col :span="8"></el-col>
        </el-row>
      </div>
      <div v-show="actives == 1">
        <el-row type="flex" class="row-bg combottom" justify="space-around">
          <el-col :span="9">
            <div class="bankno">申请信息</div>
          </el-col>
          <el-col :span="8">
            <div></div>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="是否本人申请">
              <el-radio disabled v-model="formData.oneselfApply" label="是"
                >是</el-radio
              >
              <el-radio disabled v-model="formData.oneselfApply" label="否"
                >否</el-radio
              >
              <!-- <el-select style="width:100%" v-model="formData.oneselfApply" placeholder="请选择是否本人申请" disabled>
                <el-option v-for="(item, index) in oneselfApplys" :key="index" :label="item.label" :value="item.label"
                  :disabled="item.disabled"></el-option>
              </el-select> -->
            </el-form-item>
          </el-col>
          <el-col :span="9"></el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="申请人姓名" prop="applyName">
              <el-select
                style="width: 100%"
                v-model="formData.applyName"
                placeholder="请选择申请人姓名"
                clearable
                filterable
                @visible-change="changeApplyName($event)"
                @change="selectApplyName"
              >
                <el-option
                  v-for="(item, index) in applyNames"
                  :key="index"
                  :label="item.nickName"
                  :value="item.userId"
                  :disabled="item.disabled"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="9"> </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="移动电话">
              <el-input v-model="formData.applyPhone" :readonly="true">
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9"></el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="证件类型">
              <el-input v-model="formData.applyDocumentType" :readonly="true">
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="证件号码">
              <el-input v-model="formData.applyIdNum" :readonly="true">
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg combottom" justify="space-around">
          <el-col :span="8">
            <div class="bankno">联络员</div>
          </el-col>
          <el-col :span="8">
            <div></div>
          </el-col>
        </el-row>

        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="姓名" prop="contactName">
              <el-input
                v-model="formData.contactName"
                clearable
                placeholder="请输入法人姓名"
              >
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="移动电话" prop="contactPhone">
              <el-input
                v-model="formData.contactPhone"
                clearable
                placeholder="请输入法人移动电话"
              >
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="证件类型">
              <el-select
                style="width: 100%"
                v-model="formData.contactDocumentType"
                placeholder="请输入法人证件类型"
                clearable
              >
                <el-option
                  v-for="dict in dict.type.id_card_type"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.label"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="证件号码" prop="contactIdNum">
              <el-input
                v-model="formData.contactIdNum"
                clearable
                placeholder="请输入法人证件号码"
              >
              </el-input>
            </el-form-item>
            <!-- <el-form-item label="证件号码" prop="contactIdNum1">
              <el-input v-model="formData.contactIdNum1" clearable placeholder="请输入法人证件号码">
              </el-input>
            </el-form-item> -->
          </el-col>
        </el-row>

        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="8"></el-col>
          <el-col :span="8" class="flexs">
            <el-button type="danger" @click="resetInfo" class="btn"
              >上一步</el-button
            >
            <el-button type="primary" @click="nextBasic" class="btn"
              >下一步</el-button
            >
          </el-col>
          <el-col :span="8"></el-col>
        </el-row>
      </div>

      <div v-show="actives == 2">
        <el-row type="flex" class="row-bg combottom" justify="space-around">
          <el-col :span="8">
            <div class="bankno">基本情况</div>
          </el-col>
          <el-col :span="8">
            <div></div>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="组成形式">
              <el-input v-model="formData.organizationalForm" disabled>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="从业人数">
              <el-input v-model="formData.numberEmployees" disabled>
                <template slot="append">人</template>
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="出资额">
              <el-input
                disabled
                type="number"
                v-model="formData.contributionAmount"
              >
                <template slot="append">万元</template>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="城市">
              <el-input v-model="formData.city" disabled> </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="区（县）">
              <el-input v-model="formData.county" disabled> </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="电子商务经营者">
              <el-select
                style="width: 100%"
                v-model="formData.electronicCommerce"
                placeholder="请选择是否是电子商务经营者"
                disabled
              >
                <el-option
                  v-for="(item, index) in electronicCommerces"
                  :key="index"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="21">
            <el-form-item label="经营场所">
              <el-input v-model="formData.selfAddress" disabled> </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="联系电话">
              <el-input v-model="formData.contactPhone" disabled> </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="所属自贸区">
              <el-input v-model="formData.freeTradeZone" disabled> </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="所属自贸片区">
              <el-input v-model="formData.freeTradeArea" disabled> </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="产权">
              <el-input v-model="formData.propertyRight" disabled> </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="21">
            <el-form-item label="经营范围" prop="natureBusiness">
              <el-input
                maxlength="500"
                show-word-limit
                type="textarea"
                :rows="2"
                placeholder="请输入经营范围"
                v-model="formData.natureBusiness"
              >
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="银行账号类型" prop="accountType">
              <el-select
                style="width: 100%"
                v-model="formData.accountType"
                placeholder="请选择银行账号类型"
                @change="selectAccountType"
              >
                <el-option
                  v-for="(item, index) in accountTypes"
                  :key="index"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="私账名称">
              <el-input v-model="formData.legalPersonName" disabled> </el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row
          v-if="isPrivateBank"
          type="flex"
          class="row-bg"
          justify="space-around"
        >
          <el-col :span="9">
            <el-form-item label="对公开户银行">
              <el-input
                disabled
                v-model="formData.publicDepositBank1"
                clearable
              >
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="对公银行账号">
              <el-input
                disabled
                v-model="formData.publicAccountNumber1"
                clearable
              >
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row v-else class="rowCss row-bg" type="flex" justify="space-around">
          <el-col :span="9">
            <el-form-item label="私账开户银行" prop="privateDepositBank">
              <el-input
                placeholder="请输入法人私账开户银行"
                v-model="formData.privateDepositBank"
                clearable
                :disabled="isPrivateBank"
              >
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="私账银行账号" prop="privateAccountNumber">
              <el-input
                placeholder="请输入法人私账银行账号"
                v-model="formData.privateAccountNumber"
                clearable
                :disabled="isPrivateBank"
              >
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="8"></el-col>
          <el-col :span="8" class="flexs">
            <el-button type="danger" @click="BackInfo" class="btn"
              >上一步</el-button
            >
            <el-button type="primary" @click="nextBus" class="btn"
              >下一步</el-button
            >
          </el-col>
          <el-col :span="8"></el-col>
        </el-row>
      </div>
      <div v-show="actives == 3">
        <el-row type="flex" class="row-bg combottom" justify="space-around">
          <el-col :span="11">
            <div class="bankno">经营者（负责人）信息</div>
          </el-col>
          <el-col :span="8">
            <div></div>
          </el-col>
        </el-row>

        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="姓名">
              <el-input v-model="formData.contactName" disabled> </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="人员类型">
              <el-input v-model="formData.personnelType" disabled> </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="证件类型">
              <el-input v-model="formData.contactDocumentType" disabled>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="证件号码">
              <el-input v-model="formData.contactIdNum" disabled> </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="性别" prop="gender">
              <el-select
                style="width: 100%"
                v-model="formData.gender"
                placeholder="请选择性别"
                clearable
              >
                <el-option
                  v-for="(item, index) in genders"
                  :key="index"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="出生日期" prop="dateBirth">
              <el-date-picker
                style="width: 100%"
                v-model="formData.dateBirth"
                type="date"
                placeholder="选择出生日期"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="民族" prop="nation">
              <el-input
                v-model="formData.nation"
                clearable
                placeholder="请输入法人民族"
              >
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="文化程度" prop="eduation">
              <el-select
                style="width: 100%"
                v-model="formData.eduation"
                placeholder="请输入法人文化程度"
                clearable
              >
                <el-option
                  v-for="dict in dict.type.educational_level"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="申请前职业状况">
              <el-input v-model="formData.occupationalStatus" disabled>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="政治面貌" prop="politicalStatus">
              <el-select
                style="width: 100%"
                v-model="formData.politicalStatus"
                placeholder="请输入法人政治面貌"
                clearable
              >
                <el-option
                  v-for="dict in dict.type.political_status"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.label"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="21">
            <el-form-item label="经营者居所" prop="residence">
              <el-input
                maxlength="250"
                show-word-limit
                type="textarea"
                :rows="2"
                v-model="formData.residence"
                clearable
                placeholder="请输入法人居住地址"
              >
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="联系电话" prop="contactPhone">
              <el-input
                v-model="formData.contactPhone"
                clearable
                placeholder="请输入法人联系电话"
              >
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="电子邮箱" prop="mail">
              <el-input
                v-model="formData.mail"
                clearable
                placeholder="请输入电子邮箱"
              >
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="工商实名" prop="fileName6">
              <uploadSmall
                @getfileName="getfileName6"
                :fileName="formData.fileName6"
                :fileNameOld="fileNameOld"
                :isDetail="isDetail"
              ></uploadSmall>
            </el-form-item>
          </el-col>

          <el-col :span="9">
            <el-form-item label="税务实名" prop="fileName7">
              <uploadSmall
                @getfileName="getfileName7"
                :fileName="formData.fileName7"
                :fileNameOld="fileNameOld"
                :isDetail="isDetail"
              ></uploadSmall>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="身份证扫描件" prop="fileName5">
              <uploadSmall
                @getfileName="getfileName5"
                :fileName="formData.fileName5"
                :fileNameOld="fileNameOld"
                :isDetail="isDetail"
              ></uploadSmall>
            </el-form-item>
          </el-col>
          <el-col :span="9"></el-col>
        </el-row>

        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="8"></el-col>
          <el-col :span="8" class="flexs">
            <el-button type="danger" @click="backs" class="btn"
              >上一步</el-button
            >
            <el-button type="primary" @click="nextYe" class="btn"
              >下一步
            </el-button>
          </el-col>
          <el-col :span="8"></el-col>
        </el-row>
      </div>

      <div v-show="actives == 4">
        <el-row type="flex" class="row-bg combottom" justify="space-around">
          <el-col :span="8">
            <div class="bankno">结算信息</div>
          </el-col>
          <el-col :span="8">
            <div></div>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="客户全名" prop="placeName">
              <el-select
                @visible-change="changeValue1($event)"
              
                style="width: 100%"
                v-model="formData.placeCode"
                placeholder="请选择客户全名"
                clearable
                filterable
              >
                <el-option
                  v-for="(item, index) in places"
                  :key="index"
                  :label="item.placeAliasName"
                  :value="item.placeCode"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="客户经理" :required='true'>
              <el-input v-model="formData.userName" disabled> </el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="结算方式" :required="true">
              <el-radio
                v-model="formData.isSelfCount"
                label="0"
                @change="singleOK"
                >按个体结算</el-radio
              >
              <el-radio
                v-model="formData.isSelfCount"
                label="1"
                @change="singleOK"
                >按客户结算</el-radio
              >
            </el-form-item>
          </el-col>
          <el-col :span="9"> </el-col>
        </el-row>
        <el-row
        v-if="formData.isSelfCount == 0"
        type="flex"
        class="row-bg"
        justify="space-around"
        style="margin-top: -10px;margin-bottom: 10px;"
      >
        <el-col :span="9">
          <div class="bankno">个体注册服务费</div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>
        <el-row
          v-if="formData.isSelfCount == 0"
          type="flex"
          class="row-bg"
          justify="space-around"
        >
          <el-col :span="9">
            <el-form-item label="状态" prop="isRegisterMoney">
              <el-radio v-model="formData.isRegisterMoney" label="0"
                >开启</el-radio
              >
              <el-radio v-model="formData.isRegisterMoney" label="1"
                >关闭</el-radio
              >
            </el-form-item>
            <el-form-item
              label="服务费"
              v-if="formData.isRegisterMoney == 0"
              prop="registerMoney"
            >
              <el-input
                v-model="formData.registerMoney"
                :min="0"
                onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
              >
                <template slot="append">元</template>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9"> </el-col>
        </el-row>
        <el-row
          v-if="formData.isSelfCount == 0 && formData.isRegisterMoney == 0"
          type="flex"
          class="row-bg"
          justify="space-around"
        >
          <el-col :span="9">
            <el-form-item label="是否分润" prop="isSelfShare">
              <el-radio v-model="formData.isSelfShare" label="0">是</el-radio>
              <el-radio v-model="formData.isSelfShare" label="1">否</el-radio>
            </el-form-item>
          </el-col>
          <el-col :span="9"> </el-col>
        </el-row>
        <el-row
          v-if="formData.isSelfCount == 0 && formData.isSelfShare == 0"
          type="flex"
          class="row-bg"
          justify="space-around"
        >
          <el-col :span="9">
            <el-form-item label="分润方式" prop="selfShare">
              <div style="">
                <el-radio
                  @change="selfShareIsmoneys"
                  v-model="formData.selfShareIsmoney"
                  label="0"
                  >按定额收取</el-radio
                >
                <el-radio
                  @change="selfShareIsmoneys"
                  v-model="formData.selfShareIsmoney"
                  label="1"
                  >按百分比收取</el-radio
                >

                <el-input
                  v-if="formData.selfShareIsmoney == 0"
                  style="width: 100%"
                  :min="0"
                  v-model="formData.selfShare"
                  onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                  oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                >
                  <template slot="append">元</template>
                </el-input>
                <el-input
                  v-model="formData.selfShare"
                  v-else
                  style="width: 100%"
                  @input="isSelfShares"
                  @change="isSelfShares"
                  :step="0.01"
                  :min="0"
                  :max="100"
                  onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                  oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                >
                  <template slot="append">%</template>
                </el-input>
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="9"> </el-col>
        </el-row>
        <el-row
        v-if="formData.isSelfCount == 0"
        type="flex"
        class="row-bg"
        justify="space-around"
        style="margin-top: -10px;margin-bottom: 10px;"
      >
        <el-col :span="9">
          <div class="bankno">增值税普通发票</div>
        </el-col>
        <el-col :span="9"> </el-col>
      </el-row>  
        <el-row
          type="flex"
          class="row-bg"
          justify="space-around"
          v-if="formData.isSelfCount == 0"
        >
          <el-col :span="9">
            <el-form-item label="状态" :required="true">
              <el-radio v-model="formData.isSliderOrdinary" label="0"
                >开启</el-radio
              >
              <el-radio v-model="formData.isSliderOrdinary" label="1"
                >关闭</el-radio
              >
            </el-form-item>

            <div v-if="formData.isSliderOrdinary == 0">
              <el-form-item label="普票税率" prop="ordinaryTax">
                <!-- <el-select style="width:100%" v-model="formData.ordinaryTax" clearable placeholder="请选择">
                  <el-option v-for="item in optiond" :key="item.value" :label="item.label" :value="item.value">
                  </el-option>
                </el-select> -->
                <el-input :readonly="true" value="免税"> </el-input>
              </el-form-item>
              <el-form-item label="普票服务费" prop="ordinarySelfFee">
                <div style="">
                  <el-radio
                    @change="handPoxy"
                    v-model="formData.ordinaryProxyIsmoney"
                    label="0"
                    >按定额收取</el-radio
                  >
                  <el-radio
                    @change="handPoxy"
                    v-model="formData.ordinaryProxyIsmoney"
                    label="1"
                    >按百分比收取</el-radio
                  >
                  <el-input
                    v-if="formData.ordinaryProxyIsmoney == 0"
                    v-model="formData.ordinarySelfFee"
                    style="margin-right: 10px"
                    :min="0"
                    onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                    oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                  >
                    <template slot="append">元</template>
                  </el-input>
                  <el-input
                    v-else
                    @input="ordinarySelfFeeh"
                    @change="ordinarySelfFeeh"
                    v-model="formData.ordinarySelfFee"
                    style="margin-right: 10px"
                    :min="0"
                    :max="100"
                    onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                    oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                  >
                    <template slot="append">%</template>
                  </el-input>
                </div>
              </el-form-item>
              <el-form-item label="服务费含税" :required="true">
                <el-radio v-model="formData.isSelfTax" label="0">是</el-radio>
                <el-radio v-model="formData.isSelfTax" label="1">否</el-radio>
              </el-form-item>
              <el-form-item label="价税分离" :required="true">
                <el-radio v-model="formData.isOrdinaryTax" label="0"
                  >是</el-radio
                >
                <el-radio v-model="formData.isOrdinaryTax" label="1"
                  >否</el-radio
                >
              </el-form-item>
              <el-form-item label="是否分润" :required="true">
                <el-radio v-model="formData.isOrdinaryShare" label="0"
                  >是</el-radio
                >
                <el-radio v-model="formData.isOrdinaryShare" label="1"
                  >否</el-radio
                >
              </el-form-item>
              <el-form-item
                v-if="formData.isOrdinaryShare == 0"
                label="分润方式"
                prop="ordinaryShare"
              >
                <div style="">
                  <el-radio
                    @change="hanOrshare"
                    v-model="formData.ordinaryShareIsmoney"
                    label="0"
                    >按定额收取</el-radio
                  >
                  <el-radio
                    @change="hanOrshare"
                    v-model="formData.ordinaryShareIsmoney"
                    label="1"
                    >按百分比收取</el-radio
                  >
                  <el-input
                    v-if="formData.ordinaryShareIsmoney == 0"
                    v-model="formData.ordinaryShare"
                    style="margin-right: 10px; width: 100%"
                    :min="0"
                    onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                    oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                  >
                    <template slot="append">元</template>
                  </el-input>
                  <el-input
                    v-else
                    @input="ordinaryShareh"
                    @change="ordinaryShareh"
                    v-model="formData.ordinaryShare"
                    style="margin-right: 10px; width: 100%"
                    :min="0"
                    :max="100"
                    onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                    oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                  >
                    <template slot="append">%</template>
                  </el-input>
                </div>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="9">
          </el-col>
        </el-row>
        <el-row
        v-if="formData.isSelfCount == 0"
        type="flex"
        class="row-bg"
        justify="space-around"
        style="margin-top: -10px;margin-bottom: 10px;"
      >
        <el-col :span="9">
          <div class="bankno">增值税专用发票</div>
        </el-col>
        <el-col :span="9"></el-col>
      </el-row>
        <el-row
          type="flex"
          class="row-bg"
          justify="space-around"
          v-if="formData.isSelfCount == 0"
        >
         
          <el-col :span="9">
            <el-form-item label="状态" :required="true">
              <el-radio v-model="formData.isSlider" label="0">开启</el-radio>
              <el-radio v-model="formData.isSlider" label="1">关闭</el-radio>
            </el-form-item>
            <div v-if="formData.isSlider == 0">
              <el-form-item label="专票税率" prop="ordinarySpecialTax">
                <!-- <el-select style="width:100%" v-model="formData.ordinarySpecialTax" clearable placeholder="请选择">
                  <el-option v-for="item in optionz" :key="item.value" :label="item.label" :value="item.value">
                  </el-option>
                </el-select> -->
                <el-input :readonly="true" value="3">
                  <template slot="append">%</template>
                </el-input>
              </el-form-item>
              <el-form-item label="专票服务费" prop="specialSelfFee">
                <div style="">
                  <el-radio
                    @change="handSpecial"
                    v-model="formData.specialProxyIsmoney"
                    label="0"
                    >按定额收取</el-radio
                  >
                  <el-radio
                    @change="handSpecial"
                    v-model="formData.specialProxyIsmoney"
                    label="1"
                    >按百分比收取</el-radio
                  >
                  <el-input
                    v-if="formData.specialProxyIsmoney == 0"
                    v-model="formData.specialSelfFee"
                    style="margin-right: 10px; width: 100%"
                    :min="0"
                    onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                    oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                  >
                    <template slot="append">元</template>
                  </el-input>
                  <el-input
                    v-else
                    @input="specialSelfFeeh"
                    @change="specialSelfFeeh"
                    v-model="formData.specialSelfFee"
                    style="margin-right: 10px; width: 100%"
                    :min="0"
                    :max="100"
                    onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                    oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                  >
                    <template slot="append">%</template>
                  </el-input>
                </div>
              </el-form-item>

              <el-form-item label="服务费含税" :required="true">
                <el-radio v-model="formData.isSpecialSelfTax" label="0"
                  >是</el-radio
                >
                <el-radio v-model="formData.isSpecialSelfTax" label="1"
                  >否</el-radio
                >
              </el-form-item>
              <el-form-item label="价税分离" :required="true">
                <el-radio v-model="formData.isSpecialTax" label="0"
                  >是</el-radio
                >
                <el-radio v-model="formData.isSpecialTax" label="1"
                  >否</el-radio
                >
              </el-form-item>
              <el-form-item label="是否分润" :required="true">
                <el-radio v-model="formData.isSpecialShare" label="0"
                  >是</el-radio
                >
                <el-radio v-model="formData.isSpecialShare" label="1"
                  >否</el-radio
                >
              </el-form-item>
              <el-form-item
                v-if="formData.isSpecialShare == 0"
                label="分润方式"
                prop="specialShare"
              >
                <div style="">
                  <el-radio
                    @change="handMoney"
                    v-model="formData.specialShareIsmoney"
                    label="0"
                    >按定额收取</el-radio
                  >
                  <el-radio
                    @change="handMoney"
                    v-model="formData.specialShareIsmoney"
                    label="1"
                    >按百分比收取</el-radio
                  >
                  <el-input
                    v-if="formData.specialShareIsmoney == 0"
                    :disabled="yecomfirm"
                    v-model="formData.specialShare"
                    style="margin-right: 10px; width: 100%"
                    :min="0"
                    onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                    oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                  >
                    <template slot="append">元</template>
                  </el-input>
                  <el-input
                    @input="specialShareh"
                    @change="specialShareh"
                    v-model="formData.specialShare"
                    v-else
                    style="margin-right: 10px; width: 100%"
                    :min="0"
                    :max="100"
                    onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                    oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                  >
                    <template slot="append">%</template>
                  </el-input>
                </div>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="9">
           
          </el-col>
        </el-row>
        <el-row
        v-if="formData.isSelfCount == 0"
        type="flex"
        class="row-bg"
        justify="space-around"
        style="margin-top: -10px;margin-bottom: 10px;"
      >
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width: 60%">一次性费用</div>
          <div style="width: 40%"></div>
        </el-col>
        <el-col :span="8">
          <div></div>
        </el-col>
      </el-row>
        <el-row
          type="flex"
          class="row-bg"
          justify="space-around"
          v-if="formData.isSelfCount == 0"
        >
          <el-col :span="9">
            <el-form-item label="状态" prop="isDisposable">
              <el-radio v-model="formData.isDisposable" label="0"
                >开启</el-radio
              >
              <el-radio v-model="formData.isDisposable" label="1"
                >关闭</el-radio
              >
            </el-form-item>
            <el-row
              type="flex"
              justify="flex-end"
              v-if="formData.isDisposable == 0"
            >
              <el-col :span="24">
                <el-form-item label="费用" prop="disposableFee">
                  <div>
                    <el-radio
                      @change="handleDis"
                      v-model="formData.disposableFeeIsmoney"
                      label="0"
                      >按定额收取</el-radio
                    >
                    <el-radio
                      @change="handleDis"
                      v-model="formData.disposableFeeIsmoney"
                      label="1"
                      >按百分比收取</el-radio
                    >

                    <el-input
                      v-if="formData.disposableFeeIsmoney == 0"
                      style="width: 100%"
                      v-model="formData.disposableFee"
                      :min="0"
                      onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                      oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                    >
                      <template slot="append">元</template>
                    </el-input>
                    <el-input
                      v-else
                      style="width: 100%"
                      v-model="formData.disposableFee"
                      @input="disposableIsmoney"
                      @change="disposableIsmoney"
                      :min="0"
                      :max="100"
                      onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                      oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                    >
                      <template slot="append">%</template>
                    </el-input>
                  </div>
                </el-form-item>
                <el-form-item label="备注" v-if="formData.isDisposable==0">
                <el-input
                maxlength="50"
                show-word-limit
                type="textarea"
                :rows="2"
                placeholder="请输入备注"
                v-model="formData.disposableRemark"
              >
              </el-input>
              </el-form-item>
              </el-col>
            </el-row>
            <el-form-item
              label="是否分润"
              prop="isDisposableShare"
              v-if="formData.isDisposable == 0"
            >
              <el-radio v-model="formData.isDisposableShare" label="0"
                >是</el-radio
              >
              <el-radio v-model="formData.isDisposableShare" label="1"
                >否</el-radio
              >
            </el-form-item>
            <el-row
              v-if="formData.isDisposableShare == 0"
              type="flex"
              justify="flex-end"
            >
              <el-col :span="24">
                <el-form-item label="分润方式" prop="disposableShare">
                  <div style="">
                    <el-radio
                      @change="isdisshare"
                      v-model="formData.disposableShareIsmoney"
                      label="0"
                      >按定额收取</el-radio
                    >
                    <el-radio
                      @change="isdisshare"
                      v-model="formData.disposableShareIsmoney"
                      label="1"
                      >按百分比收取</el-radio
                    >

                    <el-input
                      v-if="formData.disposableShareIsmoney == 0"
                      style="width: 100%"
                      :min="0"
                      v-model="formData.disposableShare"
                      onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                      oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                    >
                      <template slot="append">元</template>
                    </el-input>
                    <el-input
                      v-model="formData.disposableShare"
                      v-else
                      style="width: 100%"
                      @input="disShare"
                      @change="disShare"
                      :step="0.01"
                      :min="0"
                      :max="100"
                      onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                      oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                    >
                      <template slot="append">%</template>
                    </el-input>
                  </div>
                </el-form-item>
              </el-col>
            </el-row>
          </el-col>
          <el-col :span="9">
           
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="8"></el-col>
          <el-col :span="8" class="flexs">
            <el-button type="danger" @click="backBus" class="btn"
              >上一步</el-button
            >
            <el-button
              type="primary"
              @click="submitForm"
              class="btn"
              v-hasPermi="['company:employed:add']"
              >提交
            </el-button>
          </el-col>
          <el-col :span="8"></el-col>
        </el-row>
      </div>
    </el-form>
  </div>
</template>
<script>
var validateIdNumber = (rule, value, callback) => {
  console.log();
  var reg =
    /^[1-9]\d{5}((\d{2}(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[13456789]|1[012])(0[1-9]|[12][0-9]|30))|(02(0[1-9]|1[0-9]|2[0-8]))))|(((0[48]|[2468][048]|[13579][26])|(00))0229))\d{2}[0-9Xx]$/;

  var reg1 =
    /^[1-9]\d{5}((((19|[2-9][0-9])\d{2})(0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|(((19|[2-9][0-9])\d{2})(0[13456789]|1[012])(0[1-9]|[12][0-9]|30))|(((19|[2-9][0-9])\d{2})02(0[1-9]|1[0-9]|2[0-8]))|(((1[6-9]|[2-9][0-9])(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))0229))\d{3}[0-9Xx]$/;
  // 港澳居民来往内地通行证
  // 规则： H/M + 10位或6位数字
  // 样本： H1234567890
  var reg2 = /^([A-Z]\d{6,10}(\(\w{1}\))?)$/;
  // 台湾居民来往大陆通行证
  // 规则： 新版8位或18位数字， 旧版10位数字 + 英文字母
  // 样本： 12345678 或 1234567890B
  var reg3 = /^\d{8}|^[a-zA-Z0-9]{10}|^\d{18}$/;

  if (value == "" || value == undefined || value == null) {
    callback();
  } else {
    if (!reg.test(value) && !reg1.test(value)) {
      callback(new Error("输入18位或15位正确的身份证号码！"));
    } else {
      callback();
    }
  }
};

import uploadSmall from "@/components/douploads/uploadSmall";
import crudReview from "@/api/tg-api/company/review";
import crudPerson from "@/api/tg-api/company/person";
import crudInfo from "@/api/tg-api/company/info";
import crudEmployed from "@/api/tg-api/company/employed";
import crudPlace from "@/api/tg-api/company/place";
import { getInfo } from "@/api/login";
import { Decimal } from "decimal.js";

export default {
  name: "AddEmployedInfo",
  dicts: ["political_status", "educational_level", "id_card_type"],
  props: [],
  components: {
    uploadSmall,
  },
  data() {
    return {
      fileNameNEW1: [],
      fileNameNEW2: [],
      fileNameNEW3: [],
      customerOptions: [
        {
          value: "0",
          label: "渠道",
        },
        {
          value: "1",
          label: "直客",
        },
      ],
     
      activeNameseg: "1",
      activeNamese: "1",
      optiond: [
        {
          value: "0",
          label: "免税",
        },
        {
          value: "0.01",
          label: "1%",
        },
        {
          value: "0.03",
          label: "3%",
        },
      ],
      optionz: [
        {
          value: "0.01",
          label: "1%",
        },
        {
          value: "0.03",
          label: "3%",
        },
      ],
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
      //pdf预览
      isDetail: "0",
      fileNameOld: [],
      expandOnClickNode: true,
      defaultProps: {
        children: "children",
        label: "label",
      },
      isDisable: true,
      IsSpecialTax: "0",
      actives: 0,
      options: [
        {
          id: "a",
          label: "a",
          children: [
            {
              id: "aa",
              label: "aa",
            },
            {
              id: "ab",
              label: "ab",
            },
          ],
        },
        {
          id: "b",
          label: "b",
        },
        {
          id: "c",
          label: "c",
        },
      ],
      isPrivateBank: false,
      activeName: "first",
      oneselfApplys: [
        {
          value: 1,
          label: "是",
        },
        {
          value: 2,
          label: "否",
        },
      ],
      electronicCommerces: [
        {
          value: 1,
          label: "是",
        },
        {
          value: 2,
          label: "否",
        },
      ],
      genders: [
        {
          value: 1,
          label: "男",
        },
        {
          value: 2,
          label: "女",
        },
      ],
      eduations: [],
      politicalStatuList: [],
      applyNames: [],
      contactNames: [],
      industryTypes: [],
      industryTypeList: [],
      accountTypes: [
        {
          value: 1,
          label: "私人账号",
        },
        {
          value: 2,
          label: "对公账号",
        },
      ],
      places: [],
      dialogImageUrl1: "",
      dialogVisible1: false,
      dialogImageUrl2: "",
      dialogVisible2: false,
      dialogImageUrl3: "",
      dialogVisible3: false,
      userinfo: {},
      formData: {
        disposableRemark:'',
        isDisposableShare: "1",
        disposableShareIsmoney: "0",
        disposableShare: "0",
        disposableFeeIsmoney: "1",
        disposableFee: "0",
        isDisposable: "0", //是否一次性费用
        isRegisterMoney: "0", //是否收取注册服务费

        isSliderOrdinary: "0", //普票滑块
        isSlider: "0", //专票滑块
        ordinaryProxyIsmoney: "1", //普票平台服务费是否定额
        specialProxyIsmoney: "1", //专票平台服务费是否定额
        ordinaryShareIsmoney: "1", //普票分润方式是否定额
        specialShareIsmoney: "1", //专票分润方式是否定额
        isOrdinaryShare: "1",
        isSpecialShare: "1",

        specialShareMoney: "",
        specialShare: "",
        ordinaryShare: "",
        ordinaryShareMoney: "",
        ordinarySpecialTax: "0.03",
        ordinaryTax: "0",
        userId: "",
        titleType: "区县名",
        administrativeDivision: "漳平市",
        industry: "",
        organizationalForm: "",
        administrativeRegion: "龙岩市漳平市",
        registrationAuthority: "漳平市市场监督管理局",
        random: true,
        //字号
        fontSize1: "",
        fontSize2: "",
        fontSize3: "",
        fontSize4: "",
        fontSize5: "",
        //拟设名称
        poposedName1: "",
        poposedName2: "",
        poposedName3: "",
        poposedName4: "",
        poposedName5: "",

        userName: "",
        //名称
        selfShareIsmoney: "0",
        isSelfShare: "1",
        selfShare: "0",
        isSpecialTax: "0",
        isOrdinaryTax: "0",
        specialSelfFee: 0, //专票个体户代办费(率)
        specialSelfMoney: 0, //专票个体户代办费(元）
        ordinarySelfFee: 0, //普票个体户代办费(率)
        ordinarySelfMoney: 0, //普票个体户代办费(元）
        isSelfTax: "0", //个体户服务费是否含税
        isSpecialSelfTax: "0", //专票含税
        isSelfCount: "1", //结算方式
        registerMoney: "", //注册服务费
        selfCode: "",

        //申请信息
        oneselfApply: "否",
        applyName: "",
        applyPhone: "",
        applyDocumentType: "中华人民共和国居民身份证",
        applyIdNum: "",
        applyId: "",
        //联络员
        contactName: "",
        contactPhone: "",
        contactDocumentType: "中华人民共和国居民身份证",
        contactIdNum: "",

        //基本情况
        organizationalForm: "个人经营",
        numberEmployees: 5,
        contributionAmount: 15,
        city: "龙岩市",
        county: "漳平市",
        electronicCommerce: 2, //1是 2否  是否是电子商务经营者
        selfAddress: "漳平市菁城街道双拥路202号A栋210室（集群注册）",
        freeTradeZone: "无",
        freeTradeArea: "不属自贸区",
        propertyRight: "租赁",
        natureBusiness: "",
        industryType: "",
        industryTax: "",
        accountType: 1,
        legalPersonName: "",
        privateDepositBank: "",
        privateAccountNumber: "",
        publicDepositBank1: "",
        publicAccountNumber1: "",
        placeName: "",
        placeCode: "",
        placeAliasName: "",
        userName: "",

        //经营者
        personnelType: "待业人员",
        gender: "", //1 男 2女
        dateBirth: "",
        nation: "",
        eduation: "",
        occupationalStatus: "待业人员",
        residence: "",
        mail: "",
        idCard: "",
        politicalStatus: "",
        fileName5: [],
        fileName6: [],
        fileName7: [],
      },
      industryTax: "", //行业税率展示用
      unlist: {},
      selectTipType: "",
      IsSpecialTax: "0",
      fileName5: [],
      fileName6: [],
      fileName7: [],
      rules: {
        isDisposableShare: [
          {
            required: true,
            trigger: "bulr",
          },
        ],
        disposableShareIsmoney: [
          {
            required: true,
          },
        ],
        disposableShare: [
          {
            required: true,
            message: "一次性费用分润费不能为空",
          },
        ],
        disposableFeeIsmoney: [
          {
            required: true,
            message: "请选择一次性费用是否定额",
            trigger: "change",
          },
        ],
        disposableFee: [
          {
            required: true,
            message: "一次性费用不能为空",
            trigger: "blur",
          },
        ],
        isDisposable: [
          {
            required: true,
          },
        ],
        isRegisterMoney: [
          {
            required: true,
            message: "请选择个体注册服务费是否开启",
            trigger: "change",
          },
        ],
        selfShareIsmoney: [
          {
            required: true,
            message: "请选择个体注册服务费分润方式",
            trigger: "change",
          },
        ],
        isSelfShare: [
          {
            required: true,
            message: "请选择个体注册服务是否分润",
            trigger: "change",
          },
        ],
        selfShare: [
          {
            required: true,
            message: "请输入个体注册服务费分润费",
            trigger: "blur",
          },
        ],
        privateDepositBank: [
          {
            required: true,
            message: "请输入法人私账开户银行",
            trigger: "blur",
          },
        ],
        privateAccountNumber: [
          {
            required: true,
            message: "请输入法人私账银行账号",
            trigger: "blur",
          },
        ],
        fileName7: [
          {
            required: true,
            message: "请上传税务实名",
            trigger: "change",
          },
        ],
        fileName6: [
          {
            required: true,
            message: "请上传税务实名",
            trigger: "change",
          },
        ],
        fileName5: [
          {
            required: true,
            message: "请上传身份证扫描件",
            trigger: "change",
          },
        ],
        oneselfApply: [
          {
            required: true,
            message: "请选择是否本人申请",
            trigger: "change",
          },
        ],
        applyName: [
          {
            required: true,
            message: "请选择申请人",
            trigger: "change",
          },
        ],
        applyDocumentType: [
          {
            required: true,
            message: "请输入证件类型",
            trigger: "blur",
          },
        ],

        contactName: [
          {
            required: true,
            message: "请输入法人姓名",
            trigger: "blur",
          },
        ],
        contactPhone: [
          {
            required: true,
            message: "请输入联系电话",
            trigger: "blur",
          },
          {
            pattern: /^1(3|4|5|7|8|9)\d{9}$/,
            message: "手机号格式错误",
            trigger: "blur",
          },
        ],
        contactDocumentType: [
          {
            required: true,
            message: "请输入证件类型",
            trigger: "blur",
          },
        ],
        contactIdNum: [
          {
            required: true,
            message: "请输入证件号码",
            trigger: "blur",
          },

          // {
          //   validator: validateIdNumber,
          //   required: true,
          //   trigger: 'blur'
          // }
        ],

        organizationalForm: [
          {
            required: true,
            message: "请输入组成形式",
            trigger: "blur",
          },
        ],
        numberEmployees: [
          {
            required: true,
            message: "请输入从业人数",
            trigger: "blur",
          },
        ],
        contributionAmount: [
          {
            required: true,
            message: "请输入出资额",
            trigger: "blur",
          },
        ],
        city: [
          {
            required: true,
            message: "请输入城市",
            trigger: "blur",
          },
        ],
        county: [
          {
            required: true,
            message: "请输入区（县）",
            trigger: "blur",
          },
        ],
        electronicCommerce: [
          {
            required: true,
            message: "请选择是否是电子商务经营者",
            trigger: "change",
          },
        ],
        selfAddress: [
          {
            required: true,
            message: "请输入经营场所",
            trigger: "blur",
          },
        ],
        selfAddress: [
          {
            required: true,
            message: "请输入所属自贸区",
            trigger: "blur",
          },
        ],
        freeTradeZone: [
          {
            required: true,
            message: "请输入所属自贸区",
            trigger: "blur",
          },
        ],
        freeTradeArea: [
          {
            required: true,
            message: "请输入所属自贸片区",
            trigger: "blur",
          },
        ],
        propertyRight: [
          {
            required: true,
            message: "请输入产权",
            trigger: "blur",
          },
        ],
        natureBusiness: [
          {
            required: true,
            message: "请输入经营范围",
            trigger: "blur",
          },
        ],
        industryType: [
          {
            required: true,
            message: "请选择行业类型",
            trigger: "change",
          },
        ],
        accountType: [
          {
            required: true,
            message: "请选择银行账号类型",
            trigger: "change",
          },
        ],
        placeName: [
          {
            required: true,
            message: "请选择客户全名",
            trigger: "change",
          },
        ],
        userName: [
          {
            required: true,
            message: "请输入客户经理",
            trigger: "blur",
          },
        ],

        personnelType: [
          {
            required: true,
            message: "请输入人员类型",
            trigger: "blur",
          },
        ],
        gender: [
          {
            required: true,
            message: "请选择性别",
            trigger: "change",
          },
        ],
        dateBirth: [
          {
            required: true,
            message: "选择出生日期",
            trigger: "change",
          },
        ],
        nation: [
          {
            required: true,
            message: "请输入法人民族",
            trigger: "blur",
          },
        ],
        eduation: [
          {
            required: true,
            message: "请选择法人文化程度",
            trigger: "change",
          },
        ],
        occupationalStatus: [
          {
            required: true,
            message: "请输入申请前职业状况",
            trigger: "blur",
          },
        ],
        politicalStatus: [
          {
            required: true,
            message: "请选择法人政治面貌",
            trigger: "change",
          },
        ],
        residence: [
          {
            required: true,
            message: "请输入法人居住地址",
            trigger: "blur",
          },
        ],
        mail: [
          {
            required: true,
            message: "请输入电子邮箱地址",
            trigger: "blur",
          },
          {
            pattern: /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
            message: "邮箱地址格式错误",
            trigger: "blur",
          },
        ],

        industry: [
          {
            required: true,
            message: "请输入行业",
            trigger: "blur",
          },
        ],

        organizationalForm: [
          {
            required: true,
            message: "请选择组织形式",
            trigger: "change",
          },
        ],

        administrativeRegion: [
          {
            required: true,
            message: "请输入所在行政区划",
            trigger: "blur",
          },
        ],
        registrationAuthority: [
          {
            required: true,
            message: "请输入登记机关",
            trigger: "blur",
          },
        ],
        registerMoney: [
          {
            required: true,
            message: "请输入个体户注册服务费",
            trigger: "blur",
          },
        ],
        ordinaryTax: [
          {
            required: true,
            message: "请选择普票税率",
            trigger: "change",
          },
        ],
        ordinarySpecialTax: [
          {
            required: true,
            message: "请选择专票税率",
            trigger: "change",
          },
        ],
        specialSelfFee: [
          {
            required: true,
            message: "请输入专票服务费",
            trigger: "blur",
          },
        ],
        ordinarySelfFee: [
          {
            required: true,
            message: "请输入普票服务费",
            trigger: "blur",
          },
        ],
        ordinaryShare: [
          {
            required: true,
            message: "请输普票分润",
            trigger: "blur",
          },
        ],
        specialShare: [
          {
            required: true,
            message: "请输专票分润",
            trigger: "blur",
          },
        ],
        random: [
          {
            required: false,
            message: "请输入随机字号",
            trigger: "blur",
          },
        ],
        fontSize1: [
          // { min: 3, message: "字号一不能少于3个字符", trigger: "blur" },
        ],
        fontSize2: [
          // { min: 3, message: "字号二不能少于3个字符", trigger: "blur" },
        ],
        fontSize3: [
          // { min: 3, message: "字号三不能少于3个字符", trigger: "blur" },
        ],
        fontSize4: [
          // { min: 3, message: "字号四不能少于3个字符", trigger: "blur" },
        ],
        fontSize5: [
          // { min: 3, message: "字号五不能少于3个字符", trigger: "blur" },
        ],
      },

      organizationalFormOptions: [
        {
          label: "服务部",
          value: 1,
        },
        {
          label: "经营部",
          value: 2,
        },
        {
          label: "工作室",
          value: 3,
        },
        {
          label: "商行",
          value: 4,
        },
        {
          label: "商店",
          value: 5,
        },
        {
          label: "店",
          value: 6,
        },
      ],
      wordTypeOptions: [
        {
          label: "选项一",
          value: 1,
        },
      ],
      flag: false,
    };
  },
  computed: {},
  watch: {
    formData: {
      handler: function () {
        this.getPoposedName();
      },
      deep: true,
    },
    "formData.contactName": {
      handler: function () {
        this.formData.legalPersonName = this.formData.contactName;
      },
      deep: true,
    },
  },
  mounted() {
    this.getSelfCode();
   
    this.getLoginInfo();
    //申请人
    this.getApplyName();
    //联系人
    this.getContactName();
  },
  methods: {
    //一次性分润
    isdisshare(e) {
      if (e == "1") {
        if (this.formData.disposableShareIsmoney == "1") {
          if (this.formData.disposableShare > 100) {
            this.formData.disposableShare = "100";
          }
        }
      }
    },
    //一次性分润
    disShare(e) {
      if (this.formData.disposableShareIsmoney == "1") {
        if (e > 100) {
          this.formData.disposableShare = "100";
        }
      }
    },

    //一次性费用
    disposableIsmoney(e) {
      if (this.formData.disposableFeeIsmoney == "1") {
        if (e > 100) {
          this.formData.disposableFee = "100";
        }
      }
    },
    //一次性费用
    handleDis(e) {
      if (e == "1") {
        if (this.formData.disposableFeeIsmoney == "1") {
          if (this.formData.disposableFee > 100) {
            this.formData.disposableFee = "100";
          }
        }
      }
    },
    changeApplyName(e) {
      if (e == true) {
        this.getApplyName();
      }
    },
    changeValue1(e) {
      if (e == true) {
        this.getLoginInfo();
      }
    },
    isSelfShares(e) {
      if (this.formData.selfShareIsmoney == "1") {
        if (e > 100) {
          this.formData.selfShare = "100";
        }
      }
    },
    selfShareIsmoneys(e) {
      if (e == "1") {
        if (this.formData.selfShareIsmoney == "1") {
          if (this.formData.selfShare > 100) {
            this.formData.selfShare = "100";
          }
        }
      }
    },
    handPoxy(e) {
      if (e == "1") {
        if (this.formData.ordinaryProxyIsmoney == "1") {
          if (this.formData.ordinarySelfFee > 100) {
            this.formData.ordinarySelfFee = "100";
          }
        }
      }
    },
    hanOrshare(e) {
      if (e == "1") {
        if (this.formData.ordinaryShareIsmoney == "1") {
          if (this.formData.ordinaryShare > 100) {
            this.formData.ordinaryShare = "100";
          }
        }
      }
    },
    handSpecial(e) {
      if (e == "1") {
        if (this.formData.specialProxyIsmoney == "1") {
          if (this.formData.specialSelfFee > 100) {
            this.formData.specialSelfFee = "100";
          }
        }
      }
    },
    handMoney(e) {
      if (e == "1") {
        if (this.formData.specialShareIsmoney == "1") {
          if (this.formData.specialShare > 100) {
            this.formData.specialShare = "100";
          }
        }
      }
    },

    ordinarySelfFeeh(e) {
      if (this.formData.ordinaryProxyIsmoney == "1") {
        if (e > 100) {
          this.formData.ordinarySelfFee = "100";
        }
      }
    },
    specialSelfFeeh(e) {
      if (this.formData.specialProxyIsmoney == "1") {
        if (e > 100) {
          this.formData.specialSelfFee = "100";
        }
      }
    },
    specialShareh(e) {
      if (this.formData.specialShareIsmoney == "1") {
        if (e > 100) {
          this.formData.specialShare = "100";
        }
      }
    },
    ordinaryShareh(e) {
      if (this.formData.ordinaryShareIsmoney == "1") {
        if (e > 100) {
          this.formData.ordinaryShare = "100";
        }
      }
    },

    getfileName5(data) {
      this.formData.fileName5 = data;
    },
    getfileName6(data) {
      this.formData.fileName6 = data;
    },
    getfileName7(data) {
      this.formData.fileName7 = data;
    },

    handleNodeClick(node) {
      this.formData.industryType = node.id;
      this.$refs.selectTree.blur();
      this.$nextTick(function () {
        this.selectTipType = this.$refs.selectTree.selected.label;
      });
    },
    formatData(data) {
      let options = [];
      data.forEach((item, key) => {
        options.push({
          label: item.label,
          value: item.id,
          taxRates: item.taxRates,
        });
        if (item.children) {
          item.children.forEach((items, keys) => {
            options.push({
              label: item.label + "-" + items.label,
              value: items.id,
              taxRates: items.taxRates,
            });
            if (items.children) {
              items.children.forEach((itemss, keyss) => {
                options.push({
                  label: item.label + "-" + items.label + "-" + itemss.label,
                  value: itemss.id,
                  taxRates: itemss.taxRates,
                });
                if (itemss.children) {
                  itemss.children.forEach((itemsss, keysss) => {
                    options.push({
                      label:
                        item.label +
                        "-" +
                        items.label +
                        "-" +
                        itemss.label +
                        "-" +
                        itemsss.label,
                      value: itemsss.id,
                      taxRates: itemsss.taxRates,
                    });
                  });
                }
              });
            }
          });
        }
      });
      return options;
    },
    strlen(str) {
      var len = 0;
      for (var i = 0; i < str.length; i++) {
        var c = str.charCodeAt(i);
        //单字节加1
        if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
          len++;
        } else {
          len += 2;
        }
      }
      return len;
    },
    submitFormName() {
      let fieldsToValidate;
      if (!this.isDisable) {
        // fieldsToValidate = [
        //   "industry",
        //   "fontSize1",
        //   "fontSize2",
        //   "fontSize3",
        //   "fontSize4",
        //   "fontSize5",
        // ];

        // if (!this.formData.fontSize1) {
        //   this.formData.fontSize1 = ".";
        //   this.$refs.fontSize1.focus();
        // }
        // if (!this.formData.fontSize2) {
        //   this.formData.fontSize2 = ".";
        //   this.$refs.fontSize2.focus();
        // }
        // if (!this.formData.fontSize3) {
        //   this.formData.fontSize3 = ".";
        //   this.$refs.fontSize3.focus();
        // }
        // if (!this.formData.fontSize4) {
        //   this.formData.fontSize4 = ".";
        //   this.$refs.fontSize4.focus();
        // }

        // if (!this.formData.fontSize5) {
        //   this.formData.fontSize5 = ".";
        //   this.$refs.fontSize5.focus();
        // }
        fieldsToValidate = ["industry"];
      } else { 
        fieldsToValidate = ["industry"];
      }
      Promise.all(
        fieldsToValidate.map((field) => {
          console.log(field);
          return new Promise((resolve, reject) => {
            this.$refs["elForm"].validateField(field, (errorMessage) => {
              console.log(errorMessage);
              resolve(errorMessage);
            });
          });
        })
      ).then((errorMessages) => {
        console.info(errorMessages);
        let valid = errorMessages.every((errorMessage) => {
          return errorMessage == "";
        });

        console.log(valid); // true/false
        if (valid) {
          this.actives = 1;
        } else {
          // if (this.formData.fontSize1 == ".") {
          //   this.formData.fontSize1 = "";
          // }
          // if (this.formData.fontSize2 == ".") {
          //   this.formData.fontSize2 = "";
          // }
          // if (this.formData.fontSize3 == ".") {
          //   this.formData.fontSize3 = "";
          // }
          // if (this.formData.fontSize4 == ".") {
          //   this.formData.fontSize4 = "";
          // }
          
          // this.$alert("请正确填写", "系统提示", {
          //   confirmButtonText: "确定",

          //   type: "warning",
          // });
          // let that = this;
          // setTimeout(function () {
          //   if ((that.formData.fontSize5 = ".")) {
          //     that.formData.fontSize5 = "";
          //   }
          // }, 1000);
           this.$alert("请正确填写", "系统提示", {
            confirmButtonText: "确定",

            type: "warning",
          });
        }
      });
    },
  
    //客户下拉筛选获取客户结算信息
    singleOK() {
      if (this.formData.isSelfCount == 0) {
        this.formData.disposableRemark='';
        this.formData.isDisposableShare = "1";
        this.formData.disposableShareIsmoney = "0";
        this.formData.disposableShare = "0";
        this.formData.disposableFeeIsmoney = "1";
        this.formData.disposableFee = "0";
        this.formData.isDisposable = "1"; //是否一次性费用
        this.formData.isRegisterMoney = "1"; //是否收取注册服务费

        this.formData.selfShareIsmoney = "0";
        this.formData.isSelfShare = "1";
        this.formData.selfShare = "0";
        this.formData.specialSelfFee = "0";
        this.formData.ordinarySelfFee = "0";
        this.formData.registerMoney = "0";
        this.formData.specialShare = "0";
        this.formData.ordinaryShare = "0";
        this.formData.ordinaryProxyIsmoney = "0"; //普票平台服务费是否定额
        this.formData.specialProxyIsmoney = "0"; //专票平台服务费是否定额
        this.formData.ordinaryShareIsmoney = "0"; //普票分润方式是否定额
        this.formData.specialShareIsmoney = "0"; //专票分润方式是否定额
        this.formData.isOrdinaryShare = "1";
        this.formData.isSpecialShare = "1";
        this.formData.ordinarySpecialTax = "0.03";
        this.formData.ordinaryTax = "0";
        this.formData.isSlider = "1";
        this.formData.isSliderOrdinary = "1";
        this.formData.isSpecialSelfTax = "1";
        this.formData.isSelfTax = "1";
        this.formData.isOrdinaryTax = "1";
        this.formData.isSpecialTax = "1";
      } else {
        
        this.formData.disposableRemark=null;
        this.formData.isDisposableShare = null;
        this.formData.disposableShareIsmoney = null;
        this.formData.disposableShare = null;
        this.formData.disposableFeeIsmoney = null;
        this.formData.disposableFee = null;
        this.formData.isDisposable = null; //是否一次性费用
        this.formData.isRegisterMoney = null; //是否收取注册服务费

        this.formData.selfShareIsmoney = null;
        this.formData.isSelfShare = null;
        this.formData.selfShare = null;
        this.formData.specialSelfFee = null;
        this.formData.ordinarySelfFee = null;
        this.formData.registerMoney = null;
        this.formData.specialShare = null;
        this.formData.ordinaryShare = null;
        this.formData.ordinaryProxyIsmoney = null; //普票平台服务费是否定额
        this.formData.specialProxyIsmoney = null; //专票平台服务费是否定额
        this.formData.ordinaryShareIsmoney = null; //普票分润方式是否定额
        this.formData.specialShareIsmoney = null; //专票分润方式是否定额
        this.formData.isOrdinaryShare = null;
        this.formData.isSpecialShare = null;
        this.formData.ordinarySpecialTax = null;
        this.formData.ordinaryTax = null;
        this.formData.isSlider = null;
        this.formData.isSliderOrdinary = null;
        this.formData.isSpecialSelfTax = null;
        this.formData.isSelfTax = null;
        this.formData.isOrdinaryTax = null;
        this.formData.isSpecialTax = null;
      }
    },
    backBus() {
      this.actives = 3;
    },
    nextYe() {
      let fieldsToValidate = [
        "gender",
        "dateBirth",
        "nation",
        "eduation",
        "politicalStatus",
        "residence",
        "contactPhone",
        "mail",
        "fileName5",
        "fileName6",
        "fileName7",
      ];
      Promise.all(
        fieldsToValidate.map((field) => {
          return new Promise((resolve, reject) => {
            this.$refs["elForm"].validateField(field, (errorMessage) => {
              resolve(errorMessage);
            });
          });
        })
      ).then((errorMessages) => {
        console.info(errorMessages);
        let valid = errorMessages.every((errorMessage) => {
          return errorMessage == "";
        });

        console.log(valid); // true/false
        if (valid) {
          this.actives = 4;
        } else {
          this.$alert("请正确填写", "系统提示", {
            confirmButtonText: "确定",

            type: "warning",
          });
        }
      });
   },
    /** 转换部门数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.deptId,
        label: node.deptName,
        children: node.children,
      };
    },
    getLoginInfo() {
      getInfo().then((res) => {
        console.log(res);
        // this.$cache.local.setJSON("userinfo", res.user);  //个人登录缓存
        this.userinfo = res.user;
        this.formData.userName = res.user.nickName;
        this.formData.userId = res.user.userId;

        crudPlace.getPlaceByUserId({ userId: res.user.userId }).then((res) => {
          this.places = res.data;
        });
      });
    },
    selectAccountType(value) {
      if (value == 1) {
        this.formData.legalPersonName = this.formData.contactName;
        this.isPrivateBank = false;
      } else {
        this.isPrivateBank = true;
        this.formData.legalPersonName = "";
        this.formData.privateDepositBank = "";
        this.formData.privateAccountNumber = "";
      }
    },
    selectIndustryType() {
      var rate = this.industryTypeList.find(
        (item) => item.industryId == this.formData.industryType
      );
      if (rate.taxRate) {
        this.formData.industryTax = rate.taxRate;
        this.industryTax =
          new Decimal(rate.taxRate).mul(new Decimal(100)) + "%";
      } else {
        this.formData.industryTax = "";
        this.industryTax = "";
      }
    },
    selectApplyName(value) {
      var applyName = this.applyNames.find(
        (item) => item.userId == value
      );
      console.log("applyName===",applyName);
      this.formData.applyPhone = applyName.phonenumber;
      this.formData.applyIdNum = applyName.idNo;
      this.formData.applyId = applyName.userId;
   
     
    },
    
    //把数据整成树状
    parseTree(industry, tree, pid) {
      for (var i = 0; i < industry.length; i++) {
        if (industry[i].parentId == pid) {
          let a = industry[i].taxRate;
          let b = null;
          if (a) {
            b = new Decimal(a).mul(new Decimal(100));
            b = "税率" + b + "%";
          } else {
            b = null;
          }
          var obj = {
            id: industry[i].industryId,
            label: industry[i].industryName,
            children: [],
            taxRates: b,
          };
          tree.push(obj);
          this.parseTree(industry, obj.children, obj.id);
        }
      }
    },
    getContactName() {
      crudPerson.getAllPerson().then((res) => {
        this.contactNames = res.rows;
      });
    },
    getApplyName() {
      crudEmployed.getAllUser().then((res) => {
        console.log(" getAllUser==",res.data);
        let arr = res.data;
        let brr = [];
        for (let i in arr) {
          if (arr[i].deptId == 202) {
            brr.push(arr[i]);
          }
        }
        this.applyNames = brr;
      });
    },
    handleClick(tab, event) {},
    nextBasic() {
      let fieldsToValidate = [
        "applyName",
        "contactName",
        "contactPhone",
        "contactIdNum",
      ];
      Promise.all(
        fieldsToValidate.map((field) => {
          return new Promise((resolve, reject) => {
            this.$refs["elForm"].validateField(field, (errorMessage) => {
              resolve(errorMessage);
            });
          });
        })
      ).then((errorMessages) => {
        console.info(errorMessages);
        let valid = errorMessages.every((errorMessage) => {
          return errorMessage == "";
        });

        console.log(valid); // true/false
        if (valid) {
          this.actives = 2;
        } else {
          this.$alert("请正确填写", "系统提示", {
            confirmButtonText: "确定",

            type: "warning",
          });
        }
      });
    },
    resetInfo() {
      this.actives = 0;
    },

    nextBus() {
      let fieldsToValidate;
      if (this.formData.accountType == 1) {
        fieldsToValidate = [
          "natureBusiness",
          "privateDepositBank",
          "privateAccountNumber",
        ];
      } else {
        fieldsToValidate = ["natureBusiness"];
      }

      Promise.all(
        fieldsToValidate.map((field) => {
          return new Promise((resolve, reject) => {
            this.$refs["elForm"].validateField(field, (errorMessage) => {
              resolve(errorMessage);
            });
          });
        })
      ).then((errorMessages) => {
        console.info(errorMessages);
        let valid = errorMessages.every((errorMessage) => {
          return errorMessage == "";
        });

        console.log(valid); // true/false
        if (valid) {
          this.actives = 3;
        } else {
          this.$alert("请填写完整", "系统提示", {
            confirmButtonText: "确定",

            type: "warning",
          });
        }
      });
      
    },
    BackInfo() {
      this.actives = 1;
    },
    //提交表单
    submitForm() {
      this.$refs["elForm"].validate((valid) => {
        if (valid) {
          this.submitNameList(); //检验个体户编号是否重复 不重复就新增数据
        } else {
          this.$alert("请正确填写", "系统提示", {
            confirmButtonText: "确定",

            type: "warning",
          });
        }
      });
    },
    backs() {
      this.actives = 2;
    },

    getPoposedName() {
      this.formData.poposedName1 =
        this.formData.administrativeDivision +
        this.formData.fontSize1 +
        this.formData.industry +
        this.formData.organizationalForm;
      this.formData.poposedName2 =
        this.formData.administrativeDivision +
        this.formData.fontSize2 +
        this.formData.industry +
        this.formData.organizationalForm;
      this.formData.poposedName3 =
        this.formData.administrativeDivision +
        this.formData.fontSize3 +
        this.formData.industry +
        this.formData.organizationalForm;
      this.formData.poposedName4 =
        this.formData.administrativeDivision +
        this.formData.fontSize4 +
        this.formData.industry +
        this.formData.organizationalForm;
      this.formData.poposedName5 =
        this.formData.administrativeDivision +
        this.formData.fontSize5 +
        this.formData.industry +
        this.formData.organizationalForm;
    },
    isRandom() {
      if (!this.formData.random) {
        this.isDisable = false;
      } else {
        this.isDisable = true;
        this.formData.fontSize1 = "";
        this.formData.fontSize2 = "";
        this.formData.fontSize3 = "";
        this.formData.fontSize4 = "";
        this.formData.fontSize5 = "";
      }
    },
    changeOrganizational(value) {
      if (value == 1) {
        this.formData.organizationalForm = "服务部";
      }
      if (value == 2) {
        this.formData.organizationalForm = "经营部";
      }
      if (value == 3) {
        this.formData.organizationalForm = "工作室";
      }
      if (value == 4) {
        this.formData.organizationalForm = "商行";
      }
      if (value == 5) {
        this.formData.organizationalForm = "商店";
      }
      if (value == 6) {
        this.formData.organizationalForm = "店";
      }
    },
    //获取编号
    getSelfCode() {
      //获取员工编号
     crudReview.getCode().then((res) => {
          this.formData.selfCode = res;
        });
    },
    submitNameList() {
      this.$refs["elForm"].validate((valid) => {
        // TODO 提交表单
        if (valid) {
          this.$modal.loading("正在提交中，请稍后...");
          let parms = {
            selfCode: this.formData.selfCode,
            titleType: this.formData.titleType,
            administrativeDivision: this.formData.administrativeDivision,
            industry: this.formData.industry,
            organizationalForm: this.formData.organizationalForm,
            administrativeRegion: this.formData.administrativeRegion,
            registrationAuthority: this.formData.registrationAuthority,
            random: this.formData.random,
            fontSize1: this.formData.fontSize1,
            fontSize2: this.formData.fontSize2,
            fontSize3: this.formData.fontSize3,
            fontSize4: this.formData.fontSize4,
            fontSize5: this.formData.fontSize5,
            poposedName1: this.formData.poposedName1,
            poposedName2: this.formData.poposedName2,
            poposedName3: this.formData.poposedName3,
            poposedName4: this.formData.poposedName4,
            poposedName5: this.formData.poposedName5,
            createBy: this.formData.userName,
            updateBy: this.formData.userName,
            nameStatus: 0,
          };

          crudReview
            .addReview(parms)
            .then((res) => {
              if (res != undefined) {
                if (res.code === 200) {
                  this.submitReg();
                }
              }
            })
            .catch((error) => {
              if (error == "Error: 不允许插入重复单据，自动返回，请重新创建") {
                this.$modal.closeLoading();
                this.getSelfCode();
                this.$alert("已重新获取个体户编码", "系统提示", {
                  confirmButtonText: "确定",
                  type: "success",
                  callback: (action) => {
                    this.$modal.loading("重新提交中，请稍后...");
                    let parms = {
                      selfCode: this.formData.selfCode,
                      titleType: this.formData.titleType,
                      administrativeDivision:
                        this.formData.administrativeDivision,
                      industry: this.formData.industry,
                      organizationalForm: this.formData.organizationalForm,
                      administrativeRegion: this.formData.administrativeRegion,
                      registrationAuthority:
                        this.formData.registrationAuthority,
                      random: this.formData.random,
                      fontSize1: this.formData.fontSize1,
                      fontSize2: this.formData.fontSize2,
                      fontSize3: this.formData.fontSize3,
                      fontSize4: this.formData.fontSize4,
                      fontSize5: this.formData.fontSize5,
                      poposedName1: this.formData.poposedName1,
                      poposedName2: this.formData.poposedName2,
                      poposedName3: this.formData.poposedName3,
                      poposedName4: this.formData.poposedName4,
                      poposedName5: this.formData.poposedName5,
                      createBy: this.formData.userName,
                      updateBy: this.formData.userName,
                      nameStatus: 0,
                    };

                    crudReview.addReview(parms).then((res) => {
                      if (res != undefined) {
                        if (res.code === 200) {
                           this.submitReg();
                        }
                      }
                    });
                  },
                });
              }
            });
        } else {
          this.$alert("请正确填写", "系统提示", {
            confirmButtonText: "确定",
            type: "warning",
          });
        }
      });
    },
    //关闭
    resetForm() {
      this.$tab.closeOpenPage({ path: "/tg-business/reg/employed" });
    },
    //提交注册
    submitReg() {
      let parms1 = {
        selfCode: this.formData.selfCode,

        oneselfApply: this.formData.oneselfApply,
        applyName: this.formData.applyId,
        applyPhone: this.formData.applyPhone,
        applyDocumentType: this.formData.applyDocumentType,
        applyIdNum: this.formData.applyIdNum,

        contactName: this.formData.contactName,
        contactPhone: this.formData.contactPhone,
        contactDocumentType: this.formData.contactDocumentType,
        contactIdNum: this.formData.contactIdNum,
        createBy: this.formData.userName,
        updateBy: this.formData.userName,
      };
      if (Array.isArray(this.formData.fileName5)) {
        this.fileNameNEW1 = JSON.stringify(this.formData.fileName5);
      }
      if (Array.isArray(this.formData.fileName6)) {
        this.fileNameNEW2 = JSON.stringify(this.formData.fileName6);
      }
      if (Array.isArray(this.formData.fileName7)) {
        this.fileNameNEW3 = JSON.stringify(this.formData.fileName7);
      }

      let parms2 = {
        selfCode: this.formData.selfCode,
        userId: this.formData.userId,
        organizationalForm: this.formData.organizationalForm,
        numberEmployees: this.formData.numberEmployees,
        contributionAmount: this.formData.contributionAmount,
        city: this.formData.city,
        county: this.formData.county,
        electronicCommerce: this.formData.electronicCommerce,
        selfAddress: this.formData.selfAddress,
        freeTradeZone: this.formData.freeTradeZone,
        freeTradeArea: this.formData.freeTradeArea,
        propertyRight: this.formData.propertyRight,
        natureBusiness: this.formData.natureBusiness,
        // industryType: this.formData.industryType,
        // industryTax: this.formData.industryTax,
        accountType: this.formData.accountType,
        legalPersonName: this.formData.contactName,

        privateDepositBank: this.formData.privateDepositBank,
        privateAccountNumber: this.formData.privateAccountNumber,
        placeCode: this.formData.placeCode,
        placeName: this.formData.placeName,
        placeAliasName: this.formData.placeAliasName,
        username: this.formData.userName,
        fileName5: this.fileNameNEW1,
        fileName6: this.fileNameNEW2,
        fileName7: this.fileNameNEW3,
        publicDepositBank1: this.formData.publicDepositBank1,
        publicAccountNumber1: this.formData.publicAccountNumber1,
        createBy: this.formData.userName,
        updateBy: this.formData.userName,

        specialSelfFee: this.formData.specialSelfFee,

        ordinarySelfFee: this.formData.ordinarySelfFee,

        isSpecialTax: this.formData.isSpecialTax,
        isOrdinaryTax: this.formData.isOrdinaryTax,

        isSelfTax: this.formData.isSelfTax, //普票含税
        isSpecialSelfTax: this.formData.isSpecialSelfTax, //专票含税

        isSelfCount: this.formData.isSelfCount, //是否结算
        registerMoney: this.formData.registerMoney,

        specialShare: this.formData.specialShare,
        ordinaryShare: this.formData.ordinaryShare,
        
        disposableRemark:this.formData.disposableRemark,
        ordinarySpecialTax: this.formData.ordinarySpecialTax,
        ordinaryTax: this.formData.ordinaryTax,
        isSlider: this.formData.isSlider,
        isSliderOrdinary: this.formData.isSliderOrdinary,
        ordinaryProxyIsmoney: this.formData.ordinaryProxyIsmoney, //普票平台服务费是否定额
        specialProxyIsmoney: this.formData.specialProxyIsmoney, //专票平台服务费是否定额
        ordinaryShareIsmoney: this.formData.ordinaryShareIsmoney, //普票分润方式是否定额
        specialShareIsmoney: this.formData.specialShareIsmoney, //专票分润方式是否定额
        isOrdinaryShare: this.formData.isOrdinaryShare, //普票是否分润
        isSpecialShare: this.formData.isSpecialShare, //普票平台服务费是否定额

        selfShareIsmoney: this.formData.selfShareIsmoney,
        isSelfShare: this.formData.isSelfShare,
        selfShare: this.formData.selfShare,

        isDisposableShare: this.formData.isDisposableShare,
        disposableShareIsmoney: this.formData.disposableShareIsmoney,
        disposableShare: this.formData.disposableShare,
        disposableFeeIsmoney: this.formData.disposableFeeIsmoney,
        disposableFee: this.formData.disposableFee,
        isDisposable: this.formData.isDisposable, //是否一次性费用
        isRegisterMoney: this.formData.isRegisterMoney, //是否收取注册服务费

        businessStatus: 0,
        infoStatus: 0,
        taxStatus: 0,
        bankStatus: 0,
        realnameStatus: 1, //实名认证
      };
      let parms3 = {
        selfCode: this.formData.selfCode,
        legalPersonName: this.formData.contactName,
        personnelType: this.formData.personnelType,
        documentType: this.formData.contactDocumentType,
        idCardNum: this.formData.contactIdNum,
        gender: this.formData.gender,
        dateBirth: this.formData.dateBirth,
        nation: this.formData.nation,
        eduation: this.formData.eduation,
        occupationalStatus: this.formData.occupationalStatus,
        residence: this.formData.residence,
        contactPhone: this.formData.contactPhone,
        mail: this.formData.mail,
        politicalStatus: this.formData.politicalStatus,
        createBy: this.formData.userName,
        updateBy: this.formData.userName,
      };

      crudInfo.addInfo(parms1).then((res) => {
        if (res != undefined) {
          if (res.code === 200) {
          } else {
            this.$message({
              message: res.msg,
              type: "danger",
            });
          }
        }
      });
      crudEmployed.addEmployed(parms2).then((res) => {
        if (res != undefined) {
          if (res.code === 200) {
          } else {
            this.$message({
              message: res.msg,
              type: "danger",
            });
          }
        }
      });

      crudPerson.addPerson(parms3).then((res) => {
        if (res != undefined) {
          if (res.code === 200) {
            this.$message({
              message: res.msg,
              type: "success",
            });
          } else {
            this.$message({
              message: res.msg,
              type: "danger",
            });
          }
          let parms = {
            checkReasult: res.msg,
            checkUser: this.userinfo.userName,
            phonenumber: this.userinfo.phonenumber,
            selfCode: this.formData.selfCode,
            selfType: "1",
          };
          crudEmployed.check(parms).then((res) => {
            this.$modal.closeLoading();
            if (res != undefined) {
              if (res.code === 200) {
                this.$tab.closeOpenPage({ path: "/tg-business/reg/regSuccess" });
              } else {
                this.$message({
                  message: res.msg,
                  type: "danger",
                });
              }
            }
          });
        }
      });
    },
  },
};
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
// 改变input框字体颜色
::v-deep .is-disabled .el-input__inner {
  background-color: transparent !important;
  color: black;
}

.btn {
  width: 90px;
}

.combottom {
  margin-bottom: 10px;
}

.rowCss {
  margin-top: 10px;
}

.comright {
  padding-right: 10%;
}

.bankno {
  letter-spacing: 2px;

  font-size: 20px;

  color: blue;
}

.flexs {
  display: flex;
  justify-content: center;
}

::v-deep .el-collapse {
  border: 0 !important;
}

::v-deep .el-collapse-item__header {
  border-bottom: 0 !important;
  font-size: 14px !important;
  color: #606266 !important;
  font-weight: bold !important;
}

::v-deep .el-icon-arrow-right:before {
  color: #333;
}
</style>
