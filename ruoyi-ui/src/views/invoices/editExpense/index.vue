<template>
    <div class="app-container">
        <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="auto">
            <el-form-item  id="title">
                <span style="font-size:30px">报销单修改</span>  
            </el-form-item>
            <el-row type="flex" class="row-bg" style="margin-top:20px" justify="space-around">
                <el-col :span="6">
                    <el-form-item label="报销单号">
                        <el-input disabled v-model="ruleForm.expenseCode"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="6">
                    <el-form-item label="报销部门">
                        <el-input disabled v-model="ruleForm.deptName"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="6">
                    <el-form-item label="报销日期" prop="expenseDate">
                        <el-date-picker v-model="ruleForm.expenseDate" type="date" placeholder="选择日期"
                            style="width:100%">
                        </el-date-picker>
                    </el-form-item>

                </el-col>
                <el-col :span="6">
                    <el-form-item label="附件">
                        <el-input-number v-model="ruleForm.accessory" :precision="0" :step="1" :min="0"
                            style="width:160px"></el-input-number>
                        <span style="margin-left:20px">张</span>
                    </el-form-item>
                </el-col>

            </el-row>

            <el-row type="flex" class="row-bg" justify="space-around">
                <el-col :span="9">
                    <el-form-item>
                        <div>费用项目</div>
                    </el-form-item>
                    <el-form-item prop="item1desc" >
                        <el-select style="width:100%" v-model="ruleForm.item1desc" clearable placeholder="请选择">
                            <el-option v-for="item in items" :key="item.id" :label="item.itemName"
                                :value="item.itemName">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item >

                        <el-select style="width:100%" v-model="ruleForm.item2desc" clearable placeholder="请选择">
                            <el-option v-for="item in items" :key="item.id" :label="item.itemName"
                                :value="item.itemName">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item >

                        <el-select style="width:100%" v-model="ruleForm.item3desc" clearable placeholder="请选择">
                            <el-option v-for="item in items" :key="item.id" :label="item.itemName"
                                :value="item.itemName">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item >

                        <el-select style="width:100%" v-model="ruleForm.item4desc" clearable placeholder="请选择">
                            <el-option v-for="item in items" :key="item.id" :label="item.itemName"
                                :value="item.itemName">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item>

                        <el-select style="width:100%" v-model="ruleForm.item5desc" clearable placeholder="请选择">
                            <el-option v-for="item in items" :key="item.id" :label="item.itemName"
                                :value="item.itemName">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="9">
                    <el-form-item >
                        <div>金额</div>
                    </el-form-item>
                    <el-form-item >
                        <el-input-number  v-model="ruleForm.item1money" :precision="2" :step="0.1" :min="0"
                            style="width:100%">
                            <template slot="append">元</template>
                        </el-input-number>
                    </el-form-item>
                    <el-form-item >
                         <el-input-number  v-model="ruleForm.item2money" :precision="2" :step="0.1" :min="0"
                            style="width:100%">
                            <template slot="append">元</template>
                        </el-input-number>
                    </el-form-item>
                    <el-form-item >
                        <el-input-number  v-model="ruleForm.item3money" :precision="2" :step="0.1" :min="0"
                            style="width:100%">
                            <template slot="append">元</template>
                        </el-input-number>
                    </el-form-item>
                    <el-form-item>
                        <el-input-number  v-model="ruleForm.item4money" :precision="2" :step="0.1" :min="0"
                            style="width:100%">
                            <template slot="append">元</template>
                        </el-input-number>
                    </el-form-item>
                    <el-form-item>
                        <el-input-number  v-model="ruleForm.item5money" :precision="2" :step="0.1" :min="0"
                            style="width:100%">
                            <template slot="append">元</template>
                        </el-input-number>
                    </el-form-item>
                </el-col>
                <el-col :span="9">
                    <el-form-item  >
                        <div>备注</div>
                    </el-form-item>
                    <el-form-item >
                        <el-input v-model="ruleForm.item1remark" placeholder="请输入备注" style="width:100%"></el-input>
                    </el-form-item>
                    <el-form-item >
                        <el-input v-model="ruleForm.item2remark" placeholder="请输入备注" style="width:100%"></el-input>
                    </el-form-item>
                    <el-form-item >
                        <el-input v-model="ruleForm.item3remark" placeholder="请输入备注" style="width:100%"></el-input>
                    </el-form-item>
                    <el-form-item >
                        <el-input v-model="ruleForm.item4remark" placeholder="请输入备注" style="width:100%"></el-input>
                    </el-form-item>
                    <el-form-item >
                        <el-input v-model="ruleForm.item5remark" placeholder="请输入备注" style="width:100%"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row type="flex" class="row-bg" justify="space-around">
                <el-col :span="8">
                    <el-form-item label="合计金额(小写)"  prop="money">
                        {{ totalMoney}}
                    </el-form-item>
                    </el-col>
                    <el-col :span="8"></el-col>
                    <el-col :span="8">
                        <el-form-item label="合计金额(大写)" >
                        {{digitUppercase(totalMoney)}}
                    </el-form-item>
                </el-col>
            </el-row>

           
            <el-form-item label="付款方式" prop="paywayId">
                <el-radio-group @change="selectPayWay" v-model="ruleForm.paywayId" style="width:100%">
                <el-row type="flex" class="row-bg" justify="space-around">
                  <el-col :span="8" class="flexs" style="justify-content: flex-start;align-items: center;">
                      <el-radio :label="1">转账</el-radio>
                  </el-col>
                  <el-col :span="8" style="display:flex;align-items: center;">
                     <el-radio :label="2" >现金</el-radio>
                  </el-col>
                  <el-col :span="8" class="flexs">
                    <el-radio :label="3" >其他</el-radio>
                    <el-input :disabled=isDisabled2 v-model="ruleForm.paywayRemark"></el-input>
                  </el-col>
                </el-row>
                </el-radio-group>
            </el-form-item>
            <el-row type="flex" class="row-bg" justify="space-around">
                <el-col :span="8">
                    <el-form-item label="收款人">
                <el-input v-model="ruleForm.expenseName" disabled></el-input>
                <!-- <el-select 
                    v-model="ruleForm.userGetid" 
                    @change="getCardInfoBycompany"
                    placeholder="请选择收款单位"   
                    style=" width: 210PX;"
                    :disabled="isDisabled">
                    <el-option 
                        v-for="item in searchGetUsers" 
                        :key="item.userId" 
                        :label="item.nickName" 
                        :value="item.userId"
                    > </el-option>
                </el-select> -->
            </el-form-item>
                </el-col>
                <el-col :span="8">
                   <el-form-item label="收款账号" >
                <el-input v-model="ruleForm.bankcardGetid" disabled></el-input>
            </el-form-item>
                </el-col>
                <el-col :span='8'>
                 <el-form-item label="收款开户行">
                    <el-input v-model="ruleForm.bankGetname" disabled></el-input>
                 </el-form-item>
                </el-col>
            </el-row>

            
             <el-row type="flex" class="row-bg" justify="space-around">
                  <el-col :span='8'>
                       <el-form-item label="付款单位" prop="companyPayId">
                <el-select v-model="ruleForm.companyPayId" placeholder="请选择付款单位" @change="getCarInfoByCompanyId"
                    style=" width: 100%;">
                    <el-option v-for="item in payCompanys" :key="item.groupCode" :label="item.groupName"
                        :value="item.groupCode"> </el-option>
                </el-select>
            </el-form-item>
                  </el-col>
                    <el-col :span='8'>
                         <el-form-item label="付款账号" >
                <el-input v-model="ruleForm.bankPaycode"  disabled></el-input>
            </el-form-item>
                  </el-col>
                   <el-col :span='8'>
                        <el-form-item label="付款开户行">
                <el-input v-model="ruleForm.bankPayname"  disabled></el-input>
            </el-form-item>
                  </el-col>
             </el-row>
            
        <el-row type="flex" class="row-bg" justify="space-around">
            <el-col :span="12">
                <el-form-item label="报销凭证影像">
                    <uploadSmall v-if="imgArrOld.length >= 0" @getfileName="getExpense" :fileName="imgArr2"
                    :fileNameOld="imgArrOld" :isDetail="isDetail"></uploadSmall>
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="报销人："  >
                    <el-input
                        v-model="ruleForm.expenseName"
                        class="inputCss"
                        placeholder=""
                        disabled
                    ></el-input>
                </el-form-item>
            </el-col>
            <el-col :span="12"></el-col>
        </el-row>

        <el-row type="flex" class="row-bg " justify="space-around">
            <el-col :span="5"></el-col>
            <!-- <el-col :span='8' class="flexs"> -->
            <el-button type="danger" @click="toReturn">返回</el-button>
            <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
            <!-- </el-col> -->
            <el-col :span="8"></el-col>
        </el-row>
      </el-form>
    </div>
