package WC;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class LineCounter {
	long nullLine=0;
	long noteLine=0;
	long codeLine=0;
	InputStreamReader inputStreamReader;
	BufferedReader bufferedReader;
	boolean flag = true;//check /* is end
	String t = "";//读取一行的数据；
	LineCounter(File file){
		if(file.isFile()){
			try {
				inputStreamReader = new InputStreamReader(new FileInputStream(file));
				bufferedReader =  new BufferedReader(inputStreamReader);
				try {
					while((t=bufferedReader.readLine())!=null){
						if(Pattern.matches("([\\s*{]*)|[\\s*}]*",t)&&flag==true){//计算空行
							nullLine++;
						}else if(Pattern.matches("([\\s*{]*\\/{2,})|[\\s*}]*\\/{2,}.*",t)&&flag==true){//计算注释行，该行为//或{//或}//情况
							noteLine++;
						}else if(Pattern.matches("(([\\s*{]*\\/\\*)|[\\s*}]*\\/\\*).*\\*\\/",t)){//该行为/**/或{/**/或}/**/情况
							noteLine++;
						}else if(Pattern.matches("([\\s*{]*\\/\\*)|[\\s*}]*\\/\\*.*",t)){//剩下两个为判断是否为/*分开*/情况
							flag=false;
							noteLine++;
						}else if(flag==false){
							noteLine++;
							if(Pattern.matches(".*\\*\\/", t)){
								flag=true;
							}
						}else{
							codeLine++;
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	long getNullLine(){
		return nullLine;
	}
	long getNoteLine(){
		return noteLine;
	}
	long getCodeLine(){
		return codeLine;
	}
	public static void main(String[] args) {
		File file = new File("D:/test/2.java");
		LineCounter lineCounter = new LineCounter(file);
		System.out.println(lineCounter.getNullLine());
		System.out.println(lineCounter.getNoteLine());
		System.out.println(lineCounter.getCodeLine());
	}
}
