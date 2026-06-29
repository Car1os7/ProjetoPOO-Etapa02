package models;

public interface Agendavel {
    void agendar();
    String cancelar();
    String cancelar(String motivo);
    String remarcar(String novaData, String novoHorario);
}
