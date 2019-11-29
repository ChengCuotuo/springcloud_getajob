<template>
  <!--
       学生信息查询
       ToDo: 点击显示学生对应的导师
   -->
  <div>
    <el-container>
       <el-header style="padding: 0 40px 0 40px;display:flex;justify-content:space-between;align-items: center; background:white">
          <div style="display: inline">
              <!-- 搜索框，根据学生的名称进行模糊搜索导师的信息
                  clearSearch 当输入框清空的时候自动搜索
                  enterpriseSearchViewVisible 搜索框是否允许使用
                  searchEnterprise 按下回车键时候搜索方法
              -->
              <el-input
                  placeholder="通过学生名称查询,记得回车哦..."
                  clearable
                  @change="clearSearch"
                  style="width: 300px;margin: 0px;padding: 0px;"
                  size="mini"
                  :disabled="studentSearchViewVisible"
                  @keyup.enter.native="searchStudent"
                  prefix-icon="el-icon-search"
                  v-model="keywords"
                  maxlength="100">
              </el-input>
              <el-button type="primary" size="mini" style="margin-left: 5px" icon="el-icon-search" @click="searchStudent">搜索
              </el-button>
          </div>
          <div style="margin-left: 5px;margin-right: 20px;display: inline">
            <!-- 当选中的数据 -->
            <el-button type="danger" size="mini" v-if="studentList.length > 0" :disabled="multipleSelection.length==0"
                  @click="deleteManyStudents">批量删除
            </el-button>
            <el-button type="primary" size="mini" 
                    @click="exportStudent()">
                    导出学生信息
                </el-button>
            <el-button type="primary" size="mini" icon="el-icon-plus"
                @click="showAddStudentView()">
                添加学生资料
            </el-button>
            <el-badge :value="totalWaitCount">
              <el-button size="small" @click="showWaitStorageStudent" >待入库学生</el-button>
            </el-badge>
          </div>
        </el-header>
        <!-- 抽屉 -->
        <el-drawer
          title="待入库学生"
          :visible.sync="drawer"
          :direction="direction"
          :before-close="handleClose"
          size="80%">
          <div style="padding: 0 10px 20px 10px;">
           <el-table :data="waitStorageStudentList" v-loading="tableLoading" border stripe
              size="mini" style="width: 100%">

            <el-table-column prop="identifier" align="left" fixed label="验证信息" width="120"></el-table-column>
            <el-table-column prop="username" align="left" fixed label="用户名" width="120"></el-table-column>
            <el-table-column prop="cname" align="left" fixed label="中文名" width="120"></el-table-column>
            <el-table-column prop="idea" align="left" fixed label="求职意向" width="100"></el-table-column>
            <el-table-column prop="gender" align="left" label="性别" width="50"></el-table-column>
            <el-table-column prop="bir" align="left"  label="生日" width="100"></el-table-column>
            <el-table-column prop="tel" align="left" label="联系电话" width="100"></el-table-column>
            <el-table-column prop="email" align="left" label="邮箱" width="200"></el-table-column>
            <el-table-column prop="schtime" align="left" label="毕业时间" width="100"></el-table-column>
            <el-table-column prop="address" align="left" label="居住地" width="100"></el-table-column>
            <el-table-column prop="lev" align="left" label="等级" width="100"></el-table-column>
            <el-table-column prop="major" align="left" label="主修" width="100"></el-table-column>
            <el-table-column prop="schname" align="left" label="毕业院校" width="100"></el-table-column>
            <el-table-column prop="pol" align="left" label="政治面貌" width="100"></el-table-column>
            <el-table-column prop="ability" align="left" label="技能" width="300"></el-table-column>
            <el-table-column prop="des" align="left" label="自我描述" width="1000"></el-table-column>

            <el-table-column fixed="right" label="操作" width="80">
              <template slot-scope="scope">
                <el-button
                  @click="storageStudentView(scope.row)"
                  style="padding: 3px 4px 3px 4px;margin: 2px"
                  size="mini"
                >允许入库</el-button>
                </template>
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="80">
              <template slot-scope="scope">
                <el-button
                  type="danger"
                  style="padding: 3px 4px 3px 4px;margin: 2px"
                  size="mini"
                  @click="deleteWaitStorageStudent(scope.row)"
                >删除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="display: flex;justify-content: space-between;margin: 2px">
            <el-pagination
              background
              :page-size="10"
              :current-page="currentWaitPage"
              @current-change="currentWaitStoragePageChange"
              layout="prev, pager, next"
              :total="totalWaitCount"
            ></el-pagination>
          </div>
        </div>
      </el-drawer>
       <el-main style="padding-left: 0px;padding-top: 10px">
        <div>
           <el-table
            :data="studentList"
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
            <el-table-column prop="idea" align="left" fixed label="求职意向" width="100"></el-table-column>
            <el-table-column prop="gender" align="left" label="性别" width="50"></el-table-column>
            <el-table-column prop="bir" align="left"  label="生日" width="100"></el-table-column>
            <el-table-column prop="tel" align="left" label="联系电话" width="100"></el-table-column>
            <el-table-column prop="email" align="left" label="邮箱" width="100"></el-table-column>
            <el-table-column prop="schtime" align="left" label="毕业时间" width="100"></el-table-column>
            <el-table-column prop="address" align="left" label="居住地" width="100"></el-table-column>
            <el-table-column prop="lev" align="left" label="等级" width="100"></el-table-column>
            <el-table-column prop="major" align="left" label="主修" width="100"></el-table-column>
            <el-table-column prop="ability" align="left" label="技能" width="100"></el-table-column>
            <el-table-column prop="schname" align="left" label="毕业院校" width="100"></el-table-column>
            <el-table-column prop="pol" align="left" label="政治面貌" width="100"></el-table-column>
            <el-table-column prop="des" align="left" label="自我描述" width="500"></el-table-column>

            <el-table-column fixed="right" label="操作" width="80">
              <template slot-scope="scope">
                <el-button
                  @click="showEditStudentView(scope.row)"
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
                  @click="deleteStudent(scope.row)"
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

    <!-- 添加学生信息的浮窗 -->
    <el-form :model="student" 
      :rules="rules" 
      ref="addStudentForm" 
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
          :visible.sync="studentFormVisible"
          :before-close="cancleAddStudent"    
          width="50%">
          <el-row style="margin-top: 10px;padding: 0px;">
            <el-col :span="15">
              <div>
                <el-form-item label="中文名:" prop="cname">
                   <el-input prefix-icon="el-icon-edit" v-model="student.cname" size="mini" style="width: 300px"
                            placeholder="中文名"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15">
              <div>
                <el-form-item label="导师:" prop="tid">
                    <el-select v-model="student.tid" @change="getSelected"  style="width: 300px" size="mini" placeholder="导师">
                        <el-option
                            v-for="item in tutors"
                            :key="item.id"
                            :label="item.cname + ' ' + item.id"
                            :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15">
              <div>
                <el-form-item label="求职意向:" prop="idea">
                   <el-input prefix-icon="el-icon-edit" v-model="student.idea" size="mini" style="width: 300px"
                            placeholder="求职意向"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15">
              <div>
                <el-form-item label="性别:" prop="gender">
                    <el-select v-model="student.gender" @change="SelecteGender"  style="width: 300px" size="mini" placeholder="性别">
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
                <el-form-item label="生日:" prop="bir">
                  <el-date-picker
                    v-model="student.bir"
                    size="mini"
                    value-format="yyyy-MM-dd"
                    style="width: 150px"
                    type="date"
                    placeholder="生日"
                  ></el-date-picker>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15">
              <div>
                <el-form-item label="电话号码:" prop="tel">
                   <el-input prefix-icon="el-icon-edit" v-model="student.tel" size="mini" style="width: 300px"
                            placeholder="电话号码"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15">
              <div>
                <el-form-item label="邮箱:" prop="email">
                   <el-input prefix-icon="el-icon-edit" v-model="student.email" size="mini" style="width: 300px"
                            placeholder="邮箱"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15">
              <div>
               <el-form-item label="入学时间:" prop="schtime">
                  <el-date-picker
                    v-model="student.schtime"
                    size="mini"
                    value-format="yyyy-MM-dd"
                    style="width: 150px"
                    type="date"
                    placeholder="入学时间"
                  ></el-date-picker>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15">
              <div>
                <el-form-item label="居住地址:" prop="address">
                   <el-input prefix-icon="el-icon-edit" v-model="student.address" size="mini" style="width: 300px"
                            placeholder="居住地址"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15">
              <div>
                <el-form-item label="等级:" prop="lev">
                    <el-select v-model="student.lev" @change="SelecteStudentLev"  style="width: 300px" size="mini" placeholder="等级">
                        <el-option
                            v-for="item in studentLevel"
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
                <el-form-item label="主修专业:" prop="major">
                   <el-input prefix-icon="el-icon-edit" v-model="student.major" size="mini" style="width: 300px"
                            placeholder="主修专业"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15">
              <div>
                <el-form-item label="其他技能:" prop="ability">
                   <el-input prefix-icon="el-icon-edit" v-model="student.ability" size="mini" style="width: 300px"
                            placeholder="其他技能"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15">
              <div>
                <el-form-item label="所属院校:" prop="schname">
                   <el-input prefix-icon="el-icon-edit" v-model="student.schname" size="mini" style="width: 300px"
                            placeholder="所属院校"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15">
              <div>
                <el-form-item label="政治面貌:" prop="pol">
                   <el-input prefix-icon="el-icon-edit" v-model="student.pol" size="mini" style="width: 300px"
                            placeholder="政治面貌"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="20">
              <div>
                  <el-form-item label="自我描述:" prop="des">
                    <el-input type="textarea" prefix-icon="el-icon-edit" v-model="student.des" size="mini" style="width: 500px"
                            placeholder="自我描述"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <span slot="footer" >
            <el-button size="mini" @click="cancleAddStudent()">取 消</el-button>
            <el-button size="mini" type="primary" @click="addStudent('addStudentForm')">确 定</el-button>
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
        student: {
            id: '',
            userid:'', 
            identifier: '',
            username:'',
            cname: '',
            tid:'',
            gender:'',
            bir:'',
            tel:'',
            email: '',
            photo: '',
            schtime:'',
            idea:'',
            address:'',
            lev:'',
            major:'',
            ability:'',
            des:'',
            account:'',
            schname:'',
            pol:''
        },
        drawer: false, // 是否显示抽屉
        direction: 'ttb', // 抽屉打开法相
        waitStart: 1, // 待入库学生信息起始页
        totalWaitCount: '', // 待入库学生的总页数
        studentFormVisible: false, // 添加学生信息的浮窗
        multipleSelection: [], // 多选数据
        studentSearchViewVisible: false,
        studentLevel: [{value: 1, label: '蓝色人才'}, {value: 2, label: '绿色人才'},{value: 3, label: '灰色人才'}],
        genders:[{label: '男', value: 1}, {label: '女', value: 2}], // 性别
        studentList: [], // 存储所有的学生信息
        waitStorageStudentList: [], // 存储等待入库的学生的信息
        startPage: 1, // 翻页的起始值
        tableLoading: '', 
        totalCount: '', // 查询到的总条数
        tutors:[], // 存储所有的导师信息
        title: '', // 显示浮窗的表头
        addOrEdit: '', // 当前显示的浮窗的功能 1 添加 2 更新
        keywords: '', // 搜索框中的信息
      };
    },
    mounted: function () {
      // 获取所有的学生信息
      this.getStudentList();
      // 获取所有简单的导师的信息
      this.getTutors();
      this.getWaitStorageStudentNumber();
    },
    methods: {
      // 查询未入库学生的人数
      getWaitStorageStudentNumber() {
        var _this = this;
        this.getRequest("/student/getnotpassedstudentsnumber").then(resp=> {           
          if (resp && resp.status == 200) {
            _this.totalWaitCount = resp.data.obj;
            
            console.log(_this.totalWaitCount);
          }
        })
      },
      // 导出学生信息
      exportStudent() {
         window.open("/student/exportstudents", "_parent");
        this.$message({ type: "success", message: "正在导出！" });
      },
      // 显示抽屉
      showWaitStorageStudent() {
        this.waitStart = 1;
        this.drawer = true;
        this.getWaitStorageStudentList();
      },
      // 加载所有待入库的学生的信息
      getWaitStorageStudentList() {
        var data = {
          start: (this.waitStart - 1) * 10 ,
          size: 10
        }
        var _this = this;
        this.postRequest("/student/getallnotpassedstudents", data).then(resp=> {           
          if (resp && resp.status == 200) {
            _this.waitStorageStudentList = resp.data.obj.studentList;
            for (var i = 0; i < _this.waitStorageStudentList.length; i++) {
              _this.waitStorageStudentList[i].gender = _this.waitStorageStudentList[i].gender == '1' ? '男' : '女';
            }
            _this.totalWaitCount = resp.data.obj.totalCount;
            console.log(_this.totalWaitCount);
          }
        })
      },
      // 待入库学生信息的换页
      currentWaitStoragePageChange(currentWaitPage) {
        this.waitStart = currentWaitPage;
        this.getWaitStorageStudentList();
      },
      // 允许学生入库
      storageStudentView(val) {
        console.log(val.id);
        var _this = this;
        this.getRequest("/student/passstudent/" + val.id).then(resp=> {           
          if (resp && resp.status == 200) {
            _this.$message({ type: "success", message: "入库成功" });
            _this.getStudentList();
            _this.getWaitStorageStudentList();
          }
        })
      },
      // 不允许学生入库
      deleteWaitStorageStudent(val) {
        console.log(val.id);
        var _this = this;
        this.deleteRequest("/student/deletestudentbyid/" + val.id).then(resp=> {           
          if (resp && resp.status == 200) {
            _this.$message({ type: "success", message: "删除成功" });
            _this.getStudentList();
            _this.getWaitStorageStudentList();
          }
        })
      },
      // 处理抽屉
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
            // 管理显示待入库学生信息之后要重新加载学生信息
            this.getStudentList();
          })
          .catch(_ => {});
      },
      // 换页
      currentChange(currentPage) {
        this.startPage = currentPage;
        this.getStudentList();
      },
      // 取消搜索
      clearSearch(val) {
        if (val == '') {
            this.getStudentList();
        }
      },
      // 搜索学生信息
      searchStudent() {
        this.startPage = 1;
        this.getStudentList();
      },
      getStudentList() {
        var data = {
          studentName : this.keywords,
            start: (this.startPage - 1) * 10 ,
            size: 10
        }
        var _this = this;
        this.tableLoading = true;
        this.postRequest("/student/getallstudents", data).then(resp=> {           
          if (resp && resp.status == 200) {
            _this.studentList = resp.data.obj.studentList;
            for (var i = 0; i < _this.studentList.length; i++) {
              _this.studentList[i].gender = _this.studentList[i].gender == '1' ? '男' : '女';
            }
            _this.totalCount = resp.data.obj.totalCount;
            console.log(_this.totalCount);
            _this.tableLoading = false;
          }
        })
      },
      // 下拉框选择性别
      selecteGender(val) {
        for(let i = 0; i < this.genders.length; i++){
          if(this.genders[i].value == val){
            this.student.gender = this.genders[i].value;
          }
        }
      },
       // 下拉框选择等级
      SelecteStudentLev(val) {
        for(let i = 0; i < this.studentLevel.length; i++){
          if(this.studentLevel[i].value == val){
            this.student.lev = this.studentLevel[i].value;
          }
        }
      },
      // 加载所有的导师信息
      getTutors() {
        var _this = this;
        this.getRequest("/tutor/getallsimpletutors").then(resp=> {           
          if (resp && resp.status == 200) {
            _this.tutors = resp.data.obj;
          }
        })
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      // 去掉添加学生浮窗
      cancleAddStudent() {
        this.student = {
             id: '',
             userid:'', 
             identifier: '',
             username:'',
             cname: '',
             tid:'',
             gender:'',
             bir:'',
             tel:'',
             email: '',
             photo: '',
             schtime:'',
             idea:'',
             address:'',
             lev:'',
             major:'',
             ability:'',
             des:'',
             account:'',
             schname:'',
             pol:''
          };
        this.studentFormVisible = false;
      },
      // 显示添加学生信息的浮窗
      showAddStudentView() {
        this.$refs['addStudentForm'].clearValidate() // 重置验证
        this.studentFormVisible = true;
        this.addOrEdit = 1;
        this.title = "添加学生信息";
      },
      // 显示编辑学生信息的浮窗
      showEditStudentView(val) {
        this.$refs['addStudentForm'].clearValidate() // 重置验证
        this.studentFormVisible = true;
        this.student = val;
        this.addOrEdit = 2;
        this.title = "编辑学生信息";
      },
      // 添加学生信息
      addStudent(formName) {
        var _this = this;
        this.$refs[formName].validate((valid) => {
          // 验证成功
          if (valid) {
            // 开始加载
            _this.tableLoading = true;
            // addOrEdit = 1 表示添加企业信息 2 表示修改
            var url = '';
            var message = '';
            if (_this.addOrEdit === 1) {
              url = "/student/addstudent";
              message = "添加成功";
            } else {
              url = "/student/updatestudent";
              message = "更新成功";
            }

            this.postRequest(url, _this.student).then(resp => {
            if (resp && resp.status == 200) {
              // 数据添加成功之后需要重新加载数据
              _this.studentFormVisible = false;
              _this.$message({ type: "success", message: message });
              _this.getStudentList();
            }
          })
          }
        })
      },
      // 删除学生信息
      deleteStudent(val) {
        var _this = this;
        this.$confirm(
          "此操作将永久删除[" + val.cname + "学生信息], 是否继续?",
          "提示",
          {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }
        ).then(() => {
          this.deleteRequest("/student/deletestudentbyid/" + val.id).then(resp => {
            if (resp && resp.status == 200) {
              _this.$message({ type: "success", message: "删除成功！" });
              _this.getStudentList();
            }
          })
        })
      },
      // 批量删除信息
      deleteManyStudents() {
        var _this = this;
        this.$confirm(
          "此操作将永久删除[" + this.multipleSelection.length + "条学生信息], 是否继续?",
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
          this.deleteRequest("/student/deletestudents", ids).then(resp => {
            if (resp && resp.status == 200) {
              _this.$message({ type: "success", message: "删除成功！" });
              _this.getStudentList();
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
