<template>
  <div>

    <!--卡片视图-->
    <el-card class="box-card">

      <el-row :gutter="20">
        <el-col :span="8">
          <el-input placeholder="请输入内容" v-model="queryParams" clearable @clear="getUserSearchList">

            <el-button slot="append" icon="el-icon-search" @click="getUserSearchList"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="handleAddDialog">添加报警</el-button>
        </el-col>
      </el-row>
      <el-table :data="userList" stripe border>
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column label="组织" prop="departmentName" ></el-table-column>
        <el-table-column label="报警时间" prop="alarmTime" ></el-table-column>
        <el-table-column label="事件类型" prop="incidentType" ></el-table-column>
        <el-table-column label="设备名称" prop="deviceName" ></el-table-column>
        <el-table-column show-overflow-tooltip label="抓拍图" height="160px" width="220px">
          <template slot-scope="scope">
            <el-image
              :src="scope.row.screenShot"
            ></el-image>
          </template>
        </el-table-column>
        <el-table-column label="状态" width="150px" height="200px">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{scope.row.status === 1 ? '已处置' : '未处置'}}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="100px">
          <template slot-scope="scope">

            <!--            <el-button type="primary" icon="el-icon-edit" size="mini" @click="editUser"></el-button>-->
            <!--            &lt;!&ndash;删除&ndash;&gt;-->
            <!--            <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteUser"></el-button>-->
            <!--分配角色-->
            <el-tooltip  effect="dark" content="告警详细信息" placement="top" :enterable="false">
              <el-button type="warning" icon="el-icon-info" size="mini" @click="handleAddDialog(scope.row.id)"></el-button>
            </el-tooltip>
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
      title="告警处置信息"
      style="text-align: center"
      :visible.sync="addDialogVisible"
      width="40%"
      @close="addDialogClosed">
      <el-row :gutter="24" >
        <el-col :span="10">
          <template>
            <el-image
              :src="this.videoDetectResult.currentScreenShot">
            </el-image>
          </template>
        </el-col>
        <el-col :span="14" >
          <!--Dialog内容主体区域-->
          <el-form :model="videoDetectResult" label-width="70px">
            <el-form-item label="组织名:" prop="departmentName">
              {{videoDetectResult.departmentName}}
            </el-form-item>
            <el-form-item label="设备名:" prop="deviceName">
              {{videoDetectResult.deviceName}}
            </el-form-item>
            <el-form-item label="类型:" prop="incidentType">
              {{videoDetectResult.incidentType}}
            </el-form-item>
            <el-form-item label="状态:" prop="incidentType">
              已处置
            </el-form-item>
            <el-form-item label="时间:" prop="incidentType">
              {{videoDetectResult.alarmTime}}
            </el-form-item>
            <el-form-item label="备注:" prop="note">
              {{videoDetectResult.note}}
            </el-form-item>
            <el-form-item label="手机号:" prop="contact">
              {{videoDetectResult.contact}}
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
      <span  slot="footer" class="dialog-footer">
      <el-button @click="addDialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="addDialogVisible = false">确 定</el-button>
  </span>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: 'AlertSecondManage',
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
      videoDetectResult: {
        currentScreenShot: '',
        departmentName: '',
        alarmTime: '',
        deviceName: '',
        incidentType: '',
        note: '',
        contact: ''
      },
      queryInfo: {
        pagenum: 1,
        pagesize: 3
      },
      userList: [
      ],
      currentScreenShot: '',
      total: 3,
      queryParams: '',
      addDialogVisible: false,
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
    console.log('init......................')
  },
  methods: {
    async getUserList() {
      // const token = window.sessionStorage.getItem('token')
      const { data: res } = await this.$http({
        method: 'post',
        url: 'department-solved/getbypage',
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
    async userStateChange(userInfo) {
      const token = window.sessionStorage.getItem('token')
      // 监听Switch开关状态的改变
      const { data: res } = await this.$http.post(`users/${userInfo.id}/state/${userInfo.mg_state}`, {
        headers: {
          Authorization: token
        }
      })
      if (res.meta.status !== 200) {
        userInfo.mg_state = !userInfo.mg_state
        return this.$message.error('更新用户失败 ' + res.meta.msg)
      }
      this.$message.success('更新用户状态成功')
    },
    getUserSearchList() {
      this.$message.success(this.queryParams)
    },
    async handleAddDialog(id) {
      const { data: res } = await this.$http({
        method: 'post',
        url: 'department-solved/getone',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        data: {
          id: id,
        },
        transformRequest: [function (data) {
          let ret = ''
          for (const it in data) {
            ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
          }
          return ret
        }]
      })
      this.videoDetectResult.currentScreenShot = res.data.screenShot
      this.videoDetectResult.alarmTime = res.data.alarmTime
      this.videoDetectResult.incidentType = res.data.incidentType
      this.videoDetectResult.departmentName = res.data.departmentName
      this.videoDetectResult.deviceName = res.data.deviceName
      this.videoDetectResult.note = res.data.note
      this.videoDetectResult.contact = res.data.contact

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
