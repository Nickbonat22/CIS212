import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Client extends JFrame
{
        private static final int PORT = 1337;
        private final JTextArea textArea;
        private final JTextField textField;
        private final LinkedBlockingQueue<String> messageQueue;

        public Client()
        {
                super("Prime Finder");
                setLayout(new BorderLayout());
                messageQueue = new LinkedBlockingQueue<>();
                textArea = new JTextArea(10, 40);
                textArea.setEditable(false);
                add(new JScrollPane(textArea), BorderLayout.CENTER);

                textField = new JTextField(40);
                textField.addActionListener(new ActionListener()
                {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                                String text = textField.getText();
                                textArea.append(text + "\n");
                                textArea.setCaretPosition(textArea.getText().length());
                                textField.setText("");

                                try
                                {
                                        messageQueue.put(text);
                                }

                                catch (InterruptedException e1)
                                {
                                        e1.printStackTrace();
                                }
                        }
                });
                add(textField, BorderLayout.SOUTH);
        }

        public void handleNetwork()
        {
                System.out.println("running client!");

                Socket socket = null;
                ObjectOutputStream output = null;
                ObjectInputStream input = null;

                try
                {
                        InetAddress address = InetAddress.getLocalHost();
                        socket = new Socket(address, PORT); //Connect to server

                        System.out.println("socket created");

                        //Get input/output stream
                        output = new ObjectOutputStream(socket.getOutputStream());
                        output.flush();

                        input = new ObjectInputStream(socket.getInputStream());

                        while (true)
                        {
                                textArea.append("Enter '!' to start/stop, '#' to quit:\n");

                                if (messageQueue.take().equals("#"))
                                {
                                        break;
                                }

                                if (messageQueue.take().equals("!"))
                                {
                                        do
                                        {
                                                List<Integer> sendList = new ArrayList<>();
                                                List<Integer> recieveList = new ArrayList<>();
                                                Random randint = new Random();

                                                for (int i = 0; i < 5; i++)
                                                {
                                                        int low = 2;
                                                        int high = 200;
                                                        int num = randint.nextInt(high - low) + low;
                                                        output.writeInt(num);
                                                        output.flush();
                                                        sendList.add(num);
                                                }

                                                Thread.sleep(1000);
                                                textArea.append("Sent: " + sendList.toString() + "\n");
                                                sendList.clear();

                                                while (input.available() != 0)
                                                {
                                                        if (!(input == null))
                                                        {
                                                                int response = input.readInt();
                                                                recieveList.add(response);
                                                        }
                                                }

                                                SwingUtilities.invokeLater(new Runnable()
                                                {
                                                        @Override
                                                        public void run()
                                                        {
                                                                textArea.append("Recieved: " + recieveList + "\n");
                                                                textArea.setCaretPosition(textArea.getText().length());
                                                        }
                                                });
                                        }

                                        while (!messageQueue.take().equals("!"));
                                }

                                else
                                {
                                        textArea.append("Enter '!' to start/stop, '#' to quit:\n");
                                }
                        }
                }

                catch (IOException ex)
                {
                        ex.printStackTrace();
                }

                catch (InterruptedException e)
                {
                        e.printStackTrace();
                }

                finally //Close connection
                {
                        try
                        {
                                if (socket != null)
                                {
                                        socket.close();
                                }
                                if (output != null)
                                {
                                        output.close();
                                }
                                if (input != null)
                                {
                                        input.close();
                                }
                        }

                        catch (IOException ex)
                        {
                                ex.printStackTrace();
                        }
                }

                System.out.println("client finished");
        }

        public static void main(String[] args)
        {
                Client frame = new Client();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                frame.handleNetwork();
        }
}