// Strongly connected components --> using kosaraju's algorithm

#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
    void dfs1(int u, vector<bool> &vis, stack<int> &st, vector<vector<int>> &g)
    {
        vis[u] = true;
        for (int &v : g[u])
        {
            if (vis[v])
                continue;
            dfs1(v, vis, st, g);
        }
        st.push(u); // storing the sequence
    }

    void dfs2(int u, vector<bool> &vis, vector<vector<int>> &gT)
    {
        vis[u] = true;
        for (int &v : gT[u])
        {
            if (vis[v])
                continue;
            dfs2(v, vis, gT);
        }
    }

    // Function to find number of strongly connected components in the graph.
    int kosaraju(int n, vector<vector<int>> &adj)
    {
        vector<bool> vis(n, false);
        vector<vector<int>> g(n), gT(n);

        for (int i = 0; i < n; i++)
        {
            for (auto v : adj[i])
            {
                g[i].push_back(v);
                gT[v].push_back(i); // reversed graph
            }
        }

        stack<int> st;
        for (int i = 0; i < n; i++)
        {
            if (!vis[i])
                dfs1(0, vis, st, g);
        }

        for (int i = 0; i < n; i++)
        {
            vis[i] = false;
        }

        int cnt = 0;
        while (!st.empty()) // based on the stored sequence
        {
            int i = st.top();
            st.pop();

            if (!vis[i])
            {
                dfs2(i, vis, gT);
                cnt++;
            }
        }

        return cnt;
    }
};

int main()
{

    int t = 1;
    // cin >> t;
    while (t--)
    {
        int V, E;
        cin >> V >> E;

        vector<vector<int>> adj(V);

        for (int i = 0; i < E; i++)
        {
            int u, v;
            cin >> u >> v;
            adj[u].push_back(v);
        }

        Solution obj;
        cout << obj.kosaraju(V, adj) << "\n";
    }

    return 0;
}
