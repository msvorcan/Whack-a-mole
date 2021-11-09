package igra;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class Rupa extends Canvas implements Runnable {

	private static final Color boja = new Color(102, 51, 0);
	private Basta basta;
	private Zivotinja zivotinja;
	private boolean udarena = false;

	private Thread nit;
	private boolean uToku = false;

	private int brojKoraka;
	private int trenutanBrojKoraka;
	private static final int dt = 100;

	public Rupa(Basta basta) {
		super();
		setBackground(boja);
		this.basta = basta;
	}

	public Zivotinja getZivotinja() {
		return zivotinja;
	}

	public void setZivotinja(Zivotinja zivotinja) {
		this.zivotinja = zivotinja;
	}

// run() metoda i povezane metode....................................

	public synchronized void postaviNit() {
		nit = new Thread(this);
	}

	public synchronized void pokreniNit() {
		uToku = true;
		nit.start();
	}

	public synchronized void zaustaviNit() {
		if (nit != null) {
			uToku = false;
			if (!udarena && (zivotinja != null))
				zivotinja.pobegla();
			zivotinja = null;
			udarena = false;
			repaint();
			nit.interrupt();
		}
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			try {
				synchronized (this) {
					while (!uToku)
						wait();
				}
				for (trenutanBrojKoraka = 0; trenutanBrojKoraka < brojKoraka; trenutanBrojKoraka++) {
					Thread.sleep(dt);
					repaint();
				}
				Thread.sleep(2000);
				zaustaviNit();
			} catch (InterruptedException e) {
				break;
			}
		}

	}

// PONASANJE TOKOM IGRE.................................................

	public void zgazi() {
		if (zivotinja != null) {
			zivotinja.udarena();
		}
	}

	public boolean uTokuJe() {
		return uToku;
	}

	public int getBrojKoraka() {
		return brojKoraka;
	}

	public int getTrenutanBrojKoraka() {
		return trenutanBrojKoraka;
	}

	public void setBrojKoraka(int brojKoraka) {
		this.brojKoraka = brojKoraka;
		this.trenutanBrojKoraka = brojKoraka;
	}

	public void setUdarena(boolean udarena) {
		this.udarena = udarena;
	}

	public void umanjiPovrceUBasti() {
		basta.umanjiPovrce();
	}

// paint().............................

	@Override
	public void paint(Graphics g) {
		if (zivotinja != null)
			zivotinja.iscrtaj();
	}

}
