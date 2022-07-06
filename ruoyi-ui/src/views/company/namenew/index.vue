<template>
  <div>
    <el-form
      ref="elForm"
      :model="formData"
      :rules="rules"
      size="medium"
      label-width="auto"
    
    >
      <el-row type="flex" class="row-bg rowCss" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="个体户编号">
            <el-input
              v-model="formData.selfCode"
              placeholder="请输入个体户编号"
              :disabled="true"
              clearable
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9"></el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="冠名类型">
            <el-input
              v-model="formData.titleType"
              placeholder="请输入行政区划"
              disabled
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="行政区划">
            <el-input
              v-model="formData.administrativeDivision"
              placeholder="请输入行政区划"
              disabled
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="行业" prop="industry">
            <el-input
              disabled
              v-model="formData.industry"
              placeholder="请输入行业"
              clearable
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="组织形式" prop="organizationalForm">
            <el-select
              style="width:100%"
              disabled
              v-model="formData.organizationalForm"
              placeholder="请选择组织形式"
              clearable
              @change="changeOrganizational"
            >
              <el-option
                v-for="(item, index) in organizationalFormOptions"
                :key="index"
                :label="item.label"
                :value="item.value"
                :disabled="item.disabled"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="所在行政区划">
            <el-input
              v-model="formData.administrativeRegion"
              placeholder="请输入所在行政区划"
              disabled
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="登记机关">
            <el-input
              v-model="formData.registrationAuthority"
              placeholder="请输入登记机关"
              disabled
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="字号">
            <el-checkbox disabled v-model="formData.random" @change="isRandom"
              >随机字号</el-checkbox
            >
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="拟设名称"> 随机名称 </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" prop="fontSize1">
            <el-input
              v-model="formData.fontSize1"
              :disabled="isDisable"
              clearable
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright">
            <el-input v-model="formData.poposedName1" disabled></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" prop="fontSize2">
            <el-input
              v-model="formData.fontSize2"
              :disabled="isDisable"
              clearable
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright">
            <el-input v-model="formData.poposedName2" disabled></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" prop="fontSize3">
            <el-input
              v-model="formData.fontSize3"
              :disabled="isDisable"
              clearable
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright">
            <el-input v-model="formData.poposedName3" disabled></el-input>
          </el-form-item>
        </el-col>
      </el-row>
     
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright">
                <el-radio v-model="isokradio" label="1"> 通过</el-radio>
                <el-radio v-model="isokradio" label="2">驳回 </el-radio>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="驳回理由">
            <el-input v-model="remark" :disabled="isokradio==1"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
        
      <el-row type="flex" class="row-bg " justify="space-around">
         <el-col :span="8"></el-col>
         <el-col :span='8' class="flexs">
             <el-button type="danger" @click="resetForm">返回</el-button> 
             <el-button v-if="isokradio==2" type="primary" @click="submitForm(2)">驳回</el-button>
             <el-button v-else type="primary" @click="submitForm(1)">通过</el-button>
         </el-col>
         <el-col :span="8"></el-col>
       </el-row>
    </el-form>
  </div>
