package igra;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class Igra extends Frame {
	
	private Basta basta;
	private static boolean flag = true;
	private static Igra igra;
	private boolean uToku = false;
	
	private Button kreni = new Button("Kreni");
	private CheckboxGroup cGroup;
	private Checkbox lako, srednje, tesko;
	private Label povrce = new Label("Povrce: 100");
	private Panel bocniPanel = new Panel(new GridLayout(0, 1));
	
	public Igra() {
		super("Igra");
		setSize(570, 500);
		basta = new Basta(4, 4);
		flag = false;
		igra = this;
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				if(uToku)
					basta.zaustavi();
				dispose();
			}
			
		});
		
		add(basta, BorderLayout.CENTER);
		popuniPanel();
		add(bocniPanel, BorderLayout.EAST);
		
		kreni.addActionListener((ActionEvent e) -> {
			if(uToku) {
				basta.zaustavi();
				kreni.setLabel("Kreni");
				promeniPovrce("Povrce: 100");
			} else {
				switch (cGroup.getSelectedCheckbox().getLabel()) {
				case "Lako": 
					basta.setDt(1000);
					basta.setBrojKoraka(10);
					break;
				case "Srednje":
					basta.setDt(750);
					basta.setBrojKoraka(8);
					break;
				case "Tesko":
					basta.setDt(500);
					basta.setBrojKoraka(6);
					break;
				}
				basta.pokreni();
				kreni.setLabel("Zaustavi");
			}
			uToku = !uToku;
		});
		
		setVisible(true);
	}
	
//.................................................................................
	
	public static Igra dohvatiIgru() {return igra; }
	
	public boolean uTokuJe() { return uToku; }
	
	public void promeniPovrce(String string) {
		povrce.setText(string);
		if(povrce.getText().equals("Povrce: 0")) {
			basta.zaustavi();
			kreni.setLabel("Kreni");
			promeniPovrce("Povrce: 100");
			uToku = false;
		}
			
	}
	
	public static void main(String[] args) {
		if(flag) new Igra();
	}
	
	private void popuniPanel() {
		cGroup = new CheckboxGroup();
		lako = new Checkbox("Lako", true, cGroup);
		srednje = new Checkbox("Srednje", false, cGroup);
		tesko = new Checkbox("Tesko", false, cGroup);
		
		Panel p1 = new Panel(new GridLayout(0, 1));
		Panel p2 = new Panel(new GridLayout(0, 1));
		
		povrce.setAlignment(Label.CENTER);
		povrce.setFont(new Font(null, Font.BOLD, 18));
		
		p1.add(lako);
		p1.add(srednje);
		p1.add(tesko);
		p1.add(kreni);
		p2.add(povrce, BorderLayout.CENTER);
		bocniPanel.add(p1, BorderLayout.NORTH);
		bocniPanel.add(p2, BorderLayout.SOUTH);
	}

}
