#include <bits/stdc++.h>
using namespace std;
#define ll long long

bool subSetSum(vector<int> &a, vector<vector<int>> &dp, int target, int i) // complexity --> time: n * target; space: n * target
{
    if (target == 0)
        return true;

    if (i < 0)
        return false;

    if (dp[i][target] != -1)
        return dp[i][target];

    bool ans = subSetSum(a, dp, target, i - 1);
    if (target - a[i] >= 0)
        ans |= subSetSum(a, dp, target - a[i], i - 1);

    return dp[i][target] = ans;
}

void solve()
{
    int n, target;
    cin >> n >> target;

    vector<int> a(n);
    vector<vector<int>> dp(n, vector<int>(target + 1, -1));
    for (int i = 0; i < n; i++)
    {
        cin >> a[i];
    }

    cout << (subSetSum(a, dp, target, n - 1) ? "YES\n" : "NO\n");
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
