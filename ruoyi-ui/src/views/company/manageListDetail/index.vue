<template>
  <div>
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="auto">
      <el-row type="flex" class="row-bg" style="margin-top:20px" justify="space-around">
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width:35%">基本情况</div>
          <div style="width:50%;hegiht:10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>
      
       <el-row  type="flex" class="row-bg " justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="个体户名称" prop="selfName">
            <el-input  v-model="formData.selfName" :readonly="true"></el-input>
          </el-form-item>

          <!-- <el-form-item class="comright" label="法人姓名" prop="legalPersonName">
            <el-input  v-model="formData.legalPersonName" :readonly="true"></el-input>
          </el-form-item> -->
        </el-col>
       
        <el-col :span="9">
          <el-form-item class="comright" label="纳税人识别号" prop="taxId">
            <el-input  v-model="formData.taxId" :readonly="true"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="组成形式" prop="organizationalForm">
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
          <el-form-item class="comright" label="出资额" :required="true">
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
          <el-form-item style="padding-right:4%" label="经营场所" prop="selfAddress">
            <el-input v-model="formData.selfAddress" :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>

      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="联系电话" prop="contactPhone">
            <el-input v-model="formData.contactPhone" :readonly="true">
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
          <el-form-item style="padding-right:4.3%" label="经营范围" prop="natureBusiness">
            <el-input disabled type="textarea" :rows="2" placeholder="请输入经营范围" v-model="formData.natureBusiness">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <!-- <el-row type="flex" class="row-bg rowCss" justify="space-around">
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
          <el-form-item class="comright" label="私账名称" :required="true">
            <el-input v-model="formData.legalPersonName" :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row> -->
      <!-- <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="私账开户银行" :required="true">
            <el-input :readonly="true" v-model="formData.privateDepositBank" clearable>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="私账银行账号" :required="true">
            <el-input v-model="formData.privateAccountNumber" clearable :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row> -->

      <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width:55%">经营者（负责人）信息</div>
          <div style="width:40%;hegiht:10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="姓名" :required="true">
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
          <el-form-item class="comright" label="证件号码" :required="true">
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
          <el-form-item style="padding-right:4.3%" label="经营者居所" prop="residence">
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
            <el-input style="width:100%" :readonly="true" v-model="formData.mail" clearable>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="9">
          <el-form-item label="工商实名" :required="true">
            <uploadSmall v-if="fileNameN6.length > 0" @getfileName="getfileNameS" :fileName="isNone"
              :fileNameOld="fileNameN6" :isDetail="isDetail"></uploadSmall>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item label="税务实名" :required="true">
            <uploadSmall v-if="fileNameN7.length > 0" @getfileName="getfileNameS" :fileName="isNone"
              :fileNameOld="fileNameN7" :isDetail="isDetail"></uploadSmall>
          </el-form-item>
        </el-col>
      </el-row>


      <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="9">
          <el-form-item label="身份证扫描件" :required="true">
            <uploadSmall v-if="fileNameN5.length > 0" @getfileName="getfileNameS" :fileName="isNone"
              :fileNameOld="fileNameN5" :isDetail="isDetail"></uploadSmall>
          </el-form-item>
        </el-col>
        <el-col :span="9"></el-col>
      </el-row>
     <el-row type="flex" class="row-bg rowCss"  justify="space-around">
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width:35%">工商信息</div>
          <div style="width:50%;hegiht:10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <!-- <el-form-item class="comright" label="个体户名称" prop="selfName">
            <el-input v-model="formData.selfName" :readonly="true"></el-input>
          </el-form-item>

          <el-form-item class="comright" label="法人姓名" prop="legalPersonName">
            <el-input v-model="formData.legalPersonName" :readonly="true"></el-input>
          </el-form-item> -->
          <el-form-item label="营业执照" prop="fileName1" :required="true">
             <uploadSmall v-if="fileNameN1.length>0" @getfileName="getfileNameS" :fileName="isNone" :fileNameOld="fileNameN1" :isDetail="isDetail"></uploadSmall>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <!-- <el-form-item class="comright" label="纳税人识别号" prop="taxId">
            <el-input v-model="formData.taxId" :readonly="true"></el-input>
          </el-form-item> -->
          <el-form-item class="comright" label="注册时间" :required="true">
            <el-date-picker disabled style="width:100%" v-model="formData.businessTerm" type="date"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss"  justify="space-around">
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width:35%">税务信息</div>
          <div style="width:50%;hegiht:10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <!-- <el-form-item class="comright" label="个体户名称" prop="selfName">
            <el-input
              v-model="formData.selfName"
              :readonly="true"
            ></el-input>
          </el-form-item>
          <el-form-item class="comright" label="法人姓名" prop="legalPersonName">
            <el-input
              v-model="formData.legalPersonName"
              :readonly="true"
            ></el-input>
          </el-form-item> -->
          <el-form-item label="核定通知书" :required="true">
             <uploadSmall v-if="fileNameN2.length>0" @getfileName="getfileNameS" :fileName="isNone" :fileNameOld="fileNameN2" :isDetail="isDetail"></uploadSmall>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <!-- <el-form-item class="comright" label="纳税人识别号" prop="taxId">
            <el-input
              v-model="formData.taxId"
              :readonly="true"
            ></el-input>
          </el-form-item> -->
        </el-col>
      </el-row>

         <el-row type="flex" class="row-bg rowCss"  justify="space-around">
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width:35%">银行信息</div>
          <div style="width:50%;hegiht:10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>


       <!-- <el-row  type="flex" class="row-bg " justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="个体户名称" prop="selfName">
            <el-input  v-model="formData.selfName" :readonly="true"></el-input>
          </el-form-item>

          <el-form-item class="comright" label="法人姓名" prop="legalPersonName">
            <el-input  v-model="formData.legalPersonName" :readonly="true"></el-input>
          </el-form-item>
        </el-col>
       
        <el-col :span="9">
          <el-form-item class="comright" label="纳税人识别号" prop="taxId">
            <el-input  v-model="formData.taxId" :readonly="true"></el-input>
          </el-form-item>
        </el-col>
      </el-row> -->
       
     
      <el-row class="paddingbg-s">
         <el-row type="flex" class="row-bg" justify="space-around" v-if="formData.accountType==1">
            <el-col :span="9">
              <el-form-item class="comright" label="账号类型" :required="true">
                <el-input
                 value="私人账号"
                  disabled
                ></el-input>
              </el-form-item>

              <el-form-item class="comright" label="开户银行" :required="true">
                <el-input
                  v-model="formData.privateDepositBank"
                  :readonly="true"
                ></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="9">
              <el-form-item class="comright" label="账号名称" :required="true">
                <el-input
                 v-model="formData.legalPersonName"
                  :readonly="true"
                ></el-input>
              </el-form-item>
              <el-form-item class="comright" label="银行账号" :required="true">
                <el-input
                  v-model="formData.privateAccountNumber"
                  :readonly="true"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
         <el-row type="flex" class="row-bg" justify="space-around" v-if="formData.accountType==2">
            <el-col :span="9">
               <el-form-item class="comright" label="账号类型" :required="true">
                <el-input
                  value="对公基本户"
                  :readonly="true"
                ></el-input>
              </el-form-item>
          

              <el-form-item class="comright" label="开户银行">
                <el-input   v-model="formData.publicDepositBank1" :readonly="true"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="9">
                   <el-form-item class="comright" label="账号名称">
                <el-input     v-model="formData.selfName" :readonly="true"></el-input>
              </el-form-item>
              <el-form-item class="comright" label="银行账号">
                <el-input     v-model="formData.publicAccountNumber1" :readonly="true"></el-input>
              </el-form-item>
            </el-col>
            
          </el-row>
         <el-row type="flex" class="row-bg" justify="space-around" v-if="formData.accountType==2">
            <el-col :span="9">
                <el-form-item class="comright" label="账号类型" prop="">
                <el-input
                 
                  value="对公一般户"
                  disabled
                ></el-input>
              </el-form-item>
              

              <el-form-item class="comright" label="开户银行">
                <el-input  v-model="formData.publicDepositBank2" :readonly="true"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="9">
               <el-form-item class="comright" label="账号名称">
                <el-input  v-model="formData.selfName" :readonly="true"></el-input>
              </el-form-item>
              <el-form-item class="comright" label="银行账号">
                <el-input  v-model="formData.publicAccountNumber2" :readonly="true"></el-input>
              </el-form-item>
            </el-col>
            
          </el-row>
          
        
        
      </el-row>
          <el-row type="flex" class="row-bg rowCss"  justify="space-around">
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width:35%">纳税信息</div>
          <div style="width:50%;hegiht:10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>

      <el-row  type="flex" class="row-bg " justify="space-around">
        <el-col :span="9">
          <!-- <el-form-item class="comright" label="户名" prop="accountName">
           
            <el-input v-model="formData.accountName"></el-input>
          
          </el-form-item> -->
            <el-form-item class="comright" label="开户银行" :required="true">
            <el-input  disabled v-model="formData.publicDepositBank3" :readonly="true"></el-input>
          </el-form-item>
          <el-form-item class="comright" label="户名" :required="true">
              <el-select
             disabled
               style="width:100%"
              @change="changeValue($event)"
              v-model="formData.accountName"
              filterable
              placeholder="请选择"
            >
              <el-option
                v-for="item in accountName_options"
                :key="item.value"
                :label="item.value"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>

        
          <el-form-item  label="纳税委托协议" :required="true">
              <uploadSmall v-if="fileNameN3.length>0" @getfileName="getfileNameS" :fileName="isNone" :fileNameOld="fileNameN3" :isDetail="isDetail"></uploadSmall>
          </el-form-item>
        </el-col>
        
        <el-col :span="9">
          <el-form-item class="comright" label="银行账号" :required="true">
            <el-input
             
              disabled
              v-model="formData.publicAccountNumber3"
            ></el-input>
          </el-form-item>
          <el-form-item >
            <div style="height:40px"></div>
          </el-form-item>
     
        
          <el-form-item label="三方协议签约凭证" :required="true">
              <uploadSmall v-if="fileNameN4.length>0" @getfileName="getfileNameS" :fileName="isNone" :fileNameOld="fileNameN4" :isDetail="isDetail"></uploadSmall>
          </el-form-item>
        </el-col>
      </el-row>




     <!-- <el-row type="flex" class="row-bg rowCss"  justify="space-around">
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
            <el-input disabled v-model="formData.contactIdNum" :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row> -->
      <el-row type="flex" class="row-bg rowCss"  justify="space-around">
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width:35%">结算信息</div>
          <div style="width:50%;hegiht:10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="渠道商全名" prop="placeName">
            <el-select style="width:100%" disabled v-model="formData.placeName" placeholder="请选择渠道商全名" clearable
              filterable>
              <el-option v-for="(item, index) in places" :key="index" :label="item.placeAliasName" :value="item.placeName">
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
          <el-form-item class="comright" label="行业税率" :required="true">
            <el-input v-model="industryTax" :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>

          <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="单独结算" :required="true">
              <el-radio disabled v-model="formData.isSelfCount" label="0">是</el-radio>
              <el-radio disabled v-model="formData.isSelfCount" label="1">否</el-radio>
            </el-form-item>
          </el-col>
          <el-col :span="9">

          </el-col>
        </el-row>


        <el-row v-if="formData.isSelfCount==0"  type="flex" class="row-bg " justify="space-around">
          <el-col :span="9">
          <el-form-item label="个体户注册服务费" :required="true" >
              <el-input style="width:87%" v-model="formData.registerMoney" :disabled="true">
                <template slot="append">元</template>
              </el-input>
            </el-form-item>
            
          </el-col>
          <el-col :span="9">
            
          </el-col>

        </el-row>

          <el-collapse v-if="formData.isSelfCount==0" v-model="activeNamese"  accordion style="padding-left:8%;padding-right: 6%;">
          <el-collapse-item name="1">
            <template slot="title" :required="true">
              增值税普通发票
            </template>
            <el-col :span="10">
              <el-form-item label="状态"> 
             <el-radio disabled v-model="formData.isSliderOrdinary" label="0">开启</el-radio>
             <el-radio disabled v-model="formData.isSliderOrdinary" label="1">关闭</el-radio>
             </el-form-item>
             <div v-if="formData.isSliderOrdinary">
              
             
              <el-form-item label="普票税率"  :required="true">
                <el-select :disabled="true" style="width:87%" v-model="formData.ordinaryTax" clearable
                  placeholder="请选择">
                  <el-option v-for="item in optiond" :key="item.value" :label="item.label" :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item  label="普票服务费" :required="true">
                <div style="">

                  <el-radio :disabled="true" v-model="formData.ordinaryProxyIsmoney" label="0">按定额收取</el-radio>
                  <el-radio :disabled="true" v-model="formData.ordinaryProxyIsmoney" label="1">按百分比收取</el-radio>
                  <el-input v-if="formData.ordinaryProxyIsmoney == 0" :disabled="true" type="number"
                    v-model="formData.ordinarySelfFee" style="width:87%;margin-right:10px" :step="0.01" :min="0" >
                    <template slot="append">元</template>
                  </el-input>
                  <el-input v-else :disabled="true" type="number" v-model="formData.ordinarySelfFee"
                    style="width:87%;margin-right:10px" :step="0.01" :min="0" :max="100">
                    <template slot="append">%</template>
                  </el-input>
                </div>
              </el-form-item>
              <el-form-item label="服务费含税" :required="true">
              <el-radio :disabled="true" v-model="formData.isSelfTax" label='0'>是</el-radio>
              <el-radio :disabled="true" v-model="formData.isSelfTax" label='1'>否</el-radio>
            </el-form-item>
              <el-form-item label="价税分离" :required="true">
              <el-radio :disabled="true" v-model="formData.isOrdinaryTax" label='0'>是</el-radio>
              <el-radio :disabled="true" v-model="formData.isOrdinaryTax" label='1'>否</el-radio>
            </el-form-item>
              <el-form-item label="是否分润">
                  <el-radio  :disabled="true" v-model="formData.isOrdinaryShare" label="0">是</el-radio>
                  <el-radio  :disabled="true" v-model="formData.isOrdinaryShare" label="1">否</el-radio>
              </el-form-item>
              <el-form-item  v-if="formData.isOrdinaryShare== 0" :required="true">
                <div style="">

                  <el-radio :disabled="true" v-model="formData.ordinaryShareIsmoney" label="0">按定额收取</el-radio>
                  <el-radio :disabled="true" v-model="formData.ordinaryShareIsmoney"  label="1">按百分比收取</el-radio>
                  <el-input v-if="formData.ordinaryShareIsmoney == 0" :disabled="true" type="number"
                    v-model="formData.ordinaryShare" style="margin-right:10px;width:87%;" :step="0.01" :min="0"
                    :max="9999">
                    <template slot="append">元</template>
                  </el-input>
                  <el-input v-else :disabled="true" type="number" v-model="formData.ordinaryShare"
                    style="margin-right:10px;width:87%;" :step="0.01" :min="0" :max="100">
                    <template slot="append">%</template>
                  </el-input>
                </div>
              </el-form-item>
               </div>
            </el-col>

          </el-collapse-item>

        </el-collapse>

          <el-collapse v-if="formData.isSelfCount==0" v-model="activeNameseg"  accordion style="padding-left:8%;padding-right: 6%;">
          <el-collapse-item name='1'>
            <template slot="title" :required="true">
              增值税专用发票
            </template>
             <el-col :span="10">
             <el-form-item label="状态"> 
             <el-radio disabled v-model="formData.isSlider" label="0">开启</el-radio>
             <el-radio disabled v-model="formData.isSlider" label="1">关闭</el-radio>
             </el-form-item>
             <div  v-if="formData.isSlider==0">
                <el-form-item label="专票税率" :required="true">
              <el-select :disabled="true" style="width:87%;" v-model="formData.ordinarySpecialTax" clearable
                placeholder="请选择">
                <el-option v-for="item in optionz" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="专票服务费" :required="true">
              <div style="">
                <el-radio :disabled="true" v-model="formData.specialProxyIsmoney" label="0">按定额收取</el-radio>
                <el-radio :disabled="true" v-model="formData.specialProxyIsmoney" label="1">按百分比收取</el-radio>
                <el-input v-if="formData.specialProxyIsmoney == 0" :disabled="true" type="number" 
                  v-model="formData.specialSelfFee"
                  style="margin-right:10px;width:87%;" :step="0.01" :min="0">
                  <template slot="append">元</template>
                </el-input>
                <el-input v-else :disabled="true" type="number" v-model="formData.specialSelfFee"
                  style="margin-right:10px;width:87%;" :step="0.01" :min="0">
                  <template slot="append">%</template>
                </el-input>

              </div>
            </el-form-item>
                 <el-form-item label="服务费含税" :required="true">
              <el-radio :disabled="true" v-model="formData.isSpecialSelfTax" label='0'>是</el-radio>
              <el-radio :disabled="true" v-model="formData.isSpecialSelfTax" label='1'>否</el-radio>
            </el-form-item>
            <el-form-item label="价税分离" :required="true">
              <el-radio :disabled="true" v-model="formData.isSpecialTax" label='0'>是</el-radio>
              <el-radio :disabled="true" v-model="formData.isSpecialTax" label='1'>否</el-radio>
            </el-form-item>
              <el-form-item label="是否分润">
                  <el-radio :disabled="true"  v-model="formData.isSpecialShare"     label="0">是</el-radio>
                  <el-radio :disabled="true"  v-model="formData.isSpecialShare"     label="1">否</el-radio>
            </el-form-item>
            <el-form-item  v-if="formData.isSpecialShare==0" label="分润方式" :required="true">
              <div style="">

                <el-radio :disabled="true" v-model="formData.specialShareIsmoney" label="0">按定额收取</el-radio>
                <el-radio :disabled="true" v-model="formData.specialShareIsmoney" label="1">按百分比收取</el-radio>
                <el-input v-if="formData.specialShareIsmoney == 0" :disabled="true" type="number"
                  v-model="formData.specialShare" style="margin-right:10px;width:87%;" :step="0.01" :min="0"
                  >
                  <template slot="append">元</template>
                </el-input>
                <el-input v-model="formData.specialShare" v-else :disabled="true" type="number"
                  style="margin-right:10px;width:87%;" :step="0.01" :min="0" :max="100">
                  <template slot="append">%</template>
                </el-input>
              </div>
            </el-form-item>
             </div>
          </el-col>
         </el-collapse-item>

        </el-collapse>


      

        






      <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="8"></el-col>
        <el-col :span='8' class="flexs">
          <el-button type="danger" @click="resetForm">返回</el-button>

        </el-col>
        <el-col :span="8"></el-col>
      </el-row>


    </el-form>



  </div>
