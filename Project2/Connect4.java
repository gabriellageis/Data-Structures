import java.util.Scanner;


public class Connect4 {
	static final int blue =1, red=2;
	static int NUM_COLUMNS;
	static int NUM_IN_ROW;
	static Scanner input = new Scanner(System.in);
	static int firstplayer;
	static int p1=0,p2=0,ties=0;
	static long cnt=0;

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter 3 to run 3x3 model, or 4 to run 4x4 model");
		String gameType = input.next();
		
		if (gameType.equals("3")) {
			 NUM_COLUMNS = 3;
			 NUM_IN_ROW = 3;
		} else if (gameType.equals("4")) {
			 NUM_COLUMNS = 4;
			 NUM_IN_ROW = 4;
		} 
		
		for (int i=0; i<NUM_COLUMNS; i++) {
			int[ ][ ] list = new int[NUM_COLUMNS][NUM_COLUMNS];
			firstplayer = blue;
			p1=0;p2=0;cnt=0;ties=0;
		    switch(i) {
		    case 0: list[NUM_COLUMNS-1][0]=blue; break; 
		    case 1: list[NUM_COLUMNS-1][1]=blue; break; 
		    case 2: list[NUM_COLUMNS-1][2]=blue; break; 
		    case 3: list[NUM_COLUMNS-1][3]=blue; break;
		    
		    }
			Play(list , red);
			System.out.println ("NetWins for column " + i + ": " + (p1-p2));
			System.out.println ("Number of recursion calls: " + cnt);
			if (NUM_COLUMNS == 3) {
				System.out.println("Red wins: "+p1 +"    Blue Wins: "+p2);
			} else if (NUM_COLUMNS == 4){
				System.out.println("Blue wins: "+p1 +"    Red Wins: "+p2);
			}
			System.out.println("********************");
			
		}
	}
	public static int Play(int[][] inlist, int clr) {
		cnt++;
		int res=checkBoard(inlist,clr);
		// 0 - board full, 1- blue wins  2 = red wins   3-keep playing
		if (res < 3) { 
			if (res == 0) {ties++;return 0;
			} else {
				if (res == firstplayer) {p1++; return 1;} else {p2++; return -1;}
			}
		}	
		res = 0;

		// for each space that can be the next move
		//    make a copy of board (next lines)	

		//   update the board for this move
		for (int row = NUM_COLUMNS-1; row>=0;row--  ){
			if (row == NUM_COLUMNS-1) {
				for (int col = 0; col<NUM_COLUMNS;col++  ){
					if (inlist[row][col] == 0) {
						int[][] clonelist = new int[NUM_COLUMNS][NUM_COLUMNS];
						for (int x = 0;x <NUM_COLUMNS;x++  ){
							for (int y = 0; y<NUM_COLUMNS;y++  ){
								clonelist[x][y] = inlist[x][y] ;
							}
						}
						 
						clonelist[row][col] = clr;
						Play(clonelist, 3- clr);
					}
				}
			} else {
				for (int col = 0; col < NUM_COLUMNS; col++) {
					if (inlist[row][col] == 0 ) {
						if (inlist[row+1][col] != 0) {
							int[][] clonelist = new int[NUM_COLUMNS][NUM_COLUMNS];
							for (int x = 0;x <NUM_COLUMNS;x++  ){
								for (int y = 0; y<NUM_COLUMNS;y++  ){
									clonelist[x][y] = inlist[x][y] ;
								}
							}
							 
							clonelist[row][col] = clr;
							Play(clonelist, 3- clr);
						}
						
					}
				}
			}
			
		}
		//  recursively call Play
		return res;
	}
	public static boolean isFull(int[][] inlist){
		boolean empty = true;
		for (int i = 0 ; i<NUM_COLUMNS ; i++ ) {
			for (int i2 = 0 ; i2<NUM_COLUMNS ; i2++ ) {
				if (inlist[i][i2] ==0   ) { empty = false; break;} 
			}
		}
		return empty;
	}
	public static int checkBoard(int[][] inlist ,int clr){
		int chkclr = 3-clr;
		for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
			int colcnt = 0;
			for (int j=0; j<NUM_COLUMNS; j++) {
				if (inlist[i][j] == chkclr) {
					colcnt++;
					if (colcnt == NUM_IN_ROW)  { return chkclr;}	 
				}  else {
					colcnt =0;
				}
			}
		}
		for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
			int colcnt = 0;
			for (int j=0; j<NUM_COLUMNS; j++) {
				if (inlist[j][i] == chkclr) {
					colcnt++;
					if (colcnt == NUM_IN_ROW)  { return chkclr;}	 
				}  else {
					colcnt =0;
				}
			}
		}
		int colcnt = 0;
		for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
			if (inlist[i][i] == chkclr) {
				colcnt++;
				if (colcnt == NUM_IN_ROW)  {return chkclr;}	 
			}  else {
				colcnt =0;
			}
		}
		colcnt = 0;
		for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
			if (inlist[NUM_COLUMNS-1-i][i] == chkclr) {
				colcnt++;
				if (colcnt == NUM_IN_ROW)  { return chkclr;}	 
			}  else {
				colcnt =0;
			}
		}
		if (isFull(inlist)) {  return 0; 
		} else {
			return 3;
		}


	}
	public static void printlist(int[][] inlist) {
		for (int i =0; i<inlist.length; i++) {
			for (int j =0; j<inlist.length; j++) {
				System.out.print(inlist[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

}