<template>
    <div class="app-container">
        <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="auto" >
           <el-form-item  id="title">
                <span style="font-size:30px">报销单新增</span>  
            </el-form-item>
            <el-row type="flex" class="row-bg" style="margin-top:20px" justify="space-around">
                <el-col :span="9">
                    <el-form-item label="报销单号">
                        <el-input disabled v-model="ruleForm.expenseCode"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="9">
                    <el-form-item label="报销部门">
                        <el-input disabled v-model="ruleForm.dept"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="9">
                    <el-form-item label="报销日期" prop="expenseDate">
                        <el-date-picker v-model="ruleForm.expenseDate" type="date" placeholder="选择日期"
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
                <el-col :span="5">
                    <el-form-item>
                        <div>附件</div>
                    </el-form-item>
                    <el-form-item >
                        <el-input  type="number" v-model="ruleForm.accessoryNum1">
                            <template slot="append">
                                张
                            </template>
                        </el-input>
                    </el-form-item>
                      <el-form-item >
                        <el-input  type="number" v-model="ruleForm.accessoryNum2">
                            <template slot="append">
                                张
                            </template>
                        </el-input>
                    </el-form-item>
                      <el-form-item >
                        <el-input  type="number" v-model="ruleForm.accessoryNum3">
                            <template slot="append">
                                张
                            </template>
                        </el-input>
                    </el-form-item>
                      <el-form-item >
                        <el-input  type="number" v-model="ruleForm.accessoryNum4">
                            <template slot="append">
                                张
                            </template>
                        </el-input>
                    </el-form-item>
                      <el-form-item >
                        <el-input type="number" v-model="ruleForm.accessoryNum5">
                            <template slot="append">
                                张
                            </template>
                        </el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="7">
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
                <el-radio-group  v-model="ruleForm.paywayId" style="width:100%">
                <el-row type="flex" class="row-bg" justify="space-around">
                  <el-col :span="8" class="flexs" style="justify-content: flex-start;align-items: center;">
                      <el-radio :label="1">转账</el-radio>
                  </el-col>
                  <el-col :span="8" style="display:flex;align-items: center;">
                     <el-radio :label="2" >现金</el-radio>
                  </el-col>
                  <el-col :span="8" class="flexs">
                    <el-radio :label="3" >其他</el-radio>
                    <el-input  v-model="ruleForm.paywayRemark"></el-input>
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
                        <uploadInvoices v-if="imgArrOld.length >= 0" @getfileName="getExpense" :fileName="imgArr"
                        :fileNameOld="imgArrOld" :isDetail="isDetail"></uploadInvoices>
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
                <el-button type="danger" @click="toReturn">关闭</el-button>
                <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                <!-- </el-col> -->
                <el-col :span="8"></el-col>
            </el-row>

            <!-- <el-form-item class="btn">
                <el-button @click="toReturn" style="width:100px">取消</el-button>
                <el-button type="primary" @click="submitForm('ruleForm')" style="margin-left: 120px;width:100px">确认</el-button>

            </el-form-item> -->
        </el-form>
    </div>
