//Autores: Carlos Eduardo Santos Oliveira & Maria Eduarda Santos Oliveira

import java.text.ParseException;
import java.util.Scanner;

public class App {
    private static final Scanner sc = new Scanner(System.in);
    private static final LeituraArquivo leituraArquivo = new LeituraArquivo();
    private static ListadeRuas ltRuas;

    public static void main(String[] args) throws ParseException {
        leituraArquivo.leArquivo();
        ltRuas = leituraArquivo.getLtRuas();
        int escolha;

        do {
            escolha = menu();
            switch (escolha) {
                case 1 -> System.out.println(ltRuas.getRuaComMaisSinalizacoes());
                case 2 -> System.out.println("Mês com mais sinalizações: "+ltRuas.getMesComMaisSinalizacoes());
                case 3 -> menuNavegacao();
                case 0 -> System.out.println("Fim do programa");
                default -> System.out.println("Opção inválida, tente novamente.");
            }

        } while (escolha != 0);
    }

    public static int menu() {
        int esc;

        do {
            System.out.println("Menu de Opções:");
            System.out.println("1- Ver rua com mais sinalizações.");
            System.out.println("2- Ver mês com mais sinalizações implantadas.");
            System.out.println("3- Navegar pelas ruas.");
            System.out.println("0- Finalizar programa.");
            esc = sc.nextInt();

            if (esc < 0 || esc > 3) {
                System.out.println("Opção inválida, tente novamente.");
            }
        } while (esc < 0 || esc > 3);
        return esc;
    }

    public static void menuNavegacao() {
        int userEsc;

        do {
            System.out.println(ltRuas.atual()); // Imprimir informações da rua atual
            System.out.println("1- Ir para a próxima rua.");
            System.out.println("2- Voltar para a rua anterior.");
            System.out.println("0- Voltar para o menu anterior.");
            userEsc = sc.nextInt();

            switch (userEsc) {
                case 0 -> ltRuas.next();
                case 1 -> ltRuas.prev();
                case 2 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        } while (userEsc < 0 || userEsc > 2);
    }
}
