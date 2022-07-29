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
                        <el-input disabled type="number" style="width:100%" v-model="Father.projectPackageAmount" :step="0.01"
                            :min="0">
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
                        <el-input disabled type="number" style="width:100%" v-model="Father.projectRemainAmount" :step="0.01" :min="0">
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

                        <div v-for="(item, index) in previewList" :key="index">
                            <el-image lazy :preview-src-list="previewList" style="width: 150px; height: 150px"
                                :src="item" alt="" />
                        </div>
                        <div v-for="(x, y) in pdfList" :key="y">
                            <span @click="pdfdetail(x)">
                                {{ x }}
                            </span>
                        </div>

                    </el-form-item>


                    <el-form-item class="comright" label="发票编号" prop="ticketCode">
                        <el-input v-model="formData.ticketCode"></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="发票金额" prop="ticketAmount">
                        <el-input @change="ticketAsee" type="number" style="width:100%" v-model="formData.ticketAmount"
                            :step="0.01" :min="0">
                            <template slot="append">元</template>
                        </el-input>
                    </el-form-item>


                </el-col>
            </el-row>
            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="21">
                    <el-form-item style="padding-right:4%" label="发票备注">
                        <el-input type="textarea" :rows="2" placeholder="请输入发票备注" v-model="formData.ticketRemark">
                        </el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="发票影像" prop="fileName">

                        <el-upload class="upload-demo" action="/eladmin/api/files/doUpload" :on-success="handlesuccess1"
                            :on-preview="handlePreview1" :on-remove="handleRemove1" :before-remove="beforeRemove1"
                            multiple :limit="9" :on-exceed="handleExceed1" :file-list="fileName" list-type="picture"
                            :before-upload="beforeAvatarUpload">
                            <el-button size="small" type="primary">点击上传</el-button>
                            <div slot="tip" class="el-upload__tip" style="color:red">仅支持jpg/png/jpeg/pdf文件，且不超过10M</div>
                        </el-upload>
                        <el-dialog :visible.sync="dialogVisible1" append-to-body>
                            <img width="100%" :src="dialogImageUrl1" alt="" />
                        </el-dialog>
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
        <!--PDF 预览-->
        <el-dialog :title="titles" :visible.sync="viewVisible" width="80%" center @close='closeDialog'>

            <div>
                <div class="tools flexs" style=" align-items: center;">
                    <div class="page" style="margin-right:20px;font-size: 20px;">共{{ pageNum }}/{{ pageTotalNum }}
                    </div>
                    <el-button :theme="'default'" type="submit" @click.stop="prePage" class="mr10"> 上一页</el-button>
                    <el-button :theme="'default'" type="submit" @click.stop="nextPage" class="mr10"> 下一页</el-button>
                    <el-button :theme="'default'" type="submit" @click.stop="clock" class="mr10"> 顺时针</el-button>
                    <el-button :theme="'default'" type="submit" @click.stop="counterClock" class="mr10"> 逆时针</el-button>

                </div>
                <pdf ref="pdf" :src="url" :page="pageNum" :rotate="pageRotate" @progress="loadedRatio = $event"
                    @page-loaded="pageLoaded($event)" @num-pages="pageTotalNum = $event" @error="pdfError($event)"
                    @link-clicked="page = $event">
                </pdf>

            </div>
        </el-dialog>
    </div>
</template>
<script>
import pdf from 'vue-pdf-signature'
import CMapReaderFactory from 'vue-pdf/src/CMapReaderFactory.js'
import crudRate from '@/api/company/rate'
import { list2, add } from "@/api/project/ticket";
import { detail, getcode, getinfoByUserId, ownlist,edit } from "@/api/project/list";

