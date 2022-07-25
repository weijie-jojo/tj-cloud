<template>
    <div>
        <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="140px">


            <el-row type="flex" class="row-bg rowCss combottom" style="padding-top:20px" justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="项目编号">
                        <el-input v-model="formData.projectCode" disabled></el-input>
                    </el-form-item>

                    <el-form-item class="comright" label="项目名称" prop="projectName">
                        <el-input v-model="formData.projectName"></el-input>
                    </el-form-item>
                </el-col>

                <el-col :span="9">

                    <el-form-item class="comright" label="项目时间" prop="projectTimeStart">
                        <el-input v-model="formData.projectTimeStart" disabled></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="项目金额" prop="projectTotalAmount">
                        <el-input-number style="width:100%" v-model="formData.projectTotalAmount" :precision="2"
                            :step="0.01" :min="0">
                        </el-input-number>
                    </el-form-item>
                </el-col>
            </el-row>


            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                    

                    <el-form-item class="comright" label="甲方" prop="purchCompany">
                        <!-- <el-select  clearable v-model="formData.purchCompany">
                          <el-option v-for="item in purchCompanyOptions" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                        </el-select> -->
                        <el-input v-model="formData.purchCompany"></el-input>
                    </el-form-item>
                      
                </el-col>

                <el-col :span="9">
                   <el-form-item class="comright" label="甲方纳税人识别号" prop="purchCompanyTaxid">
                        <el-input v-model="formData.purchCompanyTaxid"></el-input>
                    </el-form-item>
                       
                </el-col>
            </el-row>
             <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="乙方" prop="projectOwner">
                        <el-select @change="ownnew" style="width:100%" clearable v-model="formData.projectOwner">
                            <el-option v-for="item in ownoptions" :key="item.selfId" :label="item.selfName"
                                :value="item.selfCode">
                            </el-option>
                        </el-select>
                    </el-form-item>
                     <el-form-item class="comright" label="已开金额">
                        <el-input v-model="issuedAmount" disabled></el-input>
                    </el-form-item>

                   
                </el-col>

                <el-col :span="9">

                    <el-form-item class="comright" label="乙方纳税人识别号">
                        <el-input disabled v-model="owerTax"></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="剩余金额">
                        <el-input v-model="balance" disabled></el-input>
                    </el-form-item>
                   
                </el-col>
            </el-row>





            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="开票内容类型">
                        <el-radio v-model="fileNameradio" label="1" @change="filenamer">手动输入</el-radio>
                        <el-radio v-model="fileNameradio" label="2" @change="filenamer">上传附件 </el-radio>
                    </el-form-item>



                </el-col>

                <el-col :span="9">
                    <el-form-item class="comright" label="开票内容" prop="fileName" v-if="fileNameradio == 1">

                        <el-input type="textarea" :rows="2" v-model="formData.fileName">
                        </el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="开票内容附件" prop="fileName" v-if="fileNameradio == 2">

                        <el-upload class="upload-demo" action="/eladmin/api/files/doUpload"
                            :on-success="handlesuccess1" :on-preview="handlePreview1" :on-remove="handleRemove1"
                            :before-remove="beforeRemove1" multiple :limit="9" :on-exceed="handleExceed1"
                            :file-list="fileName" list-type="picture">
                            <el-button size="small" type="primary">点击上传</el-button>
                        </el-upload>
                        <el-dialog :visible.sync="dialogVisible1" append-to-body>
                            <img width="100%" :src="dialogImageUrl1" alt="" />
                        </el-dialog>
                    </el-form-item>
                </el-col>
            </el-row>


           

            

            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="8"></el-col>
                <el-col :span='8' class="flexs">
                    <el-button type="danger" @click="resetForm">返回</el-button>
                    <el-button type="primary" @click="onSubmit">提交</el-button>
                </el-col>
                <el-col :span="8"></el-col>
            </el-row>
        </el-form>
    </div>
