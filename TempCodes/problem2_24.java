import java.io.*;
import java.util.*;


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
            if (!vis[i]) return false;
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
            if (!vis[i]) return false;
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







// import java.util.*;

// public class problem2_24 {
//     static class Graph_edge {
//         int from, to;
//         Graph_edge(int from, int to) {
//             this.from = from;
//             this.to = to;
//         }
//     }
    
//     static boolean[] vis;
//     static List<Integer>[] graph;
    
//     static void dfs(int node) {
//         vis[node] = true;
//         for (int next : graph[node]) {
//             if (!vis[next]) {
//                 dfs(next);
//             }
//         }
//     }
    
//     static boolean connectionCheck(int n) {
//         // Check reachability from node 1
//         Arrays.fill(vis, false);
//         dfs(1);
        
//         for (int i = 1; i <= n; i++) {
//             if (!vis[i]) return false;
//         }
        
//         // Create reversed graph - reuse the array
//         List<Integer>[] reversedGraph = new ArrayList[n + 1];
//         for (int i = 0; i <= n; i++) {
//             reversedGraph[i] = new ArrayList<>();
//         }
        
//         for (int i = 1; i <= n; i++) {
//             for (int j : graph[i]) {
//                 reversedGraph[j].add(i);
//             }
//         }
        
//         Arrays.fill(vis, false);
//         graph = reversedGraph;
//         dfs(1);
        
//         for (int i = 1; i <= n; i++) {
//             if (!vis[i]) return false;
//         }
        
//         return true;
//     }

//     public static void solve(Scanner sc) {
//         int n = sc.nextInt();
        
//         Graph_edge[] adj = new Graph_edge[n];
//         int[] cost = new int[n];
//         int totalCost = 0;
        
//         for (int i = 0; i < n; i++) {
//             int from = sc.nextInt();
//             int to = sc.nextInt();
//             cost[i] = sc.nextInt();
//             totalCost += cost[i];
//             adj[i] = new Graph_edge(from, to);
//         }

//         // Initialize graph once
//         graph = new ArrayList[n + 1];
//         for (int i = 0; i <= n; i++) {
//             graph[i] = new ArrayList<>(n);  // Pre-size ArrayList
//         }
        
//         vis = new boolean[n + 1];  // Initialize vis array once
//         int mini = totalCost;  // Start with worst case
        
//         // Try all combinations using bit manipulation
//         int sz = 1 << n;
//         for (int k = 0; k < sz; k++) {
//             // Clear previous graph state
//             for (int i = 0; i <= n; i++) {
//                 graph[i].clear();
//             }
            
//             // Calculate cost and build graph
//             int curr = Integer.bitCount(k) == 0 ? 0 : 
//                             Integer.bitCount(k) * cost[0];  // Optimization for identical costs
            
//             // Build graph based on k
//             int m = k;
//             for (int i = 0; i < n; i++) {
//                 if ((m & 1) == 0) {
//                     graph[adj[i].from].add(adj[i].to);
//                 } else {
//                     graph[adj[i].to].add(adj[i].from);
//                     if (cost[i] != cost[0]) {  // Only calculate if costs differ
//                         curr = curr - cost[0] + cost[i];
//                     }
//                 }
//                 m >>= 1;
//             }
            
//             // Early pruning - skip if current cost already exceeds mini
//             if (curr >= mini) continue;
            
//             // Check connectivity only if potential better solution
//             if (connectionCheck(n)) {
//                 mini = Math.min(mini, curr);
//             }
//         }
        
//         System.out.println(mini);
//     }
    
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         solve(sc);
//         sc.close();
//     }
// }








// import java.util.*;

// public class problem2_24 {
//     static class Graph_edge {
//         int u, v, cost;
//         boolean used;
        
//         Graph_edge(int u, int v, int cost) {
//             this.u = u;
//             this.v = v;
//             this.cost = cost;
//             this.used = false;
//         }
//     }
    
//     static List<Integer>[] graph;
//     static List<Integer>[] reverseGraph;
//     static boolean[] vis;
//     static List<Integer> order;
//     static int[] component;
//     static int componentCount;
    
//     // First DFS for Kosaraju's algorithm
//     static void dfs1(int v) {
//         vis[v] = true;
//         for (int u : graph[v]) {
//             if (!vis[u]) {
//                 dfs1(u);
//             }
//         }
//         order.add(v);
//     }
    
