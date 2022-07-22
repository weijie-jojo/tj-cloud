<template>
  <div>
    <el-form
      ref="elForm"
      :model="formData"
      :rules="rules"
      size="medium"
      label-width="140px"
     
    >
      <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
        <el-tab-pane label="个体户信息" name="first">
         <el-row type="flex" class="row-bg rowCss combottom" style="margin-bottom:20px"  justify="space-around">
        <el-col :span="8">
           <div class="bankno">个体户信息</div>
        
        </el-col>
        <el-col :span="7" >
          <div></div>
        </el-col>
      </el-row>
          <el-row type="flex" class="row-bg " justify="space-around"> 
            <el-col :span="9">
              <el-form-item class="comright" label="个体户名称" prop="selfName">
                <el-input
                  v-model="formBank.selfName"
                    :readonly="true"
                ></el-input>
              </el-form-item>

              <el-form-item class="comright" label="法人姓名" prop="legalPersonName">
                <el-input
                  v-model="formBank.legalPersonName"
                    :readonly="true"
                ></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="9">
              <el-form-item class="comright" label="纳税人识别号" prop="taxId">
                <el-input
                 v-model="formBank.taxId"
                    :readonly="true"
                ></el-input>
              </el-form-item>
              <el-form-item class="comright"  label="从业人数" prop="numberEmployees">
                <el-input
                
                  v-model="formData.numberEmployees"
                  :readonly="true"
                >
                 <template slot="append">万元</template>
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row type="flex" class="row-bg " justify="space-around">
            <el-col :span="9">
             <el-form-item class="comright" label="出资额" prop="contributionAmount">
              <el-input   :readonly="true" type="number" v-model="formData.contributionAmount">
                  <template slot="append">万元</template>
          </el-input>
           </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item class="comright" label="城市" prop="city">
                <el-input  v-model="formData.city"   :readonly="true">
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row type="flex" class="row-bg " justify="space-around">
            <el-col :span="9">
              <el-form-item  class="comright" label="区（县）" prop="county">
                <el-input
                  v-model="formData.county"
                    :readonly="true"
                >
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item  class="comright" label="电子商务经营者" prop="electronicCommerce">
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
          <el-row type="flex" class="row-bg " justify="space-around">
            <el-col :span="21">
              <el-form-item style="padding-right:4.2%" label="经营场所" prop="selfAddress">
                <el-input
                  
                  v-model="formData.selfAddress"
                    :readonly="true"
                >
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row type="flex" class="row-bg " justify="space-around">
            <el-col :span="9">
              <el-form-item class="comright" label="联系电话" prop="contactPhone">
                <el-input
                  v-model="formData.contactPhone"
                    :readonly="true"
                >
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item class="comright" label="所属自贸区" prop="freeTradeZone">
                <el-input
                  v-model="formData.freeTradeZone"
                    :readonly="true"
                >
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row type="flex" class="row-bg " justify="space-around">
            <el-col :span="9">
              <el-form-item class="comright" label="所属自贸片区" prop="freeTradeArea">
                <el-input
                 
                  v-model="formData.freeTradeArea"
                    :readonly="true"
                >
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item class="comright" label="产权" prop="propertyRight">
                <el-input
                  v-model="formData.propertyRight"
                    :readonly="true"
                >
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row type="flex" class="row-bg " justify="space-around" >
            <el-col :span="21" >
              <el-form-item style="padding-right:4.2%" label="经营范围" prop="natureBusiness">
                <el-input
                   :readonly="true"
                  type="textarea"
                  :rows="2"
                  placeholder="请输入经营范围"
                  v-model="formData.natureBusiness"
                >
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row type="flex" class="row-bg " justify="space-around">
            <el-col :span="9">
              <el-form-item class="comright" label="行业类型" prop="industryType">
                <!-- <treeselect 
                    v-model="formData.industryType" 
                    :multiple="true" 
                    :options="options" /> -->
                <el-select
                  style="width: 100%"
                  disabled
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
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item class="comright" label="行业税率">
                <el-input
                  v-model="formData.industryTax"
                    :readonly="true"
                >
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row type="flex" class="row-bg " justify="space-around">
            <el-col :span="9">
              <el-form-item label="营业执照">
                <div v-for="(item, index) in formBank.fileName1" :key="index">
                  <el-image
                    lazy
                    :preview-src-list="formBank.fileName1"
                    style="width: 150px; height: 150px"
                    :src="item"
                    alt=""
                  />
                </div>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item label="税务核定通知书">
                <div v-for="(item, index) in formBank.fileName2" :key="index">
                  <el-image
                    lazy
                    :preview-src-list="formBank.fileName2"
                    style="width: 150px; height: 150px"
                    :src="item"
                    alt=""
                  />
                </div>
              </el-form-item>
            </el-col>
          </el-row>
        </el-tab-pane>

        <el-tab-pane label="经营者信息" name="second">
          <el-row type="flex" class="row-bg rowCss combottom" style="margin-bottom:20px"  justify="space-around">
          <el-col :span="7">
           <div class="bankno"> 经营者（负责人）信息</div>
           </el-col>
           <el-col :span="7" >
            <div></div>
          </el-col>
         </el-row>

          <el-row type="flex" class="row-bg " justify="space-around">
            <el-col :span="9">
              <el-form-item class="comright" label="姓名">
                <el-input
                 
                  v-model="formData.contactName"
                    :readonly="true"
                >
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item class="comright" label="人员类型" prop="personnelType">
                <el-input
                  v-model="formData.personnelType"
                    :readonly="true"
                >
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row type="flex" class="row-bg " justify="space-around">
            <el-col :span="9">
              <el-form-item class="comright" label="证件类型" prop="contactDocumentType">
                <el-input
                  
                  v-model="formData.contactDocumentType"
                    :readonly="true"
                >
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item class="comright" label="证件号码">
                <el-input
                 
                  v-model="formData.contactIdNum"
                    :readonly="true"
                >
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row type="flex" class="row-bg " justify="space-around">
            <el-col :span="9">
              <el-form-item class="comright" label="性别" prop="gender">
                <el-select
                  style="width: 100%"
                  disabled
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
              <el-form-item class="comright" label="出生日期" prop="dateBirth">
                <el-date-picker
                  style="width: 100%"
                  disabled
                  v-model="formData.dateBirth"
                  type="date"
                  placeholder="选择出生日期"
                >
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row type="flex" class="row-bg " justify="space-around">
            <el-col :span="9">
              <el-form-item class="comright" label="民族" prop="nation">
                <el-input
                
                    :readonly="true"
                  v-model="formData.nation"
                  clearable
                >
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item class="comright" label="文化程度" prop="eduation">
                <el-select
                  style="width: 100%"
                  disabled
                  v-model="formData.eduation"
                  placeholder="文化程度"
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
          <el-row type="flex" class="row-bg " justify="space-around">
            <el-col :span="9">
              <el-form-item class="comright" label="申请前职业状况" prop="occupationalStatus">
                <el-input
                  
                  v-model="formData.occupationalStatus"
                    :readonly="true"
                >
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item class="comright" label="政治面貌" prop="politicalStatus">
                <el-select
                  style="width: 100%"
                  disabled
                  v-model="formData.politicalStatus"
                  placeholder="文化程度"
                  clearable
                >
                  <el-option
                    v-for="dict in dict.type.political_status"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row type="flex" class="row-bg " justify="space-around">
            <el-col :span="21">
              <el-form-item style="padding-right:4.2%" label="经营者居所" prop="residence">
                <el-input
                  
                    :readonly="true"
                  v-model="formData.residence"
                  clearable
                >
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row type="flex" class="row-bg " justify="space-around">
            <el-col :span="9">
              <el-form-item class="comright" label="联系电话" prop="contactPhone">
                <el-input
                  
                    :readonly="true"
                  v-model="formData.contactPhone"
                  clearable
                >
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9" >
              <el-form-item class="comright" label="电子邮箱" prop="mail">
                <el-input
                 
                    :readonly="true"
                  v-model="formData.mail"
                  clearable
                >
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row type="flex" class="row-bg " justify="space-around">
            <el-col :span="9">
              <el-form-item label="身份证扫描件">
                <div v-for="(item, index) in fileName5" :key="index">
                  <el-image
                    lazy
                    :preview-src-list="fileName5"
                    style="width: 150px; height: 150px"
                    :src="item"
                    alt=""
                  />
                </div>
              </el-form-item>
            </el-col>
            <el-col :span="9">
                <el-form-item label="工商实名">
                <div v-for="(item, index) in fileName6" :key="index">
                  <el-image
                    lazy
                    :preview-src-list="fileName6"
                    style="width: 150px; height: 150px"
                    :src="item"
                    alt=""
                  />
                </div>
              </el-form-item>
            </el-col>
          </el-row>


            <el-row type="flex" class="row-bg " justify="space-around">
            <el-col :span="9">
              <el-form-item label="税务实名">
                <div v-for="(item, index) in fileName7" :key="index">
                  <el-image
                    lazy
                    :preview-src-list="fileName7"
                    style="width: 150px; height: 150px"
                    :src="item"
                    alt=""
                  />
                </div>
              </el-form-item>
            </el-col>
            <el-col :span="9">
               
            </el-col>
          </el-row>

          <el-row type="flex" class="row-bg" style="margin-bottom:20px" justify="space-around">
            <el-col :span="7">
           <div class="bankno">联络员信息</div>
          </el-col>
           <el-col :span="7" >
          <div></div>
        </el-col>
          </el-row>

          <el-row type="flex" class="row-bg " justify="space-around">
            <el-col :span="9">
              <el-form-item class="comright" label="姓名" prop="contactName">
                <el-input
                    :readonly="true"
                  v-model="formData.contactName"
                  clearable
                >
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item class="comright" label="移动电话" prop="contactPhone">
                <el-input
                    :readonly="true"
                  v-model="formData.contactPhone"
                  clearable
                >
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row type="flex" class="row-bg " justify="space-around">
            <el-col :span="9">
              <el-form-item class="comright" label="证件类型" prop="contactDocumentType">
                <el-input
                  
                  v-model="formData.contactDocumentType"
                    :readonly="true"
                >
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item class="comright" label="证件号码" prop="contactIdNum">
                <el-input
                 
                    :readonly="true"
                  v-model="formData.contactIdNum"
                  clearable
                >
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-tab-pane>
        <el-tab-pane label="银行信息" name="third">
            <el-row type="flex" class="row-bg " style="margin-bottom:20px" justify="space-around">
            <el-col :span="7">
           <div class="bankno">银行信息</div>
          </el-col>
           <el-col :span="7" >
          <div></div>
        </el-col>
          </el-row>

          <el-row type="flex" class="row-bg " justify="space-around">
            <el-col :span="9">
              <el-form-item class="comright" label="个体户名称" prop="selfName">
                <el-input
                   v-model="formBank.selfName"
                    :readonly="true"
                ></el-input>
              </el-form-item>

              <el-form-item class="comright" label="法人姓名" prop="legalPersonName">
                <el-input
                  v-model="formBank.legalPersonName"
                    :readonly="true"
                ></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="9">
              <el-form-item class="comright" label="纳税人识别号" prop="taxId">
                <el-input
                 v-model="formBank.taxId"
                    :readonly="true"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
             <el-row type="flex" class="row-bg " style="margin-bottom:20px" justify="space-around">
            <el-col :span="7">
           <div class="bankno">银行信息</div>
          </el-col>
           <el-col :span="7" >
          <div></div>
        </el-col>
          </el-row>
          <el-row type="flex" class="row-bg "  justify="space-around">
            <el-col :span="9">
              <el-form-item class="comright" label="账号类型" prop="">
                <el-input
                 
                  value="私人账号"
                    :readonly="true"
                ></el-input>
              </el-form-item>

              <el-form-item class="comright" label="开户银行" prop="privateDepositBank">
                <el-input
                 
                  v-model="formBank.privateDepositBank"
                    :readonly="true"
                ></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="9">
              <el-form-item class="comright" label="账号名称" prop="legalPersonName">
                <el-input
                 
                  v-model="formBank.legalPersonName"
                    :readonly="true"
                ></el-input>
              </el-form-item>
              <el-form-item class="comright" label="银行账号" prop="privateAccountNumber">
                <el-input
                  
                  v-model="formBank.privateAccountNumber"
                    :readonly="true"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- <el-collapse-item title="对公基本户" name="2" v-if="formData.accountType==2"> -->
          <el-row type="flex" class="row-bg " justify="space-around">
            <el-col :span="9">
              <el-form-item class="comright" label="账号类型" prop="">
                <el-input
                 
                  value="对公基本户"
                    :readonly="true"
                ></el-input>
              </el-form-item>

              <el-form-item class="comright" label="开户银行">
                <el-input
                
                    :readonly="true"
                  v-model="formBank.publicDepositBank1"
                ></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="9">
              <el-form-item class="comright" label="账号名称">
                <el-input
                 
                  v-model="formBank.selfName"
                    :readonly="true"
                ></el-input>
              </el-form-item>
              <el-form-item class="comright" label="银行账号">
                <el-input
                 
                    :readonly="true"
                  v-model="formBank.publicAccountNumber1"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- <el-collapse-item title="对公一般户" name="3" v-if="formData.accountType==2"> -->
          <el-row type="flex" class="row-bg " justify="space-around">
            <el-col :span="9">
              <el-form-item class="comright" label="账号类型" prop="">
                <el-input
                  value="对公一般户"
                    :readonly="true"
                ></el-input>
              </el-form-item>

              <el-form-item class="comright" label="开户银行">
                <el-input
                    :readonly="true"
                  v-model="formBank.publicDepositBank2"
                ></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="9">
              <el-form-item class="comright" label="账号名称">
                <el-input
                  
                  v-model="formBank.selfName"
                    :readonly="true"
                ></el-input>
              </el-form-item>
              <el-form-item class="comright" label="银行账号">
                <el-input
                 
                    :readonly="true"
                  v-model="formBank.publicAccountNumber2"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
              <el-row type="flex" class="row-bg " style="margin-bottom:20px" justify="space-around">
            <el-col :span="7">
           <div class="bankno">纳税账号</div>
          </el-col>
           <el-col :span="7" >
          <div></div>
        </el-col>
          </el-row>
          

          <el-row type="flex" class="row-bg " justify="space-around">
            <el-col :span="9">
              <el-form-item class="comright" label="户名" prop="accountName">
                <!-- <el-input v-model="formBank.accountName"></el-input> -->
                <el-select
                disabled
                  style="width: 100%"
                  @change="changeValue($event)"
                  v-model="formBank.accountName"
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

              <el-form-item class="comright" label="开户银行" prop="publicDepositBank3">
                <el-input
                   :readonly="true"
                  v-model="formBank.publicDepositBank3"
                ></el-input>
                <!-- <el-select disabled v-model="formBank.publicDepositBank3" filterable placeholder="请选择">
                     <el-option v-for="item in publicDepositBank3_options" :key="item.value" :label="item.value"
                        :value="item.value">
                     </el-option>
                  </el-select> -->
              </el-form-item>
              <el-form-item  label="纳税委托协议">
                <div v-for="(item, index) in formBank.fileName3" :key="index">
                  <el-image
                    lazy
                    :preview-src-list="formBank.fileName3"
                    style="width: 150px; height: 150px"
                    :src="item"
                    alt=""
                  />
                </div>
              </el-form-item>
            </el-col>

            <el-col :span="9">
              <el-form-item class="comright" label="银行账号" prop="publicAccountNumber3">
                <el-input
                 
                    :readonly="true"
                  v-model="formBank.publicAccountNumber3"
                ></el-input>
                <!-- <el-select disabled v-model="formBank.publicAccountNumber3" filterable placeholder="请选择">
                     <el-option v-for="item in publicAccountNumber3_options" :key="item.value" :label="item.value" :value="item.value">
                     </el-option>
                  </el-select> -->
              </el-form-item>
              <el-form-item style="color: rgba(0, 0, 0, 0)">
                <br />
              </el-form-item>
              <el-form-item label="三方协议签约凭证">
                <div   v-for="(item, index) in formBank.fileName4" :key="index">
                  <el-image
                    lazy
                    :preview-src-list="formBank.fileName4"
                    style="width: 150px; height: 150px"
                    :src="item"
                    alt=""
                  />
                </div>
              </el-form-item>
            </el-col>
          </el-row>
        </el-tab-pane>
      </el-tabs>
      <!-- <div class="footers grid-content">
            <el-footer>
              <el-button type="danger" @click="toReturn2">返回</el-button>
              <el-button type="primary" @click="submitForm3">提交</el-button>
            </el-footer>
          </div> -->
    </el-form>
  </div>
