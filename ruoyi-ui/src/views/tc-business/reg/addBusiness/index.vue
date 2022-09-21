<template>
  <div class="paddingbg-s">
    <el-form ref="formbusiness" :model="formbusiness" :rules="rules" label-width="auto">
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="个体户名称" prop="selfName">
            <el-input v-model="formbusiness.selfName" placeholder="请输入个体户名称"></el-input>
          </el-form-item>

          <el-form-item class="comright" label="法人姓名" prop="legalPersonName">
            <el-input v-model="formbusiness.legalPersonName" disabled></el-input>
          </el-form-item>
          <el-form-item label="营业执照" prop="fileName1">
                <uploadSmall @getfileName="getfileNameS" :fileName="fileName1" :fileNameOld="fileName1" :isDetail="isDetail"></uploadSmall>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="纳税人识别号" prop="taxId">
            <el-input v-model="formbusiness.taxId" placeholder="请输入纳税人识别号"></el-input>
          </el-form-item>
          <el-form-item class="comright" label="注册时间" prop="businessTerm">
             <el-date-picker style="width:100%" v-model="formbusiness.businessTerm" type="date" placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="8"></el-col>
        <el-col :span="8" class="flexs">
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
import { getInfo } from '@/api/login'
import { addEmployed, updateEmployed, check,regDetail } from "@/api/tc-api/company/employed";
export default {
  name: "AddBusiness",
  components: {
    uploadSmall
  },
  data() {
    return {
      isDetail:'0',
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
      fileName1: [],
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
      userinfo: {},
      formbusiness: {
        businessStatus: "1",
        selfId: "",
        legalPersonName: "",
        selfName: "",
        taxId: "",
        fileName1: [],
        businessTerm: "",
      },
     
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
        fileName1: [
          { required: true, message: "请上传文件", trigger: "change" },
        ],
      },
    };
  },
  mounted() {
    this.$modal.loading("正在加载数据，请稍后...");
      regDetail(this.$cache.local.getJSON("tc-businesslist"))
        .then((res) => {
          this.$modal.closeLoading();
          let list = res.data;
          this.formbusiness.selfId = list.selfId;
          this.formbusiness.legalPersonName = list.legalPersonName;
          this.getInfo();
         
        })
        .catch((error) => {
          this.$modal.closeLoading();
        }); 
  },
  methods: {
    getfileNameS(data){
     this.formbusiness.fileName1=data;
    },
    resetForm(){
      this.$tab.closeOpenPage({ path: this.$cache.local.getJSON('tc-resultBack').backUrl}).then(() => {
        this.$tab.refreshPage({ path: this.$cache.local.getJSON('tc-resultBack').backUrl,name:this.$cache.local.getJSON('tc-resultBack').backName});
     })
    },
    //获取个人信息
    getInfo() {
      getInfo().then(res => {
        this.userinfo = res.user;
      })
    },
    //新增工商办理进度
    check(resmsg) {
      let parms = {
        "checkReasult": resmsg,
        "checkUser": this.userinfo.userName,
        'phonenumber': this.userinfo.phonenumber,
        "selfCode": this.$cache.local.getJSON("tc-businesslist"),
        "selfType": "5",
      }
      check(parms).then(res => {
        console.log('工商办理插入日志成功！');
      }).catch(error => {

      });
    },
    //提交表单
    onSubmit() {
      this.$refs["formbusiness"].validate((valid) => {
        if (valid) {

          if(Array.isArray(this.formbusiness.fileName1)){
            this.formbusiness.fileName1 = JSON.stringify( this.formbusiness.fileName1);
          }
        //  this.formbusiness.fileName1 = JSON.stringify(
        //     this.formbusiness.fileName1
        //   );
         updateEmployed(this.formbusiness)
            .then((res) => {
              if (res != undefined) {
                if (res.code === 200) {
                  this.$nextTick(function () {
                       let resmsg = '工商办理完成';
                       this.check(resmsg);
                       this.$tab.closeOpenPage({ path: "/tc-business/reg/result" });
                   
                  });
                  } else {
                  this.$modal.msgError(error);
                  
                }
              }
            })
            .catch((error) => {
              this.$modal.msgError(error);
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
</style>