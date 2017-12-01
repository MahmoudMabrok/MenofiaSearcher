import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

import java.util.InputMismatchException;

public class Main extends Application {

    Test t = new Test() ;
    addCityPane apane = new addCityPane() ;

   ObservableList<Node> cir = FXCollections.observableArrayList(apane.getChildren() ) ;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        VBox p =  new VBox ();
        p.setAlignment(Pos.CENTER);

        t.addCity( "Sadat " ,62 ,405 );
        t.addCity( "Ashmon" ,460 ,457 );
        t.addCity( "Bagor" ,509 ,327 );
        t.addCity( "Sers" ,405 ,304 );
        t.addCity( "Menouf" ,419 ,270 );//
        t.addCity( "Shohada" ,400 ,150 );
        t.addCity( "Tala" ,435 ,55 );
        t.addCity( "Shebin" ,200,480 );
        t.addCity( "quensa" ,600,215 );
        t.addCity( "Berkat" ,560,120);


         /*p.setOnMousePressed(e->
        {
            System.out.println(e.getX() +"   " +  e.getY());
        });*/

        circlePane cp = new circlePane( t.getData() ) ;

        p.getChildren().addAll(apane   , cp , t.getPoly() ) ;

        Scene s = new Scene(p ,700, 600) ;
        primaryStage.setScene(s);
         primaryStage.show();

    }


    class addCityPane extends HBox {

        public  addCityPane ()
        {
            this.setAlignment(Pos.CENTER);
            this.setSpacing(15);
            this.setPadding( new Insets(15));
            TextField tname = new TextField () ;
            TextField tx = new TextField () ;
            TextField ty = new TextField () ;

            tname.setPromptText("name of city ");
            tx.setPromptText("x of city");
            ty.setPromptText("y of city ");

            Button add = new Button("add") ;
            add.setOnAction(e-> {
                try {
                    String name = tname.getText();
                    int x = Integer.parseInt(tx.getText());
                    int y = Integer.parseInt(ty.getText());
                    t.addCity(name , x , y );
                }catch (InputMismatchException i )
                {
                }



            });

            this.getChildren().addAll(tname , tx , ty , add) ;

        }
    }



}
