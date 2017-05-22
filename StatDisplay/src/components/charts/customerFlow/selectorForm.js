/**
 * Created by yyy on 2017/5/21.
 * to provide the selector for users to choose
 */

import React, { PropTypes } from 'react';
import { Form, Icon, Input, Button, Select, Card, Alert, DatePicker} from 'antd';
import styles from './selectorForm.less';


const FormItem = Form.Item;

const Option = Select.Option;

const SelectorForm = ({
  probeOptions,
  getFlowData,
  form : {
    getFieldDecorator,
    validateFields,
    getFieldsValue,
    setFieldsValue
  }
}) => {

  function handleSubmit(e) {
    e.preventDefault();
    validateFields((err)=> {
      if(!err) {
        const form = getFieldsValue();
        let divided = 1;
        if(form.threshold == 'HOUR'){
          divided = 1000*60*60;
        }else if(form.threshold == 'DAY'){
          divided = 1000*60*60*24;
        }else if(form.threshold == 'WEEK'){
          divided = 1000*60*60*24*7;
        }else if(form.threshold == 'MONTH'){
          divided = 1000*60*60*24*30;
        }else{
          divided = 1000*60*60*24*365;
        }
        const hours = parseInt( (form.startTime).valueOf()/(1000*60*60) )
        const startHour =parseInt( (form.startTime).valueOf()/divided ) ;
        const endHour = parseInt( (form.endTime).valueOf()/divided );
        let range = endHour-startHour ;
        console.log(form.probeId+" "+hours+" "+range+" "+form.threshold);
        const data = {
          probeId: form.probeId,
          startHour: hours,
          startRange:range,
          threshold: form.threshold,
        };
        console.log("param data is : "+ data);
        getFlowData(data);
      }
    });
  }

  const formItemLayout = {
    labelCol: {
      xs: {span:12},
      sm: {span:12},
    },
    wrapperCol: {
      xs: {span:12},
      sm: {span:12},
    },
  };

  function onProbeChange(value){}

  const thresholdOptions = [
    {
      value:'HOUR', label:'HOUR',
    },
    {
      value:'DAY', label:'DAY',
    },
    {
      value:'WEEK', label:'WEEK',
    },
    {
      value:'MONTH', label:'MONTH',
    },
    {
      value:'YEAR', label:'YEAR',
    }
  ];

  function onThreshHoldChange(value){}



  return (
    <div className={styles['selector-form']}>
      <Form onSubmit={handleSubmit} layout="inline">
        <FormItem
          {...formItemLayout}
          label="ProbeId"
          className={styles['form-item']}
        >
          {getFieldDecorator('probeId',{
            initialValue: '1s12sz'
          })(
            <Select>
              <Option value="1s12sz">1s12sz</Option>
            </Select>
          )}
        </FormItem>
        <FormItem
          {...formItemLayout}
          label="Threshold"
          className={styles['form-item']}
        >
          {getFieldDecorator('threshold',{
            initialValue: 'YEAR'
          })(
            <Select>
              <Option value = "HOUR">HOUR</Option>
              <Option value = "DAY">DAY</Option>
              <Option value = "WEEK">WEEK</Option>
              <Option value = "MONTH">MONTH</Option>
              <Option value = "YEAR">YEAR</Option>
            </Select>
          )}
        </FormItem>
        <FormItem
          {...formItemLayout}
          label="StartTime"
          className={styles['form-item']}
        >
          {getFieldDecorator('startTime',{
            rules: [{ type: 'object', required: true, message: 'please enter the start time' }],
          })(
            <DatePicker
              showTime
              format="YYYY-MM-DD HH:mm:ss"
              placeholder="startTime"
            />
          )}
        </FormItem>
        <FormItem
          {...formItemLayout}
          label="EndTime"
          className={styles['form-item']}
        >
          {getFieldDecorator('endTime',{
            rules: [{ type: 'object', required: true, message: 'please enter the end time' }],
          })(
            <DatePicker
              showTime
              format="YYYY-MM-DD HH:mm:ss"
              placeholder="endTime"
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

SelectorForm.propTypes = {
  getFlowData: PropTypes.func,
};

export default  Form.create()(SelectorForm);
