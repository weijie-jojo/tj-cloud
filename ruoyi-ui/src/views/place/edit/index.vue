<template>
  <div>
        <el-form ref="form" :model="ruleForm" :rules="rules" label-width="auto">
        <!-- 卡1 -->
        <el-row type="flex" class="row-bg " style="margin-top:20px" justify="space-around">
              <el-col :span="9">
          <el-form-item label="渠道商编码" prop="editPlaceCode">
            <el-input v-model="ruleForm.editPlaceCode" :readonly="true" />
          </el-form-item>
           <el-form-item label="渠道商名称" prop="editPlaceName">
            <el-input v-model="ruleForm.editPlaceName" :readonly="confirmEditStatus" />
          </el-form-item>
            <el-form-item label="联系人" prop="editPlaceLinkman">
            <el-input v-model="ruleForm.editPlaceLinkman" :readonly="confirmEditStatus" />
          </el-form-item>
            <el-form-item label="个体户注册服务费" prop="editOrdinarySelfFee">
                <el-input type="number" :readonly="confirmEditStatus" v-model="ruleForm.editOrdinarySelfFee"
                  @change="handleChange" :step="0.1" :min="0"  style="width:100%"
                   oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                  >
                  <template slot="append">元</template>
                </el-input>
              </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item label="业务经理" prop="editUserId" :required="true">
            <el-input v-model="ruleForm.editUserName" :readonly="true"></el-input>
          </el-form-item>
           <el-form-item label="渠道商别名" prop="editPlaceAlias">
            <el-input v-model="ruleForm.editPlaceAlias" :readonly="confirmEditStatus" />
          </el-form-item>
           <el-form-item label="联系方式" prop="editPlaceTel">
            <el-input v-model="ruleForm.editPlaceTel" :readonly="confirmEditStatus" />
          </el-form-item>
        </el-col>
        
        </el-row>
          
       
         <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="9">
          <el-form-item label="增值税普通发票">
             <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSliderOrdinary" label="0">开启</el-radio>
             <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSliderOrdinary" label="1">关闭</el-radio>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item label="增值税专用发票">
             <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSlider" label="0">开启</el-radio>
             <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSlider" label="1">关闭</el-radio>
          </el-form-item>
        </el-col>

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
              <el-form-item label="普票服务费" prop="editOrdinaryProxyFee">
                <div style="">
                  <el-radio @change="handPoxyS" :disabled="confirmEditStatus" v-model="ruleForm.editOrdinaryProxyIsmoney" label="0">按定额收取
                  </el-radio>
                  <el-radio @change="handPoxyS" :disabled="confirmEditStatus" v-model="ruleForm.editOrdinaryProxyIsmoney" label="1">按百分比收取
                  </el-radio>

                  <el-input v-if="ruleForm.editOrdinaryProxyIsmoney == 0" style="width:100%"
                    :readonly="confirmEditStatus" type="number" v-model="ruleForm.editOrdinaryProxyFee" :step="0.01"
                    :min="0"
                     oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                    >
                    <template slot="append">元</template>
                  </el-input>
                  <el-input :readonly="confirmEditStatus" v-else type="number" style="width:100%"
                    v-model="ruleForm.editOrdinaryProxyFee" 
                    @input="handleordinaryProxyFeeS"
                    @change="handleordinaryProxyFeeS" :step="0.01" :min="0" :max="100"
                     oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                    >
                    <template slot="append">%</template>
                  </el-input>
                </div>
              </el-form-item>
            </el-col>

          </el-row>
          <el-form-item label="服务费含税" prop="editIsOrdinaryTax">
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsOrdinaryTax" label='0'>是</el-radio>
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsOrdinaryTax" label='1'>否</el-radio>
          </el-form-item>
          <el-form-item label="价税分离" prop="editIsSelfTax">
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSelfTax" label='0'>是</el-radio>
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSelfTax" label='1'>否</el-radio>
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

                  <el-input v-if="ruleForm.editOrdinaryShareIsmoney == 0" style="width:100%" type="number"
                    :readonly="confirmEditStatus" :step="0.01" :min="0" v-model="ruleForm.editOrdinaryShare"
                     oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                    >
                    <template slot="append">元</template>
                  </el-input>
                  <el-input v-else :readonly="confirmEditStatus" v-model="ruleForm.editOrdinaryShare" type="number"
                    style="width:100%"
                    @input="handleordinaryShareIsmoneyS"
                    @change="handleordinaryShareIsmoneyS" :step="0.01" :min="0" :max="100"
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
                    :readonly="confirmEditStatus" type="number" v-model="ruleForm.editSpecialProxyFee" :step="0.01"
                    :min="0"
                     oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                    >
                    <template slot="append">元</template>
                  </el-input>
                  <el-input :readonly="confirmEditStatus" v-else type="number" style="width:100%"
                    v-model="ruleForm.editSpecialProxyFee"
                    @input="handlespecialProxyIsmoneyS"
                    @change="handlespecialProxyIsmoneyS" :step="0.01" :min="0"
                    :max="100"
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

                  <el-input v-if="ruleForm.editSpecialShareIsmoney == 0" style="width:100%" type="number" :step="0.01"
                    :min="0" v-model="ruleForm.editSpecialShare" :readonly="confirmEditStatus"
                     oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                    >
                    <template slot="append">元</template>
                  </el-input>
                  <el-input v-else v-model="ruleForm.editSpecialShare" type="number" style="width:100%"
                    :readonly="confirmEditStatus"
                    @input="handlespecialShareIsmoneyS"
                    @change="handlespecialShareIsmoneyS" :step="0.01" :min="0" :max="100"
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

        </el-row>
         <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="8">

        </el-col>
        <el-col :span="8" class="flexs">
           <el-button type="danger" @click="closeS">关闭</el-button>
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
import crudPlace from "@/api/place/place";
import agencyfee from "@/api/place/agencyfee";
import { getAllUser } from '@/api/system/user';
import { getInfo } from '@/api/login';
import '@riophae/vue-treeselect/dist/vue-treeselect.css'

