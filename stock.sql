create table companydata
(
    symbols         text   not null,
    companyName     text   not null,
    primaryExchange text   not null,
    open            double not null,
    close           double not null,
    latestPrice     double not null,
    latestTime      text   not null,
    latestVolume    double not null,
    changeInPrice   double not null,
    changePercent   double not null,
    avgTotalVolume  double not null,
    week52High      double not null,
    week52Low       double not null
);

create table keystat
(
    symbols             text   null,
    week52Change        double not null,
    year5ChangePercent  double not null,
    year2ChangePercent  double not null,
    year1ChangePercent  double not null,
    ytd1ChangePercent   double not null,
    month6ChangePercent double not null,
    month3ChangePercent double not null,
    month1ChangePercent double not null,
    day5ChangePercent   double not null,
    day30ChangePercent  double not null
);

create table screenedlist
(
    symbol text null
);

create table sym_table
(
    symbol text not null
);

create table users
(
    user     text null,
    password text null
);

