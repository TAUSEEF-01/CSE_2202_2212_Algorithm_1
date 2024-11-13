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

const int sz = 1e5 + 7;
vector<ll> g[sz];
vector<bool> vis(sz, false);
ll cnt, edge;

void dfs(ll u)
{
    vis[u] = true;
    for (auto v : g[u])
    {
        edge++;
        if (vis[v])
            continue;
        dfs(v);
    }
    cnt++;
}

bool comparator(const vector<ll> &a, const vector<ll> &b)
{
    if (a[0] != b[0])
    {
        return a[0] > b[0];
    }
    else
    {
        return a[1] < b[1];
    }
}

void solve()
{
    ll n, m, k;
    cin >> n >> m >> k;

    vector<ll> a(k);
    ll total1 = 0, total2 = 0;

    for (int i = 0; i < k; i++)
    {
        cin >> a[i];
    }

    for (int i = 0; i < m; i++)
    {
        int u, v;
        cin >> u >> v;

        g[u].push_back(v);
        g[v].push_back(u);
    }

    vector<vector<ll>> v(k);

    for (int i = 0; i < k; i++)
    {
        cnt = 0;
        edge = 0;

        dfs(a[i]);

        v[i].push_back(cnt);
        v[i].push_back(edge / 2);
        v[i].push_back(a[i]);

        total1 += edge / 2;
        total2 += cnt;
    }

    sort(v.begin(), v.end(), comparator);

    ll limit = m - (total1 - v[0][1]);
    ll g = n - (total2 - v[0][0]);
    ll value = g * (g - 1) * 1LL / 2 - limit;

    ll ans = value;
    for (int i = 1; i < k; i++)
    {
        limit = v[i][1];
        g = v[i][0];
        value = g * (g - 1) * 1LL / 2 - limit;

        ans += value;
    }

    cout << ans << endl;
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
