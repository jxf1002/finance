<template>
  <div class="dialog">
    <div class="loginPage">
      <h1>登录</h1>
      <el-form>
        <el-form-item label="用户名">
          <el-input type="text" id="user" v-model="formName.user" @blur="inputBlur('user',formName.user)"></el-input>
          <p>{{formName.userError}}</p>
        </el-form-item>
        <el-form-item label="密码">
          <el-input type="password" id="password" v-model="formName.password" @blur="inputBlur('password',formName.password)"></el-input>
          <p>{{formName.passwordError}}</p>
        </el-form-item>
        <el-button type="primary" @click="submitForm('formName')">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form>
    </div>
  </div>
</template>

<script>
import { setCookie, getCookie } from '../assets/js/cookie.js'

import Axios from 'axios'
export default {
  name: 'login',
  mounted () {
    if (getCookie('username')) {
      this.$router.push('/')
    }
  },
  data () {
    return {
      formName: {
        user: '',
        userError: '',
        password: '',
        passwordError: ''
      }
    }
  },
  methods: {
    resetForm: function () {
      this.formName.user = ''
      this.formName.userError = ''
      this.formName.password = ''
      this.formName.passwordError = ''
    },
    submitForm: function (formName) {
      var user = this.formName.user
      var password = this.formName.password
      var that = this
      Axios.get(
        '/api/users/login?name=' + user + '&password=' + password
      ).then(function (res) {
        if (res.data.code === 0) {
          setCookie('username', res.data.data.name, 7 * 24 * 60 * 60)
          that.$router.push('/')
        } else {
          console.log('登录失败')
        }
      }).catch(function () { })
    },
    inputBlur: function (errorItem, inputContent) {
      if (errorItem === 'user') {
        if (inputContent === '') {
          this.formName.userError = '用户名不能为空'
        } else {
          this.formName.userError = ''
        }
      } else if (errorItem === 'password') {
        if (inputContent === '') {
          this.formName.passwordError = '密码不能为空'
        } else {
          this.formName.passwordError = ''
        }
      }
    }
  }
}
</script>

<style scoped>
html,
body {
  margin: 0;
  padding: 0;
  position: relative;
}
.dialog {
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.8);
}
.loginPage {
  position: absolute;
  top: 50%;
  left: 50%;
  margin-top: -150px;
  margin-left: -175px;
  width: 350px;
  min-height: 300px;
  padding: 30px 20px 20px;
  border-radius: 8px;
  box-sizing: border-box;
  background-color: #fff;
}
.loginPage p {
  color: red;
  text-align: left;
}
</style>
