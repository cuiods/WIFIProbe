/**
 * Created by yyy on 2017/5/16.
 */
import {request} from '../utils/request';

export async function login(params) {
  return request(`/auth`,{
    method: `POST`,
    body: JSON.stringify({
      username: params.username,
      password: params.password
    })
  });
}

export async function register(params) {
  return request(`/auth/register`,{
    method: `post`,
    body: JSON.stringify({
      username: params.username,
      password: params.password
    })
  });
}

export async function logout(params) {
  return request(`/auth/logout`, {
    method: `GET`
  });
}

export async function changePassword(params) {
  return request(`/auth/changePassword`, {
    method: `post`,
    body: JSON.stringify({
      username: params.username,
      oldPassword: params.oldPassword,
      newPassword: params.newPassword,
    })
  })
}
