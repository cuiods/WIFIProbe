/**
 * Created by yyy on 2017/5/17.
 * used the data from @customerFlowInfo to get the corresponding charts view
 */
import { color } from '../../utils';
import { Row, Col, Card } from 'antd';
import React, { PropTypes } from 'react';
import { connect } from 'dva';
import HourFlowChart from '../../components/charts/customerFlow/hourFlowChart';
import HourCompareChart from '../../components/charts/customerFlow/hourCompareChart';
import DetailBarChart from '../../components/charts/detailBarChart';
import RealTimeChart from '../../components/charts/customerFlow/realTimeChart';
import SelectorForm from '../../components/charts/selectorForm';
import DetailSelectorForm from '../../components/charts/detailSelectorForm';

function CustomerFlowPage({dispatch,customerFlowInfo}) {
  const {hourData, detailData,realTimeData, probeOptions} = customerFlowInfo;

  const selectorProps = {
    probeOptions,
    getHourData(fieldValue) {
      dispatch({
        type: 'customerFlowInfo/getFlow',
        payload: fieldValue,
      });
    }
  };

  const detailProps = {
    probeOptions,
    getDetailData(fieldValue) {
      dispatch({
        type: 'customerFlowInfo/getDetail',
        payload: fieldValue,
      })
    },
  };

  // let time = 0;
  // function refreshRealTimeData(){
  //   dispatch({
  //     type:"customerFlowInfo/getRealTimeFlow",
  //     payload:{}
  //   });
  //   console.log("execute:"+ (++time));
  // }


  return (
    <Row gutter={16}>
      <Card>
        <Col lg={24} md={24}>
          <Card title="请选择需要分析的时间范围：">
          <SelectorForm {...selectorProps}/>
          </Card>
        </Col>
        <Col lg={12} md={24} >
          <Card title="客流分析（跳出率、深访率和入店率）">
            <HourFlowChart data={hourData}/>
          </Card>
        </Col>
        <Col lg={12} md={24} >
          <Card title="客流数量分析">
            <HourCompareChart data={hourData}/>
          </Card>
        </Col>
      </Card>
      <Card title="实时客流量跟踪">
        <RealTimeChart data={realTimeData}/>
      </Card>

      <Card>
        <Col lg={24} md={24}>
          <Card title="请选择需要分析的具体时间">
            <DetailSelectorForm {...detailProps}/>
          </Card>
        </Col>
        <Col lg={24} md={24} >
          <Card title="详细客流分析图">
            <DetailBarChart data={detailData}/>
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
