package site.zido.test;

import org.junit.Assert;
import org.junit.Test;
import site.zido.bone.core.beans.AnnotationParser;
import site.zido.bone.core.beans.BoneContext;
import site.zido.bone.core.beans.IBeanParser;
import site.zido.bone.core.beans.XmlParser;
import site.zido.test.anpack.CommonComponent;

public class IocTest {
    /**
     * xml注入测试
     */
    @Test
    public void testXmlIoc() {
        IBeanParser xmlParser = new XmlParser("/applicationContext.xml");
        xmlParser.parser();
        One one = (One) BoneContext.getInstance().getBean("one");
        Two two = (Two) BoneContext.getInstance().getBean("two", Two.class);
        Three three = (Three) BoneContext.getInstance().getBean("three", Three.class);
        Assert.assertNotNull("one 注入失败", one);
        Assert.assertNotNull("two 注入失败", two);
        Assert.assertNotNull("three 注入失败", three);
    }

    /**
     * 注解注入测试
     */
    @Test
    public void testAnnotation() {
        IBeanParser parser = new AnnotationParser("site.zido.test.anpack");
        parser.parser();
        One one = (One) BoneContext.getInstance().getBean(One.class);
        Two two = (Two) BoneContext.getInstance().getBean(Two.class);
        Three three = (Three) BoneContext.getInstance().getBean("three");
        Assert.assertNotNull("one 注入失败", one);
        Assert.assertNotNull("two 注入失败", two);
        Assert.assertNotNull("three 注入失败", three);
    }

    /**
     * Component注解测试
     */
    @Test
    public void testComponent() {
        IBeanParser parser = new AnnotationParser("site.zido.test.anpack");
        parser.parser();
        CommonComponent component = (CommonComponent) BoneContext.getInstance().getBean("common", CommonComponent.class);
        Assert.assertNotNull("component 注入失败", component);
        Assert.assertNotNull("Inject 属性注入失败", component.getOtherOne());
    }
}
