package exercicios.exercicios08;

class Notificacao {

    private String destinatario;

    Notificacao(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void enviar(String mensagem) {
        System.out.println("Enviando notificação para " +
                destinatario + ": " + mensagem);
    }
}

class EmailNotificacao extends Notificacao {

    EmailNotificacao(String destinatario) {
        super(destinatario);
    }

    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando E-mail para " +
                getDestinatario() + ": " + mensagem);
    }
}

class SmsNotificacao extends Notificacao {

    SmsNotificacao(String destinatario) {
        super(destinatario);
    }

    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando SMS para o número " +
                getDestinatario() + ": " + mensagem);
    }
}

class PushNotificacao extends Notificacao {

    PushNotificacao(String destinatario) {
        super(destinatario);
    }

    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando Push Notification para o dispositivo " +
                getDestinatario() + ": " + mensagem);
    }
}

public class Main {

    public static void processarEnvio(Notificacao notificacao, String texto) {
        notificacao.enviar(texto);
    }

    public static void main(String[] args) {

        EmailNotificacao email =
                new EmailNotificacao("carlos@gmail.com");

        SmsNotificacao sms =
                new SmsNotificacao("11999999999");

        PushNotificacao push =
                new PushNotificacao("Celular de Carlos");

        processarEnvio(email, "Seu pedido foi enviado.");
        processarEnvio(sms, "Seu código é 1234.");
        processarEnvio(push, "Você recebeu uma nova mensagem.");
    }
}