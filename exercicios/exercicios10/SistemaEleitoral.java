package exercicios.exercicios10;

class IdadeInvalidaException extends RuntimeException {

    IdadeInvalidaException(String mensagem) {
        super(mensagem);
    }
}

class Eleitor {

    public void cadastrar(String nome, int idade) {
        if (idade < 0 || idade > 130) {
            throw new IdadeInvalidaException("A idade " + idade + " é inválida para cadastro.");
        }
        
        System.out.println("Eleitor " + nome + " cadastrado com sucesso! (Idade: " + idade + ")");
    }
}

public class SistemaEleitoral {

    public static void main(String[] args) {
        
        Eleitor eleitor = new Eleitor();
        
        try {
            eleitor.cadastrar("Carlos", 30);
            
            System.out.println("\nTentando cadastrar com idade negativa...");
            eleitor.cadastrar("Mariana", -5);
            
        } catch (IdadeInvalidaException e) {
            System.out.println("Falha no cadastro: " + e.getMessage());
        }
        
        try {
            System.out.println("\nTentando cadastrar com idade acima do limite...");
            eleitor.cadastrar("José", 150);
            
        } catch (IdadeInvalidaException e) {
            System.out.println("Falha no cadastro: " + e.getMessage());
        }
    }
}