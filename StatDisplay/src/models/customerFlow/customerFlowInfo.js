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
    timer:-1000,
    last_num1: 0,
    last_num2: 0,
  },

  subscriptions: {
    setup ({ dispatch, history }) {
      history.listen(location => {
        const matchFlow = pathToRegexp('/customerFlow').exec(location.pathname);
        if(matchFlow) {
          const currentDate = new Date();
          const currentHour = parseInt(currentDate.valueOf()/(1000*60*60));//距离现在最近的整点对应的小时数
          const beforeHour = currentHour-5*24;//获取距离最近整点前5天的数据
          console.log("currentHour :"+currentHour+";beforehour:"+beforeHour);
          dispatch({
            type: 'getFlow',
            payload: {probeId:"1s12sz",startHour:beforeHour,startRange:5,threshold:"DAY"}
          });
          dispatch({
            type: 'getDetail',
            payload: {hour:currentHour,probeId:"1s12sz"}
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

        console.log("data is:"+JSON.stringify(data));
        console.log(data.latestCommit != null);


        if(data.serverName == 'server1'){

          if(data.latestCommit != null) {
            time = data.latestCommit.slice(11, 19);
            num1 = data.connectNum;
          }else{//服务器刚刚提交数据
            let currentDate = new Date();
            time = currentDate.toLocaleTimeString().slice(0,8);
            num1 = -2; //-2表示服务器刚刚提交数据，需要上一次数据
          }

          console.log("server1 num:"+num1);

        }
        if(data.serverName == 'server2'){
          if(data.latestCommit != null){
            num2 = data.connectNum;
          }else{//服务器刚刚提交数据
            num2 = -2;
          }

          console.log("server2 num:"+num2);

        }
        if(num1 != -1 && num2 !=-1){
          console.log("get both server1 and server2");
          if(num1 !=-2){ //更新存储的前一次数据
            yield put({
              type: 'setLastNum1',
              payload:{last_num1:num1}
            });
          }

          if(num2 !=-2){ //更新存储的前一次数据
            yield put({
              type: 'setLastNum2',
              payload:{last_num2:num2}
            });
          }
          break;
        }
      }
      yield put({
        type: 'updateRealTimeData',
        payload:{num1: num1,num2:num2, time: time}
      });

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

        delete commonItem.inNoOutWifi;
        delete commonItem.inNoOutStore;
        delete commonItem.outNoInWifi;
        delete commonItem.outNoInStore;
        delete commonItem.inAndOutWifi;
        delete commonItem.intAndOutStore;
        delete commonItem.stayInWifi;
        delete commonItem.stayInStore;

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
      let num1 = action.payload.num1;
      let num2 = action.payload.num2;
      if(num1 == -2){
        num1 = state.last_num1;
        console.log("last num1 :"+num1);
      }
      if(num2 == -2){
        num2 = state.last_num2;
        console.log("last num2:"+num2);
      }
      let newItem = {
        num: num1+num2,
        time: action.payload.time
      };
      console.log("new item is:"+JSON.stringify(newItem));
      let size = state.realTimeData.push(newItem);
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
    },

    setLastNum1(state,action){
      console.log("redux setLastNum1: "+action.payload.last_num1);
      return {
        ...state,last_num1:action.payload.last_num1
      }
    },

    setLastNum2(state, action){
      console.log("redux setLastNum2:"+action.payload.last_num2);
      return {
        ...state,last_num2:action.payload.last_num2
      }
    },

    getState(state,action){
      return {
        ...state
      }
    }

  }


}
