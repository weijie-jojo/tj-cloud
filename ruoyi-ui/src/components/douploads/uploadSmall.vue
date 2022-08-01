<template>
  <div>
        <el-upload 
         :disabled="isDetails==1"
         class="upload-demo" action="/eladmin/api/files/doUpload" :on-success="handlesuccess"
          :on-preview="handlePreview" :on-remove="handleRemove"
            multiple :limit="9" :file-list="fileNameOlds" list-type="picture"
              :before-upload="beforeAvatarUpload">
              <el-button v-if="isDetails==0" size="small" type="primary">点击上传</el-button>
              <div  v-if="isDetails==0" slot="tip" class="el-upload__tip" style="color:red">仅支持jpg/png/jpeg/pdf文件,且不超过10M</div>
          </el-upload>
         <!-- 图片预览 -->
        <el-dialog :visible.sync="dialogVisible" append-to-body>
             <img width="100%" :src="dialogImageUrl" alt="" />
       </el-dialog>
        <!--PDF 预览-->
        <el-dialog :title="titles" :visible.sync="viewVisible" width="80%" center @close='closeDialog'>
             <div>
                <div class="tools flexs">
                    <div class="page" style="margin-right:20px;font-size: 20px;">共{{ pageNum }}/{{ pageTotalNum }}
                    </div>
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
export default {
   name:'uploadSmall',
   components: {
        pdf
    },
   data(){
     return {
      dialogVisible: false,
      dialogImageUrl: "",
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
	  fileNameOlds:this.$options.propsData.fileNameOld,
      fileNames: this.$options.propsData.fileName,
      titles: '',
      pdfList: [],  //pdf 预览
      previewList: [], //预览
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
       isDetails:this.$options.propsData.isDetail,
      
		}
   },
   watch: {
		'isDetails': 'isDetailArr',
   },
   
   props: {
     isDetail: {
      type: String,
      default: '0',
      
    },
      fileName: {
        type:Array
      },
      fileNameOld: {
        type:Array
      },
     
   },
   mounted(){
     console.log(this.fileNameOlds);
     console.log(this.fileNames);
   },
   methods:{
        isDetailArr(){
          console.log(this.isDetails);
            if(this.isDetails==1){
              //this.pdfdetail(this.url);
            } 
        },
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

         beforeAvatarUpload(file) {
            
            const isLt2M = file.size / 1024 / 1024 < 5;
            const fileSuffix = file.name.substring(file.name.lastIndexOf(".") + 1);
            const whiteList = ["jpg", "png", 'pdf', 'jpeg'];
            if (whiteList.indexOf(fileSuffix) === -1) {
              this.$alert('上传文件只能是 jpg,png,jpeg,pdf格式', '提示', {
                confirmButtonText: '确定',
              });
                return false;
            }
            if (!isLt2M) {
              this.$alert('上传文件大小不能超过 10MB!', '提示', {
                 confirmButtonText: '确定',
              });
                return false;
            }
            return fileSuffix & isLt2M;

        },
        handlesuccess(file, fileList) {
            this.fileNames.push(file.obj);
            this.$emit('getfileName',this.fileNames);
        },
        handleRemove(file, fileList) {
            const i = this.fileNames.findIndex((item) => item === fileList);
            this.fileNames.splice(i, 1);
            this.$emit('getfileName',this.fileNames);
        },
        handlePreview(file) {
            if (file.hasOwnProperty('response')) {
                if (file.response.obj.substring(file.response.obj.lastIndexOf('.') + 1) == 'pdf') {
                    this.titles = '正在预览' + file.response.obj;
                    this.viewVisible = true;
                        this.url= pdf.createLoadingTask({ url: this.baseImgPath + file.response.obj,CMapReaderFactory,cMapPacked: true });
                } else {
                    this.dialogImageUrl = file.url;
                    this.dialogVisible = true;
                }
            } else {
                if (file.url.substring(file.url.lastIndexOf('.') + 1) == 'pdf') {
                    this.titles = '正在预览' + file.url;
                    this.viewVisible = true;
                    this.url = file.url;
                } else {
                    this.dialogImageUrl = file.url;
                    this.dialogVisible = true;
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
.upload-demo{
  width:88%;
}
</style>