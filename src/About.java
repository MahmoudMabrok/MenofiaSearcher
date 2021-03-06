import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;


public class About {
    Desktop desktop = Desktop.getDesktop();
    public About() {
        Pane root = new Pane();
        Stage aboutStage = new Stage();
        ImageView aboutImage = new ImageView(new Image("About3.png"));
        root.setOnMousePressed(e -> {
            aboutStage.close();
        });
        Button sourceCode = new Button("Open GitHub");
        sourceCode.setLayoutX(208);
        sourceCode.setLayoutY(405);
        sourceCode.setOnAction(e -> {
            try {
                URL gitLink = new URL("https://github.com/MahmoudMabrok/MenofiaSearcher");
                if (Desktop.isDesktopSupported()) {
                    if (desktop.isSupported(Desktop.Action.BROWSE) )
                        desktop.browse(gitLink.toURI());
                    else
                    {

                        String s = System.getProperty("os.name").toLowerCase();
                        if (s.contains("linux"))
                        {

                            String msg ;
                        //    Process p = Runtime.getRuntime().exec("cat /proc/version") ;
                            Process p = Runtime.getRuntime().exec("pgrep -l \"gnome|kde|mate|cinnamon\"") ;
                            BufferedReader br = new BufferedReader(
                                    new InputStreamReader(p.getInputStream()));
                            msg = br.readLine() ;
                            if (msg.contains("kde")){
                                Runtime.getRuntime().exec("kde-open  " +gitLink.toString() );
                            }
                            else if (msg.contains("gnome"))
                            {
                                Runtime.getRuntime().exec("gnome-open  " +gitLink.toString() );
                            }else
                            {
                                Runtime.getRuntime().exec("xdg-open  " +gitLink.toString() );

                            }
                            p.destroy();

                            System.out.println(gitLink);
                          //  Runtime.getRuntime().exec("xdg-open  " +gitLink.toString() );

                            //Runtime.getRuntime().exec("gnome-open  " +gitLink.toString() );

                        }

                    }


                }

            } catch (URISyntaxException ux) {
                System.out.println(ux.toString());
            } catch (MalformedURLException m) {
                System.out.println(m.toString());
            } catch (IOException io) {
                System.out.println(io.toString());
            }
        });

        root.getChildren().addAll(aboutImage, sourceCode);
    Scene sc = new Scene(root) ;
    aboutStage.setScene(sc);
    aboutStage.getIcons().add(new Image("if_Help_1493288.png")) ;
    aboutStage.setTitle("About App.");
    aboutStage.setResizable(false);
    aboutStage.show();

    }
}
