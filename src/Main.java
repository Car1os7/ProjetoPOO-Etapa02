// ARQUIVO: src/Main.java

import java.util.*;

/**
 * CLASSE MAIN
 * 
 * Conceitos aplicados:
 * - SEPARACAO DE RESPONSABILIDADES: apenas menus e entrada/saida
 * - TRATAMENTO DE EXCEÇÕES: try/catch/finally em toda entrada de dados
 * - LIGACAO DINAMICA: demonstrada nos relatorios
 * - POLIMORFISMO: uso de List<Pessoa> e List<Pagamento>
 * 
 * Responsabilidades da Main (segundo o roteiro):
 * - menus
 * - entrada de dados
 * - exibicao de resultados
 * 
 * A logica de negocio esta em ClinicaServico
 */
public class Main {
    
    private static ClinicaServico servico = new ClinicaServico();
    private static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        int opcao = -1;
        
        // Dados iniciais para teste
        inicializarDadosTeste();
        
        while (opcao != 0) {
            try {
                System.out.println("\n=== CLINICA VIDAPLENA ===");
                System.out.println("1 - Pacientes");
                System.out.println("2 - Profissionais");
                System.out.println("3 - Consultas");
                System.out.println("4 - Atendimentos");
                System.out.println("5 - Pagamentos");
                System.out.println("6 - Relatorios");
                System.out.println("7 - Exportacao de Dados (Jornada 26)");
                System.out.println("0 - Sair");
                System.out.print("Escolha: ");
                
                opcao = lerInteiro();
                
                switch (opcao) {
                    case 1: menuPacientes(); break;
                    case 2: menuProfissionais(); break;
                    case 3: menuConsultas(); break;
                    case 4: menuAtendimentos(); break;
                    case 5: menuPagamentos(); break;
                    case 6: menuRelatorios(); break;
                    case 7: menuExportacao(); break;
                    case 0: 
                        System.out.println("Sistema encerrado.");
                        break;
                    default: 
                        System.out.println("Opcao invalida!"); 
                        break;
                }
            } catch (Exception e) {
                System.err.println("Erro inesperado: " + e.getMessage());
                System.out.println("Tente novamente.");
            } finally {
                System.out.println("--- Operacao finalizada ---");
            }
        }
        sc.close();
    }
    
    // ========== METODO AUXILIAR PARA LEITURA SEGURA ==========
    // TRATAMENTO DE EXCEÇÕES: NumberFormatException capturada
    
    private static int lerInteiro() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.err.println("Entrada invalida! Digite um numero inteiro.");
                System.out.print("Digite novamente: ");
            }
        }
    }
    
    private static double lerDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.err.println("Entrada invalida! Digite um numero valido.");
                System.out.print("Digite novamente: ");
            }
        }
    }
    
    private static String lerString() {
        return sc.nextLine().trim();
    }
    
    private static String lerStringNaoVazia() {
        while (true) {
            String valor = lerString();
            if (!valor.isEmpty()) {
                return valor;
            }
            System.err.println("Este campo nao pode ser vazio!");
            System.out.print("Digite novamente: ");
        }
    }
    
    // ========== DADOS INICIAIS PARA TESTE ==========
    
    private static void inicializarDadosTeste() {
        try {
            // Criando convenios
            Convenio saudePlus = new Convenio("SaudePlus", 0.40);
            saudePlus.adicionarEspecialidade("clinica geral");
            saudePlus.adicionarEspecialidade("fisioterapia");
            
            Convenio vidaMais = new Convenio("VidaMais", 0.30);
            vidaMais.adicionarEspecialidade("clinica geral");
            vidaMais.adicionarEspecialidade("psicologia");
            
            Convenio bemEstar = new Convenio("BemEstar", 0.50);
            bemEstar.adicionarEspecialidade("clinica geral");
            bemEstar.adicionarEspecialidade("fisioterapia");
            bemEstar.adicionarEspecialidade("nutricao");
            
            // Pacientes
            Paciente p1 = new Paciente("Joao Silva", "12345678901", 35, "(11) 99999-1111", saudePlus);
            Paciente p2 = new Paciente("Maria Santos", "98765432100", 28, "(11) 99999-2222", vidaMais);
            Paciente p3 = new Paciente("Pedro Costa", "11122233344", 42, "(11) 99999-3333", bemEstar);
            Paciente p4 = new Paciente("Ana Oliveira", "55566677788", 25, "(11) 99999-4444", "Nenhum");
            
            servico.cadastrarPaciente(p1);
            servico.cadastrarPaciente(p2);
            servico.cadastrarPaciente(p3);
            servico.cadastrarPaciente(p4);
            
            // Horarios
            HorarioDisponivel h1 = new HorarioDisponivel("segunda", "08:00");
            HorarioDisponivel h2 = new HorarioDisponivel("segunda", "09:00");
            HorarioDisponivel h3 = new HorarioDisponivel("terca", "08:00");
            HorarioDisponivel h4 = new HorarioDisponivel("quarta", "10:00");
            HorarioDisponivel h5 = new HorarioDisponivel("quinta", "14:00");
            HorarioDisponivel h6 = new HorarioDisponivel("sexta", "08:00");
            
            List<HorarioDisponivel> horarios = Arrays.asList(h1, h2, h3, h4, h5, h6);
            
            // Profissionais
            ClinicoGeral drCarlos = new ClinicoGeral(
                "Dr. Carlos", "11122233344", "CRM-12345", 200.00
            );
            drCarlos.setHorarios(new ArrayList<>(horarios));
            
            Fisioterapeuta draAna = new Fisioterapeuta(
                "Dra. Ana", "22233344455", "CREFITO-67890", 150.00, 12
            );
            draAna.setHorarios(new ArrayList<>(Arrays.asList(h1, h3, h5)));
            
            Psicologo drPaulo = new Psicologo(
                "Dr. Paulo", "33344455566", "CRP-09876", 180.00, "TCC"
            );
            drPaulo.setHorarios(new ArrayList<>(Arrays.asList(h2, h4, h6)));
            
            Nutricionista draMariana = new Nutricionista(
                "Dra. Mariana", "44455566677", "CRN-54321", 160.00, "Reeducacao alimentar"
            );
            draMariana.setHorarios(new ArrayList<>(Arrays.asList(h1, h4)));
            
            servico.cadastrarProfissional(drCarlos);
            servico.cadastrarProfissional(draAna);
            servico.cadastrarProfissional(drPaulo);
            servico.cadastrarProfissional(draMariana);
            
            System.out.println("=== DADOS DE TESTE INICIALIZADOS COM SUCESSO! ===");
            System.out.println("4 pacientes e 4 profissionais cadastrados.");
            
        } catch (Exception e) {
            System.err.println("Erro ao inicializar dados de teste: " + e.getMessage());
        }
    }
    
    // ========== METODOS DOS MENUS ==========
    // (continuacao do codigo - todos os metodos que voce ja tem)
    // ...
    
    // ========== METODO AUXILIAR ==========
    
    private static String descobrirDiaSemana(String data) {
        try {
            int dia = Integer.parseInt(data.substring(0, 2));
            int mes = Integer.parseInt(data.substring(3, 5));
            int ano = Integer.parseInt(data.substring(6, 10));
            
            if (mes < 3) {
                mes += 12;
                ano--;
            }
            
            int k = ano % 100;
            int j = ano / 100;
            int resultado = (dia + (13 * (mes + 1)) / 5 + k + k/4 + j/4 - 2*j) % 7;
            if (resultado < 0) resultado += 7;
            
            String[] nomes = {"sabado", "domingo", "segunda", "terca", "quarta", "quinta", "sexta"};
            return nomes[resultado];
            
        } catch (Exception e) {
            return "segunda";
        }
    }
}

