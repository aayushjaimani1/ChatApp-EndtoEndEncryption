import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;
import java.util.Base64;

public class NewServer extends JFrame implements ActionListener {

    JTextField text;
    JPanel mp;
    static Box vertical = Box.createVerticalBox();
    static DataOutputStream dout;

    NewServer() {
        super("Server");
        setLayout(null);
        createGUI();
        setSize(468, 745);
        setLocation(200, 50);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    public void createGUI() {
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        p1.setLayout(null);
        add(p1);

        JLabel name = new JLabel("Server");
        name.setBounds(10, 15, 100, 18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        p1.add(name);

        JLabel status = new JLabel("Connected");
        status.setBounds(10, 35, 100, 18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
        p1.add(status);

        mp = new JPanel();
        mp.setBounds(5, 75, 440, 570);
        add(mp);

        JScrollPane scrollPane = new JScrollPane(mp);
        scrollPane.setBounds(5, 75, 440, 570);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(null);

        add(scrollPane);

        text = new JTextField();
        text.setBounds(5, 655, 310, 40);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(text);

        JButton send = new JButton("Send");
        send.setBounds(320, 655, 123, 40);
        send.setBackground(new Color(7, 94, 84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(send);
    }