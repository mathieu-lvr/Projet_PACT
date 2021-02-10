package com.e.network;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.e.structures.Credentials;
import com.github.tomakehurst.wiremock.WireMockServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.UUID;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(SpringRunner.class)
public class NetworkInterfaceTest
{
    WireMockServer wireMockServer;

    NetworkInterface ni = new NetworkInterface("http://localhost:9090");

    UUID token = UUID.randomUUID();

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void setUp()
    {
        ReflectionTestUtils.setField(ni, "authToken", token);

        wireMockServer = new WireMockServer(9090);
        wireMockServer.start();
    }

    @After
    public void cleanUp()
    {
        wireMockServer.stop();
    }

    @Test
    public void testGetCredits() throws Exception
    {
        wireMockServer.stubFor(get(urlPathEqualTo("/credits/0")).withHeader("Authorization", equalTo(token.toString()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("2")));

        assertEquals(2, ni.getCredits(0));
    }

    @Test
    public void testGetCreditsWrongToken() throws Exception
    {
        wireMockServer.stubFor(get(urlPathEqualTo("/credits/0")).withHeader("Authorization", equalTo(token.toString()))
                .willReturn(aResponse()
                        .withStatus(401)));

        exceptionRule.expect(HTTPError.class);
        exceptionRule.expectMessage("401");

        ni.getCredits(0);
    }

    @Test
    public void testGetUserInfo() throws Exception
    {
        wireMockServer.stubFor(get(urlPathEqualTo("/0")).withHeader("Authorization", equalTo(token.toString()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("{ \"id\": 0, \"username\": \"testU\", \"credits\": 2, \"email\": \"testM\" }")));

        User res = (User)ni.getUserInfo(0);

        assertEquals(res.getCredits(), 2);
        assertEquals(res.getId(), 0);
        assertEquals(res.getUsername(), "testU");
        assertEquals(res.getEmail(), "testM");
    }

    @Test
    public void testGetUserInfoNoAuth() throws Exception
    {
        wireMockServer.stubFor(get(urlPathEqualTo("/0")).withHeader("Authorization", equalTo(token.toString()))
                .willReturn(aResponse()
                        .withStatus(401)));

        exceptionRule.expect(HTTPError.class);
        exceptionRule.expectMessage("401");

        ni.getUserInfo(0);
    }

    @Test
    public void testConnect() throws Exception
    {
        wireMockServer.stubFor(post(urlPathEqualTo("/token"))
                .withRequestBody(equalToJson("{\n" +
                        "\"username\": \"test\",\n" +
                        "\"password\": \"test\"\n" +
                        "}"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("{\n" +
                "\"token\": \"" + token.toString() + "\",\n" +
                "\"id\": 1\n" +
                "}")));

        ni.connect(new Credentials("test", "test"));

        assertEquals(((UUID)ReflectionTestUtils.getField(ni, "authToken")).toString(), token.toString());
        assertEquals(ReflectionTestUtils.getField(ni, "_id").toString(), "1");
    }

    @Test
    public void testConnectError() throws Exception
    {
        wireMockServer.stubFor(post(urlPathEqualTo("/token"))
                .withRequestBody(equalToJson("{\n" +
                        "\"username\": \"test\",\n" +
                        "\"password\": \"test\"\n" +
                        "}"))
                .willReturn(aResponse()
                        .withStatus(401)));

        exceptionRule.expect(HTTPError.class);
        exceptionRule.expectMessage("401");

        ni.connect(new Credentials("test", "test"));
    }

}
