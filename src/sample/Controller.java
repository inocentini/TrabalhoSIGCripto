package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller {
    @FXML
    private Button btnCifrar;

    @FXML
    private Button btnDecifrar;

    @FXML
    private Button btnClear;

    @FXML
    private Label lbPlainText;

    @FXML
    private TextField txtPlainText;

    @FXML
    private Label lbCipher;

    @FXML
    private TextField txtCipherText;

    private char key[];
    private byte text;

    public Controller() {
        key = new char[5];
        key[0] = 255;
        key[1] = 170;
        key[2] = 125;
        key[3] = 240;
        key[4] =  15;
    }

    @FXML
    void btnCifrarEvent(MouseEvent event) {
        int size = txtPlainText.getText().length();
        if(size == 0) return;
        if(size%2 !=0){
            size++;
            txtPlainText.setText(txtPlainText.getText()+" ");
        }

        char F,aux, bloco[];
        bloco = new char[2];


        char cipherText[] = txtPlainText.getText().toCharArray();
        int half = size/2;

        for(int i =0; i<half; i++){
            bloco[0] = cipherText[i*2];
            bloco[1] = cipherText[i*2+1];
            for(int j=0;j<5;j++){
                F = (char)((int)bloco[1] & (int)key[j]);
                bloco[0] = (char)((int)bloco[0] ^ (int)F);
                aux = bloco[0];
                bloco[0] = bloco[1];
                bloco[1] = aux;
            }
            cipherText[i*2] = bloco[1];
            cipherText[i*2+1] = bloco[0];
        }
        txtCipherText.setText(String.valueOf(cipherText));
    }

    @FXML
    void btnClearEvent(MouseEvent event) {

    }

    @FXML
    void btnDecifrarEvent(MouseEvent event) {
        int size = txtCipherText.getText().length();
        if(size == 0) return;
        if(size%2 !=0){
            size++;
            txtCipherText.setText(txtCipherText.getText()+" ");
        }

        char F,aux, bloco[];
        bloco = new char[2];


        char plainText[] = txtCipherText.getText().toCharArray();
        int half = size/2;

        for(int i =0; i<half; i++){
            bloco[0] = plainText[i*2];
            bloco[1] = plainText[i*2+1];
            for(int j=4;j>=0;j--){
                F = (char)((int)bloco[1] & (int)key[j]);
                bloco[0] = (char)((int)bloco[0] ^ (int)F);
                aux = bloco[0];
                bloco[0] = bloco[1];
                bloco[1] = aux;
            }
            plainText[i*2] = bloco[1];
            plainText[i*2+1] = bloco[0];
        }
        txtPlainText.setText(String.valueOf(plainText));

    }

}
