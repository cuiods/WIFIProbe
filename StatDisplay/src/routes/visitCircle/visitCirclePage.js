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
          <Card title="请选择需要分析的时间范围">
            <SelectorForm {...selectorProps}/>
          </Card>
        </Col>
        <Col lg={24} md={24}>
          <Card title="来访周期统计图">
            <HourVisitCircleChart data={hourData}/>
          </Card>
        </Col>
      </Card>

      <Card>
        <Col lg={24} md={24}>
          <Card title="请选择需要查看的具体时间">
            <DetailSelectorForm {...detailProps}/>
          </Card>
        </Col>
        <Col lg={24} md={24}>
          <Card title="具体时间来访周期统计图">
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
