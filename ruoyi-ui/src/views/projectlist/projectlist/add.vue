<template>
    <div>
        <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="140px">


            <el-row type="flex" class="row-bg rowCss combottom" style="padding-top:20px;margin-bottom: 0px;" justify="space-around">
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
                        <el-input type="number" style="width:100%" v-model="formData.projectTotalAmount" :step="0.01"
                            :min="0" oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'>
                            <template slot="append">元</template>
                        </el-input>
                    </el-form-item>
                </el-col>
            </el-row>


            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="客户全名">
                        <el-select filterable @change="placeNew" style="width:100%" clearable v-model="formData.placeCode">
                            <el-option v-for="item in placeCodeOptions" :key="item.placeCode"
                                :label="item.placeAliasName" :value="item.placeCode">
                            </el-option>
                        </el-select>
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
                    <el-form-item class="comright" label="客户状态" prop="isokradio">
                        <el-input disabled v-if="isokradio == 0" value="正常"></el-input>
                        <el-input disabled v-if="isokradio == 1" value="欠费"></el-input>
                        <el-input disabled v-if="isokradio == 2" value="冻结"></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="甲方纳税人识别号" prop="purchCompanyTaxid">
                        <el-input v-model.trim="inputValCompute"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="乙方行业类型" prop="industryType">
                       <el-tooltip class="item" effect="dark" :content="selectTipType" placement="top-start">
                       <el-select class="main-select-tree" ref="selectTree" v-model="formData.industryType"
                            style="width: 100%;">
                            <el-option v-for="item in formatData(industryTypes)" :key="item.value" :label="item.label"
                                :value="item.value"  style="display: none;" />

                            <el-tree class="main-select-el-tree" ref="selecteltree" :data="industryTypes" node-key="id"
                                highlight-current :props="defaultProps" @node-click="handleNodeClick"
                                :current-node-key="formData.industryType" :expand-on-click-node="expandOnClickNode">
                                <span class="custom-tree-node" slot-scope="{ node, data  }" style="width:100%">
                                    <span style="float: left">{{ node.label }}</span>
                                    <span style="float: right; color: #8492a6; font-size: 14px;padding-right:10px">{{
                                            data.taxRates
                                    }}</span>
                                </span>

                            </el-tree>
                        </el-select>
                        </el-tooltip>


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
                        <el-input style="width:86%" v-model="formData.ticketTax" :required="true">
                        <template slot="append">
                                 %
                        </template>
                        </el-input>
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
                    <el-form-item class="comright" label="乙方" prop="projectOwner">
                        <el-select filterable @change="ownnew" style="width:100%" clearable v-model="formData.projectOwner">
                            <el-option v-for="item in ownoptions" :key="item.selfId" :label="item.selfName"
                                :value="item.selfCode">
                            </el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item class="comright" label="乙方状态">
                        <el-select style="width:100%" disabled clearable v-model="projectStatus" placeholder="请选择项目状态">
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
                    <el-form-item class="comright" label="开票内容类型">
                        <el-radio v-model="fileNameradio" label="1" @change="filenamer">手动输入</el-radio>
                        <el-radio v-model="fileNameradio" label="2" @change="filenamer">上传附件 </el-radio>
                    </el-form-item>



                </el-col>

                <el-col :span="9">
                    <el-form-item class="comright" label="开票内容" prop="fileName" v-if="fileNameradio == 1">

                        <el-input type="textarea" :rows="2" v-model="formData.fileName"
                        maxlength="50"
                        show-word-limit
                        >
                        </el-input>
                    </el-form-item>

                </el-col>
            </el-row>
            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="开票内容附件" prop="fileName" v-if="fileNameradio == 2">
                        <uploadSmall @getfileName="getfileNameS" :fileName="isNone" :fileNameOld="isNone"
                            :isDetail="isDetail"></uploadSmall>
                    </el-form-item>
                </el-col>
                <el-col :span="9"></el-col>
            </el-row>


            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="21">
                    <el-form-item style="padding-right:4%" label="项目行业类型" prop="projectTrade">
                        <el-input disabled v-model="formData.projectTrade"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="21">
                    <el-form-item style="padding-right:4%" label="乙方经营范围">
                        <el-input 
                         
                        disabled type="textarea"
                        
                        :rows="2" placeholder="请输入乙方经营范围" v-model="natureBusiness">
                        </el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="21">
                    <el-form-item style="padding-right:4%" label="发票备注" prop="projectDesc">
                        <el-input 
                         maxlength="50"
                         show-word-limit
                        type="textarea" :rows="2" placeholder="请输入发票备注" v-model="formData.projectDesc">
                        </el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="8"></el-col>
                <el-col :span='8' class="flexs">
                    <el-button type="danger" @click="resetForm">关闭</el-button>
                    <el-button type="primary" @click="onSubmit">提交</el-button>
                </el-col>
                <el-col :span="8"></el-col>
            </el-row>
        </el-form>

    </div>
