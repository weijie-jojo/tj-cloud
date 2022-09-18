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
        <el-step title="结算信息"></el-step>
        <el-step title="提交审核"></el-step>
      </el-steps>
      <el-row type="flex" class="row-bg rowCss combottom" justify="space-around" >
        <el-col :span="8">
            <div style="font-size: 30px;text-align: center;">提交成功，请等待审核办理</div>
        </el-col>
      </el-row>

       <el-row type="flex" class="row-bg rowCss combottom" justify="space-around" >
        <el-col :span="8">
        <div style="display:flex;justify-content: center;">
               <el-button style="width:140px" type="success"  @click="toEnd">完成</el-button> 
        </div>
       </el-col>
      </el-row>
      <el-row class="rowCss"  >
      </el-row>
</div>
</template>
<script>
export default {
  name:'RegSuccess',
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
    toEnd(){
     this.$tab.closeOpenPage({path:"/tg-business/reg/employed"}).then(() => {
      this.$tab.refreshPage({ path: "/tg-business/reg/employed", name: "Employed" });     
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
