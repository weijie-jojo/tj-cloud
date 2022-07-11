<template>
  <div>
     <el-upload class="upload-demo myupload" action="https://jsonplaceholder.typicode.com/posts/"
         :on-success="handlesuccess1" :on-preview="handlePreview1" :on-remove="handleRemove1"
         :before-remove="beforeRemove1" multiple :limit="9" :on-exceed="handleExceed1"
        :file-list="fileName" 
        list-type="picture">
           <el-button size="small" type="primary">上传图片</el-button>
        </el-upload>
   <!--PDF 预览-->
 <el-dialog 
 title="pdf预览"
  :visible.sync="viewVisible" width="80%" center
   @close='closeDialog'>
    <div >
    <pdf
      v-for="i in numPages"
      :key="i"
      :src="src"
      :page="i"
    />
  </div>
</el-dialog>
    
  </div>
</template>

<script>

import pdf from 'vue-pdf'
// /pdf/%E9%AA%8C%E6%94%B6%E6%8A%A5%E5%91%8A-20220321094519301.pdf
var loadingTask = pdf.createLoadingTask('http://storage.xuetangx.com/public_assets/xuetangx/PDF/PlayerAPI_v1.0.6.pdf', {withCredentials: false});

export default {
  name: 'HomedDd',
  components: {
    pdf
  },
  data() {
    return {
      fileName1:[],
      fileName:[],
      dialogImageUrl1:'',
      dialogVisible1:false,
      closeDialog:false,
      viewVisible:true,
      src: loadingTask,
      numPages: undefined
    }
  },
  mounted() {
    this.src.promise.then(pdf => {
      this.numPages = pdf.numPages
    })
  },
  methods:{
    renderFileIcon() {
	  //找出所有文件图标的class
	  this.$nextTick(() => {
	    let fileElementList = document.getElementsByClassName('el-upload-list__item-name');
	    if (fileElementList && fileElementList.length > 0) {
	      for (let ele of fileElementList) {
	        let fileName = ele.innerText;
	        //获取文件名后缀
	        let fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
	        let iconElement = ele.getElementsByTagName('i')[0];
	        if (['png','jpg','jpeg',".gif",'PNG','JPG','JPEG',"GIF"].indexOf(fileType) != -1) {
              iconElement.className = "imgicon-img" // 图⽚，动图
            } else if (['mp4','3gp','avi',"flv",'MP4','3GP','AVI',"FLV"].indexOf(fileType) != -1) {
              iconElement.className = 'imgicon-video' // 视频
            } else if (['doc','docx','DOC','DOCX'].indexOf(fileType) != -1) {
              iconElement.className = 'imgicon-docx' // 文档
            } else if (['xls','xlsx','XLS','XLSX'].indexOf(fileType) != -1) {
              iconElement.className = 'imgicon-xlsx' // 表格
            } else if (['ppt','pptx','PPT','PPTX'].indexOf(fileType) != -1) {
              iconElement.className = 'imgicon-pptx' // PPT
            } else if (['zip','ZIP'].indexOf(fileType) != -1) {
              iconElement.className = 'imgicon-zip' // 压缩包
            } else if (['pdf','PDF'].indexOf(fileType) != -1) {
              iconElement.className = 'imgicon-pdf' // PDF
            } else {
              iconElement.className = 'imgicon-default' //默认图标
            }
	      }
	    }
	    })
	   },

       handlesuccess1(response,file, fileList) {
            this.fileName1.push(file.obj);
              var houzhui = file.name.split('.') // 获取上传文件的后缀
      var title = document.getElementsByClassName('el-icon-document')[fileList.length-1] 
      // [fileList.length-1]  这个需要特别注意，需要查看当前页面不上传之前有多少个后才能根据具体情况具体分析
      if (houzhui[1]=='png'||houzhui[1]=='jpg'||houzhui[1]=='jpeg'||houzhui[1]=='gif') { 
        title.classList.add('el-icon-picture-outline') // 图片，动图
      } else if (houzhui[1]=='MP4'||houzhui[1]=='mp4'||houzhui[1]=='avi') {
        title.classList.add('el-icon-video-camera') // 视频
      } else if(houzhui[1]=='pdf') {
        title.classList.add('el-icon-document') // 其他默认文档
      }
           // this.renderFileIcon();
        },
        handleRemove1(file, fileList) {
            const i = this.fileName1.findIndex((item) => item === fileList);
            this.fileName1.splice(i, 1);
        },
        handlePreview1(file) {
            this.dialogImageUrl1 = file.url;
            this.dialogVisible1 = true;
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
<style scoped>
  
	.myupload >>> .imgicon-video {
	  display: inline-block;
	  width: 20px;
	  margin-bottom: -3px;
	  height: 20px;
	  background-size: 100% 100%;
	  margin-right: 10px;
	  background-image: url("~@/assets/images/pdf.jpg");
	}
	.myupload >>> .imgicon-img {
	  display: inline-block;
	  width: 20px;
	  margin-bottom: -3px;
	  height: 20px;
	  background-size: 100% 100%;
	  margin-right: 10px;
	  background-image: url("~@/assets/images/pdf.jpg");
	}
	.myupload >>> .imgicon-pdf {
	  display: inline-block;
	  width: 20px;
	  margin-bottom: -3px;
	  height: 20px;
	  background-size: 100% 100%;
	  margin-right: 10px;
	  background-image: url("~@/assets/images/pdf.jpg") !important;
	}
	.myupload >>> .imgicon-docx {
	  display: inline-block;
	  width: 20px;
	  margin-bottom: -3px;
	  height: 20px;
	  background-size: 100% 100%;
	  margin-right: 10px;
	  background-image: url("~@/assets/images/pdf.jpg") !important;
	}
	.myupload >>> .imgicon-zip {
	  display: inline-block;
	  width: 20px;
	  margin-bottom: -3px;
	  height: 20px;
	  background-size: 100% 100%;
	  margin-right: 10px;
	  background-image: url("~@/assets/images/pdf.jpg") !important;
	}
	.myupload >>> .imgicon-pptx {
	  display: inline-block;
	  width: 20px;
	  margin-bottom: -3px;
	  height: 20px;
	  background-size: 100% 100%;
	  margin-right: 10px;
	  background-image: url("~@/assets/images/pdf.jpg") !important;
	}
	.myupload >>> .imgicon-xlsx {
	  display: inline-block;
	  width: 20px;
	  margin-bottom: -3px;
	  height: 20px;
	  background-size: 100% 100%;
	  margin-right: 10px;
	  background-image: url("~@/assets/images/pdf.jpg") !important;
	}
	.myupload >>> .imgicon-default {
	  display: inline-block;
	  width: 20px;
	  margin-bottom: -3px;
	  height: 20px;
	  background-size: 100% 100%;
	  margin-right: 10px;
	  background-image: url("~@/assets/images/pdf.jpg") !important;
	}
</style>
