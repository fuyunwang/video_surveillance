<template>
  <div>
    <svg-icon icon-class="doc" @click="click" />
  </div>
</template>

<script>
export default {
  name: 'Doc',
  methods: {
    click() {
      window.open('https://github.com/fuyunwang/Chuoyue-Element-Admin', '_blank')
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
