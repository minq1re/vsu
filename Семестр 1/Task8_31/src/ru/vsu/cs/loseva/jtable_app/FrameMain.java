package ru.vsu.cs.loseva.jtable_app;

import ru.vsu.cs.loseva.main_logic.ArrayUtils;
import ru.vsu.cs.loseva.main_logic.MainLogic;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;


public class FrameMain extends JFrame{

    private JTable table1;
    private JButton readFileBtn;
    private JButton writeFileBtn;
    private JPanel panelMain;
    private JTable table2;
    private JButton runProgramBtn;


    public FrameMain() throws IOException {


        this.setTitle("Main program");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();

        //создание объектов
        JFileChooser fileChooserOpen = new JFileChooser();
        JFileChooser fileChooserSave = new JFileChooser();

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");


        JTableUtils.initJTableForArray(table1,40,false,false,true, true);
        JTableUtils.initJTableForArray(table2,40,false,false,true, true);

        JFileChooser finalFileChooserOpen = fileChooserOpen;
        readFileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (finalFileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        //считывание двумерного массива из файла выбранного
                        int[][] arr = ArrayUtils.readIntArray2FromFile(finalFileChooserOpen.getSelectedFile().getPath());
                        JTableUtils.writeArrayToJTable(table1, arr);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        runProgramBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[][] arr = JTableUtils.readIntMatrixFromJTable(table1);
                    int[][] resultArr = MainLogic.runSolution(arr);
                    JTableUtils.writeArrayToJTable(table2,resultArr);
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        JFileChooser finalFileChooserSave = fileChooserSave;
        writeFileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (finalFileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        String answer = "Файл пуст";
                        int[][] arr = JTableUtils.readIntMatrixFromJTable(table1);
                        String path = finalFileChooserSave.getSelectedFile().getPath();
                        if (!path.toLowerCase().endsWith(".txt")) {
                            path += ".txt";
                        }
                        ArrayUtils.writeArrayToFile(path,arr);
                        System.out.println("Output file path: " + Paths.get(path).toAbsolutePath());
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
    }
}
