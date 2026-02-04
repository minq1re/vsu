package ru.vsu.cs.loseva;

import ru.vsu.cs.loseva.Task.Task;
import ru.vsu.cs.loseva.Util.ArrayUtils;
import ru.vsu.cs.loseva.Util.JTableUtils;
import ru.vsu.cs.loseva.Util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

    public class FrameMain extends JFrame {
        private JButton buttonLoadFromFile;
        //private JButton buttonRandomNumbers;
        private JButton buttonSaveInputIntoFile;
        private JButton buttonPerformSorting;
        private JButton buttonSaveSortedIntoFile;
        private JTable tableInput;
        private JTable tableSorted;
        private JScrollPane JScrollInput;
        private JScrollPane JScrollOutput;
        private JPanel PanelMain;

        public FrameMain() {
            this.setTitle("Переворот списка");
            this.setContentPane(PanelMain);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.pack();
            JTableUtils.initJTableForArray(tableInput, 40, false, true, false, true);
            JTableUtils.initJTableForArray(tableSorted, 40, false, true, false, true);
            tableInput.setRowHeight(25);
            JScrollInput.setPreferredSize(new Dimension(-1, 90));
            JScrollOutput.setPreferredSize(new Dimension(-1, 90));
            JFileChooser fileChooserOpen = new JFileChooser();
            JFileChooser fileChooserSave = new JFileChooser();
            fileChooserOpen.setCurrentDirectory(new File("D:\\ВВП\\Task9_14\\src\\ru\\vsu\\cs\\loseva\\Files"));
            fileChooserSave.setCurrentDirectory(new File("D:\\ВВП\\Task9_14\\src\\ru\\vsu\\cs\\loseva\\Files"));
            FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
            fileChooserOpen.addChoosableFileFilter(filter);
            fileChooserSave.addChoosableFileFilter(filter);
            fileChooserSave.setAcceptAllFileFilterUsed(false);
            fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
            fileChooserSave.setApproveButtonText("Save");
            JTableUtils.writeArrayToJTable(tableInput, new int[]{2, 11, 8, 11, 2, 3, 2, 2, 6, 7, 2, 3, 5, 10});
            this.pack();
            this.setSize(650, 350);

            buttonLoadFromFile.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (fileChooserOpen.showOpenDialog(PanelMain) == JFileChooser.APPROVE_OPTION) {
                            int[] array = ArrayUtils.readIntArrayFromFile(fileChooserOpen.getSelectedFile().getPath());
                            JTableUtils.writeArrayToJTable(tableInput, array);
                        }
                    } catch (Exception exception) {
                        SwingUtils.showErrorMessageBox(exception);
                    }
                }
            });
            /*buttonRandomNumbers.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int[] array = ArrayUtils.createRandomIntArray(tableInput.getColumnCount(), 0, 100);
                        JTableUtils.writeArrayToJTable(tableInput, array);
                    } catch (Exception exception) {
                        SwingUtils.showErrorMessageBox(exception);
                    }
                }
            });*/
            buttonSaveInputIntoFile.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (fileChooserSave.showSaveDialog(PanelMain) == JFileChooser.APPROVE_OPTION) {
                            int[] array = JTableUtils.readIntArrayFromJTable(tableInput);
                            String file = fileChooserSave.getSelectedFile().getPath();
                            if (!file.toLowerCase().endsWith(".txt")) {
                                file += ".txt";
                            }
                            ArrayUtils.writeArrayToFile(file, array);
                        }
                    } catch (Exception exception) {
                        SwingUtils.showErrorMessageBox(exception);
                    }
                }
            });
            buttonPerformSorting.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int[] array = JTableUtils.readIntArrayFromJTable(tableInput);
                        List<Integer> list = new ArrayList<>();
                        int i = 0;
                        while (i < array.length) {
                            list.add(array[i]);
                            i++;
                        }
                        Task.process(list);
                        int j = 0;
                        while (j < list.size()) {
                            array[j] = list.get(j);
                            j++;
                        }
                        JTableUtils.writeArrayToJTable(tableSorted, array);
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            buttonSaveSortedIntoFile.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (fileChooserSave.showSaveDialog(PanelMain) == JFileChooser.APPROVE_OPTION) {
                            int[] array = JTableUtils.readIntArrayFromJTable(tableSorted);
                            String file = fileChooserSave.getSelectedFile().getPath();
                            if (!file.toLowerCase().endsWith(".txt")) {
                                file += ".txt";
                            }
                            ArrayUtils.writeArrayToFile(file, array);
                        }
                    } catch (Exception exception) {
                        SwingUtils.showErrorMessageBox(exception);
                    }
                }
            });
        }
    }