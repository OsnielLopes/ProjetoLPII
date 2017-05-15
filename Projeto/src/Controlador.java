import java.io.*;

public class Controlador {
    public static void main(String[] args) throws IOException{
        OpcEntrada opc = Tela.opcLeitura();
        if (opc.equals(OpcEntrada.Console)){
            int qtdTimes = Tela.qtdTimes();
            Time times[] = new Time[qtdTimes];
            for(int i = 0; i < qtdTimes; i++){
                String time[] = Tela.digitaTime();
                times[i] = new Time(time[0], time[1], time[2]);
            }
        }else if (opc.equals(OpcEntrada.Arquivo)){
            //Exibir Tela para a pessoa escrever o nome do arquivo
            //Exibir tela para a pessoa colocar o separador
           // leitura(filename, separador);
        }
        
        
        
    }
    //Instanciando times a partir de um arquivo
    private void leitura(String filename, String separador) throws FileNotFoundException, IOException{
        FileReader fr = new FileReader(filename);
        BufferedReader in = new BufferedReader(fr);
        int qtdTimes = Integer.parseInt(in.readLine());
        Time times[] = new Time[qtdTimes];
        for (int i = 0; i < qtdTimes; i++){
            String linha[] = in.readLine().split(separador);
            times[i] = new Time(linha[0], linha[1], linha[2]);
        }
        fr.close();
    }
}
