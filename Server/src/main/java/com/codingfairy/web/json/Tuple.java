package com.codingfairy.web.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by darxan on 2017/5/14.
 * TWO DATA
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tuple<T,V> {
    private T x;//int
    private V y;//
}
