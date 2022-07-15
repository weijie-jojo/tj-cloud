<template>
    <div class="app-container">      
        <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="200px" >
            <el-form-item id="title" >
                <span style="font-size:30px">报销单</span>                  
            </el-form-item>
             <el-form-item label="报销单号" class="left" >
                <div >{{ruleForm.expenseCode}}</div>            
            </el-form-item>
           
            <el-form-item label="报销部门"   class="middle">
                <el-input 
                    disabled
                    v-model="ruleForm.deptName"
                    style="width:200px"
                ></el-input>
            </el-form-item>
        
            <el-form-item label="报销日期"   prop="expenseDate" class="right" >  
                <el-date-picker
                    disabled
                    v-model="ruleForm.expenseDate"
                    type="date"
                    placeholder="选择日期"
                    style="width:200px">
                </el-date-picker>
            </el-form-item>

            <el-form-item label="附件"   class="right2" >
                <el-input-number 
                    disabled
                    v-model="ruleForm.accessory" 
                    :precision="0" :step="1" :min="0"  
                    style="width:160px"></el-input-number>
                <span style="margin-left:20px">张</span>  
            </el-form-item>
            <el-form-item label="费用项目" style="margin-left:70px;margin-top:20px">
            </el-form-item>    
            <el-form-item  prop="item1desc" class="descCss">
                <el-input  v-model="ruleForm.item1desc"  style="width:200px" disabled></el-input>
            </el-form-item>
            <el-form-item   class="descCss">
                <el-input  v-model="ruleForm.item2desc"  style="width:200px" disabled></el-input>
            </el-form-item>
            <el-form-item  class="descCss">
                <el-input  v-model="ruleForm.item3desc"  style="width:200px" disabled></el-input>
            </el-form-item>
                <el-form-item  class="descCss">
                <el-input  v-model="ruleForm.item4desc"  style="width:200px" disabled></el-input>
            </el-form-item>
            <el-form-item class="descCss">
                <el-input  v-model="ruleForm.item5desc"  style="width:200px" disabled></el-input>
            </el-form-item>

            <el-form-item label="金额" style="margin-left:500px;margin-top:-410px">
            </el-form-item>  
            <el-form-item  class="moneyCss">
                <el-input-number disabled v-model="ruleForm.item1money" :precision="2" :step="0.1" :min="0"  style="width:160px"></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
             <el-form-item  class="moneyCss">
                <el-input-number disabled v-model="ruleForm.item2money" :precision="2" :step="0.1" :min="0"  style="width:160px"></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
            <el-form-item class="moneyCss" >
                <el-input-number disabled v-model="ruleForm.item3money" :precision="2" :step="0.1"  :min="0"  style="width:160px"></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
             <el-form-item  class="moneyCss">
                <el-input-number disabled v-model="ruleForm.item4money" :precision="2" :step="0.1" :min="0"  style="width:160px"></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
            <el-form-item  class="moneyCss" >
                <el-input-number disabled v-model="ruleForm.item5money" :precision="2" :step="0.1" :min="0"  style="width:160px"></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>

           <el-form-item label="备注" style="margin-left:1000px;margin-top:-390px">
            </el-form-item>  
            <el-form-item  class="remarkCss" >
                <el-popover
                    placement="top-start"
                    width="780px"
                    trigger="hover"
                    :content="ruleForm.item1remark">
                    <el-button slot="reference" style="width:240px">{{ruleForm.item1remark}}</el-button>
                </el-popover>
            </el-form-item> 
            <el-form-item  class="remarkCss">
                 <el-popover
                    placement="top-start"
                    width="780px"
                    trigger="hover"
                    :content="ruleForm.item2remark">
                    <el-button slot="reference" style="width:240px">{{ruleForm.item2remark}}</el-button>
                </el-popover>
            </el-form-item>    
            <el-form-item  class="remarkCss">
                 <el-popover
                    placement="top-start"
                    width="780px"
                    trigger="hover"
                    :content="ruleForm.item3remark">
                    <el-button slot="reference" style="width:240px">{{ruleForm.item3remark}}</el-button>
                </el-popover>
            </el-form-item>
            <el-form-item  class="remarkCss">
                <el-popover
                    placement="top-start"
                    width="780px"
                    trigger="hover"
                    :content="ruleForm.item4remark">
                    <el-button slot="reference" style="width:240px">{{ruleForm.item4remark}}</el-button>
                </el-popover>
            </el-form-item>
            <el-form-item  class="remarkCss" style="margin-bottom:20px">
                 <el-popover
                    placement="top-start"
                    width="780px"
                    trigger="hover"
                    :content="ruleForm.item5remark">
                    <el-button slot="reference" style="width:240px">{{ruleForm.item5remark}}</el-button>
                </el-popover>
            </el-form-item>
           
            <el-form-item  label="合计金额(小写)" class="left" prop="money">        
                {{ruleForm.item1money+ruleForm.item2money+ruleForm.item3money+ruleForm.item4money+ruleForm.item5money}}    
            </el-form-item>
            <el-form-item  label="合计金额(大写)" class="right2">                 
                {{digitUppercase(ruleForm.item1money+ruleForm.item2money+ruleForm.item3money+ruleForm.item4money+ruleForm.item5money)}}      
            </el-form-item>
            
            <el-form-item label="付款方式"  prop="paywayId" >
                <el-radio-group 
                   disabled
                    v-model="ruleForm.paywayId">
                    <el-radio 
                        :label="1" 
                    >转账</el-radio>
                    <el-radio :label="2" 
                        style="margin-left:330px"
                    >现金</el-radio>
                    <el-radio 
                        :label="3" 
                        style="margin-left:335px"
                    >其他</el-radio>
                    <el-input 
                        disabled
                        v-model="ruleForm.paywayRemark"
                        style="width:210px"
                    ></el-input>
                </el-radio-group>
            </el-form-item>

           <el-form-item label="收款人"   id="left">
                 <el-input 
                    v-model="ruleForm.userGetid" 
                    style="width:200px"
                    disabled
                ></el-input>
            </el-form-item>
            <el-form-item label="收款账号"   style="margin-top: -50px;margin-left:440px">
                <el-input 
                    v-model="ruleForm.bankcardGetid" 
                    style="width:200px"
                    disabled
                ></el-input>
            </el-form-item>
            <el-form-item label="收款开户行"  style="margin-top: -50px;margin-left:920px">
                <el-input 
                    v-model="ruleForm.bankGetname" 
                    style="width:200px"
                    disabled
                ></el-input>
            </el-form-item>

             <el-form-item label="付款单位"  prop="companyPayId"  class="left">
                 <el-select 
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
                </el-select>
            </el-form-item>
            <el-form-item label="借款账号"  style="margin-top: -50px;margin-left:440px">
              <el-input 
                    v-model="ruleForm.bankPaycode" 
                    style="width:200px"
                    disabled
                ></el-input>
            </el-form-item>
            <el-form-item label="借款开户行"  style="margin-top: -50px;margin-left:920px">
                <el-input 
                    v-model="ruleForm.bankPayname" 
                    style="width:200px"
                    disabled
                ></el-input>
            </el-form-item>

             <el-form-item 
                label="报销凭证影像："  
                class="left">
                <el-button  
                    type="primary"
                   @click="getImage"
                >点击查看</el-button>
            </el-form-item>
            <el-form-item 
                label="付款凭证影像："  
                class="middle">
                <el-button  
                    type="primary"
                   @click="imgDialog=true"
                   v-hasPermi="['invoices:expense:upload']"
                >点击上传</el-button>
            </el-form-item>
            <!-- 上传付款凭证影像 -->
            <el-dialog title="上传图片" :visible.sync="imgDialog" width="30%">
                <el-upload
                    action="http://36.133.2.179:8000/api/files/doUpload"
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
                class="btn2">返回</el-button>
            </el-dialog>
            <!-- 图片查看 -->
            <el-dialog title="图片" :visible.sync="imageVisible" width="20%">
                <div class="demo-image" 
                    v-for="(item,index) in imgArr" :key="index" 
                    style="margin-top:20px;">
                    <el-image
                        style="width: 300px; height: 200px"
                        :src="baseImgPath+item.value"
                        :preview-src-list="srcList"
                    ></el-image>
                </div>
            </el-dialog>

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
                <el-input  v-model="ruleForm.expenseName" placeholder="" class="inputCss" disabled></el-input> 
            </el-form-item>

              <el-form-item style="margin-top:100px">
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
                <el-button @click="toReturn" style="width:80px">返回</el-button>
                <el-button type="primary" @click="checkInvoices('ruleForm')" style="width:80px;margin-left:80px">审核</el-button>
            </el-form-item>

        </el-form>
    </div>
