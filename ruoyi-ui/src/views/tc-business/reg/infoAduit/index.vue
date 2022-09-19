<template>
  <div>
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="auto">
      <el-row type="flex" class="row-bg rowCss" style="margin-top:20px" justify="space-around">
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width:40%">申请信息</div>
          <div style="width:50%;hegiht:10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss " justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="是否本人申请" prop="oneselfApply">
            <!-- <el-select style="width:100%" v-model="formData.oneselfApply" placeholder="请选择是否本人申请" disabled>
              <el-option v-for="(item, index) in oneselfApplys" :key="index" :label="item.label" :value="item.label"
                :disabled="item.disabled"></el-option>
            </el-select> -->
               <el-radio disabled v-model="formData.oneselfApply" label="是">是</el-radio>
               <el-radio disabled v-model="formData.oneselfApply" label="否">否</el-radio>
          </el-form-item>
        </el-col>
        <el-col :span="9"></el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="申请人姓名" prop="applyName">
            <el-select style="width:100%" disabled v-model="formData.applyName" placeholder="请选择申请人姓名" clearable
              filterable @change="selectApplyName">
              <el-option v-for="(item, index) in applyNames" :key="index" :label="item.nickName" :value="item.userId"
                :disabled="item.disabled"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="9"></el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="移动电话">
            <el-input v-model="formData.applyPhone" :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9"></el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="证件类型" prop="applyDocumentType">
            <el-input v-model="formData.applyDocumentType" :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="证件号码">
            <el-input v-model="formData.applyIdNum" :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="8" class="flexs">
          <div class="bankno" style="width:40%">联络员</div>
          <div style="width:50%;hegiht:10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="姓名" prop="contactName">
            <el-input :readonly="true" v-model="formData.contactName" clearable>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="移动电话" prop="contactPhone">
            <el-input :readonly="true" v-model="formData.contactPhone" clearable>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="证件类型" prop="contactDocumentType">
            <el-input v-model="formData.contactDocumentType" :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="证件号码"  :required="true">
            <el-input :readonly="true" v-model="formData.contactIdNum" clearable>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width:50%">基本情况</div>
          <div style="width:40%;hegiht:10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="组织形式" prop="organizationalForm">
            <el-input v-model="formData.organizationalForm" :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="从业人数" prop="numberEmployees">
            <el-input v-model="formData.numberEmployees" :readonly="true">
              <template slot="append">人</template>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="出资额">
            <el-input disabled type="number" v-model="formData.contributionAmount">
              <template slot="append">万元</template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="城市" prop="city">
            <el-input v-model="formData.city" :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="区（县）" prop="county">
            <el-input v-model="formData.county" :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="电子商务经营者" prop="electronicCommerce">
            <el-select style="width:100%" v-model="formData.electronicCommerce" placeholder="请选择是否是电子商务经营者" disabled>
              <el-option v-for="(item, index) in electronicCommerces" :key="index" :label="item.label"
                :value="item.value"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="21">
          <el-form-item style="padding-right:4.2%" label="经营场所" prop="selfAddress">
            <el-input v-model="formData.selfAddress" :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>

      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="联系电话" prop="contactPhone">
            <el-input v-model="formData.contactPhone" :change="true">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="所属自贸区" prop="freeTradeZone">
            <el-input v-model="formData.freeTradeZone" :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="所属自贸片区" prop="freeTradeArea">
            <el-input v-model="formData.freeTradeArea" :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="产权" prop="propertyRight">
            <el-input v-model="formData.propertyRight" :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="21">
          <el-form-item style="padding-right:4.2%" label="经营范围" prop="natureBusiness">
            <el-input 
             maxlength="500"
             show-word-limit
            :readonly="true" type="textarea" :rows="2" placeholder="请输入经营范围"
              v-model="formData.natureBusiness">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="行业类型" prop="industryType">
            <el-select style="width:100%" disabled v-model="formData.industryType" placeholder="请选择行业类型" clearable
              @change="selectIndustryType">
              <el-option v-for="(item, index) in industryTypes" :key="index" :label="item.industryName"
                :value="item.industryId"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="行业税率">
            <el-input v-model="formData.industryTax" :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row> -->
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="银行账号类型" prop="accountType">
            <el-select style="width:100%" disabled v-model="formData.accountType" placeholder="请选择银行账号类型"
              @change="selectAccountType">
              <el-option v-for="(item, index) in accountTypes" :key="index" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="私账名称"  v-if="formData.accountType==1">
            <el-input v-model="formData.legalPersonName" :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around" v-if="formData.accountType==1">
        <el-col :span="9">
          <el-form-item class="comright" label="私账开户银行">
            <el-input :readonly="true" v-model="formData.privateDepositBank" clearable>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="私账银行账号" v-if="formData.accountType==1">
            <el-input v-model="formData.privateAccountNumber" clearable :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <!-- <el-row type="flex" class="row-bg rowCss" justify="space-around" v-if="formData.accountType==2">
        <el-col :span="9">
          <el-form-item class="comright" label="对公开户银行">
            <el-input :readonly="true" v-model="formData.publicDepositBank1" clearable>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="对公银行账号">
            <el-input v-model="formData.publicAccountNumber1" clearable :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row> -->
      <!-- <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="渠道商" prop="placeName">
            <el-select style="width:100%" disabled v-model="formData.placeName" placeholder="请选择渠道商" clearable
              filterable>
              <el-option v-for="(item, index) in places" :key="index" :label="item.placeName" :value="item.placeName">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="客户经理" prop="username">
            <el-input v-model="formData.username" :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row> -->

      <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="9">
          <div class="bankno">经营者（负责人）信息</div>

        </el-col>
        <el-col :span="9">

        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="姓名">
            <el-input v-model="formData.contactName" :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="人员类型" prop="personnelType">
            <el-input v-model="formData.personnelType" :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="证件类型" prop="contactDocumentType">
            <el-input v-model="formData.contactDocumentType" :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="证件号码">
            <el-input v-model="formData.contactIdNum" :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="性别" prop="gender">
            <el-select style="width:100%" disabled v-model="formData.gender" placeholder="请选择性别" clearable>
              <el-option v-for="(item, index) in genders" :key="index" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="出生日期" prop="dateBirth">
            <el-date-picker style="width:100%" disabled v-model="formData.dateBirth" type="date" placeholder="选择出生日期">
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="民族" prop="nation">
            <el-input :readonly="true" v-model="formData.nation" clearable>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="文化程度" prop="eduation">
            <el-select style="width:100%" disabled v-model="formData.eduation" placeholder="文化程度" clearable>
              <el-option v-for="dict in dict.type.educational_level" :key="dict.value" :label="dict.label"
                :value="dict.value" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="申请前职业状况" prop="occupationalStatus">
            <el-input v-model="formData.occupationalStatus" :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="政治面貌" prop="politicalStatus">
            <el-select style="width:100%" disabled v-model="formData.politicalStatus" placeholder="文化程度" clearable>
              <el-option v-for="dict in dict.type.political_status" :key="dict.value" :label="dict.label"
                :value="dict.value" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="21">
          <el-form-item style="padding-right:4.2%" label="经营者居所" prop="residence">
            <el-input 
              maxlength="250"
              show-word-limit
              type="textarea" :rows="2"
            :readonly="true" v-model="formData.residence" clearable>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="联系电话" prop="contactPhone">
            <el-input :readonly="true" v-model="formData.contactPhone" clearable>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="电子邮箱" prop="mail">
            <el-input :readonly="true" v-model="formData.mail" clearable>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      
      <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="9">
          <el-form-item label="工商实名">
            <uploadSmall v-if="fileNameN2.length > 0" @getfileName="getfileNs" :fileName="isNone"
              :fileNameOld="fileNameN2" :isDetail="isDetail"></uploadSmall>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item label="税务实名">
            <uploadSmall v-if="fileNameN3.length > 0" @getfileName="getfileNs" :fileName="isNone"
              :fileNameOld="fileNameN3" :isDetail="isDetail"></uploadSmall>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="9">
          <el-form-item label="身份证扫描件">
            <uploadSmall v-if="fileNameN2.length > 0" @getfileName="getfileNs" :fileName="isNone"
              :fileNameOld="fileNameN1" :isDetail="isDetail"></uploadSmall>
          </el-form-item>
        </el-col>
        <el-col :span="9"></el-col>
      </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="21">
          <el-form-item class="comright" style="padding-right: 4.2%;margin-left: -5%;">
            <el-radio v-model="isokradio" label="1"> 通过</el-radio>

          </el-form-item>
        </el-col>

      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="21">
          <el-form-item class="comright" style="padding-right: 4.2%;margin-left: -5%;">
            <div style="display: flex; align-items: center;justify-content: flex-start;">
              <el-radio v-model="isokradio" label="2">不通过 </el-radio>
              <el-input type="textarea" placeholder="请输入不通过说明" v-model="remark" :disabled="isokradio == 1"></el-input>
            </div>


          </el-form-item>
        </el-col>

      </el-row>

      <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="8"></el-col>
        <el-col :span='8' class="flexs">
          <el-button type="danger" @click="closeForms">关闭</el-button>
          <el-button v-if="isokradio == 2" type="primary" @click="submitForm3(2)">提交</el-button>
          <el-button v-else type="primary" @click="submitForm3(1)">提交</el-button>

        </el-col>
        <el-col :span="8"></el-col>
      </el-row>



    </el-form>
  </div>
