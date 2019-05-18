import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;


class myMenuBar extends JMenuBar implements ActionListener{
	MyFrame okno;
	JMenu mnuTlo, mnuCzcionka;
	JMenuItem tlo1, tlo2, czcionka1, czcionka2;
	public myMenuBar(MyFrame o){
		okno=o;
		mnuTlo = new JMenu ("T³o");
		mnuCzcionka = new JMenu ("Czcionka");
		tlo1 = new JMenuItem ("Pink");
		tlo2 = new JMenuItem ("Light Gray");
		czcionka1 = new JMenuItem ("czcionka1");
		czcionka2 = new JMenuItem ("czcionka2");
	
	
		add(mnuTlo);
		add(mnuCzcionka);
		mnuTlo.add(tlo1);tlo1.addActionListener( this );
		mnuTlo.add(tlo2);tlo2.addActionListener( this );
		mnuCzcionka.add(czcionka1);czcionka1.addActionListener( this );
		mnuCzcionka.add(czcionka2);czcionka2.addActionListener( this );
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	
		if (e.getActionCommand().equals("Pink")){
			okno.content.setBackground(Color.PINK);
			okno.lbBt.setBackground(Color.PINK);
			okno.lbEq.setBackground(Color.PINK);
		}
		if (e.getActionCommand().equals("Light Gray")){
			okno.content.setBackground(Color.LIGHT_GRAY);
			okno.lbBt.setBackground(Color.LIGHT_GRAY);
			okno.lbEq.setBackground(Color.LIGHT_GRAY);
		}
	
		if (e.getActionCommand().equals("czcionka1")){
			Font f2 = new Font("Comic Sans MS", Font.PLAIN, 20);
			okno.tf.setFont(f2);	
		}
		if (e.getActionCommand().equals("czcionka2")){
			Font f2 = new Font("SansSerif", Font.BOLD, 20);
			okno.tf.setFont(f2);	
		}
	}
	
}
public class MyFrame extends JFrame implements ActionListener{
	String[] btnLabel={"7","8","9","*","4","5","6","/","1","2","3","+","0",".","C","-","=","+/-"};
	JButton[] btnTab=new JButton[18];
	String sNumber="";
	int indNumber=0;
	int indOperation=0;
	double[] tabNumber=new double[3];
	myMenuBar mnuBar;
	

