//This class is used to display the vmid's
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MyVMinfo {

    private String vmids;

    public String getVmids() {
        return vmids;
    }

    public void setVmids(String vmids) {
        this.vmids = vmids;
    }
}
