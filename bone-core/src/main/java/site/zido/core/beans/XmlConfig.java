package site.zido.core.beans;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlConfig {

    public static Map<String, Definition> getConfig(String path) {
        Map<String, Definition> configMap = new HashMap<>();
        //记录ref，解决循环依赖的问题
        Map<String, String> refBuf = new HashMap<>();
        Document doc = null;

        SAXReader reader = new SAXReader();
        InputStream in = XmlConfig.class.getResourceAsStream(path);

        try {
            doc = reader.read(in);
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException("xml文件路径错误");
        }

        String xpath = "//bean";
        List<Element> list = doc.selectNodes(xpath);

        if (list != null) {
            for (Element beanElement : list) {
                Definition definition = new Definition();

                String id = beanElement.attributeValue("id");

                String className = beanElement.attributeValue("class");

                definition.setId(id);
                definition.setClassName(className);

                List<Element> proList = beanElement.elements("property");
                if (proList != null) {
                    for (Element proElement : proList) {
                        Property prop = new Property();
                        String propName = proElement.attributeValue("name");
                        String propValue = proElement.attributeValue("value");
                        String propRef = proElement.attributeValue("ref");
                        if (propRef != null && definition.getId().equals(refBuf.get(propRef))) {
                            throw new RuntimeException("查找到xml文件中含有循环依赖 [" + propRef + "] <-> [" + refBuf.get(propRef) + "]");
                        } else if (propRef != null)
                            refBuf.put(definition.getId(), propRef);
                        prop.setName(propName);
                        prop.setValue(propValue);
                        prop.setRef(propRef);

                        definition.getProperties().add(prop);
                    }
                }
                if (configMap.containsKey(id)) {
                    throw new RuntimeException("bean节点id重复:" + id);
                }
                configMap.put(id, definition);
            }
        }
        return configMap;
    }
}
