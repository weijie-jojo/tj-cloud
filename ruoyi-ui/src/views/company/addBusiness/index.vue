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
import { addEmployed, updateEmployed, check } from "@/api/company/employed";
export default {
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


  created() {
    let list = this.$cache.local.getJSON("employednewlist");
    this.formbusiness.selfId = list.selfId;
    this.formbusiness.legalPersonName = list.legalPersonName;
  },
  mounted() {
    this.getInfo();
  },
  methods: {
    getfileNameS(data){
     this.formbusiness.fileName1=data;
    },
    resetForm(){
      this.$tab.closeOpenPage({ path: this.$cache.local.getJSON('successNew').backUrl}).then(() => {
        this.$tab.refreshPage({ path: this.$cache.local.getJSON('successNew').backUrl});
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
        "selfCode": this.$cache.local.getJSON("employednewlist").selfCode,
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
         this.formbusiness.fileName1 = JSON.stringify(
            this.formbusiness.fileName1
          );
         updateEmployed(this.formbusiness)
            .then((res) => {
              if (res != undefined) {
                if (res.code === 200) {
                  this.$nextTick(function () {
                    this.$tab.refreshPage({ path: "/company/customer/manageBusiness" }).then(() => {
                      let resmsg = '工商办理完成';
                      this.check(resmsg);
                      // let obj = {
                      //   title: '工商办理',
                      //   backUrl: '/company/customer/manageBusiness',
                      //   resmsg: resmsg
                      // };
                      // this.$cache.local.setJSON('successNew', obj);
                      this.$tab.closeOpenPage({ path: "/company/customer/successNew" });
                    });
                  });
                  } else {
                  this.$modal.msgError(error);
                  this.$tab.closeOpenPage({ path: "/company/customer/manageBusiness" });
                }
              }
            })
            .catch((error) => {
              this.$modal.msgError(error);
            });
        } else {
          this.$modal.msgError("请填写完整");
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