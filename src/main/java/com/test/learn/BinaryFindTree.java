package com.test.learn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class BinaryFindTree {
	static class TreeNode{
		TreeNode left;
		TreeNode right;
		int element;
		
		public TreeNode(int e){
			this.element = e;
			this.left = null;
			this.right = null;
		}
	}
	
	public static void main(String[] args) {
		List<Integer> nodeList = new ArrayList<Integer>();
		int[] nodeArr = new int[]{4,5,2,3,1,9};
		for(int i=0;i<nodeArr.length;i++){
			nodeList.add(nodeArr[i]);
		}
		BinaryFindTree tree = new BinaryFindTree();
		tree.createTree(nodeList);
//		tree.preTravels(tree.root);
//		tree.midTravels(tree.root);
//		tree.aftTravels(tree.root);
//		tree.preTravelsByStack(tree.root);
//		tree.midTravelsByStack(tree.root);
//		tree.aftTravelsByStack(tree.root);
		tree.layerTravels(tree.root);
		for(int ele : tree.nodeList){
			System.out.print(ele + " ");
		}
		System.out.println("\n");
//		TreeNode findNode = tree.findKey(0);
//		System.out.println(findNode.element);
		TreeNode minNode = tree.getMinNode(tree.root);
		System.out.println(minNode.element);
	}
	
	TreeNode root;
	int size = 0;
	List<Integer> nodeList = new ArrayList<Integer>();
	
	public int createTree(List<Integer> nodeList){
		if(nodeList == null){
			return 0;
		}
		for(Integer element : nodeList){
			insert(element);
		}
		return size;
	}
	
	public boolean insert(int e){
		boolean flag = true;
		TreeNode newNode = new TreeNode(e);
		if(root == null){
			root = newNode;
		}else{
			TreeNode current = root;
			TreeNode parent = null;
			while(current!=null){
				if(e < current.element){
					parent = current;
					current = current.left;
					if(current == null){
						parent.left = newNode;
						size ++;
						break;
					}
				}else if(e > current.element){
					parent = current;
					current = current.right;
					if(current == null){
						parent.right = newNode;
						size ++;
						break;
					}
				}else{
					flag = false;
				}
			}
		}
		return flag;
	}
	//前序遍历递归方式
	public void preTravels(TreeNode root){
		if(root == null){
			return;
		}
		nodeList.add(root.element);
		if(root.left != null) preTravels(root.left);
		if(root.right != null) preTravels(root.right);
	}
	//中序遍历递归方式
	public void midTravels(TreeNode root){
		if(root == null){
			return;
		}
		if(root.left != null) midTravels(root.left);
		nodeList.add(root.element);
		if(root.right != null) midTravels(root.right);
	}
	//后序遍历递归方式
	public void aftTravels(TreeNode root){
		if(root == null){
			return;
		}
		if(root.left != null) aftTravels(root.left);
		if(root.right != null) aftTravels(root.right);
		nodeList.add(root.element);
	}
	
	//前序遍历栈实现（模拟递归）
	public void preTravelsByStack(TreeNode root){
		if(root == null){
			return;
		}
		TreeNode current = root;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while(current !=null || !stack.isEmpty()){
			while(current!=null){
				nodeList.add(current.element);
//				System.out.println(current.element);
				stack.push(current);//先访问再入栈
				current = current.left;
			}
			current = stack.pop();
			current = current.right;
		}
	}
	
	//中序遍历栈实现（模拟递归）
	public void midTravelsByStack(TreeNode root){
		if(root == null){
			return;
		}
		TreeNode current = root;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while(current != null || !stack.isEmpty()){
			while(current != null){
				stack.push(current);
				current = current.left;
			}
			current = stack.pop();
			nodeList.add(current.element);
			current = current.right;
		}
	}
	/*后序遍历栈实现（模拟递归）后序遍历不同于先序和中序，
	它是要先处理完左右子树，然后再处理根(回溯)，
	所以需要一个记录哪些节点已经被访问的结构(可以在树结构里面加一个标记)，这里可以用map实现 */
	public void aftTravelsByStack(TreeNode root){
		if(root == null){
			return;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		Set<TreeNode> set = new HashSet<TreeNode>();
		while(!stack.isEmpty()){
			TreeNode temp = stack.peek();
			if(temp.left !=null && !set.contains(temp.left)){
				 temp = temp.left;
				 while(temp!=null){
					 if(set.contains(temp))break;
					 else stack.push(temp);
			         temp = temp.left;
				 }
				continue;
			}
			if(temp.right !=null && !set.contains(temp.right)){
				stack.push(temp.right);
				continue;
			}
			TreeNode lastNode = stack.pop();
			set.add(lastNode);
			nodeList.add(lastNode.element);
		}
		
	}
	
	//层序遍历
	public void layerTravels(TreeNode root){
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		TreeNode current = root;
		queue.add(current);
		while(!queue.isEmpty()){
			TreeNode node = queue.poll();
			nodeList.add(node.element);
			if(node.left != null)queue.add(node.left);
			if(node.right !=null)queue.add(node.right);
		}
	}
	
	public TreeNode findKey(int ele){
		TreeNode current = root;
		while(current !=null){
			if(ele < current.element){
				current = current.left;
			}else if(ele > current.element){
				current = current.right;
			}else{
				return current;
			}
		}
		return null;
	}

	public TreeNode getMinNode(TreeNode node){
		if(node == null){
			return null;
		}
		TreeNode current = node;
		TreeNode parent = null;
		while(current != null){
			parent = current;
			current = current.left;
		}
		return parent;
	}
}
