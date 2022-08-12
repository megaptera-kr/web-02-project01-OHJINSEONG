package application;

import models.*;
import panels.ButtonPanel;
import panels.PersonalImformationButtonPanel;
import utils.*;


import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class NSuns {
    private JFrame frame;
    private JPanel contentPanel;
    private User user;
    private Weeks weeks;
    private JPanel menuPanel;
    private List<LifeStyle> lifeStyles;
    private JPanel displayPanel;

    private TraningWeight1RM traningWeight1RM;
    private TraingWeightCalculator traingWeightCalculator;
    private SaveRepsList saveRepsList;
    private List<Reps> repss;
    private List<TraningWeight1RM> traningWeight1RMs;


    public static void main(String[] args) throws FileNotFoundException {
        NSuns application = new NSuns();

        application.run();
    }

    public NSuns() throws FileNotFoundException {
        UserBodyLoader userBodyLoader = new UserBodyLoader();
        TrainingWeightListLoader trainingWeightListLoader = new TrainingWeightListLoader();
        repsListLoader repsListLoader = new repsListLoader();
        weeksLoader weeksLoader = new weeksLoader();

        this.user = userBodyLoader.load();
        this.traningWeight1RMs = TrainingWeightListLoader.load();
        this.repss = repsListLoader.load();
        this.weeks = weeksLoader.load();
        this.traningWeight1RM = traningWeight1RMs.get(traningWeight1RMs.size() - 1);
    }

    private void run() {
        createLifeStyles();

        createCreations();

        initFrame();

        initMenuPanel();

        initContentPanel();

        initDisplayPanel();

        firstPage();

        displayFrame();
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

    private void createCreations() {
        saveRepsList = new SaveRepsList();
        repss = new ArrayList<>();
        traingWeightCalculator = new TraingWeightCalculator();
    }

    private void initFrame() {
        frame = new JFrame("nSuns");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);
    }

    private void initMenuPanel() {
        menuPanel = new JPanel();
        frame.add(menuPanel, BorderLayout.PAGE_START);
    }

    private void initContentPanel() {
        contentPanel = new JPanel();
        frame.add(contentPanel);
    }

    private void initDisplayPanel() {
        displayPanel = new JPanel();
        frame.add(displayPanel, BorderLayout.SOUTH);
    }

    public void firstPage() {
        menuPanel.add(programStartButton());

        menuPanel.add(personalImformationButton());

        menuPanel.add(trainingDiaryButton());

        JPanel panel = new JPanel();
        contentPanel.add(panel);
        panel.add(documentationButton());
    }

    private JButton documentationButton() {
        JButton button = new JButton("NSuns 설명서");
        button.addActionListener(e -> {
            displayRemoveAll();

            documentation();

            displayUpdateAll();
        });
        return button;
    }

    private void documentation() {
        menuPanel.add(goFisrtPagePanelButton());

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

        nSunsDocumentationPanel.add(new JLabel("--------------------- application.NSuns 프로그램이란 ---------------------"));
        nSunsDocumentationPanel.add(new JLabel("  application.NSuns 프로그램은 웬들러 5/3/1과 Sheiko의 프로그램을 합친 프로그램입니다."));
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

        displayPanel.setPreferredSize(new Dimension(200, 50));
    }

    private JButton trainingDiaryButton() {
        JButton button = new JButton("운동 일지");
        button.addActionListener(e -> {
            displayRemoveAll();

            trainingDiary();

            displayUpdateAll();
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

    public void trainingDiary() {
        menuPanel.add(goFisrtPagePanelButton());

        JPanel panel = new JPanel();

        contentPanel.setLayout(new FlowLayout());

        panel.setLayout(new GridLayout(weeks.week(), 2));

        contentPanel.add(panel);

        for (int i = 1; i < weeks.week(); i += 1) {
            panel.add(new JLabel(i + " 주차"));
            panel.add(trainingDiary(i));
        }
        displayPanel.setPreferredSize(new Dimension(200, 200));
    }

    private JButton trainingDiary(int week) {
        JButton button = new JButton("보기");
        button.addActionListener(e -> {
            displayRemoveAll();

            DiaryPanel(week);

            displayUpdateAll();
        });
        return button;
    }

    private void DiaryPanel(int week) {
        menuPanel.add(goTrainingDiary());

        JPanel panel1 = new JPanel();
        contentPanel.add(panel1);
        JPanel panel2 = new JPanel();
        contentPanel.add(panel2);

        contentPanel.setLayout(new FlowLayout());

        panel1.setLayout(new GridLayout(4, 1));
        panel2.setLayout(new GridLayout(4, 1));

        for (TraningWeight1RM traningWeight1RM : traningWeight1RMs) {
            if (traningWeight1RM.week() == week - 1) {
                panel1.add(new JLabel("벤치프레스 1RM      " + traningWeight1RM.benchPress() + "kg" + " -> "));
                panel1.add(new JLabel("스쿼트 1RM            " + traningWeight1RM.squt() + "kg" + " -> "));
                panel1.add(new JLabel("오버헤드프레스 1RM   " + traningWeight1RM.overHeadPress() + "kg" + " -> "));
                panel1.add(new JLabel("데드리프트 1RM      " + traningWeight1RM.deadLift() + "kg" + " -> "));
            }
            if (traningWeight1RM.week() == week) {
                panel2.add(new JLabel("" + traningWeight1RM.benchPress() + "kg"));
                panel2.add(new JLabel("" + traningWeight1RM.squt() + "kg"));
                panel2.add(new JLabel("" + traningWeight1RM.overHeadPress() + "kg"));
                panel2.add(new JLabel("" + traningWeight1RM.deadLift() + "kg"));
            }
        }
    }

    private JButton goTrainingDiary() {
        JButton button = new JButton("돌아가기");
        button.addActionListener(e -> {
            displayRemoveAll();

            trainingDiary();

            displayUpdateAll();
        });
        return button;
    }

    private JButton personalImformationButton() {
        JButton button = new JButton("개인 정보");
        button.addActionListener(e -> {
            displayRemoveAll();

            personalImformation();

            displayUpdateAll();
        });
        return button;
    }

    public void personalImformation() {
        PersonalImformationButtonPanel personalImformationButtonPanel = new PersonalImformationButtonPanel(this,
                menuPanel,
                contentPanel,
                displayPanel,
                user,
                traningWeight1RM,
                frame,
                lifeStyles,
                traningWeight1RMs);

        menuPanel.add(personalImformationButtonPanel);

        contentPanel.setLayout(new GridLayout(5, 1));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        displayPanel.add(panel);
        displayPanel.setPreferredSize(new Dimension(450, 300));
        panel.setPreferredSize(new Dimension(400, 200));
        displayPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 100, 50));
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

    private JButton programStartButton() {
        JButton button = new JButton("프로그램 시작");
        button.addActionListener(e -> {
            displayRemoveAll();

            selectProgramPanel();

            displayUpdateAll();

            weeks.initStartFrameIndex();
            if (weeks.startFrameIndex() == 0) {
                initStartFrame();
            }
            weeks.increaseStartFrameIndex();
        });
        return button;
    }

    public void selectProgramPanel() {
        menuPanel.add(goFisrtPagePanelButton());

        JPanel labelPanel = new JPanel();
        contentPanel.add(labelPanel);

        ButtonPanel buttonPanel = new ButtonPanel(this,
                weeks,
                displayPanel,
                menuPanel,
                contentPanel,
                traingWeightCalculator,
                traningWeight1RMs,
                traningWeight1RM,
                repss,
                saveRepsList,
                frame
                );

        contentPanel.add(buttonPanel);

        contentPanel.setLayout(new GridLayout(2, 1));

        labelPanel.add(new Label(weeks.week() + " 주차"));

        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 25));

        displayPanel.setPreferredSize(new Dimension(200, 450));
    }

    public JButton goFisrtPagePanelButton() {
        JButton button = new JButton("돌아가기");
        button.addActionListener(e -> {
            displayRemoveAll();

            removeCreateEmptyBorder();

            firstPage();

            displayUpdateAll();
        });
        return button;
    }

    private void removeCreateEmptyBorder() {
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        displayPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }

    public void displayUpdateAll() {
        updateDisplayPanel(menuPanel);
        updateDisplayPanel(contentPanel);
        updateDisplayPanel(displayPanel);
    }

    private void displayFrame() {
        frame.setVisible(true);
    }

    public void displayRemoveAll() {
        menuPanel.removeAll();
        contentPanel.removeAll();
        displayPanel.removeAll();
    }

    public void updateDisplayPanel(JPanel Panel) {
        Panel.setVisible(false);
        Panel.setVisible(true);
        displayFrame();
    }
}
