package br.furb.eventos.ws;

/**
 * Classe para retornar erro de validação de model.
 * @author alexandre.vicenzi
 */
public class ModelError {

    private String message;
    private String field;
    private Object value;

    public ModelError() {

    }

    public ModelError(String message, String field, Object value) {
        this.message = message;
        this.field = field;
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
