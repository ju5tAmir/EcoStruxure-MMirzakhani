package com.se.ecostruxure_mmirzakhani.be;

public enum Currency {
    EURO("Euro", "EUR", "€"),
    USD("US Dollar", "USD", "$"),
    GBP("British Pound", "GBP", "£"),
    AUD("Australian Dollar", "AUD", "A$"),
    CAD("Canadian Dollar", "CAD", "C$"),
    CNY("Chinese Yuan", "CNY", "¥"),
    HKD("Hong Kong Dollar", "HKD", "HK$"),
    IDR("Indonesian Rupiah", "IDR", "Rp"),
    INR("Indian Rupee", "INR", "₹"),
    JPY("Japanese Yen", "JPY", "¥"),
    KRW("South Korean Won", "KRW", "₩"),
    MYR("Malaysian Ringgit", "MYR", "RM"),
    NZD("New Zealand Dollar", "NZD", "NZ$"),
    PHP("Philippine Peso", "PHP", "₱"),
    SGD("Singapore Dollar", "SGD", "S$"),
    THB("Thai Baht", "THB", "฿"),
    TWD("New Taiwan Dollar", "TWD", "NT$"),
    VND("Vietnamese Dong", "VND", "₫"),
    AED("U.A.E Dirham", "AED", "د.إ"),
    BGN("Bulgarian Lev", "BGN", "лв"),
    BRL("Brazilian Real", "BRL", "R$"),
    CHF("Swiss Franc", "CHF", "CHF"),
    CZK("Czech Koruna", "CZK", "CZK"),
    DKK("Danish Krone", "DKK", "DKK"),
    EGP("Egyptian Pound", "EGP", "EGP"),
    HUF("Hungarian Forint", "HUF", "HUF"),
    MDL("Moldova Lei", "MDL", "MDL"),
    MXN("Mexican Peso", "MXN", "MXN"),
    NOK("Norwegian Krone", "NOK", "NOK"),
    PLN("Polish Zloty", "PLN", "PLN"),
    RON("Romanian New Leu", "RON", "RON"),
    RSD("Serbian Dinar", "RSD", "RSD"),
    RUB("Russian Rouble", "RUB", "RUB"),
    SEK("Swedish Krona", "SEK", "SEK"),
    TRY("Turkish Lira", "TRY", "TRY"),
    UAH("Ukrainian Hryvnia", "UAH", "UAH"),
    ZAR("South African Rand", "ZAR", "ZAR"),
    AFN("Afghan afghani", "AFN", "AFN"),
    ALL("Albanian lek", "ALL", "ALL"),
    AMD("Armenia Dram", "AMD", "AMD"),
    ANG("Neth. Antillean Guilder", "ANG", "ANG"),
    AOA("Angolan kwanza", "AOA", "AOA"),
    ARS("Argentine Peso", "ARS", "ARS"),
    AWG("Aruban florin", "AWG", "AWG"),
    AZN("Azerbaijan Manat", "AZN", "AZN"),
    BAM("Bosnia and Herzegovina convertible mark", "BAM", "BAM"),
    BBD("Barbadian Dollar", "BBD", "BBD"),
    BDT("Bangladeshi taka", "BDT", "BDT"),
    BHD("Bahrain Dinar", "BHD", "BHD"),
    BIF("Burundian franc", "BIF", "FBu"),
    BND("Brunei Dollar", "BND", "B$"),
    BOB("Bolivian Boliviano", "BOB", "Bs."),
    BSD("Bahamian Dollar", "BSD", "B$"),
    BWP("Botswana Pula", "BWP", "P"),
    BYN("Belarussian Ruble", "BYN", "Br"),
    BZD("Belize dollar", "BZD", "BZ$"),
    CDF("Congolese franc", "CDF", "FC"),
    CLP("Chilean Peso", "CLP", "$"),
    COP("Colombian Peso", "COP", "$"),
    CRC("Costa Rican Colón", "CRC", "₡"),
    CUP("Cuban peso", "CUP", "$"),
    CVE("Cape Verde escudo", "CVE", "$"),
    DJF("Djiboutian franc", "DJF", "Fdj"),
    DOP("Dominican Peso", "DOP", "RD$"),
    DZD("Algerian Dinar", "DZD", "دج"),
    ERN("Eritrean nakfa", "ERN", "Nfk"),
    ETB("Ethiopian birr", "ETB", "Br"),
    FJD("Fiji Dollar", "FJD", "FJ$"),
    GEL("Georgian lari", "GEL", "₾"),
    GHS("Ghanaian Cedi", "GHS", "GH₵"),
    GIP("Gibraltar pound", "GIP", "£"),
    GMD("Gambian dalasi", "GMD", "D"),
    GNF("Guinean franc", "GNF", "FG"),
    GTQ("Guatemalan Quetzal", "GTQ", "Q"),
    GYD("Guyanese dollar", "GYD", "G$"),
    HNL("Honduran Lempira", "HNL", "L"),
    HTG("Haitian gourde", "HTG", "G"),
    ILS("Israeli New Sheqel", "ILS", "₪"),
    IQD("Iraqi dinar", "IQD", "ع.د"),
    IRR("Iranian rial", "IRR", "﷼"),
    ISK("Icelandic Krona", "ISK", "kr"),

    JMD("Jamaican Dollar", "JMD", "J$"),
    JOD("Jordanian Dinar", "JOD", "د.ا"),
    KES("Kenyan shilling", "KES", "KSh"),
    KGS("Kyrgyzstan Som", "KGS", "сом"),
    KHR("Cambodian riel", "KHR", "៛"),
    KMF("Comoro franc", "KMF", "CF"),
    KWD("Kuwaiti Dinar", "KWD", "د.ك"),
    KZT("Kazakhstani Tenge", "KZT", "₸"),
    LAK("Lao kip", "LAK", "₭"),
    LBP("Lebanese Pound", "LBP", "ل.ل"),
    LKR("Sri Lanka Rupee", "LKR", "රු"),
    LRD("Liberian dollar", "LRD", "$"),
    LSL("Lesotho loti", "LSL", "L"),
    LYD("Libyan Dinar", "LYD", "ل.د"),
    MAD("Moroccan Dirham", "MAD", "د.م."),
    MGA("Malagasy ariary", "MGA", "Ar"),
    MKD("Macedonian denar", "MKD", "ден"),
    MMK("Myanma Kyat", "MMK", "Ks"),
    MNT("Mongolian togrog", "MNT", "₮"),
    MOP("Macanese pataca", "MOP", "MOP$");


    //We will add more currencies
    private final String displayName;
    private final String currencyCode;
    private final String symbol;

    Currency(String displayName, String currencyCode, String symbol) {
        this.displayName = displayName;
        this.currencyCode = currencyCode;
        this.symbol = symbol;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getSymbol() {
        return symbol;
    }

    public static Currency getByCurrencyCode(String currencyCode) {
        for (Currency currency : values()) {
            if (currency.getCurrencyCode().equalsIgnoreCase(currencyCode)) {
                return currency;
            }
        }
        return null; // Or throw an exception if currency code not found
    }
}
