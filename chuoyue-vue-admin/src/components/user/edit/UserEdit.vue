<template>
  <div class="form-container" >
    <h3>{{$store.state.count}}</h3>
    <el-button @click="handleCountChange">count++</el-button>
    <el-row :gutter="20" style="margin: 0 auto;">
      <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" >
        <el-form
          ref="ruleForm"
          :model="ruleForm"
          :rules="rules"
          label-width="100px"
          class="demo-ruleForm" >
          <el-form-item label="活动名称" prop="name">
            <el-input style="width: 300px" v-model="ruleForm.name"></el-input>
          </el-form-item>
          <el-form-item label="活动区域" prop="region">
            <el-select v-model="ruleForm.region" placeholder="请选择活动区域">
              <el-option label="区域一" value="shanghai"></el-option>
              <el-option label="区域二" value="beijing"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="即时配送" prop="delivery">
            <el-switch v-model="ruleForm.delivery"></el-switch>
          </el-form-item>
          <el-form-item label="活动性质" prop="type">
            <el-checkbox-group v-model="ruleForm.type">
              <el-checkbox label="美食/餐厅线上活动" name="type"></el-checkbox>
              <el-checkbox label="地推活动" name="type"></el-checkbox>
              <el-checkbox label="线下主题活动" name="type"></el-checkbox>
              <el-checkbox label="单纯品牌曝光" name="type"></el-checkbox>
            </el-checkbox-group>
          </el-form-item>
          <el-form-item label="特殊资源" prop="resource">
            <el-radio-group v-model="ruleForm.resource">
              <el-radio label="线上品牌商赞助"></el-radio>
              <el-radio label="线下场地免费"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item style="width: 600px" label="活动形式" prop="desc">
            <el-input v-model="ruleForm.desc" type="textarea"></el-input>
          </el-form-item>
          <el-form-item label="Markdown" prop="desc">
            <el-row :gutter="5">
              <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
                <vab-markdown-editor
                  ref="mde"
                  v-model="value"
                  @show-html="handleShowHtml"
                ></vab-markdown-editor>

              </el-col>
              <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
                <el-card shadow="hover">
                  <div slot="header">
                    <span>markdown转换html实时演示区域</span>
                  </div>
                  <div v-html="html"></div>
                </el-card>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">
              立即创建
            </el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import VabMarkdownEditor from '@/plugins/vabMarkdownEditor'

export default {
  name: 'UserEdit',
  components: {
    VabMarkdownEditor
  },
  data() {
    return {
      ruleForm: {
        name: '',
        region: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      value: '',
      html: '',
      rules: {
        name: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          {
            min: 3,
            max: 5,
            message: '长度在 3 到 5 个字符',
            trigger: 'blur'
          }
        ],
        region: [
          { required: true, message: '请选择活动区域', trigger: 'change' }
        ],
        type: [
          {
            type: 'array',
            required: true,
            message: '请至少选择一个活动性质',
            trigger: 'change'
          }
        ],
        resource: [
          { required: true, message: '请选择活动资源', trigger: 'change' }
        ],
        desc: [
          { required: true, message: '请填写活动形式', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!')
        } else {
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
      this.value = ''
      this.html = ''
    },
    handleShowHtml(html) {
      this.html = html
    },
    handleCountChange() {
      this.$store.commit('addN', 15)
    }
  }
}
</script>

<style lang="less" scoped>

</style>
