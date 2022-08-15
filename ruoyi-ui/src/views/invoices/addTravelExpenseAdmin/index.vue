<template>
    <div class="app-container">
        <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="auto" >
            <el-form-item id="title" >
                <span style="font-size:30px">差旅费报销单新增（管理员）</span>
            </el-form-item>

            <el-row >
                <el-col :span="4">
                    <el-form-item label="差旅报销单单号" >
                        <span >{{ruleForm.travelExpenseCode}}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="5">
                    <el-form-item label="报销日期"  prop="expenseDate" >
                        <el-date-picker
                            disabled
                            v-model="ruleForm.expenseDate"
                            type="date"
                            placeholder="选择日期"
                            >
                        </el-date-picker>
                    </el-form-item>
                </el-col>
                <el-col :span="4">
                    <el-form-item label="姓名"  prop="expenseId" >
                        <!-- <el-input
                            v-model="ruleForm.expenseName"
                            placeholder=""
                        ></el-input> -->
                        <el-select 
                            style="width:100%" 
                            v-model="ruleForm.expenseId" 
                            clearable 
                            filterable
                            placeholder="请选择姓名"
                             @change="getUserById">
                            <el-option 
                                v-for="item in users" 
                                :key="item.userId" 
                                :label="item.nickName"
                                :value="item.userId">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="4">
                    <div class="grid-content bg-purple">
                        <el-form-item label="职别"  prop="job" >
                            <el-input
                                v-model="ruleForm.job"
                                placeholder="请输入职别"
                                disabled ></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="4">
                    <div class="grid-content bg-purple">
                        <el-form-item label="同行人数" prop="togetherNum"  >
                            <el-input-number
                                v-model="ruleForm.togetherNum"
                                :precision="0"
                                :step="1"
                                :min="0"
                                :max="1000"
                                style="width:100px"
                            ></el-input-number>
                            <span style="margin-left:10px">人</span>
                        </el-form-item>
                    </div>
                </el-col>
            </el-row>
            <el-row style="margin-top:20px">
                <el-col :span="21">
                    <el-form-item label="出差事由"  prop="reason">
                            <el-input
                            type="textarea"
                            :rows="4"
                            placeholder="请输入出差事由"
                            v-model="ruleForm.reason"
                            >
                        </el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-form-item label="起讫时间" style="margin-left:40px;margin-top:20px">
            </el-form-item>
            <el-form-item   prop="travelDate1" >
                <el-date-picker
                    v-model="ruleForm.travelDate1"
                    type="daterange"
                    align="right"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
                    class="travelDate">
                </el-date-picker>
            </el-form-item>
            <el-form-item  >
                <el-date-picker
                    v-model="ruleForm.travelDate2"
                    type="daterange"
                    align="right"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
                    class="travelDate">
                </el-date-picker>
            </el-form-item>
            <el-form-item >
                <el-date-picker
                    v-model="ruleForm.travelDate3"
                    type="daterange"
                    align="right"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
                    class="travelDate">
                </el-date-picker>
            </el-form-item>

            <el-form-item label="起讫地点" style="margin-left:240px;margin-top:-350px">
            </el-form-item>
            <el-form-item  prop="place1" class="placeCss">
                <el-input
                    class="placeCss2"
                    v-model="ruleForm.place1"
                    placeholder="请输入起讫地点"
                ></el-input>
            </el-form-item>
            <el-form-item   class="placeCss">
                <el-input
                    class="placeCss2"
                    v-model="ruleForm.place2"
                    placeholder="请输入起讫地点"
                ></el-input>
            </el-form-item>
            <el-form-item   class="placeCss">
                <el-input
                    class="placeCss2"
                    v-model="ruleForm.place3"
                    placeholder="请输入起讫地点"
                ></el-input>
            </el-form-item>

            <el-form-item label="出行方式" style="margin-left:400px;margin-top:-350px">
            </el-form-item>
            <el-form-item  prop="travelWay1" class="travelWayCss">
                <el-input
                    class="travelWayCss2"
                    v-model="ruleForm.travelWay1"
                    placeholder="请输入出行方式"
                ></el-input>
            </el-form-item>
            <el-form-item   class="travelWayCss">
                <el-input
                    class="travelWayCss2"
                    v-model="ruleForm.travelWay2"
                    placeholder="请输入出行方式"
                ></el-input>
            </el-form-item>
            <el-form-item   class="travelWayCss">
                <el-input
                    class="travelWayCss2"
                    v-model="ruleForm.travelWay3"
                    placeholder="请输入出行方式"
                ></el-input>
            </el-form-item>

            <el-form-item label="天数" style="margin-left:530px;margin-top:-350px">
            </el-form-item>
            <el-form-item  prop="days1" class="daysCss">
                <el-input-number
                    v-model="ruleForm.days1"
                    :precision="0"
                    :step="1" :min="1"
                    :max="1000"
                    style="width:100px"
                ></el-input-number>
                <span style="margin-left:10px">天</span>
            </el-form-item>
                <el-form-item  class="daysCss">
                <el-input-number
                    v-model="ruleForm.days2"
                    :precision="0"
                    :step="1" :min="1"
                    :max="1000"
                    style="width:100px"
                ></el-input-number>
                <span style="margin-left:10px">天</span>
            </el-form-item>
                <el-form-item   class="daysCss">
                <el-input-number
                    v-model="ruleForm.days3"
                    :precision="0"
                    :step="1" :min="1"
                    :max="1000"
                    style="width:100px"
                ></el-input-number>
                <span style="margin-left:10px">天</span>
            </el-form-item>
            <el-form-item label="交通费" style="margin-left:710px;margin-top:-350px">
            </el-form-item>
            <el-form-item prop="traffic1" class="trafficCss">
                <el-input-number
                    v-model="ruleForm.traffic1"
                    :precision="2"
                    :step="0.01" :min="0"
                    class="numberInput"
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
                <el-form-item  class="trafficCss">
                <el-input-number
                    v-model="ruleForm.traffic2"
                    :precision="2"
                    :step="0.01" :min="0"
                        class="numberInput"
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
                <el-form-item  class="trafficCss" >
                <el-input-number
                    v-model="ruleForm.traffic3"
                    :precision="2"
                    :step="0.01" :min="0"
                    class="numberInput"
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
            <el-form-item label="住宿费" style="margin-left:900px;margin-top:-350px">
            </el-form-item>
            <el-form-item prop="stay1"  class="stayCss">
                <el-input-number
                    v-model="ruleForm.stay1"
                    :precision="2"
                    :step="0.01" :min="0"
                    class="numberInput"
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
                <el-form-item  class="stayCss">
                <el-input-number
                    v-model="ruleForm.stay2"
                    :precision="2"
                    :step="0.01" :min="0"
                    class="numberInput"
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
                <el-form-item  class="stayCss" >
                <el-input-number
                    v-model="ruleForm.stay3"
                    :precision="2"
                    :step="0.01" :min="0"
                    class="numberInput"
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
            <el-form-item label="差旅补助" style="margin-left:1100px;margin-top:-350px">
            </el-form-item>
            <el-form-item prop="subsidy1" class="subsidyCss">
                <el-input-number
                    v-model="ruleForm.subsidy1"
                    :precision="2"
                    :step="0.01" :min="0"
                    class="numberInput"
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
                <el-form-item  class="subsidyCss">
                <el-input-number
                    v-model="ruleForm.subsidy2"
                    :precision="2"
                    :step="0.01" :min="0"
                    class="numberInput"
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
                <el-form-item  class="subsidyCss" >
                <el-input-number
                    v-model="ruleForm.subsidy3"
                    :precision="2"
                    :step="0.01" :min="0"
                        class="numberInput"
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
            <el-form-item label="其他" style="margin-left:1270px;margin-top:-350px">
            </el-form-item>
            <el-form-item  class="otherCss">
                <el-input-number
                    v-model="ruleForm.other1"
                    :precision="2"
                    :step="0.01" :min="0"
                        class="numberInput"
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
                <el-form-item  class="otherCss">
                <el-input-number
                    v-model="ruleForm.other2"
                    :precision="2"
                    :step="0.01" :min="0"
                    class="numberInput"
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
                <el-form-item  class="otherCss" >
                <el-input-number
                    v-model="ruleForm.other3"
                    :precision="2"
                    :step="0.01" :min="0"
                        class="numberInput"
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
            <el-form-item label="小计" style="margin-left:1460px;margin-top:-350px">
            </el-form-item>
            <el-form-item  class="subtotal">
                <el-input
                    v-model="subTotalMoney1"
                    style="width:100px"></el-input>
                <span style="margin-left:10px">元</span>
            </el-form-item>
            <el-form-item  class="subtotal">
                <el-input
                    disabled
                    v-model="subTotalMoney2"
                    style="width:100px"></el-input>
                    <span style="margin-left:10px">元</span>
            </el-form-item>
            <el-form-item  class="subtotal">
                 <el-input
                    disabled
                    v-model="subTotalMoney3"
                    style="width:100px"></el-input>
                <!-- {{ruleForm.traffic3+ruleForm.stay3+ruleForm.subsidy3+ruleForm.other3}} -->
                    <span style="margin-left:10px">元</span>
            </el-form-item>

            <el-row style="margin-top:20px">
                <el-col :span="10">
                    <el-form-item  label="总计金额(小写)" >
                        <el-input
                            disabled
                            v-model="totalMoney"
                            style="width:140px"></el-input>
                            <span style="margin-left:10px">元</span>
                    </el-form-item>
                </el-col>
                <el-col :span="10">
                    <el-form-item  label="总计金额(大写)" >
                        {{digitUppercase(ruleForm.traffic1+ruleForm.stay1+ruleForm.subsidy1+ruleForm.other1+
                        ruleForm.traffic2+ruleForm.stay2+ruleForm.subsidy2+ruleForm.other2+
                        ruleForm.traffic3+ruleForm.stay3+ruleForm.subsidy3+ruleForm.other3)+"元"}}
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row style="margin-top:20px">
               <el-col :span="8">
                    <el-form-item label="收款人：">
                         <!-- <el-input
                            v-model="ruleForm.expenseName"
                            style="width:220px"
                            disabled
                        ></el-input> -->
                        <el-select
                            v-model="ruleForm.userGetid"
                            @change="getCardInfoBycompany"
                            placeholder="请选择收款单位"
                            style=" width: 200PX;">
                            <el-option
                                v-for="item in searchGetUsers"
                                :key="item.userId"
                                :label="item.nickName"
                                :value="item.userId"
                            > </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="收款帐号：" >
                        <el-input
                            v-model="ruleForm.bankcardGetid"
                            style="width:220px"
                            disabled
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="收款开户行：" >
                        <el-input
                            v-model="ruleForm.bankGetname"
                            style="width:240px"
                            disabled
                        ></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
               <el-col :span="8">
                   <el-form-item label="报销单位："  prop="companyPayId" >
                        <el-select
                            v-model="ruleForm.companyPayId"
                            placeholder="请选择付款单位"
                            @change="getCarInfoByCompanyId"
                            style=" width: 220PX;">
                            <el-option
                                v-for="item in payCompanys"
                                :key="item.groupCode"
                                :label="item.groupName"
                                :value="item.groupCode"
                            > </el-option>
                        </el-select>
                    </el-form-item>
               </el-col>
               <el-col :span="8">
                   <el-form-item label="借款帐号：" >
                    <el-input
                            v-model="ruleForm.bankPaycode"
                            style="width:220px"
                            disabled
                        ></el-input>
                    </el-form-item>
               </el-col>
               <el-col :span="8">
                   <el-form-item label="借款开户行：" >
                    <el-input
                            v-model="ruleForm.bankPayname"
                            style="width:240px"
                            disabled
                        ></el-input>
                    </el-form-item>
               </el-col>
            </el-row>
            <!-- 上传报销凭证影像
            <el-dialog title="上传图片" :visible.sync="imgDialog" width="70%">
                <el-upload
                    action="/eladmin/api/files/doUpload"
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
                    class="btn2"
                >返回</el-button>
            </el-dialog> -->
            <el-row>
                <el-col :span="8">
                    <el-form-item  label="报销凭证影像："  >
                        <uploadSmall  @getfileName="getThree" :fileName="isNone" :fileNameOld="isNone" :isDetail="isDetail"></uploadSmall>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="报销人："  >
                        <el-input
                            v-model="ruleForm.expenseName"
                            class="inputCss"
                            placeholder=""
                            style="width:220px"
                        ></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-form-item class="btn" >
                <el-button @click="toReturn" style="width:100px">取消</el-button>
                <el-button type="primary" @click="submitForm('ruleForm')" style="width:100px;margin-left: 120px;">提交</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    import uploadSmall from '@/components/douploads/uploadSmall'
    import {addCheckInvoices} from '@/api/invoices/checkInvoices'
    import { getCardInfoBycompany ,getPost} from '@/api/invoices/expense'
    import {getAllCompany,getAllGetUser} from '@/api/invoices/borrow'
    import {getInfo} from '@/api/login'
    import {getAllUser,getUser} from '@/api/system/user'
    import { getCode,addTravelExpense } from '@/api/invoices/travelExpense'
    export default {
    components: {
     uploadSmall
    },
    name: 'travelExpense',
    data() {
      return {
        users:[],
        isDetail:'0',
        isNone:[],
        roles:[],
        //影像上传参数
        imgArr:[],
        imgDialog:false,
        dialogImageUrl: '',
        dialogVisible: false,

        hideUpload: false,
        limitNum:10,//可上传数量

        payCompanys:[],//所有单位
        searchGetUsers:[],//所有收款用户信息
        ruleForm: {
            deptId:'',//部门id
            travelExpenseCode:'',//报销单号
            expenseDate:'',//报销时间
            expenseName:'',//报销人createUser
            expenseId:'',//报销人id
            job:'',//职别
            togetherNum:'',//同行人数
            reason:'',//出差事由

            travelDate1:'',//起忆时间
            place1:'',//起忆地点
            travelWay1:'',//出行方式
            days1:'',//天数
            traffic1:'',//交通费
            stay1:'',//住宿费
            subsidy1:'',//差旅补助
            other1:'',//其他费用

            travelDate2:'',//起忆时间
            place2:'',//起忆地点
            travelWay2:'',//出行方式
            days2:'',//天数
            traffic2:'',//交通费
            stay2:'',//住宿费
            subsidy2:'',//差旅补助
            other2:'',//其他费用

            travelDate3:'',//起忆时间
            place3:'',//起忆地点
            travelWay3:'',//出行方式
            days3:'',//天数
            traffic3:'',//交通费
            stay3:'',//住宿费
            subsidy3:'',//差旅补助
            other3:'',//其他费用

            userGetid:'',//收款单位/人id
            bankcardGetid:'',//收款单位/人银行卡号
            bankGetname:'',//收款单位/人银行卡所属银行

            companyPayId:'',//付款单位id
            bankPaycode:'',//付款单位银行卡号
            bankPayname:'',//付款单位银行卡所属银行

            dmCheck:'',//部门主管
            gmCheck:'',//总经理
            financeCheck:'',//财务
        },
        rules: {
            expenseId: [
                { required: true, message: '请选择报销人', trigger: 'change' }
            ],
            userGetid: [
                { required: true, message: '请选择收款人', trigger: 'change' }
            ],
            companyPayId: [
                { required: true, message: '请选择出款单位名', trigger: 'change' }
            ],
            expenseDate:[
                { type: 'date', required: true, message: '请选择报销时间', trigger: 'change' }
            ],
            expenseName: [
                { required: true, message: '请输入姓名', trigger: 'blur' }
            ],
            job: [
                { required: true, message: '请输入职位', trigger: 'blur' }
            ],
            togetherNum: [
                { required: true, message: '请输入同行人数', trigger: 'blur' }
            ],
            reason: [
                { required: true, message: '请输入出差事由', trigger: 'blur' }
            ],

            travelDate:[
                { required: true, message: '请选择起讫时间', trigger: 'change' }
            ],
            place: [
                { required: true, message: '请输入起讫地点', trigger: 'blur' }
            ],
            days: [
                { required: true, message: '请输入住宿天数', trigger: 'blur' }
            ],
        },
      }
    },
    mounted: function() {
        
        //获取登录用户信息
        getInfo().then(res => {
            console.log("getInfo==", res);
            this.ruleForm.deptId=res.user.deptId;
            this.roles=res.user.roles;
            this.ruleForm.userGetid=res.user.id;
            //根据收款人id查询岗位
            getPost(res.user.userId).then(res=>{
                console.log("getPost==", res.postName);
                this.ruleForm.job=res.postName;
            })
        })

        this.ruleForm.expenseDate=new Date();
        this.getTravelExpenseCode();
        this.getAllCompany();
        this.getAllGetUser();
        this.getAllUser();
    },
    computed: {
        subTotalMoney1:function(){
            return this.ruleForm.traffic1+this.ruleForm.stay1+this.ruleForm.subsidy1+this.ruleForm.other1;
        },
        subTotalMoney2:function(){
             return this.ruleForm.traffic2+this.ruleForm.stay2+this.ruleForm.subsidy2+this.ruleForm.other2;
        },
        subTotalMoney3:function(){
             return this.ruleForm.traffic3+this.ruleForm.stay3+this.ruleForm.subsidy3+this.ruleForm.other3;
        },
        totalMoney:function(){
            return this.subTotalMoney1+this.subTotalMoney2+this.subTotalMoney3;
        },
    },
    methods: {
        getUserById(userId){
            getUser(userId).then(res=>{
                console.log('users==',res.data);
                this.ruleForm.expenseName=res.data.nickName;
            })    
        },
        getAllUser(){
            getAllUser().then(res=>{
                console.log('users==',res);
                this.users=res;
            })    
        },
         //根据收款人id查找收款银行卡信息
        getCardInfoBycompany(userId){
             getCardInfoBycompany(userId).then(res => {
                console.log('getCardInfoBycompany==',res);
                this.ruleForm.bankcardGetid=res.payCheck;
                this.ruleForm.bankGetname=res.accountCardBank;
                this.ruleForm.userGetid=res.nickName;
            })
        },
        getThree(data){
           this.imgArr=data;
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
            })
        },
        //根据收款人id查找收款银行卡信息
        // getCardInfoBycompany() {
        //     getCardInfoBycompany(this.ruleForm.userGetid).then(res => {
        //         console.log('getCardInfoBycompany==',res);
        //         this.ruleForm.bankcardGetid=res.accountCard;
        //         this.ruleForm.bankGetname=res.accountCardBank;
        //         this.ruleForm.userGetid=res.nickName;
        //     })
        // },

        getTravelExpenseCode(){
            getCode().then(res => {
                console.log("getExpenseCode",res.message);
                this.ruleForm.travelExpenseCode=res.message;
            })
        },

        //提交表单
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {

                    let params={
                        gmCheck:this.ruleForm.expenseName,
                        dmCheck:this.ruleForm.expenseName,

                        invoiceType:3,//打款状态
                        deptId:this.ruleForm.deptId,
                        travelExpenseCode:this.ruleForm.travelExpenseCode,
                        createTime:this.returnTime(this.ruleForm.expenseDate),
                        createUser:this.ruleForm.expenseId,
                        job:this.ruleForm.job,
                        togetherNum:this.ruleForm.togetherNum,
                        reason:this.ruleForm.reason,

                        travelDate1:this.returnTime(this.ruleForm.travelDate1[0])+"--"+this.returnTime(this.ruleForm.travelDate1[1]),
                        place1:this.ruleForm.place1,
                        travelWay1:this.ruleForm.travelWay1,
                        days1:this.ruleForm.days1,
                        traffic1:this.ruleForm.traffic1,
                        stay1:this.ruleForm.stay1,
                        subsidy1:this.ruleForm.subsidy1,
                        other1:this.ruleForm.other1,

                        travelDate2:this.returnTime(this.ruleForm.travelDate2[0])+"--"+this.returnTime(this.ruleForm.travelDate2[1]),
                        place2:this.ruleForm.place2,
                        travelWay2:this.ruleForm.travelWay2,
                        days2:this.ruleForm.days2,
                        traffic2:this.ruleForm.traffic2,
                        stay2:this.ruleForm.stay2,
                        subsidy2:this.ruleForm.subsidy2,
                        other2:this.ruleForm.other2,

                        travelDate3:this.returnTime(this.ruleForm.travelDate3[0])+"--"+this.returnTime(this.ruleForm.travelDate3[1]),
                        place3:this.ruleForm.place3,
                        travelWay3:this.ruleForm.travelWay3,
                        days3:this.ruleForm.days3,
                        traffic3:this.ruleForm.traffic3,
                        stay3:this.ruleForm.stay3,
                        subsidy3:this.ruleForm.subsidy3,
                        other3:this.ruleForm.other3,

                        userGetid:this.ruleForm.userGetid,
                        bankcardGetid:this.ruleForm.bankcardGetid,
                        bankGetname:this.ruleForm.bankGetname,
                        companyPayId:this.ruleForm.companyPayId,
                        bankPaycode:this.ruleForm.bankPaycode,
                        bankPayname:this.ruleForm.bankPayname,

                        expenseImage:this.imgArr.join(),

                        //总费用
                        totalAllMoney:this.ruleForm.traffic1+this.ruleForm.stay1+this.ruleForm.subsidy1+this.ruleForm.other1+
                        this.ruleForm.traffic2+this.ruleForm.stay2+this.ruleForm.subsidy2+this.ruleForm.other2+
                        this.ruleForm.traffic3+this.ruleForm.stay3+this.ruleForm.subsidy3+this.ruleForm.other3,
                    };
                    let params2={
                        invoiceCode:this.ruleForm.travelExpenseCode,
                        checkReasult:"管理员补入",
                        checkUser:this.ruleForm.expenseName,
                        checkDate:this.returnTime(new Date()),
                        invoiceType:2,//单据类型（差旅报销单）
                    };
                    console.log('submit!');
                    addTravelExpense(params).then(res => {
                        console.log('addTravelExpense==',res.message);
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
        //     console.log(1111);
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
             if(time2==""){
               return "";
            }
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
            console.log("12121");
            this.imgDialog = false;
        },
    },

  }
