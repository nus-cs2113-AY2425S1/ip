import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Scanner;

public class LLMChat {
    private static final String API_KEY = System.getenv("OPENAI_API_KEY");

    public String callLLMApi(String prompt) {
        if (API_KEY == null || API_KEY.isEmpty()) {
            System.out.println("Error: API Key not found. Please set the OPENAI_API_KEY environment variable.");
            return "API Key not set.";
        }

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        String json = "{ \"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}] }";

        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .post(body)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                return parseLLMResponse(responseBody);  // Parse and return the assistant's message
            } else {
                System.out.println("API request failed with status code: " + response.code());
                String errorBody = response.body().string();
                System.out.println("Error body: " + errorBody);
                return "Error: " + errorBody;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error communicating with LLM API.";
        }
    }

    private String parseLLMResponse(String responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody);
            JSONArray choices = jsonObject.getJSONArray("choices");
            JSONObject choice = choices.getJSONObject(0);
            JSONObject message = choice.getJSONObject("message");
            return message.getString("content");
        } catch (Exception e) {
            e.printStackTrace();
            return "Error parsing response.";
        }
    }

    public void chatWithAudience(Scanner scanner) {
        System.out.println("You can start chatting with the LLM. Type 'bye' to exit.");
        boolean exit = false;

        while (!exit) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                exit = true;
                System.out.println("Exiting chat mode.");
            } else {
                String llmResponse = callLLMApi(userInput);
                System.out.println("LLM: " + llmResponse);
            }
        }
    }
}
