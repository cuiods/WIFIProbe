/**
 * Created by yyy on 2017/5/27.
 */
import {request} from "../utils/request";

export async function getVisitCircle(params) {
  return request(`/visitCircle/stat`,{
    method: `POST`,
    body: JSON.stringify({
      probeId: params.probeId,
      startHour: params.startHour,
      startRange: params.startRange,
      threshold: params.threshold
    })
  })
}

export async function getVisitCircleDetail(params) {
  return request(`/visitCircle/detail`,{
    method: `POST`,
    body: JSON.stringify({
      hour: params.hour,
      probeId: params.probeId
    })
  })
}
