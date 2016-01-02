
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "")
public class PMQueryInfo {

    private Long pmid;
    private int vms;
    private NodeInfoClass capacity;
    private NodeInfoClass free;

    public NodeInfoClass getCapacity() {
        return capacity;
    }

    public void setCapacity(NodeInfoClass capacity) {
        this.capacity = capacity;
    }

    public NodeInfoClass getFree() {
        return free;
    }

    public void setFree(NodeInfoClass free) {
        this.free = free;
    }

    public Long getPmid() {
        return pmid;
    }

    public void setPmid(Long pmid) {
        this.pmid = pmid;
    }

    public int getVms() {
        return vms;
    }

    public void setVms(int vms) {
        this.vms = vms;
    }
}
