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
        <el-step title="结算信息"></el-step>
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
                <el-collapse accordion>
                <el-collapse-item>
                <template slot="title">
                <i class="header-icon el-icon-info"></i>
              </template>
              <div style="color:green">个体工商户名称，例如“福州市鼓楼区中山社区天天服装店”中，行业为“服装”</div>
               </el-collapse-item>
               </el-collapse>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <!-- <el-form-item label="组织形式" prop="organizationalForm">
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
          </el-form-item> -->
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
         <el-form-item label="字号" prop="random">
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
            <el-button type="danger" @click="toReturn" class="btn">关闭</el-button> 
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
import crudEmployed from '@/api/company/employed'
import crudInformation from '@/api/company/information'
import {getInfo} from '@/api/login' 
export default {
  name:'EditEmployedName',
  components: {},
  props: [],
  data() {
    return {
      userinfo:{},
      isDisable:false,
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
        fontSize4:'',
        fontSize5:'',
        //拟设名称
        poposedName1:'',
        poposedName2:'',
        poposedName3:'',
        poposedName4: "",
        poposedName5: "",
        username:'',
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
        random:[{
          required: true,
          message: '请选择字号',
          trigger: 'change'
      }]
       
      
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
  
  mounted() {
    this.getLoginInfo();
    var employedName=this.$cache.local.getJSON('tj-namelist');
    crudReview.getByCode({selfCode:employedName.selfCode}).then(res=>{
    this.formData=res;
     if(this.formData.random=='true'){
      this.formData.random=true;
      this.isRandom();
     }else{
       this.formData.random=false;
     }
   
    })
    
  },
  methods: {
     check(msg){
      let parms = {
            "checkReasult": msg,
            "checkUser": this.userinfo.userName,
            'phonenumber': this.userinfo.phonenumber,
            "selfCode": this.formData.selfCode,
            "selfType": "9",
          }
          crudEmployed.check(parms).then(res => {
            if (res != undefined) {
              if (res.code === 200) {
               
              } else {
                this.$message({
                  message: res.msg,
                  type: 'danger'
                })
              }
            }
          })
    },
    getLoginInfo(){
      getInfo().then(res=>{  
        this.userinfo=res.user;
       })
    },
    getPoposedName(){
        this.formData.poposedName1=this.formData.administrativeDivision+this.formData.fontSize1+this.formData.industry+this.formData.organizationalForm;
        this.formData.poposedName2=this.formData.administrativeDivision+this.formData.fontSize2+this.formData.industry+this.formData.organizationalForm;
        this.formData.poposedName3=this.formData.administrativeDivision+this.formData.fontSize3+this.formData.industry+this.formData.organizationalForm;
        this.formData.poposedName4=this.formData.administrativeDivision+this.formData.fontSize4+this.formData.industry+this.formData.organizationalForm;
        this.formData.poposedName5=this.formData.administrativeDivision+this.formData.fontSize5+this.formData.industry+this.formData.organizationalForm;
    },
    isRandom(){
      if(!this.formData.random){
        this.isDisable=false;
        this.formData.poposedName1=this.$cache.local.getJSON('tj-namelist').poposedName1;
        this.formData.poposedName2=this.$cache.local.getJSON('tj-namelist').poposedName3;
        this.formData.poposedName3=this.$cache.local.getJSON('tj-namelist').poposedName3;
        this.formData.poposedName4=this.$cache.local.getJSON('tj-namelist').poposedName4;
        this.formData.poposedName5=this.$cache.local.getJSON('tj-namelist').poposedName5;
        this.formData.fontSize1=this.$cache.local.getJSON('tj-namelist').fontSize1;
        this.formData.fontSize2=this.$cache.local.getJSON('tj-namelist').fontSize2;
        this.formData.fontSize3=this.$cache.local.getJSON('tj-namelist').fontSize3;
        this.formData.fontSize4=this.$cache.local.getJSON('tj-namelist').fontSize4;
        this.formData.fontSize5=this.$cache.local.getJSON('tj-namelist').fontSize5;
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
  
    submitForm() {
      this.$refs['elForm'].validate(valid => {
        // TODO 提交表单
        if (valid) {
           
          if(this.$cache.local.getJSON('tj-namelist').infoStatus==0 || this.$cache.local.getJSON('tj-namelist').infoStatus==1){
            
             let parmsEmployed={
              selfId:this.$cache.local.getJSON('tj-namelist').selfId,
              endStatus:0,
             }
              crudEmployed.updateEmployed(parmsEmployed);
            }
            let parms={
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
             
              nameStatus:0,
            
            };
           
            crudReview.updateReview(parms).then(res=>{
            this.check('名称异常修改完成');
            this.$modal.msgSuccess('名称异常修改完成');
            this.$tab.closeOpenPage( {path:"/tj-business/reg/employed"} ).then(() => {
                this.$tab.refreshPage( {path:"/tj-business/reg/employed",name:'Employed'} );
               })
             })
              
        }else{
            this.$alert('请正确填写', '系统提示', {
              confirmButtonText: '确定',
             
              type: 'warning'
           });
        }
      })
    },
    toReturn() {
        this.$tab.closeOpenPage( {path:"/tj-business/reg/employed"} ).then(() => {
        this.$tab.refreshPage( {path:"/tj-business/reg/employed",name:'Employed'} );
         })
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
