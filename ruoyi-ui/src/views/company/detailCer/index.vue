<template>
  <div class="paddingbg-s">
    <el-form
      ref="formBank"
      :model="formBank"
      :rules="rules"
      label-width="auto"
    >
      <el-row  type="flex" class="row-bg " justify="space-around">
        <el-col :span="9">
          <!-- <el-form-item class="comright" label="个体户名称" prop="selfName">
            <el-input  v-model="formBank.selfName" disabled></el-input>
          </el-form-item> -->

          <el-form-item class="comright" label="法人姓名" prop="legalPersonName">
            <el-input  v-model="formBank.legalPersonName" :readonly="true"></el-input>
          </el-form-item>
        </el-col>
       
        <el-col :span="9">
          <!-- <el-form-item class="comright" label="纳税人识别号" prop="taxId">
            <el-input  v-model="formBank.taxId" :readonly="true" ></el-input>
          </el-form-item> -->
        </el-col>
      </el-row>
      
     
    

      <el-row  type="flex" class="row-bg " justify="space-around">
        <el-col :span="9">
          
          <el-form-item  label="工商实名" prop="fileName6">
                  <div v-for="(item, index) in fileName6" :key="index">
                  <el-image
                    lazy
                    :preview-src-list="fileName6"
                    style="width: 150px; height: 150px"
                    :src="item"
                    alt=""
                  />
                </div>
            
          </el-form-item>
        </el-col>
        
        <el-col :span="9">
          
           
          <el-form-item label="税务实名" prop="fileName7">
                <div v-for="(item, index) in fileName7" :key="index">
                  <el-image
                    lazy
                    :preview-src-list="fileName7"
                    style="width: 150px; height: 150px"
                    :src="item"
                    alt=""
                  />
                </div>
          </el-form-item>
        </el-col>
      </el-row>
       
    </el-form>
  </div>
</template>

