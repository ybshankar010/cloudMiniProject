
// Entry point the class that is used to start the server.
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.libvirt.LibvirtException;
import org.apache.cxf.jaxrs.lifecycle.ResourceProvider;
public class Server {

    protected Server(String ip,String port) throws Exception {

        System.out.println("In Original...!!");
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setResourceClasses(Redirect.class);
        sf.setResourceProvider(Redirect.class,
                new SingletonResourceProvider(new Redirect()));
        sf.setResourceClasses(ImageService.class);
        sf.setResourceProvider(ImageService.class,
                new SingletonResourceProvider(new ImageService()));
        sf.setResourceClasses(ResourceAPI.class);
        sf.setResourceProvider(ResourceAPI.class,
                new SingletonResourceProvider(new ResourceAPI()));
        sf.setAddress("http://" + ip+":"+port);
        sf.create();
    }

    public static void main(String args[]) throws Exception {
        //init(args);
        NodeList.getConnectionInfo(args[1]);    //populates the list of nodes present.
        Helper.resourcesAvailable = Helper.nodeInfo;
        ConnectionTypes.getTypes(args[3]);     // populates the types present.
        ImageTypes.getImageInfo(args[2]);

        new Server(args[0],args[4]);

        System.out.println("Server ready...");

        Thread.sleep(5 * 60 * 1000);
        System.out.println("Server exiting");
        System.exit(0);
    }

    public static void init(String[] files) throws IOException, FileNotFoundException, LibvirtException {

        NodeList.getConnectionInfo(files[1]);    //populates the list of nodes present.
        Helper.resourcesAvailable = Helper.nodeInfo;
        ConnectionTypes.getTypes(files[3]);     // populates the types present.
        ImageTypes.getImageInfo(files[2]);

    }
}
