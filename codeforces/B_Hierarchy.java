import java.io.*;
import java.util.*;

public class B_Hierarchy {

    static class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    static class Triple {
        int first;
        Pair secondPair;

        Triple(int first, Pair secondPair) {
            this.first = first;
            this.secondPair = secondPair;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        solve(br, out);

        br.close();
        out.close();
    }

    public static void solve(BufferedReader br, PrintWriter out) throws IOException {

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());

        PriorityQueue<Triple> pq = new PriorityQueue<>(Comparator.comparingInt(t -> t.first));
        boolean[] vis = new boolean[n];

        for (int i = 0; i < n; i++) {
            vis[i] = false;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            u--;
            v--;
            pq.add(new Triple(w, new Pair(u, v)));
        }

        int ans = 0, cnt = 0;
        while (!pq.isEmpty()) {
            Triple t = pq.poll();
            int w = t.first;
            int u = t.secondPair.first;
            int v = t.secondPair.second;

            if (vis[v] == true)
                continue;

            ans += w;
            cnt++;
            vis[v] = true;
        }

        if (cnt == n - 1)
            out.println(ans);
        else
            out.println(-1);
        out.flush();
    }
}