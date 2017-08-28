package com.codingfairy.bl.service;

import com.codingfairy.bl.vo.*;
import com.codingfairy.utils.enums.QueryThreshold;

import java.util.List;

/**
 * predict data service
 * @author cuihao
 */
public interface PredictService {

    List<ActivenessVo> predictActiveness(List<ActivenessVo> activenessVos, QueryThreshold threshold);

    List<FlowVo> predictFlow(List<FlowVo> flowVos, QueryThreshold threshold);

    List<NewOldVo> predictNewOldVos(List<NewOldVo> newOldVos, QueryThreshold threshold);

    List<StoreHoursVo> predictStoreHours(List<StoreHoursVo> storeHoursVos, QueryThreshold threshold);

    List<VisitCircleVo> predictVisitCircles(List<VisitCircleVo> visitCircleVos, QueryThreshold threshold);
}
