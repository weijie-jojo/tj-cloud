<template>
    <div>
        <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="140px">


            <el-row type="flex" class="row-bg rowCss" style="padding-top:20px" justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="项目编号">
                        <el-input v-model="formData.projectCode" :readonly="true"></el-input>
                    </el-form-item>

                    <el-form-item class="comright" label="项目名称">
                        <el-input v-model="formData.projectName" :readonly="true"></el-input>
                    </el-form-item>
                </el-col>

                <el-col :span="9">

                    <el-form-item class="comright" label="项目时间">
                        <el-input v-model="formData.projectTimeStart" :readonly="true"></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="项目金额">
                        <el-input type="number" disabled style="width:100%" v-model="formData.projectTotalAmount"
                            :step="0.01" :min="0"
                             oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                            >
                            <template slot="append">元</template>
                        </el-input>
                    </el-form-item>
                </el-col>
            </el-row>


            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="客户全名">
                        <el-input v-model="formData.placeAliasName" :readonly="true"></el-input>
                    </el-form-item>

                    <el-form-item class="comright" label="甲方">
                        <!-- <el-select  clearable v-model="formData.purchCompany">
                          <el-option v-for="item in purchCompanyOptions" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                        </el-select> -->
                        <el-input v-model="formData.purchCompany" :readonly="true"></el-input>
                    </el-form-item>
                </el-col>

                <el-col :span="9">
                    <el-form-item class="comright" label="客户状态" prop="isokradio">
                        <el-input :readonly="true" v-if="isokradio == 0" value="正常"></el-input>
                        <el-input :readonly="true" v-else value="冻结"></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="甲方纳税人识别号">
                        <el-input :readonly="true" v-model="formData.purchCompanyTaxid"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>



            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="乙方">
                        <el-input :readonly="true" v-model="formData.selfName"></el-input>
                        <!-- <el-select disabled style="width:100%" clearable v-model="formData.selfName">
                            <el-option v-for="item in ownoptions" :key="item.selfId" :label="item.selfName"
                                :value="item.selfCode">
                            </el-option>
                        </el-select> -->
                    </el-form-item>

                    <el-form-item class="comright" label="乙方状态">
                        <el-select style="width:100%" disabled clearable v-model="projectStatus" placeholder="请选择项目状态">
                            <el-option v-for="item in options" :key="item.value" :label="item.label"
                                :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>

                <el-col :span="9">

                    <el-form-item class="comright" label="乙方纳税人识别号">
                        <el-input :readonly="true" v-model="formData.taxId"></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="业务经理">
                        <el-input v-model="formData.projectLeader" :readonly="true"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>


            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="乙方行业类型">
                        <el-select class="main-select-tree" ref="selectTree" v-model="formData.industryType"
                            style="width: 100%;" disabled>
                            <el-option v-for="item in formatData(industryTypes)" :key="item.value" :label="item.label"
                                :value="item.value" style="display: none;" />
                            <el-tree class="main-select-el-tree" ref="selecteltree" :data="industryTypes" node-key="id"
                                highlight-current :props="defaultProps" @node-click="handleNodeClick"
                                :current-node-key="formData.industryType" :expand-on-click-node="expandOnClickNode">
                                <span class="custom-tree-node" slot-scope="{ node, data  }" style="width:100%">
                                    <span style="float: left">{{ node.label }}</span>
                                    <span style="float: right; color: #8492a6; font-size: 14px;padding-right:10px">{{
                                            data.taxRates
                                    }}</span>
                                </span>
                            </el-tree>

                        </el-select>
                        <!-- <treeselect  disabled   v-model="formData.industryType" :options="industryTypes" :show-count="true" /> -->
                    </el-form-item>

                    <el-form-item class="comright" label="发票类型" prop="ticketType">
                        <el-select disabled style="width:100%" clearable v-model="formData.ticketType"
                            @change="tickettaxvip">
                            <el-option v-for="item in ticketTypeoptions" :key="item.value" :label="item.label"
                                :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>

                <el-col :span="9">
                    <el-form-item class="comright" label="乙方行业税率">
                        <el-input :readonly="true" v-model="formData.industryTax"></el-input>
                    </el-form-item>
                    <el-form-item v-if="tickettaxvipok" label="发票税率">
                        <el-input v-model="formData.ticketTax" :readonly="true"></el-input>
                    </el-form-item>
                    <el-form-item v-else class="comright" label="发票税率">


                        <el-select disabled style="width:100%" clearable v-model="formData.ticketTax">
                            <el-option v-for="item in ticketNormal" :key="item.value" :label="item.label"
                                :value="item.value">
                            </el-option>
                        </el-select>

                    </el-form-item>
                </el-col>
            </el-row>


            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="开票内容类型">
                        <el-input :readonly="true" v-if="fileNameradio == 1" value="手动输入"></el-input>
                        <el-input :readonly="true" v-else value="上传附件"></el-input>
                    </el-form-item>
                </el-col>

                <el-col :span="9">
                    <el-form-item class="comright" label="开票内容" v-if="fileNameradio == 1">

                        <el-input 
                         maxlength="50"
                        show-word-limit
                        :readonly="true" type="textarea" :rows="2" v-model="formData.fileName">
                        </el-input>
                    </el-form-item>
                    
                </el-col>
            </el-row>
            <el-row type="flex" class="row-bg " justify="space-around">
               <el-col :span="9">
                 <el-form-item class="comright" label="开票内容附件" v-if="fileNameradio == 2">
                        <uploadSmall v-if="fileName.length>0" @getfileName="getfileNameS" :fileName="isNone" :fileNameOld="fileName" :isDetail="isDetail"></uploadSmall>
                    </el-form-item>
               </el-col>
               <el-col :span="9"></el-col>
            </el-row>

            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="21">
                    <el-form-item style="padding-right:4%" label="项目行业类型">
                        <el-input :readonly="true" v-model="formData.projectTrade">
                        </el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="21">
                    <el-form-item style="padding-right:4.2%" label="乙方经营范围">
                        <el-input 
                        
                        :readonly="true" type="textarea" :rows="2" placeholder="请输入乙方经营范围"
                            v-model="formData.natureBusiness">
                        </el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="21">
                    <el-form-item style="padding-right:4.2%" label="发票备注" prop="ticketRemark">
                        <el-input
                        maxlength="50"
                         show-word-limit
                        :readonly="true" type="textarea" :rows="2" placeholder="请输入发票备注"
                            v-model="formData.projectDesc">
                        </el-input>
                    </el-form-item>
                </el-col>
            </el-row>
        <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="21">
          <el-form-item class="comright" style="padding-right: 4%;margin-left: -7%;">
            <el-radio v-model="isokradioS" label="1"> 通过</el-radio>

          </el-form-item>
        </el-col>

      </el-row>   
       <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="21">
          <el-form-item class="comright" style="padding-right: 4.2%;margin-left: -7%;">
            <div style="display: flex; align-items: center;justify-content: flex-start;">
              <el-radio v-model="isokradioS" label="2">不通过 </el-radio>
              <el-input type="textarea" placeholder="请输入不通过说明" v-model="remark" :disabled="isokradioS == 1"></el-input>
            </div>


          </el-form-item>
        </el-col>

      </el-row>
            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="8"></el-col>
                <el-col :span='8' class="flexs">
                
                     <el-button type="danger" @click="resetForm">关闭</el-button>
                     <el-button v-if="isokradioS == 2" type="primary" @click="submitForm(2)">提交</el-button>
                     <el-button v-else type="primary" @click="submitForm(1)">提交</el-button>
                </el-col>
                <el-col :span="8"></el-col>
            </el-row>

        </el-form>
        
    </div>
