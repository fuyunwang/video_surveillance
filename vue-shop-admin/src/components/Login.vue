<template>
  <div class="login-container">
    <div class="login_box">
      <div class="avatar_box">
        <img src="../assets/avatar.jpg" alt="">
      </div>
      <!--登录表单区域-->
      <el-form ref="loginFormRef" label-width="0" :model="loginForm" :rules="loginFormRules" class="login_form">
        <el-form-item prop="username">
          <el-input v-model="loginForm.username" prefix-icon="iconfont icon-user"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="loginForm.password" prefix-icon="iconfont icon-3702mima" type="password"></el-input>
        </el-form-item>
        <el-form-item class="btns">
          <el-button type="primary" @click="login">登录</el-button>
          <el-button type="info" @click="resetFormLogin">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      loginFormRules: {
        username: [
          { required: true, message: '用户名不能为空', trigger: 'blur' },
          { min: 2, max: 10, message: '用户名长度不合法', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          { min: 6, max: 10, message: '密码长度不能小于六位', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    resetFormLogin () {
      this.$refs.loginFormRef.resetFields()
    },
    login () {
      this.$refs.loginFormRef.validate(async valid => {
        if (!valid) {
          return
        }
        const { data: res } = await this.$http.post('login', this.loginForm)
        if (res.meta.status !== 200) {
          return this.$message.error(res.meta.msg)
        }
        this.$message.success(res.data.username + '登陆成功')
        window.sessionStorage.setItem('token', res.data.token)
        await this.$router.push('/home')
      })
    }
  }

}
</script>

<style lang="less" scoped>
  .login-container {
    background-color: lightblue;
    height: 100%;
  }
  .login_box {
    width: 400px;
    height: 300px;
    background-color: #fff;
    border-radius: 3px;
    position: absolute;
    /*以下三行将盒子定位到正中*/
    left: 50%;
    top: 50%;
    transform: translate(-50%,-50%);

    .avatar_box {
      height: 130px;
      width: 130px;
      border: 1px solid #eee;
      border-radius: 50%;
      padding: 10px;
      box-shadow: 0 0 10px #eee;//加阴影
      //保证圆盒子在正中
      position: absolute;
      left: 50%;
      transform: translate(-50%,-50%);
      background-color: white;
      img {
        width: 100%;
        height: 100%;
        border-radius: 50%;
        background-color: #eee;
      }
    }
  }

  .login_form {
    position: absolute;
    bottom: 0;
    width: 100%;
    padding: 0 20px;
    box-sizing: border-box;
  }

  .btns {
    display: flex;
    justify-content: flex-end;
  }
</style>
