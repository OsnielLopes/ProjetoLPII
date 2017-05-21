
/**
 *
 * @author osniellopesteixeira
 */
public class Local {

    private String cidade;
    private String estado;

    public Local(String c, String e) {
        this.cidade = c;
        this.estado = e;
    }

    public String getCidade(){
        return this.cidade;
    }
    
    public String getEstado(){
        return this.estado;
    }
}
