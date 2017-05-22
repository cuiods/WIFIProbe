/**
 * Created by yyy on 2017/5/17.
 * to get the data for customerFlow's charts
 */
import {message} from 'antd';
import pathToRegexp from 'path-to-regexp';
import {getProbeAll, getProbeDetail} from '../../services/probeService';
import {getCustomerFlow, getCustomerFlowDetail} from '../../services/customerFlowService';
import cookie from 'react-cookie';
import { routerRedux } from 'dva/router';

export default {
  namespace: 'customerFlowInfo',

  state: {
    probeOptions:[],
    hourData:[],
    detailData:[]
  },

  subscriptions: {
    setup ({ dispatch, history }) {
      history.listen(location => {
        const matchFlow = pathToRegexp('/customerFlow').exec(location.pathname);
        if(matchFlow) {
          dispatch({
            type: 'getFlow',
            payload: {probeId:"1s12sz",startHour:60000,startRange:5,threshold:"YEAR"}
          });
          dispatch({
            type: 'getDetail',
            payload: {hour:415357,probeId:"1s12sz"}
          });
          dispatch({
            type: 'getProbeOptions',
            payload: {page:0, size: 10}
          })
        }
      })
    }
  },

  effects: {
    *getFlow ({payload}, {call,put}) {
      const data = yield call(getCustomerFlow, payload);
      if(data){
        const hourVo = data.data;
        yield put({
          type: 'setHourData',
          payload:hourVo
        });
      }
    },

    *updateFlow ({payload}, {call,put}) {
      console.log("json: "+JSON.stringify(payload));
      for(let key in payload){
        console.log(key+":"+payload[key]);
      }
      console.log("MMM:"+payload.probeId);
      const data = yield call(getCustomerFlow, payload);
      console.log("updatedData is : "+ data.code);
      console.log("data is: "+ data);
      console.log("data.data is: " + data.data);
      if(data){
        const hourVo = data.data;
        yield put({
          type: 'setHourData',
          payload:hourVo
        });
      }
    },

    *getDetail({payload}, {call,put}) {
      const data = yield call(getCustomerFlowDetail, payload);
      if(data){
        yield put({
          type: 'setDetailData',
          payload:data.data
        })
      }
    },

    *getProbeOptions({payload},{call,put}) {
      const data = yield call(getProbeAll, payload);
      if(data){
        if(data.code == 1000){
          let probeList = [];
          const initProbeList = data.data.content;
          for(let key in initProbeList){
            probeList.push({value: initProbeList[key].probeId, label: initProbeList[key].probeId});
          }
          yield put({
            type: 'setProbeOptions',
            payload: probeList
          })


        }

      }
    }




  },

  reducers: {

    setHourData(state, action) {
      return {
        ...state,
        hourData:action.payload
      }
    },

    setDetailData(state, action) {
      return {
        ...state,
        detailData:action.payload
      }
    },

    setProbeOptions(state, action) {
      return {
        ...state,
        probeOptions:action.payload
      }
    }
  }


}
