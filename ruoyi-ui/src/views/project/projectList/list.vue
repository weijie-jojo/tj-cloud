<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
            label-width="auto">
            <el-form-item label="乙方">
                <el-input v-model="queryParams.selfName" placeholder="请输入乙方" clearable
                    @keyup.enter.native="handleQuery" />
            </el-form-item>

            <el-form-item label="项目时间">
                <el-date-picker
                @change="prjecs"
                v-model="projectTime" value-format="yyyy-MM-dd" type="daterange"
                    :picker-options="pickerOptions" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期"
                    :default-time="['00:00:00', '23:59:59']" align="right">
                </el-date-picker>

            </el-form-item>

            <el-form-item>
                <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
            <el-col :span="15">
                <el-tabs v-model="endStatus" @tab-click="handleClick">
                <el-tab-pane :label="loadingLabel" name="0"></el-tab-pane>
                <el-tab-pane :label="errLabel" name="1"></el-tab-pane>
                <el-tab-pane :label="finishLabel" name="2"></el-tab-pane>
                <el-tab-pane :label="allLabel" name="-1"></el-tab-pane>
                   

                </el-tabs>

            </el-col>
            
            <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
        <el-button style="margin-top:-8px;margin-bottom:16px" type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>
        <el-button style="margin-top:-8px;margin-bottom:16px" type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete">删除</el-button>    

        <el-table v-loading="loading" :data="projectList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="乙方" align="center" prop="selfName"  width="200" :show-overflow-tooltip="true" />
            <el-table-column label="甲方" align="center" prop="purchCompany" :show-overflow-tooltip="true" />
            <el-table-column label="项目名称" align="center" prop="projectName" :show-overflow-tooltip="true" />
            <el-table-column label="项目时间" align="center"  :show-overflow-tooltip="true">
              <template slot-scope="scope"> {{ scope.row.createTime | filterTime }}</template>
            </el-table-column>
            <el-table-column label="业务经理" align="center" prop="projectLeader" :show-overflow-tooltip="true" />
            <el-table-column label="进度状态" align="center" prop="projectStatus">
                <template slot-scope="scope">
                    <el-link :underline="false" @click="progressNew(scope.row.projectCode, 0)" type="danger"
                        v-if="scope.row.projectStatus == '1'">异常</el-link>
                    <el-link :underline="false" @click="progressNew(scope.row.projectCode, 0)" type="success"
                        v-if="scope.row.projectStatus == '2'">完成</el-link>
                    <el-link :underline="false" @click="progressNew(scope.row.projectCode, 0)" type="primary"
                        v-if="scope.row.projectStatus == '0'">办理中</el-link>
                </template>
            </el-table-column>

            <el-table-column label="项目审核" align="center" prop="projectCheckStatus">
                <template slot-scope="scope">
                    <el-link :underline="false" type="primary" @click="examine(scope.row.userId, scope.row, 1)"
                        v-if="scope.row.projectCheckStatus == '0'">办理中</el-link>
                    <el-link :underline="false" type="danger"
                        @click="progressError(scope.row.projectCode, scope.row, 1)"
                        v-if="scope.row.projectCheckStatus == '2'">异常</el-link>
                    <el-link :underline="false" type="success" @click="progressNe(scope.row.projectCode, scope.row, 1)"
                        v-if="scope.row.projectCheckStatus == '1'">完成</el-link>
                </template>
            </el-table-column>
            <el-table-column label="项目票据" align="center">

                <template slot-scope="scope">
                    <el-link :underline="false" type="info"
                        v-if="scope.row.projectCheckStatus == 0 || scope.row.projectCheckStatus == 2">
                        未开始</el-link>

                    <el-link @click="examine(scope.row.userId, scope.row, 2,scope.row.projectCode)" :underline="false" type="primary"
                        v-if="scope.row.projectCheckStatus == '1' && scope.row.projectTicketStatus == '0'">办理中</el-link>

                    <el-link :underline="false" type="danger"
                        @click="progressError(scope.row.projectCode, scope.row, 2)"
                        v-if="scope.row.projectCheckStatus == '1' && scope.row.projectTicketStatus == '2'">异常</el-link>

                    <el-link @click="progressNe(scope.row.projectCode, scope.row, 2)" :underline="false" type="success"
                        v-if="scope.row.projectCheckStatus == '1' && scope.row.projectTicketStatus == '1'">完成</el-link>

                </template>
            </el-table-column>
            <el-table-column label="项目合同" align="center">

                <template slot-scope="scope">
                    <el-link :underline="false" type="info"
                        v-if="scope.row.projectCheckStatus == 0 || scope.row.projectCheckStatus == 2">
                        未开始</el-link>

                    <el-link @click="examine(scope.row.userId, scope.row, 3)" :underline="false" type="primary"
                        v-if="scope.row.projectCheckStatus == '1' && scope.row.projectContractStatus == 0">办理中
                    </el-link>
                    <el-link :underline="false" type="danger"
                        @click="progressError(scope.row.projectCode, scope.row, 3)"
                        v-if="scope.row.projectCheckStatus == '1' && scope.row.projectContractStatus == '2'">异常
                    </el-link>
                    <el-link :underline="false" type="success" @click="progressNe(scope.row.projectCode, scope.row, 3)"
                        v-if="scope.row.projectCheckStatus == '1' && scope.row.projectContractStatus == '1'">完成
                    </el-link>

                </template>
            </el-table-column>
            <el-table-column label="项目验收" align="center">

                <template slot-scope="scope">
                    <el-link :underline="false" type="info"
                        v-if="scope.row.projectCheckStatus == 0 || scope.row.projectCheckStatus == 2">
                        未开始</el-link>

                    <el-link :underline="false" type="primary" @click="examine(scope.row.userId, scope.row, 4)"
                        v-if="scope.row.projectCheckStatus == '1' && scope.row.projectAcceptanceStatus == 0">办理中
                    </el-link>
                    <el-link :underline="false" type="danger"
                        @click="progressError(scope.row.projectCode, scope.row, 4)"
                        v-if="scope.row.projectCheckStatus == '1' && scope.row.projectAcceptanceStatus == '2'">异常
                    </el-link>
                    <el-link :underline="false" type="success" @click="progressNe(scope.row.projectCode, scope.row, 4)"
                        v-if="scope.row.projectCheckStatus == '1' && scope.row.projectAcceptanceStatus == '1'">完成
                    </el-link>

                </template>
            </el-table-column>
            <el-table-column label="项目完税" align="center">

                <template slot-scope="scope">
                    <el-link :underline="false" type="info"
                        v-if="scope.row.projectCheckStatus == 0 || scope.row.projectCheckStatus == 2">
                        未开始</el-link>

                    <el-link :underline="false" type="primary" @click="examine(scope.row.userId, scope.row, 5)"
                        v-if="scope.row.projectCheckStatus == '1' && scope.row.projectDutypaidStatus == '0'">办理中
                    </el-link>
                    <el-link :underline="false" type="danger"
                        @click="progressError(scope.row.projectCode, scope.row, 5)"
                        v-if="scope.row.projectCheckStatus == '1' && scope.row.projectDutypaidStatus == '2'">异常
                    </el-link>
                    <el-link :underline="false" type="success" @click="progressNe(scope.row.projectCode, scope.row, 5)"
                        v-if="scope.row.projectCheckStatus == '1' && scope.row.projectDutypaidStatus == '1'">完成
                    </el-link>
                </template>

            </el-table-column>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button size="mini" type="text" icon="el-icon-s-custom" @click="detail(scope.row)">详情</el-button>
                    <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">修改
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize" @pagination="getList" />
        <!-- 进度提示 -->
        <el-dialog :closeOnClickModal=false :closeOnPressEscape=false :title="title" :visible.sync="dialogVisible"
            width="70%">
            <el-table :data="progressList">
                <el-table-column label="步骤" align="center" prop="dictLabel" :show-overflow-tooltip="true" />
                <el-table-column label="时间" align="center" prop="checkDate" width="180" />
                <el-table-column label="用户" align="center" prop="checkUser" />
                <el-table-column label="说明" align="center" prop="checkReasult" :show-overflow-tooltip="true" />
            </el-table>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">关闭</el-button>
                <el-button type="primary" v-if="lookstatus" @click="findList">查看</el-button>
                <el-button type="primary" v-if="editstatus" @click="editList">修改</el-button>
            </span>
        </el-dialog>

    </div>

