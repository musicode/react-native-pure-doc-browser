# react-native-pure-doc-browser

This is a module which help you preview a document file, such as word, excel, ppt and pdf.

## Installation

```
npm i react-native-pure-doc-browser
react-native link react-native-pure-doc-browser
```

## Usage

```js
import DocBrowser from 'react-native-pure-doc-browser'

DocBrowser.open({
  path: 'local file path',
  // mimeType is android only
  mimeType: 'application/pdf'
})
.then(() => {
  // success
})
.catch(error => {
  let { code } = error

  // android
  // 1: has no activity for the intent

})
```