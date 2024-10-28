import java.io.*;
import java.util.*;

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

class Graph_edge {
    int u, v;

    Graph_edge(int u, int v) {
        this.u = u;
        this.v = v;
    }
}

public class problem2_24 {
    static boolean[] vis;
    static List<Integer>[] gt;

    static void dfs(int node) {
        vis[node] = true;
        for (int next : gt[node]) {
            if (!vis[next]) {
                dfs(next);
            }
        }
    }

    static boolean connectionCheck(int n) {
        vis = new boolean[n + 1];
        dfs(1);

        for (int i = 1; i <= n; i++) {
            if (!vis[i])
                return false;
        }

        List<Integer>[] reversedGraph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            reversedGraph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            for (int j : gt[i]) {
                reversedGraph[j].add(i);
            }
        }

        vis = new boolean[n + 1];
        gt = reversedGraph;
        dfs(1);

        for (int i = 1; i <= n; i++) {
            if (!vis[i])
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        // solve(sc);
        // sc.close();

        File myObj = new File("input2.txt");
        Scanner sc;
        try {
            sc = new Scanner(myObj);
            solve(sc);
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void solve(Scanner sc) {
        int n = sc.nextInt();

        Graph_edge[] adj = new Graph_edge[n];
        int[] cost = new int[n];
        for (int i = 0; i < n; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            cost[i] = sc.nextInt();
            adj[i] = new Graph_edge(u, v);
        }

        int mini = Integer.MAX_VALUE;
        int sz = (1 << n);
        for (int k = 0; k < sz; k++) {
            gt = new ArrayList[n + 1];
            for (int i = 0; i <= n; i++) {
                gt[i] = new ArrayList<>();
            }

            int curr = 0;
            for (int i = 0; i < n; i++) {
                if ((k & (1 << i)) == 0) {
                    gt[adj[i].u].add(adj[i].v);
                } else {
                    gt[adj[i].v].add(adj[i].u);
                    curr += cost[i];
                }
            }

            if (connectionCheck(n)) {
                mini = Math.min(mini, curr);
            }
        }

        System.out.println(mini);
    }
}
