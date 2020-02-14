package org.gallant.shortest.path;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.PriorityQueue;
import lombok.Builder;
import lombok.Data;

/**
 * @author 会灰翔的灰机
 * @date 2020/2/12
 */
public class DijkstraWithPriorityQueue {

    /**
     * 起点到终点的最短路径
     * start，end分别为点的下标
     */
    public static void shortestPath(List<List<Node>> graph, int start, int end) {
        List<Node> allPathStarts = graph.get(start);
        if (allPathStarts != null && allPathStarts.size() > 0) {
            int leastWeight = Integer.MAX_VALUE;
            for (int i = 0; i < allPathStarts.size(); i++) {
                List<Integer> currentPath = Lists.newArrayList(start);
                int pathLeastWeight = shortestPathRelaxation(graph, allPathStarts.get(i), end, currentPath);
                System.out.println(currentPath);
                if (pathLeastWeight < leastWeight) {
                    leastWeight = pathLeastWeight;
                }
            }
            System.out.println("leastWeight:"+leastWeight);
        }
    }

    public static int shortestPathRelaxation(List<List<Node>> graph, Node startNode, int end, List<Integer> currentPath) {
        int leastWeight = startNode.weight;
        currentPath.add(startNode.num);
        if (startNode.num == end) {
            return startNode.weight;
        }
        List<Node> allPathStarts = graph.get(startNode.num);
        if (allPathStarts != null && allPathStarts.size() > 0) {
            PriorityQueue<Node> priorityQueue = new PriorityQueue<>(allPathStarts);
            Node leastWeightNode = priorityQueue.poll();
            if (leastWeightNode != null) {
                leastWeight += shortestPathRelaxation(graph, leastWeightNode, end, currentPath);
            }
        }
        return leastWeight;
    }

    /**
     * 初始化构建图（邻接链表形式）
     */
    public static List<List<Node>> init(){
        List<List<Node>> originWeight = Lists.newArrayList();
        for (int i = 0; i < 7; i++) {
            originWeight.add(Lists.newArrayList());
        }
        originWeight.get(1).addAll(Lists.newArrayList(
                Node.builder().num(2).weight(7).build(), Node.builder().num(3).weight(9).build(), Node
                        .builder().num(6).weight(14).build()));
        originWeight.get(2).addAll(Lists.newArrayList(
                Node.builder().num(3).weight(10).build(), Node.builder().num(4).weight(15).build()));
        originWeight.get(3).addAll(Lists.newArrayList(
                Node.builder().num(6).weight(2).build(), Node.builder().num(4).weight(11).build()));
        originWeight.get(4).addAll(Lists.newArrayList(Node.builder().num(5).weight(6).build()));
        originWeight.get(6).addAll(Lists.newArrayList(Node.builder().num(5).weight(9).build()));
        return originWeight;
    }

    public static void main(String[] args) {
        List<List<Node>> listGraph = init();
        System.out.println(listGraph);
        shortestPath(listGraph, 1, 5);
    }

    @Data
    @Builder
    private static class Node implements Comparable<Node> {
        private Integer num;
        private Integer weight;

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }

}