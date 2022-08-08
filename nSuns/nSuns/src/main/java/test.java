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

        JButton genderButton = new JButton("남성");
        genderButton.addActionListener(e -> {
//            user.updateManGender();
//
//            label.setText(user.userGender());
//            panel.setVisible(false);
//            panel.setVisible(true);
//            frame.setVisible(true);
        });
        panel.add(genderButton);

        JButton gender2Button = new JButton("여성");
        genderButton.addActionListener(e -> {
            user.updateWomanGender();
            panel.setVisible(false);
            panel.setVisible(true);
            frame.setVisible(true);
        });
        panel.add(gender2Button);

        label = new JLabel(user.userGender());
        panel.add(label);


        frame.setVisible(true);
    }
}
