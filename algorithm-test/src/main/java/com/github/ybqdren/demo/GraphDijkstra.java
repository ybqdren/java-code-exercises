package com.github.ybqdren.demo;

import java.util.LinkedList;

/**
 * @author zhao wen
 * @since 2022-08-09
 **/
public class GraphDijkstra {
    // 邻接表
    private LinkedList<Edge> adj[];
    // 顶点个数
    private int v;


    public GraphDijkstra(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        for(int i=0;i<v ;++i){
            this.adj[i] = new LinkedList<>();
        }
    }

    // 添加一条边
    public void addEdge(int s , int t , int w){
        this.adj[s].add(new Edge(s , t ,s ));
    }

    private class Edge{
        // 边的起始顶点编号
        public int sid;
        // 边的终止顶点编号
        public int tid;
        // 权重
        public int w;

        public Edge(int sid, int tid, int w) {
            this.sid = sid;
            this.tid = tid;
            this.w = w;
        }
    }


    // dijkstra
    private class Vertex{
        public int id; // 顶点编号
        public int dist; // 从起始顶点到这个顶点的距离

        public Vertex(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }
    }

    // Java提供的优先级队列没有更新数据的接口
    // 根据 vertex.dist 构建小顶堆
    private class PriorityQueue{
        private Vertex[] nodes;
        private int count;

        public PriorityQueue(int count) {
            this.nodes = new Vertex[count+1];
            this.count = count;
        }

        // todo
        public Vertex poll(){
            return null;
        }

        // todo
        public void add(Vertex vertex){}

        // todo 更新结点的值，并且从下往上堆化，重新符合堆的定义。时间复杂度O(logn)
        public void update(Vertex vertex){

        }

        // todo
        public boolean isEmpty(){
            return true;
        }
    }

    // 从顶点s到顶点t的最短路径
    public void dijkstra(int s , int t){
        // 用来还原最短路径，记录每个顶点的前驱顶点
        int[] predecessor = new int[this.v];
        // 记录从起始顶点到每个顶点的距离(dist)
        Vertex[] vertices = new Vertex[this.v];
        for(int i=0;i<this.v;i++){
            vertices[i] = new Vertex(i , Integer.MAX_VALUE);
        }

        // 小顶堆
        PriorityQueue queue = new PriorityQueue(this.v);
        // 标记是否进入过队列，避免将一个顶点多次添加到优先级队列中
        boolean[] inqueue = new boolean[this.v];
        vertices[s].dist = 0;
        queue.add(vertices[s]);
        inqueue[s] = true;

        while (!queue.isEmpty()){
            // 取小顶堆 堆顶元素并删除
            Vertex minVertex = queue.poll();
            // 最短路径产生了
            if(minVertex.id == t) break;
            for(int i=0 ; i<adj[minVertex.id].size(); ++ i){
                Edge e = adj[minVertex.id].get(i); // 取出一条minVetex相连的边
                Vertex nextVertex = vertices[e.tid]; // minVertex --> nextVertex

                // 如果minVertex的dist值加上minVertex与nextVertex之间边的权重w小于nextVertex当前的dist值
                // 就意味着存在着一条更短的路径，它经过minVertex到达nextVertex
                if(minVertex.dist + e.w < nextVertex.dist){ // 更新next的dist
                    nextVertex.dist = minVertex.dist + e.w;
                    predecessor[nextVertex.id] = minVertex.id;
                    if(inqueue[nextVertex.id] == true){
                        // 更新优先级队列中的dist值
                        queue.update(nextVertex);
                    }else{
                        queue.add(nextVertex);
                        inqueue[nextVertex.id] = true;
                    }
                }
            }
        }

        // 输出最短路径
        System.out.println(s);

    }


    private void print(int s , int t,int[] predecessor){
        if(s == t) return;
        print(s , predecessor[t], predecessor );
        System.out.println("->" + t);
    }

}
