<template>
  <div>
    <div>
        <el-steps 
        align-center
        :active="shenmsgs" 
        finish-status="success"
        style="padding-left:2%;margin-top:30px;margin-bottom:20px;">
        <el-step title="项目填写完成"></el-step>
        <el-step title="项目审核完成"></el-step>
        <el-step title="合同审核完成"></el-step>
        <el-step title="验收审核完成"></el-step>
        <el-step title="完税审核完成"></el-step>
       
       </el-steps>
      <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="8"></el-col>
        <el-col :span='8' class="flexs">
          <div style="text-align:center;font-size: 30px;color:rgb(41,36,33,0.8) ;">{{ shenmsg }}
          <div
              style="margin-bottom:40px"></div>
            <el-button style="width:100px" type="success"  @click="submitForm">完成</el-button>
          </div>
        </el-col>
        <el-col :span="8"></el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name:'Success',
  components: {},
  props: [],
  data() {
    return {
      shenmsgs:0,
      shenmsg: '',
      obj: {

      }
    }
  },
  watch: {

  },
  created() { },
  mounted() {
    this.obj = this.$cache.local.getJSON('successProject');
    this.shenmsg = this.obj.resmsg;
    switch(this.shenmsg){
      case '项目填写完成':
       this.shenmsgs=0;
      break;
       case '项目审核完成':
       this.shenmsgs=1;
      break;
       case '合同审核完成':
       this.shenmsgs=2;
      break;
       case '验收审核完成':
       this.shenmsgs=3;
      break;
       case '完税审核完成':
       this.shenmsgs=4;
      break;
      

    }
  },
  methods: {
    resetForm() {

    },
    submitForm() {
      this.$tab.closeOpenPage({path:this.$cache.local.getJSON('successProject').backUrl}).then(() => {
         this.$tab.refreshPage({path:this.$cache.local.getJSON('successProject').backUrl,name:this.$cache.local.getJSON('successProject').backName})
      })
    }
  }
}
</script>

<style>
.flexsbg {
  width: 100%;
  margin-top: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.flexs {
   margin-top: 50px;
  display: flex;
  justify-content: center;
}
</style>