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
            <el-upload class="upload-demo" action="/eladmin/api/files/doUpload" :on-success="handlesuccess"
              :on-preview="handlePreview" :on-remove="handleRemove" :before-remove="beforeRemove" multiple :limit="9"
              :on-exceed="handleExceed" :file-list="fileName1" list-type="picture"
               :before-upload="beforeAvatarUpload"
              >
              <el-button size="small" type="primary">点击上传</el-button>
               <div slot="tip" class="el-upload__tip" style="color:red">仅支持jpg/png/jpeg/pdf文件，且不超过10M</div>
            </el-upload>
            <el-dialog :visible.sync="dialogVisible" append-to-body>
              <img width="100%" :src="dialogImageUrl" alt="" />
            </el-dialog>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="纳税人识别号" prop="taxId">
            <el-input v-model="formbusiness.taxId" placeholder="请输入纳税人识别号"></el-input>
          </el-form-item>
          <el-form-item class="comright" label="注册时间" prop="businessTerm">
            <!-- <el-date-picker
              v-model="formbusiness.businessTerm"
              type="daterange"
              align="right"
              unlink-panels
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              :picker-options="pickerOptions"
            >
            </el-date-picker> -->
            <el-date-picker style="width:100%" v-model="formbusiness.businessTerm" type="date" placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="8"></el-col>
        <el-col :span="8" class="flexs">
          <el-button type="danger" @click="resetForm">返回</el-button>
          <el-button type="primary" @click="onSubmit">提交</el-button>
        </el-col>
        <el-col :span="8"></el-col>
      </el-row>
    </el-form>
    <!--PDF 预览-->
    <el-dialog :title="titles" :visible.sync="viewVisible" width="80%" center @close='closeDialog'>

      <div>
        <div class="tools flexs" style=" align-items: center;">
          <div class="page" style="margin-right:20px;font-size: 20px;">共{{ pageNum }}/{{ pageTotalNum }} </div>
          <el-button :theme="'default'" type="submit" @click.stop="prePage" class="mr10"> 上一页</el-button>
          <el-button :theme="'default'" type="submit" @click.stop="nextPage" class="mr10"> 下一页</el-button>
          <el-button :theme="'default'" type="submit" @click.stop="clock" class="mr10"> 顺时针</el-button>
          <el-button :theme="'default'" type="submit" @click.stop="counterClock" class="mr10"> 逆时针</el-button>

        </div>
        <pdf ref="pdf" :src="url" :page="pageNum" :rotate="pageRotate" @progress="loadedRatio = $event"
          @page-loaded="pageLoaded($event)" @num-pages="pageTotalNum = $event" @error="pdfError($event)"
          @link-clicked="page = $event">
        </pdf>

      </div>
    </el-dialog>
  </div>
</template>

<script>
import pdf from 'vue-pdf-signature'
import CMapReaderFactory from 'vue-pdf/src/CMapReaderFactory.js'
import { getInfo } from '@/api/login'
import { addEmployed, updateEmployed, check } from "@/api/company/employed";
export default {
  components: {
    pdf
  },
  data() {
    return {
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
      //pdf预览
      titles: '',
      url: '',
      viewVisible: false,
      pageNum: 1,
      pageTotalNum: 1,
      pageRotate: 0,
      // 加载进度
      loadedRatio: 0,
      curPageNum: 0,
      closeDialog: false,



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
      fileName1: [],
      dialogVisible: false,
      previewPath: "",
      dialogImageUrl: "",
      rules: {
        selfName: [
          { required: true, message: "请输入个体户名称", trigger: "blur" },
          // { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
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

  beforeRouteLeave(to, from, next) {
    to.meta.keepAlive = true;
    next(0);
  },

  created() {
    let list = this.$cache.local.getJSON("employednewlist");
    this.formbusiness.selfId = list.selfId;
    this.formbusiness.legalPersonName = list.legalPersonName;
  },
  mounted() {
    this.getInfo();
  },
  beforeRouteLeave(to, from, next) {
    to.meta.keepAlive = true;
    next(0);
  },


  methods: {
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


    //返回
    resetForm() {
      this.$tab.closeOpenPage({ path: "/company/customer/manageBusiness" });
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
          // var startDate = new Date(this.formbusiness.businessTerm[0]);
          // var endDate = new Date(this.formbusiness.businessTerm[1]);
          // var businessTerm =
          //   startDate.getFullYear() +
          //   "年" +
          //   startDate.getMonth() +
          //   "月" +
          //   startDate.getDate() +
          //   "日" +
          //   "至" +
          //   endDate.getFullYear() +
          //   "年" +
          //   endDate.getMonth() +
          //   "月" +
          //   endDate.getDate() +
          //   "日";
          // console.log("businessTerm", businessTerm);
          this.formbusiness.fileName1 = JSON.stringify(
            this.formbusiness.fileName1
          );
          // this.formbusiness.businessTerm = businessTerm;
          updateEmployed(this.formbusiness)
            .then((res) => {
              if (res != undefined) {
                if (res.code === 200) {
                  this.$nextTick(function () {
                    this.$tab.refreshPage({ path: "/company/customer/manageBusiness" }).then(() => {
                      let resmsg = '办理工商完成';
                      this.check(resmsg);
                      let obj = {
                        title: '工商办理',
                        backUrl: '/company/customer/manageBusiness',
                        resmsg: resmsg
                      };
                      this.$cache.local.setJSON('successNew', obj);
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

    handlesuccess(file, fileList) {
      this.formbusiness.fileName1.push(file.obj);
    },
    handleRemove(file, fileList) {
      const i = this.formbusiness.fileName1.findIndex(
        (item) => item === fileList
      );
      this.formbusiness.fileName1.splice(i, 1);
    },
    handlePreview(file) {
      if (file.response.obj.substring(file.response.obj.lastIndexOf('.') + 1) == 'pdf') {
        this.titles = '正在预览' + file.response.obj;
        this.viewVisible = true;
        this.url= pdf.createLoadingTask({ url: this.baseImgPath + file.response.obj,CMapReaderFactory,cMapPacked: true });
      } else {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      }

    },
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length
        } 个文件`
      );
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
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