</template>
<script>
import crudReview from "@/api/company/review";
import crudInformation from "@/api/company/information";
import { getInfo } from "@/api/login";
import { updateReview } from "@/api/company/review";
export default {
  components: {},
  props: [],
  data() {
    return {
      remark:null,
      isokradio:'1',
      isDisable: true,
      formData: {
        selfCode: "",
        titleType: "区县名",
        administrativeDivision: "漳平市",
        industry: "",
        organizationalForm: "",
        administrativeRegion: "龙岩市漳平市",
        registrationAuthority: "漳平市市场监督管理局",
        random: true,
        //字号
        fontSize1: "",
        fontSize2: "",
        fontSize3: "",
        //拟设名称
        poposedName1: "",
        poposedName2: "",
        poposedName3: "",
        userName: "",
      },
      rules: {
        industry: [
          {
            required: true,
            message: "请输入行业",
            trigger: "blur",
          },
        ],

        organizationalForm: [
          {
            required: true,
            message: "请选择组织形式",
            trigger: "change",
          },
        ],

        administrativeRegion: [
          {
            required: true,
            message: "请输入所在行政区划",
            trigger: "blur",
          },
        ],
        registrationAuthority: [
          {
            required: true,
            message: "请输入登记机关",
            trigger: "blur",
          },
        ],
      },
      organizationalFormOptions: [
        {
          label: "服务部",
          value: 1,
        },
        {
          label: "经营部",
          value: 2,
        },
      ],
      wordTypeOptions: [
        {
          label: "选项一",
          value: 1,
        },
      ],
    };
  },
  computed: {},
  watch: {
    formData: {
      handler: function () {
        this.getPoposedName();
      },
      deep: true,
    },
  },
  created() {},
  mounted() {
    this.formData = this.$cache.local.getJSON("employedName");
    this.getSelfCode();
    this.getLoginInfo();
  },
  methods: {
    getLoginInfo() {
      getInfo().then((res) => {
        this.formData.userName = res.user.nickName;
      });
    },
    getPoposedName() {
      this.formData.poposedName1 =
        this.formData.administrativeDivision +
        this.formData.fontSize1 +
        this.formData.industry +
        this.formData.organizationalForm;
      this.formData.poposedName2 =
        this.formData.administrativeDivision +
        this.formData.fontSize2 +
        this.formData.industry +
        this.formData.organizationalForm;
      this.formData.poposedName3 =
        this.formData.administrativeDivision +
        this.formData.fontSize3 +
        this.formData.industry +
        this.formData.organizationalForm;
    },
    isRandom() {
      if (!this.formData.random) {
        this.isDisable = false;
      } else {
        this.isDisable = true;
        this.formData.fontSize1 = "";
        this.formData.fontSize2 = "";
        this.formData.fontSize3 = "";
      }
    },
    changeOrganizational(value) {
      if (value == 1) {
        this.formData.organizationalForm = "服务部";
      }
      if (value == 2) {
        this.formData.organizationalForm = "经营部";
      }
    },
    //获取编号
    getSelfCode() {
      //获取员工编号
      getInfo().then((res) => {
        var userId = res.user.userId;
        crudInformation.getInformation(userId).then((res) => {
          var employeeNumber = res.data.employeeNumber;
          crudReview.getCode({ employeeNumber: employeeNumber }).then((res) => {
            this.formData.selfCode = res;
            console.log("selfCode", res);
          });
        });
      });
    },
    submitForm(type) {
      this.$refs["elForm"].validate((valid) => {
        // TODO 提交表单
        if (valid) {
          let parms;
       if(type==1){
          parms = {
              selfId: this.formData.selfId,
              nameStatus: type,
          };
          }else{
           parms = {
            selfId: this.formData.selfId,
            nameStatus: type,
            remarkName:this.remark
            };
          }
          updateReview(parms).then((res) => {
            if (res != undefined) {
              if (res != undefined) {
                if (res.code === 200) {
                  if(type==1){
                     this.$modal.msgSuccess("名称审核通过成功!");
                  }else{
                    this.$modal.msgSuccess("名称审核驳回成功!");
                  }
                 
                  this.$nextTick(function () {
                    this.$tab.refreshPage("/customer/employed").then(() => {
                      this.$tab.openPage("注册进度", "/customer/employed");
                    });
                  });
                } else {
                  this.$modal.msgError(res.msg);
                }
              }
            }
          });
        } else {
          this.$message({
            message: "请填写完整",
            type: "warning",
          });
        }
      });
    },
    resetForm() {
      this.$router.back();
    },
  },
};
</script>
<style rel="stylesheet/scss" lang="scss" scoped>



// 改变input框字体颜色
::v-deep .is-disabled .el-input__inner {
  background-color: transparent !important;
  color: black;
}

.paddingbg-s {
  padding-top: 15px;
}

.footers {
  display: flex;
  justify-content: center;
  align-items: center;
}
.rowCss {
  margin-top: 30px;
}
.comright{
  padding-right: 10%;
}
.flexs{
  display: flex;
  justify-content: center;

}
</style>
