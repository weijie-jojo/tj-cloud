<template>
  <div class="home">
    <div class="content">
      <el-tabs type="border-card">
        <!-- 报销单tab页面 -->
        <el-tab-pane label="报销单">
          <div class="secondDiv">
            <span>单据日期</span>
              <el-date-picker
                v-model="search.startTime"
                type="date"
                placeholder="开始日期"
                value-format="yyyy-MM-dd"
              >
              </el-date-picker>
              至
              <el-date-picker
                v-model="search.endTime"
                type="date"
                placeholder="结束日期"
                value-format="yyyy-MM-dd"
              >
              </el-date-picker>
              <el-select
                v-model="search.expenseUser"
                clearable
                filterable
                placeholder="请选择人员"
                style="margin-left:20px"
              >
                <el-option
                  v-for="item in users"
                  :key="item.userId"
                  :label="item.nickName"
                  :value="item.userId">
                </el-option>
              </el-select>
              <span style="margin-left:20px">进度状态</span>
              <el-select
                v-model="search.expenseType"
                clearable
                placeholder="请选择状态"
                
              >
                <el-option
                  v-for="item in invoiceTypes"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            <el-button 
              style="margin-left:20px" 
              type="primary" 
              icon="el-icon-search" 
              @click="queryExpense"
            >查询</el-button>
          </div>
          <el-table
            ref="multipleTable"
            :data="allExpense"
            border
            stripe
            class="customer-table"
            @selection-change="handleSelectionChange"
          >
           <el-table-column type="selection" width="55" />
            <el-table-column 
              prop="nickName" 
              label="人员" 
              align="center" 
            />
            <el-table-column
              prop="createTime"
              label="单据时间"
              align="center"
            />
            <el-table-column
              prop="totalMoney"
              label="单据金额"
              align="center"
            />
            <el-table-column
              prop="dictLabel"
              label="状态"
              align="center"
            />
            <el-table-column label="操作" width="250" align="center">
              <template slot-scope="scope">
                <el-button 
                  type="primary" 
                  size="mini"
                  @click="toViewExpense(scope.row)"
                  >详情
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <div class="pagination">
            <el-pagination
              :current-page="this.currentPage"
              :page-size="this.search.limit"
              layout="total, prev, pager, next, jumper"
              :total="this.expenseCount"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
            />
          </div>
        </el-tab-pane>

        <!-- 差旅报销单tab页面 -->
        <el-tab-pane label="差旅报销单">
          <div class="secondDiv">
            <span>单据日期</span>
              <el-date-picker
                v-model="search.startTime2"
                type="date"
                placeholder="开始日期"
                value-format="yyyy-MM-dd"
              >
              </el-date-picker>
              至
              <el-date-picker
                v-model="search.endTime2"
                type="date"
                placeholder="结束日期"
                value-format="yyyy-MM-dd"
              >
              </el-date-picker>
              <el-select
                v-model="search.travelExpenseUser"
                clearable
                filterable
                placeholder="请选择人员"
                style="margin-left:20px"
              >
                <el-option
                  v-for="item in users"
                  :key="item.userId"
                  :label="item.nickName"
                  :value="item.userId">
                </el-option>
              </el-select>
              <span style="margin-left:20px">进度状态</span>
              <el-select
                v-model="search.travelExpenseType"
                clearable
                placeholder="请选择状态"
              >
                <el-option
                  v-for="item in invoiceTypes"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
            <el-button 
              style="margin-left:20px" 
              type="primary" 
              icon="el-icon-search" 
              @click="queryTravelExpense"
            >查询</el-button>
          </div>
          <el-table
            ref="multipleTable"
            :data="allTravelExpense"
            border
            stripe
            class="customer-table"
            @selection-change="handleSelectionChange2"
          >
           <el-table-column type="selection" width="55" />
            <el-table-column 
              prop="nickName" 
              label="人员" 
              align="center" 
            />
            <el-table-column
              prop="createTime"
              label="单据时间"
              align="center"
            />
            <el-table-column
              prop="totalAllMoney"
              label="单据金额"
              align="center"
            />
            <el-table-column
              prop="dictLabel"
              label="状态"
              align="center"
            />
            <el-table-column label="操作" width="250" align="center">
              <template slot-scope="scope">
                <el-button 
                  type="primary" 
                  size="mini"
                  @click="toViewTravelExpense(scope.row)"
                  >详情
                </el-button>
              </template>
            </el-table-column>
          </el-table>
            <div class="pagination">
              <el-pagination
                :current-page="this.currentPage2"
                :page-size="this.search.limit2"
                layout="total, prev, pager, next, jumper"
                :total="this.travelExpenseCount"
                @size-change="handleSizeChange2"
                @current-change="handleCurrentChange2"
              />
            </div>
        </el-tab-pane>

        <!-- 借支单tab页面 -->
        <el-tab-pane label="借支单">
          <div class="secondDiv">
            <span>单据日期</span>
              <el-date-picker
                v-model="search.startTime3"
                type="date"
                placeholder="开始日期"
                value-format="yyyy-MM-dd"
              >
              </el-date-picker>
              至
              <el-date-picker
                v-model="search.endTime3"
                type="date"
                placeholder="结束日期"
                value-format="yyyy-MM-dd"
              >
              </el-date-picker>
               <el-select
                v-model="search.borrowUser"
                clearable
                filterable
                placeholder="请选择人员"
                style="margin-left:20px"
              >
                <el-option
                  v-for="item in users"
                  :key="item.userId"
                  :label="item.nickName"
                  :value="item.userId">
                </el-option>
              </el-select>
              <span style="margin-left:20px">进度状态</span>
              <el-select
                v-model="search.borrowType"
                clearable
                placeholder="请选择状态"
              >
                <el-option
                  v-for="item in invoiceTypes"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
            <el-button 
              style="margin-left:20px" 
              type="primary" 
              icon="el-icon-search" 
              @click="queryBorrow"
            >查询</el-button>
          </div>
          <el-table
            ref="multipleTable"
            :data="allBorrow"
            border
            stripe
            class="customer-table"
            @selection-change="handleSelectionChange3"
          >
           <el-table-column type="selection" width="55" />
            <el-table-column 
              prop="nickName" 
              label="人员" 
              align="center" 
            />
            <el-table-column
              prop="borrowDate"
              label="单据时间"
              align="center"
            />
            <el-table-column
              prop="totalMoney"
              label="单据金额"
              align="center"
            />
            <el-table-column
              prop="dictLabel"
              label="状态"
              align="center"
            />
            <el-table-column label="操作" width="250" align="center">
              <template slot-scope="scope">
                <el-button 
                  type="primary" 
                  size="mini"
                  @click="toViewBorrow(scope.row)"
                  >详情
                </el-button>
              </template>
            </el-table-column>
          </el-table>
            <div class="pagination">
              <el-pagination
                :current-page="this.currentPage3"
                :page-size="this.search.limit3"
                layout="total, prev, pager, next, jumper"
                :total="this.borrowCount"
                @size-change="handleSizeChange3"
                @current-change="handleCurrentChange3"
              />
            </div>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>