	JTextField tf;
	JPanel lbBt, lbEq, lbTf, content;
	JLabel lbl;
	MyFrame(){
		super( "Kalkulator" );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(340, 470);
		setLocation(50,50);
		setResizable(false);
		tf= new JTextField();
		tf.setEditable(false);
		tf.setBackground(Color.WHITE);
		Font f1 = new Font("SansSerif", Font.BOLD, 20);
		tf.setFont(f1);
		tf.setHorizontalAlignment(JTextField.RIGHT);
		tf.setText("0");
		for (int i=0;i<18;i++){
			btnTab[i]=new JButton(btnLabel[i]);
			btnTab[i].addActionListener(this);
		}
		lbTf=new JPanel();
		lbBt = new JPanel();
		lbEq = new JPanel();
		content = new JPanel();
		mnuBar = new myMenuBar(this);
		setJMenuBar ( mnuBar );
		
		add(lbTf);
		lbTf.setBounds(30, 40, 280, 30);
		lbTf.setLayout(new GridLayout(1,1));
		add(lbEq);
		lbEq.setBounds(60, 370, 210, 30);
		lbEq.setLayout(new GridLayout(1,2,25,25));
		add(lbBt);
		lbBt.setBounds(40,100,260,250);
		lbBt.setLayout(new GridLayout(4,4,25,25));
		add(content);
		lbTf.add(tf);
		for (int i=0;i<16;i++)lbBt.add(btnTab[i]);
		for (int i=16;i<18;i++)lbEq.add(btnTab[i]);

		setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		String symbol=e.getActionCommand();
		if(symbol.equals("1") || symbol.equals("2") || symbol.equals("3") || symbol.equals("4") || symbol.equals("5") || 
		   symbol.equals("6") || symbol.equals("7") || symbol.equals("8") || symbol.equals("9") || symbol.equals("0")) {

			if(indNumber==2){
				sNumber="";
				indOperation=0;
				indNumber=0;
			}
			else if (indNumber==3){
				if (sNumber.equals("ERROR")){
					sNumber="";
					indOperation=0;
					indNumber=0;
				}
				else {
					sNumber="";
					tabNumber[0]=tabNumber[2];
					indNumber=1;
				}
			}
			sNumber=sNumber.concat(symbol);
		}
		else if(symbol.equals(".")) {
			if (indNumber<2){
				if (sNumber.indexOf(".")==-1) sNumber=sNumber.concat(symbol);
				if (sNumber.equals(".")) sNumber="0.";
				else if (sNumber.equals("-.")) sNumber="-0.";
			}
		}
		else if(symbol.equals("+/-")) {
			if (indNumber<2){
				if (sNumber.indexOf("-")==-1) sNumber="-"+sNumber;
				else sNumber=sNumber.substring(1);
			}
		}
		else if(symbol.equals("C")) {
			if (indNumber<2) sNumber="";
			else if (sNumber.equals("ERROR")){
				indOperation=0;
				sNumber="";
				indNumber=0;
			}
		}
		else if(symbol.equals("+") || symbol.equals("-") || symbol.equals("*") || symbol.equals("/")) {
			if(indNumber==0){
				if(sNumber.equals("-")) return;
				
				if(sNumber.equals(""))sNumber="0";
				tabNumber[0]=Double.parseDouble(sNumber);
				indNumber++;
				sNumber="";
	
				for (int i=3;i<16;i+=4){
					if (symbol.equals(btnTab[i].getText())) {
						indOperation=i;
						btnTab[i].setBackground(Color.ORANGE);
					}
					else btnTab[i].setBackground(btnTab[0].getBackground());
	
			}
			else if(indNumber==1){
				if(sNumber.equals("-")) return;
				if(sNumber.equals("")){
				
					for (int i=3;i<16;i+=4){
						if (symbol.equals(btnTab[i].getText())) {
							indOperation=i;
							btnTab[i].setBackground(Color.ORANGE);
						}
						else btnTab[i].setBackground(btnTab[0].getBackground());
					}
						
					return;
				}
				tabNumber[1]=Double.parseDouble(sNumber);
				Equals();
				indNumber=3;
				for (int i=3;i<16;i+=4){
					if (symbol.equals(btnTab[i].getText())) {
						indOperation=i;
						btnTab[i].setBackground(Color.ORANGE);
					}
					else btnTab[i].setBackground(btnTab[0].getBackground());
				}

			}
			else if (indNumber==2) {
				if(sNumber.equals("ERROR"))return;
				indNumber=1;
				for (int i=3;i<16;i+=4){
					if (symbol.equals(btnTab[i].getText())) {
						indOperation=i;
						btnTab[i].setBackground(Color.ORANGE);
					}
					else btnTab[i].setBackground(btnTab[0].getBackground());
				}
		
				tabNumber[0]=tabNumber[2];
				sNumber="";
			}
			else{
				System.out.println("FATAL ERROR");
			}
		}

		else if(e.getActionCommand().equals("=")) {
			if(indNumber==1){
				if(sNumber.equals("-")) return;
				
				if(sNumber.equals(""))sNumber="0";
				tabNumber[1]=Double.parseDouble(sNumber);
				Equals();
				indNumber=2;
			}
		}
		if (sNumber.length()==0) tf.setText("0");
		else tf.setText(sNumber);
	}
	
	public void Equals (){
		if(indNumber==1){
			for (int i=3;i<16;i+=4){
				btnTab[i].setBackground(btnTab[0].getBackground());
			}
			switch(indOperation){
				case 3:
					tabNumber[2]=tabNumber[0]*tabNumber[1];
					break;
				case 7:
					if (tabNumber[1]==0){
						sNumber="ERROR";
						return;
					}
					tabNumber[2]=tabNumber[0]/tabNumber[1];
					break;
				case 11:
					tabNumber[2]=tabNumber[0]+tabNumber[1];
					break;
				case 15:
					tabNumber[2]=tabNumber[0]-tabNumber[1];
					break;
				default:
					System.out.println("CRITICAL ERROR!");		
			}
			sNumber=Double.toString(tabNumber[2]);
		}

	}
	
	public static void main (String args[]){
		MyFrame okno=new MyFrame();
	}
}






