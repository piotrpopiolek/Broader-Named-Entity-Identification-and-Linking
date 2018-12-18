package pl.zti.put.poznan.zti.beans;

import pl.zti.put.poznan.zti.Identificator;

import javax.annotation.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class NerBean {
    private String input = "";
    private String result = "";

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getInput() {
        return this.input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void identificate() {
        try {
            result = Identificator.identificate(input);
        }catch(Exception e){
            result = "Nie udało się przetworzyć zapytania.";
        }
    }
}