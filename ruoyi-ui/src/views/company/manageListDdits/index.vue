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
            <el-input  v-model="formData.selfName" ></el-input>
          </el-form-item>

          <!-- <el-form-item class="comright" label="法人姓名" prop="legalPersonName">
            <el-input  v-model="formData.legalPersonName" :readonly="true"></el-input>
          </el-form-item> -->
        </el-col>
       
        <el-col :span="9">
          <el-form-item class="comright" label="纳税人识别号" prop="taxId">
            <el-input  v-model="formData.taxId" ></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="组成形式" prop="organizationalForm">
            <el-input v-model="formData.organizationalForm" disabled>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="从业人数" prop="numberEmployees">
            <el-input v-model="formData.numberEmployees" disabled>
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
            <el-input v-model="formData.city" disabled>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="区（县）" prop="county">
            <el-input v-model="formData.county" disabled>
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
            <el-input v-model="formData.selfAddress" disabled>
            </el-input>
          </el-form-item>
        </el-col>

      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="联系电话" prop="contactPhone">
            <el-input v-model="formData.contactPhone" disabled>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="所属自贸区" prop="freeTradeZone">
            <el-input v-model="formData.freeTradeZone" disabled>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="所属自贸片区" prop="freeTradeArea">
            <el-input v-model="formData.freeTradeArea" disabled>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="产权" prop="propertyRight">
            <el-input v-model="formData.propertyRight" disabled>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="21">
          <el-form-item style="padding-right:4.3%" label="经营范围" prop="natureBusiness">
            <el-input
             maxlength="250"
             show-word-limit
            type="textarea" :rows="2" placeholder="请输入经营范围" v-model="formData.natureBusiness">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="银行账号类型" prop="accountType">
            <el-select style="width:100%" v-model="formData.accountType" placeholder="请选择银行账号类型"
              @change="selectAccountType">
              <el-option v-for="(item, index) in accountTypes" :key="index" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="私账名称" :required="true" v-if="formData.accountType == 1">
            <el-input v-model="formData.legalPersonName" disabled>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row v-if="formData.accountType == 2" type="flex" class="row-bg rowCss " justify="space-around">
        <el-col :span="9">
          <el-form-item label="对公开户银行">
            <el-input style="width:87%" disabled v-model="formData.publicDepositBank1" clearable>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item label="对公银行账号">
            <el-input style="width:87%" disabled v-model="formData.publicAccountNumber1" clearable>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row v-else type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="私账开户银行" :required="true">
            <el-input v-model="formData.privateDepositBank" clearable>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="私账银行账号" :required="true">
            <el-input v-model="formData.privateAccountNumber" clearable>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>

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
            <el-input v-model="formData.contactName" disabled>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="人员类型" prop="personnelType">
            <el-input v-model="formData.personnelType" disabled>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="证件类型" prop="contactDocumentType">
            <el-input v-model="formData.contactDocumentType" disabled>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="证件号码" :required="true">
            <el-input v-model="formData.contactIdNum" disabled>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="性别" prop="gender">
            <el-select style="width:100%" v-model="formData.gender" placeholder="请选择性别" clearable>
              <el-option v-for="(item, index) in genders" :key="index" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="出生日期" prop="dateBirth">
            <el-date-picker style="width:100%" v-model="formData.dateBirth" type="date" placeholder="选择出生日期">
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="民族" prop="nation">
            <el-input v-model="formData.nation" clearable>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="文化程度" prop="eduation">
            <el-select style="width:100%" v-model="formData.eduation" placeholder="文化程度" clearable>
              <el-option v-for="dict in dict.type.educational_level" :key="dict.value" :label="dict.label"
                :value="dict.value" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="申请前职业状况" prop="occupationalStatus">
            <el-input v-model="formData.occupationalStatus" disabled>
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
            <el-input 
              maxlength="250"
              show-word-limit
              type="textarea" :rows="2"
            v-model="formData.residence" clearable>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="联系电话" prop="contactPhone">
            <el-input v-model="formData.contactPhone" clearable>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="电子邮箱" prop="mail">
            <el-input style="width:100%" v-model="formData.mail" clearable>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="9">
          <el-form-item label="工商实名" prop="fileName6">
            <uploadSmall v-if="fileNameN6.length > 0" @getfileName="getfileNameS6" :fileName="fileName6"
              :fileNameOld="fileNameN6" :isDetail="isDetail"></uploadSmall>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item label="税务实名" prop="fileName7">
            <uploadSmall v-if="fileNameN7.length > 0" @getfileName="getfileNameS7" :fileName="fileName7"
              :fileNameOld="fileNameN7" :isDetail="isDetail"></uploadSmall>
          </el-form-item>
        </el-col>
      </el-row>


      <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="9">
          <el-form-item label="身份证扫描件" prop="fileName5">
            <uploadSmall v-if="fileNameN5.length > 0" @getfileName="getfileNameS5" :fileName="fileName5"
              :fileNameOld="fileNameN5" :isDetail="isDetail"></uploadSmall>
          </el-form-item>
        </el-col>
        <el-col :span="9"></el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
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
          <el-form-item label="营业执照" prop="fileName1">
            <uploadSmall v-if="fileNameN1.length > 0" @getfileName="getfileNameS1" :fileName="fileName1"
              :fileNameOld="fileNameN1" :isDetail="isDetail"></uploadSmall>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <!-- <el-form-item class="comright" label="纳税人识别号" prop="taxId">
            <el-input v-model="formData.taxId" :readonly="true"></el-input>
          </el-form-item> -->
          <el-form-item class="comright" label="注册时间" prop="businessTerm">
            <el-date-picker disabled style="width:100%" v-model="formData.businessTerm" type="date" placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
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
          <el-form-item label="核定通知书" prop="fileName2">
            <uploadSmall v-if="fileNameN2.length > 0" @getfileName="getfileNameS2" :fileName="fileName2"
              :fileNameOld="fileNameN2" :isDetail="isDetail"></uploadSmall>
          </el-form-item>
        </el-col>
        <el-col :span="9">
         
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg rowCss" justify="space-around">
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
        <el-row type="flex" class="row-bg" justify="space-around" v-if="formData.accountType == 1">
          <el-col :span="9">
            <el-form-item class="comright" label="账号类型" :required="true">
              <el-input value="私人账号" disabled></el-input>
            </el-form-item>

            <el-form-item class="comright" label="开户银行" prop="privateDepositBank">
              <el-input v-model="formData.privateDepositBank" :disabled="formData.accountType == 1"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="9">
            <el-form-item class="comright" label="账号名称" prop="legalPersonName">
              <el-input v-model="formData.legalPersonName" disabled></el-input>
            </el-form-item>
            <el-form-item class="comright" label="银行账号" prop="privateAccountNumber">
              <el-input :disabled="formData.accountType == 1" v-model="formData.privateAccountNumber"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around" v-if="formData.accountType == 2">
          <el-col :span="9">
            <el-form-item class="comright" label="账号类型" :required="true">
              <el-input value="对公基本户" :readonly="true"></el-input>
            </el-form-item>


            <el-form-item class="comright" label="开户银行">
              <el-input v-model="formData.publicDepositBank1"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="9">
            <el-form-item class="comright" label="账号名称" :required="true">
              <el-input v-model="formData.selfName" disabled></el-input>
            </el-form-item>
            <el-form-item class="comright" label="银行账号">
              <el-input v-model="formData.publicAccountNumber1"></el-input>
            </el-form-item>
          </el-col>

        </el-row>
           <el-row type="flex" class="row-bg" justify="space-around" v-if="formData.accountType == 2">
          <el-col :span="9">
            <el-form-item class="comright" label="对公一般户状态" :required="true">
              <el-radio   v-model="formData.isPublicUser" label="0">开启</el-radio>
              <el-radio   v-model="formData.isPublicUser" label="1">关闭</el-radio>
            </el-form-item>
          </el-col>
          <el-col :span="9">
          </el-col>
        </el-row>
         <div v-if="formData.isPublicUser==0">
        <el-row type="flex" class="row-bg" justify="space-around" v-if="formData.accountType == 2">
          <el-col :span="9">
            <el-form-item class="comright" label="账号类型" :required="true">
              <el-input value="对公一般户" disabled></el-input>
            </el-form-item>


            <el-form-item class="comright" label="开户银行">
              <el-input v-model="formData.publicDepositBank2"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="9">
            <el-form-item class="comright" label="账号名称">
              <el-input v-model="formData.selfName" disabled></el-input>
            </el-form-item>
            <el-form-item class="comright" label="银行账号">
              <el-input v-model="formData.publicAccountNumber2"></el-input>
            </el-form-item>
          </el-col>

        </el-row>
        </div>



      </el-row>
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width:35%">纳税信息</div>
          <div style="width:50%;hegiht:10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="开户银行" prop="publicDepositBank3">
            <el-select style="width:100%" @change="changeValue($event)" v-model="formData.publicDepositBank3" filterable
              placeholder="请选择">
              <el-option v-for="item in accountName_options" :key="item.value" :label="item.value" :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item class="comright" label="户名" :required="true">

            <el-input v-model="formData.accountName" disabled></el-input>

          </el-form-item>
          <el-form-item label="纳税委托协议" prop="fileName3">
            <uploadSmall v-if="fileNameN3.length > 0" @getfileName="getfileNameS3" :fileName="fileName3"
              :fileNameOld="fileNameN3" :isDetail="isDetail"></uploadSmall>
          </el-form-item>
        </el-col>

        <el-col :span="9">
          <el-form-item class="comright" label="银行账号" :required="true">
            <el-input disabled v-model="formData.publicAccountNumber3"></el-input>
          </el-form-item>
          <el-form-item>
            <div style="height:40px"></div>
          </el-form-item>


          <el-form-item label="三方协议签约凭证" prop="fileName4">
            <uploadSmall v-if="fileNameN4.length > 0" @getfileName="getfileNameS4" :fileName="fileName4"
              :fileNameOld="fileNameN4" :isDetail="isDetail"></uploadSmall>
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
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
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
          <el-form-item class="comright" label="行业类型" prop="industryType">
              <el-tooltip class="item" effect="dark" :content="selectTipType" placement="top-start">
            <el-select 
             
             :popper-append-to-body="false" class="main-select-tree" ref="selectTree" v-model="formData.industryType" style="width: 100%;">
               <el-option v-for="item in formatData(industryTypes)" :title="item.label" :key="item.value" :label="item.label"
                  :value="item.value" style="display: none;" />
                <el-tree class="main-select-el-tree" ref="selecteltree" :data="industryTypes" node-key="id"
                  highlight-current :props="defaultProps" @node-click="handleNodeClick"
                  :current-node-key="formData.industryType" :expand-on-click-node="expandOnClickNode"
                   >
                    <span class="custom-tree-node" slot-scope="{ node, data  }" style="width:100%">
                         <span style="float: left">{{ node.label }}</span>
                         <span style="float: right; color: #8492a6; font-size: 14px;padding-right:10px">{{ data.taxRates }}</span>
                    </span>
                  </el-tree>
              </el-select>
            </el-tooltip>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="行业税率" :required="true">
            <el-input v-model="industryTax" :readonly="true">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="渠道商全名" prop="placeName">
            <el-select style="width:100%" @change="placenew" v-model="formData.placeName" placeholder="请选择渠道商全名"
              clearable filterable>
              <el-option v-for="(item, index) in places" :key="index" :label="item.placeAliasName"
                :value="item.placeName">
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

   

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item label="单独结算" :required="true">
            <el-radio v-model="formData.isSelfCount" label="0" @change="singleOK">是</el-radio>
            <el-radio v-model="formData.isSelfCount" label="1" @change="singleOK">否</el-radio>
          </el-form-item>
        </el-col>
        <el-col :span="9">

        </el-col>
      </el-row>


      <el-row v-if="formData.isSelfCount == 0" type="flex" class="row-bg " justify="space-around">
        <el-col :span="9">
          <el-form-item label="个体户注册服务费" :required="true">
            <el-input style="width:87%" v-model="formData.registerMoney"
             :min="0"
              onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
            oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>

        </el-col>
        <el-col :span="9">

        </el-col>

      </el-row>


      <el-collapse v-if="formData.isSelfCount == 0" v-model="activeNamese" accordion
        style="padding-left:8%;padding-right: 6%;">
        <el-collapse-item name="1">
          <template slot="title" :required="true">
            增值税普通发票
              <el-radio   style="margin-left:10px"   v-model="formData.isSliderOrdinary" label="0">开启</el-radio>
              <el-radio   v-model="formData.isSliderOrdinary" label="1">关闭</el-radio>
          </template>
          <el-col :span="10">
           <div v-if="formData.isSliderOrdinary==0">
            <el-form-item label="普票税率" :required="true">
              <!-- <el-select style="width:87%" v-model="formData.ordinaryTax" clearable placeholder="请选择">
                <el-option v-for="item in optiond" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
              </el-select> -->
              <el-input :readonly="true" value="免税">
                
                </el-input>
              
            </el-form-item>
            <el-form-item label="普票服务费" :required="true">
              <div style="">

                <el-radio @change="handPoxy" v-model="formData.ordinaryProxyIsmoney" label="0">按定额收取</el-radio>
                <el-radio @change="handPoxy" v-model="formData.ordinaryProxyIsmoney" label="1">按百分比收取</el-radio>
                <el-input v-if="formData.ordinaryProxyIsmoney == 0"  v-model="formData.ordinarySelfFee"
                  style="width:87%;margin-right:10px"  :min="0"
                   onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                  oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                  >
                  <template slot="append">元</template>
                </el-input>
                <el-input v-else @input="ordinarySelfFeeh" @change="ordinarySelfFeeh" 
                  v-model="formData.ordinarySelfFee" style="width:87%;margin-right:10px"  :min="0"
                  :max="100"
                   onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                  oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                  >
                  <template slot="append">%</template>
                </el-input>
              </div>
            </el-form-item>
            <el-form-item label="服务费含税" :required="true">
              <el-radio v-model="formData.isSelfTax" label='0'>是</el-radio>
              <el-radio v-model="formData.isSelfTax" label='1'>否</el-radio>
            </el-form-item>
            <el-form-item label="价税分离" :required="true">
              <el-radio v-model="formData.isOrdinaryTax" label='0'>是</el-radio>
              <el-radio v-model="formData.isOrdinaryTax" label='1'>否</el-radio>
            </el-form-item>
            <el-form-item label="是否分润">
              <el-radio v-model="formData.isOrdinaryShare" label="0">是</el-radio>
              <el-radio v-model="formData.isOrdinaryShare" label="1">否</el-radio>
            </el-form-item>
            <el-form-item v-if="formData.isOrdinaryShare == 0" label="分润方式" :required="true">
              <div style="">

                <el-radio @change="hanOrshare" v-model="formData.ordinaryShareIsmoney" label="0">按定额收取</el-radio>
                <el-radio @change="hanOrshare" v-model="formData.ordinaryShareIsmoney" label="1">按百分比收取</el-radio>
                <el-input v-if="formData.ordinaryShareIsmoney == 0"  v-model="formData.ordinaryShare"
                  style="margin-right:10px;width:87%;"  :min="0"
                   onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                  oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                  >
                  <template slot="append">元</template>
                </el-input>
                <el-input v-else  @input="ordinaryShareh" @change="ordinaryShareh"
                  v-model="formData.ordinaryShare" style="margin-right:10px;width:87%;"  :min="0"
                  :max="100"
                   onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                  oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                  >
                  <template slot="append">%</template>
                </el-input>
              </div>
            </el-form-item>
             </div>
          </el-col>

        </el-collapse-item>

      </el-collapse>

      <el-collapse v-if="formData.isSelfCount == 0" v-model="activeNameseg" accordion
        style="padding-left:8%;padding-right: 6%;">
        <el-collapse-item name="1">
          <template slot="title" :required="true">
            增值税专用发票
              <el-radio  style="margin-left:10px"  v-model="formData.isSlider" label="0">开启</el-radio>
              <el-radio  v-model="formData.isSlider" label="1">关闭</el-radio>
          </template>
          <el-col :span="10">
           
            <div v-if="formData.isSlider==0">
                 <el-form-item label="专票税率" :required="true">
              <!-- <el-select style="width:87%;" v-model="formData.ordinarySpecialTax" clearable placeholder="请选择">
                <el-option v-for="item in optionz" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
              </el-select> -->
               <el-input :readonly="true" value="3">
                  <template slot="append">%</template>
               </el-input>
            </el-form-item>
            <el-form-item label="专票服务费" :required="true">
              <div style="">
                <el-radio @change="handSpecial" v-model="formData.specialProxyIsmoney" label="0">按定额收取</el-radio>
                <el-radio @change="handSpecial" v-model="formData.specialProxyIsmoney" label="1">按百分比收取</el-radio>
                <el-input v-if="formData.specialProxyIsmoney == 0"  v-model="formData.specialSelfFee"
                  style="margin-right:10px;width:87%;"  :min="0"
                   onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                  oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                  >
                  <template slot="append">元</template>
                </el-input>
                <el-input v-else @change="specialSelfFeeh" @input="specialSelfFeeh" 
                  v-model="formData.specialSelfFee" style="margin-right:10px;width:87%;"  :min="0"
                  :max='100'
                   onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                  oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                  >
                  <template slot="append">%</template>
                </el-input>

              </div>
            </el-form-item>
            <el-form-item label="服务费含税" :required="true">
              <el-radio v-model="formData.isSpecialSelfTax" label='0'>是</el-radio>
              <el-radio v-model="formData.isSpecialSelfTax" label='1'>否</el-radio>
            </el-form-item>
            <el-form-item label="价税分离" :required="true">
              <el-radio v-model="formData.isSpecialTax" label='0'>是</el-radio>
              <el-radio v-model="formData.isSpecialTax" label='1'>否</el-radio>
            </el-form-item>
            <el-form-item label="是否分润">
              <el-radio v-model="formData.isSpecialShare" label="0">是</el-radio>
              <el-radio v-model="formData.isSpecialShare" label="1">否</el-radio>
            </el-form-item>
            <el-form-item v-if="formData.isSpecialShare == 0" label="分润方式" :required="true">
              <div style="">

                <el-radio @change="handMoney" v-model="formData.specialShareIsmoney" label="0">按定额收取</el-radio>
                <el-radio @change="handMoney" v-model="formData.specialShareIsmoney" label="1">按百分比收取</el-radio>
                <el-input v-if="formData.specialShareIsmoney == 0"  v-model="formData.specialShare"
                  style="margin-right:10px;width:87%;"  :min="0"
                   onkeyup="value=value.replace(/[^\x00-\xff]/g, '')" 
                  oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                  >
                  <template slot="append">元</template>
                </el-input>
                <el-input @input="specialShareh" @change="specialShareh" v-model="formData.specialShare" v-else
                  style="margin-right:10px;width:87%;"  :min="0" :max="100"
                   onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                  oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                  >
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
          <el-button type="primary" @click="submitForm" class="btn">修改
          </el-button>
        </el-col>
        <el-col :span="8"></el-col>
      </el-row>


    </el-form>



  </div>
