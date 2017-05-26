/**
 * Created by yyy on 2017/5/26.
 */

import { Row, Col, Card } from 'antd';
import React, { PropTypes } from 'react';
import { connect } from 'dva';

function NewOldPage({dispatch,activenessInfo}) {

  return (
    <Row>

    </Row>
  )
}

NewOldPage.prototypes = {
  activenessInfo: PropTypes.object
};
export default connect( ({activenessInfo}) => ({activenessInfo}) )(NewOldPage)
