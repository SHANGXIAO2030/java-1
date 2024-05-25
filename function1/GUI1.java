package function1;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

public class GUI1 extends JFrame implements ActionListener {
    JTextField messageText1;
    JTextField messageText2;
    JTextArea resultText;
    JButton calBtn;
    JButton closeBtn;
    //constrcuctor
    public GUI1(){
        super("武汉地铁模拟系统-返回所有的中转站");
        Container c = this.getContentPane();
        c.setLayout(new FlowLayout());
        JPanel centerPane1 = new JPanel(new FlowLayout());

        JLabel messageLabel1 = new JLabel("return all Transfer Stations");



        calBtn = new JButton("enter");
        closeBtn = new JButton("quit");
        resultText = new JTextArea("show all Transfer Stations:",15,30);

        Font f = new Font("微软雅黑", Font.PLAIN, 12);

        calBtn.setFont(f);
        closeBtn.setFont(f);

        centerPane1.add(messageLabel1);


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
            showResultDialog();
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

        try {
            SubwayMap subwayMap = new SubwayMap();
            try (BufferedReader br = new BufferedReader(new FileReader("D:/IDEA/subway.txt"))) {
                String line;
                String currentLine = null;
                while ((line = br.readLine()) != null) {
                    if (line.contains("线")) {
                        currentLine = line.split("号线站点间距")[0];
                        subwayMap.addLine(currentLine);
                    } else if (line.contains("---") || line.contains("—")) {
                        String separator = line.contains("---") ? "---" : "—";
                        String[] parts = line.split(separator);
                        String station1 = parts[0].trim();
                        String station2 = parts[1].split("\t")[0].trim();
                        double distance = Double.parseDouble(parts[1].split("\t")[1].trim());
                        subwayMap.addStation(currentLine, station1, distance);
                        subwayMap.addStation(currentLine, station2, distance);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Set<String> transferStations = subwayMap.getTransferStations();
            StringBuilder sb = new StringBuilder();
            sb.append("show the all Transfer Stations: ").append("\n");
            for (String station1 : transferStations) {
                sb.append(station1).append("\n"); // 假设SubwayStation类有一个getName()方法
            }
            resultText.setText(sb.toString());

        } catch (NumberFormatException e) {
            // 处理数字格式异常，例如显示一个错误消息
            resultText.setText("输入的距离不是有效的数字！");
        } catch (IllegalArgumentException e) {
            // 处理非法参数异常，例如显示一个错误消息
            resultText.setText(e.getMessage());
        }
    }
    public void shutDown(){
        System.exit(0);
    }
    public static void main(String []args){
        SwingUtilities.invokeLater(() -> new function1.GUI1());



    }
}

