<template>
  <div class="app-container">
    <div>
        <el-row type="flex" class="row-bg" justify="end">
                <el-col :span="6" style="display: flex;justify-content: flex-end;">
                    <el-button v-print="'#printMe'" type="primary">打印</el-button>
                </el-col>
            </el-row>
      
      <el-form id="printMe">
        <div style="height:50vh;width:100vw">
           <div class="reimtitle">
            费用报销单
          </div>
           <el-row >
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
            <el-col :span="6">
                 <div class="comborder" style="border-top:1px solid #333;border-left:1px solid #333">报销项目</div>
                 <div class="comborder1" style="border-left:1px solid #333">报销项目</div>
                 <div class="comborder1" style="border-left:1px solid #333">报销项目</div>
                 <div class="comborder1" style="border-left:1px solid #333">报销项目</div>
                 <div class="comborder1" style="border-left:1px solid #333">报销项目</div>
                 <div class="comborder1" style="border-left:1px solid #333">报销项目</div>
            </el-col>
             <el-col :span="2">
                 <div class="comborder">张数</div>
                 <div class="comborder1">张数</div>
                 <div class="comborder1">张数</div>
                 <div class="comborder1">张数</div>
                 <div class="comborder1">张数</div>
                 <div class="comborder1">张数</div>
            </el-col>
             <el-col :span="4">
                 <div class="comborder">金额</div>
                 <div class="comborder1">金额</div>
                 <div class="comborder1">金额</div>
                 <div class="comborder1">金额</div>
                 <div class="comborder1">金额</div>
                 <div class="comborder1">金额</div>
            </el-col>
             <el-col :span="12">
                 <div class="comborder">备注</div>
                 <div class="comborder1">备注</div>
                 <div class="comborder1">备注</div>
                 <div class="comborder1">备注</div>
                 <div class="comborder1">备注</div>
                 <div class="comborder1">备注</div>  
            </el-col>
            
           </el-row> 
           <el-row>
              <el-col :span="6">
                <div  class="comborder1" style="border-right:0px solid #333">合计</div>
              </el-col>
              <el-col :span="2">
                <div  class="comborder1" style="border-left:0px solid #333"></div>
              </el-col>
              <el-col  :span="4">
                 <div  class="comborder1"></div>
              </el-col>
              <el-col  :span="12">
                   <div class="comborder1"></div>
              </el-col>
           </el-row>
           <el-row>
            <el-col :span="24">
              <div class="comborder" style="text-align: left;padding-left: 4px; letter-spacing: 4px;">总计(大写)人民币:<span>拾万仟佰拾元角分</span></div>
            </el-col>
           </el-row>
           <el-row style="margin-top:20px;margin-bottom:70vh">
            <el-col :span="6">
              <div>总经理:</div>
            </el-col>
            <el-col :span="6">
             <div>财务:</div>
            </el-col>
            <el-col :span="6">
              <div>部门经理:</div>
            </el-col>
            <el-col :span="6">
              <div>报销人:</div>
            </el-col>
           </el-row>
        </div>
        <div style="height:50vh;width:100vw">
                 <el-row type="flex" class="row-bg" justify="space-around">
                <el-col :span="23">
                    <div style="font-size:20px;
                    margin-top:10px;
                    margin-bottom:20px;
                    color:blue">{{ "审批进度" }}</div>
                    <el-table border :data="checks">
                        <el-table-column prop="checkDate" label="时间" width="180" />
                        <el-table-column prop="checkUser" label="人员" width="180" />
                        <el-table-column prop="checkReasult" label="结果" />
                    </el-table>
                </el-col>

            </el-row>
        </div>
            
           <el-row>
             <el-col :span="12">
               <el-form-item label="付费凭证">
                
                <!-- transform: rotate(90deg); -->
                    <div v-for="(item,index) in fileNameOld" :key="index" style="padding-bottom: 10vh;display:flex;width:100vw;height: 50vh;align-items: center;">
                   <el-image :src="item.url" fill="scale-down" ></el-image>
                  </div>

                
                 
               </el-form-item>
             </el-col>
             <el-col :span="12">
             </el-col>
           </el-row>
      </el-form>
    </div>
     <!-- <uploadSmall @getfileName="getnames" :fileName="fileName" :fileNameOld="fileNameOld" :isDetail="isDetail"></uploadSmall> -->
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
    checks:[],
    isDetail:'0',
    fileName:['20220726044928cc604a-7564-4ae2-bf6c-0367663fdf73加菲猫背景2.jpg'],
    fileNameOld:[
      {
      url:'/eladmin/api/files/showTxt?imgPath=2022081205292d5c342d-ebea-4117-90b1-5c7534c8b950d917f156e982efe7b24d2c5f0a1313d.jpg',
      name:'2022081205292d5c342d-ebea-4117-90b1-5c7534c8b950d917f156e982efe7b24d2c5f0a1313d.jpg'
    },
    // {
    //   url:'/eladmin/api/files/showTxt?imgPath=2022081205220ec0b59a-a5d4-4119-a9a8-fb37bee194f7f8f120f6266fae88131f779f9dbb2b9.jpg',
    //   name:'2022081205220ec0b59a-a5d4-4119-a9a8-fb37bee194f7f8f120f6266fae88131f779f9dbb2b9.jpg'
    // },
    {
      url:'/eladmin/api/files/showTxt?imgPath=2022081205292d5c342d-ebea-4117-90b1-5c7534c8b950d917f156e982efe7b24d2c5f0a1313d.jpg',
      name:'2022081205292d5c342d-ebea-4117-90b1-5c7534c8b950d917f156e982efe7b24d2c5f0a1313d.jpg'
    },
    {
      url:'/eladmin/api/files/showTxt?imgPath=2022081205292d5c342d-ebea-4117-90b1-5c7534c8b950d917f156e982efe7b24d2c5f0a1313d.jpg',
      name:'2022081205292d5c342d-ebea-4117-90b1-5c7534c8b950d917f156e982efe7b24d2c5f0a1313d.jpg'
    },
     {
      url:'/eladmin/api/files/showTxt?imgPath=2022081205292d5c342d-ebea-4117-90b1-5c7534c8b950d917f156e982efe7b24d2c5f0a1313d.jpg',
      name:'2022081205292d5c342d-ebea-4117-90b1-5c7534c8b950d917f156e982efe7b24d2c5f0a1313d.jpg'
    }
    
    ]
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
    height:40px;
    line-height: 40px;
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