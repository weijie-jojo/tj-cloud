<template>
  <div>
    <el-form
      ref="elForm"
      :model="formData"
      :rules="rules"
      size="medium"
      label-width="140px"
    >
      <el-row
        type="flex"
        class="row-bg"
        style="margin-top: 20px"
        justify="space-around"
      >
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width: 35%">项目信息</div>
          <div style="width: 50%; hegiht: 10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>

      <el-row
        type="flex"
        class="row-bg rowCss combottom"
        style="margin-bottom: 0px"
        justify="space-around"
      >
        <el-col :span="9">
          <el-form-item class="comright" label="项目编号">
            <el-input v-model="formData.projectCode" disabled></el-input>
          </el-form-item>

          <el-form-item class="comright" label="项目名称" prop="projectName">
            <el-input v-model="formData.projectName"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="9">
          <el-form-item
            class="comright"
            label="项目时间"
            prop="projectTimeStart"
          >
          <el-date-picker
          style="width:100%"
          v-model="formData.projectTimeStart"
          value-format="yyyy-MM-dd"
          :picker-options="pickerOptions"
         
          align="right"
        >
        </el-date-picker>
           
          </el-form-item>
          <el-form-item
            class="comright"
            label="项目金额"
            prop="projectTotalAmount"
          >
            <el-input
              type="number"
              style="width: 100%"
              v-model="formData.projectTotalAmount"
              :step="0.01"
              :min="0"
              onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="客户全名">
            <el-select
              filterable
             
              @visible-change="changeValue1($event)"
              style="width: 100%"
              clearable
              v-model="formData.placeCode"
            >
              <el-option
                v-for="item in placeCodeOptions"
                :key="item.placeCode"
                :label="item.placeAliasName"
                :value="item.placeCode"
              >
              </el-option>
            </el-select>
          </el-form-item>
           <el-form-item label="项目款往来" :required="true">
              <el-radio v-model="formData.isDealings" label="1">是</el-radio>
              <el-radio v-model="formData.isDealings" label="0">否</el-radio>
           </el-form-item>
        </el-col>

        <el-col :span="9">
          <el-form-item class="comright" label="业务经理">
            <el-input v-model="formData.projectLeader" disabled></el-input>
          </el-form-item>
      
        </el-col>
      </el-row>

      <el-row
        type="flex"
        class="row-bg"
        style="margin-bottom: 20px"
        justify="space-around"
      >
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width: 35%">购货单位（甲方）</div>
          <div style="width: 50%; hegiht: 10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="名称" prop="purchCompany">
            <!-- <el-input v-model="formData.purchCompany"></el-input> -->
            <el-autocomplete
              ref="autos"
              popper-class="my-autocomplete"
              style="width: 100%"
              v-model="formData.purchCompany"
              :fetch-suggestions="querySearchAsync"
              placeholder="请输入内容"
              @select="handleSelect"
            >
              <template slot-scope="{ item }">
                <div class="name">{{ item.purchCompany }}</div>
                <span class="addr">{{ item.purchCompanyAddress }}</span>
              </template>
            </el-autocomplete>
          </el-form-item>
          <el-form-item class="comright" label="地址">
            <el-input
              v-model="formData.purchCompanyAddress"
              maxlength="250"
              show-word-limit
              type="textarea"
              :rows="1"
            ></el-input>
          </el-form-item>
          
          <el-form-item class="comright" label="开户行">
            <el-input v-model="formData.bankName"></el-input>
          </el-form-item>
          <el-form-item label="添加购方列表" :required="true">
              <el-radio v-model="formData.isAddBuyer" label="1">是</el-radio>
              <el-radio v-model="formData.isAddBuyer" label="0">否</el-radio>
           </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item
            class="comright"
            label="纳税人识别号"
            prop="purchCompanyTaxid"
          >
            <el-input v-model.trim="inputValCompute"></el-input>
          </el-form-item>
          <el-form-item class="comright" label="电话">
            <el-input v-model="formData.purchCompanyPhone"></el-input>
          </el-form-item>
          <el-form-item class="comright" label="帐号">
            <el-input v-model="formData.bankCode"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row
        type="flex"
        class="row-bg"
        style="margin-bottom: 20px"
        justify="space-around"
      >
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width: 35%">销货单位（乙方）</div>
          <div style="width: 50%; hegiht: 10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="名称" prop="projectOwner">
            <el-select
              placeholder="请选择销货单位（乙方）"
              filterable
              @visible-change="changeValue($event)"
              @change="ownnew"
              style="width: 100%"
              clearable
              v-model="formData.projectOwner"
            >
              <el-option
                v-for="item in ownoptions"
                :key="item.selfId"
                :label="item.selfName"
                :value="item.selfCode"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item class="comright" label="地址">
            <el-input
              v-model="residence"
              :readonly="true"
              maxlength="250"
              show-word-limit
              type="textarea"
              :rows="1"
            ></el-input>
          </el-form-item>
          <el-form-item class="comright" label="开户行">
            <el-input
              :readonly="true"
              v-if="accountType == 1"
              v-model="privateDepositBank"
            ></el-input>
            <el-input
              :readonly="true"
              v-else
              v-model="publicDepositBank1"
            ></el-input>
          </el-form-item>
        
        </el-col>

        <el-col :span="9">
          <el-form-item class="comright" label="纳税人识别号">
            <el-input :readonly="true" v-model="owerTax"></el-input>
          </el-form-item>
          <el-form-item class="comright" label="电话">
            <el-input :readonly="true" v-model="contactPhone"></el-input>
          </el-form-item>
          <el-form-item class="comright" label="帐号">
            <el-input
              v-if="accountType == 1"
              v-model="privateAccountNumber"
            ></el-input>
            <el-input v-else v-model="publicAccountNumber1"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="行业类型">
            <el-tooltip
              class="item"
              effect="dark"
              :content="selectTipType"
              placement="top-start"
            >
              <el-select
               disabled
                class="main-select-tree"
                ref="selectTree"
                v-model="formData.industryType"
                style="width: 100%"
              >
                <el-option
                  v-for="item in formatData(industryTypes)"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                  style="display: none"
                />

                <el-tree
                  class="main-select-el-tree"
                  ref="selecteltree"
                  :data="industryTypes"
                  node-key="id"
                  highlight-current
                  :props="defaultProps"
                  @node-click="handleNodeClick"
                  :current-node-key="formData.industryType"
                  :expand-on-click-node="expandOnClickNode"
                >
                  <span
                    class="custom-tree-node"
                    slot-scope="{ node, data }"
                    style="width: 100%"
                  >
                    <span style="float: left">{{ node.label }}</span>
                    <span style=" float: right;color: #8492a6; font-size: 14px;padding-right: 10px;
                      "
                      >{{ data.taxRates }}</span
                    >
                  </span>
                </el-tree>
              </el-select>
            </el-tooltip>
          </el-form-item>
        </el-col>

        <el-col :span="9">
          <el-form-item class="comright" label="行业税率">
            <el-input disabled v-model="owerTaxfee"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="个体户状态">
                        <el-select style="width:100%" disabled clearable v-model="projectStatus">
                            <el-option v-for="item in options" :key="item.value" :label="item.label"
                                :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
        </el-col>
        <el-col :span="9"></el-col>
      </el-row>
   
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="21">
          <el-form-item style="padding-right: 4%" label="经营范围">
            <el-input
              disabled
              type="textarea"
              :rows="2"
              placeholder="请输入经营范围"
              v-model="natureBusiness"
            >
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row
        type="flex"
        class="row-bg"
        style="margin-bottom: 20px"
        justify="space-around"
      >
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width: 35%">票据信息</div>
          <div style="width: 50%; hegiht: 10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="发票类型" prop="ticketType">
            <el-select
              style="width: 100%"
              clearable
              v-model="formData.ticketType"
              @change="tickettaxvip"
            >
              <el-option
                v-for="item in ticketTypeoptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item class="comright" label="开票内容类型">
            <el-radio v-model="fileNameradio" label="1" @change="filenamer"
              >手动输入</el-radio
            >
            <el-radio v-model="fileNameradio" label="2" @change="filenamer"
              >上传附件
            </el-radio>
          </el-form-item>
        </el-col>

        <el-col :span="9">
          <el-form-item v-if="tickettaxvipok" label="发票税率" prop="ticketTax">
            <el-input
              style="width: 88%"
              v-model="formData.ticketTax"
              :readonly="true"
            >
              <template slot="append"> % </template>
            </el-input>
          </el-form-item>
          <el-form-item
            v-else
            class="comright"
            label="发票税率"
            prop="ticketTax"
          >
            <el-select
              style="width: 100%"
              clearable
              v-model="formData.ticketTax"
            >
              <el-option
                v-for="item in ticketNormal"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            
            label="发票备注"
            prop="projectDesc"
          >
            <el-input
              style="width:88%"
              maxlength="50"
              show-word-limit
              type="textarea"
              :rows="2"
              placeholder="请输入发票备注"
              v-model="formData.projectDesc"
            >
            </el-input>
          </el-form-item>
       
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item
            class="comright"
            label="开票内容附件"
            prop="fileName"
            v-if="fileNameradio == 2"
          >
            <uploadSmall
              @getfileName="getfileNameS"
              :fileName="isNone"
              :fileNameOld="isNone"
              :isDetail="isDetail"
            ></uploadSmall>
          </el-form-item>
        </el-col>
        <el-col :span="9"></el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="21">
              <el-form-item
              style="padding-right: 3.8%"
            class="comright"
            label="开票内容"
            prop="fileName"
            v-if="fileNameradio == 1"
          >
            <el-input
              type="textarea"
              :rows="2"
              v-model="formData.fileName"
              maxlength="50"
              show-word-limit
            >
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row
        type="flex"
        class="row-bg"
        style="margin-bottom: 20px"
        justify="space-around"
      >
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width: 35%">结算信息</div>
          <div style="width: 50%; hegiht: 10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item label="结算方式" :required="true">
            <el-radio
             v-model="formData.isSelfCount"
              label="0"
              @change="singleOK"
              :disabled="confirmEditStatus2"
              v-show="confirmEditStatus"
              >按个体结算</el-radio
            >
            <el-radio
            :disabled="confirmEditStatus3"
             v-show="confirmEditStatus1"
              v-model="formData.isSelfCount"
              label="1"
              @change="singleOK"
              >按客户结算</el-radio
            >
            <el-radio
              v-model="formData.isSelfCount"
              label="2"
              @change="singleOK"
              >按项目结算</el-radio
            >
          </el-form-item>
        </el-col>
        <el-col :span="9"> </el-col>
      </el-row>
     
      <el-row
        v-if="formData.isSelfCount == 2"
          type="flex"
          class="row-bg"
          style="margin-bottom: 20px"
          justify="space-around"
        >
          <el-col :span="10">
            <div class="bankno">增值税普通发票</div>
           </el-col>
          <el-col :span="9"> </el-col>
        </el-row>

      <el-row
        type="flex"
        class="row-bg"
        justify="space-around"
        v-if="formData.isSelfCount == 2"
      >
        <el-col :span="9">
          <el-form-item label="状态" :required="true">
            <el-radio
              @change="ispublic"
              v-model="formData.isSliderOrdinary"
              label="0"
              >开启</el-radio
            >
            <el-radio
              @change="ispublic"
              v-model="formData.isSliderOrdinary"
              label="1"
              >关闭</el-radio
            >
          </el-form-item>

          <div v-if="formData.isSliderOrdinary == 0">
            <el-form-item label="普票税率" :required="true">
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
                  >按定额收取
                </el-radio>
                <el-radio
                  @change="handPoxy"
                  v-model="formData.ordinaryProxyIsmoney"
                  label="1"
                  >按百分比收取
                </el-radio>
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
              <el-radio v-model="formData.isOrdinaryTax" label="0">是</el-radio>
              <el-radio v-model="formData.isOrdinaryTax" label="1">否</el-radio>
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
                  >按定额收取
                </el-radio>
                <el-radio
                  @change="hanOrshare"
                  v-model="formData.ordinaryShareIsmoney"
                  label="1"
                  >按百分比收取
                </el-radio>
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
        v-if="formData.isSelfCount == 2"
          type="flex"
          class="row-bg"
          style="margin-bottom: 20px"
          justify="space-around"
        >
          <el-col :span="10" >
            <div class="bankno">增值税专用发票
              </div>
           </el-col>
          <el-col :span="9"></el-col>
        </el-row>


      <el-row
        type="flex"
        class="row-bg"
        justify="space-around"
        v-if="formData.isSelfCount == 2"
      >
     
        <el-col :span="9">
          <el-form-item label="状态" :required="true">
            <el-radio @change="ispublic" v-model="formData.isSlider" label="0"
              >开启</el-radio
            >
            <el-radio @change="ispublic" v-model="formData.isSlider" label="1"
              >关闭</el-radio
            >
          </el-form-item>
          <div v-if="formData.isSlider == 0">
            <el-form-item label="专票税率" :required="true">
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
                  >按定额收取
                </el-radio>
                <el-radio
                  @change="handSpecial"
                  v-model="formData.specialProxyIsmoney"
                  label="1"
                  >按百分比收取
                </el-radio>
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
              <el-radio v-model="formData.isSpecialTax" label="0">是</el-radio>
              <el-radio v-model="formData.isSpecialTax" label="1">否</el-radio>
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
                  >按定额收取
                </el-radio>
                <el-radio
                  @change="handMoney"
                  v-model="formData.specialShareIsmoney"
                  label="1"
                  >按百分比收取
                </el-radio>
                <el-input
                  v-if="formData.specialShareIsmoney == 0"
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
        v-if="formData.isSelfCount == 2"
          type="flex"
          class="row-bg"
          style="margin-bottom: 20px"
          justify="space-around"
        >
          <el-col :span="8" class="flexs">
            <div class="bankno" style="width:60%;">
               一次性费用
             </div>
             <div style="width:40%"></div>
          </el-col>
          <el-col :span="8">
            <div></div>
          </el-col>
        </el-row>


      <el-row
        type="flex"
        class="row-bg"
        justify="space-around"
        v-if=" formData.isSelfCount == 2"
      >
        <el-col :span="9">
          <el-form-item label="状态" prop="isDisposable">
            <el-radio v-model="formData.isDisposable" label="0">开启</el-radio>
            <el-radio v-model="formData.isDisposable" label="1">关闭</el-radio>
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
                  >
                    按定额收取</el-radio
                  >
                  <el-radio
                    @change="handleDis"
                    v-model="formData.disposableFeeIsmoney"
                    label="1"
                  >
                    按百分比收取</el-radio
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
            </el-col>
          </el-row>
          <el-form-item label="备注" v-if="formData.isDisposable==0 ">
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
                  >
                    按定额收取</el-radio
                  >
                  <el-radio
                    @change="isdisshare"
                    v-model="formData.disposableShareIsmoney"
                    label="1"
                  >
                    按百分比收取</el-radio
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
        <el-col :span="9"> </el-col>
      </el-row>

      <!-- <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="21">
                    <el-form-item style="padding-right:4%" label="项目行业类型" prop="projectTrade">
                        <el-input disabled v-model="formData.projectTrade"></el-input>
                    </el-form-item>
                </el-col>
            </el-row> -->

      <el-row type="flex" class="row-bg" justify="space-around" style="margin-bottom:20px">
        <el-col :span="8"></el-col>
        <el-col :span="8" class="flexs">
          <el-button type="danger" @click="resetForm">关闭</el-button>
          <el-button type="primary" @click="onSubmit">提交</el-button>
        </el-col>
        <el-col :span="8"></el-col>
      </el-row>
    </el-form>
  </div>
