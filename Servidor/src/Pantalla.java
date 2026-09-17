import javax.swing.*;
import java.awt.*;

public class Pantalla extends JFrame{
    public Pantalla(){
        this.setLayout(new GridLayout(1,1));
        JButton b = new JButton("Mandar");
        JPanel p = new JPanel();
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout());
        JTextArea t = new JTextArea();
        p2.add(t);
        p.setLayout(new BorderLayout());
        p.add(b,BorderLayout.NORTH);
        p.add(p2,BorderLayout.CENTER);
        b.addActionListener(l -> {
            String a = t.getText();
            t.setText("");
            System.out.println(a);
        });
        //p.add(new JButton("adios"));
        this.add(p);
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.pack();
        this.setVisible(true);
    }
}