</script>


<style rel="stylesheet/scss" lang="scss" scoped>
    #title{
        margin-bottom: 40px;
        margin-left: 560px;
        margin-top: 20px;
    }
    .yuan{
        margin-left: 150px;
        margin-top: -30px;
    }

    .travelDate{
        width:220px;
        margin-left:-110px
    }
    .placeCss{
        margin-left: 460px;

    }
    .placeCss2{
        margin-left:-330px;
        width:140px
    }
    .travelWayCss{
        margin-left: 680px;
    }
    .travelWayCss2{
        margin-left:-390px;
        width:140px
    }
   .daysCss{
        margin-left: 450px;

    }
    .trafficCss{
          margin-left:610px;
    }
    .stayCss{
        margin-left: 800px;
    }
    .subsidyCss{
        margin-left: 990px;
    }

    .otherCss{
        margin-left: 1180px;
    }
    .subtotal{
        margin-left:1370px;
    }
    .numberInput{
       width:140px;
    }
    .btn{
        margin-left:460px;
        margin-top: 40px;
    }

    ::v-deep .el-input-number .el-input__inner {
        text-align: left;
    }
    ::v-deep .vue-treeselect__control,::v-deep .vue-treeselect__placeholder,::v-deep .vue-treeselect__single-value {
        height: 30px;
        line-height: 30px;
    }
    .btn2{
      margin-left:160px;
      margin-top:30px;
    }
     // 改变input框字体颜色
     ::v-deep .is-disabled .el-input__inner{
        background-color: transparent !important;
        color: black;
    }
</style>
