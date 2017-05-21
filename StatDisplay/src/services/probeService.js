/**
 * Created by yyy on 2017/5/21.
 */
import {request} from "../utils/request";

export async function getProbeAll(params) {
  return request(`/probe/all`,{
    method: `POST`,
    body: JSON.stringify({
      page: params.page,
      size: params.size
    })
  })
}

export async function getProbeDetail(params) {
  return request(`/probe/detail/${params.probeId}`,{
    method: `GET`
  })
}