import { getInfo } from '@/api/login'
import { Decimal } from 'decimal.js'
export default {
    components: {
        pdf
    },
    data() {
        return {
            fileNames: '',
            titles: '',
            pdfList: [],  //pdf 预览
            previewList: [], //预览
            //pdf预览
            url: '',
            viewVisible: false,
            pageNum: 1,
            pageTotalNum: 1,
            pageRotate: 0,
            // 加载进度
            loadedRatio: 0,
            curPageNum: 0,
            closeDialog: false,


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
                        message: "请输入发票金额额",
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
        // //监听行业类型

        beforeAvatarUpload(file) {

            const isLt2M = file.size / 1024 / 1024 < 5;
            const fileSuffix = file.name.substring(file.name.lastIndexOf(".") + 1);
            const whiteList = ["jpg", "png", 'pdf', 'jpeg'];
            if (whiteList.indexOf(fileSuffix) === -1) {
                this.$message.error('上传文件只能是 jpg,png,jpeg,pdf格式');
                return false;
            }
            if (!isLt2M) {
                this.$message.error('上传文件大小不能超过 10MB!');
                return false;
            }
            return fileSuffix & isLt2M;

        },
        pdfdetail(i) {
            this.titles = '正在预览' + i;
            this.viewVisible = true;
            this.url = pdf.createLoadingTask({ url:this.baseImgPath + i,CMapReaderFactory,cMapPacked: true });
        },
        // 上一页函数，
        prePage() {
            var page = this.pageNum
            page = page > 1 ? page - 1 : this.pageTotalNum
            this.pageNum = page
        },
        // 下一页函数
        nextPage() {
            var page = this.pageNum
            page = page < this.pageTotalNum ? page + 1 : 1
            this.pageNum = page
        },
        // 页面顺时针翻转90度。
        clock() {
            this.pageRotate += 90
        },
        // 页面逆时针翻转90度。
        counterClock() {
            this.pageRotate -= 90
        },
        // 页面加载回调函数，其中e为当前页数
        pageLoaded(e) {
            this.curPageNum = e
        },
        // 其他的一些回调函数。
        pdfError(error) {
            console.error(error)
        },
        ticketAsee(e) {
            if (e > this.Father.projectRemainAmount) {
                this.$modal.msgError('发票金额不能大于剩余金额');
                this.formData.ticketAmount = 0;
            }else{
               this.ticketByCode();
            }
            

        },
       
          //计算已开和剩余金额
        ticketByCode() {
           
              list2({
                projectCode: this.Father.projectCode
            }).then(res => {
                let arr = res;
                    // this.Father.projectPackageAmount = 0;

                 if(Array.isArray(arr) && arr.length>0){
                    this.Father.projectPackageAmount = 0;
                   for (let i in arr) {
                    if (arr[i].ticketAmount > 0) {
                        this.Father.projectPackageAmount=new Decimal(this.Father.projectPackageAmount).add(new Decimal(arr[i].ticketAmount));
                    }
                  }
                  //如果存在发票 累计发票 加上发票金额 
                   this.Father.projectPackageAmount=new Decimal(this.Father.projectPackageAmount).add(new Decimal(this.formData.ticketAmount));
                   this.Father.projectRemainAmount = new Decimal(this.Father.projectTotalAmount).sub(new Decimal(this.Father.projectPackageAmount));
               }else{
                   this.Father.projectPackageAmount=this.formData.ticketAmount;
                   this.Father.projectRemainAmount = new Decimal(this.Father.projectTotalAmount).sub(new Decimal(this.Father.projectPackageAmount));
               }
            }).catch(err => {

            });
            
          
            
        },
        getlist() {
            detail({
                projectCode: this.$cache.local.getJSON("publicTickets").projectCode
            }).then((response) => {
                if (response.data.length == 0) {
                    this.Father = this.$cache.local.getJSON("publicTickets");
                } else {
                    this.Father = response.data[0];
                }
                if (this.Father.fileName) {
                    this.Father.fileName = JSON.parse(this.Father.fileName);
                    if (Array.isArray(this.Father.fileName)) {
                        this.fileNameradio = '2';
                        //如果是图片的话
                        for (let j in this.Father.fileName) {
                            if (this.Father.fileName[j].substring(this.Father.fileName[j].lastIndexOf('.') + 1) == 'pdf') {
                                this.pdfList.push(this.Father.fileName[j]);
                            } else {
                                this.Father.fileName[j] = this.baseImgPath + this.Father.fileName[j];
                                this.previewList.push(this.Father.fileName[j]);
                            }
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
        handlesuccess1(file, fileList) {
            this.fileNamefile.push(file.obj);
        },
        handleRemove1(file, fileList) {
            const i = this.fileNamefile.findIndex((item) => item === fileList);
            this.fileNamefile.splice(i, 1);
        },
        handlePreview1(file) {
            if (file.hasOwnProperty('response')) {
                if (file.response.obj.substring(file.response.obj.lastIndexOf('.') + 1) == 'pdf') {
                    this.titles = '正在预览' + file.response.obj;
                    this.viewVisible = true;
                        this.url= pdf.createLoadingTask({ url: this.baseImgPath + file.response.obj,CMapReaderFactory,cMapPacked: true });
                } else {
                    this.dialogImageUrl1 = file.url;
                    this.dialogVisible1 = true;
                }
            } else {
                if (file.url.substring(file.url.lastIndexOf('.') + 1) == 'pdf') {
                    this.titles = '正在预览' + file.url;
                    this.viewVisible = true;
                    this.url = file.url;
                } else {
                    this.dialogImageUrl1 = file.url;
                    this.dialogVisible1 = true;
                }
            }
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



        //监听开票内容类型
        tickettaxvip(e) {
             if (e > 0) {
                this.tickettaxvipok = true;
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
            this.formData.fileName = this.fileNamefile;
            this.formData.fileName = JSON.stringify(this.formData.fileName);
            this.$refs["elForm"].validate((valid) => {
                // TODO 提交表单
                if (valid) {
                    //如果是附件的话
                     edit(this.Father).then((res) => {
                     });
                    add(this.formData).then((res) => {
                        if (res != undefined) {
                                if (res.code === 200) {
                                    this.$modal.msgSuccess("新增成功!");
                                    this.$nextTick(function () {
                                        this.$tab.refreshPage("/project/ticketlist").then(() => {
                                            this.$tab.openPage("票据列表", "/project/ticketlist");
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
</style>
