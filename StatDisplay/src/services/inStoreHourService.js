/**
 * Created by yyy on 2017/5/27.
 */
import {request} from "../utils/request";

export async function getInStoreHour(params) {
  return request(`/storeHour/stat`,{
    method: `POST`,
    body: JSON.stringify({
      probeId: params.probeId,
      startHour: params.startHour,
      startRange: params.startRange,
      threshold: params.threshold
    })
  })
}

export async function getInStoreHourDetail(params) {
  return request(`/storeHour/detail`,{
    method: `POST`,
    body: JSON.stringify({
      hour: params.hour,
      probeId: params.probeId
    })
  })
}
