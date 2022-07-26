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
      v-model="projectTime"
      value-format="yyyy-MM-dd HH:mm:ss"
      type="datetimerange"
      :picker-options="pickerOptions"
      range-separator="至"
      start-placeholder="开始日期"
      end-placeholder="结束日期"
      :default-time="['00:00:00', '23:59:59']"

      align="right">
    </el-date-picker>
              
            </el-form-item>
            <el-form-item label="项目状态">
                <el-select clearable v-model="queryParams.projectStatus" placeholder="请选择项目状态">
                    <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
                <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>
            </el-col>
            <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="projectList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="乙方" align="center" prop="selfName" :show-overflow-tooltip="true" />
            <el-table-column label="甲方" align="center" prop="purchCompany" :show-overflow-tooltip="true" />
            <el-table-column label="项目名称" align="center" prop="projectName" :show-overflow-tooltip="true" />
            <el-table-column label="项目时间" align="center" prop="createTime" width="180" />
            <el-table-column label="业务经理" align="center" prop="projectLeader" :show-overflow-tooltip="true" />
            <el-table-column label="完结状态" align="center" prop="projectStatus">
                <template slot-scope="scope">
                    <el-link :underline="false" type="danger" v-if="scope.row.projectStatus == '1'">异常</el-link>
                    <el-link :underline="false" type="success" v-if="scope.row.projectStatus == '2'">完结</el-link>
                    <el-link :underline="false" type="primary" v-if="scope.row.projectStatus == '0'">进行</el-link>
                </template>
            </el-table-column>

            <el-table-column label="项目审核" align="center" prop="projectCheckStatus">
                <template slot-scope="scope">
                    <el-link :underline="false" type="primary" @click="shenloading"
                        v-if="scope.row.projectCheckStatus == '0'">进行中</el-link>
                    <el-link :underline="false" type="danger" @click="errorsinfo(scope.row.remarkName)"
                        v-if="scope.row.projectCheckStatus == '2'">异常</el-link>
                    <el-link @click="nameisok(scope.row)" :underline="false" type="success"
                        v-if="scope.row.projectCheckStatus == '1'">完结</el-link>
                </template>
            </el-table-column>
            <el-table-column label="项目票据" align="center">

                <template slot-scope="scope">
                    <el-link :underline="false" type="primary" v-if="scope.row.projectTicketStatus == '0'"
                        @click="tickets(scope.row)">进行中</el-link>
                    <el-link :underline="false" type="danger" @click="errorsinfo(scope.row.remark)"
                        v-if="scope.row.projectTicketStatus == '2'">异常</el-link>
                    <el-link @click="newisok(scope.row)" :underline="false" type="success"
                        v-if="scope.row.projectTicketStatus == '1'">完结</el-link>

                </template>
            </el-table-column>
            <el-table-column label="项目合同" align="center">

                <template slot-scope="scope">
                   <el-link :underline="false" type="primary" @click="addContract"
                        v-if="scope.row.projectContractStatus == 0">进行中
                    </el-link>
                    <el-link :underline="false" type="danger" @click="errorsinfo(scope.row.remarkBus)"
                        v-if="scope.row.projectContractStatus == '2'">异常</el-link>
                    <el-link :underline="false" @click="newbusiness(scope.row)" type="success"
                        v-if="scope.row.projectContractStatus == '1'">完结</el-link>

                </template>
            </el-table-column>
            <el-table-column label="项目验收" align="center">

                <template slot-scope="scope">
                    <el-link :underline="false" type="primary" @click="shenloading"
                        v-if="scope.row.projectAcceptanceStatus == 0">进行中</el-link>
                    <el-link :underline="false" type="danger" @click="errorsinfo(scope.row.remarkTax)"
                        v-if="scope.row.projectAcceptanceStatus == '2'">异常</el-link>
                    <el-link :underline="false" @click="newtax(scope.row)" type="success"
                        v-if="scope.row.projectAcceptanceStatus == '1'">完结</el-link>

                </template>
            </el-table-column>
            <el-table-column label="项目完税" align="center">

                <template slot-scope="scope">
                   
                    <el-link :underline="false" type="primary" @click="shenloading"
                        v-if="scope.row.projectDutypaidStatus == '0'">进行中</el-link>
                    <el-link :underline="false" type="danger" @click="errorsinfo(scope.row.remarkBank)"
                        v-if="scope.row.projectDutypaidStatus == '2'">异常</el-link>
                    <el-link :underline="false" @click="newbank(scope.row)" type="success"
                        v-if="scope.row.projectDutypaidStatus == '1'">完结</el-link>
                </template>

            </el-table-column>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button size="mini" type="text" icon="el-icon-s-custom" @click="detail(scope.row)">详情</el-button>
                    <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">修改
                    </el-button>
                    <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)">删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize" @pagination="getList" />


    </div>

</template>

