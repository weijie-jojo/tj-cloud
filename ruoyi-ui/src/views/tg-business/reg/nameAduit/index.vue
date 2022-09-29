<template>
  <div>
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="auto">
       <el-row type="flex" class="row-bg" style="margin-top:20px" justify="space-around">
        <el-col :span="9" class="flexs">
          <div class="bankno" style="width:40%">个体户名称</div>
          <div style="width:55%;hegiht:10px"></div>
        </el-col>
        <el-col :span="9">
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
          <el-form-item class="comright" label="客户全名" :required="true">
            <el-input v-model="formData.placeAliasName" :readonly="true" clearable>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="客户经理" :required="true">
            <el-input v-model="formData.username" :readonly="true" clearable>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="个体户编号">
            <el-input v-model="formData.selfCode" placeholder="请输入个体户编号" :readonly="true" clearable></el-input>
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
             <!-- <span style="color:green">*个体工商户名称例如“福州市鼓楼区中山社区天天服装店”中，行业为“服装”</span> -->
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item class="comright" label="组织形式" prop="organizationalForm">
            <el-select style="width:100%" disabled v-model="formData.organizationalForm" placeholder="请选择组织形式" clearable
              @change="changeOrganizational">
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
          <el-form-item class="comright" prop="fontSize3">
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
          <el-form-item class="comright" prop="fontSize3">
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
            <el-radio v-model="isokradio" label="1"> 通过</el-radio>

          </el-form-item>
        </el-col>

      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="21">
          <el-form-item class="comright" style="padding-right: 4.2%;margin-left: -7%;">
            <div style="display: flex; align-items: center;">
              <el-radio v-model="isokradio" label="2">不通过 </el-radio>
              <el-input placeholder="请输入不通过说明" type="textarea" v-model="remark" :disabled="isokradio == 1"></el-input>
            </div>


          </el-form-item>
        </el-col>

      </el-row>

      <el-row type="flex" class="row-bg " justify="space-around" style="margin-bottom:20px">
        <el-col :span="8"></el-col>
        <el-col :span='8' class="flexs">
          <el-button type="danger" @click="resetForm">关闭</el-button>
          <el-button v-if="isokradio == 2" type="primary" @click="submitForm(2)">提交</el-button>
          <el-button v-else type="primary" @click="submitForm(1)">提交</el-button>
        </el-col>
        <el-col :span="8"></el-col>
      </el-row>
    </el-form>
  </div>
</template>
<script>
import { getInfo } from "@/api/login";
import { updateReview } from "@/api/tg-api/company/review";
import crudEmployed from '@/api/tg-api/company/employed'
export default {
  name:'NameAduit',
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
        //拟设名称
        poposedName1: "",
        poposedName2: "",
        poposedName3: "",
        username: "",
      },
      userinfo:{},
      checkDate: '',
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
 mounted() {
    this.getlist();
    this.getLoginInfo();
  },
  methods: {
    getlist(){
      this.$modal.loading("正在加载数据，请稍后...");
      crudEmployed.regDetail(this.$cache.local.getJSON("tg-namelist")).then((res) => {
        this.$modal.closeLoading();
        this.formData = res.data;
       
        if (this.formData.random == "true") {
          this.formData.random = true;
         
        } else {
          this.formData.random = false;
        }
      
       
      }).catch((error)=>{
        this.$modal.closeLoading();
      })
    },
    getLoginInfo() {
      getInfo().then((res) => {
        this.userinfo=res.user;
      });
    },
    
    isRandom() {
      if (!this.formData.random) {
        this.isDisable = false;
      } else {
        this.isDisable = true;
        this.formData.fontSize1 = "";
        this.formData.fontSize2 = "";
        this.formData.fontSize3 = "";
        this.formData.fontSize4 = "";
        this.formData.fontSize5 = "";
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
    repair(i) {
      if (i >= 0 && i <= 9) {
        return "0" + i;
      } else {
        return i;
      }
    },
    gettoday() {
      var date = new Date();//当前时间
      var year = date.getFullYear() //年
      var month = this.repair(date.getMonth() + 1);//月
      var day = this.repair(date.getDate());//日

      // var hour = this.repair(date.getHours());//时
      // var minute = this.repair(date.getMinutes());//分
      // var second = this.repair(date.getSeconds());//秒

      //当前时间 
      var curTime = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
      // this.formData.createTime = curTime;
      this.checkDate = curTime;

    },
    //新增名称审核进度
    check(resmsg) {
      let parms = {
        "checkReasult": resmsg,
        "checkUser": this.userinfo.userName,
        'phonenumber':this.userinfo.phonenumber,
        "selfCode": this.formData.selfCode,
        "selfType": "2",
      }
      crudEmployed.check(parms).then(res => {
        console.log('名称审核插入日志成功！');
      }).catch(error => {

      });
    },
    submitForm(type) {

      this.$refs["elForm"].validate((valid) => {
        // TODO 提交表单
        if (valid) {
          let parms;
          if (type == 1) {
            parms = {
              selfCode: this.formData.selfCode,
              nameStatus: type,
            };
          } else {
            parms = {
              selfCode: this.formData.selfCode,
              nameStatus: type,
              nameRemark: this.remark,
              
            };
          }
          updateReview(parms).then((res) => {




            if (res != undefined) {
              if (res.code === 200) {
                this.$nextTick(function () {
                   if (type == 1) {
                      this.check('名称审核已完成');
                    } else {
                       this.check('名称审核不通过。'+'原因：'+this.remark);
                      let parmsEmployed={
                        remarkName:this.remark,
                        selfId: this.formData.selfId,
                        endStatus:2,
                        isSelfCount:this.formData.isSelfCount,
                        placeCode:this.formData.placeCode
                      };
                      crudEmployed.updateEmployed(parmsEmployed).then(res=>{
                      });
                    }
                     this.$tab.closeOpenPage({ path: "/tg-business/reg/result" });
                });
               } else {
                this.$modal.msgError(res.msg);
               
              }
            }

          });
        } else {
            this.$alert('请正确填写', '系统提示', {
              confirmButtonText: '确定',
            
              type: 'warning'
           });
        }
      });
    },
    resetForm() {
      this.$tab.closeOpenPage({ path: this.$cache.local.getJSON('tg-resultBack').backUrl}).then(() => {
        this.$tab.refreshPage({ path: this.$cache.local.getJSON('tg-resultBack').backUrl,name:this.$cache.local.getJSON('tg-resultBack').backName});
     })
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
