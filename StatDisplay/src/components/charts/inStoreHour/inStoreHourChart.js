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
      <Line type="monotone" dataKey="data0"  stroke="#8884d8"  name="0-0.5min" strokeWidth={2}/>
      <Line type="monotone" dataKey="data1"  stroke="#82ca9d"  name="0.5-2min"strokeWidth={2} />
      <Line type="monotone" dataKey="data2"  stroke="#ffc658"  name="2-5min" strokeWidth={2}/>
      <Line type="monotone" dataKey="data3"  stroke="#BA55D3"  name="5-15min" strokeWidth={2}/>
      <Line type="monotone" dataKey="data4"  stroke="#55488d"  name="15-30min" strokeWidth={2}/>
      <Line type="monotone" dataKey="data5"  stroke="#28acd9"  name="30-60min" strokeWidth={2}/>
      <Line type="monotone" dataKey="data6"  stroke="#ff6c85"  name="1-2h" strokeWidth={2}/>
      <Line type="monotone" dataKey="data7"  stroke="#AB553D"  name="2-4h" strokeWidth={2}/>
      <Line type="monotone" dataKey="data8"  stroke="#4d8888"  name="4-6h" strokeWidth={2}/>
      <Line type="monotone" dataKey="data9"  stroke="#9dac82"  name=">6h" strokeWidth={2}/>

      <Line type="monotone" dataKey="data0Pre"  stroke="#8884d8"  name="0-0.5min-pre" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="data1Pre"  stroke="#82ca9d"  name="0.5-2min-pre" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="data2Pre"  stroke="#ffc658"  name="2-5min-pre" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="data3Pre"  stroke="#BA55D3"  name="5-15min-pre" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="data4Pre"  stroke="#55488d"  name="15-30min-pre" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="data5Pre"  stroke="#28acd9"  name="30-60min-pre" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="data6Pre"  stroke="#ff6c85"  name="1-2h-pre" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="data7Pre"  stroke="#AB553D"  name="2-4h-pre" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="data8Pre"  stroke="#4d8888"  name="4-6h-pre" strokeDasharray="5 5" strokeWidth={2}/>
      <Line type="monotone" dataKey="data9Pre"  stroke="#9dac82"  name=">6h-pre" strokeDasharray="5 5" strokeWidth={2}/>
    </LineChart>
  </Container>
);



export default InStoreHourChart
