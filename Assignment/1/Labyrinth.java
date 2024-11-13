import java.io.*;
import java.util.*;

public class Labyrinth {
    static final int MOD = (int) 1e9 + 7;
    static final long INF = (long) 1e18;
    static final double PI = Math.acos(-1);

    static char[][] adj = new char[1005][1005];
    static int[][] vis = new int[1005][1005];
    static int[][] level = new int[1005][1005];

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static char[] direct = { 'L', 'R', 'U', 'D' };

    static int n, m, ax, ay, bx, by;
    static StringBuilder ans = new StringBuilder();

    static List<Pair> vp = new ArrayList<>();

    static boolean notValid(int x, int y) {
        return x >= n || x < 0 || y >= m || y < 0 || adj[x][y] == '#';
    }

    static void bfs(int X, int Y) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(X, Y));

        vis[X][Y] = 1;
        level[X][Y] = 1;

        while (!q.isEmpty()) {
            Pair p = q.poll();
            X = p.x;
            Y = p.y;

            for (int i = 0; i < 4; i++) {
                int x = X + dx[i];
                int y = Y + dy[i];

                if (notValid(x, y) || vis[x][y] == 1)
                    continue;

                vis[x][y] = 1;
                level[x][y] = level[X][Y] + 1;
                q.add(new Pair(x, y));

                if (x == bx && y == by) {
                    int lvl = level[x][y];
                    vp.add(new Pair(x, y));

                    lvl--;

                    while (lvl > 0) {
                        for (int j = 3; j >= 0; j--) {
                            int px = x - dx[j];
                            int py = y - dy[j];
                            if (notValid(px, py))
                                continue;
                            if (level[px][py] == lvl) {
                                vp.add(new Pair(px, py));
                                x = px;
                                y = py;
                                break;
                            }
                        }
                        lvl--;
                    }
                }
            }
        }
    }

    static void restorePath() {
        int n = vp.size();

        for (int i = 1; i < n; i++) {
            if (vp.get(i).x == vp.get(i - 1).x) {
                if (vp.get(i).y < vp.get(i - 1).y)
                    ans.append('R');
                else
                    ans.append('L');
            } else {
                if (vp.get(i).x < vp.get(i - 1).x)
                    ans.append('D');
                else
                    ans.append('U');
            }
        }
        ans.reverse();
    }

    static void solve(Scanner sc) {
        for (int i = 0; i < 1005; i++) {
            Arrays.fill(vis[i], 0);
            Arrays.fill(level[i], Integer.MAX_VALUE);
        }

        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < m; j++) {
                adj[i][j] = line.charAt(j);
                if (adj[i][j] == 'A') {
                    ax = i;
                    ay = j;
                } else if (adj[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }

        bfs(ax, ay);

        if (vis[bx][by] == 0) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            restorePath();
            System.out.println(ans.length());
            System.out.println(ans.toString());
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int t = 1;
        for (int i = 1; i <= t; i++) {
            solve(sc);
        }
    }

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
