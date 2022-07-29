<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" size="small" v-show="showSearch" label-width="auto">

            <el-row type="flex" class="row-bg rowCss combottom" style="padding-top;:20px" justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="项目编号">
                        <el-input :readonly="true" v-model="publicList.projectCode"></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="已开金额">
                        <el-input type="number" :readonly="true" style="width:100%" v-model="publicList.projectPackageAmount" :step="0.00">
                            <template slot="append">元</template>
                        </el-input>
                    </el-form-item>
                </el-col>

                <el-col :span="9">
                      <el-form-item class="comright" label="项目金额">
                        <el-input :readonly="true" v-model="publicList.projectTotalAmount">
                            <template slot="append">元</template>
                        </el-input>
                    </el-form-item>
                    
                    <el-form-item class="comright" label="剩余金额">
                        <el-input type="number" :readonly="true" style="width:100%" v-model="publicList.projectRemainAmount" :step="0.00">
                            <template slot="append">元</template>
                        </el-input>
                    </el-form-item>
                </el-col>
            </el-row>


            <el-row type="flex" class="row-bg " justify="space-around">
                <el-col :span="9">
                    <el-form-item class="comright" label="发票类型">
                        <el-select disabled style="width:100%" clearable v-model="publicList.ticketType">
                            <el-option v-for="item in ticketTypeoptions" :key="item.value" :label="item.label"
                                :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item class="comright" label="开票内容" v-if="fileNameradio == 1">
                        <el-input :readonly="true" type="textarea" :rows="2" v-model="publicList.fileName">
                        </el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="开票内容附件" v-if="fileNameradio == 2">
                        <div v-for="(item, index) in previewList" :key="index">
                            <el-image lazy :preview-src-list="previewList" style="width: 150px; height: 150px"
                                :src="item" alt="" />
                        </div>
                        <div v-for="(x, y) in pdfList" :key="y">
                            <span @click="pdfdetail(x)"> {{ x }} </span>
                        </div>
                    </el-form-item>
                </el-col>

                <el-col :span="9">
                    <el-form-item class="comright" label="发票税率">
                        <el-input v-if="publicList.ticketTax == 0" :readonly="true" value="免税"></el-input>
                        <el-input v-else value="3%" :readonly="true"></el-input>
                    </el-form-item>
                    <el-form-item class="comright" label="发票备注">
                        <el-input :readonly="true" v-model="publicList.projectDesc"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

        </el-form>

        <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
                <el-button v-if="publicList.projectRemainAmount > 0" type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd">新增
                </el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple"
                    @click="handleDelete">删除</el-button>
            </el-col>
            <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="projectList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="发票种类编号" align="center" prop="ticketTypeCode" :show-overflow-tooltip="true" />
            <el-table-column label="发票编号" align="center" prop="ticketCode" :show-overflow-tooltip="true" />
            <el-table-column label="发票时间" align="center" prop="ticketTime" width="180" />
            <el-table-column label="发票金额" align="center" prop="ticketAmount" :show-overflow-tooltip="true" />
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
        <!--PDF 预览-->
        <el-dialog :title="titles" :visible.sync="viewVisible" width="80%" center @close='closeDialog'>
            <div>
                <div class="tools flexs" style=" align-items: center;">
                    <div class="page" style="margin-right:20px;font-size: 20px;">共{{ pageNum }}/{{ pageTotalNum }}
                    </div>
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
import pdf from 'vue-pdf-signature'
import CMapReaderFactory from 'vue-pdf/src/CMapReaderFactory.js'
import { list, del } from "@/api/project/ticket";
import {detail } from "@/api/project/list";
import { Skeleton } from 'element-ui';

export default {
    components: {
        pdf
    },
    data() {
        return {
            multipleSelection: [],
            titles: '',
            pdfList: [],  //pdf 预览
            previewList: [], //预览

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



            baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
            fileNameradio: '1',
            fileName2: [],
            issuedAmount: 0.00,//已开金额
            balance: 0.00,//剩余金额
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
                projectCode: ''
            },
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
            publicList: '',
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
       // this.publicList = this.$cache.local.getJSON('publicTickets');
          detail({
                projectCode: this.$cache.local.getJSON("projectCodeNew")
            }).then((response) => {
              this.publicList=response.data[0];
              this.$cache.local.setJSON('publicTickets',this.publicList);

               this.queryParams = {
            pageNum: 1,
            pageSize: 10,
            projectCode: this.publicList.projectCode
        };
        if(this.publicList.fileName.indexOf("[") != -1 ){
           this.publicList.fileName = JSON.parse(this.publicList.fileName);
        }
       
        if (Array.isArray(this.publicList.fileName)) {
            this.fileNameradio = '2';
            //如果是图片的话
            for (let j in this.publicList.fileName) {
                if (this.publicList.fileName[j].substring(this.publicList.fileName[j].lastIndexOf('.') + 1) == 'pdf') {
                    this.pdfList.push(this.publicList.fileName[j]);
                } else {
                    this.publicList.fileName[j] = this.baseImgPath + this.publicList.fileName[j];
                    this.previewList.push(this.publicList.fileName[j]);
                }
            }

        } else {
            this.fileNameradio = '1';
        }

        this.getList();




           }); 
       
       
        
    },
    methods: {
        pdfdetail(i) {
            this.titles = '正在预览' + i;
            this.viewVisible = true;
            this.url = pdf.createLoadingTask({ url:this.baseImgPath + i,CMapReaderFactory,cMapPacked: true });
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

                            })


                        });
                    } else {
                        this.$modal.msgError(res.msg);
                    }

                }

            }).catch(error => {
                this.$modal.msgError(error);
            });
        },
        detail(row) {
            this.$cache.local.setJSON("ticketDetails",row);
            this.$router.push('ticketDetail');
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
            this.queryParams = {
                pageNum: 1,
                pageSize: 10,
                projectCode: this.publicList.projectCode
            }
            this.handleQuery();
        },

        // 多选框选中数据
        handleSelectionChange(selection) {
            this.multipleSelection = selection.map((item) => item.ticketId);;
            this.ids = selection.map((item) => item.selfId);
            this.single = selection.length !== 1;
            this.multiple = !selection.length;
        },

        /** 新增按钮操作 */
        handleAdd() {
            this.$router.push('addTicket');
        },
        /** 修改按钮操作 */
        handleUpdate(row) {
            this.$cache.local.setJSON("ticketDetails",row);
            this.$router.push("editTicketDetail");

        },

        /** 删除按钮操作 */
        handleDelete() {
             if (confirm('你确定删除吗？')) {
                del(this.multipleSelection).then((res) => {
                    if (res != undefined) {
                        if (res.code == 200) {
                            this.$message({
                                message: '删除成功',
                                type: 'success',
                            });
                        } else {
                            this.$message({
                                message: res.msg,
                                type: 'danger',
                            });
                        }
                        this.getList();
                        this.ticketByCode();
                    }
                })
            }
            
        },

    },
}
</script>

<style>
.flexs {
    display: flex;
    justify-content: center;

}
</style>