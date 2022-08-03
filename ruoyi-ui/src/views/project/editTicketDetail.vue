<template>
    <div>
        <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="140px">


            <el-row type="flex" class="row-bg rowCss" style="padding-top:20px" justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="项目编号">
                        <el-input v-model="Father.projectCode" disabled></el-input>
                    </el-form-item>

                    <el-form-item class="comright" label="项目名称" :required="true">
                        <el-input disabled v-model="Father.projectName"></el-input>
                    </el-form-item>
                </el-col>

                <el-col :span="9">

                    <el-form-item class="comright" label="项目时间" :required="true">
                        <el-input v-model="Father.projectTimeStart" disabled></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="项目金额" :required="true">
                        <el-input disabled type="number" style="width:100%" v-model="Father.projectTotalAmount"
                            :step="0.01" :min="0">
                            <template slot="append">元</template>
                        </el-input>
                    </el-form-item>
                </el-col>
            </el-row>


            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">


                    <el-form-item class="comright" label="甲方" :required="true">
                        <!-- <el-select  clearable v-model="formData.purchCompany">
                          <el-option v-for="item in purchCompanyOptions" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                        </el-select> -->
                        <el-input disabled v-model="Father.purchCompany"></el-input>
                    </el-form-item>

                </el-col>

                <el-col :span="9">
                    <el-form-item class="comright" label="甲方纳税人识别号" :required="true">
                        <el-input disabled v-model="Father.purchCompanyTaxid"></el-input>
                    </el-form-item>

                </el-col>
            </el-row>

            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="乙方" :required="true">
                        <el-input v-model="Father.selfName" disabled></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="已开金额" :required="true">
                        <el-input disabled type="number" style="width:100%" v-model="Father.projectPackageAmount"
                            :step="0.01" :min="0">
                            <template slot="append">元</template>
                        </el-input>

                    </el-form-item>
                    <el-form-item class="comright" label="发票类型" prop="ticketType">
                        <el-select style="width:100%" clearable v-model="formData.ticketType" @change="tickettaxvip">
                            <el-option v-for="item in ticketTypeoptions" :key="item.value" :label="item.label"
                                :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item class="comright" label="开票内容类型">
                        <el-radio disabled v-model="fileNameradio" label="1" @change="filenamer">手动输入</el-radio>
                        <el-radio disabled v-model="fileNameradio" label="2" @change="filenamer">上传附件 </el-radio>
                    </el-form-item>
                    <el-form-item class="comright" label="发票种类编号" prop="ticketTypeCode">
                        <el-input v-model="formData.ticketTypeCode"></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="发票时间" :required="true">
                        <el-input v-model="formData.ticketTime" disabled></el-input>
                    </el-form-item>


                </el-col>

                <el-col :span="9">

                    <el-form-item class="comright" label="乙方纳税人识别号" :required="true">
                        <el-input disabled v-model="owerTax"></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="剩余金额" :required="true">
                        <el-input disabled type="number" style="width:100%" v-model="Father.projectRemainAmount"
                            :step="0.01" :min="0">
                            <template slot="append">元</template>
                        </el-input>
                    </el-form-item>
                    <el-form-item v-if="tickettaxvipok" label="发票税率" prop="ticketTax">
                        <el-input style="width:86%" v-model="formData.ticketTax" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item v-else class="comright" label="发票税率" prop="ticketTax">
                        <el-select style="width:100%" clearable v-model="formData.ticketTax">
                            <el-option v-for="item in ticketNormal" :key="item.value" :label="item.label"
                                :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item class="comright" label="开票内容" v-if="fileNameradio == 1">
                        <el-input type="textarea" disabled :rows="2" v-model="Father.fileName">
                        </el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="开票内容附件" v-if="fileNameradio == 2">

                        <uploadSmall @getfileName="getfileNameS" :fileName="isNone" :fileNameOld="fileNames"
                            :isDetail="isDetail"></uploadSmall>

                    </el-form-item>


                    <el-form-item class="comright" label="发票编号" prop="ticketCode">
                        <el-input v-model="formData.ticketCode"></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="发票金额" prop="ticketAmount">
                        <el-input disabled  type="number" style="width:100%"
                            v-model="formData.ticketAmount" :step="0.01" :min="0">
                            <template slot="append">元</template>
                        </el-input>
                    </el-form-item>


                </el-col>
            </el-row>
            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="21">
                    <el-form-item style="padding-right:4.2%" label="发票备注">
                        <el-input type="textarea" :rows="2" placeholder="请输入发票备注" v-model="formData.ticketRemark">
                        </el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="发票影像" prop="fileName">
                        <uploadSmall v-if="fileName.length > 0" @getfileName="getfileNameSS" :fileName="formData.fileName"
                            :fileNameOld="fileName" :isDetail="isDetails"></uploadSmall>
                    </el-form-item>
                </el-col>
                <el-col :span="9">
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
import uploadSmall from '@/components/douploads/uploadSmall'
import crudRate from '@/api/company/rate'
import { list2, edit } from "@/api/project/ticket";
import { detail, getcode, getinfoByUserId, ownlist } from "@/api/project/list";
import arrss from "@/api/project/list";
import { getInfo } from '@/api/login'
import { Decimal } from 'decimal.js'
export default {
    components: {
        uploadSmall
    },
    data() {
        return {
            ticketAmount: 0,
            fileNames: '',
            isDetails: '0',
            isDetail: '1',
            isNone: [],

            issuedAmount: 0.00, //已开金额
            balance: 0.00,  //剩余金额
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

            Father: [],
            formData: {
                projectCode: this.$cache.local.getJSON("publicTickets").projectCode,//项目编号
                ticketRemark: '',//发票备注
                ticketTax: 3,//发票税率
                ticketType: 0,  //发票类型
                ticketCode: '',//发票种类编号
                ticketTypeCode: '',//发票编号
                fileName: '',//开票内容
                ticketAmount: '',//发票金额
                ticketTime: '',  //发票时间
                isDeleted: 1,
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
                ticketAmount: [
                    {
                        required: true,
                        message: "请输入发票金额",
                        trigger: "change",
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
                        message: "发票影像不能为空",

                    },
                ],
                projectDesc: [
                    {
                        required: true,
                        message: "请输入发票描述",
                        trigger: "blur",
                    },
                ],
                ticketCode: [
                    {
                        required: true,
                        message: "请输入发票编号",
                        trigger: "blur",
                    },
                ],
                ticketTypeCode: [
                    {
                        required: true,
                        message: "请输入发票种类编号",
                        trigger: "blur",
                    },
                ],

            },

        };
    },
    watch: {
        'Father.industryType': 'selectIndustryType',
    },
    mounted() {
        this.getlist();
        this.getinfoByUserId();
        
        this.gettoday();
        this.getRate();
        this.formData = this.$cache.local.getJSON("ticketDetails");
        this.formData.fileName = JSON.parse(this.formData.fileName);
        this.fileName=[];
        let arr = this.formData.fileName;
        for (let i in arr) {
            this.fileName.push({
                name: arr[i],
                url: this.baseImgPath + arr[i]
            })
        }
    },


    methods: {
        //监听行业类型
        selectIndustryType() {
            var rate = this.industryTypeList.find((item) => item.industryId == this.Father.industryType);
            if (rate) {
                this.industryId = rate.industryId;  //行业类型id
                this.owerTaxfee = rate.taxRate;
                let industryType = rate.industryId;
                ownlist({ username: this.username, industryType: industryType }).then(res => {
                    this.ownoptions = res;

                    for (let i in this.ownoptions) {
                        if (this.ownoptions[i].selfName == this.Father.selfName) {
                            this.natureBusiness = this.ownoptions[i].natureBusiness;
                            this.owerTax = this.ownoptions[i].taxId;
                        }
                    }
                }).catch(err => {
                    console.log(err);
                });
            } else {
                this.getRate();


            }


        },
        getfileNameS() {

        },
        getfileNameSS(data) {
            this.formData.fileName = data;
            console.log(this.formData.fileName);
        },
        //计算已开和剩余金额
        ticketByCode() {
            list2({
                projectCode: this.Father.projectCode
            }).then(res => {
                let arr = res;
                if (Array.isArray(arr) && arr.length > 0) {
                    this.Father.projectPackageAmount = 0;
                    for (let i in arr) {
                        if (arr[i].ticketAmount > 0 && arr[i].isDeleted==1) {
                            this.Father.projectPackageAmount = new Decimal(this.Father.projectPackageAmount).add(new Decimal(arr[i].ticketAmount));
                        }
                    }
                    //如果存在发票 累计发票 加上发票金额 
                   this.Father.projectRemainAmount = new Decimal(this.Father.projectTotalAmount).sub(new Decimal(this.Father.projectPackageAmount));
                } else {
                    this.Father.projectPackageAmount = this.formData.ticketAmount;
                    this.Father.projectRemainAmount = new Decimal(this.Father.projectTotalAmount).sub(new Decimal(this.Father.projectPackageAmount));
                }
            }).catch(err => {

            });
        },
        getlist() {
            detail({
                projectCode: this.$cache.local.getJSON("publicTickets").projectCode
            }).then((response) => {
                this.Father = response.data[0];
                this.ticketByCode();
                if (this.Father.fileName) {
                    if (this.Father.fileName.indexOf("[") != -1) {
                        this.Father.fileName = JSON.parse(this.Father.fileName);
                    }
                    if (Array.isArray(this.Father.fileName)) {
                        this.fileNameradio = '2';
                        this.fileNames = [];
                        //如果是图片的话
                        for (let j in this.Father.fileName) {
                            this.fileNames.push({
                                url: this.baseImgPath + this.Father.fileName[j],
                                name: this.Father.fileName[j]

                            })
                        }

                    } else {
                        this.fileNameradio = '1';
                    }

                } else {
                    this.fileNameradio = '1';
                }
                if (this.formData.isActive) {
                    this.projectStatus = parseInt(this.formData.isActive);
                } else {
                    this.projectStatus = 1;
                }
            });

        },
        filenamer() {

        },

        //监听乙方
        ownnew(e) {
            console.log(e);
            for (let i in this.ownoptions) {
                if (this.ownoptions[i].selfCode == e) {
                    console.log(this.ownoptions[i].isActive);
                    if (this.ownoptions[i].isActive) {
                        this.projectStatus = parseInt(this.ownoptions[i].isActive);
                    } else {
                        this.projectStatus = 1;
                    }
                    this.formData.selfName = this.ownoptions[i].selfName;
                    this.natureBusiness = this.ownoptions[i].natureBusiness;
                    this.owerTax = this.ownoptions[i].taxId;


                }
            }
        },

        getRate() {
            crudRate.getAllRate().then(res => {
                let tree = []; // 用来保存树状的数据形式
                this.parseTree(res.rows, tree, 0);
                this.industryTypes = tree;
                this.industryTypeList = res.rows;
                var rate = this.industryTypeList.find((item) => item.industryId == this.Father.industryType);
                //   this.industryId = rate.industryId;  //行业类型id
                //   this.owerTaxfee = rate.taxRate;
                let industryType = rate.industryId;

                ownlist({ username: this.username, industryType: industryType }).then(res => {
                    this.ownoptions = res;
                    for (let i in this.ownoptions) {

                        if (this.ownoptions[i].selfName == this.Father.selfName) {
                            this.natureBusiness = this.ownoptions[i].natureBusiness;
                            this.owerTax = this.ownoptions[i].taxId;
                        }
                    }
                }).catch(err => {
                    console.log(err);
                });


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
            this.$tab.closeOpenPage({ path: '/project/ticketlist' });
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



        //监听开票内容类型
        tickettaxvip(e) {
            // this.formData.ticketType=e;
            if (e > 0) {

                this.tickettaxvipok = true;
                // this.formData.ticketTax = 3;
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

            this.formData.ticketTime = curTime;

        },
        getcode(selfCode) {
            getcode({ selfCode: selfCode }).then((res) => {
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
                    if(this.$cache.local.getJSON("iscxxiu")==1){
                     this.formData.projectTicketStatus=0;
                    }
                     this.formData.fileName = JSON.stringify(this.formData.fileName);
                    //如果是附件的话
                    arrss.edit(this.Father);
                    
                    edit(this.formData).then((res) => {
                        if (res != undefined) {
                                if (res.code === 200) {
                                    this.$nextTick(function () {
                                        this.$tab.refreshPage("/project/ticketlist").then(() => {
                                             let obj = {
                                            title: '票据列表',
                                            backUrl: '/project/ticketlist',
                                            resmsg: '票据修改完成'
                                            };
                                        this.$cache.local.setJSON('successNew', obj);
                                        this.$tab.closeOpenPage({ path: "/company/customer/successNew" });
                                        });
                                       
                                    });
                                } else {
                                    this.$modal.msgError(res.msg);
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
</style>
