//My string class that is used to store the pmid list.
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MyStringClass {

    private String pmids;

    public String getPmids() {
        return pmids;
    }

    public void setPmids(String pmids) {
        this.pmids = pmids;
    }
}
