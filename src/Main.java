import construtor.BuilderBebida;
import construtor.BuilderHamburguer;
import construtor.BuilderSobremesa;
import facade.FacadeHamburgueria;
import pagamento.Pagamento;
import pagamento.PagamentoCartao;
import pagamento.PagamentoDinheiro;
import pagamento.PagamentoPix;

import java.util.Scanner;

public class Main {
    private static final Scanner entrada = new Scanner(System.in);
    private static final FacadeHamburgueria facade = new FacadeHamburgueria();

    public static void main(String[] args) {
        int opcao;
        do {
            mostrarMenu();
            opcao = lerInteiro("Escolha: ");
            try {
                executar(opcao);
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n=== Hamburgueria ===");
        System.out.println("1 - Adicionar hambúrguer");
        System.out.println("2 - Adicionar bebida");
        System.out.println("3 - Adicionar sobremesa");
        System.out.println("4 - Finalizar pedido");
        System.out.println("5 - Pagar");
        System.out.println("6 - Ver resumo");
        System.out.println("0 - Sair");
    }

    private static void executar(int opcao) {
        switch (opcao) {
            case 1 ->  {
                facade.exigirPedidoAnteriorPago();
                montarLanche();
            }
            case 2 -> {
                facade.exigirPedidoAnteriorPago();
                montarBebida();
            }
            case 3 -> {
                facade.exigirPedidoAnteriorPago();
                montarSobremesa();
            }
            case 4 -> {
                facade.exigirPedidoAnteriorPago();
                finalizarPedido();
            }
            case 5 -> facade.pagar(escolherPagamento());
            case 6 -> facade.resumo();
            case 0 -> System.out.println("Até logo!");
            default -> System.out.println("Opção inválida.");
        }
    }

    // MUDANÇA: Pergunta qual lanche base o cliente quer antes de montar os ingredientes.
    private static void montarLanche() {
        String nomeLanche = escolherTexto("Escolha o lanche:", new String[]{
                "X-Burger", "X-Salada", "X-Tudo"
        });

        BuilderHamburguer opcoes = new BuilderHamburguer();

        String pao = escolherTexto("Tipo de pão:", new String[]{
                "Pão italiano", "Pão brioche", "Pão australiano", "Pão com gergelim"
        });
        opcoes.tipoPao(pao);

        int carnes = escolherInteiro("Quantidade de hambúrgueres:",
                new String[]{"1 hambúrguer", "2 hambúrgueres", "3 hambúrgueres", "4 hambúrgueres", "5 hambúrgueres"},
                new int[]{1, 2, 3, 4, 5});
        opcoes.quantidadeHamburguer(carnes);

        opcoes.temCebola(lerSimNao("Com cebola? (s/n): "));
        opcoes.temCodimentos(lerSimNao("Com condimentos? (s/n): "));
        opcoes.temKetchup(lerSimNao("Com ketchup? (s/n): "));

        boolean queijo = lerSimNao("Extra de queijo? (s/n): ");
        boolean bacon = lerSimNao("Extra de bacon? (s/n): ");
        int quantidade = lerInteiroPositivo("Quantos lanches iguais a esse? ");

        facade.adicionarHamburger(nomeLanche, opcoes, quantidade, queijo, bacon);
        System.out.println(nomeLanche + " adicionado!");
    }

    // MUDANÇA: Pergunta qual bebida base o cliente quer antes de escolher tamanho/marca/sabor.
    private static void montarBebida() {
        String nomeBebida = escolherTexto("Escolha a bebida:", new String[]{
                "Refrigerante", "Milk-Shake", "Suco Natural", "Água"
        });

        BuilderBebida opcoes = new BuilderBebida();

        double volume = escolherDouble("Tamanho da bebida:",
                new String[]{"350 ml (Lata/Pequeno)", "500 ml (Médio)", "1000 ml (1 Litro)"},
                new double[]{350.0, 500.0, 1000.0});
        opcoes.tamanhoBebida(volume);

        String marca = escolherTexto("Marca:", new String[]{
                "Coca-Cola", "Guaraná Antarctica", "Del Valle", "Da Casa"
        });
        opcoes.marcaBebida(marca);

        String sabor = escolherTexto("Sabor:", new String[]{
                "Cola", "Guaraná", "Chocolate", "Morango", "Laranja", "Sem sabor"
        });
        opcoes.saborBebida(sabor);

        opcoes.terCopo(lerSimNao("Com copo? (s/n): "));
        opcoes.terCanudo(lerSimNao("Com canudo? (s/n): "));

        int quantidade = lerInteiroPositivo("Quantas bebidas iguais a essa? ");
        facade.adicionarBebida(nomeBebida, opcoes, quantidade);
        System.out.println(nomeBebida + " adicionada!");
    }

    // MUDANÇA: Pergunta qual sobremesa base o cliente quer (Sorvete, Açaí ou Pizza Doce).
    private static void montarSobremesa() {
        String nomeSobremesa = escolherTexto("Escolha a sobremesa:", new String[]{
                "Sorvete", "Açaí", "Pizza Doce"
        });

        BuilderSobremesa opcoes = new BuilderSobremesa();

        String sabor = escolherTexto("Sabor:", new String[]{
                "Morango", "Chocolate", "Baunilha", "Tradicional"
        });
        opcoes.saborSobremesa(sabor);

        String calda = escolherTexto("Sabor da calda:", new String[]{
                "Chocolate", "Morango", "Caramelo", "Leite Condensado", "Sem calda"
        });
        opcoes.saborCalda(calda);

        opcoes.temJujuba(lerSimNao("Com jujuba? (s/n): "));
        opcoes.temNutella(lerSimNao("Com Nutella? (s/n): "));

        int quantidade = lerInteiroPositivo("Quantas sobremesas iguais a essa? ");
        facade.adicionarSobremesa(nomeSobremesa, opcoes, quantidade);
        System.out.println(nomeSobremesa + " adicionada!");
    }

    private static void finalizarPedido() {
        System.out.print("Endereço de entrega: ");
        String endereco = entrada.nextLine().trim();
        while (endereco.isEmpty()) {
            System.out.print("O endereço não pode ficar vazio. Digite novamente: ");
            endereco = entrada.nextLine().trim();
        }
        System.out.print("Observações (Enter para pular): ");
        String observacoes = entrada.nextLine().trim();

        facade.fazerPedido(endereco, observacoes);
        System.out.println("Pedido criado!");
    }

    private static Pagamento escolherPagamento() {
        double valorTotal = facade.getValorTotalPedido();
        System.out.println("\nValor total a pagar: R$ " + valorTotal);
        System.out.println("Forma de pagamento:");
        System.out.println("1 - Pix (5% de desconto)");
        System.out.println("2 - Cartão");
        System.out.println("3 - Dinheiro");
        int forma = lerOpcaoMenu(3);
        return switch (forma) {
            case 1 -> new PagamentoPix();
            case 2 -> new PagamentoCartao();
            case 3 -> new PagamentoDinheiro(lerValor("Quanto o cliente entregou? R$ "));
            default -> throw new IllegalArgumentException("Forma de pagamento inválida.");
        };
    }

    private static String escolherTexto(String titulo, String[] opcoes) {
        System.out.println("\n" + titulo);
        for (int i = 0; i < opcoes.length; i++) {
            System.out.println((i + 1) + " - " + opcoes[i]);
        }
        return opcoes[lerOpcaoMenu(opcoes.length) - 1];
    }

    private static int escolherInteiro(String titulo, String[] rotulos, int[] valores) {
        System.out.println("\n" + titulo);
        for (int i = 0; i < rotulos.length; i++) {
            System.out.println((i + 1) + " - " + rotulos[i]);
        }
        return valores[lerOpcaoMenu(rotulos.length) - 1];
    }

    private static double escolherDouble(String titulo, String[] rotulos, double[] valores) {
        System.out.println("\n" + titulo);
        for (int i = 0; i < rotulos.length; i++) {
            System.out.println((i + 1) + " - " + rotulos[i]);
        }
        return valores[lerOpcaoMenu(rotulos.length) - 1];
    }

    private static int lerOpcaoMenu(int max) {
        int escolha = lerInteiro("Opção (1 a " + max + "): ");
        while (escolha < 1 || escolha > max) {
            System.out.println("Escolha uma opção entre 1 e " + max + ".");
            escolha = lerInteiro("Opção (1 a " + max + "): ");
        }
        return escolha;
    }

    private static int lerInteiroPositivo(String mensagem) {
        int numero = lerInteiro(mensagem);
        while (numero < 1) {
            System.out.println("Digite um número maior que zero.");
            numero = lerInteiro(mensagem);
        }
        return numero;
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(entrada.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }

    private static double lerValor(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                double valor = Double.parseDouble(entrada.nextLine().trim().replace(',', '.'));
                if (valor > 0) {
                    return valor;
                }
                System.out.println("Digite um valor maior que zero.");
            } catch (NumberFormatException e) {
                System.out.println("Digite um valor válido.");
            }
        }
    }

    private static boolean lerSimNao(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String resp = entrada.nextLine().trim();
            if (resp.equalsIgnoreCase("s")) {
                return true;
            }
            if (resp.equalsIgnoreCase("n")) {
                return false;
            }
            System.out.println("Responda apenas com 's' ou 'n'.");
        }
    }
}