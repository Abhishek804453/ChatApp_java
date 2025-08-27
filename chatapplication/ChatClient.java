package chatapplication;

import java.io.*;
import java.net.*;
import javax.swing.*;

public class ChatClient extends JFrame {
    private JTextArea msg_area;
    private JTextField msg_txt;
    static Socket s;
    static DataInputStream dis;
    static DataOutputStream dout;

    public ChatClient() {
        setTitle("Client");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        msg_area = new JTextArea();
        msg_area.setEditable(false);
        JScrollPane sp = new JScrollPane(msg_area);

        msg_txt = new JTextField();
        JButton sendBtn = new JButton("Send");
        sendBtn.addActionListener(e -> sendMessage());

        JPanel bottom = new JPanel(new java.awt.BorderLayout());
        bottom.add(msg_txt, java.awt.BorderLayout.CENTER);
        bottom.add(sendBtn, java.awt.BorderLayout.EAST);

        add(sp, "Center");
        add(bottom, "South");

        getContentPane().setBackground(new java.awt.Color(255, 228, 196));
        setVisible(true);

        // Receive thread
        new Thread(() -> {
            try {
                s = new Socket("127.0.0.1", 1201);
                dis = new DataInputStream(s.getInputStream());
                dout = new DataOutputStream(s.getOutputStream());

                String msgin = "";
                while (!msgin.equals("EXIT")) {
                    msgin = dis.readUTF();
                    msg_area.append("\nServer: " + msgin);
                }
            } catch (Exception e) {
                msg_area.append("\nConnection closed.");
            }
        }).start();
    }

    private void sendMessage() {
        try {
            String msgout = msg_txt.getText().trim();
            dout.writeUTF(msgout);
            msg_area.append("\nYou: " + msgout);
            msg_txt.setText("");
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChatClient());
    }
}
