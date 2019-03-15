// @flow

import * as React from 'react';
import { Link } from 'react-router-dom';

export default function HomeButton(props) {

  return (
    <Link to="/home">
      <img src="http://codeitdown.com/media/Home_Icon.svg"
           alt="home"
           className="home-btn"/>
    </Link>
  );
}