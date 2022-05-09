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
public class SmallMSTTest2 implements RunTest {

    public String runTest() {
        var theGraph = new Graph(8, false);
        theGraph.addEdge(0, 1, 8); // A -B
        theGraph.addEdge(0, 5, 10);// A-F
        theGraph.addEdge(0, 7, 4);// A-H

        theGraph.addEdge(1, 2, 4); // B-C
        theGraph.addEdge(1, 4, 10); //B-E
        theGraph.addEdge(1, 5, 7); // B-F
        theGraph.addEdge(1, 7, 9); // B-h

        theGraph.addEdge(2, 3, 3); // C-D
        theGraph.addEdge(2, 5, 3); // C-F

        theGraph.addEdge(3, 4, 25); //D-E
        theGraph.addEdge(3, 5, 18); // D-F
        theGraph.addEdge(3, 6, 2); // D-G

        theGraph.addEdge(4, 5, 2); // E-F
        theGraph.addEdge(3, 5, 18); // D-F
        theGraph.addEdge(3, 6, 2); // D-G
        theGraph.addEdge(6, 7, 3); // G-H
        var mst = theGraph.computeMinimumSpanningForest();
        if (mst.getEdgeCount() != 7) {
            // Hmm, do we count 1x or 2x for undirected edges
            return "E1001";
        }
        if (mst.getEdge(0, 7) != 4.0) {
            return "E1002";
        }
        if (mst.getEdge(6, 7) != 3.0) {
            return "E1003";
        }
        if (mst.getEdge(3, 6) != 2.0) {
            return "E1004";
        }
        if (mst.getEdge(2, 3) != 3.0) {
            return "E1005";
        }
        if (mst.getEdge(1, 2) != 4.0) {
            return "E1006";
        }
        if (mst.getEdge(2, 5) != 3.0) {
            return "E1007";
        }
        if (mst.getEdge(4, 5) != 2.0) {
            return "E1008";
        }
        return "";
    }

}
