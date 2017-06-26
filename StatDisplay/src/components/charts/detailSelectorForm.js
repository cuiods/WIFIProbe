/**
 * Created by yyy on 2017/5/25.
 * used for detail info selected
 */
import React, { PropTypes } from 'react';
import { Form, Icon, Input, Button, Select, Card, Alert, DatePicker} from 'antd';
import styles from './selectorForm.less';


const FormItem = Form.Item;

const DetailSelectorForm = ({
  probeOptions,
  getDetailData,
  form : {
    getFieldDecorator,
    validateFields,
    getFieldsValue,
    setFieldsValue
  },
}) => {
  function handleSubmit(e) {
    e.preventDefault();
    validateFields((err)=> {
      if(!err) {
        const form = getFieldsValue();
        const hours = parseInt( (form.time).valueOf()/(1000*60*60) );
        console.log("hour: "+hours);
        const data = {
          probeId: form.probeId,
          hour: hours,
        };
        getDetailData(data);
      }
    });

  }

  const options_shows = probeOptions.map((item,key) => {

    return (
      <Option value={item.value}>{item.value}</Option>
    )
  })

  return (
    <div className={styles['selector-form']}>
      <Form onSubmit={handleSubmit} layout="inline">
        <FormItem
          label="ProbeId"
          className={styles['form-item']}
        >
          {getFieldDecorator('probeId',{
            initialValue: '1s12sz'
          })(
            <Select>
              {options_shows}
            </Select>
          )}
        </FormItem>
        <FormItem
          label="Time"
          className={styles['form-item']}
        >
          {getFieldDecorator('time',{
            rules: [{ type: 'object', required: true, message: 'please enter the time' }],
          })(
            <DatePicker
              showTime
              format="YYYY-MM-DD HH:mm:ss"
              placeholder="time"
            />
          )}
        </FormItem>
        <FormItem className={styles['form-item']}>
          <Button type="primary" htmlType="submit" className={styles["selector-form-button"]}>submit</Button>
        </FormItem>

      </Form>
    </div>
  )
}

DetailSelectorForm.propTypes = {
  getDetailData: PropTypes.func,
};

export default  Form.create()(DetailSelectorForm);
