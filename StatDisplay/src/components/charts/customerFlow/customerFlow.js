/**
 * Created by yyy on 2017/5/17.
 * the customerFlow page component
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
} from 'recharts';

const staticHourData = [
  {
    name: 'Page A',
    uv: 4000,
    pv: 2400,
    amt: 2400,
  }, {
    name: 'Page B',
    uv: 3000,
    pv: 1398,
    amt: 2210,
  }, {
    name: 'Page C',
    uv: 2000,
    pv: 9800,
    amt: 2290,
  }, {
    name: 'Page D',
    uv: 2780,
    pv: 3908,
    amt: 2000,
  }, {
    name: 'Page E',
    uv: 1890,
    pv: 4800,
    amt: 2181,
  }, {
    name: 'Page F',
    uv: 2390,
    pv: 3800,
    amt: 2500,
  }, {
    name: 'Page G',
    uv: 3490,
    pv: 4300,
    amt: 2100,
  },
];

const HourFlowChart = ({data}) => (
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
      <Area type="monotone" dataKey="jumpRate" stackId="1" stroke="#8884d8" fill="#8884d8" />
      <Area type="monotone" dataKey="deepVisit" stackId="1" stroke="#82ca9d" fill="#82ca9d" />
      <Area type="monotone" dataKey="inStoreRate" stackId="1" stroke="#ffc658" fill="#ffc658" />
    </AreaChart>
  </Container>
);

export default HourFlowChart
