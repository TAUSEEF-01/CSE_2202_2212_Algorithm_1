import java.util.*;
import java.io.*;

public class D_Igor_In_the_Museum {
    static int n, m, k;
    static char[][] grid;
    static int[][] answer;
    static boolean[][] visited;
    static List<Pair> positions;
    static int count;

    // Direction arrays for adjacent cells
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    static class Pair {
        int first, second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    static boolean isValid(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m &&
                grid[x][y] == '.' && !visited[x][y];
    }

    static void dfs(int startX, int startY) {
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(startX, startY));
        visited[startX][startY] = true;
        positions.add(new Pair(startX, startY));

        while (!stack.isEmpty()) {
            Pair current = stack.pop();
            int x = current.first;
            int y = current.second;

            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (newX >= 0 && newX < n && newY >= 0 && newY < m) {
                    if (grid[newX][newY] == '*') {
                        count++;
                    }

                    if (isValid(newX, newY)) {
                        stack.push(new Pair(newX, newY));
                        visited[newX][newY] = true;
                        positions.add(new Pair(newX, newY));
                    }
                }
            }
        }
    }

    static void updateInfo() {
        for (Pair p : positions) {
            answer[p.first][p.second] = count;
        }
        positions.clear();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // Initialize arrays
        grid = new char[n][m];
        answer = new int[n][m];
        visited = new boolean[n][m];
        positions = new ArrayList<>();

        // Read the grid
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        // Process the grid to find connected components
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && grid[i][j] == '.') {
                    count = 0;
                    dfs(i, j);
                    updateInfo();
                }
            }
        }

        // Process queries
        StringBuilder sb = new StringBuilder();

        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            sb.append(answer[x][y]).append('\n');
        }

        System.out.print(sb);
    }
}
