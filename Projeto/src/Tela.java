import java.util.Calendar;
import javax.swing.*;
public class Tela {
    public static OpcEntrada opcLeitura(){
        Object[] opcoes = { "Escrever Times Manualmente", "Importar Times de um arquivo" };
        
        JPanel panel = new JPanel();
        panel.add(new JLabel("Escolha uma das opções! "));
        
        int result = JOptionPane.showOptionDialog(null, panel, "Tabela de Jogos", 
                     JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, null);
        if (result == JOptionPane.YES_OPTION){
            return OpcEntrada.Console;
        }else{
            return OpcEntrada.Arquivo;
        }
    }
    
    public static int qtdTimes(){
        int quantidade = 0;
        do{
            quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de times.\nMinimo: 1 | Maximo: 30 "));
        }while(quantidade < 1 || quantidade > 30);
        return quantidade;
    }
    
    public static String[] getInfoLeituraArquivo(){
        Object[] opcoes = { "OK", "Cancelar" };
        
        JPanel panel = new JPanel();
        
        JLabel labelNomeArquivo = new JLabel("Nome do arquivo: ");
        panel.add(labelNomeArquivo);
        JTextField textFieldNomeArquivo= new JTextField(10);
        panel.add(textFieldNomeArquivo);
        
        JLabel labelSeparador = new JLabel("Separador das informações do time: ");
        panel.add(labelSeparador);
        JTextField textFieldSeparador = new JTextField(10);
        panel.add(textFieldSeparador);

        int result = JOptionPane.showOptionDialog(null, panel, "Tabela de Jogos", 
                     JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, null);
        if (result == JOptionPane.YES_OPTION){
            String arquivo = textFieldNomeArquivo.getText();
            String separador = textFieldSeparador.getText();
            if (arquivo.isEmpty() || arquivo.equals(" ") ||
                    separador.isEmpty() || separador.equals(" ")){
                return null;
            }
            return new String[] {arquivo,separador};
        }else{
            return null;
        }
    }
    
      // A tela de interação para pedir: nome - cidade - estado para o usuario
    public static String[] digitaTime(){
        Object[] opcoes = { "OK", "Cancelar" };
        
        JPanel panel = new JPanel();
        
        JLabel labelTime = new JLabel("Time: ");
        panel.add(labelTime);
        JTextField textFieldNome = new JTextField(10);
        panel.add(textFieldNome);
        
        JLabel labelCidade = new JLabel("Cidade: ");
        panel.add(labelCidade);
        JTextField textFieldCidade = new JTextField(10);
        panel.add(textFieldCidade);
        
        JLabel labelEstado = new JLabel("Estado: ");
        panel.add(labelEstado);
        JTextField textFieldEstado = new JTextField(10);
        panel.add(textFieldEstado);

        int result = JOptionPane.showOptionDialog(null, panel, "Tabela de Jogos", 
                     JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, null);
        if (result == JOptionPane.YES_OPTION){
            String nome = textFieldNome.getText();
            String cidade = textFieldCidade.getText();
            String estado = textFieldEstado.getText();
            if (nome.isEmpty() || nome.equals(" ") ||
                    cidade.isEmpty() || cidade.equals(" ") ||
                    estado.isEmpty() || estado.equals(" ")){
                return null;
            }
            return new String[] {nome,cidade,estado};
        }else{
            return null;
        }
    }

