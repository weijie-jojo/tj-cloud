<template>
    <div class="app-container">  
        <el-button 
            v-print="'#printMe'" 
            type="primary"
            style="margin-left:1400px;margin-bottom: -50px;">打印</el-button>

        <el-form 
            ref="ruleForm" 
            :model="ruleForm" 
            :rules="rules" 
            id="printMe"
            label-width="110px" >
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
                        <el-form-item label="姓名"  prop="expenseName">
                             <el-input  
                                v-model="ruleForm.expenseName" 
                                placeholder=""  
                                disabled
                            ></el-input> 
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="4">
                    <div class="grid-content bg-purple">
                        <el-form-item label="职别"  prop="job" >
                            <el-input 
                                disabled
                                v-model="ruleForm.job" 
                                placeholder="请输入职别" 
                             ></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="4">
                    <div class="grid-content bg-purple">
                        <el-form-item label="同行人数" prop="togetherNum"  >
                            <el-input-number 
                                disabled
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
                            disabled
                            type="textarea"
                            :rows="4"
                            placeholder="请输入出差事由"
                            v-model="ruleForm.reason">
                        </el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
            <el-form-item label="起讫时间" style="margin-left:10px;margin-top:20px">
            </el-form-item>    
            <el-form-item   prop="travelDate1" >
                <el-input  
                    v-model="ruleForm.travelDate1" 
                    disabled
                    class="travelDate"
                ></el-input> 
            </el-form-item>
            <el-form-item  >
                <el-input  
                    v-model="ruleForm.travelDate2" 
                    disabled
                    class="travelDate"
                ></el-input> 
            </el-form-item>
            <el-form-item  >
                <el-input  
                    v-model="ruleForm.travelDate3" 
                    disabled
                    class="travelDate"
                ></el-input>
            </el-form-item>
            
            <el-form-item label="起讫地点" style="margin-left:200px;margin-top:-350px">
            </el-form-item>   
            <el-form-item  prop="place1" class="placeCss">
                <el-input  
                    disabled
                    class="placeCss2"
                    v-model="ruleForm.place1" 
                    placeholder="请输入起讫地点" 
                ></el-input>
            </el-form-item>
            <el-form-item   class="placeCss">
                <el-input  
                    disabled
                     class="placeCss2"
                    v-model="ruleForm.place2" 
                    placeholder="请输入起讫地点" 
                ></el-input>
            </el-form-item>
            <el-form-item   class="placeCss">
                <el-input  
                    disabled
                    class="placeCss2"
                    v-model="ruleForm.place3" 
                    placeholder="请输入起讫地点" 
                ></el-input>
            </el-form-item>
              
            <el-form-item label="出行方式" style="margin-left:360px;margin-top:-350px">
            </el-form-item> 
            <el-form-item  prop="travelWay1" class="travelWayCss">
                <el-input 
                    disabled
                    class="travelWayCss2"
                    v-model="ruleForm.travelWay1"
                    placeholder="请输入出行方式" 
                ></el-input>
            </el-form-item>
            <el-form-item   class="travelWayCss">
                <el-input 
                    disabled
                    class="travelWayCss2"
                    v-model="ruleForm.travelWay2" 
                    placeholder="请输入出行方式" 
                ></el-input>
            </el-form-item>
            <el-form-item   class="travelWayCss">
                <el-input  
                    disabled
                    class="travelWayCss2"
                    v-model="ruleForm.travelWay3" 
                    placeholder="请输入出行方式" 
                ></el-input>
            </el-form-item>

            <el-form-item label="天数" style="margin-left:490px;margin-top:-350px">
            </el-form-item> 
            <el-form-item  prop="days1" class="daysCss">
                <el-input-number 
                    disabled
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
                    disabled
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
                    disabled
                    v-model="ruleForm.days3" 
                    :precision="0" 
                    :step="1" :min="1" 
                    :max="1000" 
                    style="width:100px"
                ></el-input-number>
                <span style="margin-left:10px">天</span>
            </el-form-item>
            <el-form-item label="交通费" style="margin-left:670px;margin-top:-350px">
            </el-form-item> 
            <el-form-item prop="traffic1" class="trafficCss">
                <el-input-number 
                    v-model="ruleForm.traffic1" 
                    :precision="2" 
                    :step="0.1" :min="0" 
                    class="numberInput" 
                    disabled
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
             <el-form-item  class="trafficCss">
                <el-input-number 
                    v-model="ruleForm.traffic2" 
                    :precision="2" 
                    :step="0.1" :min="0" 
                     class="numberInput" 
                     disabled
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
             <el-form-item  class="trafficCss" >
                <el-input-number 
                    v-model="ruleForm.traffic3" 
                    :precision="2" 
                    :step="0.1" :min="0"  
                    class="numberInput" 
                    disabled
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
            <el-form-item label="住宿费" style="margin-left:860px;margin-top:-350px">
            </el-form-item> 
            <el-form-item prop="stay1"  class="stayCss">
                <el-input-number 
                    v-model="ruleForm.stay1" 
                    :precision="2" 
                    :step="0.1" :min="0" 
                    class="numberInput" 
                    disabled 
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
             <el-form-item  class="stayCss">
                <el-input-number 
                    v-model="ruleForm.stay2" 
                    :precision="2" 
                    :step="0.1" :min="0"  
                   class="numberInput" 
                   disabled
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
             <el-form-item  class="stayCss" >
                <el-input-number 
                    v-model="ruleForm.stay3" 
                    :precision="2" 
                    :step="0.1" :min="0"  
                   class="numberInput" 
                   disabled
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
            <el-form-item label="差旅补助" style="margin-left:1060px;margin-top:-350px">
            </el-form-item> 
            <el-form-item prop="subsidy1" class="subsidyCss">
                <el-input-number 
                    v-model="ruleForm.subsidy1" 
                    :precision="2" 
                    :step="0.1" :min="0"  
                    class="numberInput" 
                    disabled
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
             <el-form-item  class="subsidyCss">
                <el-input-number 
                    v-model="ruleForm.subsidy2" 
                    :precision="2" 
                    :step="0.1" :min="0"  
                    class="numberInput" 
                    disabled
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
             <el-form-item  class="subsidyCss" >
                <el-input-number 
                    v-model="ruleForm.subsidy3" 
                    :precision="2" 
                    :step="0.1" :min="0"  
                     class="numberInput" 
                     disabled
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
            <el-form-item label="其他" style="margin-left:1230px;margin-top:-350px">
            </el-form-item>
            <el-form-item  class="otherCss">
                <el-input-number 
                    v-model="ruleForm.other1" 
                    :precision="2" 
                    :step="0.1" :min="0"  
                     class="numberInput" 
                     disabled
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
             <el-form-item  class="otherCss">
                <el-input-number 
                    v-model="ruleForm.other2" 
                    :precision="2" 
                    :step="0.1" :min="0"  
                    class="numberInput" 
                    disabled
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item>
             <el-form-item  class="otherCss" >
                <el-input-number 
                    v-model="ruleForm.other3" 
                    :precision="2" 
                    :step="0.1" :min="0"  
                     class="numberInput" 
                     disabled
                ></el-input-number>
                <div class="yuan">元</div>
            </el-form-item> 
            <el-form-item label="小计" style="margin-left:1370px;margin-top:-350px">
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
            </el-row>
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
               <el-col :span="8" > 
                    <el-form-item label="收款人："  >
                        <el-input 
                            v-model="ruleForm.userGetid" 
                            style="width:200px"
                            disabled
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8"> 
                    <el-form-item label="收款帐号：" >
                        <el-input 
                            v-model="ruleForm.bankcardGetid" 
                            style="width:200px"
                            disabled
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8"> 
                    <el-form-item label="收款开户行：" >
                        <el-input 
                            v-model="ruleForm.bankGetname" 
                            style="width:200px"
                            disabled
                        ></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row style="margin-top:20px">
               <el-col :span="8"> 
                   <el-form-item label="报销单位："  >
                        <el-input 
                            v-model="ruleForm.companyPayId" 
                            style="width:200px"
                            disabled
                        ></el-input>
                        <!-- <el-select 
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
                        </el-select> -->
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
               
            <el-row style="margin-top:20px">
                <el-col :span="5">
                    <el-form-item label="总经理"    >
                        <el-input  
                            v-model="ruleForm.gmCheck" 
                            class="inputCss" 
                            disabled
                        ></el-input> 
                    </el-form-item>
                </el-col>
                <el-col :span="5" style="margin-left:25px">
                     <el-form-item label="财务"    >
                        <el-input  
                            v-model="ruleForm.financeCheck" 
                            class="inputCss" 
                            disabled
                        ></el-input> 
                    </el-form-item>
                </el-col>
                <el-col :span="5" style="margin-left:25px">
                     <el-form-item label="部门主管"   >
                        <el-input  
                            v-model="ruleForm.dmCheck" 
                            class="inputCss" 
                            disabled
                        ></el-input> 
                    </el-form-item>
                </el-col>
                <el-col :span="5" style="margin-left:25px">
                    <el-form-item label="报销人"   >
                        <el-input  v-model="ruleForm.expenseName" placeholder="" class="inputCss" disabled></el-input> 
                    </el-form-item>
                </el-col>
            </el-row>

            <el-form-item style="margin-left:80px;margin-top:300px">
                <div 
                    style=" font-size:20px;
                    margin-top:10px;
                    margin-bottom:20px;
                    color:blue"
                    >{{"审批进度"}}</div>
                <el-table
                    border
                    :data="checks"
                    style="width: 75%"
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
                <span class="imgTitle">报销凭证影像</span>
                <el-image
                   style="width:1100px;height: 700px;"
                    :src="baseImgPath+item.value"
                ></el-image>
            </el-form-item>

            <el-form-item 
                v-show="isShowImg2" 
                class="demo-image__preview" 
                v-for="(item,index) in imgArr2" :key="index" 
                style="margin-top:200px;margin-left: -60px;">
                <span  class="imgTitle">报销凭证影像</span>
                <el-image
                   style="width:1100px;height: 700px;"
                    :src="baseImgPath+item.value"
                ></el-image>
            </el-form-item>

            <!-- <el-form-item 
                v-show="isShowImg2" 
                class="demo-image__preview" 
                style="margin-top:200px;margin-left: -60px;">
                <span class="imgTitle">付款凭证影像</span>
                <el-image
                   style="width:1100px;height: 700px;"
                    :src="imgpath2"
                ></el-image>
            </el-form-item> -->
            
        </el-form>
        <div style="margin-left:140px;margin-top:40px">
            <div >
                报销凭证影像：
                <el-button  
                    type="primary"
                    @click="getImage"
                >点击查看</el-button>
            </div>
            <div 
               style="margin-left:320px;margin-top: -32px;">
                付款凭证影像：
                <el-button  
                    type="primary"
                @click="getImage2"
                >点击查看</el-button>
            </div>
        </div>
        <!-- 图片 -->
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
        <el-dialog title="图片" :visible.sync="imageVisible2" width="60%">
            <div class="demo-image" 
                v-for="(item,index) in imgArr2" :key="index" 
                style="margin-top:20px;">
                <el-image
                    style="width: 300px; height: 200px"
                    :src="baseImgPath+item.value"
                    :preview-src-list="srcList2"
                ></el-image>
            </div>
            <!-- <div class="demo-image">
                <el-image
                    style="width: 500px; height: 500px"
                    :src="imgpath2"
                ></el-image>
            </div> -->
        </el-dialog>
    </div>
