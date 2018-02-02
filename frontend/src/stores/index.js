import Vue from 'vue/dist/vue.js'
import Vuex from 'vuex'
import languages from '../utilities/languages'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    languages: languages,
    currentLanguage: 'javascript',
    editor: '',
    theme: 'vs-dark',
    menu: {
      theme: false
    }
  }
})