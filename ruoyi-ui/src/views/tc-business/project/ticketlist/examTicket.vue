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
        <el-row
          type="flex"
          class="row-bg rowCss"
          justify="space-around"
        >
          <el-col :span="9">
            <el-form-item class="comright" label="项目编号">
              <el-input v-model="Father.projectCode" :readonly="true"></el-input>
            </el-form-item>
  
            <el-form-item class="comright" label="项目名称">
              <el-input :readonly="true" v-model="Father.projectName"></el-input>
            </el-form-item>
          </el-col>
  
          <el-col :span="9">
            <el-form-item class="comright" label="项目时间">
              <el-date-picker
                disabled
                style="width: 100%"
                v-model="Father.projectTimeStart"
                value-format="yyyy-MM-dd"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                align="right"
              >
              </el-date-picker>
            </el-form-item>
            <el-form-item class="comright" label="项目金额">
              <el-input
                :readonly="true"
                type="number"
                style="width: 100%"
                v-model="Father.projectTotalAmount"
                :step="0.01"
                :min="0"
                oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
              >
                <template slot="append">元</template>
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
  
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
              <el-form-item class="comright" label="已开金额">
              <el-input
                :required="true"
                :readonly="true"
                type="number"
                style="width: 100%"
                v-model="Father.projectPackageAmount"
                :step="0.01"
                :min="0"
              >
                <template slot="append">元</template>
              </el-input>
            </el-form-item>
            
          </el-col>
  
          <el-col :span="9">
              <el-form-item class="comright" label="剩余金额">
              <el-input
                :readonly="true"
                type="number"
                style="width: 100%"
                v-model="Father.projectRemainAmount"
                :step="0.01"
                :min="0"
              >
                <template slot="append">元</template>
              </el-input>
            </el-form-item>
           
          </el-col>
        </el-row>
        <el-row
          type="flex"
          class="row-bg"
          style="margin-bottom: 20px"
          justify="space-around"
        >
          <el-col :span="9" class="flexs">
            <div class="bankno" style="width: 35%">购货单位（甲方）</div>
            <div style="width: 50%; hegiht: 10px"></div>
          </el-col>
          <el-col :span="9">
            <div></div>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item class="comright" label="名称" :required="true">
              <el-input disabled v-model="Father.purchCompany"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item class="comright" label="纳税人识别号" :required="true">
              <el-input disabled v-model="Father.purchCompanyTaxid"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
  
        <el-row
          type="flex"
          class="row-bg"
          style="margin-bottom: 20px"
          justify="space-around"
        >
          <el-col :span="9" class="flexs">
            <div class="bankno" style="width: 35%">销货单位（乙方）</div>
            <div style="width: 50%; hegiht: 10px"></div>
          </el-col>
          <el-col :span="9">
            <div></div>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item class="comright" label="名称" :required="true">
              <el-input v-model="Father.selfName" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item class="comright" label="纳税人识别号" :required="true">
              <el-input disabled v-model="Father.projectOwnerTaxid"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
  
        <el-row
          type="flex"
          class="row-bg"
          style="margin-bottom: 20px"
          justify="space-around"
        >
          <el-col :span="9" class="flexs">
            <div class="bankno" style="width: 35%">票据信息</div>
            <div style="width: 50%; hegiht: 10px"></div>
          </el-col>
          <el-col :span="9">
            <div></div>
          </el-col>
        </el-row>
  
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            
           
            <el-form-item class="comright" label="发票类型" prop="ticketType">
              <el-select
                disabled
                style="width: 100%"
                clearable
                v-model="formData.ticketType"
                @change="tickettaxvip"
              >
                <el-option
                  v-for="item in ticketTypeoptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item class="comright" label="开票内容类型">
              <el-radio
                disabled
                v-model="fileNameradio"
                label="1"
                @change="filenamer"
                >手动输入</el-radio
              >
              <el-radio
                disabled
                v-model="fileNameradio"
                label="2"
                @change="filenamer"
                >上传附件
              </el-radio>
            </el-form-item>
            <el-form-item
              class="comright"
              label="发票种类编号"
              prop="ticketTypeCode"
            >
              <el-input
                :readonly="true"
                v-model="formData.ticketTypeCode"
              ></el-input>
            </el-form-item>
            <el-form-item class="comright" label="发票时间">
              <el-input :readonly="true" v-model="formData.ticketTime"></el-input>
            </el-form-item>
          </el-col>
  
          <el-col :span="9">
           
          
            <el-form-item v-if="tickettaxvipok" label="发票税率" prop="ticketTax">
              <el-input
                style="width: 86%"
                v-model="formData.ticketTax"
                :readonly="true"
              ></el-input>
            </el-form-item>
            <el-form-item
              v-else
              class="comright"
              label="发票税率"
              prop="ticketTax"
            >
              <el-select
                disabled
                style="width: 100%"
                clearable
                v-model="formData.ticketTax"
              >
                <el-option
                  v-for="item in ticketNormal"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
            </el-form-item>
  
            <el-form-item
              class="comright"
              label="开票内容"
              v-if="fileNameradio == 1"
            >
              <el-input
                maxlength="50"
                show-word-limit
                type="textarea"
                disabled
                :rows="2"
                v-model="Father.fileName"
              >
              </el-input>
            </el-form-item>
            <el-form-item
              class="comright"
              label="开票内容附件"
              v-if="fileNameradio == 2"
            >
              <uploadSmall
                v-if="fileName.length > 0"
                @getfileName="getfileNameS"
                :fileName="isNone"
                :fileNameOld="fileName"
                :isDetail="isDetail"
              ></uploadSmall>
            </el-form-item>
  
            <el-form-item class="comright" label="发票编号" prop="ticketCode">
              <el-input v-model="formData.ticketCode" :readonly="true"></el-input>
            </el-form-item>
            <el-form-item class="comright" label="发票金额" prop="ticketAmount">
              <el-input
                :readonly="true"
                @change="ticketAsee"
                type="number"
                style="width: 100%"
                v-model="formData.ticketAmount"
                :step="0.01"
                :min="0"
              >
                <template slot="append">元</template>
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="21">
            <el-form-item style="padding-right: 4%" label="发票备注">
              <el-input
                maxlength="50"
                show-word-limit
                :readonly="true"
                type="textarea"
                :rows="2"
                placeholder="请输入发票备注"
                v-model="formData.ticketRemark"
              >
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="9">
            <el-form-item class="comright" label="发票影像" prop="fileName">
              <uploadSmall
                v-if="fileNames.length > 0"
                @getfileName="getfileNameS"
                :fileName="isNone"
                :fileNameOld="fileNames"
                :isDetail="isDetail"
              ></uploadSmall>
            </el-form-item>
          </el-col>
          <el-col :span="9"> </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="21">
            <el-form-item
              class="comright"
              style="padding-right: 4.2%; margin-left: -7%"
            >
              <el-radio v-model="isokradioS" label="1"> 通过</el-radio>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="21">
            <el-form-item
              class="comright"
              style="padding-right: 4.2%; margin-left: -7%"
            >
              <div
                style="
                  display: flex;
                  align-items: center;
                  justify-content: flex-start;
                "
              >
                <el-radio v-model="isokradioS" label="2">不通过 </el-radio>
                <el-input
                  type="textarea"
                  placeholder="请输入不通过说明"
                  v-model="remark"
                  :disabled="isokradioS == 1"
                ></el-input>
              </div>
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
            <el-button
              v-if="isokradioS == 2"
              type="primary"
              @click="submitForm(2)"
              >驳回</el-button
            >
            <el-button v-else type="primary" @click="submitForm(1)"
              >通过</el-button
            >
          </el-col>
          <el-col :span="8"></el-col>
        </el-row>
      </el-form>
    </div>
  </template>
  <script>
  import uploadSmall from "@/components/douploads/uploadSmall";
  import crudRate from "@/api/project/rate";
  import { list2, edit, getTicketDetail } from "@/api/tc-api/project/ticket";
  import arrss from "@/api/tc-api/project/list";
  import {
    detail,
    getcode,
    getinfoByUserId,
    ownlist,
    check,
  } from "@/api/tc-api/project/list";
  import { getInfo } from "@/api/login";
  import { Decimal } from "decimal.js";
  export default {
    name: "ExamTicket",
    components: {
      uploadSmall,
    },
    data() {
      return {
        projectStatusNew: "0",
        isokradioS: "1",
        fileNames: [],
        isDetail: "1",
        isNone: [],
        remark: "",
  
        issuedAmount: 0.0, //已开金额
        balance: 0.0, //剩余金额
        projectStatus: 1, //销货单位（乙方）状态
        username: "",
        userId: "",
        industryId: "",
        industryTypes: [],
        industryTypeList: [],
        fileName: [],
        fileNamefile: [], //附件
        dialogVisible1: false,
        dialogImageUrl1: "",
        fileNameradio: "1", //开票类型
        natureBusiness: "", //经营范围
        tickettaxvipok: false,
        placename: "",
        isokradio: "2",
        companyTax: "", //购货单位（甲方）纳税人识别号
        owerTax: "", //销货单位（乙方）纳税人识别号
        owntype: "", //销货单位（乙方）行业类型
        owerTaxfee: "", //销货单位（乙方）税率
        userinfo: {},
        Father: [],
        formData: {
        },
        baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
        options: [
          {
            value: 0,
            label: "异常",
          },
          {
            value: 1,
            label: "正常",
          },
          {
            value: 2,
            label: "异常",
          },
        ],
        //购货单位（甲方）
        purchCompanyOptions: [],
        //销货单位（乙方）
        ownoptions: [],
        //销货单位（乙方）行业类型
  
        ownindustry: [],
        //发票类型
        ticketTypeoptions: [
          {
            value: 0,
            label: "增值税普通发票",
          },
          {
            value: 1,
            label: "增值税专用发票",
          },
        ],
        //发票普通税率
        ticketNormal: [
          {
            value: 0,
            label: "免税",
          },
          {
            value: 3,
            label: "3%",
          },
        ],
        rules: {
          projectName: [
            {
              required: true,
              message: "请输入项目名称",
              trigger: "blur",
            },
          ],
          projectTotalAmount: [
            {
              required: true,
              message: "请输入项目额",
              trigger: "blur",
            },
          ],
          ticketAmount: [
            {
              required: true,
              message: "请输入发票金额",
              trigger: "blur",
            },
          ],
  
          ticketType: [
            {
              required: true,
              message: "请选择发票类型",
              trigger: "change",
            },
          ],
  
          ticketTax: [
            {
              required: true,
              message: "发票税率不能为空",
              trigger: "change",
            },
          ],
          industryType: [
            {
              required: true,
              message: "请选择销货单位（乙方）行业类型",
              trigger: "change",
            },
          ],
          placeCode: [
            {
              required: true,
              message: "请选择渠道商",
              trigger: "change",
            },
          ],
  
          projectOwner: [
            {
              required: true,
              message: "请选择销货单位（乙方）",
              trigger: "change",
            },
          ],
  
          purchCompany: [
            {
              required: true,
              message: "请选择购货单位（甲方）",
              trigger: "change",
            },
          ],
  
          purchCompanyTaxid: [
            {
              required: true,
              message: "请输入购货单位（甲方）纳税人识别号",
              trigger: "blur",
            },
          ],
  
          fileName: [
            {
              required: true,
              message: "发票影像不能为空",
            },
          ],
          projectDesc: [
            {
              required: true,
              message: "请输入发票描述",
              trigger: "blur",
            },
          ],
          ticketCode: [
            {
              required: true,
              message: "请输入发票编号",
              trigger: "blur",
            },
          ],
          ticketTypeCode: [
            {
              required: true,
              message: "请输入发票种类编号",
              trigger: "blur",
            },
          ],
        },
      };
    },
    watch: {
      "Father.industryType": "selectIndustryType",
    },
    mounted() {
      this.getTicket();
    },
  
    methods: {
      getTicket() {
        this.$modal.loading("正在加载数据，请稍后...");
        getTicketDetail(this.$cache.local.getJSON("tc-ticketid"))
          .then((res) => {
            this.$modal.closeLoading();
            this.formData = res.data;
            this.getlist();
            this.getinfoByUserId();
            //this.ticketByCode();
            this.getRate();
            this.formData.fileName = JSON.parse(this.formData.fileName);
            this.fileNames = [];
            for (let j in this.formData.fileName) {
              this.fileNames.push({
                url: this.baseImgPath + this.formData.fileName[j],
                name: this.formData.fileName[j],
              });
            }
          })
          .catch((err) => {
            this.$modal.closeLoading();
          });
      },
      getfileNameS() {},
      check(resmsg) {
        let parms = {
          checkReasult: resmsg,
          checkUser: this.userinfo.userName,
          phonenumber: this.userinfo.phonenumber,
          projectCode: this.Father.projectCode,
          projectType: "2",
        };
        check(parms)
          .then((res) => {
            console.log("票据审核完成插入日志成功！");
          })
          .catch((error) => {});
      },
  
      submitForm(type) {
        this.$refs["elForm"].validate((valid) => {
          // TODO 提交表单
          if (valid) {
            let parms;
            this.ticketByCode();
  
            if (
              this.Father.projectReceiveStatus == 1 &&
              this.Father.projectPayStatus == 1 &&
              this.Father.projectDutypaidStatus == 1 &&
              this.Father.projectAcceptanceStatus == 1 &&
              this.Father.projectContractStatus == 1 &&
              this.Father.projectCheckStatus == 1
            ) {
              this.projectStatusNew = 2;
            } else if (
              this.Father.projectReceiveStatus == 2 ||
              this.Father.projectPayStatus == 2 ||
              this.Father.projectDutypaidStatus == 2 ||
              this.Father.projectAcceptanceStatus == 2 ||
              this.Father.projectCheckStatus == 2 ||
              this.Father.projectContractStatus == 2
            ) {
              this.projectStatusNew = 1;
            } else {
              this.projectStatusNew = 0;
            }
  
            if (type == 1) {
              this.formData.isDeleted = 1;
  
              if (
                new Decimal(this.Father.projectRemainAmount).sub(
                  new Decimal(this.formData.ticketAmount)
                ) == 0
              ) {
                parms = {
                  projectId: this.Father.projectId,
                  projectTicketStatus: 1,
                  projectStatus: this.projectStatusNew,
                  isSelfCount: this.Father.isSelfCount,
                  projectCode: this.Father.projectCode,
                  projectOwner: this.Father.projectOwner,
                };
              } else {
                parms = {
                  projectId: this.Father.projectId,
                  projectTicketStatus: 0,
                  projectStatus: this.projectStatusNew,
                  isSelfCount: this.Father.isSelfCount,
                  projectCode: this.Father.projectCode,
                  projectOwner: this.Father.projectOwner,
                };
              }
            } else {
              this.formData.isDeleted = 3;
              parms = {
                projectId: this.Father.projectId,
                ticketRemark: this.remark,
                projectTicketStatus: type,
                projectStatus: 1,
                isSelfCount: this.Father.isSelfCount,
                projectCode: this.Father.projectCode,
                projectOwner: this.Father.projectOwner,
              };
            }
            arrss.edit(parms);
  
            edit(this.formData).then((res) => {
              if (res != undefined) {
                if (res.code === 200) {
                  this.$nextTick(function () {
                    let resmsg = "";
                    if (type == 1) {
                      resmsg = "票据审核完成";
                      this.check("票据审核完成");
                    } else {
                      this.check("票据审核不通过。" + "原因：" + this.remark);
                      resmsg = "票据审核完成";
                    }
  
                    let obj = {
                      title: "票据审核",
                      backUrl: this.$cache.local.getJSON("tc-aduitback").backurl,
                      resmsg: resmsg,
                      backName: this.$cache.local.getJSON("tc-aduitback").name,
                    };
                    this.$cache.local.setJSON("tc-successProject", obj);
                    this.$tab.closeOpenPage({
                      path: "/tc-business/project/success",
                    });
                  });
                } else {
                  this.$modal.msgError(res.msg);
                  this.$tab.closeOpenPage({
                    path: this.$cache.local.getJSON("tc-aduitback").backurl,
                  });
                }
              }
            });
          } else {
            this.$message({
              message: "请填写完整",
              type: "warning",
            });
          }
        });
      },
      //监听行业类型
      selectIndustryType() {
        var rate = this.industryTypeList.find(
          (item) => item.industryId == this.Father.industryType
        );
        if (rate) {
          this.industryId = rate.industryId; //行业类型id
          this.owerTaxfee = rate.taxRate;
          let industryType = rate.industryId;
          ownlist({ username: this.username, industryType: industryType })
            .then((res) => {
              this.ownoptions = res;
  
              for (let i in this.ownoptions) {
                if (this.ownoptions[i].selfName == this.Father.selfName) {
                  this.natureBusiness = this.ownoptions[i].natureBusiness;
                  this.owerTax = this.ownoptions[i].taxId;
                }
              }
            })
            .catch((err) => {
              console.log(err);
            });
        } else {
          this.getRate();
        }
      },
  
      ticketAsee(e) {
        console.log(e);
        console.log(this.balance);
        if (e > this.balance) {
          this.$modal.msgError("发票金额不能大于剩余金额");
          this.formData.ticketAmount = "";
        }
      },
      //计算已开和剩余金额
      ticketByCode() {},
      getlist() {
        detail({
          projectCode: this.$cache.local.getJSON("tc-project-code"),
        }).then((response) => {
          this.Father = response.data;
          if (this.Father.fileName) {
            if (this.Father.fileName.indexOf("[") != -1) {
              this.Father.fileName = JSON.parse(this.Father.fileName);
            }
            // this.Father.fileName = JSON.parse(this.Father.fileName);
            if (Array.isArray(this.Father.fileName)) {
              this.fileNameradio = "2";
              this.fileName = [];
              //如果是图片的话
              for (let j in this.Father.fileName) {
                this.fileName.push({
                  url: this.baseImgPath + this.Father.fileName[j],
                  name: this.Father.fileName[j],
                });
              }
            } else {
              this.fileNameradio = "1";
            }
          } else {
            this.fileNameradio = "1";
          }
          if (this.formData.isActive) {
            this.projectStatus = parseInt(this.formData.isActive);
          } else {
            this.projectStatus = 1;
          }
        });
      },
      //监听开票内容选择
      filenamer(e) {
        if (e == 1) {
          this.formData.fileName = "";
        }
      },
      //监听销货单位（乙方）
      ownnew(e) {
        console.log(e);
        for (let i in this.ownoptions) {
          if (this.ownoptions[i].selfCode == e) {
            console.log(this.ownoptions[i].isActive);
            if (this.ownoptions[i].isActive) {
              this.projectStatus = parseInt(this.ownoptions[i].isActive);
            } else {
              this.projectStatus = 1;
            }
            this.formData.selfName = this.ownoptions[i].selfName;
            this.natureBusiness = this.ownoptions[i].natureBusiness;
            this.owerTax = this.ownoptions[i].taxId;
          }
        }
      },
  
      getRate() {
        crudRate.getAllRate().then((res) => {
          let tree = []; // 用来保存树状的数据形式
          this.parseTree(res.rows, tree, 0);
          this.industryTypes = tree;
          this.industryTypeList = res.rows;
          var rate = this.industryTypeList.find(
            (item) => item.industryId == this.Father.industryType
          );
          //   this.industryId = rate.industryId;  //行业类型id
          //   this.owerTaxfee = rate.taxRate;
          let industryType = rate.industryId;
  
          ownlist({ username: this.username, industryType: industryType })
            .then((res) => {
              this.ownoptions = res;
              for (let i in this.ownoptions) {
                if (this.ownoptions[i].selfName == this.Father.selfName) {
                  this.natureBusiness = this.ownoptions[i].natureBusiness;
                  this.owerTax = this.ownoptions[i].taxId;
                }
              }
            })
            .catch((err) => {
              console.log(err);
            });
        });
      },
      //把数据整成树状
      parseTree(industry, tree, pid) {
        for (var i = 0; i < industry.length; i++) {
          if (industry[i].parentId == pid) {
            var obj = {
              id: industry[i].industryId,
              label: industry[i].industryName,
              children: [],
            };
            tree.push(obj);
            this.parseTree(industry, obj.children, obj.id);
          }
        }
      },
  
      //监听渠道商状态
      placeNew(e) {
        console.log(e);
        for (let i in this.placeCodeOptions) {
          if (this.placeCodeOptions[i].placeCode == e) {
            this.isokradio = JSON.stringify(this.placeCodeOptions[i].placeStatus);
          }
        }
      },
      //返回
      resetForm() {
        this.$tab.closeOpenPage({ path: "/tc-business/project/ticketList" });
      },
  
      //渠道商接口  记得修改 userid
      getinfoByUserId() {
        getInfo().then((res) => {
          this.userinfo = res.user;
          this.userId = res.user.userId;
          this.username = res.user.userName;
          //this.formData.projectLeader = res.user.nickName;
          getinfoByUserId({ userId: this.formData.userId }).then((res) => {
            this.placeCodeOptions = res.data;
          });
        });
      },
  
      //监听开票内容类型
      tickettaxvip(e) {
        // this.formData.ticketType=e;
        if (e > 0) {
          this.tickettaxvipok = true;
          // this.formData.ticketTax = 3;
        } else {
          this.tickettaxvipok = false;
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
        var date = new Date(); //当前时间
        var year = date.getFullYear(); //年
        var month = this.repair(date.getMonth() + 1); //月
        var day = this.repair(date.getDate()); //日
  
        var hour = this.repair(date.getHours()); //时
        var minute = this.repair(date.getMinutes()); //分
        var second = this.repair(date.getSeconds()); //秒
  
        //当前时间
        var curTime =
          year +
          "-" +
          month +
          "-" +
          day +
          " " +
          hour +
          ":" +
          minute +
          ":" +
          second;
  
        this.formData.ticketTime = curTime;
      },
      getcode(selfCode) {
        getcode({ selfCode: selfCode })
          .then((res) => {
            this.formData.projectCode = res;
          })
          .catch((errore) => {
            console.log(errore);
          });
      },
      handleChange(val) {
        console.log(val);
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
  