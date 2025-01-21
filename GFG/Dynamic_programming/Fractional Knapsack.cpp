#include <bits/stdc++.h>
using namespace std;
#define ll long long

double fractionalKnapsack(vector<int> &val, vector<int> &wt, int capacity)
{
    int n = val.size();
    vector<pair<double, int>> vp;
    for (int i = 0; i < n; i++)
    {
        vp.push_back({val[i] / (double)wt[i], i});
    }

    sort(vp.begin(), vp.end(), greater<>());
    double ans = 0;
    for (int i = 0; i < n; i++)
    {
        if (capacity - wt[vp[i].second] >= 0)
        {
            ans += val[vp[i].second];
            capacity -= wt[vp[i].second];
        }
        else
        {
            ans += vp[i].first * capacity;
            capacity = 0;
        }
    }

    return ans;
}

void solve()
{
    int n, capacity;
    cin >> n >> capacity;

    vector<int> val(n), wt(n);
    for (int i = 0; i < n; i++)
    {
        cin >> val[i];
    }
    for (int i = 0; i < n; i++)
    {
        cin >> wt[i];
    }

    cout << fractionalKnapsack(val, wt, capacity) << endl;
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
