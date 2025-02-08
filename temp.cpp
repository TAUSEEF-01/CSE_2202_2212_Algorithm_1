//{ Driver Code Starts
#include <bits/stdc++.h>
using namespace std;


// } Driver Code Ends
class Solution {
  public:
    // Function to find sum of weights of edges of the Minimum Spanning Tree.
    int spanningTree(int V, vector<vector<int>> adj[]) {
            // code here
            using pr = pair<int, int>;
            priority_queue<pr, vector<pr>, greater<pr>> pq;

            vector<bool> vis(V, false);
            pq.push({0, 0});

            int sum  = 0;
            while(!pq.empty())
            {
                int w = pq.top().first;
                int v = pq.top().second;

                pq.pop();

                if(vis[v]) 
                    continue;
                
                vis[v] = true;
                sum += w;
                for(auto &x: adj[v])
                {
                    if(!vis[x[0]])
                    pq.push({x[1], x[0]});
                }
            }

            return sum;
    }
};

//{ Driver Code Starts.

int main() {
    int t = 1;
    // cin >> t;
    while (t--) {
        int V, E;
        cin >> V >> E;
        vector<vector<int>> adj[V];
        int i = 0;
        while (i++ < E) {
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

        Solution obj;
        cout << obj.spanningTree(V, adj) << "\n";
    }

    return 0;
}

// } Driver Code Ends