/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.farmingdale.m11minimumspanningtree;

/**
 * Union Find class--as discussed in class, allows you to, in O(lg n), 
 * check the "connectivity" of components and combine them in in 
 * O(lg n) time. Please see the class notes
 * @author gerstl
 */
public class UnionFind {
    /**
     * Node internal to a union find. 
     * Identity is the "name" of the node (usually the vertex number"
     * Height is the height of this node from the bottom (only matters for the
     * "root"
     * parent is the node above us in the tree
     */
    class ufNode {
        int identity;
        int height;
        ufNode parent;
        ufNode(){
            identity = -1;
            height =0;
            parent = null;
        }
    }
    ufNode ufNodes[];
    /** 
     * Constructor
     * @param vCount count of vertices (size of ufNode[]) 
     */
    public UnionFind(int vCount){
        ufNodes = new ufNode[vCount];
        for (int i = 0;i<vCount;++i){
            ufNodes[i] = new ufNode();
            ufNodes[i].identity = i;
            ufNodes[i].parent = ufNodes[i];
        }
    } // ctor
 
    
    /**
     * Finds a node and returns the "name" of the component it is in (the 
     * identity of the "root" of it's tree)
     * @param nodeNumber The node to start from 
     * @return the identity of the "root" of the node's tree
     */
    public int find(int nodeNumber){
        return 0;
    }
    
    /**
     * combines the trees containing the two nodes with as minimum height addition
     * as possible
     * @param nodeA the identity of the first node
     * @param nodeB the identity of the second node
     */
    public void union(int nodeA, int nodeB){
      
    }   // union
}
