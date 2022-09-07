<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
            label-width="auto">
            <el-form-item label="乙方">
                <el-input v-model="queryParams.selfName" placeholder="请输入乙方" clearable
                    @keyup.enter.native="handleQuery" />
            </el-form-item>

            <el-form-item label="项目时间">
                <el-date-picker v-model="projectTime" value-format="yyyy-MM-dd" type="daterange"
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
                   <el-tab-pane :label="errLabel" name="2"></el-tab-pane>
                   <el-tab-pane :label="finishLabel" name="1"></el-tab-pane>
                   <el-tab-pane :label="allLabel" name="-1"></el-tab-pane>
                </el-tabs>
            </el-col>
            <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="projectList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="乙方" align="center" prop="selfName" :show-overflow-tooltip="true" />
            <el-table-column label="甲方" align="center" prop="purchCompany" :show-overflow-tooltip="true" />
            <el-table-column label="项目名称" align="center" prop="projectName" :show-overflow-tooltip="true" />
             <el-table-column label="项目时间" align="center"  :show-overflow-tooltip="true">
              <template slot-scope="scope"> {{ scope.row.createTime | filterTime }}</template>
            </el-table-column>
            <el-table-column label="业务经理" align="center" prop="projectLeader" :show-overflow-tooltip="true" />
            <el-table-column label="完结状态" align="center" prop="projectStatus">
                <template slot-scope="scope">
                    <el-link :underline="false" type="danger" v-if="scope.row.projectTicketStatus == '2'">异常</el-link>
                    <el-link :underline="false" type="success" v-if="scope.row.projectTicketStatus == '1'">完成</el-link>
                    <el-link :underline="false" type="primary" v-if="scope.row.projectTicketStatus == '0'">开票中</el-link>
                </template>
            </el-table-column>   
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button size="mini" v-if="scope.row.projectTicketStatus==0" type="text" icon="el-icon-circle-plus-outline"
                        @click="add(scope.row)">开票</el-button>
                    <el-button size="mini" v-if="scope.row.projectTicketStatus==1" type="text" icon="el-icon-view" @click="find(scope.row,scope.row.projectCode)">查看</el-button>
                    <el-button size="mini" v-if="scope.row.projectTicketStatus==2" type="text" icon="el-icon-edit" @click="edit(scope.row,scope.row.projectCode)">修改
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize" @pagination="getList" />


    </div>

</template>

<script>
import moment from 'moment'
import { list, del,getCount } from "@/api/project/list";
export default {
    name:'TicketAudit',
    data() {
        return {
            allLabel: '全部',
            errLabel: '异常',
            loadingLabel: '开票中',
            finishLabel: '完成',
            endStatus:'0',
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
                type:3,
                pageNum: 1,
                pageSize: 10,
                selfName: null,  //乙方
                projectTimeStart: null, //开始
                projectTimeEnd: null,   //结束
                projectTicketStatus:0,
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
                    label: '完结',
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
          //完税查看
         find(row,code){
           this.$cache.local.setJSON('projectCodeNew', code);
          
           this.$cache.local.setJSON("projectListNews", row);
            let obj={
                backurl:'/projectlist/ticketAudit'
            };
            this.$cache.local.setJSON('backTicket',obj);
            this.$tab.closeOpenPage({ path: '/projectlist/ticketlist' });
        },
        //完税修改
        edit(row,code){
            let obj1={
                backurl:'/projectlist/ticketAudit'
            };
            this.$cache.local.setJSON('backTicket',obj1);
             let obj = {
             name: 'TicketAudit',
             url: '/projectlist/ticketAudit',
            };
            this.$cache.local.setJSON('Projectedit', obj);
            this.$cache.local.setJSON('projectCodeNew', code);
            this.$cache.local.setJSON("projectListNews", row);
             this.$tab.closeOpenPage({ path: '/projectlist/ticketlist' });
        },
         handleClick(){
            if(this.endStatus=='-1'){
             this.queryParams.projectTicketStatus=null;
           }else{
              this.queryParams.projectTicketStatus=this.endStatus;
            }
              this.queryParams.pageNum=1;
              this.getList();
        },
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
               getCount() {
      getCount(this.queryParams).then(res => {
        this.errLabel = "异常(" + res.error + ")";
        this.allLabel = "全部(" + res.total + ")";
        this.loadingLabel = "开票中(" + res.unfinished + ")";
        this.finishLabel = "完成(" + res.finished + ")";
      });
    },
    
       /** 查询项目列表 */
        getList() {
            this.loading = true;
            if (this.projectTime != null) {//如果不选择时间，或者选择时间再将时间清除，直接点击查询，会报错，所以要判断一下，这个为时间不为空走这个。
                this.queryParams.start = this.projectTime[0];
                this.queryParams.end = this.projectTime[1];
               
            } else {//判断选择时间再将时间清除
                this.projectTime = null;
            };

            list(this.queryParams).then((response) => {
                this.projectList = response.rows;
                this.total = response.total;
                this.loading = false;
                this.getCount();
            }).catch(err=>{
                this.loading=false;
            })

        },
        aduit(scope) {
              let obj={
                backurl:'/projectlist/ticketAudit',
                name:'TicketAudit'
            };
            let obj1={
                backurl:'/projectlist/ticketAudit'
            };
            this.$cache.local.setJSON('backTicket',obj1);
            this.$cache.local.setJSON('aduitProjectBack',obj);
            this.$cache.local.setJSON("projectListNews", scope);
            this.$cache.local.setJSON("projectCodeNew", scope.projectCode);
            this.$tab.closeOpenPage({ path: '/projectlist/ticketlist' });
        },
        //新增完税
        add(scope) {
              let obj={
                backurl:'/projectlist/TicketAudit',
                name:'TicketAudit'
            };
            let obj1={
                backurl:'/projectlist/ticketAudit'
            };
            this.$cache.local.setJSON('backTicket',obj1);
            this.$cache.local.setJSON('addProjectBack',obj);
            this.$cache.local.setJSON("projectListNews", scope);
            this.$cache.local.setJSON("projectCodeNew", scope.projectCode);
            this.$tab.closeOpenPage({ path: '/projectlist/ticketlist' });
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
            this.projectTime = null;
            this.queryParams = {
                type:3,
                pageNum: 1,
                pageSize: 10,
                selfName: null,  //乙方
                projectTimeStart: null, //开始
                projectTimeEnd: null,   //结束
                projectTicketStatus: 0,
                start: null, //开始
                end: null,   //结束
            }
            this.handleQuery();
        },

        // 多选框选中数据
        handleSelectionChange(selection) {
            this.ids = selection.map((item) => item.selfId);
            this.single = selection.length !== 1;
            this.multiple = !selection.length;
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

<style scoped>
  ::v-deep .el-message-box__content{
     height: 200px !important;
   }
   ::v-deep .el-tabs__nav-wrap::after{
        background-color:rgba(0,0,0,0) !important;
   }
</style>