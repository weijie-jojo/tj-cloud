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
                  <div v-for="(item, index) in previewList2" :key="index">
              <el-image lazy :preview-src-list="previewList2" style="width: 150px; height: 150px" :src="item" alt="" />
            </div>
            <div v-for="(x, y) in pdfList2" :key="y">
              <span @click="pdfdetail(x)">
                {{ x }}
              </span>
            </div>
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
      pdfList2:[],
    
      previewList2:[], //预览
      
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
          // { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
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
    this.pdfList2=[];  //pdf 预览
    this.previewList2=[]; //预览
    
    let list = this.$cache.local.getJSON("employednewlist");
    this.formtax = list;
      this.fileName2=JSON.parse(this.$cache.local.getJSON('employednewlist').fileName2);
    for(let k1 in this.fileName2){
      //this.fileName2[k1]=this.baseImgPath+this.fileName2[k1];
       if (this.fileName2[k1].substring(this.fileName2[k1].lastIndexOf('.') + 1) == 'pdf') {

        this.pdfList2.push(this.fileName2[k1]);
      } else {
        this.fileName2[k1] = this.baseImgPath + this.fileName2[k1];
        this.previewList2.push(this.fileName2[k1]);
      }

    } 
    // this.formtax.selfName = list.selfName;
    // this.formtax.legalPersonName = list.legalPersonName;
    // this.formtax.taxId = list.taxId;
  },
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
    resetForm() {
      this.$tab.closeOpenPage({ path: "/company/customer/manageTax"});
     },
    //提交
    onSubmit() {
      this.$refs["formtax"].validate((valid) => {
        if (valid) {
          this.formtax.fileName2 = JSON.stringify(this.formtax.fileName2);
          updateEmployed(this.formtax)
            .then((res) => {
              if (res != undefined) {
                if (res.code === 200) {
                  this.$nextTick(function () {
                     this.$tab.refreshPage({ path: "/company/customer/manageTax"}).then(() => {
                     let  resmsg='办理税务成功';
                     let obj={
                        title:'税务办理',
                        backUrl:'/company/customer/manageTax',
                        resmsg:resmsg
                        };
                      this.$cache.local.setJSON('successNew', obj);
                      this.$tab.closeOpenPage({ path: "/company/customer/successNew"});
                    });
                   });
                } else {
                  this.$modal.msgError(res.msg);
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
      this.formtax.fileName2.push(file.obj);
    },
    handleRemove(file, fileList) {
      const i = this.formtax.fileName2.findIndex((item) => item === fileList);
      this.formtax.fileName2.splice(i, 1);
    },
    handlePreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${
          files.length + fileList.length
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
</style>