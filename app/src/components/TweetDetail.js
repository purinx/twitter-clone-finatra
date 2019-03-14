// @flow

import * as React from 'react';

import type {TweetType, User} from './types';

type Props = {
  ...$Exact<TweetType>,
  user:User
}

type State = {

}

export default class TweetDetail extends React.Component<Props,State>{

}