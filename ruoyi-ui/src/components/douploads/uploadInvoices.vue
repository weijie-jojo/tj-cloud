<template>
  <div>
   
      <el-upload ref="uploads" :disabled="isDetails == 1" class="upload-demo" action="/eladmin/api/files/doUpload"
      :on-success="handlesuccess" :on-preview="handlePreview" :on-remove="handleRemove" multiple 
      :file-list="fileNameOlds" list-type="picture" :before-upload="beforeAvatarUpload">
      <el-button v-if="isDetails == 0" size="small" type="primary" style="width:80px;">点击上传</el-button>
     
      <div v-if="isDetails == 0" slot="tip" class="el-upload__tip">仅支持jpg/png/jpeg/pdf文件,且不超过10M</div>
    </el-upload>
    <div v-if="isDetails == 0" style="position: absolute;left: 100px;
    top: -12px;" :class="isDetail==0 ?'brrs':'arrs'">
     <el-tooltip  style="font-size: 20px;"
                  class="item"
                  effect="dark"
                  content="点击图标,查看上传格式须知"
                  placement="top-start" >
       <i class="header-icon el-icon-info" @click="uploadsCheck"></i>
                </el-tooltip>
    
   </div>
   
  
    
    


    <!-- 图片预览 -->
    <!-- <el-dialog :visible.sync="dialogVisible" append-to-body>
       <el-image width="100%" ref="preview" :src="dialogImageUrl" :preview-src-list="dialogImageUrls"></el-image>
    </el-dialog> -->
    <el-image-viewer v-if="showViewer" :on-close="closeViewer" :url-list="dialogImageUrls" />
    <!--PDF 预览-->
    <el-dialog :title="titles" :visible.sync="viewVisible" width="80%" center @close='closeDialog == false'>
      <div>
        <div class="tools flexs">
          <el-button class="page" style="margin-right:20px;font-size: 20px;">共{{ pageNum }}/{{ pageTotalNum }}
          </el-button>
          <el-button :theme="'default'" type="submit" @click.stop="prePage" class="mr10"> 上一页</el-button>
          <el-button :theme="'default'" type="submit" @click.stop="nextPage" class="mr10"> 下一页</el-button>
          <!-- <el-button :theme="'default'" type="submit" @click.stop="clock" class="mr10"> 顺时针</el-button>
          <el-button :theme="'default'" type="submit" @click.stop="counterClock" class="mr10"> 逆时针</el-button> -->

        </div>
        <div class="tools flexs" style="font-size: 20px;margin-top: 40px;" v-if="errOk">pdf文件已损坏</div>
        <pdf ref="pdf" :src="url" :page="pageNum" :rotate="pageRotate" @progress="loadedRatio = $event"
          @page-loaded="pageLoaded($event)" @num-pages="pageTotalNum = $event" @error="pdfError($event)"
          @link-clicked="page = $event">
        </pdf>


      </div>
    </el-dialog>
    <el-dialog title="影像图片上传要求" :visible.sync="viewVisible1" width="60%" center @close='closeDialog1 == false'>
           <div style="color:red;font-size:20px">
            注意:如果上传凭证或影像为长图 ,请旋转90度,变为横图去上传,这样打印不会失真!!!
           </div>
           <el-row>
            <el-col :span="8" style="display:flex;justify-content:flex-end;position: relative;">
              <el-image style="" :src="baseImgPath+oks1" fill="scale-down"></el-image>
            </el-col>
            <el-col :span="12" style="display:flex;justify-content:flex-end;position: relative;">
              <!-- <el-image style="width:70px;height:70px;position:absolute;left:11px;top:20vh" :src="baseImgPath+oks2"></el-image> -->
              <el-image  :src="baseImgPath+oks1" style="transform: rotate(90deg); " fill="scale-down"></el-image>
            </el-col>
           </el-row>
           <div style="display:flex;justify-content:center;position: relative;">
            <el-button size="mini"  type="danger" @click='closeAdd'>关闭</el-button>
           </div>
           
           
           
    </el-dialog>
  </div>
</template>

<script>
import ElImageViewer from 'element-ui/packages/image/src/image-viewer'
import pdf from 'vue-pdf-signature'
import CMapReaderFactory from 'vue-pdf-signature/src/CMapReaderFactory.js'