<script>
import {getInfo} from '@/api/login'
// import {getDicts} from '@/api/system/dict'
import { getBorrow,editBorrowType,getAllBorrows} from "@/api/invoices/borrow";
import { getAllUser,editExpense,editExpenseType,getExpenses,getAllExpenses } from "@/api/invoices/expense";
import { getTravelExpense,editTravelExpenseType,getAllTravelExpense } from "@/api/invoices/travelExpense";
export default {
   dicts: ['invoice_type'],
  components: {},
  props: [],
  data() {
    return {
      //导入导出数据
      fileUploadBtnText: '导入数据',
      hasError: false,
      uploadBtnIcon: 'el-icon-upload2',
      enabledUploadBtn: false,
      // hotel: [],
      errMsg: [],
      parkingAreaName: '', // 明细中的停车场名
      addVisible: false,//添加设备弹窗
      addParkingVisible: false,//添加弹窗
      editVisible: false,//修改设备弹窗
      editParkingVisible:false,//修改停车场弹窗
      channelVisible: false,
      add:{//添加弹窗中的数据
        deviceId:'',//地磁设备编号
        parkingLotId:'',//车位编号
      },
      addParkingLotId:'',//车位编号
      parkAreas:[],
      parkTypes:[],
      ruleForm: {
          //添加设备弹窗中的数据
          
          parkingAreaNo:'',//停车场编号 
          deviceCode:'',//设备出厂编号
          deviceName:'',//设备名
          parkingLotName:'',//车位名
          //设备编辑弹窗值
          id:'',//地磁停车位id
          editParkingLotName:'',
          //停车场添加弹窗
          parkingAreaName:'',// 停车场名
          parkingType:'1',//停车场类型
          areaCode:'',//区域编码
          createTime:'',//创建时间
          parkingTotal:'',//车位数量
          //停车场编辑弹窗值
          editId:'',//停车场id
          editParkingType:'',//停车场类型
          editAreaCode:'',//区域编码
          editParkingTotal:'',//车位数量
         
      },

      rules: {
        parkingType: [{ required: true, message: "请选择设备类型", trigger: "change" }],
        areaCode: [{ required: true, message: "请选择归属区域", trigger: "change" }],
        parkingTotal: [
          { required: true, message: "请输入车位数量", trigger: "blur" },
          { type: 'number', message: '车位数量必须为数字值'}
        ],
        parkingAreaName: [
          { required: true, message: "请输入停车场名", trigger: "blur" },
          // { min: 3, max: 15, message: "长度在 3 到 15 个字符", trigger: "blur" },
        ],
        editParkingType: [{ required: true, message: "请选择设备类型", trigger: "change" }],
        editAreaCode: [{ required: true, message: "请选择归属区域", trigger: "change" }],
        editParkingTotal: [
          { required: true, message: "请输入车位数量", trigger: "blur" },
          { type: 'number', message: '车位数量必须为数字值'}
        ],

        deviceId: [
          { required: true, message: "请输入设备id", trigger: "blur" },
          // { min: 3, max: 15, message: "长度在 3 到 15 个字符", trigger: "blur" },
        ],
        deviceCode: [
          { required: true, message: "请输入设备出厂编号", trigger: "blur" },
          // { min: 3, max: 15, message: "长度在 3 到 15 个字符", trigger: "blur" },
        ],
        parkingLotId: [
          { required: true, message: "请输入车位编号", trigger: "blur" },
          // { min: 3, max: 15, message: "长度在 3 到 15 个字符", trigger: "blur" },
        ],
        deviceName: [
          { required: true, message: "请输入设备名", trigger: "blur" },
          // { min: 3, max: 10, message: "长度在 3 到 10 个字符", trigger: "blur" },
        ],
        parkingLotName: [
          { required: true, message: "请输入车位名", trigger: "blur" },
          // { min: 3, max:10, message: "长度在 3 到 10 个字符", trigger: "blur" },
        ],
        parkingAreaNo: [{ required: true, message: "请选择停车场", trigger: "change" }],

        editParkingLotName: [
          { required: true, message: "请输入车位名", trigger: "blur" },
          // { min: 3, max:10, message: "长度在 3 到 10 个字符", trigger: "blur" },
        ],

      },
      filterText: '',
      pagesize: 10, // 每页显示个数     默认
      disable: false,
      search: {
        expenseUser:'',
        expenseType:'',
        limit:10,
        startTime:'',
        endTime:'',

        travelExpenseUser:'',
        travelExpenseType:'',
        limit2:10,
        startTime2:'',
        endTime2:'',

        borrowUser:'',
        borrowType:'',
        limit3: 10,
        startTime3:'',
        endTime3:'',
      },
      //分页信息
      currentPage:1,
      
      currentPage2:1,

      currentPage3:1,

      expenseCount:0,//所有报销单总计
      travelExpenseCount:0,//所有差旅报销单总计
      borrowCount:0,//所有借支单总计
     
      deviceByAreaNo: [], // 根据停车场id获取对应的设备明细
      deviceParkinglotArea: [], // 地磁车位信息及其对应的停车场
      deviceArea: [], // 获取所有停车场信息(地磁设备的)
      areas:[],//所有区域信息
      parkingAreas:[],//获取所有停车场信息(不分页 添加弹窗用)
      filterText: '', 
      multipleSelection: [],
      multipleSelection2: [],
      multipleSelection3: [],
      filterText1: '',
      id: 1000,

      allExpense:[],
      allTravelExpense:[],
      allBorrow:[],
      // invoiceTypes: [{
      //   value: '0',
      //   label: '待审批'
      // }, {
      //   value: '1',
      //   label: '已审批'
      // }],
      invoiceTypes:[],
      borrows:[],
      expenses:[],
      travelExpenses:[],
      loginRoles:[],
      checkPerson:[],
      users:[],
    }
  },
  computed: {},
  watch: {
    filterText1(val) {
      var value = val
      this.$refs.tree.filter(value)
    }
  },

  mounted() {
    getInfo().then(res => {
        console.log("getInfo==",res);
        this.loginRoles=res.user.roles;
        this.checkPerson=res.user.nickName;
    })
    this.getDicts();
    this.getAllExpense();
    this.getAllTravelExpense();
    this.getAllBorrow();  
    this.getAllUser();
  },

  methods: {
    //获取所有用户
    getAllUser(){
      getAllUser().then(res=>{
        console.log("getAllUser",res);
        this.users=res;
      })
    },
    //撤回
    rollback(item){
       let params={
          expenseId: item.expenseId,
        }
         editExpenseType(params).then(res => {
            if(res!=undefined){
              this.$message({
                type:'success',
                message:"撤回成功"
              })
              this.queryExpense();
            }
        })
    },
    rollback2(item){
       let params={
          id: item.id,
        }
         editTravelExpenseType(params).then(res => {
            if(res!=undefined){
              this.$message({
                type:'success',
                message:"撤回成功"
              })
              this.queryTravelExpense();
            }   
        })
    },
    rollback3(item){
       let params={
          borrowId: item.borrowId,
        }
         editBorrowType(params).then(res => {
            if(res!=undefined){
              this.$message({
                type:'success',
                message:"撤回成功"
              })
              this.queryBorrow();
            }
        })
    },
    //获取字典表数据
    getDicts(){
      this.invoiceTypes=this.dict.type.invoice_type;
      // getDicts().then(res=>{
      //   // console.log("item==",res.find((item) => item.id == 7).dictDetails);
      //   this.invoiceTypes= res.find((item) => item.id == 7).dictDetails  
      // })
      
    },
    //查询所有报销单信息
    getAllExpense() {
        let params={
          currentPage: this.currentPage,
          limit: this.search.limit,
          startTime:this.search.startTime,
          endTime:this.search.endTime,
          invoiceType:this.search.expenseType,
          createUser:this.search.expenseUser,
        }
        getAllExpenses(params).then(res => {
            console.log("getAllExpenses",res);
            this.allExpense = res.list;
            this.expenseCount = res.count;
            this.totlePage=this.expenseCount%this.limit==0 ? this.expenseCount/this.limit :parseInt(this.expenseCount/this.limit+1);
        })
    }, 
    //查询所有差旅报销单信息
    getAllTravelExpense() {
        let params={
          currentPage: this.currentPage2,
          limit: this.search.limit2,
          startTime:this.search.startTime2,
          endTime:this.search.endTime2,
          invoiceType:this.search.travelExpenseType,
          createUser:this.search.travelExpenseUser,
        }
        getAllTravelExpense(params).then(res => {
            console.log("getCheckTravelExpense",res);
            this.allTravelExpense = res.list;
            this.travelExpenseCount = res.count;
            this.totlePage2=this.travelExpenseCount%this.limit2==0 ? this.travelExpenseCount/this.limit2 :parseInt(this.travelExpenseCount/this.limit2+1);
        })
    }, 
    //查询所有借支单信息
    getAllBorrow() {
        let params={
            currentPage: this.currentPage3,
            limit: this.search.limit3,
            startTime:this.search.startTime3,
            endTime:this.search.endTime3,
            invoiceType:this.search.borrowType,
            createUser:this.search.borrowUser,
        }
        getAllBorrows(params).then(res => {
           console.log("getCheckBorrow",res);
            this.allBorrow = res.list;
            this.borrowCount = res.count;
            this.totlePage=this.borrowCount%this.limit3==0 ? this.borrowCount/this.limit3 :parseInt(this.borrowCount/this.limit3+1);
        })
    }, 
    downExcle() {
      var tableHeader = [[ '设备编号', '设备名','设备出厂编码', '车位编号', '车位名', '停车场编号']];
      var arr=[];
      this.deviceParkinglotArea.map((item , index)=>{
        var arrChild=[];
        arrChild.push(item.deviceId);
        arrChild.push(item.deviceName);
        arrChild.push(item.deviceCode);
        arrChild.push(item.parkingLotId);
        arrChild.push(item.parkingLotName);
        arrChild.push(item.parkingAreaNo);
        arr.push(arrChild);
      });
      dataConversionUtil.dataToExcel('设备管理', tableHeader, arr)
      // this.$message.primary('设备管理模板下载成功')
    },
    fileUploadprimary(res) {
      this.enabledUploadBtn = true
      this.uploadBtnIcon = 'el-icon-upload2'
      this.fileUploadBtnText = '导入数据'
      if (res.errMsg.length > 0) {
        this.errMsg = res.errMsg
        this.hasError = true
      }
      this.$message.primary('数据导入成功！')
      this.crud.refresh()
    },
    fileUploadError() {
      this.enabledUploadBtn = true
      this.uploadBtnIcon = 'el-icon-upload2'
      this.fileUploadBtnText = '导入数据'
    },
    beforeFileUpload(file) {
      this.enabledUploadBtn = false
      this.uploadBtnIcon = 'el-icon-loading'
      this.fileUploadBtnText = '正在导入'
    },

    //查询按钮
    queryExpense() {
      this.currentPage = 1;
      this.getAllExpense();
    },
    queryTravelExpense() {
      this.currentPage2 = 1;
      this.getAllTravelExpense();
    },
    queryBorrow() {
      this.currentPage3 = 1;
      this.getAllBorrow();
    },

    //删除
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    delExpense() {
      if(confirm("你确定删除吗？")){
          let expenseIds=[];
          this.multipleSelection.map((item)=> {
            expenseIds.push(item.expenseId); 
          })
          let params={
            expenseIds:expenseIds.join(',')//转换成字符串
          };
          if (this.multipleSelection.length != 0) {
            editExpense(params).then((res) => {
              if (res != undefined) {
                  console.log("id====",res);
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
                  this.queryExpense();
              }
            })
          } else {
            this.$message.error('数据没有选择')
          }
      }
    },
    handleSelectionChange2(val) {
      this.multipleSelection2 = val
    },
    delTravelExpense() {
      if(confirm("你确定删除吗？")){
        
          let travelExpenseIds=[];
          this.multipleSelection2.map((item)=> {
            
            travelExpenseIds.push(item.id); 
          })
    
          let params={
            travelExpenseIds:travelExpenseIds.join(',')//转换成字符串
          };
          if (this.multipleSelection2.length != 0) {
            editTravelExpense(params).then((res) => {
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
                  this.queryTravelExpense();
              }
            })
          }else {
            this.$message.error('数据没有选择')
          } 
      }
    },
    handleSelectionChange3(val) {
      this.multipleSelection3 = val
       console.log("val",this.multipleSelection3);
    },
    delBorrow() {
      if(confirm("你确定删除吗？")){
          let borrowIds=[];
          this.multipleSelection3.map((item)=> {
            borrowIds.push(item.borrowId); 
          })
          console.log("ids",borrowIds.join(','));
          let params={
            borrowIds:borrowIds.join(',')//转换成字符串
          };
          if (this.multipleSelection3.length != 0) {
            editBorrow(params).then((res) => {
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
                   this.queryBorrow();
              }
            })
          } else {
            this.$message.error('数据没有选择')
          }
      }
    },
  
    // 分页
    handleSizeChange(limit) {
      this.search.limit = limit
      this.getAllExpense();
    },
    handleCurrentChange(currentPage) {
      // this.search.start = (currentPage-1)*this.search.limit
      this.currentPage=currentPage;
      this.getAllExpense();
    },
    handleSizeChange2(limit2) {
      this.search.limit2 = limit2;
      this.getAllTravelExpense()
    },
    handleCurrentChange2(currentPage) {
       this.currentPage2=currentPage;
      this.getAllTravelExpense()
    },
    handleSizeChange3(limit3) {
      this.search.limit3 = limit3
      this.getAllBorrow()
    },
    handleCurrentChange3(currentPage) {
      this.currentPage3=currentPage;
      this.getAllBorrow()
    },

    //新增单据
    addExpense(){
      this.$router.push({
        path: "/invoices/addExpense"
      });
    },
    addTravelExpense() {    
      this.$router.push({
        path: "/invoices/addTravelExpense"
      });
    },
    addBorrow() {
      this.$router.push({
        path: "/invoices/addBorrow"
      });
    },
     //查看详情
    toViewBorrow(data) {
      //通过单据编号查询单据信息
      getBorrow({borrowCode:data.borrowCode,
        currentPage:1,limit:1 
        }).then(res=>{
          this.borrows=res;
          console.log("borrows==",this.borrows);
          window.localStorage.setItem("borrows", JSON.stringify(this.borrows));
          window.localStorage.setItem("borrowRoles", JSON.stringify(this.loginRoles));
          window.localStorage.setItem("borrowCheckPerson", JSON.stringify(this.checkPerson));
          this.$router.push({
              path: "/invoices/showBorrow"
          });
        }) 
      },
    toViewExpense(data) {
      //通过单据编号查询单据信息
      getExpenses({expenseCode:data.expenseCode,
        currentPage:1,limit:1 
        }).then(res=>{
          this.expenses=res;
          console.log("expenses==",this.expenses);
          window.localStorage.setItem("expenses", JSON.stringify(this.expenses));
          window.localStorage.setItem("expenseRoles", JSON.stringify(this.loginRoles));
          window.localStorage.setItem("expenseCheckPerson", JSON.stringify(this.checkPerson));
          this.$router.push({
              path: "/invoices/showExpense"
          });
        }) 
      },
    toViewTravelExpense(data) {
      //通过单据编号查询单据信息
      getTravelExpense({travelExpenseCode:data.travelExpenseCode,
        currentPage:1,limit:1 
        }).then(res=>{
          this.travelExpenses=res;
          console.log("travelExpenses==",this.travelExpenses);
          window.localStorage.setItem("travelExpenses", JSON.stringify(this.travelExpenses));
          window.localStorage.setItem("travelExpenseRoles", JSON.stringify(this.loginRoles));
          window.localStorage.setItem("travelExpenseCheckPerson", JSON.stringify(this.checkPerson));
          this.$router.push({
              path: "/invoices/showTravelExpense"
          });
        }) 
      },
  }
}
</script>
<style lang="scss" scoped>
.tree {
  width: 20%;
  // min-height: 100vh;
  height: 83vh;
  margin: 10px;
  display: inline-block;
  background-color: #fff;
  overflow: auto;
}
.content {
  width: 99%;
  height: 84vh;
  display: inline-block;
  vertical-align: top;
  background-color: #fff;
  margin: 0px 10px 0 10px;
}
.videoTitle {
  font-size: 15px;
  padding: 10px 0;
}

