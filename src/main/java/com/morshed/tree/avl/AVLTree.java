package com.morshed.tree.avl;

public class AVLTree {
    Node root;

    private int getHeight(Node node){
        if(node == null){
            return 0;
        }
        return node.height;
    }

    private int getBalance(Node node){
        if(node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    private Node rightRotate(Node y){
        Node x = y.left;
        Node t = x.right;
        x.right = y;
        y.left = t;
        y.height = Math.max(getHeight(y.left), getHeight(y.right))+1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right))+1;
        return x;
    }

    private Node leftRotate(Node x){
        Node y = x.right;
        Node t = y.left;
        y.left = x;
        x.right = t;
        y.height = Math.max(getHeight(y.left), getHeight(y.right))+1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right))+1;
        return y;
    }

    public Node insert(Node node, int key){
        if( node == null){
            return new Node(key);
        }

        if(key < node.key){
            node.left = insert(node.left, key);
        }
        else if(key > node.key){
            node.right = insert(node.right, key);
        }
        else {
            return node; // found duplicate, and it's not allowed, so return the node
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        int balance = getBalance(node);

        if(balance>1 && key< node.left.key){
            return rightRotate(node);
        }
        if(balance <-1 && key > node.right.key){
            return leftRotate(node);
        }
        if(balance>1 && key>node.left.key){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if(balance<-1 && key< node.right.key){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }
}
