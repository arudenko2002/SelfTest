package test;

import java.util.ArrayList;
import java.util.Hashtable;

public class test1 {
	public test1(){}
	public int getPair(int[] a) {
		int result=-666;
		Hashtable<Integer,Integer> ht = new Hashtable<Integer,Integer>();
		for(int i=0; i< a.length;i++) {
			ht.put(a[i],a[i]);
		}
		for(int i=0; i< a.length;i++) {
			int number=7-a[i];
			if(ht.containsKey(number)) {
				result=7-number;
				System.out.println("Yes it exists number="+number+" other number="+(7-number));
				break;
			}
		}
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a= {20,3,4,50,60,7};
		test1 t = new test1();
		int r = t.getPair(a);
        System.out.println("R="+r);
	}

}
