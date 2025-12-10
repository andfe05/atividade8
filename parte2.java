import java.io.*;

// ===========================================================
// PARTE 2 – TRATAMENTO DE EXCEÇÕES
// ===========================================================

// 4. Classe DivisorSeguro
class DivisorSeguro {
    public int dividir(int a, int b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Divisão por zero não é permitida!");
        }
        return a / b;
    }
}

// 5. Classe LeitorDeArquivos
class LeitorDeArquivos {

    public void lerArquivo(String caminho) {

        BufferedReader leitor = null;

        try {
            leitor = new BufferedReader(new FileReader(caminho));
            String linha;

            System.out.println("Conteúdo do arquivo:");
            while ((linha = leitor.readLine()) != null) {
                System.out.println(linha);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo não encontrado!");
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo!");
        } finally {
            System.out.println("Tentando fechar o arquivo...");
            try {
                if (leitor != null) leitor.close();
                System.out.println("Arquivo fechado.");
            } catch (IOException e) {
                System.out.println("Erro ao fechar o arquivo.");
            }
        }
    }
}

// 6. Classe de exceção personalizada
class NumeroInvalidoException extends Exception {
    public NumeroInvalidoException(String mensagem) {
        super(mensagem);
    }
}

// ConversorNumero
class ConversorNumero {
    public int converterParaInteiro(String valor) throws NumeroInvalidoException {
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            throw new NumeroInvalidoException("Valor inválido para conversão: " + valor);
        }
    }
}

// ===========================================================
// PARTE 3 – DESAFIO INTEGRADOR – SISTEMA DE PAGAMENTOS
// ===========================================================

// Interface
interface MetodoPagamento {
    void processarPagamento(double valor) throws Exception;
}

// Pagamento com Cartão
class PagamentoCartao implements MetodoPagamento {
    public void processarPagamento(double valor) throws Exception {
        if (valor > 2000) {
            throw new Exception("Limite de cartão excedido!");
        }
        System.out.println("Pagamento de R$" + valor + " aprovado no cartão.");
    }
}

// Pagamento via Pix
class PagamentoPix implements MetodoPagamento {
    public void processarPagamento(double valor) {
        System.out.println("Pagamento de R$" + valor + " processado via PIX.");
    }
}

// Pagamento em Dinheiro
class PagamentoDinheiro implements MetodoPagamento {
    public void processarPagamento(double valor) throws Exception {
        if (valor < 0) {
            throw new Exception("Valor negativo não é permitido!");
        }
        System.out.println("Pagamento de R$" + valor + " recebido em dinheiro.");
    }
}

// ===========================================================
// MAIN – TESTES COMPLETOS
// ===========================================================

public class Main {
    public static void main(String[] args) {

        // ======================
        // 4. TESTE – DivisorSeguro
        // ======================
        System.out.println("=== TESTE DivisorSeguro ===");
        DivisorSeguro divisor = new DivisorSeguro();

        try {
            System.out.println("10 / 2 = " + divisor.dividir(10, 2));
            System.out.println("10 / 0 = " + divisor.dividir(10, 0));
        } catch (ArithmeticException e) {
            System.out.println("Erro capturado: " + e.getMessage());
        }

        // ======================
        // 5. TESTE – LeitorDeArquivos
        // ======================
        System.out.println("\n=== TESTE LeitorDeArquivos ===");
        LeitorDeArquivos leitor = new LeitorDeArquivos();
        leitor.lerArquivo("caminho_invalido.txt"); // caminho falso

        // ======================
        // 6. TESTE – ConversorNumero
        // ======================
        System.out.println("\n=== TESTE ConversorNumero ===");
        ConversorNumero conversor = new ConversorNumero();

        try {
            System.out.println("Convertendo '123': " + conversor.converterParaInteiro("123"));
            System.out.println("Convertendo 'abc': " + conversor.converterParaInteiro("abc"));
        } catch (NumeroInvalidoException e) {
            System.out.println("Erro capturado: " + e.getMessage());
        }

        // ======================
        // 7. SISTEMA DE PAGAMENTOS
        // ======================
        System.out.println("\n=== TESTE Sistema de Pagamentos ===");

        MetodoPagamento[] pagamentos = {
                new PagamentoCartao(),
                new PagamentoPix(),
                new PagamentoDinheiro(),
                new PagamentoCartao(),
                new PagamentoDinheiro()
        };

        double[] valores = {1500, 500, -10, 2500, 100};

        for (int i = 0; i < pagamentos.length; i++) {
            System.out.println("\nProcessando pagamento de R$" + valores[i]);

            try {
                pagamentos[i].processarPagamento(valores[i]);
            } catch (Exception e) {
                System.out.println("Falha no pagamento: " + e.getMessage());
            }
        }
    }
}
