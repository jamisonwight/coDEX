<template>
  <div id="container" class="container">
    <MonacoEditor
          :language="state.currentLanguage"
          :code="code"
          :editorOptions="options"
          :theme="state.theme"
          @mounted="onMounted"
          @codeChange="onCodeChange"
          >
    </MonacoEditor>
    <StatusBar />
    <ThemeBar />
  </div>
</template>

<script>
import MonacoEditor from 'vue-monaco-editor'
import StatusBar from './StatusBar'
import ThemeBar from './ThemeBar'
import actionThemes from '../actions/themes'

export default {
  name: 'HelloWorld',
  data () {
    return {
      state: this.$store.state,
      editor: '',
      code: "function hello() {\n\talert('Hello world!');\n}",
      language: 'javascript',
      options: {
        selectOnLineNumbers: true,
        roundedSelection: false,
        readOnly: false,
        cursorStyle: 'line',
        automaticLayout: true,
        glyphMargin: true
      },
    }
  },
  components: {
    MonacoEditor,
    StatusBar,
    ThemeBar
  },
  componentDidMount() {
    window.addEventListener("resize", this.updateDimensions.bind(this));
  },
  componentWillUnmount() {
    window.removeEventListener("resize", this.updateDimensions.bind(this));
  },
  methods: {
    onMounted(editor) {
      this.state.editor = editor
      actionThemes(editor)
    },
    onCodeChange(editor) {
      console.log(this.state.editor.getValue());
      //MonacoEditor.editor.setModelLanguage('', 'bat')
    },
    updateDimensions() {
      this.state.editor.layout();
    }
  },
  mounted() {
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  #container {
    width: 100vw;
    height: 100vh;
  }
</style>
