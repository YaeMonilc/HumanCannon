package cc.kocho;

import cc.kocho.config.Config;
import cc.kocho.util.HCRandom;
import cc.kocho.util.HttpRequest;
import okhttp3.*;

import java.io.IOException;

public class Cannon {

    public static Cannonball fire(String qName){
        Cannonball cannonball = new Cannonball();
        cannonball.name = qName + HCRandom.rString(10);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"),
                Config.requestMessage.replace("(username)", cannonball.name));

        try {
            if (HttpRequest.post(Config.serveUrl + "/hk4e_global/mdk/shield/api/login", body).contains("\"message\":\"OK\"")){
                cannonball.result = "OK";
            }else {
                cannonball.result = "NO";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cannonball;
    }

}
