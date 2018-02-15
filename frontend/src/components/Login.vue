<template>
  <div class="dialog">
    <div class="loginPage">
      <h1>登录</h1>
      <el-form :model="form" :rules="rules" ref="form">
        <el-form-item prop="name" label="用户名">
          <el-input type="text" v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="密码">
          <el-input type="password" v-model="form.password"></el-input>
        </el-form-item>
        <el-button type="primary" @click="submitForm('form')">提交</el-button>
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
    if (getCookie('user')) {
      this.$router.push('/')
    }
  },
  data () {
    return {
      form: {
        name: '',
        password: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    resetForm: function () {
      this.$refs['form'].resetFields()
    },
    submitForm: function (form) {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          var user = this.form.name
          var password = this.form.password
          var that = this
          Axios.get(
            '/api/users/login?name=' + user + '&password=' + password
          ).then(function (res) {
            if (res.data.code === 0) {
              setCookie('user', JSON.stringify(res.data.data), 7 * 24 * 60 * 60)
              that.$router.go('/')
              that.$message.success('登录成功')
            } else {
              that.$message.error('登录失败，用户名或密码不正确')
            }
          }).catch(function () {
            that.$message.error('登录失败，用户名或密码不正确')
          })
        } else {
          return false
        }
      })
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
