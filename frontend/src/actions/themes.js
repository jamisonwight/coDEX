import MonacoEditor from 'vue-monaco-editor'
import store from '../stores/index'

export default function(editor) {
  editor.addAction({
    id: 'theme',
    label: 'Color Themes',
    keybindings: [
      monaco.KeyMod.chord(monaco.KeyMod.CtrlCmd | monaco.KeyCode.F2)
    ],
    precondition: null,
    keybindingContext: null,
    contextMenuGroupId: 'navigation',
    contextMenuOrder: 1.5,

    // Method that will be executed when the action is triggered.
    // @param editor The editor instance is passed in as a convinience
    run: function(ed, state) {
       return store.state.menu.theme = true;
    }
  })
}