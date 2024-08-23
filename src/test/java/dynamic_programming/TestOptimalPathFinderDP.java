package dynamic_programming;

import org.hellgren.dynamic_programming.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class TestOptimalPathFinderDP {

    DirectedGraphDP graph;
    ValueMemoryDP memory;
    OptimalPathFinderDP pathFinder;

    @BeforeEach
     void init() {
        graph=TestHelper.createExampleGraph();
        createMemoryAndPathFinder();
    }

    private void createMemoryAndPathFinder() {
        MemoryTrainerDP trainer = new MemoryTrainerDP(graph);
        memory = trainer.createMemory();
        pathFinder=new OptimalPathFinderDP(graph,memory);
    }

    @Test
     void whenCreated_thenCorrectSize() {
        List<Integer> actions=pathFinder.getActionsOnPath();
        System.out.println("actions = " + actions);

        assertEquals(3, actions.size());
        assertEquals(List.of(1,1,0), actions);
    }

    @Test
     void whenCreated_thenCorrectNodes() {
        List<NodeDP> nodesOnOptimalPath=pathFinder.getNodesOnPath();
        System.out.println("nodesOnOptimalPath = " + nodesOnOptimalPath);

        assertEquals(3, nodesOnOptimalPath.size());
        assertEquals(List.of(NodeDP.of(0,0), NodeDP.of(1,1), NodeDP.of(2,1)), nodesOnOptimalPath);
    }

    @Test
     void whenCreatedUpIsNegativeGraph_thenCorrectSize() {
        graph=TestHelper.createExampleGraphUpIsNegative();
        createMemoryAndPathFinder();

        List<Integer> actions=pathFinder.getActionsOnPath();
        System.out.println("actions = " + actions);

        assertEquals(3, actions.size());
        assertEquals(List.of(0,0,0), actions);
    }

}
