<template>
  <div class="app-container">

   <el-card>
     <el-tabs v-model="activeTab" @tab-click="handleClick">
       <el-tab-pane label="未处理" name="activity">
         <alert-first-manage  @notifySecond="notifySecondChild"/>
       </el-tab-pane>
       <el-tab-pane label="已处理" name="timeline">
         <alert-second-manage ref="secondChild"/>
       </el-tab-pane>
       <el-tab-pane label="误报警" name="account">
         <alert-third-manage />
       </el-tab-pane>
     </el-tabs>
   </el-card>
  </div>
</template>

<script>
import AlertFirstManage from './components/AlertFirstManage'
import AlertSecondManage from './components/AlertSecondManage'
import AlertThirdManage from './components/AlertThirdManage'

export default {
  name: 'AlertAllManage',
  components: { AlertFirstManage, AlertSecondManage, AlertThirdManage },
  data() {
   return {
    activeTab: 'activity'
   }
  },
  methods: {
      handleClick(tab, event) {
          console.log(tab)
          console.log(event.target)
      },
      notifySecondChild() {
          this.$refs.secondChild.getAlarmsList()
      }
  }
}
</script>

<style scoped>

</style>
