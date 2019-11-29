<template>
  <!--
       导师管理
   -->
  <div>
    <el-container>
        <el-header style="padding: 0 40px 0 40px;display:flex;justify-content:space-between;align-items: center; background:white">
          <div style="display: inline">
              <!-- 搜索框，根据导师的名称进行模糊搜索导师的信息
                  clearSearch 当输入框清空的时候自动搜索
                  enterpriseSearchViewVisible 搜索框是否允许使用
                  searchEnterprise 按下回车键时候搜索方法
              -->
              <el-input
                  placeholder="通过导师名称查询,记得回车哦..."
                  clearable
                  @change="clearSearch"
                  style="width: 300px;margin: 0px;padding: 0px;"
                  size="mini"
                  :disabled="tutorSearchViewVisible"
                  @keyup.enter.native="searchTutor"
                  prefix-icon="el-icon-search"
                  v-model="keywords"
                  maxlength="100">
              </el-input>
              <el-button type="primary" size="mini" style="margin-left: 5px" icon="el-icon-search" @click="searchTutor">搜索
              </el-button>
          </div>
          <div style="margin-left: 5px;margin-right: 20px;display: inline">
            <!-- 当选中的数据 -->
            <el-button type="danger" size="mini" v-if="tutorList.length > 0" :disabled="multipleSelection.length==0"
                  @click="deleteManyTutors">批量删除
            </el-button>
            <el-button type="primary" size="mini" 
                    @click="exportTutor()">
                    导出导师信息
                </el-button>
            <el-button type="primary" size="mini" icon="el-icon-plus"
                @click="showAddTutorView()">
                添加导师资料
            </el-button>
          </div>
        </el-header>
       <el-main style="padding-left: 0px;padding-top: 10px">
        <div>
           <el-table
            :data="tutorList"
            v-loading="tableLoading"
            border
            stripe
            @selection-change="handleSelectionChange"
            size="mini"
            style="width: 100%"
          >
            <el-table-column prop="id" type="selection" align="left" width></el-table-column>
            <el-table-column prop="identifier" align="left" fixed label="验证信息" width="120"></el-table-column>
            <el-table-column prop="username" align="left" fixed label="用户名" width="120"></el-table-column>
            <el-table-column prop="cname" align="left" fixed label="中文名" width="120"></el-table-column>
            <el-table-column prop="gender" align="left" label="性别" width="50"></el-table-column>
            <el-table-column prop="job" align="left"  label="目前工作" width="100"></el-table-column>
            <el-table-column prop="lev" align="left" label="等级" width="50"></el-table-column>
            <el-table-column prop="tel" align="left" label="联系电话" width="100"></el-table-column>
            <el-table-column prop="sch" align="left" label="所在院校" width="100"></el-table-column>
            <el-table-column prop="email" align="left" label="邮箱" width="150"></el-table-column>
            <el-table-column prop="edu" align="left" label="学历" width="100"></el-table-column>  
            <el-table-column prop="pol" align="left" label="政治面貌" width="100"></el-table-column>
            <el-table-column prop="des" align="left" label="自我描述" width="500"></el-table-column>
            <el-table-column fixed="right" label="操作" width="80">
              <template slot-scope="scope">
                <el-button
                  @click="showEditTutorView(scope.row)"
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
                  @click="deleteTutor(scope.row)"
                >删除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="display: flex;justify-content: space-between;margin: 2px">
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
    <!-- 添加导师信息的浮窗 -->
    <el-form :model="tutor" 
      :rules="rules" 
      ref="addTutorForm" 
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
          :visible.sync="tutorFormVisible"
          :before-close="cancleAddTutor"    
          width="50%">
          <el-row style="margin-top: 10px;padding: 0px;">
            <el-col :span="15">
              <div>
                <el-form-item label="中文名" prop="cname">
                   <el-input prefix-icon="el-icon-edit" v-model="tutor.cname" size="mini" style="width: 300px"
                          placeholder="中文名"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15">
              <div>
                <el-form-item label="性别:" prop="gender">
                    <el-select v-model="tutor.gender" @change="SelecteGender"  style="width: 300px" size="mini" placeholder="性别">
                        <el-option
                            v-for="item in genders"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
          <el-col :span="15">
              <div>
                <el-form-item label="工作" prop="job">
                   <el-input prefix-icon="el-icon-edit" v-model="tutor.job" size="mini" style="width: 300px"
                          placeholder="工作"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15">
              <div>
                <el-form-item label="等级:" prop="lev">
                    <el-select v-model="tutor.lev" @change="SelecteTutorLev"  style="width: 300px" size="mini" placeholder="等级">
                        <el-option
                            v-for="item in tutorLevel"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
          <el-col :span="15">
              <div>
                <el-form-item label="院校" prop="sch">
                   <el-input prefix-icon="el-icon-edit" v-model="tutor.sch" size="mini" style="width: 300px"
                          placeholder="院校"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
          <el-col :span="15">
              <div>
                <el-form-item label="手机号" prop="tel">
                   <el-input prefix-icon="el-icon-edit" v-model="tutor.tel" size="mini" style="width: 300px"
                          placeholder="手机号"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
          <el-col :span="15">
              <div>
                <el-form-item label="邮箱" prop="email">
                   <el-input prefix-icon="el-icon-edit" v-model="tutor.email" size="mini" style="width: 300px"
                          placeholder="邮箱"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
          <el-col :span="15">
              <div>
                <el-form-item label="学历" prop="edu">
                   <el-input prefix-icon="el-icon-edit" v-model="tutor.edu" size="mini" style="width: 300px"
                          placeholder="学历"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
          <el-col :span="15">
              <div>
                <el-form-item label="政治面貌" prop="pol">
                   <el-input prefix-icon="el-icon-edit" v-model="tutor.pol" size="mini" style="width: 300px"
                          placeholder="政治面貌"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="20">
              <div>
                  <el-form-item label="自我描述:" prop="des">
                    <el-input type="textarea" prefix-icon="el-icon-edit" v-model="tutor.des" size="mini" style="width: 500px"
                            placeholder="自我描述"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <span slot="footer" >
            <el-button size="mini" @click="cancleAddTutor()">取 消</el-button>
            <el-button size="mini" type="primary" @click="addTutor('addTutorForm')">确 定</el-button>
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
          tutor: {
            id:'',
            userid: '', 
            identifier: '',
            cname: '',
            username:'',
            gender: '',
            account: '',
            job: '',
            lev: '',
            sch: '',
            tel: '',
            email: '',
            edu: '',
            photo: '',
            pol:'',   
            des:''
          },
          genders:[{label: '男', value: 1}, {label: '女', value: 2}], // 性别
          rules: [], // 添加学生信息需要遵守的规则
          tutorList: [], // 存储所有的企业信息
          startPage: 1, // 翻页的起始值
          tableLoading: '', // 信息加载缓冲
          totalCount: '', // 查询到的总条数
          tutorSearchViewVisible: false,
          multipleSelection: [], // 处理多选
          keywords: '', // 存储搜索框输入内容
          tutorFormVisible: false, // 添加导师信息的
          tutorLevel: [{value: 1, label: '蓝色伯乐'}, {value: 2, label: '绿色伯乐'},{value: 3, label: '灰色伯乐'}],
          title: '', // 浮窗的表头
          addOrEdit: '', // 表示是添加或是修改  1 添加 2 修改

      };
    },
    mounted: function () {
        this.getTutorList();
    },
    methods: {
      // 搜索输入框为空的时候自动搜索所有的信息
      clearSearch(val){
        if (val == '') {
            this.getTutorList();
        }
      },
      // 导出导师的信息
      exportTutor() {
        window.open("/tutor/exporttutors", "_parent");
        this.$message({ type: "success", message: "正在导出！" });
      },
      // 根据导师中文名模糊搜索
      searchTutor() {
        this.startPage = 1;
        this.getTutorList();
      },
      currentChange(currentPage) {
        this.startPage = currentPage;
        this.getTutorList();
      },
      getTutorList() {
          var data = {
            tutorName : this.keywords,
            start: (this.startPage - 1) * 10 ,
            size: 10
          }
          var _this = this;
          this.tableLoading = true;
          this.postRequest("/tutor/getalltutors", data).then(resp=> {           
              if (resp && resp.status == 200) {
                  _this.tutorList = resp.data.obj.tutorList;
                  for (var i = 0; i < _this.tutorList.length; i++) {
                    _this.tutorList[i].gender = _this.tutorList[i].gender == '1' ? '男' : '女';
                  }
                  _this.totalCount = resp.data.obj.totalCount;
                  console.log(_this.totalCount);
                  _this.tableLoading = false;
              }
          })
      },
      // 处理多选
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      // 取消添加导师信息
      cancleAddTutor() {
        this.tutor = {
            id:'',
            userid: '', 
            identifier: '',
            cname: '',
            username:'',
            gender: '',
            account: '',
            job: '',
            lev: '',
            sch: '',
            tel: '',
            email: '',
            edu: '',
            photo: '',
            pol:'',   
            des:''
          };
        this.tutorFormVisible = false;
      },
      // 下拉框选择性别
      selecteGender(val) {
        for(let i = 0; i < this.genders.length; i++){
          if(this.genders[i].value == val){
            this.tutor.gender = this.genders[i].value;
          }
        }
      },
      // 下拉框选择等级
      SelecteTutorLev(val) {
        for(let i = 0; i < this.tutorLevel.length; i++){
          if(this.tutorLevel[i].value == val){
            this.tutor.lev = this.tutorLevel[i].value;
          }
        }
      },
      // 显示添加导师信息浮窗
      showAddTutorView() {
        this.$refs['addTutorForm'].clearValidate() // 重置验证
        this.tutorFormVisible = true;
        this.title = "添加导师信息";
        this.addOrEdit = 1;
      },
      // 显示更新导师信息浮窗
      showEditTutorView(val) {
        this.$refs['addTutorForm'].clearValidate() // 重置验证
        this.tutorFormVisible = true;
        this.tutor = val;
        this.title = "修改导师信息";
        this.addOrEdit = 2;
      },
      // 添加导师信息
      addTutor(formName) {
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
              url = "/tutor/addtutor";
              message = "添加成功";
            } else {
              url = "/tutor/updatetutor";
              message = "更新成功";
            }

            _this.postRequest(url, this.tutor).then(resp => {
              if (resp && resp.status == 200) {
                // 数据添加成功之后需要重新加载数据
                _this.tutorFormVisible = false;
                _this.$message({ type: "success", message: message });
                _this.getTutorList();
              }
            })
          }
        });
      },
      // 删除导师信息
      deleteTutor(val) {
        var _this = this;
        this.$confirm(
          "此操作将永久删除[" + val.cname + "导师信息], 是否继续?",
          "提示",
          {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }
        ).then(() => {
          this.deleteRequest("/tutor/deletetutorbyid/" + val.id).then(resp => {
            if (resp && resp.status == 200) {
              _this.$message({ type: "success", message: "删除成功！" });
              _this.getTutorList();
            }
          })
        })
      },
      // 批量删除信息
      deleteManyTutors() {
        var _this = this;
        this.$confirm(
          "此操作将永久删除[" + this.multipleSelection.length + "条导师信息], 是否继续?",
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
          this.deleteRequest("/tutor/deletetutors", ids).then(resp => {
            if (resp && resp.status == 200) {
              _this.$message({ type: "success", message: "删除成功！" });
              _this.getTutorList();
            }
          })
        })
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
