class BellmanFord:
    def __init__(self, vertices):
        self.V = vertices  # Number of vertices
        self.edges = []    # List to store all edges

    def add_edge(self, u, v, weight):
        # Add directed edge (u -> v) with a given weight
        self.edges.append((u, v, weight))

    def shortest_path(self, src):
        # Step 1: Initialize distances from src to all other vertices as infinite
        dist = [float('inf')] * self.V
        dist[src] = 0

        # Step 2: Relax all edges |V| - 1 times
        for _ in range(self.V - 1):
            for u, v, weight in self.edges:
                if dist[u] != float('inf') and dist[u] + weight < dist[v]:
                    dist[v] = dist[u] + weight

        # Step 3: Check for negative weight cycles
        for u, v, weight in self.edges:
            if dist[u] != float('inf') and dist[u] + weight < dist[v]:
                print("Graph contains a negative weight cycle.")
                return None

        # Return the computed shortest distances
        return dist


# Example Usage
if __name__ == "__main__":
    g = BellmanFord(5)
    g.add_edge(0, 1, -1)
    g.add_edge(0, 2, 4)
    g.add_edge(1, 2, 3)
    g.add_edge(1, 3, 2)
    g.add_edge(1, 4, 2)
    g.add_edge(3, 2, 5)
    g.add_edge(3, 1, 1)
    g.add_edge(4, 3, -3)

    source = 0
    distances = g.shortest_path(source)
    if distances:
        print("Shortest distances from source vertex", source, ":")
        for i, d in enumerate(distances):
            print(f"Vertex {i} : Distance {d}")
