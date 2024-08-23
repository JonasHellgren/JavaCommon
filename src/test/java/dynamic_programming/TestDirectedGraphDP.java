package dynamic_programming;


import org.hellgren.dynamic_programming.domain.DirectedGraphDP;
import org.hellgren.dynamic_programming.domain.EdgeDP;
import org.hellgren.dynamic_programming.domain.NodeDP;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestDirectedGraphDP {

     static final int X_MAX = 3, Y_MAX = 1;
    DirectedGraphDP graph;

    @BeforeEach
     void init() {
        graph= DirectedGraphDP.newWithSize(X_MAX, Y_MAX);
    }

    @Test
     void whenAddingOneReward_thenCorrect() {
        graph.addEdgeWithReward(EdgeDP.of(NodeDP.of(0,0), NodeDP.of(1,0)),1d);
        assertEquals(1,graph.size());
    }

    @Test
     void whenAddingOneReward_thenCanGet() {
        EdgeDP edge = EdgeDP.of(NodeDP.of(0, 0), NodeDP.of(1, 0));
        double reward = 1d;
        graph.addEdgeWithReward(edge, reward);
        assertTrue(graph.getReward(edge).isPresent());
        assertEquals(reward,graph.getReward(edge).get());
    }

    @Test
     void whenAdding_thenCorrectStateSet() {
        double reward = 1d;
        EdgeDP edge1 = EdgeDP.of(NodeDP.of(0, 0), NodeDP.of(1, 0));
        EdgeDP edge2 = EdgeDP.of(NodeDP.of(0, 0), NodeDP.of(1, 1));
        EdgeDP edge3 = EdgeDP.of(NodeDP.of(1, 0), NodeDP.of(2, 0));
        EdgeDP edge4 = EdgeDP.of(NodeDP.of(1, 0), NodeDP.of(2, 1));

        graph.addEdgeWithReward(edge1, reward);
        graph.addEdgeWithReward(edge2, reward);
        graph.addEdgeWithReward(edge3, reward);
        graph.addEdgeWithReward(edge4, reward);

        var states=graph.getNodeSet();

        System.out.println("states = " + states);

        assertEquals(5,states.size());

    }

    @Test
     void whenAddingOneReward_thenCanGetByActionInState() {
        NodeDP s0 = NodeDP.of(0, 0);
        NodeDP s1 = NodeDP.of(1, 0);
        EdgeDP edge = EdgeDP.of(s0, s1);
        double reward = 1d;
        graph.addEdgeWithReward(edge, reward);
        assertTrue(graph.getReward(s0,0).isPresent());
        assertEquals(reward,graph.getReward(s0,0).get());
        Assertions.assertFalse(graph.getReward(s1,0).isPresent());
    }

    @Test
     void whenGetStateNew_thenCorrect() {
        NodeDP s00 = NodeDP.of(0, 0);
        NodeDP s10 = NodeDP.of(1, 0);
        NodeDP s11 = NodeDP.of(1, 1);
        NodeDP s30 = NodeDP.of(3, 0);

        assertEquals(s10,graph.getNextNode(s00,0));
        assertEquals(s11,graph.getNextNode(s00,1));
        assertNotEquals(s30,graph.getNextNode(s30,0));  //cant move on
    }

}