<script>
import { all } from "@/api/company/payTaxInfo";
import { addEmployed, updateEmployed } from "@/api/company/employed";
export default {
  data() {
    return {
      formBank: {
        realnameStatus: 1,
        selfId: "",
        selfName: "",
        legalPersonName: "",
        taxId: "",
        fileName6: [],
        fileName7: [],
      },
      accountType: "",
      activeNames: ["1"],
      dialogImageUrl1: "",
      fileName6: [],
      dialogImageUrl2: "",
      fileName7: [],
      dialogVisible1: false,
      dialogVisible2: false,
      mylist: [],
      accountName_options: [],
      publicDepositBank3_options: [],
      publicAccountNumber3_options: [],
      baseImgPath:"http://36.133.2.179:8000/api/files/showImg?imgPath=",
      rules: {
        selfName: [
          { required: true, message: "请输入名称", trigger: "blur" },
          // { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        legalPersonName: [
          { required: true, message: "请输入法人姓名", trigger: "blur" },
        ],
        taxId: [
          { required: true, message: "请输入纳税人识别号", trigger: "blur" },
        ],

        publicDepositBank1: [
          {
            required: true,
            message: "请输入对公基本户开户银行",
            trigger: "blur",
          },
        ],
        publicAccountNumber1: [
          {
            required: true,
            message: "请输入对公基本户银行账号",
            trigger: "blur",
          },
        ],
        publicDepositBank2: [
          {
            required: true,
            message: "请输入对公一般户开户银行",
            trigger: "blur",
          },
        ],
        publicAccountNumber2: [
          {
            required: true,
            message: "请输入对公一般户银行账号",
            trigger: "blur",
          },
        ],

        privateDepositBank: [
          { required: true, message: "请输入私人开户银行", trigger: "blur" },
        ],
        privateAccountNumber: [
          { required: true, message: "请输入私人银行账号", trigger: "blur" },
        ],
        accountName: [
          { required: true, message: "请输入纳税账号户名", trigger: "change" },
        ],
        publicDepositBank3: [
          {
            required: true,
            message: "请输入纳税账号开户银行",
            trigger: "change",
          },
        ],
        publicAccountNumber3: [
          {
            required: true,
            message: "请输入纳税账号银行账号",
            trigger: "change",
          },
        ],

        fileName6: [
          {
            required: true,
            message: "请上传工商实名文件",
            trigger: "change",
          },
        ],
        fileName7: [
          {
            required: true,
            message: "请上传税务实名文件",
            trigger: "change",
          },
        ],
      },
    };
  },
  created() {
    let list = this.$cache.local.getJSON("employednewlist");
    this.formBank=list;
    this.fileName6=JSON.parse(this.$cache.local.getJSON('employednewlist').fileName6);
    for(let k1 in this.fileName6){
      this.fileName6[k1]=this.baseImgPath+this.fileName6[k1];
    } 
     this.fileName7=JSON.parse(this.$cache.local.getJSON('employednewlist').fileName7);
    for(let k2 in this.fileName7){
      this.fileName7[k2]=this.baseImgPath+this.fileName7[k2];
    } 
    // this.formBank.selfId = list.selfId;
    // this.formBank.selfName = list.selfName;
    // this.formBank.taxId=list.taxId;
    // this.formBank.legalPersonName = list.legalPersonName;
},
  beforeRouteLeave(to, from, next) {
    to.meta.keepAlive = true;
    next(0);
  },

  methods: {
    changeValue(res) {
      for (let i in this.mylist) {
        if (this.mylist[i].accountName == res) {
          this.formBank.publicDepositBank3 = this.mylist[i].publicDepositBank3;
          this.formBank.publicAccountNumber3 =
            this.mylist[i].publicAccountNumber3;
          return;
        }
      }
    },
    nailist() {
      all()
        .then((res) => {
          if (res != undefined) {
            console.log(res);
            this.mylist = [];
            this.accountName_options = [];
            this.mylist = res;
            // this.publicDepositBank3_options = [];
            // this.publicAccountNumber3_options = [];
            for (let i in res) {
              this.accountName_options.push({ value: res[i].accountName });
              // this.publicDepositBank3_options.push({value:res[i].publicDepositBank3});
              // this.publicAccountNumber3_options.push({value:res[i].publicAccountNumber3});
            }
          }
        })
        .catch((error) => {
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
      this.$refs["formBank"].validate((valid) => {
        if (valid) {
          this.formBank.fileName6 = JSON.stringify(this.formBank.fileName6);
          this.formBank.fileName7 = JSON.stringify(this.formBank.fileName7);
          updateEmployed(this.formBank)
            .then((res) => {
              if (res != undefined) {
                if (res.code === 200) {
                   this.$nextTick(function () {
                     this.$tab.refreshPage({ path: "/company/customer/manageCer"}).then(() => {
                     let  resmsg='办理实名成功';
                     let obj={
                        title:'实名办理',
                        backUrl:'/company/customer/manageCer',
                        resmsg:resmsg
                        };
                      this.$cache.local.setJSON('successNew', obj);
                      this.$tab.closeOpenPage({ path: "/company/customer/successNew"});
                    });
                   });
                  
                } else {
                  this.$modal.msgError(res.msg);
                }
              }
            })
            .catch((error) => {
              this.$modal.msgError(error);
            });
        } else {
          this.$modal.msgError("请填写完整");
        }
      });
    },
    handlesuccess1(file, fileList) {
      this.formBank.fileName3.push(file.obj);
    },
    handleRemove1(file, fileList) {
      const i = this.formBank.fileName3.findIndex((item) => item === fileList);
      this.formBank.fileName3.splice(i, 1);
    },
    handlePreview1(file) {
      this.dialogImageUrl1 = file.url;
      this.dialogVisible1 = true;
    },
    handleExceed1(files, fileList) {
      this.$message.warning(
        `当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${
          files.length + fileList.length
        } 个文件`
      );
    },
    beforeRemove1(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    handlesuccess2(file, fileList) {
      this.formBank.fileName4.push(file.obj);
    },
    handleRemove2(file, fileList) {
      const i = this.formBank.fileName4.findIndex((item) => item === fileList);
      this.formBank.fileName4.splice(i, 1);
    },
    handlePreview2(file) {
      this.dialogImageUrl2 = file.url;
      this.dialogVisible2 = true;
    },
    handleExceed2(files, fileList) {
      this.$message.warning(
        `当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${
          files.length + fileList.length
        } 个文件`
      );
    },
    beforeRemove2(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
  },
};
</script>

<style>
.bankno {
 
  letter-spacing: 2px;

  font-size: 20px;

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
.combottom{
  margin-bottom: 10px;
}
.rowCss {
  margin-top: 10px;
}
.comright{
  padding-right: 10%;
}
.flexs{
  display: flex;
  justify-content: center;

}
</style>