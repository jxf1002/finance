<template>
  <div class="book">
    <div class="title">
      <h1>账本管理</h1>
      <el-button @click="showDialog()" type="primary" icon="el-icon-plus"></el-button>
    </div>
    <el-table :data="books" stripe style="width: 100%">
      <el-table-column prop="id" label="序号" width="180">
      </el-table-column>
      <el-table-column prop="user.name" label="用户">
      </el-table-column>
      <el-table-column prop="account.name" label="科目">
      </el-table-column>
      <el-table-column prop="accountDate" label="日期">
      </el-table-column>
      <el-table-column prop="summary" label="摘要">
      </el-table-column>
      <el-table-column prop="amount" label="金额">
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button @click="showDialog(scope.row)" type="primary" icon="el-icon-edit"></el-button>
          <el-button @click="handleDelete(scope.$index, scope.row)" type="primary" icon="el-icon-delete"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="账本管理" :visible.sync="dialogFormVisible" :before-close="handleClose">
      <el-form :model="form" :rules="rules" ref="form">
        <el-input v-model="form.id" auto-complete="off" type="hidden"></el-input>
        <el-form-item label="用户" prop="user" :label-width="formLabelWidth">
          <el-select v-model="form.user.id" placeholder="请选择用户">
            <el-option v-for="item in userOptions" :key="item.id" :label="item.name" :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="科目" prop="account" :label-width="formLabelWidth">
          <el-select v-model="form.account.id" placeholder="请选择科目">
            <el-option v-for="item in accountOptions" :key="item.id" :label="item.name" :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="日期" prop="accountDate" :label-width="formLabelWidth">
          <el-date-picker v-model="form.accountDate" type="date" placeholder="选择日期" value-format="yyyy-MM-dd">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="摘要" prop="summary" :label-width="formLabelWidth">
          <el-input v-model="form.summary" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="金额" prop="amount" :label-width="formLabelWidth">
          <el-input v-model="form.amount" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleClose ()">取 消</el-button>
        <el-button type="primary" @click="handleSave ()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
var axios = require('axios')
var urlPrefix = '/api/books'
var userUrlPrefix = '/api/users'
var accountUrlPrefix = '/api/accounts'
export default {
  name: 'book',
  data: function () {
    return {
      books: [],
      dialogFormVisible: false,
      formLabelWidth: '120px',
      form: {
        id: '',
        name: '',
        user: {},
        account: {},
        role: ''
      },
      rules: {
        user: [
          { required: true, message: '请选择用户', trigger: 'blur' }
        ],
        account: [
          { required: true, message: '请选择科目', trigger: 'blur' }
        ],
        accountDate: [
          { required: true, message: '请输入日期', trigger: 'blur' }
        ],
        summary: [
          { required: true, message: '请输入摘要', trigger: 'blur' }
        ],
        amount: [
          { required: true, message: '请输入金额', trigger: 'blur' }
        ]
      },
      userOptions: [],
      accountOptions: []
    }
  },
  mounted: function () {
    this.loadingOnce()
    this.loading()
  },
  methods: {
    loadingOnce () {
      var that = this
      axios.get(userUrlPrefix)
        .then(function (response) {
          if (response.data.code === 0) {
            that.userOptions = response.data.data
          } else {
            that.$message.error('获取列表失败' + response.data.message)
          }
        })
        .catch(function (error) {
          that.$message.error('服务器异常' + error)
        })
      axios.get(accountUrlPrefix)
        .then(function (response) {
          if (response.data.code === 0) {
            that.accountOptions = response.data.data
          } else {
            that.$message.error('获取列表失败' + response.data.message)
          }
        })
        .catch(function (error) {
          that.$message.error('服务器异常' + error)
        })
    },
    loading () {
      var that = this
      axios.get(urlPrefix)
        .then(function (response) {
          if (response.data.code === 0) {
            that.books = response.data.data
          } else {
            that.$message.error('获取列表失败' + response.data.message)
          }
        })
        .catch(function (error) {
          that.$message.error('服务器异常' + error)
        })
    },
    showDialog (row) {
      this.dialogFormVisible = true
      if (row) {
        this.form = row
      } else {
        this.form = { user: {}, account: {} }
      }
    },
    handleSave () {
      var that = this
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.form.id) {
            axios.put(urlPrefix + '/' + this.form.id, this.form)
              .then(function (response) {
                if (response.data.code === 0) {
                  that.$message({
                    message: '修改成功',
                    type: 'success'
                  })
                  that.loading()
                  that.handleClose()
                } else {
                  that.$message.error('修改失败' + response.data.message)
                }
              })
              .catch(function (error) {
                that.$message.error('服务器异常' + error)
              })
          } else {
            axios.post(urlPrefix, this.form)
              .then(function (response) {
                if (response.data.code === 0) {
                  that.$message({
                    message: '新增成功',
                    type: 'success'
                  })
                  that.loading()
                  that.handleClose()
                } else {
                  that.$message.error('新增失败' + response.data.message)
                }
              })
              .catch(function (error) {
                that.$message.error('服务器异常' + error)
              })
          }
        } else {
          return false
        }
      })
    },
    handleDelete (index, row) {
      var that = this
      this.$confirm('确认删除？')
        .then(_ => {
          axios.delete(urlPrefix + '/' + row.id)
            .then(function (response) {
              if (response.data.code === 0) {
                that.$message({
                  message: '删除成功',
                  type: 'success'
                })
                that.loading()
              } else {
                that.$message.error('删除失败' + response.data.message)
              }
            })
            .catch(function (error) {
              that.$message.error('服务器异常' + error)
            })
        })
        .catch(_ => { })
    },
    handleClose () {
      this.$refs['form'].resetFields()
      this.dialogFormVisible = false
    }
  }
}
</script>

<style>
.title h1 {
  float: left;
}

.title button {
  float: right;
  margin: 20px;
}
</style>
