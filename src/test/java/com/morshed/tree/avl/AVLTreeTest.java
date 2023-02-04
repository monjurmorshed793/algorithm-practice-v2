package com.morshed.tree.avl;

import org.junit.jupiter.api.Test;

import java.util.List;

public class AVLTreeTest {

    @Test
    public void testInsertion1(){
        AVLTree avl = new AVLTree();
        List.of(30, 20, 10).forEach( n -> {
            avl.root = avl.insert(avl.root, n);
        });
        avl.dfsPreOrder(avl.root);
    }

    @Test
    public void testInsertion2(){
        AVLTree avl = new AVLTree();
        List.of(33,53,9,21,61,8,11).forEach( n -> {
            avl.root = avl.insert(avl.root, n);
        });
        avl.dfsPreOrder(avl.root);
        System.out.println("---------");
        avl.dfsInOrder(avl.root);
        System.out.println("---------");
        avl.dfsPostOrder(avl.root);
        System.out.println("---------");
        avl.bfs(avl.root);
    }
}
