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
        justify="space-around"
        style="margin-top: 10px"
      >
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
          <el-form-item class="comright" label="收款账户" prop="receiveName">
            <el-select
              v-model="formData.receiveName"
              placeholder="请选择付款单位"
              @change="getCarInfoByCompanyId"
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
          <el-form-item class="comright" label="收款金额" prop="receiveMoney">
            <el-input
              v-model="formData.receiveMoney"
              :step="0.01"
              :min="0"
              onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>

          <el-form-item class="comright" label="付款账户" prop="paymentName">
            <el-input v-model="formData.paymentName"></el-input>
          </el-form-item>

          <el-form-item
            class="comright"
            label="收款凭证"
            prop="fileNameReceive"
          >
            <uploadSmall
              @getfileName="getReceive"
              ref="receive"
              :fileName="isNoneArray"
              :fileNameOld="fileNameN"
              :isDetail="isDetail"
              :index="0"
            ></uploadSmall>
          </el-form-item>
        </el-col>

        <el-col :span="9">
          <el-form-item class="comright" label="收款账号" prop="receiveAccount">
            <el-input v-model="formData.receiveAccount"></el-input>
          </el-form-item>
          <el-form-item class="comright" label="收款时间" prop="receiveTime">
            <el-date-picker
              style="width: 100%"
              v-model="formData.receiveTime"
              value-format="yyyy-MM-dd"
              :picker-options="pickerOptions"
              align="right"
            >
            </el-date-picker>
          </el-form-item>

          <el-form-item class="comright" label="付款账号" prop="paymentAccount">
            <el-input v-model="formData.paymentAccount"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row
        type="flex"
        class="row-bg"
        justify="space-around"
        style="margin-bottom: 20px"
      >
        <el-col :span="8"></el-col>
        <el-col :span="8" class="flexs">
          <el-button type="danger" @click="resetForm">关闭</el-button>
          <el-button type="primary" @click="submitForm">提交</el-button>
        </el-col>
        <el-col :span="8"></el-col>
      </el-row>
    </el-form>
  </div>
