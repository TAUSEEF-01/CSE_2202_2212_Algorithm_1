// import java.util.*;

// public class problem4_24 {
//     static List<List<Integer>> adj;
//     static List<List<Integer>> reversedGraph;
//     static boolean[] vis_done;
//     static List<Integer> finishOrder;
//     static int[] kingdomLabels;

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);

//         // Read input
//         int n = sc.nextInt();  // number of planets
//         int m = sc.nextInt();  // number of teleporters

//         // Initialize adjs
//         adj = new ArrayList<>();
//         reversedGraph = new ArrayList<>();
//         for (int i = 0; i < n; i++) {
//             adj.add(new ArrayList<>());
//             reversedGraph.add(new ArrayList<>());
//         }

//         // Read teleporters and build both adjs
//         for (int i = 0; i < m; i++) {
//             int a = sc.nextInt() - 1;  // Convert to 0-based indexing
//             int b = sc.nextInt() - 1;
//             adj.get(a).add(b);
//             reversedGraph.get(b).add(a);
//         }

//         // Initialize data structures
//         vis_done = new boolean[n];
//         finishOrder = new ArrayList<>();
//         kingdomLabels = new int[n];

//         // First DFS to get finish order
//         for (int i = 0; i < n; i++) {
//             if (!vis_done[i]) {
//                 dfsStk(i);
//             }
//         }

//         // Reset vis_done array for second DFS
//         Arrays.fill(vis_done, false);

//         // Second DFS to find SCCs (kingdoms)
//         int currentKingdom = 0;
//         for (int i = finishOrder.size() - 1; i >= 0; i--) {
//             int node = finishOrder.get(i);
//             if (!vis_done[node]) {
//                 currentKingdom++;
//                 dfsAns(node, currentKingdom);
//             }
//         }

//         // Print output
//         System.out.println(currentKingdom);
//         for (int i = 0; i < n; i++) {
//             System.out.print(kingdomLabels[i] + " ");
//         }

//         sc.close();
//     }

//     // First DFS to get finish order
//     static void dfsStk(int node) {
//         vis_done[node] = true;
//         for (int neighbor : adj.get(node)) {
//             if (!vis_done[neighbor]) {
//                 dfsStk(neighbor);
//             }
//         }
//         finishOrder.add(node);
//     }

//     // Second DFS to find SCCs
//     static void dfsAns(int node, int label) {
//         vis_done[node] = true;
//         kingdomLabels[node] = label;
//         for (int neighbor : reversedGraph.get(node)) {
//             if (!vis_done[neighbor]) {
//                 dfsAns(neighbor, label);
//             }
//         }
//     }
// }

////////////////////////////////////////////////////////////////////////////////

// import java.util.*;
// import java.io.*;

// class Graph {
//     public static ArrayList<Integer>[] rsz(int m, ArrayList<Integer>[] adj, int n) {
//         ArrayList<Integer>[] tempAdj = new ArrayList[m + 1];
//         for (int i = 0; i <= n; i++) {
//             tempAdj[i] = adj[i];
//         }
//         for (int i = n + 1; i <= m; i++) {
//             tempAdj[i] = new ArrayList<>();
//         }
//         return tempAdj;
//     }

//     public static void connect_nodes(int u, int v, ArrayList<Integer>[] adj) {
//         adj[u].add(v);
//     }
// }

// public class problem4_24 {
//     static ArrayList<Integer>[] adj, rev_adj;
//     static boolean[] vis_done;
//     static Stack<Integer> st;
//     static int[] ans;
//     static int cnt;

//     public static void main(String[] args) {
// File myObj = new File("input4.txt");
// Scanner sc;
// try {
// sc = new Scanner(myObj);
// solve(sc);
// sc.close();
// } catch (FileNotFoundException e) {
// e.printStackTrace();
// }

//         Scanner sc = new Scanner(System.in);
//         solve(sc);
//     }

//     static void solve(Scanner sc) {
//         int n = sc.nextInt();
//         int m = sc.nextInt();

//         adj = new ArrayList[n + 1];
//         rev_adj = new ArrayList[n + 1];
//         for (int i = 0; i <= n; i++) {
//             adj[i] = new ArrayList<>();
//             rev_adj[i] = new ArrayList<>();
//         }

