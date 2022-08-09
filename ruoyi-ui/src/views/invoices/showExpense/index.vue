<template>
    <div class="app-container">
        <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="auto" id="printMe">
            <el-row type="flex" class="row-bg" justify="end">
                <el-col :span="6" style="display: flex;justify-content: flex-end;">
                    <el-button v-print="'#printMe'" type="primary">打印</el-button>
                </el-col>
            </el-row>
            <el-row type="flex" class="row-bg" style="margin-top:20px" justify="space-around">
                <el-col :span="6">
                    <el-form-item label="报销单号" :required="true">
                        <el-input :readonly="true" v-model="ruleForm.expenseCode"></el-input>

                    </el-form-item>
                </el-col>
                <el-col :span="6">
                    <el-form-item label="报销部门" :required="true">
                        <el-input :readonly="true" v-model="ruleForm.deptName"></el-input>
                    </el-form-item>
                </el-col>
                  <el-col :span="6">
                    <el-form-item label="报销日期" prop="expenseDate">
                        <el-date-picker disabled v-model="ruleForm.expenseDate" type="date" placeholder="选择日期"
                            style="width:100%">
                        </el-date-picker>
                    </el-form-item>

                </el-col>
                <el-col :span="6">
                    <el-form-item label="附件">
                        <el-input :readonly="true" type="number" v-model="ruleForm.accessory">
                            <template slot="append">
                                张
                            </template>
                        </el-input>

                    </el-form-item>
                </el-col>

            </el-row>
            <el-row type="flex" class="row-bg" justify="space-around">
                <el-col :span="9">
                    <el-form-item>
                        <div>费用项目</div>
                    </el-form-item>
                    <el-form-item >
                        <el-input v-model="ruleForm.item1desc"  :readonly="true"></el-input>
                    </el-form-item>
                    <el-form-item >
                        <el-input v-model="ruleForm.item2desc" :readonly="true"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-input v-model="ruleForm.item3desc"  :readonly="true"></el-input>
                    </el-form-item>
                    <el-form-item >
                        <el-input v-model="ruleForm.item4desc"  :readonly="true"></el-input>
                    </el-form-item>
                    <el-form-item >
                        <el-input v-model="ruleForm.item5desc"  :readonly="true"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="9">
                    <el-form-item>
                        <div>金额</div>
                    </el-form-item>
                    <el-form-item >
                        <el-input type="number" :readonly="true" v-model="ruleForm.item1money" 
                        >
                        <template slot="append">
                           <div >元</div>
                        </template>
                        </el-input>
                        
                    </el-form-item>
                    <el-form-item >
                        <el-input type="number" :readonly="true" v-model="ruleForm.item2money" 
                        >
                         <template slot="append">
                           <div >元</div>
                        </template>
                        </el-input>
                        
                    </el-form-item>
                    <el-form-item >
                        <el-input type="number" :readonly="true" v-model="ruleForm.item3money" 
                        >
                        <template slot="append">
                           <div >元</div>
                        </template>    
                        </el-input>
                        
                    </el-form-item>
                    <el-form-item >
                        <el-input type="number" :readonly="true"  v-model="ruleForm.item4money" 
                           >
                        <template slot="append">
                           <div >元</div>
                        </template>    
                        </el-input>
                       
                    </el-form-item>
                    <el-form-item >
                        <el-input type="number" :readonly="true" v-model="ruleForm.item5money" 
                        >
                         <template slot="append">
                           <div >元</div>
                        </template>    
                        </el-input>
                        
                    </el-form-item>

                </el-col>
                <el-col :span="9">
                     <el-form-item>
                        <div>备注</div>
                    </el-form-item>
                    <el-form-item >
                        <el-popover placement="top-start" width="800px" trigger="hover" :content="ruleForm.item1remark">
                            <el-button slot="reference" style="width:100%">{{ ruleForm.item1remark }}</el-button>
                        </el-popover>
                    </el-form-item>
                    <el-form-item >
                        <el-popover placement="top-start" width="800px" trigger="hover" :content="ruleForm.item2remark">
                            <el-button slot="reference" style="width:100%">{{ ruleForm.item2remark }}</el-button>
                        </el-popover>
                    </el-form-item>
                    <el-form-item >
                        <el-popover placement="top-start" width="800px" trigger="hover" :content="ruleForm.item3remark">
                            <el-button slot="reference" style="width:100%">{{ ruleForm.item3remark }}</el-button>
                        </el-popover>
                    </el-form-item>
                    <el-form-item >
                        <el-popover placement="top-start" width="800px" trigger="hover" :content="ruleForm.item4remark">
                            <el-button slot="reference" style="width:100%">{{ ruleForm.item4remark }}</el-button>
                        </el-popover>
                    </el-form-item>
                    <el-form-item >
                        <el-popover placement="top-start" width="800px" trigger="hover" :content="ruleForm.item5remark">
                            <el-button slot="reference" style="width:100%">{{ ruleForm.item5remark }}</el-button>
                        </el-popover>
                    </el-form-item>
                </el-col>

            </el-row>

              <el-row type="flex" class="row-bg" justify="space-around">
                 <el-col :span="9"></el-col>
                <el-col :span="9">
                 <el-form-item label="合计金额(小写)">
                        {{ ruleForm.item1money + ruleForm.item2money + ruleForm.item3money + ruleForm.item4money +ruleForm.item5money}}
                </el-form-item>
                </el-col>
                
                <el-col :span="9">
                  <el-form-item label="合计金额(大写)">
                {{ digitUppercase(ruleForm.item1money + ruleForm.item2money + ruleForm.item3money + ruleForm.item4money+ ruleForm.item5money)}}
             </el-form-item>
                </el-col>
             </el-row>
            <el-form-item label="付款方式" prop="paywayId">
                <el-radio-group disabled v-model="ruleForm.paywayId" style="width:100%">
                    <el-row  type="flex" class="row-bg" justify="space-around">
                        <el-col :span="9" class="flexs" style="justify-content: flex-start;">
                           <el-radio :label="1">转账</el-radio>
                        </el-col>
                        <el-col :span="9" class="flexs">
                           <el-radio :label="2">现金</el-radio>
                        </el-col>
                        <el-col :span="9" class="flexs">
                            <el-radio :label="3">其他</el-radio>
                    <el-input disabled v-model="ruleForm.paywayRemark"></el-input>
                        </el-col>
                    </el-row>
                   
                    
                </el-radio-group>
            </el-form-item>
            
            <el-row type="flex" class="row-bg" justify="space-around">
                 <el-col :span="9">
                      <el-form-item label="收款人">
                <el-input v-model="ruleForm.userGetid" style="width:100%" :readonly="true"></el-input>
            </el-form-item>
                 </el-col>
                  <el-col :span="9">
                <el-form-item label="收款账号">
                <el-input v-model="ruleForm.bankcardGetid" style="width:100%" :readonly="true"></el-input>
               </el-form-item>
                 </el-col>
                  <el-col :span="9">
                  <el-form-item label="收款开户行">
                   <el-input v-model="ruleForm.bankGetname" style="width:100%" :readonly="true"></el-input>
                 </el-form-item>
                 </el-col>

            </el-row>
           
          
               <el-row type="flex" class="row-bg" justify="space-around">
                <el-col :span="9">
                   <el-form-item label="付款单位" prop="companyPayId" >
                <el-input v-model="ruleForm.companyPayId"  :readonly="true"></el-input>
                <!-- <el-select 
                    disabled
                    v-model="ruleForm.companyPayId" 
                    placeholder="请选择付款单位" 
                    style=" width: 200PX;">
                    <el-option 
                        v-for="item in payCompanys" 
                        :key="item.groupCode" 
                        :label="item.groupName" 
                        :value="item.groupCode"
                    > </el-option>
                </el-select> -->
            </el-form-item>
                </el-col>
                <el-col :span="9">
                    <el-form-item label="借款账号">
                <el-input v-model="ruleForm.bankPaycode"  :readonly="true"></el-input>
            </el-form-item>
                </el-col>
                <el-col :span="9">
                    <el-form-item label="借款开户行">
                <el-input v-model="ruleForm.bankPayname"  :readonly="true"></el-input>
            </el-form-item>
                </el-col>
               </el-row>

            
           
           

            <el-form-item label="总经理" class="left">
                <el-input v-model="ruleForm.gmCheck" placeholder="" class="inputCss" disabled></el-input>
            </el-form-item>
            <el-form-item label="财务" class="middle">
                <el-input v-model="ruleForm.financeCheck" placeholder="" class="inputCss" disabled></el-input>
            </el-form-item>
            <el-form-item label="部门主管" class="right">
                <el-input v-model="ruleForm.dmCheck" placeholder="" class="inputCss" disabled></el-input>
            </el-form-item>
            <el-form-item label="借款人" class="right2">
                <el-input v-model="ruleForm.expenseName" placeholder="" class="inputCss" disabled></el-input>
            </el-form-item>

            <el-form-item style="margin-left:80px;margin-top: 300px;height: 300px;">
                <div style="font-size:20px;
                    margin-top:10px;
                    margin-bottom:20px;
                    color:blue">{{ "审批进度" }}</div>
                <el-table border :data="checks" style="width: 75%">
                    <el-table-column prop="checkDate" label="时间" width="180" />
                    <el-table-column prop="checkUser" label="人员" width="180" />
                    <el-table-column prop="checkReasult" label="结果" />
                </el-table>
            </el-form-item>

            <el-form-item v-show="isShowImg" class="demo-image__preview" v-for="(item, index) in imgArr" :key="index"
                style="margin-top:200px;margin-left: -60px;">
                <span class="imgTitle">报销凭证影像</span>
                <el-image style="width:1100px;height: 700px;" :src="baseImgPath + item.value"></el-image>
            </el-form-item>

            <el-form-item v-show="isShowImg2" class="demo-image__preview" v-for="(item, index) in imgArr2" :key="index"
                style="margin-top:200px;margin-left: -60px;">
                <span class="imgTitle">付款凭证影像</span>
                <el-image style="width:1100px;height: 700px;" :src="baseImgPath + item.value"></el-image>
            </el-form-item>
            <!-- 
            <el-form-item
                v-show="isShowImg2" 
                class="demo-image__preview" 
                style="margin-top:200px;margin-left: -60px;">
                <span  class="imgTitle">报销凭证影像</span>
                <el-image
                   style="width:1100px;height: 700px;"
                    :src="imgpath2"
                ></el-image>
            </el-form-item> -->

        </el-form>

        <div style="margin-left:140px;margin-top:40px">
            <div>
                报销凭证影像：
                <el-button type="primary" @click="getImage">点击查看</el-button>
            </div>
            <div style="margin-left:320px;margin-top: -32px;">
                付款凭证影像：
                <el-button type="primary" @click="getImage2">点击查看</el-button>
            </div>
        </div>

        <!-- 图片 -->
        <el-dialog title="图片" :visible.sync="imageVisible" width="30%">
            <div class="demo-image" v-for="(item, index) in imgArr" :key="index" style="margin-top:20px;">
                <el-image style="width: 300px; height: 200px" :src="baseImgPath + item.value"
                    :preview-src-list="srcList">
                </el-image>
            </div>
        </el-dialog>
        <el-dialog title="图片" :visible.sync="imageVisible2" width="30%">
            <div class="demo-image" v-for="(item, index) in imgArr2" :key="index" style="margin-top:20px;">
                <el-image style="width: 300px; height: 200px" :src="baseImgPath + item.value"
                    :preview-src-list="srcList2"></el-image>
            </div>
        </el-dialog>
    </div>
