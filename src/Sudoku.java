/**
*
* @author Erick Zetinator
*/
import java.io.*;
import java.util.*;
import java.lang.*;
import java.math.*;

public class Sudoku {

  //properties.
  public static int level = 1;


  //load game, there are different levels that one can select with the variable level
  public static int[][] load_game( int level){

    int[][] dataMatrix = new int[9][9];

    switch (level){

      case 2:

      dataMatrix[0][0] = 7;dataMatrix[0][4] = 5;dataMatrix[0][6] = 4;dataMatrix[1][0] = 4;
      dataMatrix[1][3] = 1;dataMatrix[1][4] = 9;dataMatrix[1][6] = 6;dataMatrix[1][7] = 2;
      dataMatrix[1][8] = 7;dataMatrix[2][2] = 6;dataMatrix[2][8] = 9;dataMatrix[3][0] = 9;
      dataMatrix[3][2] = 3;dataMatrix[3][6] = 8;dataMatrix[4][3] = 4;dataMatrix[4][5] = 3;
      dataMatrix[5][2] = 8;dataMatrix[5][6] = 5;dataMatrix[5][8] = 2;dataMatrix[6][0] = 5;
      dataMatrix[6][6] = 2;dataMatrix[7][0] = 2;dataMatrix[7][1] = 9;dataMatrix[7][2] = 1;
      dataMatrix[7][4] = 4;dataMatrix[7][5] = 7;dataMatrix[7][8] = 8;dataMatrix[8][2] = 7;
      dataMatrix[8][4] = 1;dataMatrix[8][8] = 5;

      break;


      case 1:
      default:

      dataMatrix[0][2] = 9;dataMatrix[0][5] = 8;dataMatrix[0][6] = 5; dataMatrix[0][7] = 4;
      dataMatrix[1][8] = 7;dataMatrix[2][1] = 5;dataMatrix[2][2] = 4; dataMatrix[2][4] = 9;
      dataMatrix[2][6] = 1;dataMatrix[3][5] = 6;dataMatrix[3][6] = 3; dataMatrix[3][7] = 2;
      dataMatrix[4][1] = 8;dataMatrix[4][2] = 2;dataMatrix[4][6] = 4; dataMatrix[4][7] = 1;
      dataMatrix[5][1] = 3;dataMatrix[5][2] = 5;dataMatrix[5][3] = 2; dataMatrix[6][2] = 7;
      dataMatrix[6][4] = 3;dataMatrix[6][6] = 2;dataMatrix[6][7] = 5; dataMatrix[7][0] = 9;
      dataMatrix[8][1] = 4;dataMatrix[8][2] = 3;dataMatrix[8][3] = 8; dataMatrix[8][7] = 9;

      break;

    }

    return dataMatrix;
  }

  //overwrite values
  public static boolean is_origin( int row, int column, int[][] dataMatrix ){

    boolean res = false;
    if ( dataMatrix[row][column] != 0)
    res = true;

    return res;

  }

  //is the game over?
  public static boolean is_over( int[][] dataMatrix ){

    boolean res = true;

    for ( int f = 0; f < dataMatrix.length; f ++)
    for ( int c = 0; c < dataMatrix[0].length; c ++)
    if ( dataMatrix[f][c] == 0 )
    res = false;

    return res;

  }

  //print message
  public static void message ( String message ){

    //head.
    System.out.print("+");
    for(int i = 0; i < (message.length() + 21); i ++ )
    System.out.print ("-");
    System.out.print ("+\n");

    //body.
    System.out.print("|          ");
    System.out.print(message);
    System.out.print("           |\n");

    //foot
    System.out.print("+");
    for(int i = 0; i < (message.length() + 21); i ++ )
    System.out.print ("-");

    System.out.print ("+\n\n");

  }

  //print vector
  public static void print_vector ( int[][] dataMatrix ){


    System.out.print("+");
    for(int i = 0; i < (dataMatrix.length*3 + 8); i ++ )
    System.out.print ("=");

    System.out.print ("+\n");

    for ( int f = 0; f < dataMatrix.length; f ++ ){

      System.out.print("|");

      for ( int c = 0; c < dataMatrix.length; c++){

        if ( dataMatrix[f][c] != 0 ){
          System.out.print (" ");
          System.out.print (dataMatrix[f][c]);
          System.out.print (" ");

        }
        else{
          System.out.print (" ");
          System.out.print (" ");
          System.out.print (" ");

        }

        if ( c == 2 || c == 5 || c == 8){
          System.out.print ("|");

        }
        else{
          System.out.print (":") ;

        }

      }
      System.out.println();
      if ( f != 2 && f != 5 && f != 8){
        System.out.print("+");
        for(int i = 0; i < (dataMatrix.length*3 + 8); i ++ )
        System.out.print ("-");

        System.out.print ("+\n");
      }
      else{
        System.out.print("+");
        for(int i = 0; i < (dataMatrix.length*3 + 8); i ++ )
        System.out.print ("=");

        System.out.print ("+\n");

      }
    }
  }

