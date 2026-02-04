package ru.vsu.cs.loseva;

import ru.vsu.cs.loseva.Task.SimpleLinkedList;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.text.ParseException;

public class FrameMain extends JFrame{
    private final JFileChooser fileChooserOpen;
    private final JFileChooser fileChooserSave;
    private JTable tableInput;
    private JTable tableOutput;
    private JButton buttonSort;
    private JButton buttonOutput;
    private JButton buttonInput;
    private JPanel panelMain;

    public FrameMain() {
        this.setTitle("Сортировка вставками");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        ru.vsu.cs.loseva.utils.JTableUtils.initJTableForArray(tableInput, 40, true, true, true, true);
        ru.vsu.cs.loseva.utils.JTableUtils.initJTableForArray(tableOutput, 40, true, true, true, true);
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

        buttonSort.addActionListener(e -> {
            try {
                int[] array = ru.vsu.cs.loseva.utils.JTableUtils.readIntArrayFromJTable(tableInput);
                SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
                int i = 0;
                while (i < array.length) {
                    list.addLast(array[i]);
                    i++;
                }
                list.insertionSort();
                int j = 0;
                while (j < list.size()) {
                    array[j] = list.get(j);
                    j++;
                }
                ru.vsu.cs.loseva.utils.JTableUtils.writeArrayToJTable(tableOutput, array);
            } catch (ParseException | SimpleLinkedList.SimpleLinkedListException ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonInput.addActionListener(e -> {
            try {
                if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[][] arr = ru.vsu.cs.loseva.utils.ArrayUtils.readIntArray2FromFile(fileChooserOpen.getSelectedFile().getPath());
                    ru.vsu.cs.loseva.utils.JTableUtils.writeArrayToJTable(tableInput, arr);
                }
            } catch (Exception exception) {
                ru.vsu.cs.loseva.utils.SwingUtils.showErrorMessageBox(exception);
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
                    ru.vsu.cs.loseva.utils.ArrayUtils.writeArrayToFile(file, matrix);
                }
            } catch (Exception exception) {
                ru.vsu.cs.loseva.utils.SwingUtils.showErrorMessageBox(exception);
            }
        });
    }
}
