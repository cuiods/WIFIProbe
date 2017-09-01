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


const InStoreHourChart = ({data}) => (
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
      <Line type="monotone" dataKey="data0"  stroke="#8884d8"  name="0-0.5分钟" strokeWidth={2}/>
      <Line type="monotone" dataKey="data1"  stroke="#82ca9d"  name="0.5-2分钟"strokeWidth={2} />
      <Line type="monotone" dataKey="data2"  stroke="#ffc658"  name="2-5分钟" strokeWidth={2}/>
      <Line type="monotone" dataKey="data3"  stroke="#BA55D3"  name="5-15分钟" strokeWidth={2}/>
      <Line type="monotone" dataKey="data4"  stroke="#55488d"  name="15-30分钟" strokeWidth={2}/>
      <Line type="monotone" dataKey="data5"  stroke="#28acd9"  name="30-60分钟" strokeWidth={2}/>
      <Line type="monotone" dataKey="data6"  stroke="#ff6c85"  name="1-2小时" strokeWidth={2}/>
      <Line type="monotone" dataKey="data7"  stroke="#AB553D"  name="2-4小时" strokeWidth={2}/>
      <Line type="monotone" dataKey="data8"  stroke="#4d8888"  name="4-6小时" strokeWidth={2}/>
      <Line type="monotone" dataKey="data9"  stroke="#9dac82"  name=">6小时" strokeWidth={2}/>

      <Line type="monotone" dataKey="data0Pre"  stroke="#8884d8"  name="0-0.5分钟预测" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="data1Pre"  stroke="#82ca9d"  name="0.5-2分钟预测" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="data2Pre"  stroke="#ffc658"  name="2-5分钟预测" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="data3Pre"  stroke="#BA55D3"  name="5-15分钟预测" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="data4Pre"  stroke="#55488d"  name="15-30分钟预测" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="data5Pre"  stroke="#28acd9"  name="30-60分钟预测" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="data6Pre"  stroke="#ff6c85"  name="1-2小时预测" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="data7Pre"  stroke="#AB553D"  name="2-4小时预测" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="data8Pre"  stroke="#4d8888"  name="4-6小时预测" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="data9Pre"  stroke="#9dac82"  name=">6小时预测" strokeDasharray="5 5" strokeWidth={2}/>
    </LineChart>
  </Container>
);



export default InStoreHourChart
