/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.ServerController;
import domain.Administrator;
import domain.Kupac;
import domain.Porudzbina;
import domain.Predmet;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

/**
 *
 * @author Asus
 */
public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();
                Response response = handleRequest(request);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response(null, null, ResponseStatus.Success);
        try {
            switch (request.getOperation()) {
                case Operation.ADD_PREDMET:
                    ServerController.getInstance().addPredmet((Predmet) request.getData());
                    break;
                case Operation.ADD_KUPAC:
                    ServerController.getInstance().addKupac((Kupac) request.getData());
                    break;
                case Operation.ADD_PORUDZBINA:
                    ServerController.getInstance().addPorudzbina((Porudzbina) request.getData());
                    break;
                case Operation.DELETE_KUPAC:
                    ServerController.getInstance().deleteKupac((Kupac) request.getData());
                    break;
                case Operation.DELETE_PORUDZBINA:
                    ServerController.getInstance().deletePorudzbina((Porudzbina) request.getData());
                    break;
                case Operation.UPDATE_KUPAC:
                    ServerController.getInstance().updateKupac((Kupac) request.getData());
                    break;
                case Operation.UPDATE_PORUDZBINA:
                    ServerController.getInstance().updatePorudzbina((Porudzbina) request.getData());
                    break;
                case Operation.GET_ALL_ADMINISTRATOR:
                    response.setData(ServerController.getInstance().getAllAdministrator());
                    break;
                case Operation.GET_ALL_KUPAC:
                    response.setData(ServerController.getInstance().getAllKupac());
                    break;
                case Operation.GET_ALL_KNJIGA:
                    response.setData(ServerController.getInstance().getAllKnjiga());
                    break;
                case Operation.GET_ALL_PORUDZBINA:
                    response.setData(ServerController.getInstance().getAllPorudzbina());
                    break;
                case Operation.GET_ALL_ZANR:
                    response.setData(ServerController.getInstance().getAllZanr());
                    break;
                case Operation.GET_ALL_STAVKA_PORUDZBINE:
                    response.setData(ServerController.getInstance().getAllStavkaPorudzbine((Porudzbina) request.getData()));
                    break;
                case Operation.LOGIN:
                    Administrator administrator = (Administrator) request.getData();
                    Administrator ulogovani = ServerController.getInstance().login(administrator);
                    response.setData(ulogovani);
                    break;
                default:
                    return null;
            }
        } catch (Exception e) {
            response.setResponseStatus(ResponseStatus.Error);
            response.setException(e);
        }
        return response;
    }

}
