<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="auto">
      <el-form-item label="渠道商" prop="placeName">
        <el-input v-model="queryParams.placeName" placeholder="请输入渠道商" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="业务经理" prop="userName">
        <el-input v-model="queryParams.userName" placeholder="请输入渠道商" clearable @keyup.enter.native="handleQuery" />
      </el-form-item> -->
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
      <!-- <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
         >修改</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete">删除
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="employedList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="渠道商名称" align="center" prop="placeName" :show-overflow-tooltip="true" />
      <el-table-column label="联系人" align="center" prop="placeLinkman" :show-overflow-tooltip="true" />
      <el-table-column label="联系方式" prop="placeTel" :show-overflow-tooltip="true" />
      <el-table-column form-item label="业务经理" prop="userName" :show-overflow-tooltip="true" />
      <el-table-column label="状态" prop="dictLabel" align="center" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-s-custom" @click="detail(scope.row)">详情</el-button>


          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">编辑</el-button>

          <el-button size="mini" type="text" v-if="scope.row.placeStatus == 0" @click="isDormancy(scope.row, 2)">
            休眠
          </el-button>
          <el-button size="mini" type="text" v-if="scope.row.placeStatus == 2" @click="isDormancy(scope.row, 0)">
            激活
          </el-button>
          <el-button size="mini" type="text" disabled v-if="scope.row.placeStatus == 1" @click="isDormancy(scope.row, 1)">
            禁用
          </el-button>
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
            <el-input v-model="ruleForm.placeCode" disabled />
          </el-form-item>
          <el-form-item label="渠道商名称" prop="placeName">
            <el-input v-model="ruleForm.placeName" />
          </el-form-item>
          <el-form-item label="联系人" prop="placeLinkman">
            <el-input v-model="ruleForm.placeLinkman" />
          </el-form-item>
          <el-form-item label="联系方式" prop="placeTel">
            <el-input v-model="ruleForm.placeTel" />
          </el-form-item>
          <el-form-item label="业务经理">
            <el-input v-model="ruleForm.userName" disabled></el-input>
            <!-- {{ruleForm.userName}} -->
            <!-- <el-select v-model="ruleForm.userId" placeholder="业务经理" class="filter-item" @change="selectUser" style="">
              <el-option v-for="item in userLeaders" :key="item.userId" :label="item.nickName" :value="item.userId" />
            </el-select> -->
          </el-form-item>
        </el-card>
        <!-- 卡2 -->
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>增值税专用发票</span>
          </div>
         <el-row type="flex" justify="flex-end">
              <el-col :span="24">
               <el-form-item label="6%专票服务费" :required="true">
                <div style="">
                   <el-radio
                     v-model="specialInvoice6"  label="1">按定额收取</el-radio>
                     <el-radio
                      v-model="specialInvoice6"  label="2" >按百分比收取</el-radio>
               
               <el-input v-if="specialInvoice6==1"
               style="width:100%"
                 type="number"  v-model="ruleForm.specialInvoice6Money" @change="handleChange"
                    :step="0.01" :min="0" :max="9999">
                  <template slot="append">元</template>     
                </el-input>
                 <el-input v-else type="number" style="width:100%"   v-model="ruleForm.specialInvoice6" @change="handleChange"
                    :step="0.01" :min="0" :max="6">
                  <template slot="append">%</template>     
                </el-input>
                 </div>
                
               </el-form-item>
            </el-col>
          </el-row>
          
            <el-row type="flex" justify="flex-end">
              <el-col :span="24">
              <el-form-item label="13%专票服务费" :required="true">
                <!-- <el-radio v-model="radio" label="1" style="width:100%">
                  <el-input style="width:80%" v-model="ruleForm.specialInvoice13" @change="handleChange" :step="0.01"
                    :precision="2" :min="0" :max="13"></el-input>
                </el-radio> -->
                  <div >
                   <el-radio
                     v-model="specialInvoice13"  label="1">按定额收取</el-radio>
                     <el-radio
                      v-model="specialInvoice13"  label="2" >按百分比收取</el-radio>
               
               <el-input v-if="specialInvoice13==1"
               style="width:100%"
                 type="number"  v-model="ruleForm.specialInvoice13Money" @change="handleChange"
                    :step="0.01" :min="0" :max="9999">
                  <template slot="append">元</template>     
                </el-input>
                 <el-input v-else type="number" style="width:100%"   v-model="ruleForm.specialInvoice13" @change="handleChange"
                    :step="0.01" :min="0" :max="13">
                  <template slot="append">%</template>     
                </el-input>
                 </div>
                 </el-form-item>
              </el-col>
             
            </el-row>

         
          <el-form-item label="个体户注册服务费" prop="specialSelfFee">
               <el-row>
                   <el-col :span="24">
                        <el-input type="number" :step="0.1" :min="0" :max="9999" v-model="ruleForm.specialSelfFee">
                             <template slot="append">元</template>
                        </el-input>
                     </el-col>
               </el-row>
              
         </el-form-item>
          <el-form-item label="服务费含税" prop="isSpecialTax">
            <el-radio v-model="ruleForm.isSpecialTax" label='0'>是</el-radio>
            <el-radio v-model="ruleForm.isSpecialTax" label='1'>否</el-radio>
          </el-form-item>
        </el-card>
        <!-- 卡3 -->
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>增值税普通发票</span>
          </div>
          

           <el-row type="flex" justify="flex-end">
              <el-col :span="24">
              <el-form-item label="普票服务费" :required="true">
                <!-- <el-radio v-model="radio" label="1" style="width:100%">
                  <el-input style="width:80%" v-model="ruleForm.specialInvoice13" @change="handleChange" :step="0.01"
                    :precision="2" :min="0" :max="13"></el-input>
                </el-radio> -->
                  <div >
                   <el-radio
                     v-model="ordinaryProxyFee"  label="1">按定额收取</el-radio>
                     <el-radio
                      v-model="ordinaryProxyFee"  label="2">按百分比收取</el-radio>
               
               <el-input v-if="ordinaryProxyFee==1"
               style="width:100%"
                 type="number"  v-model="ruleForm.ordinaryProxyMoney" @change="handleChange"
                    :step="0.01" :min="0" :max="9999">
                  <template slot="append">元</template>     
                </el-input>
                 <el-input v-else type="number" style="width:100%"   v-model="ruleForm.ordinaryProxyFee" @change="handleChange"
                    :step="0.01" :min="0" :max="13">
                  <template slot="append">%</template>     
                </el-input>
                 </div>
                 </el-form-item>
              </el-col>
             
            </el-row>

                 <el-row type="flex" justify="flex-end">
              <el-col :span="24">
              <el-form-item label="专票服务费" :required="true">
                <!-- <el-radio v-model="radio" label="1" style="width:100%">
                  <el-input style="width:80%" v-model="ruleForm.specialInvoice13" @change="handleChange" :step="0.01"
                    :precision="2" :min="0" :max="13"></el-input>
                </el-radio> -->
                  <div >
                   <el-radio
                     v-model="specialProxyFee"  label="1">按定额收取</el-radio>
                     <el-radio
                      v-model="specialProxyFee"  label="2">按百分比收取</el-radio>
               
               <el-input v-if="specialProxyFee==1"
               style="width:100%"
                 type="number"  v-model="ruleForm.specialProxyMoney" @change="handleChange"
                    :step="0.01" :min="0" :max="9999">
                  <template slot="append">元</template>     
                </el-input>
                 <el-input v-else type="number" style="width:100%"   v-model="ruleForm.specialProxyFee" @change="handleChange"
                    :step="0.01" :min="0" :max="13">
                  <template slot="append">%</template>     
                </el-input>
                 </div>
                 </el-form-item>
              </el-col>
             
            </el-row>



             <el-row type="flex" justify="flex-end">

              <el-col :span="24">
              <el-form-item label="个体户注册服务费" prop="ordinarySelfFee">
                  <el-input type="number"   v-model="ruleForm.ordinarySelfFee" @change="handleChange" :step="0.1" 
              :min="0" :max="9999" style="width:100%">
                 <template slot="append">元</template>
               </el-input>
               </el-form-item>
              </el-col>
               
             </el-row>

        <el-form-item label="服务费含税" prop="isOrdinaryTax">
            <el-radio v-model="ruleForm.isOrdinaryTax" label='0'>是</el-radio>
            <el-radio v-model="ruleForm.isOrdinaryTax" label='1'>否</el-radio>
          </el-form-item>
        </el-card>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确认</el-button>
        <el-button @click="cancelS(1)">取消</el-button>
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
          <el-form-item label="联系人" prop="editPlaceLinkman">
            <el-input v-model="ruleForm.editPlaceLinkman" :readonly="confirmEditStatus" />
          </el-form-item>
          <el-form-item label="联系方式" prop="editPlaceTel">
            <el-input v-model="ruleForm.editPlaceTel" :readonly="confirmEditStatus" />
          </el-form-item>
          <el-form-item label="业务经理" prop="editUserId">
            <el-input v-model="ruleForm.userName" :readonly="true"></el-input>
          </el-form-item>
        </el-card>
        <!-- 卡2 -->
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>增值税专用发票</span>
          </div>
           <el-row type="flex" justify="flex-end">
              <el-col :span="24">
               <el-form-item label="6%专票服务费" :required="true">
                <div>
                   <el-radio
                     :disabled="confirmEditStatus"
                     v-model="editSpecialInvoice6"  label="1">按定额收取</el-radio>
                     <el-radio
                     :disabled="confirmEditStatus"
                      v-model="editSpecialInvoice6"  label="2" >按百分比收取</el-radio>
               
               <el-input v-if="editSpecialInvoice6==1"
               style="width:100%"
               :readonly="confirmEditStatus"
                 type="number"  v-model="ruleForm.editSpecialInvoice6Money" @change="handleChange"
                    :step="0.01" :min="0" :max="9999">
                  <template slot="append">元</template>     
                </el-input>
                 <el-input
                 :readonly="confirmEditStatus"
                 v-else type="number" style="width:100%"   v-model="ruleForm.editSpecialInvoice6" @change="handleChange"
                    :step="0.01" :min="0" :max="6">
                  <template slot="append">%</template>     
                </el-input>
                 </div>
                
               </el-form-item>
            </el-col>
          </el-row>

           <el-row type="flex" justify="flex-end">
              <el-col :span="24">
              <el-form-item label="13%专票服务费" :required="true">
                 <div >
                   <el-radio
                    :disabled="confirmEditStatus"
                     v-model="editSpecialInvoice13"  label="1">按定额收取</el-radio>
                     <el-radio
                      :disabled="confirmEditStatus"
                      v-model="editSpecialInvoice13"  label="2" >按百分比收取</el-radio>
               
               <el-input v-if="editSpecialInvoice13==1"
               style="width:100%"
                :readonly="confirmEditStatus"
                 type="number"  v-model="ruleForm.editSpecialInvoice13Money" @change="handleChange"
                    :step="0.01" :min="0" :max="9999">
                  <template slot="append">元</template>     
                </el-input>
                 <el-input 
                  :readonly="confirmEditStatus"
                 v-else type="number" style="width:100%"   v-model="ruleForm.editSpecialInvoice13" @change="handleChange"
                    :step="0.01" :min="0" :max="13">
                  <template slot="append">%</template>     
                </el-input>
                 </div>
                 </el-form-item>
              </el-col>
             
            </el-row>



        

           <el-form-item label="个体户注册服务费" prop="editSpecialSelfFee">
               <el-row>
                   <el-col :span="24">
                        <el-input type="number" :readonly="confirmEditStatus" :step="0.1" :min="0" :max="9999" v-model="ruleForm.editSpecialSelfFee">
                             <template slot="append">元</template>
                        </el-input>
                     </el-col>
               </el-row>
              
         </el-form-item>
          <el-form-item label="服务费含税" prop="editIsSpecialTax">
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSpecialTax" label='0'>是</el-radio>
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsSpecialTax" label='1'>否</el-radio>
          </el-form-item>
        </el-card>
        <!-- 卡3 -->
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>增值税普通发票</span>
          </div>
             <el-row type="flex" justify="flex-end">
              <el-col :span="24">
              <el-form-item label="普票服务费" :required="true">
                  <div style="">
                   <el-radio
                    :disabled="confirmEditStatus"
                     v-model="editOrdinaryProxyFee"  label="1">按定额收取</el-radio>
                     <el-radio
                      :disabled="confirmEditStatus"
                      v-model="editOrdinaryProxyFee"  label="2" >按百分比收取</el-radio>
               
               <el-input v-if="editOrdinaryProxyFee==1"
               style="width:100%"
                :readonly="confirmEditStatus"
                 type="number"  v-model="ruleForm.editOrdinaryProxyMoney" @change="handleChange"
                    :step="0.01" :min="0" :max="9999">
                  <template slot="append">元</template>     
                </el-input>
                 <el-input 
                  :readonly="confirmEditStatus"
                  v-else type="number" style="width:100%"   v-model="ruleForm.editOrdinaryProxyFee" @change="handleChange"
                    :step="0.01" :min="0" :max="13">
                  <template slot="append">%</template>     
                </el-input>
                 </div>
                 </el-form-item>
              </el-col>
             
            </el-row>

            <el-row type="flex" justify="flex-end">
              <el-col :span="24">
              <el-form-item label="专票服务费" :required="true">
                  <div style="">
                   <el-radio
                    :disabled="confirmEditStatus"
                     v-model="editSpecialProxyFee"  label="1">按定额收取</el-radio>
                     <el-radio
                      :disabled="confirmEditStatus"
                      v-model="editSpecialProxyFee"  label="2" >按百分比收取</el-radio>
               
               <el-input v-if="editSpecialProxyFee==1"
               style="width:100%"
                :readonly="confirmEditStatus"
                 type="number"  v-model="ruleForm.editSpecialProxyMoney" @change="handleChange"
                    :step="0.01" :min="0" :max="9999">
                  <template slot="append">元</template>     
                </el-input>
                 <el-input 
                  :readonly="confirmEditStatus"
                  v-else type="number" style="width:100%"   v-model="ruleForm.editSpecialProxyFee" @change="handleChange"
                    :step="0.01" :min="0" :max="13">
                  <template slot="append">%</template>     
                </el-input>
                 </div>
                 </el-form-item>
              </el-col>
             
            </el-row>



           <el-row type="flex" justify="flex-end">

              <el-col :span="24">
              <el-form-item label="个体户注册服务费" prop="editOrdinarySelfFee">
                  <el-input type="number"
                  :readonly="confirmEditStatus"
                  v-model="ruleForm.editOrdinarySelfFee" @change="handleChange" :step="0.1" 
              :min="0" :max="9999" style="width:100%">
                 <template slot="append">元</template>
               </el-input>
               </el-form-item>
              </el-col>
               
             </el-row>
        
         
          <el-form-item label="服务费含税" prop="editIsOrdinaryTax">
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsOrdinaryTax" label='0'>是</el-radio>
            <el-radio :disabled="confirmEditStatus" v-model="ruleForm.editIsOrdinaryTax" label='1'>否</el-radio>
          </el-form-item>
        </el-card>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="confirmEdits" v-if="confirmEditStatus">编辑</el-button>
        <el-button type="primary" @click="confirmEdit" v-else>确 认</el-button>
        <el-button @click="cancelS(2)">取消</el-button>
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
          <el-form-item label="联系人" prop="placeLinkman">
            <el-input :readonly="true" v-model="ruleForm.placeLinkman" />
          </el-form-item>
          <el-form-item label="联系方式" prop="placeTel">
            <el-input :readonly="true" v-model="ruleForm.placeTel" />
          </el-form-item>
          <el-form-item label="业务经理">
             <el-input v-model="ruleForm.userName" :readonly="true"></el-input>
          </el-form-item>
        </el-card>
        <!-- 卡2 -->
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>增值税专用发票</span>
          </div>
           <el-row type="flex" justify="flex-end">
              <el-col :span="24">
               <el-form-item label="6%专票服务费" :required="true">
                <div style="">
                   <el-radio
                   disabled
                     v-model="specialInvoice6"  label="1">按定额收取</el-radio>
                     <el-radio
                     disabled
                      v-model="specialInvoice6"  label="2" >按百分比收取</el-radio>
               
               <el-input v-if="specialInvoice6==1"
               style="width:100%"
               :readonly="true"
                 type="number"  v-model="ruleForm.specialInvoice6Money" @change="handleChange"
                    :step="0.01" :min="0" :max="9999">
                  <template slot="append">元</template>     
                </el-input>
                 <el-input
                 :readonly="true"
                 v-else type="number" style="width:100%"   v-model="ruleForm.specialInvoice6" @change="handleChange"
                    :step="0.01" :min="0" :max="6">
                  <template slot="append">%</template>     
                </el-input>
                 </div>
                
               </el-form-item>
            </el-col>
          </el-row>
             <el-row type="flex" justify="flex-end">
              <el-col :span="24">
              <el-form-item label="13%专票服务费" :required="true">
                <!-- <el-radio v-model="radio" label="1" style="width:100%">
                  <el-input style="width:80%" v-model="ruleForm.specialInvoice13" @change="handleChange" :step="0.01"
                    :precision="2" :min="0" :max="13"></el-input>
                </el-radio> -->
                  <div style="">
                   <el-radio
                   disabled
                     v-model="specialInvoice13"  label="1">按定额收取</el-radio>
                     <el-radio
                     disabled
                      v-model="specialInvoice13"  label="2" >按百分比收取</el-radio>
               
               <el-input v-if="specialInvoice13==1"
               style="width:100%"
               :readonly="true"
                 type="number"  v-model="ruleForm.specialInvoice13Money" @change="handleChange"
                    :step="0.01" :min="0" :max="9999">
                  <template slot="append">元</template>     
                </el-input>
                 <el-input :readonly="true" v-else type="number" style="width:100%"   v-model="ruleForm.specialInvoice13" @change="handleChange"
                    :step="0.01" :min="0" :max="13">
                  <template slot="append">%</template>     
                </el-input>
                 </div>
                 </el-form-item>
              </el-col>
             
            </el-row>
            <el-form-item label="个体户注册服务费" prop="specialSelfFee">
               <el-row>
                   <el-col :span="24">
                        <el-input :readonly="true" type="number" :step="0.1" :min="0" :max="9999" v-model="ruleForm.specialSelfFee">
                             <template slot="append">元</template>
                        </el-input>
                     </el-col>
               </el-row>
              
         </el-form-item>
          <el-form-item label="服务费含税" prop="isSpecialTax">
            <el-radio disabled v-model="ruleForm.isSpecialTax" label='0'>是</el-radio>
            <el-radio disabled v-model="ruleForm.isSpecialTax" label='1'>否</el-radio>
          </el-form-item>
        </el-card>
        <!-- 卡3 -->
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>增值税普通发票</span>
          </div>
           <el-row type="flex" justify="flex-end">
              <el-col :span="24">
              <el-form-item label="普票服务费" :required="true">
                <!-- <el-radio v-model="radio" label="1" style="width:100%">
                  <el-input style="width:80%" v-model="ruleForm.specialInvoice13" @change="handleChange" :step="0.01"
                    :precision="2" :min="0" :max="13"></el-input>
                </el-radio> -->
                  <div style="">
                   <el-radio
                   disabled
                     v-model="ordinaryProxyFee"  label="1">按定额收取</el-radio>
                     <el-radio
                     disabled
                      v-model="ordinaryProxyFee"  label="2">按比例收取</el-radio>
               
               <el-input v-if="ordinaryProxyFee==1"
               style="width:100%"
                :readonly="true"
                 type="number"  v-model="ruleForm.ordinaryProxyMoney" @change="handleChange"
                    :step="0.01" :min="0" :max="9999">
                  <template slot="append">元</template>     
                </el-input>
                 <el-input :readonly="true" v-else type="number" style="width:100%"   v-model="ruleForm.ordinaryProxyFee" @change="handleChange"
                    :step="0.01" :min="0" :max="13">
                  <template slot="append">%</template>     
                </el-input>
                 </div>
                 </el-form-item>
              </el-col>
             
            </el-row>

               <el-row type="flex" justify="flex-end">
              <el-col :span="24">
              <el-form-item label="专票服务费" :required="true">
                <!-- <el-radio v-model="radio" label="1" style="width:100%">
                  <el-input style="width:80%" v-model="ruleForm.specialInvoice13" @change="handleChange" :step="0.01"
                    :precision="2" :min="0" :max="13"></el-input>
                </el-radio> -->
                  <div >
                   <el-radio
                   disabled
                     v-model="specialProxyFee"  label="1">按定额收取</el-radio>
                     <el-radio
                     disabled
                      v-model="specialProxyFee"  label="2">按百分比收取</el-radio>
               
               <el-input v-if="specialProxyFee==1"
               style="width:100%"
               :readonly="true"
                 type="number"  v-model="ruleForm.specialProxyMoney" @change="handleChange"
                    :step="0.01" :min="0" :max="9999">
                  <template slot="append">元</template>     
                </el-input>
                 <el-input 
                 :readonly="true"
                 v-else type="number" style="width:100%"   v-model="ruleForm.specialProxyFee" @change="handleChange"
                    :step="0.01" :min="0" :max="13">
                  <template slot="append">%</template>     
                </el-input>
                 </div>
                 </el-form-item>
              </el-col>
             
            </el-row>



           <el-row type="flex" justify="flex-end">

              <el-col :span="24">
              <el-form-item label="个体户注册服务费" prop="ordinarySelfFee">
                  <el-input :readonly="true" type="number"   v-model="ruleForm.ordinarySelfFee" @change="handleChange" :step="0.1" :precision="1"
              :min="0" :max="9999" style="width:100%">
                 <template slot="append">元</template>
               </el-input>
               </el-form-item>
              </el-col>
               
             </el-row>

          
          <el-form-item label="服务费含税" prop="isOrdinaryTax">
            <el-radio disabled v-model="ruleForm.isOrdinaryTax" label='0'>是</el-radio>
            <el-radio disabled v-model="ruleForm.isOrdinaryTax" label='1'>否</el-radio>
          </el-form-item>
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
    return callback(new Error('个体代办费不能为空'))
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
      editSpecialProxyFee:'2',
      editOrdinaryProxyFee:'2',
      editSpecialInvoice13:'2',
      editSpecialInvoice6:'2',
      ordinaryProxyFee:'2',
      specialInvoice13:'2',
      specialInvoice6:'2',
      specialProxyMoney:'2',
      specialProxyFee:'2',
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
        placeCode: '',
        placeName: '',
        placeLinkman: '',
        placeTel: '',
        userId: 26,
        userName: '',

        specialProxyMoney:0,
        specialProxyFee:0,

        specialInvoice13Money:0, //  专票 13 元
        specialInvoice6Money:0, //  专票6 元
        specialInvoice6: 0,   //专票 6 （%）
        specialInvoice13: 0,  //专票13 （%）
        specialSelfFee: 0,
        isSpecialTax: '',//是否含税-专票
        ordinaryProxyMoney:0,//普票平台服务费(元）
        ordinarySelfFee: 0,  
        ordinaryProxyFee: 0, //普票平台服务费(%）
        isOrdinaryTax: '',//是否含税-普票

        //编辑参数
        editPlaceId: '',
        editPlaceCode: '',
        editPlaceName: '',
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

        editOrdinarySelfFee: '',
        editOrdinaryProxyFee: '',
        editOrdinaryProxyMoney: '',
        editIsOrdinaryTax: '',//是否含税-普票
      },
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
        placeName: [
          { required: true, message: '请输入渠道商名', trigger: 'blur' }
        ],
        placeLinkman: [
          { required: true, message: '请输入联系人', trigger: 'blur' }
        ],
        placeTel: [
          { validator: contactPhone, required: true, trigger: 'blur' }
        ],
        userId: [
          { required: true, message: '请选择业务经理', trigger: 'change' }
        ],
        specialInvoice6: [
          { required: true, message: '请输入6%专票代开费率', trigger: 'blur' }
        ],
         specialInvoice6: [
          { required: true, message: '请输入6%专票代开费率', trigger: 'blur' }
        ],
        specialInvoice13: [
          { required: true, message: '请输入13%专票代开费率', trigger: 'blur' }
        ],
        // specialSelfFee: [
        //     {validator: numCheck,required: true, trigger: 'blur' }
        // ],
        isSpecialTax: [
          { required: true, message: '请选择是否含税', trigger: 'change' }
        ],
        // ordinarySelfFee: [
        //     { validator: numCheck,required: true, trigger: 'blur' }
        // ],
        ordinaryProxyFee: [
          { required: true, message: '请输入普票代开收费', trigger: 'blur' }
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
      this.specialProxyFee='2';
      this.ordinaryProxyFee='2';
      this.specialInvoice13='2';
      this.specialInvoice6='2';
      this.ruleForm.placeName = null;
      this.ruleForm.placeLinkman = null;
      this.ruleForm.placeTel = null;
      this.ruleForm.userId = null;
      this.ruleForm.userName = null;
      this.ruleForm.specialInvoice6 = '';
      this.ruleForm.specialInvoice13 = '';
      this.ruleForm.specialInvoice6Money = '';
      this.ruleForm.specialInvoice13Money = '';
      this.ruleForm.specialSelfFee = null;
      this.ruleForm.isSpecialTax = null;
      this.ruleForm.ordinarySelfFee = null;
      this.ruleForm.ordinaryProxyFee = '';
      this.ruleForm.ordinaryProxyMoeny = '';
      this.ruleForm.isOrdinaryTax = null;
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
      console.log(this.multipleSelection);
      // this.ids = selection.map((item) => item.selfId);
      // this.single = selection.length !== 1;
      // this.multiple = !selection.length;
    },

    handleChange(value) {
      console.log(value);
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();

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

     if(res.specialInvoice6>0){
        this.specialInvoice6='2';
      }else if(res.specialInvoice6Money>0){
        this.specialInvoice6='1';
      }
      if(res.specialInvoice13>0){
        this.editSpecialInvoice13='2';
      }else if(res.specialInvoice13Money>0){
        this.editSpecialInvoice13='1';
      }
      if(res.ordinaryProxyFee>0){
        this.editOrdinaryProxyFee='2';
      }else if(res.ordinaryProxyMoney>0){
        this.editOrdinaryProxyFee='1';
      }



        this.ruleForm.editAgencyFeeId = res.agencyFeeId;
        this.ruleForm.editSpecialInvoice6 = res.specialInvoice6;
        this.ruleForm.editSpecialInvoice13 = res.specialInvoice13;
        
        this.ruleForm.editSpecialInvoice6Money = res.specialInvoice6Money; //专票6 元
        this.ruleForm.editSpecialInvoice13Money = res.specialInvoice13Money; //专票13 元 
        this.ruleForm.editOrdinaryProxyMoney = res.ordinaryProxyMoney; //定额服务费 

        this.ruleForm.editSpecialSelfFee = res.specialSelfFee;
        this.ruleForm.editIsSpecialTax = res.isSpecialTax;
        this.ruleForm.editOrdinarySelfFee = res.ordinarySelfFee;
        this.ruleForm.editOrdinaryProxyFee = res.ordinaryProxyFee;
        this.ruleForm.editIsOrdinaryTax = res.isOrdinaryTax;

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
      this.ruleForm.userName=item.userName;
    
      agencyfee.selectFeeByCode({ placeCode: this.ruleForm.placeCode }).then(res => {
        console.log("selectFeeByCode==", res);
       
       if(res.specialInvoice6>0){
        this.specialInvoice6='2';
      }else if(res.specialInvoice6Money>0){
        this.specialInvoice6='1';
      }
      if(res.specialInvoice13>0){
        this.specialInvoice13='2';
      }else if(res.specialInvoice13Money>0){
        this.specialInvoice13='1';
      }
      if(res.ordinaryProxyFee>0){
        this.ordinaryProxyFee='2';
      }else if(res.ordinaryProxyMoney>0){
        this.ordinaryProxyFee='1';
      }

       if(res.specialProxyFee>0){
        this.specialProxyFee='2';
      }else if(res.specialProxyMoney>0){
        this.specialProxyFee='1';
      }
       
       
        this.ruleForm.specialProxyMoney = res.specialProxyMoney;
        this.ruleForm.specialProxyFee = res.specialProxyFee;


        this.ruleForm.specialInvoice6 = res.specialInvoice6;
        this.ruleForm.specialInvoice13 = res.specialInvoice13;
        this.ruleForm.specialInvoice6Money = res.specialInvoice6Money;
        this.ruleForm.specialInvoice13Money = res.specialInvoice13Money;
        this.ruleForm.specialSelfFee = res.specialSelfFee;
        this.ruleForm.isSpecialTax = res.isSpecialTax;
        this.ruleForm.ordinarySelfFee = res.ordinarySelfFee;
        this.ruleForm.ordinaryProxyFee = res.ordinaryProxyFee;
        this.ruleForm.ordinaryProxyMoney = res.ordinaryProxyMoney;
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
        console.log("getAllUser", res)
      })
    },


    selectUser(value) {
      console.log(value);
      // this.ruleForm.userName='测试啊';
      // this.ruleForm.editUserName='111';
      this.ruleForm.userName = this.userLeaders.find((item) => item.userId == value).nickName;
      this.ruleForm.editUserName = this.userLeaders.find((item) => item.userId == value).nickName;
    },
    /** 提交按钮 */
    submitForm() {
     
       if(this.specialInvoice6=='1'){
          this.ruleForm.specialInvoice6=0;
        if(this.ruleForm.specialInvoice6Money<=0 ){
              this.$modal.msgError("请输入6%专票服务费并且大于0");
              return;
        }
       }else{
         this.ruleForm.specialInvoice6Money=0;
         if(this.ruleForm.specialInvoice6<=0 ){
              this.$modal.msgError("请输入6%专票服务费并且大于0");
              return;
        }
       }
        if(this.specialInvoice13=='1'){
          this.ruleForm.specialInvoice13=0;
           if(this.ruleForm.specialInvoice13Money<=0 ){
              this.$modal.msgError("请输入13%专票服务费并且大于0");
              return;
            }
       }else{
         this.ruleForm.specialInvoice6Money=0;
          if(this.ruleForm.specialInvoice13<=0){
              this.$modal.msgError("请输入13%专票服务费并且大于0");
              return;
         }
       }
        if(this.ordinaryProxyFee=='1'){
          this.ruleForm.ordinaryProxyFee=0;
          if(this.ruleForm.ordinaryProxyMoney<=0){
              this.$modal.msgError("请输入普票服务费并且大于0");
              return;
           }
       }else{
         this.ruleForm.ordinaryProxyMoney=0;
          if(this.ruleForm.ordinaryProxyFee<=0){
              this.$modal.msgError("请输入普票服务费并且大于0");
              return;
           }
       }
       
         if(this.specialProxyFee=='1'){
          this.ruleForm.specialProxyFee=0;
          if(this.ruleForm.specialProxyMoney<=0){
              this.$modal.msgError("请输入专票服务费并且大于0");
              return;
           }
       }else{
         this.ruleForm.specialProxyMoney=0;
          if(this.ruleForm.specialProxyFee<=0){
              this.$modal.msgError("请输入专票服务费并且大于0");
              return;
           }
       }
     
      
      
      this.$refs["form"].validate((valid) => {
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
              placeLinkman: this.ruleForm.placeLinkman,
              placeTel: this.ruleForm.placeTel,
              userId: this.ruleForm.userId,
              userName: this.ruleForm.userName,
            },
            businessAgencyFee: {
              placeCode: this.ruleForm.placeCode,

              specialInvoice6Money: this.ruleForm.specialInvoice6Money,
              specialInvoice13Money: this.ruleForm.specialInvoice13Money,
              ordinaryProxyMoney:this.ruleForm.ordinaryProxyMoney,
              ordinaryProxyFee: this.ruleForm.ordinaryProxyFee,
              specialProxyMoney:this.ruleForm.specialProxyMoney,
              specialProxyFee: this.ruleForm.specialProxyFee,
              
              specialInvoice6: this.ruleForm.specialInvoice6,
              specialInvoice13: this.ruleForm.specialInvoice13,
              specialSelfFee: this.ruleForm.specialSelfFee,
              isSpecialTax: this.ruleForm.isSpecialTax,
              ordinarySelfFee: this.ruleForm.ordinarySelfFee,
             
              isOrdinaryTax: this.ruleForm.isOrdinaryTax,
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
          });
          this.addVisible = false;
          this.getPlaceCode();//重新在获取一遍编号（避免编号重复）
          this.$tab.refreshPage();
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
       if(this.editSpecialInvoice6=='1'){
          this.ruleForm.editSpecialInvoice6=0;
        if(this.ruleForm.editSpecialInvoice6Money<=0 ){
              this.$modal.msgError("请输入6%专票平台服务费并且大于0");
              return;
        }
       }else{
         this.ruleForm.editSpecialInvoice6Money=0;
         if(this.ruleForm.editSpecialInvoice6<=0 ){
              this.$modal.msgError("请输入6%专票平台服务费并且大于0");
              return;
        }
       }
        if(this.editSpecialInvoice13=='1'){
          this.ruleForm.editSpecialInvoice13=0;
           if(this.ruleForm.editSpecialInvoice13Money<=0 ){
              this.$modal.msgError("请输入13%专票服务费并且大于0");
              return;
            }
       }else{
         this.ruleForm.editSpecialInvoice6Money=0;
          if(this.ruleForm.editSpecialInvoice13<=0){
              this.$modal.msgError("请输入13%专票服务费并且大于0");
              return;
         }
       }
        if(this.editOrdinaryProxyFee=='1'){
          this.ruleForm.editOrdinaryProxyFee=0;
          if(this.ruleForm.editOrdinaryProxyMoney<=0){
              this.$modal.msgError("请输入普票服务费并且大于0");
              return;
           }
       }else{
         this.ruleForm.editOrdinaryProxyMoney=0;
          if(this.ruleForm.editOrdinaryProxyFee<=0){
              this.$modal.msgError("请输入普票服务费并且大于0");
              return;
           }
       }

         if(this.editSpecialProxyFee=='1'){
          this.ruleForm.editSpecialProxyFee=0;
          if(this.ruleForm.specialProxyMoney<=0){
              this.$modal.msgError("请输入专票服务费并且大于0");
              return;
           }
       }else{
         this.ruleForm.editSpecialProxyMoney=0;
          if(this.ruleForm.editSpecialProxyFee<=0){
              this.$modal.msgError("请输入专票服务费并且大于0");
              return;
           }
       }
        this.$refs['form'].validate((valid) => {
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
            placeLinkman: this.ruleForm.editPlaceLinkman,
            placeTel: this.ruleForm.editPlaceTel,
            userId: this.ruleForm.editUserId,
            userName: this.ruleForm.editUserName,

            specialInvoice6: this.ruleForm.editSpecialInvoice6,
            specialInvoice13: this.ruleForm.editSpecialInvoice13,
            specialInvoice6Money: this.ruleForm.editSpecialInvoice6Money,   //6元
            specialInvoice13Money: this.ruleForm.editSpecialInvoice13Money,  //13元
            specialSelfFee: this.ruleForm.editSpecialSelfFee,
            isSpecialTax: this.ruleForm.editIsSpecialTax,

            ordinarySelfFee: this.ruleForm.editOrdinarySelfFee,
            
            specialProxyMoney:this.ruleForm.editSpecialProxyMoney,
            specialProxyFee: this.ruleForm.editSpecialProxyFee,
           
            ordinaryProxyFee: this.ruleForm.editOrdinaryProxyFee,
            ordinaryProxyMoney: this.ruleForm.editOrdinaryProxyMoney, //服务费元
            isOrdinaryTax: this.ruleForm.editIsOrdinaryTax,
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

      if (confirm('你确定删除吗？')) {
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
      }

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
::v-deep  .el-input__inner {
  background-color: transparent !important;
  color: black;
  border-color:none !important;
}
::v-deep .is-disabled{
    color: black;
 
}
::v-deep .el-radio__input.is-disabled + span.el-radio__label{ 
   background-color: transparent !important;
  color: black  !important;
}

  ::v-deep .el-radio{
   background-color: #FFFFFF !important;
}
::v-deep .el-radio__input.is-checked .el-radio__inner{
  border-color: #1890ff !important;
    background: #1890ff !important;
}

::v-deep .el-dialog:not(.is-fullscreen) {
  margin-top: 1vh !important;
}
</style>

