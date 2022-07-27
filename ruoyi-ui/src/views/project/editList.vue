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
                        <el-input type="number" style="width:100%" v-model="formData.projectTotalAmount" 
                            :step="0.01" :min="0">
                              <template slot="append">元</template>
                        </el-input>
                    </el-form-item>
                </el-col>
            </el-row>


            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="渠道商">
                        <el-select @change="placeNew" style="width:100%" clearable v-model="formData.placeCode">
                            <el-option v-for="item in placeCodeOptions" :key="item.placeCode" :label="item.placeName"
                                :value="item.placeCode">
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
                    <el-form-item class="comright" label="渠道商状态" prop="isokradio">
                        <el-input disabled v-if="isokradio == 0" value="正常"></el-input>
                        <el-input disabled v-if="isokradio == 1" value="欠费"></el-input>
                        <el-input disabled v-if="isokradio == 2" value="冻结"></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="甲方纳税人识别号" prop="purchCompanyTaxid">
                        <el-input v-model="formData.purchCompanyTaxid"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="乙方行业类型" prop="industryType">
                        <el-select class="main-select-tree" ref="selectTree" v-model="formData.industryType"
                            style="width: 100%;">
                            <el-option v-for="item in formatData(industryTypes)" :key="item.value" :label="item.label"
                                :value="item.value" style="display: none;" />
                            <el-tree class="main-select-el-tree" ref="selecteltree" :data="industryTypes" node-key="id"
                                highlight-current :props="defaultProps" @node-click="handleNodeClick"
                                :current-node-key="formData.industryType" :expand-on-click-node="expandOnClickNode"
                                default-expand-all />
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
                        <el-input style="width:86%" v-model="formData.ticketTax" :required="true"></el-input>
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
                        <el-select @change="ownnew" style="width:100%" clearable v-model="formData.projectOwner">
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

                        <el-input type="textarea" :rows="2" v-model="formData.fileName">
                        </el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="开票内容附件" prop="fileName" v-if="fileNameradio == 2">

                        <el-upload class="upload-demo" action="/eladmin/api/files/doUpload" :on-success="handlesuccess1"
                            :on-preview="handlePreview1" :on-remove="handleRemove1" :before-remove="beforeRemove1"
                            multiple :limit="9" :on-exceed="handleExceed1" :file-list="fileName" list-type="picture">
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
                    <el-form-item style="padding-right:4%" class="comright" label="项目行业类型" :required="true">
                        <el-select class="main-select-tree" ref="selectTrees" v-model="projectTrades"
                            style="width: 100%;">
                            <el-option v-for="item in formatData(projectTradeS)" :key="item.value" :label="item.label"
                                :value="item.value" style="display: none;" />
                            <el-tree class="main-select-el-tree" ref="selecteltrees" :data="projectTradeS" node-key="id"
                                highlight-current :props="defaultProps" @node-click="handleNodeClick1"
                                :current-node-key="projectTrades" :expand-on-click-node="expandOnClickNode"
                                default-expand-all />
                        </el-select>

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
                    <el-form-item style="padding-right:4%" label="发票备注" prop="projectDesc">
                        <el-input type="textarea" :rows="2" placeholder="请输入发票备注" v-model="formData.projectDesc">
                        </el-input>
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
             <!--PDF 预览-->
    <el-dialog :title="titles" :visible.sync="viewVisible" width="80%" center @close='closeDialog'>

      <div>
        <div class="tools flexs" style=" align-items: center;">
          <div class="page" style="margin-right:20px;font-size: 20px;">共{{ pageNum }}/{{ pageTotalNum }} </div>
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
import pdf from 'vue-pdf'
import crudRate from '@/api/company/rate'
import { detail, getcode, getinfoByUserId, edit, ownlist } from "@/api/project/list";
import { getInfo } from '@/api/login'
export default {
    components: {
        pdf
    },
    data() {
        return {
            titles: '',
            pdfList1: [],  //pdf 预览
            previewList1: [], //预览
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


            defaultProps: {
                children: 'children',
                label: 'label'
            },
            expandOnClickNode: true,
            projectTradeS: [],  //项目行业类型
            projectTradeSList: '',//项目行业类型
            projectStatus: 1,//乙方状态
            username: '',
            userId: '',
            industryId: '',
            industryTypes: [],
            industryTypeList: [],

            industryTypes1: [],
            industryTypeList1: [],

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
            projectTrades: '',
            formData: {
                
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
                projectTrades: [

                    {
                        required: true,
                        message: "请选择项目行业类型",
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
        'formData.industryType': 'selectIndustryType',
        'projectTrades': 'selectInType',

    },

    mounted() {
        this.getlist();
        this.getinfoByUserId(); //渠道商
        this.getRate();



    },


    methods: {
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



        handleNodeClick(node) {

            this.formData.industryType = node.id;
            this.$refs.selectTree.blur();
        },
        handleNodeClick1(node) {
            this.projectTrades = node.id;
            this.$refs.selectTrees.blur();
        },
        // 四级菜单
        formatData(data) {
            let options = [];
            if(data.length>0){

            data.forEach((item, key) => {
                options.push({ label: item.label, value: item.id });
                if (item.children) {
                    item.children.forEach((items, keys) => {
                        options.push({ label: items.label, value: items.id });
                        if (items.children) {
                            items.children.forEach((itemss, keyss) => {
                                options.push({ label: itemss.label, value: itemss.id });
                                if (itemss.children) {
                                    itemss.children.forEach((itemsss, keysss) => {
                                        options.push({ label: itemsss.label, value: itemsss.id });
                                    });
                                }
                            });
                        }
                    });
                }
            });
            }
            return options;
        },
        getlist() {
            detail({
                projectCode: this.$cache.local.getJSON("projectCodeNew")
            }).then((response) => {
               this.formData = response.data[0];
               // this.formData=this.$cache.local.getJSON("projectListNews");
                this.projectTrades = '';
                // this.isokradio = JSON.stringify(this.formData.placeStatus);
                if (this.formData.fileName) {
                    this.formData.fileName = JSON.parse(this.formData.fileName);
                    if (Array.isArray(this.formData.fileName)) {
                        this.fileNameradio = '2';
                        //如果是图片的话
                        for (let j in this.formData.fileName) {
                            this.fileName.push({
                                name: this.formData.fileName[j],
                                url: this.baseImgPath + this.formData.fileName[j]

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
            if (file.hasOwnProperty('response')) {
                if (file.response.obj.substring(file.response.obj.lastIndexOf('.') + 1) == 'pdf') {
                    this.titles = '正在预览' + file.response.obj;
                    this.viewVisible = true;
                    this.url = this.baseImgPath + file.response.obj;
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
                this.username = res.user.nickName;
                this.formData.projectLeader = res.user.nickName;
                getinfoByUserId({ userId: this.userId }).then(res => {
                    this.placeCodeOptions = res.data;
                    for(let i in this.placeCodeOptions){
                         if (this.placeCodeOptions[i].placeCode == this.formData.placeCode) {
                           this.isokradio = JSON.stringify(this.placeCodeOptions[i].placeStatus);
                        }
                    }
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
                this.projectTradeS = tree;
                this.projectTradeSList = res.rows;
                var rate1 = this.projectTradeSList.find((item) => item.industryName == this.formData.projectTrade);
                this.projectTrades = rate1.industryId;
                this.selectIndustryType1();
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

        // //监听行业类型
        selectIndustryType1() {
            var rate = this.industryTypeList.find((item) => item.industryId == this.formData.industryType);
            this.industryId = rate.industryId;  //行业类型id
            this.owerTaxfee = rate.taxRate;
            let industryType = rate.industryId;

            ownlist({ username: this.username, industryType: industryType }).then(res => {
                this.ownoptions = res;
                for (let i in this.ownoptions) {
                    if (this.ownoptions[i].selfName == this.formData.selfName) {
                        this.natureBusiness = this.ownoptions[i].natureBusiness;
                        this.owerTax = this.ownoptions[i].taxId;
                    }
                }
            }).catch(err => {
                console.log(err);
            });

        },



        //监听行业类型
        selectIndustryType() {
            
           
            var rate = this.industryTypeList.find((item) => item.industryId == this.formData.industryType);
            if(rate){
               this.industryId = rate.industryId;  //行业类型id
               this.owerTaxfee = rate.taxRate;
               let industryType = rate.industryId;
             ownlist({ username: this.username, industryType: industryType }).then(res => {
                this.ownoptions = res;
            }).catch(err => {
                console.log(err);
            });
            }else{
                this.getRate();
            }
           

        },
        //监听项目行业类型
        selectInType() {

            var rate = this.projectTradeSList.find((item) => item.industryId == this.projectTrades);
            this.formData.projectTrade = rate.industryName;//所属行业
            console.log(this.formData.projectTrade);
        
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