</template>
<script>
import uploadSmall from '@/components/douploads/uploadSmall'
import { getAllGetUser } from '@/api/invoices/borrow'
import { getAllCheck } from '@/api/invoices/checkInvoices'
import { getAllCompany } from '@/api/invoices/borrow'
import { getDepts, getAllGetCompany, getCardInfoBycompany, editExpense2, getCode, editExpenseByExpenseId } from '@/api/invoices/expense'
import { getExpenseItem } from '@/api/invoices/travelExpense'
export default {
    name: 'expense',
    components: {
        uploadSmall
    },
    data() {
        return {
            isDetail: '1',
            imgArrOld: [],
            //isNone:[],
            //影像上传参数
            imgArr: [],
            imgArr2: [],
            baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
            limitNum: 10,
            imgDialog: false,
            dialogImageUrl: '',
            dialogVisible: false,
            //影像查看参数
            imgpath: '',
            expenseImage: '',
            imageVisible: false,

            checks: '',
            rejectReasult: '',
            imgDialog2: false,
            isAgree: 1,//是否同意 1 同意 2驳回

            payCompanys: [],//所有单位
            items: [],
            filePath: '',
            height: document.documentElement.clientHeight - 180 + 'px;',
            form: {},
            formLabelWidth: "80px",
            ruleForm: {
                 totalMoney:'',
                 
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
            searchGetUsers: [],
            searchDepts: [],
            searchGetCompanys: [],//所有收款单位信息
            searchCardInfos: [],//所选公司对应的银行卡信息
            checkType: '',

            expenseId: '',
            filePath2: '',
            isDisabled: false,//收款人
            isDisabled2: true,//其他付款方式输入框
        }
    },
    mounted: function () {
        this.getAllGetUser();
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
        this.imgArrOld = [];
        var imgArr = this.expenseImage.split(",");
        this.imgArr2=[];
        imgArr.map((item, index) => {
            if (item != null && item != "") {
                this.imgArrOld.push({
                    name: item,
                    url: this.baseImgPath + item
                })
                this.imgArr2.push(item);
            }
        })
    },
    computed: {          
        totalMoney:function(){ 
            return this.ruleForm.item1money+this.ruleForm.item2money+this.ruleForm.item3money+this.ruleForm.item4money+this.ruleForm.item5money;
        }
    },
    methods: {
        getExpense(data) {
            this.imgArr2 = data;
        },
        isAgrees() {
            console.log("点了是否同意");
            if (this.isAgree == 2) {
                this.isDisabled = false;
            }
            if (this.isAgree == 1) {
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
        selectPayWay() {
            console.log("paywayId", this.ruleForm.paywayId);
            if (this.ruleForm.paywayId == 3) {
                this.isDisabled = false;
                this.isDisabled2 = false;//其他输入选择框可输入
            }
            if (this.ruleForm.paywayId == 2) {
                this.isDisabled = true;//收款人置空
                this.isDisabled2 = true;
            }
            if (this.ruleForm.paywayId == 1) {
                this.isDisabled = false;
                this.isDisabled2 = true;
            }
        },
        //获取报销人信息 
        getAllGetUser() {
            getAllGetUser().then(res => {
                console.log('getAllGetUser==', res.list);
                this.searchGetUsers = res.list;
            })
        },
        //获取所有出款单位信息 
        getAllCompany() {
            getAllCompany().then(res => {
                this.payCompanys = res.list;
                console.log('payCompanys==', this.payCompanys);
            })
        },
        //根据出款单位id查找出款银行卡信息 
        getCarInfoByCompanyId() {
            var cardInfo = this.payCompanys.find((item) =>
                item.groupCode == this.ruleForm.companyPayId);
            this.ruleForm.bankPaycode = cardInfo.groupBankAccount;
            this.ruleForm.bankPayname = cardInfo.groupOpenBank;
            console.log('cardInfo==', cardInfo);
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
        getCardInfoBycompany() {
            getCardInfoBycompany(this.ruleForm.userGetid).then(res => {
                console.log('getCardInfoBycompany==', res);
                this.ruleForm.bankcardGetid = res.payCheck;
                this.ruleForm.bankGetname = res.accountCardBank;
                this.ruleForm.userGetid = res.nickName;
            })
        },
        //根据收款单位银行卡id查找对应的银行名
        // getBankNameBycardId() {
        //     getBankNameBycardId(this.ruleForm.cardGetId).then(res => {
        //         console.log('getBankNameBycardId==',res.message);
        //         this.ruleForm.bankNameGet = res.message
        //     }).catch(() => { })

        // }, 
        //提交表单
        submitForm(formName) {
            let params = {
                invoiceType: 1,//发起状态
                expenseId: this.expenseId,
                deptId: this.ruleForm.deptId,
                expenseCode: this.ruleForm.expenseCode,
                dept: this.ruleForm.dept,
                createTime:this.returnTime(this.ruleForm.expenseDate) ,
                accessoryNum: this.ruleForm.accessory,

                item1Remark: this.ruleForm.item1remark,
                item1Money: this.ruleForm.item1money,
                item1Desc: this.ruleForm.item1desc,
                item2Remark: this.ruleForm.item2remark,
                item2Money: this.ruleForm.item2money,
                item2Desc: this.ruleForm.item2desc,
                item3Remark: this.ruleForm.item3remark,
                item3Money: this.ruleForm.item3money,
                item3Desc: this.ruleForm.item3desc,
                item4Remark: this.ruleForm.item4remark,
                item4Money: this.ruleForm.item4money,
                item4Desc: this.ruleForm.item4desc,
                item5Remark: this.ruleForm.item5remark,
                item5Money: this.ruleForm.item5money,
                item5Desc: this.ruleForm.item5desc,

                totalMoney: this.totalMoney,
                paywayId: this.ruleForm.paywayId,
                paywayRemark: this.ruleForm.paywayRemark,

                userGetid: this.ruleForm.userGetid,
                bankcardGetid: this.ruleForm.bankcardGetid,
                bankGetname: this.ruleForm.bankGetname,
                companyPayId: this.ruleForm.companyPayId,
                bankPaycode: this.ruleForm.bankPaycode,
                bankPayname: this.ruleForm.bankPayname,
                filePath: this.filePath,
                expenseImage: this.imgArr2.join(),
            };
            console.log('submit!');
            editExpense2(params).then(res => {
                console.log('addExpense==', res.message);
                this.$message({
                    message: "修改成功",
                    type: 'success',
                });
            });
            this.$refs[formName].resetFields();
            this.$router.push({
                path: "/invoices/addInvoices"
            });
            // this.$refs[formName].validate((valid) => {

            //     if (valid) {

            //     }
            //     else {
            //         console.log('error submit!!');
            //         this.$message({
            //             message: "请填写完整",
            //             type: 'warning',
            //         });
            //         return false;
            //     }
            // });
        },
        //返回
        toReturn() {
            this.$tab.closeOpenPage({
                path:'/invoices/addInvoices'
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
        //取消按钮
        cancel() {
            this.imgDialog = false;
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
        toEditImg() {
            this.imgDialog = true;
            this.imgArr = [];
            console.log("toEditImg", this.imgArr);
        },
      
    },
}
</script>


<style rel="stylesheet/scss" lang="scss" scoped>
#title {
    margin-left: 600px;
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
}

.remarkCss {
    margin-left: 880px;
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
    margin-top: -50px;
}

.right {
    margin-left: 630px;
    margin-top: -50px;
}

.right2 {
    margin-left: 920px;
    margin-top: -50px;
}

#btn {
    margin-left: 320px;
    margin-top: 40px;
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
.flexs{
    display: flex;
    align-items: center;
}
// 改变input框字体颜色
::v-deep .is-disabled .el-input__inner {
    background-color: transparent !important;
    color: black;
}
</style>