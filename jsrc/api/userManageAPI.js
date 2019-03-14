// @flow

import * as React from 'react';
import {withRouter} from "react-router-dom";
import List from '@material-ui/core/List';
import type {RouterHistory} from 'react-router-dom';
import type {TweetType, User} from "../components/types";

type Props = {
  tweets: Array<TweetType>,
  user: User,
  history: RouterHistory,
}

type State = {
  newTweetWindowIsActive: boolean,
}

class

