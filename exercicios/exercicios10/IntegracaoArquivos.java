package exercicios.exercicios10;

import java.io.IOException;

class ProcessamentoDadosException extends Exception {

    ProcessamentoDadosException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

class ServicoProcessamento {

    public void processarArquivo(String caminho) throws ProcessamentoDadosException {
        try {
            if (caminho == null || caminho.trim().isEmpty()) {
                throw new IOException("O caminho do arquivo não pode ser vazio ou nulo.");
            }
            
            // Simulação de erro de parsing caso o arquivo seja inválido
            if (caminho.equals("arquivo_corrompido.txt")) {
                throw new RuntimeException("Erro de formatação interna: parsing falhou.");
            }
            
            System.out.println("Arquivo '" + caminho + "' processado com sucesso.");
            
        } catch (IOException | RuntimeException e) {
            throw new ProcessamentoDadosException("Ocorreu uma falha na camada de serviço ao processar os dados.", e);
        }
    }
}

public class IntegracaoArquivos {

    public static void main(String[] args) {
        
        ServicoProcessamento servico = new ServicoProcessamento();
        
        System.out.println("--- Teste 1: Caminho Vazio ---");
        try {
            servico.processarArquivo("");
        } catch (ProcessamentoDadosException e) {
            System.out.println("Mensagem Principal: " + e.getMessage());
            System.out.println("Motivo Raiz (Cause): " + e.getCause().getMessage());
        }

        System.out.println("\n--- Teste 2: Falha de Parsing ---");
        try {
            servico.processarArquivo("arquivo_corrompido.txt");
        } catch (ProcessamentoDadosException e) {
            System.out.println("Mensagem Principal: " + e.getMessage());
            System.out.println("Motivo Raiz (Cause): " + e.getCause().getMessage());
        }
    }
}