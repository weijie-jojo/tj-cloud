<template>
  <div>
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-position="right" label-width="auto">
      <el-steps align-center :active="0" finish-status="success" style="padding-left: 2%;margin-top:30px;margin-bottom:10px;">
        <el-step title="个体户名称"></el-step>
        <el-step title="申请信息"></el-step>
        <el-step title="基本情况"></el-step>
        <el-step title="经营者信息"></el-step>
        <el-step title="业务信息"></el-step>
        <el-step title="提交审核"></el-step>
      </el-steps>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="21">
        <el-form-item  label="填报提示">
          <!-- <el-input style="color:red" readonly="true" type="textarea" :rows="3" value="1.个体工商户名称包含行政区划、乡镇街道社区、字号、行业、组织形式五个组成部分，例如“福州市鼓楼区中山社区天天服装店”中，行政区划为“福州市鼓楼区”，乡镇街道社区为“中山社区”，字号为“天天”，行业为“服装”，组织形式为“店”。
2.请使用谷歌chrome浏览器或360等浏览器的极速模式访问本页面进行填报。">
            </el-input> -->
            <div style="color:red;font-size: 16px;">1.个体工商户名称包含行政区划、乡镇街道社区、字号、行业、组织形式五个组成部分，例如“福州市鼓楼区中山社区天天服装店”中，行政区划为“福州市鼓楼区”，乡镇街道社区为“中山社区”，字号为“天天”，行业为“服装”，组织形式为“店”。</div>
            <div style="color:red;font-size: 16px;">2.请使用谷歌chrome浏览器或360等浏览器的极速模式访问本页面进行填报。</div>
        </el-form-item>
        </el-col>
       
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item label="个体户编号">
            <el-input v-model="formData.selfCode" placeholder="请输入个体户编号" :disabled='true' clearable></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9"></el-col>

      </el-row>

       <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item label="冠名类型">
            <el-input v-model="formData.titleType" placeholder="请输入行政区划" disabled></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
             <el-form-item label="行政区划">
            <el-input v-model="formData.administrativeDivision" placeholder="请输入行政区划" disabled></el-input>
          </el-form-item>
        </el-col>

      </el-row>

        <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
           <el-form-item label="行业" prop="industry">
            <el-input v-model="formData.industry" placeholder="请输入行业" clearable></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
              <!-- <el-form-item label="组织形式" prop="organizationalForm">
            <el-select style="width:100%" v-model="formData.organizationalForm" placeholder="请选择组织形式" clearable
              @change="changeOrganizational">
              <el-option v-for="(item, index) in organizationalFormOptions" :key="index" :label="item.label"
                :value="item.value" :disabled="item.disabled"></el-option>
            </el-select>
          </el-form-item> -->
        </el-col>

      </el-row>

          <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item label="所在行政区划">
            <el-input v-model="formData.administrativeRegion" placeholder="请输入所在行政区划" disabled></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
             <el-form-item label="登记机关">
              <el-input v-model="formData.registrationAuthority" placeholder="请输入登记机关" disabled></el-input>
            </el-form-item>
        </el-col>

      </el-row>
    
         <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item label="字号" :required="true">
            <el-checkbox v-model="formData.random" @change="isRandom">随机字号</el-checkbox>
          </el-form-item>
        </el-col>
        <el-col :span="9">
            <el-form-item label="拟设名称">
            随机名称
          </el-form-item>
        </el-col>

      </el-row>
    

      

     

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item prop="fontSize1">
            <el-input v-if="isDisable" v-model="formData.fontSize1" :disabled="isDisable" clearable>
            </el-input>
            <el-input v-else  placeholder="请输入字号" v-model="formData.fontSize1" :disabled="isDisable" clearable>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item>
            <el-input v-model="formData.poposedName1" disabled></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item prop="fontSize2">
            <el-input v-if="isDisable" v-model="formData.fontSize2" :disabled="isDisable" clearable>
            </el-input>
            <el-input v-else placeholder="请输入字号" v-model="formData.fontSize2" :disabled="isDisable" clearable>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item>
            <el-input v-model="formData.poposedName2" disabled></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item prop="fontSize3">
            <el-input v-if="isDisable" v-model="formData.fontSize3" :disabled="isDisable" clearable>
            </el-input>
            <el-input v-else  placeholder="请输入字号" v-model="formData.fontSize3" :disabled="isDisable" clearable>
            </el-input>

          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item>
            <el-input v-model="formData.poposedName3" disabled></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item prop="fontSize4">
            <el-input v-if="isDisable" v-model="formData.fontSize4" :disabled="isDisable" clearable>
            </el-input>
            <el-input v-else  placeholder="请输入字号" v-model="formData.fontSize4" :disabled="isDisable" clearable>
            </el-input>

          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item>
            <el-input v-model="formData.poposedName4" disabled></el-input>
          </el-form-item>
        </el-col>
      </el-row>


      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item prop="fontSize5">
            <el-input v-if="isDisable" v-model="formData.fontSize5" :disabled="isDisable" clearable>
            </el-input>
            <el-input v-else  placeholder="请输入字号" v-model="formData.fontSize5" :disabled="isDisable" clearable>
            </el-input>

          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item>
            <el-input v-model="formData.poposedName5" disabled></el-input>
          </el-form-item>
        </el-col>
      </el-row>


       <el-row type="flex" class="row-bg " justify="space-around">
         <el-col :span="8"></el-col>
         <el-col :span='8' class="flexs">
                <el-button type="danger" @click="resetForm" class="btn">取消</el-button>
                <el-button type="primary" @click="submitForm" class="btn" v-hasPermi="['company:review:add']">下一步</el-button>
         </el-col>
         <el-col :span="8"></el-col>
     </el-row>
   

    </el-form>
  </div>
