//{ Driver Code Starts
// Initial Template for C++

#include <bits/stdc++.h>
using namespace std;

// Back-end complete function Template for C++

// } Driver Code Ends
class Solution
{
public:
    // Function to find the shortest path from source to all other nodes
    vector<int> shortestPath(vector<vector<int>> &adj, int src)
    {
        // code here
        vector<int> dist(adj.size(), -1), vis(adj.size(), false);

        queue<int> st;
        st.push(src);
        vis[src] = true;
        dist[src] = 0;

        while (!st.empty())
        {
            int u = st.front();
            st.pop();

            for (int v : adj[u])
            {
                if (!vis[v])
                {
                    vis[v] = true;
                    dist[v] = dist[u] + 1;
                    st.push(v);
                }
            }
        }

        return dist;
    }
};

//{ Driver Code Starts.
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
            adj[v].push_back(u);
        }

        int src;
        cin >> src;

        Solution obj;

        vector<int> res = obj.shortestPath(adj, src);

        for (auto x : res)
        {
            cout << x << " ";
        }
        cout << "\n";
    }
}

// } Driver Code Ends
