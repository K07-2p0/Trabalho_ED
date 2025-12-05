import core.game.MotorJogo;

public class Main {
	public static void main(String[] args) {
		MotorJogo motor = new MotorJogo();
		// Permite carregar mapa e enigmas via argumentos
		if (args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				String a = args[i];
				if ("--mapa".equals(a) && i + 1 < args.length) {
					motor.carregarMapa(args[++i]);
				} else if ("--enigmas".equals(a) && i + 1 < args.length) {
					motor.carregarEnigmas(args[++i]);
				}
			}
		}
		motor.start();
	}
}
