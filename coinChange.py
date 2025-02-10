def can_make_amount_infinite_coins(coins, K):
    """
    Determines if it's possible to make amount K using infinite supply of given coins
    Using Dynamic Programming
    Time Complexity: O(n*K) where n is number of coin types
    Space Complexity: O(K)
    """
    dp = [False] * (K + 1)
    dp[0] = True  # Base case - zero amount is always possible
    
    for i in range(1, K + 1):
        for coin in coins:
            if i >= coin:
                dp[i] = dp[i] or dp[i - coin]
    
    return dp[K]

def can_make_amount_finite_coins(coins, counts, K):
    """
    Determines if it's possible to make amount K using finite supply of coins
    Using Dynamic Programming with coin counts
    Time Complexity: O(n*K*max(counts)) where n is number of coin types
    Space Complexity: O(K)
    """
    dp = [False] * (K + 1)
    dp[0] = True
    
    for coin, count in zip(coins, counts):
        for amount in range(K, -1, -1):
            for used in range(1, count + 1):
                if amount >= used * coin:
                    dp[amount] = dp[amount] or dp[amount - used * coin]
    
    return dp[K]

def min_coins_infinite_supply(coins, K):
    """
    Finds minimum number of coins needed to make amount K with infinite supply
    Using Dynamic Programming
    Time Complexity: O(n*K) where n is number of coin types
    Space Complexity: O(K)
    """
    dp = [float('inf')] * (K + 1)
    dp[0] = 0
    
    for i in range(1, K + 1):
        for coin in coins:
            if i >= coin:
                dp[i] = min(dp[i], dp[i - coin] + 1)
    
    return dp[K] if dp[K] != float('inf') else -1

def min_coins_finite_supply(coins, counts, K):
    """
    Finds minimum number of coins needed to make amount K with finite supply
    Using Dynamic Programming with coin counts
    Time Complexity: O(n*K*max(counts)) where n is number of coin types
    Space Complexity: O(K)
    """
    dp = [float('inf')] * (K + 1)
    dp[0] = 0
    
    for coin, count in zip(coins, counts):
        for amount in range(K, -1, -1):
            for used in range(1, count + 1):
                if amount >= used * coin:
                    if dp[amount - used * coin] != float('inf'):
                        dp[amount] = min(dp[amount], dp[amount - used * coin] + used)
    
    return dp[K] if dp[K] != float('inf') else -1

# Example usage:
if __name__ == "__main__":
    coins = [1, 2, 5]  # Coin denominations
    K = 11  # Target amount
    counts = [3, 2, 1]  # Number of each coin available
    
    # Test all scenarios
    print(f"Can make {K} with infinite coins:", 
          can_make_amount_infinite_coins(coins, K))
    
    print(f"Can make {K} with finite coins:", 
          can_make_amount_finite_coins(coins, counts, K))
    
    print(f"Minimum coins needed (infinite supply):", 
          min_coins_infinite_supply(coins, K))
    
    print(f"Minimum coins needed (finite supply):", 
          min_coins_finite_supply(coins, counts, K))