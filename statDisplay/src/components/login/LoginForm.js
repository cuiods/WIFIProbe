/**
 * Created by yyy on 2017/5/16.
 */
import React, { PropTypes } from 'react';
import { Form, Icon, Input, Button, Checkbox, Card, Alert } from 'antd';
import styles from './LoginForm.less';

const FormItem = Form.Item;

const LoginForm = ({
  userId,
  alertVisible,
  loginMsg,
  onLogin,
  closeAlert,
  form: {
    validateFields,
    getFieldDecorator,
  },
}) => {
  function handleSubmit(e) {
    e.preventDefault();
    validateFields((err, values) => {
      if (!err) {
        // console.log('Received values of form: ', values);
        onLogin(values);
      }
    });
  }

  function onClose() {
    closeAlert();
  }

  function loginFail(alertVisible){
    if(alertVisible){
      return (
        <Alert
          message="Fail! Please ensure your username and password is right."
          description={loginMsg}
          type="error"
          closable
          onClose = {onClose}
          />
      )
    }
  }
  return (
    <div className={styles['login-form']}>
      <div className={styles.alert} >
        {loginFail(alertVisible)}
      </div>
      <Card className={styles['card']} >
        <h1 className={styles['title']}>Log In</h1>
        <Form className={styles['login-inline-form']} onSubmit={handleSubmit}>
          <FormItem>
            {getFieldDecorator('username', {
              initialValue: userId,
              rules: [{ required: true, message: 'Please enter username' }],
            })(
              <Input addonBefore={<Icon type="user" />} placeholder="username" />,
            )}
          </FormItem>
          <FormItem>
            {getFieldDecorator('password', {
              rules: [{ required: true, message: 'Please enter password' }],
            })(
              <Input addonBefore={<Icon type="lock" />} type="password" placeholder="password" />,
            )}
          </FormItem>
          <FormItem>
            {getFieldDecorator('remember', {
              valuePropName: 'checked',
              initialValue: true,
            })(
              <Checkbox>Remember me</Checkbox>,
            )}
            <a className={styles['login-form-forget']} href="">Forgot password</a>
            <Button type="primary" htmlType="submit" className={styles['login-form-button']}>
              Log in
            </Button>
          </FormItem>
        </Form>
      </Card>
    </div>
  );
};

LoginForm.PropTypes = {

};

export default Form.create()(LoginForm);

