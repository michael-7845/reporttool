package kemin.my.toollet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class TextFile {
	/*
	 * 获取文件的属性
	 */
	public static void getProperty(String filepath) {
		File f = new File(filepath);
		System.out.println(f.getName());
		System.out.println(f.getParent());
		System.out.println(f.getPath());
		System.out.println(f.getParentFile().getPath());
	}
	/*
	 * 文件是否存在
	 */
	public static boolean fileExists(String filepath) {
		File f = new File(filepath);
		return f.exists();
	}
	/*
	 * 文件所在的目录是否存在
	 */
	public static boolean dirExists(String filepath) {
		File f = new File(filepath);
		return f.getParentFile().exists();
	}
	/*
	 * 读取文件内容，返回字符串
	 */
	public static String read(String filepath) {
    	BufferedReader in;
    	StringBuilder sb = new StringBuilder();
		try {
			String s;
			in = new BufferedReader( new FileReader(filepath));
			while((s = in.readLine())!=null)
				sb.append(s).append("\n");
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
    }
	/*
	 * 写内容到文件
	 */
	public static void write(String filepath, String[] lines) {
		File file = new File(filepath);
		File dir = file.getParentFile();
		try {
			if(!dir.exists()) {
				dir.mkdirs();
			}
			if(!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    	PrintWriter out;
		try {
			out = new PrintWriter(filepath);
			for(String line: lines) {
				out.println(line);
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
    }
	/*
	 * 写内容到文件
	 */
	public static void write(String filepath, String content) {
		String[] lines = content.split("\n");
		write(filepath, lines);
	}
	/*
	 * 写内容到文件，如果没有此文件则创建后再写入
	 */
	public static void writeWhenever(String filepath, String[] lines) {
		File file = new File(filepath);
		File dir = file.getParentFile();
		try {
			if(!dir.exists()) {
				dir.mkdirs();
			}
			if(!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		write(filepath, lines);
	}
	public static void writeWhenever(String filepath, String content) {
		String[] lines = content.split("\n");
		writeWhenever(filepath, lines);
	}
	
	public static void main(String[] args) {
		String content = read("D:\\tatool\\pic2res.txt");
		System.out.println(content);
		writeWhenever("D:\\tatool2\\fileutility.txt", content);
//		getProperty("D:\\tatool\\pic2res.txt");
//		System.out.println(fileExists("D:\\tatool\\pic5res.txt"));
//		System.out.println(dirExists("D:\\tatool\\pic5res.txt"));
	}
}
