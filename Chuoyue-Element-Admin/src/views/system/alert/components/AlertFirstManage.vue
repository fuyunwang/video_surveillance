<template>
  <div>
    <el-row :gutter="20" style="margin-bottom: 20px">
        <el-col :span="8">
          <el-input placeholder="请输入内容" clearable>
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary">添加报警</el-button>
        </el-col>
      </el-row>
      <el-table :data="departments" stripe border>
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column label="组织" prop="departmentName" ></el-table-column>
        <el-table-column label="报警时间" prop="alarmTime" ></el-table-column>
        <el-table-column label="事件类型" prop="incidentType" ></el-table-column>
        <el-table-column label="设备名称" prop="deviceName" ></el-table-column>
        <el-table-column show-overflow-tooltip label="播放视频" >
          <template slot-scope="scope">
            <el-button @click="handlePlayer(scope.row.id)">安全绳检测</el-button>
          </template>
        </el-table-column>
        <el-table-column label="状态" width="150px">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{scope.row.status === 0 ? '已处理' : '未处理'}}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="100px">
          <template slot-scope="scope">
            <el-tooltip  effect="dark" content="报警处置" placement="top" :enterable="false">
              <el-button type="warning" icon="el-icon-setting" size="mini" @click="handleDisposeDialog(scope.row.id)"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <!--分页区域-->
      <el-pagination
        style="margin-top: 18px"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pagenum"
        :page-sizes="[1, 2, 3, 4, 5, 6]"
        :page-size="queryInfo.pagesize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="departmentTotal">
      </el-pagination>

    <el-dialog
      title="报警处置"
      style="text-align: center"
      :visible.sync="disposeDialogVisible"
      width="40%"
      @close="disposeDialogClosed">
      <el-row :gutter="24" >
        <el-col :span="10">
          <template>

              <el-image
                :src="currentDepartment.screenShot">
              </el-image>
          </template>
        </el-col>
        <el-col :span="14" >
          <!--Dialog内容主体区域-->
          <el-form :model="videoDetectResult" :rules="disposalAlarmRules" label-width="70px" ref="disposalAlarmForm">
            <el-form-item label="组织名:" prop="departmentName">
              {{currentDepartment.departmentName}}
            </el-form-item>
            <el-form-item label="设备名:" prop="deviceName">
              {{currentDepartment.deviceName}}
            </el-form-item>
            <el-form-item label="类型:" prop="incidentType">
              {{currentDepartment.incidentType}}
            </el-form-item>
            <el-form-item label="时间:" prop="incidentType">
              {{currentDepartment.alarmTime}}
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
      <el-button @click="disposeDialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="disposalAlarm">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog
      title="视频播放"
      style="text-align: center"
      :visible.sync="videoPlayerDialogVisible"
      width="40%"
      @close="videoPlayerDialogClose">
      <el-row :gutter="24" >
          <template v-if="videoPlayerDialogVisible">
            <div v-if="config1.url !== ''">
              <vab-player-mp4 :config="config1" @player="Player1 = $event" />
            </div>
            <div v-else>
              暂无视频源
            </div>
          </template>
      </el-row>
      <span  slot="footer" class="dialog-footer">
      <el-button @click="videoPlayerDialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="detectPerson">开始检测</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
import { VabPlayerMp4 } from 'zx-player'
// import { VabPlayerMp4 } from '@/plugins/vabPlayer.js'
import { mapGetters } from 'vuex'
import { Loading } from 'element-ui'
import axios from 'axios'

export default {
  name: 'AlertFirstManage',
  components: {
    VabPlayerMp4
  },
  data() {
    return {
      config1: {
        id: '12',
        url: 'http://120.27.20.141/ming/111.mp4',
        volume: 1,
        autoplay: true
      },
      currentDetectType: '',
      Player1: null,
      queryInfo: {
        pagenum: 1,
        pagesize: 3
      },
      disposeDialogVisible: false,
      videoPlayerDialogVisible: false,
      disposalAlarmRules: {
        note: [
          { required: true, message: '备注不能为空', trigger: 'blur' }
        ],
        contact: [
          { required: true, message: '联系方式不合法', trigger: 'blur' }
        ]
      },
      videoDetectResult: {
        note: '',
        contact: ''
      }
    }
  },
  computed: {
    ...mapGetters(['departments', 'departmentTotal', 'currentDepartment','deviceList'])
  },
  created() {
    this.getDepartments()
  },
  methods: {
    getDepartments() {
        this.loading = true
        this.$store.dispatch('department/getDepartmentList', this.queryInfo)
            .then((res) => {
                // this.deviceList = this
              console.log(res)
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
      this.getDepartments()
    },
    handleCurrentChange(newPage) {
      // 监听 页码值 改变的事件
      this.queryInfo.pagenum = newPage
      this.getDepartments()
    },
    handleDisposeDialog(id) {
      this.loading = true
      this.$store.dispatch('department/getDepartmentInfo', id).then((res) => {
        // console.log('DepartmentInfo' + res)
        this.loading = false
        console.log(this.currentDepartment)
        this.disposeDialogVisible = true
      })
      .catch(() => {
        this.loading = false
      })
    },
    handlePlayer(id) {
      this.config1.url = this.deviceList[id].screenShot
      this.currentDetectType = this.deviceList[id].note
      this.videoPlayerDialogVisible = true
    },
    disposeDialogClosed() {
      this.$refs.disposalAlarmForm.resetFields()
    },
    videoPlayerDialogClose() {
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
          video_url: this.config1.url,
          detect_type: this.currentDetectType
        }
      })
      this.getDepartments()
      if (res.status !== 20000) {
        this.$message.error('服务器内部错误,检测失败' + res.message)
        console.log(res.message)
        loading.close()
      } else {
        this.$message.success(res.message)
      }
      this.videoPlayerDialogVisible = false
      loading.close()
    },
    disposalAlarm() {
      this.$refs.disposalAlarmForm.validate(async valid => {
        if (!valid) {
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
        this.loading = true
        startLoading()
        const params = {
          departmentId: this.currentDepartment.departmentId,
          contact: this.videoDetectResult.contact,
          note: this.videoDetectResult.note
        }

        this.$store.dispatch('department/disposeAlarm', params).then((res) => {
          console.log(res)
          if (res.status === 20000){
            this.$message.success(res.message)
          } else {
            this.$message.error(res.message)
          }
          loading.close()
          this.loading = false
          this.disposeDialogVisible = false
          this.$emit('notifySecond')
        })
        .catch(() => {
          this.loading = false
          loading.close()
          this.disposeDialogVisible = false
        })


      })
    }
  }
}
</script>

<style lang="less" scoped>

</style>
