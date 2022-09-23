<template>
  <div>
        <el-form ref="form" :model="ruleForm" :rules="rules" label-width="auto">
        <!-- 卡1 -->
        <el-row type="flex" class="row-bg " style="margin-top:20px" justify="space-around">
              <el-col :span="9">
          <el-form-item label="客户编码" prop="editPlaceCode">
            <el-input v-model="ruleForm.editPlaceCode" :readonly="true"  />
          </el-form-item>
           <el-form-item label="客户名称" prop="editPlaceName">
            <el-input v-model="ruleForm.editPlaceName" :readonly="confirmEditStatus" @input="placeNews"  />
          </el-form-item>
           <el-form-item label="客户全名" :required="true">
                 <div style="display:flex;align-items:center">
              <el-input style="width:100%;margin-right:-25px" v-model="ruleForm.editPlaceAliasName" :disabled="true" />
              <el-tooltip style="position: relative;left: 30px;font-size:20px" class="item" effect="dark" content="客户名称由客户名称和客户别名组成" placement="top-start">
                  <i class="header-icon el-icon-info"></i>
              </el-tooltip>
              </div>
          
          </el-form-item>
            <el-form-item label="联系人" prop="editPlaceLinkman">
            <el-input v-model="ruleForm.editPlaceLinkman" :readonly="confirmEditStatus" />
          </el-form-item>
          <el-row
           type="flex"
           class="row-bg"
           style="margin-bottom: 20px"
           justify="space-around"
           >
          <el-col :span="20" >
            <div class="bankno">
              个体注册服务费
             </div>
           
          </el-col>
          <el-col :span="4">
            <div></div>
          </el-col>
          </el-row>
          <el-form-item label="状态" prop='editIsRegisterMoney'>
              <el-radio :disabled="confirmEditStatus"  v-model="ruleForm.editIsRegisterMoney" label="0">开启</el-radio>
              <el-radio :disabled="confirmEditStatus"  v-model="ruleForm.editIsRegisterMoney" label="1">关闭</el-radio>
            </el-form-item>
          <el-form-item label="服务费" prop="editRegisterMoney" v-if="ruleForm.editIsRegisterMoney==0">
                <el-input  :readonly="confirmEditStatus" v-model="ruleForm.editRegisterMoney"
                  @change="handleChange"  :min="0"  style="width:100%"
                   onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                   oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                  >
                  <template slot="append">元</template>
                </el-input>
              </el-form-item>
            
               <el-form-item label="是否分润" prop='isSelfShare' v-if="ruleForm.editIsRegisterMoney==0">
              <el-radio v-model="ruleForm.isSelfShare" label="0">是</el-radio>
              <el-radio v-model="ruleForm.isSelfShare" label="1">否</el-radio>
            </el-form-item>
            <el-row v-if="ruleForm.isSelfShare == 0 && ruleForm.editIsRegisterMoney==0" type="flex" justify="flex-end">
              <el-col :span="24">
                <el-form-item label="分润方式" prop="selfShare">
                  <div style="">
                    <el-radio :disabled="confirmEditStatus" @change="selfShareIsmoneys" v-model="ruleForm.selfShareIsmoney" label="0">按定额收取</el-radio>
                    <el-radio :disabled="confirmEditStatus" @change="selfShareIsmoneys" v-model="ruleForm.selfShareIsmoney" label="1">按百分比收取</el-radio>

                    <el-input 
                     :readonly="confirmEditStatus"
                    v-if="ruleForm.selfShareIsmoney == 0" style="width:100%" 
                      :min="0" v-model="ruleForm.selfShare"
                      onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                      oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                      >
                      <template slot="append">元</template>
                    </el-input>
                    <el-input
                     :readonly="confirmEditStatus"
                    v-model="ruleForm.selfShare" v-else style="width:100%"
                      @input="isSelfShares" @change="isSelfShares" :step="0.01" :min="0"
                      :max="100"
                       onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                       oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                      >
                      <template slot="append">%</template>
                    </el-input>
                  </div>

                </el-form-item>
              </el-col>
            </el-row>


         
        </el-col>
        <el-col :span="9">
          <el-form-item label="业务经理" prop="editUserName">
            <!-- <el-input v-model="ruleForm.editUserName" :readonly="true"></el-input> -->
            <el-select
            :disabled="confirmEditStatus"
              style="width: 100%"
              v-model="ruleForm.editUserName"
              filterable
              placeholder="请选择"
            >
              <el-option
                v-for="(item,index) in leaderList"
                :key="index"
                :label="item.value"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
           <el-form-item label="客户别名" prop="editPlaceAlias">
            <el-input v-model="ruleForm.editPlaceAlias" :readonly="confirmEditStatus" @input="placeNews"  />
          </el-form-item>
            <el-form-item label="客户类型" :required="true">
             <el-select 
              :disabled="confirmEditStatus"
             style="width:100%" v-model="ruleForm.customerType" clearable placeholder="请选择客户类型">
                  <el-option v-for="item in customerOptions" :key="item.value" :label="item.label" :value="item.value">
                  </el-option>
                </el-select>
          </el-form-item>
           <el-form-item label="联系方式" prop="editPlaceTel">
            <el-input v-model="ruleForm.editPlaceTel" :readonly="confirmEditStatus" />
          </el-form-item>
        </el-col>
        
        </el-row>

        <el-row
           type="flex"
           class="row-bg"
           style="margin-bottom: 20px"
           justify="space-around"
           >
          <el-col :span="8" >
            <div class="bankno">
              增值税普通发票
             </div>
           
          </el-col>
          <el-col :span="10">
            <div></div>
          </el-col>
        </el-row>
          
       
         <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="9">
          <el-form-item label="状态" :required="true">
             <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSliderOrdinary" label="0">开启</el-radio>
             <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSliderOrdinary" label="1">关闭</el-radio>
          </el-form-item>
        </el-col>
        <el-col :span="9"></el-col>
      </el-row>
        <el-row type="flex" class="row-bg " justify="space-around">
           <el-col :span="9">
               <div v-if="ruleForm.editIsSliderOrdinary==0">
          <el-row type="flex" justify="flex-end">
            <el-col :span="24">
              <el-form-item label="普票税率" prop="editOrdinaryTax">
                <!-- <el-select style="width:100%" :disabled="confirmEditStatus" v-model="ruleForm.editOrdinaryTax" clearable
                  placeholder="请选择">
                  <el-option v-for="item in option" :key="item.value" :label="item.label" :value="item.value">
                  </el-option>
                </el-select> -->
                 <el-input :readonly="true" value="免税">
                 </el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row type="flex" justify="flex-end">
            <el-col :span="24">
              <el-form-item label="普票服务费" prop="editOrdinarySelfFee">
                <div style="">
                  <el-radio @change="handPoxyS" :disabled="confirmEditStatus" v-model="ruleForm.editOrdinaryProxyIsmoney" label="0">按定额收取
                  </el-radio>
                  <el-radio @change="handPoxyS" :disabled="confirmEditStatus" v-model="ruleForm.editOrdinaryProxyIsmoney" label="1">按百分比收取
                  </el-radio>

                  <el-input v-if="ruleForm.editOrdinaryProxyIsmoney == 0" style="width:100%"
                    :readonly="confirmEditStatus"  v-model="ruleForm.editOrdinarySelfFee" 
                    :min="0"
                     onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                     oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                    >
                    <template slot="append">元</template>
                  </el-input>
                  <el-input :readonly="confirmEditStatus" v-else  style="width:100%"
                    v-model="ruleForm.editOrdinarySelfFee" 
                    @input="handleordinaryProxyFeeS"
                    @change="handleordinaryProxyFeeS" :min="0" :max="100"
                     onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                     oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                    >
                    <template slot="append">%</template>
                  </el-input>
                </div>
              </el-form-item>
            </el-col>

          </el-row>
          <el-form-item label="服务费含税" prop="editIsSelfTax">
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSelfTax" label='0'>是</el-radio>
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSelfTax" label='1'>否</el-radio>
          </el-form-item>
          <el-form-item label="价税分离" prop="editIsOrdinaryTax">
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsOrdinaryTax" label='0'>是</el-radio>
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsOrdinaryTax" label='1'>否</el-radio>
          </el-form-item>
          
          <el-form-item label="是否分润" prop="editIsOrdinaryShare">
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsOrdinaryShare" label="0">是</el-radio>
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsOrdinaryShare" label="1">否</el-radio>
          </el-form-item>
          <el-row v-if="ruleForm.editIsOrdinaryShare == 0" type="flex" justify="flex-end">
            <el-col :span="24">
              <el-form-item label="分润方式" prop="editOrdinaryShareIsmoney">
                <div style="">
                  <el-radio @change="hanOrshareS" :disabled="confirmEditStatus" v-model="ruleForm.editOrdinaryShareIsmoney" label="0">按定额收取
                  </el-radio>
                  <el-radio @change="hanOrshareS" :disabled="confirmEditStatus" v-model="ruleForm.editOrdinaryShareIsmoney" label="1">按百分比收取
                  </el-radio>

                  <el-input v-if="ruleForm.editOrdinaryShareIsmoney == 0" style="width:100%" 
                    :readonly="confirmEditStatus"  :min="0" v-model="ruleForm.editOrdinaryShare"
                     onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                     oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                    >
                    <template slot="append">元</template>
                  </el-input>
                  <el-input v-else :readonly="confirmEditStatus" v-model="ruleForm.editOrdinaryShare" 
                    style="width:100%"
                    @input="handleordinaryShareIsmoneyS"
                    @change="handleordinaryShareIsmoneyS"  :min="0" :max="100"
                    onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                    oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                    >
                    <template slot="append">%</template>
                  </el-input>
                </div>

              </el-form-item>
            </el-col>
          </el-row>
          </div>
           </el-col>
           <el-col :span="9">
           </el-col>

        </el-row>


        <el-row
           type="flex"
           class="row-bg"
           style="margin-bottom: 20px"
           justify="space-around"
           >
          <el-col :span="8" >
            <div class="bankno">
              增值税专用发票
             </div>
           
          </el-col>
          <el-col :span="10">
            <div></div>
          </el-col>
        </el-row>



        <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="9">
          <el-form-item label="状态" :required="true">
             <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSlider" label="0">开启</el-radio>
             <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSlider" label="1">关闭</el-radio>
          </el-form-item>
        </el-col>
        <el-col :span="9">
         
        </el-col>

      </el-row>
        <el-row type="flex" class="row-bg " justify="space-around">
           
           <el-col :span="9">
                <div v-if="ruleForm.editIsSlider==0">

                 <el-row type="flex" justify="flex-end">
            <el-col :span="24">
              <el-form-item label="专票税率" prop="editOrdinarySpecialTax">
                <!-- <el-select style="width:100%" :disabled="confirmEditStatus" v-model="ruleForm.editOrdinarySpecialTax"
                  clearable placeholder="请选择">
                  <el-option v-for="item in optiond" :key="item.value" :label="item.label" :value="item.value">
                  </el-option>
                </el-select> -->
                 <el-input :readonly="true" value="3">
                  <template slot="append">%</template>
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row type="flex" justify="flex-end">
            <el-col :span="24">
              <el-form-item label="专票服务费" prop="editSpecialProxyFee">

                <div style="">
                  <el-radio @change="handSpecialS" :disabled="confirmEditStatus" v-model="ruleForm.editSpecialProxyIsmoney" label="0">按定额收取
                  </el-radio>
                  <el-radio @change="handSpecialS" :disabled="confirmEditStatus" v-model="ruleForm.editSpecialProxyIsmoney" label="1">按百分比收取
                  </el-radio>

                  <el-input v-if="ruleForm.editSpecialProxyIsmoney == 0" style="width:100%"
                    :readonly="confirmEditStatus"  v-model="ruleForm.editSpecialProxyFee" 
                    :min="0"
                     onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                     oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                    >
                    <template slot="append">元</template>
                  </el-input>
                  <el-input :readonly="confirmEditStatus" v-else  style="width:100%"
                    v-model="ruleForm.editSpecialProxyFee"
                    @input="handlespecialProxyIsmoneyS"
                    @change="handlespecialProxyIsmoneyS"  :min="0"
                    :max="100"
                     onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                     oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                    >
                    <template slot="append">%</template>
                  </el-input>
                </div>
              </el-form-item>
            </el-col>

          </el-row>

          <el-form-item label="服务费含税" prop="editIsSpecialTax">
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSpecialTax" label='0'>是</el-radio>
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSpecialTax" label='1'>否</el-radio>
          </el-form-item>
          <el-form-item label="价税分离" prop="editIsSpecialSelfTax">
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSpecialSelfTax" label='0'>是</el-radio>
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSpecialSelfTax" label='1'>否</el-radio>
          </el-form-item>
          <el-form-item label="是否分润" prop="editIsSpecialShare">
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSpecialShare" label="0">是</el-radio>
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSpecialShare" label="1">否</el-radio>
          </el-form-item>

          <el-row v-if="ruleForm.editIsSpecialShare == 0" type="flex" justify="flex-end">
            <el-col :span="24">
              <el-form-item label="分润方式" prop="editSpecialShareIsmoney">
                <div style="">
                  <el-radio @change="handMoneyS" :disabled="confirmEditStatus" v-model="ruleForm.editSpecialShareIsmoney" label="0">按定额收取
                  </el-radio>
                  <el-radio @change="handMoneyS" :disabled="confirmEditStatus" v-model="ruleForm.editSpecialShareIsmoney" label="1">按百分比收取
                  </el-radio>

                  <el-input v-if="ruleForm.editSpecialShareIsmoney == 0" style="width:100%" 
                    :min="0" v-model="ruleForm.editSpecialShare" :readonly="confirmEditStatus"
                     onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                     oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                    >
                    <template slot="append">元</template>
                  </el-input>
                  <el-input v-else v-model="ruleForm.editSpecialShare"  style="width:100%"
                    :readonly="confirmEditStatus"
                    @input="handlespecialShareIsmoneyS"
                    @change="handlespecialShareIsmoneyS"  :min="0" :max="100"
                     onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                     oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                    >
                    <template slot="append">%</template>
                  </el-input>
                </div>

              </el-form-item>
            </el-col>
          </el-row>
          </div>
          </el-col>
           <el-col :span="9">
            
          </el-col>

        </el-row>

        <el-row
           type="flex"
           class="row-bg"
           style="margin-bottom: 20px"
           justify="space-around"
           >
          <el-col :span="8" >
            <div class="bankno">
              一次性费用
             </div>
           
          </el-col>
          <el-col :span="10">
            <div></div>
          </el-col>
        </el-row>


        <el-row type="flex" class="row-bg " justify="space-around">
            <el-col :span="9">
              <el-form-item label="状态" prop='editIsDisposable'>
              <el-radio  :disabled="confirmEditStatus" v-model="ruleForm.editIsDisposable" label="0">开启</el-radio>
              <el-radio  :disabled="confirmEditStatus" v-model="ruleForm.editIsDisposable" label="1">关闭</el-radio>
            </el-form-item>
            <el-row type="flex" justify="flex-end" v-if="ruleForm.editIsDisposable==0">
              <el-col :span="24">
                <el-form-item label="费用" prop="editDisposableFee">
                  <div>
                    <el-radio  :disabled="confirmEditStatus" @change="handleDis" v-model="ruleForm.editDisposableFeeIsmoney" label="0">按定额收取</el-radio>
                    <el-radio  :disabled="confirmEditStatus" @change="handleDis" v-model="ruleForm.editDisposableFeeIsmoney" label="1">按百分比收取</el-radio>

                    <el-input 
                    :readonly="confirmEditStatus"
                    v-if="ruleForm.editDisposableFeeIsmoney == 0" style="width:100%" 
                      v-model="ruleForm.editDisposableFee"  :min="0"
                      onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                      oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                      >
                      <template slot="append">元</template>
                    </el-input>
                    <el-input
                    :readonly="confirmEditStatus"
                    v-else  style="width:100%" v-model="ruleForm.editDisposableFee"
                      @input="disposableIsmoney" @change="disposableIsmoney"  :min="0"
                      :max="100"
                       onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                       oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                      >
                      <template slot="append">%</template>
                    </el-input>
                  </div>
                </el-form-item>
                <el-form-item label="备注" v-if="ruleForm.editIsDisposable==0">
                <el-input
                maxlength="50"
                show-word-limit
                type="textarea"
                :rows="2"
                placeholder="请输入备注"
                :disabled="confirmEditStatus"
                v-model="ruleForm.disposableRemark"
              >
              </el-input>
              </el-form-item>
              </el-col>

            </el-row>
            <el-form-item label="是否分润" prop="editIsDisposableShare" v-if="ruleForm.editIsDisposable==0">
              <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsDisposableShare" label="0">是</el-radio>
              <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsDisposableShare" label="1">否</el-radio>
            </el-form-item>
            <el-row v-if="ruleForm.editIsDisposableShare == 0" type="flex" justify="flex-end">
              <el-col :span="24">
                <el-form-item label="分润方式" prop="editDisposableShare">
                  <div style="">
                    <el-radio :disabled="confirmEditStatus" @change="isdisshare" v-model="ruleForm.editDisposableShareIsmoney" label="0">按定额收取</el-radio>
                    <el-radio :disabled="confirmEditStatus" @change="isdisshare" v-model="ruleForm.editDisposableShareIsmoney" label="1">按百分比收取</el-radio>

                    <el-input 
                    :disabled="confirmEditStatus"
                    v-if="ruleForm.editDisposableShareIsmoney == 0" style="width:100%" 
                      :min="0" v-model="ruleForm.editDisposableShare"
                      onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                      oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                      >
                      <template slot="append">元</template>
                    </el-input>
                    <el-input 
                    :disabled="confirmEditStatus"
                    v-model="ruleForm.editDisposableShare" v-else style="width:100%"
                      @input="disShare" @change="disShare" :step="0.01" :min="0"
                      :max="100"
                       onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                       oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                      >
                      <template slot="append">%</template>
                    </el-input>
                  </div>

                </el-form-item>
              </el-col>
            </el-row>
            </el-col>
            <el-col :span="9">
           
            </el-col>

      </el-row>



         <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="8">

        </el-col>
        <el-col :span="8" class="flexs">
           <el-button type="danger" @click="backAgos">关闭</el-button>
           <el-button type="primary" @click="confirmEdits" v-if="confirmEditStatus">编辑</el-button>
           <el-button type="primary" @click="confirmEdit"  v-else>提交</el-button>
         </el-col>
        <el-col :span="8">

        </el-col>
      </el-row>
     
        
          
         


 </el-form>
  </div>
