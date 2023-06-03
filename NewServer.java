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