import java.io.*;
import java.net.*;

public class HelloServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        ServerSocket server = new ServerSocket(port);
        System.out.println("Server running at http://localhost:" + port);

        while (true) {
            Socket client = server.accept();
            BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(client.getOutputStream())
            );
            BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream())
            );

            // Read request headers
            String line;
            while (!(line = in.readLine()).isEmpty()) {
                System.out.println(line);
            }

            // Serve the index.html
            BufferedReader file = new BufferedReader(new FileReader("index.html"));
            StringBuilder content = new StringBuilder();
            String l;
            while ((l = file.readLine()) != null) {
                content.append(l).append("\n");
            }

            out.write("HTTP/1.1 200 OK\r\n");
            out.write("Content-Type: text/html\r\n");
            out.write("Content-Length: " + content.length() + "\r\n");
            out.write("\r\n");
            out.write(content.toString());
            out.flush();

            file.close();
            out.close();
            in.close();
            client.close();
        }
    }
}
