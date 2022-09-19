<template>
  <div class="paddingbg-s">
      
    <el-form ref="formtax" :model="formtax" :rules="rules" label-width="auto">
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="个体户名称" prop="selfName">
            <el-input v-model="formtax.selfName" disabled></el-input>
          </el-form-item>
          <el-form-item label="行业类型" prop="industryType">
              <el-tooltip class="item" effect="dark" :content="selectTipType" placement="top-start">
              <el-select class="main-select-tree" ref="selectTree" v-model="formtax.industryType" style="width: 88%;">
                <el-option v-for="item in formatData(industryTypes)" :key="item.value" :label="item.label"
                  :value="item.value" style="display: none;" />
                <el-tree class="main-select-el-tree" ref="selecteltree" :data="industryTypes" node-key="id"
                  highlight-current :props="defaultProps" @node-click="handleNodeClick"
                  :current-node-key="formtax.industryType" :expand-on-click-node="expandOnClickNode">
                  <span class="custom-tree-node" slot-scope="{ node, data  }" style="width:100%">
                    <span style="float: left">{{ node.label }}</span>
                    <span style="float: right; color: #8492a6; font-size: 14px;padding-right:10px">{{ data.taxRates
                    }}</span>
                  </span>
                </el-tree>
              </el-select>
              </el-tooltip>
            </el-form-item>
          
          <el-form-item class="comright" label="纳税人识别号" prop="taxId">
            <el-input v-model="formtax.taxId" disabled></el-input>
          </el-form-item>
           
        
        </el-col>
        <el-col :span="9">
         <el-form-item class="comright" label="法人姓名" prop="legalPersonName">
            <el-input v-model="formtax.legalPersonName" disabled></el-input>
          </el-form-item>
          <el-form-item label="行业税率" prop="industryTax">
              <el-input style="width:88%" v-model="industryTax" disabled>

              </el-input>

            </el-form-item>
       
            <el-form-item label="核定通知书" prop="fileName2">
              <uploadSmall @getfileName="getfileNameS" :fileName="isNone" :fileNameOld="isNone" :isDetail="isDetail"></uploadSmall>
          </el-form-item>
        </el-col>
      </el-row>
     
      <el-row type="flex" class="row-bg " justify="space-around">
        <el-col :span="8"></el-col>
        <el-col :span='8' class="flexs">
          <el-button type="danger" @click="resetForm">关闭</el-button>
          <el-button type="primary" @click="onSubmit">提交</el-button>
        </el-col>
        <el-col :span="8"></el-col>
      </el-row>
    </el-form>
   
  </div>
</template>

