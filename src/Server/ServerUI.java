// Author: Wentao Li, 953828

package Server;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ServerUI extends Thread {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    public ServerUI()
    {
        frame = new JFrame("Operation Log");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 300);

        String[] columnNames = {"time", "Operation", "Word", "Meaning"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
    }

    public void AddRow(String time, String operation, String word, String meaning)
    {
        SwingUtilities.invokeLater(() -> {
            tableModel.addRow(new Object[]{time, operation, word, meaning});
        });
    }

    @Override
    public void run()
    {        SwingUtilities.invokeLater(() -> {
        frame.setVisible(true);
    });

    }
}
