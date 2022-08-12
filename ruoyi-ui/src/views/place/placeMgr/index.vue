<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="auto">
      <el-form-item label="渠道商名称" prop="placeName">
        <el-input v-model="queryParams.placeName" placeholder="请输入渠道商名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select clearable v-model="queryParams.status" placeholder="请选择">
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
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete">删除
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="employedList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="渠道商名称" align="center" prop="placeName" :show-overflow-tooltip="true" />
      <el-table-column label="渠道商全名" align="center" prop="placeAliasName" :show-overflow-tooltip="true" />
      <el-table-column label="联系人" align="center" prop="placeLinkman" :show-overflow-tooltip="true" />
      <el-table-column label="联系方式" prop="placeTel" :show-overflow-tooltip="true" />
      <el-table-column form-item label="业务经理" prop="userName" :show-overflow-tooltip="true" />
      <!-- <el-table-column label="状态" prop="dictLabel" align="center" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-s-custom" @click="detail(scope.row)">详情</el-button>


          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">编辑</el-button>

          <!-- <el-button size="mini" type="text" v-if="scope.row.placeStatus == 0" @click="isDormancy(scope.row, 2)">
            休眠
          </el-button>
          <el-button size="mini" type="text" v-if="scope.row.placeStatus == 2" @click="isDormancy(scope.row, 0)">
            激活
          </el-button>
          <el-button size="mini" type="text" disabled v-if="scope.row.placeStatus == 1"
            @click="isDormancy(scope.row, 1)">
            禁用
          </el-button> -->
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加-->
    <el-dialog :close-on-press-escape="false" :close-on-click-modal="false"
      style="margin-top:1vh !important;margin-bottom: 1vh !important;" :title="title" :visible.sync="addVisible"
      width="50%" append-to-body>
      <el-form ref="form" :model="ruleForm" :rules="rules" label-width="auto">
        <!-- 卡1 -->
        <el-card class="box-card" id="form1">
          <div slot="header" class="clearfix">
            <span>渠道商信息</span>
          </div>
          <el-form-item label="渠道商编码" prop="placeCode">
            <el-input v-model="ruleForm.placeCode" :readonly="true" />
          </el-form-item>
          <el-form-item label="渠道商名称" prop="placeName">
            <el-input v-model="ruleForm.placeName" />
          </el-form-item>
          <el-form-item label="渠道商别名" prop="placeAlias">
            <el-input v-model='ruleForm.placeAlias' />
          </el-form-item>
          <el-form-item label="联系人" prop="placeLinkman">
            <el-input v-model="ruleForm.placeLinkman" />
          </el-form-item>
          <el-form-item label="联系方式" prop="placeTel">
            <el-input v-model="ruleForm.placeTel" />
          </el-form-item>
          <el-form-item label="业务经理" :required="true">
            <el-input v-model="ruleForm.userName" :readonly="true"></el-input>
            <!-- {{ruleForm.userName}} -->
            <!-- <el-select v-model="ruleForm.userId" placeholder="业务经理" class="filter-item" @change="selectUser" style="">
              <el-option v-for="item in userLeaders" :key="item.userId" :label="item.nickName" :value="item.userId" />
            </el-select> -->
          </el-form-item>
          <el-row type="flex" justify="flex-end">

            <el-col :span="24">
              <el-form-item label="个体户注册服务费" prop="ordinarySelfFee">
                <el-input type="number" v-model="ruleForm.ordinarySelfFee" @change="handleChange" :step="0.1" :min="0"
                  :max="9999" style="width:100%">
                  <template slot="append">元</template>
                </el-input>
              </el-form-item>
            </el-col>

          </el-row>
        </el-card>
        <!-- 卡2 -->
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>增值税普通发票</span>
          </div>
          <el-row type="flex" justify="flex-end">
            <el-col :span="24">
              <el-form-item label="普票税率" :required="true">
                <el-select style="width:100%" v-model="ruleForm.ordinaryTax" clearable placeholder="请选择">
                  <el-option v-for="item in option" :key="item.value" :label="item.label" :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row type="flex" justify="flex-end">
            <el-col :span="24">
              <el-form-item label="普票服务费" prop="ordinaryProxyFee">

                <div>
                  <el-radio @change="handPoxy" v-model="ruleForm.ordinaryProxyIsmoney" label="0">按定额收取</el-radio>
                  <el-radio @change="handPoxy" v-model="ruleForm.ordinaryProxyIsmoney" label="1">按百分比收取</el-radio>

                  <el-input v-if="ruleForm.ordinaryProxyIsmoney == 0" style="width:100%" type="number"
                    v-model="ruleForm.ordinaryProxyFee" :step="0.01" :min="0">
                    <template slot="append">元</template>
                  </el-input>
                  <el-input v-else type="number" style="width:100%" v-model="ruleForm.ordinaryProxyFee"
                    @input="handleordinaryProxyFee" @change="handleordinaryProxyFee" :step="0.01" :max="100">
                    <template slot="append">%</template>
                  </el-input>
                </div>
              </el-form-item>
            </el-col>

          </el-row>

          <el-form-item label="服务费含税" prop="isOrdinaryTax">
            <el-radio v-model="ruleForm.isOrdinaryTax" label='0'>是</el-radio>
            <el-radio v-model="ruleForm.isOrdinaryTax" label='1'>否</el-radio>
          </el-form-item>

          <el-form-item label="价税分离" prop="isSelfTax">
            <el-radio v-model="ruleForm.isSelfTax" label='0'>是</el-radio>
            <el-radio v-model="ruleForm.isSelfTax" label='1'>否</el-radio>
          </el-form-item>
          <el-form-item label="是否分润" :required="true">
            <el-radio v-model="ruleForm.isOrdinaryShare" label="0">是</el-radio>
            <el-radio v-model="ruleForm.isOrdinaryShare" label="1">否</el-radio>
          </el-form-item>
          <el-row v-if="ruleForm.isOrdinaryShare == 0" type="flex" justify="flex-end">
            <el-col :span="24">
              <el-form-item label="分润方式" prop="ordinaryShare">
                <div style="">
                  <el-radio @change="hanOrshare" v-model="ruleForm.ordinaryShareIsmoney" label="0">按定额收取</el-radio>
                  <el-radio @change="hanOrshare" v-model="ruleForm.ordinaryShareIsmoney" label="1">按百分比收取</el-radio>

                  <el-input v-if="ruleForm.ordinaryShareIsmoney == 0" style="width:100%" type="number" :step="0.01"
                    :min="0" v-model="ruleForm.ordinaryShare">
                    <template slot="append">元</template>
                  </el-input>
                  <el-input v-model="ruleForm.ordinaryShare" v-else type="number" style="width:100%"
                    @input="handleordinaryShareIsmoney" @change="handleordinaryShareIsmoney" :step="0.01" :min="0"
                    :max="100">
                    <template slot="append">%</template>
                  </el-input>
                </div>

              </el-form-item>
            </el-col>
          </el-row>




        </el-card>
        <!-- 卡3 -->
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>增值税专用发票</span>
          </div>
          <el-row type="flex" justify="flex-end">
            <el-col :span="24">
              <el-form-item label="专票税率" :required="true">
                <el-select style="width:100%" v-model="ruleForm.ordinarySpecialTax" clearable placeholder="请选择">
                  <el-option v-for="item in optiond" :key="item.value" :label="item.label" :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row type="flex" justify="flex-end">
            <el-col :span="24">
              <el-form-item label="专票服务费" prop="specialProxyFee">
                <div>
                  <el-radio @change="handSpecial" v-model="ruleForm.specialProxyIsmoney" label="0">按定额收取</el-radio>
                  <el-radio @change="handSpecial" v-model="ruleForm.specialProxyIsmoney" label="1">按百分比收取</el-radio>

                  <el-input v-if="ruleForm.specialProxyIsmoney == 0" style="width:100%" type="number"
                    v-model="ruleForm.specialProxyFee" :step="0.01" :min="0">
                    <template slot="append">元</template>
                  </el-input>
                  <el-input v-else type="number" style="width:100%" v-model="ruleForm.specialProxyFee"
                    @input="handlespecialProxyIsmoney" @change="handlespecialProxyIsmoney" :step="0.01" :min="0"
                    :max="100">
                    <template slot="append">%</template>
                  </el-input>
                </div>
              </el-form-item>
            </el-col>

          </el-row>
          <el-form-item label="服务费含税" prop="isSpecialTax">
            <el-radio v-model="ruleForm.isSpecialTax" label='0'>是</el-radio>
            <el-radio v-model="ruleForm.isSpecialTax" label='1'>否</el-radio>
          </el-form-item>
          <el-form-item label="价税分离" prop="isSpecialSelfTax">
            <el-radio v-model="ruleForm.isSpecialSelfTax" label='0'>是</el-radio>
            <el-radio v-model="ruleForm.isSpecialSelfTax" label='1'>否</el-radio>
          </el-form-item>
          <el-form-item label="是否分润">
            <el-radio v-model="ruleForm.isSpecialShare" label="0">是</el-radio>
            <el-radio v-model="ruleForm.isSpecialShare" label="1">否</el-radio>
          </el-form-item>
          <el-row v-if="ruleForm.isSpecialShare == 0" type="flex" justify="flex-end">
            <el-col :span="24">
              <el-form-item label="分润方式" prop="specialShare">
                <div style="">
                  <el-radio @change="handMoney" v-model="ruleForm.specialShareIsmoney" label="0">按定额收取</el-radio>
                  <el-radio @change="handMoney" v-model="ruleForm.specialShareIsmoney" label="1">按百分比收取</el-radio>

                  <el-input v-if="ruleForm.specialShareIsmoney == 0" style="width:100%" type="number" :step="0.01"
                    :min="0" v-model="ruleForm.specialShare">
                    <template slot="append">元</template>
                  </el-input>
                  <el-input v-model="ruleForm.specialShare" v-else type="number" style="width:100%"
                    @input="handlespecialShareIsmoney" @change="handlespecialShareIsmoney" :step="0.01" :min="0"
                    :max="100">
                    <template slot="append">%</template>
                  </el-input>
                </div>

              </el-form-item>
            </el-col>
          </el-row>

        </el-card>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="cancelS(1)">关闭</el-button>
      </div>
    </el-dialog>



    <!-- 编辑-->
    <el-dialog :close-on-press-escape="false" :close-on-click-modal="false"
      style="margin-top:1vh !important;margin-bottom: 1vh !important;" :title="titles" :visible.sync="editVisible"
      width="50%" append-to-body>
      <el-form ref="form" :model="ruleForm" :rules="rules" label-width="auto">
        <!-- 卡1 -->
        <el-card class="box-card" id="form1">
          <div slot="header" class="clearfix">
            <span>渠道商信息</span>
          </div>
          <el-form-item label="渠道商编码" prop="editPlaceCode">
            <el-input v-model="ruleForm.editPlaceCode" :readonly="true" />
          </el-form-item>
          <el-form-item label="渠道商名称" prop="editPlaceName">
            <el-input v-model="ruleForm.editPlaceName" :readonly="confirmEditStatus" />
          </el-form-item>
          <el-form-item label="渠道商别名" prop="editPlaceAlias">
            <el-input v-model="ruleForm.editPlaceAlias" :readonly="confirmEditStatus" />
          </el-form-item>
          <el-form-item label="联系人" prop="editPlaceLinkman">
            <el-input v-model="ruleForm.editPlaceLinkman" :readonly="confirmEditStatus" />
          </el-form-item>
          <el-form-item label="联系方式" prop="editPlaceTel">
            <el-input v-model="ruleForm.editPlaceTel" :readonly="confirmEditStatus" />
          </el-form-item>
          <el-form-item label="业务经理" prop="editUserId" :required="true">
            <el-input v-model="ruleForm.userName" :readonly="true"></el-input>
          </el-form-item>
          <el-row type="flex" justify="flex-end">

            <el-col :span="24">
              <el-form-item label="个体户注册服务费" prop="editOrdinarySelfFee">
                <el-input type="number" :readonly="confirmEditStatus" v-model="ruleForm.editOrdinarySelfFee"
                  @change="handleChange" :step="0.1" :min="0" :max="9999" style="width:100%">
                  <template slot="append">元</template>
                </el-input>
              </el-form-item>
            </el-col>

          </el-row>
        </el-card>
        <!-- 卡2 -->


        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>增值税普通发票</span>
          </div>
          <el-row type="flex" justify="flex-end">
            <el-col :span="24">
              <el-form-item label="普票税率" prop="editOrdinaryTax">
                <el-select style="width:100%" :disabled="confirmEditStatus" v-model="ruleForm.editOrdinaryTax" clearable
                  placeholder="请选择">
                  <el-option v-for="item in option" :key="item.value" :label="item.label" :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row type="flex" justify="flex-end">
            <el-col :span="24">
              <el-form-item label="普票服务费" prop="editOrdinaryProxyFee">
                <div style="">
                  <el-radio @change="handPoxyS" :disabled="confirmEditStatus" v-model="ruleForm.editOrdinaryProxyIsmoney" label="0">按定额收取
                  </el-radio>
                  <el-radio @change="handPoxyS" :disabled="confirmEditStatus" v-model="ruleForm.editOrdinaryProxyIsmoney" label="1">按百分比收取
                  </el-radio>

                  <el-input v-if="ruleForm.editOrdinaryProxyIsmoney == 0" style="width:100%"
                    :readonly="confirmEditStatus" type="number" v-model="ruleForm.editOrdinaryProxyFee" :step="0.01"
                    :min="0">
                    <template slot="append">元</template>
                  </el-input>
                  <el-input :readonly="confirmEditStatus" v-else type="number" style="width:100%"
                    v-model="ruleForm.editOrdinaryProxyFee" 
                    @input="handleordinaryProxyFeeS"
                    @change="handleordinaryProxyFeeS" :step="0.01" :min="0" :max="100">
                    <template slot="append">%</template>
                  </el-input>
                </div>
              </el-form-item>
            </el-col>

          </el-row>
          <el-form-item label="服务费含税" prop="editIsOrdinaryTax">
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsOrdinaryTax" label='0'>是</el-radio>
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsOrdinaryTax" label='1'>否</el-radio>
          </el-form-item>
          <el-form-item label="价税分离" prop="editIsSelfTax">
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSelfTax" label='0'>是</el-radio>
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSelfTax" label='1'>否</el-radio>
          </el-form-item>
          <el-form-item label="是否分润" :required="true">
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsOrdinaryShare" label="0">是</el-radio>
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsOrdinaryShare" label="1">否</el-radio>
          </el-form-item>
          <el-row v-if="ruleForm.editIsOrdinaryShare == 0" type="flex" justify="flex-end">
            <el-col :span="24">
              <el-form-item label="分润方式" prop="editOrdinaryShare">
                <div style="">
                  <el-radio @change="hanOrshareS" :disabled="confirmEditStatus" v-model="ruleForm.editOrdinaryShareIsmoney" label="0">按定额收取
                  </el-radio>
                  <el-radio @change="hanOrshareS" :disabled="confirmEditStatus" v-model="ruleForm.editOrdinaryShareIsmoney" label="1">按百分比收取
                  </el-radio>

                  <el-input v-if="ruleForm.editOrdinaryShareIsmoney == 0" style="width:100%" type="number"
                    :readonly="confirmEditStatus" :step="0.01" :min="0" v-model="ruleForm.editOrdinaryShare">
                    <template slot="append">元</template>
                  </el-input>
                  <el-input v-else :readonly="confirmEditStatus" v-model="ruleForm.editOrdinaryShare" type="number"
                    style="width:100%"
                    @input="handleordinaryShareIsmoneyS"
                    @change="handleordinaryShareIsmoneyS" :step="0.01" :min="0" :max="100">
                    <template slot="append">%</template>
                  </el-input>
                </div>

              </el-form-item>
            </el-col>
          </el-row>



        </el-card>
        <!-- 卡3 -->
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>增值税专用发票</span>
          </div>
          <el-row type="flex" justify="flex-end">
            <el-col :span="24">
              <el-form-item label="专票税率" prop="editOrdinarySpecialTax">
                <el-select style="width:100%" :disabled="confirmEditStatus" v-model="ruleForm.editOrdinarySpecialTax"
                  clearable placeholder="请选择">
                  <el-option v-for="item in optiond" :key="item.value" :label="item.label" :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row type="flex" justify="flex-end">
            <el-col :span="24">
              <el-form-item label="专票服务费" prop="editSpecialProxyFee">

                <div style="">
                  <el-radio @change="handSpecialS" :disabled="confirmEditStatus" v-model="ruleForm.editSpecialProxyIsmoney" label="0">按定额收取
                  </el-radio>
                  <el-radio @change="handSpecialS" :disabled="confirmEditStatus" v-model="ruleForm.editSpecialProxyIsmoney" label="1">按百分比收取
                  </el-radio>

                  <el-input v-if="ruleForm.editSpecialProxyIsmoney == 0" style="width:100%"
                    :readonly="confirmEditStatus" type="number" v-model="ruleForm.editSpecialProxyFee" :step="0.01"
                    :min="0">
                    <template slot="append">元</template>
                  </el-input>
                  <el-input :readonly="confirmEditStatus" v-else type="number" style="width:100%"
                    v-model="ruleForm.editSpecialProxyFee"
                    @input="handlespecialProxyIsmoneyS"
                    @change="handlespecialProxyIsmoneyS" :step="0.01" :min="0"
                    :max="100">
                    <template slot="append">%</template>
                  </el-input>
                </div>
              </el-form-item>
            </el-col>

          </el-row>

          <el-form-item label="服务费含税" prop="editIsSpecialTax">
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSpecialTax" label='0'>是</el-radio>
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSpecialTax" label='1'>否</el-radio>
          </el-form-item>
          <el-form-item label="价税分离" prop="editIsSpecialSelfTax">
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSpecialSelfTax" label='0'>是</el-radio>
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSpecialSelfTax" label='1'>否</el-radio>
          </el-form-item>
          <el-form-item label="是否分润" :required="true">
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSpecialShare" label="0">是</el-radio>
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSpecialShare" label="1">否</el-radio>
          </el-form-item>

          <el-row v-if="ruleForm.editIsSpecialShare == 0" type="flex" justify="flex-end">
            <el-col :span="24">
              <el-form-item label="分润方式" prop="editSpecialShare">
                <div style="">
                  <el-radio @change="handMoneyS" :disabled="confirmEditStatus" v-model="ruleForm.editSpecialShareIsmoney" label="0">按定额收取
                  </el-radio>
                  <el-radio @change="handMoneyS" :disabled="confirmEditStatus" v-model="ruleForm.editSpecialShareIsmoney" label="1">按百分比收取
                  </el-radio>

                  <el-input v-if="ruleForm.editSpecialShareIsmoney == 0" style="width:100%" type="number" :step="0.01"
                    :min="0" v-model="ruleForm.editSpecialShare" :readonly="confirmEditStatus">
                    <template slot="append">元</template>
                  </el-input>
                  <el-input v-else v-model="ruleForm.editSpecialShare" type="number" style="width:100%"
                    :readonly="confirmEditStatus"
                    @input="handlespecialShareIsmoneyS"
                    @change="handlespecialShareIsmoneyS" :step="0.01" :min="0" :max="100">
                    <template slot="append">%</template>
                  </el-input>
                </div>

              </el-form-item>
            </el-col>
          </el-row>





        </el-card>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="confirmEdits" v-if="confirmEditStatus">编辑</el-button>
        <el-button type="primary" @click="confirmEdit" v-else>提交</el-button>
        <el-button @click="cancelS(2)">关闭</el-button>
      </div>
    </el-dialog>



    <!-- 详情-->
    <el-dialog :close-on-press-escape="false" :close-on-click-modal="false"
      style="margin-top:1vh !important;margin-bottom: 1vh !important;" :title="titleh" :visible.sync="checkVisible"
      width="50%" append-to-body>
      <el-form ref="form" :model="ruleForm" :rules="ruleForm" label-width="auto">
        <!-- 卡1 -->
        <el-card class="box-card" id="form1">
          <div slot="header" class="clearfix">
            <span>渠道商信息</span>
          </div>
          <el-form-item label="渠道商编码" prop="placeCode">
            <el-input v-model="ruleForm.placeCode" :readonly="true" />
          </el-form-item>
          <el-form-item label="渠道商名称" prop="placeName">
            <el-input :readonly="true" v-model="ruleForm.placeName" />
          </el-form-item>
          <el-form-item label="渠道商别名">
            <el-input :readonly="true" v-model="ruleForm.placeAlias" />
          </el-form-item>
          <el-form-item label="联系人" prop="placeLinkman">
            <el-input :readonly="true" v-model="ruleForm.placeLinkman" />
          </el-form-item>
          <el-form-item label="联系方式" prop="placeTel">
            <el-input :readonly="true" v-model="ruleForm.placeTel" />
          </el-form-item>
          <el-form-item label="业务经理" :required="true">
            <el-input v-model="ruleForm.userName" :readonly="true"></el-input>
          </el-form-item>
          <el-row type="flex" justify="flex-end">

            <el-col :span="24">
              <el-form-item label="个体户注册服务费" prop="ordinarySelfFee">
                <el-input :readonly="true" type="number" v-model="ruleForm.ordinarySelfFee" @change="handleChange"
                  :step="0.1" :precision="1" :min="0" :max="9999" style="width:100%">
                  <template slot="append">元</template>
                </el-input>
              </el-form-item>
            </el-col>

          </el-row>
        </el-card>
        <!-- 卡2 -->
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>增值税普通发票</span>
          </div>
          <el-row type="flex" justify="flex-end">
            <el-col :span="24">
              <el-form-item label="普票税率" :required="true">
                <el-select style="width:100%" disabled v-model="ruleForm.ordinaryTax" clearable placeholder="请选择">
                  <el-option v-for="item in option" :key="item.value" :label="item.label" :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row type="flex" justify="flex-end">
            <el-col :span="24">
              <el-form-item label="普票服务费" :required="true">

                <div style="">
                  <el-radio disabled v-model="ruleForm.ordinaryProxyIsmoney" label="0">按定额收取</el-radio>
                  <el-radio disabled v-model="ruleForm.ordinaryProxyIsmoney" label="1">按比例收取</el-radio>

                  <el-input v-if="ruleForm.ordinaryProxyIsmoney == 0" style="width:100%" :readonly="true" type="number"
                    v-model="ruleForm.ordinaryProxyFee" @change="handleChange" :step="0.01" :min="0">
                    <template slot="append">元</template>
                  </el-input>
                  <el-input :readonly="true" v-else type="number" style="width:100%" v-model="ruleForm.ordinaryProxyFee"
                    @change="handleChange" :step="0.01" :min="0" :max="13">
                    <template slot="append">%</template>
                  </el-input>
                </div>
              </el-form-item>
            </el-col>

          </el-row>


          <el-form-item label="服务费含税" prop="isOrdinaryTax">
            <el-radio disabled v-model="ruleForm.isOrdinaryTax" label='0'>是</el-radio>
            <el-radio disabled v-model="ruleForm.isOrdinaryTax" label='1'>否</el-radio>
          </el-form-item>
          <el-form-item label="价税分离" prop="isSelfTax">
            <el-radio v-model="ruleForm.isSelfTax" label='0'>是</el-radio>
            <el-radio v-model="ruleForm.isSelfTax" label='1'>否</el-radio>
          </el-form-item>
          <el-form-item label="是否分润" :required="true">
            <el-radio v-model="ruleForm.isOrdinaryShare" label="0">是</el-radio>
            <el-radio v-model="ruleForm.isOrdinaryShare" label="1">否</el-radio>
          </el-form-item>
          <el-row v-if="ruleForm.isOrdinaryShare == 0" type="flex" justify="flex-end">
            <el-col :span="24">
              <el-form-item label="分润方式" prop="ordinaryShare">
                <div style="">
                  <el-radio disabled v-model="ruleForm.ordinaryShareIsmoney" label="0">按定额收取</el-radio>
                  <el-radio disabled v-model="ruleForm.ordinaryShareIsmoney" label="1">按百分比收取</el-radio>

                  <el-input :readonly="true" v-if="ruleForm.ordinaryShareIsmoney == 0" style="width:100%" type="number"
                    @change="handleChange" :step="0.01" :min="0" v-model="ruleForm.ordinaryShare">
                    <template slot="append">元</template>
                  </el-input>
                  <el-input :readonly="true" v-model="ruleForm.ordinaryShare" v-else type="number" style="width:100%"
                    @change="handleChange" :step="0.01" :min="0" :max="100">
                    <template slot="append">%</template>
                  </el-input>
                </div>

              </el-form-item>
            </el-col>
          </el-row>


        </el-card>

        <!-- 卡3 -->
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>增值税专用发票</span>
          </div>
          <el-row type="flex" justify="flex-end">
            <el-col :span="24">
              <el-form-item label="专票税率" :required="true">
                <el-select style="width:100%" disabled v-model="ruleForm.ordinarySpecialTax" clearable
                  placeholder="请选择">
                  <el-option v-for="item in optiond" :key="item.value" :label="item.label" :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row type="flex" justify="flex-end">
            <el-col :span="24">
              <el-form-item label="专票服务费" prop="specialProxyFee">

                <div>
                  <el-radio disabled v-model="ruleForm.specialProxyIsmoney" label="0">按定额收取</el-radio>
                  <el-radio disabled v-model="ruleForm.specialProxyIsmoney" label="1">按百分比收取</el-radio>

                  <el-input v-if="ruleForm.specialProxyIsmoney == 0" style="width:100%" :readonly="true" type="number"
                    v-model="ruleForm.specialProxyFee" @change="handleChange" :step="0.01" :min="0">
                    <template slot="append">元</template>
                  </el-input>
                  <el-input :readonly="true" v-else type="number" style="width:100%" v-model="ruleForm.specialProxyFee"
                    @change="handleChange" :step="0.01" :min="0" :max="100">
                    <template slot="append">%</template>
                  </el-input>
                </div>
              </el-form-item>
            </el-col>

          </el-row>


          <el-form-item label="服务费含税" prop="isSpecialTax">
            <el-radio disabled v-model="ruleForm.isSpecialTax" label='0'>是</el-radio>
            <el-radio disabled v-model="ruleForm.isSpecialTax" label='1'>否</el-radio>
          </el-form-item>
          <el-form-item label="价税分离" prop="isSpecialSelfTax">
            <el-radio v-model="ruleForm.isSpecialSelfTax" label='0'>是</el-radio>
            <el-radio v-model="ruleForm.isSpecialSelfTax" label='1'>否</el-radio>
          </el-form-item>
          <el-form-item label="是否分润">
            <el-radio v-model="ruleForm.isSpecialShare" label="0">是</el-radio>
            <el-radio v-model="ruleForm.isSpecialShare" label="1">否</el-radio>
          </el-form-item>

          <el-row v-if="ruleForm.isSpecialShare == 0" type="flex" justify="flex-end">
            <el-col :span="24">
              <el-form-item label="分润方式" prop="specialShare">
                <div style="">
                  <el-radio disabled v-model="ruleForm.specialShareIsmoney" label="0">按定额收取</el-radio>
                  <el-radio disabled v-model="ruleForm.specialShareIsmoney" label="1">按百分比收取</el-radio>

                  <el-input v-if="ruleForm.specialShareIsmoney == 0" style="width:100%" type="number"
                    @change="handleChange" :step="0.01" :min="0" v-model="ruleForm.specialShare" :readonly="true">
                    <template slot="append">元</template>
                  </el-input>
                  <el-input v-else v-model="ruleForm.specialShare" :readonly="true" type="number" style="width:100%"
                    @change="handleChange" :step="0.01" :min="0" :max="100">
                    <template slot="append">%</template>
                  </el-input>
                </div>

              </el-form-item>
            </el-col>
          </el-row>
        </el-card>
      </el-form>
    </el-dialog>

  </div>
