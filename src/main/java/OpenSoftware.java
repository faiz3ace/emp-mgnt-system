// import java.io.BufferedReader;
// import java.io.File;
// import java.io.IOException;
// import java.io.InputStreamReader;

// public class OpenSoftware {
//     public static void main(String[] args) throws IOException {
//         Runtime rt = Runtime.getRuntime();
//         try {
//             rt.exec(new String[] { "notepad.exe", "welcome.txt" });

//         } catch (IOException e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         }

//         // Read data from console
//         Process process;
//         try {
//             process = Runtime.getRuntime().exec("cmd /c mkdir newDir", null, new File("C:\\Users\\"));
//         } catch (IOException e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         }
//         process = Runtime.getRuntime().exec("cmd /c dir", null, new File("C:\\Users\\"));
//         BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//         String line = "";
//         while ((line = reader.readLine()) != null) {
//             System.out.println(line);
//         }
//     }
// }
