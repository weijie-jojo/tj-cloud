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
                        <el-input
                        :readonly="true"
                        type="number" style="width:100%" v-model="formData.projectTotalAmount" 
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
                    <el-form-item class="comright" label="项目合同" prop="fileName1">
                        <uploadSmall v-if="formData.fileName1.length>0" @getfileName="getfileNameS" :fileName="isNone" :fileNameOld="fileName" :isDetail="isDetail"></uploadSmall>
                    </el-form-item>
                </el-col>

                <el-col :span="9">

                    <el-form-item class="comright" label="乙方" prop="projectOwner">
                        <el-input  v-model="formData.selfName" :readonly="true"></el-input>
                    </el-form-item>
                    
                  
                </el-col>
            </el-row>
              
                <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="21">
          <el-form-item class="comright" style="padding-right: 4.2%;margin-left: -7%;">
            <el-radio v-model="isokradioS" label="1"> 通过</el-radio>

          </el-form-item>
        </el-col>

      </el-row>   
       <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="21">
          <el-form-item class="comright" style="padding-right: 4.2%;margin-left: -7%;">
            <div style="display: flex; align-items: center;justify-content: flex-start;">
              <el-radio v-model="isokradioS" label="2">不通过 </el-radio>
              <el-input type="textarea" placeholder="请输入不通过说明" v-model="remark" :disabled="isokradioS == 1"></el-input>
            </div>


          </el-form-item>
        </el-col>

      </el-row>
            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="8"></el-col>
                <el-col :span='8' class="flexs">
                
                     <el-button type="danger" @click="resetForm">关闭</el-button>
                     <el-button v-if="isokradioS == 2" type="primary" @click="submitForm(2)">提交</el-button>
                     <el-button v-else type="primary" @click="submitForm(1)">提交</el-button>
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
            remark:'',
            isokradioS:'1',
            fileName: [],
            isNone:[],
            formData: { },
            baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
           };
    },
    computed: {},
    mounted() {
        this.fileName=[];
        this.formData=this.$cache.local.getJSON("projectListNews");
        this.formData.fileName1=JSON.parse(this.formData.fileName1);
        for(let i in this.formData.fileName1){
           this.fileName.push({
            name:this.formData.fileName1[i],
            url:this.baseImgPath+this.formData.fileName1[i]
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
              "projectType": "3",
            };
            check(parms).then(res => {
                console.log('合同审核完成');
            }).catch(error => {

            });
          })
       
       },
      getfileNameS(data){
        this.formData.fileName1=data;
      },
      submitForm(type) {
        this.$refs['elForm'].validate(valid => {
        // TODO 提交表单
        if (valid) {
          let parms;
          if (type == 1) {
            parms = {
              projectId: this.formData.projectId,
              projectContractStatus:type,
            };
          } else {
            parms = {
              projectId: this.formData.projectId,
              contractRemark: this.remark,
              projectContractStatus:type,
              projectStatus:1,
            };
          }
           edit(parms).then((res) => {
            if (res != undefined) {
              if (res.code === 200) {
                 this.$nextTick(function () {
                  this.$tab.refreshPage({ path: "/project/reviewContract" }).then(() => {
                    let resmsg = '';
                    if (type == 1) {
                      resmsg = '合同审核完成';
                      this.check('合同审核完成');
                    } else {
                      this.check('合同审核完成未通过'+'(原因)'+this.remark);
                      resmsg = '合同审核完成';
                    }

                   let obj = {
                      title: '合同审核',
                      backUrl: '/project/reviewContract',
                      resmsg: resmsg

                    }
                    this.$cache.local.setJSON('successNew', obj);
                    this.$tab.closeOpenPage({ path: "/company/customer/successNew" });
                  });
                });

              } else {
                this.$modal.msgError(res.msg);
                this.$tab.closeOpenPage({ path: "/project/reviewContract" });
              }

            }
         });

        } else {
          this.$message({
            message: '请填写完整',
            type: 'warning'
          })
        }
      })

    },
      //返回
        resetForm(){
         this.$tab.closeOpenPage({path:'/project/reviewContract'})
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
