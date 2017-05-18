import React, {PropTypes} from 'react';
import {Router, Route, IndexRoute, Link} from 'dva/router';

import IndexPage from './routes/IndexPage';
import CustomerFlowPage from './routes/customerFlow/customerFlowPage';


export default function ({history}) {
  return (
    <Router history={history}>
      <Route path="/" component={IndexPage}>
        <Route path="/customerFlow/customerFlow" component={CustomerFlowPage}/>
      </Route>
    </Router>
  );
};
