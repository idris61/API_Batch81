package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqresPatchNamePojo {

    private String name;

    public ReqresPatchNamePojo(String name) {
        this.name = name;
    }

    public ReqresPatchNamePojo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ReqresPatchNamePojo{" +
                "name='" + name + '\'' +
                '}';
    }
}