</template>

<script>
// 手机号码验证
var contactPhone = (rule, value, callback) => {
  if (!value) {
    return callback(new Error('手机号不能为空'))
  } else {
    const reg = /^1[3|4|5|7|8][0-9]\d{8}$/
    if (reg.test(value)) {
      callback()
    } else {
      return callback(new Error('请输入11位数字'))
    }
  }
};
// 数字验证
var numCheck = (rule, value, callback) => {
  if (!value) {
    return callback(new Error('个体户注册服务费不能为空'))
  } else {
    const reg = /[^\d]/g;
    if (!reg.test(value)) {
      callback()
    } else {
      return callback(new Error('请输入正整数'))
    }
  }
};
import crudPlace from "@/api/place/place";
import agencyfee from "@/api/place/agencyfee";
import { getAllUser } from '@/api/system/user';
import { getInfo } from '@/api/login';
import '@riophae/vue-treeselect/dist/vue-treeselect.css'

export default {
  name: "placeMgr",

  data() {
    return {
      editSpecialProxyFee: '2',
      editOrdinaryProxyFee: '2',
      editSpecialInvoice13: '2',
      editSpecialInvoice6: '2',
      editSpecialShare: '2',
      editOrdinaryShare: '2',

      ordinaryProxyFee: '2',
      specialInvoice13: '2',
      specialInvoice6: '2',
      specialProxyMoney: '2',
      specialProxyFee: '2',

      specialShare: '2',
      ordinaryShare: '2',
      radio: '1',
      confirmEditStatus: true, //编辑页面编辑按钮
      tabelData: [],
      editVisible: false,
      multipleSelection: [],
      addVisible: false,
      userLeaders: [],
      status: [],
      placeStatus: [], // 多选时使用
      is_special_tax: true,
      is_ordinary_tax: true,
      value: '正常',
      values: '0',
      special_invoice_6: 0,
      special_invoice_13: 6.1,
      ordinary_proxy_fee: 0,
      Include: '1',
      users: [],
      isCheck: true,
      checkVisible: false,
      currentRow: {}, selectIndex: '', placeCode: '', urlHistory: '',
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 个体商户表格数据
      employedList: [],
      // 弹出层标题
      title: "",
      titles: '',
      titleh: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        placeName: null,
        userName: null,
        status: '',
      },
      ruleForm: {
        isSelfTax: '1',     //普票价税分离
        isSpecialSelfTax: '1',  //专票价税分离
        ordinaryTax: '',//普票税率
        ordinarySpecialTax: '',//专票税率

        placeAlias: '',//渠道商别名
        placeCode: '',
        placeName: '',
        placeLinkman: '',
        placeTel: '',
        userId: 26,
        userName: '',

        specialShareMoney: '',
        specialShare: '',//专票分润
        ordinaryShareMoney: '',
        ordinaryShare: '',//普票分润

        specialProxyMoney: 0,
        specialProxyFee: 0,

        specialInvoice13Money: 0, //  专票 13 元
        specialInvoice6Money: 0, //  专票6 元
        specialInvoice6: 0,   //专票 6 （%）
        specialInvoice13: 0,  //专票13 （%）
        specialSelfFee: 0,
        isSpecialTax: '1',//是否含税-专票
        ordinaryProxyMoney: 0,//普票平台服务费(元）
        ordinarySelfFee: 0,
        ordinaryProxyFee: 0, //普票平台服务费(%）
        isOrdinaryTax: '1',//是否含税-普票

        ordinaryProxyIsmoney: '0',
        isOrdinaryShare: '1',
        ordinaryShareIsmoney: '0',
        specialProxyIsmoney: '0',
        isSpecialShare: '1',
        specialShareIsmoney: '0',


        //编辑参数

        editOrdinaryProxyIsmoney: '0',
        editIsOrdinaryShare: '1',
        editOrdinaryShareIsmoney: '0',
        editSpecialProxyIsmoney: '0',
        editIsSpecialShare: '1',
        editSpecialShareIsmoney: '0',



        editIsSelfTax: '0',     //普票价税分离
        editIsSpecialSelfTax: '0',  //专票价税分离
        editPlaceId: '',
        editPlaceCode: '',
        editPlaceName: '',
        editPlaceAlias: '',//渠道商别名
        editPlaceLinkman: '',
        editPlaceTel: '',
        editUserId: '',
        editUserName: '',

        editAgencyFeeId: '',
        editSpecialInvoice6: '',
        editSpecialInvoice13: '',
        editSpecialInvoice6Money: '',
        editSpecialInvoice13Money: '',
        editSpecialSelfFee: '',
        editIsSpecialTax: '',//是否含税-专票
        editSpecialProxyMoney: '',
        editSpecialProxyFee: '',

        editOrdinaryTax: '',
        editOrdinarySpecialTax: '',

        editSpecialShareMoney: '',
        editSpecialShare: '',//专票分润
        editOrdinaryShareMoney: '',
        editOrdinaryShare: '',//普票分润

        editOrdinarySelfFee: '',
        editOrdinaryProxyFee: '',
        editOrdinaryProxyMoney: '',
        editIsOrdinaryTax: '',//是否含税-普票
      },
      option: [
        {
          value: '0',
          label: '免税'
        },
        {
          value: '0.01',
          label: '1%'
        }, {
          value: '0.03',
          label: '3%'
        },
      ],
      optiond: [
        {
          value: '0.01',
          label: '1%'
        }, {
          value: '0.03',
          label: '3%'
        },
      ],
      options: [
        {
          value: 0,
          label: '正常'
        },
        {

          value: 2,
          label: '休眠'
        },
        {

          value: 1,
          label: '欠费',
        },
      ],
      rules: {
        placeCode: [
          { required: true, message: '请输入编号', trigger: 'blur' }
        ],
        placeAlias: [
          { required: true, message: '请输入渠道别名', trigger: 'blur' }
        ],
        placeName: [
          { required: true, message: '请输入渠道商名', trigger: 'blur' }
        ],
        placeLinkman: [
          { required: true, message: '请输入联系人', trigger: 'blur' }
        ],
        placeTel: [
          { validator: contactPhone, required: true, trigger: 'blur' }
        ],
        ordinaryTax: [
          { required: true, message: '请选择普票税率', trigger: 'change' }
        ],
        ordinarySpecialTax: [
          { required: true, message: '请选择专票税率', trigger: 'change' }
        ],
        editOrdinaryTax: [
          { required: true, message: '请选择普票税率', trigger: 'change' }
        ],
        editOrdinarySpecialTax: [
          { required: true, message: '请选择专票税率', trigger: 'change' }
        ],
        ordinaryProxyFee: [
          { required: true, message: '请输入普票服务费', trigger: 'blur' }
        ],
        editOrdinaryProxyFee: [
          { required: true, message: '请输入普票服务费', trigger: 'blur' }
        ],
        ordinaryShare: [
          { required: true, message: '请输入普票分润费', trigger: 'blur' }
        ],
        editOrdinaryShare: [
          { required: true, message: '请输入普票分润费', trigger: 'blur' }
        ],
        specialProxyFee: [
          { required: true, message: '请输入专票服务费', trigger: 'blur' }
        ],
        editSpecialProxyFee: [
          { required: true, message: '请输入专票服务费', trigger: 'blur' }
        ],
        specialShare: [
          { required: true, message: '请输入专票分润费', trigger: 'blur' }
        ],
        editSpecialShare: [
          { required: true, message: '请输入专票分润费', trigger: 'blur' }
        ],


        editPlaceAlias: [
          { required: true, message: '请输入渠道别名', trigger: 'blur' }
        ],
        editPlaceName: [
          { required: true, message: '请输入渠道商名', trigger: 'blur' }
        ],
        editPlaceLinkman: [
          { required: true, message: '请输入联系人', trigger: 'blur' }
        ],
        editPlaceTel: [
          { validator: contactPhone, required: true, trigger: 'blur' }
        ],



        userId: [
          { required: true, message: '请选择业务经理', trigger: 'change' }
        ],



        specialSelfFee: [
          { validator: numCheck, required: true, trigger: 'blur' }
        ],
        editSpecialSelfFee: [
          { validator: numCheck, required: true, trigger: 'blur' }
        ],
        isSpecialTax: [
          { required: true, message: '请选择是否含税', trigger: 'change' }
        ],
        ordinarySelfFee: [
          { validator: numCheck, required: true, trigger: 'blur' }
        ],
        editOrdinarySelfFee: [
          { validator: numCheck, required: true, trigger: 'blur' }
        ],

        isOrdinaryTax: [
          { required: true, message: '请选择是否含税', trigger: 'change' }
        ],
      },
      queryTypeOptions: [
        { key: 'placeName', display_name: '渠道商名称' },
        { key: 'placeStatus', display_name: '渠道商状态' },
        { key: 'nickName', display_name: '业务经理' }
      ],
      // 表单参数
      form: {},
      // 表单校验
      // rules: {},
    };
  },
  created() {
    this.$http.get('/getUsers').then(res => {
      console.log(res);
    });

  },
  mounted() {
    this.getList();
    this.getAllUser();
    this.getPlaceCode();
    //获取登录用户
    getInfo().then(res => {
      this.ruleForm.userName = res.user.userName;
      this.ruleForm.editUserName = res.user.userName;
      this.ruleForm.userId = res.user.userId;
      console.log("getInfo", this.ruleForm.userName);
    })
  },

  methods: {
    handlespecialShareIsmoneyS(e) {
      if (this.ruleForm.editSpecialShareIsmoney == '1') {
        if (e > 100) {
          this.ruleForm.editSpecialShare = '100';
        }
      }
    },
    handlespecialProxyIsmoneyS(e) {
      if (this.ruleForm.editSpecialProxyIsmoney == '1') {
        if (e > 100) {
          this.ruleForm.editSpecialProxyFee = '100';
        }
      }
    },
    handleordinaryShareIsmoneyS(e) {
      if (this.ruleForm.editOrdinaryShareIsmoney == '1') {
        if (e > 100) {
          this.ruleForm.editOrdinaryShare = '100';
        }
      }
    },
    handleordinaryProxyFeeS(e) {
      if (this.ruleForm.editOrdinaryProxyIsmoney == '1') {
        if (e > 100) {
          this.ruleForm.editOrdinaryProxyFee = '100';
        }
      }
    },

    handlespecialShareIsmoney(e) {
      if (this.ruleForm.specialShareIsmoney == '1') {
        if (e > 100) {
          this.ruleForm.specialShare = '100';
        }
      }
    },
    handlespecialProxyIsmoney(e) {
      if (this.ruleForm.specialProxyIsmoney == '1') {
        if (e > 100) {
          this.ruleForm.specialProxyFee = '100';
        }
      }
    },
    handleordinaryShareIsmoney(e) {
      if (this.ruleForm.ordinaryShareIsmoney == '1') {
        if (e > 100) {
          this.ruleForm.ordinaryShare = '100';
        }
      }
    },
    handleordinaryProxyFee(e) {

      if (this.ruleForm.ordinaryProxyIsmoney == '1') {
        if (e > 100) {
          this.ruleForm.ordinaryProxyFee = '100';
        }
      }
    },
    handPoxy(e) {
      if (e == '1') {
        if (this.ruleForm.ordinaryProxyIsmoney == '1') {
          if (this.ruleForm.ordinaryProxyFee > 100) {
            this.ruleForm.ordinaryProxyFee = '100';
          }
        }
      }
    },
    hanOrshare(e) {
      if (e == '1') {
        if (this.ruleForm.ordinaryShareIsmoney == '1') {
          if (this.ruleForm.ordinaryShare > 100) {
            this.ruleForm.ordinaryShare = '100';
          }
        }
      }
    },
    handSpecial(e) {
      if (e == '1') {
        if (this.ruleForm.specialProxyIsmoney == '1') {
          if (this.ruleForm.specialProxyFee > 100) {
            this.ruleForm.specialProxyFee = '100';
          }
        }
      }
    },
    handMoney(e) {
      if (e == '1') {
        if (this.ruleForm.specialShareIsmoney == '1') {
          if (this.ruleForm.specialShare > 100) {
            this.ruleForm.specialShare = '100';
          }
        }
      }
    },
    handPoxyS(e) {
      if (e == '1') {
        if (this.ruleForm.editOrdinaryProxyIsmoney == '1') {
          if (this.ruleForm.editOrdinaryProxyFee > 100) {
            this.ruleForm.editOrdinaryProxyFee = '100';
          }
        }
      }
    },
    hanOrshareS(e) {
      if (e == '1') {
        if (this.ruleForm.editOrdinaryShareIsmoney == '1') {
          if (this.ruleForm.editOrdinaryShare > 100) {
            this.ruleForm.editOrdinaryShare = '100';
          }
        }
      }
    },
    handSpecialS(e) {
      if (e == '1') {
        if (this.ruleForm.editSpecialProxyIsmoney == '1') {
          if (this.ruleForm.editSpecialProxyFee > 100) {
            this.ruleForm.editSpecialProxyFee = '100';
          }
        }
      }
    },
    handMoneyS(e) {
      if (e == '1') {
        if (this.ruleForm.editSpecialShareIsmoney == '1') {
          if (this.ruleForm.editSpecialShare > 100) {
            this.ruleForm.editSpecialShare = '100';
          }
        }
      }
    },
    getPlaceCode() {
      crudPlace.getCode().then(res => {
        this.ruleForm.placeCode = res.message;
        console.log("placeCode", this.ruleForm.placeCode);
      })
    },

    /** 查询列表 */
    getList() {
      this.loading = true;
      crudPlace.getByPage(this.queryParams).then((response) => {
        this.employedList = response.rows;

        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.addVisible = false;
      this.reset();
    },
    //取消按钮新
    cancelS(type) {
      if (type == 1) {
        this.addVisible = false;

      } else if (type == 2) {
        this.editVisible = false;
      } else if (type == 3) {
        this.checkVisible = false;
      }

    },
    // 表单重置
    reset() {

      this.ruleForm.isSelfTax = '1';   //普票价税分离
      this.ruleForm.isSpecialSelfTax = '1';  //专票价税分离
      this.ruleForm.ordinaryProxyIsmoney = '0';
      this.ruleForm.isOrdinaryShare = '1';
      this.ruleForm.ordinaryShareIsmoney = '0'
      this.ruleForm.specialProxyIsmoney = '0'
      this.ruleForm.isSpecialShare = '1';
      this.ruleForm.specialShareIsmoney = '0';




      this.ruleForm.placeName = null;
      this.ruleForm.placeAlias = null;//渠道商别名
      this.ruleForm.placeLinkman = null;
      this.ruleForm.placeTel = null;
      this.ruleForm.userId = null;
      this.ruleForm.userName = null;
      this.ruleForm.ordinarySpecialTax = null;
      this.ruleForm.ordinaryTax = null;
      this.ruleForm.specialInvoice6 = '';
      this.ruleForm.specialInvoice13 = '';
      this.ruleForm.specialInvoice6Money = '';
      this.ruleForm.specialInvoice13Money = '';
      this.ruleForm.specialSelfFee = null;
      this.ruleForm.isSpecialTax = '1';
      this.ruleForm.ordinarySelfFee = null;
      this.ruleForm.ordinaryProxyFee = '';
      this.ruleForm.ordinaryProxyMoeny = '';
      this.ruleForm.isOrdinaryTax = '1';
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.multipleSelection = selection;
    },

    handleChange(value) {
      console.log(value);
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.getPlaceCode();
      this.addVisible = true;
      this.title = "新增渠道管理";
      //获取登录用户
      getInfo().then(res => {
        this.ruleForm.userName = res.user.userName;
        this.ruleForm.editUserName = res.user.userName;
        this.ruleForm.userId = res.user.userId;
        console.log("getInfo", this.ruleForm.userName);
      })
    },
    /** 修改按钮操作 */
    handleUpdate(item) {
      this.editVisible = true;
      this.confirmEditStatus = true;// 开启编辑保护
      this.titles = "编辑渠道管理";
      var placeCode = item.placeCode;
      agencyfee.selectFeeByCode({ placeCode: placeCode }).then(res => {
        this.ruleForm.editOrdinaryProxyIsmoney = JSON.stringify(res.ordinaryProxyIsmoney);
        this.ruleForm.editIsOrdinaryShare = JSON.stringify(res.isOrdinaryShare);
        this.ruleForm.editOrdinaryShareIsmoney = JSON.stringify(res.ordinaryShareIsmoney);
        this.ruleForm.editSpecialProxyIsmoney = JSON.stringify(res.specialProxyIsmoney);
        this.ruleForm.editIsSpecialShare = JSON.stringify(res.isSpecialShare);
        this.ruleForm.editSpecialShareIsmoney = JSON.stringify(res.specialShareIsmoney);
        this.ruleForm.editIsSelfTax = JSON.stringify(res.isSelfTax),
          this.ruleForm.editIsSpecialSelfTax = JSON.stringify(res.isSpecialSelfTax),
          this.ruleForm.editAgencyFeeId = res.agencyFeeId;
        this.ruleForm.editSpecialSelfFee = res.specialSelfFee;
        this.ruleForm.editIsSpecialTax = res.isSpecialTax;
        this.ruleForm.editOrdinarySelfFee = res.ordinarySelfFee;
        this.ruleForm.editOrdinaryProxyFee = res.ordinaryProxyFee;
        this.ruleForm.editIsOrdinaryTax = res.isOrdinaryTax;
        this.ruleForm.editSpecialProxyFee = res.specialProxyFee;
        this.ruleForm.editSpecialShare = res.specialShare;
        this.ruleForm.editOrdinaryShare = res.ordinaryShare;
        this.ruleForm.editOrdinarySpecialTax = JSON.stringify(res.ordinarySpecialTax);
        this.ruleForm.editOrdinaryTax = JSON.stringify(res.ordinaryTax);
        if (this.ruleForm.editIsOrdinaryTax == true) {
          this.ruleForm.editIsOrdinaryTax = "0"
        } else if (this.ruleForm.editIsOrdinaryTax == false) {
          this.ruleForm.editIsOrdinaryTax = "1"
        }
        if (this.ruleForm.editIsSpecialTax == true) {
          this.ruleForm.editIsSpecialTax = "0"
        } else if (this.ruleForm.editIsSpecialTax == false) {
          this.ruleForm.editIsSpecialTax = "1"
        }

      })

      this.ruleForm.editPlaceId = item.placeId;
      this.ruleForm.editPlaceCode = item.placeCode;
      this.ruleForm.editPlaceName = item.placeName;
      this.ruleForm.editPlaceAlias = item.placeAlias;//渠道商别名
      this.ruleForm.editPlaceLinkman = item.placeLinkman;
      this.ruleForm.editPlaceTel = item.placeTel;
      this.ruleForm.editUserId = item.userId;
      this.ruleForm.editUserName = item.userName;
    },
    //详情
    detail(item) {
      this.titleh = '渠道管理详情';
      this.checkVisible = true;
      this.ruleForm.placeCode = item.placeCode;
      this.ruleForm.placeName = item.placeName;
      this.ruleForm.placeLinkman = item.placeLinkman;
      this.ruleForm.placeTel = item.placeTel;
      this.ruleForm.nickName = item.nickName;
      this.ruleForm.placeStatus = item.placeStatus;
      this.ruleForm.userName = item.userName;
      this.ruleForm.placeAlias = item.placeAlias;

      agencyfee.selectFeeByCode({ placeCode: this.ruleForm.placeCode }).then(res => {
        console.log("selectFeeByCode==", res);

        this.ruleForm.ordinaryProxyIsmoney = JSON.stringify(res.ordinaryProxyIsmoney);
        this.ruleForm.isOrdinaryShare = JSON.stringify(res.isOrdinaryShare);
        this.ruleForm.ordinaryShareIsmoney = JSON.stringify(res.ordinaryShareIsmoney);
        this.ruleForm.specialProxyIsmoney = JSON.stringify(res.specialProxyIsmoney);
        this.ruleForm.isSpecialShare = JSON.stringify(res.isSpecialShare);
        this.ruleForm.specialShareIsmoney = JSON.stringify(res.specialShareIsmoney);

        this.ruleForm.isSelfTax = JSON.stringify(res.isSelfTax);
        this.ruleForm.isSpecialSelfTax = JSON.stringify(res.isSpecialSelfTax),

          this.ruleForm.ordinarySpecialTax = res.ordinarySpecialTax;//专票税率
        this.ruleForm.ordinaryTax = res.ordinaryTax; //普票税率


        //this.ruleForm.specialProxyMoney = res.specialProxyMoney;
        this.ruleForm.specialProxyFee = res.specialProxyFee;




        this.ruleForm.specialSelfFee = res.specialSelfFee;
        this.ruleForm.isSpecialTax = res.isSpecialTax;
        this.ruleForm.ordinarySelfFee = res.ordinarySelfFee;
        this.ruleForm.ordinaryProxyFee = res.ordinaryProxyFee;
        //this.ruleForm.ordinaryProxyMoney = res.ordinaryProxyMoney;

        this.ruleForm.ordinarySpecialTax = JSON.stringify(res.ordinarySpecialTax);
        this.ruleForm.ordinaryTax = JSON.stringify(res.ordinaryTax);

        this.ruleForm.specialShare = res.specialShare;
        // this.ruleForm.specialShareMoney=res.specialShareMoney;
        this.ruleForm.ordinaryShare = res.ordinaryShare;
        //this.ruleForm.ordinaryShareMoney=res.ordinaryShareMoney;




        this.ruleForm.isOrdinaryTax = res.isOrdinaryTax;
        if (this.ruleForm.isOrdinaryTax == true) {
          this.ruleForm.isOrdinaryTax = "0"
        } else if (this.ruleForm.isOrdinaryTax == false) {
          this.ruleForm.isOrdinaryTax = "1"
        }
        if (this.ruleForm.isSpecialTax == true) {
          this.ruleForm.isSpecialTax = "0"
        } else if (this.ruleForm.isSpecialTax == false) {
          this.ruleForm.isSpecialTax = "1"
        }
      })
      this.ruleForm.value = item.value;
    },
    //修改状态
    isDormancy(item, type) {
      var placeStatus;
      if (item.placeStatus == 0) {
        placeStatus = 2;
      }
      if (item.placeStatus == 1) {
        this.$message({
          message: '欠费状态不能点哦',
          type: 'warning',
        });
        return;
      }
      if (item.placeStatus == 2) {
        placeStatus = 0;
      }
      let params = {
        placeId: item.placeId,
        placeStatus: placeStatus,
      }
      crudPlace.editPlace2(params).then((res) => {
        if (res.id == 0) {
          this.$message({
            message: res.message,
            type: 'success',
          });
        } else {
          this.$message({
            message: res.message,
            type: 'warning',
          });
        }
        this.$tab.refreshPage();
        // this.editVisible=false;
      })
    },
    //获取所有用户
    getAllUser() {
      getAllUser().then(res => {
        this.userLeaders = res;

      })
    },


    selectUser(value) {
      this.ruleForm.userName = this.userLeaders.find((item) => item.userId == value).nickName;
      this.ruleForm.editUserName = this.userLeaders.find((item) => item.userId == value).nickName;
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (this.ruleForm.specialShareIsmoney == '1') {
          if (this.ruleForm.specialShare > 100) {
            this.$alert('专票分润费按百分比不能大于100%', '提示', {
              confirmButtonText: '确定',
            });
            return;
          }
        }
        if (this.ruleForm.ordinaryShareIsmoney == '1') {
          if (this.ruleForm.ordinaryShare > 100) {
            this.$alert('普票分润费按百分比不能大于100%', '提示', {
              confirmButtonText: '确定',
            });
            return;
          }
        }

        if (this.ruleForm.specialProxyIsmoney == '1') {
          if (this.ruleForm.specialProxyFee > 100) {
            this.$alert('专票服务费按百分比不能大于100%', '提示', {
              confirmButtonText: '确定',
            });
            return;
          }
        }
        if (this.ruleForm.ordinaryProxyIsmoney == '1') {
          if (this.ruleForm.ordinaryProxyFee > 100) {
            this.$alert('普票服务费按百分比不能大于100%', '提示', {
              confirmButtonText: '确定',
            });
            return;
          }
        }






        if (valid) {
          if (this.ruleForm.isOrdinaryTax == "0") {
            this.ruleForm.isOrdinaryTax = true;
          } else if (this.ruleForm.isOrdinaryTax == '1') {
            this.ruleForm.isOrdinaryTax = false;
          }
          if (this.ruleForm.isSpecialTax == "0") {
            this.ruleForm.isSpecialTax = true;
          } else if (this.ruleForm.isSpecialTax == "1") {
            this.ruleForm.isSpecialTax = false;
          }



          let data = {
            businessPlace: {
              placeCode: this.ruleForm.placeCode,
              placeName: this.ruleForm.placeName,
              placeAlias: this.ruleForm.placeAlias,
              placeLinkman: this.ruleForm.placeLinkman,
              placeTel: this.ruleForm.placeTel,
              userId: this.ruleForm.userId,
              userName: this.ruleForm.userName,
            },
            businessAgencyFee: {
              isSelfTax: this.ruleForm.isSelfTax,
              isSpecialSelfTax: this.ruleForm.isSpecialSelfTax,
              placeCode: this.ruleForm.placeCode,

              // specialInvoice6Money: this.ruleForm.specialInvoice6Money,
              // specialInvoice13Money: this.ruleForm.specialInvoice13Money,
              // ordinaryProxyMoney: this.ruleForm.ordinaryProxyMoney,
              ordinaryProxyFee: this.ruleForm.ordinaryProxyFee,
              // specialProxyMoney: this.ruleForm.specialProxyMoney,
              specialProxyFee: this.ruleForm.specialProxyFee,

              // specialInvoice6: this.ruleForm.specialInvoice6,
              // specialInvoice13: this.ruleForm.specialInvoice13,
              specialSelfFee: this.ruleForm.specialSelfFee,
              isSpecialTax: this.ruleForm.isSpecialTax,
              ordinarySelfFee: this.ruleForm.ordinarySelfFee,

              ordinaryTax: this.ruleForm.ordinaryTax,
              ordinarySpecialTax: this.ruleForm.ordinarySpecialTax,

              specialShare: this.ruleForm.specialShare,
              //specialShareMoney:this.ruleForm.specialShareMoney,
              ordinaryShare: this.ruleForm.ordinaryShare,
              //ordinaryShareMoney:this.ruleForm.ordinaryShareMoney,


              isOrdinaryTax: this.ruleForm.isOrdinaryTax,
              ordinaryProxyIsmoney: this.ruleForm.ordinaryProxyIsmoney,
              isOrdinaryShare: this.ruleForm.isOrdinaryShare,
              ordinaryShareIsmoney: this.ruleForm.ordinaryShareIsmoney,
              specialProxyIsmoney: this.ruleForm.specialProxyIsmoney,
              isSpecialShare: this.ruleForm.isSpecialShare,
              specialShareIsmoney: this.ruleForm.specialShareIsmoney,





            }
          };
          crudPlace.add(data).then(res => {
            if (res.id == 0) {
              this.$message({
                message: res.message,
                type: 'success',
              });
            } else {
              this.$message({
                message: res.message,
                type: 'warning',
              });
            }
            this.addVisible = false;
            this.getPlaceCode();//重新在获取一遍编号（避免编号重复）
            this.$tab.refreshPage();

          }).catch(err => {
            this.addVisible = false;
            this.getPlaceCode();//重新在获取一遍编号（避免编号重复）
            this.$tab.refreshPage();
          });

        }
        else {
          this.$message({
            message: "请填写完整",
            type: 'warning',
          });
          return false;
        }
      });
    },
    //编辑状态
    confirmEdits() {
      this.confirmEditStatus = false;
    },
    //修改提交
    confirmEdit() {
      this.$refs['form'].validate((valid) => {
        if (this.ruleForm.editSpecialShareIsmoney == '1') {
          if (this.ruleForm.editSpecialShare > 100) {
            this.$alert('专票分润费按百分比不能大于100%', '提示', {
              confirmButtonText: '确定',
            });
            return;
          }
        }
        if (this.ruleForm.editOrdinaryShareIsmoney == '1') {
          if (this.ruleForm.editOrdinaryShare > 100) {
            this.$alert('普票分润费按百分比不能大于100%', '提示', {
              confirmButtonText: '确定',
            });
            return;
          }
        }

        if (this.ruleForm.editSpecialProxyIsmoney == '1') {
          if (this.ruleForm.editSpecialProxyFee > 100) {
            this.$alert('专票服务费按百分比不能大于100%', '提示', {
              confirmButtonText: '确定',
            });
            return;
          }
        }
        if (this.ruleForm.editOrdinaryProxyIsmoney == '1') {
          if (this.ruleForm.editOrdinaryProxyFee > 100) {
            this.$alert('普票服务费按百分比不能大于100%', '提示', {
              confirmButtonText: '确定',
            });
            return;
          }
        }






        if (valid) {
          if (this.ruleForm.editIsOrdinaryTax == "0") {
            this.ruleForm.editIsOrdinaryTax = true;
          } else if (this.ruleForm.editIsOrdinaryTax == '1') {
            this.ruleForm.editIsOrdinaryTax = false;
          }
          if (this.ruleForm.editIsSpecialTax == "0") {
            this.ruleForm.editIsSpecialTax = true;
          } else if (this.ruleForm.editIsSpecialTax == "1") {
            this.ruleForm.editIsSpecialTax = false;
          }


          let params = {
            agencyFeeId: this.ruleForm.editAgencyFeeId,
            placeId: this.ruleForm.editPlaceId,
            placeCode: this.ruleForm.editPlaceCode,
            placeName: this.ruleForm.editPlaceName,
            placeAlias: this.ruleForm.editPlaceAlias,//渠道商别名
            placeLinkman: this.ruleForm.editPlaceLinkman,
            placeTel: this.ruleForm.editPlaceTel,
            userId: this.ruleForm.editUserId,
            userName: this.ruleForm.editUserName,

            isOrdinaryTax: this.ruleForm.editIsOrdinaryTax,
            ordinaryProxyIsmoney: this.ruleForm.editOrdinaryProxyIsmoney,
            isOrdinaryShare: this.ruleForm.editIsOrdinaryShare,
            ordinaryShareIsmoney: this.ruleForm.editOrdinaryShareIsmoney,
            specialProxyIsmoney: this.ruleForm.editSpecialProxyIsmoney,
            isSpecialShare: this.ruleForm.editIsSpecialShare,
            specialShareIsmoney: this.ruleForm.editSpecialShareIsmoney,

            specialSelfFee: this.ruleForm.editSpecialSelfFee,
            isSpecialTax: this.ruleForm.editIsSpecialTax,

            ordinarySelfFee: this.ruleForm.editOrdinarySelfFee,

            //specialProxyMoney: this.ruleForm.editSpecialProxyMoney,
            specialProxyFee: this.ruleForm.editSpecialProxyFee,

            ordinaryProxyFee: this.ruleForm.editOrdinaryProxyFee,
            //ordinaryProxyMoney: this.ruleForm.editOrdinaryProxyMoney, //服务费元

            ordinaryTax: this.ruleForm.editOrdinaryTax,
            ordinarySpecialTax: this.ruleForm.editOrdinarySpecialTax,

            specialShare: this.ruleForm.editSpecialShare,
            // specialShareMoney:this.ruleForm.editSpecialShareMoney,
            ordinaryShare: this.ruleForm.editOrdinaryShare,
            //ordinaryShareMoney:this.ruleForm.editOrdinaryShareMoney,

            isOrdinaryTax: this.ruleForm.editIsOrdinaryTax,
            isSelfTax: this.ruleForm.editIsSelfTax,
            isSpecialSelfTax: this.ruleForm.editIsSpecialSelfTax,
          }
          crudPlace.editPlace(params).then((res) => {
            if (res.id == 0) {
              this.$message({
                message: res.message,
                type: 'success',
              });
            } else {
              this.$message({
                message: res.message,
                type: 'warning',
              });
            }

            this.editVisible = false;
            this.$tab.refreshPage();

          })
        } else {
          this.$message({
            message: "请填写完整",
            type: 'warning',
          });
          return false;
        }
      })
    },


    /** 删除按钮操作 */
    handleDelete(row) {
      let placeCodeStr = [];
      console.log(this.multipleSelection);
      this.multipleSelection.map((item) => {
        placeCodeStr.push(item.placeCode);
      })
      let params = {
        placeCodes: placeCodeStr.join(',')//转换成字符串
      };


      this.$confirm('是否确认删除此渠道的信息', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        crudPlace.delPlace(params).then((res) => {
          if (res != undefined) {
            if (res.id == 0) {
              this.$message({
                message: res.message,
                type: 'success',
              });
            } else {
              this.$message({
                message: res.message,
                type: 'warning',
              });
            }
            this.$tab.refreshPage();

          }
        })
      }).catch(() => {

      });


    },

  },
};
</script>
<style lang="scss" scoped>
.btnDiv {

  height: 50px;
  margin-top: 20px;

  ::v-deep .el-button {
    margin: 0 10px 0 10px;
  }
}

// 改变input框字体颜色
::v-deep .el-input__inner {
  background-color: transparent !important;
  color: black;
  border-color: none !important;
}

::v-deep .is-disabled {
  color: black;

}

::v-deep .el-radio__input.is-disabled+span.el-radio__label {
  background-color: transparent !important;
  color: black !important;
}

::v-deep .el-input.is-disabled .el-input__inner {
  color: black !important;
}

::v-deep .el-radio {
  background-color: #FFFFFF !important;
}

::v-deep .el-radio__input.is-checked .el-radio__inner {
  border-color: #1890ff !important;
  background: #1890ff !important;
}

::v-deep .el-dialog:not(.is-fullscreen) {
  margin-top: 1vh !important;
}

::v-deep .el-input.is-disabled .el-input__inner {
  background-color: rgba(255, 255, 255, 1.5) !important;
  color: black !important;
  border-color: rgba(135, 206, 250, 0.7) !important;
}

::v-deep .el-input-group__append {
  background-color: rgba(255, 255, 255, 1.5) !important;
  color: black !important;
  border-color: rgba(135, 206, 250, 0.7) !important;
}
</style>

