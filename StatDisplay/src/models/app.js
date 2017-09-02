/**
 * Created by yyy on 2017/5/16.
 */
import cookie from 'react-cookie';
import {routerRedux} from 'dva/router';
import {login, logout,register,changePassword} from '../services/VerifyService';
import BasicAuth from '../utils/BasicAuth';
import {message,notification,Modal} from 'antd';


export default {
  namespace: 'app',
  state:{
    user:{
      id:0,
      username:''
    },
    userId: cookie.load('userId'),
    isLogin:false,
    isRegister:false,
    alertVisible: false,
    loginMsg:'',
    menuPopoverVisible: false,
    siderFold: localStorage.getItem('probeSiderFold') === 'true',
    darkTheme: localStorage.getItem('probeDarkTheme') !== 'false',
    isNavbar: document.body.clientWidth < 769,
    navOpenKeys: JSON.parse(localStorage.getItem('navOpenKeys')) || [],

    loading: false,
  },

  subscriptions: {
    setup({ dispatch, history }) {
      history.listen(location => {
        if (location.pathname !== '/') {
          let userInfo = cookie.load('info');
          if(userInfo == null){//未登录则跳转到登录页面
            dispatch(routerRedux.push('/'));
          }else{
            dispatch({
              type: 'storeInfo',
              payload: userInfo
            });
          }

        }
      })
      window.onresize = function() {
        dispatch({type: 'changeNavbar'})
      }
    }
  },

  effects:{
    *verify({payload}, {call,put}) {
      const data = yield call(login, {
        username: payload.username,
        password: payload.password
      });
      if(data){
        let verifyFlag = data.code;
        if(verifyFlag == 1000){//登录成功
          let userVo = data.data;
          cookie.save('info',userVo);
          BasicAuth.setAuth(payload.username, payload.password);
          yield put({
            type: 'storeInfo',
            payload: userVo
          });
          yield put(routerRedux.push('/customerFlow'));
        }else{//登录失败
          BasicAuth.clearAuth();
          yield put({
            type: 'showAlert',
            payload: data.message,
          });
        }
      }
    },
    *switchSider ({payload}, {put}) {
      yield put({
        type: 'handleSwitchSider'
      });
    },
    *changeTheme ({payload}, {put}) {
      yield put({
        type: "handleChangeTheme"
      });
    },
    *changNavbar ({payload}, {put}) {
      if(document.body.clientWidth < 769) {
        yield put({type: 'showNavbar'});
      }else{
        yield put({type: 'hideNavbar'});
      }
    },
    *switchMenuPopover ({payload}, {put}) {
      yield put({
        type: 'handleSwitchMenuPopover'
      })
    },
    *logout ({payload}, {call, put}) {
      const data = yield call(logout, payload);
      BasicAuth.clearAuth();
      cookie.remove('info');
      yield put({
        type: 'logoutSuccess'
      })
    },

    *register ({payload}, {call,put}){
      yield put({type: "setRegister"});
      yield put(routerRedux.push(`/register`));
    },

    *createUser({payload}, {call,put}) {
      const data = yield call(register, payload);
      if (data && data.code == 1000) {
        yield put({type: 'finishRegister'});
        message.success("Register success, please login.");
        yield put(routerRedux.push(`/`));
      } else {
        console.log(data);
        Modal.error({
          title: 'Register occurs error',
          content: data.data,
        });
      }
    },

    *jumpToLogin({payload},{call,put}){
      console.log("jump to login!");
      yield put({
        type: 'finishRegister'
      });

      yield put(routerRedux.push(`/`));
    },

    *changePassword({payload}, {call,put}){
      let userInfo = cookie.load("info");
      const userName = userInfo.username;
      const data = yield call(changePassword, {
        oldPassword: payload.oldPassword,
        newPassword: payload.newPassword,
        username: userName
      })

      if(data&&data.code == 1000) {
        const args = {
          message: 'Password Changed',
          description: 'Your password has been changed, Please login again',
          duration: 3,
        }
        notification.open(args);
        yield put({type:"app/logout"});
        yield put(routerRedux.push(`/`));
      }else{
        Modal.error({
          title: 'Register occurs error',
          content: data.data,
        });
      }
    },


  },

  reducers:{
    showAlert(state,action){
      return {...state, alertVisible: true, loginMsg: action.payload};
    },

    closeAlert(state){
      return {...state, alertVisible: false};
    },

    storeInfo(state, action){
      return {
        ...state, user: action.payload, isLogin: true
      }
    },

    showLoading (state) {
      return {...state, loading:true};
    },

    hideLoading (state) {
      return {...state, loading:false};
    },

    handleSwitchSider(state){
      localStorage.setItem('probeSiderFold', !state.siderFold);
      return {...state, siderFold: !state.siderFold};
    },

    handleChangeTheme(state){
      localStorage.setItem('probeDarkTheme', !state.darkTheme);
      return {...state, darkTheme: !state.darkTheme};
    },

    showNavbar(state){
      return {...state, isNavbar:true};
    },

    hideNavbar(state){
      return {...state, isNavbar:false};
    },

    handleNavOpenKeys (state, action) {
      return {
        ...state,
        ...action.payload
      }
    },

    logoutSuccess(state){
      return {
        ...state,isLogin:false
      }
    },

    setRegister(state){
      return {
        ...state,isRegister:true
      }
    },

    finishRegister(state){
      return {
        ...state,isRegister:false
      }
    },



  }




}
