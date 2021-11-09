package igra;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Basta extends Panel implements Runnable {
	
	private int povrce = 100;
	private static Color boja = Color.GREEN;
	private Rupa[][] matrica;
	private int brojVrsta, brojKolona;
	
	private int brojKoraka;
	private int dt;
	
	private Thread nit;
	
	public Basta(int brojVrsta, int brojKolona) {
		super(new GridLayout(brojVrsta, brojKolona, 10, 10));
		this.brojVrsta = brojVrsta;
		this.brojKolona = brojKolona;
		matrica = new Rupa[brojKolona][brojVrsta];
		nit = new Thread(this);
		
		setBackground(boja);
		dodajRupe();
	}
	
// GETERI I SETERI.......................................

	public int getBrojKoraka() {
		return brojKoraka;
	}

	public void setBrojKoraka(int brojKoraka) {
		this.brojKoraka = brojKoraka;
		for (Rupa[] rupas : matrica) {
			for (Rupa rupa : rupas) {
				rupa.setBrojKoraka(brojKoraka);
			}
		}
	}
	
	public void setDt(int dt) {
		this.dt = dt;
	}
	
	public void umanjiPovrce() {
		--povrce;
		String string = "Povrce: " + povrce;
		Igra.dohvatiIgru().promeniPovrce(string);
	}
	
// run() I POVEZANE METODE............................................

	public synchronized void pokreni() {
		nit = new Thread(this);
		povrce = 100;
		String string = "Povrce: " + povrce;
		Igra.dohvatiIgru().promeniPovrce(string);
		nit.start();
	}
	
	public synchronized void zaustavi() {
		for(int i = 0; i < brojVrsta; i++)
			for(int j = 0; j < brojKolona; j++) {
				matrica[i][j].zaustaviNit();				
			}
		if (nit != null)
			nit.interrupt();
	}

	@Override
	public void run() {
			try {
				while(!Thread.interrupted()) {
				postaviKrticu();
				Thread.sleep(dt);
				setDt(dt*99/100);
				repaint();
				}	
			} catch (InterruptedException e) {}
		
	}
	
	private void dodajRupe() {
		for(int i = 0; i < brojKolona; i++)
			for(int j = 0; j < brojVrsta; j++) {
				int a = i;
				int b = j;
				add(matrica[i][j] = new Rupa(this));
				matrica[i][j].addMouseListener(new MouseAdapter() {

					@Override
					public void mousePressed(MouseEvent e) {
						matrica[a][b].zgazi();
					}
				});
			}
	}
	
	private void postaviKrticu() {     
		while(true) {
			int vrsta = (int) (Math.random() * brojVrsta);
			int kolona = (int) (Math.random() * brojKolona);
			if(matrica[kolona][vrsta].uTokuJe()) continue;
			matrica[kolona][vrsta].setZivotinja(new Krtica(matrica[kolona][vrsta]));		
			matrica[kolona][vrsta].postaviNit();
			matrica[kolona][vrsta].pokreniNit();
			break;
		}
	}

}
