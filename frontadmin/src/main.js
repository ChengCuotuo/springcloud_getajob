// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import store from './store'
import {getRequest} from './utils/api'
import {postRequest} from './utils/api'
import {postRequestLogin} from './utils/api'
import {deleteRequest} from './utils/api'
import {putRequest} from './utils/api'
// import {initMenu} from './utils/utils'
import {isNotNullORBlank} from './utils/utils'
import './utils/filter_utils'
import 'font-awesome/css/font-awesome.min.css'
import commonCss from './assets/css/common.css'

Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(commonCss)


Vue.prototype.getRequest = getRequest;
Vue.prototype.postRequest = postRequest;
Vue.prototype.postRequestLogin = postRequestLogin;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.isNotNullORBlank = isNotNullORBlank;

//每次路由切换的时候，进行异步操作，检查用户信息
// router.beforeEach((to, from, next)=> {
//     if (to.name == 'Login') {
//       next();
//       return;
//     }
//     var name = store.state.user.name;
//     if (name == '未登录') {
//       if (to.meta.requireAuth || to.name == null) {
//         next({path: '/', query: {redirect: to.path}})
//       } else {
//         next();
//       }
//     } else {
//       // initMenu(router, store);
//       next();
//     }
//   }
// )
// 刷新保存状态
if (sessionStorage.getItem('store')) {
  store.replaceState(
    Object.assign(
      {},
      store.state,
      JSON.parse(sessionStorage.getItem('store'))
    )
  )
  sessionStorage.removeItem('store')
}
// 监听，在页面刷新时将vuex里的信息保存到sessionStorage里
window.addEventListener('beforeunload', () => {
  sessionStorage.setItem('store', JSON.stringify(store.state))
})
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
})

