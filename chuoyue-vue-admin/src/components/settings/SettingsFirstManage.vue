<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>配置管理</el-breadcrumb-item>
      <el-breadcrumb-item>配置管理1</el-breadcrumb-item>
    </el-breadcrumb>

    <!--卡片视图-->
    <el-card class="box-card">

      <el-row :gutter="24">
        <el-col :span="4">
          <el-input placeholder="请输入内容" v-model="queryParams" clearable @clear="getUserSearchList">

            <el-button slot="append" icon="el-icon-search" @click="getUserSearchList"></el-button>
          </el-input>
        </el-col>

        <el-col :span="4">
          <el-button type="primary" @click="handleAddDialog">添加检测算法</el-button>
        </el-col>
      </el-row>
      <el-table :data="userList" stripe border>
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column label="组织" prop="departmentName" ></el-table-column>
        <el-table-column label="摄像机名称" prop="deviceName"></el-table-column>
        <el-table-column label="设备用户名" prop="deviceUserName" ></el-table-column>
        <el-table-column label="位置" prop="address"></el-table-column>
        <el-table-column label="IP地址" prop="deviceIp"></el-table-column>
        <el-table-column label="RTSP端口" prop="rtsp" ></el-table-column>
        <el-table-column label="网关" prop="gateway"></el-table-column>
        <el-table-column label="状态"  width="120px">
          <template slot-scope="scope">
            <span v-if="scope.row.status === 0">正常</span>
            <span v-else>异常</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="251px">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.state === 1"
              active-color="#13ce66"
              inactive-color="#ff4949"
              @change="deviceStateChange(scope.row)">
            </el-switch>
            <span style="margin-left: 10px">{{scope.row.state === 1 ? '已启用' : '未启用'}}</span>
          </template>
        </el-table-column>
      </el-table>

      <!--分页区域-->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pagenum"
        :page-sizes="[1, 2, 3, 4, 5, 6]"
        :page-size="queryInfo.pagesize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </el-card>

    <el-dialog
      title="添加报警"
      :visible.sync="addDialogVisible"
      width="30%"
      @close="addDialogClosed"
    >

      <!--Dialog内容主体区域-->
      <el-form :model="addUserForm" :rules="addUserFormRules" ref="ruleForm" label-width="70px" >
        <el-form-item label="用户名" prop="username">
          <el-input v-model="addUserForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="addUserForm.password"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="addUserForm.email"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="mobile">
          <el-input v-model="addUserForm.mobile"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
    <el-button @click="addDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addUser">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>
import NProgress from 'nprogress'
export default {
  name: 'SettingsFirstManage',
  data () {
    // 验证邮箱和手机号的校验规则
    var checkEmail = (rule, value, cb) => {
      // 验证邮箱的正则表达式
      const regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/

      if (regEmail.test(value)) {
        // 合法的邮箱
        return cb()
      }

      cb(new Error('请输入合法的邮箱'))
    }
    var checkMobile = (rule, value, cb) => {
      // 验证手机号的正则表达式
      const regMobile = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/

      if (regMobile.test(value)) {
        return cb()
      }

      cb(new Error('请输入合法的手机号'))
    }

    return {
      queryInfo: {
        pagenum: 1,
        pagesize: 3
      },
      userList: [
      ],
      total: 3,
      queryParams: '',
      addDialogVisible: false,
      switchState: false,
      // 添加用户的表单数据和校验规则
      addUserForm: {
        username: '',
        password: '',
        email: '',
        mobile: ''
      },
      addUserFormRules: {
        username: [
          { required: true, message: '用户名不能为空', trigger: 'blur' },
          { min: 2, max: 10, message: '用户名长度不合法', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          { min: 6, message: '密码长度不能小于6位', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { validator: checkEmail, trigger: 'blur' }
        ],
        mobile: [
          { required: true, message: '请输入手机号', trigger: 'blur' },
          { validator: checkMobile, trigger: 'blur' }
        ]
      }

    }
  },
  created() {
    this.getUserList()
  },
  methods: {
    async getUserList() {
      // const token = window.sessionStorage.getItem('token')
      const { data: res } = await this.$http({
        method: 'post',
        url: 'device/getbypage',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        data: {
          pagenum: this.queryInfo.pagenum,
          pagesize: this.queryInfo.pagesize
        },
        transformRequest: [function (data) {
          let ret = ''
          for (const it in data) {
            ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
          }
          return ret
        }]
      })
      this.userList = res.data.records
      this.total = res.data.total
    },

    handleSizeChange(newSize) {
      // 监听pageSize改变的事件
      this.queryInfo.pagesize = newSize
      // 调用此方法,后端会自动返回指定条数的数据
      this.getUserList()
    },
    handleCurrentChange(newPage) {
      // 监听 页码值 改变的事件
      this.queryInfo.pagenum = newPage
      this.getUserList()
    },
    async deviceStateChange(deviceInfo, event) {
      // event.currentTarget.
      console.log(deviceInfo.id)
      deviceInfo.state = deviceInfo.state === 0 ? 1 : 0
      const { data: res } = await this.$http({
        method: 'post',
        url: 'device/change_state',
        headers: {
          'Content-Type': 'application/json;charset=utf-8'
        },
        data: {
          id: deviceInfo.id,
          state: deviceInfo.state
        }
      }).catch(error => {
        deviceInfo.state = 0
        this.$message.error('请保证同时只选择一种算法')
        NProgress.done()
      })

      this.$message.success(res.message)
    },
    getUserSearchList() {
      this.$message.success(this.queryParams)
    },
    handleAddDialog() {
      this.addDialogVisible = true
    },
    // 监听添加用户对话框的关闭事件
    addDialogClosed() {
      this.$refs.ruleForm.resetFields()
    },
    // 点击按钮，添加新用户
    addUser() {
      // const token = window.sessionStorage.getItem('token')
      this.$refs.ruleForm.validate(async valid => {
        if (!valid) return
        // 可以发起添加用户的网络请求

        const { data: res } = await this.$http.post('users', this.addUserForm)

        if (res.meta.status !== 201) {
          this.$message.error('添加用户失败！' + res.meta.msg)
        }

        this.$message.success('添加用户成功！')
        // 隐藏添加用户的对话框
        this.addDialogVisible = false
        // 重新获取用户列表数据
        this.getUserList()
      })
    },
    async editUser() {
      await this.$router.push('/users/edit')
    },
    async deleteUser() {
      await this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message({
          type: 'success',
          message: '删除成功!'
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    }
  }
}
</script>

<style lang="less" scoped>

</style>
