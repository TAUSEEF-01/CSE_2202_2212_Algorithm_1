#include <bits/stdc++.h>
using namespace std;
#define ll long long

// int LCS(string &a, string &b, int n, int m, int i, int j, vector<vector<int>> &dp)
// {
//     if (i == n)
//         return 0;

//     if (j == m)
//         return 0;

//     if (dp[i][j] != -1)
//         return dp[i][j];

//     int ans = LCS(a, b, n, m, i + 1, j, dp);
//     ans = max(ans, LCS(a, b, n, m, i, j + 1, dp));
//     if (a[i] == b[j])
//         ans = max(ans, LCS(a, b, n, m, i + 1, j + 1, dp) + 1);

//     return dp[i][j] = ans;
// }

// int lcs(string &a, string &b)
// {
//     vector<vector<int>> dp(a.size(), vector<int>(b.size(), -1));
//     return LCS(a, b, a.size(), b.size(), 0, 0, dp);
// }

// void solve()
// {
//     string a, b;
//     cin >> a >> b;

//     cout << lcs(a, b) << endl;
// }

void solve()
{
    string a, b;
    cin >> a >> b;

    int n = a.size(), m = b.size();
    vector<vector<int>> dp(n + 1, vector<int>(m + 1, 0));

    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= m; j++)
        {
            int ans = dp[i - 1][j];
            ans = max(ans, dp[i][j - 1]);
            if (a[i - 1] == b[j - 1])
                ans = max(ans, dp[i - 1][j - 1] + 1);

            dp[i][j] = ans;
        }
    }

    // cout << dp[n][m] << endl;

    string ans;
    int i = n, j = m;
    while (i > 0 && j > 0)
    {
        if (a[i - 1] == b[j - 1])
        {
            ans.push_back(a[i - 1]);
            i--, j--;
        }
        else if (dp[i - 1][j] > dp[i][j - 1])
        {
            i--;
        }
        else
        {
            j--;
        }
    }

    reverse(ans.begin(), ans.end());
    cout << ans << endl;
}

int main()
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    int t = 1;
    // cin >> t;

    for (int i = 0; i < t; i++)
    {
        solve();
    }

    return 0;
}
