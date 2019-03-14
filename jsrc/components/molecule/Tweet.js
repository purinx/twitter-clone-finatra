//@flow

import * as React from 'react';
import {withRouter} from 'react-router-dom';
import ListItem from '@material-ui/core/ListItem/ListItem';
import LikeButton from '../atom/LikeButton';
import RetweetButton from '../atom/RetweetButton';
import ReplyButton from '../atom/ReplyButton';
import Avatar from '@material-ui/core/Avatar';
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';

import type {RouterHistory} from 'react-router-dom';
import type {TweetType} from '../types';
import type {User} from "../types";

type Props = {
  ...$Exact<TweetType>,
  user: User,
  history: RouterHistory,
}

type State = {
  replyWindowIsActive: boolean,
  newTweetWindowIsActive: boolean,
};

class Tweet extends React.Component<Props, State> {

  constructor(props: Props) {
    super(props);

    this.state = {
      replyWindowIsActive: false,
      newTweetWindowIsActive: false,
    };
  }

  tweetDetail = (id: number) => {
    this.props.history.push(`/tweet/${id}`);
  };

  replyWindow = () => {
    this.setState({replyWindowIsActive: true});
  };

  render() {

    const {id, userIcon, username, userCalled, text, user} = this.props;

    return (
      <ListItem button onClick={this.tweetDetail.bind(id)} className="tweet">
        <Grid container>
          <Grid item>
            <Avatar alt="Remy Sharp" src={userIcon}/>
          </Grid>
          <Grid item>
            <Typography variant="subtitle1">{username}</Typography>
            <Typography variant="h6">{userCalled}</Typography>
          </Grid>
        </Grid>
        <Grid>
          <Typography variant="h6">{text}</Typography>
        </Grid>
        <Grid container>
          <Grid item><ReplyButton onClick={this.replyWindow}/></Grid>
          <Grid item><RetweetButton id={id} userId={user.id}/></Grid>
          <Grid item><LikeButton id={id} userId={user.id}/></Grid>
        </Grid>
      </ListItem>
    );
  }
}

export default withRouter(Tweet);
