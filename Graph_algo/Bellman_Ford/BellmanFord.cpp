#include <iostream>
#include <vector>
#include <limits.h>
using namespace std;

struct Edge {
    int u, v, weight;
};

class BellmanFord {
public:
    int V; // Number of vertices
    vector<Edge> edges; // List of edges

    BellmanFord(int vertices) {
        V = vertices;
    }

    void addEdge(int u, int v, int weight) {
        edges.push_back({u, v, weight});
    }

    void findShortestPath(int src) {
        // Step 1: Initialize distances from src to all other vertices as infinite
        vector<int> dist(V, INT_MAX);
        dist[src] = 0;

        // Step 2: Relax all edges |V| - 1 times
        for (int i = 1; i <= V - 1; i++) {
            for (auto edge : edges) {
                if (dist[edge.u] != INT_MAX && dist[edge.u] + edge.weight < dist[edge.v]) {
                    dist[edge.v] = dist[edge.u] + edge.weight;
                }
            }
        }

        // Step 3: Check for negative weight cycles
        for (auto edge : edges) {
            if (dist[edge.u] != INT_MAX && dist[edge.u] + edge.weight < dist[edge.v]) {
                cout << "Graph contains a negative weight cycle." << endl;
                return;
            }
        }

        // Print the shortest distances
        cout << "Shortest distances from source vertex " << src << ":" << endl;
        for (int i = 0; i < V; i++) {
            if (dist[i] == INT_MAX)
                cout << "Vertex " << i << " : Distance INF" << endl;
            else
                cout << "Vertex " << i << " : Distance " << dist[i] << endl;
        }
    }
};

int main() {
    // Create a graph with 5 vertices
    BellmanFord graph(5);

    // Add edges
    graph.addEdge(0, 1, -1);
    graph.addEdge(0, 2, 4);
    graph.addEdge(1, 2, 3);
    graph.addEdge(1, 3, 2);
    graph.addEdge(1, 4, 2);
    graph.addEdge(3, 2, 5);
    graph.addEdge(3, 1, 1);
    graph.addEdge(4, 3, -3);

    // Find the shortest paths from source vertex 0
    graph.findShortestPath(0);

    return 0;
}
