<template>
    <div class="app-container">      
        <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="110px" >
            <el-form-item id="title" >
                <span style="font-size:30px">差旅费报销单</span>                  
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
                    <div class="grid-content bg-purple">
                        <el-form-item label="姓名"  prop="expenseName" class="css1">
                             <el-input  
                                v-model="ruleForm.expenseName" 
                                class="inputCss"
                                placeholder=""  
                                disabled
                            ></el-input> 
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="4">
                    <div class="grid-content bg-purple">
                        <el-form-item label="职别"  prop="job" class="css2">
                            <el-input 
                                disabled
                                v-model="ruleForm.job" 
                                placeholder="请输入职别" 
                                style="width:200px"
                             ></el-input>
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
            <el-form-item   prop="travelDate1">
                <el-input  
                    v-model="ruleForm.travelDate1" 
                    class="travelDate"
                ></el-input> 
            </el-form-item>
            <el-form-item >
                <el-input  
                    v-model="ruleForm.travelDate2" 
                    class="travelDate"
                ></el-input> 
            </el-form-item>
            <el-form-item  >
                <el-input  
                    v-model="ruleForm.travelDate3" 
                   class="travelDate"
                ></el-input>
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
                    :step="0.1" :min="0" 
                    class="numberInput" 
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
             <el-form-item  class="trafficCss">
                <el-input-number 
                    v-model="ruleForm.traffic2" 
                    :precision="2" 
                    :step="0.1" :min="0" 
                     class="numberInput" 
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
             <el-form-item  class="trafficCss" >
                <el-input-number 
                    v-model="ruleForm.traffic3" 
                    :precision="2" 
                    :step="0.1" :min="0"  
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
                    :step="0.1" :min="0" 
                    class="numberInput" 
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
             <el-form-item  class="stayCss">
                <el-input-number 
                    v-model="ruleForm.stay2" 
                    :precision="2" 
                    :step="0.1" :min="0"  
                   class="numberInput" 
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
             <el-form-item  class="stayCss" >
                <el-input-number 
                    v-model="ruleForm.stay3" 
                    :precision="2" 
                    :step="0.1" :min="0"  
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
                    :step="0.1" :min="0"  
                    class="numberInput" 
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
             <el-form-item  class="subsidyCss">
                <el-input-number 
                    v-model="ruleForm.subsidy2" 
                    :precision="2" 
                    :step="0.1" :min="0"  
                    class="numberInput" 
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
             <el-form-item  class="subsidyCss" >
                <el-input-number 
                    v-model="ruleForm.subsidy3" 
                    :precision="2" 
                    :step="0.1" :min="0"  
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
                    :step="0.1" :min="0"  
                     class="numberInput" 
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
             <el-form-item  class="otherCss">
                <el-input-number 
                    v-model="ruleForm.other2" 
                    :precision="2" 
                    :step="0.1" :min="0"  
                    class="numberInput" 
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
             <el-form-item  class="otherCss" >
                <el-input-number 
                    v-model="ruleForm.other3" 
                    :precision="2" 
                    :step="0.1" :min="0"  
                     class="numberInput" 
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item> 
            <el-form-item label="小计" style="margin-left:1410px;margin-top:-350px">
            </el-form-item>
            <el-form-item  class="subtotal">
                {{ruleForm.traffic1+ruleForm.stay1+ruleForm.subsidy1+ruleForm.other1}}
            </el-form-item>
            <el-form-item  class="subtotal">
                {{ruleForm.traffic2+ruleForm.stay2+ruleForm.subsidy2+ruleForm.other2}}
            </el-form-item>
            <el-form-item  class="subtotal">
                {{ruleForm.traffic3+ruleForm.stay3+ruleForm.subsidy3+ruleForm.other3}}
            </el-form-item>
            <el-row style="margin-top:20px">
                <el-col :span="8" >
                    <el-form-item  label="总计金额(小写)" >        
                        {{ruleForm.traffic1+ruleForm.stay1+ruleForm.subsidy1+ruleForm.other1+
                        ruleForm.traffic2+ruleForm.stay2+ruleForm.subsidy2+ruleForm.other2+
                        ruleForm.traffic3+ruleForm.stay3+ruleForm.subsidy3+ruleForm.other3+"元"}}    
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item  label="总计金额(大写)"  >                 
                        {{digitUppercase(ruleForm.traffic1+ruleForm.stay1+ruleForm.subsidy1+ruleForm.other1+
                        ruleForm.traffic2+ruleForm.stay2+ruleForm.subsidy2+ruleForm.other2+
                        ruleForm.traffic3+ruleForm.stay3+ruleForm.subsidy3+ruleForm.other3)+"元"}}    
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row style="margin-top:20px">
               <el-col :span="8"> 
                    <el-form-item label="收款人："  >
                        <el-input 
                            v-model="ruleForm.expenseName" 
                            style="width:200px"
                            disabled
                        ></el-input>
                        <!-- <el-select 
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
                        </el-select> -->
                    </el-form-item>
                </el-col>
                <el-col :span="8"> 
                    <el-form-item label="收款帐号：" >
                        <el-input 
                            disabled
                            v-model="ruleForm.bankcardGetid" 
                            style="width:200px"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8"> 
                    <el-form-item label="收款开户行：" >
                        <el-input 
                            disabled
                            v-model="ruleForm.bankGetname" 
                            style="width:200px"
                        ></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
               <el-col :span="8"> 
                   <el-form-item label="报销单位："  prop="companyPayId">
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
               </el-col>    
               <el-col :span="8"> 
                   <el-form-item label="借款帐号：" >
                    <el-input 
                            v-model="ruleForm.bankPaycode" 
                            style="width:200px"
                            disabled
                        ></el-input>
                    </el-form-item>
               </el-col> 
               <el-col :span="8"> 
                   <el-form-item label="借款开户行：" >
                    <el-input 
                            v-model="ruleForm.bankPayname" 
                            style="width:200px"
                            disabled
                        ></el-input>
                    </el-form-item>
               </el-col> 
            </el-row>  
              <!-- 上传报销凭证影像 -->
            <el-dialog title="上传图片" :visible.sync="imgDialog" width="30%">
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
            </el-dialog>  
             <!-- 图片 -->
            <el-dialog title="图片" :visible.sync="imageVisible" width="60%">
            <div class="demo-image"
                v-for="(item,index) in imgArr" :key="index">
                 <el-image
                    style="width: 500px; height: 500px"
                    :src="baseImgPath+item.value"
                     :preview-src-list="srcList"
                ></el-image>
            </div>
            </el-dialog> 

            <el-row style="margin-top:20px">
                <el-col :span="8">
                   <el-form-item  label="报销凭证影像："  >
                        <el-button  
                            type="primary"
                            @click="getImage"
                        >点击查看</el-button>
                    </el-form-item>
                </el-col>
                 <el-col :span="8">
                   <el-form-item  label="修改影像：" >
                         <el-button  
                            type="primary"
                        @click="toEditImg"
                        >点击上传</el-button>
                    </el-form-item>
                </el-col>
            </el-row>   
             <el-form-item id="btn">
                <el-button @click="toReturn" style="width:100px;">取消</el-button>
                <el-button type="primary" @click="submitForm('ruleForm')" style="width:100px;margin-left: 120px">确认修改</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    import {getAllCheck,addCheckInvoices} from '@/api/invoices/checkInvoices'
    import { getCardInfoBycompany } from '@/api/invoices/expense'
    import {getAllCompany,getAllGetUser} from '@/api/invoices/borrow'
    import {getInfo} from '@/api/login'
    import { getCode,getExpenseItem,editTravelExpenseById,editTravelExpense2} from '@/api/invoices/travelExpense'
    export default {
    name: 'travelExpense',
    data() {
      return {
        imgArr:[],
        imgArr2:[],
        srcList:[],
        baseImgPath:"/eladmin/api/files/showTxt?imgPath=",
        //影像上传参数
        limitNum:10,
        expenseImage:'',
        imgDialog:false,
        dialogImageUrl: '',
        dialogVisible: false,
        //影像查看参数
        imgpath:'',
        imageVisible:false,

        isDisabled:true,
        filePath2:'',
        id:'',
        items:[],
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

            gm:'',//总经理
            dm:'',//部门主管
            finance:'',//财务

            gmCheck:'',
            financeCheck:'',
            dmCheck:'',

            role:'',
            checkType:'',
            
        },
        rules: {
            userGetid: [
                { required: true, message: '请选择收款人', trigger: 'change' }
            ],
            companyPayId: [
                { required: true, message: '请选择出款单位名', trigger: 'change' }
            ],
            expenseDate:[
                {  required: true, message: '请选择报销时间', trigger: 'change' }
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
        travelExpenses:[],
        isAgree:1,
        rejectReasult:'',
        checks:[],
      }
    },
    mounted: function() {
        this.getAllCompany();
        this.getAllGetUser();
        this.selectAllCheck();
        //获取登录用户信息
        // getInfo().then(res => {
        //     console.log("getInfo==", res);
        //      //根据收款人id查找收款银行卡信息 
        //     getCardInfoBycompany(res.user.id).then(res => {
        //         console.log('getCardInfoBycompany==',res);
        //         this.ruleForm.bankcardGetid=res.accountCard;
        //         this.ruleForm.bankGetname=res.accountCardBank;
        //         this.ruleForm.userGetid=res.nickName;
        //     })
        // })
        this.getExpenseItem();
        this.travelExpenses = JSON.parse(window.localStorage.getItem('travelExpenses')).list
        console.log("travelExpenses==",this.travelExpenses[0]);
        this.id=this.travelExpenses[0].id;

        this.ruleForm.job=this.travelExpenses[0].job;
        this.ruleForm.expenseDate=this.travelExpenses[0].createTime;
        this.ruleForm.travelExpenseCode=this.travelExpenses[0].travelExpenseCode;
        this.ruleForm.expenseName=this.travelExpenses[0].nickName;
        
        this.ruleForm.togetherNum=this.travelExpenses[0].togetherNum;
        this.ruleForm.reason=this.travelExpenses[0].reason;

        this.ruleForm.travelDate1=this.travelExpenses[0].travelDate1;
        this.ruleForm.place1=this.travelExpenses[0].place1;
        this.ruleForm.travelWay1=this.travelExpenses[0].travelWay1;
        this.ruleForm.days1=this.travelExpenses[0].days1;
        this.ruleForm.traffic1=this.travelExpenses[0].traffic1;
        this.ruleForm.stay1=this.travelExpenses[0].stay1;
        this.ruleForm.subsidy1=this.travelExpenses[0].subsidy1;
        this.ruleForm.other1=this.travelExpenses[0].other1;
        
        this.ruleForm.travelDate2=this.travelExpenses[0].travelDate2;
        this.ruleForm.place2=this.travelExpenses[0].place2;
        this.ruleForm.travelWay2=this.travelExpenses[0].travelWay2;
        this.ruleForm.days2=this.travelExpenses[0].days2;
        this.ruleForm.traffic2=this.travelExpenses[0].traffic2;
        this.ruleForm.stay2=this.travelExpenses[0].stay2;
        this.ruleForm.subsidy2=this.travelExpenses[0].subsidy2;
        this.ruleForm.other2=this.travelExpenses[0].other2;

        this.ruleForm.travelDate3=this.travelExpenses[0].travelDate3;
        this.ruleForm.place3=this.travelExpenses[0].place3;
        this.ruleForm.travelWay3=this.travelExpenses[0].travelWay3;
        this.ruleForm.days3=this.travelExpenses[0].days3;
        this.ruleForm.traffic3=this.travelExpenses[0].traffic3;
        this.ruleForm.stay3=this.travelExpenses[0].stay3;
        this.ruleForm.subsidy3=this.travelExpenses[0].subsidy3;
        this.ruleForm.other3=this.travelExpenses[0].other3;

        this.ruleForm.totalMoney=this.travelExpenses[0].totalMoney;
        this.ruleForm.paywayId=this.travelExpenses[0].label;
        this.ruleForm.payDate=this.travelExpenses[0].payDate;

        this.ruleForm.companyPayId=this.travelExpenses[0].companyPayId;
        this.ruleForm.bankPaycode=this.travelExpenses[0].bankPaycode;
        this.ruleForm.bankPayname=this.travelExpenses[0].bankGetname;
        this.ruleForm.userGetid=this.travelExpenses[0].userGetid;
        this.ruleForm.bankcardGetid=this.travelExpenses[0].bankcardGetid;
        this.ruleForm.bankGetname=this.travelExpenses[0].bankGetname;

        this.ruleForm.deptId=this.travelExpenses[0].deptId;

        this.ruleForm.gm=JSON.parse(window.localStorage.getItem('travelExpenseCheckPerson'));
        this.ruleForm.finance=JSON.parse(window.localStorage.getItem('travelExpenseCheckPerson'));
        this.ruleForm.dm=JSON.parse(window.localStorage.getItem('travelExpenseCheckPerson'));

        this.borrowId=this.travelExpenses[0].borrowId;

        this.ruleForm.role=JSON.parse(window.localStorage.getItem('travelExpenseRoles'))[0].id;
        
        this.ruleForm.gmCheck=this.travelExpenses[0].gmCheck;
        this.ruleForm.financeCheck=this.travelExpenses[0].financeCheck;
        this.ruleForm.dmCheck=this.travelExpenses[0].dmCheck;

        this.expenseImage=this.travelExpenses[0].expenseImage;
        
        var imgArr= this.expenseImage.split(",");  
        imgArr.map((item,index)=>{
            if(item!=null&&item!=""){
                 this.imgArr.push({id:index,value:item});
            }
        })
        console.log( "imgArr=====",this.imgArr);

    },
    methods: {
        isAgrees(){
            console.log("点了是否同意");
            if(this.isAgree==2){
                this.isDisabled=false;
            }
            if(this.isAgree==1){
                this.isDisabled=true;
            }
        },
        //获取所有审批结果
        selectAllCheck(){
            var travelExpenses = JSON.parse(window.localStorage.getItem('travelExpenses')).list
            var travelExpenseCode=travelExpenses[0].travelExpenseCode;
            console.log('travelExpenseCode==',travelExpenseCode);
            getAllCheck({invoiceCode:travelExpenseCode}).then(res => {
                console.log('selectAllCheck==',res);
                this.checks = res
            })
        },
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
                        this.rejectReasult="审批驳回："+this.rejectReasult;
                        this.checkType=6;
                    };
                    var params1={
                        invoiceCode:this.ruleForm.travelExpenseCode,
                        checkReasult:this.rejectReasult,
                        checkUser:this.ruleForm.gm,
                        checkDate:this.returnTime(new Date()),
                        invoiceType:2,//单据类别（差旅报销单）
                    };
                    var params2={
                        filePath2:this.filePath2,
                        gmCheck:this.ruleForm.gm,
                        id:this.id,
                        invoiceType:this.checkType,
                    };
                    addCheckInvoices(params1).then(res => {
                        this.$message({
                            message: res.message,
                            type: 'success',
                        });
                    });
                    editTravelExpenseById(params2).then(res => {
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
                        this.rejectReasult="审批驳回："+this.rejectReasult;
                        this.checkType=6;
                    };
                    var params1={
                        invoiceCode:this.ruleForm.travelExpenseCode,
                        checkReasult:this.rejectReasult,
                        checkUser:this.ruleForm.finance,
                        checkDate:this.returnTime(new Date()),
                        invoiceType:2,
                    };
                    var params2={
                        filePath2:this.filePath2,
                        financeCheck:this.ruleForm.finance,
                        id:this.id,
                        invoiceType:this.checkType,
                    };
                    addCheckInvoices(params1).then(res => {
                        this.$message({
                            message: res.message,
                            type: 'success',
                        });
                    });
                    editTravelExpenseById(params2).then(res => {
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
                        this.rejectReasult="审批驳回："+this.rejectReasult;
                        this.checkType=6;
                    };
                    var params1={
                        invoiceCode:this.ruleForm.travelExpenseCode,
                        checkReasult:this.rejectReasult,
                        checkUser:this.ruleForm.dm,
                        checkDate:this.returnTime(new Date()),
                        invoiceType:2,
                    };
                    var params2={
                        filePath2:this.filePath2,
                        dmCheck:this.ruleForm.dm,
                        id:this.id,
                        invoiceType:this.checkType,
                    };
                    addCheckInvoices(params1).then(res => {
                        this.$message({
                            message: res.message,
                            type: 'success',
                        });
                    });
                    editTravelExpenseById(params2).then(res => {
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
            getCardInfoBycompany(this.ruleForm.userGetid).then(res => {
                console.log('getCardInfoBycompany==',res);
                this.ruleForm.bankcardGetid=res.payCheck;
                this.ruleForm.bankGetname=res.accountCardBank;
            })
        }, 

        getTravelExpenseCode(){
            getCode().then(res => {
                console.log("getExpenseCode",res.message);
                this.ruleForm.travelExpenseCode=res.message;
            })
        },
        //获取报销项目的金额
        // getItemMoney(){
        //     console.log("item1money==",this.ruleForm.item1Money);
        //     this.items.map(item=>{
        //         if(item.id==this.ruleForm.item1){
        //             this.ruleForm.item1Money=item.itemMoney;
        //         }
        //         if(item.id==this.ruleForm.item2){
        //             this.ruleForm.item2Money=item.itemMoney;
        //         }
        //         if(item.id==this.ruleForm.item3){
        //             this.ruleForm.item3Money=item.itemMoney;
        //         }
        //         if(item.id==this.ruleForm.item4){
        //             this.ruleForm.item4Money=item.itemMoney;
        //         }
        //     })
        // },
        //获取报销项目
        getExpenseItem(){
            getExpenseItem().then(res=>{
                this.items=res;
            })    
        },
        //提交表单
        submitForm(formName) {
            // this.$refs[formName].validate((valid) => {
                // if (valid) {
                    let params={
                        id:this.id,
                        invoiceType:1,//发起状态
                        deptId:this.ruleForm.deptId,
                        travelExpenseCode:this.ruleForm.travelExpenseCode,
                        createTime:this.returnTime(this.ruleForm.expenseDate),
                        createUser:this.ruleForm.expenseId,
                        job:this.ruleForm.job,                 
                        togetherNum:this.ruleForm.togetherNum,
                        reason:this.ruleForm.reason,
                        
                        travelDate1:this.ruleForm.travelDate1,
                        place1:this.ruleForm.place1,
                        travelWay1:this.ruleForm.travelWay1,
                        days1:this.ruleForm.days1,
                        traffic1:this.ruleForm.traffic1,
                        stay1:this.ruleForm.stay1,
                        subsidy1:this.ruleForm.subsidy1,
                        other1:this.ruleForm.other1,

                        travelDate2:this.ruleForm.travelDate2,
                        place2:this.ruleForm.place2,
                        travelWay2:this.ruleForm.travelWay2,
                        days2:this.ruleForm.days2,
                        traffic2:this.ruleForm.traffic2,
                        stay2:this.ruleForm.stay2,
                        subsidy2:this.ruleForm.subsidy2,
                        other2:this.ruleForm.other2,

                        travelDate3:this.ruleForm.travelDate3,
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

                        expenseImage:this.imgArr2.join(),

                        //总费用
                        totalAllMoney:this.ruleForm.traffic1+this.ruleForm.stay1+this.ruleForm.subsidy1+this.ruleForm.other1+
                        this.ruleForm.traffic2+this.ruleForm.stay2+this.ruleForm.subsidy2+this.ruleForm.other2+
                        this.ruleForm.traffic3+this.ruleForm.stay3+this.ruleForm.subsidy3+this.ruleForm.other3,
                    };
                  
                    console.log('submit!');
                    editTravelExpense2(params).then(res => {
                        console.log('editTravelExpense2==',res.message);
                        this.$message({
                            message: "修改成功",
                            type: 'success',
                        });
                    });
                   
                    this.$refs[formName].resetFields(); 
                    this.$router.push({
                        path: "/invoices/addInvoices"
                    });                                     
                // }
                // else {
                //     console.log('error submit!!');
                //     this.$message({
                //         message: "请填写完整",
                //         type: 'warning',
                //     });
                //     return false;
                // }
            // });
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
            console.log("12121"); 
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

        toEditImg(){
            this.imgDialog=true;
            this.imgArr=[];
            console.log("toEditImg",this.imgArr);
        },            
    },
  }
</script>


<style rel="stylesheet/scss" lang="scss" scoped>
    #title{
        margin-bottom: 40px;
        margin-left: 510px; 
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
        width:180px
    }
    .travelWayCss{
        margin-left: 720px;
    }
    .travelWayCss2{
        margin-left:-390px;
        width:180px
    }
   .daysCss{
        margin-left: 530px;

    }
    .trafficCss{
          margin-left:680px;
    }
    .stayCss{
        margin-left: 860px;
    }
    .subsidyCss{
        margin-left: 1040px;
    }
    
    .otherCss{
        margin-left: 1220px;
    }
    .subtotal{
        margin-left:1410px;
    }
    .numberInput{
       width:140px;
    }
    #btn{
        margin-left:460px;
        margin-top: 40px;
    }
    // .daysCss{//天数
    //     margin-left: 710px;
    //     margin-top:-50px;
    // }
    // .subsidyCss{//出差补助
    //     width: 120px;
    //     margin-left: 980px;
    //     margin-top:-50px;
    // }
    // .otherCss{
    //     margin-left:1080px; 
    //     margin-top:-50px;
    //     // width:400px;
    // }
   
    
    // .mealCss{ //餐费
    //     margin-left: 220px;
    //     margin-top:-50px;
      
    // }
    // .trafficCss{
    //     margin-left: 470px;
    //     margin-top: -50px;
        
    // }
    // .stayCss{
    //     margin-left:710px;
    //     margin-top:-50px;
    //     width:400px;
    // }

    // .totalMoneyCss{
    //     margin-left: 970px;
    //     margin-top:-50px;
    // }
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