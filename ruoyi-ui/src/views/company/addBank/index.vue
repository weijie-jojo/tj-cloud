<template>
  <div class="paddingbg-s">
    <el-form ref="formBank" :model="formBank" :rules="rules" label-width="auto">
      <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="个体户名称" prop="selfName">
            <el-input v-model="formBank.selfName" disabled></el-input>
          </el-form-item>

          <el-form-item class="comright" label="法人姓名" prop="legalPersonName">
            <el-input v-model="formBank.legalPersonName" disabled></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="9">
          <el-form-item class="comright" label="纳税人识别号" prop="taxId">
            <el-input v-model="formBank.taxId" disabled></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg rowCss combottom" justify="space-around">
        <el-col :span="7">
          <div class="bankno">银行账号</div>

        </el-col>
        <el-col :span="7">
          <div></div>
        </el-col>
      </el-row>

      <el-row class="paddingbg-s">
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item class="comright" label="账号类型" prop="">
              <el-input value="私人账号" disabled></el-input>
            </el-form-item>

            <el-form-item class="comright" label="开户银行" prop="privateDepositBank">
              <el-input v-model="formBank.privateDepositBank" :disabled="accountType==1"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="9">
            <el-form-item class="comright" label="账号名称" prop="legalPersonName">
              <el-input v-model="formBank.legalPersonName" disabled></el-input>
            </el-form-item>
            <el-form-item class="comright" label="银行账号" prop="privateAccountNumber">
              <el-input v-model="formBank.privateAccountNumber" :disabled="accountType==1"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around" v-if="accountType == 2">
          <el-col :span="9">
            <el-form-item class="comright" label="账号类型" prop="">
              <el-input value="对公基本户" disabled></el-input>
            </el-form-item>


            <el-form-item class="comright" label="开户银行">
              <el-input v-model="formBank.publicDepositBank1"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="9">
            <el-form-item class="comright" label="账号名称">
              <el-input v-model="formBank.selfName" disabled></el-input>
            </el-form-item>
            <el-form-item class="comright" label="银行账号">
              <el-input v-model="formBank.publicAccountNumber1"></el-input>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around" v-if="accountType == 2">
          <el-col :span="9">
            <el-form-item class="comright" label="账号类型" prop="">
              <el-input value="对公一般户" disabled></el-input>
            </el-form-item>


            <el-form-item class="comright" label="开户银行">
              <el-input v-model="formBank.publicDepositBank2"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="9">
            <el-form-item class="comright" label="账号名称">
              <el-input v-model="formBank.selfName" disabled></el-input>
            </el-form-item>
            <el-form-item class="comright" label="银行账号">
              <el-input v-model="formBank.publicAccountNumber2"></el-input>
            </el-form-item>
          </el-col>

        </el-row>



      </el-row>
      <el-row type="flex" class="row-bg combottom" justify="space-around">
        <el-col :span="7">
          <div class="bankno">纳税账号</div>
        </el-col>
        <el-col :span="7">

        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="户名" prop="accountName">
            <!-- <el-input v-model="formBank.accountName"></el-input> -->
            <el-select style="width:100%" @change="changeValue($event)" v-model="formBank.accountName" filterable
              placeholder="请选择">
              <el-option v-for="item in accountName_options" :key="item.value" :label="item.value" :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item class="comright" label="开户银行" prop="publicDepositBank3">
            <el-input disabled v-model="formBank.publicDepositBank3"></el-input>

          </el-form-item>
          <el-form-item label="纳税委托协议" prop="fileName3">
             <uploadSmall  @getfileName="getTax" :fileName="isNone" :fileNameOld="isNone" :isDetail="isDetail"></uploadSmall>
          </el-form-item>
        </el-col>

        <el-col :span="9">
          <el-form-item class="comright" label="银行账号" prop="publicAccountNumber3">
            <el-input disabled v-model="formBank.publicAccountNumber3"></el-input>

          </el-form-item>
          <el-form-item style="color: rgba(0, 0, 0, 0)">
            <br />
          </el-form-item>
          <el-form-item label="三方协议签约凭证" prop="fileName4">
            <uploadSmall  @getfileName="getThree" :fileName="isNone" :fileNameOld="isNone" :isDetail="isDetail"></uploadSmall>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="8"></el-col>
        <el-col :span='8' class="flexs">
          <el-button type="danger" @click="resetForm">返回</el-button>
          <el-button type="primary" @click="onSubmit">提交</el-button>
        </el-col>
        <el-col :span="8"></el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
