<template>
    <div>
      <el-form
        ref="elForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="140px"
      >
        <el-row
          type="flex"
          class="row-bg"
          style="margin-top: 20px"
          justify="space-around"
        >
          <el-col :span="9" class="flexs">
            <div class="bankno" style="width: 35%">项目信息</div>
            <div style="width: 50%; hegiht: 10px"></div>
          </el-col>
          <el-col :span="9">
            <div></div>
          </el-col>
        </el-row>
  
        <el-row type="flex" class="row-bg rowCss" justify="space-around">
          <el-col :span="9">
            <el-form-item class="comright" label="项目编号" :required="true">
              <el-input
                v-model="publicList.projectCode"
                :disabled="true"
              ></el-input>
            </el-form-item>
  
            <el-form-item class="comright" label="项目名称" :required="true">
              <el-input
                v-model="publicList.projectName"
                :disabled="true"
              ></el-input>
            </el-form-item>
            <el-form-item class="comright" label="应收账款" :required="true">
              <el-input
                :disabled="true"
                v-model="publicList.projectTotalAmount"
                onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
              >
                <template slot="append">元</template>
              </el-input>
            </el-form-item>
            <el-form-item class="comright" label="已收账款" :required="true">
              <el-input
                :disabled="true"
                v-model="publicList.receiveMoneys"
                :step="0.00001"
                :min="0"
                onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
              >
                <template slot="append">元</template>
              </el-input>
            </el-form-item>
            <el-form-item class="comright" label="未收账款" :required="true">
              <el-input
                :disabled="true"
                v-model="publicList.receiveRemainMoneys"
                :step="0.00001"
                :min="0"
                onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
              >
                <template slot="append">元</template>
              </el-input>
            </el-form-item>
          </el-col>
  
          <el-col :span="9">
            <el-form-item class="comright" label="项目时间" :required="true">
              <el-input v-model="publicList.createTime" disabled></el-input>
            </el-form-item>
            <el-form-item class="comright" label="项目金额" :required="true">
              <el-input
                :disabled="true"
                type="number"
                v-model="publicList.projectTotalAmount"
                :step="0.00001"
                :min="0"
                onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
              >
                <template slot="append"> 元 </template>
              </el-input>
            </el-form-item>
            <el-form-item class="comright" label="应出账款" :required="true">
              <el-input
                :disabled="true"
                v-model="publicList.payTotalMoneys"
                :step="0.00001"
                :min="0"
                onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
              >
                <template slot="append">元</template>
              </el-input>
            </el-form-item>
            <el-form-item class="comright" label="已出账款" :required="true">
              <el-input
                :disabled="true"
                v-model="publicList.payMoneys"
                :step="0.00001"
                :min="0"
                oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
              >
                <template slot="append">元</template>
              </el-input>
            </el-form-item>
            <el-form-item class="comright" label="未出账款" :required="true">
              <el-input
                disabled
                v-model="publicList.payRemainMoneys"
                :step="0.00001"
                :min="0"
                onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
              >
                <template slot="append">元</template>
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
  
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9" class="flexs">
            <div class="bankno" style="width: 35%">出款信息</div>
            <div style="width: 50%; hegiht: 10px"></div>
          </el-col>
          <el-col :span="9">
            <div></div>
          </el-col>
        </el-row>
  
        <el-row type="flex" class="row-bg rowCss" justify="space-around">
          <el-col :span="9">
            <el-form-item class="comright" label="出账账户" prop="payName">
              <el-input   v-model="formData.payName"></el-input>
            </el-form-item>
            <el-form-item class="comright" label="出账金额" prop="payMoney">
              <el-input
             
                v-model="formData.payMoney"
                :step="0.00001"
                :min="0"
                onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
              >
                <template slot="append">元</template>
              </el-input>
            </el-form-item>
            <el-form-item
              class="comright"
              label="转账凭证"
              prop="fileNamePay"
            >
              <uploadSmall
                @getfileName="getPay"
                ref="receive"
                :fileName="isNoneArray"
                :fileNameOld="fileNameN"
                :isDetail="isDetail"
                :index="0"
              ></uploadSmall>
            </el-form-item>
  
          </el-col>
  
          <el-col :span="9">
            <el-form-item class="comright" label="出账时间" :required="true">
              <el-input v-model="formData.payTime" disabled></el-input>
            </el-form-item>
  
            <el-form-item class="comright" label="出账账号" prop="payAccount">
              <el-input v-model="formData.payAccount"  ></el-input>
            </el-form-item>
            <el-form-item class="comright" label="财务流水号" prop="payCode">
              <el-input v-model="formData.payCode"  ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
  
      
        
  
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="8"></el-col>
          <el-col :span="8" class="flexs">
            <el-button type="danger" @click="resetForm">关闭</el-button>
            <el-button  type="primary" @click="submitForm">提交</el-button>
            
          </el-col>
          <el-col :span="8"></el-col>
        </el-row>
      </el-form>
    </div>
  </template>
  <script>
  import uploadSmall from "@/components/douploads/uploadCollect";
  import { check,detail,editPay} from "@/api/project/list";
  import { getInfo } from "@/api/login";
  export default {
    name: "DisburseEdit",
    components: { uploadSmall },
    data() {
      return {
        types:'0',
        isokradioS:'1',
        fileNameN:[],
        isNoneArray:[],
        index: 0,
        remark:'',
        publicList: {},
        userinfo: {},
        isDetail: "0",
        fileName: [],
        
        formData: {
         
        },
        rules: {
          
          payCode: [
            {
              required: true,
              message: "财务流水号不能为空",
              trigger: "blur",
            },
          ],
          payName: [
            {
              required: true,
              message: "出账账户不能为空",
              trigger: "blur",
            },
          ],
          payMoney: [
            {
              required: true,
              message: "出账金额不能为空",
              trigger: "blur",
            },
          ],
          payAccount: [
            {
              required: true,
              message: "出账账号不能为空",
              trigger: "blur",
            },
          ],
          fileNamePay: [
            {
              required: true,
              message: "出账凭证不能为空",
              trigger: "change",
            },
          ],
        },
        baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
      };
    },
    computed: {},
    mounted() {
      this.getCommonList();
      this.formData=this.$cache.local.getJSON("collectDetails");
      this.formData.fileNamePay=JSON.parse(this.formData.fileNamePay)
      this.$refs.receive.getSrcList(this.formData.fileNamePay);
      for(let i in this.formData.fileNamePay){
             this.fileNameN.push({
              name:this.formData.fileNamePay[i],
              url:this.baseImgPath+this.formData.fileNamePay[i]
             })
          }
    },
    methods: {
      getPay(data) {
      this.formData.fileNamePay = data;
      console.log(3333,data);
      },
      //获取公共数据
      getCommonList(){
          detail({
           projectCode: this.$cache.local.getJSON("tc-project-code")
          }).then((response) => {
           this.publicList = response.data;
         });
      },
     //收款日志
      check(resmsg) {
        getInfo().then((res) => {
          this.userinfo = res.user;
          let parms = {
            checkReasult: resmsg,
            checkUser: this.userinfo.userName,
            phonenumber: this.userinfo.phonenumber,
            projectCode: this.formData.projectCode,
            projectType: "23",
          };
          check(parms)
            .then((res) => {
              console.log("修改出款成功!");
            })
            .catch((error) => {});
        });
      },
     
      //返回
      resetForm() {
        if(this.$cache.local.getJSON('tc-ifcollect')==0){
           this.$tab.closeOpenPage({
            path:'/projectlist/aduitDisburseList'
           })
        }else{
          this.$tab.closeOpenPage({
          path: this.$cache.local.getJSON("aduitProjectBack").backurl,
        });
        }
        
      },
      handleChange(val) {
        console.log(val);
      },
     
      submitForm() {
        this.$refs["elForm"].validate((valid) => {
          // TODO 提交表单
          if (valid) {
           this.formData.payRemark='';
           this.formData.isCheck=0;
           if(Array.isArray(this.formData.fileNamePay)){
            this.formData.fileNamePay=JSON.stringify(this.formData.fileNamePay);
           }
            editPay(this.formData).then((res) => {
              if (res != undefined) {
                if (res.code === 200) {
                     this.check('修改出款完成')
                     this.$modal.msgSuccess("修改出款成功");
                     if(this.$cache.local.getJSON('tc-ifcollect')==0){
                        this.$tab.refreshPage({
                         path:'/projectlist/aduitDisburseList',
                         name:'AduitDisburseList'
                         })
                     }else{
                       this.$tab.refreshPage({
                           path: this.$cache.local.getJSON("Projectedit").backurl,
                           name: this.$cache.local.getJSON("Projectedit").name,
                          });
                     }
                    } 
                  }
            });
          } else {
            this.$alert("请正确填写", "系统提示", {
              confirmButtonText: "确定",
  
              type: "warning",
            });
          }
        });
      },
    },
  };
  </script>
      <style rel="stylesheet/scss" lang="scss" scoped>
  .rowCss {
    margin-top: 10px;
  }
  
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
    margin-top: 10px;
  }
  
  .comright {
    padding-right: 10%;
  }
  
  .combottom {
    margin-bottom: 10px;
  }
  
  .flexs {
    display: flex;
    justify-content: center;
  }
  
  .bankno {
    letter-spacing: 2px;
    font-size: 20px;
    color: blue;
  }
  ::v-deep .el-input.is-disabled .el-input__inner {
      background-color: rgba(255, 255, 255, 1.5) !important;
      color: black !important;
      border-color: rgba(135, 206, 250, 0.7) !important;
  }
  
  ::v-deep .el-input-group__append {
      background-color: rgba(255, 255, 255, 1.5) !important;
      color: black !important;
      border-color: rgba(135, 206, 250, 0.7) !important;
  }
  </style>
  