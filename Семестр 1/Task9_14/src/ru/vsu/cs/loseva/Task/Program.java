package ru.vsu.cs.loseva.Task;

import ru.vsu.cs.loseva.FrameMain;
import ru.vsu.cs.loseva.Util.ArrayUtils;
import ru.vsu.cs.loseva.Util.SwingUtils;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Program {
    public static class CmdParams {
        public String inputFile;
        public String outputFile;
        public boolean error;
        public boolean help;
        public boolean window;
    }

    public static CmdParams parseArgs(String[] args) {
        CmdParams params = new CmdParams();
        if (args.length > 0) {
            if (args[0].equals("--help")) {
                params.help = true;
                return params;
            }
            if (args[0].equals("--window")) {
                params.window = true;
                return params;
            }
            if (args.length < 2) {
                params.help = true;
                params.error = true;
                return params;
            }
            params.inputFile = args[0];
            params.outputFile = args[1];
        } else {
            params.help = true;
            params.error = true;
        }
        return params;
    }

    public static void winMain() {
        //SwingUtils.setLookAndFeelByName("Windows");
        Locale.setDefault(Locale.ROOT);
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtils.setDefaultFont("Microsoft Sans Serif", 18);
        //Create and display the form
        java.awt.EventQueue.invokeLater(() -> new FrameMain().setVisible(true));
    }

    public static void main(String[] args) throws Exception {
        CmdParams params = parseArgs(args);
        if (params.help) {
            System.exit(params.error ? 1 : 0);
        }
        if (params.window) {
            winMain();
        } else {
            int[] arr2 = ArrayUtils.readIntArrayFromFile(params.inputFile);
            if (arr2 == null) {
                System.err.printf("Can't read array from \"%s\"%n", params.inputFile);
                System.exit(2);
            }
            PrintStream out = (params.outputFile != null) ? new PrintStream(params.outputFile) : System.out;
            List<Integer> list = new ArrayList<>();
            int i = 0;
            while (i < arr2.length) {
                list.add(arr2[i]);
                i++;
            }
            Task.process(list);
            out.println(list);
            out.close();
        }
    }
}
