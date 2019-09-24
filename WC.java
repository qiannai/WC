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
		System.out.println("�����������У�(������wc.exe��ѯʹ��)");
		inputLine = in.nextLine();
		pattern = ".*(wc.exe|WC.exe)((( \\s*(-c|-l|-w|-a|-s|-x))+.+)*|.*-x.*)";
		while(!Pattern.matches(pattern, inputLine)){
			System.out.println("�������������������У�(������wc.exe��ѯʹ��)");
			inputLine = in.nextLine();
		};
		if(Pattern.matches(".*(-c|-l|-w|-a|-s|-x).*", inputLine)){
			cutInput = inputLine.replaceAll("\\s", "").split("(-c|-l|-w|-a|-s|-x)");
			path = cutInput[cutInput.length-1];
			bInput = WC.isChoose(inputLine);
			boolean b_havefile=false;
			if(bInput[3]==true&bInput[5]==false){
				if(Pattern.matches("(.+\\*\\..+)|(.+\\?\\..+)", path)){//ģ������
					
					String[] t =path.split("(\\*\\.)|(\\?\\.)");
					file = new File(t[0]);
					if(!(file.isFile()|file.isDirectory())){
						inputLineAll();
					}
					File[] fA = file.listFiles();
					for(File f: fA){
						if((f.isFile()&&f.exists())){//ֻҪ�����з����������ļ���Ϊ���ϵĵ�ַ����
							b_havefile = true;
						}
					}
					if(true==b_havefile){
						pprogramer = new Pprogramer(file,path,bInput);
					}else{
						inputLineAll();
					}
					
				}else{//��Ҫģ������ȴ������ȷ��ַ
					file =new File(path);
					if((file.isFile()&&file.exists())){
						charCounter = new CharCounter(file);
						lineCounter = new LineCounter(file);
						if(bInput[0]==true){
							System.out.println("�ַ���Ϊ��"+charCounter.getCharCount());
						}
						if(bInput[1]==true){
							System.out.println("Ӣ����Ϊ��"+charCounter.getWordCounter());
						}
						if(bInput[2]==true){
							System.out.println("����Ϊ��"+charCounter.getLineCounter());
						}
						if(bInput[4]==true){
							System.out.println("ע������: "+lineCounter.getNoteLine());
							System.out.println("������: "+lineCounter.getNullLine());
							System.out.println("��������: "+lineCounter.getCodeLine());
						}
					}else{
						inputLineAll();
					}
					
				}
			}else if(bInput[5]==false){//����Ҫģ������
				System.out.println(path);
				file =new File(path);
				charCounter = new CharCounter(file);
				lineCounter = new LineCounter(file);
				while(!(file.isFile()&&file.exists())){
					inputLineAll();
				}
				if(bInput[0]==true){
					System.out.println("�ַ���Ϊ��"+charCounter.getCharCount());
				}
				if(bInput[1]==true){
					System.out.println("Ӣ����Ϊ��"+charCounter.getWordCounter());
				}
				if(bInput[2]==true){
					System.out.println("����Ϊ��"+charCounter.getLineCounter());
				}
				if(bInput[4]==true){
					System.out.println("ע������: "+lineCounter.getNoteLine());
					System.out.println("������: "+lineCounter.getNullLine());
					System.out.println("��������: "+lineCounter.getCodeLine());
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
			if(b[5]==true){//��ȡ��ַ�ķ���������Ҫȷ���Ƿ�Ϊȫ��·��
				Visual visual = new Visual(b);
			}else if(b[0]==false&b[1]==false&b[2]==false&b[3]==false&b[4]==false){
				System.out.println("������: wc.exe [-c|-l|-w|-a|-s|-x]*");
				System.out.println("-c :  ���ز�ѯ�ļ����ַ���");
				System.out.println("-l :  ���ز�ѯ�ļ�������");
				System.out.println("-w :  ���ز�ѯ�ļ��ĵ���");
				System.out.println("-a :  ���ز�ѯ�ļ��Ŀ�������ע����������������");
				System.out.println("-s :  [-c|-l|-w|-a]֧��*.��׺�����ߣ�.��׺�����ĵ���ѯ");
				System.out.println("-x :  [-c|-l|-w|-a]����ͼ�ν���ѡ���ļ�,��������");
			}
		}
	}		
}
