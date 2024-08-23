package dynamic_programming;

import org.hellgren.dynamic_programming.domain.EdgeDP;
import org.hellgren.dynamic_programming.domain.NodeDP;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

 class TestStateAndEdge {

    @Test
     void whenCreatedState_thenCorrect() {
        NodeDP node = NodeDP.of(0,0);
        Assertions.assertEquals(0, node.x());
        Assertions.assertEquals(0, node.y());
    }

    @Test
     void whenCreatedTwoStates_thenCorrectEqualsAndHash() {
        NodeDP node1 = NodeDP.of(0,0);
        NodeDP node2 = NodeDP.of(1,0);

        Assertions.assertEquals(node1, node1);
        Assertions.assertNotEquals(node1, node2);
        Assertions.assertEquals(node1.hashCode(), node1.hashCode());
        Assertions.assertNotEquals(node1.hashCode(), node2.hashCode());

    }


    @Test
     void whenCreatedEdge_thenCorrect() {
        EdgeDP edge1 = EdgeDP.of(NodeDP.of(0,0), NodeDP.of(1,0));
        EdgeDP edge2 = EdgeDP.of(NodeDP.of(1,0), NodeDP.of(2,0));
        System.out.println("edge1 = " + edge1);

        Assertions.assertEquals(edge1,edge1);
        Assertions.assertNotEquals(edge1,edge2);
        Assertions.assertEquals(edge1.hashCode(),edge1.hashCode());
        Assertions.assertNotEquals(edge1.hashCode(),edge2.hashCode());
    }


    @Test
     void whenCreatedEdge_thenCorrectIsValid() {
        EdgeDP edge1 = EdgeDP.of(NodeDP.of(0,0), NodeDP.of(1,0));
        EdgeDP edge2 = EdgeDP.of(NodeDP.of(4,0), NodeDP.of(5,0));
        int xMax = 1, yMax = 1;
        Assertions.assertTrue(edge1.isValid(xMax, yMax));
        Assertions.assertFalse(edge2.isValid(xMax, yMax));

    }

}