<script>
import qs from 'qs';
import { list, del } from "@/api/project/list";
export default {
    data() {
        return {
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

                pageNum: 1,
                pageSize: 10,
                projectOwner: null,  //乙方
                projectTimeStart: null, //开始
                projectTimeEnd: null,   //结束
                projectStatus: null, //项目状态
                start: null, //开始
                end: null,   //结束
            },
            projectTime: [],
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
                    label: '完结',
                },
            ],
            // 表单参数
            form: {},
            // 表单校验
            rules: {},
        };
    },
    mounted() {
        this.getList();
    },
    methods: {  
    //返回当前时间
    returnTime(time2) {
        var time = new Date(time2);
        return time.toLocaleString();
        // return time;
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

  //跳转票据列表页
        tickets(row) {
            this.$confirm("点击查看进入详情", "票据说明", {
                confirmButtonText: '查看票据',
                cancelButtonText: '关闭',
                
            }).then(() => {
                this.$cache.local.setJSON('publicTickets', row);
                this.$tab.closeOpenPage({ path:'/project/ticketlist' })
              //  this.$router.push('ticketlist');
            }).catch(() => {

            });

        },
        /** 查询项目列表 */
        getList() {
            this.loading = true;
            //  if(this.queryParams.projectTimeStart != null){//如果不选择时间，或者选择时间再将时间清除，直接点击查询，会报错，所以要判断一下，这个为时间不为空走这个。
            //     this.queryParams.projectTimeStart[0] = this.filterTime(this.queryParams.projectTimeStart[0]),
            //     this.queryParams.projectTimeStart[1] = this.filterTime(this.queryParams.projectTimeStart[1])
            // }else {//判断选择时间再将时间清除
            //         this.queryParams.projectTimeStart=null;
            // };
            if(this.projectTime!= null){//如果不选择时间，或者选择时间再将时间清除，直接点击查询，会报错，所以要判断一下，这个为时间不为空走这个。
                this.queryParams.start =this.projectTime[0];
                this.queryParams.end =this.projectTime[1];
                console.log("start",this.queryParams.start);
                console.log("end",this.queryParams.end);
            }else {//判断选择时间再将时间清除
                    this.projectTime=null;
            };
        //    console.log( "projectTimeStart1==",this.queryParams.projectTimeStart[1]);
            list(this.queryParams).then((response) => {
                this.projectList = response.rows;
                this.total = response.total;
                this.loading = false;
            });

            //mock请求
            // let params = qs.parse(this.queryParams);

            // this.$http.get('/getProjectList', {
            //     params, headers: {
            //         "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"
            //     },
            // }).then(response => {
            //     console.log(response);
            //     this.projectList = response.data.rows;
            //     this.total = response.data.total;
            //     this.loading = false;

            // }).catch(error => {
            //     this.$modal.msgError(error);
            // })
        },





        //激活休眠
        changeSwitch(scope) {
            let isActive = scope.isActive;
            console.log(isActive);


            let obj = {
                isActive: isActive,
                selfId: scope.selfId
            };
            updateEmployed(obj).then(res => {
                if (res != undefined) {
                    if (res.code === 200) {
                        if (obj.isActive == 1) {
                            this.$modal.msgSuccess("激活成功");
                        } else {
                            this.$modal.msgSuccess("休眠成功");
                        }

                        this.$nextTick(function () {
                            this.$tab.refreshPage().then(() => {
                                // this.$tab.openPage("个体列表", "manageList")
                            })

                            // this.$router.push({ path: "/company/customer/manageBank"});
                        });
                    } else {
                        this.$modal.msgError(res.msg);
                    }

                }

            }).catch(error => {
                this.$modal.msgError(error);
            });
        },
        detail(scope) {
            this.$cache.local.setJSON("projectCodeNew", scope.projectCode);
            this.$router.push("detail");
        },
        //审核中
        shenloading() {
            this.$alert("审核中,请耐心等待...", "审核说明", {
                confirmButtonText: "确定",
                callback: (action) => {
                    // this.$message({
                    //   type: 'info',
                    //   message: `action: ${ action }`
                    // });
                },
            });
        },
        addContract(){
              this.$alert("是否新增合同", "提示", {
                confirmButtonText: "新增",
                callback: (action) => {
                    this.$tab.closeOpenPage({path:'/project/contract'})
                },
            }); 
        },

        /** 搜索按钮操作 */
        handleQuery() {

            this.queryParams.pageNum = 1;
            this.getList();
        },
        /** 重置按钮操作 */
        resetQuery() {

            this.resetForm("queryForm");
            this.queryParams = {
                pageNum: 1,
                pageSize: 10,
                projectOwner: null,  //乙方
                projectTimeStart: null, //开始
                projectTimeEnd: null,   //结束
                projectStatus: null, //项目状态
            }
            this.handleQuery();
        },

        // 多选框选中数据
        handleSelectionChange(selection) {
            this.ids = selection.map((item) => item.selfId);
            this.single = selection.length !== 1;
            this.multiple = !selection.length;
        },

        /** 新增按钮操作 */
        handleAdd() {
            this.$router.push('addlist');
        },
        /** 修改按钮操作 */
        handleUpdate(row) {
            this.$cache.local.setJSON("projectCodeNew", row.projectCode);
            this.$router.push("editList");

        },

        /** 删除按钮操作 */
        handleDelete(row) {
            const projectIds = row.projectId;
            this.$modal
                .confirm('是否确认删除id为"' + projectIds + '"的数据项？')
                .then(function () {
                    return del(projectIds);
                })
                .then(() => {
                    this.getList();
                    this.$modal.msgSuccess("删除成功");
                })
                .catch(() => { });
        },

    },
}
</script>

<style>
</style>