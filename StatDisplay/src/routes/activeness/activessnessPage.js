/**
 * Created by yyy on 2017/5/26.
 * used the data from @activenessInfo to get the corresponding charts view
 */

import { Row, Col, Card } from 'antd';
import React, { PropTypes } from 'react';
import { connect } from 'dva';
import SelectorForm from '../../components/charts/selectorForm';
import DetailSelectorForm from '../../components/charts/detailSelectorForm';
import HourActiveChart from '../../components/charts/activeness/hourActiveChart';
import DetailBarChart from '../../components/charts/detailBarChart';

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
    <Row>
      <Card>
        <Col lg={24} md={24}>
          <Card title="请选择需要分析的时间范围：">
            <SelectorForm {...selectorProps}/>
          </Card>
        </Col>
        <Col lg={24} md={24}>
          <Card title="活跃度分析表">
            <HourActiveChart data={hourData}/>
          </Card>
        </Col>
      </Card>

      <Card>
        <Col lg={24} md={24}>
          <Card title="请选择需要分析的具体时间：">
            <DetailSelectorForm {...detailProps}/>
          </Card>
        </Col>
        <Col lg={24} md={24}>
          <Card title="特定时间活跃度分析图">
            <DetailBarChart data={detailData} />
          </Card>
        </Col>
      </Card>

    </Row>
  )
}

ActivenessPage.prototypes = {
  activenessInfo: PropTypes.object
};
export default connect( ({activenessInfo}) => ({activenessInfo}) )(ActivenessPage)

