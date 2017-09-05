/**
 * Created by yyy on 2017/5/26.
 * to get the data for the activeness charts
 */

import pathToRegexp from 'path-to-regexp';
import {getProbeAll, getProbeDetail} from '../../services/probeService';
import {getActiveness, getActivenessDetail} from '../../services/activenessService';
import {Modal} from 'antd';


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
          const currentDate = new Date();
          const currentHour = parseInt(currentDate.valueOf()/(1000*60*60));//距离现在最近的整点对应的小时数
          const beforeHour = currentHour-5*24;//获取距离最近整点前5天的数据
          console.log("currentHour :"+currentHour+";beforehour:"+beforeHour);
          dispatch({
            type: 'getHourData',
            payload: {probeId:"1s12sz",startHour:beforeHour,startRange:5,threshold:"DAY"}
          });
          dispatch({
            type: 'getDetail',
            payload: {hour:currentHour,probeId:"1s12sz"}
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
        console.log("get hour data:"+JSON.stringify(data));
        const hourVo = data.data;
        const len = hourVo.length;
        let realData = hourVo.slice(0,len-3);
        let commonItem = hourVo[len-3];
        commonItem["numOfHighActivePre"] = commonItem.numOfHighActive;
        commonItem["numOfMedianActivePre"] = commonItem.numOfMedianActive;
        commonItem["numOfLowActivePre"] = commonItem.numOfLowActive;
        commonItem["numOfSleepActivePre"] = commonItem.numOfSleepActive;
        realData.push(commonItem);

        const predictHourData = hourVo.slice(len-2);
        predictHourData.forEach(function(item) {
            console.log("item is :" + JSON.stringify(item));
            let predictItem = {
              id: item.id,
              wifiProb: item.wifiProb,
              hour: item.hour,
              numOfHighActivePre: item.numOfHighActive,
              numOfMedianActivePre: item.numOfMedianActive,
              numOfLowActivePre: item.numOfLowActive,
              numOfSleepActivePre: item.numOfSleepActive
            };
            console.log("predict item is: "+ JSON.stringify(predictItem));
            realData.push(predictItem);
          }
        );


        yield put({
          type: 'setHourData',
          payload:realData
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
      const data = yield call(getActivenessDetail, payload);
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

