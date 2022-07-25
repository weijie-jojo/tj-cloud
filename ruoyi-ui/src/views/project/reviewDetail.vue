<template>
    <div>
        <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="140px">

           
            <el-row type="flex" class="row-bg rowCss combottom" style="margin-bottom:20px" justify="space-around">
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
                    <el-form-item class="comright" label="渠道商" prop="">
                        <el-input  v-model="placename" disabled></el-input>
                    </el-form-item>

                    <el-form-item class="comright" label="甲方" prop="purchCompany">
                        <!-- <el-select  clearable v-model="formData.purchCompany">
                          <el-option v-for="item in purchCompanyOptions" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                        </el-select> -->
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
                        <el-select style="width:100%" clearable v-model="formData.projectOwner">
                            <el-option v-for="item in ownoptions" :key="item.value" :label="item.label"
                                :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item class="comright" label="乙方状态" prop="projectStatus">
                        <el-select style="width:100%" disabled clearable v-model="formData.projectStatus" placeholder="请选择项目状态">
                            <el-option v-for="item in options" :key="item.value" :label="item.label"
                                :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>

                <el-col :span="9">

                    <el-form-item class="comright" label="乙方纳税人识别号">
                        <el-input disabled v-model="owerTax"></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="业务经理">
                        <el-input v-model="formData.projectLeader" disabled></el-input>
                    </el-form-item>
                </el-col>
            </el-row>


            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="乙方行业类型">
                        <el-select style="width:100%" disabled clearable v-model="owntype">
                            <el-option v-for="item in ownindustry" :key="item.value" :label="item.label"
                                :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item class="comright" label="发票类型" prop="ticketType">
                        <el-select style="width:100%" clearable v-model="formData.ticketType" @change="tickettaxvip">
                            <el-option v-for="item in ticketTypeoptions" :key="item.value" :label="item.label"
                                :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>

                <el-col :span="9">
                    <el-form-item class="comright" label="乙方行业税率">
                        <el-input disabled v-model="owerTaxfee"></el-input>
                    </el-form-item>
                    <el-form-item v-if="tickettaxvipok" label="发票税率" prop="ticketTax">
                        <el-input v-model="formData.ticketTax" disabled></el-input>
                    </el-form-item>
                    <el-form-item v-else class="comright" label="发票税率" prop="ticketTax">


                        <el-select style="width:100%" clearable v-model="formData.ticketTax">
                            <el-option v-for="item in ticketNormal" :key="item.value" :label="item.label"
                                :value="item.value">
                            </el-option>
                        </el-select>

                    </el-form-item>
                </el-col>
            </el-row>


            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="开票内容类型">
                        <el-radio v-model="fileNameradio" label="1">手动输入</el-radio>
                        <el-radio v-model="fileNameradio" label="2">上传附件 </el-radio>
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
                <el-col :span="21">
                    <el-form-item style="padding-right:4%" label="乙方经营范围">
                        <el-input disabled type="textarea" :rows="2" placeholder="请输入乙方经营范围" v-model="natureBusiness">
                        </el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="21">
                    <el-form-item style="padding-right:4%" label="发票备注" prop="ticketRemark">
                        <el-input type="textarea" :rows="2" placeholder="请输入发票备注" v-model="formData.ticketRemark">
                        </el-input>
                    </el-form-item>
                </el-col>
            </el-row>
          
          
          
          <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright">
            <el-radio v-model="isokradio" label="1"> 通过</el-radio>
            <el-radio v-model="isokradio" label="2">驳回 </el-radio>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="驳回理由">
            <el-input v-model="remark" :disabled="isokradio == 1"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="8"></el-col>
        <el-col :span='8' class="flexs">
          <el-button type="danger" @click="resetForm">返回</el-button>
          <el-button v-if="isokradio == 2" type="primary" @click="submitForm(2)">驳回</el-button>
          <el-button v-else type="primary" @click="submitForm(1)">通过</el-button>
        </el-col>
        <el-col :span="8"></el-col>
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
import { list, getcode,getinfoByUserId } from "@/api/project/list";
import {getInfo} from '@/api/login' 
export default {
    data() {
        return {
            fileName: [],
            dialogVisible1: false,
            dialogImageUrl1: "",
            fileNameradio: '1', //开票类型
            natureBusiness: '',//经营范围
            tickettaxvipok: false,
            placename: '',
            isokradio: '1',
            companyTax: '',//甲方纳税人识别号
            owerTax: '',//乙方纳税人识别号
            owntype: '',//乙方行业类型
            owerTaxfee: '',//乙方税率

            formData: {
                // fileName:'',//开票内容
                ticketTax: '',//发票税率
                ticketType: '',  //发票类型
                checkContent: "",
                createBy: "",
                createTime: "",
                fileName: [],
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
            baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
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
                ticketTax: [
                    {
                        required: true,
                        message: "发票税率不能为空",
                        trigger: "change",
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
                fileName: [
                    {
                        required: true,
                        message: "开票内容不能为空",

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
        this.formData= this.$cache.local.getJSON("projectListInfo");
        // this.gettoday();
        // this.getcode();
        this.getinfoByUserId(); //渠道商


    },


    methods: {
        resetForm(){
          this.$router.back();
        },

        handlesuccess1(file, fileList) {
            this.formData.fileName.push(file.obj);
        },
        handleRemove1(file, fileList) {
            const i = this.formData.fileName.findIndex((item) => item === fileList);
            this.formBank.fileName.splice(i, 1);
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
        //渠道商接口
        getinfoByUserId() {
          getInfo().then(res=>{  
           getinfoByUserId({userId:res.user.userId}).then(res=>{
               this.placename=res.data;
              })
             })
       },

    getRate(){
      crudRate.getAllRate().then(res=>{
          console.log("getAllRate",res.rows);
          // this.industryTypes=res.rows;
          let tree = []; // 用来保存树状的数据形式
          this.parseTree(res.rows, tree, 0);
          console.log("tree",tree);
          this.industryTypes=tree;
          this.industryTypeList=res.rows;
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
            //当前时间 
            var curTime = year + "-" + month + "-" + day
            this.formData.createTime = curTime;

        },
        getcode() {
            getcode().then((res) => {
                this.formData.projectCode = res;
            }).catch((errore) => {
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
                    this.formData.fileName = JSON.stringify(this.formData.fileName);

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
