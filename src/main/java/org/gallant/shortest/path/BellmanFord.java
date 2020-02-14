package org.gallant.shortest.path;

import com.google.common.collect.Lists;
import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * @author 会灰翔的灰机
 * @date 2020/2/12
 */
public class BellmanFord {

    /**
     * 起点到终点的最短路径
     * start，end分别为点的下标
     * 目前是一个顶点至另一个顶点的场景，复杂度为1*边数，计算所有顶点复杂度则为：（顶点数-1）*边数
     */
    public static void shortestPath(List<List<Node>> graph, int start, int end, int currentWeight, List<Integer> currentPath) {
        currentPath.add(start);
        if (start == end) {
            System.out.println(currentPath+"="+currentWeight);
        }
        // 取出顶点的所有边
        List<Node> allPathStarts = graph.get(start);
        if (allPathStarts != null && allPathStarts.size() > 0) {
            // 遍历每个顶点计算所有可能路径的最小代价
            for (int i = 0; i < allPathStarts.size(); i++) {
                shortestPath(graph, allPathStarts.get(i).num, end, allPathStarts.get(i).weight + currentWeight, Lists.newArrayList(currentPath));
            }
        }
    }

    /**
     * 初始化构建图（邻接链表形式）
     */
    public static List<List<Node>> init(){
        List<List<Node>> originWeight = Lists.newArrayList();
        for (int i = 0; i < 7; i++) {
            originWeight.add(Lists.newArrayList());
        }
        originWeight.get(1).addAll(Lists.newArrayList(Node.builder().num(2).weight(7).build(), Node.builder().num(3).weight(9).build(), Node.builder().num(6).weight(14).build()));
        originWeight.get(2).addAll(Lists.newArrayList(Node.builder().num(3).weight(10).build(), Node.builder().num(4).weight(15).build()));
        originWeight.get(3).addAll(Lists.newArrayList(Node.builder().num(6).weight(2).build(), Node.builder().num(4).weight(11).build()));
        originWeight.get(4).addAll(Lists.newArrayList(Node.builder().num(5).weight(6).build()));
        originWeight.get(6).addAll(Lists.newArrayList(Node.builder().num(5).weight(9).build()));
        return originWeight;
    }

    public static void main(String[] args) {
        List<List<Node>> listGraph = init();
        System.out.println(listGraph);
        shortestPath(listGraph, 1, 5, 0, Lists.newArrayList());
    }

    @Data
    @Builder
    private static class Node {
        private Integer num;
        private Integer weight;
    }

}