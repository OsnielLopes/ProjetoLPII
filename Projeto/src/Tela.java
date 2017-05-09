import javax.swing.*;
public class Tela {
    public static OpcEntrada opcLeitura(){
        Object[] opcoes = { "Escrever Times Manualmente", "Importar Times de um arquivo" };
        
        JPanel panel = new JPanel();
        panel.add(new JLabel("Escolha uma das opções : "));
        
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
            quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de times.\nMinimo: 10 | Maximo: 30 "));
        }while(quantidade < 10 || quantidade > 30);
        return quantidade;
    }
    
    public static String[] digitaTime(){
        Object[] opcoes = { "OK", "Cancelar" }; 
        JPanel
    }
}