</template>
<script>
import uploadSmall from '@/components/douploads/uploadSmall'
import {updateEmployed } from "@/api/tc-api/company/employed";
import crudPerson from '@/api/tc-api/company/person'
import crudEmployed from '@/api/tc-api/company/employed'
import crudRate from '@/api/tc-api/company/rate'
import crudPlace from '@/api/tc-api/company/place'
import { getInfo } from '@/api/login'
import { Decimal } from 'decimal.js'
export default {
  name: "InfoAduit",
  components: { uploadSmall },
  dicts: ['political_status', 'educational_level'],

  props: [],
  data() {
    return {
      activeNamese:'1',
      activeNameseg:'1',
      specialShare: '1',
      ordinaryShare: '1',
      industryTax: '',
      optiond: [
        {
          value: '0',
          label: '免税'
        }, {
          value: '0.01',
          label: '1%'
        }, {
          value: '0.03',
          label: '3%'
        },
      ],
      optionz: [
        {
          value: '0.01',
          label: '1%'
        }, {
          value: '0.03',
          label: '3%'
        },
      ],
      singleRadio: '1',
      basicRadio: '1',
      vipRadio: '1',
      isNone: [],
      isDetail: '1',
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
      fileName5: [],
      fileName6: [],
      fileName7: [],
      fileNameN1: [],
      fileNameN2: [],
      fileNameN3: [],
      remark: null,
      isokradio: '1',
      center: 'center',
      options: [{
        id: 'a',
        label: 'a',
        children: [{
          id: 'aa',
          label: 'aa',
        }, {
          id: 'ab',
          label: 'ab',
        }],
      }, {
        id: 'b',
        label: 'b',
      }, {
        id: 'c',
        label: 'c',
      }],
      isPrivateBank: false,
      activeName: 'first',
      oneselfApplys: [
        {
          value: 1,
          label: '是',
        },
        {
          value: 2,
          label: '否',
        },
      ],
      electronicCommerces: [
        {
          value: 1,
          label: '是',
        },
        {
          value: 2,
          label: '否',
        },
      ],
      genders: [
        {
          value: 1,
          label: '男',
        },
        {
          value: 2,
          label: '女',
        },
      ],
      eduations: [],
      politicalStatuList: [],
      applyNames: [],
      contactNames: [],
      industryTypes: [],
      accountTypes: [
        {
          value: 1,
          label: '私人账号',
        },
        {
          value: 2,
          label: '对公账号',
        },
      ],
      places: [],

      formData: {
        selfCode: '',

        //申请信息
        oneselfApply: '否',
        applyName: '',
        applyPhone: '',
        applyDocumentType: '中华人民共和国居民身份证',
        applyIdNum: '',
        //联络员
        contactName: '',
        contactPhone: '',
        contactDocumentType: '中华人民共和国居民身份证',
        contactIdNum: '',

        //基本情况
        organizationalForm: '',
        numberEmployees: 5,
        contributionAmount: '',
        city: '龙岩市',
        county: '漳平市',
        electronicCommerce: 2,//1是 2否  是否是电子商务经营者
        selfAddress: '漳平市菁城街道双拥路202号A栋210室（集群注册）',
        freeTradeZone: '无',
        freeTradeArea: '不属自贸区',
        propertyRight: '租赁',
        natureBusiness: '',
        industryType: '',
        industryTax: '',
        accountType: 1,
        legalPersonName: '',
        privateDepositBank: '',
        privateAccountNumber: '',
        placeName: '',
        username: '',

        //经营者
        personnelType: '待业人员',
        gender: '',//1 男 2女
        dateBirth: '',
        nation: '',
        eduation: '',
        occupationalStatus: '待业人员',
        residence: '',
        mail: '',
        idCard: '',

      },
      userinfo: {},
      rules: {
        oneselfApply: [{
          required: true,
          message: '请选择是否本人申请',
          trigger: 'change'
        }],
        applyName: [{
          required: true,
          message: '请选择申请人',
          trigger: 'change'
        }],
        applyDocumentType: [{
          required: true,
          message: '请输入证件类型',
          trigger: 'blur'
        }],

        contactName: [{
          required: true,
          message: '请输入联系人',
          trigger: 'blur'
        }],
        contactPhone: [{
          required: true,
          message: '请输入手机号',
          trigger: 'blur'
        }, {
          pattern: /^1(3|4|5|7|8|9)\d{9}$/,
          message: '手机号格式错误',
          trigger: 'blur'
        }],
        contactDocumentType: [{
          required: true,
          message: '请输入证件类型',
          trigger: 'blur'
        }],
        contactIdNum: [{
          required: true,
          message: '请输入证件号码',
          trigger: 'blur'
        }],

        organizationalForm: [{
          required: true,
          message: '请输入组织形式',
          trigger: 'blur'
        }],
        numberEmployees: [{
          required: true,
          message: '请输入从业人数',
          trigger: 'blur'
        }],
        contributionAmount: [{
          required: true,
          message: '请输入出资额',
          trigger: 'blur'
        }],
        city: [{
          required: true,
          message: '请输入城市',
          trigger: 'blur'
        }],
        county: [{
          required: true,
          message: '请输入区（县）',
          trigger: 'blur'
        }],
        electronicCommerce: [{
          required: true,
          message: '请选择是否是电子商务经营者',
          trigger: 'change'
        }],
        selfAddress: [{
          required: true,
          message: '请输入经营场所',
          trigger: 'blur'
        }],
        selfAddress: [{
          required: true,
          message: '请输入所属自贸区',
          trigger: 'blur'
        }],
        freeTradeZone: [{
          required: true,
          message: '请输入所属自贸区',
          trigger: 'blur'
        }],
        freeTradeArea: [{
          required: true,
          message: '请输入所属自贸片区',
          trigger: 'blur'
        }],
        propertyRight: [{
          required: true,
          message: '请输入产权',
          trigger: 'blur'
        }],
        natureBusiness: [{
          required: true,
          message: '请输入经营范围',
          trigger: 'blur'
        }],
        industryType: [{
          required: true,
          message: '请选择行业类型',
          trigger: 'change'
        }],
        accountType: [{
          required: true,
          message: '请选择银行账号类型',
          trigger: 'change'
        }],
        placeName: [{
          required: true,
          message: '请选择渠道商',
          trigger: 'change'
        }],
        username: [{
          required: true,
          message: '请输入客户经理',
          trigger: 'blur'
        }],

        personnelType: [{
          required: true,
          message: '请输入人员类型',
          trigger: 'blur'
        }],
        gender: [{
          required: true,
          message: '请选择性别',
          trigger: 'change'
        }],
        dateBirth: [{
          required: true,
          message: '选择出生日期',
          trigger: 'change'
        }],
        nation: [{
          required: true,
          message: '请输入民族',
          trigger: 'blur'
        }],
        eduation: [{
          required: true,
          message: '请选择文化程度',
          trigger: 'change'
        }],
        occupationalStatus: [{
          required: true,
          message: '请输入申请前职业状况',
          trigger: 'blur'
        }],
        politicalStatus: [{
          required: true,
          message: '请选择政治面貌',
          trigger: 'change'
        }],
        residence: [{
          required: true,
          message: '请输入经营者居所',
          trigger: 'blur'
        }],
        mail: [{
          required: true,
          message: '请输入电子邮箱地址',
          trigger: 'blur'
        }, {
          pattern: /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
          message: '邮箱地址格式错误',
          trigger: 'blur'
        }],
      },
      wordTypeOptions: [{
        "label": "选项一",
        "value": 1
      }],
    }
  },
  computed: {},
  watch: {
    'formData.contactName': {
      handler: function () {
        this.formData.legalPersonName = this.formData.contactName
      },
      deep: true
    }
  },
  created() {

  },
  mounted() {

    this.getLoginInfo();
    //申请人
    this.getApplyName();
    //联系人
    this.getContactName();
    //个体户行业类型税率
    //this.getRate();
    //从上一个页面获取个体户编码


    this.formData = this.$cache.local.getJSON('tc-infolist');
    //this.industryTax = new Decimal(this.formData.industryTax).mul(new Decimal(100)) + '%';
    this.formData.gender = parseInt(this.formData.gender);
    this.formData.accountType = parseInt(this.formData.accountType);
    this.formData.electronicCommerce = parseInt(this.formData.electronicCommerce);
    this.formData.applyName = parseInt(this.formData.applyName);
    this.fileName5 = JSON.parse(this.$cache.local.getJSON('tc-infolist').fileName5);
    this.fileNameN1 = [];
    this.fileNameN2 = [];
    this.fileNameN3 = [];

    this.fileName5 = JSON.parse(this.$cache.local.getJSON('tc-infolist').fileName5);
    for (let k1 in this.fileName5) {
      this.fileNameN1.push({
        url: this.baseImgPath + this.fileName5[k1],
        name: this.fileName5[k1],
      });

    }
    this.fileName6 = JSON.parse(this.$cache.local.getJSON('tc-infolist').fileName6);
    for (let k2 in this.fileName6) {
      this.fileNameN2.push({
        url: this.baseImgPath + this.fileName6[k2],
        name: this.fileName6[k2],
      });
    }
    this.fileName7 = JSON.parse(this.$cache.local.getJSON('tc-infolist').fileName7);
    for (let k3 in this.fileName7) {
      this.fileNameN3.push({
        url: this.baseImgPath + this.fileName7[k3],
        name: this.fileName7[k3],
      });
    }

    this.formData.ordinaryTax = JSON.stringify(this.formData.ordinaryTax);
    this.formData.ordinarySpecialTax = JSON.stringify(this.formData.ordinarySpecialTax);
    this.formData.isSelfTax=JSON.stringify(this.formData.isSelfTax);
    this.formData.isSpecialSelfTax=JSON.stringify(this.formData.isSpecialSelfTax);
    this.formData.isSelfCount=JSON.stringify(this.formData.isSelfCount);


    this.formData.ordinaryProxyIsmoney=JSON.stringify(this.formData.ordinaryProxyIsmoney);
    this.formData.specialProxyIsmoney=JSON.stringify(this.formData.specialProxyIsmoney);
    this.formData.ordinaryShareIsmoney=JSON.stringify(this.formData.ordinaryShareIsmoney);
    this.formData.specialShareIsmoney=JSON.stringify(this.formData.specialShareIsmoney);
    this.formData.isOrdinaryShare=JSON.stringify(this.formData.isOrdinaryShare);
    this.formData.isSpecialShare=JSON.stringify(this.formData.isSpecialShare);

    
    
    if (this.formData.isOrdinaryTax == 1) {
      this.formData.isOrdinaryTax = '1';
    } else {
      this.formData.isOrdinaryTax = '0';
    }
    if (this.formData.isSpecialTax == 1) {
      this.formData.isSpecialTax = '1';
    } else {
      this.formData.isSpecialTax = '0';
    }


     if(this.formData.isSelfCount=='0'){
          this.formData.isSelfCount='0';
    }else{
          this.formData.isSelfCount='1';
    }
     
     if(this.formData.isSelfTax=='0'){
          this.formData.isSelfTax='0';
    }else{
          this.formData.isSelfTax='1';
    }
      if(this.formData.isSpecialSelfTax=='0'){
          this.formData.isSpecialSelfTax='0';
    }else{
          this.formData.isSpecialSelfTax='1';
    }
  },
  methods: {
    getfileNs(){

    },
    getLoginInfo() {
      getInfo().then(res => {
        this.userinfo = res.user;
        crudPlace.getPlaceByUserId({ userId: this.formData.userId }).then(res => {
          console.log("getPlaceByUserId==", res.data);
          this.places = res.data;
        })
      })
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
    selectIndustryType(value) {
      var rate = this.industryTypes.find((item) => item.industryId == value);
      this.formData.industryTax = rate.taxRate;
      this.industryTax = new Decimal(this.formData.industryTax).mul(new Decimal(100)) + '%';
      console.log("rate==", rate);
    },
    selectApplyName(value) {
      var applyName = this.applyNames.find((item) => item.userId == value);
      this.formData.applyPhone = applyName.phone;
      this.formData.applyIdNum = applyName.idNo;
      console.log("applyName==", applyName);
    },
    getRate() {
      crudRate.getAllRate().then(res => {
        console.log("getAllRate", res.rows);
        this.industryTypes = res.rows;
      })
    },
    getContactName() {
      crudPerson.getAllPerson().then(res => {
        console.log("getContactName", res.rows);
        this.contactNames = res.rows;
      })
    },
    getApplyName() {
      crudEmployed.getAllUser().then(res => {
        let arr = res.data;
        let brr = [];
        for (let i in arr) {
          if (arr[i].deptId == 202) {
            brr.push(arr[i]);
          }
        }
        this.applyNames = brr;
      })
    },
    handleClick(tab, event) {
      console.log(tab, event);
    },
    submitForm1() {
      this.activeName = 'second';
    },
    resetForm1() {
      this.$refs['elForm'].resetFields()
    },

    submitForm2() {
      this.activeName = 'third';
    },
    toReturn1() {
      this.activeName = 'first';
    },

    submitForm3(type) {

      this.$refs['elForm'].validate(valid => {
        // TODO 提交表单
        if (valid) {
          let parms;
          if (type == 1) {
            parms = {
              selfId: this.formData.selfId,
              infoStatus: type,
            };
          } else {
            parms = {
              selfId: this.formData.selfId,
              infoStatus: type,
              remarkInfo: this.remark,
              endStatus: 2,
            };
          }



          updateEmployed(parms).then(res => {


            if (res != undefined) {
              if (res.code === 200) {

                this.$nextTick(function () {
                    if (type == 1) {
                      this.check('信息审核完成');
                    } else {
                      this.check('信息审核不通过。' + '原因:' + this.remark);
                    }
                    this.$tab.closeOpenPage({ path: "/tc-business/reg/result" });
                 
                });

              } else {
                this.$modal.msgError(res.msg);
                
              }

            }




          });

        } else {
            this.$alert('请正确填写', '系统提示', {
              confirmButtonText: '确定',
             
              type: 'warning'
           });
        }
      })

    },
    closeForms() {
      this.$tab.closeOpenPage({ path: this.$cache.local.getJSON('tc-resultBack').backUrl}).then(() => {
        this.$tab.refreshPage({ path: this.$cache.local.getJSON('tc-resultBack').backUrl,name:this.$cache.local.getJSON('tc-resultBack').backName});
     })
    },

    //新增信息审核进度
    check(resmsg) {
      let parms = {
        "checkReasult": resmsg,
        "checkUser": this.userinfo.userName,
        'phonenumber': this.userinfo.phonenumber,
        "selfCode": this.formData.selfCode,
        "selfType": "3",
      }
      crudEmployed.check(parms).then(res => {
        console.log('名称审核插入日志成功！');
      }).catch(error => {

      });
    },
  }
}

</script>
<style rel="stylesheet/scss" lang="scss" scoped>
.rowCss {
  margin-top: 10px;
}

.comright {
  padding-right: 10%;
}

.flexs {
  display: flex;
  justify-content: center;

}

.bankno {
  display: flex;
  justify-content: space-between;
  letter-spacing: 2px !important;
  font-size: 20px !important;
  color: blue !important;
  margin-bottom: 10px !important;
}

// 改变input框字体颜色
::v-deep .is-disabled .el-input__inner {
  background-color: transparent !important;
  color: black;
}

// 改变input框字体颜色
::v-deep textarea {
  background-color: transparent !important;
  color: black !important;
}

.paddingbg-s {
  padding-top: 15px;
}

.footers {
  display: flex;
  justify-content: center;
  align-items: center;
}

::v-deep .el-tabs__nav-scroll {
  width: 50% !important;
  margin: 0 auto !important;
}
::v-deep .el-input.is-disabled .el-input__inner{
   background-color: rgba(255, 255, 255, 1.5) !important;
   color: black  !important;
   border-color: rgba(135,206,250,0.7) !important;
}
::v-deep .el-input-group__append{
   background-color: rgba(255, 255, 255, 1.5) !important;
   color: black  !important;
   border-color: rgba(135,206,250,0.7) !important;
}


::v-deep .el-collapse {
  border: 0  !important;
 
}
::v-deep .el-collapse-item__header{
  border-bottom:0 !important;
  font-size: 14px !important;
  color: #606266 !important;
  font-weight: bold !important;
}
::v-deep .el-icon-arrow-right:before{
   color:#333;
}

</style>
