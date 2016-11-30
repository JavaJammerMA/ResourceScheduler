package javaFX;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application {

    GridPane root;
    Scene scene;

    @Override
    public void start(Stage primaryStage) {
        try {
            // Code to get MAC, IP and Host name for computer
            InetAddress inetAddress = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(inetAddress);
            byte[] mac = network.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for (byte b : mac) {
                sb.append(String.format("%02X ", b));
            }

            String hostName = inetAddress.getCanonicalHostName();
            String ipAddress = inetAddress.getHostAddress();

            System.out.println("Current MAC address: " + sb.toString());
            System.out.println("Hostname: " + hostName + " \nIP Address: " + ipAddress);

            Label welcomeLabel = new Label();
            welcomeLabel.setText("Welcome " + ipAddress);

            // Create a gridpane to display everything and put padding round everything
            root = new GridPane();
            root.setAlignment(Pos.CENTER);
            root.add(welcomeLabel, 1, 0);
            root.setPadding(new Insets(10, 10, 10, 10));
            root.setHgap(10);
            root.setVgap(10);
            
            // Create some desks and add a rectangle to seperate them apart (otherwise could skip more rows)
            ArrayList<Desk> desks = new ArrayList<>();
            int counter = 0;
            for (int i = 3; i < 10; i++) {
                if (i % 3 == 0) {
                    for (int j = 0; j < 3; j++) {
                        Rectangle rect = new Rectangle(0, 0, 150, 25);
                        rect.setFill(Color.TRANSPARENT);
                        root.add(rect, j, i);
                    }
                    continue;
                }
                for (int j = 0; j < 3; j++) {
                    desks.add(new Desk(i, j, "Desk " + counter, "2 Power Sockets\nTelephone"));
                    counter++;
                }
            }

            // Book some of the desks
            desks.get(0).setBooked(true);
            desks.get(0).setBookee("Emma Archibald");
            desks.get(1).setBooked(true);
            desks.get(1).setBookee("Peter Norton");
            desks.get(2).setBooked(true);
            desks.get(2).setBookee("Dayam Javed");
            desks.get(4).setBooked(true);
            desks.get(4).setBookee("Elliot Jenkins");

            // Loop round the desks and create the buttons for them
            // Used buttons to display windows rather than rectangles as couldn't
            // work out how to add text onto them.
            for (Desk desk : desks) {
                Button newButton = createDeskButton(desk);
                root.add(newButton, desk.getColumn(), desk.getRow());
                Button window = new Button();
                window.setStyle("-fx-background-color:#0000FF;");
                window.setMinHeight(50);
                root.add(window, 4, desk.getRow());
            }
            desks.get(3).setBooked(true);

            scene = new Scene(root, 600, 550);

            primaryStage.setResizable(false);
            primaryStage.setTitle("Resource Scheduler");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    // Launch comes from inheritance, has nothing to do with me
    public static void main(String[] args) {
        launch(args);
    }

    public Button createDeskButton(Desk desk) {
        Button button = new Button();
        button.setMinHeight(50);
        button.setMinWidth(150);
        if (desk.isBooked()) {
            button.setText(desk.getBookee());
        } else {
            button.setText(desk.getName());
        }
        button.setWrapText(true);
        Font font = new Font("Arial", 12);
        button.setFont(font);

        // Create a rectangle to display the text tooltip on
        Rectangle rect = new Rectangle(0, 0, 100, 100);
        String toolTipText = "";

        // Choose what colour to make the button
        if (desk.isBooked()) {
            button.setStyle("-fx-background-color:#ED1C24;");
            toolTipText = "Sorry the desk is booked.";
        } else {
            button.setStyle("-fx-background-color:#22B14C;");
            toolTipText = "The desk is available and has the following:\n" + desk.getFeatures();
        }

        Tooltip tt = new Tooltip(toolTipText);
        Tooltip.install(rect, tt);
        button.setTooltip(tt);

        // If a desk is booked then don't make it change colour on mouse hover
        if (!desk.isBooked()) {
            button.setOnMouseEntered((MouseEvent t) -> {
                // Make it red, can be any hex code for colours, (RGB values in hex)
                button.setStyle("-fx-background-color:#ED1C24;");
            });

            button.setOnMouseExited((MouseEvent t) -> {
                button.setStyle("-fx-background-color:#22B14C;");
            });
        }

        // For now just display the desk name, simples!
        button.setOnAction((ActionEvent event) -> {
            System.out.println(desk.getName());
        });
        return button;
    }

}
