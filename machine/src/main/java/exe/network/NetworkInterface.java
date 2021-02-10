package exe.network;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import exe.ihm.IQRCode;
import exe.ihm.InterfaceCCS;
import exe.ihm.User;
import javafx.geometry.HPos;
import okhttp3.*;

import java.io.Console;
import java.io.IOException;

public class NetworkInterface implements InterfaceCCS {
    String baseURL = "http://127.0.0.1:8080/api/v1/machine";

    private OkHttpClient client;

    public NetworkInterface()
    {
        client = new OkHttpClient();
    }

    @Override
    public int verifyQRCode(String qrcode) throws IOException {
        Request request = new Request.Builder()
                .url(baseURL + "/validateQR")
                .post(RequestBody.create(qrcode, MediaType.parse("text")))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if(!response.isSuccessful())
            {

                return -1;
            }
            else
            {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(response.body().string());
                System.out.println(response.body().toString());
                return (int)Integer.parseInt(node.path("message").textValue());
            }



        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public User getUser(int userID) {
        return null;
    }

    @Override
    public User getUser(String email) {
        return null;
    }

    @Override
    public boolean authorizeDistribution(int user) throws IOException {
        Request request = new Request.Builder()
                .url(baseURL + "/auth/" + user)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if(!response.isSuccessful())
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public void validateDistribution(int user) throws IOException {
        Request request = new Request.Builder()
                .url(baseURL + "/valD/" + user)
                .build();

        try (Response response = client.newCall(request).execute())
        {
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public void validateCupRecover(int user) throws IOException {
        Request request = new Request.Builder()
                .url(baseURL + "/valR/" + user)
                .build();

        try (Response response = client.newCall(request).execute()) {
        }
        catch (Exception e)
        {
            throw e;
        }
    }


    @Override
    public int logUser(String username, String password) {
        return 0;
    }
}
