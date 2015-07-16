import java.io.*;

class useCsvTable {
	public static void main(String[] args)
	throws IOException {
		CsvTable table = new CsvTable(5, 5);
		
		table.set(0, 0, "テスト");
		table.set(0, 1, "だよ");
		table.set(0, 2, "CSVのね");
		
		for(int i=2; i<5; i++){
			for(int j=0; j<5; j++){
				table.set(i, j, String.valueOf(i * j));
			}
		}
		
		table.printTable();
		try{
			table.fileExport("export.csv");
		}finally{}
	}
}
