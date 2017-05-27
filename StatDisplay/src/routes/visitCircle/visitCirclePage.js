/**
 * Created by yyy on 2017/5/27.
 */
import { Row, Col, Card } from 'antd';
import React, { PropTypes } from 'react';
import { connect } from 'dva';
import SelectorForm from '../../components/charts/selectorForm';
import DetailSelectorForm from '../../components/charts/detailSelectorForm';
import HourVisitCircleChart from '../../components/charts/visitCircle/hourVisitCircleChart';
import DetailBarChart from '../../components/charts/detailBarChart';

function VisitCirclePage({dispatch,visitCircleInfo}) {
  const {hourData, detailData, probeOptions} = visitCircleInfo;

  const selectorProps = {
    probeOptions,
    getHourData(fieldValue) {
      dispatch({
        type: 'visitCircleInfo/getHourData',
        payload: fieldValue,
      });
    }
  }

  const detailProps = {
    probeOptions,
    getDetailData(fieldValue) {
      dispatch({
        type: 'visitCircleInfo/getDetail',
        payload: fieldValue,
      })
    },
  }

  return (
    <Row>
      <Card>
        <Col lg={24} md={24}>
          <Card title="select to get expected data">
            <SelectorForm {...selectorProps}/>
          </Card>
        </Col>
        <Col lg={24} md={24}>
          <Card title="Hour Visit Circle chart">
            <HourVisitCircleChart data={hourData}/>
          </Card>
        </Col>
      </Card>

      <Card>
        <Col lg={24} md={24}>
          <Card title="select time to get detail">
            <DetailSelectorForm {...detailProps}/>
          </Card>
        </Col>
        <Col lg={24} md={24}>
          <Card title="detail Visit Circle chart">
            <DetailBarChart data={detailData}/>
          </Card>
        </Col>
      </Card>
    </Row>
  )
}

VisitCirclePage.prototypes = {
  visitCircleInfo: PropTypes.object
};
export default connect( ({visitCircleInfo}) => ({visitCircleInfo}) )(VisitCirclePage)
