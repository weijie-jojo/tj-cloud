<template>
  <div>
      <el-steps 
        :space="1500" 
        :active="3" 
        finish-status="success"
        style="margin-left:280px;margin-top:30px;margin-bottom:80px;">
        <el-step title="个体户名称"></el-step>
        <el-step title="个体户信息"></el-step>
        <el-step title="提交"></el-step>
      </el-steps>
      <el-row class="rowCss"  style="margin-top:200px" >
        <el-col :span="8">
            <div style="font-size: 30px;">提交成功，请等待审核办理结果</div>
        </el-col>
      </el-row>
      <el-row class="rowCss"  >
        <el-col :span="8">
            <div style="font-size: 20px;">工作人员会在1-3个工作日进行审核办理</div>
        </el-col>
      </el-row>
      <div class="rowCss" style="margin-left:-280px">
          <el-button  @click="toEnd">结束</el-button> 
          <el-button type="primary" @click="toContinue" style="margin-left:110px">继续注册</el-button>
      </div>
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
      this.$router.push("employed");
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
