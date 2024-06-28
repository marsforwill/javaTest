package CodeTest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import org.json.JSONObject;

import com.apple.itunes.storekit.client.APIException;
import com.apple.itunes.storekit.client.AppStoreServerAPIClient;
import com.apple.itunes.storekit.model.Environment;
import com.apple.itunes.storekit.model.SendTestNotificationResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.jackson2.JacksonFactory;

public class JWTtest {

    // Utilisez l'URL de sandbox pour les tests
	private static final String APPLE_CANCEL_URL = "https://api.storekit.itunes.apple.com/inApps/v1/subscription/cancel";
    //private static final String APPLE_CANCEL_URL = "https://api.storekit-sandbox.itunes.apple.com/inApps/v1/subscriptions/cancel";
    //private static final String KEY_ID = "DA49MNDLHP";
	private static final String KEY_ID = "4S9259465T";
    private static final String ISSUER_ID = "c7ff0713-2600-4241-ab6f-9ad730c74ec1";
    private static String PRIVATE_KEY = "YOUR_PRIVATE_KEY";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    public static void main(String[] args) throws Exception {

        String bundleId = "com.scribens.natife";
        Path filePath = Path.of("/workspaces/javaTest/src/main/java/AuthKey_4S9259465T.p8");
        String encodedKey = Files.readString(filePath);
        Environment environment = Environment.PRODUCTION;

        System.out.println("encodeKey: " + encodedKey);
        AppStoreServerAPIClient client =
                new AppStoreServerAPIClient(encodedKey, KEY_ID, ISSUER_ID, bundleId, environment);

        try {
            SendTestNotificationResponse response = client.requestTestNotification();
            System.out.println(response);
        } catch (APIException | IOException e) {
            e.printStackTrace();
        }


        // try {
        //     //PRIVATE_KEY = loadPrivateKey("D:/Bureau/NewApp_IA/Evolutions/Payment/Server/AuthKey_DA49MNDLHP.p8");
        // 	PRIVATE_KEY = loadPrivateKey("/workspaces/javaTest/src/main/java/AuthKey_4S9259465T.p8");
        //     long nowSeconds = System.currentTimeMillis() / 1000;
        //     String jwtToken = generateJWT(KEY_ID, ISSUER_ID, PRIVATE_KEY, nowSeconds, nowSeconds + 900); // 15 minutes

        //     System.out.println("Generated JWT: " + jwtToken);

        //     URL url = new URL(APPLE_CANCEL_URL);
        //     HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //     conn.setRequestMethod("POST");
        //     conn.setRequestProperty("Authorization", "Bearer " + jwtToken);
        //     conn.setRequestProperty("Content-Type", "application/json");
        //     conn.setDoOutput(true);

        //     // Assurez-vous que l'ID de transaction est au bon format
        //     String payload = String.format("{\"transactionId\":\"%s\"}", "420001909658385");
        //     System.out.println("Payload: " + payload);

        //     try (OutputStream os = conn.getOutputStream()) {
        //         os.write(payload.getBytes(StandardCharsets.UTF_8));
        //     }
            
        //     int responseCode = conn.getResponseCode();
        //     String xRequestId = conn.getHeaderField("X-Request-ID");
        //     System.out.println("X-Request-ID: " + xRequestId);
            
        //     if (responseCode == 200) {
        //         System.out.println("Subscription cancelled successfully.");
        //     } else {
        //         System.out.println("Failed to cancel subscription. Response code: " + responseCode);
        //         try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8))) {
        //             StringBuilder response = new StringBuilder();
        //             String responseLine;
        //             while ((responseLine = br.readLine()) != null) {
        //                 response.append(responseLine);
        //             }
        //             System.out.println("Error Response: " + response.toString());
                    
        //             if (response.toString().trim().startsWith("{")) {
        //                 JSONObject jsonResponse = new JSONObject(response.toString());
        //                 String id = jsonResponse.optString("ID");
        //                 String code = jsonResponse.optString("Code");
        //                 System.out.println("Error ID: " + id);
        //                 System.out.println("Error Code: " + code);
        //             }
        //         }
        //     }
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
    }

