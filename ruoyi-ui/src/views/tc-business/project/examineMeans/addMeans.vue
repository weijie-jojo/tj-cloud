<template>
    <div>
        <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="140px">

            <el-row type="flex" class="row-bg" style="margin-top:20px;" justify="space-around">
                <el-col :span="9" class="flexs">
                    <div class="bankno" style="width:35%">项目信息</div>
                    <div style="width:50%;hegiht:10px"></div>
                </el-col>
                <el-col :span="9">
                    <div></div>
                </el-col>
            </el-row>  
            <el-row type="flex" class="row-bg rowCss"  justify="space-around">
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
                        <el-input :readonly="true" type="number" style="width:100%"
                            v-model="formData.projectTotalAmount" :step="0.00001" :min="0"
                            oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'>
                            <template slot="append">
                                元
                            </template>
                        </el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="购货单位（甲方）" :required="true">
                        <el-input v-model="formData.purchCompany" :readonly="true"></el-input>
                    </el-form-item>
                    
                   


                </el-col>

                <el-col :span="9">

                    <el-form-item class="comright" label="销货单位（乙方）" prop="projectOwner">
                        <el-input v-model="formData.selfName" :readonly="true"></el-input>
                    </el-form-item>


                </el-col>
            </el-row>
            <el-row type="flex" class="row-bg"  justify="space-around" style="margin-bottom:10px;margin-top: -10px;">
                <el-col :span="9" class="flexs">
                    <div class="bankno" style="width:35%">项目合同</div>
                    <div style="width:50%;hegiht:10px"></div>
                </el-col>
                <el-col :span="9">
                    <div></div>
                </el-col>
            </el-row>  
            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="项目合同资料">
                        <el-radio v-model="formData.isUpContract" label="0">有</el-radio>
                        <el-radio v-model="formData.isUpContract" label="1">无</el-radio>
                        <div  v-if="formData.isUpContract==0">
                            <uploadSmall @getfileName="getContractFile" :fileName="fileName1" :fileNameOld="fileName1"
                            :isDetail="isDetail"></uploadSmall>
                        </div>
                       
                    </el-form-item>
                  
                </el-col>
                <el-col :span="9">
                  
                </el-col>
              
            </el-row>
            <el-row type="flex" class="row-bg"  justify="space-around" style="margin-bottom:10px;margin-top: -10px;">
                <el-col :span="9" class="flexs">
                    <div class="bankno" style="width:35%">项目验收</div>
                    <div style="width:50%;hegiht:10px"></div>
                </el-col>
                <el-col :span="9">
                    <div></div>
                </el-col>
            </el-row>
            <el-row type="flex" class="row-bg " justify="space-around" >
                <el-col :span="9">
                    <el-form-item class="comright" label="项目验收资料">
                        <el-radio v-model="formData.isUpAcceptance" label="0">有</el-radio>
                        <el-radio v-model="formData.isUpAcceptance" label="1">无</el-radio>
                        <div v-if="formData.isUpAcceptance==0">
                            <uploadSmall  @getfileName="getAccepFile" :fileName="fileName2" :fileNameOld="fileName2"
                            :isDetail="isDetail"></uploadSmall>
                        </div>
                        
                    </el-form-item>
                </el-col>
                <el-col :span="9">
                  
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
import uploadSmall from '@/components/douploads/uploadFiles'
import { edit, check,detail} from "@/api/tc-api/project/list";
import { getInfo } from '@/api/login'
export default {
    name: 'AddMeans',
    components: { uploadSmall },
    data() {
        return {
            contractIf:'0',
            acceptf:'0',
            userinfo: {},
            isDetail: '0',
            fileName1: [],
            fileName2: [],
            formData: {
              
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
    mounted() {
        this.getlist();
    },
    methods: {
        getlist() {
        this.$modal.loading("正在加载数据，请稍后...");
        detail({
          projectCode: this.$cache.local.getJSON("tc-project-code"),
        }).then((response) => {
            this.$modal.closeLoading();
          this.formData = response.data;
          this.formData.fileName2 = [];
          this.formData.fileName1 = [];
          this.formData.isUpContract='0';
          this.formData.isUpAcceptance='0';
         }).catch((error) => {
        this.$modal.closeLoading();
      });
      },
        check(resmsg) {
            getInfo().then(res => {
                this.userinfo = res.user;
                let parms = {
                    "checkReasult": resmsg,
                    "checkUser": this.userinfo.userName,
                    'phonenumber': this.userinfo.phonenumber,
                    "projectCode": this.formData.projectCode,
                    "projectType": "15",
                };
                check(parms).then(res => {
                    console.log('资料办理成功！');
                }).catch(error => {

                });
            })

        },
        //合同
        getContractFile(data) {
            this.formData.fileName1 = data;
        },
        //验收
        getAccepFile(data) {
            this.formData.fileName2 = data;
        },
        //返回
        resetForm() {
            this.$tab.closeOpenPage({path:this.$cache.local.getJSON('tc-addback').backurl })
        },
        handleChange(val) {
            console.log(val);
        },
        onSubmit() {
            this.$refs["elForm"].validate((valid) => {
                // TODO 提交表单
                if (valid) {
                    this.formData.fileName2 = JSON.stringify(this.formData.fileName2);
                    this.formData.fileName1 = JSON.stringify(this.formData.fileName1);
                    let parms = {
                        projectId: this.formData.projectId,
                        fileName2: this.formData.fileName2,
                        fileName1: this.formData.fileName1,
                        projectAcceptanceStatus:0,
                        projectContractStatus:0,
                        isUpAcceptance:this.formData.isUpAcceptance,
                        isUpContract:this.formData.isUpContract,
                        isSelfCount: this.formData.isSelfCount,
                        projectCode: this.formData.projectCode,
                        placeCode:this.formData.placeCode,
                    };
                    edit(parms).then((res) => {
                        if (res != undefined) {
                            if (res.code === 200) {
                                this.$nextTick(function () {
                                 this.check('资料办理成功');
                                 this.$modal.msgSuccess('资料办理成功');
                                 this.$tab.closeOpenPage({path:this.$cache.local.getJSON('tc-addback').backurl }).then(() => {
                                          // 执行结束的逻辑 
                                 this.$tab.refreshPage({ path:this.$cache.local.getJSON('tc-addback').backurl,name:this.$cache.local.getJSON('tc-addback').name})
                                })
                                  
                            });
                            } else {
                                this.$modal.msgError(res.msg);
                                this.$tab.closeOpenPage({ path:this.$cache.local.getJSON('tc-addback').backurl});
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
