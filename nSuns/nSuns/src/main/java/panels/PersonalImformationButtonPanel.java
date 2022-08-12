package panels;

import application.NSuns;
import models.CalorieCalculator;
import models.TraningWeight1RM;
import models.User;

import javax.swing.*;
import java.awt.*;

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

    public PersonalImformationButtonPanel(NSuns nSuns,
                                          JPanel menuPanel,
                                          JPanel contentPanel,
                                          JPanel displayPanel,
                                          JTextField benchPress1RM,
                                          JTextField squt1RM,
                                          JTextField overHeadPress1RM,
                                          JTextField deadLift1RM, User user,
                                          CalorieCalculator calorieCalculator,
                                          TraningWeight1RM traningWeight1RM){
        this.nSuns = nSuns;
        this.menuPanel = menuPanel;
        this.contentPanel = contentPanel;
        this.displayPanel = displayPanel;
        this.benchPress1RM = benchPress1RM;
        this.squt1RM = squt1RM;
        this.overHeadPress1RM = overHeadPress1RM;
        this.deadLift1RM = deadLift1RM;
        this.user = user;
        this.calorieCalculator = calorieCalculator;
        this.traningWeight1RM = traningWeight1RM;
        this.add(nSuns.goFisrtPagePanelButton());
        this.add(setCalorieButton());
        this.add(programSettingButton());
        this.add(goSelectLifeStylePanelButton());
    }

    private JButton setCalorieButton() {
        JButton button = new JButton("개인정보 수정");
        button.addActionListener(e -> {
            nSuns.displayRemoveAll();

            nSuns.inputBodyPanel();

            nSuns.displayUpdateAll();
        });
        return button;
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

            nSuns.lifeStylePanel();

            nSuns.displayUpdateAll();
        });
        return button;
    }

    private void programSettingPanel() {
        menuPanel.add(nSuns.goPersonalImpormationPanel());
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

            nSuns.saveTrainingWeight();

            nSuns.displayRemoveAll();

            nSuns.firstPage();

            nSuns.displayUpdateAll();
        });
        return button;
    }

    private void createTraningWeight1RMCreation() {
        traningWeight1RM = new TraningWeight1RM(Double.parseDouble(benchPress1RM.getText()),
                Double.parseDouble(squt1RM.getText()), Double.parseDouble(overHeadPress1RM.getText()),
                Double.parseDouble(deadLift1RM.getText()), 0);
    }
}