export default {
  name: "placeMgr",

  data() {
    return {
      editSpecialProxyFee: '2',
      editOrdinaryProxyFee: '2',
      editSpecialInvoice13: '2',
      editSpecialInvoice6: '2',
      editSpecialShare: '2',
      editOrdinaryShare: '2',

      ordinaryProxyFee: '2',
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
        isSliderOrdinary: '0',
        isSlider: '0',//专票滑块
        isSelfTax: '1',     //普票价税分离
        isSpecialSelfTax: '1',  //专票价税分离
        ordinaryTax: '0',//普票税率
        ordinarySpecialTax: '0.03',//专票税率

        placeAlias: '',//渠道商别名
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
        ordinarySelfFee: 0,
        ordinaryProxyFee: 0, //普票平台服务费(%）
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
        editPlaceAlias: '',//渠道商别名
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
          { required: true, message: '请输入渠道别名', trigger: 'blur' }
        ],
        placeName: [
          { required: true, message: '请输入渠道商名', trigger: 'blur' }
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
        ordinaryProxyFee: [
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
          { required: true, message: '请输入渠道别名', trigger: 'blur' }
        ],
        editPlaceName: [
          { required: true, message: '请输入渠道商名', trigger: 'blur' }
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



        specialSelfFee: [
          { validator: numCheck, required: true, trigger: 'blur' }
        ],
        editSpecialSelfFee: [
          { validator: numCheck, required: true, trigger: 'blur' }
        ],
        isSpecialTax: [
          { required: true, message: '请选择是否含税', trigger: 'change' }
        ],
        ordinarySelfFee: [
          { validator: numCheck, required: true, trigger: 'blur' }
        ],
        editOrdinarySelfFee: [
          { validator: numCheck, required: true, trigger: 'blur' }
        ],

        isOrdinaryTax: [
          { required: true, message: '请选择是否含税', trigger: 'change' }
        ],
      },
      queryTypeOptions: [
        { key: 'placeName', display_name: '渠道商名称' },
        { key: 'placeStatus', display_name: '渠道商状态' },
        { key: 'nickName', display_name: '业务经理' }
      ],
      // 表单参数
      form: {},
      // 表单校验
      
    };
  },
 mounted() {
      let item=this.$cache.local.getJSON("placeItem");
      let  res=this.$cache.local.getJSON("placeItems");
      this.ruleForm.editPlaceId = item.placeId;
      this.ruleForm.editPlaceCode = item.placeCode;
      this.ruleForm.editPlaceName = item.placeName;
      this.ruleForm.editPlaceAlias = item.placeAlias;//渠道商别名
      this.ruleForm.editPlaceLinkman = item.placeLinkman;
      this.ruleForm.editPlaceTel = item.placeTel;
      this.ruleForm.editUserId = item.userId;
      this.ruleForm.editUserName = item.userName;


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
        this.ruleForm.editOrdinaryProxyFee = res.ordinaryProxyFee;
        this.ruleForm.editIsOrdinaryTax = res.isOrdinaryTax;
        this.ruleForm.editSpecialProxyFee = res.specialProxyFee;
        this.ruleForm.editSpecialShare = res.specialShare;
        this.ruleForm.editOrdinaryShare = res.ordinaryShare;
        this.ruleForm.editOrdinarySpecialTax = JSON.stringify(res.ordinarySpecialTax);
        this.ruleForm.editOrdinaryTax = JSON.stringify(res.ordinaryTax);
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
    // //获取登录用户
    // getInfo().then(res => {
    //   this.ruleForm.userName = res.user.userName;
    //   this.ruleForm.editUserName = res.user.userName;
    //   this.ruleForm.userId = res.user.userId;
    //   console.log("getInfo", this.ruleForm.userName);
    // })
  },

  methods: {
     closeS(){
       this.$tab.closeAllPage().then(() => {
        this.$tab.closeOpenPage({path:'/place/placeMgr'})
      })
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
          this.ruleForm.editOrdinaryProxyFee = '100';
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
          this.ruleForm.specialProxyFee = '100';
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
          this.ruleForm.ordinaryProxyFee = '100';
        }
      }
    },
    handPoxy(e) {
      if (e == '1') {
        if (this.ruleForm.ordinaryProxyIsmoney == '1') {
          if (this.ruleForm.ordinaryProxyFee > 100) {
            this.ruleForm.ordinaryProxyFee = '100';
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
          if (this.ruleForm.specialProxyFee > 100) {
            this.ruleForm.specialProxyFee = '100';
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
    handPoxyS(e) {
      if (e == '1') {
        if (this.ruleForm.editOrdinaryProxyIsmoney == '1') {
          if (this.ruleForm.editOrdinaryProxyFee > 100) {
            this.ruleForm.editOrdinaryProxyFee = '100';
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
       if (this.ruleForm.editIsSlider == '0') {
            if (this.ruleForm.editSpecialProxyIsmoney == '1') {
              if (this.ruleForm.editSpecialProxyFee > 100) {
                this.$alert('专票服务费按百分比不能大于100%', '提示', {
                  confirmButtonText: '确定',
                });
                return;
              }
            }
            if (this.ruleForm.editSpecialShareIsmoney == '1') {
              if (this.ruleForm.editSpecialShare > 100) {
                this.$alert('专票分润费按百分比不能大于100%', '提示', {
                  confirmButtonText: '确定',
                });
                return;
              }
            }
          }
          if (this.ruleForm.editIsSliderOrdinary == '0') {
            if (this.ruleForm.editOrdinaryShareIsmoney == '1') {
              if (this.ruleForm.editOrdinaryShare > 100) {
                this.$alert('普票分润费按百分比不能大于100%', '提示', {
                  confirmButtonText: '确定',
                });
                return;
              }
            }


            if (this.ruleForm.editOrdinaryProxyIsmoney == '1') {
              if (this.ruleForm.editOrdinaryProxyFee > 100) {
                this.$alert('普票服务费按百分比不能大于100%', '提示', {
                  confirmButtonText: '确定',
                });
                return;
              }
            }
          }




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
            placeAlias: this.ruleForm.editPlaceAlias,//渠道商别名
            placeLinkman: this.ruleForm.editPlaceLinkman,
            placeTel: this.ruleForm.editPlaceTel,
            userId: this.ruleForm.editUserId,
            userName: this.ruleForm.editUserName,

            isOrdinaryTax: this.ruleForm.editIsOrdinaryTax,
            ordinaryProxyIsmoney: this.ruleForm.editOrdinaryProxyIsmoney,
            isOrdinaryShare: this.ruleForm.editIsOrdinaryShare,
            ordinaryShareIsmoney: this.ruleForm.editOrdinaryShareIsmoney,
            specialProxyIsmoney: this.ruleForm.editSpecialProxyIsmoney,
            isSpecialShare: this.ruleForm.editIsSpecialShare,
            specialShareIsmoney: this.ruleForm.editSpecialShareIsmoney,

            specialSelfFee: this.ruleForm.editSpecialSelfFee,
            isSpecialTax: this.ruleForm.editIsSpecialTax,

            ordinarySelfFee: this.ruleForm.editOrdinarySelfFee,

            //specialProxyMoney: this.ruleForm.editSpecialProxyMoney,
            specialProxyFee: this.ruleForm.editSpecialProxyFee,

            ordinaryProxyFee: this.ruleForm.editOrdinaryProxyFee,
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
            this.closeS();
            //this.editVisible = false;
            //this.$tab.refreshPage();

          })
        } else {
          this.$message({
            message: "请填写完整",
            type: 'warning',
          });
          return false;
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
</style>

