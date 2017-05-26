/**
 * Created by yyy on 2017/5/26.
 * to get the data for the activeness charts
 */

import pathToRegexp from 'path-to-regexp';
import {getProbeAll, getProbeDetail} from '../../services/probeService';
import {getActiveness, getActivenessDetail} from '../../services/activenessService';


export default {
  namespace: 'activenessInfo',

  state: {
    probeOptions:[],
    hourData:[],
    detailData:[]
  },

  subscriptions: {
    setup ({ dispatch, history }) {
      history.listen(location => {
        const matchFlow = pathToRegexp('/activeness').exec(location.pathname);
        if(matchFlow) {
          dispatch({
            type: 'getHourData',
            payload: {probeId:"1s12sz",startHour:40000,startRange:5,threshold:"HOUR"}
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
    *getHourData ({payload}, {call,put}) {
      const data = yield call(getActiveness, payload);
      if(data.code==1000){
        const hourVo = data.data;
        yield put({
          type: 'setHourData',
          payload:hourVo
        });
      }
    },

    *getDetail({payload}, {call,put}) {
      const data = yield call(getActivenessDetail, payload);
      if(data.code==1000){
        const dataVo = data.data;
        yield put({
          type: 'setDetailData',
          payload:dataVo
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

