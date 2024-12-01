To extract the shortest path between two nodes in the **Floyd-Warshall algorithm**, we need to store additional information during the computation to reconstruct the path. This is typically done using a **predecessor (or parent) matrix**, which keeps track of the previous node for each pair of nodes on the shortest path.

---

### **Steps to Find the Path**

#### 1. **Initialize a Predecessor Matrix**
- Let `pred[i][j]` store the predecessor of node \( j \) on the shortest path from \( i \) to \( j \).
- Initially:
  - If there’s a direct edge \( (i, j) \), set `pred[i][j] = i`.
  - Otherwise, set `pred[i][j] = -1` (indicating no path).

#### 2. **Update the Predecessor Matrix**
- During the relaxation step of the Floyd-Warshall algorithm, update `pred[i][j]` whenever the shortest path from \( i \) to \( j \) is updated:
  \[
  \text{If } D[i][j] > D[i][k] + D[k][j]:
  \]
  \[
  D[i][j] = D[i][k] + D[k][j] \quad \text{and} \quad \text{pred}[i][j] = \text{pred}[k][j]
  \]
  This means the shortest path from \( i \) to \( j \) goes through \( k \), so \( k \)’s predecessor for reaching \( j \) becomes the new predecessor.

#### 3. **Reconstruct the Path**
- Use the `pred` matrix to backtrack the path from the destination node to the source node:
  1. Start with the destination node \( t \).
  2. Repeatedly set \( t = \text{pred}[s][t] \), where \( s \) is the source node.
  3. Stop when \( t \) equals \( s \), or \( t = -1 \) (indicating no path).

#### 4. **Handle Negative Cycles**
- If \( D[i][i] < 0 \), a negative cycle exists. Paths involving negative cycles may not be well-defined, as the cost can be reduced indefinitely.

---

### **Algorithm Implementation**

Here's a pseudocode example:

```python
# Initialization
n = number of nodes
D = distance matrix (initialized with edge weights or INF)
pred = [[-1 for _ in range(n)] for _ in range(n)]

for i in range(n):
    for j in range(n):
        if i != j and graph[i][j] != INF:  # Direct edge exists
            pred[i][j] = i

# Floyd-Warshall with Predecessor Updates
for k in range(n):
    for i in range(n):
        for j in range(n):
            if D[i][j] > D[i][k] + D[k][j]:
                D[i][j] = D[i][k] + D[k][j]
                pred[i][j] = pred[k][j]

# Reconstruct the path
def get_path(source, destination, pred):
    if pred[source][destination] == -1:
        return []  # No path exists
    path = []
    current = destination
    while current != source:
        path.append(current)
        current = pred[source][current]
    path.append(source)
    path.reverse()
    return path
```

---

### **Example**

Consider this graph:

| \(i \) | \( j \) | Weight |
|-------|-------|--------|
| \(0\) | \(1\) | \(2\) |
| \(1\) | \(2\) | \(3\) |
| \(0\) | \(2\) | \(6\) |

1. **Initial `pred` Matrix:**
   ```
   pred = [
       [-1, 0, 0],
       [-1, -1, 1],
       [-1, -1, -1]
   ]
   ```

2. **After Floyd-Warshall Relaxations:**
   ```
   pred = [
       [-1, 0, 1],
       [-1, -1, 1],
       [-1, -1, -1]
   ]
   ```

3. **Query Path from 0 to 2:**
   - Start from \(2\), backtrack using `pred[0][2] = 1`, then `pred[0][1] = 0`.
   - Path: \(0 --> 1 --> 2\).

---

This method ensures you can find the path efficiently in \( O(V^2) \) additional memory and \( O(V^3) \) total time.