/**
 * Created by yyy on 2017/5/16.
 */
import React, { PropTypes } from 'react';
import { Form, Icon, Input, Button, Checkbox } from 'antd';
import styles from './LoginForm.less';

const FormItem = Form.Item;

const LoginForm = ({
  form: {
    validateFields,
    getFieldDecorator,
  },
}) => {
  function handleSubmit(e) {
    e.preventDefault();
    // to do
  }

  return (
    <div className={styles['login-form']}>
      <div className={styles.alert} />
      <Form className={styles['login-inline-form']} onSubmit={handleSubmit}>
        <FormItem>
          {getFieldDecorator('username', {
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
          or <a href="">register now!</a>
        </FormItem>
      </Form>
    </div>
  );
};

LoginForm.PropTypes = {

};

export default Form.create()(LoginForm);

