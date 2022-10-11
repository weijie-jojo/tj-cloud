<template>
  <div>
    <el-form ref="form" :model="ruleForm" :rules="rules" label-width="auto">
      <el-row type="flex" class="row-bg " style="margin-top:20px" justify="space-around">
        <el-col :span="9">
          <el-form-item label="客户编号" prop="placeCode">
            <el-input v-model="ruleForm.placeCode" :readonly="true" />
          </el-form-item>
          <el-form-item label="客户名称" prop="placeName">
            <el-input v-model="ruleForm.placeName" @input="placeNews" />
          </el-form-item>
          <el-form-item label="客户全名" :required="true">
                 <div style="display:flex;align-items:center">
              <el-input style="width:100%;margin-right:-25px" v-model="placeAliasName" :disabled="true" />
              <el-tooltip style="position: relative;left: 30px;font-size: 20px;" class="item" effect="dark" content="客户名称由客户名称和客户别名组成" placement="top-start">
                  <i class="header-icon el-icon-info"></i>
              </el-tooltip>
              </div>
          
          </el-form-item>
          <el-form-item label="联系人" prop="placeLinkman">
            <el-input v-model="ruleForm.placeLinkman" />
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

          <el-form-item label="状态" prop='isRegisterMoney'>
              <el-radio v-model="ruleForm.isRegisterMoney" label="0">开启</el-radio>
              <el-radio v-model="ruleForm.isRegisterMoney" label="1">关闭</el-radio>
            </el-form-item>

          <el-form-item label="服务费" prop="registerMoney" v-if="ruleForm.isRegisterMoney==0">
            <el-input
             v-model="ruleForm.registerMoney" @change="handleChange"  :min="0"
              style="width:100%"
             onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
             oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]' 
              >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>

         <el-form-item label="是否分润" prop='isSelfShare' v-if="ruleForm.isRegisterMoney==0">
              <el-radio v-model="ruleForm.isSelfShare" label="0">是</el-radio>
              <el-radio v-model="ruleForm.isSelfShare" label="1">否</el-radio>
            </el-form-item>
            <el-row v-if="ruleForm.isSelfShare == 0 && ruleForm.isRegisterMoney==0" type="flex" justify="flex-end">
              <el-col :span="24">
                <el-form-item label="分润方式" prop="selfShare">
                  <div style="">
                    <el-radio @change="selfShareIsmoneys" v-model="ruleForm.selfShareIsmoney" label="0">按定额收取</el-radio>
                    <el-radio @change="selfShareIsmoneys" v-model="ruleForm.selfShareIsmoney" label="1">按百分比收取</el-radio>

                    <el-input v-if="ruleForm.selfShareIsmoney == 0" style="width:100%" 
                      :min="0" v-model="ruleForm.selfShare"
                      onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                      oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                      >
                      <template slot="append">元</template>
                    </el-input>
                    <el-input v-model="ruleForm.selfShare" v-else style="width:100%"
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
          <el-form-item label="业务经理" :required="true">
            <el-select v-hasPermi="['place:list:add1']"
              style="width: 100%"
              v-model="ruleForm.userName"
              filterable
              placeholder="请选择"
              @change="editUserid"
            >
              <el-option
                v-for="(item,index) in leaderList"
                :key="index"
                :label="item.value"
                :value="item.value"
                
              >
              </el-option>
            </el-select>
            <el-input   v-model="ruleForm.userName" :readonly="true"></el-input>
           
          </el-form-item>
          <el-form-item label="客户别名" prop="placeAlias">
            <el-input v-model='ruleForm.placeAlias' @input="placeNews"/>
          </el-form-item>
          <el-form-item label="客户类型" :required="true">
             <el-select style="width:100%" v-model="ruleForm.customerType" clearable placeholder="请选择客户类型">
                  <el-option v-for="item in customerOptions" :key="item.value" :label="item.label" :value="item.value">
                  </el-option>
                </el-select> 
          </el-form-item>
          <el-form-item label="联系方式" prop="placeTel">
            <el-input v-model="ruleForm.placeTel" />
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
            <el-radio v-model="ruleForm.isSliderOrdinary" label="0">开启</el-radio>
            <el-radio v-model="ruleForm.isSliderOrdinary" label="1">关闭</el-radio>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          
        </el-col>

      </el-row>
      <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="9">
          <div v-if="ruleForm.isSliderOrdinary == 0">
            <el-row type="flex" justify="flex-end">
              <el-col :span="24">
                <el-form-item label="普票税率" :required="true">
                  <!-- <el-select style="width:100%" v-model="ruleForm.ordinaryTax" clearable placeholder="请选择">
                  <el-option v-for="item in option" :key="item.value" :label="item.label" :value="item.value">
                  </el-option>
                </el-select> -->
                  <el-input :readonly="true" value="免税">
                    <template slot="append">%</template>
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row type="flex" justify="flex-end">
              <el-col :span="24">
                <el-form-item label="普票服务费" prop="ordinarySelfFee">

                  <div>
                    <el-radio @change="handPoxy" v-model="ruleForm.ordinaryProxyIsmoney" label="0">按定额收取</el-radio>
                    <el-radio @change="handPoxy" v-model="ruleForm.ordinaryProxyIsmoney" label="1">按百分比收取</el-radio>

                    <el-input v-if="ruleForm.ordinaryProxyIsmoney == 0" style="width:100%" 
                      v-model="ruleForm.ordinarySelfFee"  :min="0"
                       onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                       oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                      >
                      <template slot="append">元</template>
                    </el-input>
                    <el-input v-else style="width:100%" v-model="ruleForm.ordinarySelfFee"
                       onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                      @input="handleordinaryProxyFee" @change="handleordinaryProxyFee"  :max="100"
                       oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                      >
                      <template slot="append">%</template>
                    </el-input>
                  </div>
                </el-form-item>
              </el-col>

            </el-row>

           

            <el-form-item label="服务费含税" prop="isSelfTax">
              <el-radio v-model="ruleForm.isSelfTax" label='0'>是</el-radio>
              <el-radio v-model="ruleForm.isSelfTax" label='1'>否</el-radio>
            </el-form-item>
            <el-form-item label="价税分离" prop="isOrdinaryTax">
              <el-radio v-model="ruleForm.isOrdinaryTax" label='0'>是</el-radio>
              <el-radio v-model="ruleForm.isOrdinaryTax" label='1'>否</el-radio>
            </el-form-item>
            <el-form-item label="是否分润" prop='isOrdinaryShare'>
              <el-radio v-model="ruleForm.isOrdinaryShare" label="0">是</el-radio>
              <el-radio v-model="ruleForm.isOrdinaryShare" label="1">否</el-radio>
            </el-form-item>
            <el-row v-if="ruleForm.isOrdinaryShare == 0" type="flex" justify="flex-end">
              <el-col :span="24">
                <el-form-item label="分润方式" prop="ordinaryShare">
                  <div style="">
                    <el-radio @change="hanOrshare" v-model="ruleForm.ordinaryShareIsmoney" label="0">按定额收取</el-radio>
                    <el-radio @change="hanOrshare" v-model="ruleForm.ordinaryShareIsmoney" label="1">按百分比收取</el-radio>

                    <el-input v-if="ruleForm.ordinaryShareIsmoney == 0" style="width:100%" 
                      :min="0" v-model="ruleForm.ordinaryShare"
                      onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                      oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                      >
                      <template slot="append">元</template>
                    </el-input>
                    <el-input v-model="ruleForm.ordinaryShare" v-else style="width:100%"
                      @input="handleordinaryShareIsmoney" @change="handleordinaryShareIsmoney" :step="0.01" :min="0"
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

          </div>
        </el-col>
        <el-col :span="9"> </el-col>
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
            <el-radio v-model="ruleForm.isSlider" label="0">开启</el-radio>
            <el-radio v-model="ruleForm.isSlider" label="1">关闭</el-radio>
          </el-form-item>
        </el-col>
        <el-col :span="9"> </el-col>
     </el-row>
      <el-row type="flex" class="row-bg " justify="space-around">
        
        <el-col :span="9">
          <div v-if='ruleForm.isSlider == 0'>
            <el-row type="flex" justify="flex-end">
              <el-col :span="24">
                <el-form-item label="专票税率" :required="true">
                  <!-- <el-select style="width:100%" v-model="ruleForm.ordinarySpecialTax" clearable placeholder="请选择">
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
                <el-form-item label="专票服务费" prop="specialSelfFee">
                  <div>
                    <el-radio @change="handSpecial" v-model="ruleForm.specialProxyIsmoney" label="0">按定额收取</el-radio>
                    <el-radio @change="handSpecial" v-model="ruleForm.specialProxyIsmoney" label="1">按百分比收取</el-radio>

                    <el-input v-if="ruleForm.specialProxyIsmoney == 0" style="width:100%" 
                      v-model="ruleForm.specialSelfFee"  :min="0"
                      onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                      oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                      >
                      <template slot="append">元</template>
                    </el-input>
                    <el-input v-else  style="width:100%" v-model="ruleForm.specialSelfFee"
                      @input="handlespecialProxyIsmoney" @change="handlespecialProxyIsmoney"  :min="0"
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
            <el-form-item label="服务费含税" prop="isSpecialTax">
              <el-radio v-model="ruleForm.isSpecialTax" label='0'>是</el-radio>
              <el-radio v-model="ruleForm.isSpecialTax" label='1'>否</el-radio>
            </el-form-item>
            <el-form-item label="价税分离" prop="isSpecialSelfTax">
              <el-radio v-model="ruleForm.isSpecialSelfTax" label='0'>是</el-radio>
              <el-radio v-model="ruleForm.isSpecialSelfTax" label='1'>否</el-radio>
            </el-form-item>
            <el-form-item label="是否分润" prop="isSpecialShare">
              <el-radio v-model="ruleForm.isSpecialShare" label="0">是</el-radio>
              <el-radio v-model="ruleForm.isSpecialShare" label="1">否</el-radio>
            </el-form-item>
            <el-row v-if="ruleForm.isSpecialShare == 0" type="flex" justify="flex-end">
              <el-col :span="24">
                <el-form-item label="分润方式" prop="specialShare">
                  <div style="">
                    <el-radio @change="handMoney" v-model="ruleForm.specialShareIsmoney" label="0">按定额收取</el-radio>
                    <el-radio @change="handMoney" v-model="ruleForm.specialShareIsmoney" label="1">按百分比收取</el-radio>

                    <el-input v-if="ruleForm.specialShareIsmoney == 0" style="width:100%"  
                      :min="0" v-model="ruleForm.specialShare"
                      onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                       oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                      >
                      <template slot="append">元</template>
                    </el-input>
                    <el-input v-model="ruleForm.specialShare" v-else  style="width:100%"
                      
                      @input="handlespecialShareIsmoney" @change="handlespecialShareIsmoney"  :min="0"
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
              <el-form-item label="状态" prop='isDisposable'>
              <el-radio v-model="ruleForm.isDisposable" label="0">开启</el-radio>
              <el-radio v-model="ruleForm.isDisposable" label="1">关闭</el-radio>
            </el-form-item>
            <el-row type="flex" justify="flex-end" v-if="ruleForm.isDisposable==0">
              <el-col :span="24">
                <el-form-item label="费用" prop="disposableFee">
                  <div>
                    <el-radio @change="handleDis" v-model="ruleForm.disposableFeeIsmoney" label="0">按定额收取</el-radio>
                    <el-radio @change="handleDis" v-model="ruleForm.disposableFeeIsmoney" label="1">按百分比收取</el-radio>

                    <el-input v-if="ruleForm.disposableFeeIsmoney == 0" style="width:100%" 
                      v-model="ruleForm.disposableFee"  :min="0"
                      onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                      oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                      >
                      <template slot="append">元</template>
                    </el-input>
                    <el-input v-else  style="width:100%" v-model="ruleForm.disposableFee"
                      @input="disposableIsmoney" @change="disposableIsmoney"  :min="0"
                      :max="100"
                       onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                       oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                      >
                      <template slot="append">%</template>
                    </el-input>
                  </div>
                </el-form-item>
                <el-form-item label="备注" v-if="ruleForm.isDisposable==0">
                <el-input
                maxlength="50"
                show-word-limit
                type="textarea"
                :rows="2"
                placeholder="请输入备注"
                v-model="ruleForm.disposableRemark"
              >
              </el-input>
              </el-form-item>
              </el-col>
              

            </el-row>
            <el-form-item label="是否分润" prop="isDisposableShare" v-if="ruleForm.isDisposable==0">
              <el-radio v-model="ruleForm.isDisposableShare" label="0">是</el-radio>
              <el-radio v-model="ruleForm.isDisposableShare" label="1">否</el-radio>
            </el-form-item>
            <el-row v-if="ruleForm.isDisposableShare == 0" type="flex" justify="flex-end">
              <el-col :span="24">
                <el-form-item label="分润方式" prop="disposableShare">
                  <div style="">
                    <el-radio @change="isdisshare" v-model="ruleForm.disposableShareIsmoney" label="0">按定额收取</el-radio>
                    <el-radio @change="isdisshare" v-model="ruleForm.disposableShareIsmoney" label="1">按百分比收取</el-radio>

                    <el-input v-if="ruleForm.disposableShareIsmoney == 0" style="width:100%" 
                      :min="0" v-model="ruleForm.disposableShare"
                      onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                      oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                      >
                      <template slot="append">元</template>
                    </el-input>
                    <el-input v-model="ruleForm.disposableShare" v-else style="width:100%"
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
          <el-button type="primary" @click="submitForm">提交</el-button>

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
    const reg = /^1[3|4|5|7|8|9][0-9]\d{8}$/
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
import { getInfo } from '@/api/login';
import '@riophae/vue-treeselect/dist/vue-treeselect.css'
import { getAllUser } from '@/api/system/user';
export default {
  name: "Add",

  data() {
    return {
      leaderList:[],
      placeAliasName:'',
      editSpecialProxyFee: '2',
      editOrdinaryProxyFee: '2',
      editSpecialInvoice13: '2',
      editSpecialInvoice6: '2',
      editSpecialShare: '2',
      editOrdinaryShare: '2',

      ordinarySelfFee: '2',
      specialInvoice13: '2',
      specialInvoice6: '2',
      specialProxyMoney: '2',
      specialSelfFee: '2',

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
        disposableRemark:'',//备注
        isDisposableShare:'1',
        disposableShareIsmoney:'0',
        disposableShare:'0',
        disposableFeeIsmoney:'1',
        disposableFee:'0',
        isDisposable:'0',//是否一次性费用
        isRegisterMoney:'0',//是否收取注册服务费
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
       

        specialInvoice13Money: 0, //  专票 13 元
        specialInvoice6Money: 0, //  专票6 元
        specialInvoice6: 0,   //专票 6 （%）
        specialInvoice13: 0,  //专票13 （%）
        specialSelfFee: 0,
        isSpecialTax: '1',//是否含税-专票
        ordinaryProxyMoney: 0,//普票平台服务费(元）
        registerMoney: 0,
        ordinarySelfFee: 0, //普票平台服务费(%）
        isOrdinaryTax: '1',//是否含税-普票

        ordinaryProxyIsmoney: '0',
        isOrdinaryShare: '1',
        ordinaryShareIsmoney: '0',
        specialProxyIsmoney: '0',
        isSpecialShare: '1',
        specialShareIsmoney: '0',


        //编辑参数

        editOrdinaryProxyIsmoney: '0',
        editIsOrdinaryShare: '1',
        editOrdinaryShareIsmoney: '0',
        editSpecialProxyIsmoney: '0',
        editIsSpecialShare: '1',
        editSpecialShareIsmoney: '0',
        editIsSlider: '0',//专票滑块
        editIsSliderOrdinary: '0',


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
        editOrdinaryProxyFee: '',
        editOrdinaryProxyMoney: '',
        editIsOrdinaryTax: '',//是否含税-普票
      },
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
        isDisposableShare:[{
          required: true, trigger: 'bulr'   
        }],
        disposableShareIsmoney:[{
          required: true
        }],
        disposableShare:[{
          required: true,message: '一次性费用分润费不能为空'
        }],
        disposableFeeIsmoney:[{
          required: true,message: '请选择一次性费用是否定额', trigger: 'change'   
        }],
        disposableFee:[{
          required: true,message: '一次性费用不能为空', trigger: 'blur'   
        }],


        isDisposable:[{
          required: true
        }],
        isRegisterMoney:[{
          required: true, message: '请选择个体注册服务费是否开启', trigger: 'change'   
        }],


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
          { required: true, message: '请选择普票服务费含税', trigger: 'change' }
        ],

        editIsSelfTax: [
          { required: true, message: '请选择普票服务费含税', trigger: 'change' }
        ],
        isSpecialSelfTax: [
          { required: true, message: '请选择专票价税分离', trigger: 'change' }
        ],
        editIsSpecialSelfTax: [
          { required: true, message: '请选择专票价税分离', trigger: 'change' }
        ],
        placeCode: [
          { required: true, message: '请输入客户编号', trigger: 'blur' }
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
        ordinarySelfFee: [
          { required: true, message: '请输入普票服务费', trigger: 'blur' }
        ],
        editOrdinaryProxyFee: [
          { required: true, message: '请输入普票服务费', trigger: 'blur' }
        ],
        ordinaryShare: [
          { required: true, message: '请输入普票分润费', trigger: 'blur' }
        ],
        editOrdinaryShare: [
          { required: true, message: '请输入普票分润费', trigger: 'blur' }
        ],
        specialSelfFee: [
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



        userId: [
          { required: true, message: '请选择业务经理', trigger: 'change' }
        ],



        editSpecialSelfFee: [
          { validator: numCheck, required: true, trigger: 'blur' }
        ],
        isSpecialTax: [
          { required: true, message: '请选择是否含税', trigger: 'change' }
        ],
        registerMoney: [
          { message: '请输入个体户注册服务费', required: true, trigger: 'blur' }
        ],
        editOrdinarySelfFee: [
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
      
    };
  },

  mounted() {
    
    this.getPlaceCode();
    //获取登录用户
    getInfo().then(res => {
      this.ruleForm.userName = res.user.nickName;
      this.ruleForm.editUserName = res.user.nickName;
      this.ruleForm.userId = res.user.userId;
      console.log("getInfo", this.ruleForm.userName);
      this.getLeader();
    })
     this.reset();
  },

  methods: {
    editUserid(e){
    this.leaderList.map((item)=>{
     if(item.value==e){
       console.log('id',item.userId);
       return  this.ruleForm.userId=item.userId;
       }
     })
    },
     //获取业务经理
     getLeader(){
      getAllUser().then((res)=>{
      this.leaderList=[];
      let list=res;
      list.map((item)=>{
       if(item.userId==this.ruleForm.userId){
         return this.deptId=item.deptId;
       }
      });
      list.map((item)=>{
       if(item.deptId==this.deptId){
             this.leaderList.push({
               value:item.nickName,
               label:item.nickName,
               userId:item.userId
              })
          }
      });
      
   })
  },
    //实时输入获取客户全名
    placeNews(){
     if(this.ruleForm.placeName==null){
      this.ruleForm.placeName='';
     }
     if(this.ruleForm.placeAlias==null){
      this.ruleForm.placeAlias='';
     }
     this.placeAliasName=this.ruleForm.placeName+this.ruleForm.placeAlias;
    },
    handleChange(value) {
      console.log(value);
    },
    //返回上一页不刷新
    backAgos(){
       this.$tab.closeOpenPage({path:'/tc-business/place/placelist'});
     },
    //返回上一页刷新
    backAgo(){
       this.$tab.closeOpenPage({path:'/tc-business/place/placelist'}).then(() => {
           this.$tab.refreshPage({path:'/tc-business/place/placelist',name:'Placelist'});

        })
      
    },
    //一次性分润
    isdisshare(e){
      if(e=='1'){
      if (this.ruleForm.disposableShareIsmoney == '1') {
      if ( this.ruleForm.disposableShare > 100) {
          this.ruleForm.disposableShare = '100';
        }
      }
      }

    },
    //一次性分润
    disShare(e){
      if (this.ruleForm.disposableShareIsmoney == '1') {
      if ( e > 100) {
          this.ruleForm.disposableShare = '100';
        }
      }
    },
    
    //一次性费用
    disposableIsmoney(e) {
      if (this.ruleForm.disposableFeeIsmoney == '1') {
        if (e > 100) {
          this.ruleForm.disposableFee = '100';
        }
      }
    },
    //一次性费用
    handleDis(e){
      if(e=='1'){
        if (this.ruleForm.disposableFeeIsmoney == '1') {
        if (this.ruleForm.disposableFee > 100) {
          this.ruleForm.disposableFee = '100';
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
    selfShareIsmoneys(e){
       if (e == '1') {
        if (this.ruleForm.selfShareIsmoney == '1') {
          if (this.ruleForm.selfShare > 100) {
            this.ruleForm.selfShare = '100';
          }
        }
      }
    },
   handlespecialShareIsmoney(e) {
      if (this.ruleForm.specialShareIsmoney == '1') {
        if (e > 100) {
          this.ruleForm.specialShare = '100';
        }
      }
    },
    handlespecialProxyIsmoney(e) {
      if (this.ruleForm.specialProxyIsmoney == '1') {
        if (e > 100) {
          this.ruleForm.specialSelfFee = '100';
        }
      }
    },
    handleordinaryShareIsmoney(e) {
      if (this.ruleForm.ordinaryShareIsmoney == '1') {
        if (e > 100) {
          this.ruleForm.ordinaryShare = '100';
        }
      }
    },
    handleordinaryProxyFee(e) {
      if (this.ruleForm.ordinaryProxyIsmoney == '1') {
        if (e > 100) {
          this.ruleForm.ordinarySelfFee = '100';
        }
      }
    },
    handPoxy(e) {
      if (e == '1') {
        if (this.ruleForm.ordinaryProxyIsmoney == '1') {
          if (this.ruleForm.ordinarySelfFee > 100) {
            this.ruleForm.ordinarySelfFee = '100';
          }
        }
      }
    },
    hanOrshare(e) {
      if (e == '1') {
        if (this.ruleForm.ordinaryShareIsmoney == '1') {
          if (this.ruleForm.ordinaryShare > 100) {
            this.ruleForm.ordinaryShare = '100';
          }
        }
      }
    },
    handSpecial(e) {
      if (e == '1') {
        if (this.ruleForm.specialProxyIsmoney == '1') {
          if (this.ruleForm.specialSelfFee > 100) {
            this.ruleForm.specialSelfFee = '100';
          }
        }
      }
    },
    handMoney(e) {
      if (e == '1') {
        if (this.ruleForm.specialShareIsmoney == '1') {
          if (this.ruleForm.specialShare > 100) {
            this.ruleForm.specialShare = '100';
          }
        }
      }
    },
   
   //获取客户code
    getPlaceCode() {
      crudPlace.getCode().then(res => {
        this.ruleForm.placeCode = res.message;
        console.log("placeCode", this.ruleForm.placeCode);
      })
    },

   // 表单重置
    reset() {
      this.ruleForm.disposableRemark='';
      this.ruleForm.isDisposableShare='1';
      this.ruleForm.disposableShareIsmoney='0';
      this.ruleForm.disposableShare='0';
      this.ruleForm.disposableFeeIsmoney='1';
      this.ruleForm.disposableFee='0';
      this.ruleForm.isDisposable='1';
      this.ruleForm.isRegisterMoney='1';
      this.ruleForm.selfShareIsmoney='0';
      this.ruleForm.isSelfShare='1';
      this.ruleForm.selfShare='0';
      this.ruleForm.customerType='0';
      this.ruleForm.isSliderOrdinary = '1',
      this.ruleForm.isSlider = '1';
      this.ruleForm.isSelfTax = '1';   //普票价税分离
      this.ruleForm.isSpecialSelfTax = '1';  //专票价税分离
      this.ruleForm.ordinaryProxyIsmoney = '0';
      this.ruleForm.isOrdinaryShare = '1';
      this.ruleForm.ordinaryShareIsmoney = '0'
      this.ruleForm.specialProxyIsmoney = '0'
      this.ruleForm.isSpecialShare = '1';
      this.ruleForm.specialShareIsmoney = '0';




      this.ruleForm.placeName = null;
      this.ruleForm.placeAlias = null;//客户别名
      this.ruleForm.placeLinkman = null;
      this.ruleForm.placeTel = null;
      this.ruleForm.userId = null;
      this.ruleForm.userName = null;
      this.ruleForm.ordinarySpecialTax = '0.03';
      this.ruleForm.ordinaryTax = '0';
      this.ruleForm.specialInvoice6 = '';
      this.ruleForm.specialInvoice13 = '';
      this.ruleForm.specialInvoice6Money = '';
      this.ruleForm.specialInvoice13Money = '';
      this.ruleForm.specialSelfFee = null;
      this.ruleForm.isSpecialTax = '1';
      this.ruleForm.registerMoney = 0;
      this.ruleForm.ordinarySelfFee = 0;
      this.ruleForm.ordinaryProxyMoeny = '';
      this.ruleForm.isOrdinaryTax = '1';
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
         
         let data = {
            businessPlace: {
              customerType:this.ruleForm.customerType,
              placeCode: this.ruleForm.placeCode,
              placeName: this.ruleForm.placeName,
              placeAlias: this.ruleForm.placeAlias,
              placeLinkman: this.ruleForm.placeLinkman,
              placeTel: this.ruleForm.placeTel,
              userId: this.ruleForm.userId,
              userName: this.ruleForm.userName,
            },
            businessAgencyFee: {
              isDisposableShare:this.ruleForm.isDisposableShare,
              disposableShareIsmoney:this.ruleForm.disposableShareIsmoney,
              disposableShare:this.ruleForm.disposableShare,
              disposableFeeIsmoney: this.ruleForm.disposableFeeIsmoney,
              disposableFee: this.ruleForm.disposableFee,
              isDisposable:this.ruleForm.isDisposable,
              isRegisterMoney:this.ruleForm.isRegisterMoney,
              disposableRemark:this.ruleForm.disposableRemark,
              
              isSliderOrdinary: this.ruleForm.isSliderOrdinary,
              isSlider: this.ruleForm.isSlider,
              isSelfTax: this.ruleForm.isSelfTax,
              isSpecialSelfTax: this.ruleForm.isSpecialSelfTax,
              placeCode: this.ruleForm.placeCode,

             
              ordinarySelfFee: this.ruleForm.ordinarySelfFee,
             
              specialSelfFee: this.ruleForm.specialSelfFee,
               isSpecialTax: this.ruleForm.isSpecialTax,
              registerMoney: this.ruleForm.registerMoney,
              ordinaryTax: '0',
              ordinarySpecialTax: '0.03',
              specialShare: this.ruleForm.specialShare,
               ordinaryShare: this.ruleForm.ordinaryShare,
              isOrdinaryTax: this.ruleForm.isOrdinaryTax,
              ordinaryProxyIsmoney: this.ruleForm.ordinaryProxyIsmoney,
              isOrdinaryShare: this.ruleForm.isOrdinaryShare,
              ordinaryShareIsmoney: this.ruleForm.ordinaryShareIsmoney,
              specialProxyIsmoney: this.ruleForm.specialProxyIsmoney,
              isSpecialShare: this.ruleForm.isSpecialShare,
              specialShareIsmoney: this.ruleForm.specialShareIsmoney,

              selfShareIsmoney: this.ruleForm.selfShareIsmoney,
              isSelfShare: this.ruleForm.isSelfShare,
              selfShare:this.ruleForm.selfShare





            }
          };
          crudPlace.add(data).then(res => {
            if (res.id == 0) {
              this.$message({
                message: res.message,
                type: 'success',
              });
               this.backAgo();
            } else {

              this.getPlaceCode();
              this.$alert(res.message, '系统提示', {
              confirmButtonText: '确定',
              
              type: 'warning'
           });
            }
           
           
           

          }).catch(err => {
             this.getPlaceCode();//重新在获取一遍编号（避免编号重复）
            // this.$tab.refreshPage();
          });

        }
        else {
            this.$alert('请填写完整', '系统提示', {
              confirmButtonText: '确定',
              
              type: 'warning'
           });
          
        }
      });
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

