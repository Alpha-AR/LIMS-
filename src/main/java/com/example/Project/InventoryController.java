package com.example.Project;

        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        //import java.util.ResourceBundle;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.TableColumn;
        import javafx.scene.control.TableView;
        import javafx.scene.control.TextField;
        import javafx.scene.control.RadioButton;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.scene.input.MouseEvent;
        import javafx.stage.Stage;

        import java.net.URL;
        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;


public class InventoryController {


    public void initialize(){
        loadTable();
    }


    Connection conInventory = DbConnection.ConnectDB();

    @FXML
    private Button addItem;

    @FXML
    private TableColumn<TableInventory,String > dateIssuedCol;

    @FXML
    private Button deleteItem;

    @FXML
    private TextField deleteItemEID;

    @FXML
    private TableColumn<TableInventory, String> eIDCol;

    @FXML
    private TableColumn<TableInventory, String> eNameCol;

    @FXML
    private Button issueItem;

    @FXML
    private TextField issueItemEID;

    @FXML
    private TextField issueItemUID;

    @FXML
    private TextField itemNameTextFeild;

    @FXML
    private TextField itemIDTextFeild;

    @FXML
    private Button logoutButton;

    @FXML
    private Button nextPage;

    @FXML
    private Button returnItem;

    @FXML
    private TextField returnItemEID;

    @FXML
    private TableColumn<TableInventory, String> statusCol;

    @FXML
    private TableView<TableInventory> tableInventory;

    @FXML
    private Button updateButton;

    @FXML
    private RadioButton issuableItem;

    @FXML
    void addButtonPress(ActionEvent event) throws SQLException {
        String query = "INSERT INTO inventory(e_id,e_name,e_status) VALUES(?,?,?)";
        String logQuery = "INSERT into logs(e_id,u_id,time,action) values(?,?,?,'Added')";
        PreparedStatement pst,pst1 ;
        try {
            System.out.println("###### insert statement###");
            pst = conInventory.prepareStatement(query);
            String status;
            if(issuableItem.isSelected()) status="Available";
            else status="NON-Issue";
            pst.setString(1, this.itemIDTextFeild.getText());
            pst.setString(2, this.itemNameTextFeild.getText());
            pst.setString(3, status);
            System.out.println(query);
            pst.executeUpdate();
            System.out.println("###### insert statement completed##");


            System.out.println("Logs statement started");
            pst1 = conInventory.prepareStatement(logQuery);
            pst1.setString(1, this.itemIDTextFeild.getText());
            pst1.setString(2, LoginController.s1);
            pst1.setString(3, String.valueOf(java.time.LocalDate.now()));
            pst1.executeUpdate();
            System.out.println("####Log entry done####");

        } catch (Exception e) {
            System.out.println("Exception while executing add statement "+e);
        }
    }

    @FXML
    void deleteButtonPress(ActionEvent event) {
        String query = "DELETE FROM inventory where e_id=?";
        String logQuery = "INSERT into logs(e_id,u_id,time,action) values(?,?,?,'Delete')";
        PreparedStatement pst,pst1;
        try {
            System.out.println("###### delete statement###");
            pst = conInventory.prepareStatement(query);
            String id= this.deleteItemEID.getText();
            pst.setString(1, id);
            System.out.println(query);
            pst.executeUpdate();
            System.out.println("###### delete statement completed##");
            System.out.println("Logs statement started");
            pst1 = conInventory.prepareStatement(logQuery);
            pst1.setString(1,this.deleteItemEID.getText());
            pst1.setString(2,LoginController.s1);
            pst1.setString(3, String.valueOf(java.time.LocalDate.now()));
            pst1.executeUpdate();
            System.out.println("####Log entry done####");

        } catch (Exception e) {
            System.out.println("Exception while executing delete statement "+e);
        }
    }

    @FXML
    void getSelected(MouseEvent event) {

    }

