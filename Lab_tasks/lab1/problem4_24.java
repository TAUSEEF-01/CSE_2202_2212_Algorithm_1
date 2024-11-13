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
    ArrayList<Integer>[] adj, rev_adj;
    boolean[] vis_done;
    Stack<Integer> st;
    int[] ans;
    int cnt;

    public Graph(int n) {
        adj = new ArrayList[n + 1];
        rev_adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
            rev_adj[i] = new ArrayList<>();
        }
    }

    public static ArrayList<Integer>[] rsz(int m, ArrayList<Integer>[] adj, int n) {
        ArrayList<Integer>[] tempAdj = new ArrayList[m + 1];
        for (int i = 0; i <= n; i++) {
            tempAdj[i] = adj[i];
        }
        for (int i = n + 1; i <= m; i++) {
            tempAdj[i] = new ArrayList<>();
        }
        
        return tempAdj;
    }

    public void connect_nodes(int u, int v) {
        adj[u].add(v);
        rev_adj[v].add(u);
    }

    public void goAns(int n) {
        vis_done = new boolean[n + 1];
        st = new Stack<>();

        for (int i = 1; i <= n; i++) {
            if (!vis_done[i]) {
                dfsStk(i);
            }
        }

        vis_done = new boolean[n + 1];
        ans = new int[n + 1];
        cnt = 0;

        while (!st.isEmpty()) {
            int u = st.pop();
            if (!vis_done[u]) {
                cnt++;
                dfsAns(u, cnt);
            }
        }
    }

    private void dfsStk(int u) {
        vis_done[u] = true;
        for (int v : adj[u]) {
            if (!vis_done[v]) {
                dfsStk(v);
            }
        }
        st.push(u);
    }

    private void dfsAns(int u, int x) {
        vis_done[u] = true;
        ans[u] = x;
        for (int v : rev_adj[u]) {
            if (!vis_done[v]) {
                dfsAns(v, x);
            }
        }
    }

    public int getCnt() {
        return cnt;
    }

    public int[] getAns() {
        return ans;
    }
}

public class problem4_24 {
    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        // solve(sc);
        // sc.close();

        File myObj = new File("input4.txt");
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
        int n = sc.nextInt();
        int m = sc.nextInt();

        Graph graph = new Graph(n);

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.connect_nodes(a, b);
        }

        graph.goAns(n);

        System.out.println(graph.getCnt());
        int[] ans = graph.getAns();
        // for (int i = 1; i <= n; i++) {
        // System.out.print(ans[i] + " ");
        // }
        printAns(ans, n);
    }

    static void printAns(int[] ans, int n) {
        for (int i = 1; i <= n; i++) {
            System.out.print(ans[i] + " ");
        }
    }
}
