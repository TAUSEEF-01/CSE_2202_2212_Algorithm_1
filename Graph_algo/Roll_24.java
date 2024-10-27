import java.util.*;

class Graph {
    private ArrayList<Integer>[] adj;
    private boolean[] vis;
    private int n;

    public Graph(int n) {
        this.n = n;
        adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        vis = new boolean[n + 1];
    }

    public void addEdge(int u, int v) {
        adj[u].add(v);
    }

    public int numberOfVertices(int u) {
        return adj[u].size();
    }

    public void getAdjacentVerticesOfNode(int u) {
        for (var v : adj[u]) {
            System.out.print(v + " ");
        }
        System.out.println();
    }

    public void bfs(int s) {
        Arrays.fill(vis, false);
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        vis[s] = true;

        while (!q.isEmpty()) {
            int u = q.remove();
            System.out.print(u + " ");
            for (var v : adj[u]) {
                if (!vis[v]) {
                    vis[v] = true;
                    q.add(v);
                }
            }
        }
    }

    public void dfs(int u) {
        Arrays.fill(vis, false);
        dfsHelper(u);
    }

    private void dfsHelper(int u) {
        vis[u] = true;
        System.out.print(u + " ");
        for (var v : adj[u]) {
            if (!vis[v]) {
                dfsHelper(v);
            }
        }
    }

    private void topologicalSortUtil(int v, Stack<Integer> stack) {
        vis[v] = true;

        for (int i : adj[v]) {
            if (!vis[i]) {
                topologicalSortUtil(i, stack);
            }
        }

        stack.push(v);
    }

    public void topologicalSort() {
        Stack<Integer> stack = new Stack<>();
        Arrays.fill(vis, false);

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                topologicalSortUtil(i, stack);
            }
        }

        // System.out.print("Topological Sort: ");
        while (!stack.empty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }

    public void display() {
        for (int i = 1; i <= n; i++) {
            System.out.print(i + " -> ");
            for (var j : adj[i]) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public void displayGraph() {
        for (int i = 0; i < n; i++) {
            System.out.print(i + " -> ");
            for (var j : adj[i]) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    /* strongly connected components: */
    void getOrder(int u, boolean visitedVertices[], Stack<Integer> stack) {
        visitedVertices[u] = true;

        for (var v : adj[u]) {
            if (!visitedVertices[v])
                getOrder(v, visitedVertices, stack);
        }

        stack.push(u);
    }

    Graph transpose() {
        Graph g = new Graph(n);

        for (int i = 0; i <= n; i++) {
            for (var j : adj[i]) {
                g.addEdge(j, i);
            }
        }
        return g;
    }

    void dfsUtil(int i, boolean visitedVertices[]) {
        visitedVertices[i] = true;

        System.out.print(i + " ");
        for (var j : adj[i]) {
            if (visitedVertices[j] == false)
                dfsUtil(j, visitedVertices);
        }
    }

    void printSSC() {
        Stack<Integer> stack = new Stack<Integer>();

        boolean visitedVertices[] = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            visitedVertices[i] = false;
        }

        for (int i = 0; i <= n; i++) {
            if (visitedVertices[i] == false)
                getOrder(i, visitedVertices, stack);
        }

        Graph g = transpose();
        // g.displayGraph();

        for (int i = 0; i <= n; i++) {
            visitedVertices[i] = false;
        }

        while (!stack.empty()) {
            int s = (int) stack.pop();

            if (!visitedVertices[s]) {
                g.dfsUtil(s, visitedVertices);
                System.out.println();
            }
        }
    }

}

public class Roll_24 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        solve(sc);
    }

    static void solve(Scanner sc) {
        int n = sc.nextInt();
        int e = sc.nextInt();

        Graph g = new Graph(n);

        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            g.addEdge(u, v);
        }

        // g.displayGraph();
        // System.out.println();
        g.printSSC();

        // System.out.println("BFS starting from node 5:");
        // g.bfs(5);

        // System.out.println("\nDFS starting from node 5:");
        // g.dfs(5);

        // System.out.println("\nAdjacency List:");
        // g.displayGraph();

        // g.topologicalSort();
    }
}
