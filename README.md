# react-native-pure-doc-browser

> This repo is deprecated, please visit the new [fs](https://github.com/react-native-hero/fs) repo.

This is a module which help you preview a document file, such as word, excel, ppt and pdf.

## Installation

```
npm i react-native-pure-doc-browser

// link below 0.60 version
react-native link react-native-pure-doc-browser
```

## Usage

```js
import {
  CODE,
  open,
} from 'react-native-pure-doc-browser'

open({
  path: 'local file path',
  // mimeType is android only
  mimeType: 'application/pdf'
})
.then(() => {
  // success
})
.catch(error => {
  if (error.code === CODE.FILE_NOT_FOUND) {

  }
  else if (error.code === CODE.APP_NOT_FOUND) {

  }
})
```