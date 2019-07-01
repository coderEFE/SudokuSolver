package com.efe.sudokusolver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    private static final String EMPTY_CELL = "";
    private static String[][] array = new String[9][9];
    int toastTimer = 0;
    boolean toastShowing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sudoku board
        final View[][] nums = new View[][]{{((EditText) findViewById(R.id.editText)), ((EditText) findViewById(R.id.editText2)), ((EditText) findViewById(R.id.editText3)), ((EditText) findViewById(R.id.editText4)), ((EditText) findViewById(R.id.editText5)), ((EditText) findViewById(R.id.editText6)), ((EditText) findViewById(R.id.editText7)), ((EditText) findViewById(R.id.editText8)), ((EditText) findViewById(R.id.editText9))},
                {((EditText) findViewById(R.id.editText10)), ((EditText) findViewById(R.id.editText11)), ((EditText) findViewById(R.id.editText12)), ((EditText) findViewById(R.id.editText13)), ((EditText) findViewById(R.id.editText14)), ((EditText) findViewById(R.id.editText15)), ((EditText) findViewById(R.id.editText16)), ((EditText) findViewById(R.id.editText17)), ((EditText) findViewById(R.id.editText18))},
                {((EditText) findViewById(R.id.editText19)), ((EditText) findViewById(R.id.editText20)), ((EditText) findViewById(R.id.editText21)), ((EditText) findViewById(R.id.editText22)), ((EditText) findViewById(R.id.editText23)), ((EditText) findViewById(R.id.editText24)), ((EditText) findViewById(R.id.editText25)), ((EditText) findViewById(R.id.editText26)), ((EditText) findViewById(R.id.editText27))},
                {((EditText) findViewById(R.id.editText28)), ((EditText) findViewById(R.id.editText29)), ((EditText) findViewById(R.id.editText30)), ((EditText) findViewById(R.id.editText31)), ((EditText) findViewById(R.id.editText32)), ((EditText) findViewById(R.id.editText33)), ((EditText) findViewById(R.id.editText34)), ((EditText) findViewById(R.id.editText35)), ((EditText) findViewById(R.id.editText36))},
                {((EditText) findViewById(R.id.editText37)), ((EditText) findViewById(R.id.editText38)), ((EditText) findViewById(R.id.editText39)), ((EditText) findViewById(R.id.editText40)), ((EditText) findViewById(R.id.editText41)), ((EditText) findViewById(R.id.editText42)), ((EditText) findViewById(R.id.editText43)), ((EditText) findViewById(R.id.editText44)), ((EditText) findViewById(R.id.editText45))},
                {((EditText) findViewById(R.id.editText46)), ((EditText) findViewById(R.id.editText47)), ((EditText) findViewById(R.id.editText48)), ((EditText) findViewById(R.id.editText49)), ((EditText) findViewById(R.id.editText50)), ((EditText) findViewById(R.id.editText51)), ((EditText) findViewById(R.id.editText52)), ((EditText) findViewById(R.id.editText53)), ((EditText) findViewById(R.id.editText54))},
                {((EditText) findViewById(R.id.editText55)), ((EditText) findViewById(R.id.editText56)), ((EditText) findViewById(R.id.editText57)), ((EditText) findViewById(R.id.editText58)), ((EditText) findViewById(R.id.editText59)), ((EditText) findViewById(R.id.editText60)), ((EditText) findViewById(R.id.editText61)), ((EditText) findViewById(R.id.editText62)), ((EditText) findViewById(R.id.editText63))},
                {((EditText) findViewById(R.id.editText64)), ((EditText) findViewById(R.id.editText65)), ((EditText) findViewById(R.id.editText66)), ((EditText) findViewById(R.id.editText67)), ((EditText) findViewById(R.id.editText68)), ((EditText) findViewById(R.id.editText69)), ((EditText) findViewById(R.id.editText70)), ((EditText) findViewById(R.id.editText71)), ((EditText) findViewById(R.id.editText72))},
                {((EditText) findViewById(R.id.editText73)), ((EditText) findViewById(R.id.editText74)), ((EditText) findViewById(R.id.editText75)), ((EditText) findViewById(R.id.editText76)), ((EditText) findViewById(R.id.editText77)), ((EditText) findViewById(R.id.editText78)), ((EditText) findViewById(R.id.editText79)), ((EditText) findViewById(R.id.editText80)), ((EditText) findViewById(R.id.editText81))}};
        //reset button
        final Button solveButton = findViewById(R.id.button);
        final Button resetButton = findViewById(R.id.button2);
        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        ((EditText)nums[i][j]).setText("", TextView.BufferType.EDITABLE);
                    }
                }
            }
        });
        // initiate the Toast with context, message and duration for the Toast
        final Toast toast = Toast.makeText(getApplicationContext(), "Board has no solution! Try again.", Toast.LENGTH_SHORT);
        // initiate the Toast with context, message and duration for the Toast
        final Toast toast2 = Toast.makeText(getApplicationContext(), "Board is not valid! Try again.", Toast.LENGTH_SHORT);
        //cancels toast after a certain amount of time
        if (toastShowing) {
            toastTimer++;
        }
        if (toastTimer > 20) {
            toast.cancel();
            toast2.cancel();
            toastTimer = 0;
            toastShowing = false;
        }

        //solve button
        solveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //assign array
                array = new String[][]{{((EditText) findViewById(R.id.editText)).getText().toString(), ((EditText) findViewById(R.id.editText2)).getText().toString(), ((EditText) findViewById(R.id.editText3)).getText().toString(), ((EditText) findViewById(R.id.editText4)).getText().toString(), ((EditText) findViewById(R.id.editText5)).getText().toString(), ((EditText) findViewById(R.id.editText6)).getText().toString(), ((EditText) findViewById(R.id.editText7)).getText().toString(), ((EditText) findViewById(R.id.editText8)).getText().toString(), ((EditText) findViewById(R.id.editText9)).getText().toString()},
                        {((EditText) findViewById(R.id.editText10)).getText().toString(), ((EditText) findViewById(R.id.editText11)).getText().toString(), ((EditText) findViewById(R.id.editText12)).getText().toString(), ((EditText) findViewById(R.id.editText13)).getText().toString(), ((EditText) findViewById(R.id.editText14)).getText().toString(), ((EditText) findViewById(R.id.editText15)).getText().toString(), ((EditText) findViewById(R.id.editText16)).getText().toString(), ((EditText) findViewById(R.id.editText17)).getText().toString(), ((EditText) findViewById(R.id.editText18)).getText().toString()},
                        {((EditText) findViewById(R.id.editText19)).getText().toString(), ((EditText) findViewById(R.id.editText20)).getText().toString(), ((EditText) findViewById(R.id.editText21)).getText().toString(), ((EditText) findViewById(R.id.editText22)).getText().toString(), ((EditText) findViewById(R.id.editText23)).getText().toString(), ((EditText) findViewById(R.id.editText24)).getText().toString(), ((EditText) findViewById(R.id.editText25)).getText().toString(), ((EditText) findViewById(R.id.editText26)).getText().toString(), ((EditText) findViewById(R.id.editText27)).getText().toString()},
                        {((EditText) findViewById(R.id.editText28)).getText().toString(), ((EditText) findViewById(R.id.editText29)).getText().toString(), ((EditText) findViewById(R.id.editText30)).getText().toString(), ((EditText) findViewById(R.id.editText31)).getText().toString(), ((EditText) findViewById(R.id.editText32)).getText().toString(), ((EditText) findViewById(R.id.editText33)).getText().toString(), ((EditText) findViewById(R.id.editText34)).getText().toString(), ((EditText) findViewById(R.id.editText35)).getText().toString(), ((EditText) findViewById(R.id.editText36)).getText().toString()},
                        {((EditText) findViewById(R.id.editText37)).getText().toString(), ((EditText) findViewById(R.id.editText38)).getText().toString(), ((EditText) findViewById(R.id.editText39)).getText().toString(), ((EditText) findViewById(R.id.editText40)).getText().toString(), ((EditText) findViewById(R.id.editText41)).getText().toString(), ((EditText) findViewById(R.id.editText42)).getText().toString(), ((EditText) findViewById(R.id.editText43)).getText().toString(), ((EditText) findViewById(R.id.editText44)).getText().toString(), ((EditText) findViewById(R.id.editText45)).getText().toString()},
                        {((EditText) findViewById(R.id.editText46)).getText().toString(), ((EditText) findViewById(R.id.editText47)).getText().toString(), ((EditText) findViewById(R.id.editText48)).getText().toString(), ((EditText) findViewById(R.id.editText49)).getText().toString(), ((EditText) findViewById(R.id.editText50)).getText().toString(), ((EditText) findViewById(R.id.editText51)).getText().toString(), ((EditText) findViewById(R.id.editText52)).getText().toString(), ((EditText) findViewById(R.id.editText53)).getText().toString(), ((EditText) findViewById(R.id.editText54)).getText().toString()},
                        {((EditText) findViewById(R.id.editText55)).getText().toString(), ((EditText) findViewById(R.id.editText56)).getText().toString(), ((EditText) findViewById(R.id.editText57)).getText().toString(), ((EditText) findViewById(R.id.editText58)).getText().toString(), ((EditText) findViewById(R.id.editText59)).getText().toString(), ((EditText) findViewById(R.id.editText60)).getText().toString(), ((EditText) findViewById(R.id.editText61)).getText().toString(), ((EditText) findViewById(R.id.editText62)).getText().toString(), ((EditText) findViewById(R.id.editText63)).getText().toString()},
                        {((EditText) findViewById(R.id.editText64)).getText().toString(), ((EditText) findViewById(R.id.editText65)).getText().toString(), ((EditText) findViewById(R.id.editText66)).getText().toString(), ((EditText) findViewById(R.id.editText67)).getText().toString(), ((EditText) findViewById(R.id.editText68)).getText().toString(), ((EditText) findViewById(R.id.editText69)).getText().toString(), ((EditText) findViewById(R.id.editText70)).getText().toString(), ((EditText) findViewById(R.id.editText71)).getText().toString(), ((EditText) findViewById(R.id.editText72)).getText().toString()},
                        {((EditText) findViewById(R.id.editText73)).getText().toString(), ((EditText) findViewById(R.id.editText74)).getText().toString(), ((EditText) findViewById(R.id.editText75)).getText().toString(), ((EditText) findViewById(R.id.editText76)).getText().toString(), ((EditText) findViewById(R.id.editText77)).getText().toString(), ((EditText) findViewById(R.id.editText78)).getText().toString(), ((EditText) findViewById(R.id.editText79)).getText().toString(), ((EditText) findViewById(R.id.editText80)).getText().toString(), ((EditText) findViewById(R.id.editText81)).getText().toString()}};

                //check if board is valid and if it can be solved
                if (validBoard(array)) {
                    if (solveSudoku()) {
                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                ((EditText) nums[i][j]).setText(array[i][j], TextView.BufferType.EDITABLE);
                            }
                        }
                    } else {
                        //pop up text saying that board has no solution
                        toast.show(); //display toast
                        toastShowing = true;
                    }
                } else {
                    //pop up text saying that board is invalid
                    toast2.show(); //display toast
                    toastShowing = true;
                }
            }
        });
    }

    //check if board is valid and doesn't break sudoku rules
    private static boolean validBoard (String[][] array) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!array[i][j].equals(EMPTY_CELL)) {
                    if ((inCellColumn(array, array[i][j], j) > 1 || inCellRow(array, array[i][j], i) > 1 || inCellBox(array, array[i][j], i, j) > 1)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //Check is the cell is already in the column of that coordinate
    private static int inCellColumn(String[][] array, String cell, int column) {
        int numOfCells = 0;
        for (int c = 0; c < array.length; c++) {
            if (cell.equals(array[c][column])) {
                numOfCells ++;
            }
        }
        return numOfCells;
    }

    //Check if the cell is already in the row of that coordinate
    private static int inCellRow(String[][] array, String cell, int row) {
        int numOfCells = 0;
        for (int c = 0; c < array.length; c++) {
            if (cell.equals(array[row][c])) {
                numOfCells ++;
            }
        }
        return numOfCells;
    }

    //Check if the cell is already in the box of that coordinate
    private static int inCellBox(String[][] array, String cell, int row, int column) {
        int numOfCells = 0;
        int boxStartRow = (row / 3) * 3;
        int boxStartColumn = (column / 3) * 3;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (cell.equals(array[boxStartRow + y][boxStartColumn + x])) {
                    numOfCells++;
                }
            }
        }
        return numOfCells;
    }

    //Check if the cell is already in the row, column or box of that coordinate
    private static boolean violateRules(String cell, int row, int column, String[][] array) {
        return (inCellColumn(array, cell, column) > 0 || inCellRow(array, cell, row) > 0 || inCellBox(array, cell, row, column) > 0);
    }

    private static boolean solveSudoku () {
        return solveSudokuCell(0,0);
    }

    //Use algorithm to solve sudoku puzzle
    private static boolean solveSudokuCell(int row, int col) {
        if (col == array[row].length) {
            col = 0;
            row++;
            if (row == array.length) {
                return true;
            }
        }

        if (!array[row][col].equals(EMPTY_CELL)) {
            return solveSudokuCell(row, col + 1);
        }

        for (int i = 0; i < array.length; i++) {
            String replaceCell = (i + 1) + "";
            if (!violateRules(replaceCell, row, col, array)) {
                array[row][col] = replaceCell;
                if (solveSudokuCell(row, col+1)) {
                    return true;
                }
            }
        }

        array[row][col] = EMPTY_CELL;
        return false;
    }
}
