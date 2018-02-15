<template>
  <div class="account">
    <div class="title">
      <h1>科目管理</h1>
      <el-button @click="showDialog()" type="primary" icon="el-icon-plus"></el-button>
    </div>
    <el-table :data="accounts" stripe style="width: 100%">
      <el-table-column prop="id" label="序号" width="180">
      </el-table-column>
      <el-table-column prop="type" label="类型">
      </el-table-column>
      <el-table-column prop="name" label="名称">
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button @click="showDialog(scope.row)" type="primary" icon="el-icon-edit"></el-button>
          <el-button @click="handleDelete(scope.$index, scope.row)" type="primary" icon="el-icon-delete"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="科目管理" :visible.sync="dialogFormVisible" :before-close="handleClose">
      <el-form :model="form" :rules="rules" ref="form">
        <el-input v-model="form.id" auto-complete="off" type="hidden"></el-input>
        <el-form-item label="类型" prop="type" :label-width="formLabelWidth">
          <el-select v-model="form.type" placeholder="请选择类型">
            <el-option label="资产" value="资产"></el-option>
            <el-option label="负债" value="负债"></el-option>
            <el-option label="收入" value="收入"></el-option>
            <el-option label="支出" value="支出"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="名称" prop="name" :label-width="formLabelWidth">
          <el-input v-model="form.name" auto-complete="off"></el-input>
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

export default {
  name: 'account',
  data: function () {
    return {
      accounts: [],
      dialogFormVisible: false,
      formLabelWidth: '120px',
      form: {
        id: '',
        type: '',
        name: ''
      },
      rules: {
        type: [
          { required: true, message: '请选择类别', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入名称', trigger: 'blur' }
        ]
      }
    }
  },
  mounted: function () {
    this.loading()
  },
  methods: {
    loading () {
      var that = this
      axios.get('/api/accounts')
        .then(function (response) {
          if (response.data.code === 0) {
            that.accounts = response.data.data
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
        this.form = {}
      }
    },
    handleSave () {
      var that = this
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.form.id) {
            axios.put('/api/accounts/' + this.form.id, this.form)
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
            axios.post('/api/accounts', this.form)
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
          axios.delete('/api/accounts/' + row.id)
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
