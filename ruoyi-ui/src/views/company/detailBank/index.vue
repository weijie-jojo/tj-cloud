<template>
  <div class="paddingbg-s">
    <el-form
      ref="formBank"
      :model="formBank"
      :rules="rules"
      label-width="auto"
    >
      <el-row  type="flex" class="row-bg " justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="个体户名称" prop="selfName">
            <el-input  v-model="formBank.selfName" :readonly="true"></el-input>
          </el-form-item>

          <el-form-item class="comright" label="法人姓名" prop="legalPersonName">
            <el-input  v-model="formBank.legalPersonName" :readonly="true"></el-input>
          </el-form-item>
        </el-col>
       
        <el-col :span="9">
          <el-form-item class="comright" label="纳税人识别号" prop="taxId">
            <el-input  v-model="formBank.taxId" :readonly="true"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
       <el-row type="flex" class="row-bg rowCss combottom"  justify="space-around">
        <el-col :span="7">
           <div class="bankno">银行账号</div>
        
        </el-col>
        <el-col :span="7" >
          <div></div>
        </el-col>
      </el-row>
     
      <el-row class="paddingbg-s">
         <el-row type="flex" class="row-bg" justify="space-around">
            <el-col :span="9">
              <el-form-item class="comright" label="账号类型" prop="">
                <el-input
                 value="私人账号"
                  disabled
                ></el-input>
              </el-form-item>

              <el-form-item class="comright" label="开户银行" prop="privateDepositBank">
                <el-input
                  v-model="formBank.privateDepositBank"
                  :readonly="true"
                ></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="9">
              <el-form-item class="comright" label="账号名称" prop="legalPersonName">
                <el-input
                 v-model="formBank.legalPersonName"
                  :readonly="true"
                ></el-input>
              </el-form-item>
              <el-form-item class="comright" label="银行账号" prop="privateAccountNumber">
                <el-input
                  v-model="formBank.privateAccountNumber"
                  :readonly="true"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
         <el-row v-if="accountType==2">
            <el-col :span="9">
               <el-form-item class="comright" label="账号类型" prop="">
                <el-input
                  value="对公基本户"
                  :readonly="true"
                ></el-input>
              </el-form-item>
          

              <el-form-item class="comright" label="开户银行">
                <el-input   v-model="formBank.publicDepositBank1" :readonly="true"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="9">
                   <el-form-item class="comright" label="账号名称">
                <el-input     v-model="formBank.selfName" :readonly="true"></el-input>
              </el-form-item>
              <el-form-item class="comright" label="银行账号">
                <el-input     v-model="formBank.publicAccountNumber1" :readonly="true"></el-input>
              </el-form-item>
            </el-col>
            
          </el-row>
         <el-row v-if="accountType==2">
            <el-col :span="9">
                <el-form-item class="comright" label="账号类型" prop="">
                <el-input
                 
                  value="对公一般户"
                  disabled
                ></el-input>
              </el-form-item>
              

              <el-form-item class="comright" label="开户银行">
                <el-input  v-model="formBank.publicDepositBank2" :readonly="true"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="9">
               <el-form-item class="comright" label="账号名称">
                <el-input  v-model="formBank.selfName" :readonly="true"></el-input>
              </el-form-item>
              <el-form-item class="comright" label="银行账号">
                <el-input  v-model="formBank.publicAccountNumber2" :readonly="true"></el-input>
              </el-form-item>
            </el-col>
            
          </el-row>
          
        
        
      </el-row>
      <el-row type="flex" class="row-bg combottom" justify="space-around">
        <el-col :span="7" >
          <div class="bankno">纳税账号</div>
        </el-col>
        <el-col :span="7" >
         
        </el-col>
      </el-row>

      <el-row  type="flex" class="row-bg " justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="户名" prop="accountName">
            <!-- <el-input v-model="formBank.accountName"></el-input> -->
            <el-select
             disabled
               style="width:100%"
              @change="changeValue($event)"
              v-model="formBank.accountName"
              filterable
              placeholder="请选择"
            >
              <el-option
                v-for="item in accountName_options"
                :key="item.value"
                :label="item.value"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item class="comright" label="开户银行" prop="publicDepositBank3">
            <el-input  disabled v-model="formBank.publicDepositBank3" :readonly="true"></el-input>
            <!-- <el-select disabled v-model="formBank.publicDepositBank3" filterable placeholder="请选择">
                     <el-option v-for="item in publicDepositBank3_options" :key="item.value" :label="item.value"
                        :value="item.value">
                     </el-option>
                  </el-select> -->
          </el-form-item>
          <el-form-item  label="纳税委托协议" prop="fileName3">
               <div v-for="(item, index) in previewList3" :key="index">
              <el-image lazy :preview-src-list="previewList3" style="width: 150px; height: 150px" :src="item" alt="" />
            </div>
            <div v-for="(x, y) in pdfList3" :key="y">
              <span @click="pdfdetail(x)">
                {{ x }}
              </span>
            </div>
          </el-form-item>
        </el-col>
        
        <el-col :span="9">
          <el-form-item class="comright" label="银行账号" prop="publicAccountNumber3">
            <el-input
             
              disabled
              v-model="formBank.publicAccountNumber3"
            ></el-input>
            <!-- <el-select disabled v-model="formBank.publicAccountNumber3" filterable placeholder="请选择">
                     <el-option v-for="item in publicAccountNumber3_options" :key="item.value" :label="item.value" :value="item.value">
                     </el-option>
                  </el-select> -->
          </el-form-item>
          <el-form-item style="color: rgba(0, 0, 0, 0)">
            <br />
          </el-form-item>
          <el-form-item label="三方协议签约凭证" prop="fileName4">
               <div v-for="(item, index) in previewList4" :key="index">
              <el-image lazy :preview-src-list="previewList4" style="width: 150px; height: 150px" :src="item" alt="" />
            </div>
            <div v-for="(x, y) in pdfList4" :key="y">
              <span @click="pdfdetail(x)">
                {{ x }}
              </span>
            </div>
          </el-form-item>
        </el-col>
      </el-row>
        <el-row type="flex" class="row-bg " justify="space-around">
         <el-col :span="8"></el-col>
         <el-col :span='8' class="flexs">
             <el-button type="danger" @click="resetForm">返回</el-button> 
            
         </el-col>
         <el-col :span="8"></el-col>
       </el-row>
    </el-form>
     <!--PDF 预览-->
    <el-dialog :title="titles" :visible.sync="viewVisible" width="80%" center @close='closeDialog'>

      <div>
        <div class="tools flexs" style=" align-items: center;">
          <div class="page" style="margin-right:20px;font-size: 20px;">共{{ pageNum }}/{{ pageTotalNum }} </div>
          <el-button :theme="'default'" type="submit" @click.stop="prePage" class="mr10"> 上一页</el-button>
          <el-button :theme="'default'" type="submit" @click.stop="nextPage" class="mr10"> 下一页</el-button>
          <el-button :theme="'default'" type="submit" @click.stop="clock" class="mr10"> 顺时针</el-button>
          <el-button :theme="'default'" type="submit" @click.stop="counterClock" class="mr10"> 逆时针</el-button>

        </div>
        <pdf ref="pdf" :src="url" :page="pageNum" :rotate="pageRotate" @progress="loadedRatio = $event"
          @page-loaded="pageLoaded($event)" @num-pages="pageTotalNum = $event" @error="pdfError($event)"
          @link-clicked="page = $event">
        </pdf>

      </div>
    </el-dialog>
  </div>
