<template>
   <div class="paddingbg-s">

      <el-form ref="form" :model="form" label-width="200px">
         <el-row :gutter="80">
            <el-col :span="10">
               <el-form-item label="个体户名称">
                  <el-input v-model="form.gname"></el-input>
               </el-form-item>
               <el-form-item label="法人姓名">
                  <el-input v-model="form.faname"></el-input>
               </el-form-item>
               <el-form-item label="营业执照">
                  <el-upload class="upload-demo" action="https://jsonplaceholder.typicode.com/posts/"
                     :on-preview="handlePreview" :on-remove="handleRemove" :before-remove="beforeRemove" multiple
                     :limit="3" :on-exceed="handleExceed" :file-list="fileList">
                     <el-button size="small" type="primary">点击上传</el-button>

                  </el-upload>
               </el-form-item>
            </el-col>
            <el-col :span="10">
               <el-form-item label="纳税人识别号">
                  <el-input v-model="form.sname"></el-input>
               </el-form-item>
            </el-col>
         </el-row>

      </el-form>
      <div class="footers grid-content">
         <el-footer>
            <el-button type="danger" @click="resetForm">返回</el-button>
            <el-button type="primary" @click="onSubmit">提交</el-button>
         </el-footer>
      </div>
 </div>
</template>

<script>
export default {
   data() {
      return {
         form: {
            sname:"",
            faname:'',
            gname: '',
            region: '',
            date1: '',
            date2: '',
            delivery: false,
            type: [],
            resource: '',
            desc: ''
         },
         fileList: []
      };
   },
   created(){
      this.form.faname=this.$route.query.name;
   },
    beforeRouteLeave(to,from,next){
      to.meta.keepAlive = true
      next(0)
    },
  
   methods: {
      resetForm(){
        // this.$router.push({ path: "/customer/addEmployedInfo"});
       // this.$tab.openPage("用户管理", "/customer/addEmployedInfo");
       this.$router.back()
     },
      onSubmit() {
        this.$nextTick(function(){
           this.$router.push({ path: "/customer/addTax",query:{name:this.form.faname,gname:this.form.gname,sname:this.form.sname} });
      });
      },
      handleRemove(file, fileList) {
         console.log(file, fileList);
      },
      handlePreview(file) {
         console.log(file);
      },
      handleExceed(files, fileList) {
         this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
      beforeRemove(file, fileList) {
         return this.$confirm(`确定移除 ${file.name}？`);
      }
   }
}
</script>

<style>
.paddingbg-s {
   padding-top: 15px;
}

.footers {

   width: 80%;
   position: fixed;
   bottom: 0;
   display: flex;
   justify-content: center;
   align-items: center;
}
</style>