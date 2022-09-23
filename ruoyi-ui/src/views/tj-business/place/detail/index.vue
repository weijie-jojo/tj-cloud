<template>
  <div>
    <el-form ref="form" :model="ruleForm" :rules="rules" label-width="auto">
      <el-row type="flex" class="row-bg " style="margin-top:20px" justify="space-around">
        <el-col :span="9">
          <el-form-item label="客户编码" prop="placeCode">
            <el-input v-model="ruleForm.placeCode" :readonly="true" />
          </el-form-item>
          <el-form-item label="客户名称" prop="placeName">
            <el-input v-model="ruleForm.placeName" :readonly="true"/>
          </el-form-item>
           <el-form-item label="客户全名" :required="true">
                 <div style="display:flex;align-items:center">
              <el-input style="width:100%;margin-right:-25px" v-model="ruleForm.placeAliasName" :readonly="true" />
              <el-tooltip style="position: relative;left: 30px;font-size:20px;" class="item" effect="dark" content="客户名称由客户名称和客户别名组成" placement="top-start">
                  <i class="header-icon el-icon-info"></i>
              </el-tooltip>
              </div>
          
          </el-form-item>
          <el-form-item label="联系人" prop="placeLinkman">
            <el-input v-model="ruleForm.placeLinkman" :readonly="true" />
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
              <el-radio disabled v-model="ruleForm.isRegisterMoney" label="0">开启</el-radio>
              <el-radio disabled v-model="ruleForm.isRegisterMoney" label="1">关闭</el-radio>
            </el-form-item>
          <el-form-item label="服务费" prop="registerMoney" v-if="ruleForm.isRegisterMoney==0">
            <el-input 
             disabled
             type="number" v-model="ruleForm.registerMoney" @change="handleChange" :step="0.01" :min="0"
              style="width:100%"
              oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]' 
              >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
               <el-form-item label="是否分润" prop='isSelfShare' v-if="ruleForm.isRegisterMoney==0">
              <el-radio disabled v-model="ruleForm.isSelfShare" label="0">是</el-radio>
              <el-radio disabled v-model="ruleForm.isSelfShare" label="1">否</el-radio>
            </el-form-item>
            <el-row v-if="ruleForm.isSelfShare == 0 && ruleForm.isRegisterMoney==0" type="flex" justify="flex-end">
              <el-col :span="24">
                <el-form-item label="分润方式" prop="selfShareIsmoney">
                  <div style="">
                    <el-radio :disabled="true" @change="selfShareIsmoneys" v-model="ruleForm.selfShareIsmoney" label="0">按定额收取</el-radio>
                    <el-radio :disabled="true" @change="selfShareIsmoneys" v-model="ruleForm.selfShareIsmoney" label="1">按百分比收取</el-radio>

                    <el-input 
                     :readonly="true"
                    v-if="ruleForm.selfShareIsmoney == 0" style="width:100%" 
                      :min="0" v-model="ruleForm.selfShare"
                      onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                      oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                      >
                      <template slot="append">元</template>
                    </el-input>
                    <el-input
                     :readonly="true"
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
          <el-form-item label="业务经理" :required="true">
            <el-input v-model="ruleForm.userName" :readonly="true"></el-input>
          </el-form-item>
          <el-form-item label="客户别名" prop="placeAlias">
            <el-input v-model='ruleForm.placeAlias' :readonly="true" />
          </el-form-item>
          <el-form-item label="客户类型" :required="true">
             <el-select disabled style="width:100%" v-model="ruleForm.customerType" clearable placeholder="请选择客户类型">
                  <el-option v-for="item in customerOptions" :key="item.value" :label="item.label" :value="item.value">
                  </el-option>
                </el-select>
          </el-form-item>
          <el-form-item label="联系方式" prop="placeTel">
            <el-input v-model="ruleForm.placeTel" :readonly="true" />
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
            <el-radio disabled v-model="ruleForm.isSliderOrdinary" label="0">开启</el-radio>
            <el-radio disabled v-model="ruleForm.isSliderOrdinary" label="1">关闭</el-radio>
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
                    <el-radio disabled @change="handPoxy" v-model="ruleForm.ordinaryProxyIsmoney" label="0">按定额收取</el-radio>
                    <el-radio disabled @change="handPoxy" v-model="ruleForm.ordinaryProxyIsmoney" label="1">按百分比收取</el-radio>

                    <el-input disabled v-if="ruleForm.ordinaryProxyIsmoney == 0" style="width:100%" type="number"
                      v-model="ruleForm.ordinarySelfFee" :step="0.01" :min="0"
                       oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                      >
                      <template slot="append">元</template>
                    </el-input>
                    <el-input disabled v-else type="number" style="width:100%" v-model="ruleForm.ordinarySelfFee"
                      @input="handleordinaryProxyFee" @change="handleordinaryProxyFee" :step="0.01" :max="100"
                       oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                      >
                      <template slot="append">%</template>
                    </el-input>
                  </div>
                </el-form-item>
              </el-col>

            </el-row>
            <el-form-item label="服务费含税" prop="isSelfTax">
              <el-radio disabled v-model="ruleForm.isSelfTax" label='0'>是</el-radio>
              <el-radio disabled v-model="ruleForm.isSelfTax" label='1'>否</el-radio>
            </el-form-item>
            <el-form-item label="价税分离" prop="isOrdinaryTax">
              <el-radio disabled v-model="ruleForm.isOrdinaryTax" label='0'>是</el-radio>
              <el-radio disabled v-model="ruleForm.isOrdinaryTax" label='1'>否</el-radio>
            </el-form-item>

            
            <el-form-item label="是否分润" prop='isOrdinaryShare'>
              <el-radio disabled v-model="ruleForm.isOrdinaryShare" label="0">是</el-radio>
              <el-radio disabled v-model="ruleForm.isOrdinaryShare" label="1">否</el-radio>
            </el-form-item>
            <el-row v-if="ruleForm.isOrdinaryShare == 0" type="flex" justify="flex-end">
              <el-col :span="24">
                <el-form-item label="分润方式" prop="ordinaryShare">
                  <div style="">
                    <el-radio disabled @change="hanOrshare" v-model="ruleForm.ordinaryShareIsmoney" label="0">按定额收取</el-radio>
                    <el-radio disabled @change="hanOrshare" v-model="ruleForm.ordinaryShareIsmoney" label="1">按百分比收取</el-radio>

                    <el-input disabled v-if="ruleForm.ordinaryShareIsmoney == 0" style="width:100%" type="number" :step="0.01"
                      :min="0" v-model="ruleForm.ordinaryShare"
                       oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                      >
                      <template slot="append">元</template>
                    </el-input>
                    <el-input disabled v-model="ruleForm.ordinaryShare" v-else type="number" style="width:100%"
                      @input="handleordinaryShareIsmoney" @change="handleordinaryShareIsmoney" :step="0.01" :min="0"
                      :max="100"
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
            <el-radio disabled v-model="ruleForm.isSlider" label="0">开启</el-radio>
            <el-radio disabled v-model="ruleForm.isSlider" label="1">关闭</el-radio>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          
        </el-col>

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
                    <el-radio disabled @change="handSpecial" v-model="ruleForm.specialProxyIsmoney" label="0">按定额收取</el-radio>
                    <el-radio disabled @change="handSpecial" v-model="ruleForm.specialProxyIsmoney" label="1">按百分比收取</el-radio>

                    <el-input disabled v-if="ruleForm.specialProxyIsmoney == 0" style="width:100%" type="number"
                      v-model="ruleForm.specialSelfFee" :step="0.01" :min="0"
                       oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                      >
                      <template slot="append">元</template>
                    </el-input>
                    <el-input disabled v-else type="number" style="width:100%" v-model="ruleForm.specialSelfFee"
                      @input="handlespecialProxyIsmoney" @change="handlespecialProxyIsmoney" :step="0.01" :min="0"
                      :max="100"
                       oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                      >
                      <template slot="append">%</template>
                    </el-input>
                  </div>
                </el-form-item>
              </el-col>

            </el-row>
            <el-form-item label="服务费含税" prop="isSpecialTax">
              <el-radio disabled v-model="ruleForm.isSpecialTax" label='0'>是</el-radio>
              <el-radio disabled v-model="ruleForm.isSpecialTax" label='1'>否</el-radio>
            </el-form-item>
            <el-form-item label="价税分离" prop="isSpecialSelfTax">
              <el-radio disabled v-model="ruleForm.isSpecialSelfTax" label='0'>是</el-radio>
              <el-radio disabled v-model="ruleForm.isSpecialSelfTax" label='1'>否</el-radio>
            </el-form-item>
            <el-form-item label="是否分润" prop="isSpecialShare">
              <el-radio disabled v-model="ruleForm.isSpecialShare" label="0">是</el-radio>
              <el-radio disabled v-model="ruleForm.isSpecialShare" label="1">否</el-radio>
            </el-form-item>
            <el-row v-if="ruleForm.isSpecialShare == 0" type="flex" justify="flex-end">
              <el-col :span="24">
                <el-form-item label="分润方式" prop="specialShare">
                  <div style="">
                    <el-radio disabled @change="handMoney" v-model="ruleForm.specialShareIsmoney" label="0">按定额收取</el-radio>
                    <el-radio disabled @change="handMoney" v-model="ruleForm.specialShareIsmoney" label="1">按百分比收取</el-radio>

                    <el-input disabled v-if="ruleForm.specialShareIsmoney == 0" style="width:100%" type="number" :step="0.01"
                      :min="0" v-model="ruleForm.specialShare"
                       oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                      >
                      <template slot="append">元</template>
                    </el-input>
                    <el-input disabled v-model="ruleForm.specialShare" v-else type="number" style="width:100%"
                      @input="handlespecialShareIsmoney" @change="handlespecialShareIsmoney" :step="0.01" :min="0"
                      :max="100"
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
              <el-radio disabled v-model="ruleForm.isDisposable" label="0">开启</el-radio>
              <el-radio disabled v-model="ruleForm.isDisposable" label="1">关闭</el-radio>
            </el-form-item>
            <el-row type="flex" justify="flex-end" v-if="ruleForm.isDisposable==0">
              <el-col :span="24">
                <el-form-item label="费用" prop="disposableFee">
                  <div>
                    <el-radio disabled  v-model="ruleForm.disposableFeeIsmoney" label="0">按定额收取</el-radio>
                    <el-radio disabled  v-model="ruleForm.disposableFeeIsmoney" label="1">按百分比收取</el-radio>

                    <el-input 
                     disabled
                    v-if="ruleForm.disposableFeeIsmoney == 0" style="width:100%" 
                      v-model="ruleForm.disposableFee"  :min="0"
                      onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                      oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                      >
                      <template slot="append">元</template>
                    </el-input>
                    <el-input v-else  style="width:100%" v-model="ruleForm.disposableFee"
                      disabled
                      :min="0"
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
              <el-radio disabled v-model="ruleForm.isDisposableShare" label="0">是</el-radio>
              <el-radio disabled v-model="ruleForm.isDisposableShare" label="1">否</el-radio>
            </el-form-item>
            <el-row v-if="ruleForm.isDisposableShare == 0" type="flex" justify="flex-end">
              <el-col :span="24">
                <el-form-item label="分润方式" prop="disposableShare">
                  <div style="">
                    <el-radio disabled v-model="ruleForm.disposableShareIsmoney" label="0">按定额收取</el-radio>
                    <el-radio disabled v-model="ruleForm.disposableShareIsmoney" label="1">按百分比收取</el-radio>

                    <el-input
                    disabled
                    v-if="ruleForm.disposableShareIsmoney == 0" style="width:100%" 
                      :min="0" v-model="ruleForm.disposableShare"
                      onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                      oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                      >
                      <template slot="append">元</template>
                    </el-input>
                    <el-input 
                    disabled
                    v-model="ruleForm.disposableShare" v-else style="width:100%"
                      :step="0.01" :min="0"
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
          <el-button type="danger" @click="backAgo">关闭</el-button>
          

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
import agencyfee from "@/api/place/agencyfee";
import '@riophae/vue-treeselect/dist/vue-treeselect.css'
export default {
  name: "Detail",

  data() {
    return {
      registerMoney: '2',
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
        disposableRemark:'',
        ordinarySelfFee:'0',
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
        specialShare: '',//专票分润
        ordinaryShare: '',//普票分润
        specialSelfFee: 0,
        isSpecialTax: '1',//是否含税-专票
        registerMoney: 0,
        ordinarySelfFee: 0, //普票平台服务费(%）
        isOrdinaryTax: '1',//是否含税-普票
        ordinaryProxyIsmoney: '0',
        isOrdinaryShare: '1',
        ordinaryShareIsmoney: '0',
        specialProxyIsmoney: '0',
        isSpecialShare: '1',
        specialShareIsmoney: '0',
      },
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
       
        isOrdinaryShare: [{
          required: true, message: '请选择普票是否分润', trigger: 'change'
        }],
      
        specialShareIsmoney: [{
          required: true, message: '请选择专票分润方式', trigger: 'change'
        }],
        ordinaryShareIsmoney: [{
          required: true, message: '请选择普票分润方式', trigger: 'change'
        }],
       
        isSelfTax: [
          { required: true, message: '请选择普票价税分离', trigger: 'change' }
        ],

       
        isSpecialSelfTax: [
          { required: true, message: '请选择专票价税分离', trigger: 'change' }
        ],
        
        placeCode: [
          { required: true, message: '请输入编号', trigger: 'blur' }
        ],
        placeAlias: [
          { required: true, message: '请输入客户别名', trigger: 'blur' }
        ],
        placeName: [
          { required: true, message: '请输入客户名', trigger: 'blur' }
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
       
       
        ordinarySelfFee: [
          { required: true, message: '请输入普票服务费', trigger: 'blur' }
        ],
        
        ordinaryShare: [
          { required: true, message: '请输入普票分润费', trigger: 'blur' }
        ],
        specialSelfFee: [
          { required: true, message: '请输入专票服务费', trigger: 'blur' }
        ],
       
        specialShare: [
          { required: true, message: '请输入专票分润费', trigger: 'blur' }
        ],
        userId: [
          { required: true, message: '请选择业务经理', trigger: 'change' }
        ],
        isSpecialTax: [
          { required: true, message: '请选择是否含税', trigger: 'change' }
        ],
        ordinarySelfFee:[
        { message: '请选择是否开启个体户注册服务费', required: true, trigger: 'blur' }
        ],

        isDisposableShare:[{
          required: true,message: '一次性费用分润费不能为空', trigger: 'bulr'   
        }],
        disposableShareIsmoney:[{
          required: true
        }],
        disposableShare:[{
          required: true,
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
       registerMoney: [
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
      // 表单参数
      form: {},
      
    };
  },

  mounted() {
    this.getlist();
  },
 methods: {
    getlist(){
      this.$modal.loading("正在加载数据，请稍后...");
      agencyfee.selectFeeByCode({ placeCode: this.$cache.local.getJSON("tj-placelist").placeCode }).then(res => {
      this.$modal.closeLoading();
      let item=this.$cache.local.getJSON("tj-placelist");  
        this.ruleForm.customerType=JSON.stringify(item.customerType); 
        this.ruleForm.placeCode = item.placeCode;
        this.ruleForm.placeName = item.placeName;
        this.ruleForm.placeAliasName=item.placeAliasName;
        this.ruleForm.placeLinkman = item.placeLinkman;
        this.ruleForm.placeTel = item.placeTel;
        this.ruleForm.nickName = item.nickName;
        this.ruleForm.placeStatus = item.placeStatus;
        this.ruleForm.userName = item.userName;
        this.ruleForm.placeAlias = item.placeAlias;
        this.ruleForm.selfShareIsmoney=JSON.stringify(res.selfShareIsmoney);
        this.ruleForm.isSelfShare=JSON.stringify(res.isSelfShare);
        this.ruleForm.selfShare=JSON.stringify(res.selfShare);
        this.ruleForm.isSlider=JSON.stringify(res.isSlider); 
        this.ruleForm.isSliderOrdinary=JSON.stringify(res.isSliderOrdinary); 
        this.ruleForm.ordinaryProxyIsmoney = JSON.stringify(res.ordinaryProxyIsmoney);
        this.ruleForm.isOrdinaryShare = JSON.stringify(res.isOrdinaryShare);
        this.ruleForm.ordinaryShareIsmoney = JSON.stringify(res.ordinaryShareIsmoney);
        this.ruleForm.specialProxyIsmoney = JSON.stringify(res.specialProxyIsmoney);
        this.ruleForm.isSpecialShare = JSON.stringify(res.isSpecialShare);
        this.ruleForm.specialShareIsmoney = JSON.stringify(res.specialShareIsmoney);
        this.ruleForm.isSelfTax = JSON.stringify(res.isSelfTax);
        this.ruleForm.isSpecialSelfTax = JSON.stringify(res.isSpecialSelfTax),
        this.ruleForm.ordinarySpecialTax = res.ordinarySpecialTax;//专票税率
        this.ruleForm.ordinaryTax = res.ordinaryTax; //普票税率
        this.ruleForm.specialSelfFee = res.specialSelfFee;
        this.ruleForm.isSpecialTax = res.isSpecialTax;
        this.ruleForm.disposableRemark=res.disposableRemark;
        this.ruleForm.disposableFeeIsmoney=JSON.stringify(res.disposableFeeIsmoney);
        this.ruleForm.isDisposable=JSON.stringify(res.isDisposable);
        this.ruleForm.disposableFee=res.disposableFee;
        this.ruleForm.isRegisterMoney=JSON.stringify(res.isRegisterMoney);
        this.ruleForm.ordinarySelfFee=JSON.stringify(res.ordinarySelfFee);
        this.ruleForm.isDisposableShare=JSON.stringify(res.isDisposableShare);
        this.ruleForm.disposableShareIsmoney=JSON.stringify(res.disposableShareIsmoney);
        this.ruleForm.disposableShare=res.disposableShare;
        
        
        this.ruleForm.ordinarySelfFee = res.ordinarySelfFee;
        this.ruleForm.registerMoney = res.registerMoney;
       
        this.ruleForm.ordinarySpecialTax = JSON.stringify(res.ordinarySpecialTax);
        this.ruleForm.ordinaryTax = JSON.stringify(res.ordinaryTax);
        this.ruleForm.specialShare = res.specialShare;
        this.ruleForm.ordinaryShare = res.ordinaryShare;
      




        this.ruleForm.isOrdinaryTax = res.isOrdinaryTax;
        if (this.ruleForm.isOrdinaryTax == true) {
          this.ruleForm.isOrdinaryTax = "0"
        } else if (this.ruleForm.isOrdinaryTax == false) {
          this.ruleForm.isOrdinaryTax = "1"
        }
        if (this.ruleForm.isSpecialTax == true) {
          this.ruleForm.isSpecialTax = "0"
        } else if (this.ruleForm.isSpecialTax == false) {
          this.ruleForm.isSpecialTax = "1"
        }
      }).catch((error)=>{
        this.$modal.closeLoading();
      })
    },
    handleChange(value) {
      console.log(value);
    },
    backAgo(){
        this.$tab.closeOpenPage({path:'/tj-business/place/placelist'});
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

