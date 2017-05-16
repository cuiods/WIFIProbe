import fetch from 'dva/fetch';
import host from '../services/hostConfig';
import BasicAuth from './BasicAuth';

function parse(response) {
  let promise = null;

  if(response.headers==null){
    return ;
  }

  const contentType = response.headers.get('Content-Type');

  if(contentType==null){
    return;
  }

  if(contentType.indexOf("text")>=0){
    promise = response.text();
    return promise;
  }else if(contentType.indexOf("json")>=0){
    promise = response.json();
    return promise;
  }
}

function checkStatus(response) {

  if (response.status >= 200 && response.status < 300) {
    return response;
  } else{
    throw response;
  }

}

function handleError(response) {


  let promise = parse(response);

  if(promise==null){
    return {status:response.status,ok:false};
  }

  return promise
    .then(data => {
      data.status = response.status;
      data.ok = response.ok;
      return data;
    });

}

/**
 * Requests a URL, returning a promise.
 *
 * @param  {string} url       The URL we want to request
 * @param  {object} [options] The options we want to pass to "fetch",such as
 {
   body : JSON.stringfy(obj),
   headers : {some header...}
 }
 * @return {object} response   the response body.
 *                             If status is not 2XX, response.ok == false
 *                             and response.status == http status code
 */
export function request(url, options) {

  if(options==null){
    options = {};
  }

  if(options['headers']==null){
    options['headers'] = {};
  }

  let token = BasicAuth.getAuth();
  if(token!=null){
    options['headers']['Authorization'] = token;
  }

  options['headers']['Content-Type'] = 'application/json';

  return fetch(host+url, options)
    .then(checkStatus)
    .then(parse)
    .then((data) => {

      if(data==null){
        data = {};
      }
      if(data.ok==null){
        data.ok = true;
      }
      return data;
    })
    .catch((response) => handleError(response) );
}

