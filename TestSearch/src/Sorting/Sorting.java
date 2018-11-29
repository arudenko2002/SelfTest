package Sorting;

import java.util.ArrayList;

public class Sorting {
	void printArray(String h, int[] ii) {
		System.out.print(h);
		for (int i=0; i<ii.length;i++) {
			System.out.print(ii[i]+" ");
		}
		System.out.print("\n\n");
	}
	
	
	void printA(String h, ArrayList<Integer> al) {
		System.out.print(h);
		for(int k=0; k< al.size();k++) {
			System.out.print(al.get(k)+"  ");
		}
		System.out.print("\n\n");
	}
	
	
	ArrayList<Integer> mergeSorted(int[] a1, int[] a2) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		int i=0;
		int j=0;
		while(i<a1.length || j<a2.length) {
			if (i==a1.length) {
				for(int ii=j;ii<a2.length;ii++) {
					al.add(a2[ii]);
					j++;
				}
				continue;
			}
			if (j==a2.length) {
				for(int ii=i;ii<a1.length;ii++) {
					al.add(a1[ii]);
					i++;
				}
				continue;
			}
			if(i<a1.length && j<a2.length) {
				if(a1[i]<a2[j]) {
					al.add(a1[i]);
					i++;
					continue;
				}
				if(a1[i]>a2[j]) {
					al.add(a2[j]);
					j++;
					continue;
				}
				if(a1[i]==a2[j]) {
					al.add(a1[i]);
					i++;
					al.add(a2[j]);
					j++;
					continue;
				}
			}
		}
		return al;
	}
	
	ArrayList<Integer> mergeSorted(ArrayList<Integer> a1, ArrayList<Integer> a2) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		
		int i=0;
		int j=0;
		while(i<a1.size() || j<a2.size()) {
			if (i==a1.size()) {
				for(int ii=j;ii<a2.size();ii++) {
					al.add(a2.get(ii));
					j++;
				}
				continue;
			}
			if (j==a2.size()) {
				for(int ii=i;ii<a1.size();ii++) {
					al.add(a1.get(ii));
					i++;
				}
				continue;
			}
			if(i<a1.size() && j<a2.size()) {
				if(a1.get(i)<a2.get(j)) {
					al.add(a1.get(i));
					i++;
					continue;
				}
				if(a1.get(i)>a2.get(j)) {
					al.add(a2.get(j));
					j++;
					continue;
				}
				if(a1.get(i)==a2.get(j)) {
					al.add(a1.get(i));
					i++;
					al.add(a2.get(j));
					j++;
					continue;
				}
			}
		}
		return al;

	}
	
	ArrayList<Integer> MergeSort(ArrayList<Integer> a) {
		if (a.size()==0 || a.size()==1) return a;
		if(a.size()==2) {
			ArrayList<Integer> result = new ArrayList<Integer>();
			if(a.get(0)>a.get(1)) 
				{result.add(a.get(1));result.add(a.get(0));}
			else 
				{result.add(a.get(0));result.add(a.get(1));}
			//printA("result=",result);
			return result;
		}
		int pivot = a.size()/2;
		ArrayList<Integer> a1 = new ArrayList<Integer>();
		ArrayList<Integer> a2 = new ArrayList<Integer>();
		for(int i=0; i<a.size();i++) {
			if(i<pivot) a1.add(a.get(i));
			if(i>=pivot) a2.add(a.get(i));
		}

		//printA("a1=",a1);
		//printA("a2=",a2);
		a1 = MergeSort(a1);
		a2 = MergeSort(a2);
		//printA("aaaaaa1==",a1);
		//printA("aaaaaa2==",a2);
		ArrayList<Integer> result = mergeSorted(a1,a2);
		//printA("rrrrrrr==",result);
		return result;
	}
	
	ArrayList<Integer> QuickSort(ArrayList<Integer> a) {
		if (a.size()==0 || a.size()==1) return a;
		int pivot=a.get(a.size()-1);
		ArrayList<Integer> left=new ArrayList<Integer>();
		ArrayList<Integer> right=new ArrayList<Integer>();
		for(int i=0; i<a.size()-1;i++) {
			if(a.get(i)<=pivot) {
				left.add(a.get(i));
			} else {
				right.add(a.get(i));
			}
		}
		a= new ArrayList<Integer>();
		ArrayList<Integer> a1 = QuickSort(left);
		ArrayList<Integer> a2 = QuickSort(right);
		a1.add(pivot);
		for(int i=0; i< a2.size();i++)a1.add(a2.get(i));
		return a1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello sort");
		int[] a1={5};
		int[] a2={7,9};
		Sorting srt=new Sorting();
		System.out.println("Merge Sorted Arrays");
		ArrayList<Integer> rr = srt.mergeSorted(a1,a2);
		srt.printA("result=", rr);
		int[] aa = {1,3,5,7,9,2,4,6,8,0};
		ArrayList<Integer> a = new ArrayList<Integer>();
        for (int i=0; i< aa.length;i++) {
        	a.add(aa[i]);
        }
        System.out.println("Merge Sort");
        ArrayList<Integer> mr = srt.MergeSort(a);
        srt.printA("Merge result=", mr);
        
        System.out.println("Quick Sort");
        ArrayList<Integer> qr = srt.QuickSort(a);
        srt.printA("Quick result=", qr);
	}

}
