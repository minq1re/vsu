package ru.vsu.cs.loseva;

import ru.vsu.cs.loseva.Util.ArrayUtils;
import ru.vsu.cs.loseva.Util.JTableUtils;
import ru.vsu.cs.loseva.Util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.text.ParseException;


public class FrameMain extends JFrame {
    private final JFileChooser fileChooserOpen;
    private final JFileChooser fileChooserSave;
    private JTable tableInput;
    private JTable tableOutput;
    private JButton buttonSort;
    private JButton buttonOutput;
    private JButton buttonInput;
    private JPanel panelMain;

    public FrameMain() {
        this.setTitle("Сортировка пузырьком");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(tableInput, 40, true, true, true, true);
        JTableUtils.initJTableForArray(tableOutput, 40, true, true, true, true);
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
                int[] array = JTableUtils.readIntArrayFromJTable(tableInput);
                SimpleLinkedListQueue2<Integer> queue = new SimpleLinkedListQueue2<>();
                int i = 0;
                while (i < array.length) {
                    queue.add(array[i]);
                    i++;
                }
                queue.bubbleSorted();
                int j = 0;
                while (j < queue.size()) {
                    array[j] = queue.get(j);
                    j++;
                }
                JTableUtils.writeArrayToJTable(tableOutput, array);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonInput.addActionListener(e -> {
            try {
                if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[][] arr = ArrayUtils.readIntArray2FromFile(fileChooserOpen.getSelectedFile().getPath());
                    JTableUtils.writeArrayToJTable(tableInput, arr);
                }
            } catch (Exception exception) {
                SwingUtils.showErrorMessageBox(exception);
            }
        });
        buttonOutput.addActionListener(e -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[][] matrix = JTableUtils.readIntMatrixFromJTable(tableOutput);
                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    ArrayUtils.writeArrayToFile(file, matrix);
                }
            } catch (Exception exception) {
                SwingUtils.showErrorMessageBox(exception);
            }
        });
    }
}