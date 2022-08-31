<template>
    <div class="app-container">
        <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="auto">
           <el-form-item  id="title">
             <span style="font-size:30px">报销单审核</span>  
            </el-form-item>
            <el-row type="flex" class="row-bg" justify="end">
                <el-col :span="6" style="display: flex;justify-content: flex-end;">
                    <el-button v-print="'#printMe'" type="primary">打印</el-button>
                </el-col>
            </el-row>
            <el-row type="flex" class="row-bg" style="margin-top:20px" justify="space-around">
                <el-col :span="9">
                    <el-form-item label="报销单号" :required="true">
                        <el-input :readonly="true" v-model="ruleForm.expenseCode"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="9">
                    <el-form-item label="报销部门" :required="true">
                        <el-input :readonly="true" v-model="ruleForm.deptName"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="9">
                    <el-form-item label="报销日期" prop="expenseDate">
                        <el-date-picker disabled v-model="ruleForm.expenseDate" type="date" placeholder="选择日期"
                            style="width:100%">
                        </el-date-picker>
                    </el-form-item>

                </el-col>
            </el-row>
            <el-row type="flex" class="row-bg" justify="space-around">
                <el-col :span="7">
                    <el-form-item>
                        <div>费用项目</div>
                    </el-form-item>
                    <el-form-item>
                        <el-input v-model="ruleForm.item1desc" :readonly="true"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-input v-model="ruleForm.item2desc" :readonly="true"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-input v-model="ruleForm.item3desc" :readonly="true"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-input v-model="ruleForm.item4desc" :readonly="true"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-input v-model="ruleForm.item5desc" :readonly="true"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="5">
                    <el-form-item>
                        <div>附件</div>
                    </el-form-item>
                    <el-form-item >
                        <el-input :readonly="true" type="number" v-model="ruleForm.accessoryNum1">
                            <template slot="append">
                                张
                            </template>
                        </el-input>
                    </el-form-item>
                      <el-form-item >
                        <el-input :readonly="true" type="number" v-model="ruleForm.accessoryNum2">
                            <template slot="append">
                                张
                            </template>
                        </el-input>
                    </el-form-item>
                      <el-form-item >
                        <el-input :readonly="true" type="number" v-model="ruleForm.accessoryNum3">
                            <template slot="append">
                                张
                            </template>
                        </el-input>
                    </el-form-item>
                      <el-form-item >
                        <el-input :readonly="true" type="number" v-model="ruleForm.accessoryNum4">
                            <template slot="append">
                                张
                            </template>
                        </el-input>
                    </el-form-item>
                      <el-form-item >
                        <el-input :readonly="true" type="number" v-model="ruleForm.accessoryNum5">
                            <template slot="append">
                                张
                            </template>
                        </el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="7">
                    <el-form-item>
                        <div>金额</div>
                    </el-form-item>
                    <el-form-item>
                        <el-input type="number" :readonly="true" v-model="ruleForm.item1money">
                            <template slot="append">
                                <div>元</div>
                            </template>
                        </el-input>

                    </el-form-item>
                    <el-form-item>
                        <el-input type="number" :readonly="true" v-model="ruleForm.item2money">
                            <template slot="append">
                                <div>元</div>
                            </template>
                        </el-input>

                    </el-form-item>
                    <el-form-item>
                        <el-input type="number" :readonly="true" v-model="ruleForm.item3money">
                            <template slot="append">
                                <div>元</div>
                            </template>
                        </el-input>

                    </el-form-item>
                    <el-form-item>
                        <el-input type="number" :readonly="true" v-model="ruleForm.item4money">
                            <template slot="append">
                                <div>元</div>
                            </template>
                        </el-input>

                    </el-form-item>
                    <el-form-item>
                        <el-input type="number" :readonly="true" v-model="ruleForm.item5money">
                            <template slot="append">
                                <div>元</div>
                            </template>
                        </el-input>

                    </el-form-item>

                </el-col>
                <el-col :span="9">
                    <el-form-item>
                        <div>备注</div>
                    </el-form-item>
                    <el-form-item>
                        <el-popover placement="top-start" width="800px" trigger="hover" :content="ruleForm.item1remark">
                            <el-button slot="reference" style="width:100%">{{ ruleForm.item1remark }}</el-button>
                        </el-popover>
                    </el-form-item>
                    <el-form-item>
                        <el-popover placement="top-start" width="800px" trigger="hover" :content="ruleForm.item2remark">
                            <el-button slot="reference" style="width:100%">{{ ruleForm.item2remark }}</el-button>
                        </el-popover>
                    </el-form-item>
                    <el-form-item>
                        <el-popover placement="top-start" width="800px" trigger="hover" :content="ruleForm.item3remark">
                            <el-button slot="reference" style="width:100%">{{ ruleForm.item3remark }}</el-button>
                        </el-popover>
                    </el-form-item>
                    <el-form-item>
                        <el-popover placement="top-start" width="800px" trigger="hover" :content="ruleForm.item4remark">
                            <el-button slot="reference" style="width:100%">{{ ruleForm.item4remark }}</el-button>
                        </el-popover>
                    </el-form-item>
                    <el-form-item>
                        <el-popover placement="top-start" width="800px" trigger="hover" :content="ruleForm.item5remark">
                            <el-button slot="reference" style="width:100%">{{ ruleForm.item5remark }}</el-button>
                        </el-popover>
                    </el-form-item>
                </el-col>
            </el-row>

              <el-row type="flex" class="row-bg" justify="space-around">
                
                <el-col :span="9">
                    <el-form-item label="合计金额(小写)">
                        {{ ruleForm.totalMoney+"元"}}
                    </el-form-item>
                </el-col>

                <el-col :span="9">
                    <el-form-item label="合计金额(大写)">
                        {{ digitUppercase(ruleForm.totalMoney)+"元"}}
                    </el-form-item>
                </el-col>
                <el-col :span="9"></el-col>
            </el-row>
            <el-form-item label="付款方式" prop="paywayId">
                <el-radio-group disabled v-model="ruleForm.paywayId" style="width:100%">
                    <el-row type="flex" class="row-bg" justify="space-around">
                        <el-col :span="9" class="flexs" style="justify-content: flex-start;align-items: center;">
                            <el-radio :label="1">转账</el-radio>
                        </el-col>
                        <el-col :span="9" style="display:flex;align-items: center;">
                            <el-radio :label="2">现金</el-radio>
                        </el-col>
                        <el-col :span="9" class="flexs">
                            <el-radio :label="3">其他</el-radio>
                            <el-input v-model="ruleForm.paywayRemark" :readonly="true"></el-input>
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
                    <el-form-item label="付款单位" prop="companyPayId">
                        <el-input v-model="ruleForm.companyPayId" :readonly="true"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="9">
                    <el-form-item label="付款账号">
                        <el-input v-model="ruleForm.bankPaycode" :readonly="true"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="9">
                    <el-form-item label="付款开户行">
                        <el-input v-model="ruleForm.bankPayname" :readonly="true"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>


            <el-row type="flex" class="row-bg" justify="space-around">
                <el-col :span="6">
                    <el-form-item label="总经理">
                        <el-input v-model="ruleForm.gmCheck" placeholder="" :readonly="true"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="6">
                    <el-form-item label="财务">
                        <el-input v-model="ruleForm.financeCheck" :readonly="true"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="6">
                    <el-form-item label="部门主管">
                        <el-input v-model="ruleForm.dmCheck" :readonly="true"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="6">
                    <el-form-item label="报销人">
                        <el-input v-model="ruleForm.expenseName" :readonly="true"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <!-- <el-form-item label="报销凭证影像：" >
                <showPdfAndImg v-if="imgArr.length >= 0" :fileName="isNone" :fileNameOld="imgArr"
                    :isDetail="isDetail"></showPdfAndImg>
            </el-form-item> -->
            <el-form-item v-hasPermi="['invoices:expense:upload']" label="付款凭证影像：" >
                <uploadInvoices ref="invoices" @getfileName="getExpense" :fileName="isNone" :fileNameOld="imgArr2Old"
                    :isDetail="isDetails"></uploadInvoices>
            </el-form-item>
            <div  class="demo-image__preview" v-for="(item, index) in imgArr" :key="index" style="margin-top:20px">           
                <el-form-item  v-if="item.suffix=='pdf'">
                    <div class="imgTitle">报销凭证影像</div>
                    <pdf ref="pdf" :src="item.url">
                    </pdf>
                </el-form-item>
                <el-form-item  v-else>
                    <div class="imgTitle">报销凭证影像</div>
                    <el-image :src="item.url" ></el-image>
                </el-form-item>
            </div>

          <el-row type="flex" class="row-bg" style="margin-bottom: 20px;" justify="space-around">
                <el-col :span="23">
                    <div style="font-size:20px;
                    margin-top:10px;
                    margin-bottom:20px;
                    color:blue">{{ "审批进度" }}</div>
                    <el-table border :data="checks">
                        <el-table-column prop="checkDate" label="时间" width="180" />
                        <el-table-column prop="checkUser" label="人员" width="180" />
                        <el-table-column prop="checkReasult" label="结果" />
                    </el-table>
                </el-col>
            </el-row>

           <el-form-item prop="isAgree">
                <div style=" font-size:20px;margin-top:10px;margin-bottom:20px;color:blue">{{"审批"}}</div>
                <el-radio 
                    @change="isAgrees"
                    v-model="ruleForm.isAgree" 
                    label="1"
                    >同意</el-radio>
                <br/>
                <el-radio 
                    @change="isAgrees"
                    v-model="ruleForm.isAgree" 
                    label="2"
                    >驳回</el-radio>
                <el-input 
                    :disabled="isDisabled"
                    v-model="rejectReasult" 
                    placeholder="请输入驳回原因" 
                    style="margin-top:-20px;width:820px"
                ></el-input>
            </el-form-item>
            
            <el-form-item id="btn">
                <el-button @click="toReturn" style="width:80px">关闭</el-button>
                <el-button type="primary" @click="checkInvoices('ruleForm')" style="width:80px;margin-left:80px">审核</el-button>
            </el-form-item>

        </el-form>
    </div>
