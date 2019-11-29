<template>
  <div>
    <el-header class="elHeader">
      <span class="home_title">职准准</span>
      <span>
        <i style="font-size: 16px; padding-right:10px;">{{cname}}</i>
        <el-dropdown>
          <i class="el-icon-setting" style="margin-right: 15px; color: #fff"></i>
          <el-dropdown-menu slot="dropdown">
            <!-- <el-dropdown-item @click.native="userInfo()">个人资料</el-dropdown-item> -->
            <el-dropdown-item @click.native="lagout()">退出系统</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </span>
    </el-header>
  </div>
</template>

<script>
export default {
  name: "myhead",
  data() {
    return {
      user:[],
      cname: localStorage.getItem('cname')
    };
  },
  // created() {
  //   this.getRequest("/staff/getEmpBy/" + localStorage.getItem("employeeId")).then(
  //     resp => {
  //       if (resp && resp.status == 200) {
  //         // _this.emp.workId = resp.data;
  //         console.log(resp);
  //         this.user = resp.data.obj;
  //         this.user.hireDate = this.user.hireDate.substring(0, 10);
  //       }
  //     }
  //   );
  // },
  mounted() {},
  methods: {
    // 所有的方法封装
    userInfo() {
      this.$router.push({
        path: '/home/baseInfo'
      })
    },
    lagout() {
      this.$router.push({
        path: '/'
      })
    }
  }
};
</script>

<style scoped>
.el-header,
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}
.elHeader {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  background-color: #20a0ff;
}
.user {
  font-size: 16px;
  font-weight: bolder;
}
.home_title {
  color: #fff;
  font-size: 22px;
  display: inline;
  margin-left: 8px;
}
.colorWhite{
  color: #fff
}
</style>
