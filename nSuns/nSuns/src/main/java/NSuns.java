import models.CalorieCalculator;
import models.LifeStyle;
import models.User;
import utils.LoadUserBody;
import utils.SaveUserBody;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class NSuns {
    private JFrame frame;
    private JPanel contentPanel;
    private User user;
    private int age;
    private int height;
    private int weight;
    private JTextField heightTextField;
    private JTextField ageTextField;
    private JTextField weightTextField;
    private JPanel menuPanel;
    private LifeStyle lifeStyle;
    private List<LifeStyle> lifeStyles;
    private JPanel showDisplayPanel;
    private CalorieCalculator calorieCalculator;


    public static void main(String[] args) throws FileNotFoundException {
        NSuns application = new NSuns();

        application.run();
    }

    public NSuns() throws FileNotFoundException {
        LoadUserBody loadUserBody = new LoadUserBody();

        this.user = LoadUserBody.Loader();
    }

    private void run() {
        createLifeStyles();

        user = new User("", 0, 0, 0);
        frame = new JFrame("nSuns");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);

        menuPanel = new JPanel();
        frame.add(menuPanel, BorderLayout.PAGE_START);
        initMenuPanel();

        initFirstPage();

        initContentPanel();

        initShowDisplayPanel();

        frame.setVisible(true);
    }

    private void initFirstPage() {
        menuPanel.add(setCalorieButton("기초 대사량"));

//        JButton workOutStartButton = new JButton("프로그램 시작");
//
//        menuPanel.add(workOutStartButton);
//
        JButton setWeightButton = new JButton("프로그램 세팅");
        menuPanel.add(setWeightButton);
    }

    private void createLifeStyles() {
        lifeStyles = new ArrayList<>();
        LifeStyle lifeStyle1 = new LifeStyle("빈둥빈둥", 1.4);
        lifeStyles.add(lifeStyle1);
        LifeStyle lifeStyle2 = new LifeStyle("좌식업무", 1.5);
        lifeStyles.add(lifeStyle2);
        LifeStyle lifeStyle3 = new LifeStyle("돌아다니는 업무", 1.75);
        lifeStyles.add(lifeStyle3);
        LifeStyle lifeStyle4 = new LifeStyle("활동적인 업무", 2);
        lifeStyles.add(lifeStyle4);
    }

    private void initContentPanel() {
        contentPanel = new JPanel();
        frame.add(contentPanel);
    }

    private void initMenuPanel() {
        menuPanel = new JPanel();
        frame.add(menuPanel, BorderLayout.PAGE_START);
    }

    private JButton setCalorieButton(String 기초_대사량) {
        JButton button = new JButton(기초_대사량);
        button.addActionListener(e -> {
            menuPanel.removeAll();

            initInputBodyPanel();

            updateDisplayPanel(menuPanel);
        });
        return button;
    }

    private void initInputBodyPanel() {
        calorieCalculator = new CalorieCalculator();

        contentPanel.setPreferredSize(new Dimension(200, 100));
        showDisplayPanel.setPreferredSize(new Dimension(200, 500));

        JPanel panel = new JPanel();
        menuPanel.add(panel);

        JPanel inputPanel = new JPanel();
        showDisplayPanel.add(inputPanel);

        panel.setLayout(new GridLayout(1, 2));
        inputPanel.setLayout(new GridLayout(5, 2));
        inputPanel.setPreferredSize(new Dimension(400, 150));

        panel.add(goFisrtPagePanel("돌아가기"));

        panel.add(goSelectLifeStylePanelButton());

        inputPanel.add(new JLabel("신체 조건을 기입하여 주십시오."));
        inputPanel.add(new JLabel(""));

        inputPanel.add(new JLabel("나이(세) : "));
        inputPanel.add(ageTextField = new JTextField(10));

        inputPanel.add(new JLabel("키(cm) : "));
        inputPanel.add(heightTextField = new JTextField(10));

        inputPanel.add(new JLabel("몸무게(kg) : "));
        inputPanel.add(weightTextField = new JTextField(10));

        inputPanel.add(selectGenderButton("남성"));

        inputPanel.add(selectGenderButton("여성"));
    }

    private JButton goFisrtPagePanel(String 돌아가기) {
        JButton button = new JButton(돌아가기);
        button.addActionListener(e -> {
            menuPanel.removeAll();
            contentPanel.removeAll();
            showDisplayPanel.removeAll();

            initFirstPage();

            updateDisplayPanel(menuPanel);
            updateDisplayPanel(showDisplayPanel);
            updateDisplayPanel(contentPanel);
        });
        return button;
    }

    private JButton selectGenderButton(String gender) {
        JButton button = new JButton(gender);
        button.addActionListener(e -> {
            user.updateGender(gender);
        });
        return button;
    }

    private JButton goSelectLifeStylePanelButton() {
        JButton button = new JButton("완료");
        button.addActionListener(e -> {
            age = Integer.parseInt(ageTextField.getText());
            height = Integer.parseInt(heightTextField.getText());
            weight = Integer.parseInt(weightTextField.getText());

            calculateBasicCalorie();

            user = new User(user.userGender(), age, height, weight);

            menuPanel.removeAll();
            showDisplayPanel.removeAll();

            SaveUserBody();

            initLifeStylePanel();

            updateDisplayPanel(menuPanel);
            updateDisplayPanel(showDisplayPanel);
        });
        return button;
    }

    private void calculateBasicCalorie() {
        if (user.userGender().equals("남성")) {
            calorieCalculator.manBasicCalorie(weight, height, age);
        }
        if (user.userGender().equals("여성")) {
            calorieCalculator.womanBasicCalorie(weight, height, age);
        }
    }

    private void initLifeStylePanel() {
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));
        contentPanel.setPreferredSize(new Dimension(200, 200));
        showDisplayPanel.setPreferredSize(new Dimension(200, 350));

        menuPanel.add(goInputBodyPanel("돌아가기"));
        menuPanel.add(goFisrtPagePanel("완료"));

        JPanel titlePanel = new JPanel();
        contentPanel.add(titlePanel);
        contentPanel.setLayout(new GridLayout(5, 1));

        titlePanel.add(new JLabel("활동 유형를 선택해주십시오."));

        for (LifeStyle lifeStyle : lifeStyles) {
            JPanel panel = new JPanel();
            contentPanel.add(panel);
            panel.add(createLifeStyleButton(lifeStyle));
        }
    }

    private JButton goInputBodyPanel(String 돌아가기) {
        JButton button = new JButton(돌아가기);
        button.addActionListener(e -> {
            menuPanel.removeAll();
            contentPanel.removeAll();
            showDisplayPanel.removeAll();

            initInputBodyPanel();

            updateDisplayPanel(menuPanel);
            updateDisplayPanel(contentPanel);
            updateDisplayPanel(showDisplayPanel);
        });
        return button;
    }

    private void initShowDisplayPanel() {
        showDisplayPanel = new JPanel();
        frame.add(showDisplayPanel, BorderLayout.SOUTH);
    }

    private JButton createLifeStyleButton(LifeStyle lifeStyle) {
        JButton button = new JButton(lifeStyle.style());
        button.addActionListener(e -> {
            showDisplayPanel.removeAll();
            showDisplayPanel.setLayout(new GridLayout(5, 1));

            calorieCalculator.activityCalorieCalculate(lifeStyle.activityIntensity());

            showDisplayPanel.add(new JLabel(lifeStyle.toString()));
            showDisplayPanel.add(new JLabel("섭취해야할 칼로리: " + calorieCalculator.activityCalorie()));

            updateDisplayPanel(showDisplayPanel);
        });
        return button;
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

    private void updateDisplayPanel(JPanel Panel) {
        Panel.setVisible(false);
        Panel.setVisible(true);
        frame.setVisible(true);
    }
}
