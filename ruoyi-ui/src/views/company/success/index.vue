<template>
  <div>
      <el-steps 
        align-center
        :active="5" 
        finish-status="success"
        style="padding-left:2%;margin-top:30px;margin-bottom:20px;">
       <el-step title="个体户名称"></el-step>
        <el-step title="申请信息"></el-step>
        <el-step title="基本情况"></el-step>
        <el-step title="经营者信息"></el-step>
        <el-step title="业务信息"></el-step>
        <el-step title="提交审核"></el-step>
      </el-steps>
      <el-row type="flex" class="row-bg rowCss combottom" justify="space-around" >
        <el-col :span="8">
            <div style="font-size: 30px;text-align: center;">提交成功，请等待审核办理结果</div>
        </el-col>
      </el-row>

       <el-row type="flex" class="row-bg rowCss combottom" justify="space-around" >
        <el-col :span="8">
        <div style="display:flex;justify-content: center;">
               <el-button style="width:140px" type="success"  @click="toEnd">结束</el-button> 
               <!-- <el-button type="primary" @click="toContinue" style="margin-left:20px;width:140px">继续注册</el-button> -->
        </div>
         
        </el-col>
      </el-row>
      <el-row class="rowCss"  >
        <!-- <el-col :span="8">
            <div style="font-size: 20px;">工作人员会在1-3个工作日进行审核办理</div>
        </el-col> -->
      </el-row>
      <!-- <div class="rowCss" style="margin-left:-280px">
          <el-button  @click="toEnd">结束</el-button> 
          <el-button type="primary" @click="toContinue" style="margin-left:110px">继续注册</el-button>
      </div> -->
  </div>
 
</template>
<script>
import crudReview from '@/api/company/review'

export default {
  components: {},
  props: [],
  data() {
    return {

    }
  },
  watch: {
    
  },
  created() {},
  mounted() {
    
  },
  methods: {
    toContinue(){
      this.$router.push("addEmployedName");
    },
    toEnd(){
      this.$tab.closeOpenPage({ path:"/company/customer/employed" }).then(()=>{
        this.$tab.refreshPage({ path :"/company/customer/employed"})
      })
    },
  }
}

</script>
<style rel="stylesheet/scss" lang="scss" scoped>
    .title{
      
      margin-bottom: 200px;
      width: 5000px;
    }
   .rowCss{
      display: flex;
      justify-content: center;
      margin-top: 50px;
      
   }
</style>
