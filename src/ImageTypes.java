
//Stores the images types and Ip addresses from images file.
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;

public class ImageTypes {

    public static void getImageInfo(String file) throws IOException {
        RandomAccessFile seek = new RandomAccessFile(file, "r");
        String line, temp;
        StringTokenizer stk;
        Images img;
        Long count = new Long(0);
        while ((line = seek.readLine()) != null) {
            img = new Images();
            stk = new StringTokenizer(line, ":");
            img.ip = (stk.nextToken());
            temp = stk.nextToken();
            int i = temp.lastIndexOf("/");
            count++;
            img.setId(count);
            img.path = (temp.substring(0, i));
            img.setName(temp.substring(i + 1, temp.length()));
            Helper.imgTypes.put(count, img);
        }
    }
}
