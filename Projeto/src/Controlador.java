import java.io.*;

public class Controlador {
    public static void main(String[] args) throws IOException{
        OpcEntrada opc = Tela.opcLeitura();
        Time[] times;
        if (opc.equals(OpcEntrada.Console)){
            times = leitura();
        }else if (opc.equals(OpcEntrada.Arquivo)){
            String[] infoLeituraArquivo = Tela.getInfoLeituraArquivo();
            times = leitura(infoLeituraArquivo[0],infoLeituraArquivo[1]);
        }    
    }
    //Instanciar Times Manualmente
    private static Time[] leitura(){
        int qtdTimes = Tela.qtdTimes();
            Time times[] = new Time[qtdTimes];
            int i = 0;
            do{
                String time[] = Tela.digitaTime();
                if (time != null){
                    times[i] = new Time(time[0], time[1], time[2]);
                    i+=1;
                }
            }while (i < qtdTimes);
        return times;
    }
    //Instanciando times a partir de um arquivo
    private static Time[] leitura(String filename, String separador) throws FileNotFoundException, IOException{
        FileReader fr = new FileReader(filename);
        BufferedReader in = new BufferedReader(fr);
        int qtdTimes = Integer.parseInt(in.readLine());
        Time times[] = new Time[qtdTimes];
        for (int i = 0; i < qtdTimes; i++){
            String linha[] = in.readLine().split(separador);
            times[i] = new Time(linha[0], linha[1], linha[2]);
        }
        fr.close();
        return times;
    }
}
