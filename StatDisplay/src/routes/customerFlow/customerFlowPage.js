/**
 * Created by yyy on 2017/5/17.
 * used the data from @customerFlowInfo to get the corresponding charts view
 */
import { color } from '../../utils';
import { Row, Col, Card } from 'antd';
import React, { PropTypes } from 'react';
import { connect } from 'dva';
import HourFlowChart from '../../components/charts/customerFlow/customerFlow';
import SelectorForm from '../../components/charts/customerFlow/selectorForm';

function CustomerFlowPage({dispatch,customerFlowInfo}) {
  const {hourData, detailData, probeOptions} = customerFlowInfo;

  const selectorProps = {
    probeOptions,
    getFlowData(fieldValue) {
      dispatch({
        type: 'customerFlowInfo/updateFlow',
        payload: fieldValue,
      });

    }
  }

  return (
    <Row gutter={16}>
      <Card>
        <Col lg={24} md={24}>
          <Card title="select to get expected data">
          <SelectorForm {...selectorProps}/>
          </Card>
        </Col>
        <Col lg={12} md={24} >
          <Card title="Customer Hour Flow">
            <HourFlowChart data={hourData}/>
          </Card>
        </Col>
      </Card>
    </Row>
  )
}

CustomerFlowPage.prototypes = {
  customerFlowInfo: PropTypes.object
};
export default connect( ({customerFlowInfo}) => ({customerFlowInfo}) )(CustomerFlowPage)
