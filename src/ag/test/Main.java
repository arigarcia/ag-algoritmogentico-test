package ag.test;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextArea;

import ag.test.geneticsalgoritms.model.Algoritimo;
import ag.test.geneticsalgoritms.model.Populacao;
import ag.test.geneticsalgoritms.view.MainWindow;
import ag.test.geneticsalgoritms.view.MainWindowListener;

public class Main {
	// elitismo
	private static boolean eltismo = true;	
	// tamanho da população
	private static int tamPop = 100;
	// numero máximo de gerações
	private static int numMaxGeracoes = 10000;
	
	private static void configureAlgoritmo(String sol, String car){
		Algoritimo.setSolucao(sol);
		// Define os caracteres existentes
		Algoritimo.setCaracteres(car);
		// taxa de crossover de 60%
		Algoritimo.setTaxaDeCrossover(0.5);
		// taxa de mutação de 3%
		Algoritimo.setTaxaDeMutacao(0.7);
	}
	
	public static void main(String[] args) {
		//
		MainWindowListener listener = new MainWindowListener() {
			@Override
			public void run(JTextArea arealog) {
				// define o número de genes do indivíduo baseado na solução
				int numGenes = Algoritimo.getSolucao().length();
				// cria a primeira população aleatérioa
				Populacao populacao = new Populacao(numGenes, tamPop);
				//
				boolean temSolucao = false;
				int geracao = 0;
				//
				arealog.append("Iniciando... Aptidão da solução: "
				        + Algoritimo.getSolucao().length() + "\n");

				// loop até o critério de parada
				while (!temSolucao && geracao < numMaxGeracoes) {
					geracao++;

					// cria nova populacao
					populacao = Algoritimo.novaGeracao(populacao, eltismo);

					arealog.append("Geração " + geracao + " | Aptidão: "
					        + populacao.getIndivduo(0).getAptidao() + " | Melhor: "
					        + populacao.getIndivduo(0).getGenes() + "\n");

					// verifica se tem a solucao
					temSolucao = populacao.temSolocao(Algoritimo.getSolucao());
				}

				if (geracao == numMaxGeracoes) {
					arealog.append("Número Maximo de Gerações | "
					        + populacao.getIndivduo(0).getGenes() + " "
					        + populacao.getIndivduo(0).getAptidao() + "\n");
				}

				if (temSolucao) {
					arealog.append("Encontrado resultado na geração " + geracao
					        + " | " + populacao.getIndivduo(0).getGenes()
					        + " (Aptidão: " + populacao.getIndivduo(0).getAptidao()
					        + ")" + "\n");
				}
				
			}

		};
		//
		MainWindow w = new MainWindow(listener);
        w.setBounds(0, 0, 800, 430);
        w.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                System.exit(0);
            }
        } );
        //
        configureAlgoritmo(w.getObjective(), w.getCharacters());
        //
        w.setVisible(true);
    }

}
