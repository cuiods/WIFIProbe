/**
 * Created by yyy on 2017/5/26.
 */

import { Row, Col, Card } from 'antd';
import React, { PropTypes } from 'react';
import { connect } from 'dva';
import SelectorForm from '../../components/charts/selectorForm';
import DetailSelectorForm from '../../components/charts/detailSelectorForm';
import HourNewOldChart from '../../components/charts/newOld/hourNewOldChart';
import DetailPieChart from '../../components/charts/detailPieChart';
import DetailBarChart from '../../components/charts/detailBarChart';

function NewOldPage({dispatch,newOldInfo}) {
  const {hourData, detailData, probeOptions} = newOldInfo;

  const selectorProps = {
    probeOptions,
    getHourData(fieldValue) {
      dispatch({
        type: 'newOldInfo/getHourData',
        payload: fieldValue,
      });
    }
  }

  const detailProps = {
    probeOptions,
    getDetailData(fieldValue) {
      dispatch({
        type: 'newOldInfo/getDetail',
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
          <Card title="新老顾客统计图">
            <HourNewOldChart data={hourData} />
          </Card>
        </Col>
      </Card>

      <Card>
        <Col lg={24} md={24}>
          <Card title="请选择需要分析的具体时间">
            <DetailSelectorForm {...detailProps}/>
          </Card>
        </Col>
        <Col lg={24} md={24}>
          <Card title="具体时间新老顾客统计图">
            <DetailBarChart data={detailData}/>
          </Card>
        </Col>
      </Card>

    </Row>
  )
}

NewOldPage.prototypes = {
  newOldInfo: PropTypes.object
};
export default connect( ({newOldInfo}) => ({newOldInfo}) )(NewOldPage)
