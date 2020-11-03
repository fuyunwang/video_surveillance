<template>
    <div>
      <el-card style="margin: 20px">
      <video id="myvideo" class="video-js vjs-default-skin" controls preload="auto" width="800" height="600"  data-setup="{}">
<!--          &lt;!&ndash; <source src="rtmp://58.200.131.2:1935/livetv/cctv1" type="rtmp/flv"> &ndash;&gt;-->
          <source src="rtmp://192.168.1.100:1935/live/home" type="rtmp/flv">
          <p class="vjs-no-js">
            <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>
          </p>
        </video>
        <!--<video id="myVideo" class="video-js vjs-default-skin" controls preload="auto" width="800px" height="600px"  data-setup="{}">
          <source
            src="rtmp://192.168.1.100:1935/live/home"
            type="rtmp/flv">
        </video>-->
<!--        <vab-player-mp4 :config="config1" @player="Player1 = $event" style="width: 800px;height: 400px"/>-->
<!--        <video-player class="video-player vjs-custom-skin" ref="videoPlayer" :playsinline="true" :options="playerOptions"/>-->
      </el-card>
    </div>
</template>

<script>
// import { VabPlayerMp4 } from '@/plugins/vabPlayer.js'
// import '../../../plugins/video.min.js'
// import '../../../plugins/videojs.min.js'
// import 'videojs-flash'
// import 'videojs-flash'
import 'video.js/dist/video-js.css'
import { videoPlayer } from 'vue-video-player'
// import VideoPlayer from 'vue-video-player'
export default {
  name: 'Index',
  components: {
    // VabPlayerMp4
    // VideoPlayer
    videoPlayer
  },
  data() {
    return {
      playerOptions: {
        playbackRates: [0.7, 1.0, 1.5, 2.0], // 播放速度
        autoplay: false, // 如果true,浏览器准备好时开始回放。
        muted: false, // 默认情况下将会消除任何音频。
        loop: false, // 导致视频一结束就重新开始。
        preload: 'auto', // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
        language: 'zh-CN',
        aspectRatio: '16:9', // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
        techOrder: ['flash', 'html5'], // 兼容顺序
        flash: {

          hls: { withCredentials: false },
          swf: 'static/video-js.swf' // 引入静态文件swf
        },
        html5: { hls: { withCredentials: false }},
        sources: [{ // 流配置，数组形式，会根据兼容顺序自动切换
          type: 'rtmp/hls',
          src: 'rtmp://192.168.1.100:1935/live/home'
        }],
        poster: '', // 你的封面地址
        // width: document.documentElement.clientWidth,
        notSupportedMessage: '此视频暂无法播放，请稍后再试', // 允许覆盖Video.js无法播放媒体源时显示的默认信息。
        controlBar: {
          timeDivider: true,
          durationDisplay: true,
          remainingTimeDisplay: false,
          fullscreenToggle: false // 全屏按钮
        }
      }
    }
  },
  mounted() {
  },
  methods: {
  }
}
</script>

<style lang="scss" scoped>

</style>
