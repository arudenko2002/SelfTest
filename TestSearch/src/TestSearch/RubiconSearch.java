package TestSearch;

import java.util.ArrayList;

public class RubiconSearch {

	public static boolean step(int index, int[] ar, String s) {
		boolean result=false;
		s=s+" "+index;
		int val = ar[index];
		if(val==0) {
			System.out.println("route="+s);
			return true;
		}
		if(val<0) return false;
		if(index-val>=0){
			result=step(index-val,ar,s);
		}
		if(result) {
			ar[index]=-1;
			return result;
		}
		if(index+val<ar.length){
			result=step(index+val,ar,s);
		}
		ar[index]=-1;
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] ar={1,4,1,2,0,1,1};
		boolean s = step(3,ar,"");
		System.out.println("AAAAAAA "+s);
		for(int i=0; i<ar.length;i++) {
			System.out.print("  "+ar[i]);
		}
	}

}