</template>
<script>

import { getAllCheck, addCheckInvoices } from '@/api/invoices/checkInvoices'
import { getAllCompany } from '@/api/invoices/borrow'
import { getDepts, getAllGetCompany, getCode, editExpenseByExpenseId } from '@/api/invoices/expense'
import { getExpenseItem } from '@/api/invoices/travelExpense'
export default {
    name: 'expense',
    data() {
        return {
            expenses: [],
            isShowImg: false,
            isShowImg2: false,
            srcList: [],
            srcList2: [],
            imgArr: [],
            imgArr2: [],
            baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
            imgpath: '',
            expenseImage: '',
            imageVisible: false,

            imgpath2: '',
            expenseImage2: '',
            imageVisible2: false,

            checks: [],
            rejectReasult: '',
            imgDialog: false,
            imgDialog2: false,
            isAgree: 1,//是否同意 1 同意 2驳回
            isDisabled: false,
            payCompanys: [],//所有单位
            items: [],
            filePath: '',
            height: document.documentElement.clientHeight - 180 + 'px;',
            form: {},
            formLabelWidth: "80px",
            ruleForm: {
                deptName: '',
                expenseCode: '',//报销单号
                dept: '',//部门
                deptId: '',//部门id
                expenseDate: '',//报销日期create_time
                accessory: '',//附件数量accessory_num

                userGetid: '',//收款单位/人id
                bankcardGetid: '',//收款单位/人银行卡号
                bankGetname: '',//收款单位/人银行卡所属银行

                companyPayId: '',//付款单位id
                bankPaycode: '',//付款单位银行卡号
                bankPayname: '',//付款单位银行卡所属银行

                paywayId: '',
                accessory: '',
                item1desc: '',
                item1money: '',
                item1remark: '',
                item2desc: '',
                item2money: '',
                item2remark: '',
                item3desc: '',
                item3money: '',
                item3remark: '',
                item4desc: '',
                item4money: '',
                item4remark: '',
                item5desc: '',
                item5money: '',
                item5remark: '',

                expenseName: '',//报销人createUser
                paywayId: '',//付款方式
                paywayRemark: '',//付款方式其他的时候选填

                deptId: '',//部门id
                gm: '',//总经理
                dm: '',//部门主管
                finance: '',//财务

                gmCheck: '',
                financeCheck: '',
                dmCheck: '',
                role: '',
            },
            rules: {
                deptId: [
                    { required: true, message: '请选择部门', trigger: 'change' }
                ],
                paywayId: [
                    { required: true, message: '请选择付款方式', trigger: 'change' }
                ],
                expenseDate: [
                    { required: true, message: '请选择日期', trigger: 'change' }
                ],
                payway: [
                    { required: true, message: '请选择付款方式', trigger: 'change' }
                ],
                companyGetId: [
                    { required: true, message: '请选择收款单位', trigger: 'change' }
                ],
                cardGetId: [
                    { required: true, message: '请选择收款单位银行帐号', trigger: 'change' }
                ],
                item1desc: [
                    { required: true, message: '项目一必须选择', trigger: 'change' }
                ],
                companyPayId: [
                    { required: true, message: '请选择出款单位名', trigger: 'change' }
                ],
                bankcardCodePay: [
                    { required: true, message: '请输入出款银行卡号', trigger: 'blur' }
                ],
                bankNamePay: [
                    { required: true, message: '请输入出款银行名', trigger: 'blur' }
                ],

            },
            //后端查询的数据
            // searchDepts:[],
            searchGetCompanys: [],//所有收款单位信息
            searchCardInfos: [],//所选公司对应的银行卡信息
            checkType: '',

            expenseId: '',
            filePath2: '',
        }
    },
    // computed:function(){

    // },
    mounted: function () {
        this.selectAllCheck();
        this.getAllCompany();
        // this.getExpenseCode();
        this.getExpenseItem();
        // this.getAllDept();
        // this.getAllGetCompany();
        const that = this
        window.onresize = function temp() {
            that.height = document.documentElement.clientHeight - 180 + 'px;'
        };
        this.expenses = JSON.parse(window.localStorage.getItem('expenses')).list

        this.ruleForm.expenseCode = this.expenses[0].expenseCode;
        console.log("expenses111==", this.expenses[0]);
        this.ruleForm.expenseDate = this.expenses[0].createTime;
        this.ruleForm.deptId = this.expenses[0].deptId;
        this.ruleForm.deptName = this.expenses[0].deptName;

        this.ruleForm.paywayId = this.expenses[0].paywayId;
        this.ruleForm.role = JSON.parse(window.localStorage.getItem('expenseRoles'))[0].id;
        console.log("role==", this.ruleForm.role);

        this.ruleForm.expenseName = this.expenses[0].nickName;
        this.ruleForm.accessory = this.expenses[0].accessoryNum;
        this.ruleForm.paywayRemark = this.expenses[0].paywayRemark;
        this.ruleForm.item1desc = this.expenses[0].item1Desc;
        this.ruleForm.item1money = this.expenses[0].item1Money;
        this.ruleForm.item1remark = this.expenses[0].item1Remark;
        this.ruleForm.item2desc = this.expenses[0].item2Desc;
        this.ruleForm.item2money = this.expenses[0].item2Money;
        this.ruleForm.item2remark = this.expenses[0].item2Remark;
        this.ruleForm.item3desc = this.expenses[0].item3Desc;
        this.ruleForm.item3money = this.expenses[0].item3Money;
        this.ruleForm.item3remark = this.expenses[0].item3Remark;
        this.ruleForm.item4desc = this.expenses[0].item4Desc;
        this.ruleForm.item4money = this.expenses[0].item4Money;
        this.ruleForm.item4remark = this.expenses[0].item4Remark;
        this.ruleForm.item5desc = this.expenses[0].item5Desc;
        this.ruleForm.item5money = this.expenses[0].item5Money;
        this.ruleForm.item5remark = this.expenses[0].item5Remark;

        this.ruleForm.companyPayId = this.expenses[0].companyPayId;
        this.ruleForm.bankPaycode = this.expenses[0].bankPaycode;
        this.ruleForm.bankPayname = this.expenses[0].bankPayname;
        this.ruleForm.userGetid = this.expenses[0].userGetid;
        this.ruleForm.bankcardGetid = this.expenses[0].bankcardGetid;
        this.ruleForm.bankGetname = this.expenses[0].bankGetname;

        this.ruleForm.gm = JSON.parse(window.localStorage.getItem('expenseCheckPerson'));
        this.ruleForm.finance = JSON.parse(window.localStorage.getItem('expenseCheckPerson'));
        this.ruleForm.dm = JSON.parse(window.localStorage.getItem('expenseCheckPerson'));

        this.expenseId = this.expenses[0].expenseId;

        this.ruleForm.gmCheck = this.expenses[0].gmCheck;
        this.ruleForm.financeCheck = this.expenses[0].financeCheck;
        this.ruleForm.dmCheck = this.expenses[0].dmCheck;

        this.expenseImage = this.expenses[0].expenseImage;
        this.expenseImage2 = this.expenses[0].expenseImage2;

        var imgArr = this.expenseImage.split(",");
        if (imgArr[0] == "") {
            console.log("404");
            this.isShowImg = false;
        } else {
            imgArr.map((item, index) => {
                if (item != null && item != "") {
                    this.imgArr.push({ id: index, value: item });
                }
            })
            this.isShowImg = true;
        }

        var imgArr2 = this.expenseImage2.split(",");
        if (imgArr2[0] == "") {
            console.log("404");
            this.isShowImg2 = false;
        } else {
            imgArr2.map((item, index) => {
                if (item != null && item != "") {
                    this.imgArr2.push({ id: index, value: item });
                }
            })
            this.isShowImg2 = true;
        }
    },
    methods: {

        //获取所有审批结果
        selectAllCheck() {
            var expenses = JSON.parse(window.localStorage.getItem('expenses')).list
            var expenseCode = expenses[0].expenseCode;
            console.log('expenseCode==', expenseCode);
            getAllCheck({ invoiceCode: expenseCode }).then(res => {
                console.log('selectAllCheck==', res);
                this.checks = res
            })
        },
        //审核
        checkInvoices() {

            if (this.ruleForm.role == 1 || this.ruleForm.role == 5) {
                if (this.ruleForm.gmCheck == undefined || this.ruleForm.gmCheck == "") {//未审核过
                    console.log("总经理审核");
                    if (this.isAgree == 1) {
                        this.rejectReasult = "总经理审批同意";
                        this.checkType = 3;
                    };
                    if (this.isAgree == 2) {//驳回
                        this.checkType = 6;
                    };
                    var params1 = {
                        invoiceCode: this.ruleForm.expenseCode,
                        checkReasult: this.rejectReasult,
                        checkUser: this.ruleForm.gm,
                        checkDate: this.returnTime(new Date()),
                        invoiceType: 3,
                    };
                    var params2 = {
                        filePath2: this.filePath2,
                        gmCheck: this.ruleForm.gm,
                        expenseId: this.expenseId,
                        invoiceType: this.checkType,
                    };
                    addCheckInvoices(params1).then(res => {
                        this.$message({
                            message: res.message,
                            type: 'success',
                        });
                    });
                    editExpenseByExpenseId(params2).then(res => {
                        this.$message({
                            message: res.message,
                            type: 'success',
                        });
                    });
                    this.$router.push({
                        path: "/invoices/invoicesMgr"
                    });
                } else {
                    this.$message({
                        message: "审核过了",
                        type: 'warning',
                    });
                };
            };
            if (this.ruleForm.role == 7) {
                if (this.ruleForm.financeCheck == undefined || this.ruleForm.financeCheck == "") {//未审核过
                    console.log("财务审核");
                    if (this.isAgree == 1) {
                        this.rejectReasult = "财务审批同意";
                        this.checkType = 4;
                    };
                    if (this.isAgree == 2) {//驳回
                        this.checkType = 6;
                    };
                    var params1 = {
                        invoiceCode: this.ruleForm.expenseCode,
                        checkReasult: this.rejectReasult,
                        checkUser: this.ruleForm.finance,
                        checkDate: this.returnTime(new Date()),
                        invoiceType: 3,
                    };
                    var params2 = {
                        filePath2: this.filePath2,
                        financeCheck: this.ruleForm.finance,
                        expenseId: this.expenseId,
                        invoiceType: this.checkType,
                    };
                    addCheckInvoices(params1).then(res => {
                        this.$message({
                            message: res.message,
                            type: 'success',
                        });
                    });
                    editExpenseByExpenseId(params2).then(res => {
                        this.$message({
                            message: res.message,
                            type: 'success',
                        });
                    })
                    this.$router.push({
                        path: "/invoices/invoicesMgr"
                    });
                } else {
                    this.$message({
                        message: "审核过了",
                        type: 'warning',
                    });
                };
            };
            if (this.ruleForm.role == 12 || this.ruleForm.role == 10) {
                if (this.ruleForm.dmCheck == undefined || this.ruleForm.dmCheck == "") {//未审核过
                    console.log("部门主管审核");
                    if (this.isAgree == 1) {
                        this.rejectReasult = "部门主管审批同意";
                        this.checkType = 2;
                    };
                    if (this.isAgree == 2) {//驳回
                        this.checkType = 6;
                    };
                    var params1 = {
                        invoiceCode: this.ruleForm.expenseCode,
                        checkReasult: this.rejectReasult,
                        checkUser: this.ruleForm.dm,
                        checkDate: this.returnTime(new Date()),
                        invoiceType: 3,
                    };
                    var params2 = {
                        filePath2: this.filePath2,
                        dmCheck: this.ruleForm.dm,
                        expenseId: this.expenseId,
                        invoiceType: this.checkType,
                    };
                    addCheckInvoices(params1).then(res => {
                        this.$message({
                            message: res.message,
                            type: 'success',
                        });
                    });
                    editExpenseByExpenseId(params2).then(res => {
                        this.$message({
                            message: res.message,
                            type: 'success',
                        });
                    })
                    this.$router.push({
                        path: "/invoices/invoicesMgr"
                    });
                } else {
                    this.$message({
                        message: "审核过了",
                        type: 'warning',
                    });
                };
            }
        },
        //获取所有出款单位信息 
        getAllCompany() {
            getAllCompany().then(res => {
                this.payCompanys = res.list;
                res.list.map(item => {
                    if (item.groupCode == this.ruleForm.companyPayId) {
                        this.ruleForm.companyPayId = item.groupName;
                    }
                })
                console.log('payCompanys==', this.payCompanys);
            })
        },
        getExpenseCode() {
            getCode().then(res => {
                console.log("getExpenseCode", res.message);
                this.ruleForm.expenseCode = res.message;
            })
        },
        //获取费用项目
        getExpenseItem() {
            getExpenseItem().then(res => {
                this.items = res;
                console.log("items", this.items);
            })
        },
        handleResize() {
            this.fullWidth = document.documentElement.clientWidth
        },

        //初始化下拉部门信息 
        // getAllDept() {
        //     getDepts().then(res => {
        //         console.log('getDepts==',res.list);
        //         this.searchDepts = res.list
        //     }).catch(() => { })
        // }, 
        //初始化下拉公司信息 
        getAllGetCompany() {
            getAllGetCompany().then(res => {
                console.log('getAllGetCompany==', res.list);
                this.searchGetCompanys = res.list
            })
        },

        //返回当前时间
        returnTime(time2) {
            var time = new Date(time2);
            return time.toLocaleDateString();
        },
        //转换大小写
        digitUppercase(n = 0) {
            var fraction = ['角', '分'];
            var digit = [
                '零', '壹', '贰', '叁', '肆',
                '伍', '陆', '柒', '捌', '玖'
            ];
            var unit = [
                ['元', '万', '亿'],
                ['', '拾', '佰', '仟']
            ];
            var head = n < 0 ? '欠' : '';
            n = Math.abs(n);
            var s = '';
            for (var i = 0; i < fraction.length; i++) {
                s += (digit[Math.floor(n * 10 * Math.pow(10, i)) % 10] + fraction[i]).replace(/零./, '');
            }
            s = s || '整';
            n = Math.floor(n);
            for (var i = 0; i < unit[0].length && n > 0; i++) {
                var p = '';
                for (var j = 0; j < unit[1].length && n > 0; j++) {
                    p = digit[n % 10] + unit[1][j] + p;
                    n = Math.floor(n / 10);
                }
                s = p.replace(/(零.)*零$/, '').replace(/^$/, '零') + unit[0][i] + s;
            }
            return head + s.replace(/(零.)*零元/, '元')
                .replace(/(零.)+/g, '零')
                .replace(/^整$/, '零元整');
        },
        //获取图片
        getImage() {
            if (this.imgArr.length <= 0) {
                this.imageVisible = false;
                this.$message({
                    message: "没有影像",
                    type: 'warning',
                });
            } else {
                this.imageVisible = true;
                this.imgArr.map(item => {//增加可预览功能
                    this.srcList.push(this.baseImgPath + item.value);
                })
            }
        },
        getImage2() {
            if (this.imgArr2.length <= 0) {
                this.imageVisible2 = false;
                this.$message({
                    message: "没有影像",
                    type: 'warning',
                });
            } else {
                this.imageVisible2 = true;
                this.imgArr2.map(item => {//增加可预览功能
                    this.srcList2.push(this.baseImgPath + item.value);
                })
            }

            // if(this.expenseImage2==""||this.expenseImage2==undefined){
            //     this.imageVisible2=false;
            //     this.$message({
            //         message: "没有影像",
            //         type: 'warning',
            //     });
            // }else{
            //     this.imageVisible2=true;
            // }
        },

    },
}
</script>


