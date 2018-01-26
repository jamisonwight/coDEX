<template>
  <div id="container" class="container">
    <MonacoEditor
          language="javascript"
          :code="code"
          :editorOptions="options"
          @mounted="onMounted"
          @codeChange="onCodeChange"
          >
    </MonacoEditor>
    <StatusBar />
  </div>
</template>

<script>
import MonacoEditor from 'vue-monaco-editor'
import StatusBar from './StatusBar'

export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your coDEX!',
      code: "function hello() {\n\talert('Hello world!');\n}",
      language: 'javascript',
      options: {
        selectOnLineNumbers: true,
        roundedSelection: false,
        readOnly: false,
        cursorStyle: 'line',
        automaticLayout: true,
        glyphMargin: true
      }
    }
  },
  components: {
    MonacoEditor,
    StatusBar
  },
  componentDidMount() {
    window.addEventListener("resize", this.updateDimensions.bind(this));
  },
  componentWillUnmount() {
    window.removeEventListener("resize", this.updateDimensions.bind(this));
  },
  methods: {
    onMounted(editor) {
      this.editor = editor;
    },
    onCodeChange(editor) {
      console.log(editor.getValue());
    },
    updateDimensions() {
      editor.layout();
    }
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
