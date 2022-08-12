<template>
  <div>
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="auto">
     <el-row type="flex" class="row-bg rowCss combottom"  justify="space-around">
        <el-col :span="9">
           <div class="bankno">审核结果</div>
        
        </el-col>
        <el-col :span="8" >
          <div></div>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="法人姓名">
            <el-input v-model="formData.legalPersonName" :readonly="true" clearable>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright"  label="渠道商全名" :required="true">
            <el-input v-model="formData.placeAliasName" :readonly="true" clearable>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      
       <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="客户经理">
            <el-input v-model="formData.username" :readonly="true" clearable>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="个体户编号">
            <el-input v-model="formData.selfCode" placeholder="请输入个体户编号"  :readonly="true" clearable></el-input>
          </el-form-item>
        </el-col>
      </el-row>

     
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="冠名类型">
            <el-input v-model="formData.titleType" placeholder="请输入行政区划" :readonly="true"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="行政区划">
            <el-input v-model="formData.administrativeDivision" placeholder="请输入行政区划" :readonly="true"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="行业" prop="industry">
            <el-input :readonly="true" v-model="formData.industry" placeholder="请输入行业" clearable></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="组织形式" prop="organizationalForm">
            <el-select style="width:100%" disabled v-model="formData.organizationalForm" placeholder="请选择组织形式" clearable
             >
              <el-option v-for="(item, index) in organizationalFormOptions" :key="index" :label="item.label"
                :value="item.value" :disabled="item.disabled"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="所在行政区划">
            <el-input v-model="formData.administrativeRegion" placeholder="请输入所在行政区划" :readonly="true"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="登记机关">
            <el-input v-model="formData.registrationAuthority" placeholder="请输入登记机关" :readonly="true"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="字号">
            <el-checkbox disabled v-model="formData.random">随机字号</el-checkbox>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="拟设名称"> 随机名称 </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" prop="fontSize1">
            <el-input v-model="formData.fontSize1" :readonly="true" clearable>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright">
            <el-input v-model="formData.poposedName1" :readonly="true"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" prop="fontSize2">
            <el-input v-model="formData.fontSize2" :readonly="true" clearable>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright">
            <el-input v-model="formData.poposedName2" :readonly="true"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" prop="fontSize3">
            <el-input v-model="formData.fontSize3" :readonly="true" clearable>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright">
            <el-input v-model="formData.poposedName3" :readonly="true"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

       <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" prop="fontSize4">
            <el-input v-model="formData.fontSize4" :readonly="true" clearable>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright">
            <el-input v-model="formData.poposedName4" :readonly="true"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

       <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" prop="fontSize5">
            <el-input v-model="formData.fontSize5" :readonly="true" clearable>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright">
            <el-input v-model="formData.poposedName5" :readonly="true"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

         <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="21">
          <el-form-item class="comright" style="padding-right: 4.2%;margin-left: -7%;">
            <el-radio disabled v-model="isokradio" label="1"> 通过</el-radio>

          </el-form-item>
        </el-col>

      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="21">
          <el-form-item class="comright" style="padding-right: 4.2%;margin-left: -7%;">
            <div style="display: flex; align-items: center;">
              <el-radio disabled v-model="isokradio" label="2">不通过 </el-radio>
              <el-input placeholder="请输入不通过说明" type="textarea" v-model="this.formData.remarkName" :disabled="isokradio == 1"></el-input>
            </div>


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

export default {
  components: {},
  props: [],
  data() {
    return {
      remark: null,
      isokradio: '1',
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
        fontSize4: "",
        fontSize5: "",
        //拟设名称
        poposedName1: "",
        poposedName2: "",
        poposedName3: "",
        poposedName4: "",
        poposedName5: "",
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

  created() { },
  mounted() {
    this.formData = this.$cache.local.getJSON("employedName");
    if(this.formData.random=='true'){
      this.formData.random=true;
    }else{
      this.formData.random=false;
    }
    if(this.formData.nameStatus==1){
      this.isokradio='1';
    }else if(this.formData.nameStatus==2){
      this.isokradio='2';
    }else if(this.formData.nameStatus==0){
      this.isokradio='0';
    }
 },
  methods: {
      resetForm(){
      this.$tab.closeOpenPage({ path:this.$cache.local.getJSON('backurls').backUrl});
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

.comright {
  padding-right: 10%;
}

.flexs {
  display: flex;
  justify-content: center;

}
.bankno {
  display: flex;
  justify-content: space-between;
  letter-spacing: 2px !important;
  font-size: 20px !important;
  color: blue !important;
  margin-bottom: 10px !important;
}
::v-deep .el-input.is-disabled .el-input__inner{
   background-color: rgba(255, 255, 255, 1.5) !important;
   color: black  !important;
   border-color: rgba(135,206,250,0.7) !important;
}
::v-deep .el-input-group__append{
   background-color: rgba(255, 255, 255, 1.5) !important;
   color: black  !important;
   border-color: rgba(135,206,250,0.7) !important;
}
</style>
