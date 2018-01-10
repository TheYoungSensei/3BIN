package question;

public class MainMachine {
	public static void main(String[] args) {
		MachineACafe mq = new MachineACafe();
		mq.selectionnerBoisson(ToucheBoisson.decaNoir);
		mq.entrerMonnaie(Piece.cinquanteCents);
		mq.selectionnerBoisson(ToucheBoisson.decaNoir);
		mq.entrerMonnaie(Piece.vingtCents);
		mq.selectionnerBoisson(ToucheBoisson.chocoAuLaitSucre);
		try {
			mq.selectionnerBoisson(ToucheBoisson.cafeNoir);
		} catch (IllegalStateException e) {
			System.out.println("T��t");
		}
		mq.entrerMonnaie(Piece.cinquanteCents);
		mq.entrerMonnaie(Piece.cinquanteCents);
		mq.rendreMonnaie();
	}
}
