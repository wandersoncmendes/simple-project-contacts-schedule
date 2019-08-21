import javax.swing.UIManager;

import br.com.agenda.view.JanelaPrincipal;

/**
 * Classe de iniciação do projeto
 * @author Dell
 */
public class Run {

	public static void main(String[] args) throws Exception {
		aplicaLookAndFeel();
		
		JanelaPrincipal janela = new JanelaPrincipal();
		janela.setVisible(true);
	}
	
	public static void aplicaLookAndFeel() {
		try {
			String padraoJava = 
					"javax.swing.plaf.metal.MetalLookAndFeel";
			
			String padraoSO = UIManager
					.getSystemLookAndFeelClassName();
			
			String motif = 
					"com.sun.java.swing.plaf.motif.MotifLookAndFeel";
			
			String windows = 
					"com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
			
			UIManager.setLookAndFeel(padraoSO);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
