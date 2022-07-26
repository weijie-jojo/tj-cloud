<template>
  <div>
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="120px" label-position="right">

      <el-steps align-center :active="actives" finish-status="success"
        style="padding-left: 2%;margin-top:30px;margin-bottom:10px;">
        <el-step title="个体户名称"></el-step>
        <el-step title="申请信息"></el-step>
        <el-step title="基本情况"></el-step>
        <el-step title="经营者信息"></el-step>
        <el-step title="业务信息"></el-step>
        <el-step title="确认修改"></el-step>
      </el-steps>

      <!-- <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
        <el-tab-pane label="申请信息" name="first"> -->
      <div v-show="actives == 1">
        <el-row type="flex" class="row-bg  combottom" justify="space-around">
          <el-col :span="8">
            <div class="bankno">申请信息</div>

          </el-col>
          <el-col :span="8">
            <div></div>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg rowCss" justify="space-around">
          <el-col :span="9">
            <el-form-item label="本人申请">
              <el-select style="width:100%" v-model="formData.oneselfApply" placeholder="请选择是否本人申请" disabled>
                <el-option v-for="(item, index) in oneselfApplys" :key="index" :label="item.label" :value="item.label">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="9"></el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="申请人姓名" prop="applyName">
              <el-select style="width:100%" v-model="formData.applyName" placeholder="请选择申请人姓名" clearable filterable
                @change="selectApplyName">
                <el-option v-for="(item, index) in applyNames" :key="index" :label="item.username" :value="item.userId"
                  :disabled="item.disabled"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="9"></el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="移动电话">
              <el-input v-model="formData.applyPhone" disabled>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9"></el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="证件类型">
              <el-input v-model="formData.applyDocumentType" disabled>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="证件号码">
              <el-input v-model="formData.applyIdNum" disabled>
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row type="flex" class="row-bg  combottom" justify="space-around">
          <el-col :span="7">
            <div class="bankno">联络员</div>

          </el-col>
          <el-col :span="8">
            <div></div>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg rowCss combottom" justify="space-around">
          <el-col :span="9">
            <el-form-item label="姓名" prop="contactName">
              <el-input v-model="formData.contactName" clearable>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="移动电话" prop="contactPhone">
              <el-input v-model="formData.contactPhone" clearable>
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg  combottom" justify="space-around">
          <el-col :span="9">
            <el-form-item label="证件类型">
              <el-input v-model="formData.contactDocumentType" disabled>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="证件号码" prop="contactIdNum">
              <el-input v-model="formData.contactIdNum" clearable>
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row type="flex" class="row-bg " justify="space-around">
          <el-col :span="8"></el-col>
          <el-col :span="8" class="flexs">
            <el-button type="danger" @click="toReturn" class="btn">取消</el-button>
            <el-button type="primary" @click="nextbasic" class="btn">下一步</el-button>
          </el-col>
          <el-col :span="8"></el-col>
        </el-row>
      </div>
      <!-- </el-tab-pane> -->

      <!-- <el-tab-pane label="基本情况" name="second"> -->
      <div v-show="actives == 2">
        <el-row type="flex" class="row-bg  combottom" justify="space-around">
          <el-col :span="8">
            <div class="bankno">基本情况</div>

          </el-col>
          <el-col :span="8">
            <div></div>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg rowCss" justify="space-around">
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
        <el-row type="flex" class="row-bg " justify="space-around">
          <el-col :span="9">
            <el-form-item label="出资额">
              <el-input disabled type="number" v-model="formData.contributionAmount">
                <template slot="append">万元</template>
              </el-input>

            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="城市">
              <el-input v-model="formData.city" disabled>
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg " justify="space-around">
          <el-col :span="9">
            <el-form-item label="区（县）">
              <el-input v-model="formData.county" disabled>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="电子商务经营者">
              <el-select style="width:100%" v-model="formData.electronicCommerce" placeholder="请选择是否是电子商务经营者" disabled>
                <el-option v-for="(item, index) in electronicCommerces1" :key="index" :label="item.label"
                  :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg " justify="space-around">
          <el-col :span="21">
            <el-form-item label="经营场所">
              <el-input v-model="formData.selfAddress" disabled>
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg " justify="space-around">
          <el-col :span="9">
            <el-form-item label="联系电话">
              <el-input v-model="formData.contactPhone" disabled>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="所属自贸区">
              <el-input v-model="formData.freeTradeZone" disabled>
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg " justify="space-around">
          <el-col :span="9">
            <el-form-item label="所属自贸片区">
              <el-input v-model="formData.freeTradeArea" disabled>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="产权">
              <el-input v-model="formData.propertyRight" disabled>
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg " justify="space-around">
          <el-col :span="21">
            <el-form-item label="经营范围" prop="natureBusiness">
              <el-input type="textarea" :rows="2" placeholder="请输入经营范围" v-model="formData.natureBusiness">
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg " justify="space-around">
          <el-col :span="9">
            <el-form-item label="银行账号类型" prop="accountType">
              <el-select style="width:100%" v-model="formData.accountType" placeholder="请选择银行账号类型"
                @change="selectAccountType">
                <el-option v-for="(item, index) in accountTypes1" :key="index" :label="item.label" :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="私账名称">
              <el-input v-model="formData.privateName" disabled>
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row v-if="isPrivateBank" type="flex" class="row-bg " justify="space-around">
          <el-col :span="9">
            <el-form-item label="对公开户银行">
              <el-input disabled v-model="formData.publicDepositBank1" clearable>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="对公银行账号">
              <el-input disabled v-model="formData.publicAccountNumber1" clearable>
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row v-else type="flex" class="row-bg " justify="space-around">
          <el-col :span="9">
            <el-form-item label="私账开户银行" prop="privateDepositBank">
              <el-input placeholder="请输入法人私账开户银行" v-model="formData.privateDepositBank" clearable
                :disabled="isPrivateBank">
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="私账银行账号" prop="privateAccountNumber">
              <el-input placeholder="请输入法人私账开户银行" v-model="formData.privateAccountNumber" clearable
                :disabled="isPrivateBank">
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>


        <el-row type="flex" class="row-bg " justify="space-around">
          <el-col :span="9">
            <el-form-item label="渠道商" prop="placeName">
              <el-select @change="placenew" style="width:100%" v-model="formData.placeName" placeholder="请选择渠道商"
                clearable filterable>
                <el-option v-for="(item, index) in places" :key="index" :label="item.placeName" :value="item.placeName">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="客户经理">
              <el-input v-model="formData.userName" disabled>
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg " justify="space-around">
          <el-col :span="8"></el-col>
          <el-col :span="8" class="flexs">
            <el-button type="danger" @click="backinfo" class="btn">返回</el-button>
            <el-button type="primary" @click="nextbus" class="btn">下一步</el-button>
          </el-col>
          <el-col :span="8">

          </el-col>
        </el-row>
      </div>
      <div v-show="actives == 3">
        <el-row type="flex" class="row-bg  combottom" justify="space-around">
          <el-col :span="11">
            <div class="bankno">经营者（负责人）信息</div>

          </el-col>
          <el-col :span="8">
            <div></div>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg rowCss" justify="space-around">
          <el-col :span="9">
            <el-form-item label="姓名">
              <el-input v-model="formData.contactName" disabled>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="人员类型">
              <el-input v-model="formData.personnelType" disabled>
              </el-input>
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
              <el-input v-model="formData.contactIdNum" disabled>
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="性别" prop="gender">
              <el-select style="width:100%" v-model="formData.gender" placeholder="请选择性别" clearable>
                <el-option v-for="(item, index) in genders1" :key="index" :label="item.label" :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="出生日期" prop="dateBirth">
              <el-date-picker style="width:100%" v-model="formData.dateBirth" type="date" placeholder="选择出生日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="民族" prop="nation">
              <el-input v-model="formData.nation" clearable placeholder="请输入法人民族">
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="文化程度" prop="eduation">
              <el-select style="width:100%;" v-model="formData.eduation" placeholder="请输入法人文化程度" clearable>
                <el-option v-for="dict in dict.type.educational_level" :key="dict.value" :label="dict.label"
                  :value="dict.value" />
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
              <el-input v-model="formData.politicalStatus" disabled placeholder="请输入法人政治面貌">
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="21">
            <el-form-item label="经营者居所" prop="residence">
              <el-input v-model="formData.residence" clearable placeholder="请输入法人居住地址">
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="联系电话" prop="contactPhone" placeholder="请输入法人联系电话">
              <el-input v-model="formData.contactPhone" clearable>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="电子邮箱" prop="mail">
              <el-input v-model="formData.mail" clearable placeholder="请输入电子邮箱">
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>


        <el-row type="flex" class="row-bg " justify="space-around">
          <el-col :span="9">

            <el-form-item label="工商实名" prop="fileName6">
              <el-upload class="upload-demo" action="/eladmin/api/files/doUpload" :on-success="handlesuccess2"
                :on-preview="handlePreview2" :on-remove="handleRemove2" :before-remove="beforeRemove2" multiple
                :on-exceed="handleExceed2" :file-list="fileName6" list-type="picture"
                :before-upload="beforeAvatarUpload1"
                >
                <el-button size="small" type="primary">点击上传</el-button>
                 <div slot="tip" class="el-upload__tip" style="color:red">仅支持jpg/png/jpeg/pdf文件,且不超过10M</div>
              </el-upload>
              <el-dialog :visible.sync="dialogVisible2" append-to-body>
                <img width="100%" :src="dialogImageUrl2" alt="" />
              </el-dialog>
            </el-form-item>
          </el-col>

          <el-col :span="9">


            <el-form-item label="税务实名" prop="fileName7">
              <el-upload class="upload-demo" action="/eladmin/api/files/doUpload" :on-success="handlesuccess3"
                :on-preview="handlePreview3" :on-remove="handleRemove3" :before-remove="beforeRemove3" multiple
                :on-exceed="handleExceed3" :file-list="fileName7" list-type="picture"
                :before-upload="beforeAvatarUpload2"
                >
                <el-button size="small" type="primary">点击上传</el-button>
                 <div slot="tip" class="el-upload__tip" style="color:red">仅支持jpg/png/jpeg/pdf文件,且不超过10M</div>
              </el-upload>
              <el-dialog :visible.sync="dialogVisible3" append-to-body>
                <img width="100%" :src="dialogImageUrl3" alt="" />
              </el-dialog>
            </el-form-item>
          </el-col>
        </el-row>


        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="身份证扫描件">
              <!-- <el-button type="primary" @click="toEditImg">点击上传</el-button> -->
              <el-upload class="upload-demo" action="/eladmin/api/files/doUpload" :on-success="handlesuccess1"
                :on-preview="handlePreview1" :on-remove="handleRemove1" :before-remove="beforeRemove1" multiple
                :on-exceed="handleExceed1" :file-list="fileName5" list-type="picture"
                :before-upload="beforeAvatarUpload3"
                >
                <el-button size="small" type="primary">点击上传</el-button>
                 <div slot="tip" class="el-upload__tip" style="color:red">仅支持jpg/png/jpeg/pdf文件,且不超过10M</div>
              </el-upload>
              <el-dialog :visible.sync="dialogVisible1" append-to-body>
                <img width="100%" :src="dialogImageUrl1" alt="" />
              </el-dialog>
            </el-form-item>
          </el-col>
          <el-col :span="9"></el-col>
        </el-row>





        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="8"></el-col>
          <el-col :span="8" class="flexs">
            <el-button type="danger" @click="backbs" class="btn">返回</el-button>
            <el-button type="primary" @click="nextYe" class="btn">下一步</el-button>

          </el-col>
          <el-col :span="8">

          </el-col>
        </el-row>
      </div>
      <div v-show="actives == 4">
        <el-row type="flex" class="row-bg  combottom" justify="space-around">
          <el-col :span="8">
            <div class="bankno">业务信息</div>

          </el-col>
          <el-col :span="8">
            <div></div>
          </el-col>
        </el-row>

        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="渠道商" prop="placeName">
              <el-select style="width:100%" v-model="formData.placeName" placeholder="请选择渠道商" clearable filterable>
                <el-option v-for="(item, index) in places" :key="index" :label="item.placeName" :value="item.placeName">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="客户经理">
              <el-input v-model="formData.userName" disabled>
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="行业类型" prop="industryType">
              <el-select class="main-select-tree" ref="selectTree" v-model="formData.industryType" style="width: 100%;">
                <el-option v-for="item in formatData(industryTypes)" :key="item.value" :label="item.label"
                  :value="item.value" style="display: none;" />
                <el-tree class="main-select-el-tree" ref="selecteltree" :data="industryTypes" node-key="id"
                  highlight-current :props="defaultProps" @node-click="handleNodeClick"
                  :current-node-key="formData.industryType" :expand-on-click-node="expandOnClickNode"
                  default-expand-all />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="行业税率">
              <el-input v-model="formData.industryTax" disabled>
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="单独结算" :required="true">
              <el-radio v-model="singleRadio" label="1" @change="singleOK">是</el-radio>
              <el-radio v-model="singleRadio" label="2" @change="singleOK">否</el-radio>
            </el-form-item>
          </el-col>
          <el-col :span="9">

          </el-col>
        </el-row>

        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item label="普票服务费" :required="yecomfirms">
              <div style="">

                <el-radio :disabled="yecomfirm" v-model="basicRadio" label="1">按定额收取</el-radio>
                <el-radio :disabled="yecomfirm" v-model="basicRadio" label="2">按百分比收取</el-radio>
                <el-input v-if="basicRadio == 1" :disabled="yecomfirm" type="number"
                  v-model="formData.ordinarySelfMoney" style="margin-right:10px" :step="0.01" :min="0" :max="9999">
                  <template slot="append">元</template>
                </el-input>
                <el-input v-else :disabled="yecomfirm" type="number" v-model="formData.ordinarySelfFee"
                  style="margin-right:10px" :step="0.01" :min="0" :max="100">
                  <template slot="append">%</template>
                </el-input>
              </div>
            </el-form-item>
          </el-col>

          <el-col :span="9">
            <el-form-item label="专票服务费" :required="yecomfirms">
              <div style="">
                <el-radio :disabled="yecomfirm" v-model="vipRadio" label="1">按定额收取</el-radio>
                <el-radio :disabled="yecomfirm" v-model="vipRadio" label="2">按百分比收取</el-radio>
                <el-input v-if="vipRadio == 1" :disabled="yecomfirm" type="number" v-model="formData.specialSelfMoney"
                  style="margin-right:10px" :step="0.01" :min="0">
                  <template slot="append">元</template>
                </el-input>
                <el-input v-else :disabled="yecomfirm" type="number" v-model="formData.specialSelfFee"
                  style="margin-right:10px" :step="0.01" :min="0">
                  <template slot="append">%</template>
                </el-input>

              </div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg " justify="space-around">
          <el-col :span="9">
            <el-form-item label="服务费含税">
              <el-radio :disabled="yecomfirm" v-model="formData.isSelfTax" label='0'>是</el-radio>
              <el-radio :disabled="yecomfirm" v-model="formData.isSelfTax" label='1'>否</el-radio>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="注册服务费" :required="yecomfirms">
              <el-input v-model="formData.registerMoney" :disabled="yecomfirm">
                <template slot="append">元</template>
              </el-input>
            </el-form-item>
          </el-col>

        </el-row>


        <el-row type="flex" class="row-bg " justify="space-around">
          <el-col :span="8"></el-col>
          <el-col :span='8' class="flexs">
            <el-button type="danger" @click="backBus" class="btn">返回</el-button>
            <el-button type="primary" @click="submitForm3" class="btn" v-hasPermi="['company:employed:add']">确认修改
            </el-button>

          </el-col>
          <el-col :span="8"></el-col>
        </el-row>

      </div>
    </el-form>
    <!--PDF 预览-->
    <el-dialog :title="titles" :visible.sync="viewVisible" width="80%" center @close='closeDialog'>

      <div>
        <div class="tools flexs" style=" align-items: center;">
          <div class="page" style="margin-right:20px;font-size: 20px;">共{{ pageNum }}/{{ pageTotalNum }} </div>
          <el-button :theme="'default'" type="submit" @click.stop="prePage" class="mr10"> 上一页</el-button>
          <el-button :theme="'default'" type="submit" @click.stop="nextPage" class="mr10"> 下一页</el-button>
          <el-button :theme="'default'" type="submit" @click.stop="clock" class="mr10"> 顺时针</el-button>
          <el-button :theme="'default'" type="submit" @click.stop="counterClock" class="mr10"> 逆时针</el-button>

        </div>
        <pdf ref="pdf" :src="url" :page="pageNum" :rotate="pageRotate" @progress="loadedRatio = $event"
          @page-loaded="pageLoaded($event)" @num-pages="pageTotalNum = $event" @error="pdfError($event)"
          @link-clicked="page = $event">
        </pdf>

      </div>
    </el-dialog>
  </div>
