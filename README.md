

После запуска микросервиса

базза даных будет создана автоматичски через конфигурацию пректа flyway 
доступны нескольок endpont 

Get maping : 
/transaction-limits - выводит все существующие лимиты в базе 

Post maping :
/transactions - сохдание новой транзакции

example Json body 
{
    "accountFrom":259897,
    "accountTo":2588895,
    "currencyShortname":"KZT",
    "datetime": "2024-04-22T12:00:00",
    "expenseCategory":"Service",
    "sum":120000
}

/transaction-limits/set-limit - установка нового лимита 

example Json body 
{
    "account": 259897,
    "limit": 3200.0
}
