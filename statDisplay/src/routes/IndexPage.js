import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'dva';
import { Layout } from '../components';
import { classnames, config, menu } from '../utils';
import { Helmet } from 'react-helmet';
import '../themes/index.less';
import './IndexPage.less';
import NProgress from 'nprogress';
import LoginForm from '../components/login/LoginForm';

const { prefix } = config;

function IndexPage({ children, location, dispatch, app }) {


  return (
    <LoginForm />
  );
}

IndexPage.propTypes = {
};

export default connect()(IndexPage);
