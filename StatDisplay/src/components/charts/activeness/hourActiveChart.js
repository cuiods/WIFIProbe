/**
 * Created by yyy on 2017/5/27.
 */

import React, { PropTypes } from 'react';
import Container from '../container';
import {
  AreaChart,
  Area,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
} from 'recharts';


const HourActiveChart = ({data}) => (
  <Container>
    <AreaChart data = {data} margin={{
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
      <Area type="monotone" dataKey="numOfHighActive" stackId="1" stroke="#8884d8" fill="#8884d8" />
      <Area type="monotone" dataKey="numOfMedianActive" stackId="1" stroke="#82ca9d" fill="#82ca9d" />
      <Area type="monotone" dataKey="numOfLowActive" stackId="1" stroke="#ffc658" fill="#ffc658" />
      <Area type="monotone" dataKey="numOfSleepActive" stackId="1" stroke="#BA55D3" fill="#BA55D3" />
    </AreaChart>
  </Container>
);



export default HourActiveChart

