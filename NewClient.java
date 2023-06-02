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

    public void createGUI() {
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(0, 0, 128));
        p1.setBounds(0, 0, 450, 70);
        p1.setLayout(null);
        add(p1);

        JLabel name = new JLabel("Client");
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
        send.setBackground(new Color(0, 0, 128));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(send);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            String str = text.getText();
            JPanel p2 = formatLabel(str);

            mp.setLayout(new BorderLayout());

            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));

            mp.add(vertical, BorderLayout.PAGE_START);

            String encodedMessage = encodeMessage(str);
            System.out.println("Encoded Message: " + encodedMessage);
            dout.writeUTF(encodedMessage);

            text.setText("");

            repaint();
            invalidate();
            validate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static JPanel formatLabel(String str) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel output = new JLabel("<html><p style=\"width: 150px\">" + str + "</p></html>");
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(4, 118, 208));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15, 15, 15, 50));

        panel.add(output);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));

        panel.add(time);

        return panel;
    }

    public static String encodeMessage(String message) {
        byte[] encodedBytes = Base64.getEncoder().encode(message.getBytes());
        return new String(encodedBytes);
    }

    public static String decodeMessage(String encodedMessage) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedMessage.getBytes());
        return new String(decodedBytes);
    }

    public static void main(String[] args) {
        NewClient client = new NewClient();

        try {
            Socket s = new Socket("127.0.0.1", 6001);
            DataInputStream din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            while (true) {
                mp.setLayout(new BorderLayout());
                String encodedMsg = din.readUTF();
                String decodedMsg = decodeMessage(encodedMsg);
                JPanel panel = formatLabel(decodedMsg);

                JPanel left = new JPanel(new BorderLayout());
                left.add(panel, BorderLayout.LINE_START);
                vertical.add(left);

                vertical.add(Box.createVerticalStrut(15));
                mp.add(vertical, BorderLayout.PAGE_START);

                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}