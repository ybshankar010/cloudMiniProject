
import java.util.Iterator;
import java.util.Vector;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/image/")
public class ImageService {

    @GET
    @Produces("application/json")
    @Path("/list")
    public Vector<Images> list_images() {

        Iterator iter = Helper.imgTypes.keySet().iterator();
        Vector<Images> vec = new Vector<Images>();

        while (iter.hasNext()) {
            vec.add(Helper.imgTypes.get((Long) iter.next()));
        }
        return vec;
    }
}
