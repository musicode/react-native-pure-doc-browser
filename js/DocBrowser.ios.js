
import { NativeModules } from 'react-native'

const { RNTDocBrowser } = NativeModules

export default {
  // options.path
  open(options) {
    return RNTDocBrowser.open(options)
  }
}
