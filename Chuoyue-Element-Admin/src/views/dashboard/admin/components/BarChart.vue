<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'

const animationDuration = 6000

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '300px'
    }
  },
  data() {
    return {
      chart: null,
      chartX: []
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.initChart()
    })
  },
  created() {
    const day4 = this.getDayofWeek(-4)
    const day3 = this.getDayofWeek(-3)
    const day2 = this.getDayofWeek(-2)
    const day1 = this.getDayofWeek(-1)
    const day = this.getDayofWeek(0)
    this.chartX.push(day4)
    this.chartX.push(day3)
    this.chartX.push(day2)
    this.chartX.push(day1)
    this.chartX.push(day)
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    getDayofWeek(day) {
      var dd = new Date()
      dd.setDate(dd.getDate() + (day)) // 获取p_count天后的日期
      var y = dd.getFullYear()
      var m = dd.getMonth() + 1 // 获取当前月份的日期
      if (m < 10) {
          m = '0' + m
      }
      var d = dd.getDate()
      if (d < 10) {
          d = '0' + d
      }
      return m+'-'+d
    },
    initChart() {
      this.chart = echarts.init(this.$el, 'macarons')

      this.chart.setOption({
        tooltip: {
          trigger: 'axis',
          axisPointer: { // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          top: 10,
          left: '2%',
          right: '2%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [{
          type: 'category',
          data: this.chartX,
          axisTick: {
            alignWithLabel: true
          }
        }],
        yAxis: [{
          type: 'value',
          axisTick: {
            show: false
          }
        }],
        series: [{
          name: 'Industries',
          type: 'bar',
          stack: 'vistors',
          barWidth: '60%',
          data: [10, 8, 9, 12, 11, 10],
          animationDuration
        }, {
          name: 'Medical',
          type: 'bar',
          stack: 'vistors',
          barWidth: '60%',
          data: [8, 5, 10, 5, 10, 12],
          animationDuration
        }, {
          name: 'Education',
          type: 'bar',
          stack: 'vistors',
          barWidth: '60%',
          data: [5, 5, 10, 7, 12, 14],
          animationDuration
        }]
      })
    }
  }
}
</script>
