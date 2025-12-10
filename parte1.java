// PARTE 1 – Interfaces

// 1. Interface OperacaoMatematica
interface OperacaoMatematica {
    double somar(double a, double b);
    double subtrair(double a, double b);
    double multiplicar(double a, double b);
    double dividir(double a, double b);
}

// Classe Calculadora que implementa a interface
class Calculadora implements OperacaoMatematica {

    @Override
    public double somar(double a, double b) {
        return a + b;
    }

    @Override
    public double subtrair(double a, double b) {
        return a - b;
    }

    @Override
    public double multiplicar(double a, double b) {
        return a * b;
    }

    @Override
    public double dividir(double a, double b) {
        if (b == 0) {
            System.out.println("Erro: Divisão por zero!");
            return 0;
        }
        return a / b;
    }
}

// 2. Interface Imprimivel
interface Imprimivel {
    void imprimir();
}

// Classe Relatorio
class Relatorio implements Imprimivel {
    String titulo;

    Relatorio(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public void imprimir() {
        System.out.println("Relatório: " + titulo + " (gerando relatório...)");
    }
}

// Classe Contrato
class Contrato implements Imprimivel {
    int numero;

    Contrato(int numero) {
        this.numero = numero;
    }

    @Override
    public void imprimir() {
        System.out.println("Contrato nº " + numero + " (exibindo documento do contrato)");
    }
}

// 3. Interface Autenticavel
interface Autenticavel {
    boolean autenticar(String senha);
}

// Classe Usuario
class Usuario implements Autenticavel {
    String nome;
    String senha;

    Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    @Override
    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }
}

// Classe Administrador
class Administrador extends Usuario {

    Administrador(String nome, String senha) {
        super(nome, senha);
    }

    // Sobrescrita da lógica de autenticação
    @Override
    public boolean autenticar(String senha) {
        return senha.startsWith("ADM") && this.senha.equals(senha);
    }
}

// MAIN PARA TESTES
public class Main {
    public static void main(String[] args) {

        // ======================
        // TESTE 1 – CALCULADORA
        // ======================
        System.out.println("=== TESTE DA CALCULADORA ===");
        Calculadora calc = new Calculadora();
        System.out.println("5 + 3 = " + calc.somar(5, 3));
        System.out.println("5 - 3 = " + calc.subtrair(5, 3));
        System.out.println("5 * 3 = " + calc.multiplicar(5, 3));
        System.out.println("5 / 0 = " + calc.dividir(5, 0));
        System.out.println("10 / 2 = " + calc.dividir(10, 2));

        // ======================
        // TESTE 2 – IMPRIMÍVEL
        // ======================
        System.out.println("\n=== TESTE DE IMPRESSÃO ===");
        java.util.List<Imprimivel> documentos = new java.util.ArrayList<>();
        documentos.add(new Relatorio("Vendas 2024"));
        documentos.add(new Contrato(1025));

        for (Imprimivel doc : documentos) {
            doc.imprimir();
        }

        // ======================
        // TESTE 3 – AUTENTICAÇÃO
        // ======================
        System.out.println("\n=== TESTE DE AUTENTICAÇÃO ===");
        Usuario comum = new Usuario("João", "1234");
        Administrador admin = new Administrador("Maria", "ADM5678");

        System.out.println("Usuário comum autenticando com '1234': " + comum.autenticar("1234"));
        System.out.println("Usuário comum autenticando com '0000': " + comum.autenticar("0000"));

        System.out.println("Administrador autenticando com '5678': " + admin.autenticar("5678"));
        System.out.println("Administrador autenticando com 'ADM5678': " + admin.autenticar("ADM5678"));
    }
}
