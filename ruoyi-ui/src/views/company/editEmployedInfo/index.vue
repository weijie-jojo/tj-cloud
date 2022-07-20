<template>
  <div>
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="120px" label-position="right">

      <el-steps
      align-center
      :active="actives" finish-status="success"
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
          <div v-show="actives==1">
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
                  <el-option v-for="(item, index) in oneselfApplys" :key="index" :label="item.label"
                    :value="item.label"></el-option>
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
                  <el-option v-for="(item, index) in applyNames" :key="index" :label="item.username"
                    :value="item.userId" :disabled="item.disabled"></el-option>
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
          <div v-show="actives==2">
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
              <el-form-item label="出资额" prop="contributionAmount">
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
              <el-form-item label="行业类型" prop="industryType">
                <treeselect v-model="formData.industryType" :options="industryTypes" :show-count="true"
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
            <el-col :span="9">
              <el-form-item label="行业税率">
                <el-input v-model="formData.industryTax" disabled>
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row type="flex" class="row-bg " justify="space-around">
            <el-col :span="9">
              <el-form-item label="银行账号类型" prop="accountType">
                <el-select style="width:100%" v-model="formData.accountType" placeholder="请选择银行账号类型" @change="selectAccountType">
                  <el-option v-for="(item, index) in accountTypes1" :key="index" :label="item.label"
                    :value="item.value"></el-option>
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
              <el-form-item label="私账开户银行">
                <el-input v-model="formData.privateDepositBank" clearable :disabled="isPrivateBank">
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item label="私账银行账号">
                <el-input v-model="formData.privateAccountNumber" clearable :disabled="isPrivateBank">
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>


          <el-row type="flex" class="row-bg " justify="space-around">
            <el-col :span="9">
              <el-form-item label="渠道商" prop="placeName">
                <el-select style="width:100%" v-model="formData.placeName" placeholder="请选择渠道商" clearable filterable>
                  <el-option v-for="(item, index) in places" :key="index" :label="item.placeName"
                    :value="item.placeName"></el-option>
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
        <!-- </el-tab-pane> -->

        <!-- <el-tab-pane label="经营者" name="third"> -->
               <div v-show="actives==3">
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
                <el-input v-model="formData.nation" clearable>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item label="文化程度" prop="eduation">
                <el-select style="width:100%;" v-model="formData.eduation" placeholder="文化程度" clearable>
                  <el-option v-for="dict in dict.type.educational_level" :key="dict.value" :label="dict.label"
                    :value="dict.value" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row  type="flex" class="row-bg" justify="space-around">
            <el-col :span="9">
              <el-form-item label="申请前职业状况">
                <el-input v-model="formData.occupationalStatus" disabled>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item label="政治面貌" prop="politicalStatus">
                <el-input v-model="formData.politicalStatus" disabled>
                </el-input>
                <!-- <el-select v-model="formData.politicalStatus" placeholder="文化程度" clearable>
                    <el-option
                      v-for="dict in dict.type.political_status"
                      :key="dict.value"
                      :label="dict.label"
                      :value="dict.value"
                    />
                  </el-select> -->
              </el-form-item>
            </el-col>
          </el-row>
          <el-row type="flex" class="row-bg" justify="space-around">
            <el-col :span="21">
              <el-form-item label="经营者居所" prop="residence">
                <el-input v-model="formData.residence" clearable>
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row type="flex" class="row-bg" justify="space-around">
            <el-col :span="9">
              <el-form-item label="联系电话" prop="contactPhone">
                <el-input v-model="formData.contactPhone" clearable>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item label="电子邮箱" prop="mail">
                <el-input v-model="formData.mail" clearable>
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>
          
           
         <el-row  type="flex" class="row-bg " justify="space-around">
        <el-col :span="9">
          
          <el-form-item  label="工商实名" prop="fileName6">
            <el-upload
              class="upload-demo"
              action="http://36.133.2.179:8000/api/files/doUpload"
              :on-success="handlesuccess2"
              :on-preview="handlePreview2"
              :on-remove="handleRemove2"
              :before-remove="beforeRemove2"
               multiple
              :on-exceed="handleExceed2"
              :file-list="fileName6"
              list-type="picture"
            >
              <el-button size="small" type="primary">点击上传</el-button>
            </el-upload>
            <el-dialog :visible.sync="dialogVisible2" append-to-body>
              <img width="100%" :src="dialogImageUrl2" alt="" />
            </el-dialog>
          </el-form-item>
        </el-col>
        
        <el-col :span="9">
          
           
          <el-form-item label="税务实名" prop="fileName7">
            <el-upload
              class="upload-demo"
              action="http://36.133.2.179:8000/api/files/doUpload"
              :on-success="handlesuccess3"
              :on-preview="handlePreview3"
              :on-remove="handleRemove3"
              :before-remove="beforeRemove3"
               multiple
              :on-exceed="handleExceed3"
              :file-list="fileName7"
              list-type="picture"
            >
              <el-button size="small" type="primary">点击上传</el-button>
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
                 <el-upload
              class="upload-demo"
              action="http://36.133.2.179:8000/api/files/doUpload"
              :on-success="handlesuccess1"
              :on-preview="handlePreview1"
              :on-remove="handleRemove1"
              :before-remove="beforeRemove1"
               multiple
              :on-exceed="handleExceed1"
              :file-list="fileName5"
              list-type="picture"
            >
              <el-button size="small" type="primary">点击上传</el-button>
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
        <!-- </el-tab-pane>
      </el-tabs> -->

         <div v-show="actives==4">
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
                  <el-option v-for="(item, index) in places" :key="index" :label="item.placeName"
                    :value="item.placeName"></el-option>
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
                <treeselect v-model="formData.industryType" :options="industryTypes" :show-count="true"
                  placeholder="请选择归属部门" />
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
                   <el-form-item label="单独结算">
                      <el-radio v-model="singleRadio" label="1">是</el-radio>
                      <el-radio v-model="singleRadio" label="2">否</el-radio>
                   </el-form-item>
               </el-col>
               <el-col :span="9"></el-col>
           </el-row>
              
           <el-row type="flex" class="row-bg" justify="space-around">
              <el-col :span="9">
              <el-form-item  label="普票服务费">
                <div style="display:flex;">
                     <el-radio v-model="basicRadio"  label="1">
               <el-input-number  
               :step="0.01" :precision="2" :min="0" ></el-input-number> (%)
                </el-radio>
                <el-radio style="margin-left:-20px" v-model="basicRadio"  label="2" >
                       <el-input-number 
               :step="0.01" :precision="2" :min="0" ></el-input-number> (元)
                </el-radio>

                </div>
              
                </el-form-item>
              </el-col>
              
              <el-col :span="9">
                 <el-form-item  label="专票服务费">
                  <div style="display:flex;">
                       <el-radio v-model="vipRadio"  label="1">
               <el-input-number  
               :step="0.01" :precision="2" :min="0" ></el-input-number> (%)
                </el-radio>
                <el-radio style="margin-left:-20px" v-model="vipRadio"  label="2" >
                       <el-input-number 
               :step="0.01" :precision="2" :min="0" ></el-input-number> (元)
                </el-radio>
                   </div>
                 </el-form-item>
              </el-col>
          </el-row>
            <el-row type="flex" class="row-bg " justify="space-around">
                  <el-col :span="9">
                  <el-form-item label="服务费含税">
                    <el-radio  v-model="IsSpecialTax" label='0'>是</el-radio>
                    <el-radio  v-model="IsSpecialTax" label='1'>否</el-radio>
                  </el-form-item>
                  </el-col>
                   <el-col :span="9">
                       <el-form-item label="注册服务费">
                      <el-input ></el-input>
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








      <!-- <el-dialog title="身份证扫描件修改" :visible.sync="imgDialog" width="70%">
        <el-upload list-type="picture-card" action="http://36.133.2.179:8000/api/files/doUpload"
          :on-success="handlesuccess1" :on-preview="handlePreview1" :on-remove="handleRemove1"
          :before-remove="beforeRemove1" multiple :limit="9" :on-exceed="handleExceed1" :file-list="formData.fileName5">
          <i class="el-icon-plus"></i>
        </el-upload>
        <el-dialog :visible.sync="dialogVisible1" append-to-body>
          <img width="100%" :src="dialogImageUrl1" alt="" />
        </el-dialog>
        <el-button size="small" type="primary" @click="cancel">返回</el-button>
      </el-dialog> -->
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
import { getInfo } from '@/api/login'
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
export default {
  components: { Treeselect },
  dicts: ['political_status', 'educational_level'],
  props: [],
  data() {
    return {
      actives:1,
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
      vipRadio:'1',
      basicRadio:'1',
      singleRadio:'2',
      IsSpecialTax:'0',
      places: [],
      dialogImageUrl1: '',
      dialogVisible1: false,
      dialogImageUrl2: '',
      dialogVisible2: false,
      dialogImageUrl3: '',
      dialogVisible3: false,
      baseImgPath:"http://36.133.2.179:8000/api/files/showImg?imgPath=",
      formData: {
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
    'formData.industryType': 'selectIndustryType',

    'formData.contactName': {
      handler: function () {
        this.formData.legalPersonName = this.formData.contactName;
      },
      deep: true
    }
  },
  created() {
    //  var employedInfo= this.$cache.local.getJSON('employedInfo');
    //    console.log("applyName11==",employedInfo.applyName);
    //       this.formData.applyName=employedInfo.applyName;
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
    this.fileName5=[];
    this.fileName6=[];
    this.fileName7=[];
    let arr=this.formData.fileName5;
    let brr=this.formData.fileName6;
    let crr=this.formData.fileName7;
      
    for(let i in arr){
      this.fileName5.push({
       name:arr[i],
       url:this.baseImgPath+arr[i]
      })
    }

      
      
    for(let j in brr){
      this.fileName6.push({
       name:brr[j],
       url:this.baseImgPath+brr[j]
      })
    }

      
      
    for(let k in crr){
      this.fileName7.push({
       name:crr[k],
       url:this.baseImgPath+crr[k]
      })
    }



    if (this.formData.accountType == 1) {//银行账号类型为私人时
      this.formData.privateName = this.formData.legalPersonName;
      this.isPrivateBank = false;
    }
    if (this.formData.accountType == 2) {
      this.isPrivateBank = true;
    }
    console.log("formData==", this.formData);
    // this.genders1=this.genders;
    this.getElectronicCommerce();
    this.getAccountType();
    this.getGender();
    // this.formData.electronicCommerce='';
    // this.formData.accountType='';
    // this.formData.gender='';

    // this.formData.accountType=parseInt(employedInfo.accountType);
    // this.formData.electronicCommerce=parseInt(employedInfo.electronicCommerce);
    // this.formData.gender=parseInt(employedInfo.gender);
    // if(this.formData.electronicCommerce==1){
    //     this.formData.electronicCommerce='是'
    // }
    // if(this.formData.electronicCommerce==2){
    //     this.formData.electronicCommerce='否'
    // }
    // if(this.formData.accountType==1){
    //     this.formData.accountType='私人账号'
    // }
    // if(this.formData.accountType==2){
    //     this.formData.accountType='对公账号'
    // }
    //  if(this.formData.gender==1){
    //     this.formData.gender='男'
    // }
    // if(this.formData.gender==2){
    //     this.formData.gender='女'
    // }
  },
  methods: {
     backBus(){
     this.actives=3;
    },
    nextYe(){
     this.actives=4;
    },
    getLoginInfo() {
      getInfo().then(res => {
        this.formData.userName = res.user.nickName;
        crudPlace.getPlaceByUserId({ userId: 26 }).then(res => {
          console.log("getPlaceByUserId==", res.data);
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
      // var rate= this.industryTypes.find((item)=>item.industryId==value);
      var rate = this.industryTypeList.find((item) => item.industryId == this.formData.industryType);
      this.formData.industryTax = rate.taxRate;
      console.log("rate==", rate);
    },
    selectApplyName(value) {
      var applyName = this.applyNames.find((item) => item.userId == value);
      this.formData.applyPhone = applyName.phone;
      this.formData.applyIdNum = applyName.idNo;
      console.log("selectApplyName==", applyName);
    },
    // getRate(){
    //   crudRate.getAllRate().then(res=>{
    //       console.log("getAllRate",res.rows);
    //       this.industryTypes=res.rows;
    //   })
    // },
    getRate() {
      crudRate.getAllRate().then(res => {
        console.log("getAllRate", res.rows);
        var employedInfo = this.$cache.local.getJSON('employedInfo');
        this.formData.industryType = employedInfo.industryType;
        console.log("industryType==", this.formData.industryType);
        // this.industryTypes=res.rows;
        let tree = []; // 用来保存树状的数据形式
        this.parseTree(res.rows, tree, 0);
        console.log("tree", tree);
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
        console.log("getContactName", res.rows);
        this.contactNames = res.rows;
      })
    },
    getApplyName() {
      crudInformation.getAllInformation().then(res => {
        console.log("getApplyName", res.rows);
        this.applyNames = res.rows;
        var employedInfo = this.$cache.local.getJSON('employedInfo');
        console.log("applyName====", parseInt(employedInfo.applyName));
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
      console.log(tab, event);
    },
    nextbasic() {
      this.actives =2;
    },
    toReturn() {
      //this.$router.push("employed")
       this.$tab.closeOpenPage({ path: "/company/customer/employed"});
    },

    nextbus() {
      this.actives = 3;
    },
    backinfo() {
      this.actives = 1;
    },

    submitForm3() {
      this.$refs['elForm'].validate(valid => {
        if (valid) {
          console.log("placeName", this.formData.placeName,);
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
      console.log("fileName5", this.formData.fileName5);
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

    handlesuccess2(file, fileList) {
      this.formData.fileName5.push(file.obj);
      console.log("fileName5", this.formData.fileName5);
    },
    handleRemove2(file, fileList) {
      const i = this.formData.fileName5.findIndex((item) => item === fileList)
      this.formData.fileName5.splice(i, 1);
    },
    handlePreview2(file) {
      this.dialogImageUrl1 = file.url;
      this.dialogVisible1 = true;
    },
    handleExceed2(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove2(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },

    handlesuccess3(file, fileList) {
      this.formData.fileName5.push(file.obj);
      console.log("fileName5", this.formData.fileName5);
    },
    handleRemove3(file, fileList) {
      const i = this.formData.fileName5.findIndex((item) => item === fileList)
      this.formData.fileName5.splice(i, 1);
    },
    handlePreview3(file) {
      this.dialogImageUrl1 = file.url;
      this.dialogVisible1 = true;
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
