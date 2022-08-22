<template>
    <div>
        <el-form ref="elForm" :model="formData" :rules="rules"  size="medium" label-width="140px">

           
            <el-row type="flex" class="row-bg rowCss combottom" style="padding-top: 20px;" justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="项目编号" :required="true">
                        <el-input v-model="formData.projectCode" :readonly="true"></el-input>
                    </el-form-item>

                    <el-form-item class="comright" label="项目名称" :required="true">
                        <el-input v-model="formData.projectName" :readonly="true"></el-input>
                    </el-form-item>
                </el-col>

                <el-col :span="9">

                    <el-form-item class="comright" label="项目时间" :required="true">
                        <el-input v-model="formData.createTime" :readonly="true"></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="项目金额" :required="true">
                        <el-input :readonly="true" type="number" style="width:100%" v-model="formData.projectTotalAmount" 
                            :step="0.01" :min="0"
                             oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                            >
                            <template slot="append">
                              元
                            </template>
                        </el-input>
                    </el-form-item>
                </el-col>
            </el-row>
           <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                  <el-form-item class="comright" label="甲方" :required="true">
                        <el-input v-model="formData.purchCompany" :readonly="true"></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="项目验收资料" :required="true">
                        <uploadSmall ref="productImage"  @getfileName="getfileNameS" :fileName="fileNames"  :fileNameOld="fileName" :isDetail="isDetail"></uploadSmall>
                     </el-form-item>
                </el-col>

                <el-col :span="9">

                    <el-form-item class="comright" label="乙方" prop="projectOwner">
                        <el-input  v-model="formData.selfName" :readonly="true"></el-input>
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
import {edit,check} from "@/api/project/list";
import { getInfo } from '@/api/login'
export default {
    name:'AcceptancesEdit',
     components: { uploadSmall },
    data() {
        return {
           projectStatusNew:0,
           userinfo:{},
           isDetail:'0',
           fileName: [],
           fileNames:[],
           formData: {
            fileName2:[],
           },
           rules: {
                fileName2: [
                    {
                        required: true,
                        message: "项目验收资料不能为空",
                        trigger: "change",

                    },
                ],
            },
            baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
           };
    },
    computed: {},
    mounted() {
        this.formData=this.$cache.local.getJSON("projectListNews");
        this.formData.fileName2=JSON.parse(this.formData.fileName2);
        this.$refs.productImage.getSrcList(this.formData.fileName2);
     
          for (let i in this.formData.fileName2) {
          this.fileName.push({
          name: this.formData.fileName2[i],
          url: this.baseImgPath + this.formData.fileName2[i]
         })
       }
    },
    methods: {
         check(resmsg) {
        getInfo().then(res => {
            this.userinfo=res.user;
             let parms = {
              "checkReasult": resmsg,
              "checkUser": this.userinfo.userName,
              'phonenumber': this.userinfo.phonenumber,
              "projectCode": this.formData.projectCode,
              "projectType": "12",
            };
            check(parms).then(res => {
                console.log('验收修改成功！');
            }).catch(error => {

            });
          })
       
       },
        getfileNameS(data){
           this.formData.fileName2=data;
           console.log(this.formData.fileName2);
        },
       //返回
       resetForm(){
         this.$tab.closeOpenPage({path:'/projectlist/auditAcceptanceList'})
       },
       handleChange(val) {
            console.log(val);
        },
        onSubmit() {
            this.$refs["elForm"].validate((valid) => {
                // TODO 提交表单
                this.projectStatusNew=0;
                if (valid) {
                    // project_remain_amount
                    this.formData.fileName2 = JSON.stringify(this.formData.fileName2);
                    if(this.formData.projectContractStatus==2 || this.formData.projectDutypaidStatus==2){
                        this.projectStatusNew=1;
                    }
                    let parms = {
                        projectId: this.formData.projectId,
                        fileName2: this.formData.fileName2,
                        projectAcceptanceStatus:0,
                        projectStatus:this.projectStatusNew
                    };
                    edit(parms).then((res) => {
                         if (res != undefined) {
                                if (res.code === 200) {
                                   this.$nextTick(function () {
                                     
                                    this.check('验收修改完成');
                                    this.$modal.msgSuccess('验收修改完成');
                                    this.$tab.closeOpenPage({ path: "/projectlist/auditAcceptanceList" }).then(() => {
                                          // 执行结束的逻辑 
                                         this.$tab.refreshPage({ path: "/projectlist/auditAcceptanceList",name:'AuditAcceptanceList'})
                                    })
                                        
                                    });
                                } else {
                                    this.$modal.msgError(res.msg);
                                    this.$tab.closeOpenPage({ path: "/projectlist/auditAcceptanceList" });
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
