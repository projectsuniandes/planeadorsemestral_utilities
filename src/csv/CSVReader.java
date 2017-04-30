package csv;

import java.io.*;
import java.util.*;

public class CSVReader {
	static ArrayList<String> programs = new ArrayList<String>();
	static String csvFile = "./data/courses.csv";

	static int getIndex(String program){
		boolean be=false;
		for (int i = 0; i < programs.size()&&!be; i++) {
			if(programs.get(i).equals(program))
				return i+1;
		}
		return -1;
	}
	static void reWriteCourses() throws FileNotFoundException{
	
		PrintWriter pw = new PrintWriter(new File("./data/courses_new.csv"));
		StringBuilder builder = new StringBuilder();
		String ColumnNamesList = "course_code,name,program_id";
		
		
		builder.append(ColumnNamesList +"\n");
		
		
		
		String line="";
		BufferedReader br;
		br = new BufferedReader(new FileReader(csvFile));
          
        try {
			while ((line = br.readLine()) != null) {

			      // use comma as separator
				String[] course = new String[2];
				if(line.contains("\"")){
					line.trim().substring(0, line.length()-2);
					System.out.println(line);
					String[] spl= line.split("\"");
					System.out.println(spl.length);
					course[0]= spl[0].trim().replace(",", "");
					System.out.println("course[0]"+course[0]);
					course[1]= spl[1].replace(",", "");
					System.out.println("course[1]"+course[1]);
				}	
				else{
					course = line.split(",");
				}
				
			    String program = course[0].split(" ")[0].trim();
			    int index= getIndex(program);
			    
			    builder.append(course[0]+",");
				builder.append(course[1]+",");
				builder.append(index);
				builder.append('\n');
			      
			      

			}
			pw.write(builder.toString());
			pw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void writeCSV(){
		try {
			PrintWriter pw = new PrintWriter(new File("./data/programs.csv"));
			StringBuilder builder = new StringBuilder();
			String ColumnNamesList = "Id,Code";
			
			
			builder.append(ColumnNamesList +"\n");
			
			
			
			for (int i = 0; i < programs.size(); i++) {
				builder.append(i+1+",");
				builder.append(programs.get(i));
				builder.append('\n');
				
				System.out.println("writing...");
				
			}
			
			
			pw.write(builder.toString());
			pw.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void add(String program){
		boolean be=false;
		for (int i = 0; i < programs.size()&&!be; i++) {
			if(programs.get(i).equals(program))
				be=true;
		}
		if(!be)
		programs.add(program);
		
		
	}
	public static void main(String[] args) {
		

        
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        
        try {

            br = new BufferedReader(new FileReader(csvFile));
           
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] course = line.split(cvsSplitBy);
                String program = course[0].split(" ")[0].trim();
                
                add(program);

            }
            
            writeCSV();
            reWriteCourses();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
}
