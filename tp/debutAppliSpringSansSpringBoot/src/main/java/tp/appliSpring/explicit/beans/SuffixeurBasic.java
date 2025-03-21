package tp.appliSpring.explicit.beans;

public class SuffixeurBasic implements Suffixeur{

    private String suffixe ;

    public SuffixeurBasic(String suffixe){
        this.suffixe=suffixe;
    }

    public SuffixeurBasic(){ this("**"); //default value
    }

    @Override
    public String suffixer(String message) {
        return  message + suffixe;
    }

    public String getSuffixe() {  return suffixe;   }

    public void setSuffixe(String suffixe) { this.suffixe = suffixe;   }
}