    @FXML
    void issueButtonPress(ActionEvent event) {
        String query = "UPDATE inventory SET e_status=? , d_issue=?  where e_id=? AND e_status='Available' ";
        String logQuery = "INSERT into logs(e_id,u_id,x_u_id,time,action) values(?,?,?,?,'Issued')";
        PreparedStatement pst,pst1 ;
        try {
            System.out.println("###### Issue statement###");
            pst = conInventory.prepareStatement(query);
            String status;
            pst.setString(1, issueItemUID.getText());
            pst.setString(2, String.valueOf(java.time.LocalDate.now()));
            pst.setString(3, issueItemEID.getText());
            System.out.println(query);
            pst.executeUpdate();
            System.out.println("###### issue statement completed##");
            System.out.println("logs statement started");
            pst1 =conInventory.prepareStatement(logQuery);
            pst1.setString(1,this.issueItemEID.getText());
            pst1.setString(2,LoginController.s1);
            pst1.setString(3,this.issueItemUID.getText());
            pst1.setString(4, String.valueOf(java.time.LocalDate.now()));
            pst1.executeUpdate();
            System.out.println("Logs statement completed");
        } catch (Exception e) {
            System.out.println("Exception while executing issue statement "+e);
        }
    }

    @FXML
    void logoutButtonPress(ActionEvent event) {
        try {
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } catch (Exception var9) {
        }
    }

    @FXML
    void nextButtonPress(ActionEvent event) {
        try {
            Parent workViewParentInv;
            Scene workSceneInv;
            Stage windowInv;
            URL resource = this.getClass().getResource("credential_page.fxml");
            System.out.println("# INVENTORY CONTROLLER: Resource found at: " + resource);
            FXMLLoader loader = new FXMLLoader(resource);
            Parent rootInv = loader.load();
            System.out.println("# INVENTORY CONTROLLER: Parent root loaded");
            workSceneInv = new Scene(rootInv);
            windowInv = (Stage) ((Node) event.getSource()).getScene().getWindow();
            windowInv.setTitle("Credentials");
            windowInv.setScene(workSceneInv);
            windowInv.show();
            conInventory.close();
        }
        catch(Exception var9){
        }
    }

    @FXML
    void returnButtonPress(ActionEvent event) {
        String query = "UPDATE inventory SET e_status= 'Available' , d_issue = NULL where e_id=?;";
        String logQuery = "INSERT into logs(e_id,u_id,time,action) values(?,?,?,'Returned')";

        PreparedStatement pst,pst1;
        try {
            System.out.println("###### return statement###");
            pst = conInventory.prepareStatement(query);
            pst.setString(1, returnItemEID.getText());
            System.out.println(query);
            pst.executeUpdate();
            System.out.println("###### return statement completed##");

            System.out.println("logs statement started");
            pst1 =conInventory.prepareStatement(logQuery);
            pst1.setString(1,this.returnItemEID.getText());
            pst1.setString(2,LoginController.s1);
            pst1.setString(3, String.valueOf(java.time.LocalDate.now()));
            pst1.executeUpdate();
            System.out.println("Logs statement completed");

        } catch (Exception e) {
            System.out.println("Exception while executing issue statement "+e);
        }
    }

    @FXML
    void updateButtonPress(ActionEvent event) {
        loadTable();

    }
    void loadTable(){
        this.oblist.clear();

        try {
            ResultSet rs1 = conInventory.createStatement().executeQuery("select * from inventory " );

            while (rs1.next())
                this.oblist.add(new TableInventory(rs1.getString("e_id"), rs1.getString("e_name"), rs1.getString("e_status"), rs1.getString("d_issue")) );

        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        this.eIDCol.setCellValueFactory(new PropertyValueFactory("id"));
        this.eNameCol.setCellValueFactory(new PropertyValueFactory("name"));
        this.statusCol.setCellValueFactory(new PropertyValueFactory("status"));
        this.dateIssuedCol.setCellValueFactory(new PropertyValueFactory("date"));
        this.tableInventory.setItems(this.oblist);
    }

    ObservableList<TableInventory> oblist = FXCollections.observableArrayList();
}