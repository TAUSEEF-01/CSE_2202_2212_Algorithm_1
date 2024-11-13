import java.util.*;
import java.io.*;

// class MyGraph {
//     private ArrayList<Integer>[] adj;
//     private boolean[] vis;
//     private int n;

//     public Graph(int n) {
//         this.n = n;
//         adj = new ArrayList[n + 1];
//         for (int i = 0; i <= n; i++) {
//             adj[i] = new ArrayList<Integer>();
//         }
//         vis = new boolean[n + 1];
//     }

//     public void edgeAdd(int u, int v) {
//         adj[u].add(v);
//     }

//     public int numberOfVertices(int u) {
//         return adj[u].size();
//     }

//     public void getAdjacentVerticesOfNode(int u) {
//         for (var v : adj[u]) {
//             System.out.print(v + " ");
//         }
//         System.out.println();
//     }

//     public void bfs(int s) {
//         Arrays.fill(vis, false);
//         Queue<Integer> q = new LinkedList<>();
//         q.add(s);
//         vis[s] = true;

//         while (!q.isEmpty()) {
//             int u = q.remove();
//             System.out.print(u + " ");
//             for (var v : adj[u]) {
//                 if (!vis[v]) {
//                     vis[v] = true;
//                     q.add(v);
//                 }
//             }
//         }
//     }

//     public void dfs(int u) {
//         Arrays.fill(vis, false);
//         dfsHelper(u);
//     }

//     private void dfsHelper(int u) {
//         vis[u] = true;
//         System.out.print(u + " ");
//         for (var v : adj[u]) {
//             if (!vis[v]) {
//                 dfsHelper(v);
//             }
//         }
//     }

//     private void topologicalSortProcess(int v, Stack<Integer> stack) {
//         vis[v] = true;

//         for (int i : adj[v]) {
//             if (!vis[i]) {
//                 topologicalSortProcess(i, stack);
//             }
//         }

//         stack.push(v);
//     }

//     public void topologicalSort() {
//         Stack<Integer> stack = new Stack<>();
//         Arrays.fill(vis, false);

//         for (int i = 0; i < n; i++) {
//             if (!vis[i]) {
//                 topologicalSortProcess(i, stack);
//             }
//         }

//         // System.out.print("Topological Sort: ");
//         while (!stack.empty()) {
//             System.out.print(stack.pop() + " ");
//         }
//         System.out.println();
//     }

//     public void display() {
//         for (int i = 1; i <= n; i++) {
//             System.out.print(i + " -> ");
//             for (var j : adj[i]) {
//                 System.out.print(j + " ");
//             }
//             System.out.println();
//         }
//     }

//     public void displayGraph() {
//         for (int i = 0; i < n; i++) {
//             System.out.print(i + " -> ");
//             for (var j : adj[i]) {
//                 System.out.print(j + " ");
//             }
//             System.out.println();
//         }
//     }

//     /* strongly connected components: */
//     void getVerticesOrder(int u, boolean visV[], Stack<Integer> stack) {
//         visV[u] = true;

//         for (var v : adj[u]) {
//             if (!visV[v])
//                 getVerticesOrder(v, visV, stack);
//         }

//         stack.push(u);
//     }

//     Graph graphTranspose() {
//         Graph g = new Graph(n);

//         for (int i = 0; i <= n; i++) {
//             for (var j : adj[i]) {
//                 g.edgeAdd(j, i);
//             }
//         }
//         return g;
//     }

//     void dfsSCC(int i, boolean visV[]) {
//         visV[i] = true;

//         System.out.print(i + " ");
//         for (var j : adj[i]) {
//             if (visV[j] == false)
//                 dfsSCC(j, visV);
//         }
//     }

//     void printStronglyConnectedComponent() {
//         Stack<Integer> stack = new Stack<Integer>();

//         boolean visV[] = new boolean[n + 1];
//         for (int i = 0; i <= n; i++) {
//             visV[i] = false;
//         }

//         for (int i = 0; i <= n; i++) {
//             if (visV[i] == false)
//                 getVerticesOrder(i, visV, stack);
//         }

//         Graph g = graphTranspose();
//         // g.displayGraph();

//         for (int i = 0; i <= n; i++) {
//             visV[i] = false;
//         }

//         while (!stack.empty()) {
//             int s = (int) stack.pop();

//             if (!visV[s]) {
//                 g.dfsSCC(s, visV);
//                 System.out.println();
//             }
//         }
//     }

// }

class Graph {
    private int[][] adjMat;
    private int r;
    private int c;

    public Graph(int r, int c) {
        this.r = r;
        this.c = c;
        adjMat = new int[r][c];
    }

    public void valueSet(int i, int j, int val) {
        adjMat[i][j] = val;
    }

    public int getAns() {
        if (r == 0 || c == 0 || adjMat == null)
            return 0;

        int ans = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (adjMat[i][j] == 1) {
                    ans += 4;

                    if (j > 0 && adjMat[i][j - 1] == 1) {
                        ans -= 2;
                    }
                    if (i > 0 && adjMat[i - 1][j] == 1) {
                        ans -= 2;
                    }
                }
            }
        }

        return ans;
    }
}

public class problem3_24 {
    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        // solve(sc);

        File myObj = new File("input3.txt");
        Scanner sc;
        try {
            sc = new Scanner(myObj);
            solve(sc);
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void solve(Scanner sc) {
        int r = sc.nextInt();
        int c = sc.nextInt();
        sc.nextLine();

        Graph g = new Graph(r, c);

        for (int i = 0; i < r; i++) {
            String[] str = sc.nextLine().split(",");
            for (int j = 0; j < c; j++) {
                g.valueSet(i, j, Integer.parseInt(str[j]));
            }
        }

        System.out.println(g.getAns());
    }
}