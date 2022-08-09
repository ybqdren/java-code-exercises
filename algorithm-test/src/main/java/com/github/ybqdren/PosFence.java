package com.github.ybqdren;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 围栏定位
 *
 *
 * created by ybqdren
 */
public class PosFence {
    /**
     权重最高 ：A[106.557317f][29.615452f] -> 距离 A[106.55739f],[29.618879f]
     如果每个点权重相同，在当前点中取一个中值，排除掉所有超出radius的节点
     */




    public static void go(List<Position> positions){
        // 使用 timSort 进行排序
        Collections.sort(positions);

    }

}