<script>
import crudRate from '@/api/tg-api/company/rate'
import { Decimal } from 'decimal.js'
import uploadSmall from '@/components/douploads/uploadSmall'
import { getInfo } from '@/api/login'
import { addEmployed, updateEmployed, check } from "@/api/tg-api/company/employed";
export default {
  name:'AddTax',
   components: {
    uploadSmall
  },
  data() {
    return {
      selectTipType:'',
      industryTax: '',//行业税率展示用
      expandOnClickNode: true,
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      industryTypes:[],
      industryTypeList:[],
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
      isDetail:'0',
      isNone:[],
      userinfo: {},
      formtax: {
        taxStatus: 1,
        selfId: "",
        legalPersonName: "",
        selfName: "",
        taxId: "",
        fileName2: [],
        industryTax:'',
        industryType:'',
      },

      fileName2: [],
      dialogVisible: false,
      previewPath: "",
      dialogImageUrl: "",
      rules: {
        industryTax: [{
          required: true,
          message: '请选择行业类型',
          trigger: 'change'
        }],
         industryType: [{
          required: true,
          message: '请选择行业类型',
          trigger: 'change'
        }],
        selfName: [
          { required: true, message: "请输入个体户名称", trigger: "blur" },
         ],
        legalPersonName: [
          { required: true, message: "请输入法人姓名", trigger: "blur" },
        ],
        taxId: [
          { required: true, message: "请输入纳税人识别号", trigger: "blur" },
        ],
        fileName2: [
          { required: true, message: "请上传文件", trigger: "change" },
        ],
      },
    };
  },
  watch: {
    'formtax.industryType': 'selectIndustryType',
  },
 
 
  mounted() {
    let list = this.$cache.local.getJSON("employednewlist");
    this.formtax.selfId = list.selfId;
    this.formtax.selfName = list.selfName;
    this.formtax.legalPersonName = list.legalPersonName;
    this.formtax.taxId = list.taxId;
    this.getRate();
    this.getInfo();
  },

  methods: {
      selectIndustryType() {
      var rate = this.industryTypeList.find((item) => item.industryId == this.formtax.industryType);
      if (rate.taxRate) {
        this.formtax.industryTax = rate.taxRate;
        this.industryTax = new Decimal(rate.taxRate).mul(new Decimal(100)) + '%';
      } else {
        this.formtax.industryTax = '';
        this.industryTax = '';
      }

    },
     handleNodeClick(node) {
      this.formtax.industryType = node.id;
      this.$refs.selectTree.blur();
      
      this.$nextTick(function(){
        this.selectTipType=this.$refs.selectTree.selected.label; 
      });

      
     
    },
     formatData(data) {
      let options = [];
      data.forEach((item, key) => {
        options.push({ label: item.label, value: item.id,taxRates:item.taxRates });
        if (item.children) {
          item.children.forEach((items, keys) => {
            options.push({ label: item.label+'-'+items.label, value: items.id,taxRates:items.taxRates });
            if (items.children) {
              items.children.forEach((itemss, keyss) => {
                options.push({ label: item.label+'-'+items.label+'-'+itemss.label, value: itemss.id,taxRates:itemss.taxRates });
                if (itemss.children) {
                  itemss.children.forEach((itemsss, keysss) => {
                    options.push({ label:item.label+'-'+items.label+'-'+itemss.label+'-'+itemsss.label, value: itemsss.id,taxRates:itemsss.taxRates });
                  });
                }
              });
            }
          });
        }
      });
      return options;
    },
      getRate() {
      crudRate.getAllRate().then(res => {
        let tree = []; // 用来保存树状的数据形式
        this.parseTree(res.rows, tree, 0);

        // let arr = [{
        //   id: "-1",
        //   label: '请选择行业类型',
        //   children: tree

        // }];
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
            taxRates: b,
          };
          tree.push(obj);
          this.parseTree(industry, obj.children, obj.id);
        }
      }
    },
    getfileNameS(data){
      this.formtax.fileName2=data;
      console.log(this.formtax.fileName2);
    },
    //返回
    resetForm() {
      this.$tab.closeOpenPage({ path: this.$cache.local.getJSON('tj-resultBack').backUrl}).then(() => {
        this.$tab.refreshPage({ path: this.$cache.local.getJSON('tj-resultBack').backUrl,name:this.$cache.local.getJSON('tj-resultBack').backName});
     })
    },
    //获取个人信息
    getInfo() {
      getInfo().then(res => {
        this.userinfo = res.user;
      })
    },
    //新增工商办理进度
    check(resmsg) {
      let parms = {
        "checkReasult": resmsg,
        "checkUser": this.userinfo.userName,
        'phonenumber': this.userinfo.phonenumber,
        "selfCode": this.$cache.local.getJSON("employednewlist").selfCode,
        "selfType": "6",
      }
      check(parms).then(res => {
        console.log('税务办理插入日志成功！');
      }).catch(error => {

      });
    },
    //提交
    onSubmit() {
     this.$refs["formtax"].validate((valid) => {
        if (valid) {
          if(Array.isArray(this.formtax.fileName2)){
            this.formtax.fileName2 = JSON.stringify(this.formtax.fileName2);
          }
          
          updateEmployed(this.formtax)
            .then((res) => {
              if (res != undefined) {
                if (res.code === 200) {
                  this.$nextTick(function () {
                    let resmsg = '税务办理完成';
                    this.check(resmsg);
                    this.$tab.closeOpenPage({ path: "/tg-business/reg/result" });
                   
                  });
                } else {
                  this.$modal.msgError(res.msg);
                }
              }
            })
            .catch((error) => {
              this.$modal.msgError(error);
            });
        } else {
            this.$alert('请正确填写', '系统提示', {
              confirmButtonText: '确定',
            
              type: 'warning'
           });
        }
      });
    },
   
  },
};
</script>

<style>
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

.flexs {
  display: flex;
  justify-content: center;

}
</style>