package exercicios.exercicios09;

import java.util.ArrayList;
import java.util.List;

interface Tributavel {

    double calcularTributo();
}

abstract class Item {

    private int codigo;
    private double precoBase;

    Item(int codigo, double precoBase) {
        this.codigo = codigo;
        this.precoBase = precoBase;
    }

    public int getCodigo() {
        return codigo;
    }

    public double getPrecoBase() {
        return precoBase;
    }
}

class Eletronico extends Item implements Tributavel {

    Eletronico(int codigo, double precoBase) {
        super(codigo, precoBase);
    }

    @Override
    public double calcularTributo() {
        return getPrecoBase() * 0.15;
    }
}

class Alimento extends Item {

    Alimento(int codigo, double precoBase) {
        super(codigo, precoBase);
    }
}

public class Main {

    public static double calcularTotalImpostos(
            List<Tributavel> itensTributaveis) {

        double total = 0;

        for (Tributavel item : itensTributaveis) {
            total += item.calcularTributo();
        }

        return total;
    }

    public static void main(String[] args) {

        Eletronico celular = new Eletronico(1, 2000);
        Eletronico notebook = new Eletronico(2, 4000);
        Alimento arroz = new Alimento(3, 30);

        List<Item> itens = new ArrayList<>();

        itens.add(celular);
        itens.add(notebook);
        itens.add(arroz);

        List<Tributavel> itensTributaveis = new ArrayList<>();

        for (Item item : itens) {

            if (item instanceof Tributavel) {
                itensTributaveis.add((Tributavel) item);
            }
        }

        System.out.println("Tributo do celular: R$ " +
                celular.calcularTributo());

        System.out.println("Tributo do notebook: R$ " +
                notebook.calcularTributo());

        System.out.println();

        System.out.println("Total de impostos: R$ " +
                calcularTotalImpostos(itensTributaveis));
    }
}