package com.github.ybqdren;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;

/**
 * created by ybqdren
 */
public class PosFenceTest {

    @Test
    void test(){
        int radius = 1000; // 1 km
        long nowDtTs = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));

        Position pos1 = Position.builder()
                .lat(106.557317f)
                .lon(29.615452f)
                .stayTimeTs(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"))).build();

        Position pos2 = Position.builder()
                .lat(106.55739f)
                .lon(29.618879f)
                .stayTimeTs(LocalDateTime.now().minusDays(1).toEpochSecond(ZoneOffset.of("+8"))).build();

        Position pos3 = Position.builder()
                .lat(106.554948f)
                .lon(29.618497f)
                .stayTimeTs(LocalDateTime.now().minusDays(1).minusHours(3).minusSeconds(23).toEpochSecond(ZoneOffset.of("+8"))).build();

        Position pos4 = Position.builder()
                .lat(106.519401f)
                .lon(29.612869f)
                .stayTimeTs(LocalDateTime.now().minusDays(1).minusHours(4).minusSeconds(23).toEpochSecond(ZoneOffset.of("+8"))).build();

        Position pos5 = Position.builder()
                .lat(106.514945f)
                .lon(29.633842f)
                .stayTimeTs(LocalDateTime.now().minusDays(2).minusHours(4).minusSeconds(23).toEpochSecond(ZoneOffset.of("+8"))).build();

        Position pos6 = Position.builder()
                .lat(106.499023f)
                .lon(29.621706f)
                .stayTimeTs(LocalDateTime.now().minusDays(4).minusHours(4).minusSeconds(23).toEpochSecond(ZoneOffset.of("+8"))).build();

        // 权重 位置信息
        // 建立一个索引：索引的key是权重，权重= sum(当前节点权重)
        // 找出权重综合最高的区域，并在这个区域寻找中心点
        List<Position> of = new LinkedList<>();
        of.add(pos1);
        of.add(pos2);
        of.add(pos3);
        of.add(pos4);
        of.add(pos5);
        of.add(pos6);
        PosFence.go(of);
        of.forEach(o ->{
            System.out.println(o.toString());
        });


    }
}
