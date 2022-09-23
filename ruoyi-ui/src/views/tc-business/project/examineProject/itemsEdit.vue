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
            <el-input v-model="formData.projectTimeStart" disabled></el-input>
          </el-form-item>
          <el-form-item
            class="comright"
            label="项目金额"
            prop="projectTotalAmount"
          >
            <el-input
              :disabled="isticket"
              type="number"
              style="width: 100%"
              v-model="formData.projectTotalAmount"
              :step="0.00001"
              :min="0"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
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
        </el-col>

        <el-col :span="9">
          <!-- <el-form-item class="comright" label="客户状态" prop="isokradio">
                        <el-input disabled v-if="isokradio == 0" value="正常"></el-input>
                        <el-input disabled v-if="isokradio == 1" value="欠费"></el-input>
                        <el-input disabled v-if="isokradio == 2" value="冻结"></el-input>
                    </el-form-item> -->

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
          <div class="bankno" style="width: 35%">甲方信息</div>
          <div style="width: 50%; hegiht: 10px"></div>
        </el-col>
        <el-col :span="9">
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
        </el-col>
        <el-col :span="9">
          <el-form-item
            class="comright"
            label="纳税人识别号"
            prop="purchCompanyTaxid"
          >
            <el-input
              v-model.trim="inputValCompute"
              :readonly="true"
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
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width: 35%">乙方信息</div>
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
              filterable
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
              v-model="formData.publicAccountNumber1"
              :readonly="true"
            ></el-input>
          </el-form-item>

          <!-- <el-form-item class="comright" label="乙方状态">
                        <el-select style="width:100%" disabled clearable v-model="projectStatus" placeholder="请选择项目状态">
                            <el-option v-for="item in options" :key="item.value" :label="item.label"
                                :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item> -->
        </el-col>

        <el-col :span="9">
          <el-form-item class="comright" label="纳税人识别号">
            <el-input disabled v-model="formData.projectOwnerTaxid"></el-input>
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
          <el-form-item class="comright" label="行业类型" prop="industryType">
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
          </el-form-item>
        </el-col>

        <el-col :span="9">
          <el-form-item class="comright" label="行业税率">
            <el-input disabled v-model="owerTaxfee"></el-input>
          </el-form-item>
        </el-col>
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
              style="width: 86%"
              v-model="formData.ticketTax"
              :required="true"
            ></el-input>
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
            class="comright"
            label="开票内容"
            prop="fileName"
            v-if="fileNameradio == 1"
          >
            <el-input
              maxlength="50"
              show-word-limit
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
            prop="fileName"
            v-if="fileNameradio == 2"
          >
            <uploadSmall
              @getfileName="getfileNameS"
              :fileName="isNone"
              :fileNameOld="fileName"
              :isDetail="isDetail"
            ></uploadSmall>
          </el-form-item>
        </el-col>
        <el-col :span="9"></el-col>
      </el-row>

      <!-- <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="21">
                    <el-form-item style="padding-right:4%" class="comright" label="项目行业类型" prop="projectTrade">
                         <el-input disabled v-model="formData.projectTrade"></el-input>
                    </el-form-item>
                </el-col>
            </el-row> -->

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="21">
          <el-form-item
            style="padding-right: 4%"
            label="发票备注"
            prop="projectDesc"
          >
            <el-input
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
              >按个体结算</el-radio
            >
            <el-radio
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
        v-if="formData.isSelfCount == 0"
          type="flex"
          class="row-bg"
          style="margin-bottom: 20px"
          justify="space-around"
        >
          <el-col :span="10" >
            <div class="bankno">
              个体注册服务费
             </div>
           
          </el-col>
          <el-col :span="9">
            <div></div>
          </el-col>
        </el-row>
      <el-row
        v-if="formData.isSelfCount == 0"
        type="flex"
        class="row-bg"
        justify="space-around"
      >
        <el-col :span="9">
          <el-form-item label="状态" prop="isRegisterMoney">
            <el-radio v-model="formData.isRegisterMoney" label="0">开启</el-radio>
            <el-radio v-model="formData.isRegisterMoney" label="1">关闭</el-radio>
          </el-form-item>
          <el-form-item
            v-if="formData.isRegisterMoney == 0"
            label="服务费"
            prop="registerMoney"
          >
            <el-input
              v-model="formData.registerMoney"
              :min="0"
              onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9"> </el-col>
      </el-row>
      <el-row
        v-if="formData.isSelfCount == 0 && formData.isRegisterMoney==0"
        type="flex"
        class="row-bg"
        justify="space-around"
      >
        <el-col :span="9">
          <el-form-item label="是否分润" prop="isSelfShare">
            <el-radio v-model="formData.isSelfShare" label="0">是</el-radio>
            <el-radio v-model="formData.isSelfShare" label="1">否</el-radio>
          </el-form-item>
        </el-col>
        <el-col :span="9"> </el-col>
      </el-row>
      <el-row
        v-if="formData.isSelfCount == 0 && formData.isSelfShare == 0"
        type="flex"
        class="row-bg"
        justify="space-around"
      >
        <el-col :span="9">
          <el-form-item label="分润方式" prop="selfShare">
            <div style="">
              <el-radio
                @change="selfShareIsmoneys"
                v-model="formData.selfShareIsmoney"
                label="0"
                >按定额收取
              </el-radio>
              <el-radio
                @change="selfShareIsmoneys"
                v-model="formData.selfShareIsmoney"
                label="1"
                >按百分比收取
              </el-radio>

              <el-input
                v-if="formData.selfShareIsmoney == 0"
                style="width: 100%"
                :min="0"
                v-model="formData.selfShare"
                onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
              >
                <template slot="append">元</template>
              </el-input>
              <el-input
                v-model="formData.selfShare"
                v-else
                style="width: 100%"
                @input="isSelfShares"
                @change="isSelfShares"
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
        <el-col :span="9"> </el-col>
      </el-row>

      <el-row
        v-if="formData.isSelfCount == 0 || formData.isSelfCount == 2"
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
        v-if="formData.isSelfCount == 0 || formData.isSelfCount == 2"
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
        <el-col :span="9"></el-col>
      </el-row>


      <el-row
        v-if="formData.isSelfCount == 0 || formData.isSelfCount == 2"
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


       


      <el-row   type="flex"
        class="row-bg"
        justify="space-around"
        v-if="formData.isSelfCount == 0 || formData.isSelfCount == 2">
        <el-col :span="9">
          <el-form-item label="增值税专用发票" :required="true">
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
        <el-col :span="9"></el-col>

      </el-row>

      <el-row
        v-if="formData.isSelfCount == 0 || formData.isSelfCount == 2"
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
        v-if="formData.isSelfCount == 0 || formData.isSelfCount == 2"
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
          <el-form-item label="备注" v-if="formData.isDisposable==0">
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

      <el-row type="flex" class="row-bg" justify="space-around">
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
import { list2 } from "@/api/project/ticket";
import crudRate from "@/api/project/rate";
import {
  detail,
  getcode,
  getinfoByUserId,
  edit,
  ownlist,
  check,
} from "@/api/project/list";
import { getInfo } from "@/api/login";
import { Decimal } from "decimal.js";
import crudPlace from "@/api/company/place";
//手机号验证
var phoneVerify = (rule, value, callback) => {
  if (value) {
    var reg = /^[A-Z0-9]{15}$|^[A-Z0-9]{18}$|^[A-Z0-9]{20}$/;
    if (!reg.test(value)) {
      callback(
        new Error("甲方纳税人识别号,一律由15位、18或者20位码(字符型))组成")
      );
    }
    callback();
  }
};
export default {
  name: "ItemsEdit",
  components: {
    uploadSmall,
  },
  data() {
    return {
      selectTipType: "",
      isyuan: "",
      userinfo: {},
      isticket: false,
      isDetail: "0",
      isNone: [],
      defaultProps: {
        children: "children",
        label: "label",
      },
      expandOnClickNode: true,

      projectStatus: 1, //乙方状态
      username: "",
      userId: "",
      industryId: "",
      industryTypes: [],
      industryTypeList: [],

      industryTypes1: [],
      industryTypeList1: [],

      fileName: [],
      fileNamefile: [], //附件
      dialogVisible1: false,
      dialogImageUrl1: "",
      fileNameradio: "1", //开票类型
      natureBusiness: "", //经营范围
      tickettaxvipok: false,
      placename: "",
      isokradio: "2",
      companyTax: "", //甲方纳税人识别号
      owerTax: "", //乙方纳税人识别号
      owntype: "", //乙方行业类型
      owerTaxfee: "", //乙方税率
      placeCodeOptions: "", //渠道商

      formData: {
        disposableRemark:'',
        isDisposableShare: "1",
        disposableShareIsmoney: "0",
        disposableShare: "0",
        disposableFeeIsmoney: "1",
        disposableFee: "0",
        isDisposable: "0", //是否一次性费用
        isRegisterMoney: "0", //是否收取注册服务费
      },
      projectTotalAmount: 0,
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
      //甲方
      purchCompanyOptions: [],
      //乙方
      ownoptions: [],
      //乙方行业类型

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
        projectTrade: [
          {
            required: true,
            message: "项目行业类型不能为空",
          },
        ],
        industryType: [
          {
            required: true,
            message: "请选择乙方行业类型",
            trigger: "change",
          },
        ],
        placeCode: [
          {
            required: true,
            message: "请选择客户",
            trigger: "change",
          },
        ],

        projectOwner: [
          {
            required: true,
            message: "请选择乙方",
            trigger: "change",
          },
        ],

        purchCompany: [
          {
            required: true,
            message: "请选择甲方",
            trigger: "change",
          },
        ],

        purchCompanyTaxid: [
          {
            required: true,
            message: "请输入甲方纳税人识别号",
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

  watch: {
    "formData.industryType": "selectIndustryType",
  },

  mounted() {
    this.getRate();
    this.getlist();
    this.ticketByCode();
    this.getinfoByUserId(); //渠道商
  },
  methods: {
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
    ispublic() {
      if (this.formData.ticketType == 0) {
        this.formData.isSlider = "1";
        this.formData.isSliderOrdinary = "0";
      } else {
        this.formData.isSlider = "0";
        this.formData.isSliderOrdinary = "1";
      }
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
      } else {
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
    check(resmsg) {
      getInfo().then((res) => {
        this.userinfo = res.user;
        let parms = {
          checkReasult: resmsg,
          checkUser: this.userinfo.userName,
          phonenumber: this.userinfo.phonenumber,
          projectCode: this.formData.projectCode,
          projectType: "10",
        };
        check(parms)
          .then((res) => {
            console.log("项目修改完成！");
          })
          .catch((error) => {});
      });
    },
    //计算已开和剩余金额
    ticketByCode() {
      list2({
        projectCode: this.formData.projectCode,
      })
        .then((res) => {
          let arr = res;
          if (Array.isArray(arr) && arr.length > 0) {
            this.isticket = true;
            // this.$modal.msgError('项目金额只能没有开票才能修改!');
            // return;
          } else {
            this.isticket = false;
            this.formData.projectPackageAmount = 0;
            this.formData.projectRemainAmount =
              this.formData.projectTotalAmount;
          }
        })
        .catch((err) => {});
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
    getlist() {
      detail({
        projectCode: this.$cache.local.getJSON("tc-project-code"),
      }).then((response) => {
        this.formData.industryType = "";
        this.formData = response.data;
        
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

        // this.formData.ordinarySpecialTax = JSON.stringify(
        //   this.unlist.ordinarySpecialTax
        // );
        this.formData.ordinaryTax = JSON.stringify(this.formData.ordinaryTax);
       
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

       

        this.$nextTick(function () {
          this.selectTipType = this.$refs.selectTree.selected.label;
        });

        this.projectTotalAmount = this.formData.projectTotalAmount;

        this.isokradio = JSON.stringify(this.formData.placeStatus);
        if (this.formData.fileName) {
          if (this.formData.fileName.indexOf("[") != -1) {
            this.isNone = JSON.parse(this.formData.fileName);
            this.formData.fileName = JSON.parse(this.formData.fileName);
          } else {
            this.isyuan = this.formData.fileName;
          }

          if (Array.isArray(this.formData.fileName)) {
            this.fileNameradio = "2";
            this.fileName = [];
            //如果是图片的话
            for (let j in this.formData.fileName) {
              this.fileName.push({
                name: this.formData.fileName[j],
                url: this.baseImgPath + this.formData.fileName[j],
              });
            }
          } else {
            this.fileNameradio = "1";
          }
        } else {
          this.fileNameradio = "1";
        }
        if (this.formData.isActive) {
          this.projectStatus = parseInt(this.formData.isActive);
        } else {
          this.projectStatus = 1;
        }
      });
    },
    //监听开票内容选择
    filenamer(e) {
      if (e == 1) {
      } else {
        this.formData.fileName = this.isyuan;
      }
    },
    //监听乙方
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

          this.formData.residence = this.ownoptions[i].residence;
          this.formData.privateDepositBank =
            this.ownoptions[i].privateDepositBank;
          this.formData.privateAccountNumber =
            this.ownoptions[i].privateAccountNumber;
          this.formData.publicDepositBank1 =
            this.ownoptions[i].publicDepositBank1;
          this.formData.publicAccountNumber1 =
            this.ownoptions[i].publicAccountNumber1;
          this.formData.accountType = this.ownoptions[i].accountType;
          this.formData.contactPhone = this.ownoptions[i].contactPhone;

          this.formData.selfName = this.ownoptions[i].selfName;
          this.natureBusiness = this.ownoptions[i].natureBusiness;
          this.formData.projectOwnerTaxid = this.ownoptions[i].taxId;
        }
      }
    },
    
    //返回
    resetForm() {
      this.$tab.closeOpenPage({
        path: this.$cache.local.getJSON("Projectedit").url,
      });
    },

    //渠道商接口  记得修改 userid
    getinfoByUserId() {
      getInfo().then((res) => {
        getinfoByUserId({ userId: this.formData.userId }).then((res) => {
          this.placeCodeOptions = res.data;
          for (let i in this.placeCodeOptions) {
            if (this.placeCodeOptions[i].placeCode == this.formData.placeCode) {
              this.isokradio = JSON.stringify(
                this.placeCodeOptions[i].placeStatus
              );
            }
          }
          this.selectIndustryType();
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
            children: [],
            taxRates: b,
          };
          tree.push(obj);
          this.parseTree(industry, obj.children, obj.id);
        }
      }
    },
    //监听行业类型
    selectIndustryType() {
      var rate = this.industryTypeList.find(
        (item) => item.industryId == this.formData.industryType
      );
      if (rate) {
        this.industryId = rate.industryId; //行业类型id
        if (rate.taxRate) {
          this.owerTaxfee =
            new Decimal(rate.taxRate).mul(new Decimal(100)) + "%";
        } else {
          this.owerTaxfee = "";
        }
        let industryType = rate.industryId;
        this.$nextTick(function () {
          this.formData.projectTrade = this.$refs.selectTree.selected.label;
        });
        //this.formData.projectTrade = rate.industryName;//所属行业
        ownlist({
          username: this.formData.projectLeader,
          industryType: industryType,
        })
          .then((res) => {
            this.ownoptions = res;
            let data = this.ownoptions;
            for (let i in data) {
              if (data[i].selfName == this.formData.selfName) {
                this.natureBusiness = data[i].natureBusiness;
                //  this.formData.projectOwnerTaxid = '';
              }
            }
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
        this.getRate();
      }
    },

    //监听开票内容类型
    tickettaxvip(e) {
      console.log(e);
      if (e > 0) {
        this.tickettaxvipok = true;
        this.formData.ticketTax = 3;
      } else {
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
        day +
        " " +
        hour +
        ":" +
        minute +
        ":" +
        second;
      // this.formData.createTime = curTime;
      this.formData.projectTimeStart = curTime;
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
    getfileNameS(data) {
      this.formData.fileName = data;
    },
    onSubmit() {
      if (this.formData.projectTotalAmount <= 0) {
        this.$alert("项目金额必须大于0", "系统提示", {
          confirmButtonText: "确定",

          type: "warning",
        });
        return;
      } else if (this.formData.projectTotalAmount < this.projectTotalAmount) {
        this.$alert("项目金额不能小于原来的项目金额", "系统提示", {
          confirmButtonText: "确定",

          type: "warning",
        });
        return;
      }
      this.$refs["elForm"].validate((valid) => {
        // TODO 提交表单
        if (valid) {
          //如果是附件的话
          if (this.fileNameradio == 2) {
            this.formData.fileName = JSON.stringify(this.formData.fileName);
          }
          this.formData.projectCheckStatus = 0;
          this.formData.projectStatus = 0;
          this.ticketByCode();
          edit(this.formData).then((res) => {
            if (res != undefined) {
              if (res.code === 200) {
                this.$nextTick(function () {
                  this.check("项目修改完成");
                  this.$modal.msgSuccess("项目修改完成");
                  this.$tab
                    .closeOpenPage({
                      path: this.$cache.local.getJSON("Projectedit").url,
                    })
                    .then(() => {
                      this.$tab.refreshPage({
                        path: this.$cache.local.getJSON("Projectedit").url,
                        name: this.$cache.local.getJSON("Projectedit").name,
                      });
                    });
                });
              } else {
                this.$tab
                  .closeOpenPage({
                    path: this.$cache.local.getJSON("Projectedit").url,
                  })
                  .then(() => {
                    this.$tab.refreshPage({
                      path: this.$cache.local.getJSON("Projectedit").url,
                      name: this.$cache.local.getJSON("Projectedit").name,
                    });
                  });
              }
            }
          });
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
</style>
