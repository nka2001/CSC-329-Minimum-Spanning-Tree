/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.farmingdale.m11minimumspanningtree;

/**
 *
 * @author gerstl
 */
public class SmallMSTTest implements RunTest {

    public String runTest() {
        // Implements the MST test in Roughgarden, Part 3, section 15.5.1
        var theGraph = new Graph(5, false);
        theGraph.addEdge(0, 2, 4);
        theGraph.addEdge(0, 1, 2);
        theGraph.addEdge(1, 2, 3);
        theGraph.addEdge(1, 3, 6);
        theGraph.addEdge(2, 3, 5);
        theGraph.addEdge(2, 4, 1);
        theGraph.addEdge(3, 4, 7);
        var mst = theGraph.computeMinimumSpanningForest();
        if (mst.getEdgeCount() != 4) {
            // Hmm, do we count 1x or 2x for undirected edges
            return "E1001";
        }
        if (mst.getEdge(0, 1) != 2.0) {
            return "E1002";
        }
                if (mst.getEdge(1, 2) != 3.0) {
            return "E1003";
        }
        if (mst.getEdge(2, 3) != 5.0) {
            return "E1004";
        }
        if (mst.getEdge(2, 4) != 1.0) {
            return "E1005";
        }
        if (mst.getEdge(1, 0) != 2.0) {
            return "E1006";
        }
                if (mst.getEdge(2, 1) != 3.0) {
            return "E1007";
        }
        if (mst.getEdge(3, 2) != 5.0) {
            return "E1008";
        }
        if (mst.getEdge(4, 2) != 1.0) {
            return "E1009";
        }
        return "";
    }

}
