
package Juego;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author aland
 */

public class pop extends JFrame implements ActionListener{
    private JLabel pic = new JLabel();
    private JButton buttonStart = new JButton();
    private boolean start;
    public void load(){
        pic.setIcon(new ImageIcon("images/startf.png"));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        
        buttonStart.addActionListener(this);
        buttonStart.setBounds(0,200,50,50);
        pic.setBounds(0,0,369,140);
        this.add(pic);
        this.add(buttonStart);
        this.pack();      
    }
    public boolean isReady(){
        return start;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource().equals(buttonStart)){
            start = true;
            System.out.println(ae.getSource());
        }
    }
}
