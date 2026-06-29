

/**
 * CLASSE HORARIO DISPONIVEL
 * 
 * Conceitos aplicados:
 * - AGREGAÇÃO: HorarioDisponivel existe independentemente do Profissional
 * - ENCAPSULAMENTO: atributos privados
 * 
 * Um mesmo horário pode ser reutilizado por diferentes profissionais
 */
public class HorarioDisponivel {
    
    private String diaSemana;
    private String horario;
    private boolean disponivel;
    private String turno; // manhã, tarde, noite
    
    // ========== CONSTRUTORES ==========
    
    public HorarioDisponivel(String diaSemana, String horario) {
        this.diaSemana = diaSemana;
        this.horario = horario;
        this.disponivel = true;
        this.turno = determinarTurno(horario);
    }
    
    public HorarioDisponivel(String diaSemana, String horario, boolean disponivel) {
        this(diaSemana, horario);
        this.disponivel = disponivel;
    }
    
    // ========== GETTERS E SETTERS ==========
    
    public String getDiaSemana() { return diaSemana; }
    public String getHorario() { return horario; }
    public boolean isDisponivel() { return disponivel; }
    public String getTurno() { return turno; }
    
    public void setDiaSemana(String diaSemana) {
        // VALIDAÇÃO: dia da semana válido
        String[] diasValidos = {"segunda", "terca", "quarta", "quinta", "sexta", "sabado", "domingo"};
        for (String d : diasValidos) {
            if (d.equalsIgnoreCase(diaSemana)) {
                this.diaSemana = d.toLowerCase();
                return;
            }
        }
        throw new IllegalArgumentException("Dia da semana inválido!");
    }
    
    public void setHorario(String horario) {
        if (horario != null && horario.matches("\\d{2}:\\d{2}")) {
            this.horario = horario;
            this.turno = determinarTurno(horario);
        } else {
            throw new IllegalArgumentException("Horário deve estar no formato HH:MM!");
        }
    }
    
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    
    // ========== MÉTODOS ==========
    
    private String determinarTurno(String horario) {
        if (horario == null) return "indefinido";
        int hora = Integer.parseInt(horario.substring(0, 2));
        if (hora >= 6 && hora < 12) return "manhã";
        if (hora >= 12 && hora < 18) return "tarde";
        return "noite";
    }
    
    public void ocupar() {
        this.disponivel = false;
    }
    
    public void liberar() {
        this.disponivel = true;
    }
    
    @Override
    public String toString() {
        return diaSemana + " " + horario + " (" + turno + ") - " + 
               (disponivel ? "DISPONÍVEL" : "OCUPADO");
    }
}

