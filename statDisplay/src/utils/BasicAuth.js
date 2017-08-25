import cookie from 'react-cookie';
import basic from 'basic-authorization-header';

export default {

  getAuth(){
    return cookie.load("token");
  },

  setAuth(username, password,avatar){
    let token = basic(username, password);
    cookie.save("token", token);
    cookie.save("avatar", avatar);
    cookie.save("username",username);
  },

  clearAuth(){
    cookie.remove("token","/");
    cookie.remove("avatar","/");
    cookie.remove("username","/");
  }
}
