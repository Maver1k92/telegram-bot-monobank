import com.mybot1.feature.currency.CurrencyService;
import com.mybot1.feature.currency.MonoBankCurrencyService;
import com.mybot1.feature.currency.dto.Currency;
import com.mybot1.feature.currency.ui.PrettyPrintCurrencyService;

public class TelegramBotApp {
    public static void main(String[] args) {
        CurrencyService currencyService =  new MonoBankCurrencyService();

        Currency usd = Currency.USD;
        double rate = currencyService.getRate(usd);

       String prettyText = new PrettyPrintCurrencyService().convert(rate, usd);

        System.out.println(prettyText);
    }
}
