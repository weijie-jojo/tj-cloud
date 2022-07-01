<template>
    <div class="app-container">
        <el-button 
            v-print="'#printMe'" 
            type="primary"
            style="margin-left:1180px;margin-bottom: -50px;">打印</el-button> 

        <el-form 
            ref="ruleForm" 
            :model="ruleForm" 
            :rules="rules" 
            label-width="200px" 
            id="printMe"
            style="margin-left:-80px">

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
            </el-form-item>

             <el-form-item label="借支事由"   prop="borrowDesc" class="left" >
                <el-input
                    disabled
                    type="textarea"
                    :autosize="{ minRows: 8, maxRows: 20}"
                    placeholder="请输入借支事由"
                    v-model="ruleForm.borrowDesc"
                    style="width:1120px">
                </el-input>
            </el-form-item>
            <el-form-item  label="借款金额" prop="totalMoney" id="left" >
                <el-input-number 
                    disabled
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
                    disabled
                    v-model="ruleForm.paywayId" 
                    @change="selectPayWay"
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
                    disabled
                    type="date"
                    style="width:200px">
                </el-date-picker>
            </el-form-item>

            <el-form-item label="收款人"   class="left">
                 <el-input 
                    v-model="ruleForm.userGetid" 
                    style="width:200px"
                    disabled
                ></el-input>
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
                    disabled
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

            <el-form-item label="总经理"   class="left" >
                <el-input  v-model="ruleForm.gmCheck" placeholder="" class="inputCss" disabled></el-input> 
            </el-form-item>
            <el-form-item label="财务"   class="middle" >
                <el-input  v-model="ruleForm.financeCheck" placeholder="" class="inputCss" disabled></el-input> 
            </el-form-item>
            <el-form-item label="部门主管"   class="right" >
                <el-input  v-model="ruleForm.dmCheck" placeholder="" class="inputCss"  disabled></el-input> 
            </el-form-item>
            <el-form-item label="借款人"  class="right2" >
                <el-input  v-model="ruleForm.borrowName" placeholder="" class="inputCss" disabled></el-input> 
            </el-form-item>
           
            <el-form-item style="margin-top:100px;margin-top:300px">
                <div style=" font-size:20px;margin-top:10px;margin-bottom:20px;color:blue">{{"审批进度"}}</div>
                <el-table
                    border
                    :data="checks"
                    style="width: 65%"
                    >
                    <el-table-column
                        prop="checkDate"
                        label="时间"
                        width="180"/>
                    <el-table-column
                        prop="checkUser"
                        label="人员"
                        width="180"/>
                    <el-table-column
                        prop="checkReasult"
                        label="结果"/>
                </el-table>
            </el-form-item>

            <el-form-item 
                v-show="isShowImg" 
                class="demo-image__preview" 
                v-for="(item,index) in imgArr" :key="index" 
                style="margin-top:200px;margin-left: -60px;">
                <span  class="imgTitle">付款凭证影像</span>
                <el-image
                   style="width:1100px;height: 700px;"
                    :src="baseImgPath+item.value"
                ></el-image>
            </el-form-item>

            <el-form-item 
                v-show="isShowImg2"
                class="demo-image__preview" 
                style="margin-top:200px;margin-left: -60px;">
                <span class="imgTitle">还凭证影像</span>
                <el-image
                   style="width:1100px;height: 700px;"
                    :src="imgpath2"
                ></el-image>
            </el-form-item>

        </el-form>
        <div style="margin-left:100px;margin-top:40px">
           付款凭证影像： 
            <el-button  
                type="primary"
                @click="getImage"
            >点击查看</el-button>
        </div>
        <div 
            style="margin-left:400px;margin-top: -32px;">
            还款凭证影像： 
            <el-button  
                type="primary"
                @click="getImage2"
            >点击查看</el-button>
        </div>
        <!-- 图片 -->
        <el-dialog title="图片" :visible.sync="imageVisible" width="60%">
            <div class="demo-image" 
                v-for="(item,index) in imgArr" :key="index" 
                style="margin-top:20px;">
                <el-image
                    style="width: 300px; height: 200px"
                    :src="baseImgPath+item.value"
                    :preview-src-list="srcList"
                ></el-image>
            </div>
            <!-- <div class="demo-image">
                <el-image
                   style="width:1100px;height: 700px;"
                    :src="imgpath"
                ></el-image>
            </div> -->
        </el-dialog>
        <el-dialog title="图片" :visible.sync="imageVisible2" width="60%">
            <div class="demo-image">
                <el-image
                    style="width:1100px;height: 700px;"
                    :src="imgpath2"
                ></el-image>
            </div>
        </el-dialog>
    </div>
