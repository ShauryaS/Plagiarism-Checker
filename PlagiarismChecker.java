package PersonalProjects.Plagiarism;

import java.util.*;//imports
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class PlagiarismChecker extends JFrame{//class header

	private Left l;//left panel
	private Right r;//right panel

	private Scanner read1;//scanner 1
	private Scanner read2;//scanner 2
	private String [][] words = new String [2][100]; //2d array

	private int totWords;
	private int simWords;

	private double percent;

	public static void main(String [] args){//main

		PlagiarismChecker pc = new PlagiarismChecker();//instance/instansiation

	}	

	public PlagiarismChecker(){//constructor; add panels and instansiate scanner and other do other req JFrame stuff

		super("Plagiarism Checker");//name
		setSize(700, 500);//size
		setLayout(new GridLayout(1, 2));//set layout grid
		l = new Left();//instansiate l
		add(l);//add it
		r = new Right();//instansiate r
		add(r);//add it
		setDefaultCloseOperation(EXIT_ON_CLOSE);//make it exit
		setResizable(false);//resize false
		setVisible(true);//visible true

	}

	class Left extends JPanel implements ActionListener{//panel left w/ action

		JTextArea ja1;//text areas
		JTextArea ja2;

		JButton comp;//button

		public Left(){//set layout, add components, and set colors

			setLayout(new GridLayout(3, 1));
			ja1 = new JTextArea("Print name of File 1.");
			ja2 = new JTextArea("Print name of File 2.");
			comp = new JButton("Compare");

			add(ja1);
			ja1.setBackground(Color.GREEN);
			add(ja2);
			ja1.setLineWrap(true);
			ja1.setWrapStyleWord(true);
			ja2.setBackground(Color.ORANGE);
			ja2.setLineWrap(true);
			ja2.setWrapStyleWord(true);
			add(comp);
			comp.setBackground(Color.MAGENTA);
			comp.addActionListener(this);

		}

		public void actionPerformed(ActionEvent e){//action performed method

			if(e.getSource()==comp){//check if button pressed call doStuff()

				try{

					read1 = new Scanner(new File(ja1.getText()));

				}catch(FileNotFoundException a){

					System.out.println("File 1 not found");
					System.exit(1);

				}

				try{

					read2 = new Scanner(new File(ja2.getText()));

				}catch(FileNotFoundException a){

					System.out.println("File 2 not found");
					System.exit(2);

				}

				int i = 0;
				int j = 0;

				while(read1.hasNext()){

					String a = read1.next();
					words[0][i] = a;
					i++;
					totWords++;

				}

				while(read2.hasNext()){

					String b = read2.next();
					words[1][j] = b;
					j++;

				}

				for(int k = 0; k<totWords; k++){

					if(words[0][k].equals(words[1][k])){

						simWords++;

					}

				}

				percent = (simWords*100.0)/totWords;

				if(percent<=20){

					r.b.c = Color.GREEN;
					r.b.repaint();

					r.t.lab.setText("Your report: \n" + ja1.getText() + ": " + totWords + " words \n" + ja2.getText() + ": " + totWords + " words \n" + "Rating: " + simWords + "\n\n\n" + percent + " is a green (safe) score");
					r.t.repaint();

				}

				if(percent>20 && percent<=50){

					r.b.c = Color.YELLOW;
					r.b.repaint();

					r.t.lab.setText("Your report: \n" + ja1.getText() + ": " + totWords + " words \n" + ja2.getText() + ": " + totWords + " words \n" + "Rating: " + simWords + "\n\n\n" + percent + " is a yellow (mediumly safe) score");
					r.t.repaint();

				}

				if(percent>50){

					r.b.c = Color.RED;
					r.b.repaint();

					r.t.lab.setText("Your report: \n" + ja1.getText() + ": " + totWords + " words \n" + ja2.getText() + ": " + totWords + " words \n" + "Rating: " + simWords + "\n\n\n" + percent + " is a red (insafe) score");
					r.t.repaint();

				}

			}

		}

	}

	class Right extends JPanel{//right panel

		private Top t;//top panel instance
		private Bottom b;//bottom panel instance

		public Right(){//constructor; set layout border and add panels

			setLayout(new BorderLayout());
			b = new Bottom();
			add(b, BorderLayout.SOUTH);
			t = new Top();
			add(t, BorderLayout.CENTER);

		}

		class Top extends JPanel{//top panel

			private JTextArea lab;//label

			public Top(){//constructor; instansiate and add label

				setLayout(new GridLayout(1,1));
				lab = new JTextArea("Please enter two file names, then press Compare.");
				lab.setLineWrap(true);
				lab.setWrapStyleWord(true);
				lab.setForeground(Color.WHITE);
				lab.setBackground(Color.BLACK);
				add(lab);

			}

		}

		class Bottom extends JPanel{//bottom panel

			private Color c;//color

			public Bottom(){//set initial color

				c = Color.BLACK;

			}

			public void paintComponent(Graphics g){//paint component

				setBackground(c);//set bg
				super.paintComponent(g);//paint it

			}

		}

	}

}
