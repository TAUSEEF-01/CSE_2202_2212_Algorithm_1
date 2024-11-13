import java.util.*;

public class Dijkstra {

    public static class Pair {
        int first;
        long second;

        public Pair(int x, long y) {
            this.first = x;
            this.second = y;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        solve(scan);
    }

    public static void solve(Scanner scan) {
        int n = scan.nextInt();
        int m = scan.nextInt();

        ArrayList<ArrayList<Pair>> graph = new ArrayList<>(n + 1);
        long[] dist = new long[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            dist[i] = Long.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            int u = scan.nextInt();
            int v = scan.nextInt();
            long w = scan.nextLong();
            graph.get(u).add(new Pair(v, w));
            graph.get(v).add(new Pair(u, w));
        }

        int[] path = new int[n + 1];
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingLong(o1 -> o1.second));
        
        pq.add(new Pair(1, 0));
        dist[1] = 0;

        while (!pq.isEmpty()) {
            Pair v = pq.poll();

            if (v.second != dist[v.first])
                continue;
            for (Pair u : graph.get(v.first)) {
                if (dist[u.first] > dist[v.first] + u.second) {
                    dist[u.first] = dist[v.first] + u.second;
                    path[u.first] = v.first;
                    pq.add(new Pair(u.first, dist[u.first]));
                }
            }       	
        }

        if (dist[n] == Long.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = n; i != 1; i = path[i]) {
            list.add(i);
        }
        list.add(1);

        Collections.reverse(list);

        StringBuilder sb = new StringBuilder();
        for (int i : list) {
            sb.append(i + " ");
        }
        System.out.println(sb.toString());
    }
}
