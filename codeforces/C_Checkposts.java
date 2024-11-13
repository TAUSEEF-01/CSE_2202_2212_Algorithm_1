import java.io.*;
import java.util.*;

public class C_Checkposts {

    static final int sz = 300010;
    static final long mod = (long) 1e9 + 7;
    static List<Integer>[] g = new ArrayList[sz];
    static List<Integer>[] gT = new ArrayList[sz];
    static int[] vis = new int[sz];
    static int mx, currentPass;

    static {
        for (int i = 0; i < sz; i++) {
            g[i] = new ArrayList<>();
            gT[i] = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            g[u].add(v);
            gT[v].add(u); // reversed graph
        }

        Deque<Integer> stck = new ArrayDeque<>();
        currentPass = 1;

        for (int i = 0; i < n; i++) {
            if (vis[i] != currentPass) dfs1(i, stck);
        }

        currentPass = 2;
        long cost = 0, ways = 1;
        Map<Integer, Integer> mp = new HashMap<>();

        while (!stck.isEmpty()) {
            int i = stck.pop();
            if (vis[i] != currentPass) {
                mx = Integer.MAX_VALUE;
                dfs2(i, a, mp);
                cost += mx;
                ways = (ways * mp.get(mx)) % mod;
                mp.clear();
            }
        }

        out.println(cost + " " + ways);
        out.flush();
    }

    static void dfs1(int u, Deque<Integer> stck) {
        vis[u] = currentPass;
        for (int v : g[u]) {
            if (vis[v] != currentPass) dfs1(v, stck);
        }
        stck.push(u); // storing the sequence
    }

    static void dfs2(int u, int[] a, Map<Integer, Integer> mp) {
        vis[u] = currentPass;
        mp.put(a[u], mp.getOrDefault(a[u], 0) + 1);
        mx = Math.min(mx, a[u]);
        for (int v : gT[u]) {
            if (vis[v] != currentPass) dfs2(v, a, mp);
        }
    }
}
