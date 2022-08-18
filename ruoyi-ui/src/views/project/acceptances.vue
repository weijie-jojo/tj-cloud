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
                       <uploadSmall @getfileName="getfileNameS" :fileName="fileName" :fileNameOld="fileName" :isDetail="isDetail"></uploadSmall>
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
     components: { uploadSmall },
    data() {
        return {
           userinfo:{},
           isDetail:'0',
           fileName: [],
           formData: {},
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
        this.formData.fileName2=JSON.parse(this.fileName.fileName2);
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
              "projectType": "9",
            };
            check(parms).then(res => {
                console.log('添加验收成功！');
            }).catch(error => {

            });
          })
       
       },
        getfileNameS(data){
           this.formData.fileName2=data;
        },
       //返回
       resetForm(){
         this.$tab.closeOpenPage({path:'/project/reviewAcceptance'})
       },
       handleChange(val) {
            console.log(val);
        },
        onSubmit() {
            this.$refs["elForm"].validate((valid) => {
                // TODO 提交表单
                if (valid) {
                    this.formData.fileName2 = JSON.stringify(this.formData.fileName2);

                    let parms = {
                        projectId: this.formData.projectId,
                        fileName2: this.formData.fileName2,
                        projectAcceptanceStatus:0,
                    };
                    edit(parms).then((res) => {
                         if (res != undefined) {
                                if (res.code === 200) {
                                   this.$nextTick(function () {
                                     this.$tab.refreshPage({ path: "/project/reviewAcceptance" }).then(() => {
                                     this.check('验收修改完成');
                                          let obj = {
                                            title: '验收审核',
                                            backUrl: '/project/reviewAcceptance',
                                            resmsg: '验收修改完成'
                                            };
                                        this.$cache.local.setJSON('successNew', obj);
                                        this.$tab.closeOpenPage({ path: "/company/customer/successNew" });
                                    });
                                        
                                    });
                                } else {
                                    this.$modal.msgError(res.msg);
                                    this.$tab.closeOpenPage({ path: "/project/reviewAcceptance" });
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