</template>
<script>
import uploadSmall from '@/components/douploads/uploadSmall'
import crudInformation from '@/api/company/information'
import crudPerson from '@/api/company/person'
import crudRate from '@/api/company/rate'
import crudPlace from '@/api/company/place'
import { getInfo } from '@/api/login'
import { Decimal } from 'decimal.js'
import { all } from "@/api/company/payTaxInfo";

export default {
  components: {},
  dicts: ['political_status', 'educational_level'],
  components: {
    uploadSmall
  },
  props: [],
  data() {
    return {
      activeNameseg:'1',
      activeNamese:'1',
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
      isDetail: '1',
      isNone: [],
      fileNameN1: [],
      fileNameN2: [],
      fileNameN3: [],
      fileNameN4: [],
      fileNameN5: [],
      fileNameN6: [],
      fileNameN7: [],
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
      fileName1: [],
      fileName2: [],
      fileName3: [],
      fileName4: [],
      fileName5: [],
      fileName6: [],
      fileName7: [],
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
      accountName_options : [],
      mylist :'',
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
          message: '请输入法人姓名',
          trigger: 'blur'
        }],
        contactPhone: [{
          required: true,
          message: '请输入法人手机号',
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
          message: '请选择渠道商全名',
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



    this.formData = this.$cache.local.getJSON('employedInfo');
    this.industryTax = new Decimal(this.formData.industryTax).mul(new Decimal(100)) + '%';
    this.formData.gender = parseInt(this.formData.gender);
    this.formData.accountType = parseInt(this.formData.accountType);
    this.formData.electronicCommerce = parseInt(this.formData.electronicCommerce);
    this.formData.applyName = parseInt(this.formData.applyName);

   

  
    this.fileName1=[];
    this.fileName2=[];
    this.fileName3=[];
    this.fileName4=[];
    this.fileName5=[];
    this.fileName6=[];
    this.fileName7=[];
    this.fileNameN1 = [];
    this.fileNameN2 = [];
    this.fileNameN3 = [];
    this.fileNameN4 = [];
    this.fileNameN5 = [];
    this.fileNameN6 = [];
    this.fileNameN7 = [];

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
 
    if (this.formData.isSlider == '0') {
      this.formData.isSlider = '0';
    } else {
      this.formData.isSlider = '1';
    }  

     if (this.formData.isSliderOrdinary == '0') {
      this.formData.isSliderOrdinary = '0';
    } else {
      this.formData.isSliderOrdinary = '1';
    }  
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

   this.fileName1 = JSON.parse(this.$cache.local.getJSON('employedInfo').fileName1);
    for (let k1 in this.fileName1) {
      this.fileNameN1.push({
        url: this.baseImgPath + this.fileName1[k1],
        name: this.fileName1[k1],
      });
    }

     this.fileName2 = JSON.parse(this.$cache.local.getJSON('employedInfo').fileName2);
    for (let k2 in this.fileName2) {
      this.fileNameN2.push({
        url: this.baseImgPath + this.fileName2[k2],
        name: this.fileName2[k2],
      });
    }
     this.fileName3 = JSON.parse(this.$cache.local.getJSON('employedInfo').fileName3);
    for (let k3 in this.fileName3) {
      this.fileNameN3.push({
        url: this.baseImgPath + this.fileName3[k3],
        name: this.fileName3[k3],
      });
    }

     this.fileName4 = JSON.parse(this.$cache.local.getJSON('employedInfo').fileName4);
    for (let k4 in this.fileName4) {
      this.fileNameN4.push({
        url: this.baseImgPath + this.fileName4[k4],
        name: this.fileName4[k4],
      });
    }

    this.fileName5 = JSON.parse(this.$cache.local.getJSON('employedInfo').fileName5);
    for (let k5 in this.fileName5) {
      this.fileNameN5.push({
        url: this.baseImgPath + this.fileName5[k5],
        name: this.fileName5[k5],
      });
    }
    this.fileName6 = JSON.parse(this.$cache.local.getJSON('employedInfo').fileName6);
    for (let k6 in this.fileName6) {
      this.fileNameN6.push({
        url: this.baseImgPath + this.fileName6[k6],
        name: this.fileName6[k6],
      });
    }
    this.fileName7 = JSON.parse(this.$cache.local.getJSON('employedInfo').fileName7);
    for (let k7 in this.fileName7) {
      this.fileNameN7.push({
        url: this.baseImgPath + this.fileName7[k7],
        name: this.fileName7[k7],
      });
    }
    this.nailist();
  },
  methods: {
      changeValue(res) {
      for (let i in this.mylist) {
        if (this.mylist[i].accountName == res) {
          this.formData.publicDepositBank3 = this.mylist[i].publicDepositBank3;
          this.formData.publicAccountNumber3 =
            this.mylist[i].publicAccountNumber3;
          return;
        }
      }
    },
    getfileNameS() {

    },
    getLoginInfo() {
      getInfo().then(res => {
        this.formData.userName = res.user.nickName;
        crudPlace.getPlaceByUserId({ userId: res.user.userId }).then(res => {
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
    resetForm() {
      this.$tab.closeOpenPage({ path: '/company/manageList' });
    },
      nailist() {
      all()
        .then((res) => {
          if (res != undefined) {
            console.log(res);
            this.mylist = [];
            this.accountName_options = [];
            this.mylist = res;
            // this.publicDepositBank3_options = [];
            // this.publicAccountNumber3_options = [];
            for (let i in res) {
              this.accountName_options.push({ value: res[i].accountName });
              // this.publicDepositBank3_options.push({value:res[i].publicDepositBank3});
              // this.publicAccountNumber3_options.push({value:res[i].publicAccountNumber3});
            }
          }
        })
        .catch((error) => {
         
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