//     // Second DFS for Kosaraju's algorithm
//     static void dfs2(int v) {
//         vis[v] = true;
//         component[v] = componentCount;
//         for (int u : reverseGraph[v]) {
//             if (!vis[u]) {
//                 dfs2(u);
//             }
//         }
//     }
    
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         int n = scanner.nextInt();
        
//         // Initialize graphs
//         graph = new ArrayList[n + 1];
//         reverseGraph = new ArrayList[n + 1];
//         for (int i = 0; i <= n; i++) {
//             graph[i] = new ArrayList<>();
//             reverseGraph[i] = new ArrayList<>();
//         }
        
//         // Read Graph_edges and build graphs
//         Graph_edge[] Graph_edges = new Graph_edge[n];
//         for (int i = 0; i < n; i++) {
//             int u = scanner.nextInt();
//             int v = scanner.nextInt();
//             int cost = scanner.nextInt();
//             Graph_edges[i] = new Graph_edge(u, v, cost);
//             graph[u].add(v);
//             reverseGraph[v].add(u);
//         }
        
//         // Find Strongly Connected Components using Kosaraju's algorithm
//         vis = new boolean[n + 1];
//         order = new ArrayList<>();
        
//         // First DFS
//         for (int i = 1; i <= n; i++) {
//             if (!vis[i]) {
//                 dfs1(i);
//             }
//         }
        
//         // Second DFS
//         vis = new boolean[n + 1];
//         component = new int[n + 1];
//         componentCount = 0;
        
//         for (int i = order.size() - 1; i >= 0; i--) {
//             int v = order.get(i);
//             if (!vis[v]) {
//                 dfs2(v);
//                 componentCount++;
//             }
//         }
        
//         // If there's only one component, no changes needed
//         if (componentCount == 1) {
//             System.out.println(0);
//             return;
//         }
        
//         // Create component graph
//         List<Graph_edge>[] componentGraph_edges = new ArrayList[componentCount];
//         for (int i = 0; i < componentCount; i++) {
//             componentGraph_edges[i] = new ArrayList<>();
//         }
        
//         // Find all Graph_edges between components
//         for (Graph_edge Graph_edge : Graph_edges) {
//             int uComponent = component[Graph_edge.u];
//             int vComponent = component[Graph_edge.v];
//             if (uComponent != vComponent) {
//                 componentGraph_edges[uComponent].add(Graph_edge);
//             }
//         }
        
//         // Find minimum cost Graph_edges v connect components
//         int vtalCost = 0;
//         boolean[] connectedComponents = new boolean[componentCount];
//         connectedComponents[0] = true;
//         PriorityQueue<Graph_edge> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        
//         // Add all Graph_edges u first component
//         for (Graph_edge Graph_edge : componentGraph_edges[0]) {
//             pq.offer(Graph_edge);
//         }
        
//         // Process Graph_edges until all components are connected
//         while (!pq.isEmpty() && componentCount > 1) {
//             Graph_edge Graph_edge = pq.poll();
//             int uComp = component[Graph_edge.u];
//             int vComp = component[Graph_edge.v];
            
//             if (!connectedComponents[vComp]) {
//                 connectedComponents[vComp] = true;
//                 vtalCost += Graph_edge.cost;
//                 componentCount--;
                
//                 // Add all Graph_edges u newly connected component
//                 for (Graph_edge nextGraph_edge : componentGraph_edges[vComp]) {
//                     if (!connectedComponents[component[nextGraph_edge.v]]) {
//                         pq.offer(nextGraph_edge);
//                     }
//                 }
//             }
//         }
        
//         System.out.println(vtalCost);
//         scanner.close();
//     }
// }













// import java.io.*;
// import java.util.*;


// public class problem2_24 {
//     static final long MOD = 1_000_000_007;
//     static final long INF = Long.MAX_VALUE;
//     static final double PI = Math.acos(-1);
//     static final int DFS_LIMIT = 100_010;

//     static ArrayList<Integer>[] g = new ArrayList[DFS_LIMIT];
//     static ArrayList<Integer>[] gp = new ArrayList[DFS_LIMIT];
//     static boolean[] vis = new boolean[DFS_LIMIT];
//     static boolean[] vis2 = new boolean[DFS_LIMIT];
//     static long mini;

//     public static void dfsSt(int u, Stack<Integer> st) {
//         vis[u] = true;
//         for (int v : g[u]) {
//             if (!vis[v]) {
//                 dfsSt(v, st);
//             }
//         }
//         st.push(u);
//     }

