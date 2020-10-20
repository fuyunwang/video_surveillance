<template>
  <div>

      <el-row :gutter="20">
        <el-col :span="8">
          <el-input placeholder="请输入内容" clearable >
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary">添加报警</el-button>
        </el-col>
      </el-row>

      <el-table :data="alarmResults" stripe border style="margin-top: 20px">
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column label="组织" prop="departmentName" ></el-table-column>
        <el-table-column label="报警时间" prop="alarmTime" ></el-table-column>
        <el-table-column label="事件类型" prop="incidentType" ></el-table-column>
        <el-table-column label="设备名称" prop="deviceName" ></el-table-column>
        <el-table-column show-overflow-tooltip label="抓拍图" height="160px" width="220px">
          <template slot-scope="scope">
            <el-image
              style="width: 100px; height: 100px"
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
            <el-tooltip  effect="dark" content="告警详细信息" placement="top" :enterable="false">
              <el-button type="warning" icon="el-icon-info" size="mini" @click="alarmDetailInfo(scope.row.id)"></el-button>
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
        :total="alarmTotal">
      </el-pagination>
<!--    </el-card>-->

    <el-dialog
      title="告警处置信息"
      style="text-align: center"
      :visible.sync="alarmDetailDialogVisible"
      width="40%">
      <el-row :gutter="24" >
        <el-col :span="10">
          <template>
            <el-image
              :src="currentSolvedDepartment.screenShot">
            </el-image>
          </template>
        </el-col>
        <el-col :span="14" >
          <!--Dialog内容主体区域-->
          <el-form :model="currentSolvedDepartment" label-width="70px">
            <el-form-item label="组织名:" prop="departmentName">
              {{currentSolvedDepartment.departmentName}}
            </el-form-item>
            <el-form-item label="设备名:" prop="deviceName">
              {{currentSolvedDepartment.deviceName}}
            </el-form-item>
            <el-form-item label="类型:" prop="incidentType">
              {{currentSolvedDepartment.incidentType}}
            </el-form-item>
            <el-form-item label="状态:" prop="incidentType">
              已处置
            </el-form-item>
            <el-form-item label="时间:" prop="incidentType">
              {{currentSolvedDepartment.alarmTime}}
            </el-form-item>
            <el-form-item label="备注:" prop="note">
              {{currentSolvedDepartment.note}}
            </el-form-item>
            <el-form-item label="手机号:" prop="contact">
              {{currentSolvedDepartment.contact}}
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
      <span  slot="footer" class="dialog-footer">
      <el-button @click="alarmDetailDialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="alarmDetailDialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'AlertSecondManage',
  data() {
    return {
      queryInfo: {
        pagenum: 1,
        pagesize: 3
      },
      alarmDetailDialogVisible: false
    }
  },
  computed: {
    ...mapGetters(['alarmResults', 'alarmTotal', 'currentSolvedDepartment'])
  },
  created() {
    this.getAlarmsList()
  },
  methods: {
    getAlarmsList() {
      this.loading = true
      this.$store.dispatch('alarm/getDisposedAlarms', this.queryInfo)
        .then((res) => {
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
      this.getAlarmsList()
    },
    handleCurrentChange(newPage) {
      // 监听 页码值 改变的事件
      this.queryInfo.pagenum = newPage
      this.getAlarmsList()
    },
    alarmDetailInfo(id) {
      this.loading = true
      console.log(id)
      this.$store.dispatch('alarm/getDetailAlarmInfo', id)
        .then((res) => {
          console.log(res)
          this.alarmDetailDialogVisible = true
          this.loading = false
        })
        .catch(() => {
          this.loading = false
        })

    }
  }
}
</script>

<style lang="less" scoped>

</style>
