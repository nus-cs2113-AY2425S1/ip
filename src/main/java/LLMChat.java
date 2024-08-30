import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

public class LLMChat {
    private static final String API_KEY = System.getenv("OPENAI_API_KEY");

    public String callLLMApi(String prompt) {
        if (API_KEY == null || API_KEY.isEmpty()) {
            System.out.println("Error: API Key not found. Please set the OPENAI_API_KEY environment variable.");
            return "API Key not set.";
        }

        // Example of reading data from a local directory
        String localData = readLocalDirectory("path/to/your/directory"); // Use the directory path here

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");

        // Combine the local data with the prompt
        String combinedPrompt = "Local data content: " + localData + "\nUser prompt: " + prompt;

        // Adding parameters to the JSON request
        String json = null;
        try {
            json = new JSONObject()
                    .put("model", "gpt-3.5-turbo")
                    .put("messages", new JSONArray()
                            .put(new JSONObject()
                                    .put("role", "system")
                                    .put("content", "Your name is Wildpeace, and you are a virtual assistant specialized in software engineering."))
                            .put(new JSONObject()
                                    .put("role", "user")
                                    .put("content", combinedPrompt)))
                    .put("temperature", 0.7)
                    .put("max_tokens", 100)
                    .put("top_p", 1.0)
                    .put("frequency_penalty", 0.5)
                    .put("presence_penalty", 0.5)
                    .toString();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

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
        } catch (JSONException e) {
            e.printStackTrace();
            return "Error parsing response.";
        }
    }

    private String readLocalDirectory(String directoryPath) {
        StringBuilder allContent = new StringBuilder();
        try {
            Path path = Paths.get(directoryPath);
            if (Files.isDirectory(path)) {
                // List all files in the directory
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
                    for (Path entry : stream) {
                        if (Files.isRegularFile(entry)) {
                            String content = new String(Files.readAllBytes(entry));
                            allContent.append(content).append("\n"); // Append file content with a newline
                        }
                    }
                }
            } else {
                System.out.println("Provided path is not a directory.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allContent.toString();
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
