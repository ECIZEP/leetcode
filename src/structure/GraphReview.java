package structure;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class GraphReview {

    public static void main (String args[]) {

        Scanner sc = new Scanner(System.in);
        int vertexNum = sc.nextInt();
        int edgeNum = sc.nextInt();
        String[] vertexName = new String[vertexNum];
        for (int i = 0; i < vertexNum; i++) {
            vertexName[i] = new String(String.valueOf(i+1));
        }

        MGraph graph = new MGraph(vertexName,edgeNum);
        for (int i = 0;i < edgeNum;i++) {
            int start, end, value;
            start = sc.nextInt();
            end = sc.nextInt();
            value = sc.nextInt();
            graph.setEdge(start, end, value);
        }
        graph.DFS(0);


        ArrayList<ArrayList> allPath = graph.getAllPath(0, vertexNum-1);
        for (int i = 0; i < allPath.size(); i++) {
            ArrayList singPath = allPath.get(i);
            for (int j = 0; j < singPath.size(); j++) {
                System.out.print(singPath.get(j));
            }
            System.out.println("");
        }
    }
}

class MGraph {
    private String[] vertexName = null;
    private int[][] edges = null;
    private int edgeNum = 0;

    public MGraph (String[] vertexName, int edgeNum) {
        this.edges = new int[vertexName.length][vertexName.length];
        this.edgeNum = edgeNum;
        this.vertexName = vertexName;
        for (int i = 0;i < vertexName.length;i++) {
            for (int j = 0;j < vertexName.length;j++){
                if(i != j){
                    this.edges[i][j] = -1;
                } else {
                    this.edges[i][j] = 0;
                }

            }
        }
    }

    public void setEdge (int i, int j, int value) {
        this.edges[i-1][j-1] = value;
        this.edges[j-1][i-1] = value;
    }


    public void DFS (int startIndex) {
        boolean[] visited = new boolean[this.vertexName.length];
        Stack stack = new Stack();
        stack.push(startIndex);
        visited[startIndex] = true;
        System.out.print(this.vertexName[startIndex]);
        while (!stack.isEmpty()) {
            int currentIndex = (int)stack.peek();
            int i = 0;
            for (i = 0; i < this.vertexName.length; i++) {
                if (!visited[i] && this.edges[currentIndex][i] > 0) {
                    System.out.print(this.vertexName[i]);
                    stack.push(i);
                    visited[i] = true;
                    break;
                } else {
                    continue;
                }
            }
            if (i == this.vertexName.length) {
                stack.pop();
            }
        }

    }

    public void printGraph () {
        for (int i = 0;i < this.vertexName.length;i++) {
            for (int j = 0;j < this.vertexName.length;j++){
                System.out.printf("%2d ", this.edges[i][j]);
            }
            System.out.println("");
        }
    }

    /*
    * get the shortest distance and between two node
    *
    * */
    public void floy () {
        int length = this.vertexName.length;
        int[][] path = new int[this.vertexName.length][this.vertexName.length];
        int[][] dist = new int[this.vertexName.length][this.vertexName.length];

        // 初始化
        for (int i = 0; i < this.vertexName.length; i++) {
            for (int j = 0; j < this.vertexName.length; j++) {
                dist[i][j] = this.edges[i][j];    // "顶点i"到"顶点j"的路径长度为"i到j的权值"。
            }
        }
        // 计算最短路径
        for (int k = 0; k < this.vertexName.length; k++) {
            for (int i = 0; i <  this.vertexName.length; i++) {
                for (int j = 0; j < this.vertexName.length; j++) {
                    // 如果经过下标为k顶点路径比原两点间路径更短，则更新dist[i][j]和path[i][j]
                    int tmp = (dist[i][k]== -1 || dist[k][j]== -1) ? -1 : (dist[i][k] + dist[k][j]);
                    if (dist[i][j] == -1 || (dist[i][j] > tmp && tmp != -1)) {
                        // "i到j最短路径"对应的值设，为更小的一个(即经过k)
                        dist[i][j] = tmp;
                    }
                }
            }
        }

        // 打印floyd最短路径的结果
        System.out.printf("floyd: \n");
        for (int i = 0; i <  this.vertexName.length; i++) {
            for (int j = 0; j <  this.vertexName.length; j++)
                System.out.printf("%2d  ", dist[i][j]);
            System.out.printf("\n");
        }

    }


    /*
    *  get all path between two node
    * */

    public ArrayList<ArrayList> getAllPath (int start, int end) {
        ArrayList path = null;
        ArrayList<ArrayList> allPath = new ArrayList<ArrayList>();
        Stack stack = new Stack();
        stack.push(start);
        // 记录是否访问过visited[i][j]边
        int[][] visited = new int[this.vertexName.length][this.vertexName.length];
        while (!stack.isEmpty()) {
            int index = (int)stack.peek();
            int i = 0;
            for (i = 0;i < this.vertexName.length;i++) {
                if (i == start || index == i) continue;
                if (this.edges[index][i] != -1 && visited[index][i] != 1) {
                    visited[index][i] = visited[i][index] = 1;
                    stack.push(i);
                    // 找到一条start通往end的路径
                    if (i == end) {
                        allPath.add(new ArrayList(stack));
                        stack.pop();
                        stack.pop();
                    }
                    break;
                }
            }
            if (i == this.vertexName.length) {
                stack.pop();
            }
        }
        return allPath;
    }

    /*
    *
    *
    * */

    public int getPath (int start, int end) {
        Stack stack = new Stack();
        stack.push(start);
        // 记录是否访问过visited[i][j]边
        int[][] visited = new int[this.vertexName.length][this.vertexName.length];
        int best = -1;
        int temp = -1;
        while(!stack.isEmpty()){
            // isAccess: true,当前点还有边没有访问 false:当前点的边全部访问完了
            boolean isAccess = false;
            for (int i = 0;i < this.vertexName.length;i++){
                // 获取栈顶部的点，继续寻路
                int index = (int)stack.peek();
                if(i == start || index == i) continue;
                if(this.edges[index][i] != -1 && visited[index][i] != 1){
                    // 找到可访问的边
                    isAccess = true;
                    visited[index][i] = visited[i][index] = 1;
                    stack.push(i);
                    if(this.edges[index][i] > temp) {
                        temp = this.edges[index][i];
                    }
                    // 找到一条start通往end的路径
                    if(i == end && (temp < best || best == -1)){
                        stack.pop();
                        stack.pop();
                        best = temp;
                        temp = -1;
                    }
                    break;
                }
            }
            if(!isAccess) {
                stack.pop();
            }
        }
        return best;
    }

}