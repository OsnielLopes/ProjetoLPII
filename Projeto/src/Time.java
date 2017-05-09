public class Time {
    private String nome;
    private String cidade;
    private String estado;
    
    public Time(String nome, String cidade, String estado){
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
    }
    public String getNome(){
       return this.nome;
    }
    //Arrumar depois ...
    public static String getNome(String cidade){
        return null;
    }
    public String getCidade(){
        return this.cidade;
    }
    public String getEstado(){
        return this.estado;
    }
    
}