//     public static void dfs(int u, Map<Integer, Integer> mp, int[] a) {
//         vis2[u] = true;
//         mp.put(a[u], mp.gevrDefault(a[u], 0) + 1);
//         mini = Math.min(mini, a[u]);
//         for (int v : gp[u]) {
//             if (!vis2[v]) {
//                 dfs(v, mp, a);
//             }
//         }
//     }

//     public static void solve(sc sc) {
//         int n = sc.nextInt();
//         int[] a = new int[n];

//         Stack<Integer> st = new Stack<>();

//         for (int i = 0; i < n; i++) {
//             g[i] = new ArrayList<>();
//             gp[i] = new ArrayList<>();
//         }

//         for (int i = 0; i < n; i++) {
//             int u = sc.nextInt() - 1;
//             int v = sc.nextInt() - 1;
//             a[i] = sc.nextInt();
//             g[u].add(v);
//             gp[v].add(u);
//         }

//         for (int i = 0; i < n; i++) {
//             if (!vis[i]) {
//                 dfsSt(i, st);
//             }
//         }

//         long ans = 1, cost = 0;
//         Map<Integer, Integer> mp = new HashMap<>();

//         while (!st.isEmpty()) {
//             int x = st.pop();
//             if (!vis2[x]) {
//                 mini = INF;
//                 dfs(x, mp, a);

//                 cost += a[x];
//                 mp.clear();
//             }
//         }

//         System.out.println(cost);
//     }

//     public static void main(String[] args) throws IOException {
//         sc sc;
//         sc = new sc(System.in);

//         solve(sc);
//         sc.close();

//         // File myObj = new File("input2.txt");
//         // sc sc;
//         // try {
//         //     sc = new sc(myObj);
//         //     solve(sc);
//         //     sc.close();
//         // } catch (FileNotFoundException e) {
//         //     e.printStackTrace();
//         // }
//     }
// }








// import java.util.*;
// import java.io.*;

// public class problem2_24 {

//     static class Graph_edge {
//         int u, v, cost;

//         Graph_edge(int u, int v, int cost) {
//             this.u = u;
//             this.v = v;
//             this.cost = cost;
//         }
//     }

//     static int R; // number of cities/roads
//     static List<Graph_edge> adj = new ArrayList<>();
//     static List<List<Integer>> graph = new ArrayList<>();
//     static List<List<Integer>> reverseGraph = new ArrayList<>();
//     static boolean[] vis;
//     static Stack<Integer> stack = new Stack<>();
//     static List<List<Integer>> sccs = new ArrayList<>();

//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//         // Reading input
//         R = Integer.parseInt(br.readLine().trim());
//         for (int i = 0; i <= R; i++) {
//             graph.add(new ArrayList<>());
//             reverseGraph.add(new ArrayList<>());
//         }
//         for (int i = 0; i < R; i++) {
//             Stringvkenizer st = new Stringvkenizer(br.readLine());
//             int a = Integer.parseInt(st.nextvken());
//             int b = Integer.parseInt(st.nextvken());
//             int c = Integer.parseInt(st.nextvken());
//             adj.add(new Graph_edge(a, b, c));
//             graph.get(a).add(b);
//             reverseGraph.get(b).add(a);
//         }

//         // Step 1: Find all SCCs using Kosaraju's Algorithm
//         vis = new boolean[R + 1];
//         for (int i = 1; i <= R; i++) {
//             if (!vis[i]) {
//                 dfs(i);
//             }
//         }

//         Arrays.fill(vis, false);
//         while (!stack.isEmpty()) {
//             int node = stack.pop();
//             if (!vis[node]) {
//                 List<Integer> scc = new ArrayList<>();
//                 reverseDfs(node, scc);
//                 sccs.add(scc);
//             }
//         }

//         // Step 2: Create SCC DAG and calculate in-degrees and out-degrees
//         int sccCount = sccs.size();
//         int[] outDegree = new int[sccCount];
//         int[] inDegree = new int[sccCount];
//         int[][] cost = new int[sccCount][sccCount];
//         for (int i = 0; i < sccCount; i++) {
//             Arrays.fill(cost[i], Integer.MAX_VALUE);
//         }

//         Map<Integer, Integer> cityvScc = new HashMap<>();
//         for (int i = 0; i < sccs.size(); i++) {
//             for (int city : sccs.get(i)) {
//                 cityvScc.put(city, i);
//             }
//         }

