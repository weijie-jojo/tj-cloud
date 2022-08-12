<template>
  <div class="app-container">
    <div>
    
      <div class="reimtitle">
        费用报销单
      </div>
      <el-form>
           <el-row>
            <el-col :span="12">
             <div class="combold" style="margin-bottom:5px">部门:</div>
            </el-col>
            <el-col :span="12">
               <div style="display:flex; justify-content: flex-end;margin-bottom:5px" >
                <span  class="combold">报销日期:</span>
                <span  class="combold">2022年</span>
                <span  class="combold">8月</span>
                <span  class="combold">12日</span>
               </div>
            </el-col>
             
           </el-row>
           <el-row>
            <el-col :span="10">
                 <div class="comborder" style="border-top:3px solid #333;border-left:3px solid #333">报销项目</div>
                 <div class="comborder" style="border-left:3px solid #333">报销项目</div>
                 <div class="comborder" style="border-left:3px solid #333">报销项目</div>
                 <div class="comborder" style="border-left:3px solid #333">报销项目</div>
                 <div class="comborder" style="border-left:3px solid #333">报销项目</div>
                 <div class="comborder" style="border-left:3px solid #333">报销项目</div>
            </el-col>
             <el-col :span="2">
                 <div class="comborder">张数</div>
                 <div class="comborder">张数</div>
                 <div class="comborder">张数</div>
                 <div class="comborder">张数</div>
                 <div class="comborder">张数</div>
                 <div class="comborder">张数</div>
            </el-col>
             <el-col :span="6">
                 <div class="comborder">金额</div>
                 <div class="comborder">金额</div>
                 <div class="comborder">金额</div>
                 <div class="comborder">金额</div>
                 <div class="comborder">金额</div>
                 <div class="comborder">金额</div>
            </el-col>
             <el-col :span="6">
                 <div class="comborder">备注</div>
                 <div class="comborder">备注</div>
                 <div class="comborder">备注</div>
                 <div class="comborder">备注</div>
                 <div class="comborder">备注</div>
                 <div class="comborder">备注</div>  
            </el-col>
            
           </el-row> 
           <el-row>
              <el-col :span="12">
                <div  class="comborder">合计</div>
              </el-col>
              <el-col  :span="6">
                 <div  class="comborder"></div>
              </el-col>
              <el-col  :span="6">
                   <div class="comborder"></div>
              </el-col>
           </el-row>
           <el-row>
            <el-col :span="24">
              <div class="comborder" style="text-align: left;padding-left: 4px;">总计(大写)人民币:<span>拾万仟佰拾元角分</span></div>
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
  .combold{
    font-weight: bold;
    color: #333;
  }
  .reimtitle{
     text-align: center;
     font-size: 25px;
     letter-spacing: 20px;
     color: #333;
     font-weight: bold;
  }
  .comborder1{
    height:60px;
    line-height: 60px;
    text-align: center;
    border: 1px solid #333;
  }
   .comborder{
    height:60px;
    line-height: 60px;
    text-align: center;
    border: 1px solid #333;
  }
</style>