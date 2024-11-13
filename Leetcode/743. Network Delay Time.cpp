// using dijkstra

#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<pair<int, int>> g[110];

    void make_graph(int sz, vector<vector<int>>& times) {
        for (int i = 0; i < sz; i++) {
            int u = times[i][0];
            int v = times[i][1];
            int w = times[i][2];
            g[u].push_back(make_pair(v, w));
        }
    }

    int networkDelayTime(vector<vector<int>>& times, int n, int k) {
        make_graph(times.size(), times);

        vector<int> d(n + 1, INT_MAX);
        d[k] = 0;

        using pi = pair<int, int>;

        priority_queue<pi, vector<pi>, greater<pi>> q;
        q.push({0, k});

        int ans = 0;

        while (!q.empty()) {
            int w = q.top().first;
            int u = q.top().second;

            q.pop();

            for (auto v : g[u]) {
                int x = v.second;
                int y = v.first;

                if (w + x < d[y]) {
                    d[y] = w + x;
                    q.push({d[y], y});
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (d[i] == INT_MAX)
                return -1;
            ans = max(d[i], ans);
        }

        return ans;
    }
};


int main()
{

}