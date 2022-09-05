<template>
    <div>
        <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="140px">
            <el-row type="flex" class="row-bg" style="margin-top:20px;" justify="space-around">
                <el-col :span="9" class="flexs">
                    <div class="bankno" style="width:35%">项目信息</div>
                    <div style="width:50%;hegiht:10px"></div>
                </el-col>
                <el-col :span="9">
                    <div></div>
                </el-col>
            </el-row>      
            
            <el-row type="flex" class="row-bg rowCss "  justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="项目编号" :required="true">
                        <el-input v-model="publicList.projectCode" :disabled="true"></el-input>
                    </el-form-item>

                    <el-form-item class="comright" label="项目名称" :required="true">
                        <el-input v-model="publicList.projectName" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="应收账款" :required="true">
                        <el-input :disabled="true" v-model="publicList.projectTotalAmount"
                         oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
                        >
                            <template slot="append">元</template>
                        </el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="已收账款" :required="true">
                        <el-input :disabled="true" v-model="publicList.projectTotalAmount"
                         oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
                        >
                            <template slot="append">元</template>
                        </el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="未收账款" :required="true">
                        <el-input :disabled="true" v-model="publicList.projectTotalAmount"
                         oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,2})?/g) ?? [""])[0]'
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
                        <el-input :disabled="true" type="number" style="width:100%"
                            v-model="publicList.projectTotalAmount" :step="0.00001" :min="0"
                            oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'>
                            <template slot="append">
                                元
                            </template>
                        </el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="应出账款" :required="true">
                        <el-input :disabled="true" v-model="publicList.projectTotalAmount"
                         oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
                        >
                            <template slot="append">元</template>
                        </el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="已出账款" :required="true">
                        <el-input :disabled="true" v-model="publicList.projectTotalAmount"
                         oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
                        >
                            <template slot="append">元</template>
                        </el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="未出账款" :required="true">
                        <el-input disabled v-model="publicList.projectTotalAmount"
                         oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
                        >
                            <template slot="append">元</template>
                        </el-input>
                    </el-form-item>
                    
                   
                  
                </el-col>
            </el-row>
            
             <el-row type="flex" class="row-bg"  justify="space-around">
                <el-col :span="9" class="flexs">
                    <div class="bankno" style="width:35%">收款信息</div>
                    <div style="width:50%;hegiht:10px"></div>
                </el-col>
                <el-col :span="9">
                    <div></div>
                </el-col>
            </el-row>  




            <el-row type="flex" class="row-bg rowCss " justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="转账账户"  prop="receiveName">
                        <el-input v-model="formData.receiveName" ></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="收账金额" prop="receiveMoney">
                        <el-input :disabled="true" v-model="formData.receiveMoney"
                        :step="0.00001" :min="0"
                         onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                         oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
                        >
                            <template slot="append">元</template>
                        </el-input>
                       
                    </el-form-item>
                    <el-form-item class="comright" label="转账凭证" prop="fileNameReceive">
                        <uploadSmall @getIndex="getindexs" @getfileName="getReceive" :fileName="fileName" :fileNameOld="fileName"
                            :isDetail="isDetail" :index="0"></uploadSmall>
                    </el-form-item>

                    

                </el-col>

                <el-col :span="9">
                    <el-form-item class="comright" label="转账时间" :required="true">
                        <el-input v-model="formData.receiveTime" disabled></el-input>
                    </el-form-item>

                    <el-form-item class="comright" label="转账账号" prop="receiveAccount">
                        <el-input v-model="formData.receiveAccount"></el-input>
                    </el-form-item>
                  


                </el-col>
            </el-row>
            <el-row type="flex" class="row-bg" style="margin-bottom:20px"  justify="space-around">
                <el-col :span="9" class="flexs">
                    <div class="bankno" style="width:35%">出款信息</div>
                    <div style="width:50%;hegiht:10px"></div>
                </el-col>
                <el-col :span="9">
                    <div></div>
                </el-col>
            </el-row>
            <el-row  type="flex" class="row-bg"  justify="space-around"> 
                <el-col :span="9">
                    <el-form-item class="comright" label="操作"  :required="true">
                        <el-button type="primary" size="small" style="width:80px;" @click="adds">添加</el-button>
                       
                    </el-form-item>
                   
                </el-col>
                <el-col :span="9">
                    
                </el-col>
                
            </el-row>
            <el-row v-for="(item,index) in arrs" :key="index"   type="flex" class="row-bg"  justify="space-around">
            <el-col :span="9">
                    <el-form-item class="comright" label="出账账户"  :required="true">
                        <el-input v-model="item.payName" ></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="出账金额" :required="true">
                      
                        <el-input :disabled="true" v-model="item.payMoney"
                        :step="0.00001" :min="0"
                         onkeyup="value=value.replace(/[^\x00-\xff]/g, '')"
                         oninput = 'value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
                        >
                            <template slot="append">元</template>
                        </el-input>
                     </el-form-item>
                    <el-form-item class="comright" label="出账凭证" :required="true">
                        <uploadSmall :index="index" @getIndex="getindexNew" @getfileName="getPay" :fileName="fileName" :fileNameOld="fileName"
                            :isDetail="isDetail"  ></uploadSmall>
                    </el-form-item>

                    

                </el-col>

                <el-col :span="9">
                    <el-form-item class="comright" label="出账时间" :required="true">
                        <el-input v-model="formData.payTime" :disabled="true"></el-input>
                    </el-form-item>

                    <el-form-item class="comright" label="出账账号" :required="true">
                        <el-input v-model="formData.payAccount"></el-input>
                    </el-form-item>
                    <el-form-item label="删除该项">
                        <el-button type="danger" size="small" style="width:80px;" @click="dels(index)">删除</el-button>
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
import uploadSmall from '@/components/douploads/uploadCollect'
import { getReceiveCode,check } from "@/api/project/list";
import { getInfo } from '@/api/login'
export default {
    name: 'AddCollect',
    components: { uploadSmall },
    data() {
        return {
            index:0,
            arrs:[{
                payAccount:'',
                payName:'',
                payMoney:'',
                fileNamePay:[],
            }],
            publicList:{},
            userinfo: {},
            isDetail: '0',
            fileName: [],
            formData: {
                fileNameReceive:[],
                projectCode:'',
                projectName:'',
                receiveTime:'',//转账时间 收款信息
                payReceiveSysCode:'',//流水号
                projectTotalAmount:'',
                projectRemainAmount:"",
                projectPackageAmount:'',
                receiveAccount:"",//转账账号 收款信息
                receiveName:'',//转账账户 收款信息
                receiveMoney:'0.00000',//收款金额 收款信息

            },
            rules: {
                receiveName:[
                {
                   required: true,
                   message: "转账账户不能为空",
                   trigger: "blur",
                },
                ],
                receiveMoney:[{
                    required: true,
                    message: "转账金额不能为空",
                    trigger: "blur",
                }],
                receiveAccount:[{
                    required: true,
                    message: "转账账号不能为空",
                    trigger: "blur",
                }],
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
        this.gettoday();
        this.publicList = this.$cache.local.getJSON("projectListNews");
      
    },
    methods: {
        dels(index){
            this.arrs.splice(index,1);

        },
        adds(){
           this.arrs.push({
                payAccount:'',
                payName:'',
                payMoney:'',
                fileNamePay:[],
           })
        },
        getindexNew(data){
           this.index=data;
        },
        getPay(data){
          this.arrs[this.index].fileNamePay=data;
         },
        getindexs(){

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
            var hour = this.repair(date.getHours());//时
            var minute = this.repair(date.getMinutes());//分
            var second = this.repair(date.getSeconds());//秒
            //当前时间 
            var curTime = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
            // this.formData.createTime = curTime;
            this.formData.receiveTime = curTime;

        },
        check(resmsg) {
            getInfo().then(res => {
                this.userinfo = res.user;
                let parms = {
                    "checkReasult": resmsg,
                    "checkUser": this.userinfo.userName,
                    'phonenumber': this.userinfo.phonenumber,
                    "projectCode": this.formData.projectCode,
                    "projectType": "15",
                };
                check(parms).then(res => {
                    console.log('验收办理成功！');
                }).catch(error => {

                });
            })

        },
        //获取系统流水号
        getReceiveCode(){
            getReceiveCode().then(res=>{
                this.formData.payReceiveSysCode=res;
            })
        },
        getReceive(data,type) {
            console.log(111,type);
            this.formData.fileNameReceive = data;
        },
        //返回
        resetForm() {
            this.$tab.closeOpenPage({path:this.$cache.local.getJSON('addProjectBack').backurl })
        },
        handleChange(val) {
            console.log(val);
        },
        onSubmit() {
            this.$refs["elForm"].validate((valid) => {
                // TODO 提交表单
                if (valid) {
                    this.formData.fileName2 = JSON.stringify(this.formData.fileName2);

                    let parms = {
                        projectId: this.formData.projectId,
                        fileName2: this.formData.fileName2
                    };
                    edit(parms).then((res) => {
                        if (res != undefined) {
                            if (res.code === 200) {
                                this.$nextTick(function () {
                                 this.check('验收办理成功');
                                 this.$modal.msgSuccess('验收办理成功');
                                 this.$tab.closeOpenPage({path:this.$cache.local.getJSON('addProjectBack').backurl }).then(() => {
                                          // 执行结束的逻辑 
                                 this.$tab.refreshPage({ path:this.$cache.local.getJSON('addProjectBack').backurl,name:this.$cache.local.getJSON('addProjectBack').name})
                                })
                                  
                            });
                            } else {
                                this.$modal.msgError(res.msg);
                                this.$tab.closeOpenPage({ path:this.$cache.local.getJSON('addProjectBack').backurl});
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
</style>
