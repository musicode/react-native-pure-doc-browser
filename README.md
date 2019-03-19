# react-native-pure-doc-browser

This is a module which help you preview a document file, such as word, excel, ppt and pdf.

## Installation

```
npm i react-native-pure-doc-browser
react-native link react-native-pure-doc-browser
```

## Setup

### iOS

After `react-native link`, nothing you need to do.

### Android

Make sure there is a [ContentProvider](https://developer.android.com/reference/android/support/v4/content/FileProvider.html) in your application, and the `authorities` is `${your package id}.provider`.

If you are using [rn-fetch-blob](https://github.com/joltup/rn-fetch-blob), nothing you need to do, because `rn-fetch-blob` has a suitable `ContentProvider`.

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