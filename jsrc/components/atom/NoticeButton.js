// @flow

import * as React from 'react';
import {Link} from 'react-router-dom';
import SvgIcon from '@material-ui/core/SvgIcon/SvgIcon';

export default function NoticeButton(props){

  return(
    <Link to="/notice">
      <img src="http://codeitdown.com/media/Home_Icon.svg" alt="home"
      className="hom"/>
    </Link>
  )
}