    // public static String generateJWT2(String teamId, String keyId, String privateKeyContent) throws Exception {
    //     Security.addProvider(new BouncyCastleProvider());

    //     // Decode the private key
    //     privateKeyContent = privateKeyContent.replace("-----BEGIN PRIVATE KEY-----", "")
    //                                          .replace("-----END PRIVATE KEY-----", "")
    //                                          .replaceAll("\\s+", "");
    //     byte[] keyBytes = Base64.getDecoder().decode(privateKeyContent);

    //     PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
    //     KeyFactory keyFactory = KeyFactory.getInstance("EC");
    //     PrivateKey privateKey = keyFactory.generatePrivate(spec);

    //     // Set token expiry time
    //     long now = System.currentTimeMillis();
    //     long exp = now + 3600 * 1000; // Token valid for 1 hour

        
    //     Algorithm algorithm = Algorithm.ECDSA256(null, privateKey);
    //     String token = JWT.create()
    //                       .withHeader(Map.of("alg", "ES256", "kid", keyId, "typ", "JWT"))
    //                       .withIssuer(teamId)
    //                       .withIssuedAt(new Date(now))
    //                       .withExpiresAt(new Date(exp))
    //                       .sign(algorithm);

    //     return token;
    // }

    private static String generateJWT(String keyId, String issuerId, String privateKey, long iat, long exp) throws Exception {
        String header = createJsonHeader(keyId);
        String payload = createJsonPayload(issuerId, iat, exp);
        String data = Base64.getUrlEncoder().withoutPadding().encodeToString(header.getBytes()) + "." +
                      Base64.getUrlEncoder().withoutPadding().encodeToString(payload.getBytes());

        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
        PrivateKey key = keyFactory.generatePrivate(keySpec);

        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(key);
        signature.update(data.getBytes());
        String signedData = Base64.getUrlEncoder().withoutPadding().encodeToString(signature.sign());

        return data + "." + signedData;
    }

    private static String createJsonHeader(String keyId) throws IOException {
        StringWriter writer = new StringWriter();
        JsonGenerator generator = JSON_FACTORY.createJsonGenerator(writer);
        generator.writeStartObject();
        generator.writeFieldName("alg");
        generator.writeString("ES256");
        generator.writeFieldName("kid");
        generator.writeString(keyId);
        generator.writeFieldName("typ");
        generator.writeString("JWT");
        generator.writeEndObject();
        generator.close();
        return writer.toString();
    }

    private static String createJsonPayload(String issuerId, long iat, long exp) throws IOException {
        StringWriter writer = new StringWriter();
        JsonGenerator generator = JSON_FACTORY.createJsonGenerator(writer);
        generator.writeStartObject();
        generator.writeFieldName("iss");
        generator.writeString(issuerId);
        generator.writeFieldName("iat");
        generator.writeNumber(iat);
        generator.writeFieldName("exp");
        generator.writeNumber(exp);
        generator.writeFieldName("aud");
        generator.writeString("appstoreconnect-v1");
        generator.writeFieldName("bid");
        generator.writeString("com.scribens.natife");
        generator.writeFieldName("scope");
        generator.writeString("POST /inApps/v1/subscriptions/cancel");
        generator.writeFieldName("nonce");
        generator.writeString(UUID.randomUUID().toString());
        generator.writeEndObject();
        generator.close();
        return writer.toString();
    }

    public static String loadPrivateKey(String filePath) throws IOException {
        String privateKey = new String(Files.readAllBytes(Paths.get(filePath)));
        privateKey = privateKey.replace("-----BEGIN PRIVATE KEY-----", "")
                               .replace("-----END PRIVATE KEY-----", "")
                               .replaceAll("\\s+", "");
        return privateKey;
    }
}