package org.gallant.shortest.path;

/**
 * @author kongyong
 * @date 2020/2/12
 */
public class PrintUtil {

    public static void printArray(Object[] objects) {
        System.out.print("[");
        for (Object object : objects) {
            System.out.print(object.toString()+",");
        }
        System.out.println("]");
    }
    public static void print2DimensionArray(int[][] objects) {
        System.out.print("[");
        for (int i = 0; i < objects.length; i++) {
            int[] objArray = objects[i];
            for (int j = 0; j < objArray.length; j++) {
                System.out.print(String.format("(%s-%s:%s),", i, j, objArray[j]));
            }
        }
        System.out.println("]");
    }
}
