package TestSearch;

import java.util.ArrayList;

public class TestSearch {
	
	public class Node {
		public int val=-1;
		public ArrayList<Node> legs= new ArrayList<Node>();
		public Node(int n) {
			val=n;
		}
	}
	
	public Node makeTree() {
		Node n3 = new Node(3);
		Node n1= new Node(1);
		Node n4 = new Node(4);
		Node n2 = new Node(2);
		Node n5 = new Node(5);
		Node n7 = new Node(7);
		Node n9 = new Node(9);
		Node n12 = new Node(12);
		Node n20 = new Node(20);
		Node n15 = new Node(15);
		Node n10 = new Node(10);
		n3.legs.add(n1);
		n4.legs.add(n3);
		n4.legs.add(n2);
		n5.legs.add(n4);
		n9.legs.add(n7);
		n10.legs.add(n9);
		n15.legs.add(n12);
		n15.legs.add(n20);
		n10.legs.add(n15);
		n5.legs.add(n10);
		return n5;
	}
	
	void printTreeBroad(Node n) {
		System.out.print("printTreeBreadth: ");
		ArrayList<Node> nodes = new ArrayList<Node>();
		nodes.add(n);
		printTreeBroad(nodes);
		System.out.println("\n");
	}
	
	void printTreeBroad(ArrayList<Node> nodes) {
		while(nodes.size()>0) {
			ArrayList<Node> tmp = new ArrayList<Node>();
			for(int i=0;i<nodes.size();i++) {
				System.out.print(nodes.get(i).val+" ");
				for(int j=0; j< nodes.get(i).legs.size();j++) {
					tmp.add(nodes.get(i).legs.get(j));
				}
			}
			nodes=tmp;
		}
	}
	
	void printTreeDepth(Node n) {
		
		System.out.print(n.val+" ");
		for(int i=0; i<n.legs.size();i++) {
			printTreeDepth(n.legs.get(i));
		}
	}
	
	void printTreeDepthLeftOrder(Node n) {
		if(n.legs.size()>0)
			printTreeDepthLeftOrder(n.legs.get(0));
		System.out.print(n.val+" ");
		if(n.legs.size()>1)
			printTreeDepthLeftOrder(n.legs.get(1));
	}

	void printTreeDepthInOrder(Node n) {
		System.out.print(n.val+" ");
		if(n.legs.size()>0)
			printTreeDepthInOrder(n.legs.get(0));
		if(n.legs.size()>1)
			printTreeDepthInOrder(n.legs.get(1));
	}
	
	void printTreeDepthRightOrder(Node n) {
		if(n.legs.size()>1)
			printTreeDepthRightOrder(n.legs.get(1));
		System.out.print(n.val+" ");
		if(n.legs.size()>0)
			printTreeDepthRightOrder(n.legs.get(0));
	}
	
	void printLeavesOnly(Node n) {
		if (n.legs.size()==0) {
			System.out.print(n.val+"  ");
		}
		for(int i=0; i< n.legs.size();i++) {
			printLeavesOnly(n.legs.get(i));
		}
	}
	
	int printLeafN(Node n,int m, int counter) {
		if (m < counter) {
			return counter;
		}
		if(n.legs.size()==0) {
			if(m==counter) {
				System.out.println(m+": "+n.val);
				counter++;
				return counter;
			} else {
				counter++;
			}
		}
		for(int i=0; i< n.legs.size();i++) {
			counter = printLeafN(n.legs.get(i),m,counter);
		}
		return counter;
	}
	
	int counter=0;
	
	int getLeafN(Node n, int m) {
		//System.out.println(m+"   "+counter+"   "+n.val);
		if(n.legs.size()==0) {
			if(m==counter) {
				//counter++;
				return n.val;
			} else {
				counter++;
			}
		}
		for(int i=0;i<n.legs.size();i++) {
			int r = getLeafN(n.legs.get(i),m);
			if(r!=-1) return r;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello Search");
		TestSearch ts = new TestSearch();
		Node n = ts.makeTree();
		ts.printTreeBroad(n);
		System.out.print("printTreeDepth: ");
		ts.printTreeDepth(n);System.out.println("\n");
		System.out.print("printTreeDepthLeftOrder: ");
		ts.printTreeDepthLeftOrder(n);System.out.println("\n");
		System.out.print("printTreeDepthInOrder: ");
		ts.printTreeDepthInOrder(n);System.out.println("\n");
		System.out.print("printTreeDepthRightOrder: ");
		ts.printTreeDepthRightOrder(n);System.out.println("\n");
		System.out.print("printLeavesOnly: ");
		ts.printLeavesOnly(n);System.out.println("\n");
		System.out.println("print Leave N: ");
		int counter=0;
		int m=0;
		counter = ts.printLeafN(n, m, 0);
		if(counter-1<m) {
			System.out.println(m+" not found");
		}
		ts.counter=0;
		int rez = ts.getLeafN(n, m);
		System.out.println("LEAF="+rez+" number="+m);
		
		m=3;
		counter = ts.printLeafN(n, m, 0);
		if(counter-1<m) {
			System.out.println(m+" not found");
		}
		ts.counter=0;
		rez = ts.getLeafN(n, m);
		System.out.println("LEAF="+rez+" number="+m);
		
		m=5;
		counter = ts.printLeafN(n, m, 0);
		if(counter-1<m) {
			System.out.println(m+" not found");
		}
		ts.counter=0;
		rez = ts.getLeafN(n, m);
		System.out.println("LEAF="+rez+" number="+m);
	}

}
