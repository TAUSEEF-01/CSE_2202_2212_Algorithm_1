#include <bits/stdc++.h>
using namespace std;
#define ll long long

int helper(vector<int> &a, vector<vector<int>> &dp, int i, int amount) // complexity --> time: n * amount; space: n * amount
{
    if (amount == 0)
        return 1;

    if (i < 0)
        return 0;

    if (dp[i][amount] != -1)
        return dp[i][amount];

    int ways = 0;
    for (int coin = 0; coin <= amount; coin += a[i])
    {
        ways += helper(a, dp, i - 1, amount - coin);
    }

    return dp[i][amount] = ways;
}

int change(int amount, vector<int> &coins)
{
    vector<vector<int>> dp(coins.size(), vector<int>(amount + 1, -1));
    return helper(coins, dp, coins.size() - 1, amount);
}

void solve()
{
    int n, amount;
    cin >> n >> amount;

    vector<int> coins(n);
    for (int i = 0; i < n; i++)
        cin >> coins[i];

    cout << change(amount, coins) << endl;
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
