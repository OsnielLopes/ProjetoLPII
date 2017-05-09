import javax.swing.*;
public class Tela {
    public static OpcEntrada opcLeitura(){
        Object[] opcoes1 = { "Escrever Times Manualmente", "Importar Times de um arquivo" };
        
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("Escolha uma das opções : "));
        
        int result = JOptionPane.showOptionDialog(null, panel1, "Tabela de Jogos", 
                     JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes1, null);
        if (result == JOptionPane.YES_OPTION){
            return OpcEntrada.Console;
        }else{
            return OpcEntrada.Arquivo;
        }
    }
    
    public static int qtdTimes(){
        int quantidade = 0;
        do{
            quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de times.\nMinimo: 10 | Maximo: 30 "));
        }while(quantidade < 10 || quantidade > 30);
        return quantidade;
    }
    
    // A tela de interação para pedir: nome - cidade - estado para o usuario
    public static String[] digitaTime(){
        Object[] opcoes2 = { "Enviar", "Cancelar" }; 
        // NOME
        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("Nome : "));
        JTextField textFieldNome = new JTextField(10);
        panel2.add(textFieldNome);
        // CIDADE
        panel2.add(new JLabel("Cidade : "));
        JTextField textFieldCidade = new JTextField(10);
        panel2.add(textFieldCidade);
        // ESTADO
        panel2.add(new JLabel("Estado : "));
        JTextField textFieldEstado = new JTextField(10);
        panel2.add(textFieldEstado);
        
        // ARRUMAR O RETORNO DESSE PONTO !!!
        int result = JOptionPane.showOptionDialog(null, panel2, "Tabela de Jogos", 
                     JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes2, null);
        if (result == JOptionPane.YES_OPTION){
            return null;
        }else{
            return null;
        }
    }
}
