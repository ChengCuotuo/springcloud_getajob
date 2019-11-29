import Vue from 'vue'
import Vuex from 'vuex'
import '../lib/sockjs'
import '../lib/stomp'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {
      cname: window.localStorage.getItem('user' || '[]') == null ? '' : window.localStorage.getItem('user' || '[]').cname,
      username: window.localStorage.getItem('user' || '[]') == null ? '未登录' : window.localStorage.getItem('user' || '[]').username,
      rolename: window.localStorage.getItem('user' || '[]') == null ? '未登录' : window.localStorage.getItem('user' || '[]').rolename,
      roledesc: window.localStorage.getItem('user' || '[]') == null ? '未登录' : window.localStorage.getItem('user' || '[]').roledesc,
    },
    routes: [],
    msgList: [],
    isDotMap: new Map(),
    currentFriend: {},
    stomp: null,
    nfDot: false,
    userInfo: {
      id: '',
      name: ''
    }
  },
  getters: {
  },
  mutations: {
    // initMenu(state, menus){
    //   state.routes = menus;
    // },
    login(state, user){
      state.user = user;
      window.localStorage.setItem('user', JSON.stringify(user));
    },
    toggleNFDot(state, newValue){
      state.nfDot = newValue;
    },
    removeValueDotMap(state, key){
      state.isDotMap.delete(key);
    }
  },
  actions: {
  
  }
});
