package com.morshed.tree.avl;

import java.util.LinkedList;
import java.util.Queue;

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

    private Node rightRotate(Node unbalancedNode){
        Node leftNode = unbalancedNode.left;
        unbalancedNode.left = leftNode.right;
        leftNode.right = unbalancedNode;
        unbalancedNode.height = Math.max(getHeight(unbalancedNode.left), getHeight(unbalancedNode.right))+1;
        leftNode.height = Math.max(getHeight(leftNode.left), getHeight(leftNode.right))+1;
        return leftNode;
    }

    private Node leftRotate(Node unbalanceNode){
        Node rightChild = unbalanceNode.right;
        unbalanceNode.right = rightChild.left;
        rightChild.left = unbalanceNode;
        rightChild.height = Math.max(getHeight(rightChild.left), getHeight(rightChild.right))+1;
        unbalanceNode.height = Math.max(getHeight(unbalanceNode.left), getHeight(unbalanceNode.right))+1;
        return rightChild;
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

    public void bfs(Node root){
        if(root == null)
            return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node node = queue.poll();
            System.out.println(node.key + " ");
            if(node.left != null )
                queue.add(node.left);
            if(node.right!=null)
                queue.add(node.right);
        }
    }

    public void dfsPreOrder(Node root){
        if(root == null)
            return;
        System.out.println(root.key);
        dfsPreOrder(root.left);
        dfsPreOrder(root.right);
    }

    public void dfsInOrder(Node root){
        if(root == null)
            return;
        dfsInOrder(root.left);
        System.out.println(root.key);
        dfsInOrder(root.right);
    }

    public void dfsPostOrder(Node root){
        if(root == null)
            return;
        dfsPostOrder(root.left);
        dfsPostOrder(root.right);
        System.out.println(root.key);
    }



}
