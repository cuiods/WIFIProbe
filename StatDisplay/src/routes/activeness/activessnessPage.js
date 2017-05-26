/**
 * Created by yyy on 2017/5/26.
 * used the data from @activenessInfo to get the corresponding charts view
 */

import { Row, Col, Card } from 'antd';
import React, { PropTypes } from 'react';
import { connect } from 'dva';

function ActivenessPage({dispatch,activenessInfo}) {
  const {hourData, detailData, probeOptions} = activenessInfo;

  const selectorProps = {
    probeOptions,
    getHourData(fieldValue) {
      dispatch({
        type: 'activenessInfo/getHourData',
        payload: fieldValue,
      });
    }
  }

  const detailProps = {
    probeOptions,
    getDetailData(fieldValue) {
      dispatch({
        type: 'activenessInfo/getDetail',
        payload: fieldValue,
      })
    },
  }

  return (
    <Row></Row>
  )
}

ActivenessPage.prototypes = {
  activenessInfo: PropTypes.object
};
export default connect( ({activenessInfo}) => ({activenessInfo}) )(ActivenessPage)

