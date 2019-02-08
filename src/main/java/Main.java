public class Main {

    public static void main(String[] args) {
            String baseUrl = "https://finance.yahoo.com";
            String loginUrl = "https://login.yahoo.com/config/login?.intl=us&.lang=en-US&.src=finance&.done=https%3A%2F%2Ffinance.yahoo.com%2F";
            String username = "tim@timwheeler.com";
            String password = "Scraper2019!!" ;
            String portfolioUrl = "https://finance.yahoo.com/portfolio/p_0/view/v1";

            Auth.webClient(loginUrl, username, password, portfolioUrl);

        }

    }

