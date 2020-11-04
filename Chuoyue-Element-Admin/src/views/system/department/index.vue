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
        <el-button icon="el-icon-search" type="primary" @click="handleAdd">
          查询
        </el-button>
      </el-row>
      <el-card style="margin-top:20px">
        <el-form
          ref="form"
          :inline="true"
          @submit.native.prevent>
          <el-form-item>
            <el-button icon="el-icon-plus" size="small" type="primary" @click="handleAdd">
              添加
            </el-button>
          </el-form-item>
          <el-form-item>
            <el-button icon="el-icon-edit" size="small" type="success" @click="handleAdd">
              修改
            </el-button>
          </el-form-item>
          <el-form-item>
            <el-button icon="el-icon-delete" size="small" type="danger" @click="handleDelete">
              删除
            </el-button>
          </el-form-item>
          <el-form-item>
            <el-button icon="el-icon-download" size="small" type="warning" @click="handleAdd">
              导出
            </el-button>
          </el-form-item>
        </el-form>

        <el-table :data="userList" stripe border>
          <!--<el-table-column
            type="selection"
            width="55">
          </el-table-column>-->
          <el-table-column type="expand">
            <template slot-scope="scope">
              <!-- 循环渲染Tag标签 -->
              <el-tag v-for="(item, i) in scope.row.attr_vals" :key="i" closable @close="handleClose(i, scope.row)">{{item}}</el-tag>
              <!-- 输入的文本框 -->
              <el-input class="input-new-tag" v-if="scope.row.inputVisible" v-model="scope.row.inputValue" ref="saveTagInput" size="small" @keyup.enter.native="handleInputConfirm(scope.row)" @blur="handleInputConfirm(scope.row)">
              </el-input>
              <!-- 添加按钮 -->
              <el-button v-else class="button-new-tag" size="small" @click="showInput(scope.row)">+ New Tag</el-button>
            </template>
          </el-table-column>
          <el-table-column label="用户名" prop="agentName" ></el-table-column>
          <el-table-column label="手机号" prop="agentPhone"></el-table-column>
          <el-table-column label="邮箱" prop="agentEmail" ></el-table-column>
          <el-table-column show-overflow-tooltip label="头像" height="160px" width="220px">
            <template slot-scope="scope">
              <el-image
                style="width: 100px; height: 100px"
                :src="scope.row.agentIdcardImg"
              ></el-image>
            </template>
          </el-table-column>
          <el-table-column label="状态"  width="120px">
            <template slot-scope="scope">
              <span v-if="scope.row.status === 0">正常</span>
              <span v-else>异常</span>
            </template>
          </el-table-column>
          <el-table-column label="创建时间" >
            <template slot-scope="scope">
              <i class="el-icon-time"></i>
              <span style="margin-left: 10px">{{ scope.row.createTime }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
              <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>

        <!--分页区域-->
        <el-pagination
          style="margin-top: 20px"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="queryInfo.pagenum"
          :page-sizes="[1, 2, 3, 4, 5, 6]"
          :page-size="queryInfo.pagesize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="userListTotal">
        </el-pagination>
        <!--        <table-edit ref="edit"></table-edit>-->
      </el-card>
    </div>
  </div>
</template>

<script>
  import {mapGetters} from 'vuex'
  export default {
    name: 'Index',
    data() {
      return {
        queryInfo: {
          pagenum: 1,
          pagesize: 5
        },
        queryParams: '',
        switchState: false,
        value: '',
        input: ''
      }
    },
    created() {
      this.getUserLists()
    },
    methods: {
      getUserLists() {
        this.loading = true
        this.$store.dispatch('user/getUserByPage', this.queryInfo)
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
        this.getUserLists()
      },
      handleCurrentChange(newPage) {
        // 监听 页码值 改变的事件
        this.queryInfo.pagenum = newPage
        this.getUserLists()
      },
      handleSelectionChange(val) {
        console.log(val)
      }
    },
    computed: {
      ...mapGetters(['userList', 'userListTotal'])
    }
  }
</script>
<style lang="scss" scoped>
  .table-container{

  }
</style>
