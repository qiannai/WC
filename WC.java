package WC;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;

public class WC {
	Scanner in = new Scanner(System.in);
	String inputLine;
	String pattern;
	String[] cutInput;
	String path;
	File file;
	Pprogramer pprogramer;
	CharCounter charCounter;
	LineCounter lineCounter;
	boolean[] bInput = new boolean[6];
	void inputLineAll(){
		System.out.println("请输入命令行：(可输入wc.exe查询使用)");
		inputLine = in.nextLine();
		pattern = ".*(wc.exe|WC.exe)((( \\s*(-c|-l|-w|-a|-s|-x))+.+)*|.*-x.*)";
		while(!Pattern.matches(pattern, inputLine)){
			System.out.println("输入有误，请输入命令行：(可输入wc.exe查询使用)");
			inputLine = in.nextLine();
		};
		if(Pattern.matches(".*(-c|-l|-w|-a|-s|-x).*", inputLine)){
			cutInput = inputLine.replaceAll("\\s", "").split("(-c|-l|-w|-a|-s|-x)");
			path = cutInput[cutInput.length-1];
			bInput = WC.isChoose(inputLine);
			boolean b_havefile=false;
			if(bInput[3]==true&bInput[5]==false){
				if(Pattern.matches("(.+\\*\\..+)|(.+\\?\\..+)", path)){//模糊搜索
					
					String[] t =path.split("(\\*\\.)|(\\?\\.)");
					file = new File(t[0]);
					if(!(file.isFile()|file.isDirectory())){
						inputLineAll();
					}
					File[] fA = file.listFiles();
					for(File f: fA){
						if((f.isFile()&&f.exists())){//只要里面有符合条件的文件即为符合的地址输入
							b_havefile = true;
						}
					}
					if(true==b_havefile){
						pprogramer = new Pprogramer(file,path,bInput);
					}else{
						inputLineAll();
					}
					
				}else{//需要模糊搜索却输了正确地址
					file =new File(path);
					if((file.isFile()&&file.exists())){
						charCounter = new CharCounter(file);
						lineCounter = new LineCounter(file);
						if(bInput[0]==true){
							System.out.println("字符数为："+charCounter.getCharCount());
						}
						if(bInput[1]==true){
							System.out.println("英文数为："+charCounter.getWordCounter());
						}
						if(bInput[2]==true){
							System.out.println("行数为："+charCounter.getLineCounter());
						}
						if(bInput[4]==true){
							System.out.println("注释行数: "+lineCounter.getNoteLine());
							System.out.println("空行数: "+lineCounter.getNullLine());
							System.out.println("代码行数: "+lineCounter.getCodeLine());
						}
					}else{
						inputLineAll();
					}
					
				}
			}else if(bInput[5]==false){//不需要模糊搜索
				System.out.println(path);
				file =new File(path);
				charCounter = new CharCounter(file);
				lineCounter = new LineCounter(file);
				while(!(file.isFile()&&file.exists())){
					inputLineAll();
				}
				if(bInput[0]==true){
					System.out.println("字符数为："+charCounter.getCharCount());
				}
				if(bInput[1]==true){
					System.out.println("英文数为："+charCounter.getWordCounter());
				}
				if(bInput[2]==true){
					System.out.println("行数为："+charCounter.getLineCounter());
				}
				if(bInput[4]==true){
					System.out.println("注释行数: "+lineCounter.getNoteLine());
					System.out.println("空行数: "+lineCounter.getNullLine());
					System.out.println("代码行数: "+lineCounter.getCodeLine());
				}
			}
		}
		
	}
	
	
	
	static boolean[] isChoose(String cmd){
		boolean[] bChoose=new boolean[6];
		for(int i=0;i<6;i++){
			bChoose[i]=false;
		}
		String[] pathArrays = cmd.split("\\s+");
		for(String t:pathArrays){
			if(Pattern.matches("-c", t)){
				bChoose[0]=true;
			}
			if(Pattern.matches("-w", t)){
				bChoose[1]=true;
			}
			if(Pattern.matches("-l", t)){
				bChoose[2]=true;
			}
			if(Pattern.matches("-s", t)){
				bChoose[3]=true;
			}
			if(Pattern.matches("-a", t)){
				bChoose[4]=true;
			}
			if(Pattern.matches("-x", t)){
				bChoose[5]=true;
			}
		}
		return bChoose;
	}
	
	public static void main(String[] args) {
		while(true){
			WC wc = new WC();
			wc.inputLineAll();
			boolean[] b = wc.bInput;
			if(b[5]==true){//获取地址的方法，不需要确定是否为全部路径
				Visual visual = new Visual(b);
			}else if(b[0]==false&b[1]==false&b[2]==false&b[3]==false&b[4]==false){
				System.out.println("请输入: wc.exe [-c|-l|-w|-a|-s|-x]*");
				System.out.println("-c :  返回查询文件的字符数");
				System.out.println("-l :  返回查询文件的行数");
				System.out.println("-w :  返回查询文件的单词");
				System.out.println("-a :  返回查询文件的空行数、注释行数、代码行数");
				System.out.println("-s :  [-c|-l|-w|-a]支持*.后缀名或者？.后缀名的文档查询");
				System.out.println("-x :  [-c|-l|-w|-a]弹出图形界面选择文件,并处理结果");
			}
		}
	}		
}
