<template>
    <div>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>权限管理</el-breadcrumb-item>
        <el-breadcrumb-item>角色列表</el-breadcrumb-item>
      </el-breadcrumb>

      <el-card>
        <el-row>
          <el-col>
            <el-button type="primary">添加角色</el-button>
          </el-col>
        </el-row>
        <!--角色列表-->
        <el-table :data="roleList" border stripe>
          <el-table-column type="expand">
            <template slot-scope="scope">
              <el-row :class="['bdbottom', i1 === 0 ? 'bdtop' : '', 'vcenter']" v-for="(item1,i1) in scope.row.children" :key="item1.id" :index="i1">
                <!--渲染一级列表-->
                <el-col :span="5">
                  <el-tag closable>
                    {{item1.authName}}
                  </el-tag>
                  <i class="el-icon-caret-right"></i>
                </el-col>
                <!--渲染二级三级列表-->
                <el-col :span="19">
                  <el-row :class="[i2 ===0 ? '' : 'bdtop', 'vcenter']" v-for="(item2,i2) in item1.children" :key="item2.id" :index="i2">
                    <!--二级-->
                    <el-col :span="6">
                      <el-tag type="success" closable>{{item2.authName}}</el-tag>
                      <i class="el-icon-caret-right"></i>
                    </el-col>
                    <!--三级-->
                    <el-col :span="18">
                      <el-tag type="warning" v-for="(item3,i3) in item2.children" :key="item3.id" :index="i3" closable @close="removeRightById(scope.row,item3.id)">
                        {{item3.authName}}
                      </el-tag>
                    </el-col>
                  </el-row>
                </el-col>
              </el-row>
            </template>
          </el-table-column>
          <el-table-column type="index" label="#"></el-table-column>
          <el-table-column label="角色名称" prop="roleName" width="300px"></el-table-column>
          <el-table-column label="角色描述" prop="roleDesc" width="300px"></el-table-column>
          <el-table-column label="操作">
            <template slot-scope="">
              <el-button size="mini" type="primary" icon="el-icon-edit" @click="editRole">编辑</el-button>
              <el-button size="mini" type="danger" icon="el-icon-delete">删除</el-button>
              <el-button size="mini" type="warning" icon="el-icon-setting">分配权限</el-button>
            </template>
          </el-table-column>
        </el-table>

      </el-card>
    </div>
</template>

<script>
export default {
  name: 'Roles',
  data() {
    return {
      roleList: []
    }
  },
  created() {
    this.getRolesList()
  },
  methods: {
    async getRolesList() {
      // 获取所有角色的列表
      const { data: res } = await this.$http.request({
        url: 'roles',
        method: 'GET'
      })
      if (res.meta.status !== 200) {
        return this.$message.error('获取角色列表失败')
      }
      this.roleList = res.data
      console.log(this.roleList)
    },
    async removeRightById(role, rightId) {
      // 弹窗提示用户是否要删除
      const confirmResult =
      await this.$confirm('此操作将永久删除该权限, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      if (confirmResult !== 'confirm') {
        return this.$message.info({
          message: '取消了删除'
        })
      }
      this._removeRight(role, rightId)
    },
    async _removeRight(role, rightId) {
      // const { data: res } = await this.$http.delete(`roles/${role}/rights/${parseFloat(rightId)}`)
      // if (res.meta.status !== 200) {
      //   return this.$message.error('删除失败' + res.meta.msg)
      // }
      this.$message({
        type: 'success',
        message: `角色项${rightId} 删除成功!`
      })
      role.children = role.children.remove(role.children.length - 1)
    },
    async editRole() {
      await this.$router.push('/roles/edit')
    }
  }
}
</script>

<style lang="less" scoped>
  .el-tag {
    margin: 7px;
  }

  .bdtop {
    border-top: 1px solid #eee;
  }

  .bdbottom {
    border-bottom: solid #eee;
  }
  .vcenter{
    display: flex;
    align-items: center;//纵向居中对齐
  }
</style>
