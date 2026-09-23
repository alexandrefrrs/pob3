package exercicios.exercicios07;

class Retangulo {

    private double largura;
    private double altura;

    Retangulo(double largura, double altura) {
        setLargura(largura);
        setAltura(altura);
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        if (largura > 0) {
            this.largura = largura;
        } else {
            this.largura = 1.0;
            System.out.println("Largura inválida. Valor definido como 1.0.");
        }
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        if (altura > 0) {
            this.altura = altura;
        } else {
            this.altura = 1.0;
            System.out.println("Altura inválida. Valor definido como 1.0.");
        }
    }

    public double calcularArea() {
        return largura * altura;
    }

    public double calcularPerimetro() {
        return 2 * (largura + altura);
    }
}

public class Main {
    public static void main(String[] args) {

        Retangulo retangulo = new Retangulo(5, 10);

        System.out.println("Largura: " + retangulo.getLargura());
        System.out.println("Altura: " + retangulo.getAltura());

        System.out.println("Área: " + retangulo.calcularArea());
        System.out.println("Perímetro: " +
                retangulo.calcularPerimetro());

        retangulo.setLargura(-5);

        System.out.println();

        System.out.println("Nova largura: " +
                retangulo.getLargura());

        System.out.println("Nova área: " +
                retangulo.calcularArea());
    }
}