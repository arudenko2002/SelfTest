package misc;

public class misc {

	int findCommonDividor(int n,int m) {
		int rem=n;
		while(true) {
			rem = n % m;
			//System.out.println(n+"  "+m+" rem "+rem);
			if (rem==0) break;
			n=m;
			m=rem;
		}
		//System.out.println(n+"  "+m+" rem");
		return m;
	}
	
	void test_findCommonDividor() {
		int d = findCommonDividor(100,50);
		System.out.println("dev="+d);
		int d2 = findCommonDividor(125,50);
		System.out.println("dev="+d2);
		int d3 = findCommonDividor(125,1);
		System.out.println("dev="+d3);
		int d4 = findCommonDividor(125,125);
		System.out.println("dev="+d4);
		int d5 = findCommonDividor(625,125);
		System.out.println("dev="+d5);		
		System.out.println("dev="+findCommonDividor(625,500));	
		System.out.println("dev="+findCommonDividor(625,501));	
		System.out.println("dev="+findCommonDividor(625,11));	
		System.out.println("dev="+findCommonDividor(11,625));
	}
	
	String digitToNumber(int n, int base) {
		String[] digits={"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"}; 
		String result="";
		while(n>0) {
			int digit2 = n % base;
			String digit=digits[digit2];
			result = digit+result;
			n/=base;
		}
		return result;
	}
	
	String Hex2Dec1(String s) {
		String[] digits={"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
		String r="";
		for(int j=0; j<digits.length;j++) {
			if(s.equals(digits[j])) {
				r=""+j;
				break;
			}
		}
		return r;
	}
	
	String Hex2Dec2(String s) {
		int r = s.codePointAt(0);
		if(r>=65 && r<=90)r-=55; 
		if(r>=48 && r<=57)r-=48;
		return ""+r;
	}
	
	int NumberToDigit(String n,int base) {
		int result=0;
		for(int i=0; i<n.length();i++) {
			String s = n.substring(i,i+1);
			s = Hex2Dec2(s);
			int b=1;
			for(int j=0;j<n.length()-i-1;j++) b*=base;
			result += Integer.parseInt(s)*b;
		}
		return result;
	}
	
	void bestTimeToBuySell() {
		int[] prices={11,10,4,7,2,4,8,10,39,3,5,2,7,44,1};
		int minp=1000;
		int mini=-1;
		int maxp=-1;
		int maxi=-1;
		int maxdiff=0;
		int rezmin=-1;
		int rezmax=-1;
		for(int i=0; i<prices.length-1;i++) {
			if(minp>prices[i]) {
				minp=prices[i];
				mini=i;
			}
			if(maxp<prices[i+1]) {
				maxp=prices[i+1];
				maxi=i+1;
			}
			int diff=maxp-minp;
			if (maxdiff<diff) {
				maxdiff=diff;
				rezmin=mini;
				rezmax=maxi;
			}
		}
		System.out.println("result= "+rezmin+"  "+rezmax+"   diff="+maxdiff);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		misc m = new misc();
		m.test_findCommonDividor();
		System.out.println("number to digit: "+m.digitToNumber(12000, 16));
		System.out.println("number to digit: "+m.NumberToDigit("2EE0",16));
		m.bestTimeToBuySell();
	}

}
