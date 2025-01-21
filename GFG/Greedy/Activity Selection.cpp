#include <bits/stdc++.h>
using namespace std;
#define ll long long

int activitySelection(vector<int> &start, vector<int> &end)
{
    int n = start.size();

    vector<pair<int, int>> vp;
    for (int i = 0; i < n; i++)
    {
        vp.push_back({end[i], start[i]});
    }

    sort(vp.begin(), vp.end());
    int cnt = 1;
    for (int i = 0, j = 0; i < n; i++)
    {
        if (i == 0)
            continue;

        if (vp[j].first < vp[i].second)
        {
            cnt++;
            j = i;
        }
    }

    return cnt;
}

void solve()
{
    int n;
    cin >> n;

    vector<int> start(n), end(n);
    for (int i = 0; i < n; i++)
    {
        cin >> start[i];
    }
    for (int i = 0; i < n; i++)
    {
        cin >> end[i];
    }

    cout << activitySelection(start, end) << endl;
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
