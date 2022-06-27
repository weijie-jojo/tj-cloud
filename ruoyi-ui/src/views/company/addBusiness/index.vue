<template>
   <div class="paddingbg-s">

      <el-form ref="formbusiness" :model="formbusiness" :rules="rules" label-width="200px">
         <el-row :gutter="80">
            <el-col :span="10">
               <el-form-item label="个体户名称" prop="selfName">
                  <el-input v-model="formbusiness.selfName" ></el-input>
               </el-form-item>

               <el-form-item label="法人姓名" prop="legalPersonName">
                  <el-input v-model="formbusiness.legalPersonName" disabled></el-input>
               </el-form-item>
               <el-form-item label="营业执照" prop="fileName1">
                  <el-upload class="upload-demo" action="http://36.133.2.179:8000/api/files/doUpload"
                     :on-success="handlesuccess"
                     :on-preview="handlePreview" :on-remove="handleRemove" :before-remove="beforeRemove" multiple
                     :limit="9" :on-exceed="handleExceed" :file-list="fileName1" list-type="picture">
                     <el-button size="small" type="primary">点击上传</el-button>

                  </el-upload>
                  <el-dialog :visible.sync="dialogVisible" append-to-body>
                     <img width="100%" :src="dialogImageUrl" alt="" />
                  </el-dialog>

               </el-form-item>
            </el-col>
            <el-col :span="1">
               <div class="grid-content bg-purple" style="color:rgba(0,0,0,0)">.</div>
            </el-col>
            <el-col :span="10">
               <el-form-item label="纳税人识别号" prop="taxId">
                  <el-input v-model="formbusiness.taxId"></el-input>
               </el-form-item>
               <el-form-item label="营业期限" prop="businessTerm">
                  <el-date-picker
                     v-model="formbusiness.businessTerm"
                     type="daterange"
                     align="right"
                     unlink-panels
                     range-separator="至"
                     start-placeholder="开始日期"
                     end-placeholder="结束日期"
                     :picker-options="pickerOptions">
                  </el-date-picker>
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
import { addEmployed,updateEmployed } from "@/api/company/employed";
export default {
   data() {
      return {
         pickerOptions: {
          shortcuts: [{
            text: '最近一年',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              end.setTime(end.getTime() + 3600 * 1000 * 24* 365);
              picker.$emit('pick', [start, end]);
            }
          },
          {
            text: '最近5年',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              end.setTime(end.getTime() + 3600 * 1000 * 24* 365*5);
              picker.$emit('pick', [start, end]);
            }
          },
          {
            text: '最近30年',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              end.setTime(end.getTime() + 3600 * 1000 * 24 * 365*30);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
      //    pickerOptions(){
      //    shortcuts:[{
     
      //    // var businessTerm=new Date(this.formbusiness.businessTerm);
      //    // var endYear=businessTerm.getFullYear()+30;
      //    // console.log("businessTerm111",businessTerm.getFullYear()+'年'+businessTerm.getMonth()+'月'+businessTerm.getDate()+'日'
      //    // +'至'+endYear+'年'+businessTerm.getMonth()+'月'+businessTerm.getDate()+'日');
      //    // this.formbusiness.businessTerm=businessTerm.getFullYear()+'年'+businessTerm.getMonth()+'月'+businessTerm.getDate()+'日'
      //    // +'至'+endYear+'年'+businessTerm.getMonth()+'月'+businessTerm.getDate()+'日';
      //    // console.log("businessTerm",this.formbusiness.businessTerm)
         
      // },
         formbusiness: {
            businessStatus:"1",
            selfId:'',
            legalPersonName: '',
            selfName: '',
            taxId: '',
            fileName1: [],
            businessTerm:'',
         },
         fileName1:[],
         dialogVisible: false,
         previewPath: "",
         dialogImageUrl: '',
         rules: {
            selfName: [
               { required: true, message: '请输入个体户名称', trigger: 'blur' },
               // { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
            ],
            legalPersonName: [
               { required: true, message: '请输入法人姓名', trigger: 'blur' }
            ],
            taxId: [
               { required: true, message: '请输入纳税人识别号', trigger: 'blur' }
            ],
            fileName1: [
               { required: true, message: '请上传文件', trigger: 'change' }
            ]

         }

      };
   },
   created() {
      let list=this.$cache.local.getJSON('employednewlist');
      this.formbusiness.selfId=list.selfId;
      this.formbusiness.legalPersonName = list.legalPersonName;
   },
   beforeRouteLeave(to, from, next) {
      to.meta.keepAlive = true
      next(0)
   },

   methods: {
      
     //返回
      resetForm() {
           this.$router.back()
      },
      //返回
      onSubmit() {
         this.$refs['formbusiness'].validate(valid => {
            if (valid) {
               var startDate=new Date(this.formbusiness.businessTerm[0]);
               var endDate=new Date(this.formbusiness.businessTerm[1]);
               var businessTerm=startDate.getFullYear()+'年'+startDate.getMonth()+'月'+startDate.getDate()+'日'
                   +'至'+endDate.getFullYear()+'年'+endDate.getMonth()+'月'+endDate.getDate()+'日';
               console.log("businessTerm",businessTerm);
               this.formbusiness.fileName1=JSON.stringify(this.formbusiness.fileName1);
               this.formbusiness.businessTerm=businessTerm;
               updateEmployed(this.formbusiness).then(res => {
                  if (res != undefined) {
                     if (res.code === 200) {
                        this.$modal.msgSuccess("修改成功");
                        this.$nextTick(function () {
                           this.$router.push({ path: "/customer/manageBusiness" });
                        });
                     } else {
                        this.$modal.msgError(error);
                     }
                  }
               }).catch(error => {
                  this.$modal.msgError(error);
               });
            } else {
               this.$modal.msgError('请填写完整');
            }

         })

      },
      handlesuccess(file, fileList){
          this.formbusiness.fileName1.push(file.obj);
      },
      handleRemove(file, fileList) {
         const i = this.formbusiness.fileName1.findIndex((item) => item === fileList)
         this.formbusiness.fileName1.splice(i, 1);
      },
      handlePreview(file) {
         this.dialogImageUrl = file.url;
         this.dialogVisible = true;
      },
      handleExceed(files, fileList) {
         this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
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
   display: flex;
   justify-content: center;
   align-items: center;
}
</style>