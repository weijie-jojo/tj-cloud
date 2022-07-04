<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="auto">
      <el-form-item label="渠道商" prop="placeName">
        <el-input v-model="queryParams.placeName" placeholder="请输入渠道商" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="业务经理" prop="userName">
        <el-input v-model="queryParams.userName" placeholder="请输入渠道商" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select clearable v-model="queryParams.status" placeholder="请选择">
          <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
         >修改</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete">删除
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="employedList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="渠道商名称" align="center" prop="placeName" :show-overflow-tooltip="true" />
      <el-table-column label="联系人" align="center" prop="placeLinkman" :show-overflow-tooltip="true" />
      <el-table-column label="联系方式" prop="placeTel" :show-overflow-tooltip="true" />
      <el-table-column form-item label="业务经理" prop="userName" :show-overflow-tooltip="true" />
      <el-table-column label="状态" prop="dictLabel" align="center" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-s-custom" @click="detail(scope.row)">详情</el-button>


          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">编辑</el-button>

          <el-button size="mini" type="text" v-if="scope.row.placeStatus==0"  @click="isDormancy(scope.row,2)">
               休眠
          </el-button>
           <el-button size="mini" type="text" v-if="scope.row.placeStatus==2"   @click="isDormancy(scope.row,0)">
               激活
          </el-button>
           <el-button size="mini" type="text" disabled v-if="scope.row.placeStatus==1"   @click="isDormancy(scope.row,1)">
              禁用
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.page" :limit.sync="queryParams.size"
      @pagination="getList" />

    <!-- 添加-->
    <el-dialog :title="title" :visible.sync="addVisible" width="500px" append-to-body>
      <el-form ref="form" :model="ruleForm" :rules="rules" size="small" label-width="auto" :inline="true">
        <!-- 卡1 -->
        <el-card class="box-card" id="form1">
          <div slot="header" class="clearfix">
            <span>渠道商信息</span>
          </div>
          <el-form-item label="渠道商编码" prop="placeCode">
            <el-input v-model="ruleForm.placeCode" disabled />
          </el-form-item>
          <el-form-item label="渠道商名称" prop="placeName">
            <el-input v-model="ruleForm.placeName" />
          </el-form-item>
          <el-form-item label="联系人" prop="placeLinkman">
            <el-input v-model="ruleForm.placeLinkman" />
          </el-form-item>
          <el-form-item label="联系方式" prop="placeTel">
            <el-input v-model="ruleForm.placeTel" />
          </el-form-item>
          <el-form-item label="业务经理">
            <el-select v-model="ruleForm.userId" placeholder="业务经理" class="filter-item" @change="selectUser" style="">
              <el-option v-for="item in userLeaders" :key="item.userId" :label="item.nickName" :value="item.userId" />
            </el-select>
          </el-form-item>
        </el-card>
        <!-- 卡2 -->
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>增值税专用发票</span>
          </div>
          <el-form-item label="6%专票平台服务费" prop="specialInvoice6">
            <el-input-number v-model.number="ruleForm.specialInvoice6" @change="handleChange" :step="0.01"
              :precision="2" :min="0" :max="6"></el-input-number> (%)
          </el-form-item>
          <el-form-item label="13%专票平台服务费" prop="specialInvoice13">
            <el-input-number v-model.number="ruleForm.specialInvoice13" @change="handleChange" :step="0.01"
              :precision="2" :min="0" :max="13"></el-input-number> (%)
          </el-form-item>
          <el-form-item label="专票个体户代办费" prop="specialSelfFee">
            <el-input-number v-model.number="ruleForm.specialSelfFee" @change="handleChange" :step="0.1" :precision="1"
              :min="0" :max="9999" style="width: 200px;margin-right: 10px;"></el-input-number>元
            <!-- <el-input 
                v-model="ruleForm.specialSelfFee" 
                style="width: 200px;" /> -->
          </el-form-item>
          <el-form-item label="服务费含税" prop="isSpecialTax">
            <el-radio v-model="ruleForm.isSpecialTax" label='0'>是</el-radio>
            <el-radio v-model="ruleForm.isSpecialTax" label='1'>否</el-radio>
          </el-form-item>
        </el-card>
        <!-- 卡3 -->
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>增值税普通发票</span>
          </div>
          <el-form-item label="普票个体户代办费" prop="ordinarySelfFee">
            <el-input-number v-model.number="ruleForm.ordinarySelfFee" @change="handleChange" :step="0.1" :precision="1"
              :min="0" :max="9999" style=""></el-input-number>元
            <!-- <el-input 
                v-model="ruleForm.ordinarySelfFee" 
                style="width: 130px;" /> -->
          </el-form-item>
          <el-form-item label="平台服务费(%)" prop="ordinaryProxyFee">
            <el-input-number v-model.number="ruleForm.ordinaryProxyFee" @change="handleChange" :step="0.01"
              :precision="2" :min="0" :max="13"></el-input-number>
          </el-form-item>
          <el-form-item label="服务费含税" prop="isOrdinaryTax">
            <el-radio v-model="ruleForm.isOrdinaryTax" label='0'>是</el-radio>
            <el-radio v-model="ruleForm.isOrdinaryTax" label='1'>否</el-radio>
          </el-form-item>
        </el-card>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="reset">重置</el-button>
      </div>
    </el-dialog>



    <!-- 编辑-->
    <el-dialog :title="titles" :visible.sync="editVisible" width="500px" append-to-body>
      <el-form ref="form" :model="ruleForm" :rules="rules" size="small" label-width="auto" :inline="true">
        <!-- 卡1 -->
        <el-card class="box-card" id="form1">
          <div slot="header" class="clearfix">
            <span>渠道商信息</span>
          </div>
          <el-form-item label="渠道商编码" prop="editPlaceCode">
            <el-input v-model="ruleForm.editPlaceCode" disabled />
          </el-form-item>
          <el-form-item label="渠道商名称" prop="editPlaceName">
            <el-input v-model="ruleForm.editPlaceName" />
          </el-form-item>
          <el-form-item label="联系人" prop="editPlaceLinkman">
            <el-input v-model="ruleForm.editPlaceLinkman" />
          </el-form-item>
          <el-form-item label="联系方式" prop="editPlaceTel">
            <el-input v-model="ruleForm.editPlaceTel" />
          </el-form-item>
          <el-form-item label="业务经理" prop="editUserId">
            <el-select clearable v-model="ruleForm.editUserId" placeholder="业务经理" class="filter-item"
              @change="selectUser" style="">
              <el-option v-for="item in userLeaders" :key="item.userId" :label="item.nickName" :value="item.userId" />
            </el-select>
          </el-form-item>
        </el-card>
        <!-- 卡2 -->
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>增值税专用发票</span>
          </div>
          <el-form-item label="6%专票平台服务费" prop="editSpecialInvoice6">
            <el-input-number v-model.number="ruleForm.editSpecialInvoice6" @change="handleChange" :step="0.01"
              :precision="2" :min="0" :max="6"></el-input-number> (%)
          </el-form-item>
          <el-form-item label="13%专票平台服务费" prop="editSpecialInvoice13">
            <el-input-number v-model.number="ruleForm.editSpecialInvoice13" @change="handleChange" :step="0.01"
              :precision="2" :min="0" :max="13"></el-input-number> (%)
          </el-form-item>
          <el-form-item label="专票个体户代办费" prop="editSpecialSelfFee">
            <el-input-number v-model.number="ruleForm.editSpecialSelfFee" @change="handleChange" :step="0.1"
              :precision="1" :min="0" :max="9999" style="width: 200px;margin-right: 10px;"></el-input-number>元
            <!-- <el-input 
                v-model="ruleForm.specialSelfFee" 
                style="width: 200px;" /> -->
          </el-form-item>
          <el-form-item label="服务费含税" prop="editIsSpecialTax">
            <el-radio v-model="ruleForm.editIsSpecialTax" label='0'>是</el-radio>
            <el-radio v-model="ruleForm.editIsSpecialTax" label='1'>否</el-radio>
          </el-form-item>
        </el-card>
        <!-- 卡3 -->
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>增值税普通发票</span>
          </div>
          <el-form-item label="普票个体户代办费" prop="editOrdinarySelfFee">
            <el-input-number v-model.number="ruleForm.editOrdinarySelfFee" @change="handleChange" :step="0.1"
              :precision="1" :min="0" :max="9999" style=""></el-input-number>元
            <!-- <el-input 
                v-model="ruleForm.ordinarySelfFee" 
                style="width: 130px;" /> -->
          </el-form-item>
          <el-form-item label="平台服务费(%)" prop="editOrdinaryProxyFee">
            <el-input-number v-model.number="ruleForm.editOrdinaryProxyFee" @change="handleChange" :step="0.01"
              :precision="2" :min="0" :max="13"></el-input-number>
          </el-form-item>
          <el-form-item label="服务费含税" prop="editIsOrdinaryTax">
            <el-radio v-model="ruleForm.editIsOrdinaryTax" label='0'>是</el-radio>
            <el-radio v-model="ruleForm.editIsOrdinaryTax" label='1'>否</el-radio>
          </el-form-item>
        </el-card>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="confirmEdit">确 定</el-button>
        <el-button @click="reset">重置</el-button>
      </div>
    </el-dialog>



    <!-- 详情-->
    <el-dialog :title="titleh" :visible.sync="checkVisible" width="500px" append-to-body>
      <el-form ref="form" :model="ruleForm" :rules="ruleForm" size="small" label-width="auto" :inline="true">
        <!-- 卡1 -->
        <el-card class="box-card" id="form1">
          <div slot="header" class="clearfix">
            <span>渠道商信息</span>
          </div>
          <el-form-item label="渠道商编码" prop="placeCode">
            <el-input v-model="ruleForm.placeCode" disabled />
          </el-form-item>
          <el-form-item label="渠道商名称" prop="placeName">
            <el-input disabled v-model="ruleForm.placeName" />
          </el-form-item>
          <el-form-item label="联系人" prop="placeLinkman">
            <el-input disabled v-model="ruleForm.placeLinkman" />
          </el-form-item>
          <el-form-item label="联系方式" prop="placeTel">
            <el-input disabled v-model="ruleForm.placeTel" />
          </el-form-item>
          <el-form-item label="业务经理" prop="userId">
            <el-select disabled v-model="ruleForm.userId" placeholder="业务经理" class="filter-item" @change="selectUser"
              style="">
              <el-option v-for="item in userLeaders" :key="item.userId" :label="item.nickName" :value="item.userId" />
            </el-select>
          </el-form-item>
        </el-card>
        <!-- 卡2 -->
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>增值税专用发票</span>
          </div>
          <el-form-item label="6%专票平台服务费" prop="specialInvoice6">
            <el-input-number disabled v-model.number="ruleForm.specialInvoice6" @change="handleChange" :step="0.01"
              :precision="2" :min="0" :max="6"></el-input-number> (%)
          </el-form-item>
          <el-form-item label="13%专票平台服务费" prop="specialInvoice13">
            <el-input-number disabled v-model.number="ruleForm.specialInvoice13" @change="handleChange" :step="0.01"
              :precision="2" :min="0" :max="13"></el-input-number> (%)
          </el-form-item>
          <el-form-item label="专票个体户代办费" prop="specialSelfFee">
            <el-input-number disabled v-model.number="ruleForm.specialSelfFee" @change="handleChange" :step="0.1"
              :precision="1" :min="0" :max="9999" style="width: 200px;margin-right: 10px;"></el-input-number>元
            <!-- <el-input 
                v-model="ruleForm.specialSelfFee" 
                style="width: 200px;" /> -->
          </el-form-item>
          <el-form-item label="服务费含税" prop="isSpecialTax">
            <el-radio v-model="ruleForm.isSpecialTax" label='0'>是</el-radio>
            <el-radio v-model="ruleForm.isSpecialTax" label='1'>否</el-radio>
          </el-form-item>
        </el-card>
        <!-- 卡3 -->
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>增值税普通发票</span>
          </div>
          <el-form-item label="普票个体户代办费" prop="ordinarySelfFee">
            <el-input-number disabled v-model.number="ruleForm.ordinarySelfFee" @change="handleChange" :step="0.1"
              :precision="1" :min="0" :max="9999" style=""></el-input-number>元
            <!-- <el-input 
                v-model="ruleForm.ordinarySelfFee" 
                style="width: 130px;" /> -->
          </el-form-item>
          <el-form-item label="平台服务费(%)" prop="ordinaryProxyFee">
            <el-input-number disabled v-model.number="ruleForm.ordinaryProxyFee" @change="handleChange" :step="0.01"
              :precision="2" :min="0" :max="13"></el-input-number>
          </el-form-item>
          <el-form-item label="服务费含税" prop="isOrdinaryTax">
            <el-radio v-model="ruleForm.isOrdinaryTax" label='0'>是</el-radio>
            <el-radio v-model="ruleForm.isOrdinaryTax" label='1'>否</el-radio>
          </el-form-item>
        </el-card>

      </el-form>

    </el-dialog>

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
    return callback(new Error('个体代办费不能为空'))
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
import '@riophae/vue-treeselect/dist/vue-treeselect.css'

