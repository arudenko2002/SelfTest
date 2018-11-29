package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class task {
	Hashtable<String,Record> data = new Hashtable<String,Record>();
	public task(String filename) {
		
		FileInputStream fis = null;
        BufferedReader reader = null;

		try {
            fis = new FileInputStream(filename);
            reader = new BufferedReader(new InputStreamReader(fis));
            //String schema = reader.readLine();
            String line="";
            while(line != null){
                line = reader.readLine();
                System.out.println("LINE="+line);
                if(line==null) break;
                String[] vals=line.split(",");
                String date=vals[0];
                String device=vals[1];
                String user=vals[2];
                String channel=vals[3];
                Record record;
                //System.out.println(device);
                if (data.containsKey(device)) {
                	//System.out.println("AAAAAAAAAAAAAAA");
                	record = data.get(device);
                	record.add(date,user,channel);
                	data.put(device,record);
                } else {
                	record = new Record(date,device,user,channel);
                	data.put(device,record);
                }
                
            }  
          
        } catch (FileNotFoundException ex) {
           System.out.println("file not found! "+filename); 
        } catch (IOException ex) {
        	System.out.println("IO error "+filename); 
        }
	}
	
	public void recordPath(String filename) {
		try {
	          File file = new File(filename+".path.txt");
	          BufferedWriter output = new BufferedWriter(new FileWriter(file));
		        for(String key: data.keySet()){
		        	Record r = data.get(key);
		        	String line=r.getPath();
		        	output.write(line+"\n");
		        }
		        output.close();
		      } catch ( IOException e ) {
		         e.printStackTrace();
		      }
	}
	
	public void recordUserPath(String filename) {
		TreeMap<String,TreeMap> result = new TreeMap<String,TreeMap>();
		for(String key: data.keySet()){
        	Record r = data.get(key);
        	String user = r._user;
        	TreeMap<String,String> tms = r._ts;
        	TreeMap<String,String> tmd;
        	if (result.containsKey(user)) {
        		tmd = result.get(user);
        	} else {
        		tmd = new TreeMap<String,String>();
        		result.put(user,tmd);
        	}
        	for (String key2: tms.keySet()) {
        		tmd.put(key2,tms.get(key2));
        	}
        }
		try {
            File file = new File(filename+".user_path.txt");
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            Record r = new Record();
	        for(String key: result.keySet()){
	        	TreeMap<String,String> tm = result.get(key);
	        	String user=key;
    			String channels=user+":"+r.getChannels(tm);
	        	output.write(channels+"\n");
	        }
	        output.close();
        } catch ( IOException e ) {
           e.printStackTrace();
        }
	}
	
	public void recordFreq(String filename) {
		TreeMap<String,Integer> tm = new TreeMap<String,Integer>();
		for(String key: data.keySet()) {
			Record r = data.get(key);
			TreeMap<String,String> tm2 = r._ts;
			String channels = r.getChannels(tm2);
			System.out.println("f="+channels);
			if (tm.containsKey(channels)) {
				int i = tm.get(channels)+1;
				tm.put(channels,i);
			} else {
				tm.put(channels,1);
			}
		}
		TreeMap<Integer,ArrayList<String>> tm2 = new TreeMap<Integer,ArrayList<String>>();
		for(String key: tm.keySet()) {
			int val = -tm.get(key);
			if(tm2.containsKey(val)) {
				ArrayList<String> ar = tm2.get(val);
				ar.add(key);
			} else {
				ArrayList<String> ar = new ArrayList<String>();
				ar.add(key);
				tm2.put(val,ar);
			}
			System.out.println("GGG="+val+" "+key) ;
		}
		try {
			File file = new File(filename+".channel_freq.txt");
	        BufferedWriter output = new BufferedWriter(new FileWriter(file));
	        int counter=0;
	        boolean flag=true;
			for(Integer key: tm2.keySet()) {
				ArrayList<String> ar = tm2.get(key);
				for (int i=0; i<ar.size();i++) {
					counter++;
					if(counter>20) {
						flag=false;
						break;
					}
				   String line=ar.get(i)+": "+(-key)+"\n";
				   output.write(line);
				}
				if(!flag)break;
			}
			output.close();
		} catch ( IOException e ) {
	        e.printStackTrace();
	    }
	}

	/**
	 * @param args
	 */
	public class Record {
		public String _user="";
		public String _device="";
		public TreeMap<String,String> _ts = new TreeMap<String,String>();
		public Record(){}
		public Record(String date,String device, String user, String channel) {
			_user=user;
			_device=device;
			_ts.put(date,channel);
		}
		
		public void add(String date,String user, String channel) {
			if (user.length()>0) _user=user;
			_ts.put(date,channel);
			System.out.println(getPath());
		}
		
		public String getPath() {
			String result=_device+":"+getChannels(_ts);
			return result;
		}
		
		public String getChannels(TreeMap<String,String> tm) {
			String result="";
			int i=0;
			for(String key: tm.keySet()) {
				if (i>0) result += ",";i++;
				result += tm.get(key);
			}
			return result;
		}
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello test");
		String filename="sample.txt";
		filename="events.txt";
		task t = new task(filename);
		t.recordPath(filename);
		t.recordFreq(filename);
		t.recordUserPath(filename);
		
	}

}