.videoContent {
  width: 100%;
  height: 100%;
}

.label {
  margin: 7px;
  padding: 7px;
}
.daterange {
  width: 300px;
}
.week {
  width: 160px;
}
.year {
  width: 160px;
}
::v-deep .el-range-separator {
  width: 8%;
}
.select {
  width: 250px;
}
.count {
  width: 150px;
}
::v-deep .el-tree-node__content:hover {
}
.firstDiv {
  height: 50px;
  margin-top: 20px;
  ::v-deep .el-button {
    margin: 0 10px 0 10px;
  }
}
.secondDiv {
  height: 50px;
  margin-top:20px;
  span {
    margin: 0 10px 0 10px;
  }
}
.secondDiv2 {
  height: 50px;
  margin-top:10px;
  span {
    margin: 0 10px 0 10px;
  }
}
.pagination {
  float: right;
  margin-top: 30px;
}
.custom-tree-node {
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;

  button {
    padding-left: 10px;
  }
}
.custom-tree-node:hover {
  .append :hover {
    color: #b4b4b4;
    font-size: 20px;
    font-weight: bold;
  }
}
.append {
  font-size: 20px;
  font-weight: bold;
}
::v-deep .el-popover {
  top: 60px;
  left: 125px;
}
.btn{
  margin-top:100px;
  margin-left: -100px
}
.formCss{
  // margin-top:30px;
  margin-left: 40px;
}
.inputCss{
  width: 400px;
}
</style>
