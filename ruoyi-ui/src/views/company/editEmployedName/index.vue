<template>
  <div>
    <el-form 
      ref="elForm" 
      :model="formData" 
      :rules="rules" 
      size="medium" 
      label-position="right"
      label-width="110px">
      <el-steps 
        align-center
        :active="0" 
        finish-status="success"
        style="padding-left: 2%;margin-top:30px;margin-bottom:10px;">
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
          <el-form-item label="冠名类型" >
            <el-input 
              v-model="formData.titleType" 
              placeholder="请输入行政区划" 
              disabled 
              ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item label="行政区划" >
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
          <el-form-item label="行业" prop="industry">
            <el-input 
              v-model="formData.industry" 
              placeholder="请输入行业" 
              clearable 
              ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item label="组织形式" prop="organizationalForm">
            <el-select 
              style="width:100%"
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
        <el-col :span="9" >
          <el-form-item  label="所在行政区划"  >
            <el-input 
              v-model="formData.administrativeRegion" 
              placeholder="请输入所在行政区划" 
              disabled 
              ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item label="登记机关" >
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
         <el-form-item label="字号">
          <el-checkbox 
            v-model="formData.random"
            @change="isRandom">随机字号</el-checkbox>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item label="拟设名称" >
            随机名称
          </el-form-item>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
         <el-form-item prop="fontSize1">
            <el-input 
              v-model="formData.fontSize1"
              :disabled="isDisable"
              clearable>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item >
            <el-input 
              v-model="formData.poposedName1" 
              disabled
              ></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
         <el-form-item  prop="fontSize2">
          <el-input 
            v-model="formData.fontSize2"  
            :disabled="isDisable"
            clearable >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item>
            <el-input 
              v-model="formData.poposedName2"  
              disabled ></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
         <el-form-item  prop="fontSize3">
          <el-input 
            v-model="formData.fontSize3"  
            :disabled="isDisable"
            clearable >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item >
            <el-input 
            v-model="formData.poposedName3"  
            disabled ></el-input>
          </el-form-item>
        </el-col>
      </el-row>

        <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
         <el-form-item  prop="fontSize4">
          <el-input 
            v-model="formData.fontSize4"  
            :disabled="isDisable"
            clearable >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item >
            <el-input 
            v-model="formData.poposedName4"  
            disabled ></el-input>
          </el-form-item>
        </el-col>
      </el-row>


        <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
         <el-form-item  prop="fontSize5">
          <el-input 
            v-model="formData.fontSize5"  
            :disabled="isDisable"
            clearable >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item >
            <el-input 
            v-model="formData.poposedName5"  
            disabled ></el-input>
          </el-form-item>
        </el-col>
      </el-row>


      <el-row type="flex" class="row-bg " justify="space-around">
          <el-col :span="8"></el-col>
          <el-col :span="8" class="flexs">
            <el-button type="danger" @click="toReturn" class="btn">取消</el-button> 
            <el-button 
              type="primary" 
              @click="submitForm" 
              class="btn"
               v-hasPermi="['company:review:edit']">确认修改</el-button>
         </el-col>
          <el-col :span="8"></el-col>
      </el-row>

    </el-form>
  </div>
