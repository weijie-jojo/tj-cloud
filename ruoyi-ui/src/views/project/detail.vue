<template>
    <div>
        <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="140px">

           
            <el-row type="flex" class="row-bg rowCss combottom" style="padding-top:20px" justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="项目编号">
                        <el-input v-model="formData.projectCode" :readonly="true" ></el-input>
                    </el-form-item>

                    <el-form-item class="comright" label="项目名称">
                        <el-input v-model="formData.projectName" :readonly="true"></el-input>
                    </el-form-item>
                </el-col>

                <el-col :span="9">

                    <el-form-item class="comright" label="项目时间">
                        <el-input v-model="formData.projectTimeStart" :readonly="true"></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="项目金额">
                        <el-input-number disabled style="width:100%" v-model="formData.projectTotalAmount" :precision="2"
                            :step="0.01" :min="0">
                        </el-input-number>
                    </el-form-item>
                </el-col>
            </el-row>


            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="渠道商">
                        <el-input  v-model="formData.placeName" :readonly="true"></el-input>
                    </el-form-item>

                    <el-form-item class="comright" label="甲方">
                        <!-- <el-select  clearable v-model="formData.purchCompany">
                          <el-option v-for="item in purchCompanyOptions" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                        </el-select> -->
                        <el-input v-model="formData.purchCompany" :readonly="true"></el-input>
                    </el-form-item>
                </el-col>

                <el-col :span="9">

                    <el-form-item class="comright" label="渠道商状态" prop="isokradio">
                         <el-input  :readonly="true" v-if="isokradio==0" value="正常"></el-input>
                        <el-input  :readonly="true" v-else value="冻结"></el-input>
                        <!-- <el-radio v-model="isokradio" disabled label="0"> 正常</el-radio>
                        <el-radio v-model="isokradio" disabled label="2">冻结 </el-radio> -->
                    </el-form-item>
                    <el-form-item class="comright" label="甲方纳税人识别号">
                        <el-input :readonly="true" v-model="formData.purchCompanyTaxid"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>



            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="乙方">
                        <el-input :readonly="true" v-model="formData.selfName"></el-input>
                        <!-- <el-select disabled style="width:100%" clearable v-model="formData.selfName">
                            <el-option v-for="item in ownoptions" :key="item.selfId" :label="item.selfName"
                                :value="item.selfCode">
                            </el-option>
                        </el-select> -->
                    </el-form-item>

                    <el-form-item class="comright" label="乙方状态">
                        <el-select   style="width:100%" disabled clearable v-model="projectStatus" placeholder="请选择项目状态">
                            <el-option v-for="item in options" :key="item.value" :label="item.label"
                                :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>

                <el-col :span="9">

                    <el-form-item class="comright" label="乙方纳税人识别号">
                        <el-input :readonly="true" v-model="formData.taxId"></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="业务经理">
                        <el-input v-model="formData.projectLeader" :readonly="true"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>


            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="乙方行业类型">
                         <treeselect  disabled   v-model="formData.industryType" :options="industryTypes" :show-count="true" />
                    </el-form-item>
 
                    <el-form-item class="comright" label="发票类型" prop="ticketType">
                        <el-select disabled style="width:100%" clearable v-model="formData.ticketType" @change="tickettaxvip">
                            <el-option v-for="item in ticketTypeoptions" :key="item.value" :label="item.label"
                                :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>

                <el-col :span="9">
                    <el-form-item class="comright" label="乙方行业税率">
                        <el-input :readonly="true" v-model="formData.industryTax"></el-input>
                    </el-form-item>
                    <el-form-item v-if="tickettaxvipok" label="发票税率">
                        <el-input v-model="formData.ticketTax" :readonly="true"></el-input>
                    </el-form-item>
                    <el-form-item v-else class="comright" label="发票税率">


                        <el-select disabled style="width:100%" clearable v-model="formData.ticketTax">
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
                        <el-input  :readonly="true" v-if="fileNameradio==1" value="手动输入"></el-input>
                        <el-input  :readonly="true" v-else value="上传附件"></el-input>
                    </el-form-item>
                 </el-col>

                <el-col :span="9">
                    <el-form-item class="comright" label="开票内容"  v-if="fileNameradio == 1">

                        <el-input :readonly="true" type="textarea" :rows="2" v-model="formData.fileName">
                        </el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="开票内容附件"  v-if="fileNameradio == 2">
                    <div   v-for="(item, index) in formData.fileName" :key="index">
                    <el-image
                    lazy
                    :preview-src-list="fileName2"
                    style="width: 150px; height: 150px"
                    :src="baseImgPath+item"
                    alt=""
                  />
                </div>
                 </el-form-item>
                </el-col>
            </el-row>

              <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="21">
                    <el-form-item style="padding-right:4%" label="项目行业类型">
                        <el-input :readonly="true"  v-model="formData.projectTrade">
                        </el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="21">
                    <el-form-item style="padding-right:4%" label="乙方经营范围">
                        <el-input :readonly="true" type="textarea" :rows="2" placeholder="请输入乙方经营范围" v-model="formData.natureBusiness">
                        </el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="21">
                    <el-form-item style="padding-right:4%" label="发票备注" prop="ticketRemark">
                        <el-input :readonly="true" type="textarea" :rows="2" placeholder="请输入发票备注" v-model="formData.projectDesc">
                        </el-input>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
    </div>
</template>
<script>
import qs from 'qs';
import crudRate from '@/api/company/rate'
import {getcode,getinfoByUserId,detail } from "@/api/project/list";
import {getInfo} from '@/api/login' 
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
export default {
      components: { Treeselect },
    data() {
        return {
            industryTypes:'',
            username:"",
            userId: '',
            fileName2: [],
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
            projectStatus:1,
            formData: {
                placeName:'',
                ticketTax: '',//发票税率
                ticketType: '',  //发票类型
                checkContent: "",
                fileName: '',
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
                    label: '异常',
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
               
            },
            };
    },
    computed: {},
    

    mounted() {
        this.getlist();
        this.getRate();
        this.getinfoByUserId(); //渠道商
    },


    methods: {
        getlist(){
            detail({
                projectCode: this.$cache.local.getJSON("projectCodeNew")
            }).then((response) => {
             //this.projectList = response.rows;
                 this.formData=response.data[0];
                 this.isokradio=JSON.stringify(this.formData.placeStatus);
               //  this.formData.placeStatus=parseInt(this.formData.placeStatus);
                  if(this.formData.fileName){
                    this.formData.fileName=JSON.parse(this.formData.fileName);
                    if(Array.isArray(this.formData.fileName) ){
                          this.fileNameradio='2';
                          //如果是图片的话
                         for(let j in this.formData.fileName){
                            this.fileName2.push( this.baseImgPath+this.formData.fileName[j]);
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
              this.userId = 10;
              this.username = '豆红臣';
            //    res.user.nickname;
            //    res.user.userid;
           getinfoByUserId({userId: this.userId }).then(res=>{
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

// 改变input框字体颜色
::v-deep textarea {
  background-color: transparent !important;
  color: black  !important;
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
