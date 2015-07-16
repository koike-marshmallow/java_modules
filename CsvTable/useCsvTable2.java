import java.io.*;

class useCsvTable2 {
	public static void main(String[] args)
	throws IOException {
		
		if( args.length > 0 ){
		
			CsvTable table = new CsvTable(5, 5);
		
			try{
				table.fileLoad(args[0]);
			}finally{}
		
			table.printTable();
		}
	}
}
