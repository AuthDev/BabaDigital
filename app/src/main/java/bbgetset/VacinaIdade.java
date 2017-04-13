package bbgetset;

import java.util.List;
import java.util.Vector;

/**
 * Created by evar on 21/03/17.
 */

public class VacinaIdade {

    private Vector idade = new Vector();

    public void add(int idade) {

        this.idade.add(idade);
    }
    public int count()
    {
        return idade.size();
    }
    public int[] toArray()
    {
        int[] array = new int[count()];
        for (int i = 0; i < count(); i++) {
            array[i] = (int)idade.get(i);
        }
        return array;
    }
    public int getIdade(int index)
    {
        return (int)idade.get(index);
    }
}
