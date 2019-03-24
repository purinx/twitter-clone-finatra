// @flow

import * as React from 'react';
import * as ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import Timeline from './js/components/pages/timeline';

const appNode = document.getElementById("app");

appNode && ReactDOM.render(
  <Router>
    <div>
      <nav>
        <ul>
          <li>
            <Link to="/timeline/">Timeline</Link>
          </li>
        </ul>
      </nav>

      <Route path="/timeline" component={Timeline} />

    </div>
  </Router>,
  appNode
);