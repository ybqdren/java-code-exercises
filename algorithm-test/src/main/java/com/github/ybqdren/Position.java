package com.github.ybqdren;

import lombok.Builder;
import lombok.ToString;

/**
 * created by ybqdren
 */

@ToString
@Builder
public class Position implements Comparable<Position> {
    public float lat;
    public float lon;
    // 停留时间
    public long stayTimeTs;

    // 升序排列
    @Override
    public int compareTo(Position o) {
        if (this.stayTimeTs > o.stayTimeTs) {
            return -1;
        }
        if (this.stayTimeTs < o.stayTimeTs) {
            return 1;
        }
        return 0;
    }
}