    //Implementar método que exibe uma tela para a pessoa colocar a data inicial dos jogos
    public static Calendar inicioDosJogos() {
        Object opcoes[] = {"OK", "Cancelar"};
        JPanel panel = new JPanel();
        
        JLabel labelAno = new JLabel("Ano: ");
        panel.add(labelAno);
        JTextField textFieldAno = new JTextField();
        panel.add(textFieldAno);
        
        JLabel labelMes = new JLabel("Mês: ");
        panel.add(labelMes);
        JTextField textFieldMes = new JTextField();
        panel.add(textFieldMes);
        
        JLabel labelDia = new JLabel("Dia: ");
        panel.add(labelDia);
        JTextField textFieldDia = new JTextField();
        panel.add(textFieldDia);
        
        int result = JOptionPane.showOptionDialog(null, panel, "Tabela de Jogos", 
                     JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, null);
        if (result == JOptionPane.YES_OPTION){
            int ano = Integer.parseInt(textFieldAno.getText());
            int mes = Integer.parseInt(textFieldMes.getText());
            int dia = Integer.parseInt(textFieldDia.getText());
            if (ano <= 0){
                return inicioDosJogos();
            }else if(mes <= 0 || mes > 12){
                return inicioDosJogos();
            }else if(dia <= 0 || dia > 31){
            return inicioDosJogos();
        }else{
            Calendar c = Calendar.getInstance();
            c.set(ano, mes, dia);
            return c;
        }
    }

    //Implementar uma tela onde o usuario pode escolher entre 3 botoes: pesquisar, gerar arquivo e sair
    
    public static OpcMenu menu(){
        Object[] opcoes = {"Pesquisar", "Gerar Arquivo", "Sair"};
        
        JPanel panel = new JPanel();
        panel.add(new JLabel("Escolha uma das opções! "));
        
        int result = JOptionPane.showOptionDialog(null, panel, "Tabela de Jogos", 
                     JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, null);
        
        switch(result){
            case JOptionPane.YES_OPTION:
                return OpcMenu.pesquisar;
            case JOptionPane.NO_OPTION:
                return OpcMenu.gravarArquivo;
            case JOptionPane.CANCEL_OPTION:
                return OpcMenu.sair;
            default:
                JOptionPane.showMessageDialog(null, "Impossivel realizar sessão");
        }
        return null;
    }

    //Implementar uma tela onde o usuario pode escolher entre as seguintes pesquisas:
    //jogos de um determinado time; jogos que ocorrerâo em uma determinada cidade;
    //jogos que ocorrerâo em um determinado estado; (d) jogos que ocorrerâo em um determinado mês.
    public static Filtro escolheFormaPesquisa() {
        JPanel panel = new JPanel();
        String[] filtro = {"Jogos de um time", "Jogos de uma cidade", "Jogos de um estado", "Jogos de um mês"};
        JComboBox comboBoxFiltro = new JComboBox(filtro);
        panel.add(comboBoxFiltro);
        JOptionPane.showMessageDialog(null, panel);
        int indexFiltro = comboBoxFiltro.getSelectedIndex();
        if(indexFiltro == 0){
            return Filtro.time;
        }else if(indexFiltro == 1){
            return Filtro.cidade;
        }else if(indexFiltro == 2){
            return Filtro.estado;
        }else{
            return Filtro.mes;
        }
    }

    //exibir um ComboBox com os nomes dos times, retornar indice escolhido
    public static int escolhaTime(Time[] times) {
        JPanel panel = new JPanel();
        String[] nomeTimes = new String[times.length];
        for(int i = 0; i < times.length; i++){
            nomeTimes[i] = times[i].getNome();
        }
        JComboBox comboBoxTimes = new JComboBox(nomeTimes);
        panel.add(comboBoxTimes);
        JOptionPane.showMessageDialog(null, panel);
        int indexTimes = comboBoxTimes.getSelectedIndex();
        return indexTimes;
    }

    //permitir que a pessoa digite o nome da cidade
    public static String leCidade() {
        Object opcoes[] = {"OK", "Cancelar"};
        JPanel panel = new JPanel();
        
        JLabel labelCidade = new JLabel("Digite a cidade escolhida: ");
        panel.add(labelCidade);
        JTextField textFieldCidade = new JTextField(10);
        panel.add(textFieldCidade);
        
        int result = JOptionPane.showOptionDialog(null, panel, "Tabela de Jogos", 
                     JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, null);
        if(result == JOptionPane.YES_OPTION){
            String cidade = textFieldCidade.getText();
            if (cidade.isEmpty() || cidade.equals(" ")){
                return leCidade();
            }
            return cidade;
        }
        return leCidade();
    }

    //permitir que a pessoa digite o nome do estado
    public static String leEstado() {
        Object opcoes[] = {"OK", "Cancelar"};
        JPanel panel = new JPanel();
        
        JLabel labelEstado = new JLabel("Digite o estado escolhido: ");
        panel.add(labelEstado);
        JTextField textFieldEstado = new JTextField(10);
        panel.add(textFieldEstado);
        
        int result = JOptionPane.showOptionDialog(null, panel, "Tabela de Jogos", 
                     JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, null);
        if(result == JOptionPane.YES_OPTION){
            String estado = textFieldEstado.getText();
            if (estado.isEmpty() || estado.equals(" ")){
                return leEstado();
            }
            return estado;
        }
        return leEstado();
    }

    //exibir combobox com os meses do ano, retornar o numero do mes
    public static int escolheMes() {
        JPanel panel = new JPanel();
        String[] mes = {"Janeiro", "Fevereiro", "Março", "Abril","Maio","Junho","Julho",
                           "Agosto","Setembro","Outubro","Novembro","Dezembro"};
        JComboBox comboBoxMes = new JComboBox(mes);
        panel.add(comboBoxMes);
        JOptionPane.showMessageDialog(null, panel);
        int indexMes = comboBoxMes.getSelectedIndex();
        if(indexMes == 0){
            return indexMes + 1;
        }else if(indexMes == 1){
            return indexMes + 1;
        }else if(indexMes == 2){
            return indexMes + 1;
        }else if(indexMes == 3){
            return indexMes + 1;
        }else if(indexMes == 4){
            return indexMes + 1;
        }else if(indexMes == 5){
            return indexMes + 1;
        }else if(indexMes == 6){
            return indexMes + 1;
        }else if(indexMes == 7){
            return indexMes + 1;
        }else if(indexMes == 8){
            return indexMes + 1;
        }else if(indexMes == 9){
            return indexMes + 1;
        }else if(indexMes == 10){
            return indexMes + 1;
        }else if(indexMes == 11){
            return indexMes + 1;
        }
        return escolheMes();
    }

    //exibir resultado da pesquisa
    public static void exibeResultado(String resposta) {
        JOptionPane.showMessageDialog(null, resposta);
    }

    //exibe uma tela para que o usuário escreva o nome do arquivo que será gravado 
    public static String informaNomeArquivo() {
        Object opcoes[] = {"OK", "Cancelar"};
        JPanel panel = new JPanel();
        
        JLabel labelArquivo = new JLabel("Digite o nome do arquivo: ");
        panel.add(labelArquivo);
        JTextField textFieldArquivo = new JTextField();
        panel.add(textFieldArquivo);
        
        int result = JOptionPane.showOptionDialog(null, panel, "Tabela de Jogos", 
                     JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, null);
        if(result == JOptionPane.YES_OPTION){
            String arquivo = textFieldArquivo.getText();
            if (arquivo.isEmpty() || arquivo.equals(" ")){
                return informaNomeArquivo();
            }
            return arquivo;
        }
        return informaNomeArquivo();
    }

}