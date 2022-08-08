import models.User;

import javax.swing.*;

public class test {

    private static User user;
    private static JLabel label;

    public static void main(String[] args){
        JFrame frame =new JFrame();
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);

        user = new User("1",1,1,1);



        frame.setVisible(true);
    }
}
