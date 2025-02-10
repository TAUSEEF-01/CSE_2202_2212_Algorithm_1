//{ Driver Code Starts
#include <bits/stdc++.h>
using namespace std;

// } Driver Code Ends
class Solution
{
public:
    int f(vector<int> &val, vector<int> &wt, int i, int t, vector<vector<int>> &dp)
    {
        if (i < 0)
            return 0;

        if (t == 0)
            return 0;

        if (dp[i][t] != -1)
            return dp[i][t];

        int v = f(val, wt, i - 1, t, dp);

        if (t - wt[i] >= 0)
            v = max(v, f(val, wt, i - 1, t - wt[i], dp) + val[i]);

        return dp[i][t] = v;
    }

    // Function to return max value that can be put in knapsack of capacity.
    int knapSack(int capacity, vector<int> &val, vector<int> &wt)
    {
        // code here
        vector<vector<int>> dp(val.size() + 1, vector<int>(capacity + 1, 0));
        // return f(val, wt, val.size() - 1, capacity, dp);
        
        int n = val.size();
        for (int i = 1; i <= n; i++)
        {
            for (int j = 0; j <= capacity; j++)
            {
                int v = dp[i - 1][j];

                if (j - wt[i - 1] >= 0)
                    v = max(v, dp[i - 1][j - wt[i - 1]] + val[i - 1]);

                dp[i][j] = v;
            }
        }

        return dp[n][capacity];
    }
};

//{ Driver Code Starts.

int main()
{
    // Taking total test cases
    int testCases = 1;
    // cin >> testCases;
    cin.ignore();
    while (testCases--)
    {
        // Reading number of items and capacity
        int numberOfItems, capacity;
        vector<int> weights, values;
        string input;
        int number;

        // Read capacity and number of items
        getline(cin, input);
        stringstream ss(input);
        ss >> capacity;      // The first number is the capacity
        ss >> numberOfItems; // The second number is the number of items

        // Read values
        getline(cin, input);
        ss.clear();
        ss.str(input);
        while (ss >> number)
        {
            values.push_back(number);
        }

        // Read weights
        getline(cin, input);
        ss.clear();
        ss.str(input);
        while (ss >> number)
        {
            weights.push_back(number);
        }

        Solution solution;
        cout << solution.knapSack(capacity, values, weights) << endl;
        // cout << "~" << endl;
    }
    return 0;
}

// } Driver Code Ends