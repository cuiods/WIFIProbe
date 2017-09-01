/**
 * Created by yyy on 2017/8/25.
 */
import React, {PropTypes} from 'react'
import {Form, Select, Input, Modal, Button, Card, Icon} from 'antd'
import styles from './LoginForm.less'

const FormItem = Form.Item;
const confirm = Modal.confirm;
const hint = Modal.info;

const ChangePasswordForm = ({
  onChangePassword,
  form: {
    getFieldDecorator,
    validateFields,
    getFieldsValue,
    getFieldValue
  }

}) => {

  let state = {
    confirmDirty: false,
  };

  function handleSubmit(e){
    e.preventDefault();
    validateFields((err,values) => {
      if(!err){
        onChangePassword(values);
      }


    })
  }

  function handleConfirmBlur(e){
    const value = e.target.value;
    state = { ...state,confirmDirty: state.confirmDirty || !!value };
  }

  function checkPassword(rule,value, callback){
    if(value && value !== getFieldValue('newPassword')) {
      callback('password not conform to each other!');
    }else{
      callback();
    }
  }

  function checkConfirm(rule,value,callback) {
    if(value&& state.confirmDirty){
      validateFields(['confirm'],{force:true})
    }
    callback();
  }

  return (
    <div className={styles['login-form']}>
      <Card className={styles['card']}>
        <Form className={styles['login-inline-form']} onSubmit={handleSubmit} >
          <FormItem
            label="请输入旧密码"
            hasFeedback>
            {getFieldDecorator('oldPassword',{
              rules: [{required: true, message: 'Please enter the old password'}],
            })(
              <Input placeholder="old password" type="password"/>
            )}
          </FormItem>
          <FormItem
            label="请输入新密码"
            hasFeedback>
            {getFieldDecorator('newPassword',{
              rules: [{required: true, message: 'Please enter new password'},{validator: checkConfirm}],
            })(
              <Input type="password" placeholder="new password"/>
            )}
          </FormItem>
          <FormItem
            label="再次确认新密码"
            hasFeedback>
            {getFieldDecorator('confirm',{
              rules: [{required: true, message: 'Please enter new password again'},{validator:checkPassword}],
            })(
              <Input onBlur={handleConfirmBlur} type="password" placeholder="confirm new password"/>
            )}
          </FormItem>

          <FormItem>
            <Button type="primary" htmlType="submit" className={styles['login-form-button']}>
              确认修改
            </Button>
          </FormItem>

        </Form>

      </Card>

    </div>
  )


}

ChangePasswordForm.PropTypes = {

};

export default Form.create()(ChangePasswordForm);
