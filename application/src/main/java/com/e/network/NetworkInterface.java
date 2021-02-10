package com.e.network;

import com.e.structures.Credentials;
import com.e.structures.IAuthentificationData;
import com.e.structures.UserInfo;
import com.e.structures.UserSecuredInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.UUID;

import kotlin.NotImplementedError;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkInterface implements IServerInterface
{

    private OkHttpClient client;
    private String baseURL;

    private static int _id;

    private UUID authToken;

    public NetworkInterface(String serverURL)
    {
        client = new OkHttpClient();
        baseURL= serverURL;
    }

    @Override
    public void connect(IAuthentificationData data) throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString((Credentials)data);

        Request request = new Request.Builder()
                .url(baseURL + "/token")
                .header("Content-Type", "application/json")
                .post(RequestBody.create(json, MediaType.parse("application/json; charset=utf-8")))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if(!response.isSuccessful())
            {
                throw new HTTPError(Integer.toString(response.code()), response.code());
            }
            else
            {

                String responseBody = response.body().string();
                JsonNode jsonResponse = mapper.readTree(responseBody);
                authToken = UUID.fromString(jsonResponse.get(0).get("token").asText());
                _id = jsonResponse.get(0).get("id").asInt();
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public int getCredits(int userID) throws IOException, HTTPError {
        Request request = new Request.Builder()
                .url(baseURL + "/credits/" + Integer.toString(userID))
                .header("Authorization", authToken.toString())
                .build();

        try (Response response = client.newCall(request).execute()) {
            if(!response.isSuccessful())
            {
                System.out.println(authToken.toString());
                throw new HTTPError(Integer.toString(response.code()), response.code());
            }
            else
            {
                return Integer.parseInt(response.body().string());
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public UserInfo getUserInfo(int userID) throws Exception
    {
        Request request = new Request.Builder()
                .url(baseURL + "/" + Integer.toString(userID))
                .header("Authorization", authToken.toString())
                .build();

        try (Response response = client.newCall(request).execute()) {
            if(!response.isSuccessful())
            {
                throw new HTTPError(Integer.toString(response.code()), response.code());
            }
            else
            {
                ObjectMapper mapper = new ObjectMapper();

                return mapper.readValue(response.body().string(), UserInfo.class);
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public void setUserInfo(UserInfo info) {
        throw new NotImplementedError();
    }

    @Override
    public void setPassword(String password)
    {
        throw new NotImplementedError();
    }

    @Override
    public void createAccount(UserSecuredInfo securedInfos) throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();

        Request request = new Request.Builder()
                .url(baseURL + "/")
                .header("Content-Type", "application/json")
                .post(RequestBody.create(mapper.writeValueAsString(securedInfos), MediaType.parse("application/json; charset=utf-8")))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if(!response.isSuccessful())
            {
                throw new HTTPError(Integer.toString(response.code()), response.code());
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public String getQRCode(int userID) throws IOException, HTTPError {
        Request request = new Request.Builder()
                .url(baseURL + "/qrcode/" + Integer.toString(userID))
                .header("Authorization", authToken.toString())
                .build();

        try (Response response = client.newCall(request).execute()) {
            if(!response.isSuccessful())
            {
                throw new HTTPError(Integer.toString(response.code()), response.code());
            }
            else
            {
                return response.body().string();
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public static int getCurrentUserId()
    {
        return _id;
    }
}
