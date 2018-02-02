<template>
  <div id="statusBar">
    <div class="languageHeader" v-on:click="clickHandler">{{ state.currentLanguage | capitalize }}</div>
    <div class="languageWrapper" v-click-outside="outClickHandler"  v-if="languageClicked">
      <h4>Select Language</h4>
      <div class="languageSelect">
          <div
              class="languages"
              :value="language.syntax"
              v-for="language in state.languages" 
              v-on:click="setLanguage(language.syntax)"
              v-bind:key="language.syntax">
              <span>{{ language.syntax | capitalize }}</span>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
  import Scrollbar from 'smooth-scrollbar'
  import ClickOutside from 'vue-click-outside'

  export default {
    props: ['editor'],
    data () {
      return {
        state: this.$store.state,
        languageClicked: false,
      }
    },
    filters: {
      capitalize: function (value) {
        if (!value) return ''
        value = value.toString()
        return value.charAt(0).toUpperCase() + value.slice(1)
      }
    },
    methods: {
      clickHandler() {
        return new Promise((resolve, reject) => {
          this.languageClicked = true
          setTimeout(() => {
            resolve()
          }, .5)
        })
        .then(() => {
          Scrollbar.init(document.querySelector('.languageSelect'))
        })
        .catch((error) => {
          console.log(error)
        })
      },
      outClickHandler() {
        if (this.languageClicked) {
          this.languageClicked = false
        }
      },
      setLanguage(lang) {
        console.log(lang)
        this.state.currentLanguage = lang
        var model = this.state.editor.getModel()
        monaco.editor.setModelLanguage(model, lang)
      }
    },
    mounted() {
    },

    directives: {
      ClickOutside
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  #statusBar {
    width: 100%;
    position: fixed;
    bottom: 0;
    background: #1976D2;
    color: #ffffff;
    padding: .8rem 0;
    -webkit-transition: all .2s;
    -moz-transition: all .2s;
    -ms-transition: all .2s;
    -o-transition: all .2s;
    transition: all .2s;
    z-index: 10;
  }
  .languageHeader {
    position: absolute;
    right: 2rem;
    bottom: .25rem;
    cursor: pointer;
    width: 15rem;
    text-align: center;
  }
  .languageWrapper {
    background: #252525;
    position: absolute;
    right: 1rem;
    bottom: 1.7rem;
    cursor: pointer;
    z-index: 10;
  }
  .languageWrapper h4 {
    text-align: center;
    background: #252525;
  }
  .languageSelect {
    width: 15rem;
    height: 30rem;
    padding: 1rem;
    font-size: .8rem;
    overflow: auto;
    text-align: center;
    line-height: 2;
    z-index: 9;
    background: #212121;
  }

</style>