</template>
<script>
import pdf from "vue-pdf";
import { addEmployed, updateEmployed } from "@/api/company/employed";
import crudInformation from "@/api/company/information";
import crudPerson from "@/api/company/person";
import crudInfo from "@/api/company/info";
import crudEmployed from "@/api/company/employed";
import crudRate from "@/api/company/rate";
import crudPlace from "@/api/company/place";
import { getInfo } from "@/api/login";
// import the component
import Treeselect from "@riophae/vue-treeselect";
// import the styles
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
export default {
  components: { Treeselect },
  dicts: ["political_status", "educational_level"],
  components: {
       pdf 
  },
  props: [],
  data() {
    return {
      isok4:1,
      center: "center",
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
      formBank: {
        bankStatus: 1,
        selfId: "",
        accountName: "",
        privateDepositBank: "",
        privateAccountNumber: "",
        publicDepositBank1: "",
        publicAccountNumber1: "",
        publicDepositBank2: "",
        publicAccountNumber2: "",
        publicDepositBank3: "",
        publicAccountNumber3: "",
        selfName: "",
        legalPersonName: "",
        taxId: "",
        fileName3: [],
        fileName4: [],
      },
      formData: {
        selfCode: "",
        taxId: "",
        //申请信息
        oneselfApply: "否",
        applyName: "",
        applyPhone: "",
        applyDocumentType: "中华人民共和国居民身份证",
        applyIdNum: "",
        //联络员
        contactName: "",
        contactPhone: "",
        contactDocumentType: "中华人民共和国居民身份证",
        contactIdNum: "",

        //基本情况
        organizationalForm: "",
        numberEmployees: 5,
        contributionAmount: "",
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
        placeName: "",
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
         fileName5:[],
      },
      baseImgPath: "/ontherRequest/api/files/showImg?imgPath=",
      accountName_options: [],
      accountType: "",
      activeNames: ["1"],
      dialogImageUrl1: "",
      fileName3: [],
      fileName5:[],
      fileName2:[],
      fileName1:[],
      dialogImageUrl2: "",
      fileName4: [],
      fileName6: [],
      fileName7: [],
      dialogVisible1: false,
      dialogVisible2: false,
      rules: {
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
            message: "请输入联系人",
            trigger: "blur",
          },
        ],
        contactPhone: [
          {
            required: true,
            message: "请输入手机号",
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
          {
            pattern:
              /^[1-9]\d{5}(18|19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/,
            message: "身份证号格式错误",
            trigger: "blur",
          },
        ],

        organizationalForm: [
          {
            required: true,
            message: "请输入组织形式",
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
            message: "请选择渠道商",
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
            message: "请输入民族",
            trigger: "blur",
          },
        ],
        eduation: [
          {
            required: true,
            message: "请选择文化程度",
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
            message: "请选择政治面貌",
            trigger: "change",
          },
        ],
        residence: [
          {
            required: true,
            message: "请输入经营者居所",
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
      },
      wordTypeOptions: [
        {
          label: "选项一",
          value: 1,
        },
      ],
    };
  },
  computed: {},
  watch: {
    "formData.contactName": {
      handler: function () {
        this.formData.legalPersonName = this.formData.contactName;
      },
      deep: true,
    },
  },
  created() {
    this.formData = this.$cache.local.getJSON("employedInfo");
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
    this.formBank = this.$cache.local.getJSON("employedInfo");
    this.formData = this.$cache.local.getJSON("employedInfo");
    this.formBank.fileName1 = JSON.parse(
      this.$cache.local.getJSON("employedInfo").fileName1
    );

    this.formBank.fileName2 = JSON.parse(
      this.$cache.local.getJSON("employedInfo").fileName2
    );
    this.formBank.fileName3 = JSON.parse(
      this.$cache.local.getJSON("employedInfo").fileName3
    );
    this.formBank.fileName4 = JSON.parse(
      this.$cache.local.getJSON("employedInfo").fileName4
    );
   
    
    
   for (let i in this.formBank.fileName1) {
      this.formBank.fileName1[i] =
        this.baseImgPath + this.formBank.fileName1[i];
    }
    for (let j in this.formBank.fileName2) {
      this.formBank.fileName2[j] =
        this.baseImgPath + this.formBank.fileName2[j];
    }
    for (let k in this.formBank.fileName3) {
      this.formBank.fileName3[k] =
        this.baseImgPath + this.formBank.fileName3[k];
    }
    for (let g in this.formBank.fileName4) {
      this.formBank.fileName4[g] =
        this.baseImgPath + this.formBank.fileName4[g];
        if( this.formBank.fileName4[g].endsWith('pdf')){
          this.isok4=2;
        }
    }
 
   this.fileName5=JSON.parse(this.$cache.local.getJSON('employedInfo').fileName5);
   for(let k1 in this.fileName5){
      this.fileName5[k1]=this.baseImgPath+this.fileName5[k1];
    } 
    this.fileName6=JSON.parse(this.$cache.local.getJSON('employedInfo').fileName6);
   for(let k2 in this.fileName6){
      this.fileName6[k2]=this.baseImgPath+this.fileName6[k2];
    } 
    this.fileName7=JSON.parse(this.$cache.local.getJSON('employedInfo').fileName7);
   for(let k3 in this.fileName7){
      this.fileName7[k3]=this.baseImgPath+this.fileName7[k3];
    } 
    this.formData.electronicCommerce=parseInt(this.formData.electronicCommerce);
    this.formData.gender=parseInt(this.formData.gender);
  },
 

  methods: {
    handleChange(val) {
      console.log(val); 
    },
    getLoginInfo() {
      getInfo().then((res) => {
        this.formData.userName = res.user.nickName;
        crudPlace.getPlaceByUserId({ userId: 26 }).then((res) => {
          console.log("getPlaceByUserId==", res.data);
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
      crudRate.getAllRate().then((res) => {
        console.log("getAllRate", res.rows);
        this.industryTypes = res.rows;
      });
    },
    getContactName() {
      crudPerson.getAllPerson().then((res) => {
        console.log("getContactName", res.rows);
        this.contactNames = res.rows;
      });
    },
    getApplyName() {
      crudInformation.getAllInformation().then((res) => {
        console.log("getApplyName", res.rows);
        this.applyNames = res.rows;
      });
    },
    handleClick(tab, event) {
      console.log(tab, event);
    },
    submitForm1() {
      this.activeName = "second";
    },
    resetForm1() {
      this.$refs["elForm"].resetFields();
    },

    submitForm2() {
      this.activeName = "third";
    },
    toReturn1() {
      this.activeName = "first";
    },

    submitForm3() {
      this.$refs["elForm"].validate((valid) => {
        // TODO 提交表单
        if (valid) {
          let parms = {
            selfId: this.formData.selfId,
            infoStatus: 1,
          };
          updateEmployed.addReview(parms).then((res) => {
            if (res != undefined) {
              if (res != undefined) {
                if (res.code === 200) {
                  this.$modal.msgSuccess("信息审核通过成功!");
                  this.$nextTick(function () {
                    this.$router.push("employed");
                  });
                } else {
                  this.$modal.msgError(res.msg);
                }
              }
            }
          });
        } else {
          this.$message({
            message: "请填写完整",
            type: "warning",
          });
        }
      });
    },
    toReturn2() {
      this.$router.back();
    },
  },
};
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
.rowCss {
  margin-top: 10px;
}

// 改变input框字体颜色
::v-deep .is-disabled .el-input__inner {
  background-color: transparent !important;
  color: black;
}
// 改变input框字体颜色
::v-deep textarea {
  background-color: transparent !important;
  color: black  !important;
}

.paddingbg-s {
  padding-top: 15px;
}

.footers {
  display: flex;
  justify-content: center;
  align-items: center;
}
.rowCss {
  margin-top: 10px;
}
.comright{
  padding-right: 10%;
}
.combottom{
  margin-bottom: 10px;

}
.flexs{
  display: flex;
  justify-content: center;

}
.bankno {
 
  letter-spacing: 2px;

  font-size: 20px;
  
  color: blue;
}



// ::v-deep .el-tabs__nav-scroll {
//   width: 50% !important;
//   margin: 0 auto !important;
// }
</style>