</template>
<script>
// 手机号码验证
var contactPhone = (rule, value, callback) => {
  if (!value) {
    return callback(new Error('手机号不能为空'))
  } else {
    const reg = /^1[3|4|5|7|8][0-9]\d{8}$/
    if (reg.test(value)) {
      callback()
    } else {
      return callback(new Error('请输入11位数字'))
    }
  }
};
// 数字验证
var numCheck = (rule, value, callback) => {
  if (!value) {
    return callback(new Error('个体户注册服务费不能为空'))
  } else {
    const reg = /[^\d]/g;
    if (!reg.test(value)) {
      callback()
    } else {
      return callback(new Error('请输入正整数'))
    }
  }
};
import crudPlace from "@/api/tc-api/place/place";
import agencyfee from "@/api/tc-api/place/agencyfee";
import { getAllUser } from '@/api/system/user';
import '@riophae/vue-treeselect/dist/vue-treeselect.css'

export default {
  name: "Edit",

  data() {
    return {
       customerOptions:[
        {
        value:'0',
        label:'渠道'
        },
         {
        value:'1',
        label:'直客'
        }
      ],
      editSpecialProxyFee: '2',
      editRegisterMoney: '2',
      editSpecialInvoice13: '2',
      editSpecialInvoice6: '2',
      editSpecialShare: '2',
      editOrdinaryShare: '2',

      registerMoney: '2',
      specialInvoice13: '2',
      specialInvoice6: '2',
      specialProxyMoney: '2',
      specialProxyFee: '2',

      specialShare: '2',
      ordinaryShare: '2',
      radio: '1',
      confirmEditStatus: true, //编辑页面编辑按钮
      tabelData: [],
      editVisible: false,
      multipleSelection: [],
      addVisible: false,
      userLeaders: [],
      status: [],
      placeStatus: [], // 多选时使用
      is_special_tax: true,
      is_ordinary_tax: true,
      value: '正常',
      values: '0',
      special_invoice_6: 0,
      special_invoice_13: 6.1,
      ordinary_proxy_fee: 0,
      Include: '1',
      users: [],
      isCheck: true,
      checkVisible: false,
      currentRow: {}, selectIndex: '', placeCode: '', urlHistory: '',
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 个体商户表格数据
      employedList: [],
      // 弹出层标题
      title: "",
      titles: '',
      titleh: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        placeAliasName: null,
        placeName: null,
        userName: null,
        status: '',
      },
      ruleForm: {
        disposableRemark:'',
        isRegisterMoney:'0',
        selfShareIsmoney:'0',
        isSelfShare:'1',
        selfShare:'0',
        customerType:'0',
        isSliderOrdinary: '0',
        isSlider: '0',//专票滑块
        isSelfTax: '1',     //普票价税分离
        isSpecialSelfTax: '1',  //专票价税分离
        ordinaryTax: '0',//普票税率
        ordinarySpecialTax: '0.03',//专票税率

        placeAlias: '',//客户别名
        placeCode: '',
        placeName: '',
        placeLinkman: '',
        placeTel: '',
        userId: 26,
        userName: '',

        specialShareMoney: '',
        specialShare: '',//专票分润
        ordinaryShareMoney: '',
        ordinaryShare: '',//普票分润

        specialProxyMoney: 0,
        specialProxyFee: 0,

        specialInvoice13Money: 0, //  专票 13 元
        specialInvoice6Money: 0, //  专票6 元
        specialInvoice6: 0,   //专票 6 （%）
        specialInvoice13: 0,  //专票13 （%）
        specialSelfFee: 0,
        isSpecialTax: '1',//是否含税-专票
        ordinaryProxyMoney: 0,//普票平台服务费(元）
        registerMoney: 0,
        registerMoney: 0, //普票平台服务费(%）
        isOrdinaryTax: '1',//是否含税-普票

        ordinaryProxyIsmoney: '0',
        isOrdinaryShare: '1',
        ordinaryShareIsmoney: '0',
        specialProxyIsmoney: '0',
        isSpecialShare: '1',
        specialShareIsmoney: '0',


        //编辑参数
        editDisposableFeeIsmoney:'1',
        editIsDisposable:'0',
        editDisposableFee:'0',
        editIsRegisterMoney:'0',
        editIsDisposableShare:'1',
        editDisposableShareIsmoney:'0',
        editDisposableShare:'0',
        
        editPlaceAliasName:'',
        editOrdinaryProxyIsmoney: '0',
        editIsOrdinaryShare: '1',
        editOrdinaryShareIsmoney: '0',
        editSpecialProxyIsmoney: '0',
        editIsSpecialShare: '1',
        editSpecialShareIsmoney: '0',
        editIsSlider: '0',//专票滑块
        editIsSliderOrdinary: '0',
        editIsRegisterMoney:'0',

        editIsSelfTax: '0',     //普票价税分离
        editIsSpecialSelfTax: '0',  //专票价税分离
        editPlaceId: '',
        editPlaceCode: '',
        editPlaceName: '',
        editPlaceAlias: '',//客户别名
        editPlaceLinkman: '',
        editPlaceTel: '',
        editUserId: '',
        editUserName: '',

        editAgencyFeeId: '',
        editSpecialInvoice6: '',
        editSpecialInvoice13: '',
        editSpecialInvoice6Money: '',
        editSpecialInvoice13Money: '',
        editSpecialSelfFee: '',
        editIsSpecialTax: '',//是否含税-专票
        editSpecialProxyMoney: '',
        editSpecialProxyFee: '',

        editOrdinaryTax: '',
        editOrdinarySpecialTax: '',

        editSpecialShareMoney: '',
        editSpecialShare: '',//专票分润
        editOrdinaryShareMoney: '',
        editOrdinaryShare: '',//普票分润

        editOrdinarySelfFee: '',
        editRegisterMoney: '',
        editOrdinaryProxyMoney: '',
        editIsOrdinaryTax: '',//是否含税-普票
      },
      leaderList:[],
      deptId:'',
      option: [
        {
          value: '0',
          label: '免税'
        },
        // {
        //   value: '0.01',
        //   label: '1%'
        // }, {
        //   value: '0.03',
        //   label: '3%'
        // },
      ],
      optiond: [
        // {
        //   value: '0.01',
        //   label: '1%'
        // }, 
        {
          value: '0.03',
          label: '3%'
        },
      ],
      options: [
        {
          value: 0,
          label: '正常'
        },
        {

          value: 2,
          label: '休眠'
        },
        {

          value: 1,
          label: '欠费',
        },
      ],
      rules: {
        selfShareIsmoney:[{
           required: true, message: '请选择个体注册服务费分润方式', trigger: 'change'   
        }],
        isSelfShare:[{
           required: true, message: '请选择个体注册服务是否分润', trigger: 'change'
        }],
        selfShare:[{
            required: true, message: '请输入个体注册服务费分润费', trigger: 'blur'
        }],
        isSpecialShare: [{
          required: true, message: '请选择专票是否分润', trigger: 'change'
        }],
        editIsSpecialShare: [{
          required: true, message: '请选择专票是否分润', trigger: 'change'
        }],
        editIsOrdinaryShare: [{
          required: true, message: '请选择普票是否分润', trigger: 'change'
        }],
        isOrdinaryShare: [{
          required: true, message: '请选择普票是否分润', trigger: 'change'
        }],
        editSpecialShareIsmoney: [{
          required: true, message: '请选择专票分润方式', trigger: 'change'
        }],
        specialShareIsmoney: [{
          required: true, message: '请选择专票分润方式', trigger: 'change'
        }],
        ordinaryShareIsmoney: [{
          required: true, message: '请选择普票分润方式', trigger: 'change'
        }],
        editOrdinaryShareIsmoney: [{
          required: true, message: '请选择普票分润方式', trigger: 'change'
        }],
        isSelfTax: [
          { required: true, message: '请选择普票价税分离', trigger: 'change' }
        ],

        editIsSelfTax: [
          { required: true, message: '请选择普票价税分离', trigger: 'change' }
        ],
        isSpecialSelfTax: [
          { required: true, message: '请选择专票价税分离', trigger: 'change' }
        ],
        editIsSpecialSelfTax: [
          { required: true, message: '请选择专票价税分离', trigger: 'change' }
        ],
        placeCode: [
          { required: true, message: '请输入编号', trigger: 'blur' }
        ],
        placeAlias: [
          { required: true, message: '请输入客户别名', trigger: 'blur' }
        ],
        placeName: [
          { required: true, message: '请输入客户名称', trigger: 'blur' }
        ],
        placeLinkman: [
          { required: true, message: '请输入联系人', trigger: 'blur' }
        ],
        placeTel: [
          { validator: contactPhone, required: true, trigger: 'blur' }
        ],
        ordinaryTax: [
          { required: true, message: '请选择普票税率', trigger: 'change' }
        ],
        ordinarySpecialTax: [
          { required: true, message: '请选择专票税率', trigger: 'change' }
        ],
        editOrdinaryTax: [
          { required: true, message: '请选择普票税率', trigger: 'change' }
        ],
        editOrdinarySpecialTax: [
          { required: true, message: '请选择专票税率', trigger: 'change' }
        ],
        registerMoney: [
          { required: true, message: '请输入普票服务费', trigger: 'blur' }
        ],
        editOrdinarySelfFee: [
          { required: true, message: '请输入普票服务费', trigger: 'blur' }
        ],
        ordinaryShare: [
          { required: true, message: '请输入普票分润费', trigger: 'blur' }
        ],
        editOrdinaryShare: [
          { required: true, message: '请输入普票分润费', trigger: 'blur' }
        ],
        specialProxyFee: [
          { required: true, message: '请输入专票服务费', trigger: 'blur' }
        ],
        editSpecialProxyFee: [
          { required: true, message: '请输入专票服务费', trigger: 'blur' }
        ],
        specialShare: [
          { required: true, message: '请输入专票分润费', trigger: 'blur' }
        ],
        editSpecialShare: [
          { required: true, message: '请输入专票分润费', trigger: 'blur' }
        ],


        editPlaceAlias: [
          { required: true, message: '请输入客户别名', trigger: 'blur' }
        ],
        editPlaceName: [
          { required: true, message: '请输入客户名', trigger: 'blur' }
        ],
        editPlaceLinkman: [
          { required: true, message: '请输入联系人', trigger: 'blur' }
        ],
        editPlaceTel: [
          { validator: contactPhone, required: true, trigger: 'blur' }
        ],



        editUserName: [
          { required: true, message: '请选择业务经理', trigger: 'change' }
        ],



        specialSelfFee: [
          { validator: numCheck, required: true, trigger: 'blur' }
        ],
        editSpecialSelfFee: [
          { validator: numCheck, required: true, trigger: 'blur' }
        ],
        isSpecialTax: [
          { required: true, message: '请选择是否含税', trigger: 'change' }
        ],
        
        
        editIsRegisterMoney: [
          { message: '请选择是否开启个体户注册服务费', required: true, trigger: 'blur' }
        ],
        editDisposableFeeIsmoney: [
          { message: '一次性费用是否定额', required: true, trigger: 'blur' }
        ],
        editIsDisposable: [
          { message: '请选择是否开启一次性费用', required: true, trigger: 'blur' }
        ],
        editDisposableFee: [
          { message: '一次性费用不能为空', required: true, trigger: 'blur' }
        ],
        

        editIsDisposableShare:[{
          required: true, trigger: 'bulr'   
        }],
        editDisposableShareIsmoney:[{
          required: true
        }],
        editDisposableShare:[{
          required: true,message: '一次性费用分润费不能为空'
        }],
        
        registerMoney: [
          { message: '请输入个体户注册服务费', required: true, trigger: 'blur' }
        ],
        editRegisterMoney: [
          { message: '请输入个体户注册服务费', required: true, trigger: 'blur' }
        ],

        isOrdinaryTax: [
          { required: true, message: '请选择是否含税', trigger: 'change' }
        ],
      },
      queryTypeOptions: [
        { key: 'placeName', display_name: '客户名称' },
        { key: 'placeStatus', display_name: '客户状态' },
        { key: 'nickName', display_name: '业务经理' }
      ],
      // 表单参数
      form: {},
      // 表单校验
      
    };
  },
 mounted() {
     this.getlist();
 },
 methods: {
     //获取业务经理
     getLeader(){
     
        //  this.deptId=res[0].deptId;
         getAllUser().then((res)=>{
         this.leaderList=[];
         let list=res;
         list.map((item)=>{
          if(item.userId==this.ruleForm.editUserId){
            return this.deptId=item.deptId;
          }
         });
         list.map((item)=>{
          if(item.deptId==this.deptId){
                this.leaderList.push({
                  value:item.nickName,
                  label:item.nickName,
                 })
             }
         });
         
      })
     },
     getlist(){
      this.$modal.loading("正在加载数据，请稍后...");
      agencyfee.selectFeeByCode({ placeCode: this.$cache.local.getJSON("tc-placelist").placeCode }).then(res => {
      this.$modal.closeLoading();
      let item=this.$cache.local.getJSON("tc-placelist");
      this.ruleForm.editPlaceId = item.placeId;
      this.ruleForm.editPlaceCode = item.placeCode;
      this.ruleForm.editPlaceName = item.placeName;
      this.ruleForm.editPlaceAlias = item.placeAlias;//客户别名
      this.ruleForm.editPlaceLinkman = item.placeLinkman;
      this.ruleForm.editPlaceTel = item.placeTel;
      this.ruleForm.editUserId = item.userId;
      this.ruleForm.editUserName = item.userName;
      this.ruleForm.editPlaceAliasName=item.placeAliasName;
      this.ruleForm.customerType=JSON.stringify(item.customerType);
        
        this.ruleForm.disposableRemark=res.disposableRemark;
        this.ruleForm.selfShareIsmoney=JSON.stringify(res.selfShareIsmoney);
        this.ruleForm.isSelfShare=JSON.stringify(res.isSelfShare);
        this.ruleForm.selfShare=JSON.stringify(res.selfShare);
        this.ruleForm.editIsSliderOrdinary=JSON.stringify(res.isSliderOrdinary); 
        this.ruleForm.editIsSlider=JSON.stringify(res.isSlider); 
        this.ruleForm.editOrdinaryProxyIsmoney = JSON.stringify(res.ordinaryProxyIsmoney);
        this.ruleForm.editIsOrdinaryShare = JSON.stringify(res.isOrdinaryShare);
        this.ruleForm.editOrdinaryShareIsmoney = JSON.stringify(res.ordinaryShareIsmoney);
        this.ruleForm.editSpecialProxyIsmoney = JSON.stringify(res.specialProxyIsmoney);
        this.ruleForm.editIsSpecialShare = JSON.stringify(res.isSpecialShare);
        this.ruleForm.editSpecialShareIsmoney = JSON.stringify(res.specialShareIsmoney);
        this.ruleForm.editIsSelfTax = JSON.stringify(res.isSelfTax),
        this.ruleForm.editIsSpecialSelfTax = JSON.stringify(res.isSpecialSelfTax),
        this.ruleForm.editAgencyFeeId = res.agencyFeeId;
        this.ruleForm.editSpecialSelfFee = res.specialSelfFee;
        this.ruleForm.editIsSpecialTax = res.isSpecialTax;
      
        this.ruleForm.editOrdinarySelfFee = res.ordinarySelfFee;
        this.ruleForm.editRegisterMoney = res.registerMoney;
        this.ruleForm.editIsOrdinaryTax = res.isOrdinaryTax;
        this.ruleForm.editSpecialProxyFee = res.specialProxyFee;
        this.ruleForm.editSpecialShare = res.specialShare;
        this.ruleForm.editOrdinaryShare = res.ordinaryShare;
        this.ruleForm.editOrdinarySpecialTax = JSON.stringify(res.ordinarySpecialTax);
        this.ruleForm.editOrdinaryTax = JSON.stringify(res.ordinaryTax);

        this.ruleForm.editDisposableFeeIsmoney=JSON.stringify(res.disposableFeeIsmoney);
        this.ruleForm.editIsDisposable=JSON.stringify(res.isDisposable);
        this.ruleForm.editDisposableFee=res.disposableFee;
        this.ruleForm.editIsRegisterMoney=JSON.stringify(res.isRegisterMoney);
        this.ruleForm.editIsDisposableShare=JSON.stringify(res.isDisposableShare);
        this.ruleForm.editDisposableShareIsmoney=JSON.stringify(res.disposableShareIsmoney);
        this.ruleForm.editDisposableShare=res.disposableShare;
       


        if (this.ruleForm.editIsOrdinaryTax == true) {
          this.ruleForm.editIsOrdinaryTax = "0"
        } else if (this.ruleForm.editIsOrdinaryTax == false) {
          this.ruleForm.editIsOrdinaryTax = "1"
        }
        if (this.ruleForm.editIsSpecialTax == true) {
          this.ruleForm.editIsSpecialTax = "0"
        } else if (this.ruleForm.editIsSpecialTax == false) {
          this.ruleForm.editIsSpecialTax = "1"
        }
        this.getLeader();
      
      }).catch((error)=>{
        this.$modal.closeLoading();
      })
     },
     placeNews(){
     if(this.ruleForm.editPlaceName==null){
      this.ruleForm.editPlaceName='';
     }
     if(this.ruleForm.editPlaceAlias==null){
      this.ruleForm.editPlaceAlias='';
     }
     this.ruleForm.editPlaceAliasName=this.ruleForm.editPlaceName+this.ruleForm.editPlaceAlias;
    },
    backAgos(){
        this.$tab.closeOpenPage({path:'/tc-business/place/placelist'});
    },
    backAgo(){
        this.$tab.closeOpenPage({path:'/tc-business/place/placelist'}).then(() => {
           this.$tab.refreshPage({path:'/tc-business/place/placelist',name:'Placelist'});

        })
    },
     //一次性分润
     isdisshare(e){
      if(e=='1'){
      if (this.ruleForm.editDisposableShareIsmoney == '1') {
      if ( this.ruleForm.editDisposableShare > 100) {
          this.ruleForm.editDisposableShare = '100';
        }
      }
      }

    },
    //一次性分润
    disShare(e){
      if (this.ruleForm.editDisposableShareIsmoney == '1') {
      if ( e > 100) {
          this.ruleForm.editDisposableShare = '100';
        }
      }
    },
     //一次性费用
     disposableIsmoney(e) {
      if (this.ruleForm.editDisposableFeeIsmoney == '1') {
        if (e > 100) {
          this.ruleForm.editDisposableFee = '100';
        }
      }
    },
    //一次性费用
    handleDis(e){
      if(e=='1'){
        if (this.ruleForm.editDisposableFeeIsmoney == '1') {
        if ( this.ruleForm.editDisposableFee > 100) {
          this.ruleForm.editDisposableFee = '100';
        }
      }
      }

    },
     selfShareIsmoneys(e){
       if (e == '1') {
        if (this.ruleForm.selfShareIsmoney == '1') {
          if (this.ruleForm.selfShare > 100) {
            this.ruleForm.selfShare = '100';
          }
        }
      }
    },
     isSelfShares(e){
       if (this.ruleForm.selfShareIsmoney == '1') {
        if (e > 100) {
          this.ruleForm.selfShare = '100';
        }
      }
    },
    handlespecialShareIsmoneyS(e) {
      if (this.ruleForm.editSpecialShareIsmoney == '1') {
        if (e > 100) {
          this.ruleForm.editSpecialShare = '100';
        }
      }
    },
    handlespecialProxyIsmoneyS(e) {
      if (this.ruleForm.editSpecialProxyIsmoney == '1') {
        if (e > 100) {
          this.ruleForm.editSpecialProxyFee = '100';
        }
      }
    },
    handleordinaryShareIsmoneyS(e) {
      if (this.ruleForm.editOrdinaryShareIsmoney == '1') {
        if (e > 100) {
          this.ruleForm.editOrdinaryShare = '100';
        }
      }
    },
    handleordinaryProxyFeeS(e) {
      if (this.ruleForm.editOrdinaryProxyIsmoney == '1') {
        if (e > 100) {
          this.ruleForm.editOrdinarySelfFee = '100';
        }
      }
    },

  
    
    handPoxyS(e) {
      if (e == '1') {
        if (this.ruleForm.editOrdinaryProxyIsmoney == '1') {
          if (this.ruleForm.editOrdinarySelfFee > 100) {
            this.ruleForm.editOrdinarySelfFee = '100';
          }
        }
      }
    },
    hanOrshareS(e) {
      if (e == '1') {
        if (this.ruleForm.editOrdinaryShareIsmoney == '1') {
          if (this.ruleForm.editOrdinaryShare > 100) {
            this.ruleForm.editOrdinaryShare = '100';
          }
        }
      }
    },
    handSpecialS(e) {
      if (e == '1') {
        if (this.ruleForm.editSpecialProxyIsmoney == '1') {
          if (this.ruleForm.editSpecialProxyFee > 100) {
            this.ruleForm.editSpecialProxyFee = '100';
          }
        }
      }
    },
    handMoneyS(e) {
      if (e == '1') {
        if (this.ruleForm.editSpecialShareIsmoney == '1') {
          if (this.ruleForm.editSpecialShare > 100) {
            this.ruleForm.editSpecialShare = '100';
          }
        }
      }
    },
     handleChange(value) {
      console.log(value);
    },
    
     //编辑状态
    confirmEdits() {
      this.confirmEditStatus = false;
    },
    //修改提交
    confirmEdit() {
      this.$refs['form'].validate((valid) => {
       if (valid) {
         if (this.ruleForm.editIsOrdinaryTax == "0") {
            this.ruleForm.editIsOrdinaryTax = true;
          } else if (this.ruleForm.editIsOrdinaryTax == '1') {
            this.ruleForm.editIsOrdinaryTax = false;
          }
          if (this.ruleForm.editIsSpecialTax == "0") {
            this.ruleForm.editIsSpecialTax = true;
          } else if (this.ruleForm.editIsSpecialTax == "1") {
            this.ruleForm.editIsSpecialTax = false;
          }


          let params = {
            agencyFeeId: this.ruleForm.editAgencyFeeId,
            placeId: this.ruleForm.editPlaceId,
            placeCode: this.ruleForm.editPlaceCode,
            placeName: this.ruleForm.editPlaceName,
            placeAlias: this.ruleForm.editPlaceAlias,//客户别名
            placeLinkman: this.ruleForm.editPlaceLinkman,
            placeTel: this.ruleForm.editPlaceTel,
            userId: this.ruleForm.editUserId,
            userName: this.ruleForm.editUserName,
            customerType:this.ruleForm.customerType,

            isOrdinaryTax: this.ruleForm.editIsOrdinaryTax,
            ordinaryProxyIsmoney: this.ruleForm.editOrdinaryProxyIsmoney,
            isOrdinaryShare: this.ruleForm.editIsOrdinaryShare,
            ordinaryShareIsmoney: this.ruleForm.editOrdinaryShareIsmoney,
            specialProxyIsmoney: this.ruleForm.editSpecialProxyIsmoney,
            isSpecialShare: this.ruleForm.editIsSpecialShare,
            specialShareIsmoney: this.ruleForm.editSpecialShareIsmoney,

            specialSelfFee: this.ruleForm.editSpecialSelfFee,
            isSpecialTax: this.ruleForm.editIsSpecialTax,

            registerMoney: this.ruleForm.editRegisterMoney,

            //specialProxyMoney: this.ruleForm.editSpecialProxyMoney,
            specialProxyFee: this.ruleForm.editSpecialProxyFee,

            ordinarySelfFee: this.ruleForm.editOrdinarySelfFee,
            //ordinaryProxyMoney: this.ruleForm.editOrdinaryProxyMoney, //服务费元

            ordinaryTax: '0',
            ordinarySpecialTax: '0.03',

            specialShare: this.ruleForm.editSpecialShare,
            // specialShareMoney:this.ruleForm.editSpecialShareMoney,
            ordinaryShare: this.ruleForm.editOrdinaryShare,
            //ordinaryShareMoney:this.ruleForm.editOrdinaryShareMoney,

            isOrdinaryTax: this.ruleForm.editIsOrdinaryTax,
            isSelfTax: this.ruleForm.editIsSelfTax,
            isSpecialSelfTax: this.ruleForm.editIsSpecialSelfTax,
            isSlider: this.ruleForm.editIsSlider,
            isSliderOrdinary: this.ruleForm.editIsSliderOrdinary,
            selfShareIsmoney: this.ruleForm.selfShareIsmoney,
            isSelfShare: this.ruleForm.isSelfShare,
            selfShare:this.ruleForm.selfShare,
           
            isRegisterMoney:this.ruleForm.editIsRegisterMoney,
            disposableFeeIsmoney: this.ruleForm.editDisposableFeeIsmoney,
            isDisposable: this.ruleForm.editIsDisposable,
            disposableFee:this.ruleForm.editDisposableFee,
            isDisposableShare:this.ruleForm.editIsDisposableShare,
            disposableShareIsmoney:this.ruleForm.editDisposableShareIsmoney,
            disposableShare:this.ruleForm.editDisposableShare,
            disposableRemark:this.ruleForm.disposableRemark
          }
          crudPlace.editPlace(params).then((res) => {
            if (res.id == 0) {
              this.$message({
                message: res.message,
                type: 'success',
              });
            } else {
              this.$message({
                message: res.message,
                type: 'warning',
              });
            }
            this.backAgo();
            })
        } else {
           this.$alert('请填写完整', '系统提示', {
              confirmButtonText: '确定',
            
              type: 'warning'
           });
        }
      })
    },


    

  },
};
</script>
<style lang="scss" scoped>
.btnDiv {

  height: 50px;
  margin-top: 20px;

  ::v-deep .el-button {
    margin: 0 10px 0 10px;
  }
}

// 改变input框字体颜色
::v-deep .el-input__inner {
  background-color: transparent !important;
  color: black;
  border-color: none !important;
}

::v-deep .is-disabled {
  color: black;

}

::v-deep .el-radio__input.is-disabled+span.el-radio__label {
  background-color: transparent !important;
  color: black !important;
}

::v-deep .el-input.is-disabled .el-input__inner {
  color: black !important;
}

::v-deep .el-radio {
  background-color: #FFFFFF !important;
}

::v-deep .el-radio__input.is-checked .el-radio__inner {
  border-color: #1890ff !important;
  background: #1890ff !important;
}

::v-deep .el-dialog:not(.is-fullscreen) {
  margin-top: 1vh !important;
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