</template>
<script>
import qs from 'qs';
import crudRate from '@/api/company/rate'
import { TicketByCode } from "@/api/project/ticket";
import { detail, getcode, getinfoByUserId, edit, ownlist } from "@/api/project/list";
import { getInfo } from '@/api/login'
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
    components: { Treeselect },
    data() {
        return {
            issuedAmount:0.00,
            balance:0.00,
            projectStatus: 1,//乙方状态
            username: '',
            userId: '',
            industryId: '',
            industryTypes: [],
            industryTypeList: [],
            fileName: [],
            fileNamefile: [],//附件
            dialogVisible1: false,
            dialogImageUrl1: "",
            fileNameradio: '1', //开票类型
            natureBusiness: '',//经营范围
            tickettaxvipok: false,
            placename: '',
            isokradio: '2',
            companyTax: '',//甲方纳税人识别号
            owerTax: '',//乙方纳税人识别号
            owntype: '',//乙方行业类型
            owerTaxfee: '',//乙方税率
          
            formData: {
                projectDesc: '',//开票描述
                purchCompanyTaxid: '',//甲方纳税人识别号
                ticketTax: '',//发票税率
                ticketType: '',  //发票类型
                // checkContent: "",
                // createBy: "",
                // createTime: "",
                fileName: '',//开票内容
                placeCode: "",
                projectAcceptanceStatus: 0,
                projectCheckStatus: 0,
                projectCode: "",
                projectContractStatus: 0,
                projectDesc: "",
                projectDutypaidStatus: 0,
                projectGrossMargin: 0,
                projectGrossProfit: 0,
                projectLeader: "",
                projectName: "",
                projectNetProfit: 0,
                projectOwner: "",
                projectPackageAmount: 0,
                projectStatus: 0,
                projectTicketStatus: 0,
                // projectTimeEnd: "",
                projectTimeStart: "",
                projectTotalAmount: 0,
                projectTrade: "",
                purchCompany: "",
                remark: "",
                industryType: '',
                selfName:'',
                isDeleted:1,
            },
            baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
            options: [
                {
                    value: 0,
                    label: '异常'
                },
                {

                    value: 1,
                    label: '正常'
                },
                {
                    value: 2,
                    label: '异常'
                },
            ],
            //甲方
            purchCompanyOptions: [],
            //乙方
            ownoptions: [],
            //乙方行业类型

            ownindustry: [],
            //发票类型
            ticketTypeoptions: [
                {
                    value: 0,
                    label: '增值税普通发票'
                },
                {

                    value: 1,
                    label: '增值税专用发票'
                },
            ],
            //发票普通税率
            ticketNormal: [{
                value: 0,
                label: '免税'
            },
            {

                value: 3,
                label: '3%'
            },],
            rules: {
                projectName: [
                    {
                        required: true,
                        message: "请输入项目名称",
                        trigger: "blur",
                    },
                ],
                projectTotalAmount: [
                    {
                        required: true,
                        message: "请输入项目额",
                        trigger: "blur",
                    },
                ],

                ticketType: [
                    {
                        required: true,
                        message: "请选择发票类型",
                        trigger: "change",
                    },
                ],

                ticketTax: [
                    {
                        required: true,
                        message: "发票税率不能为空",
                        trigger: "change",
                    },
                ],
                industryType: [
                    {
                        required: true,
                        message: "请选择乙方行业类型",
                        trigger: "change",
                    },
                ],
                placeCode: [
                    {
                        required: true,
                        message: "请选择渠道商",
                        trigger: "change",
                    },
                ],

                projectOwner: [
                    {
                        required: true,
                        message: "请选择乙方",
                        trigger: "change",
                    },
                ],

                purchCompany: [
                    {
                        required: true,
                        message: "请选择甲方",
                        trigger: "change",
                    },
                ],

                purchCompanyTaxid: [
                    {
                        required: true,
                        message: "请输入甲方纳税人识别号",
                        trigger: "blur",
                    },
                ],

                fileName: [
                    {
                        required: true,
                        message: "开票内容不能为空",

                    },
                ],
                projectDesc: [
                    {
                        required: true,
                        message: "请输入发票描述",
                        trigger: "blur",
                    },
                ],

            },

        };
    },
    computed: {},
    watch: {
        "formData.contactName": {
            handler: function () {
                this.formData.legalPersonName = this.formData.contactName;
            },
            deep: true,
            
        },
        'formData.industryType': 'selectIndustryType',
    },
   
    
    mounted() {
       this.getRate();
       this.getlist();
       this.ticketByCode();
        //this.gettoday();
    },


    methods: {
          //监听行业类型
        selectIndustryType1() {
            console.log("industryType==", this.formData.industryType);
            var rate = this.industryTypeList.find((item) => item.industryId == this.formData.industryType);
            console.log("rate==", rate);
            this.industryId = rate.industryId;  //行业类型id
            this.owerTaxfee = rate.taxRate;
          
            
            let industryType = rate.industryId;

            ownlist({ username: this.username, industryType: industryType }).then(res => {
                this.ownoptions = res;


                  for (let i in this.ownoptions) {
                // console.log(1111,this.ownoptions[i].selfName);
                 console.log(2222,this.formData.selfName);
                if (this.ownoptions[i].selfName == this.formData.selfName) {
                    this.natureBusiness = this.ownoptions[i].natureBusiness;
                    this.owerTax = this.ownoptions[i].taxId;
                   console.log(this.ownoptions[i]);
                   }
                }


             }).catch(err => {
                console.log(err);
            });
           
        },
          //计算已开和剩余金额
        ticketByCode(){
          TicketByCode({
           projectCode:this.formData.projectCode
           }).then(res=>{
            let arr=res;
             this.issuedAmount=0.00;
            for(let i in arr){
                if(arr[i].ticketAmount>0){
                   this.issuedAmount+=arr[i].ticketAmount*1;
                }
            }
            this.balance=this.formData.projectTotalAmount * 1-this.issuedAmount*1;

          }).catch(err=>{

          });
        },
         getlist(){
            detail({
                projectCode: this.$cache.local.getJSON("publicTickets").projectCode
            }).then((response) => {
             
                 this.formData=response.data[0];
           
           
           
            this.selectIndustryType1();

           this.isokradio=JSON.stringify(this.formData.placeStatus);
              
                 if(this.formData.fileName){
                    this.formData.fileName=JSON.parse(this.formData.fileName);
                    if(Array.isArray(this.formData.fileName) ){
                          this.fileNameradio='2';
                          //如果是图片的话
                          for(let j in this.formData.fileName){
                             this.fileName.push({
                                 name:this.formData.fileName[j],
                                 url:this.baseImgPath+this.formData.fileName[j]

                             })
                          }
                         
                        






                    }else{
                         this.fileNameradio='1';
                    }
                    
                 }else{
                    this.fileNameradio='1';
                 }
                   if(this.formData.isActive){
                         this.projectStatus=parseInt(this.formData.isActive);
                    }else{
                        this.projectStatus=1;
                    }
            });
        },
        //监听开票内容选择
        filenamer(e){
          if(e==1){
            this.formData.fileName='';
          }
        },
        //监听乙方
        ownnew(e) {
            console.log(e);
            for (let i in this.ownoptions) {
                if (this.ownoptions[i].selfCode == e) {
                    console.log(this.ownoptions[i].isActive);
                    // if (this.ownoptions[i].isActive > -1) {
                    //     this.placeStatus = parseInt(this.ownoptions[i].isActive);
                    // } else {
                    //     this.placeStatus = 0;
                    // }
                    if(this.ownoptions[i].isActive){
                         this.projectStatus=parseInt(this.ownoptions[i].isActive);
                    }else{
                        this.projectStatus=1;
                    }
                    this.formData.selfName=this.ownoptions[i].selfName;
                    this.natureBusiness = this.ownoptions[i].natureBusiness;
                    this.owerTax = this.ownoptions[i].taxId;
                   // this.getcode(this.ownoptions[i].selfCode);

                }
            }
        },
        //监听渠道商状态  
        placeNew(e) {
            console.log(e);
            for (let i in this.placeCodeOptions) {
                if (this.placeCodeOptions[i].placeCode == e) {
                    this.isokradio = JSON.stringify(this.placeCodeOptions[i].placeStatus);
                }
            }


        },
        //返回
        resetForm() {
            this.$router.back();
        },


        handlesuccess1(file, fileList) {
            this.fileNamefile.push(file.obj);
        },
        handleRemove1(file, fileList) {
            const i = this.fileNamefile.findIndex((item) => item === fileList);
            this.fileNamefile.splice(i, 1);
        },
        handlePreview1(file) {
            this.dialogImageUrl1 = file.url;
            this.dialogVisible1 = true;
        },
        handleExceed1(files, fileList) {
            this.$message.warning(
                `当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length
                } 个文件`
            );
        },
        beforeRemove1(file, fileList) {
            return this.$confirm(`确定移除 ${file.name}？`);
        },
        //渠道商接口  记得修改 userid
        getinfoByUserId() {
            getInfo().then(res => {
                this.userId = res.user.userId;
                this.username = res.user.userName;
                this.formData.projectLeader = res.user.nickName;
                getinfoByUserId({ userId: this.userId }).then(res => {
                    this.placeCodeOptions = res.data;
                })
            })
        },
        //获取税率
        getRate() {
            crudRate.getAllRate().then(res => {
                console.log("getAllRate", res.rows);
                // this.industryTypes=res.rows;
                let tree = []; // 用来保存树状的数据形式
                this.parseTree(res.rows, tree, 0);
                console.log("tree", tree);
                this.industryTypes = tree;
                this.industryTypeList = res.rows;
            })
        },
        //把数据整成树状
        parseTree(industry, tree, pid) {
            for (var i = 0; i < industry.length; i++) {
                if (industry[i].parentId == pid) {
                    var obj = {
                        id: industry[i].industryId,
                        label: industry[i].industryName,
                        children: [],
                    };
                    tree.push(obj);
                    this.parseTree(industry, obj.children, obj.id);
                }
            }
        },
        //监听行业类型
        selectIndustryType() {
            console.log("industryType==", this.formData.industryType);
            var rate = this.industryTypeList.find((item) => item.industryId == this.formData.industryType);
            console.log("rate==", rate);
            this.industryId = rate.industryId;  //行业类型id
            this.owerTaxfee = rate.taxRate;
            this.formData.projectTrade = rate.industryName;//所属行业
            let industryType = rate.industryId;

            ownlist({ username: this.username, industryType: industryType }).then(res => {
                this.ownoptions = res;
            }).catch(err => {
                console.log(err);
            });
           
        },

        //监听开票内容类型
        tickettaxvip(e) {
            console.log(e);
            if (e > 0) {
                this.tickettaxvipok = true;
                this.formData.ticketTax = 3;
            } else {
                this.tickettaxvipok = false;

            }

        },
        repair(i) {
            if (i >= 0 && i <= 9) {
                return "0" + i;
            } else {
                return i;
            }
        },
        gettoday() {
            var date = new Date();//当前时间
            var year = date.getFullYear() //年
            var month = this.repair(date.getMonth() + 1);//月
            var day = this.repair(date.getDate());//日

            var hour = this.repair(date.getHours());//时
            var minute = this.repair(date.getMinutes());//分
            var second = this.repair(date.getSeconds());//秒

            //当前时间 
            var curTime = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
            // this.formData.createTime = curTime;
            this.formData.projectTimeStart = curTime;

        },
        getcode(selfCode) {
            getcode({selfCode:selfCode}).then((res) => {
                this.formData.projectCode = res;
            }).catch((errore) => {
                console.log(errore);
            })
        },
        handleChange(val) {
            console.log(val);
        },
        onSubmit() {
            this.$refs["elForm"].validate((valid) => {
                // TODO 提交表单
                if (valid) {
                    //如果是附件的话
                    if (this.fileNameradio == 2) {
                        this.formData.fileName = this.fileNamefile;
                        this.formData.fileName = JSON.stringify(this.formData.fileName);
                    }



                    edit(this.formData).then((res) => {
                        if (res != undefined) {
                            if (res != undefined) {
                                if (res.code === 200) {
                                    this.$modal.msgSuccess("编辑成功!");
                                    this.$nextTick(function () {
                                        this.$tab.refreshPage("/project/list").then(() => {
                                            this.$tab.openPage("项目列表", "/project/list");
                                        });
                                        //this.$router.push("employed");
                                    });
                                } else {
                                    this.$modal.msgError(res.msg);
                                }
                            }
                        }
                    });
                } else {
                    this.$message({
                        message: "请填写完整",
                        type: "warning",
                    });
                }
            });
        },
        toReturn2() {
            this.$router.back();
        },
    },
};
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
.rowCss {
    margin-top: 10px;
}

// 改变input框字体颜色
::v-deep .is-disabled .el-input__inner {
    background-color: transparent !important;
    color: black;
}

.paddingbg-s {
    padding-top: 15px;
}

.footers {
    display: flex;
    justify-content: center;
    align-items: center;
}

.rowCss {
    margin-top: 10px;
}

.comright {
    padding-right: 10%;
}

.combottom {
    margin-bottom: 10px;

}

.flexs {
    display: flex;
    justify-content: center;

}

.bankno {

    letter-spacing: 2px;

    font-size: 20px;

    color: blue;
}



// ::v-deep .el-tabs__nav-scroll {
//   width: 50% !important;
//   margin: 0 auto !important;
// }
</style>
