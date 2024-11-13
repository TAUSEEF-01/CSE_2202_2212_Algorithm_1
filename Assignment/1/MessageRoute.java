import java.io.*;
import java.util.*;

public class MessageRoute {
    static final int MAX_N = 100001;
    static int N, M, K, a, b;
    static int[] p = new int[MAX_N];
    static int[] dist = new int[MAX_N];
    static boolean[] vis = new boolean[MAX_N];
    static List<List<Integer>> G = new ArrayList<>(MAX_N);
    static Queue<Integer> Q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            G.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            G.get(a).add(b);
            G.get(b).add(a);
        }

        Q.add(1);
        vis[1] = true;

        while (!Q.isEmpty()) {
            int u = Q.poll();
            for (int v : G.get(u)) {
                if (!vis[v]) {
                    dist[v] = dist[u] + 1;
                    vis[v] = true;
                    p[v] = u;
                    Q.add(v);
                }
            }
        }

        if (!vis[N]) {
            System.out.println("IMPOSSIBLE");
            return;
        }

        int u = N;
        K = dist[N];
        int[] ans = new int[K + 1];
        for (int i = K; i >= 0; i--) {
            ans[i] = u;
            u = p[u];
        }

        System.out.println(K + 1);
        for (int i = 0; i <= K; i++) {
            System.out.print(ans[i] + (i == K ? "\n" : " "));
        }
    }
}
