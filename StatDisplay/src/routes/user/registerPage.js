/**
 * Created by yyy on 2017/8/25.
 */
import React, { PropTypes } from 'react';
import { connect } from 'dva';
import RegisterForm from '../../components/login/RegisterForm';

function RegisterPage({loaction, dispatch}) {
  const registerProps = {
    onRegister(fieldValue){
      dispatch({
        type : 'app/createUser',
        payload: {...fieldValue},
      });
    }
  };

  return (
    <RegisterForm {...registerProps}/>
  );

}

RegisterPage.propTypes = {
  location: PropTypes.object,
  dispatch: PropTypes.func,
};

function mapStateToProps({}) {
  return {};
}

export default connect(mapStateToProps)(RegisterPage);
