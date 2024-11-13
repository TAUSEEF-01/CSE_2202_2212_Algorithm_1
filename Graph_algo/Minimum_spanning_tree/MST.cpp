// Minimum spanning tree

#include <bits/stdc++.h>
using namespace std;
#define ll long long

class Graph
{
public:
    int MST_prim(int vertex, vector<vector<int>> adj[])
    {
        using pr = pair<int, int>;
        priority_queue<pr, vector<pr>, greater<pr>> pq;

        vector<bool> vis(vertex, false);

        pq.push({0, 0});
        int sum = 0;

        while (!pq.empty())
        {
            int u = pq.top().second;
            int dist = pq.top().first;
            pq.pop();

            if (vis[u])
                continue;

            vis[u] = true;
            sum += dist;
            for (auto v : adj[u])
            {
                int adjNode = v[0];
                int w = v[1];

                if (!vis[adjNode])
                {
                    pq.push({w, adjNode});
                }
            }
        }

        return sum;
    }

private:
    // private section
};

int main()
{
    int t = 1;
    // cin >> t;
    while (t--)
    {
        int V, E;
        cin >> V >> E;
        vector<vector<int>> adj[V];
        int i = 0;
        while (i++ < E)
        {
            int u, v, w;
            cin >> u >> v >> w;
            vector<int> t1, t2;
            t1.push_back(v);
            t1.push_back(w);
            adj[u].push_back(t1);
            t2.push_back(u);
            t2.push_back(w);
            adj[v].push_back(t2);
        }

        Graph obj;
        cout << obj.MST_prim(V, adj) << "\n";
    }

    return 0;
}
