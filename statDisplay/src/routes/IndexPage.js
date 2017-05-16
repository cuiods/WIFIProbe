import React, { PropTypes } from 'react';
import { connect } from 'dva';
import LoginForm from '../components/login/LoginForm';


function IndexPage({ location, dispatch }) {
  return (
    <LoginForm />
  );
}

IndexPage.propTypes = {
};

export default connect()(IndexPage);
