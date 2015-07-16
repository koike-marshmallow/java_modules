import java.io.*;

class Printer {
	static char DELIMITER = ',';

	public static void main(String[] args) throws IOException{
		int i;
		
		if( args.length < 1 ){
			System.out.println("引数が少な過ぎます");
			return;
		}
		
		PrintWriter writer = 
			new PrintWriter(
				new BufferedWriter(
					new FileWriter(args[0], true)
				)
			);
		
		for( i=1; i<args.length; i++){
			writer.format("%s", args[i]);
			if( i != args.length - 1 ){
				writer.format("%c", DELIMITER);
			}
		}
		writer.println();
		
		writer.close();
		
	}
}
		
		
