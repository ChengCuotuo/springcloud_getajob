<template>
  <div class="main">
  <el-form :rules="rules" :model="loginForm" class="login-container" ref="login" label-position="left" label-width="0px" v-loading="loading">
    <h3 class="login_title">职准准</h3>
    <el-form-item prop="username">
      <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="请输入用户名"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="请输入密码"></el-input>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 100%" @click="submitLogin('login')">登录</el-button>
    </el-form-item>
    <!-- <router-link to='/register' class = "register">快速注册</router-link>
     <span style="color: #a8a8a8">|</span>
     <router-link to='/ForgetPassword' class = 'forgetPassword'>重置密码</router-link> -->
  </el-form>
  
  </div>
</template>
<script>
  export default{
    data(){
      return {
        rules: {
          username: [{required: true, message: '请输入用户名', trigger: 'change'}],
          password: [{required: true, message: '请输入密码', trigger: 'change'}]
        },
        // checked: true,
        loginForm: {
        },
        loading: false,
      }
    },
    methods: {
      login() {
        var _this = this;
        this.loading = true;
        var data = {
          username: this.loginForm.username,
          password: this.loginForm.password
        };

        // alert(data);

        this.postRequest('/login', data).then(resp=> {
          _this.loading = false;
          if (resp && resp.status == 200) {
            localStorage.setItem('cname', resp.data.obj.cname)
            localStorage.setItem('username', resp.data.obj.username)
            localStorage.setItem('rolename', resp.data.obj.roleInfos.rolename)
            localStorage.setItem('roledesc', resp.data.obj.roleInfos.roledesc)
            
             var data = resp.data.obj;
            // 存储可跳转的路由
            this.initRoutesStore(data.roleInfos[0].sysFuns)
            _this.$store.commit('login', data);
            var path = _this.$route.query.redirect;
            _this.$router
              .replace({path: path == '/' || path == undefined ? '/home' : path});
          } 
        });
      },
      submitLogin(formName) {
        const that = this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            that.login()
          } else {
            return false;
          }
        });
      },
      initRoutesStore: function(routes) {
        console.log(routes)
        this.$store.state.routes = []
        for (let i of routes) {
          this.$store.state.routes.push({
            nodeId: i.nodeid,
            displayName: i.dsiplayname,
            nodeURL: i.nodeurl,
            parentNodeId: i.parentnodeid
          })
        }
      }
    }
  }
</script>
<style>
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
  .login_remember {
    margin: 0px 0px 35px 0px;
    text-align: left;
  }
  .register{
    color: #a8a8a8;
    text-decoration-line: none;
  }
  .forgetPassword{
    color: #a8a8a8;
    text-decoration-line: none;
  }

</style>
