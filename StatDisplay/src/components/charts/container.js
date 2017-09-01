/**
 * Created by yyy on 2017/5/17.
 * the container to display the charts.
 */

import React, { PropTypes } from 'react';
import styles from './container.less';
import { ResponsiveContainer } from 'recharts';

const Container = ({ children, ratio = 5/2, minHeight = 280, maxHeight = 380 }) =>
  <div className={styles.container} style={{ minHeight, maxHeight }}>
    <div style={{ marginTop: `${100 / ratio}%` || '100%' }}></div>
    <div className={styles.content} style={{ minHeight, maxHeight}}>
      <ResponsiveContainer>
        {children}
      </ResponsiveContainer>
    </div>
  </div>

Container.propTypes = {
  children: PropTypes.element.isRequired,
  ratio: PropTypes.number,
  minHeight: PropTypes.number,
  maxHeight: PropTypes.number,
}

export default Container
