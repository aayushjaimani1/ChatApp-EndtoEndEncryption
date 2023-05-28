import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;
import java.util.Base64;

public class NewClient extends JFrame implements ActionListener {
    JTextField text;
    static JPanel mp;
    static Box vertical = Box.createVerticalBox();
    static DataOutputStream dout;

    NewClient() {
        super("Client");
        setLayout(null);
        createGUI();
        setSize(468, 745);
        setLocation(800, 50);
        getContentPane().setBackground(new Color(137, 207, 240));
        setVisible(true);
    }