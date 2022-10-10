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

      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="项目编号" :required="true">
            <el-input
              v-model="formData.projectCode"
              :readonly="true"
            ></el-input>
          </el-form-item>

          <el-form-item class="comright" label="项目名称" prop="projectName">
            <el-input
              v-model="formData.projectName"
              :readonly="true"
            ></el-input>
          </el-form-item>
          <el-form-item class="comright" label="客户全名" :required="true">
            <el-input
              v-model="formData.placeAliasName"
              :readonly="true"
            ></el-input>
          </el-form-item>
          <el-form-item label="项目款往来" :required="true">
            <el-radio disabled v-model="formData.isDealings" label="1"
              >是</el-radio
            >
            <el-radio disabled v-model="formData.isDealings" label="0"
              >否</el-radio
            >
          </el-form-item>
        </el-col>

        <el-col :span="9">
          <el-form-item class="comright" label="项目时间" :required="true">
            <el-date-picker
            style="width:100%"
            disabled
            v-model="formData.projectTimeStart"
            value-format="yyyy-MM-dd"
            align="right"
        >
        </el-date-picker>
          </el-form-item>
          <el-form-item class="comright" label="项目金额" :required="true">
            <el-input
              type="number"
              disabled
              style="width: 100%"
              v-model="formData.projectTotalAmount"
              :step="0.01"
              :min="0"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
          <el-form-item class="comright" label="业务经理" :required="true">
            <el-input
              v-model="formData.projectLeader"
              :readonly="true"
            ></el-input>
          </el-form-item>
          
        </el-col>
      </el-row>

      <el-row
        type="flex"
        class="row-bg"
        style="margin-bottom: 20px"
        justify="space-around"
      >
        <el-col :span="9">
          <div class="bankno">购货单位（甲方）</div>
         
        </el-col>
        <el-col :span="8">
          <div></div>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="名称" prop="purchCompany">
            <el-input
              v-model="formData.purchCompany"
              :readonly="true"
            ></el-input>
          </el-form-item>
          <el-form-item class="comright" label="地址">
            <el-input
              v-model="formData.purchCompanyAddress"
              :readonly="true"
              maxlength="250"
              show-word-limit
              type="textarea"
              :rows="1"
            ></el-input>
          </el-form-item>
          <el-form-item class="comright" label="开户行">
            <el-input v-model="formData.bankName" :readonly="true"></el-input>
          </el-form-item>
          <el-form-item label="添加购方列表" :required="true">
              <el-radio disabled v-model="formData.isAddBuyer" label="1">是</el-radio>
              <el-radio disabled v-model="formData.isAddBuyer" label="0">否</el-radio>
           </el-form-item>
        </el-col>

        <el-col :span="9">
          <el-form-item class="comright" label="纳税人识别号" :required="true">
            <el-input
              :readonly="true"
              v-model="formData.purchCompanyTaxid"
            ></el-input>
          </el-form-item>
          <el-form-item class="comright" label="电话">
            <el-input
              v-model="formData.purchCompanyPhone"
              :readonly="true"
            ></el-input>
          </el-form-item>
          <el-form-item class="comright" label="帐号">
            <el-input v-model="formData.bankCode" :readonly="true"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row
        type="flex"
        class="row-bg"
        style="margin-bottom: 20px"
        justify="space-around"
      >
        <el-col :span="9">
          <div class="bankno">销货单位（乙方）</div>
       </el-col>
        <el-col :span="8">
          <div></div>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="名称" :required="true">
            <el-input :readonly="true" v-model="formData.selfName"></el-input>
          </el-form-item>
          <el-form-item class="comright" label="地址">
            <el-input
              :readonly="true"
              v-model="formData.residence"
              maxlength="250"
              show-word-limit
              type="textarea"
              :rows="1"
            ></el-input>
          </el-form-item>
          <el-form-item class="comright" label="开户行">
            <el-input
              v-if="formData.accountType == 1"
              v-model="formData.privateDepositBank"
              :readonly="true"
            ></el-input>
            <el-input
              v-else
              v-model="formData.publicDepositBank1"
              :readonly="true"
            ></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="9">
          <el-form-item class="comright" label="纳税人识别号" :required="true">
            <el-input :readonly="true" v-model="formData.taxId"></el-input>
          </el-form-item>
          <el-form-item class="comright" label="电话">
            <el-input
              :readonly="true"
              v-model="formData.contactPhone"
            ></el-input>
          </el-form-item>
          <el-form-item class="comright" label="帐号">
            <el-input
              v-if="formData.accountType == 1"
              v-model="formData.privateAccountNumber"
              :readonly="true"
            ></el-input>
            <el-input
              v-else
              v-model="formData.publicAccountNumber1"
              :readonly="true"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="行业类型" :required="true">
            <el-tooltip
              class="item"
              effect="dark"
              :content="selectTipType"
              placement="top-start"
            >
              <el-select
                class="main-select-tree"
                ref="selectTree"
                v-model="formData.industryType"
                style="width: 100%"
                disabled
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
                    <span
                      style="
                        float: right;
                        color: #8492a6;
                        font-size: 14px;
                        padding-right: 10px;
                      "
                      >{{ data.taxRates }}</span
                    >
                  </span>
                </el-tree>
              </el-select>
            </el-tooltip>
            <!-- <treeselect  disabled   v-model="formData.industryType" :options="industryTypes" :show-count="true" /> -->
          </el-form-item>
        </el-col>

        <el-col :span="9">
          <el-form-item class="comright" label="行业税率" :required="true">
            <el-input
              :readonly="true"
              v-model="formData.industryTax"
            ></el-input>
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
          <el-form-item
            style="padding-right: 4%"
            label="经营范围"
            :required="true"
          >
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
              disabled
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
          <el-form-item class="comright" label="开票内容类型" :required="true">
            <el-input
              :readonly="true"
              v-if="fileNameradio == 1"
              value="手动输入"
            ></el-input>
            <el-input :readonly="true" v-else value="上传附件"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="9">
          <el-form-item v-if="tickettaxvipok" label="发票税率" :required="true">
            <el-input v-model="formData.ticketTax" :readonly="true"></el-input>
          </el-form-item>
          <el-form-item
            v-else
            class="comright"
            label="发票税率"
            :required="true"
          >
            <el-select
              disabled
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
            class="comright"
            label="开票内容"
            v-if="fileNameradio == 1"
          >
            <el-input
              maxlength="50"
              show-word-limit
              :readonly="true"
              type="textarea"
              :rows="2"
              v-model="formData.fileName"
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
            v-if="fileNameradio == 2"
            :required="true"
          >
            <uploadSmall
              @getfileName="getfileNameS"
              :fileName="formData.fileName"
              :fileNameOld="fileName"
              :isDetail="isDetail"
            ></uploadSmall>
          </el-form-item>
        </el-col>
        <el-col :span="9"></el-col>
      </el-row>

      <!-- <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="21">
                    <el-form-item style="padding-right:4%" label="项目行业类型">
                        <el-input :readonly="true" v-model="formData.projectTrade">
                        </el-input>
                    </el-form-item>
                </el-col>
            </el-row>
           -->

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="21">
          <el-form-item
            style="padding-right: 4%"
            label="发票备注"
            prop="ticketRemark"
          >
            <el-input
              maxlength="50"
              show-word-limit
              :readonly="true"
              type="textarea"
              :rows="2"
              placeholder="请输入发票备注"
              v-model="formData.projectDesc"
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
            <el-radio disabled v-model="formData.isSelfCount" label="0"
              >按个体结算</el-radio
            >
            <el-radio disabled v-model="formData.isSelfCount" label="1"
              >按客户结算</el-radio
            >
            <el-radio disabled  v-model="formData.isSelfCount" label="2"
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
          <el-col :span="9" >
            <div class="bankno">增值税普通发票
              </div>
           </el-col>
          <el-col :span="8"></el-col>
        </el-row>
      <el-row
        type="flex"
        class="row-bg"
        justify="space-around"
        v-if="formData.isSelfCount == 2"
      >
        <el-col :span="9">
          <el-form-item label="状态" :required="true">
            <el-radio disabled v-model="formData.isSliderOrdinary" label="0"
              >开启</el-radio
            >
            <el-radio disabled v-model="formData.isSliderOrdinary" label="1"
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
                  disabled
                  v-model="formData.ordinaryProxyIsmoney"
                  label="0"
                  >按定额收取
                </el-radio>
                <el-radio
                  disabled
                  v-model="formData.ordinaryProxyIsmoney"
                  label="1"
                  >按百分比收取
                </el-radio>
                <el-input
                  disabled
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
                  disabled
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
              <el-radio disabled v-model="formData.isSelfTax" label="0"
                >是</el-radio
              >
              <el-radio disabled v-model="formData.isSelfTax" label="1"
                >否</el-radio
              >
            </el-form-item>
            <el-form-item label="价税分离" :required="true">
              <el-radio disabled v-model="formData.isOrdinaryTax" label="0"
                >是</el-radio
              >
              <el-radio disabled v-model="formData.isOrdinaryTax" label="1"
                >否</el-radio
              >
            </el-form-item>
            <el-form-item label="是否分润" :required="true">
              <el-radio disabled v-model="formData.isOrdinaryShare" label="0"
                >是</el-radio
              >
              <el-radio disabled v-model="formData.isOrdinaryShare" label="1"
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
                  disabled
                  v-model="formData.ordinaryShareIsmoney"
                  label="0"
                  >按定额收取
                </el-radio>
                <el-radio
                  disabled
                  v-model="formData.ordinaryShareIsmoney"
                  label="1"
                  >按百分比收取
                </el-radio>
                <el-input
                  disabled
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
                  disabled
                  v-else
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
          <el-col :span="9" >
            <div class="bankno">增值税专用发票
              </div>
           </el-col>
          <el-col :span="8"></el-col>
        </el-row>
        <el-row
        type="flex"
        class="row-bg"
        justify="space-around"
        v-if="formData.isSelfCount == 2"
      >
        <el-col :span="9">
          <el-form-item label="状态" :required="true">
            <el-radio disabled v-model="formData.isSlider" label="0"
              >开启</el-radio
            >
            <el-radio disabled v-model="formData.isSlider" label="1"
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
                  disabled
                  v-model="formData.specialProxyIsmoney"
                  label="0"
                  >按定额收取
                </el-radio>
                <el-radio
                  disabled
                  v-model="formData.specialProxyIsmoney"
                  label="1"
                  >按百分比收取
                </el-radio>
                <el-input
                  disabled
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
                  disabled
                  v-else
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
              <el-radio disabled v-model="formData.isSpecialSelfTax" label="0"
                >是</el-radio
              >
              <el-radio disabled v-model="formData.isSpecialSelfTax" label="1"
                >否</el-radio
              >
            </el-form-item>
            <el-form-item label="价税分离" :required="true">
              <el-radio disabled v-model="formData.isSpecialTax" label="0"
                >是</el-radio
              >
              <el-radio disabled v-model="formData.isSpecialTax" label="1"
                >否</el-radio
              >
            </el-form-item>
            <el-form-item label="是否分润" :required="true">
              <el-radio disabled v-model="formData.isSpecialShare" label="0"
                >是</el-radio
              >
              <el-radio disabled v-model="formData.isSpecialShare" label="1"
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
                  disabled
                  v-model="formData.specialShareIsmoney"
                  label="0"
                  >按定额收取
                </el-radio>
                <el-radio
                  disabled
                  v-model="formData.specialShareIsmoney"
                  label="1"
                  >按百分比收取
                </el-radio>
                <el-input
                  disabled
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
                  disabled
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
        <el-col :span="9"></el-col>
    
    </el-row>

      <el-row
        v-if="formData.isSelfCount == 2"
        type="flex"
        class="row-bg"
        style="margin-bottom: 20px"
        justify="space-around"
      >
      <el-col :span="9" class="flexs">
          <div class="bankno" style="width: 35%">一次性费用</div>
          <div style="width: 50%; hegiht: 10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>

      <el-row
        type="flex"
        class="row-bg"
        justify="space-around"
        v-if="formData.isSelfCount == 2"
      >
        <el-col :span="9">
          <el-form-item label="状态" prop="isDisposable">
            <el-radio disabled v-model="formData.isDisposable" label="0"
              >开启</el-radio
            >
            <el-radio disabled v-model="formData.isDisposable" label="1"
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
                    disabled
                    v-model="formData.disposableFeeIsmoney"
                    label="0"
                  >
                    按定额收取</el-radio
                  >
                  <el-radio
                    disabled
                    v-model="formData.disposableFeeIsmoney"
                    label="1"
                  >
                    按百分比收取</el-radio
                  >

                  <el-input
                    disabled
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
                    disabled
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
          <el-form-item label="备注" v-if="formData.isDisposable == 0">
            <el-input
              disabled
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
            <el-radio disabled v-model="formData.isDisposableShare" label="0"
              >是</el-radio
            >
            <el-radio disabled v-model="formData.isDisposableShare" label="1"
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
                    disabled
                    v-model="formData.disposableShareIsmoney"
                    label="0"
                  >
                    按定额收取</el-radio
                  >
                  <el-radio
                    disabled
                    v-model="formData.disposableShareIsmoney"
                    label="1"
                  >
                    按百分比收取</el-radio
                  >

                  <el-input
                    disabled
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
                    disabled
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
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="21">
          <el-form-item
            class="comright"
            style="padding-right: 4%; margin-left: -7%"
          >
            <el-radio v-model="isokradioS" label="1"> 通过</el-radio>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="21">
          <el-form-item
            class="comright"
            style="padding-right: 4.2%; margin-left: -7%"
          >
            <div
              style=" display: flex;
                align-items: center;
                justify-content: flex-start;
              "
            >
              <el-radio v-model="isokradioS" label="2">不通过 </el-radio>
              <el-input
                type="textarea"
                placeholder="请输入不通过说明"
                v-model="formData.checkContent"
                :disabled="isokradioS == 1"
              ></el-input>
            </div>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around" style="margin-bottom:20px">
        <el-col :span="8"></el-col>
        <el-col :span="8" class="flexs">
          <el-button type="danger" @click="resetForm">关闭</el-button>
          <el-button
            v-if="isokradioS == 2"
            type="primary"
            @click="submitForm(2)"
            >提交</el-button
          >
          <el-button v-else type="primary" @click="submitForm(1)"
            >提交</el-button
          >
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
  getcode,
  getinfoByUserId,
  detail,
  edit,
  check,
} from "@/api/tg-api/project/list";
import { getInfo } from "@/api/login";
import { Decimal } from "decimal.js";
export default {
  name: "AuditItems",
  components: {
    uploadSmall,
  },
  data() {
    return {
      projectStatus: "1",
      projectStatusNew: "0",
      selectTipType: "",
      isDetail: "1",
      isNone: [],
      checkContent: "",
      expandOnClickNode: true,
      defaultProps: {
        children: "children",
        label: "label",
      },
      userinfo: {},
      industryTypes: [],
      industryTypeList: [],
      username: "",
      userId: "",
      fileName2: [],
      fileName: [],
      dialogVisible1: false,
      dialogImageUrl1: "",
      fileNameradio: "1", //开票类型
      natureBusiness: "", //经营范围
      tickettaxvipok: false,
      placename: "",
      isokradio: "1",
      isokradioS: "1",
      companyTax: "", //购货单位（甲方）纳税人识别号
      owerTax: "", //销货单位（乙方）纳税人识别号
      owntype: "", //销货单位（乙方）行业类型
      owerTaxfee: "", //销货单位（乙方）税率
      projectStatus: 1,
      formData: {},
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
      //购货单位（甲方）
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
        {
          value: 3,
          label: "3%",
        },
      ],
        //项目税率
       projectNormal: [
        {
          value: 0,
          label: "免税",
        },
        {

            value: 0.03,
            label: '3%'
        },
      ],
      rules: {
       
      },
    };
  },
  computed: {},

  mounted() {
    this.getInfo();
    this.getlist();
    this.getRate();
    // this.getinfoByUserId(); //渠道商
  },

  methods: {
    getfileNameS() {},
    //获取个人信息
    getInfo() {
      getInfo().then((res) => {
        this.userinfo = res.user;
      });
    },
    check(resmsg) {
      let parms = {
        checkReasult: resmsg,
        checkUser: this.userinfo.userName,
        phonenumber: this.userinfo.phonenumber,
        projectCode: this.formData.projectCode,
        projectType: "1",
      };
      check(parms)
        .then((res) => {
          console.log("项目审核完成插入日志成功！");
        })
        .catch((error) => {});
    },
    submitForm(type) {
      this.$refs["elForm"].validate((valid) => {
        // TODO 提交表单
        if (valid) {
          this.$modal.loading("正在加载数据，请稍后...");
          if (this.fileNameradio == 2) {
            if (Array.isArray(this.formData.fileName)) {
            this.formData.fileName = JSON.stringify(
              this.formData.fileName
            );
          }
          }
           detail({
            projectCode: this.$cache.local.getJSON("tg-project-code"),
          }).then((response) => {
              let list=response.data;
              if (
                list.projectDutypaidStatus == 1 &&
                list.projectReceiveStatus == 1 &&
                list.projectTicketStatus == 1 &&
                list.projectAcceptanceStatus == 1 &&
                list.projectContractStatus == 1 &&
                list.projectPayStatus == 1
          ) {
            this.projectStatusNew = 2;
          } else if (
            list.projectDutypaidStatus == 2 ||
            list.projectReceiveStatus == 2 ||
            list.projectTicketStatus == 2 ||
            list.projectAcceptanceStatus == 2 ||
            list.projectContractStatus == 2 ||
            list.projectPayStatus == 2
          ) {
            this.projectStatusNew = 1;
          }else{
            this.projectStatusNew=0;
          }
          this.formData.projectCheckStatus=type;
          this.formData.projectStatus=this.projectStatusNew;

          this.$nextTick(function () {
          edit(this.formData).then((res) => {
            if (res != undefined) {
              if (res.code === 200) {
                this.$modal.closeLoading();
                  let resmsg = "";
                  if (type == 1) {
                    resmsg = "项目审核完成";
                    this.check("项目审核完成");
                  } else {
                    this.check("项目审核不通过。" + "原因:" + this.formData.checkContent);
                    resmsg = "项目审核完成";
                  }

                  let obj = {
                    title: "项目审核",
                    backUrl: this.$cache.local.getJSON("tg-aduitback").backurl,
                    resmsg: resmsg,
                    backName: this.$cache.local.getJSON("tg-aduitback").name,
                  };
                  this.$cache.local.setJSON("tg-successProject", obj);
                  this.$tab.closeOpenPage({
                    path: "/tg-business/project/success",
                  });
               
              } else {
                this.$modal.closeLoading();
                this.$modal.msgError(res.msg);
                this.$tab.closeOpenPage({
                  path: this.$cache.local.getJSON("tg-aduitback").backurl,
                });
              }
            }
          });
        });

          })
     
        } else {
          this.$alert("请正确填写", "系统提示", {
            confirmButtonText: "确定",

            type: "warning",
          });
        }
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

    handleNodeClick(node) {
      this.formData.industryType = node.id;
      this.$refs.selectTree.blur();
      this.$nextTick(function () {
        this.selectTipType = this.$refs.selectTree.selected.label;
      });
    },
    getlist() {
      this.$modal.loading("正在加载数据，请稍后...");
      detail({
        projectCode: this.$cache.local.getJSON("tg-project-code"),
      })
        .then((response) => {
          this.formData = response.data;
          this.$modal.closeLoading();

          this.formData.isDisposableShare = JSON.stringify(
            this.formData.isDisposableShare
          );
          this.formData.disposableShareIsmoney = JSON.stringify(
            this.formData.disposableShareIsmoney
          );
          this.formData.disposableShare = JSON.stringify(
            this.formData.disposableShare
          );
          this.formData.disposableFeeIsmoney = JSON.stringify(
            this.formData.disposableFeeIsmoney
          );
          this.formData.disposableFee = JSON.stringify(this.formData.disposableFee);
          this.formData.isDisposable = JSON.stringify(this.formData.isDisposable);
          this.formData.isRegisterMoney = JSON.stringify(
            this.formData.isRegisterMoney
          );
        if (this.formData.isAddBuyer == 1) {
             this.formData.isAddBuyer = "1";
          } else {
            this.formData.isAddBuyer = "0";
          }
          if (this.formData.isDealings == 1) {
            this.formData.isDealings = "1";
          } else {
            this.formData.isDealings = "0";
          }
          if (this.formData.isActive) {
            this.projectStatus = parseInt(this.formData.isActive);
          } else {
            this.projectStatus = 1;
          }
          this.formData.selfShareIsmoney = JSON.stringify(
            this.formData.selfShareIsmoney
          );
          this.formData.isSelfShare = JSON.stringify(this.formData.isSelfShare);
          this.formData.selfShare = JSON.stringify(this.formData.selfShare);

          this.formData.ordinaryTax = JSON.stringify(this.formData.ordinaryTax);
          this.formData.ordinarySpecialTax = JSON.stringify(
            this.formData.ordinarySpecialTax
          );
          this.formData.isSelfTax = JSON.stringify(this.formData.isSelfTax);
          this.formData.isSpecialSelfTax = JSON.stringify(
            this.formData.isSpecialSelfTax
          );
          this.formData.isSelfCount = JSON.stringify(this.formData.isSelfCount);

          this.formData.ordinaryProxyIsmoney = JSON.stringify(
            this.formData.ordinaryProxyIsmoney
          );
          this.formData.specialProxyIsmoney = JSON.stringify(
            this.formData.specialProxyIsmoney
          );
          this.formData.ordinaryShareIsmoney = JSON.stringify(
            this.formData.ordinaryShareIsmoney
          );
          this.formData.specialShareIsmoney = JSON.stringify(
            this.formData.specialShareIsmoney
          );
          this.formData.isOrdinaryShare = JSON.stringify(
            this.formData.isOrdinaryShare
          );
          this.formData.isSpecialShare = JSON.stringify(
            this.formData.isSpecialShare
          );

          if (this.formData.isOrdinaryTax == 1) {
            this.formData.isOrdinaryTax = "1";
          } else {
            this.formData.isOrdinaryTax = "0";
          }
          if (this.formData.isSpecialTax == 1) {
            this.formData.isSpecialTax = "1";
          } else {
            this.formData.isSpecialTax = "0";
          }

          if (this.formData.isSelfCount == "0") {
            this.formData.isSelfCount = "0";
          } else if (this.formData.isSelfCount == "1") {
            this.formData.isSelfCount = "1";
          } else if (this.formData.isSelfCount == "2") {
            this.formData.isSelfCount = "2";
          }

          if (this.formData.isSelfTax == "0") {
            this.formData.isSelfTax = "0";
          } else {
            this.formData.isSelfTax = "1";
          }
          if (this.formData.isSpecialSelfTax == "0") {
            this.formData.isSpecialSelfTax = "0";
          } else {
            this.formData.isSpecialSelfTax = "1";
          }

          if (this.formData.isSlider == "0") {
            this.formData.isSlider = "0";
          } else {
            this.formData.isSlider = "1";
          }

          if (this.formData.isSliderOrdinary == "0") {
            this.formData.isSliderOrdinary = "0";
          } else {
            this.formData.isSliderOrdinary = "1";
          }
          this.formData.industryTax =
            new Decimal(this.formData.industryTax).mul(new Decimal(100)) + "%";
          this.isokradio = JSON.stringify(this.formData.placeStatus);
          this.formData.placeStatus = parseInt(this.formData.placeStatus);
          if (this.formData.fileName) {
            if (this.formData.fileName.indexOf("[") != -1) {
              this.formData.fileName = JSON.parse(this.formData.fileName);
            }

            if (Array.isArray(this.formData.fileName)) {
              this.fileNameradio = "2";
              this.fileName = [];
              //如果是图片的话
              for (let j in this.formData.fileName) {
                this.fileName.push({
                  url: this.baseImgPath + this.formData.fileName[j],
                  name: this.formData.fileName[j],
                });
              }
            } else {
              this.fileNameradio = "1";
            }
          } else {
            this.fileNameradio = "1";
          }
        })
        .catch((err) => {
          this.$modal.closeLoading();
        });
    },
    resetForm() {
      this.$tab.closeOpenPage({
        path: this.$cache.local.getJSON("tg-aduitback").backurl,
      });
    },

    //渠道商接口
    getinfoByUserId() {
      getInfo().then((res) => {
        //this.userId = res.user.userId;
        // this.username = res.user.userName;
        getinfoByUserId({ userId: this.formData.userId }).then((res) => {
          this.placename = res.data;
        });
      });
    },

    getRate() {
      crudRate.getAllRate().then((res) => {
        console.log("getAllRate", res.rows);
        let tree = []; // 用来保存树状的数据形式
        this.parseTree(res.rows, tree, 0);
        console.log("tree", tree);
        this.industryTypes = tree;
        this.industryTypeList = res.rows;
        this.$nextTick(function () {
          this.selectTipType = this.$refs.selectTree.selected.label;
        });
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
            children: [],
          };
          tree.push(obj);
          this.parseTree(industry, obj.children, obj.id);
        }
      }
    },

    tickettaxvip(e) {
      console.log(e);
      if (e > 0) {
        this.tickettaxvipok = true;
        this.formData.ticketTax = 3;
      } else {
        this.tickettaxvipok = false;
      }
    },
    getcode() {
      getcode()
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
::v-deep .el-input.is-disabled .el-input__inner {
  background-color: rgba(255, 255, 255, 1.5) !important;
  color: black !important;
  border-color: rgba(135, 206, 250, 0.7) !important;
}
::v-deep .el-input-group__append {
  background-color: rgba(255, 255, 255, 1.5) !important;
  color: black !important;
  border-color: rgba(135, 206, 250, 0.7) !important;
}
</style>
