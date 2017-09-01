/**
 * Created by yyy on 2017/5/25.
 * compare flow chart
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

const HourCompareChart = ({data}) => (
  <Container>
    <BarChart data = {data} margin={{
      top: 10,
      right: 30,
      left: 0,
      bottom: 0,
    }}>
      <XAxis dataKey="hour"/>
      <YAxis/>
      <CartesianGrid strokeDasharray="3 3"/>
      <Tooltip />
      <Legend/>
      <Bar dataKey="inNoOutStore" name="入店未离开人数" stackId="a" fill="#8884d8" />
      <Bar dataKey="outNoInStore" name="离开店铺人数" stackId="a" fill="#82ca9d" />
      <Bar dataKey="stayInStore" name="店铺停留人数" stackId="a" fill="#ffc658" />
      <Bar dataKey="inNoOutWifi" name="进入检测范围人数" stackId="b" fill="#8884d8" />
      <Bar dataKey="outNoInWifi" name="离开检测范围人数" stackId="b" fill="#82ca9d" />
      <Bar dataKey="stayInWifi" name="检测范围停留人数" stackId="b" fill="#ffc658" />
    </BarChart>
  </Container>
)

export default HourCompareChart
