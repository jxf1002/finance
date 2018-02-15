<template>
  <div class="family">
    <div class="title">
      <h1>家庭管理</h1>
      <el-button @click="showDialog()" type="primary" icon="el-icon-plus"></el-button>
    </div>
    <el-table :data="families" stripe style="width: 100%">
      <el-table-column prop="id" label="序号" width="180">
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
    <el-dialog title="家庭管理" :visible.sync="dialogFormVisible" :before-close="handleClose">
      <el-form :model="form" :rules="rules" ref="form">
        <el-input v-model="form.id" auto-complete="off" type="hidden"></el-input>
        <el-form-item label="名称" prop="name" :label-width="formLabelWidth">
          <el-input v-model="form.name" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleClose ()">取 消</el-button>
        <el-button type="primary" @click="handleSave ('form')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
var axios = require('axios')

export default {
  name: 'family',
  data: function () {
    return {
      families: [],
      dialogFormVisible: false,
      formLabelWidth: '120px',
      form: {
        id: '',
        name: ''
      },
      rules: {
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
      axios.get('/api/families')
        .then(function (response) {
          if (response.data.code === 0) {
            that.families = response.data.data
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
            axios.put('/api/families/' + this.form.id, this.form)
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
            axios.post('/api/families', this.form)
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
          axios.delete('/api/families/' + row.id)
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
