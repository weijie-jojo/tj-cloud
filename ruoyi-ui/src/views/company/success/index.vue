<template>
  <div class="title">
    提交成功
  </div>
</template>
<script>
import crudReview from '@/api/company/review'

export default {
  components: {},
  props: [],
  data() {
    return {
      isDisable:false,
      formData: {
        selfKey:'',
        titleType:'区县名',
        administrativeDivision:'漳平市',
        industry: '',
        organizationalForm: '',
        administrativeRegion:'',
        registrationAuthority:'',
        random:true,
        //字号
        fontSize1:'',
        fontSize2:'',
        fontSize3:'',
        //拟设名称
        poposedName1:'',
        poposedName2:'',
        poposedName3:'',
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
    this.getSelfKey();
  },
  methods: {
    getPoposedName(){
        this.formData.poposedName1=this.formData.administrativeDivision+this.formData.fontSize1+this.formData.industry+this.formData.organizationalForm;
        this.formData.poposedName2=this.formData.administrativeDivision+this.formData.fontSize2+this.formData.industry+this.formData.organizationalForm;
        this.formData.poposedName3=this.formData.administrativeDivision+this.formData.fontSize3+this.formData.industry+this.formData.organizationalForm;
    },
    isRandom(){
      if(this.formData.random){
        this.isDisable=true
      }else{
        this.isDisable=false
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
    getSelfKey(){
        this.formData.selfKey='YYW0001'
    },
    submitForm() {
      this.$refs['elForm'].validate(valid => {
        // TODO 提交表单
        if (valid) {
            let parms={
              selfKey:this.formData.selfKey,
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
              poposedName1:this.formData.poposedName1,
              poposedName2:this.formData.poposedName2,
              poposedName3:this.formData.poposedName3,
            };
            crudReview.addReview(parms).then(res=>{
              if(res!=undefined){
                  if(res.code===200){
                    this.$message({
                      message:res.msg,
                      type:'success'
                    })
                    }else{
                      this.$message({
                          message:res.msg,
                          type:'danger'
                      })
                  }
                  this.$router.push("addEmployedInfo"); 
              }
              
            });
              
        }else{
          this.$message({
            message:'请填写完整',
            type:'warning'
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
    .rowCss{
      margin-top: 30px;
    }
    .title{
      font-size: 50px;
      margin-top: 200px;
    }
</style>
