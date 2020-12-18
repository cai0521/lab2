package bsu.rfe.java.group7.lab2.CAI.A7;

import javax.swing.*;  //библиотека для создания графического интерфейса для программ
import java.awt.*; //исходная платформо-независимая оконная библиотека графического интерфейса
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Math.exp;

public class MainFrame extends JFrame {

//размеры окна
private static final int WIDTH = 500;
private static final int HEIGHT = 320;
//текстовые поля для считывания значений переменных
private JTextField textFieldX;
private JTextField textFieldY;
private JTextField textFieldZ;
private JTextField textFieldResult;

private JButton MC = new JButton("MC");
//очищает значение внутренней суммы
private JButton MPlus = new JButton("M+");
//суммированию результата текущего вычисления с текущим содержимым переменной
private ButtonGroup myButtons = new ButtonGroup();  //группа кнопок
private Box hboxFormulaType = Box.createHorizontalBox(); //контейнер для кнопок
private int formulaId = 1;
private Double temp = 0.0; //для сохранения предыдущего результата
private Double sum = 0.0;

//формулы
public Double calculateFormula1(Double x, Double y, Double z){
return  Math.pow(Math.pow(Math.log(1+z),2) + Math.cos(Math.PI*Math.pow(y,3)),1./4)/
        Math.pow(Math.cos(Math.exp(x))+ Math.sqrt(1/x)+Math.exp(Math.pow(x,2)), Math.sin(x));
}
public Double calculateFormula2(Double x, Double y, Double z){
return Math.pow(Math.sin(Math.pow(z,y)),2)/Math.sqrt(1+Math.pow(x,3));
}
public void addSum(Double result){
sum = sum + result;
}


private void addRadioButton(String buttonName, final int formulaId) {
JRadioButton button = new JRadioButton(buttonName);
button.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent ev) {
        MainFrame.this.formulaId = formulaId;
    }
});
myButtons.add(button);
hboxFormulaType.add(button);
}

public MainFrame(){
super("Вычислитель");
setSize(WIDTH, HEIGHT);
Toolkit kit = Toolkit.getDefaultToolkit();

setLocation((kit.getScreenSize().width - WIDTH)/2,
        (kit.getScreenSize().height - HEIGHT)/2);

hboxFormulaType.add(Box.createHorizontalGlue());
addRadioButton("Формула 1", 1);
addRadioButton("Формула 2", 2);
myButtons.setSelected(myButtons.getElements().nextElement().getModel(), true);
hboxFormulaType.add(Box.createHorizontalGlue());
hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.RED));


JLabel labelForX = new JLabel("X:");
textFieldX = new JTextField("0", 10);
textFieldX.setMaximumSize(textFieldX.getPreferredSize());
JLabel labelForY = new JLabel("Y:");
textFieldY = new JTextField("0", 10);
textFieldY.setMaximumSize(textFieldY.getPreferredSize());
JLabel labelForZ = new JLabel("Z:");
textFieldZ = new JTextField("0", 10);
textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
Box hboxVariables = Box.createHorizontalBox();
hboxVariables.setBorder(
        BorderFactory.createLineBorder(Color.ORANGE));
hboxVariables.add(labelForX);
hboxVariables.add(Box.createHorizontalStrut(10));
hboxVariables.add(textFieldX);
hboxVariables.add(Box.createHorizontalGlue());
hboxVariables.add(Box.createHorizontalStrut(100));
hboxVariables.add(Box.createHorizontalGlue());
hboxVariables.add(labelForY);
hboxVariables.add(Box.createHorizontalStrut(10));
hboxVariables.add(textFieldY);
hboxVariables.add(Box.createHorizontalGlue());
hboxVariables.add(Box.createHorizontalStrut(100));
hboxVariables.add(Box.createHorizontalGlue());
hboxVariables.add(labelForZ);
hboxVariables.add(Box.createHorizontalStrut(10));
hboxVariables.add(textFieldZ);


JLabel labelForResult = new JLabel("Результат:");

textFieldResult = new JTextField("0", 13);
textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
Box hboxResult = Box.createHorizontalBox();
hboxResult.add(Box.createHorizontalGlue());
hboxResult.add(labelForResult);
hboxResult.add(Box.createHorizontalStrut(10));
hboxResult.add(textFieldResult);
hboxResult.add(Box.createHorizontalGlue());
hboxResult.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

JButton buttonCalc = new JButton("Вычислить");

buttonCalc.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent ve) {
        try {
            Double x = Double.parseDouble(textFieldX.getText());
            Double y = Double.parseDouble(textFieldY.getText());
            Double z = Double.parseDouble(textFieldZ.getText());
            Double result;
            if (formulaId==1)
                result = calculateFormula1(x, y, z);
            else
                result = calculateFormula2(x, y, z);
            temp = result;
            textFieldResult.setText(result.toString());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(MainFrame.this,
                    "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
});

JButton buttonReset = new JButton("Очистить поля");
buttonReset.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent ev) {
        textFieldX.setText("0");
        textFieldY.setText("0");
        textFieldZ.setText("0");
        textFieldResult.setText("0");
    }
});


MC.addActionListener(new ActionListener() {
   
    public void actionPerformed(ActionEvent e) {
        sum=0.0;
    }
});
MPlus.addActionListener(new ActionListener() {
   
    public void actionPerformed(ActionEvent e) {
        addSum(temp);
        textFieldResult.setText(Double.toString(sum));
    }
});


Box hboxButtons = Box.createHorizontalBox();
hboxButtons.add(buttonCalc);
hboxButtons.add(Box.createHorizontalStrut(30));
hboxButtons.add(buttonReset);
hboxButtons.add(Box.createHorizontalGlue());
hboxButtons.add(Box.createHorizontalStrut(30));
hboxButtons.add(MC);

hboxButtons.add(Box.createHorizontalStrut(30));
hboxButtons.add(MPlus);

hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));

Box contentBox = Box.createVerticalBox();
contentBox.add(Box.createVerticalGlue());
contentBox.add(hboxFormulaType);
contentBox.add(hboxVariables);
contentBox.add(hboxResult);
contentBox.add(hboxButtons);
contentBox.add(Box.createVerticalGlue());
getContentPane().add(contentBox, BorderLayout.CENTER);
}

public static void main(String[] args){
MainFrame frame = new MainFrame();
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setVisible(true);
}

}
