<template>
    <div class="flexsbg">
    <div style="width:100%">
         <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="8"></el-col>
        <el-col :span='8' class="flexs">
        <div style="text-align:center;font-size: 30px;color:rgb(41,36,33,0.8) ;">{{shenmsg}}<div style="margin-bottom:40px"></div>
         <el-button style="width:100px"  type="primary" @click="submitForm">完成</el-button>
         </div>
        </el-col>
        <el-col :span="8"></el-col>
      </el-row>
      </div>
    </div>
</template>

<script>
export default {
      components: {},
  props: [],
  data() {
    return {
      shenmsg:'',
      obj:{

      }
    }
  },
  watch: {
    
  },
  created() {},
  mounted() {
    this.obj= this.$cache.local.getJSON('successNew');
    this.shenmsg=this.obj.resmsg;
  },
  methods: {
    resetForm(){
       
    },
    submitForm(){
       this.$tab.closeOpenPage({ path: this.obj.backUrl});
     }
    
  }
}
</script>

<style>
.flexsbg{
    width: 100%;
    height: 100vh;
      display: flex;
     justify-content: center;
     align-items: center;
}
  .flexs{
   
     display: flex;
     justify-content: center;
  }
</style>