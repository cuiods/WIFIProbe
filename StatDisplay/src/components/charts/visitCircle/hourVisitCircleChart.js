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


const HourVisitCircleChart = ({data}) => (
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
      <Line type="monotone" dataKey="data0" stroke="#8884d8" name="0-5min" strokeWidth={2}/>
      <Line type="monotone" dataKey="data1" stroke="#82ca9d" name="5-30min" strokeWidth={2}/>
      <Line type="monotone" dataKey="data2" stroke="#ffc658" name="0.5-1h" strokeWidth={2}/>
      <Line type="monotone" dataKey="data3" stroke="#BA55D3" name="1-6h" strokeWidth={2}/>
      <Line type="monotone" dataKey="data4" stroke="#55488d" name="6-24h" strokeWidth={2}/>
      <Line type="monotone" dataKey="data5" stroke="#28acd9" name="1-3day" strokeWidth={2}/>
      <Line type="monotone" dataKey="data6" stroke="#ff6c85" name="3-7day" strokeWidth={2}/>
      <Line type="monotone" dataKey="data7" stroke="#AB553D" fname="7-30day" strokeWidth={2}/>
      <Line type="monotone" dataKey="data8" stroke="#4d8888" name="30-180day" strokeWidth={2}/>
      <Line type="monotone" dataKey="data9" stroke="#9dac82" name=">180day" strokeWidth={2}/>

      <Line type="monotone" dataKey="data0Pre" stroke="#8884d8" name="0-5min-Pre" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="data1Pre" stroke="#82ca9d" name="5-30min-Pre" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="data2Pre" stroke="#ffc658" name="0.5-1h-Pre" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="data3Pre" stroke="#BA55D3" name="1-6h-Pre" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="data4Pre" stroke="#55488d" name="6-24h-Pre" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="data5Pre" stroke="#28acd9" name="1-3day-Pre" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="data6Pre" stroke="#ff6c85" name="3-7day-Pre" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="data7Pre" stroke="#AB553D" fname="7-30day-Pre" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="data8Pre" stroke="#4d8888" name="30-180day-Pre" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="data9Pre" stroke="#9dac82" name=">180day-Pre" strokeDasharray="5 5" strokeWidth={2}/>
    </LineChart>
  </Container>
);



export default HourVisitCircleChart
