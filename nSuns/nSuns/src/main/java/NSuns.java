import models.*;
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
    private JTextField heightTextField;
    private JTextField ageTextField;
    private JTextField weightTextField;
    private JPanel menuPanel;
    private LifeStyle lifeStyle;
    private List<LifeStyle> lifeStyles;
    private JPanel showDisplayPanel;
    private CalorieCalculator calorieCalculator;
    private JTextField benchPress1RM;
    private JTextField squt1RM;
    private JTextField deadLift1RM;
    private JTextField overHeadPress1RM;
    private TraningWeight1RM traningWeight1RM;
    private TraingWeightCalculator traingWeightCalculator;


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

        List<Reps> reps = loadReps();
        traingWeightCalculator = new TraingWeightCalculator();
        traningWeight1RM = new TraningWeight1RM(0, 0, 0, 0);
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

    private List<Reps> loadReps() {
        return null;
    }

    private void initFirstPage() {
        menuPanel.add(setCalorieButton("기초 대사량"));

        menuPanel.add(programStartButton("프로그램 시작"));

        menuPanel.add(programSetting("프로그램 세팅"));
    }

    private JButton programSetting(String 프로그램_세팅) {
        JButton button = new JButton(프로그램_세팅);
        button.addActionListener(e -> {
            displayRemoveAll();

            initProgramSettingPanel();

            updateAllDisplay();
        });
        return button;
    }

    private void initProgramSettingPanel() {
        menuPanel.add(goFisrtPagePanel("돌아가기"));
        menuPanel.add(programSettingButton());

        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));
        contentPanel.setLayout(new GridLayout(4, 2));
        contentPanel.add(new JLabel("벤치프레스(kg) 1rm: "));
        contentPanel.add(benchPress1RM = new JTextField(10));

        contentPanel.add(new JLabel("스쿼트(kg) 1rm: "));
        contentPanel.add(squt1RM = new JTextField(10));

        contentPanel.add(new JLabel("오버헤드 프레스(kg) 1rm: "));
        contentPanel.add(overHeadPress1RM = new JTextField(10));

        contentPanel.add(new JLabel("데드리프트(kg) 1rm: "));
        contentPanel.add(deadLift1RM = new JTextField(10));

        showDisplayPanel.setPreferredSize(new Dimension(200, 400));
    }

    private JButton programSettingButton() {
        JButton button = new JButton("완료");
        button.addActionListener(e -> {
            creatTraningWeight1RMCreation();

            displayRemoveAll();

            initFirstPage();

            updateAllDisplay();
        });
        return button;
    }

    private void creatTraningWeight1RMCreation() {
        traningWeight1RM = new TraningWeight1RM(Double.parseDouble(benchPress1RM.getText()),
                Double.parseDouble(squt1RM.getText()), Double.parseDouble(overHeadPress1RM.getText()),
                Double.parseDouble(deadLift1RM.getText()));
    }

    private JButton programStartButton(String 프로그램_시작) {
        JButton button = new JButton(프로그램_시작);
        button.addActionListener(e -> {
            displayRemoveAll();

            initSelectProgramPanel();

            updateAllDisplay();
        });
        return button;
    }

    private void initSelectProgramPanel() {
        menuPanel.add(goFisrtPagePanel("돌아가기"));

        contentPanel.setLayout(new GridLayout(1, 4));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 25));
        contentPanel.add(programButton("벤치프레스"));
        contentPanel.add(programButton("스쿼트"));
        contentPanel.add(programButton("오버헤드프레스"));
        contentPanel.add(programButton("데드리프트"));

        showDisplayPanel.setPreferredSize(new Dimension(200, 500));
    }

    private JButton programButton(String workOut) {
        JButton button = new JButton(workOut);
        button.addActionListener(e -> {
            displayRemoveAll();

            initProgramPanel(workOut);

            updateAllDisplay();
        });
        return button;
    }

    private void initProgramPanel(String workOut) {
        menuPanel.add(goSelectProgramPanel("돌아가기"));
        menuPanel.add(new JButton("완료"));

        TrainingWeightCalculate(workOut);

        showDisplayPanel.setPreferredSize(new Dimension(200, 200));
    }

    private void TrainingWeightCalculate(String workOut) {
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30,30,0,30));
        contentPanel.setLayout(new GridLayout(8, 2));
        for (int i = 0; i < 3; i += 1) {
            switch (workOut) {
                case "벤치프레스" -> contentPanel.add(new JLabel((i + 1) + ".Set " + workOut + " " +
                        traingWeightCalculator.calculate((0.75 + 0.1 * i) * traningWeight1RM.benchPress()) +
                        "kg(권장 " + (5 - 2 * i) + "회)"));
                case "스쿼트" -> contentPanel.add(new JLabel((i + 1) + ".Set " + workOut + " " +
                        traingWeightCalculator.calculate((0.75 + 0.1 * i) * traningWeight1RM.squt()) +
                        "kg(권장 " + (5 - 2 * i) + "회)"));
                case "오버헤드프레스" -> contentPanel.add(new JLabel((i + 1) + ".Set " + workOut + " " +
                        traingWeightCalculator.calculate((0.75 + 0.1 * i) * traningWeight1RM.overHeadPress()) +
                        "kg(권장 " + (5 - 2 * i) + "회)"));
                case "데드리프트" -> contentPanel.add(new JLabel((i + 1) + ".Set " + workOut + " " +
                        traingWeightCalculator.calculate((0.75 + 0.1 * i) * traningWeight1RM.deadLift()) +
                        "kg(권장 " + (5 - 2 * i) + "회)"));
            }
            contentPanel.add(new JTextField(10));
        }
        for (int i = 0; i < 5; i += 1) {
            switch (workOut) {
                case "벤치프레스" -> contentPanel.add(new JLabel((i + 4) + ".Set " + workOut + " " +
                        traingWeightCalculator.calculate((0.90 - 0.05 * i) * traningWeight1RM.benchPress()) +
                        "kg(권장 3회)"));
                case "스쿼트" -> contentPanel.add(new JLabel((i + 4) + ".Set " + workOut + " " +
                        traingWeightCalculator.calculate((0.90 - 0.05 * i) * traningWeight1RM.squt()) +
                        "kg(권장 3회)"));
                case "오버헤드프레스" -> contentPanel.add(new JLabel((i + 4) + ".Set " + workOut + " " +
                        traingWeightCalculator.calculate((0.90 - 0.05 * i) * traningWeight1RM.overHeadPress()) +
                        "kg(권장 3회)"));
                case "데드리프트" -> contentPanel.add(new JLabel((i + 4) + ".Set " + workOut + " " +
                        traingWeightCalculator.calculate((0.90 - 0.05 * i) * traningWeight1RM.deadLift()) +
                        "kg(권장 3회)"));
            }
            contentPanel.add(new JTextField(10));
        }
    }

    private JButton goSelectProgramPanel(String 돌아가기) {
        JButton button = new JButton(돌아가기);
        button.addActionListener(e -> {
            displayRemoveAll();

            initSelectProgramPanel();

            updateAllDisplay();
        });
        return button;
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
            displayRemoveAll();

            initFirstPage();

            updateAllDisplay();
        });
        return button;
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

    private void updateAllDisplay() {
        updateDisplayPanel(menuPanel);
        updateDisplayPanel(contentPanel);
        updateDisplayPanel(showDisplayPanel);
    }

    private void displayRemoveAll() {
        menuPanel.removeAll();
        contentPanel.removeAll();
        showDisplayPanel.removeAll();
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
            createUserCreation();

            calculateBasicCalorie();

            displayRemoveAll();

            SaveUserBody();

            initLifeStylePanel();

            updateAllDisplay();
        });
        return button;
    }

    private void createUserCreation() {
        user = new User(user.userGender(), Integer.parseInt(ageTextField.getText()),
                Integer.parseInt(heightTextField.getText()), Integer.parseInt(weightTextField.getText()));
    }

    private void calculateBasicCalorie() {
        if (user.gender().equals("남성")) {
            calorieCalculator.manBasicCalorie(user.weight(), user.height(), user.age());
        }
        if (user.gender().equals("여성")) {
            calorieCalculator.womanBasicCalorie(user.weight(), user.height(), user.age());
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
            displayRemoveAll();

            initInputBodyPanel();

            updateAllDisplay();
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
