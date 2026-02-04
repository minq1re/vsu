package ru.vsu.cs.loseva;

import ru.vsu.cs.loseva.utils.ArrayUtils;
import ru.vsu.cs.loseva.utils.JTableUtils;
import ru.vsu.cs.loseva.utils.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FrameMain extends JFrame {
    private final JFileChooser fileChooserOpen;
    private final JFileChooser fileChooserSave;
    private JPanel panelMain;
    private JTable tableInput;

    private JButton inputButton;
    private JButton outputButton;
    private JTable tableOutput;
    private JButton determinButton;
    private JTextField textField1;
    private JButton inverseButton;
    private JButton checkButton;
    private JTable tableOutputCheck;
    private JTable tableInputCLAY;
    private JButton buttonKramer;
    private JButton buttonLinear;
    private JButton buttonInp;
    private JButton buttonGauss;
    private JTextField textFieldGauss;

    public FrameMain() {
        this.setTitle("MLITA");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(tableInput, 50, true, true, true, true);
        JTableUtils.initJTableForArray(tableOutput, 50, true, true, true, true);
        tableInput.setRowHeight(25);
        tableOutput.setRowHeight(25);

        JTableUtils.initJTableForArray(tableInputCLAY, 50, true, true, true, true);
        tableInputCLAY.setRowHeight(25);

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

        JMenuBar menuBarMain = new JMenuBar();
        setJMenuBar(menuBarMain);

        JMenu menuLookAndFeel = new JMenu();
        menuLookAndFeel.setText("Вид");
        menuBarMain.add(menuLookAndFeel);
        SwingUtils.initLookAndFeelMenu(menuLookAndFeel);
        this.pack();

        inverseButton.addActionListener(e -> {
            try {
                double[][] arrayInput = JTableUtils.readDoubleMatrixFromJTable(tableInput);
                double[][] arrayOutput = Main.matrixInverse(arrayInput);
                JTableUtils.writeArrayToJTable(tableOutput, arrayOutput);
            } catch (Exception exception) {
                SwingUtils.showErrorMessageBox(exception);
            }
        });
        buttonLinear.addActionListener(e -> {
            try {
                double[][] arrayInput = JTableUtils.readDoubleMatrixFromJTable(tableInputCLAY);
                double[] arrayOutput = Main.inverseResult(arrayInput);
                JTableUtils.writeArrayToJTable(tableOutput, arrayOutput);
            } catch (Exception exception) {
                SwingUtils.showErrorMessageBox(exception);
            }
        });
        buttonKramer.addActionListener(e -> {
            try {
                double[][] arrayInput = JTableUtils.readDoubleMatrixFromJTable(tableInputCLAY);
                double[] arrayOutput = Main.kramerResult(arrayInput);
                JTableUtils.writeArrayToJTable(tableOutput, arrayOutput);
            } catch (Exception exception) {
                SwingUtils.showErrorMessageBox(exception);
            }
        });
        buttonGauss.addActionListener(e -> {
            try {
                double[][] arrayInput = JTableUtils.readDoubleMatrixFromJTable(tableInputCLAY);
                StringBuilder arrayOutput = Main.gauss(arrayInput);
                textFieldGauss.setText(String.valueOf(arrayOutput));
            } catch (Exception exception) {
                SwingUtils.showErrorMessageBox(exception);
            }
        });
        buttonInp.addActionListener(e -> {
            try {
                if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[][] arr = ArrayUtils.readIntArray2FromFile(fileChooserOpen.getSelectedFile().getPath());
                    JTableUtils.writeArrayToJTable(tableInputCLAY, arr);
                }
            } catch (Exception exception) {
                SwingUtils.showErrorMessageBox(exception);
            }
        });
        checkButton.addActionListener(e -> {
            try {
                double[][] arrayInput = JTableUtils.readDoubleMatrixFromJTable(tableInput);
                double[][] arrayOutput = JTableUtils.readDoubleMatrixFromJTable(tableOutput);
                double[][] arrayOutputCheck = Main.check(arrayInput, arrayOutput);
                JTableUtils.writeArrayToJTable(tableOutputCheck, arrayOutputCheck);
            } catch (Exception exception) {
                SwingUtils.showErrorMessageBox(exception);
            }
        });
        determinButton.addActionListener(e -> {
            try {
                double[][] arrayInput = JTableUtils.readDoubleMatrixFromJTable(tableInput);
                assert arrayInput != null;
                double output = Main.determinant(arrayInput);
                textField1.setText(String.valueOf(output));
            } catch (Exception exception) {
                SwingUtils.showErrorMessageBox(exception);
            }
        });
        inputButton.addActionListener(e -> {
            try {
                if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[][] arr = ArrayUtils.readIntArray2FromFile(fileChooserOpen.getSelectedFile().getPath());
                    JTableUtils.writeArrayToJTable(tableInput, arr);
                }
            } catch (Exception exception) {
                SwingUtils.showErrorMessageBox(exception);
            }
        });
        outputButton.addActionListener(e -> {
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
