package kemin.tool;

import java.util.Scanner;

public class DebugTools {
	/*
	 * 暂停
	 */
	@Deprecated public static void pause(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 打印工具：在标准输出直接打印
	 */
	public static void println(boolean x) {
		System.out.println(x);
	}
	public static void println(char x) {
		System.out.println(x);
	}
    public static void println(char[] x) {
    	System.out.println(x);
    }
    public static void println(double x) {
    	System.out.println(x);
    }
    public static void println(float x) {
    	System.out.println(x);
    }
    public static void println(int x) {
    	System.out.println(x);
    }
    public static void println(long x) {
    	System.out.println(x);
    }
    public static void println(Object x) {
    	System.out.println(x);
    }
    public static void println(String x) {
		System.out.println(x);
	}
	
	public static void print(boolean x) {
		System.out.print(x);
	}
	public static void print(char x) {
		System.out.print(x);
	}
    public static void print(char[] x) {
    	System.out.print(x);
    }
    public static void print(double x) {
    	System.out.print(x);
    }
    public static void print(float x) {
    	System.out.print(x);
    }
    public static void print(int x) {
    	System.out.print(x);
    }
    public static void print(long x) {
    	System.out.print(x);
    }
    public static void print(Object x) {
    	System.out.print(x);
    }
    public static void print(String x) {
		System.out.print(x);
	}
	
	/*
	 * 提示工具：在标准输出直接打印，停留second时间后继续向下执行
	 */
	public static void prompt(String message, int second) {
		println(message);
		pause((long)second*1000);
	}
	
	public static void prompt(String message) {
		prompt(message, 5);
	}
	
	/*
	 * 提示工具：在标准输出直接打印，打印后显示倒数，每个倒数之间间隔second时间，倒数到0后继续向下执行
	 */
	public static void countdown(String message, int second) {
		println(message);
		countdown_(second);
	}
	
	public static void countdown(String message) {
		countdown(message, 5);
	}
	
	/*
	 * 不对外公开的函数
	 */
	private static void countdown_(int second) {
		print("准备" + second + "秒:");
		for(int i=second; i>0; i--) {
			print(" " + i + ",");
			pause(1000);
		}
		println(" 0.");
	}
	
	private static void countdown_() {
		countdown_(10);
	}
	
	@Deprecated private static void deprecated_prompt(String message) {
		System.out.println(message);
		while(true) {
			System.out.println(" - y/Y");
			Scanner input = new Scanner(System.in);
			String str = input.next();
			if(str.equalsIgnoreCase("y")){
				break;
			}
		}
	}
}
