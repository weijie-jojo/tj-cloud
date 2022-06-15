<template>
  <div>
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px"
      label-position="left">
      <el-steps :space="1500" 
        :active="1" 
        finish-status="success"
        style="margin-left:280px;margin-top:30px;margin-bottom:80px;">
        <el-step title="个体户名称"></el-step>
        <el-step title="个体户信息"></el-step>
        <el-step title="提交"></el-step>
      </el-steps>

      <el-row  :gutter="60" class="rowCss" style="margin-left:260px">
        <el-col :span="8">
          <el-form-item label="个体户编号" >
            <el-input 
              v-model="formData.selfKey" 
              placeholder="请输入个体户编号" 
              :disabled='true'
              clearable 
              ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      
      <el-row class="rowCss" :gutter="60" style="margin-left:260px">
        <el-col :span="8">
          <el-form-item label="行业" prop="industry">
            <el-input 
              v-model="formData.industry" 
              placeholder="请输入行业" 
              clearable 
              ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="组织形式" prop="organizationalForm">
            <el-select 
              v-model="formData.organizationalForm" 
              placeholder="请选择组织形式" 
              clearable
              style="width:410px">
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
     
     <el-row class="rowCss" :gutter="60" style="margin-left:260px">
        <el-col :span="8">
         <el-form-item label="字号" prop="wordType">
            <el-radio-group v-model="formData.wordType" size="medium">
              <el-radio 
                v-for="(item, index) in wordTypeOptions" 
                :key="index" :label="item.value"
                :disabled="item.disabled">{{item.label}}</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="拟设名称" >
            随机名称
          </el-form-item>
        </el-col>
      </el-row>

      <el-row class="rowCss" :gutter="60" style="margin-left:260px">
        <el-col :span="8">
         <el-form-item  prop="fontName">
          <el-input v-model="formData.fontName"  clearable >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item  prop="fontName">
            <el-input v-model="formData.fontName"  clearable >
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row class="rowCss" :gutter="60" style="margin-left:260px">
        <el-col :span="8">
         <el-form-item  prop="fontName">
          <el-input v-model="formData.fontName"  clearable >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item  prop="fontName">
            <el-input v-model="formData.fontName"  clearable >
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row class="rowCss" :gutter="60" style="margin-left:260px">
        <el-col :span="8">
         <el-form-item  prop="fontName">
          <el-input v-model="formData.fontName"  clearable >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item  prop="fontName">
            <el-input v-model="formData.fontName"  clearable >
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
        industry: '',
        organizationalForm: '',
        wordType: 1,
        fontName: '',
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
        // TODO 提交表单
        if (valid) {
            this.$router.push("addEmployedInfo")
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
    .rowCss{
      margin-top: 30px;
    }
</style>
