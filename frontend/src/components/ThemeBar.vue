<template>
  <div id="themeBar" v-if="state.menu.theme">
    <h4>Select Theme</h4>
    <div class="theme-wrap">
      <div class="theme-list" v-for="theme in themes" :key="theme.id">
        <span v-on:click="clickHandler(theme.shortName)">{{ theme.name }}</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      state: this.$store.state,
      themes: [
        {id: 1,name: 'Visual Studio Dark', shortName: 'vs-dark'},
        {id: 2, name: 'Visual Studio Light', shortName: 'vs-light'},
        {id: 3, name:'High Contrast Dark', shortName: 'hc-black'},
      ]
    }
  },
  methods: {
    clickHandler(theme) {
      this.state.theme = theme
      this.state.editor.updateOptions({
        theme: theme
      })
    }
  }
}
</script>

<style scoped>
  #themeBar {
    width: 30rem;
    height: auto;
    position: fixed;
    left: 50%;
    top: 40%;
    transform: translate(-50%, -60%);
    margin: auto; 
    background: #252525;
    color: white;
    z-index: 10;
    line-height: 1;
    padding: 1rem 0 0;
    text-align: center;
  }
  h4 {
    background: #252525;
  }
  .theme-wrap {
    width: 100%;
    height: 100%;
    background: #212121;
    padding: 2rem 0;
  }
  .theme-list {
    cursor: pointer;
    padding: .3rem
  }

</style>


