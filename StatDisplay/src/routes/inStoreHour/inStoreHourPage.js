/**
 * Created by yyy on 2017/5/27.
 */
import { Row, Col, Card } from 'antd';
import React, { PropTypes } from 'react';
import { connect } from 'dva';
import SelectorForm from '../../components/charts/selectorForm';
import DetailSelectorForm from '../../components/charts/detailSelectorForm';
import InStoreHourChart from '../../components/charts/inStoreHour/inStoreHourChart';
import DetailBarChart from '../../components/charts/detailBarChart';

function InStoreHourPage({dispatch,inStoreHourInfo}) {
  const {hourData, detailData, probeOptions} = inStoreHourInfo;

  const selectorProps = {
    probeOptions,
    getHourData(fieldValue) {
      dispatch({
        type: 'inStoreHourInfo/getHourData',
        payload: fieldValue,
      });
    }
  }

  const detailProps = {
    probeOptions,
    getDetailData(fieldValue) {
      dispatch({
        type: 'inStoreHourInfo/getDetail',
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
          <Card title="Hour InStoreHour chart">
            <InStoreHourChart data={hourData}/>
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
          <Card title="detail InStoreHour chart">
            <DetailBarChart data={detailData}/>
          </Card>
        </Col>
      </Card>

    </Row>
  )
}

InStoreHourPage.prototypes = {
  inStoreHourInfo: PropTypes.object
};
export default connect( ({inStoreHourInfo}) => ({inStoreHourInfo}) )(InStoreHourPage)
