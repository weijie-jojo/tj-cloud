<template>
  <div class="app-container">
    <div>
      <h1>静态表格</h1>
      <div class="reimtitle">
        差旅费报销单
      </div>
      <el-form>
           <el-row>
            <el-col :span="12">
             <div>部门:<el-input ></el-input></div>
            </el-col>
            <el-col :span="12">
               <div><el-input></el-input>年</div>
            </el-col>
             
           </el-row>

      </el-form>
    </div>
     <uploadSmall @getfileName="getnames" :fileName="fileName" :fileNameOld="fileNameOld" :isDetail="isDetail"></uploadSmall>
  </div>
</template>

<script>
import uploadSmall from '../../components/douploads/uploadSmall.vue'

export default {
  name: 'test',
  components: {
    uploadSmall
  },
  data() {
    return {
    isDetail:'0',
    fileName:['20220726044928cc604a-7564-4ae2-bf6c-0367663fdf73加菲猫背景2.jpg'],
    fileNameOld:[{
      url:'/eladmin/api/files/showTxt?imgPath=20220804105302a66ef1-6104-4828-a7a1-f1944f85276120201128024046.png',
      name:'202208030630c641492f-0183-4057-a65e-2280a5eeb4eeR-C.jpg'
    }]
    }

  },
  mounted() {
  },
  methods: {
    getnames(data){
      console.log("parent");
      console.log(data)
    }
  }
}
</script>
<style>
  .reimtitle{
     font-size: 25px;
     letter-spacing: 20px;
     color: #333;
     font-weight: bold;
  }
</style>