package panels;

import application.NSuns;
import models.Reps;
import models.TraingWeightCalculator;
import models.TraningWeight1RM;
import models.Weeks;
import utils.SaveRepsList;
import utils.SaveTrainingWeightList;
import utils.SaveWeeks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

public class ButtonPanel extends JPanel {
    private NSuns nSuns;
    private Weeks weeks;
    private JPanel displayPanel;
    private JPanel menuPanel;
    private JPanel contentPanel;
    private TraingWeightCalculator traingWeightCalculator;
    private List<TraningWeight1RM> traningWeight1RMs;
    private JTextField set1Rep;
    private JTextField set2Rep;
    private JTextField set3Rep;
    private JTextField set4Rep;
    private JTextField set5Rep;
    private JTextField set6Rep;
    private JTextField set7Rep;
    private JTextField set8Rep;
    private TraningWeight1RM traningWeight1RM;
    private List<Reps> repss;
    private SaveRepsList saveRepsList;
    private JFrame frame;
    private Reps reps;


    public ButtonPanel(NSuns nSuns,
                       Weeks weeks,
                       JPanel displayPanel,
                       JPanel menuPanel,
                       JPanel contentPanel,
                       TraingWeightCalculator traingWeightCalculator,
                       List<TraningWeight1RM> traningWeight1RMs,
                       TraningWeight1RM traningWeight1RM,
                       List<Reps> repss,
                       SaveRepsList saveRepsList,
                       JFrame frame) {
        this.nSuns = nSuns;
        this.weeks = weeks;
        this.displayPanel = displayPanel;
        this.menuPanel = menuPanel;
        this.contentPanel = contentPanel;
        this.traingWeightCalculator = traingWeightCalculator;
        this.traningWeight1RMs = traningWeight1RMs;
        this.traningWeight1RM = traningWeight1RM;
        this.repss = repss;
        this.saveRepsList = saveRepsList;
        this.frame = frame;

        this.setLayout(new GridLayout(1, 4));
        this.add(programButton("벤치프레스"));
        this.add(programButton("스쿼트"));
        this.add(programButton("오버헤드프레스"));
        this.add(programButton("데드리프트"));
    }

    private JButton programButton(String workOut) {
        JButton button = new JButton(workOut);
        button.addActionListener(e -> {
            if (weeks.index(workOut) == 0) {
                nSuns.displayRemoveAll();
                programPanel(workOut);
            }

            if (weeks.index(workOut) == 1) {
                displayPanel.removeAll();
                displayPanel.add(new JLabel("수행완료한 운동입니다. 다른 운동을 골라주세요."));
            }
            nSuns.displayUpdateAll();
        });
        return button;
    }

    private void programPanel(String workOut) {
        menuPanel.add(goSelectProgramPanel());

        menuPanel.add(programComplete(workOut));

        trainingWeightCalculate(workOut);

        InputRepsTextField();

        displayPanel.setPreferredSize(new Dimension(200, 100));
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



    public JButton goSelectProgramPanel() {
        JButton button = new JButton("돌아가기");
        button.addActionListener(e -> {
            nSuns.displayRemoveAll();

            nSuns.selectProgramPanel();

            nSuns.displayUpdateAll();
        });
        return button;
    }

    public JButton programComplete(String workOut) {
        JButton button = new JButton("완료");
        button.addActionListener(e -> {
            createRepsCreation(workOut);

            increaseWeight(workOut);

            increaseIndex(workOut);

            saveReps();

            nSuns.displayRemoveAll();

            nSuns.selectProgramPanel();

            nSuns.displayUpdateAll();

            if (weeks.increase()) {
                increaseWeek();

                saveTrainingWeight();

                initIndex();

                saveWeeks();

                nSuns.displayRemoveAll();

                nSuns.trainingDiary();

                nSuns.displayUpdateAll();

                initEndWeekFrame();
            }

            createAlertFrame(workOut);
        });
        return button;
    }

    private void createRepsCreation(String workOut) {
        reps = new Reps(workOut, Integer.parseInt(set1Rep.getText()), Integer.parseInt(set2Rep.getText()),
                Integer.parseInt(set3Rep.getText()), Integer.parseInt(set4Rep.getText()), Integer.parseInt(set5Rep.getText()),
                Integer.parseInt(set6Rep.getText()), Integer.parseInt(set7Rep.getText()), Integer.parseInt(set8Rep.getText()));
    }

    private void increaseWeight(String workOut) {
        traningWeight1RM.increaseWeight(workOut, reps.overReps());
    }

    private void increaseIndex(String workOut) {
        weeks.increaseIndex(workOut);
    }


    private void saveReps() {
        repss.add(reps);
        try {
            saveRepsList.SaveReps(repss);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    private void increaseWeek() {
        weeks.increaseWeek();
    }

    public void saveTrainingWeight() {
        traningWeight1RMs.add(traningWeight1RM);
        try {
            SaveTrainingWeightList saveTrainingWeightList = new SaveTrainingWeightList(traningWeight1RMs);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void initIndex() {
        weeks.initIndex();
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

    private void initEndWeekFrame() {
        JFrame endWeekFrame = new JFrame("end");
        endWeekFrame.setLocationRelativeTo(null);
        endWeekFrame.setSize(300, 300);
        endWeekFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        endWeekFrame.add(new JLabel("고생하셨습니다. " + (weeks.week() - 1) + " 주차 종료"));

        endWeekFrame.setVisible(true);
    }

    private void createAlertFrame(String workOut) {
        JFrame alertFrame = new JFrame("Good job");
        alertFrame.setSize(300, 100);
        alertFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        alertFrame.setLocationRelativeTo(null);
        alertFrame.setVisible(true);

        alertFrame.add(new Label("축하합니다. " + workOut + " 중량이 " + reps.overReps() + "kg 증가하였습니다!"));
    }
}
