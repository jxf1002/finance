<template>
  <div id="app">
    <el-container class="index-container">
      <el-header v-if="username" class="el-header">
        <el-menu class="el-menu" mode="horizontal" background-color="#409EFF" text-color="#fff" active-text-color="#ffd04b">
          <el-menu-item index="0">
            <router-link to="/">首页</router-link>
          </el-menu-item>
          <el-menu-item index="1">
            <router-link to="/family">家庭管理</router-link>
          </el-menu-item>
          <el-menu-item index="2">
            <router-link to="/user">用户管理</router-link>
          </el-menu-item>
          <el-menu-item index="3">
            <router-link to="/account">科目管理</router-link>
          </el-menu-item>
          <el-menu-item index="4">
            <router-link to="/book">账本管理</router-link>
          </el-menu-item>
          <span class="menu-right">
            <span>{{username}}</span>
            <el-dropdown @command="handleCommand">
              <i class="el-icon-arrow-down" style="margin: 10px; color: #fff"></i>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="logout">登出</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </span>
        </el-menu>
      </el-header>

      <el-main class="el-main">
        <router-view/>
      </el-main>
      <el-footer>
        <span class="copyright">&copy; 2018 justful.com版权所有 京ICP备18007833号-1</span>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
import { getCookie, delCookie } from './assets/js/cookie.js'

export default {
  name: 'App',
  data () {
    return {
      username: ''
    }
  },
  mounted () {
    this.username = getCookie('username')
  },
  methods: {
    handleCommand (command) {
      if (command === 'logout') {
        this.logout()
      }
    },
    logout () {
      delCookie('username')
      this.username = ''
      this.$router.push('/login')
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

body {
  margin: 0;
}

.el-header {
  padding: 0;
  background-color: #409eff;
  color: #333;
  line-height: 60px;
  font-size: 12px;
}

.el-menu a {
  display: block;
}

.menu-right {
  float: right;
  color: #fff;
  font-size: 14px;
}

.el-main {
  height: 850px;
}

.el-footer {
  line-height: 60px;
  text-align: center;
}
</style>
