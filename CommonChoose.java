package WC;

import java.io.File;

public class CommonChoose {
	public static boolean isCommon(String path,File f){
		
		String[] t =path.split("(\\*\\.)|(\\?\\.)");
		String fPath =f.getPath();
		String[] fp=fPath.split("\\.");
		for(String cutfp:fp){
			if(cutfp.equals(t[1])){
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		String path = "D:/test/*.c";
		File f =new File("D:/test/hello.c");
		System.out.println(CommonChoose.isCommon(path, f));
	}
}