</template>
<script>
import pdf from 'vue-pdf'
import crudInformation from '@/api/company/information'
import crudPerson from '@/api/company/person'
import crudInfo from '@/api/company/info'
import crudEmployed from '@/api/company/employed'
import crudRate from '@/api/company/rate'
import crudPlace from '@/api/company/place'
import { getInfo } from '@/api/login'
export default {
  components: {
    pdf
  },
  dicts: ['political_status', 'educational_level'],
  props: [],
  data() {
    return {

      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
      //pdf预览
      titles: '',
      url: '',
      viewVisible: false,
      pageNum: 1,
      pageTotalNum: 1,
      pageRotate: 0,
      // 加载进度
      loadedRatio: 0,
      curPageNum: 0,
      closeDialog: false,

      yecomfirm: true,
      yecomfirms: false,
      unlist: {
        specialSelfFee: 0,//专票个体户代办费(率)
        specialSelfMoney: 0,//专票个体户代办费(元）
        ordinarySelfFee: 0,//普票个体户代办费(率)
        ordinarySelfMoney: 0,//普票个体户代办费(元）
        isSelfTax: '0',  //个体户服务费是否含税
        registerMoney: '', //注册服务费
      },
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      expandOnClickNode: true,
      actives: 1,
      imgDialog: false,
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
      electronicCommerces1: [],
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
      genders1: [],
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
      fileName5: [],
      fileName6: [],
      fileName7: [],
      eduations: [],
      politicalStatuList: [],
      applyNames: [],
      contactNames: [],
      industryTypes: [],
      industryTypeList: [],
      accountTypes1: [],
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
      vipRadio: '1',
      basicRadio: '1',
      singleRadio: '2',
      IsSpecialTax: '0',
      places: [],
      dialogImageUrl1: '',
      dialogVisible1: false,
      dialogImageUrl2: '',
      dialogVisible2: false,
      dialogImageUrl3: '',
      dialogVisible3: false,
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
      formData: {
        specialSelfFee: 0,//专票个体户代办费(率)
        specialSelfMoney: 0,//专票个体户代办费(元）
        ordinarySelfFee: 0,//普票个体户代办费(率)
        ordinarySelfMoney: 0,//普票个体户代办费(元）
        isSelfTax: '0',  //个体户服务费是否含税
        registerMoney: '', //注册服务费
        selfCode: '',

        //申请信息
        id: '',
        oneselfApply: '',//1是 2否 是否本人申请
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
        selfId: '',
        organizationalForm: '个人经营',
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
        accountType: '',
        legalPersonName: '',
        privateDepositBank: '',
        privateAccountNumber: '',
        publicDepositBank1: '',
        publicAccountNumber1: '',
        placeName: '',
        userName: '',
        privateName: '',//私账名

        //经营者
        legalPersonId: '',
        personnelType: '待业人员',
        gender: '',//1 男 2女
        dateBirth: '',
        nation: '',
        eduation: '',
        occupationalStatus: '待业人员',
        residence: '',
        mail: '',
        idCard: '',
        politicalStatus: '',
        fileName5: [],
        fileName6: [],
        fileName7: [],
      },
      rules: {
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
        fileName7: [{
          required: true,
          message: '请上传税务实名',
          trigger: 'change'
        }],
        fileName6: [{
          required: true,
          message: '请上传工商实名',
          trigger: 'change'
        }],
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
          message: '请输入组成形式',
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
          message: '请输入法人民族',
          trigger: 'blur'
        }],
        eduation: [{
          required: true,
          message: '请选择法人文化程度',
          trigger: 'change'
        }],
        occupationalStatus: [{
          required: true,
          message: '请输入申请前职业状况',
          trigger: 'blur'
        }],
        politicalStatus: [{
          required: true,
          message: '请选择法人政治面貌',
          trigger: 'change'
        }],
        residence: [{
          required: true,
          message: '请输入法人居住地址',
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
    'formData.industryType': 'selectIndustryType',

    'formData.contactName': {
      handler: function () {
        this.formData.legalPersonName = this.formData.contactName;
      },
      deep: true
    }
  },

  mounted() {
    this.getLoginInfo();

    //联系人
    this.getContactName();
    //个体户行业类型税率
    this.getRate();
    //申请人
    this.getApplyName();
    //从上一个页面获取信息
    var employedInfo = this.$cache.local.getJSON('employedInfo');
    this.formData = employedInfo;
    this.formData.fileName5 = JSON.parse(this.formData.fileName5);
    this.formData.fileName6 = JSON.parse(this.formData.fileName6);
    this.formData.fileName7 = JSON.parse(this.formData.fileName7);
    this.fileName5 = [];
    this.fileName6 = [];
    this.fileName7 = [];
    let arr = this.formData.fileName5;
    let brr = this.formData.fileName6;
    let crr = this.formData.fileName7;

    for (let i in arr) {
      this.fileName5.push({
        name: arr[i],
        url: this.baseImgPath + arr[i]
      })
    }



    for (let j in brr) {
      this.fileName6.push({
        name: brr[j],
        url: this.baseImgPath + brr[j]
      })
    }



    for (let k in crr) {
      this.fileName7.push({
        name: crr[k],
        url: this.baseImgPath + crr[k]
      })
    }



    if (this.formData.accountType == 1) {//银行账号类型为私人时
      this.formData.privateName = this.formData.legalPersonName;
      this.isPrivateBank = false;
    }
    if (this.formData.accountType == 2) {
      this.isPrivateBank = true;
    }

    this.getElectronicCommerce();
    this.getAccountType();
    this.getGender();
  },
  methods: {
      beforeAvatarUpload1(file){
     
       const isLt2M = file.size / 1024 / 1024 < 5;
       const fileSuffix = file.name.substring(file.name.lastIndexOf(".") + 1);
       const whiteList = ["jpg", "png",'pdf','jpeg'];
       if (whiteList.indexOf(fileSuffix) === -1) {
       this.$message.error('上传文件只能是 jpg,png,jpeg,pdf格式');
         return false;
      }
       if (!isLt2M) {
          this.$message.error('上传文件大小不能超过 10MB!');
          return false;
        }
        return fileSuffix&isLt2M;
       
    },
      beforeAvatarUpload2(file){
     
       const isLt2M = file.size / 1024 / 1024 < 5;
       const fileSuffix = file.name.substring(file.name.lastIndexOf(".") + 1);
       const whiteList = ["jpg", "png",'pdf','jpeg'];
       if (whiteList.indexOf(fileSuffix) === -1) {
       this.$message.error('上传文件只能是 jpg,png,jpeg,pdf格式');
         return false;
      }
       if (!isLt2M) {
          this.$message.error('上传文件大小不能超过 10MB!');
          return false;
        }
        return fileSuffix&isLt2M;
       
    },
      beforeAvatarUpload3(file){
     
       const isLt2M = file.size / 1024 / 1024 < 5;
       const fileSuffix = file.name.substring(file.name.lastIndexOf(".") + 1);
       const whiteList = ["jpg", "png",'pdf','jpeg'];
       if (whiteList.indexOf(fileSuffix) === -1) {
       this.$message.error('上传文件只能是 jpg,png,jpeg,pdf格式');
         return false;
      }
       if (!isLt2M) {
          this.$message.error('上传文件大小不能超过 10MB!');
          return false;
        }
        return fileSuffix&isLt2M;
       
    },


    // 上一页函数，
    prePage() {
      var page = this.pageNum
      page = page > 1 ? page - 1 : this.pageTotalNum
      this.pageNum = page
    },
    // 下一页函数
    nextPage() {
      var page = this.pageNum
      page = page < this.pageTotalNum ? page + 1 : 1
      this.pageNum = page
    },
    // 页面顺时针翻转90度。
    clock() {
      this.pageRotate += 90
    },
    // 页面逆时针翻转90度。
    counterClock() {
      this.pageRotate -= 90
    },
    // 页面加载回调函数，其中e为当前页数
    pageLoaded(e) {
      this.curPageNum = e
    },
    // 其他的一些回调函数。
    pdfError(error) {
      console.error(error)
    },
    singleOK() {
      if (this.singleRadio == 1) {
        this.yecomfirm = false;
        this.yecomfirms = true;
        this.formData.specialSelfFee = 0;
        this.formData.specialSelfMoney = 0;
        this.formData.ordinarySelfFee = 0;
        this.formData.ordinarySelfMoney = 0;
        this.formData.isSelfTax = '0';
        this.formData.registerMoney = '';
      } else {
        this.yecomfirm = true;
        this.yecomfirms = false;
        this.placenew();


      }
    },
    placenew() {
      for (let i in this.places) {
        if (this.places[i].placeName == this.formData.placeName) {
          crudPlace.selectFeeByCode({ placeCode: this.places[i].placeCode }).then(res => {
            this.unlist = res;
            this.formData.specialSelfFee = this.unlist.specialInvoice13;
            this.formData.specialSelfMoney = this.unlist.specialInvoice13Money;
            this.formData.ordinarySelfFee = this.unlist.ordinaryProxyFee;
            this.formData.ordinarySelfMoney = this.unlist.ordinaryProxyMoney;
            if (this.unlist.isOrdinaryTax) {
              this.formData.isSelfTax = '0';
            } else {
              this.formData.isSelfTax = '1';
            }
            this.formData.registerMoney = this.unlist.ordinarySelfFee;

          });

          return;
        }
      }
    },
    handleNodeClick(node) {
      this.formData.industryType = node.id;
      this.$refs.selectTree.blur();
    },
    // 四级菜单

    formatData(data) {
      let options = [];
      data.forEach((item, key) => {
        options.push({ label: item.label, value: item.id });
        if (item.children) {
          item.children.forEach((items, keys) => {
            options.push({ label: items.label, value: items.id });
            if (items.children) {
              items.children.forEach((itemss, keyss) => {
                options.push({ label: itemss.label, value: itemss.id });
                if (itemss.children) {
                  itemss.children.forEach((itemsss, keysss) => {
                    options.push({ label: itemsss.label, value: itemsss.id });
                  });
                }
              });
            }
          });
        }
      });
      return options;
    },
    backBus() {
      this.actives = 3;
    },
    nextYe() {
      this.actives = 4;
    },
    getLoginInfo() {
      getInfo().then(res => {
        this.formData.userName = res.user.nickName;
        crudPlace.getPlaceByUserId({ userId: res.user.userId }).then(res => {

          this.places = res.data;
        })
      })
    },
    selectAccountType(value) {
      if (value == 1) {
        this.formData.privateName = this.formData.legalPersonName;
        this.isPrivateBank = false;
      } else {
        this.isPrivateBank = true;
        this.formData.privateName = "";
        this.formData.privateDepositBank = "";
        this.formData.privateAccountNumber = "";
      }
    },
    selectIndustryType() {

      var rate = this.industryTypeList.find((item) => item.industryId == this.formData.industryType);
      if (rate) {
        this.formData.industryTax = rate.taxRate;
      } else {
        this.formData.industryTax = '';
      }


    },
    selectApplyName(value) {
      var applyName = this.applyNames.find((item) => item.userId == value);
      this.formData.applyPhone = applyName.phone;
      this.formData.applyIdNum = applyName.idNo;

    },
    getRate() {
      crudRate.getAllRate().then(res => {
        var employedInfo = this.$cache.local.getJSON('employedInfo');
        this.formData.industryType = employedInfo.industryType;
        let tree = []; // 用来保存树状的数据形式
        this.parseTree(res.rows, tree, 0);
        this.industryTypes = tree;
        this.industryTypeList = res.rows;
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
    getContactName() {
      crudPerson.getAllPerson().then(res => {
        this.contactNames = res.rows;
      })
    },
    getApplyName() {
      crudInformation.getAllInformation().then(res => {
        this.applyNames = res.rows;
        var employedInfo = this.$cache.local.getJSON('employedInfo');
        this.formData.applyName = parseInt(employedInfo.applyName);
      })
    },
    getElectronicCommerce() {
      this.electronicCommerces1 = this.electronicCommerces;
      var employedInfo = this.$cache.local.getJSON('employedInfo');
      this.formData.electronicCommerce = parseInt(employedInfo.electronicCommerce);
    },
    getAccountType() {
      this.accountTypes1 = this.accountTypes;
      var employedInfo = this.$cache.local.getJSON('employedInfo');
      this.formData.accountType = parseInt(employedInfo.accountType);
    },
    getGender() {
      this.genders1 = this.genders;
      var employedInfo = this.$cache.local.getJSON('employedInfo');
      this.formData.gender = parseInt(employedInfo.gender);
    },
    handleClick(tab, event) {

    },
    nextbasic() {
      this.actives = 2;
    },
    toReturn() {
      //this.$router.push("employed")
      this.$tab.closeOpenPage({ path: "/company/customer/employed" });
    },

    nextbus() {
      this.actives = 3;
    },
    backinfo() {
      this.actives = 1;
    },

    submitForm3() {
      if (!this.formData.industryTax) {
        this.$modal.msgError("税率不能为空");
        return;
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

            // createTime:new Date().toLocaleString(),
            // updateTime:new Date().toLocaleString(),
            // createBy:this.formData.userName,
            // updateBy:this.formData.userName,

          };
          let parms2 = {
            selfId: this.formData.selfId,
            selfCode: this.formData.selfCode,
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
            industryType: this.formData.industryType,
            industryTax: this.formData.industryTax,
            accountType: this.formData.accountType,
            legalPersonName: this.formData.contactName,
            privateDepositBank: this.formData.privateDepositBank,
            privateAccountNumber: this.formData.privateAccountNumber,
            placeName: this.formData.placeName,
            username: this.formData.userName,
            fileName5: JSON.stringify(this.formData.fileName5),
            fileName6: JSON.stringify(this.formData.fileName6),
            fileName7: JSON.stringify(this.formData.fileName7),
            publicDepositBank1: this.formData.publicDepositBank1,
            publicAccountNumber1: this.formData.publicAccountNumber1,
            // createTime:new Date().toLocaleString(),
            // updateTime:new Date().toLocaleString(),
            // createBy:this.formData.userName,
            // updateBy:this.formData.userName,

            // businessStatus:0,
            // infoStatus:1,
            // taxStatus:0,
            // bankStatus:0,
          };
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

            // createTime:new Date().toLocaleString(),
            // updateTime:new Date().toLocaleString(),
            // createBy:this.formData.userName,
            // updateBy:this.formData.userName,

          };
          crudInfo.updateInfo(parms1).then(res => {
            if (res != undefined) {
              if (res.code === 200) {
                this.$message({
                  message: res.msg,
                  type: 'success'
                })
              } else {
                this.$message({
                  message: res.msg,
                  type: 'danger'
                })
              }
            }
          });
          crudEmployed.updateEmployed(parms2).then(res => {
            if (res != undefined) {
              if (res.code === 200) {
                this.$message({
                  message: res.msg,
                  type: 'success'
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
                this.$message({
                  message: res.msg,
                  type: 'success'
                })
              } else {
                this.$message({
                  message: res.msg,
                  type: 'danger'
                })
              }
            }
          });
          // this.$router.push("employed");
          this.$tab.refreshPage("/company/customer/employed").then(() => {
            this.$tab.openPage("注册进度", "/company/customer/employed")
          })
        } else {
          this.$message({
            message: '请填写完整',
            type: 'warning'
          })
        }
      })
    },
    backbs() {
      this.actives = 2;
    },
    toEditImg() {
      this.imgDialog = true;
      this.formData.fileName5 = [];
    },
    //取消按钮
    cancel() {
      this.imgDialog = false;
    },
    handlesuccess1(file, fileList) {
      this.formData.fileName5.push(file.obj);

    },
    handleRemove1(file, fileList) {
      const i = this.formData.fileName5.findIndex((item) => item === fileList)
      this.formData.fileName5.splice(i, 1);
    },
    handlePreview1(file) {

      if (file.hasOwnProperty('response')) {
        if (file.response.obj.substring(file.response.obj.lastIndexOf('.') + 1) == 'pdf') {
          this.titles = '正在预览' + file.response.obj;
          this.viewVisible = true;
          this.url = this.baseImgPath + file.response.obj;
        } else {
          this.dialogImageUrl1 = file.url;
          this.dialogVisible1 = true;
        }
      } else {
        if (file.url.substring(file.url.lastIndexOf('.') + 1) == 'pdf') {
          this.titles = '正在预览' + file.url;
          this.viewVisible = true;
          this.url = file.url;
        } else {
          this.dialogImageUrl1 = file.url;
          this.dialogVisible1 = true;
        }
      }

    },
    handleExceed1(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove1(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },

    handlesuccess2(file, fileList) {
      this.formData.fileName5.push(file.obj);

    },
    handleRemove2(file, fileList) {
      const i = this.formData.fileName5.findIndex((item) => item === fileList)
      this.formData.fileName5.splice(i, 1);
    },
    handlePreview2(file) {
      // this.dialogImageUrl1 = file.url;
      // this.dialogVisible1 = true;
      if (file.hasOwnProperty('response')) {
        if (file.response.obj.substring(file.response.obj.lastIndexOf('.') + 1) == 'pdf') {
          this.titles = '正在预览' + file.response.obj;
          this.viewVisible = true;
          this.url = this.baseImgPath + file.response.obj;
        } else {
          this.dialogImageUrl2 = file.url;
          this.dialogVisible2 = true;
        }
      } else {
        if (file.url.substring(file.url.lastIndexOf('.') + 1) == 'pdf') {
          this.titles = '正在预览' + file.url;
          this.viewVisible = true;
          this.url = file.url;
        } else {
          this.dialogImageUrl2 = file.url;
          this.dialogVisible2 = true;
        }
      }



    },
    handleExceed2(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove2(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },

    handlesuccess3(file, fileList) {
      this.formData.fileName5.push(file.obj);

    },
    handleRemove3(file, fileList) {
      const i = this.formData.fileName5.findIndex((item) => item === fileList)
      this.formData.fileName5.splice(i, 1);
    },
    handlePreview3(file) {
      if (file.hasOwnProperty('response')) {
        if (file.response.obj.substring(file.response.obj.lastIndexOf('.') + 1) == 'pdf') {
          this.titles = '正在预览' + file.response.obj;
          this.viewVisible = true;
          this.url = this.baseImgPath + file.response.obj;
        } else {
          this.dialogImageUrl3 = file.url;
          this.dialogVisible3 = true;
        }
      } else {
        if (file.url.substring(file.url.lastIndexOf('.') + 1) == 'pdf') {
          this.titles = '正在预览' + file.url;
          this.viewVisible = true;
          this.url = file.url;
        } else {
          this.dialogImageUrl3 = file.url;
          this.dialogVisible3 = true;
        }
      }
    },
    handleExceed3(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove3(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
  }
}

</script>
<style rel="stylesheet/scss" lang="scss" scoped>
.rowCss {
  margin-top: 10px;
}

.btn {
  width: 90px;
}

// 改变input框字体颜色
::v-deep .is-disabled .el-input__inner {
  background-color: transparent !important;
  color: black;
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
</style>