</template>
<script>
import uploadSmall from "@/components/douploads/uploadSmall";
import crudRate from "@/api/project/rate";
import {
  getPuJialist,
  addJia,
  list,
  getcode,
  getinfoByUserId,
  add,
  ownlist,
  check,
} from "@/api/project/list";
import agencyfee from "@/api/place/agencyfee";
import { getInfo } from "@/api/login";
import { Decimal } from "decimal.js";
//手机号验证
var phoneVerify = (rule, value, callback) => {
  if (value) {
    var reg = /^[A-Z0-9]{15}$|^[A-Z0-9]{18}$|^[A-Z0-9]{20}$/;
    if (!reg.test(value)) {
      callback(
        new Error("购货单位（甲方）纳税人识别号,一律由15位、18或者20位码(字符型))组成")
      );
    }
    callback();
  }
};

export default {
  name: "Add",
  components: {
    uploadSmall,
  },
  data() {
    return {
      pickerOptions: {
          disabledDate(time) {
            return time.getTime() > Date.now();
          },
          shortcuts: [{
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date());
            }
          }, {
            text: '昨天',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit('pick', date);
            }
          }, {
            text: '一周前',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', date);
            }
          }]
        },
      restaurants: [],
      state: "",
      timeout: null,
      contactPhone: "",
      accountType: "",
      privateDepositBank: "",
      privateAccountNumber: "",
      publicDepositBank1: "",
      publicAccountNumber1: "",
      residence: "",
      unlist: {},
      selectTipType: "",
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
      isDetail: "0",
      isNone: [],
      defaultProps: {
        children: "children",
        label: "label",
        taxRates: "taxRates",
      },
      userinfo: {},
      expandOnClickNode: true,
      projectStatus: 1, //销货单位（乙方）状态
      username: "",
      userId: "",
      industryId: "",
      industryTypes: [],
      industryTypeList: [],
      fileName: [],
      fileNamefile: [], //附件
      dialogVisible1: false,
      dialogImageUrl1: "",
      fileNameradio: "1", //开票类型
      natureBusiness: "", //经营范围
      tickettaxvipok: false,
      placename: "",
     
      companyTax: "", //购货单位（甲方）纳税人识别号
      owerTax: "", //销货单位（乙方）纳税人识别号
      owntype: "", //销货单位（乙方）行业类型
      owerTaxfee: "", //销货单位（乙方）税率
      placeCodeOptions: "", //渠道商
      isaddPurch:'1',//添加甲方
      formData: {
       
        isAddBuyer:'1',
        isDealings:'1',//项目往来款
        disposableRemark:'',
        isDisposableShare: "1",
        disposableShareIsmoney: "0",
        disposableShare: "0",
        disposableFeeIsmoney: "1",
        disposableFee: "0",
        isDisposable: "1", //是否一次性费用
        isRegisterMoney: "0", //是否收取注册服务费

        purchCompanyAddress: "", //购货单位（甲方）地址
        purchCompanyPhone: "", //购货单位（甲方）电话
        bankName: "", //购货单位（甲方）开户行
        bankCode: "", //购货单位（甲方）开户账号

        isSliderOrdinary: "0", //普票滑块
        isSlider: "1", //专票滑块
        ordinaryProxyIsmoney: "1", //普票平台服务费是否定额
        specialProxyIsmoney: "1", //专票平台服务费是否定额
        ordinaryShareIsmoney: "1", //普票分润方式是否定额
        specialShareIsmoney: "1", //专票分润方式是否定额
        isOrdinaryShare: "1",
        isSpecialShare: "1",
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
        isSelfCount: "2", //结算方式
        registerMoney: "", //注册服务费

        specialShare: "0",
        ordinaryShare: "0",

        projectOwnerTaxid: "",
        projectDesc: "", //开票描述
        purchCompanyTaxid: "", //购货单位（甲方）纳税人识别号
        ticketTax: 0, //发票税率
        ticketType: 0, //发票类型  0 普通 1 专用
        projectRemainAmount: "", //项目可以用金额

        fileName: "", //开票内容
        placeCode: "",
        projectAcceptanceStatus: "-1", //办理中 验收
        projectCheckStatus: 0,
        projectCode: "",
        projectContractStatus: "-1", //办理中  合同
        projectDesc: "",
        projectDutypaidStatus: "-1", //办理中  完税
        projectReceiveStatus: "0", //收款
        projectPayStatus: "0", //出款
        projectGrossMargin: 0,
        projectGrossProfit: 0,
        projectLeader: "",
        projectName: "",
        projectNetProfit: 0,
        projectOwner: "",
        projectPackageAmount: 0, ////已使用金额
        projectStatus: 0,
        projectTicketStatus: 0,
        // projectTimeEnd: "",
        projectTimeStart: "",
        projectTotalAmount: "0.00",
        projectTrade: "",

        purchCompany: "",
        remark: "",
        industryType: "",
        selfName: "",
        isDeleted: 1,
        userId: "",
        placeAliasName: "",
      },
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
      options: [
        {
          value: 0,
          label: "异常",
        },
        {
          value: 1,
          label: "正常",
        },
        {
          value: 2,
          label: "异常",
        },
      ],
      //购货单位（购货单位（甲方））
      purchCompanyOptions: [],
      //销货单位（乙方）
      ownoptions: [],
      //销货单位（乙方）行业类型

      ownindustry: [],
      //发票类型
      ticketTypeoptions: [
        {
          value: 0,
          label: "增值税普通发票",
        },
        {
          value: 1,
          label: "增值税专用发票",
        },
      ],
      //发票普通税率
      ticketNormal: [
        {
          value: 0,
          label: "免税",
        },
        // {

        //     value: 3,
        //     label: '3%'
        // },
      ],
      
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
        projectName: [
          {
            required: true,
            message: "请输入项目名称",
            trigger: "blur",
          },
        ],
        projectTotalAmount: [
          {
            required: true,
            message: "请输入项目额",
            trigger: "blur",
          },
          // { validator: projectTotalAmounts, trigger: 'blur' }
        ],

        ticketType: [
          {
            required: true,
            message: "请选择发票类型",
            trigger: "change",
          },
        ],

        ticketTax: [
          {
            required: true,
            message: "发票税率不能为空",
            trigger: "change",
          },
        ],
        industryType: [
          {
            required: true,
            message: "请选择销货单位（乙方）行业类型",
            trigger: "change",
          },
        ],
        projectTrade: [
          {
            required: true,
            message: "行业类型不能为空",
          },
        ],
        placeCode: [
          {
            required: true,
            message: "请选择全名",
            trigger: "change",
          },
        ],

        projectOwner: [
          {
            required: true,
            message: "请选择销货单位（乙方）",
            trigger: "change",
          },
        ],

        purchCompany: [
          {
            required: true,
            message: "请选择购货单位（甲方）",
            trigger: "change",
          },
        ],

        purchCompanyTaxid: [
          {
            required: true,
            message: "请输入购货单位（甲方）纳税人识别号",
            trigger: "blur",
          },
          { validator: phoneVerify, trigger: "blur" },
        ],
        

        fileName: [
          {
            required: true,
            message: "开票内容不能为空",
           
          },
        ],
        projectDesc: [
          {
            required: true,
            message: "请输入发票描述",
            trigger: "blur",
          },
        ],
      },
      confirmEditStatus:true,//个体户
      confirmEditStatus1:true,//渠道
      confirmEditStatus2:false,//个体户无法点击
      confirmEditStatus3:false,//客户无法点击
      listnew:'',
     
    };
  },
  computed: {
    inputValCompute: {
      get() {
        return this.formData.purchCompanyTaxid;
      },
      set(val) {
        this.formData.purchCompanyTaxid = val.toUpperCase();
      },
    },
  },
  // watch: {
  //   "formData.industryType": "selectIndustryType",
  // },
  mounted() {
    this.getPuJialist();
    this.gettoday();
    this.getRate();
    this.getinfoByUserId(); //渠道商
    
  },

  methods: {
     //客户实时异步
    changeValue1(e) {
      if (e == true) {
        this.getinfoByUserId(); //渠道商
      }
    },
     //乙方实时异步
   
    changeValue(e) {
      if (e == true) {
        this.getOwn();
      }
    },
   
    ispublic() {
      if (this.formData.ticketType == 0) {
        this.formData.isSlider = "1";
        this.formData.isSliderOrdinary = "0";
      } else {
        this.formData.isSlider = "0";
        this.formData.isSliderOrdinary = "1";
      }
    
    },
    querySearchAsync(queryString, cb) {
      this.formData.purchCompanyTaxid = "";
      // this.formData.purchCompany = '';
      this.formData.purchCompanyAddress = ""; //购货单位（甲方）地址
      this.formData.purchCompanyPhone = ""; //购货单位（甲方）电话
      this.formData.bankName = ""; //购货单位（甲方）开户行
      this.formData.bankCode = ""; //购货单位（甲方）开户账号
      this.getPuJialist();
      var restaurants = this.restaurants;
      var results = queryString
        ? restaurants.filter(this.createStateFilter(queryString))
        : restaurants;
      clearTimeout(this.timeout);
      this.timeout = setTimeout(() => {
        cb(results);
      }, 1000 * Math.random());
    },
    createStateFilter(queryString) {
      return (state) => {
        return (
          state.purchCompany
            .toLowerCase()
            .indexOf(queryString.toLowerCase()) === 0
        );
      };
    },

    handleSelect(item) {
      console.log(item);
      this.formData.purchCompanyTaxid = item.purchCompanyTaxid;
      this.formData.purchCompany = item.purchCompany;
      this.formData.purchCompanyAddress = item.purchCompanyAddress; //购货单位（甲方）地址
      this.formData.purchCompanyPhone = item.purchCompanyPhone; //购货单位（甲方）电话
      this.formData.bankName = item.bankName; //购货单位（甲方）开户行
      this.formData.bankCode = item.bankCode; //购货单位（甲方）开户账号
    },
    //购货单位（甲方）数据
    getPuJialist() {
      let params = {
        purchCompany: this.formData.purchCompany,
      };
      getPuJialist(params).then((res) => {
        this.restaurants = res.rows;
      });
    },
    //结算方式赋值
    singleOK() {
      if (this.formData.isSelfCount == 2) {
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
        if (this.formData.ticketType == 0) {
          this.formData.isSlider = "1";
          this.formData.isSliderOrdinary = "0";
        } else {
          this.formData.isSlider = "0";
          this.formData.isSliderOrdinary = "1";
        }

        this.formData.isSpecialSelfTax = "1";
        this.formData.isSelfTax = "1";
        this.formData.isOrdinaryTax = "1";
        this.formData.isSpecialTax = "1";
      }else{
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
    getfileNameS(data) {
      this.formData.fileName = data;
    },
    handleNodeClick(node) {
      this.formData.industryType = node.id;
      this.$refs.selectTree.blur();
      this.$nextTick(function () {
        this.selectTipType = this.$refs.selectTree.selected.label;
      });
    },

    // 四级菜单
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

    //监听开票内容选择
    filenamer(e) {
      if (e == 1) {
        this.formData.fileName = "";
      }
    },
    //监听销货单位（乙方）
    ownnew(e) {
      console.log(e);
      for (let i in this.ownoptions) {
        if (this.ownoptions[i].selfCode == e) {
          console.log(this.ownoptions[i].isActive);
          if (this.ownoptions[i].isActive) {
            this.projectStatus = parseInt(this.ownoptions[i].isActive);
          } else {
            this.projectStatus = 1;
          }
          this.listnew=this.ownoptions[i];
          console.log(111,this.ownoptions[i].isSliderOrdinary);
          console.log(222,this.ownoptions[i].isSlider);
          console.log(333,this.ownoptions[i].isSelfCount);
          //普票  没有开启  就是 个体和客户不能点击
         
          if(this.ownoptions[i].isSelfCount==0){
                this.confirmEditStatus=true;
                this.confirmEditStatus1=false;
          }else{
                this.confirmEditStatus=false;
                this.confirmEditStatus1=true;
          }
           
          if(this.formData.ticketType==0){
             if(this.ownoptions[i].isSliderOrdinary==1){
              this.confirmEditStatus2=true;
              this.confirmEditStatus3=true;
              this.formData.isSelfCount='2';
              return;
             }else{
              this.confirmEditStatus2=false;
              this.confirmEditStatus3=false;
              this.formData.isSelfCount=JSON.stringify(this.ownoptions[i].isSelfCount);
             }
          }else if(this.formData.ticketType==1){
             //专票  没有开启  就是 个体和客户不能点击
            if(this.ownoptions[i].isSlider==1){
              this.confirmEditStatus2=true;
              this.confirmEditStatus3=true;
               this.formData.isSelfCount='2';
               return;
             }else{
              this.confirmEditStatus2=false;
              this.confirmEditStatus3=false;
              this.formData.isSelfCount=JSON.stringify(this.ownoptions[i].isSelfCount);
             }
          }
          this.$nextTick(function () {
            this.selectTipType = this.$refs.selectTree.selected.label;
            this.formData.projectTrade = this.$refs.selectTree.selected.label;
         });
          this.formData.industryType= this.ownoptions[i].industryType;
          this.formData.selfName = this.ownoptions[i].selfName;
          this.natureBusiness = this.ownoptions[i].natureBusiness;
          this.owerTax = this.ownoptions[i].taxId;
          if(this.ownoptions[i].industryTax){
            this.owerTaxfee = new Decimal( this.ownoptions[i].industryTax).mul(new Decimal(100)) + "%";
          }else{
            this.owerTaxfee='0%';
          }
          this.residence = this.ownoptions[i].residence;
          this.privateDepositBank = this.ownoptions[i].privateDepositBank;
          this.privateAccountNumber = this.ownoptions[i].privateAccountNumber;
          this.publicDepositBank1 = this.ownoptions[i].publicDepositBank1;
          this.publicAccountNumber1 = this.ownoptions[i].publicAccountNumber1;
          this.accountType = this.ownoptions[i].accountType;
          this.contactPhone = this.ownoptions[i].contactPhone;
          this.getcode(this.ownoptions[i].selfCode);
        }
      }
    },
   
    //返回
    resetForm() {
      this.$tab.closeOpenPage({ path: "/tj-business/project/list" });
    },

    //渠道商接口  记得修改 userid
    getinfoByUserId() {
      getInfo().then((res) => {
        this.userinfo = res.user;
        this.userId = res.user.userId;
        this.username = res.user.userName;
        this.getOwn();
        this.formData.projectLeader = res.user.nickName;
        getinfoByUserId({ userId: this.userId }).then((res) => {
          this.placeCodeOptions = res.data;
        });
      });
    },
    //获取税率
    getRate() {
      crudRate.getAllRate().then((res) => {
        let tree = []; // 用来保存树状的数据形式
        this.parseTree(res.rows, tree, 0);
        console.log("tree", tree);
        this.industryTypes = tree;
        this.industryTypeList = res.rows;
      });
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

            taxRates: b,
            children: [],
          };
          tree.push(obj);
          this.parseTree(industry, obj.children, obj.id);
        }
      }
    },
 
    //获取销货单位（乙方）
    getOwn(){
      ownlist({ username: this.username})
        .then((res) => {
          this.ownoptions = res;
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //监听开票内容类型
    tickettaxvip(e) {
       
      if (e > 0) {
        this.tickettaxvipok = true;
        this.formData.ticketTax = 3;
        if(this.listnew.isSlider==1){
            this.confirmEditStatus2=true;
            this.confirmEditStatus3=true;
            this.formData.isSelfCount='2';
            return;
          }else{
               this.confirmEditStatus2=false;
               this.confirmEditStatus3=false;
               this.formData.isSelfCount=JSON.stringify(this.listnew.isSelfCount);
          }
       } else {
        if(this.listnew.isSliderOrdinary==1){
              this.confirmEditStatus2=true;
              this.confirmEditStatus3=true;
              this.formData.isSelfCount='2';
             return;
          }else{
              this.confirmEditStatus2=false;
              this.confirmEditStatus3=false;
              this.formData.isSelfCount=JSON.stringify(this.listnew.isSelfCount);
          }
        this.formData.ticketTax=0;
        this.tickettaxvipok = false;
      }
      this.$nextTick(() => {
        if (this.formData.ticketType == 0) {
          this.formData.isSlider = "1";
          this.formData.isSliderOrdinary = "0";
        } else {
          this.formData.isSlider = "0";
          this.formData.isSliderOrdinary = "1";
        }
      });
    },
    repair(i) {
      if (i >= 0 && i <= 9) {
        return "0" + i;
      } else {
        return i;
      }
    },
    gettoday() {
      var date = new Date(); //当前时间
      var year = date.getFullYear(); //年
      var month = this.repair(date.getMonth() + 1); //月
      var day = this.repair(date.getDate()); //日
      var hour = this.repair(date.getHours()); //时
      var minute = this.repair(date.getMinutes()); //分
      var second = this.repair(date.getSeconds()); //秒
      //当前时间
      var curTime =
        year +
        "-" +
        month +
        "-" +
        day;
      // this.formData.createTime = curTime;
      this.formData.projectTimeStart = curTime;
    },
    getRealTime(){
      var date = new Date(); //当前时间
      var year = date.getFullYear(); //年
      var month = this.repair(date.getMonth() + 1); //月
      var day = this.repair(date.getDate()); //日
      var hour = this.repair(date.getHours()); //时
      var minute = this.repair(date.getMinutes()); //分
      var second = this.repair(date.getSeconds()); //秒
      //当前时间
      
     return   hour +":" +minute + ":" + second;
    },
    getcode(selfCode) {
      getcode({ selfCode: selfCode })
        .then((res) => {
          this.formData.projectCode = res;
        })
        .catch((errore) => {
          console.log(errore);
        });
    },
    handleChange(val) {
      console.log(val);
    },
    check(resmsg) {
      let parms = {
        checkReasult: resmsg,
        checkUser: this.userinfo.userName,
        phonenumber: this.userinfo.phonenumber,
        projectCode: this.formData.projectCode,
        projectType: "6",
      };
      check(parms)
        .then((res) => {
          console.log("添加项目成功！");
        })
        .catch((error) => {});
    },
    onSubmit() {
     
      if (this.formData.projectTotalAmount < 1) {
        this.$alert("项目金额必须大于1", "系统提示", {
          confirmButtonText: "确定",

          type: "error",
        });
        return;
      }
      this.$refs["elForm"].validate((valid) => {
        // TODO 提交表单
        if (valid) {
          this.formData.userId = this.userId;

          //如果是附件的话
          if (this.fileNameradio == 2) {
            // this.formData.fileName = this.fileNamefile;
            this.formData.fileName = JSON.stringify(this.formData.fileName);
          }
          this.formData.projectOwnerTaxid = this.owerTax;
          this.formData.projectRemainAmount = this.formData.projectTotalAmount; //新增可以用金额为总金额
          this.formData.projectPackageAmount = 0; //已用金额为0
          
          this.formData.projectTimeStart=this.formData.projectTimeStart+" "+this.getRealTime();
          //购货单位（甲方）数据

          add(this.formData).then((res) => {
            if (res != undefined) {
              if (res.code === 200) {
                let resmsg = "项目填写完成";
                this.check("项目填写完成");
                let obj = {
                  title: "项目进度",
                  backUrl: "/tj-business/project/list",
                  resmsg: resmsg,
                  backName: "List",
                };
                this.$cache.local.setJSON("tj-successProject", obj);
                this.$tab.closeOpenPage({ path: "/tj-business/project/success" });
              }
            }
          });

          let params1 = {
            purchCompany: this.formData.purchCompany,
          };
          let params2 = {
            purchCompanyTaxid: this.formData.purchCompanyTaxid,
          };
          if(this.isaddPurch==1){
            getPuJialist(params1).then((res) => {
            if (res != undefined) {
              if (res.rows.length == 0) {
                getPuJialist(params2).then((resok) => {
                  if (resok != undefined) {
                    if (resok.rows.length == 0) {
                      let params = {
                        bankCode: this.formData.bankCode,
                        bankName: this.formData.bankName,
                        createBy: this.userinfo.nickName,
                        updateBy: this.userinfo.nickName,
                        placeAliasName: this.formData.placeAliasName,
                        purchCompany: this.formData.purchCompany,
                        purchCompanyAddress: this.formData.purchCompanyAddress,
                        purchCompanyPhone: this.formData.purchCompanyPhone,
                        purchCompanyTaxid: this.formData.purchCompanyTaxid,
                        userId: this.userinfo.userId,
                      };
                      addJia(params);
                    }
                  }
                });
              }
            }
          });

          }
          
        } else {
          this.$alert("请正确填写", "系统提示", {
            confirmButtonText: "确定",

            type: "warning",
          });
        }
      });
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

.comright {
  padding-right: 10%;
}

.combottom {
  margin-bottom: 10px;
}

.flexs {
  display: flex;
  justify-content: center;
}

.bankno {
  letter-spacing: 2px;

  font-size: 20px;

  color: blue;
}

.my-autocomplete {
  li {
    line-height: normal;
    padding: 7px;

    .name {
      text-overflow: ellipsis;
      overflow: hidden;
    }

    .addr {
      font-size: 12px;
      color: #b4b4b4;
    }

    .highlighted .addr {
      color: #ddd;
    }
  }
}
</style>
