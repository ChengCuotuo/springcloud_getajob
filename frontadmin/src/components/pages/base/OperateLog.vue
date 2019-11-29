<template>
  <div>
    <el-container>
       <el-header style="align-items: center;background: white">
        <div style="display: inline">
            <el-col :span="11">
            输入时间段:
            <el-date-picker
              v-model="leaveDateScope"
              unlink-panels
              size="mini"
              type="daterange"
              value-format="yyyy-MM-dd"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            ></el-date-picker>
            </el-col>
            <el-col class="line" :span="7">
              <el-radio-group v-model="searchData.limit">
                <el-radio :label="0">不限定</el-radio>
                <el-radio :label="1">本日</el-radio>
                <el-radio :label="2">本周</el-radio>
                <el-radio :label="3">本月</el-radio>
              </el-radio-group>
            </el-col>
          <el-col class="line" :span="2">
          <el-button type="primary" size="mini" @click.native="searchOperateLog()">搜索</el-button>
        </el-col>
        </div>
      </el-header>

      <el-main style="padding-left: 0px;padding-top: 0px">
        <el-table 
          v-if="showtableData"
          :data="tableData"
          v-loading="tableLoading"
          border
          stripe
          @selection-change="handleSelectionChange"
          size="mini"
          style="width: 100%"
          >

          <!-- <el-table-column type="selection">
          </el-table-column> -->
          <el-table-column type="index" label="序号" width="120px" align="center">
          </el-table-column>
          <el-table-column prop="cname" label="操作用户" width="200px" align="center">
          </el-table-column>
          <el-table-column prop="operateName" label="操作" :formatter= "formatDateTime" width="250px" align="center">
          </el-table-column>
          <el-table-column prop="operateTime" label="操作时间" width="200px" align="center">
          </el-table-column>

          <el-table-column prop="operateDesc" label="详细信息" width="500px" align="center">
          </el-table-column>
        </el-table>
        <div style="display: flex;justify-content: space-between;">
          <el-pagination
            background
            :page-size="10"
            :current-page="currentPage"
            @current-change="currentChange"
            layout="prev, pager, next"
            :total="totalCount"
          ></el-pagination>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        tableData:[], // 存储查询到的数据
        showtableData : false,
        searchData:{
          startDate:'',
          endDate:'',
          limit:'',
          start: '',
          size: 10
        },
        startPage: 1, // 翻页的起始值
        leaveDateScope : '', // 存储搜索选中的时间范围
        totalCount: '', // 查询到的信息总条数
        tableLoading: '', // 加载信息缓冲
      }
    },
    mounted: function () {
        this.getOperateLog();
    },
    methods: {
      getOperateLog() {

        if (this.searchData.limit != 0) {
          this.leaveDateScope = '';
        }

        var data = {
          startDate: this.leaveDateScope[0],
          endDate: this.leaveDateScope[1],
          limit: this.searchData.limit,
          start: (this.startPage - 1) * 10,
          size: 10
        }
        var _this = this;
        this.tableLoading = true;
        this.postRequest("/system/operatelog/loadalloperatelog", data).then(resp => {
          if (resp && resp.status == 200) {
            _this.tableData = resp.data.obj.operateLogList
            for(var i = 0; i < _this.tableData.length; i++) {
             _this.tableData[i].operateTime = _this.formatDate(_this.tableData[i].operateTime);
            }
            _this.showtableData = true;
            _this.totalCount = resp.data.obj.totalCount;
            _this.tableLoading = false;
          }
        })
      },
      searchOperateLog() {
        this.startPage = 1;
        this.getOperateLog();
      },
      currentChange(currentPage) {
        this.startPage = currentPage;
        this.getOperateLog();
      },
    }
  }
</script>

<style>


</style>