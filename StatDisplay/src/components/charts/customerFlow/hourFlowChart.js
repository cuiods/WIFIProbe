/**
 * Created by yyy on 2017/5/17.
 * the customerFlow page component
 */

import React, { PropTypes } from 'react';
import Container from '../container';
import {
  AreaChart,
  ComposedChart,
  LineChart,
  Area,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
} from 'recharts';


const HourFlowChart = ({data}) => (
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
      <Line type="monotone" dataKey="jumpRate" stroke="#8884d8" strokeWidth={2}/>
      <Line type="monotone" dataKey="jumpRatePre" stroke="#8884d8" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="deepVisit" stroke="#82ca9d"  strokeWidth={2}/>
      <Line type="monotone" dataKey="deepVisitPre" stroke="#82ca9d" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="inStoreRate" stroke="#ffc658" strokeWidth={2}/>
      <Line type="monotone" dataKey="inStoreRatePre" stroke="#ffc658" strokeDasharray="5 5" strokeWidth={2}/>
    </LineChart>
  </Container>
);



export default HourFlowChart

