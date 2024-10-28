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

vector<vector<pair<ll, ll>>> adj(100005);

void dijkstra(ll s, vector<ll> &d, vector<ll> &p)
{
    int n = adj.size();
    d.assign(n + 1, inf);
    p.assign(n + 1, -1);

    d[s] = 0;

    using pll = pair<ll, ll>;

    priority_queue<pll, vector<pll>, greater<pll>> q;
    q.push({0, s});

    while (!q.empty())
    {
        ll v = q.top().second;
        q.pop();

        for (auto edge : adj[v])
        {
            ll to = edge.first;
            ll len = edge.second;

            if (d[v] + len < d[to])
            {
                d[to] = d[v] + len;
                p[to] = v;
                q.push({d[to], to});
            }
        }
    }
}

vector<ll> restore_path(ll s, ll t, vector<ll> const &p)
{
    vector<ll> path;

    for (int v = t; v != s; v = p[v])
        path.push_back(v);
    path.push_back(s);

    reverse(path.begin(), path.end());
    return path;
}

void solve()
{
    ll n, m;
    cin >> n >> m;

    for (int i = 0; i < m; i++)
    {
        ll a, b, w;
        cin >> a >> b >> w;

        adj[a].push_back(make_pair(b, w));
        adj[b].push_back(make_pair(a, w));
    }

    vl d(n + 1), p(n + 1);
    dijkstra(1, d, p);

    if (p[n] == -1)
    {
        cout << -1 << endl;
        return;
    }

    vl ans = restore_path(1, n, p);
    for (auto &node : ans)
    {
        cout << node << ' ';
    }
    cout << endl;
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
    cin >> t;

    for (int i = 1; i <= t; i++)
    {
        // cout<<"Case "<<i<<": ";
        solve();
    }

    return 0;
}
