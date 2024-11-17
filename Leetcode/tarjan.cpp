#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

class Solution
{
    int time = 1;

private:
    void tarjan(int u, int parent, vector<int> adj[], vector<bool> &vis,
                int lo[], int hi[], vector<vector<int>> &bridges)
    {
        vis[u] = 1;
        lo[u] = hi[u] = time;
        time++;

        for (auto &v : adj[u])
        {
            if (v == parent)
            {
                continue;
            }
            if (!vis[v])
            {
                tarjan(v, u, adj, vis, lo, hi, bridges);
                lo[u] = min(lo[u], lo[v]);

                if (lo[v] > hi[u])
                {
                    bridges.push_back({u, v});
                }
            }
            else
            {
                lo[u] = min(lo[u], lo[v]);
            }
        }
    }

public:
    vector<vector<int>> criticalConnections(int n,
                                            vector<vector<int>> &connections)
    {
        vector<int> adj[n];
        int lo[n], hi[n];
        vector<bool> vis(n, 0);

        int sz = connections.size();
        for (auto &v : connections)
        {
            adj[v[0]].push_back(v[1]);
            adj[v[1]].push_back(v[0]);
        }

        vector<vector<int>> bridges;
        for (int i = 0; i < n; i++)
        {
            if (!vis[i])
                tarjan(i, i, adj, vis, lo, hi, bridges);
        }
        return bridges;
    }
};

int main()
{
    int n;
    // cout << "Enter number of nodes: ";
    cin >> n;

    int m;
    // cout << "Enter number of connections: ";
    cin >> m;

    vector<vector<int>> connections(m, vector<int>(2));
    // cout << "Enter the connections (u v pairs):" << endl;
    for (int i = 0; i < m; i++)
    {
        cin >> connections[i][0] >> connections[i][1];
    }

    Solution sol;
    vector<vector<int>> result = sol.criticalConnections(n, connections);

    cout << "Output: [";
    for (size_t i = 0; i < result.size(); i++)
    {
        cout << "[" << result[i][0] << "," << result[i][1] << "]";
        if (i != result.size() - 1)
            cout << ",";
    }
    cout << "]" << endl;

    return 0;
}
