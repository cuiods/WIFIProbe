import React, {PropTypes} from 'react';
import {Router, Route, IndexRoute, Link} from 'dva/router';

import IndexPage from './routes/IndexPage';
import CustomerFlowPage from './routes/customerFlow/customerFlowPage';
import ActivenessPage from './routes/activeness/activessnessPage';
import NewOldPage from './routes/newOld/newOldPage';
import InStoreHourPage from './routes/inStoreHour/inStoreHourPage';
import VisitCirclePage from './routes/visitCircle/visitCirclePage';


export default function ({history}) {
  return (
    <Router history={history}>
      <Route path="/" component={IndexPage}>
        <Route path="/customerFlow" component={CustomerFlowPage}/>
        <Route path="/activeness" component={ActivenessPage} />
        <Route path="/newAndOld" component={NewOldPage} />
        <Route path="/inStoreHour" component={InStoreHourPage} />
        <Route path="/visitCircle" component={VisitCirclePage} />

      </Route>
    </Router>
  );
};
