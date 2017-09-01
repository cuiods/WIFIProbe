/**
 * Created by yyy on 2017/8/25.
 */
import React, {PropTypes} from 'react'
import {Form, Select, Input, Modal, Button, Card,Icon} from 'antd'
import styles from './LoginForm.less'
const FormItem = Form.Item;
const confirm = Modal.confirm;

const RegisterForm = ({
  onLogin,
  onRegister,
  form : {
    getFieldDecorator,
    validateFields,
    getFieldValue,
    getFieldsValue
  }
}) => {

  let state = {
    confirmDirty: false,
  };

  function handleSubmit(e) {
    e.preventDefault();

    validateFields((err) => {
      if (!err) {
        const form = getFieldsValue();
        const data = {
          ...form,
          avatar:"http://hostel-world.oss-cn-shanghai.aliyuncs.com/images/avatar.png",
        };
          onRegister(data);
        }
    })

  }

  function onLoginButton(e){
    e.preventDefault();
    onLogin();
  }

  function handleConfirmBlur(e) {
    const value = e.target.value;
    state = { ...state,confirmDirty: state.confirmDirty || !!value };
  }

  function checkPassword(rule, value, callback){
    if(value && value !== getFieldValue('password')) {
      callback('password not conform to each other!');
    }else{
      callback();
    }
  }

  function checkConfirm(rule, value, callback) {
    if (value && state.confirmDirty) {
      validateFields(['confirm'], { force: true });
    }
    callback();
  }


  const formItemLayout = {
    labelCol: {span: 6},
    wrapperCol: {span: 4},
  };

  const tailFormItemLayout = {
    wrapperCol: {
      xs: {
        span: 14,
        offset: 0,
      },
      sm: {
        span: 14,
        offset: 6,
      },
    },
  };

  return (
    <div className={styles['login-form']}>
      <Card className={styles['card']}>
        <h1 className={styles['title']}>注册</h1>
        <Form className={styles['login-inline-form']} onSubmit={handleSubmit} >
          <FormItem hasFeedback>
            {getFieldDecorator('username',{
              rules: [{required: true, message: '请输入用户名'}],
            })(
              <Input addonBefore={<Icon type="user"/>} placeholder="username"/>
            )}
          </FormItem>
          <FormItem hasFeedback>
            {getFieldDecorator('password',{
              rules: [{required: true, message: '请输入密码'},{validator: checkConfirm}],
            })(
              <Input addonBefore={<Icon type="lock"/>} type="password" placeholder="password"/>
            )}
          </FormItem>
          <FormItem hasFeedback>
            {getFieldDecorator('confirm',{
              rules: [{required: true, message: '请再次输入密码'},{validator:checkPassword}],
            })(
              <Input addonBefore={<Icon type="lock"/>} onBlur={handleConfirmBlur} type="password" placeholder="confirm password"/>
            )}
          </FormItem>

          <FormItem>
            <Button type="primary" htmlType="submit" className={styles['login-form-button']}>
              注 册
            </Button>
            <div style={{textAlign: "center"}}>已有账户？</div>
            <Button type="primary" onClick={onLoginButton} className={styles["login-form-button"]}>
              登 陆
            </Button>
          </FormItem>

        </Form>
      </Card>
    </div>
  );
}

export default  Form.create()(RegisterForm)
