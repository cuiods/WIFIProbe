/**
 * Created by yyy on 2017/5/27.
 */

import React, { PropTypes } from 'react';
import Container from '../container';
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
} from 'recharts';


const HourActiveChart = ({data}) => (
  <Container>
    <LineChart data = {data} margin={{
      top: 10,
      right: 30,
      left: 0,
      bottom: 0,
    }}>
      <XAxis dataKey="hour" />
      <YAxis />
      <CartesianGrid strokeDasharray="3 3"/>
      <Tooltip />
      <Legend />
      <Line type="monotone" dataKey="numOfHighActive" name="高活跃度" stroke="#8884d8"  strokeWidth={2}/>
      <Line type="monotone" dataKey="numOfHighActivePre" name="高活跃度预测" stroke="#8884d8"  strokeWidth={2} strokeDasharray="5 5"/>
      <Line type="monotone" dataKey="numOfMedianActive" name="中活跃度" stroke="#82ca9d" strokeWidth={2}/>
      <Line type="monotone" dataKey="numOfMedianActivePre" name="中活跃度预测" stroke="#82ca9d" strokeWidth={2} strokeDasharray="5 5"/>
      <Line type="monotone" dataKey="numOfLowActive" name="低活跃度" stroke="#ffc658"  strokeWidth={2}/>
      <Line type="monotone" dataKey="numOfLowActivePre" name="低活跃度预测" stroke="#ffc658"  strokeWidth={2} strokeDasharray="5 5"/>
      <Line type="monotone" dataKey="numOfSleepActive" name="睡眠活跃度" stroke="#BA55D3" strokeWidth={2} />
      <Line type="monotone" dataKey="numOfSleepActivePre" name="睡眠活跃度预测" stroke="#BA55D3" strokeWidth={2} strokeDasharray="5 5"/>
    </LineChart>
  </Container>
);



export default HourActiveChart

