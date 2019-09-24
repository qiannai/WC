package WC;

import java.io.File;

public class Pprogramer {
	File[] files;
	CharCounter charCounter;
	LineCounter lineCounter;
	public Pprogramer(File filePath,String path,boolean[] b) {
		files = filePath.listFiles();
		if(files==null){return;}
		/**������������̷�ֱ�Ӽ�ͨ������������һ�´���ԭ����ָ��Ϊ�գ�
		 * ���Űѿ��̷�ֱ��return��ȥ�������Ȼ������
		 * */
		for(File f:files){
			if(f.isFile()&&CommonChoose.isCommon(path, f)){
				charCounter = new CharCounter(f);
				lineCounter = new LineCounter(f);
				System.out.println(f);
				if(b[0]==true){
					System.out.println("�ַ���Ϊ��"+charCounter.getCharCount());
				}
				if(b[1]==true){
					System.out.println("Ӣ����Ϊ��"+charCounter.getWordCounter());
				}
				if(b[2]==true){
					System.out.println("����Ϊ��"+charCounter.getLineCounter());
				}
				if(b[4]==true){
					System.out.println("ע������: "+lineCounter.getNoteLine());
					System.out.println("������: "+lineCounter.getNullLine());
					System.out.println("��������: "+lineCounter.getCodeLine());
				}
			}else if(f.isDirectory()){
				Pprogramer pprogramer =new Pprogramer(f,path,b);//������ļ��и�ʽ��ݹ����
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
