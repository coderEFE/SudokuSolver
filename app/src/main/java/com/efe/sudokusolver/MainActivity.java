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
        //get sudoku board by looping through all editText ids
        final View[][] nums = new View[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int num = (1 + j + (i * 9));
                nums[i][j] = (findViewById(getResId(num == 1 ? "editText" : ("editText" + num), R.id.class)));
            }
        }
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
                //assign array by looping through all editText ids
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        array[i][j] = ((EditText) nums[i][j]).getText().toString();
                    }
                }

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

    //reflection function that loops through all resources of a certain class
    private static int getResId(String resName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
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