</template>

<script>
import pdf from 'vue-pdf'
import { all } from "@/api/company/payTaxInfo";
import { addEmployed, updateEmployed } from "@/api/company/employed";
export default {
   components: {
    pdf
  },
  data() {
    return {
      titles: '',
      pdfList3:[],
      pdfList4:[],  //pdf 预览
      previewList3:[], //预览
      previewList4:[], //预览
      //pdf预览
      url: '',
      viewVisible: false,
      pageNum: 1,
      pageTotalNum: 1,
      pageRotate: 0,
      // 加载进度
      loadedRatio: 0,
      curPageNum: 0,
      closeDialog: false,



       baseImgPath:"/eladmin/api/files/showTxt?imgPath=",
       formBank: {
        bankStatus: 1,
        selfId: "",
        accountName: "",
        privateDepositBank: "",
        privateAccountNumber: "",
        publicDepositBank1: "",
        publicAccountNumber1: "",
        publicDepositBank2: "",
        publicAccountNumber2: "",
        publicDepositBank3: "",
        publicAccountNumber3: "",
        selfName: "",
        legalPersonName: "",
        taxId: "",
        fileName3: [],
        fileName4: [],
      },
      accountType: "",
      activeNames: ["1"],
      dialogImageUrl1: "",
      fileName3: [],
      dialogImageUrl2: "",
      fileName4: [],
      dialogVisible1: false,
      dialogVisible2: false,
      mylist: [],
      accountName_options: [],
      publicDepositBank3_options: [],
      publicAccountNumber3_options: [],

      rules: {
        selfName: [
          { required: true, message: "请输入名称", trigger: "blur" },
          // { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        legalPersonName: [
          { required: true, message: "请输入法人姓名", trigger: "blur" },
        ],
        taxId: [
          { required: true, message: "请输入纳税人识别号", trigger: "blur" },
        ],

        publicDepositBank1: [
          {
            required: true,
            message: "请输入对公基本户开户银行",
            trigger: "blur",
          },
        ],
        publicAccountNumber1: [
          {
            required: true,
            message: "请输入对公基本户银行账号",
            trigger: "blur",
          },
        ],
        publicDepositBank2: [
          {
            required: true,
            message: "请输入对公一般户开户银行",
            trigger: "blur",
          },
        ],
        publicAccountNumber2: [
          {
            required: true,
            message: "请输入对公一般户银行账号",
            trigger: "blur",
          },
        ],

        privateDepositBank: [
          { required: true, message: "请输入私人开户银行", trigger: "blur" },
        ],
        privateAccountNumber: [
          { required: true, message: "请输入私人银行账号", trigger: "blur" },
        ],
        accountName: [
          { required: true, message: "请输入纳税账号户名", trigger: "change" },
        ],
        publicDepositBank3: [
          {
            required: true,
            message: "请输入纳税账号开户银行",
            trigger: "change",
          },
        ],
        publicAccountNumber3: [
          {
            required: true,
            message: "请输入纳税账号银行账号",
            trigger: "change",
          },
        ],

        fileName3: [
          {
            required: true,
            message: "请上传纳税委托协议文件",
            trigger: "change",
          },
        ],
        fileName4: [
          {
            required: true,
            message: "请上传三方协议签约凭证文件",
            trigger: "change",
          },
        ],
      },
    };
  },
  created() {
    let list = this.$cache.local.getJSON("employednewlist");
    // this.formBank.selfId = list.selfId;
    // this.formBank.selfName = list.selfName;
    // this.formBank.legalPersonName = list.legalPersonName;

    // this.formBank.privateDepositBank = list.privateDepositBank;
    // this.formBank.privateAccountNumber = list.privateAccountNumber;
    // this.formBank.taxId = list.taxId;
     this.formBank=list;
     this.pdfList3=[];  //pdf 预览
     this.previewList3=[]; //预览
     this.pdfList4=[];  //pdf 预览
     this.previewList4=[]; //预览
    
    this.fileName3=JSON.parse(this.$cache.local.getJSON('employednewlist').fileName3);
    for(let k1 in this.fileName3){
      if (this.fileName3[k1].substring(this.fileName3[k1].lastIndexOf('.') + 1) == 'pdf') {

        this.pdfList3.push(this.fileName3[k1]);
      } else {
        this.fileName3[k1] = this.baseImgPath + this.fileName3[k1];
        this.previewList3.push(this.fileName3[k1]);
      }

     //  this.fileName3[k1]=this.baseImgPath+this.fileName3[k1];
    } 
     this.fileName4=JSON.parse(this.$cache.local.getJSON('employednewlist').fileName4);
    for(let k2 in this.fileName4){
     // this.fileName4[k2]=this.baseImgPath+this.fileName4[k2];
      if (this.fileName4[k2].substring(this.fileName4[k2].lastIndexOf('.') + 1) == 'pdf') {

        this.pdfList4.push(this.fileName4[k2]);
      } else {
        this.fileName4[k2] = this.baseImgPath + this.fileName4[k2];
        this.previewList4.push(this.fileName4[k2]);
      }
    } 
    this.accountType = list.accountType;
    this.nailist();
  },
  beforeRouteLeave(to, from, next) {
    to.meta.keepAlive = true;
    next(0);
  },

  methods: {
   pdfdetail(i) {
      this.titles = '正在预览' + i;
      this.viewVisible = true;
      this.url = this.baseImgPath + i;
    },
    // 上一页函数，
    prePage() {
      var page = this.pageNum
      page = page > 1 ? page - 1 : this.pageTotalNum
      this.pageNum = page
    },
    // 下一页函数
    nextPage() {
      var page = this.pageNum
      page = page < this.pageTotalNum ? page + 1 : 1
      this.pageNum = page
    },
    // 页面顺时针翻转90度。
    clock() {
      this.pageRotate += 90
    },
    // 页面逆时针翻转90度。
    counterClock() {
      this.pageRotate -= 90
    },
    // 页面加载回调函数，其中e为当前页数
    pageLoaded(e) {
      this.curPageNum = e
    },
    // 其他的一些回调函数。
    pdfError(error) {
      console.error(error)
    },



    changeValue(res) {
      for (let i in this.mylist) {
        if (this.mylist[i].accountName == res) {
          this.formBank.publicDepositBank3 = this.mylist[i].publicDepositBank3;
          this.formBank.publicAccountNumber3 =
            this.mylist[i].publicAccountNumber3;
          return;
        }
      }
    },
    nailist() {
      all()
        .then((res) => {
          if (res != undefined) {
            console.log(res);
            this.mylist = [];
            this.accountName_options = [];
            this.mylist = res;
            // this.publicDepositBank3_options = [];
            // this.publicAccountNumber3_options = [];
            for (let i in res) {
              this.accountName_options.push({ value: res[i].accountName });
              // this.publicDepositBank3_options.push({value:res[i].publicDepositBank3});
              // this.publicAccountNumber3_options.push({value:res[i].publicAccountNumber3});
            }
          }
        })
        .catch((error) => {
          this.modal.msgError(error);
        });
    },
    handleChange(val) {
      console.log(val);
    },
    resetForm() {
       this.$tab.closeOpenPage({ path: "/company/customer/manageBank"});
    },
    onSubmit() {
      this.$refs["formBank"].validate((valid) => {
        if (valid) {
          this.formBank.fileName3 = JSON.stringify(this.formBank.fileName3);
          this.formBank.fileName4 = JSON.stringify(this.formBank.fileName4);
          updateEmployed(this.formBank)
            .then((res) => {
              if (res != undefined) {
                if (res.code === 200) {
                   this.$nextTick(function () {
                     this.$tab.refreshPage({ path: "/company/customer/manageBank"}).then(() => {
                     let  resmsg='办理银行成功';
                     let obj={
                        title:'税务办理',
                        backUrl:'/company/customer/manageBank',
                        resmsg:resmsg
                        };
                      this.$cache.local.setJSON('successNew', obj);
                      this.$tab.closeOpenPage({ path: "/company/customer/successNew"});
                    });
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
          this.$modal.msgError("请填写完整");
        }
      });
    },
    handlesuccess1(file, fileList) {
      this.formBank.fileName3.push(file.obj);
    },
    handleRemove1(file, fileList) {
      const i = this.formBank.fileName3.findIndex((item) => item === fileList);
      this.formBank.fileName3.splice(i, 1);
    },
    handlePreview1(file) {
      this.dialogImageUrl1 = file.url;
      this.dialogVisible1 = true;
    },
    handleExceed1(files, fileList) {
      this.$message.warning(
        `当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${
          files.length + fileList.length
        } 个文件`
      );
    },
    beforeRemove1(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    handlesuccess2(file, fileList) {
      this.formBank.fileName4.push(file.obj);
    },
    handleRemove2(file, fileList) {
      const i = this.formBank.fileName4.findIndex((item) => item === fileList);
      this.formBank.fileName4.splice(i, 1);
    },
    handlePreview2(file) {
      this.dialogImageUrl2 = file.url;
      this.dialogVisible2 = true;
    },
    handleExceed2(files, fileList) {
      this.$message.warning(
        `当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${
          files.length + fileList.length
        } 个文件`
      );
    },
    beforeRemove2(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
  },
};
</script>

<style>
.bankno {
 
  letter-spacing: 2px;

  font-size: 20px;

  color: blue;
}

.paddingbg-s {
  padding-top: 15px;
}

.footers {
  display: flex;
  justify-content: center;
  align-items: center;
}
.combottom{
  margin-bottom: 10px;
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
</style>