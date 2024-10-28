// import java.util.Scanner;

// public class problem3_24 {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);

//         // Read dimensions
//         int r = scanner.nextInt();
//         int c = scanner.nextInt();
//         scanner.nextLine(); // Consume newstr

//         // Create and populate the adjMat
//         int[][] adjMat = new int[r][c];
//         for (int i = 0; i < r; i++) {
//             String[] str = scanner.nextLine().split(",");
//             for (int j = 0; j < c; j++) {
//                 adjMat[i][j] = Integer.parseInt(str[j]);
//             }
//         }

//         System.out.println(getVal(adjMat));
//         scanner.close();
//     }

//     public static int getVal(int[][] adjMat) {
//         if (adjMat == null || adjMat.length == 0 || adjMat[0].length == 0) {
//             return 0;
//         }

//         int ans = 0;
//         int r = adjMat.length;
//         int c = adjMat[0].length;

//         for (int i = 0; i < r; i++) {
//             for (int j = 0; j < c; j++) {
//                 if (adjMat[i][j] == 1) {
//                     // Add 4 for each land cell
//                     ans += 4;

//                     // Subtract 2 for each adjacent land cell (once for each cell)
//                     // Check cell to the left
//                     if (j > 0 && adjMat[i][j-1] == 1) {
//                         ans -= 2;
//                     }
//                     // Check cell above
//                     if (i > 0 && adjMat[i-1][j] == 1) {
//                         ans -= 2;
//                     }
//                 }
//             }
//         }

//         return ans;
//     }
// }

import java.util.*;
import java.io.*;

class Graph {
    private int[][] adjMat;
    private int r;
    private int c;

    public Graph(int r, int c) {
        this.r = r;
        this.c = c;
        this.adjMat = new int[r][c];
    }

    public void valueSet(int i, int j, int val) {
        adjMat[i][j] = val;
    }

    public int getVal() {
        if (r == 0 || c == 0 || adjMat == null) {
            return 0;
        }

        int ans = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (adjMat[i][j] == 1) {
                    ans += 4;

                    if (j > 0 && adjMat[i][j - 1] == 1) {
                        ans -= 2;
                    }
                    if (i > 0 && adjMat[i - 1][j] == 1) {
                        ans -= 2;
                    }
                }
            }
        }

        return ans;
    }
}

public class problem3_24 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        solve(sc);

        // File myObj = new File("input3.txt");
        // Scanner sc;
        // try {
        // sc = new Scanner(myObj);
        // solve(sc);
        // sc.close();
        // } catch (FileNotFoundException e) {
        // e.printStackTrace();
        // }
    }

    static void solve(Scanner sc) {
        int r = sc.nextInt();
        int c = sc.nextInt();
        sc.nextLine();

        Graph g = new Graph(r, c);

        for (int i = 0; i < r; i++) {
            String[] str = sc.nextLine().split(",");
            for (int j = 0; j < c; j++) {
                g.valueSet(i, j, Integer.parseInt(str[j]));
            }
        }

        System.out.println(g.getVal());
    }
}