package function3;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;
public class GUI3 extends JFrame implements ActionListener {
    JTextField messageText1;
    JTextField messageText2;
    JTextArea resultText;
    JButton calBtn;
    JButton closeBtn;
    //constrcuctor
    public GUI3(){
        super("武汉地铁模拟系统-起点和终点站之间的各条路径");
        Container c = this.getContentPane();
        c.setLayout(new FlowLayout());
        JPanel centerPane1 = new JPanel(new FlowLayout());

        JLabel messageLabel1 = new JLabel("Enter startstation and endstation:");
        messageText1 = new JTextField(5);

        messageText2 = new JTextField(5);


        calBtn = new JButton("enter");
        closeBtn = new JButton("quit");
        resultText = new JTextArea("show the paths:",15,30);

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
        String startStationName = messageText1.getText();
        String endStationName = messageText2.getText();
        SubwayMap subwayGraph = new SubwayMap();
        try {

            subwayGraph.loadFromFile("D:/IDEA/subway.txt");
            SubwayStation start = new SubwayStation(startStationName);
            SubwayStation end = new SubwayStation(endStationName);
            List<List<SubwayStation>> paths = subwayGraph.findAllPaths(start, end);

            StringBuilder sb = new StringBuilder();
            sb.append("show the paths:\n");
            for (int i = 0; i < paths.size(); i++) {
                sb.append("Path ").append(i + 1).append(":\n");
                for (SubwayStation station : paths.get(i)) {
                    sb.append(station.getName()).append(" -> "); // 假设Station类有一个getName方法
                }
                sb.append("\n");
            }
            if (paths.isEmpty()) {
                sb.append("No paths found between the given stations.\n");
            }
            resultText.setText(sb.toString());

        } catch (IOException e) {
            // 处理文件加载错误，并显示给用户一个友好的错误消息
            resultText.setText("Error loading subway graph: " + e.getMessage());
        } catch (Exception e) {
            // 处理其他可能的异常，如找不到站点等
            resultText.setText("An error occurred: " + e.getMessage());
        }
    }
    public void shutDown(){
        System.exit(0);
    }
    public static void main(String []args){
        SwingUtilities.invokeLater(() -> new function3.GUI3());



    }
}
