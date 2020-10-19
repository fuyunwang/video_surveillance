<template>
  <div class="app-container">
    <div class="table-container">
      <el-row :gutter="20">
        <el-col :span="4">
          <el-input placeholder="请输入内容" v-model="input" clearable></el-input>
        </el-col>
        <el-date-picker
          style="margin-right: 20px"
          v-model="value"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="['00:00:00', '23:59:59']">
        </el-date-picker>
        <el-button icon="el-icon-plus" type="primary" >
          添加
        </el-button>
        <el-button icon="el-icon-delete" type="danger">
          删除
        </el-button>
      </el-row>
      <el-card style="margin-top:20px">
        <el-form
          ref="form"
          :inline="true"
          @submit.native.prevent>
          <el-form-item>
            <el-input placeholder="标题" />
          </el-form-item>
          <el-form-item>
            <el-button
              icon="el-icon-search"
              type="primary"
              native-type="submit">
              查询
            </el-button>
          </el-form-item>
        </el-form>

        <el-table :data="devices" stripe border>
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
          class="paginations"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="queryInfo.pagenum"
          :page-sizes="[1, 2, 3, 4, 5, 6]"
          :page-size="queryInfo.pagesize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="devicesTotal">
        </el-pagination>
      </el-card>
    </div>
  </div>
</template>

<script>
import NProgress from 'nprogress'
import { mapGetters } from 'vuex'

export default {
  name: 'Index',
  data() {
    return {
      queryInfo: {
        pagenum: 1,
        pagesize: 3
      },
      queryParams: '',
      switchState: false,
      value: '',
      input: ''
    }
  },
  computed: {
    ...mapGetters(['devices', 'devicesTotal'])
  },
  created() {
    this.getDeviceList()
  },
  methods: {
    getDeviceList() {
      this.loading = true
      this.$store.dispatch('devices/getDevices',this.queryInfo)
        .then((res) => {
          // this.deviceList = this
          this.loading = false
        })
        .catch(() => {
          this.loading = false
        })
    },
    handleSizeChange(newSize) {
      // 监听pageSize改变的事件
      this.queryInfo.pagesize = newSize
      // 调用此方法,后端会自动返回指定条数的数据
      this.getDeviceList()
    },
    handleCurrentChange(newPage) {
      // 监听 页码值 改变的事件
      this.queryInfo.pagenum = newPage
      this.getDeviceList()
    },
    async deviceStateChange(deviceInfo, event) {
      this.loading = true
      console.log(deviceInfo.id)
      const result = deviceInfo.state === 0 ? 1 : 0
      const params = { id: deviceInfo.id, state: result }
      this.$store.dispatch('devices/selectDeviceAlgorithm', params)
        .then((res) => {
          if (res.status === 20000) {
            deviceInfo.state = result
            this.$message.success(res.message)
          } else {
            this.$message.error(res.message)
          }
          this.loading = false
        })
        .catch((error) => {
          console.log(error)
          this.$message.error(error.message)
          this.loading = false
        })
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
<style lang="scss" scoped>
.paginations{
  margin-top: 18px;
}
</style>
