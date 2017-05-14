package com.codingfairy.web.json.analysis;

import com.codingfairy.web.json.analysis.element.CustomerActivenessElement;

/**
 * Created by darxan on 2017/5/14.
 * 顾客活跃度：按顾客距离上次来访间隔,划分为不同活跃度（高活跃度、中活跃度、低活跃度、沉睡活跃度）
 * 横坐标表示顾客举例上次来访的时间间隔,以毫秒为单位
 * 纵坐标表示对应时间间隔的顾客人数
 */
public class CustomerActiveness extends ProbStatisticData<CustomerActivenessElement> {
}
