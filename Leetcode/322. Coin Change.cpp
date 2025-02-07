#include <bits/stdc++.h>
using namespace std;
#define ll long long

ll helper(vector<int> &coins, vector<ll> &dp, int amount, int n) // complexity --> time: amount * n; space: amount
{
    if (amount == 0)
        return 0;

    if (dp[amount] != -1)
        return dp[amount];

    ll ans = INT_MAX;
    for (int i = 0; i < n; i++)
    {
        if (amount - coins[i] >= 0)
        {
            ans = min(ans, helper(coins, dp, amount - coins[i], n) + 1);
        }
    }
    return dp[amount] = ans;
}

int coinChange(vector<int> &coins, int amount)
{
    vector<ll> dp(amount + 1, -1);
    ll ans = helper(coins, dp, amount, coins.size());
    return ans < INT_MAX ? ans : -1;
}

void solve()
{
    int n, amount;
    cin >> n >> amount;

    vector<int> coins(n);
    for (int i = 0; i < n; i++)
        cin >> coins[i];

    cout << coinChange(coins, amount) << endl;
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