  //check rows.
  public static boolean exist_row( int number, int row, int[][] dataMatrix ){

    boolean res = false;

    for ( int i = 0; i < dataMatrix.length; i ++ )
    if ( dataMatrix[(row-1)][i] == number ){
      res = true;
      break;
    }

    //is zero?.
    if ( number == 0 ) res = false;

    return res;

  }

  //check column.
  public static boolean exist_column( int number, int column, int[][] dataMatrix ){

    boolean res = false;

    for ( int i = 0; i < dataMatrix.length; i ++ )
    if ( dataMatrix[i][(column-1)] == number ){
      res = true;
      break;
    }

    //is zero?.
    if ( number == 0 ) res = false;

    return res;

  }

  // check index.
  public static boolean check_index ( int index ){

    if ( index > 0 && index < 10)
    return true;
    else
    return false;

  }

  // check values.
  public static boolean check_value ( int value ){

    if ( value >= 0 && value < 10)
    return true;
    else
    return false;

  }

  //check box values.
  public static boolean check_value ( int value, int row, int column, int[][] dataMatrix ){

    //VARIABLES.
    int min_row;
    int max_row;
    int min_column;
    int max_column;
    boolean res = false;

    //get the rows of the box.
    if ( row > 0 && row < 4){
      min_row = 0;
      max_row = 2;
    }else if ( row > 3 && row < 7 ){
      min_row = 3;
      max_row = 5;
    }else{
      min_row = 6;
      max_row = 8;
    }

    //get the column of the box.
    if ( column > 0 && column < 4){
      min_column = 0;
      max_column = 2;
    }else if ( column > 3 && column < 7 ){
      min_column = 3;
      max_column = 5;
    }else{
      min_column = 6;
      max_column = 8;
    }

    //get value
    for ( int f = min_row; f <= max_row; f++ )
    for ( int c = min_column; c <= max_column; c++)
    if ( dataMatrix[f][c] == value ){
      res = true;
      break;

    }

    if ( value == 0 ) res = false;

    return res;

  }

  //main
  public static void main ( String[] args ) throws Exception
  {

    //VARIABLES.
    BufferedReader keyboard = new BufferedReader( new InputStreamReader ( System.in ) );
    int[][] sudoku = new int[9][9];
    int row = 0;
    int column = 0;
    int value = 0;

    sudoku = load_game( level );

    while ( true ){

      print_vector ( sudoku );
      System.out.println( "Insert codinates (row/column): " );
      System.out.print( "[row]: " );
      row = Integer.parseInt( keyboard.readLine() );
      System.out.print( "[column]: " );
      column = Integer.parseInt( keyboard.readLine() );
      System.out.print( "[value]: " );
      value = Integer.parseInt( keyboard.readLine() );

      if ( !check_index(row) )
      message ("Invalid row value...");
      else if ( !check_index(column) )
      message ("Invalid column value...");
      else if ( !check_value(value) )
      message ("Invalid value...");

      //box availible.
      else if ( is_origin( (row - 1), (column - 1), load_game( level ) ) )
      message ( "Cannot modify this values, its predefined...");

      else if ( exist_row( value, row, sudoku ) )
      message ("[X] Value " + value + " already used in the row...");

      else if ( exist_column( value, column, sudoku ) )
      message ("[X] Value " + value + " already used in the column...");

      else if ( check_value( value, row, column, sudoku ) )
      message ( "[X] already exist in the box...");

      //Write values in dataMatrix.
      else {
        sudoku[(row - 1)][(column - 1)] = value;
        message( "[" + row + "," + column + "]=" + value + " Done  ");
      }


      if ( is_over( sudoku ) ){
        message( "game over");
        print_vector( sudoku );
        System.out.println ( "Do you wish to continue?...");
        keyboard.readLine();

        //level up....
        level ++;
        sudoku = load_game( level );
        message( "SUDOKU level " + level );
      }

    }

  }

}
