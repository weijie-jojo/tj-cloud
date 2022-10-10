<template>
    <div>
      <el-form
        ref="elForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="140px"
      >
     
  
        <el-row type="flex" class="row-bg" justify="space-around" style="margin-bottom:10px">
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
              <!-- <el-input   v-model="formData.payName"></el-input> -->
              <el-select
              v-model="formData.payName"
              placeholder="请选择"
              @change="getCarInfoByCompanyIdS"
              style="width: 100%"
            >
              <el-option
                v-for="item in payCompanys"
                :key="item.groupCode"
                :label="item.groupName"
                :value="item.groupName"
              >
              </el-option>
            </el-select>
            </el-form-item>
            <el-form-item class="comright" label="出账金额" prop="payMoney">
              <el-input
             
                v-model="formData.payMoney"
                :step="0.01"
                :min="0"
                onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
              >
                <template slot="append">元</template>
              </el-input>
            </el-form-item>
            <el-form-item class="comright" label="付款账户" :required="true">
            <el-input  v-model="formData.paymentName"></el-input>
          </el-form-item>
            <el-form-item
              class="comright"
              label="出款凭证"
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
            <el-form-item class="comright" label="出账账号" prop="payAccount">
              <el-input v-model="formData.payAccount"  :readonly="true"></el-input>
            </el-form-item>
            <el-form-item class="comright" label="出款时间" prop="payTime">
              <el-date-picker
             
              style="width: 100%"
              v-model="formData.payTime"
              value-format="yyyy-MM-dd"
              :picker-options="pickerOptions"
              align="right"
            >
            </el-date-picker>
            </el-form-item>
         <el-form-item class="comright" label="付款账号" prop="paymentAccount">
            <el-input  v-model="formData.paymentAccount"></el-input>
          </el-form-item>
           
          </el-col>
        </el-row>
  
      
        
  
        <el-row type="flex" class="row-bg" justify="space-around" style="margin-bottom:20px">
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
  import { getAllCompany } from "@/api/invoices/borrow";
  import uploadSmall from "@/components/douploads/uploadCollect";
  import { check,detail,editPay,detailPay,payList2,edit} from "@/api/project/list";
  import { getInfo } from "@/api/login";
  export default {
    name: "DisburseEdit",
    components: { uploadSmall },
    data() {
      return {
        pickerOptions: {
          disabledDate(time) {
            return time.getTime() > Date.now();
          },
          shortcuts: [{
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date());
            }
          }, {
            text: '昨天',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit('pick', date);
            }
          }, {
            text: '一周前',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', date);
            }
          }]
        },
        projectStatusNew:'',
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
          payTime:[{
            required: true,
              message: "付款时间不能为空",
              trigger: "change",
          }],
          paymentName: [
            {
              required: true,
              message: "付款账户不能为空",
              trigger: "blur",
            },
          ],
          paymentAccount: [
            {
              required: true,
              message: "付款账号不能为空",
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
        payCompanys:[],
        baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
      };
    },
    computed: {},
    mounted() {
      this.getAllCompany();
      this.getCommonList();
      this.getlist();
    },
    methods: {
      getAllCompany() {
      getAllCompany().then((res) => {
        this.payCompanys = res.list;
        console.log("payCompanys==", this.payCompanys);
      });
    },
       //出款账号
    getCarInfoByCompanyIdS() {
      var cardInfo = this.payCompanys.find(
        (item) => item.groupName == this.formData.payName
      );
      this.formData.payAccount = cardInfo.groupBankAccount;
    },
      getlist(){
          this.$modal.loading("正在加载数据，请稍后...");
          detailPay(this.$cache.local.getJSON("tj-payId")).then((response) => {
          this.formData = response.data;
          this.$modal.closeLoading();
          this.formData.fileNamePay=JSON.parse(this.formData.fileNamePay)
          this.$refs.receive.getSrcList(this.formData.fileNamePay);
          for(let i in this.formData.fileNamePay){
                this.fileNameN.push({
                  name:this.formData.fileNamePay[i],
                  url:this.baseImgPath+this.formData.fileNamePay[i]
                })
              }
          
          }).catch((err) => {
              this.$modal.closeLoading();
            });
    },
      getPay(data) {
         this.formData.fileNamePay = data;
      },
      //获取公共数据
      getCommonList(){
          detail({
           projectCode: this.$cache.local.getJSON("tj-project-code")
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
        if(this.$cache.local.getJSON('tj-ifcollect')==0){
           this.$tab.closeOpenPage({
            path:'/tj-business/project/aduitDisburseList'
           })
        }else{
          this.$tab.closeOpenPage({
          path: this.$cache.local.getJSON("tj-aduitback").backurl,
        });
        }
        
      },
      handleChange(val) {
        console.log(val);
      },
     //获取操作时间
     getRealTime(){
      var date = new Date(); //当前时间
      var year = date.getFullYear(); //年
      var month = this.repair(date.getMonth() + 1); //月
      var day = this.repair(date.getDate()); //日
      var hour = this.repair(date.getHours()); //时
      var minute = this.repair(date.getMinutes()); //分
      var second = this.repair(date.getSeconds()); //秒
      //当前时间
      
     return   hour +":" +minute + ":" + second;
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
           if(this.formData.payTime.indexOf(":")!=-1){
              
            }else{
              this.formData.payTime=this.formData.payTime+" "+this.getRealTime();
            }
            this.$modal.loading("正在提交中，请稍后...");
            editPay(this.formData).then((res) => {
              if (res != undefined) {
                if (res.code === 200) {

                  payList2({
                  projectCode: this.publicList.projectCode,
                })
                  .then((res) => {
                    let arr = res;
                    detail({
                  projectCode: this.$cache.local.getJSON("tj-project-code"),
                }).then((response) => {
                  let publicList = response.data;
                  if (
                    publicList.projectDutypaidStatus == 1 &&
                    publicList.projectReceiveStatus == 1 &&
                    publicList.projectTicketStatus == 1 &&
                    publicList.projectAcceptanceStatus == 1 &&
                    publicList.projectContractStatus == 1 &&
                    publicList.projectCheckStatus == 1
                  ) {
                    this.projectStatusNew = 2;
                  } else if (
                    publicList.projectDutypaidStatus == 2 ||
                    publicList.projectReceiveStatus == 2 ||
                    publicList.projectTicketStatus == 2 ||
                    publicList.projectAcceptanceStatus == 2 ||
                    publicList.projectCheckStatus == 2 ||
                    publicList.projectContractStatus == 2
                  ) {
                    this.projectStatusNew = 1;
                  } else {
                    this.projectStatusNew = 0;
                  }
                  this.publicList.projectPayStatus=0;
                  this.publicList.projectStatus=this.projectStatusNew;
                  arr.map((item) => {
                     if (item.isCheck == 2) {
                      this.publicList.projectPayStatus=2;
                      return (this.publicList.projectStatus = 1);
                     }
                  });
                  
                 
                  this.$nextTick(function () {
                  edit(this.publicList).then(res=>{
                    this.$modal.closeLoading();
                     this.check('修改出款完成')
                     this.$modal.msgSuccess("修改出款成功");
                     if(this.$cache.local.getJSON('tj-ifcollect')==0){
                        this.$tab.refreshPage({
                         path:'/tj-business/project/aduitDisburseList',
                         name:'AduitDisburseList'
                         })
                     }else{
                       this.$tab.refreshPage({
                           path: this.$cache.local.getJSON("tj-edit-project").url,
                           name: this.$cache.local.getJSON("tj-edit-project").name,
                          });
                     }
                  })
                });
                  
                });
               });

                 } 
                  }
            });
          } else {
            this.$modal.closeLoading();
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
  