<template>
  <div class="app-container">
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
import { getWord  } from "@/api/company/employed";

export default {
  name:'BusinessTable',
    components: {
    pdf
  },
  data() {
    return {
        //pdf预览
        titles:'工商表格预览',
      url: '',
      viewVisible: false,
      pageNum: 1,
      pageTotalNum: 1,
      pageRotate: 0,
      // 加载进度
      loadedRatio: 0,
      curPageNum: 0,
      closeDialog: false,

       baseImgPath:"/eladmin/api/files/showTxt?imgPath=",
      filename8:'',
      queryparams:{
       selfCode: this.$cache.local.getJSON('employednewlist').selfCode,
       selfId:this.$cache.local.getJSON('employednewlist').selfId
      }
    };
  },
  mounted(){
    this.getWords();
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

    getWords(){
      console.log(this.queryparams);
      getWord(this.queryparams).then(res=>{
          this.titles = '正在预览' + res[0].fileName8;
          this.viewVisible = true;
          this.url=pdf.createLoadingTask({ url:this.baseImgPath + res[0].fileName8,CMapReaderFactory,cMapPacked: true });
      });
    }
  }
};
</script>
<style scoped>
   ::v-deep .el-tabs__nav-wrap::after{
        background-color:rgba(0,0,0,0) !important;
   }
   .flexs{
  display: flex;
  justify-content: center;

}
</style>