//         for (int i = 0; i < m; i++) {
//             int a = sc.nextInt();
//             int b = sc.nextInt();
//             Graph.connect_nodes(a, b, adj);
//             Graph.connect_nodes(b, a, rev_adj);
//         }

//         goAns(n);

//         System.out.println(cnt);
//         for (int i = 1; i <= n; i++) {
//             System.out.print(ans[i] + " ");
//         }
//     }

//     static void goAns(int n) {
//         vis_done = new boolean[n + 1];
//         st = new Stack<>();

//         for (int i = 1; i <= n; i++) {
//             if (!vis_done[i]) {
//                 dfsStk(i);
//             }
//         }

//         vis_done = new boolean[n + 1];
//         ans = new int[n + 1];
//         cnt = 0;

//         while (!st.isEmpty()) {
//             int u = st.pop();
//             if (!vis_done[u]) {
//                 cnt++;
//                 dfsAns(u, cnt);
//             }
//         }
//     }

//     static void dfsStk(int u) {
//         vis_done[u] = true;
//         for (int v : adj[u]) {
//             if (!vis_done[v]) {
//                 dfsStk(v);
//             }
//         }
//         st.push(u);
//     }

//     static void dfsAns(int u, int x) {
//         vis_done[u] = true;
//         ans[u] = x;
//         for (int v : rev_adj[u]) {
//             if (!vis_done[v]) {
//                 dfsAns(v, x);
//             }
//         }
//     }
// }

import java.util.*;
import java.io.*;

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
        for (int i = 1; i <= n; i++) {
            System.out.print(ans[i] + " ");
        }
    }
}

///////////////////////////////////////////

// import java.io.*;
// import java.util.*;

// public class problem4_24 {
// static ArrayList<Integer>[] adj, rev_adj;
// static boolean[] vis_done;
// static Stack<Integer> st;
// static int[] ans;
// static int cnt;

// public static void main(String[] args) throws IOException {
// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// String[] firstLine = br.readLine().split(" ");
// int n = Integer.parseInt(firstLine[0]);
// int m = Integer.parseInt(firstLine[1]);

// // Initialize adj and reverse adj
// adj = new ArrayList[n + 1];
// rev_adj = new ArrayList[n + 1];
// for (int i = 1; i <= n; i++) {
// adj[i] = new ArrayList<>();
// rev_adj[i] = new ArrayList<>();
// }

// // Read edges
// for (int i = 0; i < m; i++) {
// String[] edge = br.readLine().split(" ");
// int a = Integer.parseInt(edge[0]);
// int b = Integer.parseInt(edge[1]);
// adj[a].add(b);
// rev_adj[b].add(a); // Reverse adj for Kosaraju's algorithm
// }

// // Step 1: Order nodes by finish time in the original adj
// vis_done = new boolean[n + 1];
// st = new Stack<>();
// for (int i = 1; i <= n; i++) {
// if (!vis_done[i]) {
// dfsStk(i);
// }
// }

// // Step 2: Process nodes in reverse finish order on the transpose adj
// vis_done = new boolean[n + 1];
// ans = new int[n + 1];
// cnt = 0;

// while (!st.isEmpty()) {
// int node = st.pop();
// if (!vis_done[node]) {
// cnt++;
// dfsAns(node, cnt);
// }
// }

// // Output the number of kingdoms and the ans of each planet
// StringBuilder sb = new StringBuilder();
// sb.append(cnt).append("\n");
// for (int i = 1; i <= n; i++) {
// sb.append(ans[i]).append(" ");
// }
// System.out.println(sb);
// }

// // First DFS to fill st with finish times
// static void dfsStk(int node) {
// vis_done[node] = true;
// for (int neighbor : adj[node]) {
// if (!vis_done[neighbor]) {
// dfsStk(neighbor);
// }
// }
// st.push(node);
// }

// // Second DFS on the reversed adj to assign anss
// static void dfsAns(int node, int compID) {
// vis_done[node] = true;
// ans[node] = compID;
// for (int neighbor : rev_adj[node]) {
// if (!vis_done[neighbor]) {
// dfsAns(neighbor, compID);
// }
// }
// }
// }
