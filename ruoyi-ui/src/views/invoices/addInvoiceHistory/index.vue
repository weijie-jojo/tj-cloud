<template>
  <div class="home">
    <div  class="app-container" style="padding-top:5px">
      <el-tabs >
        <!-- 报销单tab页面 -->
        <el-tab-pane :label="firstLabel">
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
              <span style="margin-left:20px">进度状态</span>
              <el-select
                v-model="search.stepType"
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
              @click="queryExpense"
            >查询</el-button>
          </div>
          
          <el-table
            ref="multipleTable"
            :data="allExpense"
            
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
              label="单据状态"
              align="center"
            />
           <el-table-column 
              label="进度状态" 
              align="center">
              <template slot-scope="scope">
                <el-link 
                  :underline="false" 
                  type="primary"
                  v-if="scope.row.invoiceType != 5 && scope.row.invoiceType != 6"
                  @click="getCheck(scope.row.expenseCode)">办理中
                </el-link>
                <el-link :underline="false" type="danger"
                  v-if="scope.row.invoiceType == 6"
                  @click="getCheck(scope.row.expenseCode)">异常</el-link>
                <el-link :underline="false" type="success"
                  v-if="scope.row.invoiceType == 5 "
                  @click="getCheck(scope.row.expenseCode)">完成</el-link>
              </template>
            </el-table-column>
            <el-table-column label="主管" align="center">
              <template slot-scope="scope">
                  <el-link :underline="false" type="primary"
                    v-if="scope.row.invoiceType == 1" @click="expenseHandle(1)">办理中
                  </el-link>
                  <el-link :underline="false" type="success" 
                    @click="expenseFinsh(scope.row.expenseCode,1)"
                    v-if="scope.row.gmCheck != ''  && scope.row.invoiceType == 6">完成</el-link>
                  <el-link :underline="false" type="danger" 
                  @click="expenseDanger(scope.row.expenseCode,1)"
                    v-if="scope.row.gmCheck == '' && scope.row.dmCheck != '' && scope.row.invoiceType == 6">异常</el-link>

                  <el-link :underline="false" type="success"
                  @click="expenseFinsh(scope.row.expenseCode,1)"
                    v-if="scope.row.invoiceType >=2 && scope.row.invoiceType != 6">完成</el-link>
              </template>
            </el-table-column>
            <el-table-column label="总经办" align="center">
              <template slot-scope="scope">
                <el-link  :underline="false" type="info"
                  v-if="scope.row.invoiceType == 1">未开始
                </el-link>
                <el-link  :underline="false" type="primary"
                  v-if="scope.row.invoiceType == 2" @click="expenseHandle(2)">办理中
                </el-link>
        
                <el-link :underline="false" type="info" 
                  v-if="scope.row.financeCheck == '' && scope.row.gmCheck == '' && scope.row.invoiceType == 6">未开始</el-link>
                <el-link :underline="false" type="danger" 
                @click="expenseDanger(scope.row.expenseCode,2)"
                  v-if="scope.row.financeCheck == '' && scope.row.gmCheck != '' && scope.row.invoiceType == 6">异常</el-link>
                <el-link :underline="false" type="success"
                @click="expenseFinsh(scope.row.expenseCode,2)"
                  v-if="scope.row.financeCheck != ''  && scope.row.invoiceType == 6">完成</el-link>

                <el-link :underline="false" type="success"
                @click="expenseFinsh(scope.row.expenseCode,2)"
                  v-if="scope.row.invoiceType >=3 && scope.row.invoiceType != 6">完成</el-link>
              </template>
            </el-table-column>
            <el-table-column label="财务" align="center">
              <template slot-scope="scope">
                <el-link :underline="false" type="info"
                  v-if="scope.row.invoiceType <= 2">未开始
                </el-link>
                <el-link :underline="false" type="primary"
                  v-if="scope.row.invoiceType == 3" @click="expenseHandle(3)">办理中
                </el-link>

                <el-link :underline="false" type="danger" 
                 @click="expenseDanger(scope.row.expenseCode,3)"
                  v-if="scope.row.financeCheck != '' && scope.row.invoiceType == 6">异常</el-link>
                <el-link :underline="false" type="info" 
                  v-if="scope.row.financeCheck == '' && scope.row.invoiceType == 6">未开始</el-link>

                <el-link :underline="false" type="success"
                @click="expenseFinsh(scope.row.expenseCode,3)"
                  v-if="scope.row.invoiceType == 5">完成</el-link>
              </template>
            </el-table-column>
            <el-table-column label="操作" width="250" align="center">
              <template slot-scope="scope">
                <el-button
                size="mini" type="text" icon="el-icon-view"
                  @click="toViewExpense(scope.row)"
                >详情</el-button>
               
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
        <!-- 上传还款凭证影像 -->
            <el-dialog title="上传图片" :visible.sync="imgDialog" width="30%">
                <el-upload
                    action="/eladmin/api/files/doUpload"
                    list-type="picture-card"
                    :on-preview="handlePictureCardPreview"
                    :on-remove="handleRemove"
                    :on-success="success"
                    :limit=limitNum>
                    <i class="el-icon-plus"></i>
                </el-upload>
                <el-dialog :visible.sync="dialogVisible">
                    <img width="100%" :src="dialogImageUrl" alt="">
                </el-dialog>
                <el-button 
                    size="small" 
                    type="primary" 
                    @click="cancel" 
                class="btn2">关闭</el-button>
            </el-dialog>
        <!-- 差旅报销单tab页面 -->
        <el-tab-pane :label="secondLabel">
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
              <span style="margin-left:20px">进度状态</span>
              <el-select
                v-model="search.stepType"
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
              label="单据状态"
              align="center"
            />
              <el-table-column label="进度状态" align="center">
              <template slot-scope="scope">
                <el-link 
                  :underline="false" 
                  type="primary"
                  v-if="scope.row.invoiceType != 5 && scope.row.invoiceType != 6"
                  @click="getCheck(scope.row.travelExpenseCode)">办理中
                </el-link>
                <el-link :underline="false" type="danger"
                  v-if="scope.row.invoiceType == 6"
                  @click="getCheck(scope.row.travelExpenseCode)">异常</el-link>
                <el-link :underline="false" type="success"
                  v-if="scope.row.invoiceType == 5 "
                  @click="getCheck(scope.row.travelExpenseCode)">完成</el-link>
              </template>
            </el-table-column>
            <el-table-column label="主管" align="center">
              <template slot-scope="scope">
                  <el-link :underline="false" type="primary"
                    v-if="scope.row.invoiceType == 1" @click="expenseHandle(1)">办理中
                  </el-link>

                  <el-link :underline="false" type="success" 
                      @click="expenseFinsh(scope.row.travelExpenseCode,1)"
                    v-if="scope.row.gmCheck != ''  && scope.row.invoiceType == 6">完成</el-link>
                  <el-link :underline="false" type="danger"
                  @click="expenseDanger(scope.row.travelExpenseCode,1)" 
                    v-if="scope.row.gmCheck == '' && scope.row.dmCheck != '' && scope.row.invoiceType == 6">异常</el-link>

                  <el-link :underline="false" type="success"
                  @click="expenseFinsh(scope.row.travelExpenseCode,1)"
                    v-if="scope.row.invoiceType >=2 && scope.row.invoiceType != 6">完成</el-link>
              </template>
            </el-table-column>
            <el-table-column label="总经办" align="center">
              <template slot-scope="scope">
                <el-link  :underline="false" type="info"
                  v-if="scope.row.invoiceType == 1">未开始
                </el-link>
                <el-link  :underline="false" type="primary"
                  v-if="scope.row.invoiceType == 2" @click="expenseHandle(2)">办理中
                </el-link>
        
                <el-link :underline="false" type="info" 
                  v-if="scope.row.financeCheck == '' && scope.row.gmCheck == '' && scope.row.invoiceType == 6">未开始</el-link>
                <el-link :underline="false" type="danger" 
                @click="expenseDanger(scope.row.travelExpenseCode,2)" 
                  v-if="scope.row.financeCheck == '' && scope.row.gmCheck != '' && scope.row.invoiceType == 6">异常</el-link>
                <el-link :underline="false" type="success"
                @click="expenseFinsh(scope.row.travelExpenseCode,2)"
                  v-if="scope.row.financeCheck != ''  && scope.row.invoiceType == 6">完成</el-link>

                <el-link :underline="false" type="success"
                @click="expenseFinsh(scope.row.travelExpenseCode,2)"
                  v-if="scope.row.invoiceType >=3 && scope.row.invoiceType != 6">完成</el-link>
              </template>
            </el-table-column>
            <el-table-column label="财务" align="center">
              <template slot-scope="scope">
                <el-link :underline="false" type="info"
                  v-if="scope.row.invoiceType <= 2">未开始
                </el-link>
                <el-link :underline="false" type="primary"
                  v-if="scope.row.invoiceType == 3" @click="expenseHandle(3)">办理中
                </el-link>

                <el-link :underline="false" type="danger" 
                @click="expenseDanger(scope.row.travelExpenseCode,3)" 
                  v-if="scope.row.financeCheck != '' && scope.row.invoiceType == 6">异常</el-link>
                <el-link :underline="false" type="info" 
                  v-if="scope.row.financeCheck == '' && scope.row.invoiceType == 6">未开始</el-link>

                <el-link :underline="false" type="success"
                @click="expenseFinsh(scope.row.travelExpenseCode,3)"
                  v-if="scope.row.invoiceType == 5">完成</el-link>
              </template>
            </el-table-column>
            <el-table-column label="操作" width="250" align="center">
              <template slot-scope="scope">
                <el-button
                size="mini" type="text" icon="el-icon-view"
                  @click="toViewTravelExpense(scope.row)"
                >详情</el-button>
                
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
        <el-tab-pane :label="thirdLabel">
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
              <span style="margin-left:20px">进度状态</span>
              <el-select
                v-model="search.stepType"
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
            
            stripe
            class="customer-table"
            @selection-change="handleSelectionChange3"
          >
           <el-table-column type="selection" width="55" />
            <el-table-column 
              prop="nickName" 
              label="人员" 
              align="center" 
              width="200"
            />
            <el-table-column
              prop="borrowDate"
              label="单据时间"
              align="center"
              width="300"
            />
            <el-table-column
              prop="totalMoney"
              label="单据金额"
              align="center"
            />
            <el-table-column
              prop="dictLabel"
              label="单据状态"
              align="center"
            />
             <el-table-column label="进度状态" align="center">
              <template slot-scope="scope">
                <el-link :underline="false" type="primary"
                  v-if="scope.row.invoiceType != 5 && scope.row.invoiceType != 6"
                  @click="getCheck(scope.row.borrowCode)">办理中
                </el-link>
                <el-link :underline="false" type="danger"
                  v-if="scope.row.invoiceType == 6"
                  @click="getCheck(scope.row.borrowCode)">异常</el-link>
                <el-link :underline="false" type="success"
                  v-if="scope.row.invoiceType == 5 "
                  @click="getCheck(scope.row.borrowCode)">完成</el-link>
              </template>
            </el-table-column>
            <el-table-column label="主管" align="center">
              <template slot-scope="scope">
                  <el-link :underline="false" type="primary"
                    v-if="scope.row.invoiceType == 1" @click="expenseHandle(1)">办理中
                  </el-link>

                  <el-link :underline="false" type="success" 
                    @click="expenseFinsh(scope.row.borrowCode,1)"
                    v-if="scope.row.gmCheck != ''  && scope.row.invoiceType == 6">完成</el-link>
                  <el-link :underline="false" type="danger" 
                  @click="expenseDanger(scope.row.borrowCode,1)" 
                    v-if="scope.row.gmCheck == '' && scope.row.dmCheck != '' && scope.row.invoiceType == 6">异常</el-link>

                  <el-link :underline="false" type="success"
                  @click="expenseFinsh(scope.row.borrowCode,1)"
                    v-if="scope.row.invoiceType >=2 && scope.row.invoiceType != 6">完成</el-link>
              </template>
            </el-table-column>
            <el-table-column label="总经办" align="center">
              <template slot-scope="scope">
                <el-link  :underline="false" type="info"
                  v-if="scope.row.invoiceType == 1">未开始
                </el-link>
                <el-link  :underline="false" type="primary"
                  v-if="scope.row.invoiceType == 2" @click="expenseHandle(2)">办理中
                </el-link>
        
                <el-link :underline="false" type="info" 
                  v-if="scope.row.financeCheck == '' && scope.row.gmCheck == '' && scope.row.invoiceType == 6">未开始</el-link>
                <el-link :underline="false" type="danger" 
                @click="expenseDanger(scope.row.borrowCode,2)" 
                  v-if="scope.row.financeCheck == '' && scope.row.gmCheck != '' && scope.row.invoiceType == 6">异常</el-link>
                <el-link :underline="false" type="success"
                @click="expenseFinsh(scope.row.borrowCode,2)"
                  v-if="scope.row.financeCheck != ''  && scope.row.invoiceType == 6">完成</el-link>

                <el-link :underline="false" type="success"
                @click="expenseFinsh(scope.row.borrowCode,2)"
                  v-if="scope.row.invoiceType >=3 && scope.row.invoiceType != 6">完成</el-link>
              </template>
            </el-table-column>
            <el-table-column label="财务" align="center">
              <template slot-scope="scope">
                <el-link :underline="false" type="info"
                  v-if="scope.row.invoiceType <= 2">未开始
                </el-link>
                <el-link :underline="false" type="primary"
                  v-if="scope.row.invoiceType == 3" @click="expenseHandle(3)">办理中
                </el-link>

                <el-link :underline="false" type="danger" 
                @click="expenseDanger(scope.row.borrowCode,3)" 
                  v-if="scope.row.financeCheck != '' && scope.row.invoiceType == 6">异常</el-link>
                <el-link :underline="false" type="info" 
                  v-if="scope.row.financeCheck == '' && scope.row.invoiceType == 6">未开始</el-link>

                <el-link :underline="false" type="success"
                @click="expenseFinsh(scope.row.borrowCode,3)"
                  v-if="scope.row.invoiceType == 4 || scope.row.invoiceType == 5">完成</el-link>
              </template>
            </el-table-column>
            <el-table-column label="借支人" align="center">
              <template slot-scope="scope">
                <el-link :underline="false" type="info"
                  v-if="scope.row.invoiceType <= 3">未开始
                </el-link>
                <el-link :underline="false" type="primary"
                  v-if="scope.row.invoiceType == 4">办理中
                </el-link>
                <el-link :underline="false" type="success"
                @click="expenseFinsh(scope.row.borrowCode,4)"
                  v-if="scope.row.invoiceType == 5">完成</el-link>

                <el-link :underline="false" type="info"
                  v-if="scope.row.invoiceType == 6">未开始
                </el-link>
              </template>
            </el-table-column>
            <el-table-column label="操作" width="400" align="center">
              <template slot-scope="scope">
                <el-button
                size="mini" type="text" icon="el-icon-view"
                  @click="toViewBorrow(scope.row)"
                >详情</el-button>
                
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
      <!-- 进度提示 -->
      <el-dialog :closeOnClickModal=false :closeOnPressEscape=false title="操作日志" :visible.sync="checkVisible"
          width="70%">
          <el-table :data="checks">
            <el-table-column label="操作步骤" align="center" prop="checkReasult" :show-overflow-tooltip="true" />
            <el-table-column label="操作时间" align="center" prop="checkDate" width="180" />
            <el-table-column label="操作用户" align="center" prop="checkUser" />
          </el-table>
          <span slot="footer" class="dialog-footer">
            <el-button @click="checkVisible = false">关闭</el-button>
          </span>
        </el-dialog>  

        
    </div>
  </div>
