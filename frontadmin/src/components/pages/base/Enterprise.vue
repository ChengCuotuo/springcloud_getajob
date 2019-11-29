<template>
  <!--
       企业管理
       使用网格显示企业的基本信息，允许对企业的信息进行增加、删除、修改
   -->
  <div>
    <el-container>
        <el-header style="padding: 0 40px 0 40px;display:flex;justify-content:space-between;align-items: center; background:white">
            <div style="display: inline">
                <!-- 搜索框，根据企业的名称进行模糊搜索企业的信息
                    clearSearch 当输入框清空的时候自动搜索
                    enterpriseSearchViewVisible 搜索框是否允许使用
                    searchEnterprise 按下回车键时候搜索方法
                -->
                <el-input
                    placeholder="通过企业名称查询,记得回车哦..."
                    clearable
                    @change="clearSearch"
                    style="width: 300px;margin: 0px;padding: 0px;"
                    size="mini"
                    :disabled="enterpriseSearchViewVisible"
                    @keyup.enter.native="searchEnterprise"
                    prefix-icon="el-icon-search"
                    v-model="keywords"
                    maxlength="100">
                </el-input>
                <el-button type="primary" size="mini" style="margin-left: 5px" icon="el-icon-search" @click="searchEnterprise">搜索
                </el-button>
            </div>
            <div style="margin-left: 5px;margin-right: 20px;display: inline">
                <el-button type="danger" size="mini" v-if="enterpriseList.length > 0" :disabled="multipleSelection.length==0"
                     @click="deleteManyEnterprises">批量删除
                </el-button>
                <el-button type="primary" size="mini" 
                    @click="exportEnterprise()">
                    导出企业信息
                </el-button>
                <el-button type="primary" size="mini" icon="el-icon-plus"
                    @click="showAddEnterpriseView()">
                    添加企业资料
                </el-button>
            </div>
         </el-header>
        <el-main style="padding-left: 0px;padding-top: 0px">
        <div>
           <el-table
            size="mini"
            :data="enterpriseList"
            v-loading="tableLoading"
            border
            stripe
            @selection-change="handleSelectionChange"
            style="width: 100%"
          >
            <el-table-column prop="id" type="selection" align="left"></el-table-column>
            <el-table-column prop="name" align="left" fixed label="企业名称" width="100"></el-table-column>
            <el-table-column prop="lev" align="left" fixed label="等级" width="100"></el-table-column>
            <el-table-column prop="trademark" align="left"  label="商标" width="100"></el-table-column>
            <el-table-column prop="website" align="left" label="官网地址" width="200"></el-table-column>
            <el-table-column prop="address" align="left" label="地址" width="200"></el-table-column>
            <el-table-column prop="lables" align="left" label="企业标签" width="180"></el-table-column>
            <el-table-column prop="des" align="left" label="企业描述" width="500"></el-table-column>
            <el-table-column fixed="right" label="操作" width="80">
              <template slot-scope="scope">
                <el-button
                  @click="showEditEnterpriseView(scope.row)"
                  style="padding: 3px 4px 3px 4px;margin: 2px"
                  size="mini"
                >编辑</el-button>
                </template>
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="80">
              <template slot-scope="scope">
                <el-button
                  type="danger"
                  style="padding: 3px 4px 3px 4px;margin: 2px"
                  size="mini"
                  @click="deleteEmpPrize(scope.row)"
                >删除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="display: flex;justify-content: space-between;margin: 2px">
            <!-- <el-button type="danger" size="mini" v-if="emps.length>0" :disabled="multipleSelection.length==0"
                       @click="deleteManyEmps">批量删除
            </el-button>-->
            <el-pagination
              background
              :page-size="10"
              :current-page="currentPage"
              @current-change="currentChange"
              layout="prev, pager, next"
              :total="totalCount"
            ></el-pagination>
          </div>
        </div>
      </el-main>
    </el-container>
    <!-- 添加企业信息的浮窗 
        对应的数据内容是 enterprise
        使用的规则是 rules
    -->
    <el-form :model="enterprise" 
      :rules="rules" 
      ref="addEnterpriseForm" 
      style="margin: 0px;padding: 0px;"
      >
      <div style="text-align: center">
          <!-- 
            close-on-click-modal
            visible.sync 通过变量控制是否显示
            before-close 关闭之前执行的操作
            -->
        <el-dialog 
          :title="title"
          style="padding: 0px;"
          :close-on-click-modal="false"
          :visible.sync="enterpriseFormVisible"
          :before-close="cancleAdd"    
          width="50%">
          <el-row style="margin-top: 10px;padding: 0px;">
            <el-col :span="15">
              <div>
                <el-form-item label="企业名称:" prop="name">
                   <el-input prefix-icon="el-icon-edit" v-model="enterprise.name" size="mini" style="width: 300px"
                            placeholder="输入企业名称"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15">
              <div>
                <el-form-item label="企业等级:" prop="lev">
                    <el-select v-model="enterprise.lev" @change="getSelected"  style="width: 300px" size="mini" placeholder="企业等级">
                        <el-option
                            v-for="item in enterpriseLevel"
                            :key="item.label"
                            :label="item.value"
                            :value="item.label">
                        </el-option>
                    </el-select>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15">
              <div>
                <el-form-item label="官方网址:" prop="website">
                   <el-input prefix-icon="el-icon-edit" v-model="enterprise.website" size="mini" style="width: 300px"
                            placeholder="输入官方网址"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15">
              <div>
                 <el-form-item label="企业标签:" prop="lables">
                   <el-input prefix-icon="el-icon-edit" v-model="enterprise.lables" size="mini" style="width: 300px"
                            placeholder="输入企业标签逗号隔开"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="20">
              <div>
                  <el-form-item label="企业描述:" prop="des">
                    <el-input type="textarea" prefix-icon="el-icon-edit" v-model="enterprise.des" size="mini" style="width: 500px"
                            placeholder="输入企业描述信息"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <span slot="footer" >
            <el-button size="mini" @click="cancleAdd()">取 消</el-button>
            <el-button size="mini" type="primary" @click="addEnterprise('addEnterpriseForm')">确 定</el-button>
          </span>
        </el-dialog>
      </div>
    </el-form>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        enterprise: {
            address:'',
            des:'',
            id:'',
            lables: '',
            lev:'',
            name: '',
            trademark:'',
            website:''
        },
        rules:[], // 添加企业信息需要遵守的规则
        enterpriseList: [], // 存储所有的企业信息
        startPage: 1, // 翻页的起始值
        tableLoading: '', 
        totalCount: '', // 查询到的总条数
        multipleSelection:[], // 处理多选数据
        keywords: '', // 模糊搜索输入框中的内容
        enterpriseSearchViewVisible: false, // 默认搜索框是允许使用的
        enterpriseFormVisible: false, // 添加企业信息的浮窗，默认不显示
        enterpriseLevel: [{label: 1, value: '蓝色企业'}, {label: 2, value: '绿色企业'},{label: 3, value: '灰色企业'}],
        title: '', // 当前浮窗的表头
        addOrEdit: '', // 标记当前是添加还是编辑企业信息 1 添加 2 编辑
      };
    },
    mounted: function () {
        this.getEnterpriseList();
    },
    methods: {
      currentChange(currentPage) {
        this.startPage = currentPage;
        this.getEnterpriseList();
      },
      // 导出企业信息
      exportEnterprise() {
        window.open("/enterprise/exportenterprises", "_parent");
        this.$message({ type: "success", message: "正在导出！" });
      },
      getEnterpriseList() {
          var data = {
              enterpriseName: this.keywords,
              start: (this.startPage - 1) * 10 ,
              size: 10
          }
          var _this = this;
          this.tableLoading = true;
          this.postRequest("/enterprise/getallenterprise", data).then(resp=> {           
            if (resp && resp.status == 200) {
              _this.enterpriseList = resp.data.obj.enterpriseList;
              _this.totalCount = resp.data.obj.totalCount;
              console.log(_this.totalCount);
              _this.tableLoading = false;
            }
          })
      },
      // 删除企业信息
      deleteEmpPrize(val) {
        console.log(val)
        var _this = this;
        this.$confirm(
          "此操作将永久删除[" + val.name + "企业信息], 是否继续?",
          "提示",
          {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }
        ).then(() => {
          this.deleteRequest("/enterprise/deleteenterprisebyid/" + val.id).then(resp => {
            if (resp && resp.status == 200) {
              _this.$message({ type: "success", message: "删除成功！" });
              _this.getEnterpriseList();
            }
          })
        })
      },
      // 批量删除信息
      deleteManyEnterprises() {
        var _this = this;
        this.$confirm(
          "此操作将永久删除[" + this.multipleSelection.length + "条企业信息], 是否继续?",
          "提示",
          {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }
        ).then(() => {
          var ids = '';
          for (var i = 0; i < this.multipleSelection.length; i++) {
            ids = ids + this.multipleSelection[i].id + ",";
          }
          console.log(ids);
          this.deleteRequest("/enterprise/deleteenterprises", ids).then(resp => {
            if (resp && resp.status == 200) {
              _this.$message({ type: "success", message: "删除成功！" });
              _this.getEnterpriseList();
            }
          })
        })
      },
      // 处理复选框的选择
      getSelected(val) {
        for(let i = 0; i < this.enterpriseLevel.length; i++){
          if(this.enterpriseLevel[i].value == val){
            this.enterprise.lev = this.enterpriseLevel[i].value;
          }
        }
        console.log(this.enterprise.lev);
      },
      // 搜索输入框为空的时候自动搜索所有的信息
      clearSearch(val){
        if (val == '') {
            this.getEnterpriseList();
        }
      },
      // 点击搜索按钮或者在搜索框中回车进行的搜索
      searchEnterprise() {
          this.startPage = 1;
          this.getEnterpriseList();
      },
      // 取消添加企业信息
      cancleAdd() {
        this. enterprise = {
            address:'',
            des:'',
            id:'',
            lables: '',
            lev:'',
            name: '',
            trademark:'',
            website:''
        }
        this.enterpriseFormVisible = false;
      },
      // 添加企业信息
      addEnterprise(formName) {
        var _this = this;
        console.log(formName);
        this.$refs[formName].validate((valid) => {
          // 验证成功
          if (valid) {
            // 开始加载
            _this.tableLoading = true;
            // addOrEdit = 1 表示添加企业信息 2 表示修改
            var url = '';
            var message = '';
            if (_this.addOrEdit === 1) {
              url = "/enterprise/addenterprise";
              message = "添加成功";
            } else {
              url = "/enterprise/updateenterprise";
              message = "更新成功";
            }

            _this.postRequest(url, this.enterprise).then(resp => {
              if (resp && resp.status == 200) {
                // 数据添加成功之后需要重新加载数据
                _this.enterpriseFormVisible = false;
                _this.$message({ type: "success", message: message });
                _this.getEnterpriseList();
              }
            })
          }
        });
      },
      // 处理批量多选
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      // 显示添加企业信息的浮窗
      showAddEnterpriseView() {
          this.$refs['addEnterpriseForm'].clearValidate() // 重置验证
          this.enterpriseFormVisible = true;
          this.title = "添加企业信息";
          this.addOrEdit = 1;
      },
      // 显示修改企业信息的浮窗
      showEditEnterpriseView(val) {
        this.enterprise = val
        this.$refs['addEnterpriseForm'].clearValidate() // 重置验证
        this.enterpriseFormVisible = true;
        this.title = "编辑企业信息";
        this.addOrEdit = 2;
      },
    }
  };
</script>
<style>
.el-dialog__body {
  padding-top: 0px;
  padding-bottom: 0px;
}

.slide-fade-enter-active {
  transition: all 0.8s ease;
}
.slide-fade-leave-active {
  transition: all 0.8s cubic-bezier(1, 0.5, 0.8, 1);
}

.slide-fade-enter,
.slide-fade-leave-to {
  transform: translateX(10px);
  opacity: 0;
}
</style>
