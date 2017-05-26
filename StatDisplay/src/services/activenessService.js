/**
 * Created by yyy on 2017/5/26.
 * to provide activeness data
 */

import {request} from "../utils/request";

export async function getActiveness(params) {
  return request(`/activeness/stat`,{
    method: `POST`,
    body: JSON.stringify({
      probeId: params.probeId,
      startHour: params.startHour,
      startRange: params.startRange,
      threshold: params.threshold
    })
  })
}

export async function getActivenessDetail(params) {
  return request(`/activeness/detail`,{
    method: `POST`,
    body: JSON.stringify({
      hour: params.hour,
      probeId: params.probeId
    })
  })
}


