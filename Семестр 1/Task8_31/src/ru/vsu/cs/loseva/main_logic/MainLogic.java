package ru.vsu.cs.loseva.main_logic;

import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class MainLogic {
    public static int[][] runSolution(int[][] array) {
        int[][] resultArray = new int[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                int c = 1;
                //4 цикла подсчёта
                //первый цикл: подсчет диагонали вверх-влево
                int row;
                int column;
                row = i;
                column = j;
                while (row >= 0 && column >= 0) {
                    if (array[i][j] == array[row][column] && row != i && column != j) {
                        c++;
                    }
                    row--;
                    column--;
                }
                //второй цикл: подсчёт диагонали вверх-вправо
                row = i-1;
                column = j+1;
                while (row >= 0 && column < array[0].length){
                    if (array[i][j] == array[row][column] && row != i && column != j) {
                        c++;
                    }
                    row--;
                    column++;
                }
                //третий цикл: подсчёт диагонали вниз-влево
                row = i+1;
                column = j-1;
                while (row < array.length && column >= 0){
                    if (array[i][j] == array[row][column] && row != i && column != j) {
                        c++;
                    }
                    row++;
                    column--;
                }
                //четвёртый цикл: подсчёт диагонали вниз-вправо
                row = i+1;
                column = j+1;
                while(row < array.length && column < array[0].length){
                    if (array[i][j] == array[row][column] && row != i && column != j) {
                        c++;
                    }
                    row++;
                    column++;
                }
                resultArray[i][j] = c;
            }
        }
        return resultArray;
    }

    public static void runMainLogic(InputArgs inputArgs) throws FileNotFoundException {
        int[][] array = ArrayUtils.readIntArray2FromFile(inputArgs.getInputFile());
        int[][] resultArray = runSolution(array);
        ArrayUtils.writeArrayToFile(inputArgs.getOutputFile(), resultArray);
    }

    public static void runTest(InputArgs inputArgs) throws FileNotFoundException {
        Test test = new Test();

        //первый тест
        inputArgs.setInputFile(test.testPath1In);
        inputArgs.setOutputFile(test.testPath1Out);
        System.out.println("Test 1");
        MainLogic.runMainLogic(inputArgs);
        System.out.println("Output file path: " + Paths.get(inputArgs.getOutputFile()).toAbsolutePath());
        System.out.println();

        //второй тест
        inputArgs.setInputFile(test.testPath2In);
        inputArgs.setOutputFile(test.testPath2Out);
        System.out.println("Test 2");
        MainLogic.runMainLogic(inputArgs);
        System.out.println("Output file path: " + Paths.get(inputArgs.getOutputFile()).toAbsolutePath());
        System.out.println();

        //третий тест
        inputArgs.setInputFile(test.testPath3In);
        inputArgs.setOutputFile(test.testPath3Out);
        System.out.println("Test 3");
        MainLogic.runMainLogic(inputArgs);
        System.out.println("Output file path: " + Paths.get(inputArgs.getOutputFile()).toAbsolutePath());
        System.out.println();

        //четвёртый тест
        inputArgs.setInputFile(test.testPath4In);
        inputArgs.setOutputFile(test.testPath4Out);
        System.out.println("Test 4");
        MainLogic.runMainLogic(inputArgs);
        System.out.println("Output file path: " + Paths.get(inputArgs.getOutputFile()).toAbsolutePath());
        System.out.println();

        //пятый тест
        inputArgs.setInputFile(test.testPath5In);
        inputArgs.setOutputFile(test.testPath5Out);
        System.out.println("Test 5");
        MainLogic.runMainLogic(inputArgs);
        System.out.println("Output file path: " + Paths.get(inputArgs.getOutputFile()).toAbsolutePath());
        System.out.println();
    }
}