</template>
<script>
import uploadSmall from '@/components/douploads/uploadSmall'
import crudRate from '@/api/project/rate'
import { getcode, getinfoByUserId, detail,edit,check } from "@/api/project/list";
import { getInfo } from '@/api/login'
import { Decimal } from 'decimal.js'
export default {
    name:'AuditItems',
    components: {
        uploadSmall
    },
    data() {
        return {
            isDetail:'1',
            isNone:[],
            remark:'',
            expandOnClickNode: true,
            defaultProps: {
                children: 'children',
                label: 'label'
            },
            userinfo:{},
            industryTypes: [],
            industryTypeList: [],
            username: "",
            userId: '',
            fileName2: [],
            fileName: [],
            dialogVisible1: false,
            dialogImageUrl1: "",
            fileNameradio: '1', //开票类型
            natureBusiness: '',//经营范围
            tickettaxvipok: false,
            placename: '',
            isokradio: '1',
            isokradioS: '1',
            companyTax: '',//甲方纳税人识别号
            owerTax: '',//乙方纳税人识别号
            owntype: '',//乙方行业类型
            owerTaxfee: '',//乙方税率
            projectStatus: 1,
            formData: {
              },
            baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
            options: [
                {
                    value: 0,
                    label: '异常'
                },
                {

                    value: 1,
                    label: '正常'
                },
                {

                    value: 2,
                    label: '异常',
                },
            ],
            //甲方
            purchCompanyOptions: [],
            //乙方
            ownoptions: [],
            //乙方行业类型

            ownindustry: [],
            //发票类型
            ticketTypeoptions: [
                {
                    value: 0,
                    label: '增值税普通发票'
                },
                {

                    value: 1,
                    label: '增值税专用发票'
                },
            ],
            //发票普通税率
            ticketNormal: [{
                value: 0,
                label: '免税'
            },
            {

                value: 3,
                label: '3%'
            },],
            rules: {

            },
        };
    },
    computed: {},


    mounted() {
        this.getInfo();
        this.getlist();
        this.getRate();
       // this.getinfoByUserId(); //渠道商
    },


    methods: {
        getfileNameS(){

        },
    //获取个人信息
    getInfo() {
      getInfo().then(res => {
        this.userinfo = res.user;
      })
    },
    check(resmsg) {
      let parms = {
        "checkReasult": resmsg,
        "checkUser": this.userinfo.userName,
        'phonenumber': this.userinfo.phonenumber,
        "projectCode": this.formData.projectCode,
        "projectType": "1",
      }
      check(parms).then(res => {
        console.log('项目审核完成插入日志成功！');
      }).catch(error => {

      });
    },
      submitForm(type) {

      this.$refs['elForm'].validate(valid => {
        // TODO 提交表单
        if (valid) {
          let parms;
          if (type == 1) {
           parms = {
              projectId: this.formData.projectId,
              projectCheckStatus:type,
              projectStatus:0,
            };
          } else {
            parms = {
              projectId: this.formData.projectId,
              checkContent: this.remark,
              projectCheckStatus:type,
              projectStatus:1,
            };
          }
           edit(parms).then((res) => {
            if (res != undefined) {
              if (res.code === 200) {
                 this.$nextTick(function () {
                 
                    let resmsg = '';
                    if (type == 1) {
                      resmsg = '项目审核完成';
                      this.check('项目审核完成');
                    } else {
                      this.check('项目审核完成不通过。'+'原因:'+this.remark);
                      resmsg = '项目审核完成';
                    }

                   let obj = {
                      title: '项目审核',
                      backUrl:this.$cache.local.getJSON('aduitProjectBack').backurl,
                      resmsg: resmsg,
                      backName:this.$cache.local.getJSON('aduitProjectBack').name

                    }
                    this.$cache.local.setJSON('successProject', obj);
                    this.$tab.closeOpenPage({ path: "/projectlist/success" });
                 
                });

              } else {
                this.$modal.msgError(res.msg);
                this.$tab.closeOpenPage({ path: this.$cache.local.getJSON('aduitProjectBack').backurl });
              }

            }
         });

        } else {
            this.$alert('请正确填写', '系统提示', {
                            confirmButtonText: '确定',
                          
                            type: 'warning'
                        });
        }
      })

    },
        // 四级菜单
        formatData(data) {
            let options = [];
            if (data.length > 0) {

                data.forEach((item, key) => {
                    options.push({ label: item.label, value: item.id, taxRates: item.taxRates });
                    if (item.children) {
                        item.children.forEach((items, keys) => {
                            options.push({ label: items.label, value: items.id, taxRates: items.taxRates });
                            if (items.children) {
                                items.children.forEach((itemss, keyss) => {
                                    options.push({ label: itemss.label, value: itemss.id, taxRates: itemss.taxRates });
                                    if (itemss.children) {
                                        itemss.children.forEach((itemsss, keysss) => {
                                            options.push({ label: itemsss.label, value: itemsss.id, taxRates: itemsss.taxRates });
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
            return options;
        },
       
        handleNodeClick(node) {

            this.formData.industryType = node.id;
            this.$refs.selectTree.blur();
        },
        getlist() {
            detail({
                projectCode: this.$cache.local.getJSON("projectCodeNew")
            }).then((response) => {

                this.formData = response.data[0];
                this.formData.industryTax = new Decimal(this.formData.industryTax).mul(new Decimal(100)) + '%';
                this.isokradio = JSON.stringify(this.formData.placeStatus);
                this.formData.placeStatus = parseInt(this.formData.placeStatus);
                if (this.formData.fileName) {
                    if (this.formData.fileName.indexOf("[") != -1) {
                        this.formData.fileName = JSON.parse(this.formData.fileName);
                    }

                    if (Array.isArray(this.formData.fileName)) {
                        this.fileNameradio = '2';
                        this.fileName=[];
                        //如果是图片的话
                        for (let j in this.formData.fileName) {
                            this.fileName.push({
                                url:this.baseImgPath+this.formData.fileName[j],
                                name:this.formData.fileName[j]
                            });
                        }

                    } else {
                        this.fileNameradio = '1';
                    }

                } else {
                    this.fileNameradio = '1';
                }
                if (this.formData.isActive) {
                    this.projectStatus = parseInt(this.formData.isActive);
                } else {
                    this.projectStatus = 1;
                }
            });
        },
        resetForm() {
            this.$tab.closeOpenPage({ path:this.$cache.local.getJSON('aduitProjectBack').backurl});
        },

        
        //渠道商接口
        getinfoByUserId() {
            getInfo().then(res => {
                //this.userId = res.user.userId;
               // this.username = res.user.userName;
                getinfoByUserId({ userId:this.formData.userId }).then(res => {
                    this.placename = res.data;
                })
            })
        },

        getRate() {
            crudRate.getAllRate().then(res => {
                console.log("getAllRate", res.rows);
                let tree = []; // 用来保存树状的数据形式
                this.parseTree(res.rows, tree, 0);
                console.log("tree", tree);
                this.industryTypes = tree;
                this.industryTypeList = res.rows;
            })
        },
        //把数据整成树状
        parseTree(industry, tree, pid) {
            for (var i = 0; i < industry.length; i++) {
                if (industry[i].parentId == pid) {
                    let a = industry[i].taxRate;
                    let b = null;
                    if (a) {
                        b = new Decimal(a).mul(new Decimal(100));
                        b = "税率" + b + '%';
                    } else {
                        b = null;
                    }
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


        tickettaxvip(e) {
            console.log(e);
            if (e > 0) {
                this.tickettaxvipok = true;
                this.formData.ticketTax = 3;
            } else {
                this.tickettaxvipok = false;

            }

        },
        getcode() {
            getcode().then((res) => {
                this.formData.projectCode = res;
            }).catch((errore) => {
                console.log(errore);
            })
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

// 改变input框字体颜色
::v-deep textarea {
    background-color: transparent !important;
    color: black !important;
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
