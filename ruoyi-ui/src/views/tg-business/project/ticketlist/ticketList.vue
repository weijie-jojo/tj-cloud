<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      v-show="showSearch"
      label-width="auto"
    >
      <el-row
        type="flex"
        class="row-bg rowCss combottom"
        style="padding-top;:20px"
        justify="space-around"
      >
        <el-col :span="9">
          <el-form-item class="comright" label="项目编号">
            <el-input
              :readonly="true"
              v-model="publicList.projectCode"
            ></el-input>
          </el-form-item>
          <el-form-item class="comright" label="已开金额">
            <el-input
              type="number"
              :readonly="true"
              style="width: 100%"
              v-model="publicList.projectPackageAmount"
              :step="0.0"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
        </el-col>

        <el-col :span="9">
          <el-form-item class="comright" label="项目金额">
            <el-input
              :readonly="true"
              v-model="publicList.projectTotalAmount"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
              :step="0.0"
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>

          <el-form-item class="comright" label="剩余金额">
            <el-input
              type="number"
              :readonly="true"
              style="width: 100%"
              v-model="publicList.projectRemainAmount"
              :step="0.0"
              oninput='value = (value.match(/^[0-9]+(\.[0-9]{0,5})?/g) ?? [""])[0]'
            >
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="9">
          <el-form-item class="comright" label="发票类型">
            <el-select
              disabled
              style="width: 100%"
              clearable
              v-model="publicList.ticketType"
            >
              <el-option
                v-for="item in ticketTypeoptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            class="comright"
            label="开票内容"
            v-if="fileNameradio == 1"
          >
            <el-input
              maxlength="50"
              show-word-limit
              :readonly="true"
              type="textarea"
              :rows="2"
              v-model="publicList.fileName"
            >
            </el-input>
          </el-form-item>
          <el-form-item
            class="comright"
            label="开票内容附件"
            v-if="fileNameradio == 2"
          >
            <uploadSmall
              @getfileName="getfileNameS"
              :fileName="publicList.fileName"
              :fileNameOld="fileName"
              :isDetail="isDetail"
            ></uploadSmall>
          </el-form-item>
        </el-col>

        <el-col :span="9">
          <el-form-item class="comright" label="发票税率">
            <el-input
              v-if="publicList.ticketTax == 0"
              :readonly="true"
              value="免税"
            ></el-input>
            <el-input v-else value="3%" :readonly="true"></el-input>
          </el-form-item>
          <el-form-item class="comright" label="发票备注">
            <el-input
              maxlength="50"
              show-word-limit
              :readonly="true"
              v-model="publicList.projectDesc"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          v-if="publicList.projectRemainAmount > 0"
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          >作废</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-circle-close"
          size="mini"
          @click="handleClose"
          >关闭</el-button
        >
      </el-col>

      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="projectList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        label="发票种类编号"
        align="center"
        prop="ticketTypeCode"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="发票编号"
        align="center"
        prop="ticketCode"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="发票时间"
        align="center"
        prop="ticketTime"
        width="180"
      />
      <el-table-column
        label="发票金额"
        align="center"
        prop="ticketAmount"
        :show-overflow-tooltip="true"
      >
        <template slot-scope="scope">
          <div>{{ numberToCurrencyNo(scope.row.ticketAmount) }}</div>
        </template>
      </el-table-column>
      <el-table-column
        label="状态"
        align="center"
        :show-overflow-tooltip="true"
      >
        <template slot-scope="scope">
          <el-link
            :underline="false"
            type="success"
            v-if="scope.row.isDeleted == 1"
            >正常</el-link
          >
          <el-link
            :underline="false"
            type="success"
            v-if="scope.row.isDeleted == 2"
            >审核中</el-link
          >
          <el-link
            :underline="false"
            type="success"
            v-if="scope.row.isDeleted == 3"
            >未通过</el-link
          >
          <el-link
            :underline="false"
            type="danger"
            v-if="scope.row.isDeleted == 0"
            >作废</el-link
          >
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-s-custom"
            @click="detail(scope.row)"
            >查看</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-s-custom"
            v-if="scope.row.isDeleted == 2"
            @click="aduit(scope.row)"
            >审核</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            v-if="scope.row.isDeleted == 3"
            @click="handleUpdate(scope.row)"
            >修改
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import { numberToCurrencyNo } from "@/utils/numberToCurrency";
import uploadSmall from "@/components/douploads/uploadSmall";
import { list, del, list2 } from "@/api/tg-api/project/ticket";
import { detail, edit } from "@/api/tg-api/project/list";
import { Decimal } from "decimal.js";
export default {
  name: "TicketList",
  components: {
    uploadSmall,
  },
  data() {
    return {
      numberToCurrencyNo,
      multipleSelection: [],
      isDetail: "1",
      fileName: [],
      baseImgPath: "/eladmin/api/files/showTxt?imgPath=",
      fileNameradio: "1",
      fileName2: [],
      issuedAmount: 0.0, //已开金额
      balance: 0.0, //剩余金额
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
        projectCode: "",
      },
      ticketTypeoptions: [
        {
          value: 0,
          label: "增值税普通发票",
        },
        {
          value: 1,
          label: "增值税专用发票",
        },
      ],
      publicList: "",

      pickerOptions: {
        shortcuts: [
          {
            text: "最近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近三个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            },
          },
        ],
      },
      options: [
        {
          value: 0,
          label: "进行",
        },
        {
          value: 1,
          label: "异常",
        },
        {
          value: 2,
          label: "完结",
        },
      ],
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
    };
  },
  // filters: {
  //     dataFormat(newVal) {
  //         let that=this;
  //         return newVal = that.textFormat(newVal, '#,##0');

  //     }
  // },
  mounted() {
    detail({
      projectCode: this.$cache.local.getJSON("tg-project-code"),
    }).then((response) => {
      this.publicList = response.data;
      if (this.publicList.projectPackageAmount == 0) {
        this.publicList.projectRemainAmount =
          this.publicList.projectTotalAmount;
      }
      this.queryParams = {
        pageNum: 1,
        pageSize: 10,
        projectCode: this.publicList.projectCode,
      };
      if (this.publicList.fileName.indexOf("[") != -1) {
        this.publicList.fileName = JSON.parse(this.publicList.fileName);
      }

      if (Array.isArray(this.publicList.fileName)) {
        this.fileName = [];
        this.fileNameradio = "2";
        //如果是图片的话
        for (let j in this.publicList.fileName) {
          this.fileName.push({
            name: this.publicList.fileName[j],
            url: this.baseImgPath + this.publicList.fileName[j],
          });
        }
      } else {
        this.fileNameradio = "1";
      }
      this.getList();
      this.ticketByCode();
    });
  },
  methods: {
    aduit(row) {
      this.$cache.local.setJSON("tg-ticketid", row.ticketId);
      this.$tab.closeOpenPage({ path: "/tg-business/project/examTicket" });
    },
    //关闭
    handleClose() {
      this.$tab.closeOpenPage({
        path: this.$cache.local.getJSON("tg-backTicket").backurl,
      });
    },
    ticketByCode() {
      list2({
        projectCode: this.publicList.projectCode,
      })
        .then((res) => {
          let arr = res;
          if (Array.isArray(arr) && arr.length > 0) {
            this.publicList.projectPackageAmount = 0;
            for (let i in arr) {
              if (arr[i].ticketAmount > 0 && arr[i].isDeleted == 1) {
                this.publicList.projectPackageAmount = new Decimal(
                  this.publicList.projectPackageAmount
                ).add(new Decimal(arr[i].ticketAmount));
              }
            }
            arr.map((item) => {
              if (item.isDeleted == 3) {
                return (this.publicList.projectStatus = 1);
              } else {
                if (
                  this.publicList.projectReceiveStatus == 1 &&
                  this.publicList.projectPayStatus == 1 &&
                  this.publicList.projectDutypaidStatus == 1 &&
                  this.publicList.projectAcceptanceStatus == 1 &&
                  this.publicList.projectContractStatus == 1 &&
                  this.publicList.projectCheckStatus == 1
                ) {
                  this.publicList.projectStatus = 2;
                } else {
                  this.publicList.projectStatus = 0;
                }
              }
            });
            //如果存在发票 累计发票 加上发票金额

            this.publicList.projectRemainAmount = new Decimal(
              this.publicList.projectTotalAmount
            ).sub(new Decimal(this.publicList.projectPackageAmount));
          }
          let params = {
            projectOwner:this.publicList.projectOwner,
            isSelfCount: this.publicList.isSelfCount,
            projectCode: this.publicList.projectCode,
            projectStatus: this.publicList.projectStatus,
            projectId: this.publicList.projectId,
            projectTotalAmount: this.publicList.projectTotalAmount,
            projectRemainAmount: this.publicList.projectRemainAmount,
            projectPackageAmount: this.publicList.projectPackageAmount,
          };
          edit(params);
        })
        .catch((err) => {});
    },
    getfileNameS() {},
    /** 查询列表 */
    getList() {
      this.loading = true;

      list(this.queryParams)
        .then((response) => {
          this.projectList = response.rows;
          this.total = response.total;
          this.loading = false;
        })
        .catch((error) => {
          this.loading = false;
        });
    },
    detail(row) {
      this.$cache.local.setJSON("tg-ticketid", row.ticketId);
      this.$tab.openPage("票据查看", "/tg-business/project/ticketDetail");
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
        projectCode: this.publicList.projectCode,
      };
      this.handleQuery();
    },

    // 多选框选中数据
    handleSelectionChange(selection) {
      this.multipleSelection = selection.map((item) => item.ticketId);
      this.ids = selection.map((item) => item.selfId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },

    /** 新增按钮操作 */
    handleAdd() {
      this.$tab.closeOpenPage({ path: "/tg-business/project/ticketAdd" });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.$cache.local.setJSON("tg-ticketid", row.ticketId);
      this.$tab.closeOpenPage({ path: "/tg-business/project/ticketEdit" });
    },

    /** 删除按钮操作 */
    handleDelete() {
      this.$modal
        .confirm("是否确认作废发票?")
        .then(function () {
          return del(this.multipleSelection);
        })
        .then((res) => {
          if (res.code == 200) {
            this.getList();
            this.ticketByCode();
            this.$modal.msgSuccess("作废成功");
          } else {
            this.$alert(res.msg, "提示", {
              confirmButtonText: "确定",
            });
          }
        })
        .catch(() => {});
    },
  },
};
</script>

<style>
.flexs {
  display: flex;
  justify-content: center;
}
</style>