</template>
<script>
import { getAllCompany } from "@/api/invoices/borrow";
import uploadSmall from "@/components/douploads/uploadCollect";
import {
  check,
  detail,
  editReceive,
  detailCollect,
  receiveList2,
  edit,
} from "@/api/tc-api/project/list";
import { getInfo } from "@/api/login";
export default {
  name: "CollectEdit",
  components: { uploadSmall },
  data() {
    return {
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now();
        },
        shortcuts: [
          {
            text: "今天",
            onClick(picker) {
              picker.$emit("pick", new Date());
            },
          },
          {
            text: "昨天",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit("pick", date);
            },
          },
          {
            text: "一周前",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", date);
            },
          },
        ],
      },
      payCompanys: [],
      types: "0",
      isokradioS: "1",
      fileNameN: [],
      isNoneArray: [],
      index: 0,
      remark: "",
      publicList: {},
      userinfo: {},
      isDetail: "1",
      fileName: [],

      formData: {},
      rules: {
        receiveTime: [
          {
            required: true,
            message: "收款信息收款时间不能为空",
            trigger: "change",
          },
        ],
        paymentAccount: [
          {
            required: true,
            message: "收款信息付款账号不能为空",
            trigger: "blur",
          },
        ],
        paymentName: [
          {
            required: true,
            message: "收款信息付款账户不能为空",
            trigger: "blur",
          },
        ],

        receiveName: [
          {
            required: true,
            message: "收款信息收款账户不能为空",
            trigger: "blur",
          },
        ],
        receiveMoney: [
          {
            required: true,
            message: "收款信息收款金额不能为空",
            trigger: "blur",
          },
        ],
        receiveAccount: [
          {
            required: true,
            message: "收款信息收款账号不能为空",
            trigger: "blur",
          },
        ],
        fileNameReceive: [
          {
            required: true,
            message: "收款信息收款凭证不能为空",
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
    this.getlist();
    this.getAllCompany();
  },
  methods: {
    getAllCompany() {
      getAllCompany().then((res) => {
        this.payCompanys = res.list;
        console.log("payCompanys==", this.payCompanys);
      });
    },
    //根据出款单位id查找出款银行卡信息
    getCarInfoByCompanyId() {
      var cardInfo = this.payCompanys.find(
        (item) => item.groupName == this.formData.receiveName
      );
      this.formData.receiveAccount = cardInfo.groupBankAccount;
    },
    getlist() {
      this.$modal.loading("正在加载数据，请稍后...");
      detailCollect(this.$cache.local.getJSON("tc-collReceiveId"))
        .then((response) => {
          this.formData = response.data;
          this.$modal.closeLoading();
          this.formData.fileNameReceive = JSON.parse(
            this.formData.fileNameReceive
          );
          this.$refs.receive.getSrcList(this.formData.fileNameReceive);
          for (let i in this.formData.fileNameReceive) {
            this.fileNameN.push({
              name: this.formData.fileNameReceive[i],
              url: this.baseImgPath + this.formData.fileNameReceive[i],
            });
          }
        })
        .catch((err) => {
          this.$modal.closeLoading();
        });
    },
    getReceive(data) {
      this.formData.fileNameReceive = data;
      console.log(3333, data);
    },
    //获取公共数据
    getCommonList() {
      detail({
        projectCode: this.$cache.local.getJSON("tc-project-code"),
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
      if (this.$cache.local.getJSON("tc-ifcollect") == 1) {
        this.$tab.closeOpenPage({
          path: "/tc-business/project/list",
        });
      } else {
        this.$tab.closeOpenPage({
          path: '/tc-business/project/aduitCollectList'
        });
      }
    },
    handleChange(val) {
      console.log(val);
    },
    //获取操作时间
    getRealTime() {
      var date = new Date(); //当前时间
      var year = date.getFullYear(); //年
      var month = this.repair(date.getMonth() + 1); //月
      var day = this.repair(date.getDate()); //日
      var hour = this.repair(date.getHours()); //时
      var minute = this.repair(date.getMinutes()); //分
      var second = this.repair(date.getSeconds()); //秒
      //当前时间

      return hour + ":" + minute + ":" + second;
    },
    submitForm() {
      //var str = "欢迎来到这里！";

      this.$refs["elForm"].validate((valid) => {
        // TODO 提交表单
        if (valid) {
          this.formData.receiveRemark = "";
          this.formData.isCheck = 0;
          if (Array.isArray(this.formData.fileNameReceive)) {
            this.formData.fileNameReceive = JSON.stringify(
              this.formData.fileNameReceive
            );
          }
          this.$modal.loading("正在提交中，请稍后...");
          if (this.formData.receiveTime.indexOf(":") != -1) {
          } else {
            this.formData.receiveTime =
              this.formData.receiveTime + " " + this.getRealTime();
          }

          editReceive(this.formData).then((res) => {
            if (res != undefined) {
              if (res.code === 200) {
                receiveList2({
                  projectCode: this.publicList.projectCode,
                }).then((res) => {
                  let arr = res.data;
                  detail({
                    projectCode: this.$cache.local.getJSON("tc-project-code"),
                  }).then((response) => {
                    let publicList = response.data;
                    if (
                      publicList.projectDutypaidStatus == 1 &&
                      publicList.projectPayStatus == 1 &&
                      publicList.projectTicketStatus == 1 &&
                      publicList.projectAcceptanceStatus == 1 &&
                      publicList.projectContractStatus == 1 &&
                      publicList.projectCheckStatus == 1
                    ) {
                      this.projectStatusNew = 2;
                    } else if (
                      publicList.projectDutypaidStatus == 2 ||
                      publicList.projectPayStatus == 2 ||
                      publicList.projectTicketStatus == 2 ||
                      publicList.projectAcceptanceStatus == 2 ||
                      publicList.projectCheckStatus == 2 ||
                      publicList.projectContractStatus == 2
                    ) {
                      this.projectStatusNew = 1;
                    } else {
                      this.projectStatusNew = 0;
                    }
                    this.publicList.projectReceiveStatus = 0;
                    this.publicList.projectStatus = this.projectStatusNew;
                    arr.map((item) => {
                      if (item.isCheck == 2) {
                        this.publicList.projectPayStatus = 2;
                        return (this.publicList.projectStatus = 1);
                      }
                    });
                    edit(this.publicList).then((res) => {
                      this.$modal.closeLoading();
                      this.check("修改收款完成");
                      this.$modal.msgSuccess("修改收款成功");
                      
                        this.$tab.closePage({path:'/tc-business/project/collectEdit'}).then(() => {
                         // 执行结束的逻辑
                         this.$tab.refreshPage({
                          path: '/tc-business/project/aduitCollectList',
                          name: 'AduitCollectList',
                        });
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