export default {
  name: 'uploadInvoices',
  components: {
    pdf,
    ElImageViewer
  },
  data() {
    return {
      oks1:'2022091405333b7989eb-57c7-4aff-b483-611b7c044448koo.jpg',
      oks2:'2022091404410b00fef8-df2c-4a16-9a66-a462ef644a3d下载.jpg',  
      oks:false,
      showViewer: false,
      errOk: false,
      dialogVisible: false,
      dialogImageUrl: "",
      dialogImageUrls: [],
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
      fileNameOlds: this.$options.propsData.fileNameOld,
      fileNames: this.$options.propsData.fileName,
      titles: '',
      pdfList: [],  //pdf 预览
      previewList: [], //预览
      //pdf预览
      url: '',
      viewVisible: false,
      viewVisible1: false,
      pageNum: 1,
      pageTotalNum: 1,
      pageRotate: 0,
      // 加载进度
      loadedRatio: 0,
      curPageNum: 0,
      closeDialog: false,
      closeDialog1: false,
      isDetails: this.$options.propsData.isDetail,

    }
  },


  props: {
    isDetail: {
      type: String,
      default: '0',

    },
    fileName: {
      type: Array,
      default: []
    },
    fileNameOld: {
      type: Array,
      default: []
    },

  },
  watch: {
    'fileNameOlds': {
      deep: true,  // 深度监听
      handler(newVal, oldVal) {
       console.log(111,newVal); 
       console.log(222,oldVal); 
      }
    }
  },
  mounted() {
   
    this.pdfIconChange();
  },
  methods: {
    closeAdd(){
       this.viewVisible1=false;
    },
    //上传格式须知
    uploadsCheck(){
        this.viewVisible1=true;
    },
    // 导出错误详情
    async uploadAll() {
        this.$nextTick(()=>{
          this.fileNameOlds=this.$refs.uploads.uploadFiles;
        })
      if(this.fileNameOlds.length==0){
          this.$alert('下载必须至少有一份文件', '系统提示', {
          confirmButtonText: '确定',
           type: 'warning'
        });
      }else{
          let arr=this.fileNameOlds;
          console.log(this.fileNameOlds);
        this.$modal.loading("正在下载文件，请稍后...");
        for(let i in arr){
        var name = arr[i].name;
        var url = this.baseImgPath+arr[i].name;
        //var suffix = url.substring(url.lastIndexOf("."), url.length);
        const a = document.createElement('a')
        
         a.setAttribute('download', name);
         a.setAttribute('target', '_blank');
         a.setAttribute('href', url)
         a.click()
        }
       this.$modal.closeLoading();
      
      }
      

    },

    //获取已经保存数据库的值
    getSrcList(val) {
      if(Array.isArray(val)){
        this.fileNames = val;
      }else{
        this.fileNames=[];
      }
    },
    //关闭图片预览
    closeViewer() {
      this.showViewer = false
    },
    //pdf图标优化
    pdfIconChange() {
      this.$nextTick(()=>{
        this.fileNameOlds=this.$refs.uploads.uploadFiles;
        if (this.fileNameOld.length > 0) {
        let arr = this.fileNameOlds;

        for (let i in arr) {
          if (arr[i].url.substring(arr[i].url.lastIndexOf('.') + 1) == 'pdf') {
            arr[i].url = this.baseImgPath + '202208230415670439e1-2b78-46bd-b395-a7826db56f91logo.png';
          }
        }
        this.fileNameOlds = arr;
      }
      });
     
    },
    //pdf弹框
    pdfdetail(i) {
      this.titles = '正在预览' + i;
      this.viewVisible = true;
      this.url = pdf.createLoadingTask({ url: this.baseImgPath + i, CMapReaderFactory, cMapPacked: true });
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
      console.error(error);
      if (error) {
        this.errOk = true;
      }
    },

    beforeAvatarUpload(file, fileList) {
      const isLt2M = file.size / 1024 / 1024 < 5;
      const fileSuffix = file.name.substring(file.name.lastIndexOf(".") + 1);
      const whiteList = ["jpg", "png", 'pdf', 'jpeg'];
      
      if (whiteList.indexOf(fileSuffix) === -1) {
        this.$alert('上传文件只能是 jpg,png,jpeg,pdf格式', '系统提示', {
          confirmButtonText: '确定',
           type: 'warning'
        });

        return false;
      }
      if (!isLt2M) {
        this.$alert('上传文件大小不能超过 10MB!', '系统提示', {
          confirmButtonText: '确定',

          type: 'warning'
        });

        return false;
      }
     
      return fileSuffix & isLt2M;


    },
    handlesuccess(file, fileList) {
      
      this.fileNames.push(file.obj);
      fileList.name = file.obj;
      if (fileList.name.substring(fileList.name.lastIndexOf('.') + 1) == 'pdf') {
        fileList.url = this.baseImgPath + '202208230415670439e1-2b78-46bd-b395-a7826db56f91logo.png';
      }

      this.$emit('getfileName', this.fileNames);

    },
    handleRemove(file, fileList) {
      const i = this.fileNames.findIndex((item) => item === fileList);
      this.fileNames.splice(i, 1);
      this.$emit('getfileName', this.fileNames);

    },
    handlePreview(file) {
      this.dialogImageUrls = [];
      this.errOk = false;
      if (file.hasOwnProperty('response')) {
        if (file.response.obj.substring(file.response.obj.lastIndexOf('.') + 1) == 'pdf') {
          this.titles = '正在预览' + file.response.obj;
          this.viewVisible = true;
          this.url = pdf.createLoadingTask({ url: this.baseImgPath + file.response.obj, CMapReaderFactory, cMapPacked: true });
        } else {
          this.dialogImageUrls.push(this.baseImgPath + file.name);
          this.showViewer = true
        }
      } else {
        if (file.name.substring(file.name.lastIndexOf('.') + 1) == 'pdf') {
          this.titles = '正在预览' + file.name;
          this.viewVisible = true;
          this.url = pdf.createLoadingTask({ url: this.baseImgPath + file.name, CMapReaderFactory, cMapPacked: true });;
        } else {
          this.dialogImageUrls.push(this.baseImgPath + file.name);
          this.showViewer = true
        }
      }
    },
  }
}
</script>

<style>
.flexs {
  display: flex;
  justify-content: center;
  align-items: center;
}
.imgok{
  width: 100%;
  height: 300px;
}
.imgok1{
  width: 50%;
}

.upload-demo {
  width: 88%;
}

/* 大图预览的关闭图标 改为大一点的白色 */
.el-image-viewer__btn.el-image-viewer__close {
  color: #FFF;
  border: #FFF;
  opacity: unset;
  top: 50px;
  right: 50px;
  width: 50px;
  height: 50px;
  font-size: 50px;
}
.arrs{
  top: 0px !important;
  margin-top: 0px;
  left: 0px !important;
}
.brrs{
  margin-top: 15px;
}
</style>