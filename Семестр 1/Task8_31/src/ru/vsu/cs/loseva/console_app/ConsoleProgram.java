package ru.vsu.cs.loseva.console_app;

import ru.vsu.cs.loseva.main_logic.InputArgs;
import ru.vsu.cs.loseva.main_logic.MainLogic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.Objects;


public class ConsoleProgram {

    static InputArgs inputArgs = new InputArgs();

    //метод чтения из консоли строки и преобразование её в набор строковых аргументов
    public static String[] readConsoleLineParameters(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String cmdLine = null;
        try {
            cmdLine=bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert cmdLine != null;
        return cmdLine.split("\\s");
    }

    //парсинг введённых в консоль данных и присваивание путей
    public static InputArgs parseCmdArgs(String[] args) {
        String inputFilePathVar, outputFilePathVar;
        if (args.length == 2) {
            inputFilePathVar = args[0];
            outputFilePathVar = args[1];
        } else {
            inputFilePathVar = args[1];
            outputFilePathVar = args[3];
        }
        inputArgs.setInputFile(inputFilePathVar);
        inputArgs.setOutputFile(outputFilePathVar);
        return inputArgs;
    }

    public static void main(String[] args) throws FileNotFoundException {

        //запуск тестов
        MainLogic.runTest(inputArgs);
        System.out.print("Введите названия основных файлов: ");

        //создание заготовки строки для дальнейшего парсинга
        String[] argsCmd = readConsoleLineParameters();
        inputArgs = parseCmdArgs(argsCmd);

        //проверка на правильность введенных адресов
        if(!Objects.equals(inputArgs.getInputFile(), ".\\input.txt") || !Objects.equals(inputArgs.getOutputFile(), ".\\output.txt")){
            System.out.println("Неправильные параметры, пожалуйста запустите программу ещё раз");
        } else {
            //запуск решения
            MainLogic.runMainLogic(inputArgs);
            System.out.println("Output file path: " + Paths.get(inputArgs.getOutputFile()).toAbsolutePath());
        }
    }
}
