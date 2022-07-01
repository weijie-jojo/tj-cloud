<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div>
        <!-- 搜索 -->
        <el-input
          v-model="ruleForm.placeName"
          clearable
          size="small"
          placeholder="输入渠道商名称"
          style="width: 200px;"
          class="filter-item"

        />

        <el-input
          v-model="ruleForm.userName"
          clearable
          size="small"
          placeholder="业务经理"
          style="width: 200px;"
          class="filter-item"

        />
        
        <el-select
          v-model="ruleForm.status"
          clearable 
          placeholder="选择渠道商状态"
          class="filter-item"
          style="width:200px"
          multiple 
          @change="changeStatus"
        >
          <el-option
            v-for="item in dict.place_status"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
        <!-- <rrOperation :crud="crud" /> -->
      </div>
      <div class="btnDiv">
        <el-button
          type="primary"
          @click="addPlace('ruleForm')"
        ><i class="el-icon-plus" />新增</el-button>
        <el-button
          type="danger"
          @click="delPlace('ruleForm')"
        ><i class="el-icon-delete" /> 删除</el-button>
      </div>
      <!--新增弹窗-->
      <el-dialog
        title="新增渠道管理"
        :visible.sync="addVisible"
        width="50%"
        top="12vh">
       <el-form 
          ref="ruleForm" 
          :model="ruleForm" 
          :rules="rules" 
          size="small" 
          :inline="true" label-width="160px">
          <!-- 卡1 -->
          <el-card class="box-card" id="form1">
            <div slot="header" class="clearfix">
              <span>渠道商信息</span>
            </div>
            <el-form-item label="渠道商编码" prop="placeCode" >
              <el-input v-model="ruleForm.placeCode" style="width: 370px;" disabled/>
            </el-form-item>
            <el-form-item label="渠道商名称" prop="placeName">
              <el-input v-model="ruleForm.placeName" style="width: 370px;" />
            </el-form-item>
            <el-form-item label="联系人" prop="placeLinkman">
              <el-input v-model="ruleForm.placeLinkman" style="width: 370px;" />
            </el-form-item>
            <el-form-item label="联系方式" prop="placeTel">
              <el-input v-model="ruleForm.placeTel" style="width: 370px;" />
            </el-form-item>
            <el-form-item label="业务经理" prop="userId">
              <el-select 
                v-model="ruleForm.userId" 
                placeholder="业务经理" 
                class="filter-item"
                @change="selectUser"
                style="width: 370px;">
                <el-option 
                  v-for="item in userLeaders" 
                  :key="item.id" 
                  :label="item.nickName" 
                  :value="item.id" />
              </el-select>
            </el-form-item>
          </el-card>
          <!-- 卡2 -->
          <el-card class="box-card">
           <div slot="header" class="clearfix">
            <span>增值税专用发票</span>
           </div>
           <el-form-item label="6%专票平台服务费" prop="specialInvoice6">
              <el-input-number 
                v-model.number="ruleForm.specialInvoice6" 
                @change="handleChange" 
                :step="0.01" 
                :precision="2" 
                :min="0" :max="6"
                style="width: 200px;"></el-input-number>  (%)
           </el-form-item>
           <el-form-item label="13%专票平台服务费" prop="specialInvoice13" >
              <el-input-number 
                v-model.number="ruleForm.specialInvoice13" 
                @change="handleChange" 
                :step="0.01" 
                :precision="2" 
                :min="0" 
                :max="13"
                ></el-input-number>  (%)
           </el-form-item>
            <el-form-item label="专票个体户代办费" prop="specialSelfFee">
              <el-input-number 
                v-model.number="ruleForm.specialSelfFee" 
                @change="handleChange" 
                :step="0.1" 
                :precision="1" 
                :min="0" 
                :max="9999"
                style="width: 200px;margin-right: 10px;"
                ></el-input-number>元
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
             <el-input-number 
                v-model.number="ruleForm.ordinarySelfFee" 
                @change="handleChange" 
                :step="0.1" 
                :precision="1" 
                :min="0" 
                :max="9999"
                style="width: 200px;margin-right: 10px;"
                ></el-input-number>元
             <!-- <el-input 
                v-model="ruleForm.ordinarySelfFee" 
                style="width: 130px;" /> -->
           </el-form-item>
           <el-form-item label="平台服务费(%)" prop="ordinaryProxyFee">
             <el-input-number 
                v-model.number="ruleForm.ordinaryProxyFee" 
                @change="handleChange" 
                :step="0.01" :precision="2" 
                :min="0" :max="13"></el-input-number>
           </el-form-item>
           <el-form-item label="服务费含税" prop="isOrdinaryTax">
            <el-radio v-model="ruleForm.isOrdinaryTax" label='0'>是</el-radio>
            <el-radio v-model="ruleForm.isOrdinaryTax" label='1'>否</el-radio>
           </el-form-item>
          </el-card>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="text" @click="resetForm('ruleForm')">重置</el-button>
          <el-button
            type="primary"
            @click="submitForm('ruleForm')"
            >确认</el-button>
        </div>
      </el-dialog>
      <!--编辑弹窗-->
      <el-dialog
        title="编辑渠道信息"
        :visible.sync="editVisible"
        width="50%"
        top="12vh">
       <el-form ref="ruleForm" 
          :model="ruleForm" 
          :rules="rules" 
          size="small" 
          :inline="true" label-width="160px">
          <!-- 卡1 -->
          <el-card class="box-card" id="form1">
            <div slot="header" class="clearfix">
              <span>渠道商信息</span>
            </div>
            <el-form-item label="渠道商编码" prop="editPlaceCode">
              <el-input v-model="ruleForm.editPlaceCode" style="width: 370px;" disabled/>
            </el-form-item>
            <el-form-item label="渠道商名称" prop="editPlaceName">
              <el-input v-model="ruleForm.editPlaceName" style="width: 370px;" />
            </el-form-item>
            <el-form-item label="联系人" prop="editPlaceLinkman">
              <el-input v-model="ruleForm.editPlaceLinkman" style="width: 370px;" />
            </el-form-item>
            <el-form-item label="联系方式" prop="editPlaceTel">
              <el-input v-model="ruleForm.editPlaceTel" style="width: 370px;" />
            </el-form-item>
            <el-form-item label="业务经理" prop="editUserId">
              <el-select 
                v-model="ruleForm.editUserId" 
                placeholder="业务经理" 
                class="filter-item"
                @change="selectUser"
                style="width: 370px;">
                <el-option 
                  v-for="item in userLeaders" 
                  :key="item.id" 
                  :label="item.nickName" 
                  :value="item.id" />
              </el-select>
            </el-form-item>
          </el-card>
          <!-- 卡2 -->
          <el-card class="box-card">
           <div slot="header" class="clearfix">
            <span>增值税专用发票</span>
           </div>
           <el-form-item label="6%专票平台服务费" prop="editSpecialInvoice6">
              <el-input-number 
                v-model.number="ruleForm.editSpecialInvoice6" 
                @change="handleChange" 
                :step="0.01" 
                :precision="2" 
                :min="0" :max="6"
                style="width: 200px;"></el-input-number>  (%)
           </el-form-item>
           <el-form-item label="13%专票平台服务费" prop="editSpecialInvoice13">
              <el-input-number 
                v-model.number="ruleForm.editSpecialInvoice13" 
                @change="handleChange" 
                :step="0.01" 
                :precision="2" 
                :min="0" :max="13"></el-input-number>  (%)
           </el-form-item>
            <el-form-item label="专票个体户代办费" prop="editSpecialSelfFee">
              <el-input-number 
                v-model.number="ruleForm.editSpecialSelfFee" 
                @change="handleChange" 
                :step="0.1" 
                :precision="1" 
                :min="0" 
                :max="9999"
                style="width: 200px;margin-right: 10px;"
                ></el-input-number>元
              <!-- <el-input 
                v-model="ruleForm.editSpecialSelfFee" 
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
             <el-input-number 
                v-model.number="ruleForm.editOrdinarySelfFee" 
                @change="handleChange" 
                :step="0.1" 
                :precision="1" 
                :min="0" 
                :max="9999"
                style="width: 200px;margin-right: 10px;"
                ></el-input-number>元
             <!-- <el-input 
                v-model="ruleForm.editOrdinarySelfFee" 
                style="width: 130px;" /> -->
           </el-form-item>
           <el-form-item label="平台服务费(%)" prop="editOrdinaryProxyFee">
             <el-input-number 
                v-model.number="ruleForm.editOrdinaryProxyFee" 
                @change="handleChange" 
                :step="0.01" :precision="2" 
                :min="0" :max="13"></el-input-number>
           </el-form-item>
           <el-form-item label="服务费含税" prop="editIsOrdinaryTax">
            <el-radio v-model="ruleForm.editIsOrdinaryTax" label='0'>是</el-radio>
            <el-radio v-model="ruleForm.editIsOrdinaryTax" label='1'>否</el-radio>
           </el-form-item>
          </el-card>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="text" @click="resetForm('ruleForm')">重置</el-button>
          <el-button
            type="primary"
            @click="confirmEdit('ruleForm')"
            >确认</el-button>
        </div>
      </el-dialog>
      <!--表格渲染-->
      <el-table
        ref="table"
        :data="11"
        size="small"
        style="width: 100%;"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" />
        <el-table-column prop="placeName" label="渠道商名称" />
        <el-table-column prop="placeLinkman" label="联系人" />
        <el-table-column prop="nickName" label="业务经理" />
        <el-table-column prop="label" label="渠道商状态" >

          <!-- <el-tooltip :content="'状态: ' + value" placement="top">
            <el-switch
              v-model="value"
              active-color="#13ce66"
              inactive-color="#ff4949"
              active-value="正常"
              inactive-value="休眠">
            </el-switch>
          </el-tooltip> -->
        </el-table-column>
        <el-table-column 
          label="操作" 
          width="350px" 
          align="center" >
          <template slot-scope="scope">
            <el-button 
              size="mini" 
              type="success" 
              @click="check(scope.row)" 
              >详情</el-button>
            <el-button 
              type="primary" 
              size="mini"
              @click="editPlace(scope.row)"
              >编辑</el-button>
            <el-button 
              type="primary" 
              size="mini"
              @click="isDormancy(scope.row)"
              >{{scope.row.placeStatus==0 ? "休眠" : (scope.row.placeStatus==2? "激活" :"欠费")}}
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 详情弹窗 -->
        <el-dialog
          :visible.sync="checkVisible"
          width="50%"
          top="12vh"
          title="详情"
        >
          <el-form
            ref="ruleForm"
            :model="ruleForm"
            :rules="rules"
            size="small"
            label-width="140px"
            :inline="true"
          >
          <!-- 卡1 -->
          <el-card class="box-card">
           <div slot="header" class="clearfix" >
             <span>渠道商信息</span>
           </div>
           <el-form-item label="渠道商编码">
            <el-input v-model="ruleForm.placeCode" style="width: 330px;" disabled/>
          </el-form-item>
           <el-form-item label="渠道商名称">
            <el-input v-model="ruleForm.placeName" style="width: 330px;" disabled/>
           </el-form-item>
            <el-form-item label="联系人">
             <el-input v-model="ruleForm.placeLinkman" style="width: 330px;" disabled/>
            </el-form-item>
            <el-form-item label="联系方式">
             <el-input v-model="ruleForm.placeTel" style="width: 330px;" disabled/>
            </el-form-item>
            <el-form-item label="业务经理">
              <el-input v-model="ruleForm.nickName" style="width: 330px;" disabled/>
           </el-form-item>
          </el-card>
          <!-- 卡2 -->
          <el-card class="box-card">
           <div slot="header" class="clearfix">
            <span>增值税专用发票</span>
           </div>
           <el-form-item label="6%专票平台服务费">
              <el-input v-model="ruleForm.specialInvoice6" style="width: 120px;" disabled/> (%)
           </el-form-item>
           <el-form-item label="13%专票平台服务费">
              <el-input v-model="ruleForm.specialInvoice13" style="width: 120px;" disabled/> (%)
           </el-form-item>
            <el-form-item label="专票个体户代办费">
             <el-input v-model="ruleForm.specialSelfFee" style="width: 120px;" disabled/> 元
            </el-form-item>
           <el-form-item label="服务费">
             <el-input v-model="ruleForm.isSpecialTax" style="width: 120px;" disabled/>
           </el-form-item>
          </el-card>
          <!-- 卡3 -->
          <el-card class="box-card">
           <div slot="header" class="clearfix">
            <span>增值税普通发票</span>
           </div>
           <el-form-item label="普票个体户代办费">
             <el-input v-model="ruleForm.ordinarySelfFee" style="width: 120px;" disabled/> 元
           </el-form-item>
           <el-form-item label="平台服务费(%)">
             <el-input v-model="ruleForm.ordinaryProxyFee" style="width: 120px;" disabled/> (%)
           </el-form-item>
           <el-form-item label="服务费">
             <el-input v-model="ruleForm.isOrdinaryTax" style="width: 120px;" disabled/> 元
           </el-form-item>
          </el-card>
          </el-form>
        </el-dialog>
      <!--分页组件-->
      <!-- <pagination /> -->
    </div>
  </div>
