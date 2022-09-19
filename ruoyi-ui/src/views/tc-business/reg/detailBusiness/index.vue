<template>
  <div class="paddingbg-s">
    <el-form ref="formbusiness" :model="formbusiness" :rules="rules" label-width="auto">
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="个体户名称" prop="selfName">
            <el-input v-model="formbusiness.selfName" :readonly="true"></el-input>
          </el-form-item>

          <el-form-item class="comright" label="法人姓名" prop="legalPersonName">
            <el-input v-model="formbusiness.legalPersonName" :readonly="true"></el-input>
          </el-form-item>
          <el-form-item label="营业执照" prop="fileName1">
             <uploadSmall v-if="fileNameN1.length>0" @getfileName="getfileNameS" :fileName="isNone" :fileNameOld="fileNameN1" :isDetail="isDetail"></uploadSmall>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="纳税人识别号" prop="taxId">
            <el-input v-model="formbusiness.taxId" :readonly="true"></el-input>
          </el-form-item>
          <el-form-item class="comright" label="注册时间" prop="businessTerm">
            <el-date-picker disabled style="width:100%" v-model="formbusiness.businessTerm" type="date"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
        <el-row type="flex" class="row-bg " justify="space-around">
         <el-col :span="8"></el-col>
         <el-col :span='8' class="flexs">
             <el-button type="danger" @click="resetForm">关闭</el-button> 
             
         </el-col>
         <el-col :span="8"></el-col>
     </el-row>

    </el-form>
   </div>
</template>

<script>
import uploadSmall from '@/components/douploads/uploadSmall'
export default {
  name:'DetailBusiness',
  components: {
    uploadSmall
  },
  data() {
    return {
      isDetail:'1',
      isNone:[],
      fileNameN1:[],
      pickerOptions: {
        shortcuts: [
          {
            text: "最近一年",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              end.setTime(end.getTime() + 3600 * 1000 * 24 * 365);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近5年",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              end.setTime(end.getTime() + 3600 * 1000 * 24 * 365 * 5);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近30年",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              end.setTime(end.getTime() + 3600 * 1000 * 24 * 365 * 30);
              picker.$emit("pick", [start, end]);
            },
          },
        ],
      },
      formbusiness: {
        businessStatus: "1",
        selfId: "",
        legalPersonName: "",
        selfName: "",
        taxId: "",
        fileName1: [],
        businessTerm: "",
      },
      fileName1: [],
      dialogVisible: false,
      previewPath: "",
      dialogImageUrl: "",
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
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
        fileName1: [
          { required: true, message: "请上传文件", trigger: "change" },
        ],
        businessTerm:[{
          required: true, message: "请选择注册时间", trigger: "change"
        }]
      },
    };
  },
  created() {
    let list = this.$cache.local.getJSON("tc-businesslist");
    this.formbusiness = list;
    this.fileNameN1=[];
    this.fileName1 = JSON.parse(this.$cache.local.getJSON('tc-businesslist').fileName1);
    for (let k1 in this.fileName1) {
         this.fileNameN1.push({
          url:this.baseImgPath+this.fileName1[k1],
          name:this.fileName1[k1],
        });
     }
   },
    methods: {
    getfileNameS(){

    },
    //返回
    resetForm() {
       this.$tab.closeOpenPage( { path: this.$cache.local.getJSON('backurls').backUrl });
    },
    //返回
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

.flexs {
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