<template>
  <div class="user">
    <div class="title">
      <h1>用户管理</h1>
      <el-button @click="showDialog()" type="primary" icon="el-icon-plus"></el-button>
    </div>
    <el-table :data="users" stripe style="width: 100%">
      <el-table-column prop="id" label="序号" width="180">
      </el-table-column>
      <el-table-column prop="name" label="名称">
      </el-table-column>
      <el-table-column prop="family.name" label="所在家庭">
      </el-table-column>
      <el-table-column prop="role" label="角色">
        <template slot-scope="scope">
          <span v-if="scope.row.role == 0">管理员</span>
          <span v-if="scope.row.role == 1">普通用户</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button @click="showDialog(scope.row)" type="primary" icon="el-icon-edit"></el-button>
          <el-button @click="handleDelete(scope.$index, scope.row)" type="primary" icon="el-icon-delete"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="用户管理" :visible.sync="dialogFormVisible" :before-close="handleClose">
      <el-form :model="form" :rules="rules" ref="form">
        <el-input v-model="form.id" auto-complete="off" type="hidden"></el-input>
        <el-form-item label="名称" prop="name" :label-width="formLabelWidth">
          <el-input v-model="form.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password" :label-width="formLabelWidth">
          <el-input v-model="form.password" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="所在家庭" prop="family" :label-width="formLabelWidth">
          <el-select v-model="form.family.id" placeholder="请选择所在家庭">
            <el-option v-for="item in familiyOptions" :key="item.id" :label="item.name" :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="角色" prop="role" :label-width="formLabelWidth">
          <el-select v-model="form.role" placeholder="请选择角色">
            <el-option v-for="item in roleOptions" :key="item.id" :label="item.name" :value="item.id">
            </el-option>
          </el-select>
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
var urlPrefix = '/api/users'
var familyUrlPrefix = '/api/families'
export default {
  name: 'user',
  data: function () {
    return {
      users: [],
      dialogFormVisible: false,
      formLabelWidth: '120px',
      form: {
        id: '',
        name: '',
        family: {},
        role: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入名称', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        family: [
          { required: true, message: '请选择所在家庭', trigger: 'blur' }
        ],
        role: [
          { required: true, message: '请选择角色', trigger: 'blur' }
        ]
      },
      familiyOptions: [],
      roleOptions: [{ 'id': 0, 'name': '管理员' }, { 'id': 1, 'name': '普通用户' }]
    }
  },
  mounted: function () {
    this.loadingOnce()
    this.loading()
  },
  methods: {
    loadingOnce () {
      var that = this
      axios.get(familyUrlPrefix)
        .then(function (response) {
          if (response.data.code === 0) {
            that.familiyOptions = response.data.data
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
            that.users = response.data.data
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
        this.form = { 'family': { 'id': null } }
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