</template>

<script>
import crudPlace from "@/api/place/place";
import agencyfee from "@/api/place/agencyfee";
import {getAllUser} from '@/api/system/user';
// import CRUD, { presenter, header, crud } from "@crud/crud";
// import rrOperation from "@crud/RR.operation";
// import pagination from "@crud/Pagination";
import '@riophae/vue-treeselect/dist/vue-treeselect.css'

export default {
  name: "Place",
  components: {
    // pagination,
    // rrOperation,
  },
  // mixins: [presenter(), header(),  crud()],
  dicts: ["place_status"],
  // cruds() {
  //   return CRUD({
  //     title: "渠道管理",
  //     url: "api/place/getByPage",
  //     // query:{placeStatus:JSON.stringify(this.placeStatus)},
  //     // idField: "placeId,",
  //     // sort: "placeId,desc",
  //     crudMethod: { ...crudPlace }
  //   });
  // },
  data() {
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
    return {
      tabelData:[],

      editVisible:false,
      multipleSelection: [],
      addVisible:false,
      userLeaders:[],
      status:[],
      placeStatus: [], // 多选时使用
      is_special_tax: true,
      is_ordinary_tax: true,
      value: '正常',
      special_invoice_6: 0,
      special_invoice_13: 6.1,
      ordinary_proxy_fee: 0,
      Include: '1',
      users:[],
      isCheck:true,
      checkVisible:false,
      currentRow: {}, selectIndex: '', placeCode: '', urlHistory: '',

      ruleForm: {
        placeCode: '',
        placeName: '',
        placeLinkman: '',
        placeTel: '',
        userId:'',
        userName:'',

        specialInvoice6: '',
        specialInvoice13: '',
        specialSelfFee:'',
        isSpecialTax: '',//是否含税-专票

        ordinarySelfFee: '',
        ordinaryProxyFee: '',
        isOrdinaryTax: '',//是否含税-普票

        //编辑参数
        editPlaceId:'',
        editPlaceCode: '',
        editPlaceName: '',
        editPlaceLinkman: '',
        editPlaceTel: '',
        editUserId:'',
        editUserName:'',

        editAgencyFeeId:'',
        editSpecialInvoice6: '',
        editSpecialInvoice13: '',
        editSpecialSelfFee:'',
        editIsSpecialTax: '',//是否含税-专票

        editOrdinarySelfFee: '',
        editOrdinaryProxyFee: '',
        editIsOrdinaryTax: '',//是否含税-普票
      },
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
                { validator: contactPhone,required: true, trigger: 'blur' }
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
      ]
    };
  },
  mounted: function(){
    this.getAllUser();
    this.getPlaceCode();
    this.getByPage();
  },
  methods: {
    getByPage(){
        console.log("111");
      crudPlace.getByPage().then(res=>{
        console.log("res",res);
        this.tabelData=res;
        
      })
    },
    getPlaceCode(){
      crudPlace.getCode().then(res=>{
        this.ruleForm.placeCode=res.message;
        console.log("placeCode",this.ruleForm.placeCode);
      })
    },
    isDormancy(item){
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
          this.editVisible=false;
      })
    },
    //修改渠道信息
    editPlace(item){
        this.editVisible=true;
        console.log("item==",item);
        var placeCode=item.placeCode;
        agencyfee.selectFeeByCode({placeCode:placeCode}).then(res=>{
            this.ruleForm.editAgencyFeeId=res.agencyFeeId;
            this.ruleForm.editSpecialInvoice6=res.specialInvoice6;
            this.ruleForm.editSpecialInvoice13=res.specialInvoice13;
            this.ruleForm.editSpecialSelfFee=res.specialSelfFee;
            this.ruleForm.editIsSpecialTax=res.isSpecialTax;
            this.ruleForm.editOrdinarySelfFee=res.ordinarySelfFee;
            this.ruleForm.editOrdinaryProxyFee=res.ordinaryProxyFee;
            this.ruleForm.editIsOrdinaryTax=res.isOrdinaryTax;
           
        }) 
        console.log("editAgencyFeeId==",this.ruleForm.editAgencyFeeId);
        this.ruleForm.editPlaceId=item.placeId;
        this.ruleForm.editPlaceCode=item.placeCode;
        this.ruleForm.editPlaceName=item.placeName;
        this.ruleForm.editPlaceLinkman=item.placeLinkman;
        this.ruleForm.editPlaceTel=item.placeTel;
        this.ruleForm.editUserId=item.userId;
        this.ruleForm.editUserName=item.userName;

        this.ruleForm.editAgencyFeeId=item.agencyFeeId;
        this.ruleForm.editSpecialInvoice6=item.specialInvoice6;
        this.ruleForm.editSpecialInvoice13=item.specialInvoice13;
        this.ruleForm.editSpecialSelfFee=item.specialSelfFee;
        this.ruleForm.editIsSpecialTax=item.isSpecialTax;

        this.ruleForm.editOrdinarySelfFee=item.ordinarySelfFee;
        this.ruleForm.editOrdinaryProxyFee=item.ordinaryProxyFee;
        this.ruleForm.editIsOrdinaryTax=item.isOrdinaryTax;
    },
    confirmEdit(formName){
      this.$refs[formName].validate((valid) => {
          if (valid) {
              let params={
                agencyFeeId:this.ruleForm.editAgencyFeeId,
                placeId:this.ruleForm.editPlaceId,
                placeCode:this.ruleForm.editPlaceCode,
                placeName:this.ruleForm.editPlaceName,
                placeLinkman:this.ruleForm.editPlaceLinkman,
                editPlaceTel:this.ruleForm.editPlaceTel,
                userId:this.ruleForm.editUserId,
                userName:this.ruleForm.editUserName,

                specialInvoice6:this.ruleForm.editSpecialInvoice6 ,
                specialInvoice13:this.ruleForm.editSpecialInvoice13,
                specialSelfFee:this.ruleForm.editSpecialSelfFee,
                isSpecialTax:this.ruleForm.editIsSpecialTax,

                ordinarySelfFee:this.ruleForm.editOrdinarySelfFee,
                ordinaryProxyFee:this.ruleForm.editOrdinaryProxyFee,
                isOrdinaryTax:this.ruleForm.editIsOrdinaryTax,
              }
              crudPlace.editPlace(params).then((res) => {
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
                this.editVisible=false;
              })
          }else {
              this.$message({
                  message: "请填写完整",
                  type: 'warning',
              });
              return false;
          }     
      })    
    },

    //删除渠道
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    delPlace() {
      let placeCodeStr=[];
      this.multipleSelection.map((item)=> {
         placeCodeStr.push(item.placeCode); 
      })
      let params={
        placeCodes: placeCodeStr.join(',')//转换成字符串
      };
      if (this.multipleSelection.length != 0) {
        if(confirm('你确定删除吗？')){
          crudPlace.delPlace(params).then((res) => {
            if (res != undefined) {
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
            }
          })
        }
      } else {
        this.$message({
            message: '数据没有选择',
            type: 'warning',
        });
      }
    },

    //添加渠道
    addPlace(formName){
        this.addVisible=true;
        this.ruleForm.placeName=null;
        this.ruleForm.placeLinkman=null;
        this.ruleForm.placeTel=null;
        this.ruleForm.userId=null;
        this.ruleForm.userName=null;
        this.ruleForm.specialInvoice6=null;
        this.ruleForm.specialInvoice13=null;
        this.ruleForm.specialSelfFee=null;
        this.ruleForm.isSpecialTax=null;
        this.ruleForm.ordinarySelfFee=null;
        this.ruleForm.ordinaryProxyFee=null;
        this.ruleForm.isOrdinaryTax=null;
    },
    submitForm(formName){
        this.$refs[formName].validate((valid) => {
            if (valid) {
                let data={           
                    placeCode:this.ruleForm.placeCode,
                    placeName:this.ruleForm.placeName,
                    placeLinkman:this.ruleForm.placeLinkman,
                    placeTel:this.ruleForm.placeTel,
                    userId:this.ruleForm.userId,
                    specialInvoice6:this.ruleForm.specialInvoice6,
                    specialInvoice13:this.ruleForm.specialInvoice13,
                    specialSelfFee:this.ruleForm.specialSelfFee,
                    isSpecialTax:this.ruleForm.isSpecialTax,
                    ordinarySelfFee:this.ruleForm.ordinarySelfFee,
                    ordinaryProxyFee:this.ruleForm.ordinaryProxyFee,
                    isOrdinaryTax:this.ruleForm.isOrdinaryTax,
                    userName:this.ruleForm.userName,
                };
                agencyfee.add(data).then(res=>{//添加详情表
                  if(res>0){//后端捕获重复键异常
                    crudPlace.add(data)//添加主信息表
                    this.$message({
                        message: "添加成功",
                        type: 'success',
                    });
                  }else{
                    this.$message({
                        message: "编号重复，自动返回，请重新创建",
                        type: 'warning',
                    });
                  }
                  this.addVisible=false;
                  this.getPlaceCode();//重新在获取一遍编号（避免编号重复）
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
    //重置
    resetForm(formName) {
      console.log(111)
        this.$refs[formName].resetFields();
    },
    //获取所有用户
    getAllUser(){
      getAllUser().then(res=>{
        console.log("res==",res.content);
        this.userLeaders=res.content;
      })
    },
    selectUser(value){
      this.ruleForm.userName=this.userLeaders.find((item)=> item.id==value).nickName;
      this.ruleForm.editUserName=this.userLeaders.find((item)=> item.id==value).nickName;
    },
    changeStatus(value){
      this.status=value;
      console.log(this.status);
    },
    // 钩子：在获取表格数据之前执行，false 则代表不获取数据
    // [CRUD.HOOK.beforeRefresh]() {
    //   return true;
    // },
    check(item){
        console.log("checkContent",item);
        this.checkVisible=true;
        this.ruleForm.placeCode=item.placeCode;
        this.ruleForm.placeName=item.placeName;
        this.ruleForm.placeLinkman=item.placeLinkman;
        this.ruleForm.placeTel=item.placeTel;
        this.ruleForm.nickName=item.nickName;
        this.ruleForm.placeStatus=item.placeStatus;
        agencyfee.selectFeeByCode({placeCode:this.ruleForm.placeCode}).then(res=>{
            console.log("selectFeeByCode==",res);
            this.ruleForm.specialInvoice6=res.specialInvoice6;
            this.ruleForm.specialInvoice13=res.specialInvoice13;
            this.ruleForm.specialSelfFee=res.specialSelfFee;
            this.ruleForm.isSpecialTax=res.isSpecialTax;
            this.ruleForm.ordinarySelfFee=res.ordinarySelfFee;
            this.ruleForm.ordinaryProxyFee=res.ordinaryProxyFee;
            this.ruleForm.isOrdinaryTax=res.isOrdinaryTax;
            if(this.ruleForm.isOrdinaryTax){
                this.ruleForm.isOrdinaryTax="不含税"
            }else{
              this.ruleForm.isOrdinaryTax="含税"
            }
            if(this.ruleForm.isSpecialTax){
                this.ruleForm.isSpecialTax="不含税"
            }else{
              this.ruleForm.isSpecialTax="含税"
            }
        }) 
        this.ruleForm.value=item.value;
        if(this.ruleForm.placeStatus==0){
            this.ruleForm.placeStatus="正常";
        }
        if(this.ruleForm.placeStatus==1){
            this.ruleForm.placeStatus="预警";
        }
        if(this.ruleForm.placeStatus==2){
            this.ruleForm.placeStatus="休眠";
        }
        if(this.ruleForm.is_special_tax=='0'){
            this.ruleForm.is_special_tax="是";
        }
        if(this.ruleForm.is_special_tax=='1'){
            this.ruleForm.is_special_tax="否";
        }
        if(this.ruleForm.is_ordinary_tax=='0'){
            this.ruleForm.is_ordinary_tax="是";
        }
        if(this.ruleForm.is_ordinary_tax=='1'){
            this.ruleForm.is_ordinary_tax="否";
        }  
    },
    handleChange(value) {
      console.log(value);
    },
  }
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
     ::v-deep .is-disabled .el-input__inner{
        background-color: transparent !important;
        color: black;    
    }
</style>
