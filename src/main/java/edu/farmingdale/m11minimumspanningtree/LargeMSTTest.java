/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.farmingdale.m11minimumspanningtree;

//import java.util.ArrayList;
import java.util.Set;
//import org.jgrapht.graph.DefaultEdge;
import org.apache.commons.lang3.tuple.Triple;
import java.util.HashSet;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import java.util.Random;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
//import org.jgrapht.alg.connectivity.ConnectivityInspector;

/**
 * creates a large graph with random weighted edges, none with a duplicate
 * weight (implying a single MST/Minimum spanning forest). Compares the MST
 * from student code and jgrapht
 * @author gerstl
 */
public class LargeMSTTest implements RunTest {

  
    public String runTest() {
        // use org.jgrapht, compare MSTs (make a large graph with different
        // weights for every edge
        final int TEST_SIZE = 30_000;
        final int EDGES_PER_NODE = 3;
        var random = new Random();
        Graph largeGraph = new Graph(TEST_SIZE, false);
        org.jgrapht.Graph<Integer, DefaultWeightedEdge> otherGraph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        for (int i = 0; i < TEST_SIZE; ++i) {
            otherGraph.addVertex(i);
        }
        // add EDGES_PER_NODEx the nodes edges, 
        for (int i = 0; i < TEST_SIZE * EDGES_PER_NODE; ++i) {
            int randomFrom = random.nextInt(TEST_SIZE - 1);
            int randomTo = random.nextInt(TEST_SIZE - 1);
            if (randomFrom == randomTo) {
                continue;
            }
            // I want unique weights so that there's only one MST. If there are
            // duplicate values, 
            int weight = i;
            // now try to add the edge to both
            boolean mineAdded = largeGraph.addEdge(randomFrom, randomTo, (double) weight);
            var otherAddedEdge = otherGraph.addEdge(randomFrom, randomTo);
            if (null != otherAddedEdge) {
                otherGraph.setEdgeWeight(otherAddedEdge, (double) weight);
                if (!mineAdded) {
                    // mine says duplicate, but other says not
                    return "E1001";
                }
            } else if (mineAdded) {
                // mine says not duplicate, but other says yes
                return "E1002";
            }
        }
        Graph myMSF = largeGraph.computeMinimumSpanningForest();
        KruskalMinimumSpanningTree<Integer, DefaultWeightedEdge> otherMSF = new KruskalMinimumSpanningTree(otherGraph);
        // The next three lines are just for checking
        var otherConnectivity = new ConnectivityInspector(otherGraph);
        var otherListOfComponentMembers = otherConnectivity.connectedSets();
        System.out.println("Per jgrapht, there are " + otherListOfComponentMembers.size() + " components");

        // now compare them
        if (myMSF.getEdgeCount() != otherMSF.getSpanningTree().getEdges().size()) {
            System.out.println("We show " + myMSF.getEdgeCount() + " edges but they show " + otherMSF.getSpanningTree().getEdges().size());
            return "E1003";
        }
       
        Set<Triple<Integer, Integer, Double>> otherMSFEdgesCommonFormat = new HashSet<>();
        Set<DefaultWeightedEdge> otherMSFEdges = otherMSF.getSpanningTree().getEdges();
        for (DefaultWeightedEdge edge : otherMSFEdges) {
            // normalize so from->to (we do undirected different than the graph software)
            int fromNode = Math.min(otherGraph.getEdgeSource(edge), otherGraph.getEdgeTarget(edge));
            int toNode = Math.max(otherGraph.getEdgeSource(edge), otherGraph.getEdgeTarget(edge));
            double weight = otherGraph.getEdgeWeight(edge);
            otherMSFEdgesCommonFormat.add(Triple.of(fromNode, toNode, weight));
        }
        // do the same with us
        Set<Triple<Integer, Integer, Double>> usMSFEdgesCommonFormat = new HashSet<>();
        // we could do an iterator over edges, but some are null;
        for (int i = 0; i < TEST_SIZE; ++i) {
            var anEdge = myMSF.edges.get(i);
            while (null != anEdge) {
                // normalize so from->to (we do undirected different than the graph software)
                int fromNode = Math.min(anEdge.fromNode, anEdge.connectsToNode);
                int toNode = Math.max(anEdge.fromNode, anEdge.connectsToNode);
                double weight = anEdge.weight;
                usMSFEdgesCommonFormat.add(Triple.of(fromNode, toNode, weight));
                anEdge = anEdge.next;
            }
        }
      if (!usMSFEdgesCommonFormat.equals(otherMSFEdgesCommonFormat)) {
            System.out.println("Per jgrapht, found eventually "
                    + otherMSFEdgesCommonFormat.size() + " and we found "
                    + usMSFEdgesCommonFormat.size());
            System.out.println("We are "+usMSFEdgesCommonFormat );
            System.out.println("Other is "+otherMSFEdgesCommonFormat );
            usMSFEdgesCommonFormat.removeAll(otherMSFEdges);
            
            System.out.println("The difference is " + (usMSFEdgesCommonFormat));
            return "E1004";
        }
        var otherIter = otherMSFEdgesCommonFormat.iterator();
        while (otherIter.hasNext()) {
            var item = otherIter.next();
            if (!usMSFEdgesCommonFormat.contains(item)) {
                System.err.println("javat found " + item + " but we did not");
                System.err.println("(we are " + usMSFEdgesCommonFormat + ")");
                return "E1005";
            }
        }
        return "";
    } // runTest 
}
