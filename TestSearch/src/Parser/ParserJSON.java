package Parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParserJSON {
	static int token_length=0;
	static int token_index=0;
	static int verbosity=1;
	public ParserJSON(){}
	static public String setJSON(String fileName) {
		StringBuffer sb = new StringBuffer();
		try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            String line="";
            
            while((line = bufferedReader.readLine()) != null) {
            	if(line.startsWith("#"))continue;
                if(verbosity>1)System.out.println(line);
                sb.append(line+"\n");
            }   
            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '"  + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
		return sb.toString();
	}

	static private void exit(String msg) {
		System.out.println(msg);
		System.exit(1);
	}
	
	static public void close() {
		token_length=0;
		token_index=0;
		verbosity=1;
	}
	
	static boolean isKey(String s) {
		if(s.length()==0) return false;
		return true;
	}
	
	static Object getValue(String v) {
		Object result=new Object();
		if(v.startsWith("\"") && v.endsWith("\"")) {
			return v.replace("\"", "");
		}
		if(v.equals("true") || v.equals("false")) {
			return new Boolean(v);
		}
		if(v.equals("null")) return null;
		
		try {
			Integer i=Integer.parseInt(v);
			return i;
		} catch(Exception e) {}
		try {
			Double d=Double.parseDouble(v);
			return d;
		} catch(Exception e) {}
		// I allowed a string w/o quotes to be an acceptable value
		return v;
	}
	
	static Object parseObject(String[] ss) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		if(isKey(ss[token_index]) && ss[token_index+1].equals(":")) {
			boolean flag=true;
			while(flag) {
				flag=false;
				if(!isKey(ss[token_index])) exit("error in the json structure");
				
				String key=ss[token_index].replace("\"","");

				if(ss[token_index+2].equals("[")) {
					// Call array
					token_index=token_index+2;
					map.put(key, parseArray(ss));
				} else if(ss[token_index+2].equals("{")) {
					// Call Object
					token_index=token_index+3;
					map.put(key, parseObject(ss));
				} else {
					token_index=token_index+2;
					Object value=getValue(ss[token_index]);
					map.put(key, value);
					if(verbosity>1)System.out.println("Object key="+key+" value="+map.get(key));
				}
				//Or ',' or closing
				if(ss[token_index+1].equals(",")) {
					token_index=token_index+2;
					flag=true;
				} else {
					token_index=token_index+1;
				}
			}
		} else {
			exit("wrong format in parseObject");
		}
		return map;
	}
	
	static Object parseArray(String[] ss) {
		ArrayList<Object> arr=new ArrayList<Object>();
		
		if(ss[token_index].equals("[")) {		
			boolean flag=true;
			while(flag) {
				flag=false;
				if(ss[token_index+1].equals("[")) {
					// Call array
					token_index=token_index+1;
					arr.add(parseArray(ss));
				} else if(ss[token_index+1].equals("{")) {
					// Call array
					token_index=token_index+2;
					arr.add(parseObject(ss));
				} else {
					token_index=token_index+1;
					Object value=getValue(ss[token_index]);
					arr.add(value);
					if(verbosity>1)System.out.println("array value="+ss[token_index].replace("\"",""));
				}
				//Or ',' or closing
				if(ss[token_index+1].equals(",")) {
					token_index=token_index+1;
					flag=true;
				} else {
					token_index=token_index+1;
				}
			}
		} else {
			exit("wrong format in parseArray");
		}
		return arr;
	}
	
	static private String[] tokenize(String s) {
		ArrayList<String> ar = new ArrayList<String>();
		String token="";
		for(int i=0; i<s.length();i++) {
			String ch=s.substring(i,i+1);
			if(ch.equals("{")||ch.equals("}")||ch.equals("[")||ch.equals("]")||ch.equals(",")||ch.equals(":")) {
				if(token.length()>0) {
					ar.add(token.trim());
					token="";
				}
				ar.add(ch);
			} else {
				if(ch.length()>0 && !ch.equals("\n") && !ch.equals("\t"))
					token=token+ch;
			}
		}
		return ar.toArray(new String[ar.size()]);
	}
	
	static public Object parse(String jsonsource) {
		if(jsonsource==null || jsonsource.length()==0)exit("no json info!");
		if(verbosity>0)System.out.println(jsonsource);
		String[] a_tokens=tokenize(jsonsource);
		
		if(verbosity>1)
		for(int i=0; i<a_tokens.length;i++) {
			System.out.println(a_tokens[i]);
		}
		
		token_length=a_tokens.length;
		Object result=new Object();
		if (a_tokens[0].equals("{") && a_tokens[token_length-1].equals("}")) {
			token_index++;
			result = parseObject(a_tokens);
			if (token_index+1 != a_tokens.length) {
				System.out.println("JSON parser failes  1="+(token_index+1)+" 2="+a_tokens.length);
				exit("Json is underprocessed");
			}
		} else
			exit("Json must be within {} clouse");
		close();
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello Parser!");
		
		String json = ParserJSON.setJSON("data\\f1.json");
		HashMap<String,Object> output = (HashMap<String,Object>)ParserJSON.parse(json);
		System.out.println("result="+output);
		System.out.println("result="+output+"    fragment= "+output.get("debug"));
		System.out.println("result="+output+"    fragment= "+((HashMap<String,Object>)output.get("windows")).get("size"));
		
		String jsonsource = ParserJSON.setJSON("data\\f0.json");
		Object o = ParserJSON.parse(jsonsource);
		System.out.println("result="+o);
		System.out.println("result="+o+"    fragment= "+((HashMap<String,Object>)o).get("aaa"));
		System.out.println("result="+o+"    fragment= "+((HashMap<String,Object>)o).get("ccc"));
		System.out.println("result="+o+"    fragment= "+((List<Object>)((HashMap<String,Object>)((List<Object>)((HashMap<String,Object>)o).get("eee")).get(2)).get("jjj")).get(0));
		Object oo = ((HashMap<String,Object>)o).get("eee");
		System.out.println("result="+o+"    fragment= "+((List<Object>)oo).get(2));
		Object ooo = ((HashMap<String,Object>)((List<Object>)oo).get(2)).get("jjj");
		System.out.println("result="+o+"    fragment= "+((List<Object>)ooo).get(0));
	}

}
