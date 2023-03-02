package view;

import javax.swing.JOptionPane;

import controller.RedesController;

public class main {

	public static void main(String [] args) {
		
		RedesController cont = new RedesController();
		
		int opc = 0;
		
		while(opc != 9) {
			
			opc = Integer.parseInt(JOptionPane.showInputDialog("Selecione uma Operção\n1 - Encontrar os Adaptadores de Internet com IPv4\n2 - Encontrar tempo medio do Ping"));
			
				switch(opc) {
				
					case 1: 
						cont.Ip();
						break;
					
					case 2: 
						cont.Ping();
						break;
					
					case 9: 
						JOptionPane.showMessageDialog(null,"Finalizando Programa...");
						break;
				
					default: 
						JOptionPane.showMessageDialog(null,"Opção invalida");
				}
		}
	}
}
