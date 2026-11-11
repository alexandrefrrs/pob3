package exercicios.exercicios12;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Aluno {

    private String nome;
    private String curso;
    private double notaFinal;

    Aluno(String nome, String curso, double notaFinal) {
        this.nome = nome;
        this.curso = curso;
        this.notaFinal = notaFinal;
    }

    public String getNome() {
        return nome;
    }

    public String getCurso() {
        return curso;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    @Override
    public String toString() {
        return nome + " (nota: " + notaFinal + ")";
    }
}

public class AgrupamentoAlunos {

    public static void main(String[] args) {

        List<Aluno> alunos = Arrays.asList(
                new Aluno("Ana", "Informática", 9.0),
                new Aluno("Carlos", "Administração", 6.0),
                new Aluno("Beatriz", "Informática", 7.0),
                new Aluno("Antonio", "Engenharia", 8.5),
                new Aluno("Amanda", "Administração", 8.0),
                new Aluno("Bernardo", "Engenharia", 5.5)
        );

        Map<String, List<Aluno>> alunosPorCurso = alunos.stream()
                .collect(Collectors.groupingBy(Aluno::getCurso));

        Map<Boolean, List<Aluno>> alunosPorAprovacao = alunos.stream()
                .collect(Collectors.partitioningBy(aluno -> aluno.getNotaFinal() > 7.0));

        System.out.println("--- Alunos por curso ---");
        alunosPorCurso.forEach((curso, lista) -> System.out.println(curso + ": " + lista));

        System.out.println("\nAprovados (nota > 7.0): " + alunosPorAprovacao.get(true));
        System.out.println("Reprovados (nota <= 7.0): " + alunosPorAprovacao.get(false));
    }
}
