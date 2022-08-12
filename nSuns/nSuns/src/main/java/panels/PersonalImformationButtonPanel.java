package panels;

import application.NSuns;
import models.CalorieCalculator;
import models.LifeStyle;
import models.TraningWeight1RM;
import models.User;
import utils.SaveTrainingWeightList;
import utils.SaveUserBody;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

public class PersonalImformationButtonPanel extends JPanel {
    private NSuns nSuns;
    private JPanel menuPanel;
    private JPanel contentPanel;
    private JPanel displayPanel;
    private JTextField benchPress1RM;
    private JTextField squt1RM;
    private JTextField overHeadPress1RM;
    private JTextField deadLift1RM;
    private User user;
    private CalorieCalculator calorieCalculator;
    private TraningWeight1RM traningWeight1RM;
    private JTextField nameTextField;
    private JTextField ageTextField;
    private JTextField heightTextField;
    private JTextField weightTextField;
    private JFrame frame;
    private List<LifeStyle> lifeStyles;
    private List<TraningWeight1RM> traningWeight1RMs;

    public PersonalImformationButtonPanel(NSuns nSuns,
                                          JPanel menuPanel,
                                          JPanel contentPanel,
                                          JPanel displayPanel,
                                          User user,
                                          TraningWeight1RM traningWeight1RM,
                                          JFrame frame,
                                          List<LifeStyle> lifeStyles,
                                          List<TraningWeight1RM> traningWeight1RMs){
        this.nSuns = nSuns;
        this.menuPanel = menuPanel;
        this.contentPanel = contentPanel;
        this.displayPanel = displayPanel;
        this.user = user;
        this.traningWeight1RM = traningWeight1RM;
        this.frame = frame;
        this.lifeStyles = lifeStyles;
        this.traningWeight1RMs = traningWeight1RMs;
        this.add(nSuns.goFisrtPagePanelButton());
        this.add(setCalorieButton());
        this.add(programSettingButton());
        this.add(goSelectLifeStylePanelButton());
    }

    private JButton setCalorieButton() {
        JButton button = new JButton("개인정보 수정");
        button.addActionListener(e -> {
            nSuns.displayRemoveAll();

            inputBodyPanel();

            nSuns.displayUpdateAll();
        });
        return button;
    }

    public void inputBodyPanel() {
        calorieCalculator = new CalorieCalculator();

        contentPanel.setPreferredSize(new Dimension(200, 100));
        displayPanel.setPreferredSize(new Dimension(200, 500));

        JPanel panel = new JPanel();
        menuPanel.add(panel);

        JPanel inputPanel = new JPanel();
        displayPanel.add(inputPanel);

        panel.setLayout(new GridLayout(1, 2));
        inputPanel.setLayout(new GridLayout(6, 2));
        inputPanel.setPreferredSize(new Dimension(400, 150));

        panel.add(goPersonalImpormationPanel());

        panel.add(saveButton());

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

    private JButton selectGenderButton(String gender) {
        JButton button = new JButton(gender);
        button.addActionListener(e -> {
            user.updateGender(gender);
        });
        return button;
    }

    public JButton goPersonalImpormationPanel() {
        JButton button = new JButton("돌아가기");
        button.addActionListener(e -> {
            nSuns.displayRemoveAll();

            nSuns.personalImformation();

            nSuns.displayUpdateAll();
        });
        return button;
    }

    private JButton saveButton() {
        JButton button = new JButton("완료");
        button.addActionListener(e -> {
            createUserCreation();

            saveUserBody();

            lifeStylePanel();

            nSuns.displayRemoveAll();

            nSuns.personalImformation();

            nSuns.displayUpdateAll();
        });
        return button;
    }

    private void createUserCreation() {
        user = new User(user.userGender(), Integer.parseInt(ageTextField.getText()), Integer.parseInt(heightTextField.getText()),
                Integer.parseInt(weightTextField.getText()), nameTextField.getText());
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

    private JButton programSettingButton() {
        JButton button = new JButton("1RM 설정");
        button.addActionListener(e -> {
            nSuns.displayRemoveAll();

            programSettingPanel();

            nSuns.displayUpdateAll();
        });
        return button;
    }

    private JButton goSelectLifeStylePanelButton() {
        JButton button = new JButton("기초 대사량 보기");
        button.addActionListener(e -> {
            calculateBasicCalorie();

            nSuns.displayRemoveAll();

            lifeStylePanel();

            nSuns.displayUpdateAll();
        });
        return button;
    }

    public void lifeStylePanel() {
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 50, 100));
        contentPanel.setPreferredSize(new Dimension(200, 200));
        displayPanel.setPreferredSize(new Dimension(200, 300));

        menuPanel.add(goInputBodyPanel());

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

    private JButton createLifeStyleButton(LifeStyle lifeStyle) {
        JButton button = new JButton(lifeStyle.style());
        button.addActionListener(e -> {
            displayPanel.removeAll();
            displayPanel.setLayout(new GridLayout(2, 1));

            calorieCalculator.activityCalorieCalculate(lifeStyle.activityIntensity());

            displayPanel.add(new JLabel(lifeStyle.toString()));
            displayPanel.add(new JLabel("섭취해야할 칼로리: " + calorieCalculator.activityCalorie()));

            nSuns.updateDisplayPanel(displayPanel);
        });
        return button;
    }

    private JButton goInputBodyPanel() {
        JButton button = new JButton("돌아가기");
        button.addActionListener(e -> {
            nSuns.displayRemoveAll();

            inputBodyPanel();

            nSuns.displayUpdateAll();
        });
        return button;
    }

    private void programSettingPanel() {
        menuPanel.add(goPersonalImpormationPanel());
        menuPanel.add(programSettingCompleteButton());

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

        displayPanel.setPreferredSize(new Dimension(200, 400));
    }

    private void calculateBasicCalorie() {
        if (user.gender().equals("남성")) {
            calorieCalculator.manBasicCalorie(user.weight(), user.height(), user.age());
        }
        if (user.gender().equals("여성")) {
            calorieCalculator.womanBasicCalorie(user.weight(), user.height(), user.age());
        }
    }

    private JButton programSettingCompleteButton() {
        JButton button = new JButton("완료");
        button.addActionListener(e -> {
            createTraningWeight1RMCreation();

            saveTrainingWeight();

            nSuns.displayRemoveAll();

            nSuns.firstPage();

            nSuns.displayUpdateAll();
        });
        return button;
    }

    public void saveTrainingWeight() {
        traningWeight1RMs.add(traningWeight1RM);
        try {
            SaveTrainingWeightList saveTrainingWeightList = new SaveTrainingWeightList(traningWeight1RMs);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void createTraningWeight1RMCreation() {
        traningWeight1RM = new TraningWeight1RM(Double.parseDouble(benchPress1RM.getText()),
                Double.parseDouble(squt1RM.getText()), Double.parseDouble(overHeadPress1RM.getText()),
                Double.parseDouble(deadLift1RM.getText()), 0);
    }
}
