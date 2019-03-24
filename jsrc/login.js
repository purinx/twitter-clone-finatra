// @flow

import * as React from 'react';
import * as ReactDOM from 'react-dom';
import Login from './js/components/pages/Login';

const appNode = document.getElementById("login");

appNode && ReactDOM.render(
  <Login/>,
  appNode
);