<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
            label-width="auto">
            <el-form-item label="销货单位（乙方）">
                <el-input v-model="queryParams.selfName" placeholder="请输入销货单位（乙方）" clearable
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
                   <el-tab-pane :label="addLabel" name="-1"></el-tab-pane>
                   <el-tab-pane :label="loadingLabel" name="0"></el-tab-pane>
                   <el-tab-pane :label="errLabel" name="2"></el-tab-pane>
                   <el-tab-pane :label="finishLabel" name="1"></el-tab-pane>
                   <el-tab-pane :label="allLabel" name="-3"></el-tab-pane>
                </el-tabs>
            </el-col>
            <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="projectList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="销货单位（乙方）" align="center" prop="selfName" :show-overflow-tooltip="true" />
            <el-table-column label="购货单位（甲方）" align="center" prop="purchCompany" :show-overflow-tooltip="true" />
            <el-table-column label="项目名称" align="center" prop="projectName" :show-overflow-tooltip="true" />
            <el-table-column label="项目时间" align="center"  :show-overflow-tooltip="true">
              <template slot-scope="scope"> {{ scope.row.createTime | filterTime }}</template>
            </el-table-column>
            <el-table-column label="业务经理" align="center" prop="projectLeader" :show-overflow-tooltip="true" />
            <el-table-column label="完结状态" align="center" prop="projectStatus">
                <template slot-scope="scope">
                    <el-link :underline="false" type="danger"  v-if="scope.row.projectAcceptanceStatus == 2 && scope.row.projectContractStatus==2">异常</el-link>
                    <el-link :underline="false" type="success" v-if="scope.row.projectAcceptanceStatus == 1 && scope.row.projectContractStatus==1">完成</el-link>
                    <el-link :underline="false" type="primary" v-if="scope.row.projectAcceptanceStatus == 0 && scope.row.projectContractStatus==0">审核中</el-link>
                    <el-link :underline="false" type="primary" v-if="scope.row.projectAcceptanceStatus == -1 && scope.row.projectContractStatus==-1">办理中</el-link>
                </template>
            </el-table-column> 
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button size="mini" v-if="scope.row.projectAcceptanceStatus==-1 && scope.row.projectContractStatus==-1" type="text" icon="el-icon-circle-plus-outline"
                        @click="add(scope.row)">办理</el-button>
                    <el-button size="mini" v-if="scope.row.projectAcceptanceStatus==0 && scope.row.projectContractStatus==0"    type="text" icon="el-icon-s-custom" @click="detail(scope.row)">审核
                    </el-button>
                      <el-button size="mini" v-if="scope.row.projectAcceptanceStatus==1 && scope.row.projectContractStatus==1" type="text" icon="el-icon-view" @click="find(scope.row,scope.row.projectCode)">查看</el-button>
                    <el-button size="mini" v-if="scope.row.projectAcceptanceStatus==2 && scope.row.projectContractStatus==2"  type="text" icon="el-icon-edit" @click="edit(scope.row,scope.row.projectCode)">修改
                    </el-button>
                </template>
            </el-table-column>
            <!-- v-if="scope.row.fileName2 && scope.row.projectAcceptanceStatus==0 && scope.row.fileName1 && scope.row.projectContractStatus==0" -->
        </el-table>

        <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize" @pagination="getList" />


    </div>

</template>

<script>
import moment from 'moment'
import { list, del,getCount } from "@/api/tc-api/project/list";
export default {
    name:'AuditMeansList',
    data() {
        return {
            allLabel: '全部',
            addLabel: '办理中',
            errLabel: '异常',
            loadingLabel: '办理中',
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
                type:4,
                pageNum: 1,
                pageSize: 10,
                selfName: null,  //销货单位（乙方）
                projectTimeStart: null, //开始
                projectTimeEnd: null,   //结束
                // projectCheckStatus:1, //项目状态
                projectContractStatus: 0,
                projectAcceptanceStatus:0,
                start: null, //开始
                end: null,   //结束
            },
            projectTime:null,
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
        //验收详情
        find(row,code){
           this.$cache.local.setJSON('tc-project-code', code);
          
            let obj={
                backurl:'/tc-business/project/auditAcceptanceList'
            };
            this.$cache.local.setJSON('tc-backMean',obj);
           this.$tab.closeOpenPage({ path: '/tc-business/project/auditMeansDetail' })
        },
        //验收修改
        edit(row,code){
             let obj = {
             name: 'AuditMeansList',
             url: '/tc-business/project/auditMeansList',
            };
            this.$cache.local.setJSON('tc-edit-project', obj);
            this.$cache.local.setJSON('tc-project-code', code);
           
           this.$tab.closeOpenPage({ path: '/tc-business/project/meansEdit' })
        },
          handleClick(){
            if(this.endStatus=='-3'){
              this.queryParams.projectContractStatus=null;
              this.queryParams.projectAcceptanceStatus=null;
             }else{
              this.queryParams.projectContractStatus=this.endStatus;
              this.queryParams.projectAcceptanceStatus=this.endStatus;
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
        this.addLabel='办理中('+res.bl+')';
        this.errLabel = "异常(" + res.error + ")";
        this.allLabel = "全部(" + res.total + ")";
        this.loadingLabel = "审核中(" + res.unfinished + ")";
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
           
            list(this.queryParams).then((response) => {
                this.projectList = response.rows;
                this.total = response.total;
                this.loading = false;
                this.getCount();
            }).catch(err=>{
                this.loading=false;
            })


        },
        //验收审核
        detail(scope) {
              let obj={
                backurl:'/tc-business/project/auditMeansList',
                name:'AuditMeansList'
            };
            this.$cache.local.setJSON('tc-aduitback',obj);
           
            this.$cache.local.setJSON("tc-project-code", scope.projectCode);
            this.$tab.closeOpenPage({ path: '/tc-business/project/aduitMeans' });
        },
        //资料办理
        add(scope) {
             let obj={
                backurl:'/tc-business/project/auditMeansList',
                name:'AuditMeansList'
            };
            this.$cache.local.setJSON('tc-addback',obj);
           
            this.$cache.local.setJSON("tc-project-code", scope.projectCode);
            this.$tab.closeOpenPage({ path: '/tc-business/project/addMeans' });
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
                type:4,
                pageNum: 1,
                pageSize: 10,
                selfName: null,  //销货单位（乙方）
                projectTimeStart: null, //开始
                projectTimeEnd: null,   //结束
                // projectCheckStatus:1, //项目状态
                projectContractStatus:0,
                projectAcceptanceStatus:0,
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

        
        /** 修改按钮操作 */
        handleUpdate(row) {
            this.$cache.local.setJSON("tc-project-code", row.projectCode);
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

<style scoped>
    ::v-deep .el-message-box__content{
     height: 200px !important;
   }
   ::v-deep .el-tabs__nav-wrap::after{
        background-color:rgba(0,0,0,0) !important;
   }
</style>