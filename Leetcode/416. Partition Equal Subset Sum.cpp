#include <bits/stdc++.h>
using namespace std;
#define ll long long

bool subsetSum(vector<int> &a, vector<vector<int>> &dp, int i, int need) // complexity --> time: n * total; space: n * total
{ 
    if (need == 0)
        return true;

    if (i < 0)
        return false;

    if (dp[i][need] != -1)
        return dp[i][need];

    bool ans = subsetSum(a, dp, i - 1, need);
    if (need - a[i] >= 0)
        ans |= subsetSum(a, dp, i - 1, need - a[i]);

    return dp[i][need] = ans;
}

bool canPartition(vector<int> &nums)
{
    int total = accumulate(nums.begin(), nums.end(), 0);
    if (total % 2)
    {
        return false;
    }
    else
    {
        vector<vector<int>> dp(nums.size(), vector<int>(total + 1, -1));
        return subsetSum(nums, dp, nums.size() - 1, total / 2);
    }
}

void solve()
{
    int n;
    cin >> n;

    vector<int> nums(n);
    for (int i = 0; i < n; i++)
    {
        cin >> nums[i];
    }

    if (canPartition(nums))
        cout << "YES\n";
    else
        cout << "NO\n";
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
