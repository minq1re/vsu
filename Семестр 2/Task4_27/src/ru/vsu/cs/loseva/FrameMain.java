package ru.vsu.cs.loseva;

import ru.vsu.cs.loseva.utils.JTableUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.text.ParseException;

public class FrameMain extends JFrame {
    private final JFileChooser fileChooserOpen;
    private final JFileChooser fileChooserSave;
    private JTable tableInputT;
    private JTable tableInput;
    private JButton buttonInput;
    private JButton buttonSort;
    private JButton buttonOutputT;
    private JTable tableOutputT;
    private JTable tableOutput;
    private JPanel panelMain;
    private JButton buttonInputT;
    private JButton buttonOutput;

    public FrameMain() {
        this.setTitle("Сортировка вставками");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        ru.vsu.cs.loseva.utils.JTableUtils.initJTableForArray(tableInput, 50, true, true, true, true);
        ru.vsu.cs.loseva.utils.JTableUtils.initJTableForArray(tableOutput, 50, true, true, true, true);
        tableInput.setRowHeight(25);
        tableOutput.setRowHeight(25);

        ru.vsu.cs.loseva.utils.JTableUtils.initJTableForArray(tableInputT, 50, true, true, true, true);
        ru.vsu.cs.loseva.utils.JTableUtils.initJTableForArray(tableOutputT, 50, true, true, true, true);
        tableInput.setRowHeight(25);
        tableOutput.setRowHeight(25);

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
        this.pack();
        buttonSort.addActionListener( e -> {
            try {
                int[] arrayInput = JTableUtils.readIntArrayFromJTable(tableInput);
                String[] arrayInputT = JTableUtils.readStringArrayFromJTable(tableInputT);
                InsertionSort.sort(arrayInputT, arrayInput);
                int[] arrayOutput = arrayInput;
                String[] arrayOutputT = arrayInputT;
                JTableUtils.writeArrayToJTable(tableOutput, arrayOutput);
                JTableUtils.writeArrayToJTable(tableOutputT, arrayOutputT);
            } catch (Exception exception) {
                ru.vsu.cs.loseva.Util.SwingUtils.showErrorMessageBox(exception);
            }
        });
        buttonInput.addActionListener(e -> {
            try {
                if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[][] arr = ru.vsu.cs.loseva.Util.ArrayUtils.readIntArray2FromFile(fileChooserOpen.getSelectedFile().getPath());
                    ru.vsu.cs.loseva.utils.JTableUtils.writeArrayToJTable(tableInput, arr);
                }
            } catch (Exception exception) {
                ru.vsu.cs.loseva.Util.SwingUtils.showErrorMessageBox(exception);
            }
        });
        buttonInputT.addActionListener(e -> {
            try {
                if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    String[] arr = ru.vsu.cs.loseva.Util.ArrayUtils.readLinesFromFile(fileChooserOpen.getSelectedFile().getPath());
                    ru.vsu.cs.loseva.utils.JTableUtils.writeArrayToJTable(tableInputT, arr);
                }
            } catch (Exception exception) {
                ru.vsu.cs.loseva.Util.SwingUtils.showErrorMessageBox(exception);
            }
        });
        buttonOutput.addActionListener(e -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[][] matrix = ru.vsu.cs.loseva.utils.JTableUtils.readIntMatrixFromJTable(tableOutput);
                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    ru.vsu.cs.loseva.Util.ArrayUtils.writeArrayToFile(file, matrix);
                }
            } catch (Exception exception) {
                ru.vsu.cs.loseva.Util.SwingUtils.showErrorMessageBox(exception);
            }
        });
        /*buttonOutputT.addActionListener(e -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    String[] matrix = ru.vsu.cs.loseva.utils.JTableUtils.readStringArrayFromJTable(tableOutputT);
                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    ru.vsu.cs.loseva.utils.ArrayUtils.writeArrayToFile(file, matrix);
                }
            } catch (Exception exception) {
                ru.vsu.cs.loseva.Util.SwingUtils.showErrorMessageBox(exception);
            }
        });*/
    }
}
