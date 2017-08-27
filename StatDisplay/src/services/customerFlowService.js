/**
 * Created by yyy on 2017/5/21.
 */
import {request, fullRequest} from "../utils/request";

export async function getCustomerFlow(params) {
  return request(`/flow/stat`,{
    method: `POST`,
    body: JSON.stringify({
      probeId: params.probeId,
      startHour: params.startHour,
      startRange: params.startRange,
      threshold: params.threshold
    })
  })
}

export async function getCustomerFlowDetail(params) {
  return request(`/flow/detail`,{
    method: `POST`,
    body: JSON.stringify({
      hour: params.hour,
      probeId: params.probeId
    })
  })
}

export async function getRealTimeCustomerFlow() {
  return fullRequest(`http://118.89.208.120/api/v1/latest`,{
    method: `GET`
  });
}
