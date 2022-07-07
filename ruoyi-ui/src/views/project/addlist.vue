<template>
    <div>
        <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="140px">

            <el-row type="flex" class="row-bg rowCss combottom" style="margin-bottom:20px" justify="space-around">
                <el-col :span="7">
                    <div class="bankno">立项信息</div>

                </el-col>
                <el-col :span="7">
                    <div></div>
                </el-col>
            </el-row>
            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="项目编号">
                        <el-input v-model="formData.projectCode" disabled></el-input>
                    </el-form-item>

                    <el-form-item class="comright" label="项目名称" prop="projectName">
                        <el-input v-model="formData.projectName"></el-input>
                    </el-form-item>
                </el-col>

                <el-col :span="9">

                    <el-form-item class="comright" label="项目时间" prop="createTime">
                        <el-input v-model="formData.createTime" disabled></el-input>
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
                    <el-form-item class="comright" label="渠道商" prop="placeCode">
                        <el-input v-model="placename" disabled></el-input>
                    </el-form-item>

                    <el-form-item class="comright" label="甲方" prop="purchCompany">
                        <el-input v-model="formData.purchCompany"></el-input>
                    </el-form-item>
                </el-col>

                <el-col :span="9">

                    <el-form-item class="comright" label="渠道商状态" prop="isokradio">
                          <el-radio v-model="isokradio" label="1"> 正常</el-radio>
                          <el-radio v-model="isokradio" label="2">冻结 </el-radio>
                    </el-form-item>
                    <el-form-item class="comright" label="甲方纳税人识别号">
                         <el-input disabled v-model="companyTax"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
           


            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="乙方" prop="projectOwner">
                        <el-input v-model="formData.projectOwner" disabled></el-input>
                    </el-form-item>

                    <el-form-item class="comright" label="乙方状态" prop="projectStatus">
                        <el-select disabled clearable v-model="formData.projectStatus" placeholder="请选择项目状态">
                          <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                </el-select>
                    </el-form-item>
                </el-col>

                <el-col :span="9">

                    <el-form-item class="comright" label="乙方纳税人识别号">
                           <el-input disabled v-model="owerTax"></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="业务经理">
                         <el-input  v-model="formData.projectLeader" disabled></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
         

              <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="乙方行业类型">
                        
                         <el-select disabled clearable v-model="owntype">
                          <el-option v-for="item in ownoptions" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item class="comright" label="乙方状态" prop="projectStatus">
                        <el-select disabled clearable v-model="formData.projectStatus" placeholder="请选择项目状态">
                          <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                </el-select>
                    </el-form-item>
                </el-col>

                <el-col :span="9">

                    <el-form-item class="comright" label="乙方行业税率">
                           <el-input disabled v-model="owerTaxfee"></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="业务经理">
                         <el-input  v-model="formData.projectLeader" disabled></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
           
            
            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="21">
                    <el-form-item style="padding-right:4%" label="经营范围" prop="natureBusiness">
                        <el-input disabled type="textarea" :rows="2" placeholder="请输入经营范围"
                            v-model="formData.natureBusiness">
                        </el-input>
                    </el-form-item>
                </el-col>
            </el-row>

         



            <!-- <div class="footers grid-content">
            <el-footer>
              <el-button type="danger" @click="toReturn2">返回</el-button>
              <el-button type="primary" @click="submitForm3">提交</el-button>
            </el-footer>
          </div> -->
        </el-form>
    </div>
