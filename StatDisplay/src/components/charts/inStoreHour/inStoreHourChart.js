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


const InStoreHourChart = ({data}) => (
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
      <Area type="monotone" dataKey="data0" stackId="1" stroke="#8884d8" fill="#8884d8" />
      <Area type="monotone" dataKey="data1" stackId="1" stroke="#82ca9d" fill="#82ca9d" />
      <Area type="monotone" dataKey="data2" stackId="1" stroke="#ffc658" fill="#ffc658" />
      <Area type="monotone" dataKey="data3" stackId="1" stroke="#BA55D3" fill="#BA55D3" />
      <Area type="monotone" dataKey="data4" stackId="1" stroke="#8884d8" fill="#8884d8" />
      <Area type="monotone" dataKey="data5" stackId="1" stroke="#82ca9d" fill="#82ca9d" />
      <Area type="monotone" dataKey="data6" stackId="1" stroke="#ffc658" fill="#ffc658" />
      <Area type="monotone" dataKey="data7" stackId="1" stroke="#BA55D3" fill="#BA55D3" />
      <Area type="monotone" dataKey="data8" stackId="1" stroke="#8884d8" fill="#8884d8" />
      <Area type="monotone" dataKey="data9" stackId="1" stroke="#82ca9d" fill="#82ca9d" />
    </AreaChart>
  </Container>
);



export default InStoreHourChart
