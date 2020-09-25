<template>
    <div>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>数据统计</el-breadcrumb-item>
        <el-breadcrumb-item>数据报表</el-breadcrumb-item>
      </el-breadcrumb>

      <el-card>
        <el-row>
          <el-col  id="main" style="width: 600px;height:400px;"></el-col>
          <el-col id="two" style="width: 600px;height:400px;"></el-col>
        </el-row>
      </el-card>
    </div>
</template>

<script>
import echarts from 'echarts'
export default {
  name: 'ChartFirst',
  data() {
    return {
      chart1: {
        title: {
          text: 'ECharts 示例1'
        },
        grid: {
          top: '25%',
          bottom: '10%'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow',
            label: {
              show: true
            }
          }
        },
        legend: {
          data: ['销售水量', '主营业务']
        },
        xAxis: {
          data: [
            '当年完成水量',
            '去年同期水量',
            '滚动目标值水量',
            '全年目标值水量',
            '当年完成金额',
            '去年同期金额',
            '滚动目标金额',
            '全年目标值'
          ],
          axisLine: {
            show: true // 隐藏X轴轴线
          },
          axisTick: {
            show: true // 隐藏X轴刻度
          },
          axisLabel: {
            show: true
          }
        },
        yAxis: [
          {
            type: 'value',
            name: '亿元',
            splitLine: {
              show: false
            },
            axisTick: {
              show: true
            },
            axisLine: {
              show: true
            },
            axisLabel: {
              show: true
            }
          },
          {
            type: 'value',
            name: '同比',
            position: 'right',
            splitLine: {
              show: false
            },
            axisTick: {
              show: false
            },
            axisLine: {
              show: false
            },
            axisLabel: {
              show: true,
              formatter: '{value} %' // 右侧Y轴文字显示
            }
          },
          {
            type: 'value',
            gridIndex: 0,
            min: 50,
            max: 100,
            splitNumber: 8,
            splitLine: {
              show: false
            },
            axisLine: {
              show: false
            },
            axisTick: {
              show: false
            },
            axisLabel: {
              show: false
            },
            splitArea: {
              show: true,
              areaStyle: {
                color: ['rgba(250,250,250,0.0)', 'rgba(250,250,250,0.05)']
              }
            }
          }
        ],
        series: [
          {
            name: '销售水量',
            type: 'line',
            smooth: true, // 平滑曲线显示
            itemStyle: {
              color: '#058cff'
            },
            areaStyle: {
              color: 'rgba(5,140,255, 0.2)'
            },
            data: [4.2, 3.8, 4.8, 3.5, 2.9, 2.8, 3, 5]
          },
          {
            name: '主营业务',
            type: 'bar',
            barWidth: 15,
            itemStyle: {
              normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: '#00FFE3'
                  },
                  {
                    offset: 1,
                    color: '#4693EC'
                  }
                ])
              }
            },
            data: [4.2, 3.8, 4.8, 3.5, 2.9, 2.8, 3, 5]
          }
        ]
      },
      chart2: {
        title: {
          text: 'ECharts 示例2'
        },
        tooltip: {
          show: true,
          formatter: '{b} : {c}'
        },
        legend: {
          show: true,
          icon: 'circle',
          top: '10%',
          left: '10%',
          width: 50,
          padding: [0, 5],
          itemGap: 25,
          data: ['已婚已育', '已婚未育', '未婚', '学生'],
          selectedMode: true,
          orient: 'vertical'
        },
        series: [
          {
            name: 'Line 4',
            type: 'pie',
            clockWise: true,
            hoverAnimation: false,
            radius: ['65%', '75%'],
            data: [
              {
                value: 7645434,
                name: '已婚已育'
              },
              {
                value: 3612343,
                name: '总数',
                tooltip: {
                  show: false
                },
                itemStyle: {
                  normal: {
                    color: 'rgba(0,0,0,0)',
                    label: {
                      show: false
                    },
                    labelLine: {
                      show: false
                    }
                  },
                  emphasis: {
                    color: 'rgba(0,0,0,0)'
                  }
                }
              }
            ]
          },
          {
            name: 'Line 3',
            type: 'pie',
            clockWise: true,
            radius: ['50%', '60%'],
            itemStyle: {
              normal: {
                label: {
                  show: false
                },
                labelLine: {
                  show: false
                }
                // shadowBlur: 15,
                // shadowColor: 'white',
              }
            },
            hoverAnimation: false,
            data: [
              {
                value: 2632321,
                name: '已婚未育'
              },
              {
                value: 2212343,
                name: '总数',
                tooltip: {
                  show: false
                },
                itemStyle: {
                  normal: {
                    color: 'rgba(0,0,0,0)',
                    label: {
                      show: false
                    },
                    labelLine: {
                      show: false
                    }
                  },
                  emphasis: {
                    color: 'rgba(0,0,0,0)'
                  }
                }
              }
            ]
          },
          {
            name: 'Line 2',
            type: 'pie',
            clockWise: true,
            hoverAnimation: false,
            radius: ['35%', '45%'],
            itemStyle: {
              normal: {
                label: {
                  show: false
                },
                labelLine: {
                  show: false
                }
                // shadowBlur: 15,
                // shadowColor: 'white',
              }
            },
            data: [
              {
                value: 1823323,
                name: '未婚'
              },
              {
                value: 1812343,
                name: '总数',
                tooltip: {
                  show: false
                },
                itemStyle: {
                  normal: {
                    color: 'rgba(0,0,0,0)',
                    label: {
                      show: false
                    },
                    labelLine: {
                      show: false
                    }
                  },
                  emphasis: {
                    color: 'rgba(0,0,0,0)'
                  }
                }
              }
            ]
          },
          {
            name: 'Line 1',
            type: 'pie',
            clockWise: true,
            radius: ['20%', '30%'],
            itemStyle: {
              normal: {
                label: {
                  show: false
                },
                labelLine: {
                  show: false
                }
                // shadowBlur: 15,
                // shadowColor: 'white',
              }
            },
            hoverAnimation: false,
            data: [
              {
                value: 1342221,
                name: '学生'
              },
              {
                value: 1912343,
                name: '总数',
                tooltip: {
                  show: false
                },
                itemStyle: {
                  normal: {
                    color: 'rgba(0,0,0,0)',
                    label: {
                      show: false
                    },
                    labelLine: {
                      show: false
                    }
                  },
                  emphasis: {
                    color: 'rgba(0,0,0,0)'
                  }
                }
              }
            ]
          }
        ]
      }
    }
  },
  mounted() {
    // 页面dom结构初始化完毕
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'))
    var two = echarts.init(document.getElementById('two'))

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(this.chart1)
    two.setOption(this.chart2)
  }
}
</script>

<style lang="less" scoped>

</style>
