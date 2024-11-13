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

int mult(int n)
{
    return n * (n - 1) / 2;
}

void solve()
{
    ll n, m, k;
    cin >> n >> m >> k;

    vector<ll> a(k);
    for (int i = 0; i < k; i++)
    {
        cin >> a[i];
    }

    DSU obj(n);
    for (int i = 0; i < m; i++)
    {
        int u, v;
        cin >> u >> v;

        obj.union_sets(u, v);
    }

    vector<int> sz(n + 1, 0);
    for (int i = 0; i <= n; i++)
    {
        sz[obj.find_root(i)]++;
    }

    int ans = 0, mx = 0, left = n;
    for (int i = 0; i < k; i++)
    {
        int var = sz[obj.find_root(a[i])];
        ans += mult(var);
        left -= var;
        mx = max(mx, var);
    }

    ans -= mult(mx);
    ans += mult(left + mx);
    ans -= m; // existing edges --> we've included this during the calculation

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
