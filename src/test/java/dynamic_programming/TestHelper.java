package dynamic_programming;


import org.hellgren.dynamic_programming.domain.DirectedGraphDP;
import org.hellgren.dynamic_programming.domain.EdgeDP;
import org.hellgren.dynamic_programming.domain.NodeDP;

 class TestHelper {
     static final int X_MAX = 3, Y_MAX = 1;


    /**
     * createExampleGraph is illustrated in example_graph.png
     */

     static DirectedGraphDP createExampleGraph() {
        DirectedGraphDP graph = DirectedGraphDP.newWithSize(X_MAX, Y_MAX);
        graph.addEdgeWithReward(EdgeDP.of(NodeDP.of(2, 0), NodeDP.of(3, 0)), 3d);
        graph.addEdgeWithReward(EdgeDP.of(NodeDP.of(2, 1), NodeDP.of(3, 0)), 1d);
        graph.addEdgeWithReward(EdgeDP.of(NodeDP.of(1, 0), NodeDP.of(2, 0)), 2d);
        graph.addEdgeWithReward(EdgeDP.of(NodeDP.of(1, 0), NodeDP.of(2, 1)), 3d);
        graph.addEdgeWithReward(EdgeDP.of(NodeDP.of(1, 1), NodeDP.of(2, 0)), -3);
        graph.addEdgeWithReward(EdgeDP.of(NodeDP.of(1, 1), NodeDP.of(2, 1)), 5d);
        graph.addEdgeWithReward(EdgeDP.of(NodeDP.of(0, 0), NodeDP.of(1, 0)), 1d);
        graph.addEdgeWithReward(EdgeDP.of(NodeDP.of(0, 0), NodeDP.of(1, 1)), 3d);
        return graph;
    }

     static DirectedGraphDP createExampleGraphUpIsNegative() {
        DirectedGraphDP graph = DirectedGraphDP.newWithSize(X_MAX, Y_MAX);
        graph.addEdgeWithReward(EdgeDP.of(NodeDP.of(2, 0), NodeDP.of(3, 0)), 0d);
        graph.addEdgeWithReward(EdgeDP.of(NodeDP.of(2, 1), NodeDP.of(3, 0)), 0d);
        graph.addEdgeWithReward(EdgeDP.of(NodeDP.of(1, 0), NodeDP.of(2, 0)), 0d);
        graph.addEdgeWithReward(EdgeDP.of(NodeDP.of(1, 0), NodeDP.of(2, 1)), -1d);
        graph.addEdgeWithReward(EdgeDP.of(NodeDP.of(1, 1), NodeDP.of(2, 0)), 0d);
        graph.addEdgeWithReward(EdgeDP.of(NodeDP.of(1, 1), NodeDP.of(2, 1)), 0d);
        graph.addEdgeWithReward(EdgeDP.of(NodeDP.of(0, 0), NodeDP.of(1, 0)), 0d);
        graph.addEdgeWithReward(EdgeDP.of(NodeDP.of(0, 0), NodeDP.of(1, 1)), -1d);
        return graph;
    }

}
