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
    private SaveWeeks saveWeeks;


    public static void main(String[] args) throws FileNotFoundException {
        NSuns application = new NSuns();

        application.run();
    }

    public NSuns() throws FileNotFoundException {
        LoadUserBody loadUserBody = new LoadUserBody();
        LoadTrainingWeightList loadTrainingWeightList = new LoadTrainingWeightList();
        LoadRepsList loadRepsList = new LoadRepsList();
        LoadWeeks loadWeeks = new LoadWeeks();

        this.user = LoadUserBody.Loader();
        this.traningWeight1RMs = LoadTrainingWeightList.Loader();
        this.traningWeight1RM = traningWeight1RMs.get(traningWeight1RMs.size() - 1);
        this.repss = LoadRepsList.Loader();
        this.weeks = LoadWeeks.Loader();
    }

    private void run() {
        createLifeStyles();

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

        initContentPanel();

        initFirstPage();

        initShowDisplayPanel();

        frame.setVisible(true);
    }

    private void initFirstPage() {
        menuPanel.add(programStartButton("프로그램 시작"));

        menuPanel.add(programSetting("프로그램 세팅"));

        menuPanel.add(personalImformation("개인 정보"));

        menuPanel.add(TrainingDiary("운동 일지"));

        JPanel panel = new JPanel();
        contentPanel.add(panel);
        panel.add(documentation());
    }

    private JButton documentation() {
        JButton button = new JButton("NSuns 설명서");
        button.addActionListener(e -> {
            displayRemoveAll();

            initDocumentation();

            updateAllDisplay();
        });
        return button;
    }

    private void initDocumentation() {
        menuPanel.add(goFisrtPagePanel("돌아가기"));

        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        contentPanel.setLayout(new GridLayout(5, 1));

        JPanel nSunsDocumentationPanel = new JPanel();
        nSunsDocumentationPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPanel.add(nSunsDocumentationPanel);

        JPanel emptyPanel1 = new JPanel();
        contentPanel.add(emptyPanel1);

        JPanel manualPanel = new JPanel();
        manualPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPanel.add(manualPanel);

        JPanel emptyPanel2 = new JPanel();
        contentPanel.add(emptyPanel2);

        JPanel howToUsePanel = new JPanel();
        howToUsePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPanel.add(howToUsePanel);

        nSunsDocumentationPanel.setLayout(new GridLayout(5, 1));
        manualPanel.setLayout(new GridLayout(3, 1));
        howToUsePanel.setLayout(new GridLayout(4, 1));

        nSunsDocumentationPanel.add(new JLabel("--------------------- NSuns 프로그램이란 ---------------------"));
        nSunsDocumentationPanel.add(new JLabel("  NSuns 프로그램은 웬들러 5/3/1과 Sheiko의 프로그램을 합친 프로그램입니다."));
        nSunsDocumentationPanel.add(new JLabel("이 프로그램은 8세트로 진행되며 첫 3세트는 스트렝스 훈련인 웬들러 5/3/1 "));
        nSunsDocumentationPanel.add(new JLabel("그리고 4~8세트 근비대 훈련인 Sheiko가 섞여있어 스트렝스증가와 근비대를 동시에"));
        nSunsDocumentationPanel.add(new JLabel("할수있습니다."));
        manualPanel.add(new JLabel("------------------------- 메뉴얼 -------------------------"));
        manualPanel.add(new JLabel("  이 프로그램을 시작하기 전에 개인정보 기입과 프로그램 세팅에서 훈련중량을 기입해 "));
        manualPanel.add(new JLabel("주세요. 정보 기입후 프로그램 시작을 누르시면 프로그램을 시작 할수 있습니다. "));
        howToUsePanel.add(new JLabel("--------------------- 프로그램 사용방법 ---------------------"));
        howToUsePanel.add(new JLabel("  하루에 원하는 운동 종목을 하나씩 수행하시면 되고 총 4종목 임으로 한 주에 총 4번 운동"));
        howToUsePanel.add(new JLabel("하시면 됩니다. 각 운동의 3세트의 수행횟수에 따라 무게가 증가됩니다. 3세트 수행횟수가"));
        howToUsePanel.add(new JLabel("2회 이상 2.5kg 5회 이상 5kg 8회 이상 7.5kg 11회 이상 10kg이 증가됩니다."));

        showDisplayPanel.setPreferredSize(new Dimension(200,50));
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

        createTraingWeightCreation();

        startFrame.add(new JLabel(weeks.week() + "주차 훈련 시작!"));

        startFrame.setVisible(true);
    }

    private void createTraingWeightCreation() {
        traningWeight1RM = new TraningWeight1RM(traningWeight1RMs.get(traningWeight1RMs.size() - 1).benchPress(),
                traningWeight1RMs.get(traningWeight1RMs.size() - 1).squt(),
                traningWeight1RMs.get(traningWeight1RMs.size() - 1).overHeadPress(),
                traningWeight1RMs.get(traningWeight1RMs.size() - 1).deadLift(), weeks.week());
    }

    private void initTrainingDiary() {
        menuPanel.add(goFisrtPagePanel("돌아가기"));

        JPanel labelPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        contentPanel.setLayout(new FlowLayout());

        labelPanel.setLayout(new GridLayout(weeks.week(), 1));
        buttonPanel.setLayout(new GridLayout(weeks.week(), 1));

        contentPanel.add(labelPanel);
        contentPanel.add(buttonPanel);


        for (int i = 1; i < weeks.week(); i += 1) {
            labelPanel.add(new JLabel(i + " 주차"));
            buttonPanel.add(TrainingDiary(i));
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

        contentPanel.setLayout(new GridLayout(5, 1));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        showDisplayPanel.add(panel);
        showDisplayPanel.setPreferredSize(new Dimension(200, 300));
        panel.setPreferredSize(new Dimension(400,200));
        showDisplayPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 100, 50));
        panel.setLayout(new GridLayout(5, 1));

        contentPanel.add(new JLabel("개인정보"));
        contentPanel.add(new JLabel("이름: " + user.name()));
        contentPanel.add(new JLabel("나이: " + user.age() + "세"));
        contentPanel.add(new JLabel("키: " + user.height() + "cm"));
        contentPanel.add(new JLabel("몸무게: " + user.weight() + "kg"));


        panel.add(new JLabel("나의 1RM"));
        panel.add(new JLabel("벤치프레스 1RM: " +
                traningWeight1RMs.get(traningWeight1RMs.size() - 1).benchPress() + "kg"));
        panel.add(new JLabel("스쿼트 1RM: " +
                traningWeight1RMs.get(traningWeight1RMs.size() - 1).squt() + "kg"));
        panel.add(new JLabel("오버헤드프레스 1RM: " +
                traningWeight1RMs.get(traningWeight1RMs.size() - 1).overHeadPress() + "kg"));
        panel.add(new JLabel("데드리프트 1RM: " +
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
        contentPanel.setLayout(new GridLayout(5, 2));

        contentPanel.add(new JLabel("-1RM을 기입해주시오."));
        contentPanel.add(new JLabel(""));

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

                saveWeeks();

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
        labelPanel.setLayout(new GridLayout(9, 1));
        labelPanel.setPreferredSize(new Dimension(250, 300));
        contentPanel.add(labelPanel, BorderLayout.WEST);

        labelPanel.add(new JLabel("-3세트와 8세트는 가능한 많이 수행해주십시오."));
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
        textFieldPanel.setLayout(new GridLayout(9, 2));
        textFieldPanel.setPreferredSize(new Dimension(100, 300));
        contentPanel.add(textFieldPanel, BorderLayout.EAST);

        textFieldPanel.add(new JLabel(""));
        textFieldPanel.add(new JLabel(""));
        textFieldPanel.add(set1Rep = new JTextField(10));
        textFieldPanel.add(new JLabel("회"));
        textFieldPanel.add(set2Rep = new JTextField(10));
        textFieldPanel.add(new JLabel("회"));
        textFieldPanel.add(set3Rep = new JTextField(10));
        textFieldPanel.add(new JLabel("회"));
        textFieldPanel.add(set4Rep = new JTextField(10));
        textFieldPanel.add(new JLabel("회"));
        textFieldPanel.add(set5Rep = new JTextField(10));
        textFieldPanel.add(new JLabel("회"));
        textFieldPanel.add(set6Rep = new JTextField(10));
        textFieldPanel.add(new JLabel("회"));
        textFieldPanel.add(set7Rep = new JTextField(10));
        textFieldPanel.add(new JLabel("회"));
        textFieldPanel.add(set8Rep = new JTextField(10));
        textFieldPanel.add(new JLabel("회"));
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

    private void saveWeeks() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    SaveWeeks saveWeeks = new SaveWeeks(weeks);
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
