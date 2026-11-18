package exercicios.exercicios14;

class ContadorCrescente implements Runnable {

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Crescente: " + i);
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Contagem crescente interrompida.");
        }
    }
}

class ContadorRegressivo implements Runnable {

    @Override
    public void run() {
        try {
            for (int i = 10; i >= 1; i--) {
                System.out.println("Regressivo: " + i);
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Contagem regressiva interrompida.");
        }
    }
}

public class ContagensParalelas {

    public static void main(String[] args) {

        Thread crescente = new Thread(new ContadorCrescente());
        Thread regressivo = new Thread(new ContadorRegressivo());

        crescente.start();
        regressivo.start();

        try {
            crescente.join();
            regressivo.join();
            System.out.println("Contagens finalizadas!");
        } catch (InterruptedException e) {
            crescente.interrupt();
            regressivo.interrupt();
            Thread.currentThread().interrupt();
            System.out.println("Espera pelas contagens interrompida.");
        }
    }
}
