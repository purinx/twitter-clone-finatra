// @flow

import * as React from 'react';
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';
import { withRouter } from "react-router-dom";

import type { RouterHistory } from 'react-router-dom';
import type { User, TweetType } from '../types';

type Props = {
  user: User,
  tweets: Array<TweetType>,
}

type State = {
  NewTweetWindowIsActive:boolean,
}

class Timeline extends React.Component<Props, State>{

}
