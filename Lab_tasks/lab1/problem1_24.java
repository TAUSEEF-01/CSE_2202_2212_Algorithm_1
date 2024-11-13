import java.util.*;
import java.io.*;

class graph {
    public static ArrayList<Integer>[] resz(int m, ArrayList<Integer>[] adj, int n) {
        ArrayList<Integer>[] newAdj = new ArrayList[m + 1];

        for (int i = 0; i <= n; i++) {
            newAdj[i] = adj[i];
        }

        for (int i = n + 1; i <= m; i++) {
            newAdj[i] = new ArrayList<>();
        }

        return newAdj;
    }

    public static void connect_node(int u, int v, ArrayList<Integer>[] adj) {
        adj[u].add(v);
    }

    public static int getNumOfVertices(int u, ArrayList<Integer>[] adj) {
        return adj[u].size();
    }

    public static void printAdjVerticesOfNodes(int u, ArrayList<Integer>[] adj) {
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

        // for (int i = 1; i <= n; i++) {
        // System.out.println(i + " " + level[i]);
        // }
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

public class problem1_24 {
    public static void main(String args[]) {
        Scanner sc;
        sc = new Scanner(System.in);
        solve(sc);
        sc.close();

        // File myObj = new File("input1.txt");
        // Scanner sc;
        // try {
        //     sc = new Scanner(myObj);
        //     solve(sc);
        //     sc.close();
        // } catch (FileNotFoundException e) {
        //     e.printStackTrace();
        // }
    }

    static void solve(Scanner sc) {
        int n = sc.nextInt();
        int e = sc.nextInt();
        int k = sc.nextInt();
        int c = sc.nextInt();

        ArrayList<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        graph g = new graph();

        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            g.connect_node(u, v, adj);
            g.connect_node(v, u, adj);
        }

        int[] level = new int[n + 1];
        boolean[] vis = new boolean[n + 1];
        for (int j = 0; j <= n; j++) {
            level[j] = 0;
            vis[j] = false;
        }

        g.bfsLevel(c, adj, vis, level, n);

        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (vis[i] == true && level[i] <= k)
                cnt++;
        }
        System.out.println(cnt);

        // for (int i = 1; i <= n; i++) {
        // System.out.println("BFS from " + i + ":");
        // boolean[] vis = new boolean[n + 1];
        // for (int j = 0; j <= n; j++) {
        // vis[j] = false;
        // }

        // for (int j = 1; j <= n; j++) {
        // if (vis[j] == false)
        // g.bfs(j, adj, vis, n);
        // }
        // System.out.println();
        // }

        // g.display(n, adj);

        // int m = sc.nextInt();
        // adj = adj = g.resz(m, adj, n);
        // n = m;

        // g.display(n, adj);

        // for (int i = 1; i <= n; i++) {
        // System.out.println(i + ": " + g.getNumOfVertices(i, adj));
        // }

        // for (int i = 1; i <= n; i++) {
        // System.out.print(i + ": ");
        // g.printAdjVerticesOfNodes(i, adj);
        // }
    }
}