</template>
<script>
// import initDict from '@/mixins/initDict'
import {getInfo} from '@/api/login'
// import initDict,{getDicts} from '@/api/system/dict'
import {addCheckInvoices,getAllCheck} from '@/api/invoices/checkInvoices'
import {getBorrow, getBorrow2,editBorrowType,editBorrow,editBorrow2} from "@/api/invoices/borrow";
import { editExpense,getExpenses,getExpenses2,editExpenseType,getLeaderByUserId } from "@/api/invoices/expense";
import {getTravelExpense,getTravelExpense2,editTravelExpenseType,editTravelExpense } from "@/api/invoices/travelExpense";
export default {
  dicts: ['step_type'],
  name:'AddInvoiceHistory',
  // mixins: [initDict],
  components: {},
  props: [],
  data() {
    return {
      firstLabel:'费用常报销单',
      secondLabel:'差旅报销单',
      thirdLabel:'借支单',
      userinfo:'',
      errArrName:'',
      checkLabel:'',
      datainfo:{},
      checks:'',
      checkVisible:false,
      messageVisible:false,
      message:'',

      borrowId:'',
      //上传
      limitNum:10,
      imgDialog:false,
      borrowImage:'',
      dialogImageUrl: '',
      dialogVisible: false,

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
        stepType:'',

        expenseType:'',
        limit: 10,
        startTime:'',
        endTime:'',

        travelExpenseType:'',
        limit2:10,
        startTime2:'',
        endTime2:'',

        borrowType:'',
        limit3:10,
        startTime3:'',
        endTime3:'',
      },
      //分页信息
      currentPage:1,
      totalPage:'',
      
      currentPage2:1,
      totalPage2:'',

      currentPage3:1,
      totalPage3:'',

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
      invoiceTypes:[],
      borrows:[],
      checkPerson:[],

      borrows:[],
      expenses:[],
      travelExpenses:[],
      loginRoles:[],
      checkPerson:[],
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
        this.userinfo=res.user;
        this.loginRoles=res.user.roles;
        this.checkPerson=res.user.nickName;
    })
    this.getDicts();
    this.getAllExpense();
    this.getAllTravelExpense();
    this.getAllBorrow();  
  },

  methods: {
    //主管  财务 总经办 办理中
    expenseHandle(type){
      let params={
        type:type
      };
      getLeaderByUserId(params).then(res=>{

          console.log(res);
          if(type==1){
          this.datainfo=res[0];
          }else if(type==2){
            this.datainfo=res[2];
          }else if(type==3){
            this.datainfo=res[0];
          }else if(type==4){
            this.datainfo=res[0];
          }
             const h = this.$createElement;
          this.$confirm(
            '', {
            message: h('div', null, [
              h('i', { class: 'el-icon-question', style: 'color:#f90;font-size:30px;' }),
              h('span', { style: 'margin-left:10px;font-size:16px;line-height:30px;font-weight:600;vertical-align:top;' }, '温馨提示'),
              
              h('p', { style: 'margin:5px 0 0 40px;' }, '请等待' + this.datainfo.nickName + '(' + this.datainfo.phonenumber + ')' + '审核')
            ]),



            confirmButtonText: '确定',
           // cancelButtonText: '关闭',
            closeOnClickModal: false,
            closeOnPressEscape: false,

          }).then(() => {
            // if (this.errNameMsg == '修改') {
            //    this.$tab.openPage("个体户名称修改", "/company/customer/editEmployedName")
            // } else {
            //    this.$tab.openPage("个体户名称查看", "/company/customer/namedetail")
            // }
          }).catch(() => {

          });

        })
      
    },
    expenseDanger(expenseCode,type){
      getAllCheck({invoiceCode:expenseCode}).then(res=>{
        console.log('selectAllCheck==',res);
        
        let arr=res;
        let brr=[];
        this.checkLabel=null;
        if(type==1){
          this.checkLabel='部门主管驳回';          
        }else if(type==2) {
          this.checkLabel='总经理驳回';
        }else if(type==3){
          this.checkLabel='财务驳回';
        }else if(type==4){
          this.checkLabel='借支人驳回';    
        }
        for( let i in arr){
            if(arr[i].checkReasult.indexOf(this.checkLabel) !==-1){
              brr.push(arr[i]);
            }
        }
      
        this.errArrName = brr[brr.length-1].checkReasult;
        const h = this.$createElement;
          this.$confirm(
            '', {
            message: h('div', null, [
              h('i', { class: 'el-icon-question', style: 'color:#f90;font-size:30px;' }),
              h('span', { style: 'margin-left:10px;font-size:16px;line-height:30px;font-weight:600;vertical-align:top;' }, '温馨提示'),
              h('P', { style: 'margin:20px 0 0 40px;' }, this.errArrName),
              // h('p', { style: 'margin:5px 0 0 40px;' }, '请等待' + this.userinfo.nickName + '(' + this.userinfo.phonenumber + ')' + '修改')
            ]),



            confirmButtonText: '确定',
            // cancelButtonText: '关闭',
            closeOnClickModal: false,
            closeOnPressEscape: false,

          }).then(() => {
           // this.$tab.openPage("个体户名称修改", "/company/customer/editEmployedName")
            // if (this.errNameMsg == '修改') {
            //    this.$tab.openPage("个体户名称修改", "/company/customer/editEmployedName")
            // } else {
            //    this.$tab.openPage("个体户名称查看", "/company/customer/namedetail")
            // }
          }).catch(() => {

          });

        

       
      })
     
    },
    expenseFinsh(expenseCode,type){
      getAllCheck({invoiceCode:expenseCode}).then(res=>{
        console.log('selectAllCheck==',res);
        
        let arr=res;
        let brr=[];
        this.checkLabel=null;
        if(type==1){
          this.checkLabel='部门主管';          
        }else if(type==2) {
          this.checkLabel='总经理';
        }else if(type==3){
          this.checkLabel='财务';
        }else if(type==4){
          this.checkLabel='借支';    
        }
        for( let i in arr){
            if(arr[i].checkReasult.indexOf(this.checkLabel) !==-1){
              brr.push(arr[i]);
            }
        }
        this.checks = brr;
        this.checkVisible=true;
      })
    },
    getCheck(expenseCode){
      console.log('1111111');
      this.checkVisible=true;
      getAllCheck({invoiceCode:expenseCode}).then(res=>{
        console.log('selectAllCheck==',res);
        this.checks = res;
      })
    },
    pz(item){
      if(item.invoiceType==4){
        this.imgDialog=true;
        this.borrowId=item.borrowId;
      }else{
        this.$message({
          type:'warning',
          message:"只有还款状态才能上传凭证！"
        })
      }
      
    },
    //编辑
    editExpense(data){
       //通过单据编号查询单据信息
      getExpenses({expenseCode:data.expenseCode,
        currentPage:1,limit:1 
      }).then(res=>{
        this.expenses=res;
        console.log("expenses333==",this.expenses);
        window.localStorage.setItem("expenses", JSON.stringify(this.expenses));
        window.localStorage.setItem("expenseRoles", JSON.stringify(this.loginRoles));
        window.localStorage.setItem("expenseCheckPerson", JSON.stringify(this.checkPerson));
        if(data.invoiceType==1){
          this.$router.push({
            path: "/invoices/editExpense"
          });
        }else{
          this.$message({
            type:'warning',
            message:"只有发起状态能修改！"
          })
        }
      }) 
      
    },
    editBorrow(data){
      //通过单据编号查询单据信息
      getBorrow({borrowCode:data.borrowCode,
        currentPage:1,limit:1 
        }).then(res=>{
          this.borrows=res;
          console.log("borrows==",this.borrows);
          window.localStorage.setItem("borrows", JSON.stringify(this.borrows));
          window.localStorage.setItem("borrowRoles", JSON.stringify(this.loginRoles));
          window.localStorage.setItem("borrowCheckPerson", JSON.stringify(this.checkPerson));
          if(data.invoiceType==1){
            this.$router.push({
              path: "/invoices/editBorrow"
            });
          }else{
            this.$message({
              type:'warning',
              message:"只有发起状态能修改！"
            })
          }
        }) 
    },
    editTravelExpense(data){
      //通过单据编号查询单据信息
      getTravelExpense({travelExpenseCode:data.travelExpenseCode,
        currentPage:1,limit:1 
        }).then(res=>{
          this.travelExpenses=res;
          console.log("travelExpenses==",this.travelExpenses);
          window.localStorage.setItem("travelExpenses", JSON.stringify(this.travelExpenses));
          window.localStorage.setItem("travelExpenseRoles", JSON.stringify(this.loginRoles));
          window.localStorage.setItem("travelExpenseCheckPerson", JSON.stringify(this.checkPerson));
          if(data.invoiceType==1){
            this.$router.push({
              path: "/invoices/editTravelExpense"
            });
          }else{
            this.$message({
              type:'warning',
              message:"只有发起状态能修改！"
            })
          }
        }) 
     
    },
   
    //撤回
    rollback(item){
      console.log("item",item)
      if(item.invoiceType<=3){
        let params={
          expenseId: item.expenseId,
        }
        let params2={
          invoiceCode:item.expenseCode,
          checkReasult:"撤回",
          checkUser:item.userName,
          checkDate:this.returnTime(new Date()),
          invoiceType:1,//单据类别
        }
        editExpenseType(params).then(res => {
          if(res!=undefined){
            this.$message({
              type:'success',
              message:"撤回成功"
            })   
          }
          this.queryExpense();
        });
        addCheckInvoices(params2).then(res => {
            this.$message({
                message: "撤回成功",
                type: 'success',
            });
            this.queryExpense();
        });
        
      }else{
        this.$message({
          type:'warning',
          message:"打款后的状态不能撤回！"
        })
      }
       
    },
    rollback2(item){
       if(item.invoiceType<=3){
        let params={
          id: item.id,
        }
        let params2={
          invoiceCode:item.travelExpenseCode,
          checkReasult:"撤回",
          checkUser:item.userName,
          checkDate:this.returnTime(new Date()),
          invoiceType:2,//单据类别
        }
         editTravelExpenseType(params).then(res => {
            if(res!=undefined){
              this.$message({
                type:'success',
                message:"撤回成功"
              })
            }
            this.queryTravelExpense();   
        })
        addCheckInvoices(params2).then(res => {
            this.$message({
                message: "撤回成功",
                type: 'success',
            });
            this.queryTravelExpense();
        });
         
      }else{
        this.$message({
          type:'warning',
          message:"打款后的状态不能撤回！"
        })
      }
       
    },
    rollback3(item){
       if(item.invoiceType<=3){
         let params={
          borrowId: item.borrowId,

        }
        let params2={
          invoiceCode:item.borrowCode,
          checkReasult:"撤回",
          checkUser:item.userName,
          checkDate:this.returnTime(new Date()),
          invoiceType:3,//单据类别
        }
         editBorrowType(params).then(res => {
            if(res!=undefined){
              this.$message({
                type:'success',
                message:"撤回成功"
              })
            }
            this.queryBorrow();
        })
        addCheckInvoices(params2).then(res => {
            this.$message({
                message: "撤回成功",
                type: 'success',
            });
            this.queryBorrow();
        });
         
      }else{
        this.$message({
          type:'warning',
          message:"打款后的状态不能撤回！"
        })
      }
       
    },
    //获取字典表数据
    getDicts(){
      this.invoiceTypes=this.dict.type.step_type;
      console.log("stepType",this.dict.type.step_type);  
      // getDicts().then(res=>{
      //   this.invoiceTypes= this.dict.type.invoice_type.find((item) => item.id == 7).dictDetails;
      //   console.log("invoiceTypes",this.invoiceTypes);  
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
          stepType:this.search.stepType,
        }
        getExpenses2(params).then(res => {
            console.log("getAllExpense",res);
            this.allExpense = res.list;
            this.expenseCount = res.count;
            this.firstLabel='费用报销单'+'('+res.count+')';
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
          stepType:this.search.stepType,
        }
        getTravelExpense2(params).then(res => {
            console.log("getAllTravelExpense",res);
            this.allTravelExpense = res.list;
            this.travelExpenseCount = res.count;
            this.secondLabel='差旅报销单'+'('+res.count+')';
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
            stepType:this.search.stepType,
        }
        getBorrow2(params).then(res => {
           console.log("getAllBorrow",res);
            this.allBorrow = res.list;
            this.borrowCount = res.count;
            this.thirdLabel='借支单'+'('+res.count+')';
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
      this.multipleSelection.map(item=>{
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
      })
      
    },
    handleSelectionChange2(val) {
      this.multipleSelection2 = val
    },
    delTravelExpense() {
      this.multipleSelection2.map(item=>{
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
      })
      
    },
    handleSelectionChange3(val) {
      this.multipleSelection3 = val
       
    },
    delBorrow() {
      this.multipleSelection3.map(item=>{
         if(item.invoiceType==1){
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
        }else{
          this.$message({
            type:'warning',
            message:"只有发起状态能修改！"
          })
        }
      })   
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
      this.loginRoles.map(item=>{
        console.log("role==", item);
        if(item.roleId ==1){//管理员
          this.$router.push({
            path: "/invoices/addExpenseAdmin"
          });
        }else{
          this.$router.push({
            path: "/invoices/addExpense"
          });
        }
      })
    },
    addTravelExpense() {    
      this.loginRoles.map(item=>{
        console.log("role==", item);
        if(item.roleId ==1){//管理员
          this.$router.push({
            path: "/invoices/addTravelExpenseAdmin"
          });
        }else{
          this.$router.push({
            path: "/invoices/addTravelExpense"
          });
        }
      })
    },
    addBorrow() {
      this.loginRoles.map(item=>{
        console.log("role==", item);
        if(item.roleId ==1){//管理员
          this.$router.push({
            path: "/invoices/addBorrowAdmin"
          });
        }else{
          this.$router.push({
            path: "/invoices/addBorrow"
          });
        }
      })
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
          let obj={
           url:'/invoices/addInvoiceHistory'
          };
         this.$cache.local.setJSON('invoicesBackUrl', obj);
          this.$tab.openPage('借支单详情',"/invoices/showBorrow");
          
        }) 
      },
    toViewExpense(data) {
      //通过单据编号查询单据信息
      getExpenses({expenseCode:data.expenseCode,
        currentPage:1,limit:1 
        }).then(res=>{
          this.expenses=res;
          console.log("expensesvvv==",this.expenses);
          window.localStorage.setItem("expenses", JSON.stringify(this.expenses));
          window.localStorage.setItem("expenseRoles", JSON.stringify(this.loginRoles));
          window.localStorage.setItem("expenseCheckPerson", JSON.stringify(this.checkPerson));
          console.log("asa");
         let obj={
          url:'/invoices/addInvoiceHistory'
         };
         this.$cache.local.setJSON('invoicesBackUrl', obj);
          this.$tab.openPage('费用报销单详情',"/invoices/showExpense");
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
          let obj={
          url:'/invoices/addInvoiceHistory'
         };
          this.$cache.local.setJSON('invoicesBackUrl', obj);
          this.$tab.openPage('差旅报销单详情',"/invoices/showTravelExpense");
         
        }) 
      },
      //返回当前时间
      returnTime(time2){
          var time=new Date(time2);
            if(time2==""){
              return "";
          }
          return time.toLocaleDateString();
      },
       //上传图片
        handleRemove(file, fileList) {
            this.borrowImage="";
            this.$message("取消上传");
            console.log("borrowImage==", this.borrowImage); 
        },
        //点+可以放大查看图片
        handlePictureCardPreview(file) {
            this.dialogImageUrl = file.url;
            this.dialogVisible = true;
        },
        success(file) {
            this.$message(file.message);
            var fileUrl = file.obj;
            this.borrowImage = fileUrl;
            console.log("fileUrl=="+this.borrowImage);
            editBorrow2({borrowImage2:this.borrowImage,
            borrowId:this.borrowId,invoiceType:5}).then(res=>{
                this.getAllBorrow();  
                this.$message({
                  type:'success',
                  message:"上传成功"
                })
            })
        },
        //取消按钮
        cancel() {
            this.imgDialog = false;
        },
  }
}
</script>
<style lang="scss" scoped>
// .home {
//   width: 100%;
//   min-height: calc(100vh - 6.4rem);
//   background-color: #dddddd;
// }
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
  // text-align: center;
  // color: #000;
  padding: 10px 0;
  // border-bottom: 1px solid #000;
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
  margin-top: 5px;
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
// .append :hover {
//   color: red;
// }
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
::v-deep .el-message-box__content {
  height: 200px !important;
}

::v-deep .el-tabs__nav-wrap::after {
  background-color: rgba(0, 0, 0, 0) !important;
}
</style>
