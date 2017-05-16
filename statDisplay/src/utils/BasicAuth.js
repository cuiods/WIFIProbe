import cookie from 'react-cookie';
import basic from 'basic-authorization-header';

export default {

  getAuth(){
    return cookie.load("token");
  },

  setAuth(username, password){
    let token = basic(username, password);
    cookie.save("token", token);
    cookie.save("username",username);
  },

  clearAuth(){
    cookie.remove("token","/");
    cookie.remove("username","/");
  }
}
