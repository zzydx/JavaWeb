package ioc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ClassPathXmlApplicationContext implements BeanFactory {
    private Map<String, Object> beanMap = new HashMap<>();
    private String path = "applicationContext.xml";
    public ClassPathXmlApplicationContext(){
        this("applicationContext.xml");
    }

    /**
     * 在Servlet的构造方法中解析applicationContext.xml中配置的beans
     */
    public ClassPathXmlApplicationContext(String path) {
        try {
            //读取配置文件
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
            //创建DocumentBuilderFactory对象
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            //创建DocumentBuilder对象 将xml文件解析成document对象来获取数据
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            //创建Docunment对象 将xml文件解析成document对象来获取数据
            Document document = documentBuilder.parse(inputStream);
            //获取xml中所有的bean标签，转换为节点集合
            NodeList bean = document.getElementsByTagName("bean");
            for (int i = 0; i < bean.getLength(); i++) {
                Node beanNode = bean.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element beanElement = (Element) beanNode;
                    String id = beanElement.getAttribute("id");
                    String clazz = beanElement.getAttribute("class");
                    Class beanClass = Class.forName(clazz);
                    //创建bean实例
                    Object beanObj = beanClass.newInstance();
                    //将bean实例对象保存到map容器中
                    beanMap.put(id, beanObj);
                }
            }
            //组装bean之间的依赖关系
            for (int i = 0; i < bean.getLength(); i++) {
                Node beanNode = bean.item(i);
                //判断子节点是元素节点
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");
                    //获取bean的子节点
                    NodeList childNodes = beanElement.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node childNode = childNodes.item(j);
                        if (childNode.getNodeType() == Node.ELEMENT_NODE && "property".equals(childNode.getNodeName())) {
                            Element propertyElement = (Element) childNode;
                            String propertyName = propertyElement.getAttribute("name");
                            String propertyRef = propertyElement.getAttribute("ref");
                            //找到propertyRef对应的实例
                            Object refObj = beanMap.get(propertyRef);
                            //将refObj设置到当前bean对应的实例的property属性上去
                            Object beanObj = beanMap.get(beanId);
                            Class beanClazz = beanObj.getClass();
                            Field propertyField = beanClazz.getDeclaredField(propertyName);
                            propertyField.setAccessible(true);
                            propertyField.set(beanObj, refObj);
                        }
                    }


                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
