package com.github.ybqdren;

import lombok.Builder;
import lombok.ToString;

/**
 * created by ybqdren
 */

@ToString
@Builder
public class Position {
    public float lat;
    public float lon;
    // 停留时间
    public long stayTimeTs;
}