import uploadSmall from '@/components/douploads/uploadSmall'
import { getInfo } from '@/api/login'
import { all } from "@/api/company/payTaxInfo";
import { updateEmployed, check } from "@/api/company/employed";

export default {
  components: {
    uploadSmall
  },
  data() {
    return {
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
      //pdf预览
      isDetail:'0',
      isNone:[],

      userinfo: {},
      formBank: {
        endStatus:0,
        bankStatus: 1,
        selfId: "",
        accountName: "",
        privateDepositBank: "",
        privateAccountNumber: "",
        publicDepositBank1: "",
        publicAccountNumber1: "",
        publicDepositBank2: "",
        publicAccountNumber2: "",
        publicDepositBank3: "",
        publicAccountNumber3: "",
        selfName: "",
        legalPersonName: "",
        taxId: "",
        fileName3: [],
        fileName4: [],
      },
      accountType: "",
      activeNames: ["1"],
      dialogImageUrl1: "",
      fileName3: [],
      dialogImageUrl2: "",
      fileName4: [],
      dialogVisible1: false,
      dialogVisible2: false,
      mylist: [],
      accountName_options: [],
      publicDepositBank3_options: [],
      publicAccountNumber3_options: [],

      rules: {
        selfName: [
          { required: true, message: "请输入名称", trigger: "blur" },
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

        fileName3: [
          {
            required: true,
            message: "请上传纳税委托协议文件",
            trigger: "change",
          },
        ],
        fileName4: [
          {
            required: true,
            message: "请上传三方协议签约凭证文件",
            trigger: "change",
          },
        ],
      },
    };
  },
  mounted() {
    this.getInfo();
     let list = this.$cache.local.getJSON("employednewlist");
     console.log(1111,list);
    this.formBank.selfId = list.selfId;
    this.formBank.selfName = list.selfName;
    this.formBank.legalPersonName = list.legalPersonName;
    
    this.formBank.privateDepositBank =list.privateDepositBank;
    this.formBank.privateAccountNumber = list.privateAccountNumber;
  
    this.formBank.taxId = list.taxId;
    this.accountType = list.accountType;
    this.nailist();
  },
 
 

  methods: {
    getTax(data){
     this.formBank.fileName3=data;
     console.log(data);
    },
    getThree(data){
      this.formBank.fileName4=data;
    },
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
            for (let i in res) {
              this.accountName_options.push({ value: res[i].accountName });
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

    //获取个人信息
    getInfo() {
      getInfo().then(res => {
        this.userinfo = res.user;
      })
    },
    //新增银行办理进度
    check(resmsg) {
      let parms = {
        "checkReasult": resmsg,
        "checkUser": this.userinfo.userName,
        'phonenumber': this.userinfo.phonenumber,
        "selfCode": this.$cache.local.getJSON("employednewlist").selfCode,
        "selfType": "7",
      }
      check(parms).then(res => {
        console.log('工商办理插入日志成功！');
      }).catch(error => {

      });
    },
    //返回
    resetForm() {
      this.$tab.closeOpenPage({ path: this.$cache.local.getJSON('successNew').backUrl}).then(() => {
        this.$tab.refreshPage({ path: this.$cache.local.getJSON('successNew').backUrl});
     })
    },
    //提交表单
    onSubmit() {
      this.$refs["formBank"].validate((valid) => {
        if (valid) {
          this.formBank.endStatus=0;
          this.formBank.fileName3 = JSON.stringify(this.formBank.fileName3);
          this.formBank.fileName4 = JSON.stringify(this.formBank.fileName4);
          updateEmployed(this.formBank)
            .then((res) => {
              if (res != undefined) {
                if (res.code === 200) {
                  this.$nextTick(function () {
                    this.$tab.refreshPage({ path: "/company/customer/manageBank" }).then(() => {
                      let resmsg = '银行办理完成';
                      this.check(resmsg);
                      // let obj = {
                      //   title: '银行办理',
                      //   backUrl: '/company/customer/manageBank',
                      //   resmsg: resmsg
                      // };
                      // this.$cache.local.setJSON('successNew', obj);
                      this.$tab.closeOpenPage({ path: "/company/customer/successNew" });
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

.combottom {
  margin-bottom: 10px;
}

.rowCss {
  margin-top: 10px;
}

.comright {
  padding-right: 10%;
}

.flexs {
  display: flex;
  justify-content: center;

}
</style>