export default {
  name: "placeMgr",

  data() {
    return {
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
        page: 1,
        size: 10,
        placeName: null,
        userName: null,
        status: '',
      },
      ruleForm: {
        placeCode: '',
        placeName: '',
        placeLinkman: '',
        placeTel: '',
        userId: 26,
        userName: '',

        specialInvoice6: '',
        specialInvoice13: '',
        specialSelfFee: '',
        isSpecialTax: '',//是否含税-专票

        ordinarySelfFee: '',
        ordinaryProxyFee: '',
        isOrdinaryTax: '',//是否含税-普票

        //编辑参数
        editPlaceId: '',
        editPlaceCode: '',
        editPlaceName: '',
        editPlaceLinkman: '',
        editPlaceTel: '',
        editUserId: '',
        editUserName: '',

        editAgencyFeeId: '',
        editSpecialInvoice6: '',
        editSpecialInvoice13: '',
        editSpecialSelfFee: '',
        editIsSpecialTax: '',//是否含税-专票

        editOrdinarySelfFee: '',
        editOrdinaryProxyFee: '',
        editIsOrdinaryTax: '',//是否含税-普票
      },
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
        placeCode: [
          { required: true, message: '请输入编号', trigger: 'blur' }
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
        userId: [
          { required: true, message: '请选择业务经理', trigger: 'change' }
        ],
        specialInvoice6: [
          { required: true, message: '请输入6%专票代开费率', trigger: 'blur' }
        ],
        specialInvoice13: [
          { required: true, message: '请输入13%专票代开费率', trigger: 'blur' }
        ],
        // specialSelfFee: [
        //     {validator: numCheck,required: true, trigger: 'blur' }
        // ],
        isSpecialTax: [
          { required: true, message: '请选择是否含税', trigger: 'change' }
        ],
        // ordinarySelfFee: [
        //     { validator: numCheck,required: true, trigger: 'blur' }
        // ],
        ordinaryProxyFee: [
          { required: true, message: '请输入普票代开收费', trigger: 'blur' }
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
      // rules: {},
    };
  },
  created() {
    this.getList();
    this.getAllUser();
    this.getPlaceCode();
  },

  methods: {

    getPlaceCode() {
      crudPlace.getCode().then(res => {
        this.ruleForm.placeCode = res.message;
        console.log("placeCode", this.ruleForm.placeCode);
      })
    },

    /** 查询列表 */
    getList() {
      this.loading = true;
      crudPlace.getByPage(this.queryParams).then((response) => {
        this.employedList = response.content;

        this.total = response.totalElements;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.addVisible = false;
      this.reset();
    },

    // 表单重置
    reset() {
      this.ruleForm.placeName = null;
      this.ruleForm.placeLinkman = null;
      this.ruleForm.placeTel = null;
      this.ruleForm.userId = null;
      this.ruleForm.userName = null;
      this.ruleForm.specialInvoice6 = null;
      this.ruleForm.specialInvoice13 = null;
      this.ruleForm.specialSelfFee = null;
      this.ruleForm.isSpecialTax = null;
      this.ruleForm.ordinarySelfFee = null;
      this.ruleForm.ordinaryProxyFee = null;
      this.ruleForm.isOrdinaryTax = null;
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.multipleSelection = selection;
      console.log(this.multipleSelection);
      // this.ids = selection.map((item) => item.selfId);
      // this.single = selection.length !== 1;
      // this.multiple = !selection.length;
    },

    handleChange(value) {
      console.log(value);
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();

      this.addVisible = true;
      this.title = "新增渠道管理";
    },
    /** 修改按钮操作 */
    handleUpdate(item) {
      this.editVisible = true;
      this.titles = "编辑渠道管理";
      var placeCode = item.placeCode;
      agencyfee.selectFeeByCode({ placeCode: placeCode }).then(res => {
        this.ruleForm.editAgencyFeeId = res.agencyFeeId;
        this.ruleForm.editSpecialInvoice6 = res.specialInvoice6;
        this.ruleForm.editSpecialInvoice13 = res.specialInvoice13;
        this.ruleForm.editSpecialSelfFee = res.specialSelfFee;
        this.ruleForm.editIsSpecialTax = res.isSpecialTax;
        this.ruleForm.editOrdinarySelfFee = res.ordinarySelfFee;
        this.ruleForm.editOrdinaryProxyFee = res.ordinaryProxyFee;
        this.ruleForm.editIsOrdinaryTax = res.isOrdinaryTax;

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
        console.log(this.ruleForm.editIsOrdinaryTax);

      })
      console.log("editAgencyFeeId==", this.ruleForm.editAgencyFeeId);
      this.ruleForm.editPlaceId = item.placeId;
      this.ruleForm.editPlaceCode = item.placeCode;
      this.ruleForm.editPlaceName = item.placeName;
      this.ruleForm.editPlaceLinkman = item.placeLinkman;
      this.ruleForm.editPlaceTel = item.placeTel;
      this.ruleForm.editUserId = item.userId;
      this.ruleForm.editUserName = item.userName;

      this.ruleForm.editAgencyFeeId = item.agencyFeeId;
      this.ruleForm.editSpecialInvoice6 = item.specialInvoice6;
      this.ruleForm.editSpecialInvoice13 = item.specialInvoice13;
      this.ruleForm.editSpecialSelfFee = item.specialSelfFee;
      // this.ruleForm.editIsSpecialTax=item.isSpecialTax;

      this.ruleForm.editOrdinarySelfFee = item.ordinarySelfFee;
      this.ruleForm.editOrdinaryProxyFee = item.ordinaryProxyFee;
      // this.ruleForm.editIsOrdinaryTax=item.isOrdinaryTax;



    },
    //详情
    detail(item) {
      this.titleh = '渠道管理详情';
      this.checkVisible = true;

      this.ruleForm.placeCode = item.placeCode;
      this.ruleForm.placeName = item.placeName;
      this.ruleForm.placeLinkman = item.placeLinkman;
      this.ruleForm.placeTel = item.placeTel;
      this.ruleForm.nickName = item.nickName;
      this.ruleForm.placeStatus = item.placeStatus;
      agencyfee.selectFeeByCode({ placeCode: this.ruleForm.placeCode }).then(res => {
        console.log("selectFeeByCode==", res);
        this.ruleForm.specialInvoice6 = res.specialInvoice6;
        this.ruleForm.specialInvoice13 = res.specialInvoice13;
        this.ruleForm.specialSelfFee = res.specialSelfFee;
        this.ruleForm.isSpecialTax = res.isSpecialTax;
        this.ruleForm.ordinarySelfFee = res.ordinarySelfFee;
        this.ruleForm.ordinaryProxyFee = res.ordinaryProxyFee;
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



      })
      this.ruleForm.value = item.value;
      // if(this.ruleForm.placeStatus==0){
      //     this.ruleForm.placeStatus="正常";
      // }
      // if(this.ruleForm.placeStatus==1){
      //     this.ruleForm.placeStatus="预警";
      // }
      // if(this.ruleForm.placeStatus==2){
      //     this.ruleForm.placeStatus="休眠";
      // }
      // if(this.ruleForm.is_special_tax=='0'){
      //     this.ruleForm.is_special_tax="是";
      // }
      // if(this.ruleForm.is_special_tax=='1'){
      //     this.ruleForm.is_special_tax="否";
      // }
      // if(this.ruleForm.is_ordinary_tax=='0'){
      //     this.ruleForm.is_ordinary_tax="是";
      // }
      // if(this.ruleForm.is_ordinary_tax=='1'){
      //     this.ruleForm.is_ordinary_tax="否";
      // }  

    },
    //修改状态
     isDormancy(item,type){
      var placeStatus;
          if(item.placeStatus==0){
        placeStatus=2;
      }
      if(item.placeStatus==1){
        this.$message({
            message: '欠费状态不能点哦',
            type: 'warning',
        });
        return;
      }
      if(item.placeStatus==2){
        placeStatus=0;
      }
      let params={
        placeId:item.placeId,
        placeStatus:placeStatus,
      }
      crudPlace.editPlace2(params).then((res) => {
          if(res.id==0){
            this.$message({
                message: res.message,
                type: 'success',
            });      
          }else{
              this.$message({
                message: res.message,
                type: 'warning',
            });
          }
          this.$tab.refreshPage();
         // this.editVisible=false;
      })
    },
    //获取所有用户
    getAllUser() {
      getAllUser().then(res => {

        this.userLeaders = res;
      })
    },
    selectUser(value) {
      console.log(value);
      // this.ruleForm.userName='测试啊';
      // this.ruleForm.editUserName='111';
      this.ruleForm.userName = this.userLeaders.find((item) => item.userId == value).nickName;
      this.ruleForm.editUserName = this.userLeaders.find((item) => item.userId == value).nickName;
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.ruleForm.isOrdinaryTax == "0") {
            this.ruleForm.isOrdinaryTax = true;
          } else if (this.ruleForm.isOrdinaryTax == '1') {
            this.ruleForm.isOrdinaryTax = false;
          }
          if (this.ruleForm.isSpecialTax == "0") {
            this.ruleForm.isSpecialTax = true;
          } else if (this.ruleForm.isSpecialTax == "1") {
            this.ruleForm.isSpecialTax = false;
          }
          let data = {
            placeCode: this.ruleForm.placeCode,
            placeName: this.ruleForm.placeName,
            placeLinkman: this.ruleForm.placeLinkman,
            placeTel: this.ruleForm.placeTel,
            userId: this.ruleForm.userId,
            specialInvoice6: this.ruleForm.specialInvoice6,
            specialInvoice13: this.ruleForm.specialInvoice13,
            specialSelfFee: this.ruleForm.specialSelfFee,
            isSpecialTax: this.ruleForm.isSpecialTax,
            ordinarySelfFee: this.ruleForm.ordinarySelfFee,
            ordinaryProxyFee: this.ruleForm.ordinaryProxyFee,
            isOrdinaryTax: this.ruleForm.isOrdinaryTax,
            userName: this.ruleForm.userName,
          };
          agencyfee.add(data).then(res => {//添加详情表
            if (res > 0) {//后端捕获重复键异常
              crudPlace.add(data)//添加主信息表
              this.$message({
                message: "添加成功",
                type: 'success',
              });
            } else {
              this.$message({
                message: "编号重复，自动返回，请重新创建",
                type: 'warning',
              });
            }
            this.addVisible = false;
            // this.getPlaceCode();//重新在获取一遍编号（避免编号重复）
            this.$tab.refreshPage();

          });
        }
        else {
          this.$message({
            message: "请填写完整",
            type: 'warning',
          });
          return false;
        }
      });
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
            placeLinkman: this.ruleForm.editPlaceLinkman,
            editPlaceTel: this.ruleForm.editPlaceTel,
            userId: this.ruleForm.editUserId,
            userName: this.ruleForm.editUserName,

            specialInvoice6: this.ruleForm.editSpecialInvoice6,
            specialInvoice13: this.ruleForm.editSpecialInvoice13,
            specialSelfFee: this.ruleForm.editSpecialSelfFee,
            isSpecialTax: this.ruleForm.editIsSpecialTax,

            ordinarySelfFee: this.ruleForm.editOrdinarySelfFee,
            ordinaryProxyFee: this.ruleForm.editOrdinaryProxyFee,
            isOrdinaryTax: this.ruleForm.editIsOrdinaryTax,
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

            this.editVisible = false;
            this.$tab.refreshPage();

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


    /** 删除按钮操作 */
    handleDelete(row) {
      let placeCodeStr = [];
      console.log(this.multipleSelection);
      this.multipleSelection.map((item) => {
        placeCodeStr.push(item.placeCode);
      })
      let params = {
        placeCodes: placeCodeStr.join(',')//转换成字符串
      };

      if (confirm('你确定删除吗？')) {
        crudPlace.delPlace(params).then((res) => {
          if (res != undefined) {
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
            this.$tab.refreshPage();

          }
        })
      }

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
::v-deep .is-disabled .el-input__inner {
  background-color: transparent !important;
  color: black;
}
</style>

