<template>
  <div>
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px"
      label-position="right">

      <el-steps :space="1500" 
        :active="2" 
        finish-status="success"
        style="margin-left:280px;margin-top:30px;margin-bottom:50px;">
        <el-step title="个体户名称"></el-step>
        <el-step title="个体户信息"></el-step>
        <el-step title="提交"></el-step>
      </el-steps>

      <el-row class="rowCss" :gutter="60" style="margin-left:260px">
        <el-col :span="8">
         <el-form-item label="姓名" prop="legalPersonName">
          <el-input v-model="formData.legalPersonName"  clearable >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="身份证号码" prop="idCardNum">
            <el-input v-model="formData.idCardNum"  clearable >
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row class="rowCss" :gutter="60" style="margin-left:260px">
        <el-col :span="8">
         <el-form-item label="性别" prop="sex">
          <el-input v-model="formData.sex"  clearable >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="出生日期" prop="birth">
            <el-input v-model="formData.fontName"  clearable >
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row class="rowCss" :gutter="60" style="margin-left:260px">
        <el-col :span="8">
         <el-form-item label="民族" prop="fontName">
          <el-input v-model="formData.fontName"  clearable >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="文化程度" prop="eduation">
            <el-input v-model="formData.eduation"  clearable >
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row class="rowCss" :gutter="60" style="margin-left:260px">
        <el-col :span="8">
         <el-form-item label="政治面貌" prop="politicalStatus">
          <el-input v-model="formData.politicalStatus"  clearable >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="联系电话" prop="telNum">
            <el-input v-model="formData.telNum"  clearable >
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row  :gutter="60" class="rowCss" style="margin-left:260px">
        <el-col :span="16">
          <el-form-item label="经营者居所" prop="natureBusiness">
            <el-input v-model="formData.natureBusiness"  clearable >
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      
      <el-row class="rowCss" :gutter="60" style="margin-left:260px">
        <el-col :span="8">
         <el-form-item label="电子邮箱" prop="mail">
          <el-input v-model="formData.mail"  clearable >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="身份证扫描件" prop="idcardNum">
            <el-input v-model="formData.idcardNum"  clearable >
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row class="rowCss" :gutter="60" style="margin-left:260px">
        <el-col :span="8">
         <el-form-item label="行业类型" prop="industryType">
          <el-input v-model="formData.industryType"  clearable >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="出资额" prop="fontName">
            <el-input v-model="formData.fontName"  clearable >
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row class="rowCss" :gutter="60" style="margin-left:260px">
        <el-col :span="16">
          <el-form-item label="经营范围" prop="natureBusiness">
              <el-input
                type="natureBusiness"
                :rows="2"
                placeholder="请输入内容"
                v-model="textarea">
              </el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row class="rowCss" :gutter="60" style="margin-left:260px">
        <el-col :span="8">
         <el-form-item label="私人账户" prop="wordType">
            <el-radio v-model="radio" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
         <el-form-item label="对公账户" prop="wordType">
            <el-radio v-model="radio" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row class="rowCss" :gutter="60" style="margin-left:260px">
        <el-col :span="8">
         <el-form-item label="开户银行" prop="bank">
          <el-input v-model="formData.bank"  clearable >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="银行账号" prop="bankCard">
            <el-input v-model="formData.bankCard"  clearable >
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row class="rowCss" :gutter="60" style="margin-left:260px">
        <el-col :span="8">
         <el-form-item label="渠道商" prop="placeCode">
          <el-input v-model="formData.placeCode"  clearable >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="客户经理" prop="userId">
            <el-input v-model="formData.userId"  clearable >
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row class="rowCss" :gutter="220" style="margin-left:600px">
        <el-col :span="2">
            <el-button type="danger" @click="resetForm">取消</el-button> 
        </el-col>
        <el-col :span="2">
            <el-button type="primary" @click="submitForm">下一步</el-button>
        </el-col>
      </el-row>

    </el-form>
  </div>
</template>
<script>
export default {
  components: {},
  props: [],
  data() {
    return {
      formData: {
        selfKey: '',
        industry: undefined,
        organizationalForm: undefined,
        wordType: 1,
        fontName: undefined,
      },
      rules: {
        selfKey: [{
          required: true,
          message: '请输入个体户编号',
          trigger: 'blur'
        }, {
          pattern: /^1(3|4|5|7|8|9)\d{9}$/,
          message: '手机号格式错误',
          trigger: 'blur'
        }],
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
        wordType: [{
          required: true,
          message: '字号不能为空',
          trigger: 'change'
        }],
        fontName: [{
          required: true,
          message: '请输入拟设名称',
          trigger: 'blur'
        }],
      },
      organizationalFormOptions: [{
        "label": "选项一",
        "value": 1
      }, {
        "label": "选项二",
        "value": 2
      }],
      wordTypeOptions: [{
        "label": "选项一",
        "value": 1
      }],
    }
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {},
  methods: {
    submitForm() {
      this.$refs['elForm'].validate(valid => {
        if (!valid) {
            console.log(111);
        }
        // TODO 提交表单
      })
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
    },
  }
}

</script>
<style rel="stylesheet/scss" lang="scss" scoped>
    .rowCss{
      margin-top:10px;
    }
</style>
