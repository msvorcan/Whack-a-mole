package igra;

import java.awt.Color;

public abstract class Zivotinja {

	protected Rupa rupa;
	protected int sirina, visina;
	protected Color boja;

	public Zivotinja(Rupa rupa) {
		this.rupa = rupa;
	}
	
	public void racunajDimenzije() {
		double procenat = (double)rupa.getTrenutanBrojKoraka()/ (double)rupa.getBrojKoraka();
		sirina = (int)(procenat * rupa.getWidth());
		visina = (int)(procenat * rupa.getHeight());
	}

	public abstract void udarena();

	protected abstract void iscrtaj();

	protected abstract void pobegla();
	
}