</template>
<script>
import pdf from 'vue-pdf-signature'
import CMapReaderFactory from 'vue-pdf-signature/src/CMapReaderFactory.js'
import uploadInvoices from '@/components/douploads/uploadInvoices'
import { getAllCheck, addCheckInvoices } from '@/api/invoices/checkInvoices'
import { getAllCompany } from '@/api/invoices/borrow'
import { getDepts, getAllGetCompany, getCode, editExpenseByExpenseId } from '@/api/invoices/expense'
import { getExpenseItem } from '@/api/invoices/travelExpense'
export default {
    name: 'expense',
    components: {
        uploadInvoices,
        pdf
    },
    data() {
        return {
            
            isDetail: '1',
            isDetails: '0',
            isNone: [],
            srcList: [],
            imgArr: [],
            baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
            //查看
            expenseImage: '',
            imageVisible: false,
            imgpath: '',
            //上传
            imgArr2: [],
            imgArr2Old:[],
            imgDialog: false,
            dialogImageUrl: '',
            dialogVisible: false,
            limitNum: 10,//可上传数量

            checks: [],
            rejectReasult: '',

            isDisabled: true,
            payCompanys: [],//所有单位
            items: [],
            filePath: '',
            height: document.documentElement.clientHeight - 180 + 'px;',
            form: {},
            formLabelWidth: "80px",
            ruleForm: {
                isAgree: '1',//是否同意 1 同意 2驳回

                expenseCode: '',//报销单号
                dept: '',//部门
                deptId: '',//部门id
                expenseDate: '',//报销日期create_time

                accessoryNum1: '',//附件数量
                accessoryNum2: '',//附件数量
                accessoryNum3: '',//附件数量
                accessoryNum4: '',//附件数量
                accessoryNum5: '',//附件数量

                userGetid: '',//收款单位/人id
                bankcardGetid: '',//收款单位/人银行卡号
                bankGetname: '',//收款单位/人银行卡所属银行

                companyPayId: '',//付款单位id
                bankPaycode: '',//付款单位银行卡号
                bankPayname: '',//付款单位银行卡所属银行

                paywayId: '',
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
                roles: [],
            },
            rules: {
                isAgree: [
                    { required: true, message: '请选择是否同意', trigger: 'change' }
                ],
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
            searchDepts: [],
            searchGetCompanys: [],//所有收款单位信息
            searchCardInfos: [],//所选公司对应的银行卡信息
            checkType: '',

            expenseId: '',
            filePath2: '',

            stepType:'',
        }
    },
    
    mounted: function () {
        this.selectAllCheck();
        this.getAllCompany();
        // this.getExpenseCode();
        this.getExpenseItem();
        this.getAllDept();
        // this.getAllGetCompany();
        const that = this
        window.onresize = function temp() {
            that.height = document.documentElement.clientHeight - 180 + 'px;'
        };
        this.expenses = JSON.parse(window.localStorage.getItem('expenses')).list
        console.log("expenses==", this.expenses[0]);
        this.ruleForm.expenseCode = this.expenses[0].expenseCode;
        this.ruleForm.expenseDate = this.expenses[0].createTime;
        this.ruleForm.deptId = this.expenses[0].deptId;
        this.ruleForm.deptName = this.expenses[0].deptName;

        this.ruleForm.paywayId = this.expenses[0].paywayId;
        this.ruleForm.roles = JSON.parse(window.localStorage.getItem('expenseRoles'));
        console.log("roles==", this.ruleForm.roles);

        this.ruleForm.expenseName = this.expenses[0].nickName;
        this.ruleForm.totalMoney=this.expenses[0].totalMoney;
        this.ruleForm.accessoryNum1 = this.expenses[0].accessoryNum1;
        this.ruleForm.accessoryNum2 = this.expenses[0].accessoryNum2;
        this.ruleForm.accessoryNum3 = this.expenses[0].accessoryNum3;
        this.ruleForm.accessoryNum4 = this.expenses[0].accessoryNum4;
        this.ruleForm.accessoryNum5 = this.expenses[0].accessoryNum5;
        
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
        var imgArr = this.expenseImage.split(",");
        imgArr.map((item, index) => {
            if (item != null && item != "") {
                var suffix=item.substring(item.lastIndexOf('.')+1,item.length);
                if(suffix=='pdf'){
                        this.imgArr.push({
                        url: pdf.createLoadingTask({ url: this.baseImgPath + item, CMapReaderFactory, cMapPacked: true }),
                        suffix: suffix,
                    })
                    }  else{
                        this.imgArr.push({
                        url: this.baseImgPath+item,
                        suffix: suffix,
                    })
                    }
                
                // this.imgArr.push({id:index,value:item});
            }
        })


        var imgArr2 = this.expenseImage2.split(",");
        this.imgArr2=[];
        if (imgArr2[0] == "") {


        } else {
            imgArr2.map((item, index) => {
                if (item != null && item != "") {
                    var suffix=item.substring(item.lastIndexOf('.')+1,item.length);     
                     this.imgArr2Old.push({
                        url:  this.baseImgPath+item,
                        name: item,
                    })
                    
                    this.imgArr2.push(item);

                      
                }
            })
            this.$refs.invoices.getSrcList(this.imgArr2);

        }
        console.log("imgArr==",this.imgArr);
    },
    methods: {
        getExpense(data) {
            this.imgArr2 = data;
            console.log(this.imgArr2);
        },
        isAgrees() {
            console.log("点了是否同意");
            if (this.ruleForm.isAgree == 2) {
                this.isDisabled = false;
            }
            if (this.ruleForm.isAgree == 1) {
                this.isDisabled = true;
            }
        },
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

            this.ruleForm.roles.map(item => {
                console.log("item", item);
                if (item.roleId == 5 || item.roleId == 6) {
                    if (this.ruleForm.gmCheck == undefined || this.ruleForm.gmCheck == "") {//未审核过
                        console.log("总经理审核");
                        if (this.ruleForm.isAgree == 1) {
                            this.rejectReasult = "总经理审批同意";
                            this.checkType = 3;
                            this.stepType=1;
                        };
                        if (this.ruleForm.isAgree == 2) {//驳回
                            this.checkType = 6;
                            this.stepType=3;
                            this.rejectReasult = "总经理驳回：" + this.rejectReasult;
                        };
                        var params1 = {
                            invoiceCode: this.ruleForm.expenseCode,
                            checkReasult: this.rejectReasult,
                            checkUser: this.ruleForm.gm,
                            checkDate: this.returnTime(new Date()),
                            invoiceType: 1,

                        };
                        var params2 = {
                            gmCheck: this.ruleForm.gm,
                            expenseId: this.expenseId,
                            invoiceType: this.checkType,
                            expenseImage2: this.imgArr2.join(),
                            stepType:this.stepType,
                        };
                        addCheckInvoices(params1).then(res => {
                            // this.$message({
                            //     message: "审核成功",
                            //     type: 'success',
                            // });
                        });
                        editExpenseByExpenseId(params2).then(res => {
                            this.$message({
                                message: "审核成功",
                                type: 'success',
                            });
                        });
                        this.$router.push({
                            path: "/invoices/checkInvoices"
                        });
                    } else {
                        this.$message({
                            message: "审核过了",
                            type: 'warning',
                        });
                    };
                };
                if (item.roleId == 7) {
                    if (this.ruleForm.financeCheck == undefined || this.ruleForm.financeCheck == "") {//未审核过
                        console.log("财务审核");
                        if (this.ruleForm.isAgree == 1) {
                            this.rejectReasult = "财务审批同意";
                            this.checkType = 5;
                            this.stepType=2;
                        };
                        if (this.ruleForm.isAgree == 2) {//驳回
                            this.checkType = 6;
                            this.rejectReasult = "财务驳回：" + this.rejectReasult;
                            this.stepType=3;
                        };
                        var params1 = {
                            invoiceCode: this.ruleForm.expenseCode,
                            checkReasult: this.rejectReasult,
                            checkUser: this.ruleForm.finance,
                            checkDate: this.returnTime(new Date()),
                            invoiceType: 1,
                        };
                        var params2 = {
                            financeCheck: this.ruleForm.finance,
                            expenseId: this.expenseId,
                            invoiceType: this.checkType,
                            expenseImage2: this.imgArr2.join(),
                            stepType:this.stepType,
                        };
                        addCheckInvoices(params1).then(res => {
                            // this.$message({
                            //     message: "审核成功",
                            //     type: 'success',
                            // });
                        });
                        editExpenseByExpenseId(params2).then(res => {
                            this.$message({
                                message: "审核成功",
                                type: 'success',
                            });
                        })
                        this.$router.push({
                            path: "/invoices/checkInvoices"
                        });
                    } else {
                        this.$message({
                            message: "审核过了",
                            type: 'warning',
                        });
                    };
                };
                if (item.roleId == 10 || item.roleId == 12 || item.roleId == 4 || item.roleId == 8) {
                    if (this.ruleForm.dmCheck == undefined || this.ruleForm.dmCheck == "") {//未审核过
                        console.log("部门主管审核");
                        if (this.ruleForm.isAgree == 1) {
                            this.rejectReasult = "部门主管审批同意";
                            this.checkType = 2;
                            this.stepType=1;
                        };
                        if (this.ruleForm.isAgree == 2) {//驳回
                            this.checkType = 6;
                            this.stepType=3;
                            this.rejectReasult = "部门主管驳回：" + this.rejectReasult;
                        };
                        var params1 = {
                            invoiceCode: this.ruleForm.expenseCode,
                            checkReasult: this.rejectReasult,
                            checkUser: this.ruleForm.dm,
                            checkDate: this.returnTime(new Date()),
                            invoiceType: 1,
                        };
                        var params2 = {
                            dmCheck: this.ruleForm.dm,
                            expenseId: this.expenseId,
                            invoiceType: this.checkType,
                            expenseImage2: this.imgArr2.join(),
                            stepType:this.stepType,
                        };
                        addCheckInvoices(params1).then(res => {
                            // this.$message({
                            //     message: "审核成功",
                            //     type: 'success',
                            // });
                        });
                        editExpenseByExpenseId(params2).then(res => {
                            this.$message({
                                message: "审核成功",
                                type: 'success',
                            });
                        })
                        this.$router.push({
                            path: "/invoices/checkInvoices"
                        });
                    } else {
                        this.$message({
                            message: "审核过了",
                            type: 'warning',
                        });
                    };
                }
            })

        },
        // selectPayWay(){
        //     console.log("paywayId",this.ruleForm.paywayId);
        //     if(this.ruleForm.paywayId==17){
        //         this.isDisabled=true;
        //     }
        //     if(this.ruleForm.paywayId==18){
        //         this.isDisabled=false;
        //     }
        // },
        //获取所有出款单位信息
        getAllCompany() {
            getAllCompany().then(res => {
                this.payCompanys = res.list;
                console.log('payCompanys==', this.payCompanys);
                 res.list.map(item => {
                    if (item.groupCode == this.ruleForm.companyPayId) {
                        this.ruleForm.companyPayId = item.groupName;
                    }
                })
            })
        },
        //根据出款单位id查找出款银行卡信息
        // getCarInfoByCompanyId() {
        //     var cardInfo=this.payCompanys.find((item) =>
        //     item.companyId == this.ruleForm.companyPayId);
        //     this.ruleForm.bankcardCodePay= cardInfo.account;
        //     this.ruleForm.bankNamePay= cardInfo.accountOpeningBank;
        //     console.log('cardInfo==',cardInfo);
        // },
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
        getAllDept() {
            getDepts().then(res => {
                console.log('getDepts==', res.list);
                this.searchDepts = res.list
            }).catch(() => { })
        },
        //初始化下拉公司信息
        getAllGetCompany() {
            getAllGetCompany().then(res => {
                console.log('getAllGetCompany==', res.list);
                this.searchGetCompanys = res.list
            })
        },
        //根据收款单位id查找收款银行卡信息
        // getCardInfoBycompany() {
        //     this.ruleForm.cardGetId="";
        //     this.ruleForm.bankNameGet="";
        //     getCardInfoBycompany(this.ruleForm.companyGetId).then(res => {
        //         console.log('getCardInfoBycompany==',res.list);
        //         this.searchCardInfos = res.list
        //     })
        // },
        //根据收款单位银行卡id查找对应的银行名
        // getBankNameBycardId() {
        //     getBankNameBycardId(this.ruleForm.cardGetId).then(res => {
        //         console.log('getBankNameBycardId==',res.message);
        //         this.ruleForm.bankNameGet = res.message
        //     }).catch(() => { })

        // },
        //提交表单
        // submitForm(formName) {
        //     console.log("companyPayId",this.ruleForm.companyPayId);
        //     this.$refs[formName].validate((valid) => {
        //         if (valid) {
        //             let params={
        //                 expenseCode:this.ruleForm.expenseCode,
        //                 deptId:this.ruleForm.deptId,
        //                 createTime:this.returnTime(this.ruleForm.expenseDate),
        //                 accessoryNum:this.ruleForm.accessory,
        //                 item1Remark:this.ruleForm.item1remark,
        //                 item1Money:this.ruleForm.item1money,
        //                 item1Desc:this.ruleForm.item1desc,
        //                 item2Remark:this.ruleForm.item2remark,
        //                 item2Money:this.ruleForm.item2money,
        //                 item2Desc:this.ruleForm.item2desc,
        //                 item3Remark:this.ruleForm.item3remark,
        //                 item3Money:this.ruleForm.item3money,
        //                 item3Desc:this.ruleForm.item3desc,
        //                 item4Remark:this.ruleForm.item4remark,
        //                 item4Money:this.ruleForm.item4money,
        //                 item4Desc:this.ruleForm.item4desc,
        //                 item5Remark:this.ruleForm.item5remark,
        //                 item5Money:this.ruleForm.item5money,
        //                 item5Desc:this.ruleForm.item5desc,
        //                 totalMoney:this.ruleForm.item1money+this.ruleForm.item2money+this.ruleForm.item3money+this.ruleForm.item4money+this.ruleForm.item5money,
        //                 paywayId:this.ruleForm.paywayId,
        //                 borrowMoney:this.ruleForm.borrow,
        //                 remainMoney:this.ruleForm.borrow-(this.ruleForm.item1money+this.ruleForm.item2money+this.ruleForm.item3money+this.ruleForm.item4money+this.ruleForm.item5money),
        //                 overMoney:this.ruleForm.item1money+this.ruleForm.item2money+this.ruleForm.item3money+this.ruleForm.item4money+this.ruleForm.item5money-this.ruleForm.borrow,
        //                 userGetid:this.ruleForm.userGetid,
        //                 bankcardGetid:this.ruleForm.bankcardGetid,
        //                 bankGetname:this.ruleForm.bankGetname,
        //                 companyPayId:this.ruleForm.companyPayId,
        //                 bankPaycode:this.ruleForm.bankPaycode,
        //                 bankPayname:this.ruleForm.bankPayname,
        //                 filePath:this.filePath,
        //             };
        //             console.log('submit!');
        //             addExpense(params).then(res => {
        //                 console.log('addExpense==',res.message);
        //                 this.$message({
        //                     message: res.message,
        //                     type: 'success',
        //                 });
        //             })
        //         }
        //         else {
        //             console.log('error submit!!');
        //             this.$message({
        //                 message: "请填写完整",
        //                 type: 'warning',
        //             });
        //             return false;
        //         }
        //     });
        // },
        // //重置
        // resetForm(formName) {
        //     this.$refs[formName].resetFields();
        //     console.log("重置");
        // },
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
        toReturn() {
            this.$router.push({
                path: "/invoices/checkInvoices"
            })
        },




    },
}
</script>


<style rel="stylesheet/scss" lang="scss" scoped>
#title {
    margin-left: 560px;
    margin-top: 10px;
    margin-bottom: 20px;
}

.descCss {
    width: 490px;
    margin-left: -70px;
    margin-top: -10px;
}

.moneyCss {
    width: 380px;
    margin-left: 400px;
    margin-top: -10px;
}

.remarkCss {
    margin-left: 860px;
    margin-top: -10px;
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

.imgTitle{
    margin-left:600px;
    font-size: 18px;
}
</style>
