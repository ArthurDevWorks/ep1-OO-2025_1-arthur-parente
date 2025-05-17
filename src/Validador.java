public class Validador {
    public static boolean emailValido(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    public static boolean notaValida(double nota) {
        return nota >= 0 && nota <= 10;
    }

    public static boolean presencaValida(double presenca) {
        return presenca >= 0 && presenca <= 100;
    }

    public static boolean semestreValido(int semestre) {
        return semestre >= 1 && semestre <= 10;
    }

    public static boolean cargaHorariaValida(int horas) {
        return horas > 0;
    }

    public static boolean vagasValidas(int vagas) {
        return vagas > 0;
    }
}