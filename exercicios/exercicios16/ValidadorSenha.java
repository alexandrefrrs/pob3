package exercicios.exercicios16;

public class ValidadorSenha {

    public boolean isForte(String senha) {
        if (senha == null || senha.length() < 8) {
            return false;
        }

        boolean temNumero = senha.chars().anyMatch(Character::isDigit);
        boolean temMaiuscula = senha.chars().anyMatch(Character::isUpperCase);
        return temNumero && temMaiuscula;
    }
}
