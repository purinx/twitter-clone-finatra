// @flow

import * as React from 'react';
import * as Mui from '@material-ui/core';
import {like} from "../../api/tweetAPI";

const {IconButton, SvgIcon} = Mui;

type Props = {
  id: number,
  userId: number
};

export default function LikeButton(props: Props) {

  const {id, userId} = props;

  const onClick = () => {
    like(id, userId);
  };

  return (
    <IconButton className="icon-button like" onClick={onClick}>
      <SvgIcon>
        <path
          d="M38.723,12c-7.187,0-11.16,7.306-11.723,8.131C26.437,19.306,22.504,12,15.277,12C8.791,12,3.533,18.163,3.533,24.647 C3.533,39.964,21.891,55.907,27,56c5.109-0.093,23.467-16.036,23.467-31.353C50.467,18.163,45.209,12,38.723,12z"/>
      </SvgIcon>
    </IconButton>
  )
};
