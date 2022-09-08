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
            <div class="bankno" style="width: 35%">收款信息</div>
            <div style="width: 50%; hegiht: 10px"></div>
          </el-col>
          <el-col :span="9">
            <div></div>
          </el-col>
        </el-row>
  
        <el-row type="flex" class="row-bg rowCss" justify="space-around">
          <el-col :span="9">
            <el-form-item class="comright" label="转账账户" prop="receiveName">
              <el-input  :disabled="true" v-model="formData.receiveName"></el-input>
            </el-form-item>
            <el-form-item class="comright" label="收账金额" prop="receiveMoney">
              <el-input
              :disabled="true"
                v-model="formData.receiveMoney"
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
              prop="fileNameReceive"
            >
              <uploadSmall
                ref="receive"
                :fileName="isNoneArray"
                :fileNameOld="fileNameN"
                :isDetail="isDetail"
                :index="0"
              ></uploadSmall>
            </el-form-item>
  
          </el-col>
  
          <el-col :span="9">
            <el-form-item class="comright" label="转账时间" :required="true">
              <el-input v-model="formData.receiveTime" disabled></el-input>
            </el-form-item>
  
            <el-form-item class="comright" label="转账账号" prop="receiveAccount">
              <el-input v-model="formData.receiveAccount"  ></el-input>
            </el-form-item>
            <el-form-item class="comright" label="财务流水号" prop="receiveCode">
              <el-input v-model="formData.receiveCode"  ></el-input>
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
  import { check,detail,editReceive,edit,finshReceiveList} from "@/api/project/list";
  import { getInfo } from "@/api/login";
  export default {
    name: "AduitCollect",
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
        isDetail: "1",
        fileName: [],
        
        formData: {
          isCheck: 0,
          fileNameReceive: [],
          projectCode: "",
          projectName: "",
          receiveTime: "", //转账时间 收款信息
          receiveSysCode: "", //流水号
          receiveAccount: "", //转账账号 收款信息
          receiveName: "", //转账账户 收款信息
          receiveMoney: "0.00000", //收款金额 收款信息
        },
        rules: {
          
          receiveCode: [
            {
              required: true,
              message: "财务流水号不能为空",
              trigger: "blur",
            },
          ],
          receiveName: [
            {
              required: true,
              message: "转账账户不能为空",
              trigger: "blur",
            },
          ],
          receiveMoney: [
            {
              required: true,
              message: "转账金额不能为空",
              trigger: "blur",
            },
          ],
          receiveAccount: [
            {
              required: true,
              message: "转账账号不能为空",
              trigger: "blur",
            },
          ],
          fileNameReceive: [
            {
              required: true,
              message: "转账凭证不能为空",
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
      this.formData.fileNameReceive=JSON.parse(this.formData.fileNameReceive)
      this.$refs.receive.getSrcList(this.formData.fileNameReceive);
      for(let i in this.formData.fileNameReceive){
             this.fileNameN.push({
              name:this.formData.fileNameReceive[i],
              url:this.baseImgPath+this.formData.fileNameReceive[i]
             })
          }
    },
    methods: {
      //获取公共数据
      getCommonList(){
          detail({
           projectCode: this.$cache.local.getJSON("projectCodeNew")
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
            projectType: "20",
          };
          check(parms)
            .then((res) => {
              console.log("修改收款成功!");
            })
            .catch((error) => {});
        });
      },
     
      //返回
      resetForm() {
        if(this.$cache.local.getJSON('iscollect')==0){
           this.$tab.closeOpenPage({
            path:'/projectlist/aduitCollectList'
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
     
      submitForm(type) {
        this.$refs["elForm"].validate((valid) => {
          // TODO 提交表单
          if (valid) {
           let params={
            receiveId:this.formData.receiveId,
            isCheck:0,
            receiveRemark:'',
           };
           
            editReceive(params).then((res) => {
              if (res != undefined) {
                if (res.code === 200) {
                    finshReceiveList({
                        projectCode: this.formData.projectCode,
                    }).then((res)=>{
                        let data=res.data;
                        this.types=0;
                         for(let i in data){
                           if(data[i].receiveId==this.formData.receiveId){

                           }else{
                            if(data[i].isCheck==2){
                               this.type=2;
                                break;
                            }

                           }
                         }

                         let parms = {
                          projectId: this.publicList.projectId,
                          projectReceiveStatus:this.types,
                        };
                         edit(parms);
                        this.check('修改收款完成')
                        this.$modal.msgSuccess("修改收款成功");
                     if(this.$cache.local.getJSON('iscollect')==0){
                        this.$tab.refreshPage({
                         path:'/projectlist/aduitCollectList',
                         name:'AduitCollectList'
                         })
                     }else{
                       this.$tab.refreshPage({
                           path: this.$cache.local.getJSON("Projectedit").backurl,
                           name: this.$cache.local.getJSON("Projectedit").name,
                          });
                     }
                    })
                      
                      
                      
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
  