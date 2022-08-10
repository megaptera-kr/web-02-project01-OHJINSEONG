import models.*;
import utils.*;


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
    private Weeks weeks;
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
    private JTextField set1Rep;
    private JTextField set2Rep;
    private JTextField set3Rep;
    private JTextField set4Rep;
    private JTextField set5Rep;
    private JTextField set6Rep;
    private JTextField set7Rep;
    private JTextField set8Rep;
    private Reps reps;
    private SaveRepsList saveRepsList;
    private List<Reps> repss;
    private JTextField nameTextField;
    private List<TraningWeight1RM> traningWeight1RMs;


    public static void main(String[] args) throws FileNotFoundException {
        NSuns application = new NSuns();

        application.run();
    }

    public NSuns() throws FileNotFoundException {
        LoadUserBody loadUserBody = new LoadUserBody();
        LoadTrainingWeightList loadTrainingWeightList = new LoadTrainingWeightList();
        LoadRepsList loadRepsList = new LoadRepsList();

        this.user = LoadUserBody.Loader();
        this.traningWeight1RMs = LoadTrainingWeightList.Loader();
        this.traningWeight1RM = traningWeight1RMs.get(traningWeight1RMs.size() - 1);
        this.repss = LoadRepsList.Loader();
    }

    private void run() {
        createLifeStyles();

        weeks = new Weeks(1);

        saveRepsList = new SaveRepsList();
        repss = new ArrayList<>();
        reps = new Reps("", 0, 0, 0, 0, 0, 0, 0, 0);
        traingWeightCalculator = new TraingWeightCalculator();
        frame = new JFrame("nSuns");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);

        menuPanel = new JPanel();
        frame.add(menuPanel, BorderLayout.PAGE_START);

        initMenuPanel();

        initFirstPage();

        initContentPanel();

        initShowDisplayPanel();

        frame.setVisible(true);
    }

    private void initFirstPage() {
        menuPanel.add(programStartButton("프로그램 시작"));

        menuPanel.add(programSetting("프로그램 세팅"));

        menuPanel.add(personalImformation("개인 정보"));

        menuPanel.add(TrainingDiary("운동 일지"));
    }

    private JButton TrainingDiary(String 운동_일지) {
        JButton button = new JButton(운동_일지);
        button.addActionListener(e -> {
            displayRemoveAll();

            initTrainingDiary();

            updateAllDisplay();
        });
        return button;
    }

    private void initStartFrame() {
        JFrame startFrame = new JFrame("Start");
        startFrame.setLocationRelativeTo(null);
        startFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        startFrame.setSize(200, 100);

        traningWeight1RM = new TraningWeight1RM(traningWeight1RMs.get(traningWeight1RMs.size() - 1).benchPress(),
                traningWeight1RMs.get(traningWeight1RMs.size() - 1).squt(),
                traningWeight1RMs.get(traningWeight1RMs.size() - 1).overHeadPress(),
                traningWeight1RMs.get(traningWeight1RMs.size() - 1).deadLift(), weeks.week());

        startFrame.add(new JLabel(weeks.week() + "주차 훈련 시작!"));

        startFrame.setVisible(true);
    }

    private void initTrainingDiary() {
        menuPanel.add(goFisrtPagePanel("돌아가기"));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(weeks.week(), 2));
        contentPanel.add(panel);

        for (int i = 1; i < weeks.week(); i += 1) {
            panel.add(new JLabel(i + " 주차"));
            panel.add(TrainingDiary(i));
        }
    }

    private JButton TrainingDiary(int week) {
        JButton button = new JButton("보기");
        button.addActionListener(e -> {
            displayRemoveAll();

            initDiaryPanel(week);

            updateAllDisplay();
        });
        return button;
    }

    private void initDiaryPanel(int week) {
        menuPanel.add(goTrainingDiary("돌아가기"));

        JPanel panel1 = new JPanel();
        contentPanel.add(panel1);
        JPanel panel2 = new JPanel();
        contentPanel.add(panel2);

        contentPanel.setLayout(new FlowLayout());

        panel1.setLayout(new GridLayout(4, 1));
        panel2.setLayout(new GridLayout(4, 1));

        for (TraningWeight1RM traningWeight1RM : traningWeight1RMs) {
            if (traningWeight1RM.week() == week - 1) {
                panel1.add(new JLabel("벤치프레스 1RM      " + traningWeight1RM.benchPress() + " -> "));
                panel1.add(new JLabel("스쿼트 1RM         " + traningWeight1RM.squt() + " -> "));
                panel1.add(new JLabel("오버헤드프레스 1RM   " + traningWeight1RM.overHeadPress() + " -> "));
                panel1.add(new JLabel("데드리프트 1RM      " + traningWeight1RM.deadLift() + " -> "));
            }
            if (traningWeight1RM.week() == week) {
                panel2.add(new JLabel("" + traningWeight1RM.benchPress()));
                panel2.add(new JLabel("" + traningWeight1RM.squt()));
                panel2.add(new JLabel("" + traningWeight1RM.overHeadPress()));
                panel2.add(new JLabel("" + traningWeight1RM.deadLift()));
            }
        }
    }

    private JButton goTrainingDiary(String 돌아가기) {
        JButton button = new JButton(돌아가기);
        button.addActionListener(e -> {
            displayRemoveAll();

            initTrainingDiary();

            updateAllDisplay();
        });
        return button;
    }

    private JButton personalImformation(String 개인_정보) {
        JButton button = new JButton(개인_정보);
        button.addActionListener(e -> {
            displayRemoveAll();

            initPersonalImformation();

            updateAllDisplay();
        });
        return button;
    }

    private void initPersonalImformation() {
        menuPanel.add(goFisrtPagePanel("돌아가기"));
        menuPanel.add(setCalorieButton("수정"));

        contentPanel.setLayout(new GridLayout(4, 1));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        showDisplayPanel.setPreferredSize(new Dimension(200, 300));
        showDisplayPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 50));
        showDisplayPanel.setLayout(new GridLayout(4, 1));

        contentPanel.add(new JLabel("이름: " + user.name()));
        contentPanel.add(new JLabel("나이: " + user.age() + "세"));
        contentPanel.add(new JLabel("키: " + user.height() + "cm"));
        contentPanel.add(new JLabel("몸무게: " + user.weight() + "kg"));

        showDisplayPanel.add(new JLabel("벤치프레스 1RM: " +
                traningWeight1RMs.get(traningWeight1RMs.size() - 1).benchPress() + "kg"));
        showDisplayPanel.add(new JLabel("스쿼트 1RM: " +
                traningWeight1RMs.get(traningWeight1RMs.size() - 1).squt() + "kg"));
        showDisplayPanel.add(new JLabel("오버헤드프레스 1RM: " +
                traningWeight1RMs.get(traningWeight1RMs.size() - 1).overHeadPress() + "kg"));
        showDisplayPanel.add(new JLabel("데드리프트 1RM: " +
                traningWeight1RMs.get(traningWeight1RMs.size() - 1).deadLift() + "kg"));
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
            createTraningWeight1RMCreation();

            saveTrainingWeight();

            displayRemoveAll();

            initFirstPage();

            updateAllDisplay();
        });
        return button;
    }

    private void createTraningWeight1RMCreation() {
        traningWeight1RM = new TraningWeight1RM(Double.parseDouble(benchPress1RM.getText()),
                Double.parseDouble(squt1RM.getText()), Double.parseDouble(overHeadPress1RM.getText()),
                Double.parseDouble(deadLift1RM.getText()), 0);
    }

    private JButton programStartButton(String 프로그램_시작) {
        JButton button = new JButton(프로그램_시작);
        button.addActionListener(e -> {
            displayRemoveAll();

            initSelectProgramPanel();

            updateAllDisplay();

            weeks.initStartFrameIndex();
            if (weeks.startFrameIndex() == 0) {
                initStartFrame();
            }
            weeks.increaseStartFrameIndex();
        });
        return button;
    }

    private void initSelectProgramPanel() {
        menuPanel.add(goFisrtPagePanel("돌아가기"));

        JPanel labelPanel = new JPanel();
        contentPanel.add(labelPanel);
        JPanel buttonPanel = new JPanel();
        contentPanel.add(buttonPanel);

        contentPanel.setLayout(new GridLayout(2, 1));

        labelPanel.add(new Label(weeks.week() + " 주차"));

        buttonPanel.setLayout(new GridLayout(1, 4));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 25));
        buttonPanel.add(programButton("벤치프레스"));
        buttonPanel.add(programButton("스쿼트"));
        buttonPanel.add(programButton("오버헤드프레스"));
        buttonPanel.add(programButton("데드리프트"));

        showDisplayPanel.setPreferredSize(new Dimension(200, 450));
    }

    private JButton programButton(String workOut) {
        JButton button = new JButton(workOut);
        button.addActionListener(e -> {
            if (weeks.index(workOut) == 0) {
                displayRemoveAll();
                initProgramPanel(workOut);
            }

            if (weeks.index(workOut) == 1) {
                showDisplayPanel.removeAll();
                showDisplayPanel.add(new JLabel("수행완료한 운동입니다. 다른 운동을 골라주세요."));
            }
            updateAllDisplay();
        });
        return button;
    }

    private void initProgramPanel(String workOut) {
        menuPanel.add(goSelectProgramPanel("돌아가기"));
        menuPanel.add(programComplete(workOut));

        trainingWeightCalculate(workOut);

        InputRepsTextField();

        showDisplayPanel.setPreferredSize(new Dimension(200, 100));
    }

    private JButton programComplete(String workOut) {
        JButton button = new JButton("완료");
        button.addActionListener(e -> {
            createRepsCreation(workOut);

            increaseWeight(workOut);

            increaseIndex(workOut);

            saveReps();

            displayRemoveAll();

            initSelectProgramPanel();

            updateAllDisplay();

            if (weeks.increase()) {
                increaseWeek();
                saveTrainingWeight();
                initIndex();

                displayRemoveAll();

                initTrainingDiary();

                updateAllDisplay();

                initEndWeekFrame();
            }

            createAlertFrame(workOut);
        });
        return button;
    }

    private void initEndWeekFrame() {
        JFrame endWeekFrame = new JFrame("end");
        endWeekFrame.setLocationRelativeTo(null);
        endWeekFrame.setSize(300, 300);
        endWeekFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        endWeekFrame.add(new JLabel("고생하셨습니다. " + (weeks.week() - 1) + " 주차 종료"));

        endWeekFrame.setVisible(true);
    }

    private void initIndex() {
        weeks.initIndex();
    }

    private void increaseWeek() {
        weeks.increaseWeek();
    }

    private void increaseIndex(String workOut) {
        weeks.increaseIndex(workOut);
    }

    private void increaseWeight(String workOut) {
        traningWeight1RM.increaseWeight(workOut, reps.overReps());
    }

    private void createAlertFrame(String workOut) {
        JFrame alertFrame = new JFrame("Good job");
        alertFrame.setSize(300, 100);
        alertFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        alertFrame.setLocationRelativeTo(null);
        alertFrame.setVisible(true);

        alertFrame.add(new Label("축하합니다. " + workOut + " 중량이 " + reps.overReps() + "kg 증가하였습니다!"));
    }

    private void createRepsCreation(String workOut) {
        reps = new Reps(workOut, Integer.parseInt(set1Rep.getText()), Integer.parseInt(set2Rep.getText()),
                Integer.parseInt(set3Rep.getText()), Integer.parseInt(set4Rep.getText()), Integer.parseInt(set5Rep.getText()),
                Integer.parseInt(set6Rep.getText()), Integer.parseInt(set7Rep.getText()), Integer.parseInt(set8Rep.getText()));
    }

    private void trainingWeightCalculate(String workOut) {
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 0, 30));
        contentPanel.setLayout(new FlowLayout());

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(8, 1));
        labelPanel.setPreferredSize(new Dimension(250, 300));
        contentPanel.add(labelPanel, BorderLayout.WEST);

        for (int i = 0; i < 3; i += 1) {
            switch (workOut) {
                case "벤치프레스" -> labelPanel.add(new JLabel((i + 1) + ".Set " + workOut + " " +
                        traingWeightCalculator.calculate((0.75 + 0.1 * i) *
                                traningWeight1RMs.get(traningWeight1RMs.size() - 1).benchPress()) +
                        "kg(권장 " + (5 - 2 * i) + "회)"));
                case "스쿼트" -> labelPanel.add(new JLabel((i + 1) + ".Set " + workOut + " " +
                        traingWeightCalculator.calculate((0.75 + 0.1 * i) *
                                traningWeight1RMs.get(traningWeight1RMs.size() - 1).squt()) +
                        "kg(권장 " + (5 - 2 * i) + "회)"));
                case "오버헤드프레스" -> labelPanel.add(new JLabel((i + 1) + ".Set " + workOut + " " +
                        traingWeightCalculator.calculate((0.75 + 0.1 * i) *
                                traningWeight1RMs.get(traningWeight1RMs.size() - 1).overHeadPress()) +
                        "kg(권장 " + (5 - 2 * i) + "회)"));
                case "데드리프트" -> labelPanel.add(new JLabel((i + 1) + ".Set " + workOut + " " +
                        traingWeightCalculator.calculate((0.75 + 0.1 * i) *
                                traningWeight1RMs.get(traningWeight1RMs.size() - 1).deadLift()) +
                        "kg(권장 " + (5 - 2 * i) + "회)"));
            }
        }
        for (int i = 0; i < 5; i += 1) {
            switch (workOut) {
                case "벤치프레스" -> labelPanel.add(new JLabel((i + 4) + ".Set " + workOut + " " +
                        traingWeightCalculator.calculate((0.90 - 0.05 * i) *
                                traningWeight1RMs.get(traningWeight1RMs.size() - 1).benchPress()) +
                        "kg(권장 3회)"));
                case "스쿼트" -> labelPanel.add(new JLabel((i + 4) + ".Set " + workOut + " " +
                        traingWeightCalculator.calculate((0.90 - 0.05 * i) *
                                traningWeight1RMs.get(traningWeight1RMs.size() - 1).squt()) +
                        "kg(권장 3회)"));
                case "오버헤드프레스" -> labelPanel.add(new JLabel((i + 4) + ".Set " + workOut + " " +
                        traingWeightCalculator.calculate((0.90 - 0.05 * i) *
                                traningWeight1RMs.get(traningWeight1RMs.size() - 1).overHeadPress()) +
                        "kg(권장 3회)"));
                case "데드리프트" -> labelPanel.add(new JLabel((i + 4) + ".Set " + workOut + " " +
                        traingWeightCalculator.calculate((0.90 - 0.05 * i) *
                                traningWeight1RMs.get(traningWeight1RMs.size() - 1).deadLift()) +
                        "kg(권장 3회)"));
            }
        }
    }

    private void InputRepsTextField() {
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new GridLayout(8, 1));
        textFieldPanel.setPreferredSize(new Dimension(100, 300));
        contentPanel.add(textFieldPanel, BorderLayout.EAST);

        textFieldPanel.add(set1Rep = new JTextField(10));
        textFieldPanel.add(set2Rep = new JTextField(10));
        textFieldPanel.add(set3Rep = new JTextField(10));
        textFieldPanel.add(set4Rep = new JTextField(10));
        textFieldPanel.add(set5Rep = new JTextField(10));
        textFieldPanel.add(set6Rep = new JTextField(10));
        textFieldPanel.add(set7Rep = new JTextField(10));
        textFieldPanel.add(set8Rep = new JTextField(10));
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
            displayRemoveAll();

            initInputBodyPanel();

            updateAllDisplay();
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
        inputPanel.setLayout(new GridLayout(6, 2));
        inputPanel.setPreferredSize(new Dimension(400, 150));

        panel.add(goPersonalImpormationPanel("돌아가기"));

        panel.add(goSelectLifeStylePanelButton());

        inputPanel.add(new JLabel("신체 조건을 기입하여 주십시오."));
        inputPanel.add(new JLabel(""));

        inputPanel.add(new JLabel("이름 : "));
        inputPanel.add(nameTextField = new JTextField(10));

        inputPanel.add(new JLabel("나이(세) : "));
        inputPanel.add(ageTextField = new JTextField(10));

        inputPanel.add(new JLabel("키(cm) : "));
        inputPanel.add(heightTextField = new JTextField(10));

        inputPanel.add(new JLabel("몸무게(kg) : "));
        inputPanel.add(weightTextField = new JTextField(10));

        inputPanel.add(selectGenderButton("남성"));

        inputPanel.add(selectGenderButton("여성"));
    }

    private JButton goPersonalImpormationPanel(String 돌아가기) {
        JButton button = new JButton(돌아가기);
        button.addActionListener(e -> {
            displayRemoveAll();

            initPersonalImformation();

            updateAllDisplay();
        });
        return button;
    }

    private JButton goFisrtPagePanel(String 돌아가기) {
        JButton button = new JButton(돌아가기);
        button.addActionListener(e -> {
            displayRemoveAll();

            removeCreateEmptyBorder();

            initFirstPage();

            updateAllDisplay();
        });
        return button;
    }

    private void removeCreateEmptyBorder() {
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        showDisplayPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
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
        JButton button = new JButton("기초 대사량 보기");
        button.addActionListener(e -> {
            createUserCreation();

            calculateBasicCalorie();

            displayRemoveAll();

            saveUserBody();

            initLifeStylePanel();

            updateAllDisplay();
        });
        return button;
    }

    private void createUserCreation() {
        user = new User(user.userGender(), Integer.parseInt(ageTextField.getText()), Integer.parseInt(heightTextField.getText()),
                Integer.parseInt(weightTextField.getText()), nameTextField.getText());
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

    private void saveUserBody() {
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

    private void saveTrainingWeight() {
        traningWeight1RMs.add(traningWeight1RM);
        try {
            SaveTrainingWeightList saveTrainingWeightList = new SaveTrainingWeightList(traningWeight1RMs);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void saveReps() {
        repss.add(reps);
        try {
            saveRepsList.SaveReps(repss);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void updateDisplayPanel(JPanel Panel) {
        Panel.setVisible(false);
        Panel.setVisible(true);
        frame.setVisible(true);
    }
}