</template>
<script>
import crudReview from '@/api/company/review'
import crudInformation from '@/api/company/information'
import {getInfo} from '@/api/login' 
export default {
  components: {},
  props: [],
  data() {
    return {
      isDisable:true,
      formData: {
        selfId:'',
        selfCode:'',
        titleType:'区县名',
        administrativeDivision:'漳平市',
        industry: '',
        organizationalForm: '',
        administrativeRegion:'龙岩市漳平市',
        registrationAuthority:'漳平市市场监督管理局',
        random:true,
        //字号
        fontSize1:'',
        fontSize2:'',
        fontSize3:'',
        //拟设名称
        poposedName1:'',
        poposedName2:'',
        poposedName3:'',
        userName:'',
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
  computed: {
  },
  watch: {
    formData:{
         handler:function(){
          this.getPoposedName();
        },
        deep: true
    }
  },
  created() {},
  mounted() {
    // this.getSelfCode();
    this.getLoginInfo();
    var employedName=this.$cache.local.getJSON('employedName');
    console.log("selfCode",employedName.selfCode);
    crudReview.getByCode({selfCode:employedName.selfCode}).then(res=>{
      console.log("getByCode",res);
      this.formData=res;
    })
    
  },
  methods: {
    getLoginInfo(){
      getInfo().then(res=>{  
        this.formData.userName=res.user.nickName;
      })
    },
    getPoposedName(){
        this.formData.poposedName1=this.formData.administrativeDivision+this.formData.fontSize1+this.formData.industry+this.formData.organizationalForm;
        this.formData.poposedName2=this.formData.administrativeDivision+this.formData.fontSize2+this.formData.industry+this.formData.organizationalForm;
        this.formData.poposedName3=this.formData.administrativeDivision+this.formData.fontSize3+this.formData.industry+this.formData.organizationalForm;
    },
    isRandom(){
      if(!this.formData.random){
        this.isDisable=false
      }else{
        this.isDisable=true
        this.formData.fontSize1="";
        this.formData.fontSize2="";
        this.formData.fontSize3="";
        this.formData.fontSize4="";
        this.formData.fontSize5="";
      }
    },
    changeOrganizational(value){
      if(value==1){
        this.formData.organizationalForm="服务部";
      }
      if(value==2){
        this.formData.organizationalForm="经营部";
      }
    },
    //获取编号
    getSelfCode(){
      //获取员工编号
      getInfo().then(res=>{  
        var userId=res.user.userId;
        crudInformation.getInformation(userId).then(res=>{     
          var  employeeNumber=res.data.employeeNumber;
          crudReview.getCode({employeeNumber:employeeNumber}).then(res=>{
            this.formData.selfCode=res;
            console.log("selfCode",res);
          })
        });
      })
    },
    submitForm() {
      this.$refs['elForm'].validate(valid => {
        // TODO 提交表单
        if (valid) {
            let parms={
              selfId:this.formData.selfId,
              selfCode:this.formData.selfCode,
              titleType:this.formData.titleType,
              administrativeDivision:this.formData.administrativeDivision,
              industry:this.formData.industry,
              organizationalForm:this.formData.organizationalForm,
              administrativeRegion:this.formData.administrativeRegion,
              registrationAuthority:this.formData.registrationAuthority,
              random:this.formData.random,
              fontSize1:this.formData.fontSize1,
              fontSize2:this.formData.fontSize2,
              fontSize3:this.formData.fontSize3,
              fontSize4:this.formData.fontSize4,
              fontSize5:this.formData.fontSize5,
              poposedName1:this.formData.poposedName1,
              poposedName2:this.formData.poposedName2,
              poposedName3:this.formData.poposedName3,
              poposedName4:this.formData.poposedName4,
              poposedName5:this.formData.poposedName5,
              // createTime:new Date().toLocaleString(),
              // updateTime:new Date().toLocaleString(),
              createBy:this.formData.userName,
              updateBy:this.formData.userName,
              // nameStatus:1,
            };
            crudReview.updateReview(parms).then(res=>{
              console.log("addReview",res);
              this.$message({
                  message: '修改成功',
                  type: 'success',
              });
              // this.$router.push("employed");
              this.$tab.refreshPage("/company/customer/employed").then(()=>{
              this.$tab.openPage("注册进度", "/company/customer/employed")
            })
              // if(res!=undefined){
              //      if(res.id==0){
              //       this.$message({
              //           message: res.message,
              //           type: 'success',
              //       });
              //     }else{
              //       this.$message({
              //           message: res.message,
              //           type: 'warning',
              //       });
              //     }
              //     this.$router.push("employed");
              // }
              
            });
              
        }else{
          this.$message({
            message:'请填写完整',
            type:'warning'
          })
        }
      })
    },
    toReturn() {
       this.$tab.closeOpenPage({ path: "/company/customer/employed"});
    },
  }
}

</script>
<style rel="stylesheet/scss" lang="scss" scoped>
    .rowCss{
      margin-top: 30px;
    }
      // 改变input框字体颜色
     ::v-deep .is-disabled .el-input__inner{
        background-color: transparent !important;
        color: black;    
    }
     .btn{
      width: 90px;
    }
    .flexs {
  display: flex;
  justify-content: center;

}
</style>