</template>
<script>
    import {getAllCheck,addCheckInvoices} from '@/api/invoices/checkInvoices'
    import {getCardInfoBycompany,getBankNameBycardId} from '@/api/invoices/expense'
    import {addBorrow,getCode,getAllCompany,getAllGetUser,editBorrowByBorrowId} from '@/api/invoices/borrow'
    import {getInfo} from '@/api/login'
    export default {
    name: 'borrow',
    data() {
      return {
        imgArr:[],
        srcList:[],

        isShowImg:false,
        isShowImg2:false,

        baseImgPath:"http://36.133.2.179:8000/api/files/showImg?imgPath=",

        imgpath:'',
        borrowImage:'',
        imageVisible:false,

        imgpath2:'',
        borrowImage2:'',
        imageVisible2:false,

        filePath1:'',//文件路径1
        filePath2:'',//文件路径2
        rejectReasult:'',//驳回原因
        isAgree:1,//是否同意 1 同意 2驳回
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
            gm:'',//总经理
            dm:'',//部门主管
            finance:'',//财务

            gmCheck:'',
            financeCheck:'',
            dmCheck:'',

            role:'',
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
        borrowId:'',
        checkType:'',
        //后端查询的数据
        searchPayways:[],
        borrows:[],//单据审核管理页面调整传过来的借支单数据
      }
    },
    mounted: function() {
        this.searchPayways=this.dict.type.pay_way;
        this.selectAllCheck();
        // this.ruleForm.payDate="";
        // this.ruleForm.borrowDate=new Date();
        this.getAllCompany();
        // this.getAllGetUser();
        // this.getBorrowCode();
        // this.getLoginUser();
        const that = this
        window.onresize = function temp() {
            that.height = document.documentElement.clientHeight - 180 + 'px;'
        };
        this.borrows = JSON.parse(window.localStorage.getItem('borrows')).list
        console.log("borrows==",this.borrows[0]);
        this.ruleForm.borrowCode=this.borrows[0].borrowCode;
        this.ruleForm.borrowDate=this.borrows[0].borrowDate;
        this.ruleForm.deptName=this.borrows[0].name;
        this.ruleForm.borrowName=this.borrows[0].borrowName;
        this.ruleForm.userId=this.borrows[0].userId;
        this.ruleForm.job=this.borrows[0].job;
        this.ruleForm.borrowDesc=this.borrows[0].borrowDesc;
        this.ruleForm.totalMoney=this.borrows[0].totalMoney;
        this.ruleForm.paywayId=this.borrows[0].label;
        this.ruleForm.payDate=this.borrows[0].payDate;

        this.ruleForm.companyPayId=this.borrows[0].payCompanyId;
        this.ruleForm.bankPaycode=this.borrows[0].cardPayId;
        this.ruleForm.bankPayname=this.borrows[0].bankNamePay;
        this.ruleForm.userGetid=this.borrows[0].getUserId;
        this.ruleForm.bankcardGetid=this.borrows[0].cardGetId;
        this.ruleForm.bankGetname=this.borrows[0].bankNameGet;

        this.ruleForm.deptName=this.borrows[0].deptName;
        this.ruleForm.deptId=this.borrows[0].deptId;

        this.ruleForm.gm=JSON.parse(window.localStorage.getItem('borrowCheckPerson'));
        this.ruleForm.finance=JSON.parse(window.localStorage.getItem('borrowCheckPerson'));
        this.ruleForm.dm=JSON.parse(window.localStorage.getItem('borrowCheckPerson'));

        this.borrowId=this.borrows[0].borrowId;

        this.borrowRoles = JSON.parse(window.localStorage.getItem('borrowRoles'));
        this.ruleForm.role=JSON.parse(window.localStorage.getItem('borrowRoles'))[0].id;
        
        this.ruleForm.gmCheck=this.borrows[0].gmCheck;
        this.ruleForm.financeCheck=this.borrows[0].financeCheck;
        this.ruleForm.dmCheck=this.borrows[0].dmCheck;

        this.borrowImage=this.borrows[0].borrowImage;
        this.borrowImage2=this.borrows[0].borrowImage2;
        
        var imgArr=JSON.parse(this.borrowImage);
        if (imgArr.length<=0) {
            // this.imgArr.push({id:0,value:"404.jpg"})
            console.log("404");
            this.isShowImg=false;
        }else{
            imgArr.map((item,index)=>{
                if(item!=null){
                    this.imgArr.push({id:index,value:item.value});
                }
            })
            this.isShowImg=true;
        }
        
        // if (this.borrowImage==""||this.borrowImage==undefined) {
        //     // this.imgpath =this.baseImgPath+"404.jpg";
        //     console.log("404");
        //     this.isShowImg=false;
        // }else{
        //     this.imgpath =this.baseImgPath+this.borrowImage
        //     this.isShowImg=true;
        // }

        if (this.borrowImage2==""||this.borrowImage2==undefined) {
            // this.imgpath2 =this.baseImgPath+"404.jpg";
            console.log("404");
            this.isShowImg2=false;
        }else{
            this.imgpath2 =this.baseImgPath+this.borrowImage2
            this.isShowImg2=true;
        }



    },
    methods: {
       //审核
        checkInvoices(){
            console.log("role==",this.ruleForm.role);
            if(this.ruleForm.role==1||this.ruleForm.role==5){
                if(this.ruleForm.gmCheck==undefined||this.ruleForm.gmCheck==""){//未审核过
                  console.log("总经理审核");
                    if(this.isAgree==1){
                        this.rejectReasult="总经理审批同意";
                        this.checkType=3;
                    };
                    if(this.isAgree==2){//驳回
                        this.checkType=6;
                    };
                    var params1={
                        invoiceCode:this.ruleForm.borrowCode,
                        checkReasult:this.rejectReasult,
                        checkUser:this.ruleForm.gm,
                        checkDate:this.returnTime(new Date()),
                        invoiceType:3,
                    };
                    var params2={
                        filePath2:this.filePath2,
                        gmCheck:this.ruleForm.gm,
                        borrowId:this.borrowId,
                        invoiceType:this.checkType,
                    };
                    addCheckInvoices(params1).then(res => {
                        this.$message({
                            message: res.message,
                            type: 'success',
                        });
                    });
                    editBorrowByBorrowId(params2).then(res => {
                        this.$message({
                            message: res.message,
                            type: 'success',
                        });
                    });
                    this.$router.push({
                        path: "/invoices/invoicesMgr"
                    });
                }else{
                    this.$message({
                            message: "审核过了",
                            type: 'warning',
                        });
                };  
            };
             if(this.ruleForm.role==7){
                if(this.ruleForm.financeCheck==undefined||this.ruleForm.financeCheck==""){//未审核过
                    console.log("财务审核");
                    if(this.isAgree==1){
                        this.rejectReasult="财务审批同意";
                        this.checkType=4;
                    };
                    if(this.isAgree==2){//驳回
                        this.checkType=6;
                    };
                    var params1={
                        invoiceCode:this.ruleForm.borrowCode,
                        checkReasult:this.rejectReasult,
                        checkUser:this.ruleForm.finance,
                        checkDate:this.returnTime(new Date()),
                        invoiceType:3,
                    };
                    var params2={
                        filePath2:this.filePath2,
                        financeCheck:this.ruleForm.finance,
                        borrowId:this.borrowId,
                        invoiceType:this.checkType,
                    };
                    addCheckInvoices(params1).then(res => {
                        this.$message({
                            message: res.message,
                            type: 'success',
                        });
                    });
                    editBorrowByBorrowId(params2).then(res => {
                        this.$message({
                            message: res.message,
                            type: 'success',
                        });
                    }) 
                    this.$router.push({
                        path: "/invoices/invoicesMgr"
                    });
                }else{
                    this.$message({
                            message: "审核过了",
                            type: 'warning',
                        });
                };  
            };
             if(this.ruleForm.role==12||this.ruleForm.role==10){
                if(this.ruleForm.dmCheck==undefined||this.ruleForm.dmCheck==""){//未审核过
                    console.log("部门主管审核");
                    if(this.isAgree==1){
                        this.rejectReasult="部门主管审批同意";
                        this.checkType=2;
                    };
                     if(this.isAgree==2){//驳回
                        this.checkType=6;
                    };
                    var params1={
                        invoiceCode:this.ruleForm.borrowCode,
                        checkReasult:this.rejectReasult,
                        checkUser:this.ruleForm.dm,
                        checkDate:this.returnTime(new Date()),
                        invoiceType:3,
                    };
                    var params2={
                        filePath2:this.filePath2,
                        dmCheck:this.ruleForm.dm,
                        borrowId:this.borrowId,
                        invoiceType:this.checkType,
                    };
                    addCheckInvoices(params1).then(res => {
                        this.$message({
                            message: res.message,
                            type: 'success',
                        });
                    });
                    editBorrowByBorrowId(params2).then(res => {
                        this.$message({
                            message: res.message,
                            type: 'success',
                        });
                    }) 
                    this.$router.push({
                        path: "/invoices/invoicesMgr"
                    });
                }else{
                    this.$message({
                            message: "审核过了",
                            type: 'warning',
                        });
                };  
            }   
        },
        //获取所有审批结果
        selectAllCheck(){
            var borrows = JSON.parse(window.localStorage.getItem('borrows')).list
            var borrowCode=borrows[0].borrowCode;
            console.log('borrowCode222==',borrowCode);
            getAllCheck({invoiceCode:borrowCode}).then(res => {
                console.log('selectAllCheck==',res);
                this.checks = res
            })
        },
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
                this.ruleForm.userGetid=res.list.userId;
               
            })
        }, 
        //根据收款人id查找收款银行卡信息 
        getCardInfoBycompany() {
            getCardInfoBycompany(this.ruleForm.userGetid).then(res => {
                console.log('getCardInfoBycompany==',res);
                this.ruleForm.bankcardGetid=res.accountCard;
                this.ruleForm.bankGetname=res.accountCardBank;
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
                this.ruleForm.role=res.user.roles[0].id;
            })
        },
        handleResize () {
            this.fullWidth = document.documentElement.clientWidth
        }, 
       
        //提交表单
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    let params={
                        invoiceType:1,
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

                        filePath1:this.filePath1,

                    };
                    console.log('submit!');
                    addBorrow(params).then(res => {
                        console.log('addExpense==',res.message);
                        this.$message({
                            message: res.message,
                            type: 'success',
                        });
                    })
                     this.$refs[formName].resetFields();
                     this.$router.push({
                        path: "/invoices/addInvoices"
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
        resetForm(formName) {
            this.$refs[formName].resetFields();
            console.log("重置");
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
    //获取图片
    getImage(){
        if(this.imgArr.length<=0){
            this.imageVisible=false;
            this.$message({
                message: "没有影像",
                type: 'warning',
            });
        }else{
            this.imageVisible=true;
            this.imgArr.map(item=>{//增加可预览功能
                this.srcList.push(this.baseImgPath+item.value);
            })
        }
        // if(this.borrowImage==""||this.borrowImage==undefined){
        //     this.imageVisible=false;
        //     this.$message({
        //         message: "没有影像",
        //         type: 'warning',
        //     });
        // }else{
        //     this.imageVisible=true;
        // }
    },
    getImage2(){
        if(this.borrowImage2==""||this.borrowImage2==undefined){
            this.imageVisible2=false;
            this.$message({
                message: "没有影像",
                type: 'warning',
            });
        }else{
            this.imageVisible2=true;
        }
    },
       
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
      margin-left:240px;
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
    
    .imgTitle{
        font-size:20px;
        margin-bottom: 30px;
    }
</style>