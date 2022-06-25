<template>
  <div>
    <el-form 
      ref="elForm" 
      :model="formData" 
      :rules="rules" 
      size="medium" 
      label-width="120px"
      label-position="right">
      <el-steps :space="1500" 
        :active="2" 
        finish-status="success"
        style="margin-left:280px;margin-top:30px;margin-bottom:50px;">
        <el-step title="个体户名称"></el-step>
        <el-step title="个体户信息"></el-step>
        <el-step title="提交"></el-step>
      </el-steps>

      <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
        <el-tab-pane label="申请信息" name="first">
            <div 
              style="font-size:20px;
              margin-left: 300px;
              margin-top: 20px;
              margin-bottom: 40px;
              color:blue;">申请信息</div>
            <el-row class="rowCss" :gutter="60" style="margin-left:260px">
              <el-col :span="8">
                <el-form-item label="本人申请" prop="oneselfApply">
                  <el-select 
                    v-model="formData.oneselfApply" 
                    placeholder="请选择是否本人申请" 
                    disabled
                    >
                    <el-option 
                      v-for="(item, index) in oneselfApplys" 
                      :key="index" 
                      :label="item.label"
                      :value="item.label" 
                      :disabled="item.disabled"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="rowCss" :gutter="60" style="margin-left:260px">
              <el-col :span="8">
                <el-form-item label="申请人姓名" prop="applyName">
                  <el-select 
                    v-model="formData.applyName" 
                    placeholder="请选择申请人姓名" 
                    clearable
                    filterable 
                    @change="selectApplyName"
                    >
                    <el-option 
                      v-for="(item, index) in applyNames" 
                      :key="index" 
                      :label="item.username"
                      :value="item.userId" 
                      :disabled="item.disabled"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="rowCss" :gutter="60" style="margin-left:260px">
              <el-col :span="8">
                <el-form-item label="移动电话" >
                  <el-input v-model="formData.applyPhone"  disabled >
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="rowCss" :gutter="60" style="margin-left:260px">
              <el-col :span="8">
                <el-form-item label="证件类型" prop="applyDocumentType">
                  <el-input v-model="formData.applyDocumentType"  disabled >
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="证件号码" >
                  <el-input v-model="formData.applyIdNum"  disabled >
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <div 
              style="font-size:20px;
              margin-left: 310px;
              margin-top: 20px;
              margin-bottom: 40px;
              color:blue;">联络员</div>
            <el-row class="rowCss" :gutter="60" style="margin-left:260px">
              <el-col :span="8">
                <el-form-item label="姓名" prop="contactName">
                  <el-input v-model="formData.contactName"  clearable >
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="移动电话" prop="contactPhone">
                  <el-input v-model="formData.contactPhone"  clearable >
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="rowCss" :gutter="60" style="margin-left:260px">
              <el-col :span="8">
                <el-form-item label="证件类型" prop="contactDocumentType">
                  <el-input v-model="formData.contactDocumentType"  disabled >
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="证件号码" prop="contactIdNum">
                  <el-input v-model="formData.contactIdNum"  clearable >
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row class="rowCss" :gutter="220" style="margin-left:600px;margin-top: 50px;">
              <el-col :span="2">
                  <el-button type="danger" @click="resetForm1">取消</el-button> 
              </el-col>
              <el-col :span="2">
                  <el-button type="primary" @click="submitForm1">下一步</el-button>
              </el-col>
            </el-row>
        </el-tab-pane>

        <el-tab-pane label="基本情况" name="second">
             <div 
              style="font-size:20px;
              margin-left: 300px;
              margin-bottom:20px;
              color:blue;">申请信息</div>
            <el-row class="rowCss" :gutter="60" style="margin-left:260px">
              <el-col :span="8">
                <el-form-item label="组织形式" prop="organizationalForm">
                  <el-input v-model="formData.organizationalForm"  disabled >
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="从业人数" prop="numberEmployees">
                  <el-input v-model="formData.numberEmployees"  disabled >
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="rowCss" :gutter="60" style="margin-left:260px">
              <el-col :span="8">
                <el-form-item label="出资额" prop="contributionAmount">
                  <el-input-number 
                    v-model="formData.contributionAmount" 
                    :precision="6" 
                    :step="0.01" :min="0"   
                  ></el-input-number>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="城市" prop="city">
                  <el-input v-model="formData.city"  disabled >
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="rowCss" :gutter="60" style="margin-left:260px">
              <el-col :span="8">
                <el-form-item label="区（县）" prop="county">
                  <el-input v-model="formData.county"  disabled >
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="电子商务经营者" prop="electronicCommerce">
                  <el-select 
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
            <el-row class="rowCss" :gutter="60" style="margin-left:260px">
              <el-col :span="16">
                <el-form-item label="经营场所" prop="selfAddress">
                  <el-input v-model="formData.selfAddress"  disabled >
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>
             <el-row class="rowCss" :gutter="60" style="margin-left:260px">
              <el-col :span="8">
                <el-form-item label="联系电话" prop="contactPhone">
                  <el-input v-model="formData.contactPhone"  disabled >
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="所属自贸区" prop="freeTradeZone">
                  <el-input v-model="formData.freeTradeZone"  disabled >
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="rowCss" :gutter="60" style="margin-left:260px">
              <el-col :span="8">
                <el-form-item label="所属自贸片区" prop="freeTradeArea">
                  <el-input v-model="formData.freeTradeArea"  disabled >
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="产权" prop="propertyRight">
                  <el-input v-model="formData.propertyRight"  disabled >
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="rowCss" :gutter="60" style="margin-left:260px">
              <el-col :span="16">
                <el-form-item label="经营范围" prop="natureBusiness">
                  <el-input
                    type="textarea"
                    :rows="2"
                    placeholder="请输入经营范围"
                    v-model="formData.natureBusiness">
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>
             
            <el-row class="rowCss" :gutter="60" style="margin-left:260px">
              <el-col :span="8">
            
                <el-form-item label="行业类型" prop="industryType">
                  <treeselect 
                    v-model="formData.industryType" 
                    :options="industryTypes" 
                    :show-count="true" 
                    placeholder="请选择归属部门" />
                 <!-- <el-select 
                    v-model="formData.industryType" 
                    placeholder="请选择行业类型" 
                    clearable
                    @change="selectIndustryType"
                    >
                    <el-option 
                      v-for="(item, index) in industryTypes" 
                      :key="index" 
                      :label="item.industryName"
                      :value="item.industryId" 
                    ></el-option>
                  </el-select> -->
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="行业税率" >
                  <el-input v-model="formData.industryTax"  disabled >
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="rowCss" :gutter="60" style="margin-left:260px">
              <el-col :span="8">
                <el-form-item label="银行账号类型" prop="accountType">
                 <el-select 
                    v-model="formData.accountType" 
                    placeholder="请选择银行账号类型" 
                    @change="selectAccountType"
                    >
                    <el-option 
                      v-for="(item, index) in accountTypes" 
                      :key="index" 
                      :label="item.label"
                      :value="item.value" 
                    ></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="私账名称" >
                  <el-input 
                    v-model="formData.legalPersonName"  
                    disabled>
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="rowCss" :gutter="60" style="margin-left:260px">
              <el-col :span="8">
                <el-form-item label="私账开户银行" >
                  <el-input 
                    v-model="formData.privateDepositBank "  
                    clearable 
                    :disabled="isPrivateBank">
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="私账银行账号" >
                  <el-input 
                    v-model="formData.privateAccountNumber"  
                    clearable 
                    :disabled="isPrivateBank">
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="rowCss" :gutter="60" style="margin-left:260px">
              <el-col :span="8">
                <el-form-item label="渠道商" prop="placeName">
                  <el-select 
                    v-model="formData.placeName" 
                    placeholder="请选择渠道商" 
                    clearable
                    filterable 
                    >
                    <el-option 
                      v-for="(item, index) in places" 
                      :key="index" 
                      :label="item.placeName"
                      :value="item.placeName" 
                    ></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="客户经理" prop="userName">
                  <el-input 
                    v-model="formData.userName"   
                    disabled>
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="rowCss" :gutter="220" style="margin-left:600px">
              <el-col :span="2">
                  <el-button type="danger" @click="toReturn1">返回</el-button> 
              </el-col>
              <el-col :span="2">
                  <el-button type="primary" @click="submitForm2">下一步</el-button>
              </el-col>
            </el-row>
        </el-tab-pane>

        <el-tab-pane label="经营者" name="third">
            <div 
              style="font-size:20px;
              margin-top: 20px;
              margin-left: 300px;
              margin-bottom:40px;
              color:blue;">经营者（负责人）信息</div>
            <el-row class="rowCss" :gutter="60" style="margin-left:260px">
              <el-col :span="8">
                <el-form-item label="姓名" >
                  <el-input v-model="formData.contactName"  disabled >
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="人员类型" prop="personnelType">
                  <el-input v-model="formData.personnelType"  disabled >
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="rowCss" :gutter="60" style="margin-left:260px">
              <el-col :span="8">
                <el-form-item label="证件类型" prop="contactDocumentType">
                  <el-input v-model="formData.contactDocumentType"  disabled >
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="证件号码" >
                  <el-input v-model="formData.contactIdNum"  disabled >
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="rowCss" :gutter="60" style="margin-left:260px">
              <el-col :span="8">
                <el-form-item label="性别" prop="gender">
                  <el-select 
                    v-model="formData.gender" 
                    placeholder="请选择性别" 
                    clearable
                    >
                    <el-option 
                      v-for="(item, index) in genders" 
                      :key="index" 
                      :label="item.label"
                      :value="item.value" 
                    ></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="出生日期" prop="dateBirth">
                   <el-date-picker
                    v-model="formData.dateBirth"
                    type="date"
                    placeholder="选择出生日期">
                  </el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="rowCss" :gutter="60" style="margin-left:260px">
              <el-col :span="8">
                <el-form-item label="民族" prop="nation">
                  <el-input v-model="formData.nation"  clearable >
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="文化程度" prop="eduation">
                  <el-select v-model="formData.eduation" placeholder="文化程度" clearable>
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
            <el-row class="rowCss" :gutter="60" style="margin-left:260px">
              <el-col :span="8">
                <el-form-item label="申请前职业状况" prop="occupationalStatus">
                  <el-input v-model="formData.occupationalStatus"  disabled >
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="政治面貌" prop="politicalStatus">
                  <el-select v-model="formData.politicalStatus" placeholder="文化程度" clearable>
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
            <el-row class="rowCss" :gutter="60" style="margin-left:260px">
              <el-col :span="16">
                <el-form-item label="经营者居所" prop="residence">
                  <el-input v-model="formData.residence"  clearable >
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>
              <el-row class="rowCss" :gutter="60" style="margin-left:260px">
              <el-col :span="8">
                <el-form-item label="联系电话" prop="contactPhone">
                  <el-input v-model="formData.contactPhone"  clearable >
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="电子邮箱" prop="mail">
                  <el-input v-model="formData.mail"  clearable >
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row class="rowCss" :gutter="60" style="margin-left:260px">
              <el-col :span="8">
              <el-form-item label="身份证扫描件" prop="fileName5">
                  <el-upload 
                    class="upload-demo" 
                    action="http://36.133.2.179:8000/api/files/doUpload"
                     :on-success="handlesuccess1" 
                     :on-preview="handlePreview1" 
                     :on-remove="handleRemove1"
                     :before-remove="beforeRemove1" 
                     multiple :limit="9" 
                     :on-exceed="handleExceed1"
                     :file-list="formData.fileName5" 
                     list-type="picture">
                     <el-button size="small" type="primary">点击上传</el-button>
                  </el-upload>
                  <el-dialog :visible.sync="dialogVisible1" append-to-body>
                     <img width="100%" :src="dialogImageUrl1" alt="" />
                  </el-dialog>
               </el-form-item>
              </el-col>
            </el-row>
            <el-row class="rowCss" :gutter="220" style="margin-left:600px;margin-top: 50px;">
              <el-col :span="2">
                  <el-button type="danger" @click="toReturn2">返回</el-button> 
              </el-col>
              <el-col :span="2">
                  <el-button type="primary" @click="submitForm3">下一步</el-button>
              </el-col>
            </el-row>
        </el-tab-pane>
      </el-tabs>

    </el-form>
  </div>
