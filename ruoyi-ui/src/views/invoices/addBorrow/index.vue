<template>
    <div class="app-container">      
        <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="200px" >
            <el-form-item id="title" >
                <span style="font-size:30px">借支单</span>                  
            </el-form-item>

            <el-form-item label="借支单编号" >
                <span>{{ruleForm.borrowCode}}</span>                  
            </el-form-item>

            <el-form-item label="借款日期"   prop="borrowDate" class="middle2" >  
                <el-date-picker
                    v-model="ruleForm.borrowDate"
                    type="date"
                    placeholder="选择日期"
                    style="width:200px"
                    disabled>
                </el-date-picker>
            </el-form-item>

            <el-form-item label="部门"  class="right2">
                <el-input  v-model="ruleForm.deptName"  style="width:200px" disabled></el-input>
            </el-form-item>
                 
            <el-form-item label="借支人姓名"  >
                <el-input  v-model="ruleForm.borrowName"  style="width:200px" disabled></el-input> 
            </el-form-item>

            <el-form-item label="职务"    class="right2" >
                <el-input  v-model="ruleForm.job"  style="width:200px" disabled></el-input> 
                <!-- <el-select v-model="ruleForm.job" placeholder="请选择职务" clearable >
                    <el-option v-for="item in jobs" :key="item.id" :label="item.name" :value="item.id"> </el-option>
                </el-select> -->
            </el-form-item>

             <el-form-item label="借支事由"   prop="borrowDesc" class="left" >
                <el-input
                    type="textarea"
                    :autosize="{ minRows: 8, maxRows: 20}"
                    placeholder="请输入借支事由"
                    v-model="ruleForm.borrowDesc"
                    style="width:1120px">
                </el-input>
            </el-form-item>
            <el-form-item  label="借款金额" prop="totalMoney" id="left" >
                <el-input-number 
                    v-model="ruleForm.totalMoney" 
                    :precision="2" 
                    :step="0.1" :min="0" 
                    style="width:160px"  
                ></el-input-number>
                <span>元</span>
            </el-form-item>
            <el-form-item  label="人民币(大写)" class="middle2">                 
                {{digitUppercase(ruleForm.totalMoney)}}      
            </el-form-item>
            
            <el-form-item label="支付方式"  prop="paywayId" id="left">
                <el-select 
                    v-model="ruleForm.paywayId" 
                    placeholder="请选择支付方式"  
                    clearable  
                    style=" width: 200PX;">
                    <el-option 
                        v-for="item in searchPayways" 
                        :key="item.detailId" 
                        :label="item.label" 
                        :value="item.detailId"
                    ></el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="计划还款时间"   class="middle2" >  
                <el-date-picker
                    v-model="ruleForm.payDate"
                    type="date"
                    style="width:200px">
                </el-date-picker>
            </el-form-item>

            <el-form-item label="收款人"   class="left">
                <el-input 
                    v-model="ruleForm.borrowName"
                    style="width:200px"
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
            <el-form-item label="收款账号"    class="middle2">
                <el-input 
                    v-model="ruleForm.bankcardGetid" 
                    style="width:200px"
                    disabled
                ></el-input>
            </el-form-item>
            <el-form-item label="收款开户行"  class="right2">
                <el-input 
                    v-model="ruleForm.bankGetname" 
                    style="width:200px"
                    disabled
                ></el-input>
            </el-form-item>

             <el-form-item label="借款单位"  prop="companyPayId"  class="left">
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
            <el-form-item label="借款账号"    class="middle2">
              <el-input 
                    v-model="ruleForm.bankPaycode" 
                    style="width:200px"
                    disabled
                ></el-input>
            </el-form-item>
            <el-form-item label="借款开户行"   class="right2">
                <el-input 
                    v-model="ruleForm.bankPayname" 
                    style="width:200px"
                    disabled
                ></el-input>
            </el-form-item>

            <el-form-item id="btn">
                <el-button @click="toReturn" style="width:100px">取消</el-button>
                <el-button type="primary" @click="submitForm('ruleForm')" style="width:100px;margin-left:80px">确认</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    import {addCheckInvoices} from '@/api/invoices/checkInvoices'
    // import {getAllCheck} from '@/api/invoices/checkInvoices'
    import {getAllPayway,getCardInfoBycompany,getBankNameBycardId} from '@/api/invoices/expense'
    import {addBorrow,getCode,getAllCompany,getAllGetUser} from '@/api/invoices/borrow'
    import {getInfo} from '@/api/login'
    export default {
    name: 'borrow',
    data() {
      return {
        roles:[],
        filePath1:'',//文件路径1
        filePath2:'',//文件路径2
        rejectReasult:'',//驳回原因
        isAgree:'1',//是否同意 1 同意 2驳回
        checks:[],//所有审批结果
        isDisabled:false,
        companyCardInfos:[],//所选借款单位对应的银行卡信息
        userCardInfos:[],//所选收款人对应的银行卡信息
        searchGetUsers:[],//所有借款人
        payCompanys:[],//所有借款单位
        jobs:[],//登录用户拥有的职务
        height: document.documentElement.clientHeight - 180 + 'px;',
        form: {},
        formLabelWidth: "80px",
        ruleForm: {
            borrowCode:'',//借支单号
            borrowDate:'',//借款日期
            deptName:'',//部门
            borrowName:'',//借款人名
            createUser:'',//借款人id
            job:'',//职务
            borrowDesc:'',//借支事由
            totalMoney:'',//总借款金额
            paywayId:'',//付款方式id
            payDate:'',//还款日期

            companyPayId:'',//付款单位id
            bankPaycode:'',//付款单位银行卡号
            bankPayname:'',//付款单位银行卡所属银行

            userGetid:'',//收款单位/人id
            bankcardGetid:'',//收款单位/人银行卡号
            bankGetname:'',//收款单位/人银行卡所属银行

          
            deptId:'',//部门id
            gmCheck:'',//总经理
            dmCheck:'',//部门主管
            financeCheck:'',//财务
        },
        rules: {
            borrowDate:[
                { type: 'date', required: true, message: '请选择借款日期', trigger: 'change' }
            ],
            borrowDesc: [
                { required: true, message: '请输入借支事由', trigger: 'blur' }
            ],
            totalMoney: [
                { required: true, message: '请输入借款金额', trigger: 'blur' }
            ],
            paywayId: [
                { required: true, message: '请选择付款方式', trigger: 'change' }
            ],

            companyPayId: [
                { required: true, message: '请选择借款单位', trigger: 'change' }
            ],
            userGetid: [
                { required: true, message: '请选择收款人', trigger: 'change' }
            ],
        
        },
        //后端查询的数据
        searchPayways:[],
        borrows:[],
      }
    },
    mounted: function() {
        this.ruleForm.borrowDate=new Date();
        this.getAllCompany();
        this.getAllGetUser();
        this.getBorrowCode();
        this.getLoginUser();
        this.getAllPayway();
        const that = this
        window.onresize = function temp() {
            that.height = document.documentElement.clientHeight - 180 + 'px;'
        };
        // this.borrows = JSON.parse(window.localStorage.getItem('borrows')).list
        // console.log("borrows==",this.borrows[0]);

        // this.ruleForm.borrowCode=this.borrows[0].borrowCode;
        
        //  this.type=location.search.split('=')[1];
        // console.log("type==",this.type);
        // if(this.borrows.length){

        // }
    },
    methods: {
        //审核
        // checkInvoices(){
        //     var params={
        //         filePath2:this.filePath2,
        //         rejectReasult:this.rejectReasult,
        //         isPass:this.isAgree,
        //         checkUser:this.rejectReasult,
        //     };
            
        //     addBorrow(params).then(res => {
        //         console.log('addCheckInvoices==',res.message);
        //         this.$message({
        //             message: res.message,
        //             type: 'success',
        //         });
        //     })    
        // },
        //获取所有审批结果
        // selectAllCheck(){
        //     getAllCheck({invoiceType:3,invoiceCode:"JZ202205060002"}).then(res => {
        //         console.log('selectAllCheck==',res);
        //         this.checks = res
        //     })
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
        getAllGetUser() {
            getAllGetUser().then(res => {
                console.log('getAllGetUser==',res.list);
                this.searchGetUsers = res.list;
            })
        }, 
        //根据收款人id查找收款银行卡信息 
        getCardInfoBycompany() {
            console.log("userGetid11==",this.ruleForm.userGetid);
            getCardInfoBycompany(this.ruleForm.userGetid).then(res => {
                console.log('getCardInfoBycompany==',res);
                this.ruleForm.bankcardGetid=res.accountCard;
                this.ruleForm.bankGetname=res.accountCardBank;
                this.ruleForm.userGetid=res.nickName;
            })
        }, 
        selectPayWay(){
            console.log("paywayId",this.ruleForm.paywayId);
            if(this.ruleForm.paywayId==17){
                this.isDisabled=true;
                this.ruleForm.userGetid="";
                this.ruleForm.bankcardGetid="";
                this.ruleForm.bankGetname="";
            }
            if(this.ruleForm.paywayId==18){
                this.isDisabled=false;
            }
        },
        //根据收款人id查找收款银行卡信息 
        getCardInfoByUser() {
            this.ruleForm.cardGetId="";
            this.ruleForm.bankGetname="";
            getCardInfoBycompany(this.ruleForm.userGetid).then(res => {
                console.log('userCardInfos==',res.list);
                this.userCardInfos = res.list
            })
        }, 
        //根据收款单位银行卡id查找对应的银行名
        getBankNameByUserCardId() {
            getBankNameBycardId(this.ruleForm.cardGetId).then(res => {
                console.log('getBankNameByUserCardId==',res.message);
                this.ruleForm.bankGetname = res.message
            })
        },

        //根据借款单位id查找借款银行卡信息 
        getBankNameByCompanyCardId() {
            var cardInfo=this.payCompanys.find((item) => item.companyId == this.ruleForm.companyPayId);
            this.ruleForm.bankcardGetid= cardInfo.account;
            this.ruleForm.bankPayname= cardInfo.accountOpeningBank;
            console.log('cardInfo==',cardInfo);
        }, 

        //初始化下拉借款单位信息 
        getAllCompany() {
            getAllCompany().then(res => {  
                this.payCompanys = res.list;
                console.log('payCompanys==',this.payCompanys);
            })
        },   
        getBorrowCode(){
            this.type=1;
            // var date = new Date();
            // var Y = date.getFullYear();
            // var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1):date.getMonth()+1);
            // var D = (date.getDate()< 10 ? '0'+date.getDate():date.getDate())+ ' ';
            // //查询借支单的数量
            // getBorrow({currentPage:0,limit:0,}).then(res => {
            //     var count=res.count+1;
            //     this.borrowCode=Y+M+D+"000"+count;
            // })
            getCode().then(res => {
                console.log("borrowCode",res.message);
                this.ruleForm.borrowCode=res.message;
            })
        },
        getLoginUser(){
            getInfo().then(res => {
                console.log("getInfo==",res);
                this.ruleForm.createUser=res.user.id;
                this.ruleForm.borrowName=res.user.nickName;
                this.ruleForm.deptName=res.user.dept.name;
                this.ruleForm.deptId=res.user.dept.id;
                this.ruleForm.job=res.user.jobs[0].name;
                this.roles=res.user.roles;
                //根据收款人id查找收款银行卡信息 
                getCardInfoBycompany(res.user.id).then(res => {
                    console.log('getCardInfoBycompany==',res);
                    this.ruleForm.bankcardGetid=res.accountCard;
                    this.ruleForm.bankGetname=res.accountCardBank;
                    this.ruleForm.userGetid=res.nickName;
                })
            })
        },
        handleResize () {
            this.fullWidth = document.documentElement.clientWidth
        }, 
       
        //初始化下拉付款方式信息 
        getAllPayway() {
            getAllPayway().then(res => {
                console.log('getAllPayway==',res.list);
                this.searchPayways = res.list
            })
        }, 
        //提交表单
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    var invoiceType=1;
                    this.roles.map(item=>{//总经理
                        if(item.id==5){
                            invoiceType=2;
                            this.ruleForm.dmCheck=this.ruleForm.borrowName;
                        }
                    })
                    let params={
                        dmCheck:this.ruleForm.dmCheck,

                        invoiceType:invoiceType,
                        deptId:this.ruleForm.deptId,
                        createUser:this.ruleForm.createUser,
                        borrowCode:this.ruleForm.borrowCode,
                        borrowDate:this.returnTime(this.ruleForm.borrowDate),
                        deptName:this.ruleForm.deptName,
                        borrowName:this.ruleForm.borrowName,
                        job:this.ruleForm.job,                       
                        borrowDesc:this.ruleForm.borrowDesc,
                        totalMoney:this.ruleForm.totalMoney,
                        paywayId:this.ruleForm.paywayId,

                        payCompanyId:this.ruleForm.companyPayId,
                        cardPayId:this.ruleForm.bankPaycode,
                        bankNamePay:this.ruleForm.bankPayname,

                        getUserId:this.ruleForm.userGetid,
                        cardGetId:this.ruleForm.bankcardGetid,
                        bankNameGet:this.ruleForm.bankGetname,

                        payDate:this.returnTime(this.ruleForm.payDate),
                        filePath1:this.filePath1,

                    };
                    let params2={
                        invoiceCode:this.ruleForm.borrowCode,
                        checkReasult:"发起",
                        checkUser:this.ruleForm.borrowName,
                        checkDate:this.returnTime(new Date()),
                        invoiceType:3,//单据类型
                    };
                    console.log('submit!');
                    addBorrow(params).then(res => {
                        console.log('addExpense==',res.message);
                        if(res.id==0){
                            this.$message({
                                message: res.message,
                                type: 'success',
                            });
                            addCheckInvoices(params2)
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
         //上传文件  
        //填单人 
        handleRemove1(file) {
            console.log(file);
            this.filePath1=undefined;
        },
        //点击文件时
        handlePreview1(file) {
            console.log(file);
        },
        //上传成功后
        success1(file) {
            this.$message(file.message);
            var fileUrl = file.obj;
            this.filePath1 = fileUrl;
            console.log("filePath1=="+this.filePath1);
        },
        //删除前
        beforeRemove1(file) {
            return this.$confirm(`确定移除 ${ file.name }？`);
        },  
        //财务
         //删除文件后 
        handleRemove2(file) {
            console.log(file);
            this.filePath2=undefined;
        },
        //点击文件时
        handlePreview2(file) {
            console.log(file);
        },
        //上传成功后
        success2(file) {
            this.$message(file.message);
            var fileUrl = file.obj;
            this.filePath2 = fileUrl;
            console.log("filePath2=="+this.filePath2);
        },
        //删除前
        beforeRemove2(file) {
            return this.$confirm(`确定移除 ${ file.name }？`);
        }    
    },
  }
</script>


<style rel="stylesheet/scss" lang="scss" scoped>
    #title{
        margin-left: 420px; 
        margin-top: 30px;
        margin-bottom: 30px;
    }

    // .descCss{
    //    width: 490px;
    // }
    // .moneyCss {
    //     width: 380px;
    //     margin-left: 300px;
    //     margin-top: -50px;
    // }
    // .yuan{
    //     margin-left: 180px;
    //     margin-top: -30px; 
    // }
    // .remarkCss {
    //     width: 440px;
    //     margin-left: 540px;
    //     margin-top: -55px;
    // }
    
    .left{
        width: 400px;
    }
    .middle{
        margin-left: 310px;
        margin-top: -50px;
    }
    .middle2{
        margin-left: 420px;
        margin-top: -50px;
    }
    .right{
        margin-left: 630px;
        margin-top: -50px;
    }
    .right2{
        margin-left: 920px;
        margin-top: -50px;
    }
    #btn{
      margin-left:320px;
      margin-top:50px;
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