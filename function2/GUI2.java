package function2;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;

public class GUI2 extends JFrame implements ActionListener {
    JTextField messageText1;
    JTextField messageText2;
    JTextArea resultText;
    JButton calBtn;
    JButton closeBtn;
    //constrcuctor
    public GUI2(){
    super("武汉地铁模拟系统-距离范围内的站点集合");
    Container c = this.getContentPane();
    c.setLayout(new FlowLayout());
    JPanel centerPane1 = new JPanel(new FlowLayout());

    JLabel messageLabel1 = new JLabel("Enter startstation and maxDistance:");
    messageText1 = new JTextField(5);

    messageText2 = new JTextField(5);


    calBtn = new JButton("enter");
    closeBtn = new JButton("quit");
    resultText = new JTextArea("show the stations:",15,30);

    Font f = new Font("微软雅黑", Font.PLAIN, 12);
    messageText1.setFont(f);
    calBtn.setFont(f);
    closeBtn.setFont(f);

    centerPane1.add(messageLabel1);
    centerPane1.add(messageText1);
    centerPane1.add(messageText2);
    centerPane1.add(calBtn);
    centerPane1.add(closeBtn);
    c.add(centerPane1);
    c.add(resultText);

    calBtn.addActionListener(this);
    closeBtn.addActionListener(this);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(500,400);
    this.setVisible(true);
    //end of constructor
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == calBtn){
            calculte();

        }
        else{
            if(e.getSource() == closeBtn){
                shutDown();
            }
        }

    }
    public void showResultDialog() {
        JDialog d3 = new JDialog();
        // 设置对话框的大小、可见性等属性
        d3.add(resultText);
        d3.pack(); // 自动调整对话框大小以适应其内容
        d3.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        d3.setLocationRelativeTo(null); // 居中显示
        d3.setVisible(true); // 显示对话框
    }
    public void calculte() {
        String station = messageText1.getText();
        String d = messageText2.getText();

        try {
            double distance = Double.parseDouble(d);
            if (distance <= 0) {
                throw new IllegalArgumentException("距离必须是正数");
            }
            SubwayMap map = new SubwayMap();
            map.loadFromFile("D:/IDEA/subway.txt");

            List<SubwayStation> result = map.getStationsWithinDistance(station, distance);
            StringBuilder sb = new StringBuilder();
            sb.append("show the lines: ").append(station).append("，距离为").append(distance).append("的站点为：\n");
            for (SubwayStation station1 : result) {
                sb.append(station1).append("\n"); // 假设SubwayStation类有一个getName()方法
            }
            resultText.setText(sb.toString());

        } catch (NumberFormatException e) {
            // 处理数字格式异常，例如显示一个错误消息
            resultText.setText("输入的距离不是有效的数字！");
        } catch (IllegalArgumentException e) {
            // 处理非法参数异常，例如显示一个错误消息
            resultText.setText(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void shutDown(){
        System.exit(0);
    }
        public static void main(String []args){
        SwingUtilities.invokeLater(() -> new GUI2());



        }
    }
