import models.User;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

public class NSuns {

    private JFrame frame;
    private JPanel contentPanel;
    private User user;

    public static void main(String[] args) {
        NSuns application = new NSuns();

        application.run();
    }

    private void run() {
        frame = new JFrame("nSuns");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);

        initMenuPanel();

        initContentPanel();

        frame.setVisible(true);
    }

    private void initContentPanel() {
        contentPanel = new JPanel();
        frame.add(contentPanel);
    }

    private void initMenuPanel() {
        JPanel menuPanel = new JPanel();
        frame.add(menuPanel,BorderLayout.PAGE_START);

        JButton setCalorieButton = new JButton("기초 대사량");
        setCalorieButton.addActionListener(e -> {
            menuPanel.removeAll();

            initSetCaloriePanel();

            menuPanel.setVisible(false);
            menuPanel.setVisible(true);
            frame.setVisible(true);
        });
        menuPanel.add(setCalorieButton);

        JButton workOutStartButton = new JButton("프로그램 시작");

        menuPanel.add(workOutStartButton);

        JButton setWeightButton = new JButton("프로그램 세팅");
        menuPanel.add(setWeightButton);
    }

    private void initSetCaloriePanel() {
        JPanel panel = new JPanel();
        contentPanel.add(panel);

        String gender = user.userGender();

        int age = 0;
        int height = 0;
        int weight = 0;

        panel.setLayout(new GridLayout(5,2));

        JButton genderButton = new JButton("남성");
        genderButton.addActionListener(e -> {
            user.updateManGender();
        });
        panel.add(genderButton);

        JButton gender2Button = new JButton("여성");
        genderButton.addActionListener(e -> {
            user.updateWomanGender();
        });
        panel.add(gender2Button);

        panel.add(new JLabel("나이(세) : "));

        JTextField ageTextLabel = new JTextField(10);
        panel.add(ageTextLabel);

        panel.add(new JLabel("키(cm) : "));

        JTextField heightTextField = new JTextField(10);
        panel.add(heightTextField);

        panel.add(new JLabel("몸무게(kg) : "));

        JTextField weightTextField = new JTextField(10);
        panel.add(weightTextField);

        JButton goBackPageButton = new JButton("돌아가기");
        panel.add(goBackPageButton);

        JButton nextPageButton = new JButton("완료");
        nextPageButton.addActionListener(e -> {
            contentPanel.removeAll();
            User user =new User(gender,age,height,weight);

            initLifeStylePanel();
        });
        panel.add(nextPageButton);
    }

    private void initLifeStylePanel() {
    }
}
