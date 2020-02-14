package org.gallant.shortest.path;

import java.util.Random;

/**
 * @author 会灰翔的灰机
 * @date 2020/2/10
 */
public class FloydWarshall {

    public static void shortestPath(int[][] originWeight) {
        int[][] shortestWeight = new int[originWeight.length][originWeight.length];
        for (int i = 0; i < originWeight.length; i++) {
            for (int j = 0; j < originWeight.length; j++) {
                // 如果不经过k点的最短路径
                shortestWeight[i][j] = originWeight[i][j];
                for (int k = 0; k < originWeight.length; k++) {
                    int edgeWeightStartMiddle = originWeight[i][k];
                    int edgeWeightMiddleEnd = originWeight[k][j];
                    // 发现经过k点的最短路径比不经过k点的最短路径更短。更新最短路径
                    if (shortestWeight[i][j] > edgeWeightStartMiddle + edgeWeightMiddleEnd) {
                        shortestWeight[i][j] = edgeWeightStartMiddle + edgeWeightMiddleEnd;
                        System.out.print(String.format("点%s与点%s之间最小权重路径插入点%s;", i, j, k));
                    }
                }
                System.out.println(String.format("点%s与点%s之间最小权重为：%s", i, j, shortestWeight[i][j]));
            }
        }
    }

    /**
     * 初始化构建图（邻接矩阵形式）
     * @param size : 矩阵大小
     */
    public static int[][] init(int size){
        int[][] originWeight = new int[size][size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // 点i至点j这条边的权重（路径长度）
                originWeight[i][j] = random.nextInt(size * size);
            }
        }
        return originWeight;
    }

    public static void main(String[] args) {
        int[][] matrixGraph = init(4);
        PrintUtil.print2DimensionArray(matrixGraph);
        shortestPath(matrixGraph);
    }

}
