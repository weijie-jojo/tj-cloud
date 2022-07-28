<template>
    <div>
        <el-form ref="elForm" :model="formData"  size="medium" label-width="140px">

           
            <el-row type="flex" class="row-bg rowCss combottom" style="padding-top: 20px;" justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="项目编号" :required="true">
                        <el-input v-model="formData.projectCode" disabled></el-input>
                    </el-form-item>

                    <el-form-item class="comright" label="项目名称" :required="true">
                        <el-input v-model="formData.projectName"></el-input>
                    </el-form-item>
                </el-col>

                <el-col :span="9">

                    <el-form-item class="comright" label="项目时间" :required="true">
                        <el-input v-model="formData.createTime" disabled></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="项目金额" :required="true">
                        <el-input type="number" style="width:100%" v-model="formData.projectTotalAmount" 
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
                        <el-input v-model="formData.purchCompany" disabled></el-input>
                    </el-form-item>
                   
                     <el-form-item class="comright" label="项目完税资料" :required="true">

                        <el-upload class="upload-demo" action="/eladmin/api/files/doUpload"
                            :on-success="handlesuccess1" :on-preview="handlePreview1" :on-remove="handleRemove1"
                            :before-remove="beforeRemove1" multiple :limit="9" :on-exceed="handleExceed1"
                            :file-list="fileName" list-type="picture"
                             :before-upload="beforeAvatarUpload"
                            >
                            <el-button size="small" type="primary">点击上传</el-button>
                            <div slot="tip" class="el-upload__tip" style="color:red">仅支持jpg/png/jpeg/pdf文件，且不超过10M</div>
                        </el-upload>
                        <el-dialog :visible.sync="dialogVisible1" append-to-body>
                            <img width="100%" :src="dialogImageUrl1" alt="" />
                        </el-dialog>
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
             <el-button type="danger" @click="resetForm">返回</el-button> 
             <el-button type="primary" @click="onSubmit">提交</el-button>
            </el-col>
           <el-col :span="8"></el-col>
        </el-row>
        </el-form>
    </div>
</template>
<script>
import pdf from 'vue-pdf'
import {edit} from "@/api/project/list";
export default {
     components: { pdf },
    data() {
        return {
           
            fileName: [],
            dialogVisible1: false,
            dialogImageUrl1: "",
          
             formData: {
              
                ticketTax: '',//发票税率
                ticketType: '',  //发票类型
                checkContent: "",
                
                fileName: [],
                placeCode: "",
                industryType:'',
            },
            baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
           };
    },
    computed: {},
    mounted() {
        this.formData=this.$cache.local.getJSON("publicTickets");
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
         this.$tab.closeOpenPage({path:'/project/reviewDutypaid'})
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
