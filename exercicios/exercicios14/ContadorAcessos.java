package exercicios.exercicios14;

import java.util.concurrent.CountDownLatch;

public class ContadorAcessos {

    private int acessos;

    public void incrementarSemSincronizacao() {
        int valorAtual = acessos;
        // Amplia a chance de outra thread ler o mesmo valor antes da escrita.
        Thread.yield();
        acessos = valorAtual + 1;
    }

    public synchronized void incrementarSincronizado() {
        acessos++;
    }

    public int getAcessos() {
        return acessos;
    }

    public static void executarThreads(Runnable incremento) throws InterruptedException {
        Thread[] threads = new Thread[5];
        CountDownLatch inicio = new CountDownLatch(1);

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                try {
                    inicio.await();

                    for (int j = 0; j < 1000; j++) {
                        incremento.run();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            threads[i].start();
        }

        inicio.countDown();

        for (Thread thread : threads) {
            thread.join();
        }
    }

    public static void main(String[] args) {

        ContadorAcessos semSincronizacao = new ContadorAcessos();
        ContadorAcessos sincronizado = new ContadorAcessos();

        try {
            executarThreads(semSincronizacao::incrementarSemSincronizacao);
            System.out.println("Sem sincronização: " + semSincronizacao.getAcessos());
            System.out.println("O resultado pode variar; atingir 5000 por acaso não elimina a condição de corrida.");

            executarThreads(sincronizado::incrementarSincronizado);
            System.out.println("Com sincronização: " + sincronizado.getAcessos());
            System.out.println("Total esperado atingido? " + (sincronizado.getAcessos() == 5000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Espera pelas threads interrompida.");
        }
    }
}
