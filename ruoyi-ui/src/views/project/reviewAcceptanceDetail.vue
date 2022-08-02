<template>
    <div>
        <el-form ref="elForm" :model="formData"  size="medium" label-width="140px">

           
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
                            :step="0.01" :min="0">
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
                          <uploadSmall v-if="fileName.length>0" @getfileName="getfileNameS" :fileName="isNone" :fileNameOld="fileName" :isDetail="isDetail"></uploadSmall>
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
                
                     <el-button type="danger" @click="resetForm">返回</el-button>
                     <el-button v-if="isokradioS == 2" type="primary" @click="submitForm(2)">驳回</el-button>
                     <el-button v-else type="primary" @click="submitForm(1)">通过</el-button>
                </el-col>
                <el-col :span="8"></el-col>
            </el-row>
      </el-form>
     </div>
</template>
<script>
import uploadSmall from '@/components/douploads/uploadSmall'
import {edit,check} from "@/api/project/list"
import { getInfo } from '@/api/login'
export default {
     components: { uploadSmall },
    data() {
        return {
            remark:'',
            isNone:[],
            isDetail:'1',
            isokradioS:'1',
            fileName: [],
            userinfo:{},
            formData:{},
            baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
           };
    },
    computed: {},
    mounted() {
        this.formData=this.$cache.local.getJSON("projectListNews");
        this.formData.fileName2=JSON.parse(this.formData.fileName2);
        for(let i in this.formData.fileName2){
           this.fileName.push({
            name:this.formData.fileName2[i],
            url:this.baseImgPath+this.formData.fileName2[i]
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
              "projectType": "4",
            };
            check(parms).then(res => {
                console.log('验收审核完成');
            }).catch(error => {

            });
          })
       
       },
      getfileNameS(){

      },
      submitForm(type) {
      this.$refs['elForm'].validate(valid => {
        // TODO 提交表单
        if (valid) {
          let parms;
          if (type == 1) {
            parms = {
              projectId: this.formData.projectId,
              projectAcceptanceStatus:type,
            };
          } else {
            parms = {
              projectId: this.formData.projectId,
              checkContent: this.remark,
              projectAcceptanceStatus:type,
              projectStatus:1,
            };
          }
           edit(parms).then((res) => {
            if (res != undefined) {
              if (res.code === 200) {
                 this.$nextTick(function () {
                  this.$tab.refreshPage({ path: "/project/reviewAcceptance" }).then(() => {
                    let resmsg = '';
                    if (type == 1) {
                      resmsg = '验收审核完成';
                      this.check('验收审核完成');
                    } else {
                      this.check('验收审核完成未通过'+'(原因)'+this.remark);
                      resmsg = '验收审核完成';
                    }

                   let obj = {
                      title: '验收审核',
                      backUrl: '/project/reviewAcceptance',
                      resmsg: resmsg

                    }
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
            message: '请填写完整',
            type: 'warning'
          })
        }
      })

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


       beforeAvatarUpload(file){
       const isLt2M = file.size / 1024 / 1024 < 5;
       const fileSuffix = file.name.substring(file.name.lastIndexOf(".") + 1);
       const whiteList = ["jpg", "png",'pdf','jpeg'];
       if (whiteList.indexOf(fileSuffix) === -1) {
       this.$message.error('上传文件只能是 jpg,png,jpeg,pdf格式');
         return false;
      }
       if (!isLt2M) {
          this.$message.error('上传文件大小不能超过 10MB!');
          return false;
        }
        return fileSuffix&isLt2M;
       
    },
      //返回
       resetForm(){
         this.$tab.closeOpenPage({path:'/project/reviewContract'})
       },
       handlesuccess1(file, fileList) {
            this.formData.fileName.push(file.obj);
        },
        handleRemove1(file, fileList) {
            const i = this.formData.fileName.findIndex((item) => item === fileList);
            this.formData.fileName.splice(i, 1);
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
        handleChange(val) {
            console.log(val);
        },
        onSubmit() {
            this.$refs["elForm"].validate((valid) => {
                // TODO 提交表单
                if (valid) {
                    this.formData.fileName = JSON.stringify(this.formData.fileName);

                    let parms = {
                        projectId: this.formData.projectId,
                      
                    };
                    edit(parms).then((res) => {
                         if (res != undefined) {
                                if (res.code === 200) {
                                    this.$modal.msgSuccess("合同办理成功!");
                                    this.$nextTick(function () {
                                        this.resetForm();
                                        
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



// ::v-deep .el-tabs__nav-scroll {
//   width: 50% !important;
//   margin: 0 auto !important;
// }
</style>
