#include <bits/stdc++.h>
using namespace std;
#define ll long long

// vector<int> dp(1005, -1);
// int rodCutting(vector<int> &a, int n)
// {
//     if (n == 0)
//         return 0;

//     if (dp[n] != -1)
//         return dp[n];

//     int total = 0;
//     for (int i = 1; i <= n; i++)
//     {
//         total = max(total, rodCutting(a, n - i) + a[i - 1]);
//     }

//     return dp[n] = total;
// }

// void answer()
// {
//     int n;
//     cin >> n;

//     vector<int> profit(n);
//     for (int i = 0; i < n; i++)
//     {
//         cin >> profit[i];
//     }

//     cout << rodCutting(profit, n) << endl;
// }


int rodCutting(vector<int> &a, vector<int> &dp, int len_left) // complexity --> time: n * n; space: n
{
    if (len_left == 0)
        return 0;

    if (dp[len_left] != -1)
        return dp[len_left];

    int ans = 0;
    for (int i = 1; i <= len_left; i++)
    {
        ans = max(ans, rodCutting(a, dp, len_left - i) + a[i - 1]);
    }
    return dp[len_left] = ans;
}

void solve()
{
    int n;
    cin >> n;

    vector<int> a(n), dp(n + 1, -1);
    for (int i = 0; i < n; i++)
    {
        cin >> a[i];
    }

    cout << rodCutting(a, dp, n) << endl;
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