</template>
<script>
import crudReview from '@/api/company/review';
import crudInformation from '@/api/company/information';
import { getInfo } from '@/api/login';
export default {
  components: {},
  props: [],
  data() {
    return {
      isDisable: true,
      formData: {
        selfCode: '',
        titleType: '区县名',
        administrativeDivision: '漳平市',
        industry: '',
        organizationalForm: '',
        administrativeRegion: '龙岩市漳平市',
        registrationAuthority: '漳平市市场监督管理局',
        random: true,
        //字号
        fontSize1: '',
        fontSize2: '',
        fontSize3: '',
        fontSize4: '',
        fontSize5: '',
        //拟设名称
        poposedName1: '',
        poposedName2: '',
        poposedName3: '',
        poposedName4: '',
        poposedName5: '',
       
        userName: '',
      },
      rules: {
        industry: [{
          required: true,
          message: '请输入行业',
          trigger: 'blur'
        }],

        organizationalForm: [{
          required: true,
          message: '请选择组织形式',
          trigger: 'change'
        }],

        administrativeRegion: [{
          required: true,
          message: '请输入所在行政区划',
          trigger: 'blur'
        }],
        registrationAuthority: [{
          required: true,
          message: '请输入登记机关',
          trigger: 'blur'
        }],

        random: [{
          required: false,
          message: '请输入随机字号',
          trigger: 'blur'
        }],
          fontSize1: [{min: 3, message: '字号不能少于3个字符', trigger: 'blur'}],
          fontSize2: [{min: 3, message: '字号不能少于3个字符', trigger: 'blur'}],
          fontSize3: [{min: 3, message: '字号不能少于3个字符', trigger: 'blur'}],
          fontSize4: [{min: 3, message: '字号不能少于3个字符', trigger: 'blur'}],
          fontSize5: [{min: 3, message: '字号不能少于3个字符', trigger: 'blur'}]

      },
      organizationalFormOptions: [
        {
          "label": "服务部",
          "value": 1
        },
        {
          "label": "经营部",
          "value": 2
        },
        {
          "label": "工作室",
          "value": 3
        },
        {
          "label": "商行",
          "value": 4
        },
        {
          "label": "商店",
          "value": 5
        },
        {
          "label": "店",
          "value": 6
        },
      ],
      wordTypeOptions: [{
        "label": "选项一",
        "value": 1
      }],
    }
  },
  computed: {
  },
  watch: {
    formData: {
      handler: function () {
        this.getPoposedName();
      },
      deep: true
    }
  },
  created() { },
  mounted() {
    this.getSelfCode();
    this.getLoginInfo();
  },
  methods: {
       
    getLoginInfo() {
      getInfo().then(res => {
        this.formData.userName = res.user.nickName;
      })
    },
    getPoposedName() {
      this.formData.poposedName1 = this.formData.administrativeDivision + this.formData.fontSize1 + this.formData.industry + this.formData.organizationalForm;
      this.formData.poposedName2 = this.formData.administrativeDivision + this.formData.fontSize2 + this.formData.industry + this.formData.organizationalForm;
      this.formData.poposedName3 = this.formData.administrativeDivision + this.formData.fontSize3 + this.formData.industry + this.formData.organizationalForm;
      this.formData.poposedName4 = this.formData.administrativeDivision + this.formData.fontSize4 + this.formData.industry + this.formData.organizationalForm;
      this.formData.poposedName5 = this.formData.administrativeDivision + this.formData.fontSize5 + this.formData.industry + this.formData.organizationalForm;
     
    },
    isRandom() {
      if (!this.formData.random) {
        this.isDisable = false
      } else {
        this.isDisable = true
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
      if (value == 3) {
        this.formData.organizationalForm = "工作室";
      }
      if (value == 4) {
        this.formData.organizationalForm = "商行";
      }
      if (value == 5) {
        this.formData.organizationalForm = "商店";
      }
      if (value == 6) {
        this.formData.organizationalForm = "店";
      }
    },
    //获取编号
    getSelfCode() {
      //获取员工编号
      getInfo().then(res => {
        var userId = res.user.userId;
        crudInformation.getInformation(userId).then(res => {
          var employeeNumber = res.data.employeeNumber;
          crudReview.getCode({ employeeNumber: employeeNumber }).then(res => {
            this.formData.selfCode = res;
            console.log("selfCode", res);
          })
        });
      })
    },
    submitForm() {
      this.$refs['elForm'].validate(valid => {
        // TODO 提交表单
        if (valid) {
          console.log("date", new Date().toLocaleString())
          let parms = {
            selfCode: this.formData.selfCode,
            titleType: this.formData.titleType,
            administrativeDivision: this.formData.administrativeDivision,
            industry: this.formData.industry,
            organizationalForm: this.formData.organizationalForm,
            administrativeRegion: this.formData.administrativeRegion,
            registrationAuthority: this.formData.registrationAuthority,
            random: this.formData.random,
            fontSize1: this.formData.fontSize1,
            fontSize2: this.formData.fontSize2,
            fontSize3: this.formData.fontSize3,
            poposedName1: this.formData.poposedName1,
            poposedName2: this.formData.poposedName2,
            poposedName3: this.formData.poposedName3,
            // createTime:new Date().toLocaleString(),
            // updateTime:new Date().toLocaleString(),
            createBy: this.formData.userName,
            updateBy: this.formData.userName,
            nameStatus: 0,
          };
          crudReview.addReview(parms).then(res => {
            console.log("addReview", res)
            if (res != undefined) {
              if (res.id == 0) {
                this.$message({
                  message: res.message,
                  type: 'success',
                });
              } else {
                this.$message({
                  message: res.message,
                  type: 'warning',
                });
              }
             this.$router.push("addEmployedInfo");
              window.localStorage.setItem("organizationalForm", JSON.stringify(this.formData.organizationalForm));
              window.localStorage.setItem("selfCode", JSON.stringify(this.formData.selfCode));
            }

          });

        } else {
          this.$message({
            message: '请填写完整',
            type: 'warning'
          })
        }
      })
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
    },
  }
}

</script>
<style rel="stylesheet/scss" lang="scss" scoped>
.rowCss {
  margin-top: 30px;
}

// 改变input框字体颜色
::v-deep .is-disabled .el-input__inner {
  background-color: transparent !important;
  color: black;
}

.btn {
  width: 90px;
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
