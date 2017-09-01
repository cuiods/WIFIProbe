/**
 * Created by yyy on 2017/5/27.
 */
import pathToRegexp from 'path-to-regexp';
import {getProbeAll, getProbeDetail} from '../../services/probeService';
import {getNewOld, getNewOldDetail} from '../../services/newOldService';
import {Modal} from 'antd';


export default {
  namespace: 'newOldInfo',

  state: {
    probeOptions:[],
    hourData:[],
    detailData:[]
  },

  subscriptions: {
    setup ({ dispatch, history }) {
      history.listen(location => {
        const matchFlow = pathToRegexp('/newAndOld').exec(location.pathname);
        if(matchFlow) {
          dispatch({
            type: 'getProbeOptions',
            payload: {page:0, size: 10}
          });
          dispatch({
            type: 'getHourData',
            payload: {probeId: '1s12sz', startHour:40000, startRange:5, threshold:"HOUR"}
          });
          dispatch({
            type: 'getDetail',
            payload: {hour:415300,probeId:"1s12sz"}
          });
        }
      })
    }
  },

  effects: {
    *getHourData ({payload}, {call,put}) {
      const data = yield call(getNewOld, payload);
      if(data.code==1000){
        const hourVo = data.data;
        yield put({
          type: 'setHourData',
          payload:hourVo
        });
      }else{
        console.log("data is "+JSON.stringify(data));
        Modal.error({
          title: 'get data occurs error',
          content: data.msg,
        });

      }
    },

    *getDetail({payload}, {call,put}) {
      const data = yield call(getNewOldDetail, payload);
      if(data.code==1000){
        const dataVo = data.data;
        yield put({
          type: 'setDetailData',
          payload:dataVo
        })
      }else{
        console.log("data is "+JSON.stringify(data));
        Modal.error({
          title: 'get data occurs error',
          content: data.msg,
        });

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

