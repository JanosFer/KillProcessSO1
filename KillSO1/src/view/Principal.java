package view;

import controller.KillController;
import javax.swing.JOptionPane;

public class Principal {
	public static void main(String[] args) {
		KillController kc = new KillController();
		
		int opc = 0;
		
		while(opc != 4) {
			opc = Integer.parseInt(JOptionPane.showInputDialog("Informe a opção desejada: \n 1 - Listar processos ativos \n 2 - Matar processo por PID(identificador) \n 3 - Matar processo por nome \n 4 - Sair"));
			switch(opc) {
				case 1:
					kc.listaProcessos();
					break;
				case 2:
					kc.mataPid();
					break;
				case 3:
					kc.mataNome();
					break;
				case 4:
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opção inválida!");
			}
		}
	}
}