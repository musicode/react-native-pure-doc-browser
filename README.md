# react-native-pure-doc-browser

This is a module which help you preview a document file, such as word, excel, ppt and pdf.

## Installation

```
npm i react-native-pure-doc-browser

// link below 0.60 version
react-native link react-native-pure-doc-browser
```

## Usage

```js
import docBrowser from 'react-native-pure-doc-browser'

docBrowser.open({
  path: 'local file path',
  // mimeType is android only
  mimeType: 'application/pdf'
})
.then(() => {
  // success
})
.catch(error => {
  let { code } = error

  // 1. file is not existed
  // 2: ios: viewController is nil
  // 2: android: has no activity for the intent

})
```