import models.User;
import utils.SaveUserBody;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;


public class NSuns {

    private JFrame frame;
    private JPanel contentPanel;
    private User user;
    private int age;
    private int height;
    private int weight;
    private String gender;
    private JTextField heightTextField;
    private JTextField ageTextField;
    private JTextField weightTextField;


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
        frame.add(menuPanel, BorderLayout.PAGE_START);

        JButton setCalorieButton = new JButton("기초 대사량");
        setCalorieButton.addActionListener(e -> {
            menuPanel.removeAll();

            initSetCaloriePanel();

            updateDisplayPanel(menuPanel);
        });
        menuPanel.add(setCalorieButton);

        JButton workOutStartButton = new JButton("프로그램 시작");

        menuPanel.add(workOutStartButton);

        JButton setWeightButton = new JButton("프로그램 세팅");
        menuPanel.add(setWeightButton);
    }

    private void updateDisplayPanel(JPanel Panel) {
        Panel.setVisible(false);
        Panel.setVisible(true);
        frame.setVisible(true);
    }

    private void initSetCaloriePanel() {
        JPanel panel = new JPanel();
        contentPanel.add(panel);

        panel.setLayout(new GridLayout(5, 2));

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

        ageTextField = new JTextField(10);
        panel.add(ageTextField);

        panel.add(new JLabel("키(cm) : "));

        heightTextField = new JTextField(10);
        panel.add(heightTextField);

        panel.add(new JLabel("몸무게(kg) : "));

        weightTextField = new JTextField(10);
        panel.add(weightTextField);

        JButton goBackPageButton = new JButton("돌아가기");
        goBackPageButton.addActionListener(e -> {
            contentPanel.removeAll();

            initMenuPanel();

            updateDisplayPanel(contentPanel);
        });

        panel.add(goBackPageButton);


        panel.add(nextPageButton);
        panel.add(nextPage());
    }

    private JButton nextPage() {
        JButton button = new JButton("완료");
        button.addActionListener(e -> {


            age = Integer.parseInt(ageTextField.getText());
            height = Integer.parseInt(heightTextField.getText());
            weight = Integer.parseInt(weightTextField.getText());
            gender = user.userGender();

//            user = new User(gender, age, height, weight);

            contentPanel.removeAll();

            SaveUserBody();

            initLifeStylePanel();

            updateDisplayPanel(contentPanel);

        });
        return button;
    }

    private void initLifeStylePanel() {
        JPanel panel = new JPanel();
        contentPanel.add(panel);

        JTextField timeTextField = new JTextField(10);
        panel.add(timeTextField);
    }

    private void SaveUserBody() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    SaveUserBody saveUserBody = new SaveUserBody(user);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}











//    JButton nextPageButton = new JButton("완료");
//        nextPageButton.addActionListener(e -> {
//
//
//                age = Integer.parseInt(ageTextField.getText());
//                height = Integer.parseInt(heightTextField.getText());
//                weight = Integer.parseInt(weightTextField.getText());
//                gender = user.userGender();
//
////            user = new User(gender, age, height, weight);
//
//                contentPanel.removeAll();
//
//                SaveUserBody();
//
//                initLifeStylePanel();
//
//                updateDisplayPanel(contentPanel);