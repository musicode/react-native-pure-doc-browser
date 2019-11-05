/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React from 'react';
import {
  SafeAreaView,
  Text,
  StatusBar,
} from 'react-native';

import DocBrowser from 'react-native-pure-doc-browser'

const App: () => React$Node = () => {
  return (
    <>
      <StatusBar barStyle="dark-content" />
      <SafeAreaView>
        <Text onPress={() => {
          DocBrowser.open({
            path: '/a.pdf',
            mimeType: 'application/pdf',
          })
          .then(data => {
            console.log(1, data)
          })
          .catch(err => {
            console.log(2, err)
          })
        }}>
          Open
        </Text>

      </SafeAreaView>
    </>
  );
};

export default App;
