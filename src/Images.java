
import javax.xml.bind.annotation.XmlRootElement;

//This class is to used to store the information of the image.
@XmlRootElement
public class Images {

    String path, ip;
    private String name;
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Images() {
    }
}
