import java.io.*;

public class MyPrinter {
	public static PrintWriter getPrintWriter(String fname, boolean ovr)
	throws IOException{
		PrintWriter writer = new PrintWriter(
			new BufferedWriter(new FileWriter(fname, ovr))
		);
		return writer;
	}

	public static PrintWriter getPrintWriter(String fname)
	throws IOException{
		return getPrintWriter(fname, false);
	}

	public static void print(String fname, boolean ovr, String str)
	throws IOException{
		PrintWriter writer = getPrintWriter(fname, ovr);
		writer.print(str);
		writer.close();
	}

	public static void println(String fname, boolean ovr, String str)
	throws IOException{
		PrintWriter writer = getPrintWriter(fname, ovr);
		writer.println(str);
		writer.close();
	}

	public static void print(String fname, String str)
	throws IOException{
		print(fname, false, str);
	}

	public static void println(String fname, String str)
	throws IOException{
		println(fname, false, str);
	}
}
