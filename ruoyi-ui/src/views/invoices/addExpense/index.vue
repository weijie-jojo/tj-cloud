<template>
    <div class="app-container">
        <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="200px" >
            <el-form-item id="title" >
                <span style="font-size:30px">报销单</span>
            </el-form-item>
            <el-form-item label="报销单号："  class="left">
                <div >{{ruleForm.expenseCode}}</div>
            </el-form-item>

            <el-form-item label="报销部门："   class="middle">
                <el-input
                    disabled
                    v-model="ruleForm.dept"
                    style="width:200px"
                ></el-input>
            </el-form-item>

            <el-form-item label="报销日期："   prop="expenseDate" class="right" >
                <el-date-picker
                    disabled
                    v-model="ruleForm.expenseDate"
                    type="date"
                    placeholder="选择日期"
                    style="width:200px">
                </el-date-picker>
            </el-form-item>

            <el-form-item label="附件："  prop="accessory"  class="right2" >
                <el-input-number v-model="ruleForm.accessory" :precision="0" :step="1" :min="0"  style="width:160px"></el-input-number>
                <span style="margin-left:20px">张</span>
            </el-form-item>
            <el-form-item label="费用项目" style="margin-left:65px;margin-top:20px">
            </el-form-item>
            <el-form-item  prop="item1desc" class="descCss">
                <el-select
                    v-model="ruleForm.item1desc"
                    clearable
                    placeholder="请选择">
                    <el-option
                        v-for="item in items"
                        :key="item.id"
                        :label="item.itemName"
                        :value="item.itemName">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item   class="descCss">
                <el-select
                    v-model="ruleForm.item2desc"
                    clearable
                    placeholder="请选择">
                    <el-option
                        v-for="item in items"
                        :key="item.id"
                        :label="item.itemName"
                        :value="item.itemName">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item  class="descCss">
                <el-select
                    v-model="ruleForm.item3desc"
                    clearable
                    placeholder="请选择">
                    <el-option
                        v-for="item in items"
                        :key="item.id"
                        :label="item.itemName"
                        :value="item.itemName">
                    </el-option>
                </el-select>
            </el-form-item>
                <el-form-item  class="descCss">
                <el-select
                    v-model="ruleForm.item4desc"
                    clearable
                    placeholder="请选择">
                    <el-option
                        v-for="item in items"
                        :key="item.id"
                        :label="item.itemName"
                        :value="item.itemName">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item class="descCss">
                <el-select
                    v-model="ruleForm.item5desc"
                    clearable
                    placeholder="请选择">
                    <el-option
                        v-for="item in items"
                        :key="item.id"
                        :label="item.itemName"
                        :value="item.itemName">
                    </el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="金额" style="margin-left:520px;margin-top:-410px">
            </el-form-item>
            <el-form-item  class="moneyCss" prop="item1money">
                <el-input-number v-model="ruleForm.item1money" :precision="2" :step="0.01" :min="0"  style="width:160px"></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
             <el-form-item  class="moneyCss">
                <el-input-number v-model="ruleForm.item2money" :precision="2" :step="0.01" :min="0"  style="width:160px"></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
            <el-form-item class="moneyCss" >
                <el-input-number v-model="ruleForm.item3money" :precision="2" :step="0.01"  :min="0"  style="width:160px"></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
             <el-form-item  class="moneyCss">
                <el-input-number v-model="ruleForm.item4money" :precision="2" :step="0.01" :min="0"  style="width:160px"></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
            <el-form-item  class="moneyCss" >
                <el-input-number v-model="ruleForm.item5money" :precision="2" :step="0.01" :min="0"  style="width:160px"></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>

            <el-form-item label="备注" style="margin-left:1020px;margin-top:-390px">
            </el-form-item>
            <el-form-item  class="remarkCss" >
                <el-input  v-model="ruleForm.item1remark" placeholder="请输入备注" style="width:240px" ></el-input>
            </el-form-item>
            <el-form-item  class="remarkCss">
                <el-input  v-model="ruleForm.item2remark" placeholder="请输入备注" style="width:240px" ></el-input>
            </el-form-item>
            <el-form-item  class="remarkCss">
                <el-input  v-model="ruleForm.item3remark" placeholder="请输入备注" style="width:240px" ></el-input>
            </el-form-item>
            <el-form-item  class="remarkCss">
                <el-input  v-model="ruleForm.item4remark" placeholder="请输入备注" style="width:240px" ></el-input>
            </el-form-item>
            <el-form-item  class="remarkCss" style="margin-bottom:20px">
                <el-input  v-model="ruleForm.item5remark" placeholder="请输入备注" style="width:240px" ></el-input>
            </el-form-item>

            <el-form-item  label="合计金额(小写)：" class="left" >
                <el-input
                    disabled
                    v-model="totalMoney"
                    style="width:160px"></el-input>
                <div class="yuan">元</div>
            </el-form-item>
            <el-form-item  label="合计金额(大写)：" class="right2">
                {{digitUppercase(totalMoney)}}
            </el-form-item>

            <el-form-item label="付款方式："  prop="paywayId" >
                <el-radio-group
                    v-model="ruleForm.paywayId">
                    <el-radio
                        :label="1"
                        @change="paywayChange"
                    >转账</el-radio>
                    <el-radio :label="2"
                        @change="paywayChange"
                        style="margin-left:330px"
                    >现金</el-radio>
                    <el-radio
                        :label="3"
                        @change="paywayChange"
                        style="margin-left:335px"
                    >其他</el-radio>
                    <el-input
                        :disabled="isRemark"
                        v-model="ruleForm.paywayRemark"
                        style="width:210px"
                    ></el-input>
                </el-radio-group>
            </el-form-item>

            <el-form-item label="收款人：" >
                <el-input
                    v-model="ruleForm.expenseName"
                    style="width:210px"
                    disabled
                ></el-input>
                <!-- <el-select
                    v-model="ruleForm.userGetid"
                    @change="getCardInfoBycompany"
                    placeholder="请选择收款单位"
                    style=" width: 200PX;"
                    :disabled="isDisabled">
                    <el-option
                        v-for="item in searchGetUsers"
                        :key="item.userId"
                        :label="item.nickName"
                        :value="item.userId"
                    > </el-option>
                </el-select> -->
            </el-form-item>
            <el-form-item label="收款帐号："   style="margin-top: -50px;margin-left:440px">
                <el-input
                    v-model="ruleForm.bankcardGetid"
                    style="width:200px"
                    disabled
                ></el-input>
            </el-form-item>
            <el-form-item label="收款开户行："   style="margin-top: -50px;margin-left:920px">
                 <el-input
                    v-model="ruleForm.bankGetname"
                    style="width:200px"
                    disabled
                ></el-input>
            </el-form-item>
            <el-form-item label="报销单位："  prop="companyPayId" class="left">
                <el-select
                    v-model="ruleForm.companyPayId"
                    placeholder="请选择付款单位"
                    @change="getCarInfoByCompanyId"
                    style=" width: 200PX;">
                    <el-option
                        v-for="item in payCompanys"
                        :key="item.groupCode"
                        :label="item.groupName"
                        :value="item.groupCode"
                    > </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="报销帐号："  prop="bankPaycode" style="margin-top: -50px;margin-left:440px">
                 <el-input
                    v-model="ruleForm.bankPaycode"
                    style="width:200px"
                    disabled
                ></el-input>
            </el-form-item>
            <el-form-item label="报销开户行："  prop="bankPayname" style="margin-top: -50px;margin-left:920px">
                 <el-input
                    v-model="ruleForm.bankPayname"
                    style="width:200px"
                    disabled
                ></el-input>
            </el-form-item>

            <el-form-item label="报销凭证影像："   class="left">
                <el-button
                    type="primary"
                    @click="imgDialog=true"
                >点击上传</el-button>
            </el-form-item>
            <!-- 上传报销凭证影像 -->
            <el-dialog title="上传图片" :visible.sync="imgDialog" width="70%">
                <el-upload
                    action="http://36.133.2.179:8000/api/files/doUpload"
                    list-type="picture-card"
                    :on-preview="handlePictureCardPreview"
                    :on-remove="handleRemove"
                    :on-success="success"
                    :before-upload="beforeAvatarUpload"
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
                class="btn2">返回</el-button>
            </el-dialog>

            <el-form-item label="报销人："  class="right2" >
                <el-input
                    v-model="ruleForm.expenseName"
                    class="inputCss"
                    placeholder=""
                    disabled
                ></el-input>
            </el-form-item>

            <el-form-item class="btn">
                <el-button @click="toReturn" style="width:100px">取消</el-button>
                <el-button type="primary" @click="submitForm('ruleForm')" style="margin-left: 120px;width:100px">确认</el-button>

            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    import {addCheckInvoices} from '@/api/invoices/checkInvoices'
    import {getInfo} from '@/api/login'
    import {getAllCompany,getAllGetUser} from '@/api/invoices/borrow'
    import { getDepts,getCardInfoBycompany,addExpense,getCode } from '@/api/invoices/expense'
    import { getExpenseItem } from '@/api/invoices/travelExpense'
    export default {
    name: 'expense',
    data() {
      return {
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
            accessory:'',//附件数量accessory_num

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
            accessory: [
                { required: true, message: '请输入附件数', trigger: 'blur' }
            ],
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
                    // this.ruleForm.totalMoney=this.totalMoney;
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
                        accessoryNum:this.ruleForm.accessory,

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

    //上传图片
    handleRemove(file) {
        this.imgArr.map((item,index)=>{
            if(item==file.name){
                this.imgArr.splice(index, 1);
            }
        })
        console.log("imgArr",this.imgArr);
        this.$message("取消上传（只允许图片格式）");
        
    },
    //点+可以放大查看图片
    handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
    },
    success(file) {
        this.$message(file.message);
        this.imgArr.push(file.obj);
        console.log( "imgArr",this.imgArr);
    },
    beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('图片格式不对!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
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
        margin-left: 450px;
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
