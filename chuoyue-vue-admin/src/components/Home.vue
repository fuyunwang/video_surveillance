<template>
  <el-container class="home-container">
    <!--头部-->
    <el-header>
      <div>
        <img src="../assets/avatar.jpg" alt="">
        <span>Vue Element Ui Admin</span>
      </div>
      <el-button type="primary" @click="logout">退出</el-button>
    </el-header>
    <!--页面主体-->
    <el-container>
      <el-aside :width="isCollapse ? '64px' : '200px'">
        <!--侧边栏-->
        <div class="toggle-button" @click="toggleCollapse">|||</div>
        <el-menu
          background-color="#001529"
          text-color="#fff"
          active-text-color="#409BFF" :collapse="isCollapse" :collapse-transition="false" unique-opened router :default-active="activePath">
          <!--一级菜单-->
          <el-submenu :index="item.id + ''" v-for="item in menuList" :key="item.id">
            <!--一级菜单的模板区域-->
            <template slot="title">
              <!--图标-->
              <i :class="'iconfont icon-tijikongjian'"></i>
              <!--文本-->
              <span>{{item.menuName}}</span>
            </template>

           <!-- <el-submenu :index="subItem.id + ''" v-for="subItem in item.children" :key="subItem.id" >
              <template slot="title">
                &lt;!&ndash;图标&ndash;&gt;
                <i class="el-icon-menu"></i>
                &lt;!&ndash;文本&ndash;&gt;
                <span>{{subItem.menuName}}</span>
              </template>
                <el-menu-item :index="'/' + subsubItem.menuPath" @click="saveNavState('/' + subsubItem.menuPath)" v-for="subsubItem in subItem.children" :key="subsubItem.id">
                  <i class="el-icon-menu"></i>
                  &lt;!&ndash;文本&ndash;&gt;
                  <span>{{subsubItem.menuName}}</span>
                </el-menu-item>
            </el-submenu>-->
            <el-menu-item :index="'/' + subItem.menuPath" v-for="subItem in item.children" :key="subItem.id" @click="saveNavState('/' + subItem.menuPath)">
              <template slot="title">
                <!--图标-->
                <i class="el-icon-menu"></i>
                <!--文本-->
                <span>{{subItem.menuName}}</span>
              </template>
            </el-menu-item>
          </el-submenu>

        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      menuList: [],
      iconsObj: {
        125: 'iconfont icon-user',
        103: 'iconfont icon-tijikongjian',
        101: 'iconfont icon-shangpin',
        102: 'iconfont icon-danju',
        145: 'iconfont icon-baobiao'
      },
      isCollapse: false,
      activePath: ''
    }
  },
  created() {
    this.getMenuList()
    this.activePath = window.sessionStorage.getItem('activePath')
  },
  methods: {
    logout() {
      window.sessionStorage.clear()
      this.$router.push('/login')
    },
    async getMenuList() {
      const token = window.sessionStorage.getItem('token')
      const { data: res } = await this.$http({
        method: 'post',
        url: 'menus/listall'
      })
      if (res.status !== 20000) { return this.$message.error(res.meta.msg) }
      this.menuList = res.data
      console.log(res)
    },
    toggleCollapse() {
      this.isCollapse = !this.isCollapse
    },
    saveNavState(activePath) {
      // 保存链接点击之后的激活状态
      window.sessionStorage.setItem('activePath', activePath)
      this.activePath = window.sessionStorage.getItem('activePath')
    }
  }
}
</script>

<style lang="less" scoped>
  .el-header {
    background-color: #001529;
    display: flex;
    justify-content: space-between;
    padding-left: 10px;
    align-items: center;
    color: white;
    font-size: 20px;
    > div {
      display: flex;
      align-items: center;    //纵向居中对齐
      span {
        margin-left: 15px;
      }

      img {
        width: 55px;
        height: 55px;
        border-radius: 50%;
      }
    }
  }

  .el-aside {
    background-color: #001529;
    .el-menu{
      border: none;
    }
  }

  .el-main {
    background-color: white;
  }

  .home-container {
    height: 100%;
  }

  .iconfont{
    margin-right: 10px;
  }

  .toggle-button{
    background-color: #4A5064;
    font-size: 10px;
    line-height: 24px;
    color: white;
    /*text-align: center;*/
    display: flex;
    justify-content: center;
    letter-spacing: 0.2em;
    cursor: pointer;
  }
</style>
