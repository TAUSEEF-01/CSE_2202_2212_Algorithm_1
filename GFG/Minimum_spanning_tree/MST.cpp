// Minimum spanning tree

#include <bits/stdc++.h>
using namespace std;
#define ll long long

class DSU
{
public:
    vector<int> parent;

    DSU(int n)
    {
        parent.resize(n + 1);
        for (int i = 0; i <= n; i++)
        {
            parent[i] = i;
        }
    }

    void make_parent(int v)
    {
        parent[v] = v;
    }

    int find_root(int v)
    {
        if (v == parent[v])
            return v;
        return find_root(parent[v]);
    }

    void union_sets(int a, int b)
    {
        a = find_root(a);
        b = find_root(b);
        if (a != b)
            parent[b] = a;
    }

private:
    // private section
};

class Graph
{
public:
    int MST_prim(int noVertex, vector<vector<int>> adj[])
    {
        using pr = pair<int, int>;
        priority_queue<pr, vector<pr>, greater<pr>> pq;

        vector<bool> vis(noVertex, false);

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

    int MST_kruskal(int noVertex, vector<vector<int>> adj[])
    {
        priority_queue<pair<int, pair<int, int>>, vector<pair<int, pair<int, int>>>, greater<pair<int, pair<int, int>>>> p;

        for (int i = 0; i < noVertex; i++)
        {
            for (auto &v : adj[i])
            {
                int adjNode = v[0];
                int w = v[1];
                p.push({w, {i, adjNode}});
            }
        }

        int sum = 0;
        DSU dsu(noVertex);

        while (!p.empty())
        {
            int w = p.top().first;
            int u = p.top().second.first;
            int v = p.top().second.second;

            p.pop();

            if (dsu.find_root(u) != dsu.find_root(v))
            {
                sum += w;
                dsu.union_sets(u, v);
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
        cout << obj.MST_kruskal(V, adj) << "\n";
    }

    return 0;
}
