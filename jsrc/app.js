// @flow

import * as React from 'react';
import * as ReactDOM from 'react-dom';
import Signup from './js/components/pages/Signup';

const appNode = document.getElementById("app");

appNode && ReactDOM.render(
  <Signup/>,
  appNode
);