<style rel="stylesheet/scss" lang="scss" scoped>
.flexs {
    display: flex;
    align-items: center;
    justify-content: center;
}

#title {
    margin-left: 450px;
    margin-top: 10px;
    margin-bottom: 30px;
}

.descCss {
    width: 490px;
    margin-left: -70px;
}

.moneyCss {
    width: 380px;
    margin-left: 420px;
    // margin-top: -50px;
}

.remarkCss {
    margin-left: 880px;
    // margin-top: -55px;
}

.yuan {
    margin-left: 180px;
    margin-top: -30px;
}


.left {
    width: 400px;
}

.middle {
    margin-left: 310px;
    margin-top: -60px;
}

.right {
    margin-left: 630px;
    margin-top: -60px;
}

.right2 {
    margin-left: 920px;
    margin-top: -60px;
}

#btn {
    margin-left: 260px;
    margin-top: 30px;
}

::v-deep .el-input-number .el-input__inner {
    text-align: left;
}

::v-deep .vue-treeselect__control,
::v-deep .vue-treeselect__placeholder,
::v-deep .vue-treeselect__single-value {
    height: 30px;
    line-height: 30px;
}

.inputCss {

    width: 200px;
}

// 改变input框字体颜色
::v-deep .is-disabled .el-input__inner {
    background-color: transparent !important;
    color: black;
}

.imgTitle {
    font-size: 20px;
    margin-bottom: 40px;
}
</style>