/**
 * data 2018-08-16   00:02
 * E-mail   sis.nonacosa@gmail.com
 * 测试
 * @author sis.nonacosa
 */
public class Go {

        /**
         * @param args
         */
        public static void main(String[] args) {
            Publish    publish   = new Publish();
            new Subscribe1(publish);
            new Subscribe2(publish);
            new Subscribe3(publish);

            publish.setData("明天七夕，各单位提早下班");
        }

}
