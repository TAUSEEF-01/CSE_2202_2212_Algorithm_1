// need to solve this using scc --> Kosaraju’s algorithm

#include <bits/stdc++.h>
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/tree_policy.hpp>
using namespace std;
using namespace __gnu_pbds;
typedef tree<int, null_type, less<int>, rb_tree_tag, tree_order_statistics_node_update> ordered_set;

#define ll long long
#define ull unsigned long long
#define pb push_back
#define all(a) a.begin(), a.end()
#define reall(a) a.rbegin(), a.rend()
#define vsort(a) sort(all(a))
#define revsort(a) sort(reall(a))
#define vmin(a) *min_element(all(a))
#define vmax(a) *max_element(all(a))
#define loop(i, k, n) for (ll i = k; i < n; i++)
#define Loop(i, k, n) for (ll i = k; i <= n; i++)
using vi = vector<int>;
using vl = vector<ll>;
using vvi = vector<vi>;
using vvl = vector<vl>;
using vs = vector<string>;
using vb = vector<bool>;
using pii = pair<int, int>;

/**/
#define input(a)      \
    for (auto &x : a) \
        cin >> x;

#define output(a)         \
    for (auto &x : a)     \
        cout << x << ' '; \
    cout << endl;

/**/
#define yes \
    cout << "YES\n"

#define no \
    cout << "NO\n"

/**/
const ll mod = 1e9 + 7, inf = 1e18;
const double pi = acos(-1);
#define dbg(a) cerr << __LINE__ << ": " << #a << " = " << a << '\n'

const int sz = 3e5 + 10;
vector<int> g[sz], gT[sz];
vector<bool> vis(sz, false);
int mx;

void dfs1(int u, stack<int> &st)
{
    vis[u] = true;
    for (int &v : g[u])
    {
        if (vis[v])
            continue;
        dfs1(v, st);
    }
    st.push(u); // storing the sequence 
}

void dfs2(int u, vi &a, map<int, int> &mp)
{
    vis[u] = true;
    mp[a[u]]++;
    mx = min(mx, a[u]);
    for (int &v : gT[u])
    {
        if (vis[v])
            continue;
        dfs2(v, a, mp);
    }
}

void solve()
{
    int n;
    cin >> n;

    vi a(n);
    for (int i = 0; i < n; i++)
    {
        cin >> a[i];
    }

    int m;
    cin >> m;
    for (int i = 0; i < m; i++)
    {
        int u, v;
        cin >> u >> v;
        u--, v--;
        g[u].push_back(v);
        gT[v].push_back(u); // reversed graph
    }

    stack<int> st;
    for (int i = 0; i < n; i++)
    {
        if (!vis[i])
            dfs1(i, st);
    }

    for (int i = 0; i < n; i++)
    {
        vis[i] = false;
    }

    map<int, int> mp;
    ll cost = 0, ways = 1;
    while (!st.empty()) // based on the stored sequence 
    {
        int i = st.top();
        st.pop();

        if (!vis[i])
        {
            mx = INT_MAX;
            dfs2(i, a, mp);
            cost += mx;
            ways = (ways * mp[mx]) % mod;
            mp.clear();
        }
    }

    cout << cost << ' ' << ways << endl;
}

int main()
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    // #ifndef ONLINE_JUDGE
    // freopen("input.txt","r",stdin);
    // freopen("output.txt","w",stdout);
    // #endif

    int t = 1;
    // cin >> t;

    for (int i = 1; i <= t; i++)
    {
        // cout<<"Case "<<i<<": ";
        solve();
    }

    return 0;
}
