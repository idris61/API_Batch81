package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)//Bu annotation aynı levelde bilinmeyen verileri  görmezden gelerek diğer verilerin bu class tipinde Pojo class'a çevrilmesine yarıyor.
public class GoRestPojo {

    private Object meta;
    private GorestDataPojo data;

    public GoRestPojo(Object meta, GorestDataPojo data) {
        this.meta = meta;
        this.data = data;
    }

    public GoRestPojo() {
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public GorestDataPojo getData() {
        return data;
    }

    public void setData(GorestDataPojo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GoRestPojo{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}
