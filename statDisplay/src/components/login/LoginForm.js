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
  onRegister,
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
          message="登陆失败，请检查你的用户名和密码~"
          description={loginMsg}
          type="error"
          closable
          onClose = {onClose}
          />
      )
    }
  }

  function onButtonRegister(){
    onRegister();
  }

  return (
    <div className={styles['login-form']}>
      <div className={styles.alert} >
        {loginFail(alertVisible)}
      </div>
      <Card className={styles['card']} >
        <h1 className={styles['title']}>登陆</h1>
        <Form className={styles['login-inline-form']} onSubmit={handleSubmit}>
          <FormItem>
            {getFieldDecorator('username', {
              initialValue: userId,
              rules: [{ required: true, message: '请输入用户名' }],
            })(
              <Input addonBefore={<Icon type="user" />} placeholder="username" />,
            )}
          </FormItem>
          <FormItem>
            {getFieldDecorator('password', {
              rules: [{ required: true, message: '请输入密码' }],
            })(
              <Input addonBefore={<Icon type="lock" />} type="password" placeholder="password" />,
            )}
          </FormItem>
          <FormItem>
            {getFieldDecorator('remember', {
              valuePropName: 'checked',
              initialValue: true,
            })(
              <Checkbox>记住我</Checkbox>,
            )}
            <a className={styles['login-form-forget']} href="/#/customerFlow">忘记密码</a>
            <Button type="primary" htmlType="submit" className={styles['login-form-button']}>
              登 陆
            </Button>
            <div style={{textAlign: "center"}}>或</div>
            <Button type="primary" onClick={onButtonRegister} className={styles["login-form-button"]}>
              注 册
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

