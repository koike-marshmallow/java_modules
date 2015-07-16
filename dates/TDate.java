import java.util.*;

class TDate {
	
	/*内部クラス定義*/
	private static class TDate_Elements {
		int year;
		int month;
		int date;
		TDate_Elements(int y0, int m0, int d0){
			year = y0;
			month = m0;
			date = d0;
		}
		TDate_Elements(){
			year = 0;
			month = 0;
			date = 0;
		}
		public String toString(){
			return String.format(
				"(%d, %d, %d)", year, month, date);
		}
	}
	
	
	/*定数フィールド*/
	static final int START_YEAR = 1950;
	
	static final int DATE = 1;
	static final int MONTH = 2;
	static final int YEAR = 4;
	
	static final int DATES_OFYEAR = 365;
	static final int DATES_OFYEAR_LEAP = 366;
	static final int[] DATES_OFMONTH = 
		{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	static final int[] DATES_OFMONTH_LEAP = 
		{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};	
	
		
	/*共通メソッド*/
	private static TDate_Elements datesToElements(int ds0){
		int y1, m1, d1;
		int[] md_pattern;
		int tmp;
		
		/*年の計算*/
		y1 = START_YEAR;
		while( true ){
			if( isLeapYear(y1) ){
				tmp = DATES_OFYEAR_LEAP;
			}else{
				tmp = DATES_OFYEAR;
			}
			
			if( ds0 >= tmp ){
				ds0 -= tmp;
				y1++;
			}else{
				break;
			}
		}
		
		/*月の計算*/
		if( isLeapYear(y1) ){
			md_pattern = DATES_OFMONTH_LEAP;
		}else{
			md_pattern = DATES_OFMONTH;
		}
		
		m1 = 1;
		for(int i=0; i<md_pattern.length; i++){
			if( ds0 >= md_pattern[i] ){
				ds0 -= md_pattern[i];
				m1++;
			}else{
				break;
			}
		}
		
		/*日の計算*/
		d1 = ds0 + 1;
		
		return new TDate_Elements(y1, m1, d1);
	}
	
	
	private static int elementsToDates(TDate_Elements el0){
		int ds1 = 0;
		int[] md_pattern;
		
		/*年の変換*/
		for(int y1=START_YEAR; y1<el0.year; y1++){
			if( isLeapYear(y1) ){
				ds1 += DATES_OFYEAR_LEAP;
			}else{
				ds1 += DATES_OFYEAR;
			}
		}
		
		/*月の変換*/
		if( isLeapYear(el0.year) ){
			md_pattern = DATES_OFMONTH_LEAP;
		}else{
			md_pattern = DATES_OFMONTH;
		}
		for(int i=0; i<el0.month - 1; i++){
			ds1 += md_pattern[i];
		}
		
		/*日の変換*/
		ds1 += el0.date - 1;
		
		return ds1;
	}
	
	
	private static int normalizeYear(int y0){
		return Math.max(y0, START_YEAR);
	}
	
	private static int normalizeMonth(int m0){
		return Math.max(Math.min(m0, 12), 1);
	}
	
	private static int normalizeDate(int y0, int m0, int d0){
		int dmax;
		if( isLeapYear(y0) ){
			dmax = DATES_OFMONTH_LEAP[m0 - 1];
		}else{
			dmax = DATES_OFMONTH[m0 - 1];
		}
		
		return Math.max(Math.min(d0, dmax), 1);
	}
	
	
	static boolean isLeapYear(int y0){
		if( (y0%400) == 0 || ((y0%4) == 0 && (y0%100) != 0) ){
			return true;
		}else{
			return false;
		}
	}
	
	
	static TDate getToday(){
		Calendar cal = Calendar.getInstance();
		
		return new TDate(
			cal.get(Calendar.YEAR),
			cal.get(Calendar.MONTH) + 1,
			cal.get(Calendar.DATE)
		);
	}
			
		
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
		
	
	/*インスタンス変数*/
	private int dates;
	
	
	/*コンストラクタ*/
	TDate(int y0, int m0, int d0){
		set(y0, m0, d0);
	}
	
	TDate(TDate td0){
		set(td0);
	}
	
	TDate(){
		this(START_YEAR, 1, 1);
	}
	
	
	/*メソッド*/
	void set(int y0, int m0, int d0){
		TDate_Elements el1;
		
		y0 = normalizeYear(y0);
		m0 = normalizeMonth(m0);
		d0 = normalizeDate(y0, m0, d0);
		
		el1 = new TDate_Elements(y0, m0, d0);
		
		dates = elementsToDates(el1);
	}
	
	void set(int v0, int f0){
		TDate_Elements el1 = datesToElements(dates);
		
		switch( f0 ){
			case YEAR:
				set(v0, el1.month, el1.date);
				break;
			case MONTH:
				set(el1.year, v0, el1.date);
				break;
			case DATE:
				set(el1.year, el1.month, v0);
				break;
			default:
				break;
		}
	}
	
	void set(TDate td0){
		set(td0.get(YEAR), td0.get(MONTH), td0.get(DATE));
	}
		
		
	int get(int f0){
		TDate_Elements el1 = datesToElements(dates);
		
		switch( f0 ){
			case YEAR:
				return el1.year;
			case MONTH:
				return el1.month;
			case DATE:
				return el1.date;
			default:
				return -1;
		}
	}
	
	
	TDate getNextDate(){
		TDate next = new TDate(this);
		
		next.dates++;
		return next;
	}
	
	
	TDate getPreviousDate(){
		TDate prev = new TDate(this);
		
		prev.dates = Math.max((prev.dates - 1), 0);
		return prev;
	}
		
	
	boolean isLeapYear(){
		return isLeapYear(get(YEAR));
	}
	
	
	int compare(TDate td0){
		return this.dates - td0.dates;
	}
	
	
	String getString(){
		TDate_Elements el1 = datesToElements(dates);
		
		return String.format(
			"%d / %d / %d", el1.year, el1.month, el1.date);
	}
	
	
	public String toString(){
		return super.toString() + 
			" [" + getString() + " (dates=" + dates + ")]";
	}
}
