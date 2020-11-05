<template>
  <div :id="id" :class="className" :style="{height:height,width:width}" />
</template>

<script>
    import echarts from 'echarts'
    import resize from './mixins/resize'

    export default {
        mixins: [resize],
        props: {
            className: {
                type: String,
                default: 'chart'
            },
            id: {
                type: String,
                default: 'chart'
            },
            width: {
                type: String,
                default: '200px'
            },
            height: {
                type: String,
                default: '200px'
            }
        },
        data() {
            return {
                chart: null,
                chartX: []
            }
        },
        created() {
          const day6=this.getDayofWeek(-6)
          const day5=this.getDayofWeek(-5)
          const day4=this.getDayofWeek(-4)
          const day3=this.getDayofWeek(-3)
          const day2=this.getDayofWeek(-2)
          const day1=this.getDayofWeek(-1)
          const day=this.getDayofWeek(0)
          this.chartX.push(day6)
          this.chartX.push(day5)
          this.chartX.push(day4)
          this.chartX.push(day3)
          this.chartX.push(day2)
          this.chartX.push(day1)
          this.chartX.push(day)
        },
      mounted() {
            this.initChart()
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
              return y+'-'+m+'-'+d
            },
            initChart() {
                this.chart = echarts.init(document.getElementById(this.id))

                this.chart.setOption({
                    backgroundColor: '#394056',
                    title: {
                        top: 20,
                        text: 'Requests',
                        textStyle: {
                            fontWeight: 'normal',
                            fontSize: 16,
                            color: '#F1F1F3'
                        },
                        left: '1%'
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            lineStyle: {
                                color: '#57617B'
                            }
                        }
                    },
                    legend: {
                        top: 20,
                        icon: 'rect',
                        itemWidth: 14,
                        itemHeight: 5,
                        itemGap: 13,
                        data: ['Industries', 'Medical', 'Education'],
                        right: '4%',
                        textStyle: {
                            fontSize: 12,
                            color: '#F1F1F3'
                        }
                    },
                    grid: {
                        top: 100,
                        left: '2%',
                        right: '2%',
                        bottom: '2%',
                        containLabel: true
                    },
                    xAxis: [{
                        type: 'category',
                        boundaryGap: false,
                        axisLine: {
                            lineStyle: {
                                color: '#57617B'
                            }
                        },
                        data: this.chartX
                    }],
                    yAxis: [{
                        type: 'value',
                        name: '(%)',
                        axisTick: {
                            show: false
                        },
                        axisLine: {
                            lineStyle: {
                                color: '#57617B'
                            }
                        },
                        axisLabel: {
                            margin: 10,
                            textStyle: {
                                fontSize: 14
                            }
                        },
                        splitLine: {
                            lineStyle: {
                                color: '#57617B'
                            }
                        }
                    }],
                    series: [{
                        name: 'Industries',
                        type: 'line',
                        smooth: true,
                        symbol: 'circle',
                        symbolSize: 5,
                        showSymbol: false,
                        lineStyle: {
                            normal: {
                                width: 1
                            }
                        },
                        areaStyle: {
                            normal: {
                                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: 'rgba(137, 189, 27, 0.3)'
                                }, {
                                    offset: 0.8,
                                    color: 'rgba(137, 189, 27, 0)'
                                }], false),
                                shadowColor: 'rgba(0, 0, 0, 0.1)',
                                shadowBlur: 10
                            }
                        },
                        itemStyle: {
                            normal: {
                                color: 'rgb(137,189,27)',
                                borderColor: 'rgba(137,189,2,0.27)',
                                borderWidth: 12

                            }
                        },
                        data: [80, 70, 55, 72, 30, 20, 15]
                    }, {
                        name: 'Medical',
                        type: 'line',
                        smooth: true,
                        symbol: 'circle',
                        symbolSize: 5,
                        showSymbol: false,
                        lineStyle: {
                            normal: {
                                width: 1
                            }
                        },
                        areaStyle: {
                            normal: {
                                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: 'rgba(0, 136, 212, 0.3)'
                                }, {
                                    offset: 0.8,
                                    color: 'rgba(0, 136, 212, 0)'
                                }], false),
                                shadowColor: 'rgba(0, 0, 0, 0.1)',
                                shadowBlur: 10
                            }
                        },
                        itemStyle: {
                            normal: {
                                color: 'rgb(0,136,212)',
                                borderColor: 'rgba(0,136,212,0.2)',
                                borderWidth: 12

                            }
                        },
                        data: [50, 70, 60, 90, 34, 40, 80]
                    }, {
                        name: 'Education',
                        type: 'line',
                        smooth: true,
                        symbol: 'circle',
                        symbolSize: 5,
                        showSymbol: false,
                        lineStyle: {
                            normal: {
                                width: 1
                            }
                        },
                        areaStyle: {
                            normal: {
                                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: 'rgba(219, 50, 51, 0.3)'
                                }, {
                                    offset: 0.8,
                                    color: 'rgba(219, 50, 51, 0)'
                                }], false),
                                shadowColor: 'rgba(0, 0, 0, 0.1)',
                                shadowBlur: 10
                            }
                        },
                        itemStyle: {
                            normal: {
                                color: 'rgb(219,50,51)',
                                borderColor: 'rgba(219,50,51,0.2)',
                                borderWidth: 12
                            }
                        },
                        data: [20, 30, 50, 44, 60, 15, 77]
                    }]
                })
            }
        }
    }
</script>
