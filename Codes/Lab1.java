import java.util.*;
import java.io.*;

class graph {
    public static ArrayList<Integer>[] resizeAdj(int m, ArrayList<Integer>[] adj, int n) {
        ArrayList<Integer>[] newAdj = new ArrayList[m + 1];

        for (int i = 0; i <= n; i++) {
            newAdj[i] = adj[i];
        }

        for (int i = n + 1; i <= m; i++) {
            newAdj[i] = new ArrayList<>();
        }

        return newAdj;
    }

    public static void addEdge(int u, int v, ArrayList<Integer>[] adj) {
        adj[u].add(v);
    }

    public static int numberOfVertices(int u, ArrayList<Integer>[] adj) {
        return adj[u].size();
    }

    public static void getAdjacentVerticesOfNode(int u, ArrayList<Integer>[] adj) {
        for (var v : adj[u]) {
            System.out.print(v + " ");
        }
        System.out.println();
    }

    public static void bfs(int s, ArrayList<Integer>[] adj, boolean[] vis, int n) {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        vis[s] = true;
        while (!q.isEmpty()) {
            int u = q.remove();

            System.out.print(u + " ");

            for (var v : adj[u]) {
                if (vis[v] == false) {
                    vis[v] = true;
                    q.add(v);
                }
            }
        }
    }

    public static void bfsLevel(int s, ArrayList<Integer>[] adj, boolean[] vis, int[] level, int n) {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        level[s] = 0;
        vis[s] = true;

        while (!q.isEmpty()) {
            int u = q.remove();
            for (var v : adj[u]) {
                if (vis[v] == false) {
                    vis[v] = true;
                    level[v] = level[u] + 1;
                    q.add(v);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(i + " " + level[i]);
        }
    }

    public static void display(int n, ArrayList<Integer>[] adj) {
        for (int i = 1; i <= n; i++) {
            System.out.print(i + ": ");
            for (var j : adj[i]) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

}

public class Lab1 {
    public static void main(String args[]) {
        File myObj = new File("input.txt");
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
        int e = sc.nextInt();

        ArrayList<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        graph g = new graph();

        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            g.addEdge(u, v, adj);
            g.addEdge(v, u, adj);
        }

        for (int i = 1; i <= n; i++) {
            System.out.println("Distance from " + i + ":");
            int[] level = new int[n + 1];
            boolean[] vis = new boolean[n + 1];
            for (int j = 0; j <= n; j++) {
                level[j] = 0;
                vis[j] = false;
            }

            g.bfsLevel(i, adj, vis, level, n);
        }

        for (int i = 1; i <= n; i++) {
            System.out.println("BFS from " + i + ":");
            boolean[] vis = new boolean[n + 1];
            for (int j = 0; j <= n; j++) {
                vis[j] = false;
            }

            for (int j = 1; j <= n; j++) {
                if (vis[j] == false)
                    g.bfs(j, adj, vis, n);
            }
            System.out.println();
        }

        g.display(n, adj);

        int m = sc.nextInt();
        adj = adj = g.resizeAdj(m, adj, n);
        n = m;

        g.display(n, adj);

        for (int i = 1; i <= n; i++) {
            System.out.println(i + ": " + g.numberOfVertices(i, adj));
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(i + ": ");
            g.getAdjacentVerticesOfNode(i, adj);
        }
    }
}
