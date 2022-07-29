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
            <div v-for="(item, index) in previewList1" :key="index">
              <el-image lazy :preview-src-list="previewList1" style="width: 150px; height: 150px" :src="item" alt="" />
            </div>
            <div v-for="(x, y) in pdfList1" :key="y">
              <span @click="pdfdetail(x)">
                {{ x }}
              </span>
            </div>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="纳税人识别号" prop="taxId">
            <el-input v-model="formbusiness.taxId" :readonly="true"></el-input>
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
            <el-date-picker disabled style="width:100%" v-model="formbusiness.businessTerm" type="date"
              placeholder="选择日期">
            </el-date-picker>
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
import { addEmployed, updateEmployed } from "@/api/company/employed";
export default {
  components: {
    pdf
  },
  data() {
    return {
      titles: '',
      pdfList1: [],  //pdf 预览
      previewList1: [], //预览
     //pdf预览
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
  created() {
    this.pdfList = [];
    let list = this.$cache.local.getJSON("employednewlist");
    this.formbusiness = list;
   
     this.pdfList1=[];  //pdf 预览
     this.previewList1=[]; //预览



    this.fileName1 = JSON.parse(this.$cache.local.getJSON('employednewlist').fileName1);
    for (let k1 in this.fileName1) {

      if (this.fileName1[k1].substring(this.fileName1[k1].lastIndexOf('.') + 1) == 'pdf') {

        this.pdfList1.push(this.fileName1[k1]);
      } else {
        this.fileName1[k1] = this.baseImgPath + this.fileName1[k1];
        this.previewList1.push(this.fileName1[k1]);
      }


    }
    // this.formbusiness.taxId=list.taxId;
    // this.formbusiness.legalPersonName = list.legalPersonName;
  },
  beforeRouteLeave(to, from, next) {
    to.meta.keepAlive = true;
    next(0);
  },

  // created() {
  //   let list = this.$cache.local.getJSON("employednewlist");
  //   this.formbusiness.selfId = list.selfId;
  //   this.formbusiness.legalPersonName = list.legalPersonName;
  // },
  beforeRouteLeave(to, from, next) {
    to.meta.keepAlive = true;
    next(0);
  },

  methods: {
    pdfdetail(i) {
      this.titles = '正在预览' + i;
      this.viewVisible = true;
      this.url = pdf.createLoadingTask({ url:this.baseImgPath + i,CMapReaderFactory,cMapPacked: true });
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
      //this.$router.back();
    },
    //返回
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
                      let resmsg = '办理工商成功';
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
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
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