<template>
  <div style="height: 100%">
    <myhead></myhead>
    <el-container style="height: 90%; border: 1px solid #eee">
      <el-container>
        <el-aside width="200px" class="home-aside">
          <div style="display: flex;justify-content: flex-start;width: 100%;text-align: left;">

            <el-menu style="background:#ececec;width: 100%;" unique-opened router>
              <!-- 将存储在 routes 里面的路径打印出来 -->
              <el-menu-item
                style="padding-right:0px;margin-left: 0px;width: 170px;text-align: left"
                v-for="child in this.routes"
                :index="child.nodeURL"
                :key="child.nodeURL"
              >{{child.displayName}}</el-menu-item>
            </el-menu>
          </div>
        </el-aside>

        <el-main style="padding-bottom: 0px">
          <transition>
            <router-view></router-view>
          </transition>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
import myhead from "./pages/head/head";
export default {
  name: "home",
  data() {
    return {
      isDot: false,
      menu: []
    };
  },
  beforeCreate() {},
  computed: {
    user() {
      console.log(this.$store.state.user);
      return this.$store.state.user;
    },
    routes() {
      console.log(this.$store.state.routes);
      return this.$store.state.routes;
    }
  },
  created() {
    // vue 页面的生命周期 当我一进来页面之后 我先执行
  },
  mounted() {
    // vue 页面的生命周期 当我进来之后 且加载完资源了之后再执行
    console.log(this.routes);
  },
  methods: {
    // 去demo
    toDemo() {
      alert('toDemo')
      this.$router.push('/home/demo')
    },
    toBaseInfo() {
      this.$router.push({
        path: "baseInfo"
      });
    },
    toDeclaration() {
      this.$router.push({
        path: "declaration"
      });
    }
  },
  components: {
    myhead: myhead
  }
};
</script>
<style>
 .home-container {
    height: 100%;
    position: absolute;
    top: 0px;
    left: 0px;
    width: 100%;
  }

  .home-header {
    background-color: #20a0ff;
    color: #333;
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: space-between;
    box-sizing: content-box;
    padding: 0px;
  }

  .home-aside {
    background-color: #ECECEC;
  }

  .home-main {
    background-color: #fff;
    color: #000;
    text-align: center;
    margin: 0px;
    padding: 0px;;
  }

  .home_title {
    color: #fff;
    font-size: 22px;
    display: inline;
    margin-left: 8px;
  }

  .home_userinfo {
    color: #fff;
    cursor: pointer;
  }

  .home_userinfoContainer {
    display: inline;
    margin-right: 20px;
  }

  .el-submenu .el-menu-item {
    width: 180px;
    min-width: 175px;
  }
</style>
