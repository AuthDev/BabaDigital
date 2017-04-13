package bbgetset;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by evar on 26/03/17.
 */

public class BAPIResult {

    private String result;
    private JSONObject jsonObject;
    private JSONArray jsonArray;
    private boolean sucesso;
    private boolean isEmpty = false;

    public BAPIResult()
    {

    }

    public BAPIResult(String mensagem,JSONObject jsonObjectResult,boolean sucesso)
    {
        this.setResult(mensagem);
        this.setJsonObject(jsonObjectResult);
        this.setSucesso(sucesso);
    }
    public BAPIResult(String webResult,JSONArray jsonArraytResult,boolean sucesso)
    {
        this.setResult(webResult);
        this.setJsonArray(getJsonArray());
        this.setSucesso(sucesso);
    }
    public BAPIResult(boolean isEmpty)
    {
        this.setEmpty(isEmpty);
    }



    public String getResult() {
        return result;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
}