</template>
<script>
    import uploadInvoices from '@/components/douploads/uploadInvoices'
    import {addCheckInvoices} from '@/api/invoices/checkInvoices'
    import {getInfo} from '@/api/login'
    import {getAllCompany,getAllGetUser} from '@/api/invoices/borrow'
    import { getDepts,getCardInfoBycompany,addExpense,getCode } from '@/api/invoices/expense'
    import { getExpenseItem } from '@/api/invoices/travelExpense'
    export default {
    components: {
     uploadInvoices
    },
    name: 'expense',
    data() {
      return {
        imgArrOld: [],
        isDetail:'0',
        isNone:[],
        roles:[],
        //影像上传参数
       
        imgDialog:false,
        dialogImageUrl: '',
        dialogVisible: false,
        imgArr:[],

        hideUpload: false,
        limitNum:10,//可上传数量

        isRemark:true,
        //   jobs:[],
        isDisabled:false,

        items:[],
        filePath:'',
        height: document.documentElement.clientHeight - 180 + 'px;',
        form: {},
        formLabelWidth: "80px",
       
        ruleForm: {
            
            expenseCode:'',//报销单号
            dept:'',//部门
            deptId:'',//部门id
            expenseDate:'',//报销日期create_time

            accessoryNum1: '',//附件数量
            accessoryNum2: '',//附件数量
            accessoryNum3: '',//附件数量
            accessoryNum4: '',//附件数量
            accessoryNum5: '',//附件数量

            item1desc:'',
            item1money:'',
            item1remark:'',
            item2desc:'',
            item2money:'',
            item2remark:'',
            item3desc:'',
            item3money:'',
            item3remark:'',
            item4desc:'',
            item4money:'',
            item4remark:'',
            item5desc:'',
            item5money:'',
            item5remark:'',

            totalMoney:'',

            paywayId:'',//付款方式
            paywayRemark:'',//付款方式其他的时候选填

            userGetid:'',//收款单位/人id
            bankcardGetid:'',//收款单位/人银行卡号
            bankGetname:'',//收款单位/人银行卡所属银行

            companyPayId:'',//付款单位id
            bankPaycode:'',//付款单位银行卡号
            bankPayname:'',//付款单位银行卡所属银行

            expenseName:'',//报销人createUser
            expenseId:'',//报销人id
            gmCheck:'',//总经理
            financeCheck:'',//财务
            dmCheck:'',//部门主管
        },
        rules: {
            // accessory: [
            //     { required: true, message: '请输入附件数', trigger: 'blur' }
            // ],
            // dept: [
            //     { required: true, message: '请选择部门', trigger: 'change' }
            // ],
            paywayId: [
                { required: true, message: '请选择付款方式', trigger: 'change' }
            ],
            // expenseDate:[
            //     { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
            // ],
            payway: [
                { required: true, message: '请选择付款方式', trigger: 'change' }
            ],
            userGetid: [
                { required: true, message: '请选择收款人', trigger: 'change' }
            ],
            bankcardGetid: [
                { required: true, message: '请输入收款账户', trigger: 'blur' }
            ],
            bankGetname: [
                { required: true, message: '请输入收款银行名', trigger: 'blur' }
            ],
            item1desc: [
                { required: true, message: '项目一必须选择', trigger: 'change' }
            ],
            item1money: [
                { required: true, message: '项目一金额必须输入', trigger: 'blur' }
            ],

            companyPayId: [
                { required: true, message: '请选择出款单位名', trigger: 'change' }
            ],
            bankPaycode: [
                { required: true, message: '请输入出款银行卡号', trigger: 'blur' }
            ],
            bankPayname: [
                { required: true, message: '请输入出款银行名', trigger: 'blur' }
            ],

        },
        payCompanys:[],//所有单位
        searchGetUsers:[],//所有收款用户信息

        //后端查询的数据
        searchDepts:[],
      }
    },
    mounted: function() {
        //获取登录用户信息
        getInfo().then(res => {
            console.log("getInfo==", res);
            this.ruleForm.expenseName=res.user.nickName;
            this.ruleForm.expenseId=res.user.userId;
            this.ruleForm.dept=res.user.dept.deptName;
            this.ruleForm.deptId=res.user.deptId;
            this.roles=res.user.roles;
            this.ruleForm.userGetid=res.user.id;
            //根据收款人id查找收款银行卡信息
            getCardInfoBycompany(res.user.userId).then(res => {
                console.log('getCardInfoBycompany==',res);
                this.ruleForm.bankcardGetid=res.payCheck;
                this.ruleForm.bankGetname=res.accountCardBank;
                this.ruleForm.userGetid=res.nickName;
            })
        })
        this.ruleForm.expenseDate=this.returnTime(new Date());
        this.getExpenseCode();
        this.getExpenseItem();
        // this.getAllDept();
        // this.getAllGetUser();
        this.getAllCompany();
        const that = this
        window.onresize = function temp() {
            that.height = document.documentElement.clientHeight - 180 + 'px;'
        }
    },
    computed: {          
        totalMoney:function(){ 
            return this.ruleForm.item1money+this.ruleForm.item2money+this.ruleForm.item3money+this.ruleForm.item4money+this.ruleForm.item5money;
        }
    },
    methods: {
        getExpense(data) {
            this.imgArr = data;
        },
        // getThree(data){
        //    this.imgArr=data;
        // },
        //选择付款方式
        paywayChange(event){
            if(event==1){
                this.isRemark=true;
            }
            if(event==2){
                this.isRemark=true;
            }
            if(event==3){
                this.isRemark=false;
            }
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
                console.log('payCompanys==',this.payCompanys);
            })
        },
        //根据出款单位id查找出款银行卡信息
        getCarInfoByCompanyId() {
            var cardInfo=this.payCompanys.find((item) =>
            item.groupCode == this.ruleForm.companyPayId);
            this.ruleForm.bankPaycode= cardInfo.groupBankAccount;
            this.ruleForm.bankPayname= cardInfo.groupOpenBank;
            console.log('cardInfo==',cardInfo);
        },
        //获取报销人信息
        // getAllGetUser() {
        //     getAllGetUser().then(res => {
        //         console.log('getAllGetUser==',res.list);
        //         this.searchGetUsers = res.list;
        //     })
        // },
        //根据收款人id查找收款银行卡信息
        // getCardInfoBycompany() {
        //     getCardInfoBycompany(this.ruleForm.userGetid).then(res => {
        //         console.log('getCardInfoBycompany==',res);
        //         this.ruleForm.bankcardGetid=res.accountCard;
        //         this.ruleForm.bankGetname=res.accountCardBank;
        //         this.ruleForm.userGetid=res.nickName;
        //     })
        // },

        getExpenseCode(){
            getCode().then(res => {
                console.log("getExpenseCode",res.message);
                this.ruleForm.expenseCode=res.message;
            })
        },
        //获取费用项目
        getExpenseItem(){
            getExpenseItem().then(res=>{
                this.items=res;
                console.log("items",this.items);
            })
        },
        handleResize () {
            this.fullWidth = document.documentElement.clientWidth
        },

        //初始化下拉部门信息
        getAllDept() {
            getDepts().then(res => {
                console.log('getDepts==',res.list);
                this.searchDepts = res.list
            }).catch(() => { })
        },

        //提交表单
        submitForm(formName) { 
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    var invoiceType=1;
                    this.roles.map(item=>{//总经理
                        if(item.id==5||item.id==6){
                            invoiceType=3;
                            // this.ruleForm.dmCheck=this.ruleForm.expenseName;
                            this.ruleForm.gmCheck=this.ruleForm.expenseName;
                        }
                    })
                    let params={
                        // dmCheck:this.ruleForm.dmCheck,
                        gmCheck:this.ruleForm.gmCheck,

                        invoiceType:invoiceType,//发起状态
                        deptId:this.ruleForm.deptId,
                        expenseCode:this.ruleForm.expenseCode,
                        dept:this.ruleForm.dept,
                        createTime:this.ruleForm.expenseDate,
                        accessoryNum1:this.ruleForm.accessoryNum1,
                        accessoryNum2:this.ruleForm.accessoryNum2,
                        accessoryNum3:this.ruleForm.accessoryNum3,
                        accessoryNum4:this.ruleForm.accessoryNum4,
                        accessoryNum5:this.ruleForm.accessoryNum5,

                        item1Remark:this.ruleForm.item1remark,
                        item1Money:this.ruleForm.item1money,
                        item1Desc:this.ruleForm.item1desc,
                        item2Remark:this.ruleForm.item2remark,
                        item2Money:this.ruleForm.item2money,
                        item2Desc:this.ruleForm.item2desc,
                        item3Remark:this.ruleForm.item3remark,
                        item3Money:this.ruleForm.item3money,
                        item3Desc:this.ruleForm.item3desc,
                        item4Remark:this.ruleForm.item4remark,
                        item4Money:this.ruleForm.item4money,
                        item4Desc:this.ruleForm.item4desc,
                        item5Remark:this.ruleForm.item5remark,
                        item5Money:this.ruleForm.item5money,
                        item5Desc:this.ruleForm.item5desc,

                        totalMoney:this.totalMoney,
                        paywayId:this.ruleForm.paywayId,
                        paywayRemark:this.ruleForm.paywayRemark,

                        userGetid:this.ruleForm.userGetid,
                        bankcardGetid:this.ruleForm.bankcardGetid,
                        bankGetname:this.ruleForm.bankGetname,
                        companyPayId:this.ruleForm.companyPayId,
                        bankPaycode:this.ruleForm.bankPaycode,
                        bankPayname:this.ruleForm.bankPayname,
                        expenseImage:this.imgArr.join(),

                        createUser:this.ruleForm.expenseId,
                    };
                    let params2={
                        invoiceCode:this.ruleForm.expenseCode,
                        checkReasult:"发起",
                        checkUser:this.ruleForm.expenseName,
                        checkDate:this.returnTime(new Date()),
                        invoiceType:1,//单据类型（报销单）
                    };
                    console.log('submit!');
                    addExpense(params).then(res => {
                        console.log('addExpense==',res.message);
                        if(res.id==0){
                            this.$message({
                                message: res.message,
                                type: 'success',
                            });
                            addCheckInvoices(params2);
                        }else{
                             this.$message({
                                message: res.message,
                                type: 'warning',
                            });
                        }
                        this.$refs[formName].resetFields();
                        this.$router.push({
                            path: "/invoices/addInvoices"
                        });
                    });
                }
                else {
                    console.log('error submit!!');
                    this.$message({
                        message: "请填写完整",
                        type: 'warning',
                    });
                    return false;
                }
            });
        },
        //重置
        // resetForm(formName) {
        //     this.$refs[formName].resetFields();
        //     console.log("重置");
        // },
        //返回
        toReturn(){
            this.$router.push({
                 path: "/invoices/addInvoices"
            })
        },
        //返回当前时间
        returnTime(time2){
            var time=new Date(time2);
            return time.toLocaleDateString();
        },
        //转换大小写
        digitUppercase(n=0) {
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
    },
  }
</script>


<style rel="stylesheet/scss" lang="scss" scoped>
    #title{
        margin-left: 560px;
        margin-top: 10px;
        margin-bottom: 30px;
    }

    .descCss{
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
    .yuan{
        margin-left: 180px;
        margin-top: -30px;
    }


    .left{
        width: 400px;
    }
    .middle{
        margin-left: 310px;
        margin-top: -60px;
    }
    .right{
        margin-left: 630px;
        margin-top: -60px;
    }
    .right2{
        margin-left: 920px;
        margin-top: -60px;
    }
    .btn{
      margin-left:320px;
      margin-top:40px;
    }
    .btn2{
      margin-left:160px;
      margin-top:30px;
    }
    .inputCss{
        width: 200px;
    }
    ::v-deep .el-input-number .el-input__inner {
        text-align: left;
    }
    ::v-deep .vue-treeselect__control,::v-deep .vue-treeselect__placeholder,::v-deep .vue-treeselect__single-value {
        height: 30px;
        line-height: 30px;
    }

     // 改变input框字体颜色
     ::v-deep .is-disabled .el-input__inner{
        background-color: transparent !important;
        color: black;
    }
</style>
