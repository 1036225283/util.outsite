package utiltest;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;

public class TestSplitPanel {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(0, 0, 400, 400);
		frame.setVisible(true);
		
		JLabel label2=new JLabel("Label 2",JLabel.CENTER);                            
	     label2.setBackground(Color.pink);
	     label2.setOpaque(true);                           

	     JLabel label3=new JLabel("Label 3",JLabel.CENTER);                            
	     label3.setBackground(Color.yellow);
	     label3.setOpaque(true);     
	     
	     JSplitPane splitPane1=new JSplitPane(JSplitPane.VERTICAL_SPLIT,false,label3,label2);
	     
//	     splitPane1.setDividerLocation(0.3);

	     splitPane1.setResizeWeight(0.2);
	          /*设置JSplitPane是否可以展开或收起(如同文件总管一般)，设为true表示打开此功能。
	           */
	          splitPane1.setOneTouchExpandable(true);
	          splitPane1.setDividerSize(10);//设置分隔线宽度的大小，以pixel为计算单位。
	     frame.add(splitPane1);
	}
}
