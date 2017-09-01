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
      <Line type="monotone" dataKey="numOfHighActive" stroke="#8884d8"  strokeWidth={2}/>
      <Line type="monotone" dataKey="numOfHighActivePre" stroke="#8884d8"  strokeWidth={2} strokeDasharray="5 5"/>
      <Line type="monotone" dataKey="numOfMedianActive" stroke="#82ca9d" strokeWidth={2}/>
      <Line type="monotone" dataKey="numOfMedianActivePre" stroke="#82ca9d" strokeWidth={2} strokeDasharray="5 5"/>
      <Line type="monotone" dataKey="numOfLowActive" stroke="#ffc658"  strokeWidth={2}/>
      <Line type="monotone" dataKey="numOfLowActivePre" stroke="#ffc658"  strokeWidth={2} strokeDasharray="5 5"/>
      <Line type="monotone" dataKey="numOfSleepActive" stroke="#BA55D3" strokeWidth={2} />
      <Line type="monotone" dataKey="numOfSleepActivePre" stroke="#BA55D3" strokeWidth={2} strokeDasharray="5 5"/>
    </LineChart>
  </Container>
);



export default HourActiveChart

