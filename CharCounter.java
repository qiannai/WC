package WC;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class CharCounter {
	InputStreamReader inputStreamReader;
	BufferedReader bufferedReader;
	String text="";
	String t;
	String[] shortWord;
	long charCounter=0;
	long lineCounter=0;
	long wordCounter=0;
	CharCounter(File file){
		if(file.isFile()){
			try {
				inputStreamReader = new InputStreamReader(new FileInputStream(file));
				bufferedReader =  new BufferedReader(inputStreamReader);
				try {
					while((t=bufferedReader.readLine())!=null){
						lineCounter++;
						text+=t;
						text+="\n";
					}
					bufferedReader.close();
					inputStreamReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!Pattern.matches(" ",text)){
					shortWord = text.split("[^A-Za-z]+");
					for(String t:shortWord){
						if(t.matches("[A-Za-z]+")){
							wordCounter++;
						}
					}
				}
				//System.out.println(text);
				text=text.replace(" ", "");
				text=text.replace("\\s","");
				if(!text.isEmpty()){
					charCounter=text.length()-1;
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	long getCharCount(){
		return charCounter;
	}
	long getLineCounter(){
		return lineCounter;
	}
	long getWordCounter(){
		return wordCounter;
	}
	public static void main(String[] args) {
		File f = new File("D:/test/1.txt");
		CharCounter charCounter = new CharCounter(f);
		System.out.println(charCounter.getCharCount());
		System.out.println(charCounter.getLineCounter());
		System.out.println(charCounter.getWordCounter());
	}
}
