<template>
    <div>
      <el-card style="margin: 20px">
      <!--<video id="myvideo" class="video-js vjs-default-skin" controls preload="auto" width="800" height="600"  data-setup="{}">
&lt;!&ndash;          &lt;!&ndash; <source src="rtmp://58.200.131.2:1935/livetv/cctv1" type="rtmp/flv"> &ndash;&gt;&ndash;&gt;
          <source src="rtmp://192.168.1.100:1935/live/home" type="rtmp/flv">
          <p class="vjs-no-js">
            <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>
          </p>
        </video>-->
        <!--<video id="myVideo" class="video-js vjs-default-skin" controls preload="auto" width="800px" height="600px"  data-setup="{}">
          <source
            src="rtmp://192.168.1.100:1935/live/home"
            type="rtmp/flv">
        </video>-->
<!--        <vab-player-mp4 :config="config1" @player="Player1 = $event" style="width: 800px;height: 400px"/>-->
<!--        <video-player class="video-player vjs-custom-skin" ref="videoPlayer" :playsinline="true" :options="playerOptions"/>-->
        <video-player class="vjs-custom-skin" ref="videoPlayer" :options="playerOptions" style="width: 800px;height: 800px"></video-player>
      </el-card>
    </div>
</template>

<script>
// import {videoPlayer}
export default {
  name: 'Index',
  data() {
    return {
      playerOptions: {
        autoplay: false, // 自动播放
        controls: true, // 是否显示控制栏
        techOrder: ['flash', 'html5'], // 兼容顺序
        sourceOrder: true,
        flash: { hls: { withCredentials: false } },
        html5: { hls: { withCredentials: false } },
        sources: [{ // 流配置，数组形式，会根据兼容顺序自动切换
          type: 'rtmp/mp4',
          src: 'rtmp://127.0.0.1:1935/live/home'
        }, {
          withCredentials: false,
          type: 'application/x-mpegURL',
          src: 'http://playertest.longtailvideo.com/adaptive/bipbop/gear4/prog_index.m3u8'
        }]
        // poster: "/static/img/no_data.png", // 播放器默认图片
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
