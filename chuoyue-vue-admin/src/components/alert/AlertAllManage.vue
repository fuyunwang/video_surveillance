<template>
<div>
  <el-breadcrumb separator-class="el-icon-arrow-right">
    <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>报警管理</el-breadcrumb-item>
    <el-breadcrumb-item>报警管理1</el-breadcrumb-item>
  </el-breadcrumb>
  <el-tabs v-model="activeTab">
    <el-tab-pane label="未处理" name="activity">
      <alert-first-manage />
    </el-tab-pane>
    <el-tab-pane label="已处理" name="timeline">
      <alert-second-manage />
    </el-tab-pane>
    <el-tab-pane label="误报警" name="account">
      <alert-third-manage />
    </el-tab-pane>
  </el-tabs>
</div>
</template>

<script>
  import AlertFirstManage from './AlertFirstManage'
  import AlertSecondManage from './AlertSecondManage'
  import AlertThirdManage from './AlertThirdManage'
  export default {
    name: 'AlertAllManage',
    components: { AlertFirstManage, AlertSecondManage, AlertThirdManage },
    data() {
      return {
        activeTab: 'activity'
      };
    },
    methods: {
      handleClick(tab, event) {
        console.log(tab, event)
      }
    }
  }
</script>

<style scoped>

</style>