</template>

<script>
import moment from 'moment'
import { getLeaderByUserId } from "@/api/company/employed"
import { getUser } from "@/api/system/user"
import { list, del, checkdetail,getCount} from "@/api/project/list"

export default {
    data() {
        return {
            allLabel: '全部',
            errLabel: '异常',
            loadingLabel: '办理中',
            finishLabel: '完成',
            endStatus:'0',
            types: 0,
            lookstatus: false,
            editstatus: false,
            dialogVisible: false,
            progressList: [],
            // 遮罩层
            loading: true,
            // 选中数组
            ids: [],
            // 非单个禁用
            single: true,
            // 非多个禁用
            multiple: true,
            // 显示搜索条件
            showSearch: true,
            // 总条数
            total: 0,
            // 项目列表表格数据
            projectList: [],
            // 弹出层标题
            title: "",
            // 是否显示弹出层
            open: false,
            // 查询参数
            queryParams: {
                type:1,
                pageNum: 1,
                pageSize: 10,
                selfName: null,  //乙方
                projectTimeStart: null, //开始
                projectTimeEnd: null,   //结束
                projectStatus: 0, //项目状态
                start: null, //开始
                end: null,   //结束
            },
            projectTime: null,
            pickerOptions: {
                shortcuts: [{
                    text: '最近一周',
                    onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                        picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '最近一个月',
                    onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                        picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '最近三个月',
                    onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                        picker.$emit('pick', [start, end]);
                    }
                }]
            },
            options: [
                {
                    value: 0,
                    label: '进行'
                },
                {

                    value: 1,
                    label: '异常'
                },
                {

                    value: 2,
                    label: '完成',
                },
            ],
            // 表单参数
            form: {},
            // 表单校验
            rules: {},
        };
    },
   filters: {
    filterTime(time) {

      return moment(time).format('YYYY-MM-DD')
    },
   },
    mounted() {
        this.getList();
    },
    methods: {
      prjecs(e) {
        if (!this.projectTime) {
        this.queryParams.start = null;
        this.queryParams.end = null;
       }
       },
        handleClick() {
         if(this.endStatus=='-1'){
             this.queryParams.projectStatus=null;
         }else{
              this.queryParams.projectStatus=this.endStatus;
         }
              this.queryParams.pageNum=1;
              this.getList();
        },
        examine(applyName, scope, type,code) {
            this.$cache.local.setJSON('projectCodeNew', code);
            this.$cache.local.setJSON('publicTickets', scope);
            this.$cache.local.setJSON("projectListNews", scope);
            var msg = '办理';
            this.types = type;
            getLeaderByUserId({
                userId: applyName
            }).then(res => {
                // let userName;
                // let phonenumber;
                if (!res) {
                    getUser(applyName).then(success => {
                        console.log(success);
                        let userName = success.data.nickName;
                        let phonenumber = success.data.phonenumber;
                        const h = this.$createElement
                        this.$confirm(
                            '', {
                            message: h('div', null, [
                                h('i', { class: 'el-icon-question', style: 'color:#f90;font-size:30px;' }),
                                h('span', { style: 'margin-left:10px;font-size:16px;line-height:30px;font-weight:600;vertical-align:top;' }, '温馨提示'),
                                h('p', { style: 'margin:40px 0 0 40px;height:80px' }, '请等待' + userName + '(' + phonenumber + ')' + msg)
                            ]),
                            confirmButtonText: '查看',
                            cancelButtonText: '关闭',
                            closeOnClickModal: false,
                            closeOnPressEscape: false,

                        }).then(() => {
                            this.findList();
                        }).catch(() => {

                        });
                    });
                } else {
                    let userName = res[0].userName;
                    let phonenumber = res[0].phonenumber;
                    const h = this.$createElement
                    this.$confirm(
                        '', {
                        message: h('div', null, [
                            h('i', { class: 'el-icon-question', style: 'color:#f90;font-size:30px;' }),
                            h('span', { style: 'margin-left:10px;font-size:16px;line-height:30px;font-weight:600;vertical-align:top;' }, '温馨提示'),
                            h('p', { style: 'margin:40px 0 0 40px;height:80px' }, '请等待' + userName + '(' + phonenumber + ')' + msg)
                        ]),



                        confirmButtonText: '查看',
                        cancelButtonText: '关闭',
                        closeOnClickModal: false,
                        closeOnPressEscape: false,

                    }).then(() => {
                        this.findList();
                    }).catch(() => {

                    });
                }

            }).catch(error => {
                console.log(error);
            })
        },
        //修改
        editList() {
            switch (this.types) {
                case 1:
                    this.$cache.local.setJSON("iscxxiu", 1);
                    this.$tab.closeOpenPage({ path: '/project/editList' })
                    break;
                case 2:
                    this.$cache.local.setJSON("iscxxiu", 1);
                    this.$tab.closeOpenPage({ path: '/project/ticketlist' })
                    break;
                case 3:
                    this.$tab.closeOpenPage({ path: '/project/contracts' })
                    break;
                case 4:
                    this.$tab.closeOpenPage({ path: '/project/acceptances' })
                    break;
                case 5:
                    this.$tab.closeOpenPage({ path: '/project/dutypaids' })
                    break;

            }
        },
        //详情
        findList() {
            switch (this.types) {
                case 1:
                    this.$tab.closeOpenPage({ path: '/project/DetailS' })
                    break;
                case 2:
                    this.$cache.local.setJSON("iscxxiu", 0);
                    this.$tab.closeOpenPage({ path: '/project/ticketlist' })
                    break;
                case 3:
                    this.$tab.closeOpenPage({ path: '/project/ContractDetailS' })
                    break;
                case 4:
                    this.$tab.closeOpenPage({ path: '/project/AcceptanceDetailS' })
                    break;
                case 5:
                    this.$tab.closeOpenPage({ path: '/project/DutypaidDetailS' })
                    break;

            }
        },
        //进度弹框
        progressNew(code, type) {
            this.lookstatus = false;
            this.editstatus = false;
            let msg = '进度详情';
            if (type == 0) {

                msg = '进度详情';
            } else if (type == 1) {
                msg = '项目详情';
            } else if (type == 2) {
                msg = '票据详情';
            } else if (type == 3) {
                msg = '合同详情';
            } else if (type == 4) {
                msg = '验收详情';
            } else if (type == 5) {
                msg = '完税详情';
            }
            if (type > 0) {
                this.types = type;
            } else {
                this.types = '';
            }

            this.checkdetail(code, this.types, msg);
        },
        //进度弹框
        progressNe(code, row, type) {
            this.$cache.local.setJSON('projectCodeNew', code);
            this.$cache.local.setJSON('publicTickets', row);
            this.$cache.local.setJSON("projectListNews", row);
            this.lookstatus = true;
            this.editstatus = false;
            let msg = '';
            if (type == 1) {
                msg = '项目详情';
            } else if (type == 2) {
                msg = '票据详情';
            } else if (type == 3) {
                msg = '合同详情';
            } else if (type == 4) {
                msg = '验收详情';
            } else if (type == 5) {
                msg = '完税详情';
            }
            this.types = type;
            this.checkdetail(code, this.types, msg);
        },
        //异常
        progressError(code, row, type) {
            this.lookstatus = false;
            this.editstatus = true;
            this.$cache.local.setJSON('projectCodeNew', code);
            this.$cache.local.setJSON('publicTickets', row);
            this.$cache.local.setJSON("projectListNews", row);
            let msg = '';
            if (type == 1) {
                msg = '项目详情';
            } else if (type == 2) {
                msg = '票据详情';
            } else if (type == 3) {
                msg = '合同详情';
            } else if (type == 4) {
                msg = '验收详情';
            } else if (type == 5) {
                msg = '完税详情';
            }
            this.types = type;
            this.checkdetail(code, this.types, msg);
        },
        //进度详情列表
        checkdetail(arr, brr, crr) {

            this.progressList = [];
            let parms = {
                projectCode: arr,
                projectType: brr
            };
            checkdetail(parms).then(res => {
                this.title = crr;
                this.dialogVisible = true;
                this.progressList = res.rows;
            });
        },
        //返回当前时间
        returnTime(time2) {
            var time = new Date(time2);
            return time.toLocaleString();
        },
        filterTime(time) {
            var date = new Date(time);
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            m = m < 10 ? "0" + m : m;
            var d = date.getDate();
            d = d < 10 ? "0" + d : d;
            var h = date.getHours();
            h = h < 10 ? "0" + h : h;
            var minute = date.getMinutes();
            minute = minute < 10 ? "0" + minute : minute;
            var s = date.getSeconds();
            s = s < 10 ? "0" + s : s;
            return y + "-" + m + "-" + d + " " + h + ":" + minute + ":" + s;
        },
     getCount() {
      getCount(this.queryParams).then(res => {
        this.errLabel = "异常(" + res.error + ")";
        this.allLabel = "全部(" + res.total + ")";
        this.loadingLabel = "办理中(" + res.unfinished + ")";
        this.finishLabel = "完成(" + res.finished + ")";
      });
    },
        /** 查询项目列表 */
        getList() {
            this.loading = true;
            if (this.projectTime != null) {//如果不选择时间，或者选择时间再将时间清除，直接点击查询，会报错，所以要判断一下，这个为时间不为空走这个。
                this.queryParams.start = this.projectTime[0]+ ' ' + '00:00:00';
                this.queryParams.end = this.projectTime[1]+ ' ' + '23:59:59';

            } else {//判断选择时间再将时间清除
                this.projectTime = null;
            };
            //    console.log( "projectTimeStart1==",this.queryParams.projectTimeStart[1]);
            list(this.queryParams).then((response) => {
                this.projectList = response.rows;
                this.total = response.total;
                this.loading = false;
                this.getCount();
            }).catch(error=>{
                this.loading = false;
            })

        },

        detail(scope) {
            console.log(scope);
            this.$cache.local.setJSON("projectListNews", scope);
            this.$cache.local.setJSON("projectCodeNew", scope.projectCode);
            this.$router.push("/project/detail");
        },



        /** 搜索按钮操作 */
        handleQuery() {
            this.queryParams.pageNum = 1;
            this.getList();
        },
        /** 重置按钮操作 */
        resetQuery() {
            this.resetForm("queryForm");
            this.endStatus='0';
            this.projectTime=null;
            this.queryParams = {
                type:1,
                pageNum: 1,
                pageSize: 10,
                selfName: null,  //乙方
                projectTimeStart: null, //开始
                projectTimeEnd: null,   //结束
                projectStatus: 0, //项目状态
                start: null, //开始
                end: null,   //结束
            }
            this.handleQuery();
        },

        // 多选框选中数据
        handleSelectionChange(selection) {
            this.ids = selection.map((item) => item.projectId);
            this.single = selection.length !== 1;
            this.multiple = !selection.length;
        },

        /** 新增按钮操作 */
        handleAdd() {
            this.$tab.closeAllPage().then(() => {
            this.$tab.openPage("项目新增", "/project/addlist")
             })
            
        },
        /** 修改按钮操作 */
        handleUpdate(row) {
            this.$cache.local.setJSON("projectCodeNew", row.projectCode);
            this.$cache.local.setJSON("iscxxiu", 0);
            this.$router.push("/project/editList");

        },

        /** 删除按钮操作 */
        handleDelete(row) {
            const projectIds = this.ids;
            this.$modal
                .confirm('是否确认删除该项目')
                .then(function () {
                    return del(projectIds);
                })
                .then((res) => {
                    if(res.code==200){
                         this.getList();
                         this.$modal.msgSuccess("删除成功");
                    }else{
                         this.$modal.msgError("项目还有发票无法删除");
                    }
                   
                })
                .catch(() => { 
                     this.$modal.msgError("项目还有发票无法删除");
                });
        },

    },
}
</script>

<style scoped>
   ::v-deep .el-message-box__content{
     height: 200px !important;
   }
   ::v-deep .el-tabs__nav-wrap::after{
        background-color:rgba(0,0,0,0) !important;
   }
</style>