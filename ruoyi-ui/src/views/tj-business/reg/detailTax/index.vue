<template>
  <div class="paddingbg-s">
    <el-form ref="formtax" :model="formtax" :rules="rules" label-width="auto">
     
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="个体户名称" prop="selfName">
            <el-input
              v-model="formtax.selfName"
              :readonly="true"
            ></el-input>
          </el-form-item>
              <el-form-item class="comright" label="行业类型" prop="industryType">
             <el-tooltip class="item" effect="dark" :content="selectTipType" placement="top-start">
            <el-select 
             disabled
             :popper-append-to-body="false" class="main-select-tree" ref="selectTree" v-model="formtax.industryType" style="width: 100%;">
               <el-option v-for="item in formatData(industryTypes)" :title="item.label" :key="item.value" :label="item.label"
                  :value="item.value" style="display: none;" />
                <el-tree class="main-select-el-tree" ref="selecteltree" :data="industryTypes" node-key="id"
                  highlight-current :props="defaultProps" @node-click="handleNodeClick"
                  :current-node-key="formtax.industryType" :expand-on-click-node="expandOnClickNode"
                   >
                    <span class="custom-tree-node" slot-scope="{ node, data  }" style="width:100%">
                         <span style="float: left">{{ node.label }}</span>
                         <span style="float: right; color: #8492a6; font-size: 14px;padding-right:10px">{{ data.taxRates }}</span>
                    </span>
                  </el-tree>
              </el-select>
            </el-tooltip>
          </el-form-item>
            <el-form-item class="comright" label="纳税人识别号" prop="taxId">
            <el-input
              v-model="formtax.taxId"
              :readonly="true"
            ></el-input>
          </el-form-item>
     
         
        </el-col>
        <el-col :span="9">
             <el-form-item class="comright" label="法人姓名" prop="legalPersonName">
            <el-input
              v-model="formtax.legalPersonName"
              :readonly="true"
            ></el-input>
          </el-form-item>
            <el-form-item class="comright" label="行业税率">
            <el-input v-model="industryTax" :readonly="true">
            </el-input>
          </el-form-item>
           <el-form-item label="核定通知书" prop="fileName2">
             <uploadSmall v-if="fileNameN1.length>0" @getfileName="getfileNameS" :fileName="isNone" :fileNameOld="fileNameN1" :isDetail="isDetail"></uploadSmall>
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
import crudRate from '@/api/company/rate'
import { Decimal } from 'decimal.js'
import uploadSmall from '@/components/douploads/uploadSmall'
export default {
  name:'DetailTax',
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
      isDetail:'1',
      isNone:[],
      fileNameN1:[],
     formtax: {
      industryType:'',
       industryTax:'',
        taxStatus: 1,
        selfId: "",
        legalPersonName: "",
        selfName: "",
        taxId: "",
        fileName2: [],
      },
        baseImgPath:"/eladmin/api/files/showTxt?imgPath=",

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
  mounted() {
    let list = this.$cache.local.getJSON("tj-taxlist");
    this.formtax = list;
    this.industryTax = new Decimal(this.formtax.industryTax).mul(new Decimal(100)) + '%';
    this.getRate();
    this.fileNameN1=[];
    this.fileName2=JSON.parse(this.$cache.local.getJSON('tj-taxlist').fileName2);
    for(let k1 in this.fileName2){
       this.fileNameN1.push({
          url:this.baseImgPath+this.fileName2[k1],
          name:this.fileName2[k1],
        });
     } 
  },
  methods: {
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
    selectIndustryType(value) {
      var rate = this.industryTypes.find((item) => item.industryId == value);
      this.formtax.industryTax = rate.taxRate;
      console.log("rate==", rate);
    },
     getRate() {
      crudRate.getAllRate().then(res => {
        var employedInfo = this.$cache.local.getJSON('tj-taxlist');
        this.formtax.industryType = employedInfo.industryType;
        let tree = []; // 用来保存树状的数据形式
        this.parseTree(res.rows, tree, 0);
        this.industryTypes = tree;
        this.industryTypeList = res.rows;
        this.$nextTick(function(){
             this.selectTipType=this.$refs.selectTree.selected.label; 
         });
        this.selectIndustryType();
      })
    },
    //把数据整成树状
    parseTree(industry, tree, pid) {
      for (var i = 0; i < industry.length; i++) {
        if (industry[i].parentId == pid) {
           let a=industry[i].taxRate;
           let b=null;
           if(a){
              b=new Decimal(a).mul(new Decimal(100));
              b="税率"+b+'%';
            }else{
              b=null;
           }
          var obj = {
            id: industry[i].industryId,
            label: industry[i].industryName,
            children: [],
            taxRates:b,
          };
          tree.push(obj);
          this.parseTree(industry, obj.children, obj.id);
        }
      }
    },
    getfileNameS(){

    },
    resetForm() {
       this.$tab.closeOpenPage( { path: this.$cache.local.getJSON('backurls').backUrl });
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
.comright{
  padding-right: 10%;
}
.flexs{
  display: flex;
  justify-content: center;

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