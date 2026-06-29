import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = -1;
        
        while (opcao != 0) {
            try {
                System.out.println("\n=== CLINICA VIDAPLENA ===");
                System.out.println("1 - Pacientes");
                System.out.println("2 - Profissionais");
                System.out.println("3 - Consultas");
                System.out.println("4 - Atendimentos");
                System.out.println("5 - Pagamentos");
                System.out.println("6 - Relatorios");
                System.out.println("7 - Exportacao de Dados");
                System.out.println("0 - Sair");
                System.out.print("Escolha: ");
                
                opcao = Integer.parseInt(sc.nextLine().trim());
                
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
            } catch (NumberFormatException e) {
                System.err.println("Digite um numero valido!");
            } catch (Exception e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }
        sc.close();
    }

    // ========== METODOS DOS MENUS ==========

    private static void menuPacientes() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- PACIENTES ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Complementar cadastro");
            System.out.println("3 - Buscar por CPF");
            System.out.println("4 - Listar todos");
            System.out.println("5 - Desativar");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = Integer.parseInt(sc.nextLine().trim());
            
            switch (op) {
                case 1: System.out.println("Cadastrar paciente - Em desenvolvimento"); break;
                case 2: System.out.println("Complementar cadastro - Em desenvolvimento"); break;
                case 3: System.out.println("Buscar paciente - Em desenvolvimento"); break;
                case 4: System.out.println("Listar pacientes - Em desenvolvimento"); break;
                case 5: System.out.println("Desativar paciente - Em desenvolvimento"); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    private static void menuProfissionais() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- PROFISSIONAIS ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar todos");
            System.out.println("3 - Filtrar por especialidade");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = Integer.parseInt(sc.nextLine().trim());
            
            switch (op) {
                case 1: System.out.println("Cadastrar profissional - Em desenvolvimento"); break;
                case 2: System.out.println("Listar profissionais - Em desenvolvimento"); break;
                case 3: System.out.println("Filtrar profissionais - Em desenvolvimento"); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    private static void menuConsultas() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- CONSULTAS ---");
            System.out.println("1 - Agendar por profissional");
            System.out.println("2 - Agendar por especialidade");
            System.out.println("3 - Cancelar");
            System.out.println("4 - Remarcar");
            System.out.println("5 - Listar todas");
            System.out.println("6 - Buscar por CPF");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = Integer.parseInt(sc.nextLine().trim());
            
            switch (op) {
                case 1: System.out.println("Agendar - Em desenvolvimento"); break;
                case 2: System.out.println("Agendar por especialidade - Em desenvolvimento"); break;
                case 3: System.out.println("Cancelar - Em desenvolvimento"); break;
                case 4: System.out.println("Remarcar - Em desenvolvimento"); break;
                case 5: System.out.println("Listar consultas - Em desenvolvimento"); break;
                case 6: System.out.println("Buscar por CPF - Em desenvolvimento"); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    private static void menuAtendimentos() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- ATENDIMENTOS ---");
            System.out.println("1 - Registrar atendimento");
            System.out.println("2 - Listar atendimentos");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = Integer.parseInt(sc.nextLine().trim());
            
            switch (op) {
                case 1: System.out.println("Registrar atendimento - Em desenvolvimento"); break;
                case 2: System.out.println("Listar atendimentos - Em desenvolvimento"); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    private static void menuPagamentos() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- PAGAMENTOS ---");
            System.out.println("1 - Pagamento em Dinheiro");
            System.out.println("2 - Pagamento em Cartao");
            System.out.println("3 - Pagamento por Convenio");
            System.out.println("4 - Listar pagamentos");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = Integer.parseInt(sc.nextLine().trim());
            
            switch (op) {
                case 1: System.out.println("Pagamento dinheiro - Em desenvolvimento"); break;
                case 2: System.out.println("Pagamento cartao - Em desenvolvimento"); break;
                case 3: System.out.println("Pagamento convenio - Em desenvolvimento"); break;
                case 4: System.out.println("Listar pagamentos - Em desenvolvimento"); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    private static void menuRelatorios() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- RELATORIOS ---");
            System.out.println("1 - Relatorio Unificado");
            System.out.println("2 - Relatorio de Pagamentos");
            System.out.println("3 - Relatorio por Profissional");
            System.out.println("4 - Relatorio por Periodo");
            System.out.println("5 - Resumo Financeiro");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = Integer.parseInt(sc.nextLine().trim());
            
            switch (op) {
                case 1: System.out.println("Relatorio unificado - Em desenvolvimento"); break;
                case 2: System.out.println("Relatorio pagamentos - Em desenvolvimento"); break;
                case 3: System.out.println("Relatorio profissional - Em desenvolvimento"); break;
                case 4: System.out.println("Relatorio periodo - Em desenvolvimento"); break;
                case 5: System.out.println("Resumo financeiro - Em desenvolvimento"); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    private static void menuExportacao() {
        System.out.println("\n--- EXPORTACAO DE DADOS ---");
        System.out.println("Funcionalidade em desenvolvimento...");
    }
}