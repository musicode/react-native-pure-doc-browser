
import { NativeModules } from 'react-native'

const { RNTDocBrowser } = NativeModules

export default {
  // options.path
  // options.mimeType
  open(options) {
    return RNTDocBrowser.open(options)
  }
}
