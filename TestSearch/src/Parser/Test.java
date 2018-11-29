package Parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
public class Test {
	private Date m_time;
	private String m_name;
	private List<Integer> m_numbers;
	private List<String> m_strings;
	
	//  I added hashmaps to use them instead of arrays
	private HashMap<String,Object> h_strings=new HashMap<String,Object>();
	private HashMap<Integer,Object> h_numbers=new HashMap<Integer,Object>();
	
	
	public Test(Date time,String name,List<Integer> numbers,List<String> strings) {
		m_time=time;
		m_name=name;
		m_numbers=numbers;
		m_strings=strings;
		
		// Filling the hashmaps for string and numbers.  It is better to make it once than
		// to do it every time when we need to remove or find a value
		for(int i=0; i< numbers.size();i++) {
			h_numbers.put(numbers.get(i),null);
		}
		for(int i=0; i< strings.size();i++) {
			h_strings.put(strings.get(i),null);
		}
	}
	

	public boolean equals(Object obj) {
		try {
			return m_name.equals(((Test)obj).m_name);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	// Error handling looks overkill here as Java has inbuilt mechanism to recognize 
	// the class hidden in a Object
	public boolean equalsNEW(Object obj) {
		if (obj instanceof Test && m_name.equals(((Test)obj).m_name))
				return true;
		return false;
	}
	
	public String toString() {
		String out = m_name+m_numbers.toString();
		return out;
	}
	
	// I assume here that the output is required "as is" and should not be any different.
	// The only benefit here can be brought using StringBuffer as it is mutable and allows more
	// efficient memory management than addition of String
	public String toStringNEW() {
		StringBuffer sb = new StringBuffer();
		sb.append(m_name);
		sb.append(m_numbers.toString());
		return sb.toString();
	}
	
	// Another version of the function toString above to work with the hashmap
	// I make the output similar to the original one.
	public String toStringNEWNEW() {
		StringBuffer sb = new StringBuffer();
		sb.append(m_name);
		sb.append("[");
		int counter=0;
		for (Integer key : h_numbers.keySet()) {
			if (counter>0)
				sb.append(", ");
			counter++;
			sb.append(key);
		}
		sb.append("]");
		return sb.toString();
	}
	
	public void removeString(String str) {
		m_strings.remove(str);
	}

	// Using hashmap allows to achieve the removal faster than traversing the whole array 
	// looking for the element to remove
	public void removeStringNEW(String str) {
		h_strings.remove(str);
	}
	
	public boolean containsNumber(int number) {
		return m_numbers.contains(number);
	}

	// Using hashmap allows to achieve the element existence verification faster than traversing the whole array 
	// looking for a specific element
	public boolean containsNumberNEW(int number) {
		return h_numbers.containsKey(number);
	}
	
	public boolean isHistoric() {
		return m_time.before(new Date());
	}
	// before() is actually more efficient than compareTo() for this interface because it does not need to deal with 3 possible outputs
	public boolean isHistoricNEW() {
		System.out.println("TIME DIFF: "+m_time.compareTo(new Date()));
		return m_time.compareTo(new Date())<0;
	}
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d=null;
		try {
			d = sdf.parse("2009-12-31");
		} catch(ParseException e) {}
		String name="Lesha";
		List<Integer> lnumbers=new ArrayList<Integer>();
		lnumbers.add(new Integer(1));
		lnumbers.add(new Integer(2));
		lnumbers.add(new Integer(3));
		List<String> lstrings = new ArrayList<String>();
		lstrings.add("aaa");
		lstrings.add("bbb");
		lstrings.add("ccc");
		Test t = new Test(d, name,lnumbers,lstrings);
		
		String name2="Lesha";
		List<Integer> lnumbers2=new ArrayList<Integer>();
		lnumbers2.add(new Integer(1));
		lnumbers2.add(new Integer(2));
		lnumbers2.add(new Integer(3));
		List<String> lstrings2 = new ArrayList<String>();
		lstrings2.add("aaa");
		lstrings2.add("bbb");
		lstrings2.add("ccc");
		Test t2 = new Test(new Date(), name2,lnumbers2,lstrings2);
	    
		
		System.out.println("EQUALS:"+t.equals((Object)t2));
		System.out.println(t.m_name);
		System.out.println("EQUALS_new:"+t.equalsNEW((Object)t2));
		System.out.println(t.m_name);
		
		System.out.println("TO STRING:"+t.toString());
		System.out.println("TO STRINGNEW:"+t.toStringNEW());
		System.out.println("TO STRINGNEWNEW:"+t.toStringNEWNEW());
		t.removeString("bbb");
		
		
		System.out.println("REMOVED:"+t.m_strings);
		System.out.println("CONTAINS:"+t.containsNumber(22));
		System.out.println("CONTAINS:"+t.containsNumber(2));
		
		t.removeStringNEW("aaa");
		System.out.println("REMOVED:"+t.m_strings);
		System.out.println("CONTAINS:"+t.containsNumberNEW(22));
		System.out.println("CONTAINS:"+t.containsNumberNEW(2));	
		
		System.out.println("isHISTORIC:"+t.isHistoric());
		System.out.println("isHISTORICNEW:"+t.isHistoricNEW());
		
		System.out.println("isHISTORIC:"+t2.isHistoric());
		System.out.println("isHISTORICNEW:"+t2.isHistoricNEW());
	}

}