</template>
<script>
import qs from 'qs';
import { list,getcode} from "@/api/project/list";
export default {
   data() {
        return {
            placename:'',
            isokradio:1,
            companyTax:'',//甲方纳税人识别号
            owerTax:'',//乙方纳税人识别号
            owntype:'',//乙方行业类型
            owerTaxfee:'',//乙方税率
            formData: {
                checkContent: "",
                createBy: "",
                createTime: "",
                fileName: "",
                isDeleted: "",
                params: {},
                placeCode: "",
                projectAcceptanceStatus: "",
                projectCheckStatus: "",
                projectCode: "",
                projectContractStatus: "",
                projectDesc: "",
                projectDutypaidStatus: "",
                projectGrossMargin: 0,
                projectGrossProfit: 0,
                projectId: "",
                projectLeader: "",
                projectName: "",
                projectNetProfit: 0,
                projectOwner: "",
                projectPackageAmount: 0,
                projectStatus: 0,
                projectTicketStatus: "",
                projectTimeEnd: "",
                projectTimeStart: "",
                projectTotalAmount: 0,
                projectTrade: "",
                purchCompany: "",
                remark: "",
                searchValue: "",
                updateBy: "",
                updateTime: ""
            },
            baseImgPath: "http://36.133.2.179:8000/api/files/showImg?imgPath=",
                options: [
                {
                    value: 0,
                    label: '进行'
                },
                {

                    value: 1,
                    label: '异常'
                },
                {

                    value: 2,
                    label: '完结',
                },
            ],
            ownoptions: [ ],
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

                contactName: [
                    {
                        required: true,
                        message: "请输入联系人",
                        trigger: "blur",
                    },
                ],
                contactPhone: [
                    {
                        required: true,
                        message: "请输入手机号",
                        trigger: "blur",
                    },
                    {
                        pattern: /^1(3|4|5|7|8|9)\d{9}$/,
                        message: "手机号格式错误",
                        trigger: "blur",
                    },
                ],
                contactDocumentType: [
                    {
                        required: true,
                        message: "请输入证件类型",
                        trigger: "blur",
                    },
                ],
                contactIdNum: [
                    {
                        required: true,
                        message: "请输入证件号码",
                        trigger: "blur",
                    },
                    {
                        pattern:
                            /^[1-9]\d{5}(18|19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/,
                        message: "身份证号格式错误",
                        trigger: "blur",
                    },
                ],

                organizationalForm: [
                    {
                        required: true,
                        message: "请输入组织形式",
                        trigger: "blur",
                    },
                ],
                numberEmployees: [
                    {
                        required: true,
                        message: "请输入从业人数",
                        trigger: "blur",
                    },
                ],
               
                city: [
                    {
                        required: true,
                        message: "请输入城市",
                        trigger: "blur",
                    },
                ],
                county: [
                    {
                        required: true,
                        message: "请输入区（县）",
                        trigger: "blur",
                    },
                ],
                electronicCommerce: [
                    {
                        required: true,
                        message: "请选择是否是电子商务经营者",
                        trigger: "change",
                    },
                ],
                // selfAddress: [
                //     {
                //         required: true,
                //         message: "请输入经营场所",
                //         trigger: "blur",
                //     },
                // ],
                // selfAddress: [
                //     {
                //         required: true,
                //         message: "请输入所属自贸区",
                //         trigger: "blur",
                //     },
                // ],
                // freeTradeZone: [
                //     {
                //         required: true,
                //         message: "请输入所属自贸区",
                //         trigger: "blur",
                //     },
                // ],
                // freeTradeArea: [
                //     {
                //         required: true,
                //         message: "请输入所属自贸片区",
                //         trigger: "blur",
                //     },
                // ],
                // propertyRight: [
                //     {
                //         required: true,
                //         message: "请输入产权",
                //         trigger: "blur",
                //     },
                // ],
                // natureBusiness: [
                //     {
                //         required: true,
                //         message: "请输入经营范围",
                //         trigger: "blur",
                //     },
                // ],
                // industryType: [
                //     {
                //         required: true,
                //         message: "请选择行业类型",
                //         trigger: "change",
                //     },
                // ],
                placeCode: [
                    {
                        required: true,
                        message: "请选择渠道商",
                        trigger: "change",
                    },
                ],
                // placeName: [
                //     {
                //         required: true,
                //         message: "请选择渠道商",
                //         trigger: "change",
                //     },
                // ],
                projectOwner: [
                    {
                        required: true,
                        message: "请输入乙方",
                        trigger: "blur",
                    },
                ],

                purchCompany: [
                    {
                        required: true,
                        message: "请输入甲方",
                        trigger: "blur",
                    },
                ],
                gender: [
                    {
                        required: true,
                        message: "请选择性别",
                        trigger: "change",
                    },
                ],
                dateBirth: [
                    {
                        required: true,
                        message: "选择出生日期",
                        trigger: "change",
                    },
                ],
                nation: [
                    {
                        required: true,
                        message: "请输入民族",
                        trigger: "blur",
                    },
                ],
                eduation: [
                    {
                        required: true,
                        message: "请选择文化程度",
                        trigger: "change",
                    },
                ],
                occupationalStatus: [
                    {
                        required: true,
                        message: "请输入申请前职业状况",
                        trigger: "blur",
                    },
                ],
                politicalStatus: [
                    {
                        required: true,
                        message: "请选择政治面貌",
                        trigger: "change",
                    },
                ],
                residence: [
                    {
                        required: true,
                        message: "请输入经营者居所",
                        trigger: "blur",
                    },
                ],
                mail: [
                    {
                        required: true,
                        message: "请输入电子邮箱地址",
                        trigger: "blur",
                    },
                    {
                        pattern: /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
                        message: "邮箱地址格式错误",
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
    },
   
    mounted() {
       this.gettoday();
       this.getcode();
      
      
    },


    methods: {
        repair(i){
        if (i >= 0 && i <= 9) {
        return "0" + i;
        } else {
          return i;
        }
        },
        gettoday(){
           var date = new Date();//当前时间
           var year = date.getFullYear() //年
           var month = this.repair(date.getMonth() + 1);//月
           var day = this.repair(date.getDate());//日
          //当前时间 
           var curTime = year + "-" + month + "-" + day
           this.formData.createTime=curTime;
  
        },
        getcode(){
           getcode().then((res)=>{
                    this.formData.projectCode=res;
           }).catch((errore)=>{
            console.log(errore);
           })
        },
        handleChange(val) {
            console.log(val);
        },
         submitForm() {
            this.$refs["elForm"].validate((valid) => {
                // TODO 提交表单
                if (valid) {
                    let parms = {
                        selfId: this.formData.selfId,
                        infoStatus: 1,
                    };
                    updateEmployed.addReview(parms).then((res) => {
                        if (res != undefined) {
                            if (res != undefined) {
                                if (res.code === 200) {
                                    this.$modal.msgSuccess("信息审核通过成功!");
                                    this.$nextTick(function () {
                                        this.$router.push("employed");
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
