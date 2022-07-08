<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
            label-width="auto">

           <el-row type="flex" class="row-bg rowCss combottom" style="padding-top;:20px" justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="项目编号">
                       <el-input ></el-input>
                    </el-form-item>

                    <el-form-item class="comright" label="项目金额" prop="projectName">
                      <el-input ></el-input>
                    </el-form-item>
                </el-col>

                <el-col :span="9">

                    <el-form-item class="comright" label="已开金额" prop="createTime">
                      <el-input ></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="剩余金额" prop="projectTotalAmount">
                        <el-input ></el-input>
                    </el-form-item>
                </el-col>
            </el-row>


            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="发票类型" prop="">
                       <el-input ></el-input>
                    </el-form-item>

                    <el-form-item class="comright" label="发票税率" prop="purchCompany">
                        <!-- <el-select  clearable v-model="formData.purchCompany">
                          <el-option v-for="item in purchCompanyOptions" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                        </el-select> -->
                       <el-input ></el-input>
                    </el-form-item>
                </el-col>

                <el-col :span="9">

                    <el-form-item class="comright" label="开票内容" prop="isokradio">
                         <el-input ></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="发票备注">
                        <el-input ></el-input>
                    </el-form-item>
                </el-col>
            </el-row>








            <!-- <el-form-item label="乙方">
                <el-input v-model="queryParams.projectOwner" placeholder="请输入乙方" clearable
                    @keyup.enter.native="handleQuery" />
            </el-form-item>
            <el-form-item label="项目开始时间">
              
                <el-date-picker value-format="yyyy-MM-dd HH:mm:ss" v-model="queryParams.projectTimeStart" type="datetime" placeholder="选择日期时间"></el-date-picker>
            </el-form-item>
             <el-form-item label="项目结束时间">
                 <el-date-picker value-format="yyyy-MM-dd HH:mm:ss" v-model="queryParams.projectTimeEnd" type="datetime" placeholder="选择日期时间"></el-date-picker>
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
            </el-form-item> -->
        </el-form>

        <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
                <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>
            </el-col>
            <!-- <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['company:employed:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['company:employed:edit']">修改</el-button>
      </el-col> -->
            <!-- <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['company:employed:remove']">删除</el-button>
      </el-col> -->
            <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="projectList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="发票种类编号" align="center" prop="projectCode" :show-overflow-tooltip="true" />
            <el-table-column label="发票编号" align="center" prop="ticketCode" :show-overflow-tooltip="true" />
            <el-table-column label="发票时间" align="center" prop="ticketTime" width="180" />
            <el-table-column label="发票金额" align="center" prop="ticketAmount" :show-overflow-tooltip="true" />
        </el-table>

        <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize" @pagination="getList" />


    </div>

</template>

<script>
import qs from 'qs';
import { list,del} from "@/api/project/ticket";
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
                projectTimeStart:null, //开始
                projectTimeEnd:null,   //结束
                projectStatus: null, //项目状态
            },
            projectTime:[],
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

        /** 查询项目列表 */
        getList() {
            this.loading = true;
           
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

                            // this.$router.push({ path: "/customer/manageBank"});
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
            this.$cache.local.setJSON("projectListInfo", scope);
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

        /** 搜索按钮操作 */
        handleQuery() {
          
            this.queryParams.pageNum = 1;
            this.getList();
        },
        /** 重置按钮操作 */
        resetQuery() {
            
            this.resetForm("queryForm");
            this.queryParams={
                pageNum: 1,
                pageSize: 10,
                projectOwner: null,  //乙方
                projectTimeStart:null, //开始
                projectTimeEnd:null,   //结束
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
        //工商管理
        business(row) {
            this.$cache.local.setJSON("employednewlist", row);
            this.$router.push("addBusiness");
        },
        //税务管理
        atx(row) {
            if (row.businessStatus == 0) {
                this.$modal.msgError("请办理工商管理,才能继续办理税务管理");
            } else {
                this.$cache.local.setJSON("employednewlist", row);
                this.$router.push("addTax");
            }
        },
        bank(row) {
            if (row.taxStatus == 0) {
                this.$modal.msgError("请办理税务管理,才能继续办理银行管理");
            } else {
                this.$cache.local.setJSON("employednewlist", row);
                this.$router.push("addBank");
            }
        },
        /** 新增按钮操作 */
        handleAdd() {
           this.$router.push('addTicket');
        },
        /** 修改按钮操作 */
        handleUpdate(row) {
            this.$cache.local.setJSON("projecteditInfo", row);
            this.$router.push("editList");

        },

        /** 删除按钮操作 */
        handleDelete(row) {
            const ticketIds = row.projectId;
            this.$modal
                .confirm('是否确认删除个体商户编号为"' + ticketIds + '"的数据项？')
                .then(function () {
                    return del(ticketIds);
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