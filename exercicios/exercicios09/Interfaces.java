package exercicios.exercicios09;

interface Autenticavel {

    boolean autenticar(String senha);
}

interface ExportavelJSON {

    String exportarJSON();
}

class Usuario implements Autenticavel {

    private String login;
    private String senha;

    Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    @Override
    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }
}

class Administrador implements Autenticavel, ExportavelJSON {

    private String login;
    private String senha;
    private int nivelAcesso;

    Administrador(String login, String senha, int nivelAcesso) {
        this.login = login;
        this.senha = senha;
        this.nivelAcesso = nivelAcesso;
    }

    @Override
    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }

    @Override
    public String exportarJSON() {
        return "{\"login\":\"" + login +
                "\",\"nivelAcesso\":" + nivelAcesso + "}";
    }
}

public class Main {
    public static void main(String[] args) {

        Usuario usuario = new Usuario("Carlos", "1234");

        Administrador administrador =
                new Administrador("Ana", "admin123", 3);

        System.out.println("Autenticação do usuário: " +
                usuario.autenticar("1234"));

        System.out.println("Autenticação do administrador: " +
                administrador.autenticar("admin123"));

        System.out.println();

        System.out.println("Dados do administrador em JSON:");

        System.out.println(administrador.exportarJSON());
    }
}