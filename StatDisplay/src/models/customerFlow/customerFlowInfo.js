/**
 * Created by yyy on 2017/5/17.
 * to get the data for customerFlow's charts
 */
import {message,Modal} from 'antd';
import pathToRegexp from 'path-to-regexp';
import {getProbeAll, getProbeDetail} from '../../services/probeService';
import {getCustomerFlow, getCustomerFlowDetail,getRealTimeCustomerFlow} from '../../services/customerFlowService';
import cookie from 'react-cookie';
import { routerRedux } from 'dva/router';

export default {
  namespace: 'customerFlowInfo',

  state: {
    probeOptions:[],
    hourData:[],
    detailData:[],
    realTimeData:[],
    timer:-1000
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
          });
          //重新加载本页面时清空interval
          dispatch({
            type:"clearTimer",
          });
          dispatch({
            type:"getRealTimeFlow",
            payload:{}
          });
          let time = 0;
          function refreshRealTimeData(){
            dispatch({
              type:"getRealTimeFlow",
              payload:{}
            });
            console.log("execute:"+ (++time));
          }
          const tempTimer = setInterval(refreshRealTimeData,60000);
          dispatch({
            type:"setTimer",
            payload:tempTimer
          });
        }
      })
    }
  },

  effects: {
    *getRealTimeFlow({payload},{call,put}){
      let num1 = -1;
      let num2 = -1;
      let time = '';
      while (true){
        const data = yield call(getRealTimeCustomerFlow,payload);
        if(!data){
          continue;
        }
        if(data.serverName == 'server1'){
          num1 = data.connectNum;
          time = data.latestCommit.slice(11,19);
          console.log("server1 num:"+num1);
        }
        if(data.serverName == 'server2'){
          num2 = data.connectNum;
          console.log("server2 num:"+num2);
        }
        if(num1 != -1 && num2 !=-1){
          console.log("get both server1 and server2");
          break;
        }
      }
      // const data = yield call(getRealTimeCustomerFlow,payload);
      // if(data){
      //   const time = data.latestCommit.slice(11,19);
        yield put({
          type: 'updateRealTimeData',
          payload:{num: num1+num2, time: time}
        });
      // }
    },
    *getFlow ({payload}, {call,put}) {
      const data = yield call(getCustomerFlow, payload);
      if(data){
        console.log(JSON.stringify("data is: "+JSON.stringify(data)));
        const hourVo = data.data;
        const len = hourVo.length;
        let realData = hourVo.slice(0,len-3);
        let commonItem = hourVo[len-3];
        commonItem["inNoOutWifiPre"] = commonItem.inNoOutWifi;
        commonItem["inNoOutStorePre"] = commonItem.inNoOutStore;
        commonItem["outNoInWifiPre"] = commonItem.outNoInWifi;
        commonItem["outNoInStorePre"] = commonItem.outNoInStore;
        commonItem["inAndOutWifiPre"] = commonItem.inAndOutWifi;
        commonItem["intAndOutStorePre"] = commonItem.intAndOutStore;
        commonItem["stayInWifiPre"] = commonItem.stayInWifi;
        commonItem["stayInStorePre"] = commonItem.stayInStore;
        commonItem["jumpRatePre"] = commonItem.jumpRate;
        commonItem["deepVisitPre"] = commonItem.deepVisit;
        commonItem["inStoreRatePre"] = commonItem.inStoreRate;
        realData.push(commonItem);

        const predictHourData = hourVo.slice(len-2);
        predictHourData.forEach(function(item) {
            console.log("item is :" + JSON.stringify(item));
            let predictItem = {
              id: item.id,
              wifiProb: item.wifiProb,
              hour: item.hour,
              inNoOutWifiPre: item.inNoOutWifi,
              inNoOutStorePre: item.inNoOutStore,
              outNoInWifiPre: item.outNoInWifi,
              outNoInStorePre: item.outNoInStore,
              inAndOutWifiPre: item.inAndOutWifi,
              intAndOutStorePre: item.intAndOutStore,
              stayInWifiPre: item.stayInWifi,
              stayInStorePre: item.stayInStore,
              jumpRatePre: item.jumpRate,
              deepVisitPre: item.deepVisit,
              inStoreRatePre: item.inStoreRate
            };
            console.log("predict item is: "+ JSON.stringify(predictItem));
            realData.push(predictItem);
          }
        );


        console.log("final data is :"+JSON.stringify(realData));

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
      const data = yield call(getCustomerFlowDetail, payload);
      if(data.code==1000){
        const dataVo = data.data;
        dataVo.splice(8,3);
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
    },

    updateRealTimeData(state,action){
      let size = state.realTimeData.push(action.payload);
      if(size > 16){
        state.realTimeData.shift();
      }
      state.realTimeData = [...new Set(state.realTimeData)];
      return {
        ...state
      }
    },

    setTimer(state,action){
      return {
        ...state,
        timer:action.payload
      }
    },

    clearTimer(state,action){
      if(state.timer != -1000) {
        console.log("clear timer!");
        clearInterval(state.timer);
      }
      return {
        ...state,timer:-1000
      }
    }

  }


}