</template>
<script>
    import {getAllCheck,addCheckInvoices} from '@/api/invoices/checkInvoices'
    import {getAllCompany} from '@/api/invoices/borrow'
    import { getDepts,getAllGetCompany,getCode,editExpenseByExpenseId } from '@/api/invoices/expense'
    import { getExpenseItem } from '@/api/invoices/travelExpense'
    export default {
    name: 'expense',
    data() {
      return {
        
        srcList:[],
        imgArr:[],
        baseImgPath:"http://36.133.2.179:8000/api/files/showImg?imgPath=",
        //查看
        expenseImage:'',
        imageVisible:false,
        imgpath:'',
        //上传
        imgArr2:[],
        imgDialog:false,
        dialogImageUrl: '',
        dialogVisible: false,
        limitNum:10,//可上传数量

        checks:[],
        rejectReasult:'',
        
        isDisabled:true,
        payCompanys:[],//所有单位
        items:[],
        filePath:'',
        height: document.documentElement.clientHeight - 180 + 'px;',
        form: {},
        formLabelWidth: "80px",
        ruleForm: {
            isAgree:'1',//是否同意 1 同意 2驳回

            expenseCode:'',//报销单号
            dept:'',//部门
            deptId:'',//部门id
            expenseDate:'',//报销日期create_time
            accessory:'',//附件数量accessory_num

            userGetid:'',//收款单位/人id
            bankcardGetid:'',//收款单位/人银行卡号
            bankGetname:'',//收款单位/人银行卡所属银行

            companyPayId:'',//付款单位id
            bankPaycode:'',//付款单位银行卡号
            bankPayname:'',//付款单位银行卡所属银行

            paywayId:'',
            accessory:'',
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

            expenseName:'',//报销人createUser
            paywayId:'',//付款方式
            paywayRemark:'',//付款方式其他的时候选填

            deptId:'',//部门id
            gm:'',//总经理
            dm:'',//部门主管
            finance:'',//财务

            gmCheck:'',
            financeCheck:'',
            dmCheck:'',
            roles:[],
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
            expenseDate:[
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
        searchDepts:[],
        searchGetCompanys:[],//所有收款单位信息
        searchCardInfos:[],//所选公司对应的银行卡信息
        checkType:'',
     
        expenseId:'',
        filePath2:'',
      }
    },
    mounted: function() {
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
        console.log("expenses==",this.expenses[0]);
        this.ruleForm.expenseCode=this.expenses[0].expenseCode;
        this.ruleForm.expenseDate=this.expenses[0].createTime;
        this.ruleForm.deptId=this.expenses[0].deptId;
        this.ruleForm.deptName=this.expenses[0].deptName;

        this.ruleForm.paywayId=this.expenses[0].paywayId;
        this.ruleForm.roles=JSON.parse(window.localStorage.getItem('expenseRoles'));
        console.log("roles==",this.ruleForm.roles);

         this.ruleForm.expenseName=this.expenses[0].nickName;
        this.ruleForm.accessory=this.expenses[0].accessoryNum;
        this.ruleForm.paywayRemark=this.expenses[0].paywayRemark;
        this.ruleForm.item1desc=this.expenses[0].item1Desc;
        this.ruleForm.item1money=this.expenses[0].item1Money;
        this.ruleForm.item1remark=this.expenses[0].item1Remark;
        this.ruleForm.item2desc=this.expenses[0].item2Desc;
        this.ruleForm.item2money=this.expenses[0].item2Money;
        this.ruleForm.item2remark=this.expenses[0].item2Remark;
        this.ruleForm.item3desc=this.expenses[0].item3Desc;
        this.ruleForm.item3money=this.expenses[0].item3Money;
        this.ruleForm.item3remark=this.expenses[0].item3Remark;
        this.ruleForm.item4desc=this.expenses[0].item4Desc;
        this.ruleForm.item4money=this.expenses[0].item4Money;
        this.ruleForm.item4remark=this.expenses[0].item4Remark;
        this.ruleForm.item5desc=this.expenses[0].item5Desc;
        this.ruleForm.item5money=this.expenses[0].item5Money;
        this.ruleForm.item5remark=this.expenses[0].item5Remark;

        this.ruleForm.companyPayId=this.expenses[0].companyPayId;
        this.ruleForm.bankPaycode=this.expenses[0].bankPaycode;
        this.ruleForm.bankPayname=this.expenses[0].bankPayname;
        this.ruleForm.userGetid=this.expenses[0].userGetid;
        this.ruleForm.bankcardGetid=this.expenses[0].bankcardGetid;
        this.ruleForm.bankGetname=this.expenses[0].bankGetname;

        this.ruleForm.gm=JSON.parse(window.localStorage.getItem('expenseCheckPerson'));
        this.ruleForm.finance=JSON.parse(window.localStorage.getItem('expenseCheckPerson'));
        this.ruleForm.dm=JSON.parse(window.localStorage.getItem('expenseCheckPerson'));

        this.expenseId=this.expenses[0].expenseId;

        this.ruleForm.gmCheck=this.expenses[0].gmCheck;
        this.ruleForm.financeCheck=this.expenses[0].financeCheck;
        this.ruleForm.dmCheck=this.expenses[0].dmCheck;

        this.expenseImage=this.expenses[0].expenseImage;
        var imgArr= this.expenseImage.split(",");  
        imgArr.map((item,index)=>{
            if(item!=null&&item!=""){
                 this.imgArr.push({id:index,value:item});
            }
        })
    },
    methods: {
       isAgrees(){
            console.log("点了是否同意");
            if(this.ruleForm.isAgree==2){
                this.isDisabled=false;
            }
            if(this.ruleForm.isAgree==1){
                this.isDisabled=true;
            }
        },
        //获取所有审批结果
        selectAllCheck(){
            var expenses = JSON.parse(window.localStorage.getItem('expenses')).list
            var expenseCode=expenses[0].expenseCode;
            console.log('expenseCode==',expenseCode);
            getAllCheck({invoiceCode:expenseCode}).then(res => {
                console.log('selectAllCheck==',res);
                this.checks = res
            })
        },
        //审核
        checkInvoices(){
            
           this.ruleForm.roles.map(item=>{
            console.log("item",item);
               if(item.roleId==5||item.roleId==6){
                if(this.ruleForm.gmCheck==undefined||this.ruleForm.gmCheck==""){//未审核过
                  console.log("总经理审核");
                    if(this.ruleForm.isAgree==1){
                        this.rejectReasult="总经理审批同意";
                        this.checkType=3;
                    };
                    if(this.ruleForm.isAgree==2){//驳回
                        this.checkType=6;
                        this.rejectReasult="总经理驳回："+this.rejectReasult;
                    };
                    var params1={
                        invoiceCode:this.ruleForm.expenseCode,
                        checkReasult:this.rejectReasult,
                        checkUser:this.ruleForm.gm,
                        checkDate:this.returnTime(new Date()),
                        invoiceType:1,
                        
                    };
                    var params2={
                        gmCheck:this.ruleForm.gm,
                        expenseId:this.expenseId,
                        invoiceType:this.checkType,
                        expenseImage2:this.imgArr2.join(),
                    };
                    addCheckInvoices(params1).then(res => {
                        this.$message({
                            message: "审核成功",
                            type: 'success',
                        });
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
                }else{
                    this.$message({
                            message: "审核过了",
                            type: 'warning',
                        });
                };  
            };
             if(item.roleId==7){
                if(this.ruleForm.financeCheck==undefined||this.ruleForm.financeCheck==""){//未审核过
                    console.log("财务审核");
                    if(this.ruleForm.isAgree==1){
                        this.rejectReasult="财务审批同意";
                        this.checkType=5;
                    };
                    if(this.ruleForm.isAgree==2){//驳回
                        this.checkType=6;
                        this.rejectReasult="财务驳回："+this.rejectReasult;
                    };
                    var params1={
                        invoiceCode:this.ruleForm.expenseCode,
                        checkReasult:this.rejectReasult,
                        checkUser:this.ruleForm.finance,
                        checkDate:this.returnTime(new Date()),
                        invoiceType:1,
                    };
                    var params2={
                        financeCheck:this.ruleForm.finance,
                        expenseId:this.expenseId,
                        invoiceType:this.checkType,
                        expenseImage2:this.imgArr2.join(),
                    };
                    addCheckInvoices(params1).then(res => {
                        this.$message({
                            message: "审核成功",
                            type: 'success',
                        });
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
                }else{
                    this.$message({
                            message: "审核过了",
                            type: 'warning',
                        });
                };  
            };
             if(item.roleId==10||item.roleId==12||item.roleId==4||item.roleId==8){
                if(this.ruleForm.dmCheck==undefined||this.ruleForm.dmCheck==""){//未审核过
                    console.log("部门主管审核");
                    if(this.ruleForm.isAgree==1){
                        this.rejectReasult="部门主管审批同意";
                        this.checkType=2;
                    };
                     if(this.ruleForm.isAgree==2){//驳回
                        this.checkType=6;
                        this.rejectReasult="部门主管驳回："+this.rejectReasult;
                    };
                    var params1={
                        invoiceCode:this.ruleForm.expenseCode,
                        checkReasult:this.rejectReasult,
                        checkUser:this.ruleForm.dm,
                        checkDate:this.returnTime(new Date()),
                        invoiceType:1,
                    };
                    var params2={
                        dmCheck:this.ruleForm.dm,
                        expenseId:this.expenseId,
                        invoiceType:this.checkType,
                         expenseImage2:this.imgArr2.join(),
                    };
                    addCheckInvoices(params1).then(res => {
                        this.$message({
                            message: "审核成功",
                            type: 'success',
                        });
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
                }else{
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
                console.log('payCompanys==',this.payCompanys);
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
        //初始化下拉公司信息 
        getAllGetCompany() {
            getAllGetCompany().then(res => {
                console.log('getAllGetCompany==',res.list);
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
        toReturn(){
            this.$router.push({
                 path: "/invoices/checkInvoices"
            })
        }, 
        //上传图片
        handleRemove(file) {
            this.imgArr2.map((item,index)=>{
            if(item==file.name){
                    this.imgArr2.splice(index, 1);
                }
            })
            this.$message("取消上传");
        },
        //点+可以放大查看图片
        handlePictureCardPreview(file) {
            this.dialogImageUrl = file.url;
            this.dialogVisible = true;
        },
        success(file) {
            this.$message(file.message);
            this.imgArr2.push(file.obj);
        },
        //取消按钮
        cancel() {
            this.imgDialog = false;
        },
        //获取图片
        getImage(){
            if (this.imgArr.length<=0) {
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
        }, 
    },
  }
</script>


<style rel="stylesheet/scss" lang="scss" scoped>
    #title{
        margin-left: 350px; 
        margin-top: 10px;
        margin-bottom: 20px;
    }

    .descCss{
       width: 490px;
       margin-left: -70px;
    }
    .moneyCss {
        width: 380px;
        margin-left: 400px;
        // margin-top: -50px;
    }
    .remarkCss {
        margin-left: 860px;
        // margin-top: -55px;
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
    #btn{
      margin-left:260px;
      margin-top:30px;
    }
    ::v-deep .el-input-number .el-input__inner {
        text-align: left;
    }
    ::v-deep .vue-treeselect__control,::v-deep .vue-treeselect__placeholder,::v-deep .vue-treeselect__single-value {
        height: 30px;
        line-height: 30px;
    }
      .inputCss{
        width: 200px;
    }
    // 改变input框字体颜色
     ::v-deep .is-disabled .el-input__inner{
        background-color: transparent !important;
        color: black;    
    }
</style>