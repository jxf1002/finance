// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import {
  getCookie
} from './assets/js/cookie.js'

Vue.config.productionTip = false

Vue.use(ElementUI)

router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) {
    if (getCookie('username')) {
      next()
    } else {
      next({
        path: '/login',
        query: {
          redirect: to.fullPath
        }
      })
    }
  } else {
    next()
  }
})

let vRouter = new Vue({
  el: '#app',
  router,
  data: {
    loginUser: null
  },
  components: {
    App
  },
  render: h => h(App)
})

Vue.use({
  vRouter
})
