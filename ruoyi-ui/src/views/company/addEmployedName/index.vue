<template>
  <div>
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px"
      label-position="right">
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
               @change="changeOrganizational"
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
         <el-form-item label="字号">
          <el-checkbox 
            v-model="formData.isRandomWord"
            @change="isRandom">随机字号</el-checkbox>
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
         <el-form-item prop="word1">
            <el-input 
              v-model="formData.word1"
              :disabled="isDisable">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item >
            <el-input 
              v-model="titleName+formData.word1+formData.industry+formData.organLabel" 
              disabled></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row class="rowCss" :gutter="60" style="margin-left:260px">
        <el-col :span="8">
         <el-form-item  prop="word2">
          <el-input 
            v-model="formData.word2"  
            :disabled="isDisable"
            clearable >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item>
            <el-input 
              v-model="titleName+formData.word2+formData.industry+formData.organLabel"  
              disabled ></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row class="rowCss" :gutter="60" style="margin-left:260px">
        <el-col :span="8">
         <el-form-item  prop="word3">
          <el-input 
            v-model="formData.word3"  
            :disabled="isDisable"
            clearable >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item >
            <el-input 
            v-model="titleName+formData.word3+formData.industry+formData.organLabel"  
            disabled ></el-input>
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
      isDisable:false,
      titleName:'漳平市',
      formData: {
        organLabel:'',
        selfKey: '',
        industry: '',
        organizationalForm: '',
        isRandomWord: false,
        fontName: '',
        //字号
        word1:'',
        word2:'',
        word3:'',
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
        word1: [{
          required: true,
          message: '请输入字号',
          trigger: 'blur'
        }],
         word2: [{
          required: true,
          message: '请输入字号',
          trigger: 'blur'
        }],
         word3: [{
          required: true,
          message: '请输入字号',
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
        fontName: [{
          required: true,
          message: '请输入拟设名称',
          trigger: 'blur'
        }],
      },
      organizationalFormOptions: [{
        "label": "服务部",
        "value": 1
      }, {
        "label": "经营部",
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
    isRandom(){
      if(this.formData.isRandomWord){
        this.isDisable=true
      }else{
        this.isDisable=false
      }
    },
    changeOrganizational(value){
      console.log("value",value);
      if(value==1){
        this.formData.organLabel="服务部";
      }
      if(value==2){
        this.formData.organLabel="经营部";
      }
    },
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