</template>
<script>
var validateIdNumber=(rule, value, callback)=>{
  var reg = /^[1-9]\d{5}((\d{2}(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[13456789]|1[012])(0[1-9]|[12][0-9]|30))|(02(0[1-9]|1[0-9]|2[0-8]))))|(((0[48]|[2468][048]|[13579][26])|(00))0229))\d{2}[0-9Xx]$/;

  var reg1 = /^[1-9]\d{5}((((19|[2-9][0-9])\d{2})(0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|(((19|[2-9][0-9])\d{2})(0[13456789]|1[012])(0[1-9]|[12][0-9]|30))|(((19|[2-9][0-9])\d{2})02(0[1-9]|1[0-9]|2[0-8]))|(((1[6-9]|[2-9][0-9])(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))0229))\d{3}[0-9Xx]$/;
  if (value == '' || value == undefined || value == null) {
    callback();
  } else {
    if (!reg.test(value) && !reg1.test(value)) {
      callback(new Error('输入18位或15位正确的身份证号码！'));
    } else {
      callback();
    }
  }
}
import uploadSmall from '@/components/douploads/uploadSmall'
import crudInformation from '@/api/company/information'
import crudInfo from '@/api/company/info'
import crudEmployed from '@/api/company/employed'
import crudPerson from '@/api/company/person'
import crudRate from '@/api/company/rate'
import crudPlace from '@/api/company/place'
import { getInfo } from '@/api/login'
import { Decimal } from 'decimal.js'
import { all } from "@/api/company/payTaxInfo";

export default {
  name:'ManageListDdits',
  components: {},
  dicts: ['political_status', 'educational_level'],
  components: {
    uploadSmall
  },
  props: [],
  data() {
    return {
      selectTipType:'',
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      activeNameseg: '1',
      activeNamese: '1',
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
      isDetail: '0',
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
        placeAliasName: '',
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
      accountName_options: [],
      mylist: '',
      rules: {
        fileName1: [
          { required: true, message: "请上传营业执照", trigger: "change" },
        ],
        fileName2: [
          { required: true, message: "请上传核定通知书", trigger: "change" },
        ],
        fileName3: [
          { required: true, message: "请上传纳税委托协议", trigger: "change" },
        ],
        fileName4: [
          { required: true, message: "请上传三方协议签约凭证", trigger: "change" },
        ],
        fileName5: [
          { required: true, message: "请上传身份证扫描件", trigger: "change" },
        ],
        fileName6: [
          { required: true, message: "请上传工商实名", trigger: "change" },
        ],
        fileName7: [
          { required: true, message: "请上传税务实名", trigger: "change" },
        ],
        privateDepositBank: [{
          required: true,
          message: '请输入法人私账开户银行',
          trigger: 'blur'
        }],
        privateAccountNumber: [{
          required: true,
          message: '请输入法人私账银行账号',
          trigger: 'blur'
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
        }, 
        // {
        //    pattern: /(^\d{8}(0\d|10|11|12)([0-2]\d|30|31)\d{3}$)|(^\d{6}(18|19|20)\d{2}(0\d|10|11|12)([0-2]\d|30|31)\d{3}(\d|X|x)$)/,
        //     message: '请输入正确的证件号'

        // },
        {
          validator:validateIdNumber,
          required: true, 
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
        businessTerm: [{
          required: true,
          message: '请选择注册时间',
          trigger: 'change'

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
        legalPersonName: [{
          required: true,
          message: '请输入法人账号名称',
          trigger: 'blur'
        }],
        publicDepositBank3: [{
          required: true,
          message: '请选择开户银行',
          trigger: 'change'
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
      industryTypeList: [],
      industryTypes: [],
      expandOnClickNode: true,
    }
  },
  computed: {},
  watch: {
    'formData.industryType': 'selectIndustryType',

    // 'formData.contactName': {
    //   handler: function () {
    //     this.formData.legalPersonName = this.formData.contactName;
    //   },
    //   deep: true
    // }
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




    this.fileName1 = [];
    this.fileName2 = [];
    this.fileName3 = [];
    this.fileName4 = [];
    this.fileName5 = [];
    this.fileName6 = [];
    this.fileName7 = [];
    this.fileNameN1 = [];
    this.fileNameN2 = [];
    this.fileNameN3 = [];
    this.fileNameN4 = [];
    this.fileNameN5 = [];
    this.fileNameN6 = [];
    this.fileNameN7 = [];

    this.formData.ordinaryTax = JSON.stringify(this.formData.ordinaryTax);
    this.formData.ordinarySpecialTax = JSON.stringify(this.formData.ordinarySpecialTax);
    this.formData.isSelfTax = JSON.stringify(this.formData.isSelfTax);
    this.formData.isSpecialSelfTax = JSON.stringify(this.formData.isSpecialSelfTax);
    this.formData.isSelfCount = JSON.stringify(this.formData.isSelfCount);
    this.formData.ordinaryProxyIsmoney = JSON.stringify(this.formData.ordinaryProxyIsmoney);
    this.formData.specialProxyIsmoney = JSON.stringify(this.formData.specialProxyIsmoney);
    this.formData.ordinaryShareIsmoney = JSON.stringify(this.formData.ordinaryShareIsmoney);
    this.formData.specialShareIsmoney = JSON.stringify(this.formData.specialShareIsmoney);
    this.formData.isOrdinaryShare = JSON.stringify(this.formData.isOrdinaryShare);
    this.formData.isSpecialShare = JSON.stringify(this.formData.isSpecialShare);

     if (this.formData.isPublicUser == '0') {
      this.formData.isPublicUser = '0';
    } else {
      this.formData.isPublicUser = '1';
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

    if (this.formData.isSelfCount == '0') {
      this.formData.isSelfCount = '0';
    } else {
      this.formData.isSelfCount = '1';
    }

    if (this.formData.isSelfTax == '0') {
      this.formData.isSelfTax = '0';
    } else {
      this.formData.isSelfTax = '1';
    }
    if (this.formData.isSpecialSelfTax == '0') {
      this.formData.isSpecialSelfTax = '0';
    } else {
      this.formData.isSpecialSelfTax = '1';
    }

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
    this.formData.fileName1 = JSON.parse(this.formData.fileName1);
    this.formData.fileName2 = JSON.parse(this.formData.fileName2);
    this.formData.fileName3 = JSON.parse(this.formData.fileName3);
    this.formData.fileName4 = JSON.parse(this.formData.fileName4);
    this.formData.fileName5 = JSON.parse(this.formData.fileName5);
    this.formData.fileName6 = JSON.parse(this.formData.fileName6);
    this.formData.fileName7 = JSON.parse(this.formData.fileName7);
    this.nailist();
  },
  methods: {
       singleOK(e) {

      if (this.formData.isSelfCount == 0) {
        // this.yecomfirm = false;
        // this.yecomfirms = true;
          this.formData.specialSelfFee ='0';
         this.formData.ordinarySelfFee = '0';
         this.formData.registerMoney = '0';
         this.formData.specialShare = '0';
         this.formData.ordinaryShare = '0';
         this.formData.ordinaryProxyIsmoney = '0'; //普票平台服务费是否定额
         this.formData.specialProxyIsmoney = '0';  //专票平台服务费是否定额
         this.formData.ordinaryShareIsmoney = '0';//普票分润方式是否定额
         this.formData.specialShareIsmoney = '0';//专票分润方式是否定额
         this.formData.isOrdinaryShare = '1';
         this.formData.isSpecialShare = '1';
         this.formData.ordinarySpecialTax = '0.03';
         this.formData.ordinaryTax = '0';
         this.formData.isSlider='0';
         this.formData.isSliderOrdinary='0';
         this.formData.isSpecialSelfTax = '1';
         this.formData.isSelfTax = '1';
         this.formData.isOrdinaryTax = '1';
         this.formData.isSpecialTax = '1';
      

      } else {
        // this.yecomfirm = true;
        // this.yecomfirms = false;
        this.placenew();


      }
    },
    handPoxy(e) {
      if (e == '1') {
        if (this.formData.ordinaryProxyIsmoney == '1') {
          if (this.formData.ordinarySelfFee > 100) {
            this.formData.ordinarySelfFee = '100';
          }
        }
      }
    },
    hanOrshare(e) {
      if (e == '1') {
        if (this.formData.ordinaryShareIsmoney == '1') {
          if (this.formData.ordinaryShare > 100) {
            this.formData.ordinaryShare = '100';
          }
        }
      }
    },
    handSpecial(e) {
      if (e == '1') {
        if (this.formData.specialProxyIsmoney == '1') {
          if (this.formData.specialSelfFee > 100) {
            this.formData.specialSelfFee = '100';
          }
        }
      }
    },
    handMoney(e) {
      if (e == '1') {
        if (this.formData.specialShareIsmoney == '1') {
          if (this.formData.specialShare > 100) {
            this.formData.specialShare = '100';
          }
        }
      }
    },



    ordinarySelfFeeh(e) {
      if (this.formData.ordinaryProxyIsmoney == '1') {
        if (e > 100) {
          this.formData.ordinarySelfFee = '100';
        }
      }
    },
    specialSelfFeeh(e) {
      if (this.formData.specialProxyIsmoney == '1') {
        if (e > 100) {
          this.formData.specialSelfFee = '100';
        }
      }
    },
    specialShareh(e) {
      if (this.formData.specialShareIsmoney == '1') {
        if (e > 100) {
          this.formData.specialShare = '100';
        }
      }
    },
    ordinaryShareh(e) {
      if (this.formData.ordinaryShareIsmoney == '1') {
        if (e > 100) {
          this.formData.ordinaryShare = '100';
        }
      }
    },
    handleNodeClick(node) {
      this.formData.industryType = node.id;
      this.$refs.selectTree.blur();
        this.$nextTick(function(){
        this.selectTipType=this.$refs.selectTree.selected.label; 
      });
    },
     formatData(data) {
      let options = [];
      data.forEach((item, key) => {
        options.push({ label: item.label, value: item.id,taxRates:item.taxRates });
        if (item.children) {
          item.children.forEach((items, keys) => {
            options.push({ label: item.label+'-'+items.label, value: items.id,taxRates:items.taxRates });
            if (items.children) {
              items.children.forEach((itemss, keyss) => {
                options.push({ label: item.label+'-'+items.label+'-'+itemss.label, value: itemss.id,taxRates:itemss.taxRates });
                if (itemss.children) {
                  itemss.children.forEach((itemsss, keysss) => {
                    options.push({ label:item.label+'-'+items.label+'-'+itemss.label+'-'+itemsss.label, value: itemsss.id,taxRates:itemsss.taxRates });
                  });
                }
              });
            }
          });
        }
      });
      return options;
    },
    placenew() {
      for (let i in this.places) {
        if (this.places[i].placeName == this.formData.placeName) {
          this.formData.placeAliasName = this.places[i].placeAliasName;
          crudPlace.selectFeeByCode({ placeCode: this.places[i].placeCode }).then(res => {
            this.unlist = res;
            this.formData.ordinaryProxyIsmoney = JSON.stringify(this.unlist.ordinaryProxyIsmoney);
            this.formData.specialProxyIsmoney = JSON.stringify(this.unlist.specialProxyIsmoney);
            this.formData.ordinaryShareIsmoney = JSON.stringify(this.unlist.ordinaryShareIsmoney);
            this.formData.specialShareIsmoney = JSON.stringify(this.unlist.specialShareIsmoney);
            this.formData.isOrdinaryShare = JSON.stringify(this.unlist.isOrdinaryShare);
            this.formData.isSpecialShare = JSON.stringify(this.unlist.isSpecialShare);

            this.formData.specialSelfFee = this.unlist.specialProxyFee;
            this.formData.ordinarySelfFee = this.unlist.ordinaryProxyFee;
            this.formData.registerMoney = this.unlist.ordinarySelfFee;
            this.formData.specialShare = this.unlist.specialShare;
            this.formData.ordinaryShare = this.unlist.ordinaryShare;

            this.formData.ordinarySpecialTax = JSON.stringify(this.unlist.ordinarySpecialTax);
            this.formData.ordinaryTax = JSON.stringify(this.unlist.ordinaryTax);



            if (this.unlist.isSlider == '0') {
              this.formData.isSlider = '0';
            } else {
              this.formData.isSlider = '1';
            }
             if (this.unlist.isSliderOrdinary == '0') {
              this.formData.isSliderOrdinary = '0';
            } else {
              this.formData.isSliderOrdinary = '1';
            }
            //含税专票
            if (this.unlist.isSpecialTax) {
              this.formData.isSpecialSelfTax = '0';
            } else {
              this.formData.isSpecialSelfTax = '1';
            }
            //普票含税
            if (this.unlist.isOrdinaryTax) {
              this.formData.isSelfTax = '0';
            } else {
              this.formData.isSelfTax = '1';
            }
            //普票价格分离
            if (this.unlist.isSelfTax == '0') {
              this.formData.isOrdinaryTax = '0';
            } else {
              this.formData.isOrdinaryTax = '1';
            }
            //专票价格分离
            if (this.unlist.isSpecialSelfTax == '0') {
              this.formData.isSpecialTax = '0';
            } else {
              this.formData.isSpecialTax = '1';
            }

          });

          return;
        }
      }
    },
    changeValue(res) {
      for (let i in this.mylist) {

        if (this.mylist[i].publicDepositBank3 == res) {
          this.formData.accountName = this.mylist[i].accountName;
          this.formData.publicAccountNumber3 =
            this.mylist[i].publicAccountNumber3;
          return;
        }
      }
    },
    getfileNameS1(data) {
      this.formData.fileName1 = data;
    },
    getfileNameS2(data) {
      this.formData.fileName2 = data;
    },
    getfileNameS3(data) {
      this.formData.fileName3 = data;
    },
    getfileNameS4(data) {
      this.formData.fileName4 = data;
    },
    getfileNameS5(data) {
      this.formData.fileName5 = data;
    },
    getfileNameS6(data) {
      this.formData.fileName6 = data;
    },
    getfileNameS7(data) {
      this.formData.fileName7 = data;
    },
    getLoginInfo() {
      getInfo().then(res => {
        this.userinfo = res.user;
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
        this.formData.privateDepositBank = this.$cache.local.getJSON('employedInfo').privateDepositBank;
        this.formData.privateAccountNumber = this.$cache.local.getJSON('employedInfo').privateAccountNumber;
        this.isPrivateBank = false;
      } else {
        this.isPrivateBank = true;
        this.formData.legalPersonName = "";
        this.formData.privateDepositBank = "";
        this.formData.privateAccountNumber = "";
      }
    },
    selectIndustryType() {
      var rate = this.industryTypeList.find((item) => item.industryId == this.formData.industryType);
      if (rate) {
        this.formData.industryTax = rate.taxRate;
        this.industryTax = new Decimal(rate.taxRate).mul(new Decimal(100)) + '%';
      } else {
        this.formData.industryTax = '';
        this.industryTax = '';
      }
    },
    selectApplyName(value) {
      var applyName = this.applyNames.find((item) => item.userId == value);
      this.formData.applyPhone = applyName.phone;
      this.formData.applyIdNum = applyName.idNo;
      console.log("applyName==", applyName);
    },
    getRate() {
      crudRate.getAllRate().then(res => {
        var employedInfo = this.$cache.local.getJSON('employedInfo');
        this.formData.industryType = employedInfo.industryType;
        let tree = []; // 用来保存树状的数据形式
        this.parseTree(res.rows, tree, 0);
        this.industryTypes = tree;
        this.industryTypeList = res.rows;
         this.industryTypeList = res.rows;
          this.$nextTick(function(){
             this.selectTipType=this.$refs.selectTree.selected.label; 
         });
        this.selectIndustryType();
      })
    },
    //把数据整成树状
    parseTree(industry, tree, pid) {
      for (var i = 0; i < industry.length; i++) {
        if (industry[i].parentId == pid) {
          let a = industry[i].taxRate;
          let b = null;
          if (a) {
            b = new Decimal(a).mul(new Decimal(100));
            b = "税率" + b + '%';
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
    submitForm() {
      if (this.formData.industryType == '-1') {
        this.$modal.msgError("请选择行业类型");
        return;
      }
      if (!this.formData.industryTax) {
        this.$modal.msgError("税率不能为空");
        return;
      }
      if (this.formData.isSlider == '0') {

        if (this.formData.specialShareIsmoney == '1') {
          if (this.formData.specialShare > 100) {
            this.$alert('专票分润费按百分比不能大于100%', '提示', {
              confirmButtonText: '确定',
            });
            return;
          }
        }
        if (this.formData.specialProxyIsmoney == '1') {
          if (this.formData.specialSelfFee > 100) {
            this.$alert('专票服务费按百分比不能大于100%', '提示', {
              confirmButtonText: '确定',
            });
            return;
          }
        }
      }

      if(this.formData.isSliderOrdinary==0){
      if (this.formData.ordinaryShareIsmoney == '1') {
        if (this.formData.ordinaryShare > 100) {
          this.$alert('普票分润费按百分比不能大于100%', '提示', {
            confirmButtonText: '确定',
          });
          return;
        }
      }


      if (this.formData.ordinaryProxyIsmoney == '1') {
        if (this.formData.ordinarySelfFee > 100) {
          this.$alert('普票服务费按百分比不能大于100%', '提示', {
            confirmButtonText: '确定',
          });
          return;
        }
      }
      }

      this.$refs['elForm'].validate(valid => {

        if (valid) {
          
          let parms1 = {
            id: this.formData.id,
            selfCode: this.formData.selfCode,
            oneselfApply: this.formData.oneselfApply,
            applyName: this.formData.applyName,
            applyPhone: this.formData.applyPhone,
            applyDocumentType: this.formData.applyDocumentType,
            applyIdNum: this.formData.applyIdNum,

            contactName: this.formData.contactName,
            contactPhone: this.formData.contactPhone,
            contactDocumentType: this.formData.contactDocumentType,
            contactIdNum: this.formData.contactIdNum,
          };
         
          
            
          
         
          
          this.formData.fileName1 = JSON.stringify(this.formData.fileName1);
          this.formData.fileName2 = JSON.stringify(this.formData.fileName2);
          this.formData.fileName3 = JSON.stringify(this.formData.fileName3);
          this.formData.fileName4 = JSON.stringify(this.formData.fileName4);
          this.formData.fileName5 = JSON.stringify(this.formData.fileName5);
          this.formData.fileName6 = JSON.stringify(this.formData.fileName6);
          this.formData.fileName7 = JSON.stringify(this.formData.fileName7);

          let parms3 = {
            legalPersonId: this.formData.legalPersonId,
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
            isSlider: this.formData.isSlider,
            isSliderOrdinary:this.formData.isSliderOrdinary,
          };
          crudInfo.updateInfo(parms1).then(res => {
            if (res != undefined) {
              if (res.code === 200) {

              } else {
                this.$message({
                  message: res.msg,
                  type: 'danger'
                })
              }
            }
          });
          this.formData.endStatus = 0;
          crudEmployed.updateEmployed(this.formData).then(res => {
            if (res != undefined) {
              if (res.code === 200) {
                let parmsSS = {
               "checkReasult": '注册修改',
               "checkUser": this.userinfo.userName,
               'phonenumber': this.userinfo.phonenumber,
               "selfCode": this.formData.selfCode,
               "selfType": "11",
              }
            crudEmployed.check(parmsSS).then(res => {
             })

              } else {
                this.$message({
                  message: res.msg,
                  type: 'danger'
                })
              }
            }
          });
          crudPerson.updatePerson(parms3).then(res => {
            if (res != undefined) {
              if (res.code === 200) {

              } else {
                this.$message({
                  message: res.msg,
                  type: 'danger'
                })
              }
            }
          });
          this.$tab.closeOpenPage({ path: this.$cache.local.getJSON('backurls').backUrl }).then(() => {
            this.$tab.refreshPage({ path: this.$cache.local.getJSON('backurls').backUrl });
          })
        } else {
          this.$message({
            message: '请填写完整',
            type: 'warning'
          })
        }
      })
    },
    resetForm() {
      this.$tab.closeOpenPage({ path: this.$cache.local.getJSON('backurls').backUrl }).then(() => {
        this.$tab.refreshPage({ path: this.$cache.local.getJSON('backurls').backUrl })
      });
    },
    nailist() {
      all()
        .then((res) => {
          if (res != undefined) {
            console.log(res);
            this.mylist = [];
            this.accountName_options = [];
            this.mylist = res;

            for (let i in res) {
              this.accountName_options.push({ value: res[i].publicDepositBank3 });

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

// ::v-deep .el-input.is-disabled .el-input__inner{
//    background-color: rgba(255, 255, 255, 1.5) !important;
//    color: black  !important;
//    border-color: rgba(135,206,250,0.7) !important;
// }
// ::v-deep .el-input-group__append{
//    background-color: rgba(255, 255, 255, 1.5) !important;
//    color: black  !important;
//    border-color: rgba(135,206,250,0.7) !important;
// }

// ::v-deep .el-collapse {
//   border: 0  !important;

// }
// ::v-deep .el-collapse-item__header{
//   border-bottom:0 !important;
//   font-size: 14px !important;
//   color: #606266 !important;
//   font-weight: bold !important;
// }
// ::v-deep .el-icon-arrow-right:before{
//    color:#333;
// }
</style>
