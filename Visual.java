package WC;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Visual extends JFrame{
	JTextArea jTextArea;
	File Path;
	int state;
	JButton button;
	Visual(final boolean[] b){
		setBounds(200,200, 600, 800);
		setVisible(true);
		setTitle("ѡ���ļ�");
		setLayout(new FlowLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel jPanel_t = new JPanel();
		JPanel jPanel_b = new JPanel();
		
		Container c = getContentPane();
		c.add(jPanel_t,BorderLayout.NORTH);
		c.add(jPanel_b,BorderLayout.NORTH);
		
		button = new JButton("��ѡ�ļ�");
		jPanel_b.add(button,BorderLayout.WEST);
		button.addActionListener(new ActionListener() {//��ȡ·��
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jf=new JFileChooser("d:\\");
				state=jf.showOpenDialog(Visual.this);
				Path=jf.getSelectedFile();
				if(Path!=null&&state==JFileChooser.APPROVE_OPTION){
					jTextArea.append(Path.toString()+"\n");
					CharCounter charCounter = new CharCounter(Path);
					LineCounter lineCounter = new LineCounter(Path);
					if(b[0]==true){
						jTextArea.append("�ַ���Ϊ��"+charCounter.getCharCount()+"\n");
					}
					if(b[1]==true){
						jTextArea.append("Ӣ����Ϊ��"+charCounter.getWordCounter()+"\n");
					}
					if(b[2]==true){
						jTextArea.append("����Ϊ��"+charCounter.getLineCounter()+"\n");
					}
					if(b[4]==true){
						jTextArea.append("ע������: "+lineCounter.getNoteLine()+"\n");
						jTextArea.append("������: "+lineCounter.getNullLine()+"\n");
						jTextArea.append("��������: "+lineCounter.getCodeLine()+"\n");
					}
				}
			}
		});
		
		jTextArea = new JTextArea(40,30);
		JScrollPane jsp = new JScrollPane(jTextArea);
		jPanel_t.add(jsp,BorderLayout.SOUTH);
		validate();
		
	}
	public static void main(String[] args) {
		
	}
}
