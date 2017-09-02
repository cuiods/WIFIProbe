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
  getHourData,
  form : {
    getFieldDecorator,
    validateFields,
    getFieldsValue,
    setFieldsValue
  }
}) => {

  const options_shows = probeOptions.map((item,key) => {

    return (
      <Option value={item.value}>{item.value}</Option>
    )
  })

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
        console.log("param data is : "+ JSON.stringify(data));
        getHourData(data);
      }
    });
  }

  // const formItemLayout = {
  //   labelCol: {
  //     xs: {span:9},
  //     sm: {span:9},
  //   },
  //   wrapperCol: {
  //     xs: {span:15},
  //     sm: {span:15},
  //   },
  // };

  const formItemLayout = null;



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
          label="探针"
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
          {...formItemLayout}
          label="阈值"
          className={styles['form-item']}
        >
          {getFieldDecorator('threshold',{
            initialValue: 'YEAR'
          })(
            <Select>
              <Option value = "HOUR">小时</Option>
              <Option value = "DAY">天数</Option>
              <Option value = "WEEK">周数</Option>
              <Option value = "MONTH">月份</Option>
              <Option value = "YEAR">年份</Option>
            </Select>
          )}
        </FormItem>
        <FormItem
          {...formItemLayout}
          label="开始时间"
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
          label="结束时间"
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
          <Button type="primary" htmlType="submit" className={styles["selector-form-button"]}>提交</Button>
        </FormItem>

      </Form>
    </div>
  )
}

SelectorForm.propTypes = {
  getFlowData: PropTypes.func,
};

export default  Form.create()(SelectorForm);