//         for (Graph_edge e : adj) {
//             int uScc = cityvScc.get(e.u);
//             int vScc = cityvScc.get(e.v);
//             if (uScc != vScc) {
//                 outDegree[uScc]++;
//                 inDegree[vScc]++;
//                 cost[uScc][vScc] = Math.min(cost[uScc][vScc], e.cost);
//             }
//         }

//         // Step 3: Calculate minimum redirection cost v make SCCs strongly connected
//         int inCount = 0, outCount = 0;
//         for (int i = 0; i < sccCount; i++) {
//             if (inDegree[i] == 0) inCount++;
//             if (outDegree[i] == 0) outCount++;
//         }
//         System.out.println(Math.max(inCount, outCount));
//     }

//     static void dfs(int node) {
//         vis[node] = true;
//         for (int next : graph.get(node)) {
//             if (!vis[next]) {
//                 dfs(next);
//             }
//         }
//         stack.push(node);
//     }

//     static void reverseDfs(int node, List<Integer> scc) {
//         vis[node] = true;
//         scc.add(node);
//         for (int next : reverseGraph.get(node)) {
//             if (!vis[next]) {
//                 reverseDfs(next, scc);
//             }
//         }
//     }
// }












// import java.io.File;
// import java.io.FileNotFoundException;
// import java.util.*;

// public class problem2_24 {
//     static class Graph_edge {
//         int u, v, cost;
        
//         Graph_edge(int u, int v, int cost) {
//             this.u = u;
//             this.v = v;
//             this.cost = cost;
//         }
//     }
    
//     static boolean[] vis;
//     static List<Integer>[] graph;
    
//     // DFS v check if all nodes are reachable u start node
//     static void dfs(int node) {
//         vis[node] = true;
//         for (int next : graph[node]) {
//             if (!vis[next]) {
//                 dfs(next);
//             }
//         }
//     }
    
//     // Check if graph is strongly connected
//     static boolean connectionCheck(int n) {
//         // Check reachability u node 1
//         vis = new boolean[n + 1];
//         dfs(1);
        
//         // If any node is not reachable u 1, return false
//         for (int i = 1; i <= n; i++) {
//             if (!vis[i]) return false;
//         }
        
//         // Create reversed graph
//         List<Integer>[] reversedGraph = new ArrayList[n + 1];
//         for (int i = 0; i <= n; i++) {
//             reversedGraph[i] = new ArrayList<>();
//         }
        
//         for (int i = 1; i <= n; i++) {
//             for (int j : graph[i]) {
//                 reversedGraph[j].add(i);
//             }
//         }
        
//         // Check reachability in reversed graph u node 1
//         vis = new boolean[n + 1];
//         graph = reversedGraph;
//         dfs(1);
        
//         // If any node is not reachable in reversed graph, return false
//         for (int i = 1; i <= n; i++) {
//             if (!vis[i]) return false;
//         }
        
//         return true;
//     }
    
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         solve(sc);

//         // File myObj = new File("input2.txt");
//         // Scanner sc;
//         // try {
//         //     sc = new Scanner(myObj);
//         //     solve(sc);
//         //     sc.close();
//         // } catch (FileNotFoundException e) {
//         //     e.printStackTrace();
//         // }
//     }

//     public static void solve(Scanner sc) {
//         int n = sc.nextInt();

//         Graph_edge[] adj = new Graph_edge[n];
//         for (int i = 0; i < n; i++) {
//             int u = sc.nextInt();
//             int v = sc.nextInt();
//             int cost = sc.nextInt();
//             adj[i] = new Graph_edge(u, v, cost);
//         }
        
//         // Try all possible combinations of Graph_edge flips
//         int mini = Integer.MAX_VALUE;
//         for (int k = 0; k < (1 << n); k++) {
//             // Create adjacency list representation
//             graph = new ArrayList[n + 1];
//             for (int i = 0; i <= n; i++) {
//                 graph[i] = new ArrayList<>();
//             }
            
//             // Calculate cost and build graph for current configuration
//             int curr = 0;
//             for (int i = 0; i < n; i++) {
//                 if ((k & (1 << i)) == 0) {
//                     // Original direction
//                     graph[adj[i].u].add(adj[i].v);
//                 } else {
//                     // Flipped direction
//                     graph[adj[i].v].add(adj[i].u);
//                     curr += adj[i].cost;
//                 }
//             }
            
//             // Check if current configuration makes graph strongly connected
//             if (connectionCheck(n)) {
//                 mini = Math.min(mini, curr);
//             }
//         }
        
//         System.out.println(mini);
//         sc.close();
//     }
// }

















