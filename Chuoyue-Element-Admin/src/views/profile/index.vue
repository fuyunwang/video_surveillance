<template>
  <div style="padding: 20px">
    <el-row :gutter="20">
      <el-col :span="6" :xs="24">
        <el-card style="margin-bottom:20px;">
          <div slot="header" class="clearfix">
            <span>About me</span>
          </div>

          <div class="user-profile">
            <div class="box-center">
              <goods-thumb :image="user.avatar" :height="'100px'" :width="'100px'" :hoverable="false">
                <!--                <div>Hello FuyunWang</div>-->
                <!--                {{ user.role }}-->
                <!--                <el-avatar :size="75" src="src/assets/avatar2.jpg"></el-avatar>-->
              </goods-thumb>
            </div>
            <div class="box-center">
              <div class="user-name text-center">{{ user.name }}</div>
              <div class="user-role text-center text-muted">{{ user.role | uppercaseFirst }}</div>
            </div>
          </div>

          <div class="user-bio">
            <div class="user-education user-bio-section">
              <div class="user-bio-section-header"><svg-icon icon-class="education" /><span>Education</span></div>
              <div class="user-bio-section-body">
                <div class="text-muted">
                  JS in Computer Science from the University of Technology
                </div>
              </div>
            </div>

            <div class="user-skills user-bio-section">
              <div class="user-bio-section-header"><svg-icon icon-class="skill" /><span>Skills</span></div>
              <div class="user-bio-section-body">
                <div class="progress-item">
                  <span>Vue</span>
                  <el-progress :percentage="70" />
                </div>
                <div class="progress-item">
                  <span>JavaScript</span>
                  <el-progress :percentage="18" />
                </div>
                <div class="progress-item">
                  <span>Css</span>
                  <el-progress :percentage="12" />
                </div>
                <div class="progress-item">
                  <span>ESLint</span>
                  <el-progress :percentage="100" status="success" />
                </div>
              </div>
            </div>
          </div>
        </el-card>
      </el-col>

      <el-col :span="18" :xs="24">
        <el-card>
          <el-tabs v-model="activeTab">
            <el-tab-pane label="Activity" name="activity">
              <activity />
            </el-tab-pane>
            <el-tab-pane label="Timeline" name="timeline">
              <timeline />
            </el-tab-pane>
            <el-tab-pane label="Account" name="account">
              <account :user="user" />
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import GoodsThumb from './thumb/index'
import Account from './content/Account'
import Activity from './content/Activity'
import Timeline from './content/Timeline'

export default {
  components: { GoodsThumb, Account, Activity, Timeline },
  props: {
    user: {
      type: Object,
      default: () => {
        return {
          name: '',
          email: '',
          avatar: 'http://qi2c9qbdt.hb-bkt.clouddn.com/avatar1.jpg',
          role: ''
        }
      }
    }
  },
  data() {
    return {
      activeTab: 'activity'
    }
  },
  computed: {
  },
  methods: {
    handleChangeCount() {

    },
    handleChangeCount1() {
    }
  }
}
</script>

<style lang="scss" scoped>
  .box-center {
    margin: 0 auto;
    display: table;
  }

  .text-muted {
    color: #777;
  }

  .user-profile {
    .user-name {
      font-weight: bold;
    }

    .box-center {
      padding-top: 10px;
    }

    .user-role {
      padding-top: 10px;
      font-weight: 400;
      font-size: 14px;
    }

    .box-social {
      padding-top: 30px;

      .el-table {
        border-top: 1px solid #dfe6ec;
      }
    }

    .user-follow {
      padding-top: 20px;
    }
  }

  .user-bio {
    margin-top: 20px;
    color: #606266;

    span {
      padding-left: 4px;
    }

    .user-bio-section {
      font-size: 14px;
      padding: 15px 0;

      .user-bio-section-header {
        border-bottom: 1px solid #dfe6ec;
        padding-bottom: 10px;
        margin-bottom: 10px;
        font-weight: bold;
      }
    }
  }
</style>