</template>
<script>
import uploadSmall from '@/components/douploads/uploadSmall'
import crudRate from '@/api/project/rate'
import { list, getcode, getinfoByUserId, add, ownlist, check } from "@/api/project/list";
import { getInfo } from '@/api/login'
import { Decimal } from 'decimal.js'


//手机号验证
var phoneVerify = (rule, value, callback) => {
    if (value) {
        var reg = /^[A-Z0-9]{15}$|^[A-Z0-9]{18}$|^[A-Z0-9]{20}$/;
        if (!reg.test(value)) {
            callback(new Error('甲方纳税人识别号,一律由15位、18或者20位码(字符型))组成'));
        }
        callback();
    }
};


export default {
    name:'Add',
    components: {
        uploadSmall
    },
    data() {
        return {
            selectTipType:'',
            baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
            isDetail: '0',
            isNone: [],
            defaultProps: {
                children: 'children',
                label: 'label',
                taxRates: 'taxRates'
            },
            userinfo: {},
            expandOnClickNode: true,
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
            placeCodeOptions: '',//渠道商
            formData: {
                projectOwnerTaxid: '',
                projectDesc: '',//开票描述
                purchCompanyTaxid: '',//甲方纳税人识别号
                ticketTax: '',//发票税率
                ticketType: '',  //发票类型  0 普通 1 专用
                projectRemainAmount: '', //项目可以用金额

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
                projectPackageAmount: 0, ////已使用金额
                projectStatus: 0,
                projectTicketStatus: 0,
                // projectTimeEnd: "",
                projectTimeStart: "",
                projectTotalAmount: '0.00',
                projectTrade: "",

                purchCompany: "",
                remark: "",
                industryType: '',
                selfName: '',
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
                    // { validator: projectTotalAmounts, trigger: 'blur' }
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
                projectTrade: [

                    {
                        required: true,
                        message: "行业类型不能为空",

                    },
                ],
                placeCode: [
                    {
                        required: true,
                        message: "请选择全名",
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
                    { validator: phoneVerify, trigger: 'blur' }
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
    computed: {
        inputValCompute: {
            get() {
                return this.formData.purchCompanyTaxid;
            },
            set(val) {
                this.formData.purchCompanyTaxid = val.toUpperCase();
            },
        },
    },
    watch: {
        'formData.industryType': 'selectIndustryType',

    },
    mounted() {
        this.gettoday();
        this.getRate();
        this.getinfoByUserId(); //渠道商
    },


    methods: {
        getfileNameS(data) {
            this.formData.fileName = data;
        },
        handleNodeClick(node) {
            this.formData.industryType = node.id;
            this.$refs.selectTree.blur();
            this.$nextTick(function(){
             this.selectTipType=this.$refs.selectTree.selected.label; 
           });
        },

        // 四级菜单
       formatData(data) {
      let options = [];
      data.forEach((item, key) => {
        options.push({ label: item.label, value: item.id,taxRates:item.taxRates });
        if (item.children) {
          item.children.forEach((items, keys) => {
            options.push({ label: item.label+'-'+items.label, value: items.id,taxRates:items.taxRates });
            if (items.children) {
              items.children.forEach((itemss, keyss) => {
                options.push({ label: item.label+'-'+items.label+'-'+itemss.label, value: itemss.id,taxRates:itemss.taxRates });
                if (itemss.children) {
                  itemss.children.forEach((itemsss, keysss) => {
                    options.push({ label:item.label+'-'+items.label+'-'+itemss.label+'-'+itemsss.label, value: itemsss.id,taxRates:itemsss.taxRates });
                  });
                }
              });
            }
          });
        }
      });
      return options;
    },


        //监听开票内容选择
        filenamer(e) {
            if (e == 1) {
                this.formData.fileName = '';
            }
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
                    this.getcode(this.ownoptions[i].selfCode);

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
            this.$tab.closeOpenPage({ path: '/projectList/list' });
        },



        //渠道商接口  记得修改 userid
        getinfoByUserId() {
            getInfo().then(res => {
                this.userinfo = res.user;
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
                    let a = industry[i].taxRate;
                    let b = null;
                    if (a) {
                        b = new Decimal(a).mul(new Decimal(100));
                        b = "税率" + b + '%';
                    } else {
                        b = null;
                    }
                    var obj = {
                        id: industry[i].industryId,
                        label: industry[i].industryName,

                        taxRates: b,
                        children: [],

                    };
                    tree.push(obj);
                    this.parseTree(industry, obj.children, obj.id);
                }
            }
        },
        //监听乙方行业类型
        selectIndustryType() {
            this.formData.selfName = '';
            this.natureBusiness = '';
            this.owerTax = '';
            this.formData.projectCode = '';
            this.formData.projectOwner = '';

            var rate = this.industryTypeList.find((item) => item.industryId == this.formData.industryType);
            this.industryId = rate.industryId;  //行业类型id
            if (rate.taxRate) {
                this.owerTaxfee = new Decimal(rate.taxRate).mul(new Decimal(100)) + '%';
            } else {
                this.owerTaxfee = '';
            }

            let industryType = rate.industryId;
            this.$nextTick(function(){
             this.formData.projectTrade=this.$refs.selectTree.selected.label;
            });
           // this.formData.projectTrade = rate.industryName
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
            getcode({ selfCode: selfCode }).then((res) => {
                this.formData.projectCode = res;
            }).catch((errore) => {
                console.log(errore);
            })
        },
        handleChange(val) {
            console.log(val);
        },
        check(resmsg) {
            let parms = {
                "checkReasult": resmsg,
                "checkUser": this.userinfo.userName,
                'phonenumber': this.userinfo.phonenumber,
                "projectCode": this.formData.projectCode,
                "projectType": "6",
            };
            check(parms).then(res => {
                console.log('添加项目成功！');
            }).catch(error => {

            });


        },
        onSubmit() {
            if (this.formData.projectTotalAmount < 1) {
                 this.$alert('项目金额必须大于1', '系统提示', {
                            confirmButtonText: '确定',
                          
                            type: 'error'
                 });
               return;
            }
            this.$refs["elForm"].validate((valid) => {
                // TODO 提交表单
                if (valid) {
                    //如果是附件的话
                    if (this.fileNameradio == 2) {
                       // this.formData.fileName = this.fileNamefile;
                        this.formData.fileName = JSON.stringify(this.formData.fileName);
                    }
                    this.formData.projectOwnerTaxid = this.owerTax;
                    this.formData.projectRemainAmount = this.formData.projectTotalAmount;//新增可以用金额为总金额
                    this.formData.projectPackageAmount = 0;  //已用金额为0


                    add(this.formData).then((res) => {

                        if (res != undefined) {
                            if (res.code === 200) {
                                this.$nextTick(function () {
                                  let resmsg = '项目填写完成';
                                  this.check('项目填写完成');
                                  let obj = {
                                            title: '项目进度',
                                            backUrl: '/projectList/list',
                                            resmsg: resmsg,
                                            name:"List",
                                        };
                                        this.$cache.local.setJSON('successProject', obj);
                                        this.$tab.closeOpenPage({ path: "/projectlist/success" });
                                     });
                            } else {
                                this.$modal.msgError(res.msg);
                            }
                        }

                    });
                } else {
                      this.$alert('请正确填写', '系统提示', {
                            confirmButtonText: '确定',
                         
                            type: 'warning'
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
