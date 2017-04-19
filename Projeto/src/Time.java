public class Time {
    private String nome;
    private String local;
    
    public Time(String nome, String local){
        this.nome = nome;
        this.local = local;
    }
    public String getNome(){
       return this.nome;
    }
    //Verificar melhor local para implementar o método abaixo
    public static String getNome(String local){
        return null;
    }
    public String getLocal(){
        return this.local;
    }
    
}
