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
        <el-table-column show-overflow-tooltip label="播放视频" >
          <template slot-scope="scope">
            <el-button @click="handlePlayer(scope.row.id)">点击播放视频{{scope.row.note}}</el-button>
          </template>
        </el-table-column>
        <el-table-column label="状态" width="150px">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{scope.row.status === 1 ? '已处理' : '未处理'}}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="100px">
          <template slot-scope="scope">
            <el-tooltip  effect="dark" content="报警处置" placement="top" :enterable="false">
              <el-button type="warning" icon="el-icon-setting" size="mini" @click="handleAddDialog(scope.row.id)"></el-button>
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
      title="报警处置"
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
          <el-form :model="videoDetectResult" :rules="disposalAlarmRules" label-width="70px" ref="disposalAlarmForm">
            <el-form-item label="组织名:" prop="departmentName">
              {{videoDetectResult.departmentName}}
            </el-form-item>
            <el-form-item label="设备名:" prop="deviceName">
              {{videoDetectResult.deviceName}}
            </el-form-item>
            <el-form-item label="类型:" prop="incidentType">
              {{videoDetectResult.incidentType}}
            </el-form-item>
            <el-form-item label="时间:" prop="incidentType">
              {{videoDetectResult.alarmTime}}
            </el-form-item>
            <el-form-item label="备注:" prop="note">
              <el-input v-model="videoDetectResult.note"></el-input>
            </el-form-item>
            <el-form-item label="手机号:" prop="contact">
              <el-input v-model="videoDetectResult.contact"></el-input>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
      <span  slot="footer" class="dialog-footer">
      <el-button @click="addDialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="disposalAlarm">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="视频播放"
      style="text-align: center"
      :visible.sync="videoPlayerDialog"
      width="40%"
      @close="addDialogClosed">
      <el-row :gutter="24" >
          <template v-if="videoPlayerDialog">
            <div v-if="config1.url !== ''">
              <vab-player-mp4 :config="config1" @player="Player1 = $event" />
            </div>
            <div v-else>
              暂无视频源
            </div>
          </template>

      </el-row>
      <span  slot="footer" class="dialog-footer">
    <el-button @click="videoPlayerDialog = false">取 消</el-button>
    <el-button type="primary" @click="detectPerson">开始检测</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>
import { VabPlayerMp4, VabPlayerHls } from '@/plugins/vabPlayer.js'
import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'
import { Loading } from 'element-ui'
import axios from 'axios'
import Vue from 'vue'

export default {
  name: 'AlertFirstManage',
  components: {
    VabPlayerMp4,
    VabPlayerHls
  },
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
      config1: {
        id: '12',
        url: 'http://qi2c9qbdt.hb-bkt.clouddn.com/person_detect.mp4',
        volume: 1,
        autoplay: true
      },
      Player1: null,
      queryInfo: {
        pagenum: 1,
        pagesize: 3
      },
      userList: [
      ],
      currentScreenShot: '',
      videoDetectResult: {
        currentScreenShot: '',
        departmentName: '',
        alarmTime: '',
        deviceName: '',
        incidentType: '',
        note: '',
        contact: '',
        departmentId: 1
      },
      total: 3,
      queryParams: '',
      addDialogVisible: false,
      videoPlayerDialog: false,
      // 添加用户的表单数据和校验规则
      addUserForm: {
        username: '',
        password: '',
        email: '',
        mobile: ''
      },
      disposalAlarmRules: {
        note: [
          { required: true, message: '备注不能为空', trigger: 'blur' },
        ],
        contact: [
          { required: true, message: '联系方式不合法', trigger: 'blur' },
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
        url: 'department/getbypage',
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
      this.setDevices(res.data.records)
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
      const { data: res3 } = await this.$http({
        method: 'post',
        url: 'department-solved/getbyid',
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
      this.videoDetectResult.currentScreenShot = res3.data.screenShot
      this.videoDetectResult.alarmTime = res3.data.alarmTime
      this.videoDetectResult.incidentType = res3.data.incidentType
      this.videoDetectResult.departmentName = res3.data.departmentName
      this.videoDetectResult.deviceName = res3.data.deviceName
      this.videoDetectResult.departmentId = res3.data.departmentId

      this.addDialogVisible = true
    },
    handlePlayer(id) {
      console.log(id)
      this.config1.url = this.devices[id].screenShot
      // console.log(this.devices[id].screenShot)
      // this.config1.id = this.devices[id].id
      // Vue.set(this.config1,'id',this.devices[id].id)
      // this.config1 = this.videoConfigs[id]
      this.videoPlayerDialog = true
    },
    // 监听添加用户对话框的关闭事件
    addDialogClosed() {
      this.$refs.ruleForm.resetFields()
    },

    addUser() {
      // const token = window.sessionStorage.getItem('token')
      // this.$refs.ruleForm.validate(async valid => {
      //   if (!valid) return
      //   // 可以发起添加用户的网络请求
      //
      //   const { data: res } = await this.$http.post('users', this.addUserForm)
      //
      //   if (res.meta.status !== 201) {
      //     this.$message.error('添加用户失败！' + res.meta.msg)
      //   }
      //
      //   this.$message.success('添加用户成功！')
      //   // 隐藏添加用户的对话框
      //   this.addDialogVisible = false
      //   // 重新获取用户列表数据
      //   this.getUserList()
      // })
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
    },
    async detectPerson() {
      let loading
      const startLoading = () => {
        loading = Loading.service({
          lock: true,
          text: '检测中……',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        })
      }
      startLoading()

      const { data: res } = await axios({
        url: 'http://127.0.0.1:5000/video_detect/person',
        method: 'post',
        data: {
          video_url: this.config1.url
        }
      })

      const { data: res2 } = await this.$http({
        method: 'post',
        url: 'department/getbypage',
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
      this.userList = res2.data.records
      this.total = res2.data.total

      this.videoPlayerDialog = false
      if (res.status !== 20000) {
        this.$message.error('服务器内部错误,检测失败' + res.message)
        console.log(res.message)
        loading.close()
      }
      this.$message.success(res.message)
      loading.close()
    },
    disposalAlarm(){
      this.$refs.disposalAlarmForm.validate(async valid => {
        if (!valid){
          return
        }
        let loading
        const startLoading = () => {
          loading = Loading.service({
            lock: true,
            text: '正在处置……',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)'
          })
        }
        startLoading()
        const { data: res } = await this.$http({
          method: 'post',
          url: 'department-solved/dispose',
          headers: {
            'Content-Type': 'application/json;charset=utf-8'
          },
          data: {
            departmentId: this.videoDetectResult.departmentId,
            contact: this.videoDetectResult.contact,
            note: this.videoDetectResult.note
          }
        })
        if (res.status !== 20000) {
          this.addDialogVisible = false
          return this.$message.error(res.data.message)
          loading.close()
        }
        this.addDialogVisible = false
        this.$message.success(res.message)
        loading.close()
        // this.$parent.$children
        this.$emit("notifySecond")
      })
    },
    ...mapMutations(['setDevices'])
  },
  computed:{
    ...mapState(['devices','videoConfigs'])
  }
}
</script>

<style lang="less" scoped>

</style>
