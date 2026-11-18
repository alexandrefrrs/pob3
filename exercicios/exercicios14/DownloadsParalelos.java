package exercicios.exercicios14;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

class TarefaDownload implements Callable<String> {

    private int id;

    TarefaDownload(int id) {
        this.id = id;
    }

    @Override
    public String call() throws InterruptedException {
        Thread.sleep(ThreadLocalRandom.current().nextInt(500, 1501));
        return "Arquivo " + id + " processado pela thread: " + Thread.currentThread().getName();
    }
}

public class DownloadsParalelos {

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(3);
        List<Future<String>> resultados = new ArrayList<>();

        try {
            for (int i = 1; i <= 6; i++) {
                resultados.add(pool.submit(new TarefaDownload(i)));
            }

            for (Future<String> resultado : resultados) {
                System.out.println(resultado.get());
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
            System.out.println("Processamento dos downloads interrompido.");
        } catch (ExecutionException e) {
            System.out.println("Erro ao processar download: " + e.getCause().getMessage());
        } finally {
            pool.shutdown();
        }
    }
}
