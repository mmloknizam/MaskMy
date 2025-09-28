import klijent.forme.SingInForm;
import server.forme.ServerskaForma;


void main(String[] args) {

    Thread serverThread = new Thread(() -> ServerskaForma.main(args));
    serverThread.setName("ServerThread");
    serverThread.start();

    Thread clientThread = new Thread(() -> SingInForm.main(args));
    clientThread.setName("ClientThread");
    clientThread.start();
}
