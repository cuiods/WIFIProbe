/**
 * Created by yyy on 2017/5/27.
 */
import {request} from "../utils/request";

export async function getNewOld(params) {
  return request(`/newOld/stat`,{
    method: `POST`,
    body: JSON.stringify({
      probeId: params.probeId,
      startHour: params.startHour,
      startRange: params.startRange,
      threshold: params.threshold
    })
  })
}

export async function getNewOldDetail(params) {
  return request(`/newOld/detail`,{
    method: `POST`,
    body: JSON.stringify({
      hour: params.hour,
      probeId: params.probeId
    })
  })
}