</template>
<script>
    import {getAllCheck,addCheckInvoices} from '@/api/invoices/checkInvoices'
    import { getCardInfoBycompany } from '@/api/invoices/expense'
    import {getAllCompany,getAllGetUser} from '@/api/invoices/borrow'
    import {getInfo} from '@/api/login'
    import { getCode,addTravelExpense,getExpenseItem,editTravelExpenseById} from '@/api/invoices/travelExpense'
    export default {
    name: 'travelExpense',
    data() {
      return {
        isShowImg:false,
        isShowImg2:false,
        srcList:[],
        imgArr:[],
        imgArr2:[],
        srcList2:[],
        baseImgPath:"/ontherRequest/api/files/showImg?imgPath=",
        imgpath:'',
        expenseImage:'',
        imageVisible:false,

        imgpath2:'',
        expenseImage2:'',
        imageVisible2:false,

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
                { required: true, message: '请选择报销时间', trigger: 'change' }
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
        this.selectAllCheck();
        //获取登录用户信息
        getInfo().then(res => {
            console.log("getInfo==", res);
            // this.ruleForm.expenseName=res.user.nickName;
            // this.ruleForm.expenseId=res.user.id;
            // this.ruleForm.deptId=res.user.dept.id;
        })
        // this.ruleForm.expenseDate=new Date();
        this.getExpenseItem();
        // this.getTravelExpenseCode();
        this.getAllCompany();
        // this.getAllGetUser();
        this.travelExpenses = JSON.parse(window.localStorage.getItem('travelExpenses')).list
        console.log("travelExpenses==",this.travelExpenses[0]);
        this.id=this.travelExpenses[0].id;

        this.ruleForm.expenseDate=this.travelExpenses[0].createTime;
        this.ruleForm.travelExpenseCode=this.travelExpenses[0].travelExpenseCode;
        this.ruleForm.expenseName=this.travelExpenses[0].nickName;
        this.ruleForm.job=this.travelExpenses[0].job;
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
        this.expenseImage2=this.travelExpenses[0].expenseImage2;
        
        var imgArr= this.expenseImage.split(",");

        if (imgArr[0]=="") {
            console.log("404");
            this.isShowImg=false;
        }else{
            imgArr.map((item,index)=>{
                if(item!=null&&item!=""){
                    this.imgArr.push({id:index,value:item});
                }
            });
            this.isShowImg=true;
        }

        var imgArr2= this.expenseImage2.split(",");  
        if (imgArr2[0]=="") {
            console.log("404");
            this.isShowImg2=false;
        }else{
            imgArr2.map((item,index)=>{
                if(item!=null&&item!=""){
                    this.imgArr2.push({id:index,value:item});
                }
            })
            this.isShowImg2=true;
        }
      
    },
    methods: {
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
                        this.checkType=6;
                    };
                    var params1={
                        invoiceCode:this.ruleForm.travelExpenseCode,
                        checkReasult:this.rejectReasult,
                        checkUser:this.ruleForm.gm,
                        checkDate:this.returnTime(new Date()),
                        invoiceType:3,
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
                        this.checkType=6;
                    };
                    var params1={
                        invoiceCode:this.ruleForm.travelExpenseCode,
                        checkReasult:this.rejectReasult,
                        checkUser:this.ruleForm.finance,
                        checkDate:this.returnTime(new Date()),
                        invoiceType:3,
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
                        this.checkType=6;
                    };
                    var params1={
                        invoiceCode:this.ruleForm.travelExpenseCode,
                        checkReasult:this.rejectReasult,
                        checkUser:this.ruleForm.dm,
                        checkDate:this.returnTime(new Date()),
                        invoiceType:3,
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
                res.list.map(item=>{
                    if(item.groupCode==this.ruleForm.companyPayId){
                        this.ruleForm.companyPayId=item.groupName;
                    }
                })
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
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    let params={
                        invoiceType:1,//发起状态
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

                        //总费用
                        totalAllMoney:this.ruleForm.traffic1+this.ruleForm.stay1+this.ruleForm.subsidy1+this.ruleForm.other1+
                        this.ruleForm.traffic2+this.ruleForm.stay2+this.ruleForm.subsidy2+this.ruleForm.other2+
                        this.ruleForm.traffic3+this.ruleForm.stay3+this.ruleForm.subsidy3+this.ruleForm.other3,
                    };
                    console.log('submit!');
                    addTravelExpense(params).then(res => {
                        console.log('addTravelExpense==',res.message);
                        this.$message({
                            message: res.message,
                            type: 'success',
                        });
                    });
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
            console.log(1111);
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
        },    
        getImage2(){
            if(this.imgArr2.length<=0){
                this.imageVisible2=false;
                this.$message({
                    message: "没有影像",
                    type: 'warning',
                });
            }else{
                this.imageVisible2=true;
                this.imgArr2.map(item=>{//增加可预览功能
                    this.srcList2.push(this.baseImgPath+item.value);
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
        width:180px;
        margin-left:-110px
    }
    .placeCss{
        margin-left: 420px;
    }
    .placeCss2{
        margin-left:-330px;
        width:140px
    }
    .travelWayCss{
        margin-left: 640px;
    }
    .travelWayCss2{
        margin-left:-390px;
        width:140px
    }
   .daysCss{
        margin-left: 410px;

    }
    .trafficCss{
          margin-left:570px;
    }
   .stayCss{
        margin-left: 760px;
    }
    .subsidyCss{
        margin-left: 950px;
    }
    
    .otherCss{
        margin-left: 1140px;
    }
    .subtotal{
        margin-left:1330px;
    }
    .numberInput{
       width:140px;
    }
    #btn{
        margin-left:460px;
        margin-top: 20px;
    }
     .inputCss{
        width: 190px;
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