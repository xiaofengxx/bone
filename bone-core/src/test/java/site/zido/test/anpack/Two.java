package site.zido.test.anpack;

public class Two {
    private String render;
    private One one;

    public String getRender() {
        return render;
    }

    public void setRender(String render) {
        this.render = render;
    }

    public One getOne() {
        return one;
    }

    public void setOne(One one) {
        this.one = one;
    }

    @Override
    public String toString() {
        return "Two{" +
                "render='" + render + '\'' +
                ", one=" + one +
                '}';
    }
}
