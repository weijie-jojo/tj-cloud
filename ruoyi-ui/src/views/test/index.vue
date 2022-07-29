<template>
  <div class="app-container">
   

    <el-upload class="upload-demo myupload" action="https://jsonplaceholder.typicode.com/posts/" :on-success="handlesuccess1"
      :on-preview="handlePreview1" :on-remove="handleRemove1" :before-remove="beforeRemove1" multiple :limit="9"
      :on-exceed="handleExceed1" :file-list="fileName" list-type="picture"
      :before-upload="beforeAvatarUpload"
      >
      <el-button size="small" type="primary">上传图片</el-button>
       <div slot="tip" class="el-upload__tip" style="color:red">仅支持jpg/png/jpeg/pdf文件，且不超过10M</div>
    </el-upload>
    <div>
      <div class="tools">
        <el-button :theme="'default'" type="submit" :title="'基础按钮'" @click.stop="prePage" class="mr10"> 上一页</el-button>
        <el-button :theme="'default'" type="submit" :title="'基础按钮'" @click.stop="nextPage" class="mr10"> 下一页</el-button>
        <div class="page">{{ pageNum }}/{{ pageTotalNum }} </div>
        <el-button :theme="'default'" type="submit" :title="'基础按钮'" @click.stop="clock" class="mr10"> 顺时针</el-button>
        <el-button :theme="'default'" type="submit" :title="'基础按钮'" @click.stop="counterClock" class="mr10"> 逆时针
        </el-button>
        <el-button :theme="'default'" type="submit" :title="'基础按钮'" @click="pdfPrintAll" class="mr10"> 打印</el-button>
      </div>
      <pdf ref="pdf" :src="url"
       :page="pageNum" :rotate="pageRotate" @progress="loadedRatio = $event"
        @page-loaded="pageLoaded($event)"
         @num-pages="pageTotalNum = $event"
          @error="pdfError($event)"
        @link-clicked="page = $event">
      </pdf>

    </div>



  </div>

</template>

<script>
// import { getWord } from "@/api/company/employed"
import crudRate from '@/api/company/rate'
import pdf from 'vue-pdf-signature'
import CMapReaderFactory from 'vue-pdf/src/CMapReaderFactory.js'

// http://storage.xuetangx.com/public_assets/xuetangx/PDF/PlayerAPI_v1.0.6.pdf
// /pdf/%E9%AA%8C%E6%94%B6%E6%8A%A5%E5%91%8A-20220321094519301.pdf
var loadingTask = '';

export default {
  name: 'test',
  components: {
    pdf
  },
  data() {
    return {

      url:pdf.createLoadingTask({ url: '/eladmin/api/files/showTxt?imgPath=2022072908090bd84950-e118-4487-9d75-c1495728867b转换结果.pdf',CMapReaderFactory,
cMapPacked: true }),
      pageNum: 1,
      pageTotalNum: 1,
      pageRotate: 0,
      // 加载进度
      loadedRatio: 0,
      curPageNum: 0,
      fileName1: [],
      fileName: [{
        name:'2022072502105ab952ca-999a-4ff0-82e6-575f8c4def13漏洞和隐患发现工作备案表.pdf',
        url:'2022072502105ab952ca-999a-4ff0-82e6-575f8c4def13漏洞和隐患发现工作备案表.pdf'
      }],
      dialogImageUrl1: '',
      dialogVisible1: false,
      closeDialog: false,
      viewVisible: true,
      src: loadingTask,
      numPages: undefined,
      value: '-1',
      expandOnClickNode: true,
      options: [],
      datas: [{
        id: 1,
        label: '云南',
        children: [{
          id: 2,
          label: '昆明',
          children: [
            { id: 3, label: '五华区', children: [{ id: 8, label: '北辰小区' }] },
            { id: 4, label: '盘龙区' }
          ]
        }]
      }, {
        id: 5,
        label: '湖南',
        children: [
          { id: 6, label: '长沙' },
          { id: 7, label: '永州' }
        ]
      }],
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      industryTypeList: [],
      industryTypes: []
    }

  },
  mounted() {
  
    // this.src.promise.then(pdf => {
    //   this.numPages = pdf.numPages
    // })
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
    // ass(){
    //   getWord({ selfCode:'TJYW0005000155' , selfId:'291'}).then(res=>{
    //       console.log(res);
    //   })
    // },
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
      console.error(33333,error)
    },

    // 打印全部
    pdfPrintAll() {
      this.$refs.pdf.print();
      //document.getElementById('printIframe').contentWindow.print();
    },
    like() {
      this.$refs.pdf.print();
    },
    preview(e) {
      docx.renderAsync(this.$refs.file.files[0], this.$refs.preview) // 渲染到页面预览
    },
    handlesuccess1(response, file, fileList) {
      this.fileName1.push(file.obj);

    },
    
    handleRemove1(file, fileList) {
      const i = this.fileName1.findIndex((item) => item === fileList);
      this.fileName1.splice(i, 1);
    },
    handlePreview1(file) {
      console.log(file);
      if(file.url.substring(file.url.lastIndexOf('.') + 1) == 'pdf'){
           this.url='';
           this.url= pdf.createLoadingTask({ url: '/eladmin/api/files/showTxt?imgPath='+file.url,CMapReaderFactory,cMapPacked: true });
      }else{
         this.dialogImageUrl1 =file.url;
         this.dialogVisible1 = true;
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
  }
}
</script>
<style>
.main-select-el-tree .el-tree-node .is-current>.el-tree-node__content {
  font-weight: bold;
  color: #409eff;
}

.main-select-el-tree .el-tree-node.is-current>.el-tree-node__content {
  font-weight: bold;
  color: #409eff;
}
</style>