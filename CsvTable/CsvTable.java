import java.io.*;

class CsvTable {
	
	//定数フィールドゥー
	static final char DEFAULT_DELIMITER = ',';
	
	//インスタンス変数ゥー
	private String[][] cells;
	private int table_columns;
	private int table_rows;
	private char delimiter;
	
	
	//コンストラクタァー
	CsvTable(int c0, int r0, char delim){
		createTable(c0, r0);
		delimiter = delim;
	}
	
	CsvTable(int c0, int r0){
		this(c0, r0, DEFAULT_DELIMITER);
	}
	
	
	//メソッドォー
	private void createTable(int col, int row){
		table_columns = col;
		table_rows = row;
		
		cells = new String[row][col];
		for(int i=0; i<row; i++){
			for(int j=0; j<col; j++){
				cells[i][j] = new String();
			}
		}
	}
	
	
	void set(int ri, int ci, String str){
		cells[ri][ci] = new String(str);
	}
	
	String get(int ri, int ci){
		return new String(cells[ri][ci]);
	}
	
	
	void setDelimiter(char delim){
		delimiter = delim;
	}
	
	
	int getTableColumns(){
		return table_columns;
	}
	
	int getTableRows(){
		return table_rows;
	}
	
	
	void fileLoad(String fileName)
	throws IOException {
		FileReader in = null;
		StringBuffer buf = new StringBuffer();
		int tmp;
		int i, j;
		
		try{
			in = new FileReader(fileName);
			
			i = 0;
			j = 0;
			//ファイルの終端かローが上限に達したら
			while( (tmp = in.read()) != -1 && i < table_rows ){
				//コラムが上限に達したら
				if( j >= table_columns ){
					if( tmp == '\n' ){
						i++;
						j = 0;
						buf = new StringBuffer();
					}
					continue;
				}
				
				//改行コードに達したら
				if( tmp == '\n' ){
					set(i, j, buf.toString());
				
					buf = new StringBuffer();
					i++;
					j = 0;
				
					continue;
				}
			
				//区切り文字にあたったら
				if( tmp == delimiter ){
					set(i, j, buf.toString());
				
					buf = new StringBuffer();
					j++;
				
					continue;
				}
			
				buf.append((char)tmp);
			}
		}finally{
			if( in != null ){
				in.close();
			}
		}
	}
		
	void fileExport(String fileName)
	throws IOException{
		FileWriter out = null;
		String tmp;
		
		try{
			out = new FileWriter(fileName);
			
			for(int i=0; i<table_rows; i++){
				for(int j=0; j<table_columns; j++){
					tmp = cells[i][j];
				
					for(int k=0; k<tmp.length(); k++){
						out.write(tmp.charAt(k));
					}
				
					if( j < (table_columns - 1) ){
						out.write(delimiter);
					}
				}
			
				if( i < (table_rows - 1) ){
					out.write('\n');
				}
			}
		}finally{
			if( out != null ){
				out.close();
			}
		}
	}
	
	void printTable(){
		System.out.println(toString());
		System.out.println("[COLUMNS = " + 
			table_columns + ", ROWS = " + table_rows + "]");
		
		for(int i=0; i<table_rows; i++){
			for(int j=0; j<table_columns; j++){
				System.out.print(cells[i][j] + delimiter + " ");
			}
			System.out.println();
		}
	}
}
