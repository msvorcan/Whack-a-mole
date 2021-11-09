package igra;

import java.awt.Color;
import java.awt.Graphics;

public class Krtica extends Zivotinja {

	public Krtica(Rupa rupa) {
		super(rupa);
		boja = Color.DARK_GRAY;
	}

	@Override
	public void udarena() {
		rupa.setUdarena(true);
		rupa.zaustaviNit();
	}

	@Override
	protected void iscrtaj() {
		racunajDimenzije();
		Graphics g = rupa.getGraphics();
		g.setColor(boja);
		g.fillOval(rupa.getWidth()/2 - sirina/2, rupa.getHeight()/2 - visina/2, sirina, visina);

	}

	@Override
	protected void pobegla() {
		rupa.umanjiPovrceUBasti();

	}

}
