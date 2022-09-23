<template>
  <div>
    <div>
        <el-steps 
        align-center
        :active="shenmsgs" 
        finish-status="success"
        style="padding-left:2%;margin-top:30px;margin-bottom:20px;">
        <el-step title="名称审核完成"></el-step>
        <el-step title="信息审核完成"></el-step>
        <el-step title="工商办理完成"></el-step>
        <el-step title="税务办理完成"></el-step>
        <el-step title="银行办理完成"></el-step>
        <el-step title="注册确认完成"></el-step>
       </el-steps>
      <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="8"></el-col>
        <el-col :span='8' class="flexs" style=" margin-top: 50px;">
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
  name:'Result',
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
    this.obj = this.$cache.local.getJSON('tj-resultBack');
    this.shenmsg = this.obj.resmsg;
    switch(this.shenmsg){
      case '名称审核完成':
       this.shenmsgs=0;
      break;
       case '信息审核完成':
       this.shenmsgs=1;
      break;
       case '工商办理完成':
       this.shenmsgs=2;
      break;
       case '税务办理完成':
       this.shenmsgs=3;
      break;
       case '银行办理完成':
       this.shenmsgs=4;
      break;
       case '注册确认完成':
       this.shenmsgs=5;
      break;

    }
  },
  methods: {
    resetForm() {

    },
    submitForm() {
      this.$tab.closeOpenPage({path:this.$cache.local.getJSON('tj-resultBack').backUrl}).then(() => {
         this.$tab.refreshPage({path:this.$cache.local.getJSON('tj-resultBack').backUrl,name:this.$cache.local.getJSON('tj-resultBack').backName})
      })
    }
  }
}
</script>

<style>


.flexs {
   
  display: flex;
  justify-content: center;
}
</style>