package WC;

import java.io.File;

public class Pprogramer {
	File[] files;
	CharCounter charCounter;
	LineCounter lineCounter;
	public Pprogramer(File filePath,String path,boolean[] b) {
		files = filePath.listFiles();
		if(files==null){return;}
		/**如果搜索的是盘符直接加通配符会出错，查了一下错误原因，是指针为空，
		 * 想着把空盘符直接return回去，结果居然可以了
		 * */
		for(File f:files){
			if(f.isFile()&&CommonChoose.isCommon(path, f)){
				charCounter = new CharCounter(f);
				lineCounter = new LineCounter(f);
				System.out.println(f);
				if(b[0]==true){
					System.out.println("字符数为："+charCounter.getCharCount());
				}
				if(b[1]==true){
					System.out.println("英文数为："+charCounter.getWordCounter());
				}
				if(b[2]==true){
					System.out.println("行数为："+charCounter.getLineCounter());
				}
				if(b[4]==true){
					System.out.println("注释行数: "+lineCounter.getNoteLine());
					System.out.println("空行数: "+lineCounter.getNullLine());
					System.out.println("代码行数: "+lineCounter.getCodeLine());
				}
			}else if(f.isDirectory()){
				Pprogramer pprogramer =new Pprogramer(f,path,b);//如果是文件夹格式则递归调用
			}
			
		}
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		File file_1 = new File("D:/test/");
		boolean[] b=new boolean[6];
		for(int i=0;i<6;i++){
			b[i]=true;
		}
		Pprogramer pProgramer = new Pprogramer(file_1,"*.c",b);
	}
}
