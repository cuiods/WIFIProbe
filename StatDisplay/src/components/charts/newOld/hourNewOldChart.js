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


const HourNewOldChart = ({data}) => (
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
      <Line type="monotone" dataKey="newCustomer" name="新顾客数" stroke="#8884d8"  strokeWidth={2}/>
      <Line type="monotone" dataKey="newCustomerPre" name="新顾客数预测" stroke="#8884d8" strokeDasharray="5 5" strokeWidth={2} />
      <Line type="monotone" dataKey="oldCustomer" name="老顾客数" stroke="#82ca9d"  strokeWidth={2}/>
      <Line type="monotone" dataKey="oldCustomerPre" name="老顾客数预测"  stroke="#82ca9d" strokeDasharray="5 5" strokeWidth={2} />
    </LineChart>
  </Container>
);



export default HourNewOldChart
