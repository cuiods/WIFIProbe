/**
 * Created by yyy on 2017/5/25.
 * hour detail customerFlow chart
 */
import React, { PropTypes } from 'react';
import Container from '../container';
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
} from 'recharts';

const HourDetailChart = ({data}) => (
  <Container>
    <BarChart data = {data} margin={{
      top: 10,
      right: 30,
      left: 0,
      bottom: 0,
    }}>
      <XAxis dataKey="x"/>
      <YAxis/>
      <CartesianGrid strokeDasharray="3 3"/>
      <Tooltip />
      <Bar dataKey="y" fill="#8884d8" />
    </BarChart>
  </Container>
)

export default HourDetailChart
