
import { NativeModules } from 'react-native'

const { RNTDocBrowser } = NativeModules

export const CODE = Platform.select({
  ios: {
    FILE_NOT_FOUND: RNTDocBrowser.ERROR_CODE_FILE_NOT_FOUND,
  },
  android: {
    FILE_NOT_FOUND: RNTDocBrowser.ERROR_CODE_FILE_NOT_FOUND,
    APP_NOT_FOUND: RNTDocBrowser.ERROR_CODE_APP_NOT_FOUND,
  }
})

export function open(options) {
  return RNTDocBrowser.open(options)
}