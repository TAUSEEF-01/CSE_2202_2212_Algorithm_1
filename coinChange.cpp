#include <vector>
#include <climits>
#include <iostream>
using namespace std;

class CoinChange {
public:
    // Check if possible to make amount K with infinite supply of coins
    bool canMakeAmountInfiniteCoins(vector<int>& coins, int K) {
        vector<bool> dp(K + 1, false);
        dp[0] = true;  // Base case
        
        for(int i = 1; i <= K; i++) {
            for(int coin : coins) {
                if(i >= coin) {
                    dp[i] = dp[i] || dp[i - coin];
                }
            }
        }
        return dp[K];
    }
    
    // Check if possible to make amount K with finite supply of coins
    bool canMakeAmountFiniteCoins(vector<int>& coins, vector<int>& counts, int K) {
        vector<bool> dp(K + 1, false);
        dp[0] = true;
        
        for(int i = 0; i < coins.size(); i++) {
            for(int amount = K; amount >= 0; amount--) {
                for(int used = 1; used <= counts[i]; used++) {
                    if(amount >= used * coins[i]) {
                        dp[amount] = dp[amount] || dp[amount - used * coins[i]];
                    }
                }
            }
        }
        return dp[K];
    }
    
    // Find minimum coins needed with infinite supply
    int minCoinsInfiniteSupply(vector<int>& coins, int K) {
        vector<int> dp(K + 1, INT_MAX);
        dp[0] = 0;
        
        for(int i = 1; i <= K; i++) {
            for(int coin : coins) {
                if(i >= coin && dp[i - coin] != INT_MAX) {
                    dp[i] = min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[K] == INT_MAX ? -1 : dp[K];
    }
    
    // Find minimum coins needed with finite supply
    int minCoinsFiniteSupply(vector<int>& coins, vector<int>& counts, int K) {
        vector<int> dp(K + 1, INT_MAX);
        dp[0] = 0;
        
        for(int i = 0; i < coins.size(); i++) {
            for(int amount = K; amount >= 0; amount--) {
                for(int used = 1; used <= counts[i]; used++) {
                    if(amount >= used * coins[i] && dp[amount - used * coins[i]] != INT_MAX) {
                        dp[amount] = min(dp[amount], dp[amount - used * coins[i]] + used);
                    }
                }
            }
        }
        return dp[K] == INT_MAX ? -1 : dp[K];
    }
};

int main() {
    // Example usage
    vector<int> coins = {1, 2, 5};  // Coin denominations
    vector<int> counts = {3, 2, 1}; // Number of each coin available
    int K = 11;  // Target amount
    
    CoinChange solver;
    
    // Test all scenarios
    cout << "Can make " << K << " with infinite coins: " 
         << (solver.canMakeAmountInfiniteCoins(coins, K) ? "Yes" : "No") << endl;
    
    cout << "Can make " << K << " with finite coins: " 
         << (solver.canMakeAmountFiniteCoins(coins, counts, K) ? "Yes" : "No") << endl;
    
    cout << "Minimum coins needed (infinite supply): " 
         << solver.minCoinsInfiniteSupply(coins, K) << endl;
    
    cout << "Minimum coins needed (finite supply): " 
         << solver.minCoinsFiniteSupply(coins, counts, K) << endl;
    
    return 0;
}