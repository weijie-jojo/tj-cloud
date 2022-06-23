<template>
   <div class="paddingbg-s">

      <el-form ref="formBank" :model="formBank" :rules="rules" label-width="200px">
         <el-row :gutter="80">
            <el-col :span="10">
               <el-form-item label="个体户名称" prop="selfName">
                  <el-input v-model="formBank.selfName" disabled></el-input>
               </el-form-item>

               <el-form-item label="法人姓名" prop="legalPersonName">
                  <el-input v-model="formBank.legalPersonName" disabled></el-input>
               </el-form-item>

            </el-col>
            <el-col :span="1">
               <div class="grid-content bg-purple" style="color:rgba(0,0,0,0)"> <br></div>
            </el-col>
            <el-col :span="10">
               <el-form-item label="纳税人识别号" prop="taxId">
                  <el-input v-model="formBank.taxId" disabled></el-input>
               </el-form-item>
            </el-col>
         </el-row>

         <el-row>
            <el-col :span="7">
               <div class="bankno">银行账号</div>
            </el-col>
         </el-row>
         <el-row class="paddingbg-s">
            <el-col :span="3">
               <div class="grid-content bg-purple" style="color:rgba(0,0,0,0)"> <br></div>
            </el-col>
            <el-col :span="19" style="margin-left: -11px;">
               <el-collapse v-model="activeNames" @change="handleChange">
                  <el-collapse-item title="私人账号" name="1">
                     <el-row>
                        <el-col :span="10">
                           <el-form-item label="账号名称" prop="legalPersonName">
                              <el-input v-model="formBank.legalPersonName" disabled></el-input>
                           </el-form-item>

                           <el-form-item label="开户银行" prop="privateDepositBank">
                              <el-input v-model="formBank.privateDepositBank" disabled></el-input>
                           </el-form-item>

                        </el-col>

                        <el-col :span="10">
                           <el-form-item label="银行账号" prop="privateAccountNumber">
                              <el-input v-model="formBank.privateAccountNumber" disabled></el-input>
                           </el-form-item>
                        </el-col>
                        <el-col :span="4">
                           <div class="grid-content bg-purple" style="color:rgba(0,0,0,0)"> <br></div>
                        </el-col>
                     </el-row>
                  </el-collapse-item>
                  <el-collapse-item title="对公基本户" name="2">
                     <el-row>
                        <el-col :span="10">
                           <el-form-item label="账号名称" prop="selfName">
                              <el-input v-model="formBank.selfName" disabled></el-input>
                           </el-form-item>

                           <el-form-item label="开户银行" prop="publicDepositBank1">
                              <el-input v-model="formBank.publicDepositBank1"></el-input>
                           </el-form-item>

                        </el-col>

                        <el-col :span="10">
                           <el-form-item label="银行账号" prop="publicAccountNumber1">
                              <el-input v-model="formBank.publicAccountNumber1"></el-input>
                           </el-form-item>
                        </el-col>
                        <el-col :span="4">
                           <div class="grid-content bg-purple" style="color:rgba(0,0,0,0)"> <br></div>
                        </el-col>
                     </el-row>
                  </el-collapse-item>
                  <el-collapse-item title="对公一般户" name="3">
                     <el-row>
                        <el-col :span="10">
                           <el-form-item label="账号名称" prop="selfName">
                              <el-input v-model="formBank.selfName" disabled></el-input>
                           </el-form-item>

                           <el-form-item label="开户银行" prop="publicDepositBank2">
                              <el-input v-model="formBank.publicDepositBank2"></el-input>
                           </el-form-item>

                        </el-col>

                        <el-col :span="10">
                           <el-form-item label="银行账号" prop="publicAccountNumber2">
                              <el-input v-model="formBank.publicAccountNumber2"></el-input>
                           </el-form-item>
                        </el-col>
                        <el-col :span="4">
                           <div class="grid-content bg-purple" style="color:rgba(0,0,0,0)"> <br></div>
                        </el-col>
                     </el-row>
                  </el-collapse-item>
               </el-collapse>
            </el-col>
            <el-col :span="2">
               <div class="grid-content bg-purple" style="color:rgba(0,0,0,0)"> <br></div>
            </el-col>
         </el-row>
         <el-row class="paddingbg-s">
            <el-col :span="7">
               <div class="bankno">纳税账号</div>
            </el-col>
         </el-row>

         <el-row :gutter="80" class="paddingbg-s">
            <el-col :span="10">
               <el-form-item label="户名" prop="accountName">
                  <!-- <el-input v-model="formBank.accountName"></el-input> -->
                  <el-select v-model="formBank.accountName" filterable placeholder="请选择">
                     <el-option v-for="item in accountName_options" :key="item.value" :label="item.value" :value="item.value">
                     </el-option>
                  </el-select>
               </el-form-item>

               <el-form-item label="开户银行" prop="publicDepositBank3">
                  <!-- <el-input v-model="formBank.publicDepositBank3"></el-input> -->
                  <el-select v-model="formBank.publicDepositBank3" filterable placeholder="请选择">
                     <el-option v-for="item in publicDepositBank3_options" :key="item.value" :label="item.value"
                        :value="item.value">
                     </el-option>
                  </el-select>


               </el-form-item>
               <el-form-item label="纳税委托协议" prop="fileList2">
                  <el-upload class="upload-demo" action="http://36.133.2.179:8000/api/files/doUpload"
                     :on-success="handlesuccess1" :on-preview="handlePreview1" :on-remove="handleRemove1"
                     :before-remove="beforeRemove1" multiple :limit="9" :on-exceed="handleExceed1"
                     :file-list="fileName3" list-type="picture">
                     <el-button size="small" type="primary">点击上传</el-button>
                  </el-upload>
                  <el-dialog :visible.sync="dialogVisible1" append-to-body>
                     <img width="100%" :src="dialogImageUrl1" alt="" />
                  </el-dialog>
               </el-form-item>
            </el-col>
            <el-col :span="1">
               <div class="grid-content bg-purple" style="color:rgba(0,0,0,0)"> <br></div>
            </el-col>
            <el-col :span="10">
               <el-form-item label="银行账号" prop="publicAccountNumber3">
                  <!-- <el-input v-model="formBank.publicAccountNumber3"></el-input> -->
                  <el-select v-model="formBank.publicAccountNumber3" filterable placeholder="请选择">
                     <el-option v-for="item in publicAccountNumber3_options" :key="item.value" :label="item.value" :value="item.value">
                     </el-option>
                  </el-select>


               </el-form-item>
               <el-form-item style="color:rgba(0,0,0,0)">
                  <br>
               </el-form-item>
               <el-form-item label="三方协议签约凭证" prop="fileList2">
                  <el-upload class="upload-demo" action="http://36.133.2.179:8000/api/files/doUpload"
                     :on-success="handlesuccess2" :on-preview="handlePreview2" :on-remove="handleRemove2"
                     :before-remove="beforeRemove2" multiple :limit="9" :on-exceed="handleExceed2"
                     :file-list="fileName4" list-type="picture">
                     <el-button size="small" type="primary">点击上传</el-button>
                  </el-upload>
                  <el-dialog :visible.sync="dialogVisible2" append-to-body>
                     <img width="100%" :src="dialogImageUrl2" alt="" />
                  </el-dialog>
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
import { all } from '@/api/company/payTaxInfo';
import { addEmployed, updateEmployed } from "@/api/company/employed";
export default {
   data() {
      return {
         formBank: {
            bankStatus: 1,
            selfId: '',
            accountName: '',
            privateDepositBank: '',
            privateAccountNumber: '',
            publicDepositBank1: '',
            publicAccountNumber1: '',
            publicDepositBank2: '',
            publicAccountNumber2: '',
            publicDepositBank3: '',
            publicAccountNumber3: '',
            selfName: '',
            legalPersonName: '',
            taxId: '',
            fileName3: [],
            fileName4: [],
         },
         activeNames: ['1'],
         dialogImageUrl1: '',
         fileName3: [],
         dialogImageUrl2: '',
         fileName4: [],
         dialogVisible1: false,
         dialogVisible2: false,

         accountName_options: [],
         publicDepositBank3_options: [],
         publicAccountNumber3_options: [],

         rules: {
            selfName: [
               { required: true, message: '请输入名称', trigger: 'blur' },
               // { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
            ],
            legalPersonName: [
               { required: true, message: '请输入法人姓名', trigger: 'blur' }
            ],
            taxId: [
               { required: true, message: '请输入纳税人识别号', trigger: 'blur' }
            ],

            publicDepositBank1: [
               { required: true, message: '请输入对公基本户开户银行', trigger: 'blur' }
            ],
            publicAccountNumber1: [
               { required: true, message: '请输入对公基本户银行账号', trigger: 'blur' }
            ],
            publicDepositBank2: [
               { required: true, message: '请输入对公一般户开户银行', trigger: 'blur' }
            ],
            publicAccountNumber2: [
               { required: true, message: '请输入对公一般户银行账号', trigger: 'blur' }
            ],

            privateDepositBank: [
               { required: true, message: '请输入私人开户银行', trigger: 'blur' }
            ],
            privateAccountNumber: [
               { required: true, message: '请输入私人银行账号', trigger: 'blur' }
            ],
            accountName: [
               { required: true, message: '请输入纳税账号户名', trigger: 'change'}
            ],
            publicDepositBank3: [
               { required: true, message: '请输入纳税账号开户银行', trigger: 'change'}
            ],
            publicAccountNumber3: [
               { required: true, message: '请输入纳税账号银行账号', trigger: 'change'}
            ],

            fileName3: [
               { required: true, message: '请上传纳税委托协议文件', trigger: 'change' }
            ],
            fileName4: [
               { required: true, message: '请上传三方协议签约凭证文件', trigger: 'change' }
            ]

         }
      };
   },
   created() {
      let list = this.$cache.local.getJSON('employednewlist');
      this.formBank.selfId = list.selfId;
      this.formBank.selfName = list.selfName;
      this.formBank.legalPersonName = list.legalPersonName;

      this.formBank.privateDepositBank = list.privateDepositBank;
      this.formBank.privateAccountNumber = list.privateAccountNumber;
      this.formBank.taxId = list.taxId;
      this.nailist();
   },
   beforeRouteLeave(to, from, next) {
      to.meta.keepAlive = true
      next(0)
   },

   methods: {
      nailist() {
         all().then(res => {
            if (res != undefined) {
               console.log(res);
               this.accountName_options = [];
               this.publicDepositBank3_options = [];
               this.publicAccountNumber3_options = [];
               for (let i in res) {
                  this.accountName_options.push({value:res[i].accountName});
                  this.publicDepositBank3_options.push({value:res[i].publicDepositBank3});
                  this.publicAccountNumber3_options.push({value:res[i].publicAccountNumber3});
                  }
            }
         }).catch(error => {
            this.modal.msgError(error);
         });
      },
      handleChange(val) {
         console.log(val);
      },
      resetForm() {
         this.$router.back();
      },
      onSubmit() {
         this.$refs['formBank'].validate(valid => {
            if (valid) {
               this.formBank.fileName3 = JSON.stringify(this.formBank.fileName3);
               this.formBank.fileName4 = JSON.stringify(this.formBank.fileName4);
               updateEmployed(this.formBank).then(res => {
                  if (res != undefined) {
                     if (res.code === 200) {
                        this.$modal.msgSuccess("修改成功");
                        this.$nextTick(function () {
                           this.$router.push({ path: "/customer/manage" });
                        });
                     } else {
                        this.$modal.msgError(res.msg);
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
      handlesuccess1(file, fileList) {
         this.formBank.fileName3.push(file.obj);
      },
      handleRemove1(file, fileList) {
         const i = this.formBank.fileName3.findIndex((item) => item === fileList)
         this.formBank.fileName3.splice(i, 1);
      },
      handlePreview1(file) {
         this.dialogImageUrl1 = file.url;
         this.dialogVisible1 = true;
      },
      handleExceed1(files, fileList) {
         this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
      beforeRemove1(file, fileList) {
         return this.$confirm(`确定移除 ${file.name}？`);
      },
      handlesuccess2(file, fileList) {
         this.formBank.fileName4.push(file.obj);
      },
      handleRemove2(file, fileList) {
         const i = this.formBank.fileName4.findIndex((item) => item === fileList)
         this.formBank.fileName4.splice(i, 1);
      },
      handlePreview2(file) {
         this.dialogImageUrl2 = file.url;
         this.dialogVisible2 = true;
      },
      handleExceed2(files, fileList) {
         this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
      beforeRemove2(file, fileList) {
         return this.$confirm(`确定移除 ${file.name}？`);
      }
   }
}
</script>

<style>
.bankno {
   letter-spacing: 5px;
   text-align: center;
   font-size: 20px;
   font-weight: bold;
   color: blue;

}

.paddingbg-s {
   padding-top: 15px;
}

.footers {
   display: flex;
   justify-content: center;
   align-items: center;
}
</style>