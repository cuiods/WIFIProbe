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


const HourNewOldChart = ({data}) => (
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
      <Area type="monotone" dataKey="newCustomer" stackId="1" stroke="#8884d8" fill="#8884d8" />
      <Area type="monotone" dataKey="oldCustomer" stackId="1" stroke="#82ca9d" fill="#82ca9d" />
    </AreaChart>
  </Container>
);



export default HourNewOldChart
