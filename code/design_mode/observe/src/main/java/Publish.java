import java.util.Observable;
import java.util.Observer;

/**
 * data 2018-08-15   23:59
 * E-mail   sis.nonacosa@gmail.com
 * 发布
 * @author sis.nonacosa
 */
public class Publish extends Observable {
    private String data = "";

    public String getData() {
        return data;
    }

    public void setData(String data) {
        if (!this.data.equals(data)){
            this.data = data;
            setChanged();    //改变通知者的状态
        }
        notifyObservers();    //调用父类Observable方法，通知所有观察者
    }
}