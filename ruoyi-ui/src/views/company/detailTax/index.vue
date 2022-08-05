<template>
  <div class="paddingbg-s">
    <el-form ref="formtax" :model="formtax" :rules="rules" label-width="auto">
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="个体户名称" prop="selfName">
            <el-input
              v-model="formtax.selfName"
              :readonly="true"
            ></el-input>
          </el-form-item>
          <el-form-item class="comright" label="法人姓名" prop="legalPersonName">
            <el-input
              v-model="formtax.legalPersonName"
              :readonly="true"
            ></el-input>
          </el-form-item>
          <el-form-item label="核定通知书" prop="fileName2">
             <uploadSmall v-if="fileNameN1.length>0" @getfileName="getfileNameS" :fileName="isNone" :fileNameOld="fileNameN1" :isDetail="isDetail"></uploadSmall>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="纳税人识别号" prop="taxId">
            <el-input
              v-model="formtax.taxId"
              :readonly="true"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg " justify="space-around">
         <el-col :span="8"></el-col>
         <el-col :span='8' class="flexs">
             <el-button type="danger" @click="resetForm">返回</el-button> 
             
         </el-col>
         <el-col :span="8"></el-col>
     </el-row>
    </el-form>
   
   </div>
   
</template>

<script>
import uploadSmall from '@/components/douploads/uploadSmall'
export default {
   components: {
    uploadSmall
  },
  data() {
    return {
      isDetail:'1',
      isNone:[],
      fileNameN1:[],
     formtax: {
        taxStatus: 1,
        selfId: "",
        legalPersonName: "",
        selfName: "",
        taxId: "",
        fileName2: [],
      },
        baseImgPath:"/eladmin/api/files/showTxt?imgPath=",

      fileName2: [],
      dialogVisible: false,
      previewPath: "",
      dialogImageUrl: "",
      rules: {
        selfName: [
          { required: true, message: "请输入个体户名称", trigger: "blur" },
         
        ],
        legalPersonName: [
          { required: true, message: "请输入法人姓名", trigger: "blur" },
        ],
        taxId: [
          { required: true, message: "请输入纳税人识别号", trigger: "blur" },
        ],
        fileName2: [
          { required: true, message: "请上传文件", trigger: "change" },
        ],
      },
    };
  },
  created() {
    let list = this.$cache.local.getJSON("employednewlist");
    this.formtax = list;
    this.fileNameN1=[];
    this.fileName2=JSON.parse(this.$cache.local.getJSON('employednewlist').fileName2);
    for(let k1 in this.fileName2){
       this.fileNameN1.push({
          url:this.baseImgPath+this.fileName2[k1],
          name:this.fileName2[k1],
        });
     } 
  },
  methods: {
    getfileNameS(){

    },
    resetForm() {
      this.$tab.closeOpenPage({ path: "/company/customer/manageTax"});
     },
  
  },
};
</script>

<style>
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
.comright{
  padding-right: 10%;
}
.flexs{
  display: flex;
  justify-content: center;

}
::v-deep .el-input.is-disabled .el-input__inner{
   background-color: rgba(255, 255, 255, 1.5) !important;
   color: black  !important;
   border-color: rgba(135,206,250,0.7) !important;
}
::v-deep .el-input-group__append{
   background-color: rgba(255, 255, 255, 1.5) !important;
   color: black  !important;
   border-color: rgba(135,206,250,0.7) !important;
}
</style>