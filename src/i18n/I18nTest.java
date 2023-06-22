package i18n;

import org.junit.Test;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18nTest {
    @Test
    public void test() {
        //获取系统默认的语言 国家信息
        Locale locale = Locale.getDefault();
        System.out.println(locale);
        //获取中文的常量locale对象
        System.out.println(Locale.CHINA);
        //获取英文的常量locale对象
        System.out.println(Locale.US);

        Locale locale1 = Locale.US;
        //通过指定的basename和locale对象读取相应的配置文件
        ResourceBundle bundle = ResourceBundle.getBundle("i18n", locale1);
        System.out.println(bundle.getString("username"));
        System.out.println(bundle.getString("sex"));
    }
}
