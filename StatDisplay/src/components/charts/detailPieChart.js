/**
 * Created by yyy on 2017/6/24.
 */
import React, { PropTypes } from 'react';
import Container from './container';
import {
  PieChart,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  Pie,
  Sector,
  Cell
} from 'recharts';

const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042'];

const RADIAN = Math.PI / 180;
const renderCustomizedLabel = ({ cx, cy, midAngle, innerRadius, outerRadius, percent, index }) => {
  const radius = innerRadius + (outerRadius - innerRadius) * 0.5;
  const x  = cx + radius * Math.cos(-midAngle * RADIAN);
  const y = cy  + radius * Math.sin(-midAngle * RADIAN);

  return (
    <text x={x} y={y} fill="white" textAnchor={x > cx ? 'start' : 'end'} 	dominantBaseline="central">
      {`${(percent * 100).toFixed(0)}%`}
    </text>
  );
};

const DetailPieChart = ({data}) => (
  <Container>
    <PieChart margin={{
      top: 10,
      right: 30,
      left: 0,
      bottom: 0,
    }}>
      <Pie
        data={data}
        cx={300}
        cy={200}
        labelLine={false}
        label={renderCustomizedLabel}
        outerRadius={80}
        fill="#8884d8"
      >
        {
          data.map((entry, index) => <Cell fill={COLORS[index % COLORS.length]}/>)
        }
      </Pie>
    </PieChart>
  </Container>
);

export default DetailPieChart
