// Author: Wentao Li, 953828

package Client;

import Utility.PortValidation;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;


public class UI {
    private JPanel panel1;
    private JPanel dictionary;
    private JComboBox operationBox;
    private JTextField word;
    private JTextField meaning;
    private JTextArea output;
    private JButton submitButton;

    public UI()
    {
        client.start();
        Initialize();
    }

    static Client client = new Client();

    public static void main(String[] args)
    {
        if (!IsValid(args)) {
            System.exit(1);
        }
        Client.ip = args[0];
        Client.port = Integer.valueOf(args[1]);

        JFrame frame = new JFrame("Dictionary Client");
        frame.setContentPane(new UI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }

    private void Initialize()
    {
        operationBox.addItem("ADD");
        operationBox.addItem("DELETE");
        operationBox.addItem("QUERY");
        operationBox.addItem("UPDATE");

        operationBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String action = Objects.requireNonNull(operationBox.getSelectedItem()).toString();
                if (Objects.equals(action, "DELETE") || Objects.equals(action, "QUERY")) {
                    meaning.setText("");
                    meaning.setEditable(false);
                } else {
                    meaning.setEditable(true);
                }
            }
        });


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent eve) {
                String action = Objects.requireNonNull(operationBox.getSelectedItem()).toString();
                String wordInput = word.getText();
                String meaningInput = meaning.getText();
                if (InputValidation(action, wordInput, meaningInput)) {
                    try {
                        JSONObject json = new JSONObject();
                        json.put("operation", action);
                        json.put("word", wordInput);
                        json.put("meaning", meaningInput);
                        String request = json.toString();

                        while (true) {
                            if (client.reqQueue.isEmpty()) {
                                client.reqQueue.put(request);
                                break;
                            }
                        }
                        String rsp = null;
                        rsp = client.GetRsp();
                        if (rsp == null) {
                            output.setText("You are disconnected");
                        } else {
                            output.setText(rsp.replaceAll(";", ";\n"));
                        }
                    } catch (InterruptedException e) {
                        System.out.println("Operation failed!");
                    }

                    word.setText("");
                    meaning.setText("");
                }
            }
        });
    }
    public static boolean IsValidIPAddress(String ipAddress)
    {
        if (ipAddress.equalsIgnoreCase("localhost")) {
            return true;
        }

        String ipPattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        Pattern pattern = Pattern.compile(ipPattern);
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }

    private boolean InputValidation(String action, String word, String meaning)
    {
        if (word.isEmpty()) {
            output.setText("Word can not be empty!");
            return false;
        }
        if (word.length() > 20) {
            output.setText("Length of the input word can not exceed 20!");
            return false;
        }

        if (action.equals("ADD") || action.equals("UPDATE")) {
            if (meaning.isEmpty()) {
                output.setText("Meaning can not be empty!");
                return false;
            }

            if (meaning.length() > 200) {
                output.setText("Length of the input meaning can not exceed 200!");
                return false;
            }
        }
        return true;
    }


    private static boolean IsValid(String[] args) {
        if (args.length != 2) {
            System.out.println("Log Error: Incorrect number of arguments for main function: " + args.length);
            return false;
        }
        if (!IsValidIPAddress(args[0])) {
            System.out.println("Log Error: Invalid IP Adress: " + args[0]);
            return false;
        }

        return PortValidation.PortIsValid(args[1]);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        dictionary = new JPanel();
        dictionary.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(dictionary, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        dictionary.setBorder(BorderFactory.createTitledBorder(null, "Wentao Li ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label1 = new JLabel();
        label1.setText("Dictionary");
        dictionary.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Operation");
        dictionary.add(label2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        operationBox = new JComboBox();
        operationBox.setEditable(false);
        dictionary.add(operationBox, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Word");
        dictionary.add(label3, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        word = new JTextField();
        dictionary.add(word, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(300, -1), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Meaning");
        dictionary.add(label4, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        meaning = new JTextField();
        dictionary.add(meaning, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(300, -1), null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Output");
        dictionary.add(label5, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        output = new JTextArea();
        output.setEditable(false);
        output.setText("");
        dictionary.add(output, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 100), null, 0, false));
        submitButton = new JButton();
        submitButton.setText("submit");
        panel1.add(submitButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
