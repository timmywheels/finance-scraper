public class Main {

    public static void main(String[] args) {

        Credentials key = new Credentials();

        Auth.webClient(key.getLoginUrl(), key.getUsername(), key.getPassword());

        Api server = new Api();
        server.api();

        Fetch.data();

    }

}

