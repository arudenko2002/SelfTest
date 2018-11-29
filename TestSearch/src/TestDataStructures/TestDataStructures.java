package TestDataStructures;

import java.util.ArrayList;

public class TestDataStructures {
	public class HashTable {
		int Size=100;
		ArrayList<ArrayList<datacell>> data=new ArrayList<ArrayList<datacell>>();
		public HashTable() {
			for(int i=0; i< Size; i++) {
				ArrayList<datacell> ar = new ArrayList<datacell>();
				data.add(ar);
			}
		}
		int getHash(String s) {
			int v = Integer.parseInt(s);
			return v%100;
		}
		void push(String key,String s) {
			int v = getHash(s);
			data.get(v).add(new datacell(key,s));
			
		}
		String get(String s) {
			String result=null;
			int v = getHash(s);
			ArrayList<datacell> rez = data.get(v);
			if (rez.size()==1 && rez.get(0).key.equals(s))
				result = rez.get(0).data;
			else if(rez.size()>1) 
				for(int i=0; i<rez.size();i++) {
					datacell dc=rez.get(i);
					if(dc.key.equals(s)) result = dc.data;
				}
			else
				result = null;
			return result;
		}
		void remove(String s) {
			int v = getHash(s);
			ArrayList<datacell> rez = data.get(v);
			if (rez.size()==1 && rez.get(0).key.equals(s))
				data.get(v).remove(0);
			else if(rez.size()>1) 
				for(int i=0; i<rez.size();i++) {
					datacell dc=rez.get(i);
					if(dc.key.equals(s)) data.get(v).remove(i);
				}
			else {}
		}
		
		boolean ifexists(String s) {
			boolean result = false;
			int v = getHash(s);
			ArrayList<datacell> rez = data.get(v);
			if (rez.size()==1 && rez.get(0).key.equals(s))
				result = true;
			else if(rez.size()>1) 
				for(int i=0; i<rez.size();i++) {
					datacell dc=rez.get(i);
					if(dc.key.equals(s)) result = true;
				}
			else
				result = false;
			return result;
		}
		
		class datacell {
			String data="";
			String key="";
			public datacell(String _key, String _data) {
				key=_key;
				data=_data;
			}
		}
	}
	
	void hashtable_test() {
		HashTable ht = new HashTable();
		System.out.println("Hashtable:");
		ht.push("666","666");
		System.out.println(ht.get("666"));
		ht.remove("666");
		System.out.println(ht.get("666"));
		System.out.println(ht.get("777"));
		//CONFLICT Find how to resolve it
		ht.push("666","666");
		ht.push("766","766");
		System.out.println(ht.get("666"));
		System.out.println(ht.get("766"));
		System.out.println("End of Hashtable");
	}
	
	public class linklist {
		public int val = 0;
		public linklist l = null;
		linklist(int n) {
			val=n;
		}
		
		public int printll(linklist l) {
			int count=0;
			System.out.print("In linklist: ");
			while(l!=null) {
				System.out.print(l.val+"  ");
				l=l.l;
				count++;
			}
			System.out.println("\n");
			return count;
		}
		
		public boolean equals(linklist ll) {
			//System.out.println(this);
			//System.out.println(ll);
			//Equal by values
			//return val==ll.val;
			// Equal as objects
			return this==ll;
		}
		
		public linklist shiftll(linklist ll, int n) {
			int count=0;
			while(ll!=null) {
				if(n==count) return ll;
				System.out.println("shift "+ll.val);
				ll=ll.l;
				count++;
			}
			return null;
		}
		public linklist findIntersection(linklist l1, linklist l2) {
			while(l1!=null) {
				if(l1.equals(l2))return l1;
				l1=l1.l;
				l2=l2.l;
			}
			return null;
		}
		
		public void removeNodeNotFirstButOnlyKnown(linklist ll) {
			if(ll!=null && ll.l!=null) {
				ll.val=ll.l.val;
				ll.l=ll.l.l;
			} else {
				System.out.println("wrong node or last node!");
			}
		}
	}
	
	void linklist_test() {
		linklist l1 = new linklist(1);
		linklist l2 = new linklist(2);
		linklist l3 = new linklist(3);
		linklist l4 = new linklist(4);
		linklist l5 = new linklist(5);
		l4.l=l3;
		l3.l=l2;
		l2.l=l1;
		l5.l=l2;
		//Print LL1
		//l4.printll(l4);
		//Print LL2
		//l5.printll(l5);
		//Count # of nodes
		//System.out.println("node_count="+l4.printll(l4));
		//System.out.println("node_count="+l5.printll(l5));
		//Find intersection n time
		System.out.println("Linklists");
		System.out.println("Intersection of 2 linklists:");
		int c1=l4.printll(l4);
		int c2=l5.printll(l5);
		if(c1>c2) {
			System.out.println("l4 shorter"+(c1-c2));
			l4 = l4.shiftll(l4,c1-c2);
		}
		if(c2>c1) {
			System.out.println("l5 shorter"+(c2-c1));
			l5 = l5.shiftll(l5,c2-c1);
		}
		l4.printll(l4);
		l5.printll(l5);
		linklist l = l4.findIntersection(l4,l5);
		System.out.println("Result Intersection="+l.val);
		
		l4.printll(l4);
		// 3 2 1
		linklist ll = l4.shiftll(l4,1);
		l4.printll(ll);
		System.out.println("Remove Node Not First But Only Known 1:");
		// 2 1
		l4.removeNodeNotFirstButOnlyKnown(ll); // ll.val=2, after operation ll.val=1
		l4.printll(l4);
		// 3 1
		System.out.println("Remove Node Not First But Only Known 2:");
		linklist ll2 = l4.shiftll(l4,2);
		l4.removeNodeNotFirstButOnlyKnown(ll2); // ll.val=1, operation should fail as it is the last node
		l4.printll(l4);
		System.out.println("End of Linklists");
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestDataStructures ds = new TestDataStructures();
		ds.hashtable_test();
		ds.linklist_test();
	}

}