</template>
<script>
import crudInformation from '@/api/company/information' 
import crudPerson from '@/api/company/person' 
import crudInfo from '@/api/company/info' 
import crudEmployed from '@/api/company/employed' 
import crudRate from '@/api/company/rate' 
import crudPlace from '@/api/company/place' 
import {getInfo} from '@/api/login' 
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
export default {
  components: { Treeselect },
  dicts: ['political_status', 'educational_level'],
  props: [],
  data() {
    return {
      options: [ {
          id: 'a',
          label: 'a',
          children: [ {
            id: 'aa',
            label: 'aa',
          }, {
            id: 'ab',
            label: 'ab',
          } ],
        }, {
          id: 'b',
          label: 'b',
        }, {
          id: 'c',
          label: 'c',
        } ],
      isPrivateBank:false,
      activeName: 'first',
      oneselfApplys:[
        {
          value:1,
          label:'是',
        },
        {
          value:2,
          label:'否',
        },
      ],
      electronicCommerces:[
         {
          value:1,
          label:'是',
        },
        {
          value:2,
          label:'否',
        },
      ],
      genders:[
        {
          value:1,
          label:'男',
        },
        {
          value:2,
          label:'女',
        },
      ],
      eduations:[],
      politicalStatuList:[],
      applyNames:[],
      contactNames:[],
      industryTypes:[],
      industryTypeList:[],
      accountTypes:[
         {
          value:1,
          label:'私人账号',
        },
        {
          value:2,
          label:'对公账号',
        },
      ],
      places:[],
      dialogImageUrl1: '',
      dialogVisible1: false,
      formData: {
        selfCode:'',

        //申请信息
        oneselfApply:'否',
        applyName:'',
        applyPhone:'',
        applyDocumentType:'中华人民共和国居民身份证',
        applyIdNum:'',
        //联络员
        contactName:'',
        contactPhone:'',
        contactDocumentType:'中华人民共和国居民身份证',
        contactIdNum:'',

        //基本情况
        organizationalForm:'',
        numberEmployees:5,
        contributionAmount:'',
        city:'龙岩市',
        county:'漳平市',
        electronicCommerce:2,//1是 2否  是否是电子商务经营者
        selfAddress:'漳平市菁城街道双拥路202号A栋210室（集群注册）',
        freeTradeZone:'无',
        freeTradeArea:'不属自贸区',
        propertyRight:'租赁',
        natureBusiness:'',
        industryType:'',
        industryTax:'',
        accountType:1,
        legalPersonName:'',
        privateDepositBank:'',
        privateAccountNumber:'',
        placeName:'',
        userName:'',

        //经营者
        personnelType:'待业人员',
        gender:'',//1 男 2女
        dateBirth:'',
        nation:'',
        eduation:'',
        occupationalStatus:'待业人员',
        residence:'',
        mail:'',
        idCard:'',
        politicalStatus:'',
        fileName5: [],
      },
      rules: {
        fileName5: [{ 
          required: true, 
          message: '请上传纳税委托协议文件', 
          trigger: 'change' 
        }],
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
        },{
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
        },{
          pattern:/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
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
    'formData.industryType':'selectIndustryType',
    
    'formData.contactName':{
        handler:function(){
          this.formData.legalPersonName=this.formData.contactName
        },
        deep: true
    }
  },
  created() {},
  mounted() {
    console.log("options",this.options);
    this.getLoginInfo();
    //申请人
    this.getApplyName();
    //联系人
    this.getContactName();
    //个体户行业类型税率
    this.getRate();
    //从上一个页面获取个体户编码
    this.formData.selfCode=JSON.parse(window.localStorage.getItem('selfCode'));
    this.formData.organizationalForm=JSON.parse(window.localStorage.getItem('organizationalForm'));
    console.log("selfCode==",this.formData.selfCode)
  },
  methods: {
    /** 转换部门数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.deptId,
        label: node.deptName,
        children: node.children
      };
    },
    getLoginInfo(){
      getInfo().then(res=>{  
        this.formData.userName=res.user.nickName;
        crudPlace.getPlaceByUserId({userId:26}).then(res=>{
          console.log("getPlaceByUserId==",res.data);
          this.places=res.data;
        })
      })
    },
    selectAccountType(value){  
        if(value==1){  
          this.formData.legalPersonName=this.formData.contactName;
          this.isPrivateBank=false;
        }else{
          this.isPrivateBank=true;
          this.formData.legalPersonName="";
          this.formData.privateDepositBank="";
          this.formData.privateAccountNumber="";
        }
    },
    selectIndustryType(){
      console.log("industryType==",this.formData.industryType);
      var rate= this.industryTypeList.find((item)=>item.industryId==this.formData.industryType);
      console.log("rate==",rate);
      this.formData.industryTax=rate.taxRate;
    },
    selectApplyName(value){
      var applyName= this.applyNames.find((item)=>item.userId==value);
      this.formData.applyPhone=applyName.phone;
      this.formData.applyIdNum=applyName.idNo;
      console.log("applyName==",applyName);
    },
    getRate(){
      crudRate.getAllRate().then(res=>{
          console.log("getAllRate",res.rows);
          // this.industryTypes=res.rows;
          let tree = []; // 用来保存树状的数据形式
          this.parseTree(res.rows, tree, 0);
          console.log("tree",tree);
          this.industryTypes=tree;
          this.industryTypeList=res.rows;
      })
    },
    //把数据整成树状
   parseTree(industry, tree, pid) {
      for (var i = 0; i < industry.length; i++) {
        if (industry[i].parentId == pid) {
          var obj = {
            id: industry[i].industryId,
            label: industry[i].industryName,
            children: [],
          };
          tree.push(obj);
          this.parseTree(industry, obj.children, obj.id);
        }
      }
    },
    getContactName(){
        crudPerson.getAllPerson().then(res=>{
          console.log("getContactName",res.rows);
          this.contactNames=res.rows;
        })
    },
    getApplyName(){
        crudInformation.getAllInformation().then(res=>{
          console.log("getApplyName",res.rows);
          this.applyNames=res.rows;
        })
    },
    handleClick(tab, event) {
      console.log(tab, event);
    },
    submitForm1() {
      this.activeName='second';
    },
    resetForm1() {
      this.$refs['elForm'].resetFields()
    },

    submitForm2() {
      this.activeName='third';
    },
    toReturn1() {
      this.activeName='first';
    },

     submitForm3() {
      this.$refs['elForm'].validate(valid => {
        if (valid) {
            
            console.log("placeName",this.formData.placeName,);
            let parms1={
              selfCode:this.formData.selfCode,

              oneselfApply:this.formData.oneselfApply,
              applyName:this.formData.applyName,
              applyPhone:this.formData.applyPhone,
              applyDocumentType:this.formData.applyDocumentType,
              applyIdNum:this.formData.applyIdNum,

              contactName:this.formData.contactName,
              contactPhone:this.formData.contactPhone,
              contactDocumentType:this.formData.contactDocumentType,
              contactIdNum:this.formData.contactIdNum,

              createTime:new Date().toLocaleString(),
              updateTime:new Date().toLocaleString(),
              createBy:this.formData.userName,
              updateBy:this.formData.userName,

            };
            let parms2={
              selfCode:this.formData.selfCode,

              organizationalForm:this.formData.organizationalForm,
              numberEmployees:this.formData.numberEmployees,
              contributionAmount:this.formData.contributionAmount,
              city:this.formData.city,
              county:this.formData.county,
              electronicCommerce:this.formData.electronicCommerce,
              selfAddress:this.formData.selfAddress,
              freeTradeZone:this.formData.freeTradeZone,
              freeTradeArea:this.formData.freeTradeArea,
              propertyRight:this.formData.propertyRight,
              natureBusiness:this.formData.natureBusiness,
              industryType:this.formData.industryType,
              industryTax:this.formData.industryTax,
              accountType:this.formData.accountType,
              legalPersonName:this.formData.legalPersonName,
              privateDepositBank:this.formData.privateDepositBank,
              privateAccountNumber:this.formData.privateAccountNumber,
              placeName:this.formData.placeName,
              username:this.formData.userName,
              fileName5:JSON.stringify(this.formData.fileName5),
              
              createTime:new Date().toLocaleString(),
              updateTime:new Date().toLocaleString(),
              createBy:this.formData.userName,
              updateBy:this.formData.userName,

              businessStatus:0,
              infoStatus:0,
              taxStatus:0,
              bankStatus:0,
            };
            let parms3={
              selfCode:this.formData.selfCode,
              
              legalPersonName:this.formData.contactName,
              personnelType:this.formData.personnelType,
              documentType:this.formData.contactDocumentType,
              idCardNum:this.formData.contactIdNum,
              gender:this.formData.gender,
              dateBirth:this.formData.dateBirth,
              nation:this.formData.nation,
              eduation:this.formData.eduation,
              occupationalStatus:this.formData.occupationalStatus,
              residence:this.formData.residence,
              contactPhone:this.formData.contactPhone,
              mail:this.formData.mail,
              politicalStatus:this.formData.politicalStatus,

              createTime:new Date().toLocaleString(),
              updateTime:new Date().toLocaleString(),
              createBy:this.formData.userName,
              updateBy:this.formData.userName,

            };
            crudInfo.addInfo(parms1).then(res=>{
              if(res!=undefined){
                if(res.code===200){
                  this.$message({
                      message:res.msg,
                      type:'success'
                    })
                  }else{
                    this.$message({
                        message:res.msg,
                        type:'danger'
                    })
                  }
              }
            });
            crudEmployed.addEmployed(parms2).then(res=>{
              if(res!=undefined){
                if(res.code===200){
                  this.$message({
                      message:res.msg,
                      type:'success'
                    })
                  }else{
                    this.$message({
                        message:res.msg,
                        type:'danger'
                    })
                  }
              }
            });
            crudPerson.addPerson(parms3).then(res=>{
              if(res!=undefined){
                if(res.code===200){
                  this.$message({
                      message:res.msg,
                      type:'success'
                    })
                  }else{
                    this.$message({
                        message:res.msg,
                        type:'danger'
                    })
                  }
              }
            });
            this.$router.push("success");
        }else{
          this.$message({
            message:'请填写完整',
            type:'warning'
          })
        }
      })
    },
    toReturn2() {
      this.activeName='second';
    },
    handlesuccess1(file, fileList) {
         this.formData.fileName5.push(file.obj);
    },
    handleRemove1(file, fileList) {
        const i = this.formData.fileName5.findIndex((item) => item === fileList)
        this.formData.fileName5.splice(i, 1);
    },
    handlePreview1(file) {
        this.dialogImageUrl1 = file.url;
        this.dialogVisible1 = true;
    },
    handleExceed1(files, fileList) {
        this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove1(file, fileList) {
        return this.$confirm(`确定移除 ${file.name}？`);
    },
  }
}

</script>
<style rel="stylesheet/scss" lang="scss" scoped>
    .rowCss{
      margin-top:10px;
    }
      // 改变input框字体颜色
     ::v-deep .is-disabled .el-input__inner{
        background-color: transparent !important;
        color: black;    
    }
</style>
