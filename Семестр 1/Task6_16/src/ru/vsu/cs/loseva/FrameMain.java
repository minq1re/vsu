package ru.vsu.cs.loseva;

import ru.vsu.cs.loseva.util.ArrayUtils;
import ru.vsu.cs.loseva.util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FrameMain extends JFrame {
    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;
    private JButton buttonInput;
    private JButton buttonAction;
    private JButton buttonOutput;
    private JTextField textFieldInput;
    private JTextField textFieldOutput;
    private JPanel panelMain;

    public FrameMain() {
        this.setTitle("Поиск аббревиатур");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

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

        buttonInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        String[] arr = ArrayUtils.readLinesFromFile(fileChooserOpen.getSelectedFile().getPath());
                        String str="";
                        for(int i=0;i<arr.length;i++){
                            str+=arr[i];
                        }
                        textFieldInput.setText(str);
                    }
                } catch (Exception error) {
                    SwingUtils.showErrorMessageBox(error);
                }
            }
        });

        buttonAction.addActionListener(e -> {
            try {
                String str = textFieldInput.getText();
                SimpleHashMap<String, Integer> map = new SimpleHashMap<>(16);
                SimpleHashMap<String, Integer> abbreviations = map.findAbbreviations(str);
                StringBuilder output = new StringBuilder();
                for (SimpleHashMap.Entry<String, Integer> entry : abbreviations.entrySet()) {
                    output.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
                }
                textFieldOutput.setText(output.toString());
            } catch (Exception exception) {
                SwingUtils.showErrorMessageBox(exception);
            }
        });
    }
}
