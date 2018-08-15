import java.util.Observable;
import java.util.Observer;

/**
 * data 2018-08-15   23:57
 * E-mail   sis.nonacosa@gmail.com
 * 观察者3
 * @author sis.nonacosa
 */
public class Subscribe3 implements Observer {

    public Subscribe3(Observable o){
        o.addObserver(this);        //将该观察者放入待通知观察者里
    }
    /* (non-Javadoc)
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("观察者3 收到通知:" + ((Publish)o).getData());
    }

}