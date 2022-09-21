<template>
  <div class="paddingbg-s">
    <el-form ref="formBank" :model="formBank" :rules="rules" label-width="auto">
      <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="个体户名称" prop="selfName">
            <el-input v-model="formBank.selfName" :readonly="true"></el-input>
          </el-form-item>

          <el-form-item class="comright" label="法人姓名" prop="legalPersonName">
            <el-input v-model="formBank.legalPersonName" :readonly="true"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="9">
          <el-form-item class="comright" label="纳税人识别号" prop="taxId">
            <el-input v-model="formBank.taxId" :readonly="true"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" style="margin-bottom:20px;" justify="space-around">
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width:35%">银行信息</div>
          <div style="width:50%;hegiht:10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>


      <el-row>
        <el-row type="flex" class="row-bg" justify="space-around" v-if="accountType == 1">
          <el-col :span="9">
            <el-form-item class="comright" label="账号类型" :required="true">
              <el-input value="私人账号" disabled></el-input>
            </el-form-item>

            <el-form-item class="comright" label="开户银行" prop="privateDepositBank">
              <el-input v-model="formBank.privateDepositBank" :readonly="true"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="9">
            <el-form-item class="comright" label="账号名称" prop="legalPersonName">
              <el-input v-model="formBank.legalPersonName" :readonly="true"></el-input>
            </el-form-item>
            <el-form-item class="comright" label="银行账号" prop="privateAccountNumber">
              <el-input v-model="formBank.privateAccountNumber" :readonly="true"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around" v-if="accountType == 2">
          <el-col :span="9">
            <el-form-item class="comright" label="账号类型" :required="true">
              <el-input value="对公基本户" :readonly="true"></el-input>
            </el-form-item>


            <el-form-item class="comright" label="开户银行" prop="publicDepositBank1">
              <el-input v-model="formBank.publicDepositBank1" :readonly="true"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="9">
            <el-form-item class="comright" label="账号名称" :required="true">
              <el-input v-model="formBank.selfName" :readonly="true"></el-input>
            </el-form-item>
            <el-form-item class="comright" label="银行账号" prop="publicAccountNumber1">
              <el-input v-model="formBank.publicAccountNumber1" :readonly="true"></el-input>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around" v-if="accountType == 2">
          <el-col :span="9">
            <el-form-item class="comright" label="对公一般户状态" :required="true">
              <el-radio disabled v-model="formBank.isPublicUser" label="0">开启</el-radio>
              <el-radio disabled v-model="formBank.isPublicUser" label="1">关闭</el-radio>
            </el-form-item>
          </el-col>
          <el-col :span="9">
          </el-col>

        </el-row>
        <div v-if="formBank.isPublicUser == 0">


          <el-row type="flex" class="row-bg" justify="space-around" v-if="accountType == 2">
            <el-col :span="9">
              <el-form-item class="comright" label="账号类型" :required="true">
                <el-input value="对公一般户" disabled></el-input>
              </el-form-item>


              <el-form-item class="comright" label="开户银行">
                <el-input v-model="formBank.publicDepositBank2" :readonly="true"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="9">
              <el-form-item class="comright" label="账号名称" :required="true">
                <el-input v-model="formBank.selfName" :readonly="true"></el-input>
              </el-form-item>
              <el-form-item class="comright" label="银行账号">
                <el-input v-model="formBank.publicAccountNumber2" :readonly="true"></el-input>
              </el-form-item>
            </el-col>

          </el-row>
        </div>



      </el-row>
      <el-row type="flex" class="row-bg" style="margin-bottom:20px" justify="space-around">
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width:35%">纳税信息</div>
          <div style="width:50%;hegiht:10px"></div>
        </el-col>
        <el-col :span="9">
          <div></div>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="开户银行" prop="publicDepositBank3">
            <el-input disabled v-model="formBank.publicDepositBank3" :readonly="true"></el-input>
          </el-form-item>
          <el-form-item class="comright" label="户名" prop="accountName">
            <!-- <el-input v-model="formBank.accountName"></el-input> -->
            <el-select disabled style="width:100%" @change="changeValue($event)" v-model="formBank.accountName"
              filterable placeholder="请选择">
              <el-option v-for="(item,index) in accountName_options" :key="index" :label="item.value" :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>


          <el-form-item label="纳税委托协议" prop="fileName3">
            <uploadSmall v-if="fileNameN1.length > 0" @getfileName="getfileNameS" :fileName="isNone"
              :fileNameOld="fileNameN1" :isDetail="isDetail"></uploadSmall>
          </el-form-item>
        </el-col>

        <el-col :span="9">
          <el-form-item class="comright" label="银行账号" prop="publicAccountNumber3">
            <el-input disabled v-model="formBank.publicAccountNumber3"></el-input>
          </el-form-item>
          <el-form-item>
            <div style="height:34px"></div>
          </el-form-item>
          <el-form-item label="三方协议签约凭证" prop="fileName4">
            <uploadSmall v-if="fileNameN2.length > 0" @getfileName="getfileNameS" :fileName="isNone"
              :fileNameOld="fileNameN2" :isDetail="isDetail"></uploadSmall>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="8"></el-col>
        <el-col :span='8' class="flexs">
          <el-button type="danger" @click="resetForm">关闭</el-button>

        </el-col>
        <el-col :span="8"></el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
import uploadSmall from '@/components/douploads/uploadSmall'
import { all } from "@/api/tc-api/company/payTaxInfo";
export default {
  name:'DetailBank',
  components: {
    uploadSmall
  },
  data() {
    return {
      isDetail: '1',
      isNone: [],
      fileNameN1: [],
      fileNameN2: [],
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
      formBank: {
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
  created() {
    let list = this.$cache.local.getJSON("tc-banklist");
    this.formBank = list;
    if (this.formBank.isPublicUser == 0) {
      this.formBank.isPublicUser = '0';
    } else {
      this.formBank.isPublicUser = '1';
    }
    this.fileNameN1 = [];
    this.fileNameN2 = [];
    this.fileName3 = JSON.parse(this.$cache.local.getJSON('tc-banklist').fileName3);
    for (let k1 in this.fileName3) {
      this.fileNameN1.push({
        url: this.baseImgPath + this.fileName3[k1],
        name: this.fileName3[k1],
      });
    }
    this.fileName4 = JSON.parse(this.$cache.local.getJSON('tc-banklist').fileName4);
    for (let k2 in this.fileName4) {

      this.fileNameN2.push({
        url: this.baseImgPath + this.fileName4[k2],
        name: this.fileName4[k2],
      });
    }
    this.accountType = list.accountType;
    this.nailist();
  },
  methods: {
    getfileNameS() {

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

        });
    },
    handleChange(val) {
      console.log(val);
    },
    resetForm() {
      this.$tab.closeOpenPage({ path: this.$cache.local.getJSON('tc-backurls').backUrl });
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

::v-deep .el-input.is-disabled .el-input__inner {
  background-color: rgba(255, 255, 255, 1.5) !important;
  color: black !important;
  border-color: rgba(135, 206, 250, 0.7) !important;
}

::v-deep .el-input-group__append {
  background-color: rgba(255, 255, 255, 1.5) !important;
  color: black !important;
  border-color: rgba(135, 206, 250, 0.7) !important;
}
</style>