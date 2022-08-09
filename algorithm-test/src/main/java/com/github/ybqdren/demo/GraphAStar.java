package com.github.ybqdren.demo;

import java.util.LinkedList;

/**
 * 启发式搜索算法（Heuristically Search Algorithm)
 * A*：可以更快速找到从起点到终点的路线，但是无法像Dijkstra算法那样，可以找到最短路线：
 * 两者的while循环终止条件不一样
 *
 *
 * @author zhao wen
 * @since 2022-08-09
 **/
public class GraphAStar {
    // 邻接表
    private LinkedList<Edge> adj[];
    // 顶点个数
    private int v;

    private Vertex[] vertexes;


    // 添加顶点的坐标
    public void addVetex(int id , int x , int y){
        vertexes[id] = new Vertex(id , x, y);
    }

    public GraphAStar(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        for(int i=0;i<v ;++i){
            this.adj[i] = new LinkedList<>();
        }

        this.vertexes = new Vertex[this.v];
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


    /*
    A* 算法和 Dijkstra 算法主要有三个区别：
        - 优先级队列构建的方式不同：
            A* 根据f值（f(i) = g(i)+h(i))来构建优先级队列；而Dijkstra 算法是根据dist值（g(i)）来优先构建优先级队列
        - A* 算法在更新顶点 dist 值时，会同步更新 f值
        - 循环结束的条件也不一样。Dijkstra 算法是在重点出队列的时才结束，A*算法是一旦遍历到终点就结束
     */
    private class Vertex{
        public int id; // 顶点编号ID
        public int dist; // 从起始顶点到这个顶点的距离，也就是g(i)
        public int f; // f(i) = g(i)+h(i)
        public int x,y; // 顶点在地图中的坐标 (x,y)


        public Vertex(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.f = Integer.MAX_VALUE;
            this.dist = Integer.MAX_VALUE;
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

        // todo
        public void clean(){}
    }

    // 计算曼哈顿距离（Manhattan Distance)：两点之间横纵坐标的距离之和
    // 计算的过程只涉及加减法、符号为反转，所以比欧几里得距离、更加高效（欧几里得距离计算会涉及开根号）
    // Vertex 表示顶点
    // 启发函数(heuristic function ) ， 把这个距离记作h(i)（i表示这个顶点的编号）
    int hManhattan(Vertex v1 , Vertex v2){
        return Math.abs(v1.x - v2.x) + Math.abs(v1.y - v2.y);
    }


    // 从顶点s到顶点t的路径
    public void astar(int s,int t){
        // 用来还原路径
        int[] predecessor = new int[this.v];
        PriorityQueue queue = new PriorityQueue(this.v);
        // 标记是否进入过队列
        boolean[] inqueue = new boolean[this.v];
        vertexes[s].dist = 0;
        vertexes[s].f = 0;
        queue.add(vertexes[s]);
        inqueue[s] = true;
        while (!queue.isEmpty()){
            // 取小顶堆 堆顶元素并删除
            Vertex minVertex = queue.poll();
            for(int i=0 ;i<adj[minVertex.id].size(); ++i){
                // 取出一条minVetex相连的边
                Edge e = adj[minVertex.id].get(i);
                // minVertex -> nextVertex
                Vertex nextVertex = vertexes[e.tid];
                // 更新next的dist.f
                if(minVertex.dist + e.w < nextVertex.dist){
                    nextVertex.dist = minVertex.dist + e.w;
                    nextVertex.f = nextVertex.dist + hManhattan(nextVertex , vertexes[t]);
                    predecessor[nextVertex.id] = minVertex.id;
                    if(inqueue[nextVertex.id] == true){
                        queue.update(nextVertex);
                    }else{
                        queue.add(nextVertex);
                        inqueue[nextVertex.id] = true;
                    }
                }

                // 只要达到t就可以结束循环
                if(nextVertex.id == t){
                    // 清空queue，才可以退出while循环
                    queue.clean();
                    break;
                }
            }
        }


        System.out.println(s);
        print(s , t , predecessor);
    }


    private void print(int s , int t,int[] predecessor){
        if(s == t) return;
        print(s , predecessor[t], predecessor );
        System.out.println("->" + t);
    }

}
