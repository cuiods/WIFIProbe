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


const HourVisitCircleChart = ({data}) => (
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
      <Area type="monotone" dataKey="data0" stackId="1" stroke="#8884d8" fill="#8884d8" name="0-5min"/>
      <Area type="monotone" dataKey="data1" stackId="1" stroke="#82ca9d" fill="#82ca9d" name="5-30min"/>
      <Area type="monotone" dataKey="data2" stackId="1" stroke="#ffc658" fill="#ffc658" name="0.5-1h"/>
      <Area type="monotone" dataKey="data3" stackId="1" stroke="#BA55D3" fill="#BA55D3" name="1-6h"/>
      <Area type="monotone" dataKey="data4" stackId="1" stroke="#8884d8" fill="#8884d8" name="6-24h"/>
      <Area type="monotone" dataKey="data5" stackId="1" stroke="#82ca9d" fill="#82ca9d" name="1-3day"/>
      <Area type="monotone" dataKey="data6" stackId="1" stroke="#ffc658" fill="#ffc658" name="3-7day"/>
      <Area type="monotone" dataKey="data7" stackId="1" stroke="#BA55D3" fill="#BA55D3" name="7-30day"/>
      <Area type="monotone" dataKey="data8" stackId="1" stroke="#8884d8" fill="#8884d8" name="30-180day"/>
      <Area type="monotone" dataKey="data9" stackId="1" stroke="#82ca9d" fill="#82ca9d" name=">180day"/>
    </AreaChart>
  </Container>
);



export default HourVisitCircleChart
