<template>
  <div>
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="auto">
      <el-row type="flex" class="row-bg rowCss" style="margin-top:20px" justify="space-around">
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width:35%">申请信息</div>
          <div style="width:50%;hegiht:10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss " justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="本人申请" prop="oneselfApply">
            <el-select style="width:100%" v-model="formData.oneselfApply" placeholder="请选择是否本人申请" disabled>
              <el-option v-for="(item, index) in oneselfApplys" :key="index" :label="item.label" :value="item.label"
                :disabled="item.disabled"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="9"></el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="申请人姓名" prop="applyName">
            <el-select style="width:100%" disabled v-model="formData.applyName" placeholder="请选择申请人姓名" clearable
              filterable @change="selectApplyName">
              <el-option v-for="(item, index) in applyNames" :key="index" :label="item.username" :value="item.userId"
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
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width:30%">联络员</div>
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
          <el-form-item class="comright" label="证件号码" prop="contactIdNum">
            <el-input :readonly="true" v-model="formData.contactIdNum" clearable>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width:35%">基本情况</div>
          <div style="width:50%;hegiht:10px"></div>
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
            <el-input :readonly="true" type="textarea" :rows="2" placeholder="请输入经营范围"
              v-model="formData.natureBusiness">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg rowCss" justify="space-around">
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
      </el-row>
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
          <el-form-item class="comright" label="私账名称">
            <el-input v-model="formData.legalPersonName" :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="私账开户银行">
            <el-input :readonly="true" v-model="formData.privateDepositBank" clearable>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="私账银行账号">
            <el-input v-model="formData.privateAccountNumber" clearable :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
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
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
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
          <el-form-item class="comright" label="客户经理" prop="userName">
            <el-input v-model="formData.userName" :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>

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
            <el-input :readonly="true" v-model="formData.residence" clearable>
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
          <el-form-item label="身份证扫描件">
            <div v-for="(item, index) in fileName5" :key="index">
              <el-image lazy :preview-src-list="fileName5" style="width: 150px; height: 150px" :src="item" alt="" />
            </div>
          </el-form-item>
        </el-col>
        <el-col :span="9"></el-col>
      </el-row>
      <!-- <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright">
            <el-radio v-model="isokradio" label="1"> 通过</el-radio>
            <el-radio v-model="isokradio" label="2">不通过 </el-radio>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="驳回理由">
            <el-input v-model="remark" :disabled="isokradio == 1"></el-input>
          </el-form-item>
        </el-col>
      </el-row> -->
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width:35%">审核操作</div>
          <div style="width:50%;hegiht:10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>


      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="21">
          <el-form-item class="comright" style="padding-right: 4.2%;margin-left: -7%;">
            <el-radio v-model="isokradio" label="1"> 通过</el-radio>

          </el-form-item>
        </el-col>

      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="21">
          <el-form-item class="comright" style="padding-right: 4.2%;margin-left: -7%;">
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
          <el-button type="danger" @click="toReturn2">返回</el-button>
          <el-button v-if="isokradio == 2" type="primary" @click="submitForm3(2)">驳回</el-button>
          <el-button v-else type="primary" @click="submitForm3(1)">通过</el-button>

        </el-col>
        <el-col :span="8"></el-col>
      </el-row>



    </el-form>
  </div>
</template>
<script>
import { addEmployed, updateEmployed } from "@/api/company/employed";
import crudInformation from '@/api/company/information'
import crudPerson from '@/api/company/person'
import crudInfo from '@/api/company/info'
import crudEmployed from '@/api/company/employed'
import crudRate from '@/api/company/rate'
import crudPlace from '@/api/company/place'
import { getInfo } from '@/api/login'
// import the component
import Treeselect from '@riophae/vue-treeselect'
// import the styles
import '@riophae/vue-treeselect/dist/vue-treeselect.css'
export default {
  components: { Treeselect },
  dicts: ['political_status', 'educational_level'],
  components: {},
  props: [],
  data() {
    return {
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
      fileName5: [],
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
        userName: '',

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
      userinfo:{},
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
        }, {
          pattern: /^[1-9]\d{5}(18|19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/,
          message: '身份证号格式错误',
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
        userName: [{
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
    this.getRate();
    //从上一个页面获取个体户编码
    // this.formData.selfCode = JSON.parse(window.localStorage.getItem('selfCode'));
    // this.formData.organizationalForm = JSON.parse(window.localStorage.getItem('organizationalForm'));
    // console.log("selfCode==", this.formData.selfCode)

    this.formData = this.$cache.local.getJSON('employedInfo');
    this.formData.gender = parseInt(this.formData.gender);
    this.formData.accountType = parseInt(this.formData.accountType);
    this.formData.electronicCommerce = parseInt(this.formData.electronicCommerce);
    this.formData.applyName = parseInt(this.formData.applyName);
    this.fileName5 = JSON.parse(this.$cache.local.getJSON('employedInfo').fileName5);
    for (let k1 in this.fileName5) {
      this.fileName5[k1] = this.baseImgPath + this.fileName5[k1];
    }
  },
  methods: {
    getLoginInfo() {
      getInfo().then(res => {
        this.userinfo=res.user;
        this.formData.userName = res.user.nickName;
        crudPlace.getPlaceByUserId({ userId: 26 }).then(res => {
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
      crudInformation.getAllInformation().then(res => {
        console.log("getApplyName", res.rows);
        this.applyNames = res.rows;
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
              remark: this.remark,
              endStatus:2,
            };
          }



          updateEmployed(parms).then(res => {


            if (res != undefined) {
              if (res.code === 200) {

                this.$nextTick(function () {
                  this.$tab.refreshPage({ path: "/company/customer/manageInfo" }).then(() => {
                    let resmsg = '';
                    if (type == 1) {
                      resmsg = '信息审核完成';
                      this.check('信息审核完成');
                    } else {
                      this.check('信息审核未通过'+'(原因)'+this.remark);
                      resmsg = '信息审核完成';
                    }

                   let obj = {
                      title: '信息审核',
                      backUrl: '/company/customer/manageInfo',
                      resmsg: resmsg

                    }
                    this.$cache.local.setJSON('successNew', obj);
                    this.$tab.closeOpenPage({ path: "/company/customer/successNew" });
                  });
                });

              } else {
                this.$modal.msgError(res.msg);
                this.$tab.closeOpenPage({ path: "/company/customer/manageInfo" });
              }

            }




          });

        } else {
          this.$message({
            message: '请填写完整',
            type: 'warning'
          })
        }
      })

    },
    toReturn2() {
      this.$tab.closeOpenPage({ path: "/company/customer/manageInfo" });
    },

    //新增信息审核进度
    check(resmsg) {
      let parms = {
        "checkReasult": resmsg,
        "checkUser": this.userinfo.userName,
        'phonenumber':this.userinfo.phonenumber,
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
</style>
