## 01- Microservices architecture
![Badge 1](http://img.shields.io/static/v1?label=Microservice&message=Architecture:&color=GREEN&style=for-the-badge)

Em algumas situações (ver diagrama abaixo) da minha arquitetura de microsserviços utilizei da programação reativa, programando por meio de fluxos de dados assíncronos, sem interromper ou bloquear outras tarefas igualmente importantes, onde o código do remetente da mensagem não espera uma resposta. Ele apenas envia a mensagem para uma fila do RabbitMQ .

Nestes fluxos assíncronos, não bloqueantes, lancei mão de ‘mensagens de evento’ e ‘mensagens de comando’.

Entretanto, existe uma desvantagem ao lançarmos mão de Brokers: perdemos o controle de quando nossas instruções serão executadas, ocorrendo consistência eventual (inclusive quando optamos por replicar algumas tabelas), tendo que esperar toda a mensagem ser propagada para a arquitetura como um todo.

Assim sendo, em apenas uma situação do diagrama abaixo, implementei comunicação síncrona GET Feign, modelo request-response, comunicação bloqueante, forte consistência, forte acoplamento, em que a Api-User vai até a Api-TrafficFineRecord buscar por este dado atualizado.





![f01- Foto Draw-TrafficFineRecord, v4 base Linkedin](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/8bf6956e-4ffb-4d88-9898-fa0596b95614)




## 02- Config Server
![Badge 1](http://img.shields.io/static/v1?label=Config%20Server&message=em:&color=GREEN&style=for-the-badge)

```
https://github.com/SergioEGLira/CServer-Ms_Trfc_Fine_Record 
```



## 03- Quanto aos Containers Docker do PostgresSQL e do RabbitMQ
![Badge 1](http://img.shields.io/static/v1?label=Containers%20Docker&message=Postgres_SQL%20e%20Rabbit_MQ:&color=GREEN&style=for-the-badge)

Lancei mão de containers Docker para instanciar o PostgresSQL e o Broker RabbitMQ, desta forma, não precisei usar do servidor de banco de dados em minha máquina e, também, ficou desnecessário configuração prévia no CloudAMQP, inclusive criação de conta e o manuseio de informações sensíveis, como da AMQP URL no application.properties das minhas APIs.

- Implementação dos containers:
```
docker run -p 5433:5432 --name container-pg12 -e POSTGRES_USER=sergio-eduardo -e POSTGRES_PASSWORD=321 -e POSTGRES_DB=api-user postgres:12-alpine

mkdir -p /docker/rabbitmq/data

docker run --name dck-rabbitmq -p 5672:5672 -p 15672:15672 --hostname rabbitmq-master -v /docker/rabbitmq/data:/var/lib/rabbitmq rabbitmq:3.9-management
```




![f02, 02- Captura de Tela (788)](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/13427647-6061-4f10-96e3-bef43be53292)




## 04- Perspectiva do Eclipse Dashboard
![Badge 1](http://img.shields.io/static/v1?label=Eclipse&message=Dashboard:&color=GREEN&style=for-the-badge)




![f03, 03- Captura de Tela (789)](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/e6f91684-b82f-4cab-b8d6-e1c8d0f1ea6f)



## 05- Perspectiva do Eureka Dashboard
![Badge 1](http://img.shields.io/static/v1?label=Eureka!&message=Dashboard:&color=GREEN&style=for-the-badge)




![f04, 05new - Eureka, Captura de Tela (809)](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/400a84af-bed7-41c5-a81f-2f12078131fa)



## 06- Perspectiva das Databases do Pg Admin
![Badge 1](http://img.shields.io/static/v1?label=Postgre_SQL&message=Databases:&color=GREEN&style=for-the-badge)

A base de dados é distribuída, cada uma com suas próprias responsabilidades, porém que conversam entre si.




![f05, 05- Captura de Tela (802)](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/a28718ce-5831-4a78-8d14-f5b6f34f7bee)





## 07- Perspectivas do RabbitMQ

![Badge 1](http://img.shields.io/static/v1?label=Rabbit_MQ&message=Exchanges:&color=GREEN&style=for-the-badge)



![f06, 07 1new, Rabbit Exchanges, Captura de Tela (810)](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/6a9ea0e1-ce7f-457f-b7b8-5547015d4b4e)




![Badge 1](http://img.shields.io/static/v1?label=Rabbit_MQ&message=Queues:&color=GREEN&style=for-the-badge)




![f07, 07 2new, Rabbir Queues, Captura de Tela (811)](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/571a2fb1-549a-4210-90ba-0f2c5e1d31f1)





## 08- Perspectiva do Postman Environment
![Badge 1](http://img.shields.io/static/v1?label=Postman&message=environment%20variables:&color=GREEN&style=for-the-badge)




![f08, 08- Captura de Tela (801)](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/d51a01a3-bd36-431b-8b35-4946a4763010)



## 09- Database Seeder

Não utilizei UUIDs nas tabelas ‘tb_user’, ‘tb_automovel’ e ‘tb_infracao’, pois decidi implementar os identificadores já consolidados ‘cpf’, ‘placa do carro’ e ‘código da infração de trânsito’, respectivamente.

![Badge 1](http://img.shields.io/static/v1?label=Tb_Roles&message=da%20Api-User:&color=GREEN&style=for-the-badge)



![f09, 91new, TB ROLES Captura de Tela (816)](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/87244e91-7ebf-4cee-91a1-fd427e4d932f)





![Badge 1](http://img.shields.io/static/v1?label=Tb_User&message=da%20Api-User:&color=GREEN&style=for-the-badge)



![f10, 92NEW, TB USER, Captura de Tela (817)](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/b86bf6f7-6b70-4938-8fd4-0b0776a9b936)





![Badge 1](http://img.shields.io/static/v1?label=Tb_Users_Roles&message=da%20Api-User:&color=GREEN&style=for-the-badge)



![f11, 93NEW, TB USERS ROLES, Captura de Tela (818)](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/12da4659-b3c5-4065-b40b-e58ec356c2fb)





![Badge 1](http://img.shields.io/static/v1?label=Tb_Automóvel&message=da%20Api-TrafficFineRecord:&color=GREEN&style=for-the-badge)



![f12, 94NEW, TB AUTOMOVEL, Captura de Tela (819)](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/64d4b547-b892-47b9-9c78-305c0d0b7849)





![Badge 1](http://img.shields.io/static/v1?label=Tb_Autuação&message=da%20Api-TrafficFineRecord:&color=GREEN&style=for-the-badge)



![f13, 95NEW, TB AUTUAÇÃO Captura de Tela (820)](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/8df03341-2fcb-449b-bbaf-92504a600cec)





![Badge 1](http://img.shields.io/static/v1?label=Tb_Infração&message=da%20Api-TrafficFineRecord:&color=GREEN&style=for-the-badge)



![f14, 96NEW, TB INFRAÇÃO, Captura de Tela (821)](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/73e3fd17-ed96-4e7f-853e-4fa3eba541f0)




![Badge 1](http://img.shields.io/static/v1?label=Tb_User_Replica&message=da%20Api-TrafficFineRecord:&color=GREEN&style=for-the-badge)

Buscando maior disponibilidade na arquitetura, a API-User replica seus dados (‘tb_user’) para a API-TrafficFineRecord.



![f15, 97NEW, TB USER REPLICA, Captura de Tela (822)](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/fe104571-b1ed-4c93-a553-2a4a4412f80e)





![Badge 1](http://img.shields.io/static/v1?label=Tb_Twilio&message=da%20Api-SmsMessage:&color=GREEN&style=for-the-badge)

Lembrando que na minha arquitetura, a API-SmsMessage não possui endpoint autônomo/independente para envio de Twilio SMS.

Necessariamente, a API-SmsMessage deve ser provocada, através de comando assíncrono, pela API-TrafficFineRecord para, somente então, ter seu endpoint ativado e efetivamente enviar SMS.

Resumindo, a API-TrafficFineRecord ao registrar nova autuação de trânsito emite mensagem de comando para a API-SmsMessage que, por sua vez, envia e salva em seu próprio banco de dados a respectiva mensagem SMS.




![f16, 98NEW, TB TWILIO, Captura de Tela (823)](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/381e04d1-a1a2-4b93-8eab-0d3bace707ee)







## 10- Configurações necessárias para o envio de SMS utilizando o Twilio
![Badge Prof.Nélio](http://img.shields.io/static/v1?label=Prof.%20Nélio%20Alves,%20SDS-%20DSMeta&message=Semana%20Spring-React,%20Edicao%20Julho%202022&color=GREEN&style=for-the-badge)

O trecho da vídeo-aula entre **“1h09min e 1h34min” do Epísódio 2 do DSMeta, do Prof.Nélio Alves**, foi tomado por mim como parâmetro base na implementação da Api-SmsMessage (Twilio SMS).

Por padrão, preferi tornar inativo o efetivo envio de SMS (caso você opte por testar a minha arquitetura de microsserviços **sem a necessidade de, obrigatoriamente, ter de se inscrever no site "https://www.twilio.com/"**). 

Porém, caso você opte por efetivamente enviar SMS, **necessário cumprir os 03 passos abaixo**:

### Passo 01 de 03:   Necessário criar conta no site:
```
https://www.twilio.com/
```
e copiar as informações abaixo, que deverão ser inseridas como variável de ambiente no seu Eclipse:




![f17, 18- Captura de Tela (805)](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/0ee67298-35f1-4502-934b-9afd5a25a75a)




### Passo 02 de 03:   Necessário inserir as seguintes variáveis de ambiente:


- Api SmsMessage
```
twilio.sid=${TWILIO_SID}
twilio.key=${TWILIO_KEY}
twilio.phone.from=${TWILIO_PHONE_FROM}
twilio.phone.to=${TWILIO_PHONE_TO}
```
Lembrando que 'TWILIO_PHONE_TO' deve ser o seu número de telefone pessoal que ficou cadastrado no site Twilio.



![f18, 17- Captura de Tela (804)](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/5f67dd16-2156-4a6e-860e-88b669311046)



### Passo 03 de 03:   Necessário, também, **descomentar** as linhas 21 a 31 e as linhas 48 a 58 da classe TwilioSmsService.java, conforme ambas as fotos abaixo:



![f19, 19 Captura de Tela (806)](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/26e18149-11d2-42b8-be18-0705241d1da9)






![f20, 20- Captura de Tela (807)](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/98ea3571-01f7-4292-a3f1-493048b2c41d)




Seguem algumas amostras de SMSs que foram emitidos pelo Twilio enquanto eu estava em fase de testes da minha estrutura de microsserviços:




![f21, 22- SMS IMG-20230616-WA0003](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/58a06a3c-058d-4be9-8fab-e3644cec88a0)



## 11- Application.properties da Api-SmsMessage, quanto ao padrão Strategy no envio de SMS

![Badge 1](http://img.shields.io/static/v1?label=Padrão&message=Strategy&color=GREEN&style=for-the-badge)



![f22, 22- Padrão Strategy Captura de Tela (808)](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/cb25a1d4-9f3d-460f-b30b-7d3d1730cb83)




### Caso você rode toda a estrutura da arquitetura de microsserviços e não cumpra o 'passo 03 de 03' do item anterior (deixando as linhas comentadas como constante das 2 fotos), a conexão com o Twilio continuará inerte, logo:

- **spring.profiles.active=testMockSms** gerará Log, mas não enviará mensagem SMS e também não salvará a mensagem em banco de dados.
- **spring.profiles.active=testH2TwilioSms** gerará Log, mas não enviará mensagem SMS, mas salvará a mensagem no banco H2.
- **spring.profiles.active=devTwilioSmsPostgreSql** gerará Log, mas não enviará mensagem SMS, mas salvará a mensagem no banco PostgresSQL.

  
### Caso você cumpra todos os 'passos 01 ao 03' do item anterior (ativando a conexão com o Twilio) e, em seguida, rode toda a estrutura da arquitetura de microsserviços:

- **spring.profiles.active=testMockSms** gerará Log, mas não enviará mensagem SMS e também não salvará a mensagem em banco de dados.
- **spring.profiles.active=testH2TwilioSms** gerará Log e enviará mensagem SMS para o seu celular e, tembém, salvará a mensagem no banco H2.
- **spring.profiles.active=devTwilioSmsPostgreSql** gerará Log e enviará mensagem SMS para o seu celular e, também, salvará a mensagem no banco PostgresSQL.

## 12- Demais application.properties

- **spring.profiles.active=test** banco de dados H2.
- **spring.profiles.active=dev** banco de dados PostgresSQL.



![f23, app properties-Traffic Fine Record](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/fd67b184-4f46-4b3e-94d4-c3dce11b3d20)




![f24, app properties-User](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/42697e3d-cc24-474d-a6d6-4c73f6f24ee1)






## 13- Quanto a Autenticação e Autorização distribuída
![Badge 1](http://img.shields.io/static/v1?label=Spring&message=Security&color=GREEN&style=for-the-badge)

A arquitetura de microsserviços possui autenticação e autorização de forma distribuída (Spring Security). 

A API-User, além de fazer o controle de usuários, também gera o token, implementando o controle de autenticação.

Em seguida, este mesmo token transita pelo restante da arquitetura.

As outras APIs da arquitetura também possuem autenticação, mas apenas para checar as credenciais do token que recebem (anteriormente gerado pela API-User) e validar as roles permissions.
 
No payload do JWT decidi incluir 03 informações que julguei relevantes: o ‘cpf’, o ‘nome completo’ e, ainda, ‘a listagem de ROLES’ do usuário que solicitou a geração deste token.

Já o Service Registry e o Config Server possuem apenas Basic Authentication.



## 14- Requisições que implementei
![Badge 1](http://img.shields.io/static/v1?label=Postman&message=collections%20,%20environment%20variables:&color=GREEN&style=for-the-badge)

- Estão na raiz do projeto tanto o arquivo de testes quanto o arquivo contendo a variável de ambiente do Postman.
- Quanto à variável de ambiente, **deixar em branco ambos os campos INITIAL VALUE e CURRENT VALUE referentes à variável 'token'**, pois serão preenchidos automaticamente com o token corrente assim que algum usuário efetuar login.
- No arquivo Seed import.sql da Api-User a variável ‘password’ foi previamente criptografada com o algoritmo decript.
- Implementei dois tipos de RoleType: **ROLE_USER** e **ROLE_ADMIN**
- Como regra de negócio, decidi não implementar deleção em castata de usuário, uma vez que, ao deletar usuário em cascata, automaticamente estaríamos também apagando todas as multas/autuações atreladas a ele...



![f25, PostmanCollections](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/fc1f386a-9b5f-4309-aa88-65e3907b70eb)





![f26, PostmanEnvironments2](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/fc8918a0-6f2b-4de6-9482-126b1d54eb50)



### 14.1- Testes iniciais (necessário implementar previamente os três Seeds import.sql em cada uma das APIs)



![f27, PostmanInitialTests](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/2fa12406-77cd-4604-a92d-fffc8be77d4f)













![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

A Aparecida/Admin efetua login no sistema (lembrando que a aparecida já estava cadastrada no Seed import.sql da Api-User):

POST  {{UserUrl}}/user/login
>>>Request Body:
```
{  
  "username": "04username",
  "password": "04AparecidaToken"
}


```

>>>Response Body:
```
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMzcuMDEzLjIyNi0zNSIsInJvbGVzIjoiUk9MRV9BRE1JTiIsIm5vbWUiOiJBcGFyZWNpZGEgQWxpbmUgQmVybmFyZGVzIGRhIFBheiIsImlhdCI6MTY4NzU2MjMxMywiZXhwIjoxNjg3NTc2NzEzfQ.40J-kHH_Wz2kf5pumwx6E8Nr3ijvew6fsJdPFT6cGKgWaXIR5vFjudUp5XLL503V7jrsyDeLs35hY20JRwy1gg",
    "type": "Bearer"
}

```



![f28, token Y1gg Captura de Tela (829) 1gg](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/54cffb48-3ec1-4ac3-8675-135c70eb7f0e)




![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Aparecida/Admin cadastra o Juan, também como Admin:

POST  {{UserUrl}}/user/signup

>>>Request Body:
```
{
  "cep": "49043452",
  "complemento": "Viz à praça",
  "cpf": "176.002.775-81",
  "email": "juan-dacosta76@htomail.com",
  "nome": "Juan Tiago da Costa",
  "username": "ADMIN-j-Username",
  "numero": 7391,
  "telefone": "(79) 99502-3388",
  "password": "ADMIN-juan01Psrd",
  "userType": "ADMIN"
}

```

>>>Response Body:
```
{
    "cpf": "176.002.775-81",
    "nome": "Juan Tiago da Costa",
    "username": "ADMIN-j-Username",
    "email": "juan-dacosta76@htomail.com",
    "logradouro": "Rua C",
    "numero": 7391,
    "complemento": "Viz à praça",
    "bairro": "Santa Maria",
    "localidade": "Aracaju",
    "uf": "SE",
    "cep": "49043-452",
    "telefone": "(79) 99502-3388",
    "password": "$2a$10$kQXC.RoEq.U5VlWNxhfP5eNLh.5qxs6gYBodUWuOnFSZT84A7bsye",
    "userType": "ADMIN"
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

O Juan/Admin faz login no sistema:

POST  {{UserUrl}}/user/login

>>>Request Body:
```
{  
  "username": "ADMIN-j-Username",
  "password": "ADMIN-juan01Psrd"
}


```

>>>Response Body:
```
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNzYuMDAyLjc3NS04MSIsInJvbGVzIjoiUk9MRV9BRE1JTiIsIm5vbWUiOiJKdWFuIFRpYWdvIGRhIENvc3RhIiwiaWF0IjoxNjg3NTYyMzM4LCJleHAiOjE2ODc1NzY3Mzh9.DvqFwtdyQE91XJpaXkw7ejCnMYFOx1dih7yQsWdHs2UDdbMM4Dy30Eepc8Mb0nM194UsX2j9sb7tZk73Dh1nnA",
    "type": "Bearer"
}

```



![f29, token nna Captura de Tela (830) nnA](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/4215cd6d-ad3e-42d9-ba7f-f82df35d18b4)




![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

O Juan/Admin cadastra a Carolina, como User:

POST  {{UserUrl}}/user/signup

>>>Request Body:
```
{
  "cep": "09930380",
  "complemento": "Conjunto 2, Casa 3a",
  "cpf": "856.474.950-52",
  "email": "carolina_costa@fixacomunicacao.com.br",
  "nome": "Carolina Sophia Alice Costa",
  "username": "USER-c-Username",
  "numero": 572,
  "telefone": "(11) 99889-0350",
  "password": "USERcarolina01Psrd",
  "userType": "USER"
}

```

>>>Response Body:
```
{
    "cpf": "856.474.950-52",
    "nome": "Carolina Sophia Alice Costa",
    "username": "USER-c-Username",
    "email": "carolina_costa@fixacomunicacao.com.br",
    "logradouro": "Rua Eugênia S Vitale",
    "numero": 572,
    "complemento": "Conjunto 2, Casa 3a",
    "bairro": "Taboão",
    "localidade": "Diadema",
    "uf": "SP",
    "cep": "09930-380",
    "telefone": "(11) 99889-0350",
    "password": "$2a$10$kKaXK/rzcbjIXfE6bWcvmOFyaH9m0YWAX7aa8e6J9dNxnAKvpTXA2",
    "userType": "USER"
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

O Juan/Admin cadastra multa na Carolina/User:

POST  {{TrafficFineRecordUrl}}/autuação

>>>Request Body:
```
{
  "codigoDaInfracao": 53390,
  "placaDoCarro": "NEH-8723",
  "cpf": "856.474.950-52"
}

```

>>>Response Body:
```
{
    "idApiAutuacao": "84d216fd-367a-4b7a-939c-cafd3011d673",
    "placaDoCarro": "NEH-8723",
    "createdAt": "2023-06-23T20:19:10.9182038",
    "status": "NAO_PAGA",
    "codigoDaInfracao": 53390,
    "infracaoDeTransito": "Deixar o condutor de prestar socorro vítima acidente de trânsito, qdo solicit por agente",
    "infracaoPontosNaCarteira": "05pts, Infração Grave",
    "infracaoValorDaMulta": 195.23,
    "automovelRenavam": 38533649312,
    "automovelMarca": "VW - VolksWagen",
    "automovelModelo": "Saveiro CROSSOVER 1.6 Mi Total Flex 8V",
    "automovelAno": 1992,
    "automovelCor": "Vermelho",
    "authUserNome": "Carolina Sophia Alice Costa",
    "authUserEmail": "carolina_costa@fixacomunicacao.com.br",
    "cpf": "856.474.950-52",
    "authUserLogradouro": "Rua Eugênia S Vitale",
    "authUserNumero": 572,
    "authUserComplemento": "Conjunto 2, Casa 3a",
    "authUserBairro": "Taboão",
    "authUserLocalidade": "Diadema",
    "authUserUf": "SP",
    "authUserCep": "09930-380",
    "authUserTelefone": "(11) 99889-0350"
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Novamente, Juan/Admin cadastra nova multa na Carolina/User:


POST  {{TrafficFineRecordUrl}}/autuação

>>>Request Body:
```
{
  "codigoDaInfracao": 50291,
  "placaDoCarro": "JKY-8851",
  "cpf": "856.474.950-52"
}

```

>>>Response Body:
```
{
    "idApiAutuacao": "998a35f9-0fbf-4df6-b57f-5562cb98b3cb",
    "placaDoCarro": "JKY-8851",
    "createdAt": "2023-06-23T20:19:15.5568029",
    "status": "NAO_PAGA",
    "codigoDaInfracao": 50291,
    "infracaoDeTransito": "Dirigir veiculo com CNH / PPD / ACC cassada",
    "infracaoPontosNaCarteira": "07pts, Infração Gravíssima",
    "infracaoValorDaMulta": 880.41,
    "automovelRenavam": 28599731311,
    "automovelMarca": "Fiat",
    "automovelModelo": "Strada Adv/Adv TRYON 1.8 mpi Flex 8V CE",
    "automovelAno": 2003,
    "automovelCor": "Vermelho",
    "authUserNome": "Carolina Sophia Alice Costa",
    "authUserEmail": "carolina_costa@fixacomunicacao.com.br",
    "cpf": "856.474.950-52",
    "authUserLogradouro": "Rua Eugênia S Vitale",
    "authUserNumero": 572,
    "authUserComplemento": "Conjunto 2, Casa 3a",
    "authUserBairro": "Taboão",
    "authUserLocalidade": "Diadema",
    "authUserUf": "SP",
    "authUserCep": "09930-380",
    "authUserTelefone": "(11) 99889-0350"
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

A Carolina/User resolve fazer login:

POST  {{UserUrl}}/user/login

>>>Request Body:
```
{  
  "username": "USER-c-Username",
  "password": "USERcarolina01Psrd"
}


```

>>>Response Body:
```
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4NTYuNDc0Ljk1MC01MiIsInJvbGVzIjoiUk9MRV9VU0VSIiwibm9tZSI6IkNhcm9saW5hIFNvcGhpYSBBbGljZSBDb3N0YSIsImlhdCI6MTY4NzU2MjM1OSwiZXhwIjoxNjg3NTc2NzU5fQ.r21u3_J0QHEr95OlQMZIF_xtlCUtygNjALnWObvAWl5bLgAKrQThlZYk-gTPATQhp2Ozv8WOoDROXRv7FmWr6g",
    "type": "Bearer"
}

```



![f30, token r6g, Captura de Tela (831) r6g](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/b1a2dfd8-f345-4e8a-a4c8-3b7e8d627689)







![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Desavisadamente, a Carolina/User tenta cadastrar a Mariah, o servidor entendeu a sua solicitação, mas se recusa a autorizá-la a efetuar o cadastro:


POST  {{UserUrl}}/user/signup

>>>Request Body:
```
{
  "cep": "74860900",
  "complemento": "viz. ao mercado",
  "cpf": "080.329.127-20",
  "email": "mariah_aparecida_ribeiro@tlmix.com.br",
  "nome": "Mariah Aparecida Valentina Ribeiro",
  "username": "USER-m-Username",
  "numero": 772,
  "telefone": "(62) 98141-7318",
  "password": "USERmariah01Psrd",
  "userType": "USER"
}


```

>>>Response Body:
```
{
    "timestamp": "2023-06-23T23:19:46.283+00:00",
    "status": 403,
    "error": "Forbidden",
    "message": "",
    "path": "/api-user/user/signup"
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Carolina/User relmente não é fácil... tentou promover a sí mesma como newAdmin, porém tudo o que conseguiu foi um belíssimo 'Forbidden':

POST  {{UserUrl}}//user/newadmin

>>>Request Body:
```
{  
  "cpf": "856.474.950-52"
}

```

>>>Response Body:
```
{
    "timestamp": "2023-06-23T23:19:51.944+00:00",
    "status": 403,
    "error": "Forbidden",
    "message": "",
    "path": "/api-user/user/newadmin"
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

A Carolina/User tentou cadastrar multa (acredito que, a esta altura do campeonato, vocês já imaginam o que aconteceu):

POST  {{TrafficFineRecordUrl}}/autuação

>>>Request Body:
```
{
  "codigoDaInfracao": 53980,
  "placaDoCarro": "AFJ-8632",
  "cpf": "176.002.775-81"
}

```

>>>Response Body:
```
{
    "timestamp": "2023-06-23T23:20:00.624+00:00",
    "status": 403,
    "error": "Forbidden",
    "message": "",
    "path": "/api-trafficFineRecord/autuacao"
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Muito curiosa, a Carolina/User tenta buscar lista de todas as autuações que constam em CPF que não é o seu... (ver foto acima, no Payload do token, o CPF da Carolina/User):

GET  {{UserUrl}}/user/autuacao/176.002.775-81/getallautuacoesbyuseronlymytokencpf

>>>Request Body:
```

```

>>>Response Body:
```
{
    "timestamp": "2023-06-23T23:20:06.854505400Z",
    "status": 400,
    "path": "/api-user/user/autuacao/176.002.775-81/getallautuacoesbyuseronlymytokencpf",
    "message": "O Cpf do usuário procurado é diferente do Cpf do token recebido..."
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Finalmente a nossa Carolina/User pesquisou lista das autuações que lhe dizem respeito (só as do seu próprio CPF):

GET  {{UserUrl}}/user/autuacao/856.474.950-52/getallautuacoesbyuseronlymytokencpf

>>>Request Body:
```

```

>>>Response Body:
```
[
    {
        "idApiAutuacao": "84d216fd-367a-4b7a-939c-cafd3011d673",
        "placaDoCarro": "NEH-8723",
        "createdAt": "2023-06-23T20:19:10",
        "updatedAt": null,
        "status": "NAO_PAGA",
        "infracao": {
            "codigoDaInfracao": 53390,
            "infracaoDeTransito": "Deixar o condutor de prestar socorro vítima acidente de trânsito, qdo solicit por agente",
            "pontosNaCarteira": "05pts, Infração Grave",
            "valorDaMulta": 195.23
        },
        "automovel": {
            "placa": "NEH-8723",
            "renavam": 38533649312,
            "marca": "VW - VolksWagen",
            "modelo": "Saveiro CROSSOVER 1.6 Mi Total Flex 8V",
            "ano": 1992,
            "cor": "Vermelho"
        },
        "userPartialReplica": {
            "cpf": "856.474.950-52",
            "nome": "Carolina Sophia Alice Costa",
            "email": "carolina_costa@fixacomunicacao.com.br",
            "logradouro": "Rua Eugênia S Vitale",
            "numero": 572,
            "complemento": "Conjunto 2, Casa 3a",
            "bairro": "Taboão",
            "localidade": "Diadema",
            "uf": "SP",
            "cep": "09930-380",
            "telefone": "(11) 99889-0350"
        }
    },
    {
        "idApiAutuacao": "998a35f9-0fbf-4df6-b57f-5562cb98b3cb",
        "placaDoCarro": "JKY-8851",
        "createdAt": "2023-06-23T20:19:15",
        "updatedAt": null,
        "status": "NAO_PAGA",
        "infracao": {
            "codigoDaInfracao": 50291,
            "infracaoDeTransito": "Dirigir veiculo com CNH / PPD / ACC cassada",
            "pontosNaCarteira": "07pts, Infração Gravíssima",
            "valorDaMulta": 880.41
        },
        "automovel": {
            "placa": "JKY-8851",
            "renavam": 28599731311,
            "marca": "Fiat",
            "modelo": "Strada Adv/Adv TRYON 1.8 mpi Flex 8V CE",
            "ano": 2003,
            "cor": "Vermelho"
        },
        "userPartialReplica": {
            "cpf": "856.474.950-52",
            "nome": "Carolina Sophia Alice Costa",
            "email": "carolina_costa@fixacomunicacao.com.br",
            "logradouro": "Rua Eugênia S Vitale",
            "numero": 572,
            "complemento": "Conjunto 2, Casa 3a",
            "bairro": "Taboão",
            "localidade": "Diadema",
            "uf": "SP",
            "cep": "09930-380",
            "telefone": "(11) 99889-0350"
        }
    }
]

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Realmente embaraçoso, mas a Carolina/User está tentando saber os dados cadastrais de um CPF que não é o seu:

GET  {{UserUrl}}/user/808.472.843-10/onlymytokencpf

>>>Request Body:
```

```

>>>Response Body:
```
{
    "timestamp": "2023-06-23T23:20:18.179537200Z",
    "status": 400,
    "path": "/api-user/user/808.472.843-10/onlymytokencpf",
    "message": "O Cpf do usuário procurado é diferente do Cpf do token recebido..."
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Carolina/User finalmente buscou informações cadastrais do seu próprio CPF:

GET  {{UserUrl}}/user/856.474.950-52/onlymytokencpf

>>>Request Body:
```

```

>>>Response Body:
```
{
    "cpf": "856.474.950-52",
    "nome": "Carolina Sophia Alice Costa",
    "email": "carolina_costa@fixacomunicacao.com.br",
    "logradouro": "Rua Eugênia S Vitale",
    "numero": 572,
    "complemento": "Conjunto 2, Casa 3a",
    "bairro": "Taboão",
    "localidade": "Diadema",
    "uf": "SP",
    "cep": "09930-380",
    "telefone": "(11) 99889-0350"
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Juan/Admin faz login:

POST  {{UserUrl}}/user/login

>>>Request Body:
```
{  
  "username": "ADMIN-j-Username",
  "password": "ADMIN-juan01Psrd"
}


```

>>>Response Body:
```
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNzYuMDAyLjc3NS04MSIsInJvbGVzIjoiUk9MRV9BRE1JTiIsIm5vbWUiOiJKdWFuIFRpYWdvIGRhIENvc3RhIiwiaWF0IjoxNjg3NTYyNDM3LCJleHAiOjE2ODc1NzY4Mzd9.I1yaa0_JyP2qXQ0Aooey6OBdLIkQD4NthzwCAIWdPbC-h9fC4x6vjHV_WumCm-XagQ_SdDskFAfZ5JnrrKrSKw",
    "type": "Bearer"
}

```



![f31, token SKw, Captura de Tela (832) SKw](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/78afc673-92e3-4291-8065-808e2436f39c)







![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

O Juan/Admin resolve provover a Carlolina/User como newAdmin:

POST  {{UserUrl}}//user/newadmin

>>>Request Body:
```
{  
  "cpf": "856.474.950-52"
}


```

>>>Response Body:
```
{
    "cpf": "856.474.950-52",
    "nome": "Carolina Sophia Alice Costa",
    "username": "USER-c-Username",
    "email": "carolina_costa@fixacomunicacao.com.br",
    "logradouro": "Rua Eugênia S Vitale",
    "numero": 572,
    "complemento": "Conjunto 2, Casa 3a",
    "bairro": "Taboão",
    "localidade": "Diadema",
    "uf": "SP",
    "cep": "09930-380",
    "telefone": "(11) 99889-0350",
    "password": "$2a$10$kKaXK/rzcbjIXfE6bWcvmOFyaH9m0YWAX7aa8e6J9dNxnAKvpTXA2",
    "userType": "ADMIN"
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Agora, a Carolina/newAdmin efetua login:

POST  {{UserUrl}}/user/login

>>>Request Body:
```
{  
  "username": "USER-c-Username",
  "password": "USERcarolina01Psrd"
}


```

>>>Response Body:
```
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4NTYuNDc0Ljk1MC01MiIsInJvbGVzIjoiUk9MRV9BRE1JTixST0xFX1VTRVIiLCJub21lIjoiQ2Fyb2xpbmEgU29waGlhIEFsaWNlIENvc3RhIiwiaWF0IjoxNjg3NTYyNDY3LCJleHAiOjE2ODc1NzY4Njd9.zAasQXnfl1n31oKyosvmKhW-deeSwXuEsUWCPCiw_T7ulOqDmZQVrEKmqOStEca7MW8kyZgdmyff9venHcw9XA",
    "type": "Bearer"
}

```



![f32, token 9XA,Captura de Tela (833) 9XA](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/7ad69dc8-1d23-444a-9538-7dfd1706b730)











![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

A Carolina/newAdmin tenta efetuar cadastro da Mariah, porém deixa espaços em branco nos campos 'username' e 'password'. 
O sistema não ceita.


POST  {{UserUrl}}/user/signup
>>>Request Body:
```
{
  "cep": "74860900",
  "complemento": "viz. ao mercado",
  "cpf": "080.329.127-20",
  "email": "mariah_aparecida_ribeiro@tlmix.com.br",
  "nome": "Mariah Aparecida Valentina Ribeiro",
  "username": "espaço em branco",
  "numero": 772,
  "telefone": "(62) 98141-7318",
  "password": "   espaço   ",
  "userType": "USER"
}


```

>>>Response Body:
```
{
    "timestamp": "2023-06-24T21:18:14.538162900Z",
    "status": 422,
    "path": "/api-user/user/signup",
    "message": "Unprocessable Entity",
    "errors": [
        {
            "fieldName": "username",
            "message": "A username não pode ter espaço em branco..."
        },
        {
            "fieldName": "password",
            "message": "A senha não pode ter espaço em branco..."
        }
    ]
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

A Carolina/newAdmin finalmente consegue cadastrar a Mariah:

POST  {{UserUrl}}/user/signup

>>>Request Body:
```
{
  "cep": "74860900",
  "complemento": "viz. ao mercado",
  "cpf": "080.329.127-20",
  "email": "mariah_aparecida_ribeiro@tlmix.com.br",
  "nome": "Mariah Aparecida Valentina Ribeiro",
  "username": "USER-m-Username",
  "numero": 772,
  "telefone": "(62) 98141-7318",
  "password": "USERmariah01Psrd",
  "userType": "USER"
}


```

>>>Response Body:
```
{
    "cpf": "080.329.127-20",
    "nome": "Mariah Aparecida Valentina Ribeiro",
    "username": "USER-m-Username",
    "email": "mariah_aparecida_ribeiro@tlmix.com.br",
    "logradouro": "Avenida Professor Alfredo de Castro",
    "numero": 772,
    "complemento": "viz. ao mercado",
    "bairro": "Parque Acalanto",
    "localidade": "Goiânia",
    "uf": "GO",
    "cep": "74860-900",
    "telefone": "(62) 98141-7318",
    "password": "$2a$10$kf7E/HKq36DZE94YrcCcou6esKyveM4LG/7Klm7SrVZor1tqo9BbO",
    "userType": "USER"
}

```





![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

A Carolina/newAdmin cometeu erro no cadastramento de endereço da Mariah, e irá corrigir agora:

PUT  {{UserUrl}}/user/080.329.127-20/enderecoput

>>>Request Body:
```
{
  "cep": "83323350",
  "complemento": "Qd-A, Bl-02, Apt-804",
  "numero": 662
}

```

>>>Response Body:
```
204 No Content
```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

A Carolina/newAdmin irá conferir no sistema se o endereço da Mariah foi corrigido conforme esperado:

GET  {{UserUrl}}/user/080.329.127-20
>>>Request Body:
```

```

>>>Response Body:
```
{
    "cpf": "080.329.127-20",
    "nome": "Mariah Aparecida Valentina Ribeiro",
    "username": "USER-m-Username",
    "email": "mariah_aparecida_ribeiro@tlmix.com.br",
    "logradouro": "Rua Ásia",
    "numero": 662,
    "complemento": "Qd-A, Bl-02, Apt-804",
    "bairro": "Centro",
    "localidade": "Pinhais",
    "uf": "PR",
    "cep": "83323-350",
    "telefone": "(62) 98141-7318",
    "password": "$2a$10$kf7E/HKq36DZE94YrcCcou6esKyveM4LG/7Klm7SrVZor1tqo9BbO",
    "userType": "USER"
}

```




![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

A Carolina/newAdmin irá cadastrar multa na Mariah:

POST  {{TrafficFineRecordUrl}}/autuação

>>>Request Body:
```
{
  "codigoDaInfracao": 50292,
  "placaDoCarro": "LVV-0298",
  "cpf": "080.329.127-20"
}

```

>>>Response Body:
```
{
    "idApiAutuacao": "ab8e2637-4876-4a3e-a8e7-4ca57913347e",
    "placaDoCarro": "LVV-0298",
    "createdAt": "2023-06-24T18:18:40.6808635",
    "status": "NAO_PAGA",
    "codigoDaInfracao": 50292,
    "infracaoDeTransito": "Dirigir veículo com CNH / PPD / ACC com suspensão do direito de dirigir",
    "infracaoPontosNaCarteira": "07pts, Infração Gravíssima",
    "infracaoValorDaMulta": 880.41,
    "automovelRenavam": 76669865974,
    "automovelMarca": "Troller",
    "automovelModelo": "T-4 4x4 3.0 TB Int. Cap. R",
    "automovelAno": 2021,
    "automovelCor": "Branco",
    "authUserNome": "Mariah Aparecida Valentina Ribeiro",
    "authUserEmail": "mariah_aparecida_ribeiro@tlmix.com.br",
    "cpf": "080.329.127-20",
    "authUserLogradouro": "Rua Ásia",
    "authUserNumero": 662,
    "authUserComplemento": "Qd-A, Bl-02, Apt-804",
    "authUserBairro": "Centro",
    "authUserLocalidade": "Pinhais",
    "authUserUf": "PR",
    "authUserCep": "83323-350",
    "authUserTelefone": "(62) 98141-7318"
}

```








### 14.2- Testes Api-User



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

POST  {{UserUrl}}/user/login
>>>Request Body:
```
{  
  "username": "ADMIN-j-Username",
  "password": "ADMIN-juan01Psrd"
}


```

>>>Response Body:
```
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNzYuMDAyLjc3NS04MSIsInJvbGVzIjoiUk9MRV9BRE1JTiIsIm5vbWUiOiJKdWFuIFRpYWdvIGRhIENvc3RhIiwiaWF0IjoxNjg3NDU4NzY0LCJleHAiOjE2ODc0NzMxNjR9.r7fFR6xqlZCNLu6YzGhHDExJ5Bjx3t7Z52v3dhgCBaCa7os9VjytB5ejOXQfo3DqfL_Q46G3lzMQr-S8J0b7VQ",
    "type": "Bearer"
}

```



![f33, token 7VQ, 1-Token Juan](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/b3b958c1-b889-4658-ad89-9b6322266caa)








![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

GET {{UserUrl}}/user/paginacaoQuerySpec?page=4&size=2&sort=numero,desc
>>>Request Body:
```

```

>>>Response Body:
```
{
    "content": [
        {
            "cpf": "861.069.665-08",
            "nome": "Samuel Manuel da Cruz",
            "username": "07username",
            "email": "samuelmanueldacruz@hotamail.com",
            "logradouro": "Rua Flor do Espigão",
            "numero": 120,
            "complemento": "casa C",
            "bairro": "Tupi B",
            "localidade": "Belo Horizonte",
            "uf": "MG",
            "cep": "31842-687",
            "telefone": "(31) 98492-6857",
            "password": "eThWmZq4t7w!z%C*F-JaNdRgUjXn2r5u8x/A?D(G+KbPeShVmYq3s6v9y$B&E)H@",
            "userType": "USER"
        },
        {
            "cpf": "877.563.077-04",
            "nome": "Josefa Manuela da Rocha",
            "username": "06username",
            "email": "josefamanueladarocha@marcossousa.com",
            "logradouro": "Rua João Ferreira Barbosa",
            "numero": 72,
            "complemento": "Bl-A, Apt 01",
            "bairro": "Visconde de Mauá",
            "localidade": "Cachoeira do Sul",
            "uf": "RS",
            "cep": "96508-500",
            "telefone": "(51) 98897-9401",
            "password": ")J@NcRfUjXn2r5u8x!A%D*G-KaPdSgVkYp3s6v9y$B?E(H+MbQeThWmZq4t7w!z%",
            "userType": "ADMIN"
        }
    ],
    "pageable": {
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "offset": 8,
        "pageSize": 2,
        "pageNumber": 4,
        "unpaged": false,
        "paged": true
    },
    "last": false,
    "totalPages": 6,
    "totalElements": 11,
    "size": 2,
    "first": false,
    "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
    },
    "numberOfElements": 2,
    "number": 4,
    "empty": false
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Busca paginada por nomes que contenham trecho de palavra 'Ber':

GET  {{UserUrl}}/user/paginacaoQuerySpec?nome=Ber
>>>Request Body:
```

```

>>>Response Body:
```
{
    "content": [
        {
            "cpf": "137.013.226-35",
            "nome": "Aparecida Aline Bernardes da Paz",
            "username": "04username",
            "email": "aparecida_dapaz@lucaslima.com",
            "logradouro": "Rua Nossa Senhora Aparecida",
            "numero": 12,
            "complemento": "Apt 1003",
            "bairro": "Jardim Manguinhos",
            "localidade": "Cabedelo",
            "uf": "PB",
            "cep": "58103-482",
            "telefone": "(83) 98890-1076",
            "password": "2s5v8y/B?D(G+KbPeSVmYq3t6w9z$C&F)H@McQfTjWnZr4u7x!A%D*G-KaNdRgUk",
            "userType": "ADMIN"
        },
        {
            "cpf": "559.435.790-44",
            "nome": "Ester Silvana Bertrand",
            "username": "08username",
            "email": "ester_silvana_melo@eternalam.com.br",
            "logradouro": "Rua Vilma Ramos de Souza",
            "numero": 407,
            "complemento": "Bl-B, Apt-702",
            "bairro": "Centro",
            "localidade": "Palhoça",
            "uf": "SC",
            "cep": "88130-010",
            "telefone": "(48) 99747-9393",
            "password": "H@McQfTjWnZr4u7w!z%C*F-JaNdRgUkXp2s5v8y/A?D(G+KbPeShVmYq3t6w9z$C",
            "userType": "USER"
        },
        {
            "cpf": "168.832.296-55",
            "nome": "Manuel César Bergman",
            "username": "10username",
            "email": "manuel-duarte76@abdalathomaz.adv.br",
            "logradouro": "Rua Pedro Ferreira Góes",
            "numero": 824,
            "complemento": "casa",
            "bairro": "Centro",
            "localidade": "Barão de Grajaú",
            "uf": "MA",
            "cep": "65660-970",
            "telefone": "(99) 98131-7698",
            "password": "Xp2s5v8y/B?E(H+MbQeThWmZq3t6w9z$C&F)J@NcRfUjXn2r5u7x!A%D*G-KaPdS",
            "userType": "USER"
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "offset": 0,
        "pageSize": 20,
        "pageNumber": 0,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 3,
    "size": 20,
    "first": true,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "numberOfElements": 3,
    "number": 0,
    "empty": false
}

```




![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Busca paginada por localidades que tenham trecho 'Ara':

GET {{UserUrl}}/user/paginacaoQuerySpec?localidade=Ara
>>>Request Body:
```

```

>>>Response Body:
```
{
    "content": [
        {
            "cpf": "592.456.821-02",
            "nome": "Yuri Otávio André Brito",
            "username": "03username",
            "email": "yuriotaviobrito@riobc.com.br",
            "logradouro": "Travessa Dezesseis de Novembro II",
            "numero": 956,
            "complemento": "casa",
            "bairro": "Santos Dumont",
            "localidade": "Aracaju",
            "uf": "SE",
            "cep": "49087-116",
            "telefone": "(79) 99474-7612",
            "password": "eSgVkYp3s6v9y$B&E)H@McQfTjWmZq4t7w!z%C*F-JaNdRgUkXp2r5u8x/A?D(G+",
            "userType": "USER"
        },
        {
            "cpf": "249.968.249-30",
            "nome": "Levi Henrique Sales",
            "username": "05username",
            "email": "levi.henrique.sales@yahoo.co.uk",
            "logradouro": "Rua Osman Cavalcante",
            "numero": 307,
            "complemento": "Apt 201",
            "bairro": "Verdes Campos",
            "localidade": "Arapiraca",
            "uf": "AL",
            "cep": "57303-094",
            "telefone": "(82) 98626-5495",
            "password": "3t6w9z$C&F)J@NcRfUjWnZr4u7x!A%D*G-KaPdSgVkYp2s5v8y/B?E(H+MbQeThW",
            "userType": "USER"
        },
        {
            "cpf": "176.002.775-81",
            "nome": "Juan Tiago da Costa",
            "username": "ADMIN-j-Username",
            "email": "juan-dacosta76@htomail.com",
            "logradouro": "Rua C",
            "numero": 7391,
            "complemento": "Viz à praça",
            "bairro": "Santa Maria",
            "localidade": "Aracaju",
            "uf": "SE",
            "cep": "49043-452",
            "telefone": "(79) 99502-3388",
            "password": "$2a$10$cL9ATg/ZxLKweiCSlOvxyOkAna9F/4OIWZ9wH1Xqcjyl4103vod4e",
            "userType": "ADMIN"
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "offset": 0,
        "pageSize": 20,
        "pageNumber": 0,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 3,
    "size": 20,
    "first": true,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "numberOfElements": 3,
    "number": 0,
    "empty": false
}

```





![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

POST  {{UserUrl}}/user/signup
>>>Request Body:
```
{
  "cep": "51030620",
  "complemento": "Apt 1804",
  "cpf": "104.563.583-90",
  "email": "carlos_martin_dias@absoluta.med.br",
  "nome": "Carlos Martin Diego Dias",
  "username": "carlosUsername",
  "numero": 152,
  "telefone": "(21) 98135-2350",
  "password": "",
  "userType": "USER"
}


```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T18:33:04.736344Z",
    "status": 422,
    "path": "/api-user/user/signup",
    "message": "Unprocessable Entity",
    "errors": [
        {
            "fieldName": "password",
            "message": "não deve estar em branco"
        },
        {
            "fieldName": "password",
            "message": "tamanho deve ser entre 6 e 20"
        },
        {
            "fieldName": "password",
            "message": "A senha não pode ter espaço em branco..."
        }
    ]
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

POST  {{UserUrl}}/user/signup
>>>Request Body:
```
{
  "cep": "51030620",
  "complemento": "Apt 1804",
  "cpf": "104.563.583-90",
  "email": "carlos_martin_dias@absoluta.med.br",
  "nome": "Carlos Martin Diego Dias",
  "username": "car-Username",
  "numero": 152,
  "telefone": "(21) 98135-2350",
  "password": "espaco em braco",
  "userType": "USER"
}


```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T18:33:09.162313Z",
    "status": 422,
    "path": "/api-user/user/signup",
    "message": "Unprocessable Entity",
    "errors": [
        {
            "fieldName": "password",
            "message": "A senha não pode ter espaço em branco..."
        }
    ]
}

```












![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

POST {{UserUrl}}/user/signup
>>>Request Body:
```
{
  "cep": "51030620",
  "complemento": "Apt 1804",
  "cpf": "104.563.583-90",
  "email": "carlos_martin_dias@absoluta.med.br",
  "nome": "Carlos Martin Diego Dias",
  "username": "espaco em branco",
  "numero": 152,
  "telefone": "(21) 98135-2350",
  "password": "newSenha",
  "userType": "USER"
}


```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T18:33:14.269092900Z",
    "status": 422,
    "path": "/api-user/user/signup",
    "message": "Unprocessable Entity",
    "errors": [
        {
            "fieldName": "username",
            "message": "A username não pode ter espaço em branco..."
        }
    ]
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

POST  {{UserUrl}}/user/signup
>>>Request Body:
```
{
  "cep": "49042213",
  "complemento": "Casa 03",
  "cpf": "277.627.008-97",
  "username": "R-Username",
  "email": "rodrigo.leandro.almeida@its.jnj.com",
  "nome": "Rodrigo Leandro Almeida",
  "telefone": "(79) 99329-5493",
  "numero": 399,
  "password": "rodrigo01Psrd",
  "userType": "ADMIN"
}

```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T18:33:23.694497500Z",
    "status": 409,
    "path": "/api-user/user/signup",
    "message": "Erro: já existe cpf cadastrado com o número: 277.627.008-97"
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

POST  {{UserUrl}}/user/signup
>>>Request Body:
```
{
  "cep": "59114268",
  "complemento": "Bl-C, Apt 203",
  "cpf": "385.393.914-73",
  "email": "sarah-silva90@alcoa.com.br",
  "nome": "Sarah Josefa Joana Silva",
  "username": "USER-s-Username",
  "numero": 51,
  "telefone": "(84) 99645-2712",
  "password": "USERsarah01Psrd",
  "userType": "USER"
}


```

>>>Response Body:
```
{
    "cpf": "385.393.914-73",
    "nome": "Sarah Josefa Joana Silva",
    "username": "USER-s-Username",
    "email": "sarah-silva90@alcoa.com.br",
    "logradouro": "Avenida Industrial",
    "numero": 51,
    "complemento": "Bl-C, Apt 203",
    "bairro": "Nossa Senhora da Apresentação",
    "localidade": "Natal",
    "uf": "RN",
    "cep": "59114-268",
    "telefone": "(84) 99645-2712",
    "password": "$2a$10$Ul0iGHF8ji8oKle/m8jGxeCidf4XzkiKvRxXv3Mbbxd.dx2s.DgNa",
    "userType": "USER"
}

```






![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

GET  {{UserUrl}}/user/150.617.221-08/onlymytokencpf
>>>Request Body:
```

```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T18:33:34.012169800Z",
    "status": 400,
    "path": "/api-user/user/150.617.221-08/onlymytokencpf",
    "message": "O Cpf do usuário procurado é diferente do Cpf do token recebido..."
}

```

![Badge 1](http://img.shields.io/static/v1?label=XXXXXXXXX&message=YYYYYYYYYYYYYYYYYYY&color=GREEN&style=for-the-badge)

Promovendo usuário para newAdmin:

POST  {{UserUrl}}//user/newadmin
>>>Request Body:
```
{  
  "cpf": "385.393.914-73"
}

```

>>>Response Body:
```
{
    "cpf": "385.393.914-73",
    "nome": "Sarah Josefa Joana Silva",
    "username": "USER-s-Username",
    "email": "sarah-silva90@alcoa.com.br",
    "logradouro": "Avenida Industrial",
    "numero": 51,
    "complemento": "Bl-C, Apt 203",
    "bairro": "Nossa Senhora da Apresentação",
    "localidade": "Natal",
    "uf": "RN",
    "cep": "59114-268",
    "telefone": "(84) 99645-2712",
    "password": "$2a$10$Ul0iGHF8ji8oKle/m8jGxeCidf4XzkiKvRxXv3Mbbxd.dx2s.DgNa",
    "userType": "ADMIN"
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

POST  {{UserUrl}}/user/login

>>>Request Body:
```
{  
  "username": "USER-s-Username",
  "password": "USERsarah01Psrd"
}

```

>>>Response Body:
```
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzODUuMzkzLjkxNC03MyIsInJvbGVzIjoiUk9MRV9BRE1JTixST0xFX1VTRVIiLCJub21lIjoiU2FyYWggSm9zZWZhIEpvYW5hIFNpbHZhIiwiaWF0IjoxNjg3NDU4ODI0LCJleHAiOjE2ODc0NzMyMjR9.-nN3rJm8NJMPirg-OWeYxiywDSfAQgqNeJw8X704gJLwVIz6b3SYEHcSsLht1HUMoQjCytKuHAmx3pyeAnSUdQ",
    "type": "Bearer"
}

```




![f34, token SUdQ, 2-Token Sarah](https://github.com/SergioEGLira/Ms_Trfc_Fine_Record/assets/57645281/865d9bfd-11c6-46b8-ac1a-f556cad9adc7)






![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

POST  {{UserUrl}}/user/newadmin

>>>Request Body:
```
{  
  "cpf": "955.312.731-50"
}

```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T18:33:48.906625400Z",
    "status": 409,
    "path": "/api-user/user/newadmin",
    "message": "Erro: não encontramos o Cpf: 955.312.731-50"
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

POST  {{UserUrl}}/user/signup

>>>Request Body:
```
{
  "cep": " ",
  "complemento": "abcdefghijklmanopqrst",
  "cpf": " ",
  "email": " ",
  "nome": "",
  "telefone": "",
  "numero": 99991,
  "username": "USER-s-Username",
  "password": "USERsarah01Psrd",
  "userType": "USER"
}


```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T18:33:53.589759400Z",
    "status": 422,
    "path": "/api-user/user/signup",
    "message": "Unprocessable Entity",
    "errors": [
        {
            "fieldName": "telefone",
            "message": "O telefone não deve estar em branco"
        },
        {
            "fieldName": "cpf",
            "message": "Favor digitar CPF válido"
        },
        {
            "fieldName": "cpf",
            "message": "O CPF deve ter 14 caracteres, gentileza incluir '.' e '-'"
        },
        {
            "fieldName": "cpf",
            "message": "CPF não pode ser vazio"
        },
        {
            "fieldName": "numero",
            "message": "O numero deve ser inferior ou igual a 9999"
        },
        {
            "fieldName": "telefone",
            "message": "O telefone deve ter entre 8 e 15 caracteres"
        },
        {
            "fieldName": "cep",
            "message": "O Cep deve ser maior que 0"
        },
        {
            "fieldName": "cep",
            "message": "O Cep não deve estar em branco"
        },
        {
            "fieldName": "email",
            "message": "Email não pode ser vazio"
        },
        {
            "fieldName": "complemento",
            "message": "O complemento deve ter no máximo 20 caracteres"
        },
        {
            "fieldName": "nome",
            "message": "O nome não deve estar em branco"
        },
        {
            "fieldName": "cep",
            "message": "O Cep deve ter 8 caracteres numéricos"
        },
        {
            "fieldName": "email",
            "message": "Favor digitar email válido"
        },
        {
            "fieldName": "nome",
            "message": "O nome deve ter entre 2 e 40 caracteres"
        }
    ]
}

```





![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

GET {{UserUrl}}/user/277.627.008-97

>>>Request Body:
```

```

>>>Response Body:
```
{
    "cpf": "277.627.008-97",
    "nome": "Daiane Ana de Melo",
    "username": "01username",
    "email": "s__daianeanabernardes@autbook.com",
    "logradouro": "Rua das Fiandeiras",
    "numero": 470,
    "complemento": "Apt 2002",
    "bairro": "Vila Olímpia",
    "localidade": "São Paulo",
    "uf": "SP",
    "cep": "04545-002",
    "telefone": "(11) 9260-5643",
    "password": "aPdSgVkYp3s6v9y/B?E(H+MbQeThWmZq4t7w!z%C&F)J@NcRfUjXn2r5u8x/A?D(",
    "userType": "USER"
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

GET {{UserUrl}}/user/177.627.008-97
>>>Request Body:
```

```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T18:34:02.818379600Z",
    "status": 404,
    "path": "/api-user/user/177.627.008-97",
    "message": "Não encontramos o usuário com id 177.627.008-97"
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Atualização de endereço utilizando o ViaCep:

PUT  {{UserUrl}}/user/277.627.008-97/enderecoput

>>>Request Body:
```
{
  "cep": "22640904",
  "complemento": "Bl-A, Apt-1002",
  "numero": 123
}

```

>>>Response Body:
```
204 No Content

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Conferindo se o endereço atualizado anterormente foi efetivado:

GET {{UserUrl}}/user/277.627.008-97

>>>Request Body:
```

```

>>>Response Body:
```
{
    "cpf": "277.627.008-97",
    "nome": "Daiane Ana de Melo",
    "username": "01username",
    "email": "s__daianeanabernardes@autbook.com",
    "logradouro": "Avenida das Américas",
    "numero": 123,
    "complemento": "Bl-A, Apt-1002",
    "bairro": "Barra da Tijuca",
    "localidade": "Rio de Janeiro",
    "uf": "RJ",
    "cep": "22640-904",
    "telefone": "(11) 9260-5643",
    "password": "aPdSgVkYp3s6v9y/B?E(H+MbQeThWmZq4t7w!z%C&F)J@NcRfUjXn2r5u8x/A?D(",
    "userType": "USER"
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação para a atualização de endereço:

PUT  {{UserUrl}}/user/0866380e-204b-48d0-96b4-692f692ca8ad/enderecoput

>>>Request Body:
```
{
  "cep": "",
  "complemento": "abcdefghijklmanopqrst",
  "numero": 99991
}

```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T18:34:13.570594200Z",
    "status": 422,
    "path": "/api-user/user/0866380e-204b-48d0-96b4-692f692ca8ad/enderecoput",
    "message": "Unprocessable Entity",
    "errors": [
        {
            "fieldName": "cep",
            "message": "O Cep deve ser maior que 0"
        },
        {
            "fieldName": "cep",
            "message": "O Cep não deve estar em branco"
        },
        {
            "fieldName": "numero",
            "message": "O numero deve ser inferior ou igual a 9999"
        },
        {
            "fieldName": "cep",
            "message": "O Cep deve ter 8 caracteres numéricos"
        },
        {
            "fieldName": "complemento",
            "message": "O complemento deve ter no máximo 20 caracteres"
        }
    ]
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

PUT  {{UserUrl}}/user/177.627.008-97/enderecoput

>>>Request Body:
```
{
  "cep": "51030620",
  "complemento": "888",
  "numero": 999
}

```

>>>Response Body:
```
User não encontrado...

```


### 14.3- Testes Api-TrafficFineRecord




![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

GET  {{TrafficFineRecordUrl}}/autuacao/015d8a50-78e7-4a34-b8ca-8743c95d2fd2
>>>Request Body:
```

```

>>>Response Body:
```
{
    "idApiAutuacao": "015d8a50-78e7-4a34-b8ca-8743c95d2fd2",
    "placaDoCarro": "BRG-7406",
    "createdAt": "2022-07-10T11:05:55Z",
    "updatedAt": null,
    "status": "NAO_PAGA",
    "infracao": {
        "codigoDaInfracao": 52070,
        "infracaoDeTransito": "Dirigir sem atenção ou sem os cuidados indispensáveis à segurança",
        "pontosNaCarteira": "03pts, Infração Leve",
        "valorDaMulta": 88.38
    },
    "automovel": {
        "placa": "BRG-7406",
        "renavam": 85047151675,
        "marca": "Asia Motors",
        "modelo": "Towner SDX / DLX/ Std.",
        "ano": 1993,
        "cor": "Marrom"
    },
    "userPartialReplica": {
        "cpf": "277.627.008-97",
        "nome": "Daiane Ana de Melo",
        "email": "s__daianeanabernardes@autbook.com",
        "logradouro": "Avenida das Américas",
        "numero": 123,
        "complemento": "Bl-A, Apt-1002",
        "bairro": "Barra da Tijuca",
        "localidade": "Rio de Janeiro",
        "uf": "RJ",
        "cep": "22640-904",
        "telefone": "(11) 9260-5643"
    }
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

GET  {{TrafficFineRecordUrl}}/autuacao/000d8a50-78e7-4a34-b8ca-8743c95d2fd2
>>>Request Body:
```

```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T20:05:37.119514800Z",
    "status": 404,
    "path": "/api-trafficFineRecord/autuacao/000d8a50-78e7-4a34-b8ca-8743c95d2fd2",
    "message": "Não encontramos o id 000d8a50-78e7-4a34-b8ca-8743c95d2fd2"
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Busca por autuações que foram emitidas entre duas datas:

GET  {{TrafficFineRecordUrl}}/autuacao/getAutuacaoPorIntervaloDeDatas?minDate=2021-01-01T17:23:35.365287&maxDate=2021-02-04T17:23:35.365287

>>>Request Body:
```

```

>>>Response Body:
```
{
    "content": [
        {
            "idApiAutuacao": "935d8a50-78e7-4a34-b8ca-8743c95d2fd2",
            "placaDoCarro": "AFJ-8632",
            "createdAt": "2021-01-04T10:13:17Z",
            "updatedAt": "2021-01-27T13:29:17Z",
            "status": "PAGA",
            "infracao": {
                "codigoDaInfracao": 52070,
                "infracaoDeTransito": "Dirigir sem atenção ou sem os cuidados indispensáveis à segurança",
                "pontosNaCarteira": "03pts, Infração Leve",
                "valorDaMulta": 88.38
            },
            "automovel": {
                "placa": "AFJ-8632",
                "renavam": 71737113846,
                "marca": "Land Rover",
                "modelo": "Range R. Vogue 4.4 TDV8/SDV8 Diesel Aut.",
                "ano": 1988,
                "cor": "Cinza"
            },
            "userPartialReplica": {
                "cpf": "592.456.821-02",
                "nome": "Yuri Otávio André Brito",
                "email": "yuriotaviobrito@riobc.com.br",
                "logradouro": "Travessa Dezesseis de Novembro II",
                "numero": 956,
                "complemento": "casa",
                "bairro": "Santos Dumont",
                "localidade": "Aracaju",
                "uf": "SE",
                "cep": "49087-116",
                "telefone": "(79) 99474-7612"
            }
        },
        {
            "idApiAutuacao": "215d8a50-78e7-4a34-b8ca-8743c95d2fd2",
            "placaDoCarro": "JKY-8851",
            "createdAt": "2021-02-02T07:12:27Z",
            "updatedAt": "2021-03-23T07:12:27Z",
            "status": "PAGA",
            "infracao": {
                "codigoDaInfracao": 52151,
                "infracaoDeTransito": "Dirigir ameaçando os pedestres que estejam atravessando a via pública",
                "pontosNaCarteira": "INFRAÇÃO AUTO-SUSPENSIVA",
                "valorDaMulta": 293.47
            },
            "automovel": {
                "placa": "JKY-8851",
                "renavam": 28599731311,
                "marca": "Fiat",
                "modelo": "Strada Adv/Adv TRYON 1.8 mpi Flex 8V CE",
                "ano": 2003,
                "cor": "Vermelho"
            },
            "userPartialReplica": {
                "cpf": "592.456.821-02",
                "nome": "Yuri Otávio André Brito",
                "email": "yuriotaviobrito@riobc.com.br",
                "logradouro": "Travessa Dezesseis de Novembro II",
                "numero": 956,
                "complemento": "casa",
                "bairro": "Santos Dumont",
                "localidade": "Aracaju",
                "uf": "SE",
                "cep": "49087-116",
                "telefone": "(79) 99474-7612"
            }
        },
        {
            "idApiAutuacao": "915d8a50-78e7-4a34-b8ca-8743c95d2fd2",
            "placaDoCarro": "ZGT-8643",
            "createdAt": "2021-01-22T07:28:17Z",
            "updatedAt": null,
            "status": "NAO_PAGA",
            "infracao": {
                "codigoDaInfracao": 53630,
                "infracaoDeTransito": "Fazer ou deixar que se faça reparo em veículos nas vias (quando não rodovia / transito rápido)",
                "pontosNaCarteira": "03pts, Infração Leve",
                "valorDaMulta": 88.38
            },
            "automovel": {
                "placa": "ZGT-8643",
                "renavam": 13134025093,
                "marca": "Suzuki",
                "modelo": "Grand Vitara 2.0 16V 4x2/4x4 5p Aut.",
                "ano": 2010,
                "cor": "Amarelo"
            },
            "userPartialReplica": {
                "cpf": "861.069.665-08",
                "nome": "Samuel Manuel da Cruz",
                "email": "samuelmanueldacruz@hotamail.com",
                "logradouro": "Rua Flor do Espigão",
                "numero": 120,
                "complemento": "casa C",
                "bairro": "Tupi B",
                "localidade": "Belo Horizonte",
                "uf": "MG",
                "cep": "31842-687",
                "telefone": "(31) 98492-6857"
            }
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 20,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalElements": 3,
    "totalPages": 1,
    "size": 20,
    "number": 0,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "first": true,
    "numberOfElements": 3,
    "empty": false
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Busca paginada:

GET  {{TrafficFineRecordUrl}}/autuacao/paginacaoQuerySpec?page=0&size=2&sort=createdAt,desc
>>>Request Body:
```

```

>>>Response Body:
```
{
    "content": [
        {
            "idApiAutuacao": "615d8a50-78e7-4a34-b8ca-8743c95d2fd2",
            "placaDoCarro": "ZGT-8643",
            "createdAt": "2022-11-21T15:43:17Z",
            "updatedAt": "2022-11-27T09:22:17Z",
            "status": "CONVERTIDA_EM_ADVERTENCIA",
            "infracao": {
                "codigoDaInfracao": 50100,
                "infracaoDeTransito": "Dirigir veículo sem possuir CNH / PPD / ACC",
                "pontosNaCarteira": "07pts, Infração Gravíssima",
                "valorDaMulta": 880.41
            },
            "automovel": {
                "placa": "ZGT-8643",
                "renavam": 13134025093,
                "marca": "Suzuki",
                "modelo": "Grand Vitara 2.0 16V 4x2/4x4 5p Aut.",
                "ano": 2010,
                "cor": "Amarelo"
            },
            "userPartialReplica": {
                "cpf": "592.456.821-02",
                "nome": "Yuri Otávio André Brito",
                "email": "yuriotaviobrito@riobc.com.br",
                "logradouro": "Travessa Dezesseis de Novembro II",
                "numero": 956,
                "complemento": "casa",
                "bairro": "Santos Dumont",
                "localidade": "Aracaju",
                "uf": "SE",
                "cep": "49087-116",
                "telefone": "(79) 99474-7612"
            }
        },
        {
            "idApiAutuacao": "015d8a50-78e7-4a34-b8ca-8743c95d2fd2",
            "placaDoCarro": "BRG-7406",
            "createdAt": "2022-07-10T11:05:55Z",
            "updatedAt": null,
            "status": "NAO_PAGA",
            "infracao": {
                "codigoDaInfracao": 52070,
                "infracaoDeTransito": "Dirigir sem atenção ou sem os cuidados indispensáveis à segurança",
                "pontosNaCarteira": "03pts, Infração Leve",
                "valorDaMulta": 88.38
            },
            "automovel": {
                "placa": "BRG-7406",
                "renavam": 85047151675,
                "marca": "Asia Motors",
                "modelo": "Towner SDX / DLX/ Std.",
                "ano": 1993,
                "cor": "Marrom"
            },
            "userPartialReplica": {
                "cpf": "277.627.008-97",
                "nome": "Daiane Ana de Melo",
                "email": "s__daianeanabernardes@autbook.com",
                "logradouro": "Avenida das Américas",
                "numero": 123,
                "complemento": "Bl-A, Apt-1002",
                "bairro": "Barra da Tijuca",
                "localidade": "Rio de Janeiro",
                "uf": "RJ",
                "cep": "22640-904",
                "telefone": "(11) 9260-5643"
            }
        }
    ],
    "pageable": {
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 2,
        "unpaged": false,
        "paged": true
    },
    "last": false,
    "totalElements": 10,
    "totalPages": 5,
    "size": 2,
    "number": 0,
    "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
    },
    "first": true,
    "numberOfElements": 2,
    "empty": false
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Busca paginada por autuações que foram registradas com CPF específico:

GET  {{TrafficFineRecordUrl}}/autuacao/getAllAutuacoesByUserPaged?page=0&size=2&sort=createdAt,desc&cpf=877.563.077-04

>>>Request Body:
```

```

>>>Response Body:
```
{
    "content": [
        {
            "idApiAutuacao": "925d8a50-78e7-4a34-b8ca-8743c95d2fd2",
            "placaDoCarro": "TRW-5495",
            "createdAt": "2021-03-17T07:28:17Z",
            "updatedAt": "2021-03-22T07:28:17Z",
            "status": "CONVERTIDA_EM_ADVERTENCIA",
            "infracao": {
                "codigoDaInfracao": 51692,
                "infracaoDeTransito": "Dirigir sob influência de qualquer outra substância que determine dependência",
                "pontosNaCarteira": "INFRAÇÃO AUTO-SUSPENSIVA",
                "valorDaMulta": 2934.7
            },
            "automovel": {
                "placa": "TRW-5495",
                "renavam": 24771669680,
                "marca": "Renault",
                "modelo": "Clio Sed RT/ Privil",
                "ano": 2015,
                "cor": "Cinza"
            },
            "userPartialReplica": {
                "cpf": "877.563.077-04",
                "nome": "Josefa Manuela da Rocha",
                "email": "josefamanueladarocha@marcossousa.com",
                "logradouro": "Rua João Ferreira Barbosa",
                "numero": 72,
                "complemento": "Bl-A, Apt 01",
                "bairro": "Visconde de Mauá",
                "localidade": "Cachoeira do Sul",
                "uf": "RS",
                "cep": "96508-500",
                "telefone": "(51) 98897-9401"
            }
        },
        {
            "idApiAutuacao": "815d8a50-78e7-4a34-b8ca-8743c95d2fd2",
            "placaDoCarro": "NEH-8723",
            "createdAt": "2020-12-03T23:53:17Z",
            "updatedAt": null,
            "status": "NAO_PAGA",
            "infracao": {
                "codigoDaInfracao": 51692,
                "infracaoDeTransito": "Dirigir sob influência de qualquer outra substância que determine dependência",
                "pontosNaCarteira": "INFRAÇÃO AUTO-SUSPENSIVA",
                "valorDaMulta": 2934.7
            },
            "automovel": {
                "placa": "NEH-8723",
                "renavam": 38533649312,
                "marca": "VW - VolksWagen",
                "modelo": "Saveiro CROSSOVER 1.6 Mi Total Flex 8V",
                "ano": 1992,
                "cor": "Vermelho"
            },
            "userPartialReplica": {
                "cpf": "877.563.077-04",
                "nome": "Josefa Manuela da Rocha",
                "email": "josefamanueladarocha@marcossousa.com",
                "logradouro": "Rua João Ferreira Barbosa",
                "numero": 72,
                "complemento": "Bl-A, Apt 01",
                "bairro": "Visconde de Mauá",
                "localidade": "Cachoeira do Sul",
                "uf": "RS",
                "cep": "96508-500",
                "telefone": "(51) 98897-9401"
            }
        }
    ],
    "pageable": {
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 2,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalElements": 2,
    "totalPages": 1,
    "size": 2,
    "number": 0,
    "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
    },
    "first": true,
    "numberOfElements": 2,
    "empty": false
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Busca não paginada de autuações que foram emitidas contendo CPF específico:

GET   {{TrafficFineRecordUrl}}/autuacao/877.563.077-04/getAllAutuacoesByUser

>>>Request Body:
```

```

>>>Response Body:
```
[
    {
        "idApiAutuacao": "815d8a50-78e7-4a34-b8ca-8743c95d2fd2",
        "placaDoCarro": "NEH-8723",
        "createdAt": "2020-12-03T23:53:17Z",
        "updatedAt": null,
        "status": "NAO_PAGA",
        "infracao": {
            "codigoDaInfracao": 51692,
            "infracaoDeTransito": "Dirigir sob influência de qualquer outra substância que determine dependência",
            "pontosNaCarteira": "INFRAÇÃO AUTO-SUSPENSIVA",
            "valorDaMulta": 2934.7
        },
        "automovel": {
            "placa": "NEH-8723",
            "renavam": 38533649312,
            "marca": "VW - VolksWagen",
            "modelo": "Saveiro CROSSOVER 1.6 Mi Total Flex 8V",
            "ano": 1992,
            "cor": "Vermelho"
        },
        "userPartialReplica": {
            "cpf": "877.563.077-04",
            "nome": "Josefa Manuela da Rocha",
            "email": "josefamanueladarocha@marcossousa.com",
            "logradouro": "Rua João Ferreira Barbosa",
            "numero": 72,
            "complemento": "Bl-A, Apt 01",
            "bairro": "Visconde de Mauá",
            "localidade": "Cachoeira do Sul",
            "uf": "RS",
            "cep": "96508-500",
            "telefone": "(51) 98897-9401"
        }
    },
    {
        "idApiAutuacao": "925d8a50-78e7-4a34-b8ca-8743c95d2fd2",
        "placaDoCarro": "TRW-5495",
        "createdAt": "2021-03-17T07:28:17Z",
        "updatedAt": "2021-03-22T07:28:17Z",
        "status": "CONVERTIDA_EM_ADVERTENCIA",
        "infracao": {
            "codigoDaInfracao": 51692,
            "infracaoDeTransito": "Dirigir sob influência de qualquer outra substância que determine dependência",
            "pontosNaCarteira": "INFRAÇÃO AUTO-SUSPENSIVA",
            "valorDaMulta": 2934.7
        },
        "automovel": {
            "placa": "TRW-5495",
            "renavam": 24771669680,
            "marca": "Renault",
            "modelo": "Clio Sed RT/ Privil",
            "ano": 2015,
            "cor": "Cinza"
        },
        "userPartialReplica": {
            "cpf": "877.563.077-04",
            "nome": "Josefa Manuela da Rocha",
            "email": "josefamanueladarocha@marcossousa.com",
            "logradouro": "Rua João Ferreira Barbosa",
            "numero": 72,
            "complemento": "Bl-A, Apt 01",
            "bairro": "Visconde de Mauá",
            "localidade": "Cachoeira do Sul",
            "uf": "RS",
            "cep": "96508-500",
            "telefone": "(51) 98897-9401"
        }
    }
]

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Busca paginada por autuações em que a placa do carro tenha o numeral '74':

GET  {{TrafficFineRecordUrl}}/autuacao/paginacaoQuerySpec?page=0&size=2&sort=createdAt,asc&placaDoCarro=74

>>>Request Body:
```

```

>>>Response Body:
```
{
    "content": [
        {
            "idApiAutuacao": "115d8a50-78e7-4a34-b8ca-8743c95d2fd2",
            "placaDoCarro": "BRG-7406",
            "createdAt": "2022-05-02T09:19:33Z",
            "updatedAt": "2022-05-22T09:19:33Z",
            "status": "PAGA",
            "infracao": {
                "codigoDaInfracao": 53630,
                "infracaoDeTransito": "Fazer ou deixar que se faça reparo em veículos nas vias (quando não rodovia / transito rápido)",
                "pontosNaCarteira": "03pts, Infração Leve",
                "valorDaMulta": 88.38
            },
            "automovel": {
                "placa": "BRG-7406",
                "renavam": 85047151675,
                "marca": "Asia Motors",
                "modelo": "Towner SDX / DLX/ Std.",
                "ano": 1993,
                "cor": "Marrom"
            },
            "userPartialReplica": {
                "cpf": "559.435.790-44",
                "nome": "Ester Silvana Bertrand",
                "email": "ester_silvana_melo@eternalam.com.br",
                "logradouro": "Rua Vilma Ramos de Souza",
                "numero": 407,
                "complemento": "Bl-B, Apt-702",
                "bairro": "Centro",
                "localidade": "Palhoça",
                "uf": "SC",
                "cep": "88130-010",
                "telefone": "(48) 99747-9393"
            }
        },
        {
            "idApiAutuacao": "015d8a50-78e7-4a34-b8ca-8743c95d2fd2",
            "placaDoCarro": "BRG-7406",
            "createdAt": "2022-07-10T11:05:55Z",
            "updatedAt": null,
            "status": "NAO_PAGA",
            "infracao": {
                "codigoDaInfracao": 52070,
                "infracaoDeTransito": "Dirigir sem atenção ou sem os cuidados indispensáveis à segurança",
                "pontosNaCarteira": "03pts, Infração Leve",
                "valorDaMulta": 88.38
            },
            "automovel": {
                "placa": "BRG-7406",
                "renavam": 85047151675,
                "marca": "Asia Motors",
                "modelo": "Towner SDX / DLX/ Std.",
                "ano": 1993,
                "cor": "Marrom"
            },
            "userPartialReplica": {
                "cpf": "277.627.008-97",
                "nome": "Daiane Ana de Melo",
                "email": "s__daianeanabernardes@autbook.com",
                "logradouro": "Avenida das Américas",
                "numero": 123,
                "complemento": "Bl-A, Apt-1002",
                "bairro": "Barra da Tijuca",
                "localidade": "Rio de Janeiro",
                "uf": "RJ",
                "cep": "22640-904",
                "telefone": "(11) 9260-5643"
            }
        }
    ],
    "pageable": {
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 2,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalElements": 2,
    "totalPages": 1,
    "size": 2,
    "number": 0,
    "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
    },
    "first": true,
    "numberOfElements": 2,
    "empty": false
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Inserindo autuação:

POST  {{TrafficFineRecordUrl}}/autuação

>>>Request Body:
```
{
  "codigoDaInfracao": 53630,
  "placaDoCarro": "KID-0001",
  "cpf": "854.985.449-28"
}

```

>>>Response Body:
```
{
    "idApiAutuacao": "5c27ea56-6a91-4c09-800b-e308050b40ba",
    "placaDoCarro": "KID-0001",
    "createdAt": "2023-06-22T17:06:11.7002275",
    "status": "NAO_PAGA",
    "codigoDaInfracao": 53630,
    "infracaoDeTransito": "Fazer ou deixar que se faça reparo em veículos nas vias (quando não rodovia / transito rápido)",
    "infracaoPontosNaCarteira": "03pts, Infração Leve",
    "infracaoValorDaMulta": 88.38,
    "automovelRenavam": 98656030721,
    "automovelMarca": "Ford",
    "automovelModelo": "Fiesta TIT.Plus 1.0 12V EcoBoost Aut. 5p",
    "automovelAno": 2000,
    "automovelCor": "Branco",
    "authUserNome": "Hugo Diego Gomes",
    "authUserEmail": "hugo_gomes@sociedadeweb.com.br",
    "cpf": "854.985.449-28",
    "authUserLogradouro": "Beco Um",
    "authUserNumero": 190,
    "authUserComplemento": "Bl-B, Apt 03",
    "authUserBairro": "Lomba do Pinheiro",
    "authUserLocalidade": "Porto Alegre",
    "authUserUf": "RS",
    "authUserCep": "91550-052",
    "authUserTelefone": "(51) 99952-3622"
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

POST  {{TrafficFineRecordUrl}}/autuação

>>>Request Body:
```
{
  "codigoDaInfracao": 53630,
  "placaDoCarro": "KID-0001",
  "cpf": "854.985.449-28"
}

```

>>>Response Body:
```
{
    "idApiAutuacao": "e79bbbe1-df82-4c7a-9940-b7ba44832217",
    "placaDoCarro": "KID-0001",
    "createdAt": "2023-06-22T17:06:21.9741723",
    "status": "NAO_PAGA",
    "codigoDaInfracao": 53630,
    "infracaoDeTransito": "Fazer ou deixar que se faça reparo em veículos nas vias (quando não rodovia / transito rápido)",
    "infracaoPontosNaCarteira": "03pts, Infração Leve",
    "infracaoValorDaMulta": 88.38,
    "automovelRenavam": 98656030721,
    "automovelMarca": "Ford",
    "automovelModelo": "Fiesta TIT.Plus 1.0 12V EcoBoost Aut. 5p",
    "automovelAno": 2000,
    "automovelCor": "Branco",
    "authUserNome": "Hugo Diego Gomes",
    "authUserEmail": "hugo_gomes@sociedadeweb.com.br",
    "cpf": "854.985.449-28",
    "authUserLogradouro": "Beco Um",
    "authUserNumero": 190,
    "authUserComplemento": "Bl-B, Apt 03",
    "authUserBairro": "Lomba do Pinheiro",
    "authUserLocalidade": "Porto Alegre",
    "authUserUf": "RS",
    "authUserCep": "91550-052",
    "authUserTelefone": "(51) 99952-3622"
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

POST  {{TrafficFineRecordUrl}}/autuacao
>>>Request Body:
```
{
  "codigoDaInfracao": -53630,
  "placaDoCarro": "  ",
  "cpf": "854.985.449-28"
}

```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T20:06:26.926195700Z",
    "status": 422,
    "path": "/api-trafficFineRecord/autuacao",
    "message": "Unprocessable Entity",
    "errors": [
        {
            "fieldName": "codigoDaInfracao",
            "message": "O codigo da infracao deve ser positivo"
        },
        {
            "fieldName": "placaDoCarro",
            "message": "A placa não deve estar em branco"
        },
        {
            "fieldName": "placaDoCarro",
            "message": "A placa deve ter 8 caracteres alfanuméricos, incluido o hifen"
        }
    ]
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação, lembrando que as características do automóvel e o cadastro do usuário devem ser previamente inseridas no sistema:

POST   {{TrafficFineRecordUrl}}/autuação

>>>Request Body:
```
{
  "codigoDaInfracao": 53630,
  "placaDoCarro": "KID-0001",
  "cpf": "527.186.975-04"
}

```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T20:06:31.886142600Z",
    "status": 404,
    "path": "/api-trafficFineRecord/autuacao",
    "message": "Gentileza revisar as informações inseridas, pois não encontramos o código da infração 53630 e/ou a placa do carro KID-0001 e/ou o cpf  527.186.975-04"
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

POST   {{TrafficFineRecordUrl}}/autuação

>>>Request Body:
```
{
  "codigoDaInfracao": 54321,
  "placaDoCarro": "KID-0001",
  "cpf": "854.985.449-28"
}

```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T20:06:38.466955500Z",
    "status": 404,
    "path": "/api-trafficFineRecord/autuacao",
    "message": "Gentileza revisar as informações inseridas, pois não encontramos o código da infração 54321 e/ou a placa do carro KID-0001 e/ou o cpf  854.985.449-28"
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

POST   {{TrafficFineRecordUrl}}/autuação

>>>Request Body:
```
{
  "codigoDaInfracao": 53630,
  "placaDoCarro": "ZZZ-0001",
  "cpf": "854.985.449-28"
}

```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T20:06:43.455243400Z",
    "status": 404,
    "path": "/api-trafficFineRecord/autuacao",
    "message": "Gentileza revisar as informações inseridas, pois não encontramos o código da infração 53630 e/ou a placa do carro ZZZ-0001 e/ou o cpf  854.985.449-28"
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Busca por autuações que contenham característica de status específica:

GET   {{TrafficFineRecordUrl}}/autuacao/CONVERTIDA_EM_ADVERTENCIA/status

>>>Request Body:
```

```

>>>Response Body:
```
[
    {
        "idApiAutuacao": "315d8a50-78e7-4a34-b8ca-8743c95d2fd2",
        "placaDoCarro": "AFJ-8632",
        "createdAt": "2021-02-09T22:32:11Z",
        "updatedAt": "2021-02-25T22:32:11Z",
        "status": "CONVERTIDA_EM_ADVERTENCIA",
        "infracao": {
            "codigoDaInfracao": 52311,
            "infracaoDeTransito": "Atirar do veículo objetos ou substâncias",
            "pontosNaCarteira": "04pts, Infração Média",
            "valorDaMulta": 130.16
        },
        "automovel": {
            "placa": "AFJ-8632",
            "renavam": 71737113846,
            "marca": "Land Rover",
            "modelo": "Range R. Vogue 4.4 TDV8/SDV8 Diesel Aut.",
            "ano": 1988,
            "cor": "Cinza"
        },
        "userPartialReplica": {
            "cpf": "277.627.008-97",
            "nome": "Daiane Ana de Melo",
            "email": "s__daianeanabernardes@autbook.com",
            "logradouro": "Avenida das Américas",
            "numero": 123,
            "complemento": "Bl-A, Apt-1002",
            "bairro": "Barra da Tijuca",
            "localidade": "Rio de Janeiro",
            "uf": "RJ",
            "cep": "22640-904",
            "telefone": "(11) 9260-5643"
        }
    },
    {
        "idApiAutuacao": "615d8a50-78e7-4a34-b8ca-8743c95d2fd2",
        "placaDoCarro": "ZGT-8643",
        "createdAt": "2022-11-21T15:43:17Z",
        "updatedAt": "2022-11-27T09:22:17Z",
        "status": "CONVERTIDA_EM_ADVERTENCIA",
        "infracao": {
            "codigoDaInfracao": 50100,
            "infracaoDeTransito": "Dirigir veículo sem possuir CNH / PPD / ACC",
            "pontosNaCarteira": "07pts, Infração Gravíssima",
            "valorDaMulta": 880.41
        },
        "automovel": {
            "placa": "ZGT-8643",
            "renavam": 13134025093,
            "marca": "Suzuki",
            "modelo": "Grand Vitara 2.0 16V 4x2/4x4 5p Aut.",
            "ano": 2010,
            "cor": "Amarelo"
        },
        "userPartialReplica": {
            "cpf": "592.456.821-02",
            "nome": "Yuri Otávio André Brito",
            "email": "yuriotaviobrito@riobc.com.br",
            "logradouro": "Travessa Dezesseis de Novembro II",
            "numero": 956,
            "complemento": "casa",
            "bairro": "Santos Dumont",
            "localidade": "Aracaju",
            "uf": "SE",
            "cep": "49087-116",
            "telefone": "(79) 99474-7612"
        }
    },
    {
        "idApiAutuacao": "925d8a50-78e7-4a34-b8ca-8743c95d2fd2",
        "placaDoCarro": "TRW-5495",
        "createdAt": "2021-03-17T07:28:17Z",
        "updatedAt": "2021-03-22T07:28:17Z",
        "status": "CONVERTIDA_EM_ADVERTENCIA",
        "infracao": {
            "codigoDaInfracao": 51692,
            "infracaoDeTransito": "Dirigir sob influência de qualquer outra substância que determine dependência",
            "pontosNaCarteira": "INFRAÇÃO AUTO-SUSPENSIVA",
            "valorDaMulta": 2934.7
        },
        "automovel": {
            "placa": "TRW-5495",
            "renavam": 24771669680,
            "marca": "Renault",
            "modelo": "Clio Sed RT/ Privil",
            "ano": 2015,
            "cor": "Cinza"
        },
        "userPartialReplica": {
            "cpf": "877.563.077-04",
            "nome": "Josefa Manuela da Rocha",
            "email": "josefamanueladarocha@marcossousa.com",
            "logradouro": "Rua João Ferreira Barbosa",
            "numero": 72,
            "complemento": "Bl-A, Apt 01",
            "bairro": "Visconde de Mauá",
            "localidade": "Cachoeira do Sul",
            "uf": "RS",
            "cep": "96508-500",
            "telefone": "(51) 98897-9401"
        }
    }
]

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

GET   {{TrafficFineRecordUrl}}/autuacao/CANCELADA_POR_INCONSISTENCIA_OU_IRREGULARIDADE/status

>>>Request Body:
```

```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T20:06:56.787712500Z",
    "status": 404,
    "path": "/api-trafficFineRecord/autuacao/CANCELADA_POR_INCONSISTENCIA_OU_IRREGULARIDADE/status",
    "message": "Não encontrado..."
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Atualizando o status de uma multa previamente inserida no sistema:

PUT   {{TrafficFineRecordUrl}}/autuacao/515d8a50-78e7-4a34-b8ca-8743c95d2fd2/updateStatusDaMulta

>>>Request Body:
```
{
  "status": "CANCELADA_POR_INCONSISTENCIA_OU_IRREGULARIDADE"
}

```

>>>Response Body:
```
{
    "status": "CANCELADA_POR_INCONSISTENCIA_OU_IRREGULARIDADE"
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Conferido se o status da multa anteriormente atualizado foi efetivamente modificado:

GET   {{TrafficFineRecordUrl}}/autuacao/515d8a50-78e7-4a34-b8ca-8743c95d2fd2

>>>Request Body:
```

```

>>>Response Body:
```
{
    "idApiAutuacao": "515d8a50-78e7-4a34-b8ca-8743c95d2fd2",
    "placaDoCarro": "TRW-5495",
    "createdAt": "2021-04-12T13:43:17Z",
    "updatedAt": "2023-06-22T17:07:01Z",
    "status": "CANCELADA_POR_INCONSISTENCIA_OU_IRREGULARIDADE",
    "infracao": {
        "codigoDaInfracao": 50292,
        "infracaoDeTransito": "Dirigir veículo com CNH / PPD / ACC com suspensão do direito de dirigir",
        "pontosNaCarteira": "07pts, Infração Gravíssima",
        "valorDaMulta": 880.41
    },
    "automovel": {
        "placa": "TRW-5495",
        "renavam": 24771669680,
        "marca": "Renault",
        "modelo": "Clio Sed RT/ Privil",
        "ano": 2015,
        "cor": "Cinza"
    },
    "userPartialReplica": {
        "cpf": "249.968.249-30",
        "nome": "Levi Henrique Sales",
        "email": "levi.henrique.sales@yahoo.co.uk",
        "logradouro": "Rua Osman Cavalcante",
        "numero": 307,
        "complemento": "Apt 201",
        "bairro": "Verdes Campos",
        "localidade": "Arapiraca",
        "uf": "AL",
        "cep": "57303-094",
        "telefone": "(82) 98626-5495"
    }
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

PUT   {{TrafficFineRecordUrl}}/autuacao/000d8a50-78e7-4a34-b8ca-8743c95d2fd2/updateStatusDaMulta

>>>Request Body:
```
{
  "status": "NAO_PAGA"
}

```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T20:07:23.765326400Z",
    "status": 404,
    "path": "/api-trafficFineRecord/autuacao/000d8a50-78e7-4a34-b8ca-8743c95d2fd2/updateStatusDaMulta",
    "message": "Não encontramos o id 000d8a50-78e7-4a34-b8ca-8743c95d2fd2"
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Inserindo no sistema nova infração:

POST   {{TrafficFineRecordUrl}}/infrações

>>>Request Body:
```
{
  "codigoDaInfracao": 56221,
  "infracaoDeTransito": "Parar no passeio",
  "pontosNaCarteira": "03pts, Infração Leve",
  "valorDaMulta": 88.38
}

```

>>>Response Body:
```
{
    "codigoDaInfracao": 56221,
    "infracaoDeTransito": "Parar no passeio",
    "pontosNaCarteira": "03pts, Infração Leve",
    "valorDaMulta": 88.38
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

POST   {{TrafficFineRecordUrl}}/infracoes
>>>Request Body:
```
{
  "codigoDaInfracao": -56221,
  "infracaoDeTransito": " ",
  "pontosNaCarteira": "  ",
  "valorDaMulta": -88.38
}

```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T20:07:38.888314100Z",
    "status": 422,
    "path": "/api-trafficFineRecord/infracoes",
    "message": "Unprocessable Entity",
    "errors": [
        {
            "fieldName": "pontosNaCarteira",
            "message": "A descrição dos pontos na carteira não deve estar em branco"
        },
        {
            "fieldName": "pontosNaCarteira",
            "message": "tamanho deve ser entre 10 e 40"
        },
        {
            "fieldName": "codigoDaInfracao",
            "message": "O codigo da infracao deve ser positivo"
        },
        {
            "fieldName": "infracaoDeTransito",
            "message": "A descrição da infração de transito não deve estar em branco"
        },
        {
            "fieldName": "infracaoDeTransito",
            "message": "tamanho deve ser entre 8 e 120"
        },
        {
            "fieldName": "valorDaMulta",
            "message": "O valor da multa deve ser positivo"
        }
    ]
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

POST   {{TrafficFineRecordUrl}}/infrações

>>>Request Body:
```
{
  "codigoDaInfracao": 56221,
  "infracaoDeTransito": "x x x x x x ",
  "pontosNaCarteira": "y y y y y y y",
  "valorDaMulta": 0.01
}

```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T20:07:43.646102500Z",
    "status": 409,
    "path": "/api-trafficFineRecord/infracoes",
    "message": "Erro: código da infração já cadastrado..."
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

GET  {{TrafficFineRecordUrl}}/infracoes/52070
>>>Request Body:
```

```

>>>Response Body:
```
{
    "codigoDaInfracao": 52070,
    "infracaoDeTransito": "Dirigir sem atenção ou sem os cuidados indispensáveis à segurança",
    "pontosNaCarteira": "03pts, Infração Leve",
    "valorDaMulta": 88.38
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

GET  {{TrafficFineRecordUrl}}/infracoes/12345

>>>Request Body:
```

```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T20:07:52.899795100Z",
    "status": 404,
    "path": "/api-trafficFineRecord/infracoes/12345",
    "message": "Não encontramos a entidade com id 12345"
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Busca paginada por infrações:

GET   {{TrafficFineRecordUrl}}/infracoes/paginacaoQuerySpec?page=0&size=4&sort=codigoDaInfracao,asc
>>>Request Body:
```

```

>>>Response Body:
```
{
    "content": [
        {
            "codigoDaInfracao": 50100,
            "infracaoDeTransito": "Dirigir veículo sem possuir CNH / PPD / ACC",
            "pontosNaCarteira": "07pts, Infração Gravíssima",
            "valorDaMulta": 880.41
        },
        {
            "codigoDaInfracao": 50291,
            "infracaoDeTransito": "Dirigir veiculo com CNH / PPD / ACC cassada",
            "pontosNaCarteira": "07pts, Infração Gravíssima",
            "valorDaMulta": 880.41
        },
        {
            "codigoDaInfracao": 50292,
            "infracaoDeTransito": "Dirigir veículo com CNH / PPD / ACC com suspensão do direito de dirigir",
            "pontosNaCarteira": "07pts, Infração Gravíssima",
            "valorDaMulta": 880.41
        },
        {
            "codigoDaInfracao": 51691,
            "infracaoDeTransito": "Dirigir sob a influência de álcool",
            "pontosNaCarteira": "INFRAÇÃO AUTO-SUSPENSIVA",
            "valorDaMulta": 2934.7
        }
    ],
    "pageable": {
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 4,
        "unpaged": false,
        "paged": true
    },
    "last": false,
    "totalElements": 16,
    "totalPages": 4,
    "size": 4,
    "number": 0,
    "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
    },
    "first": true,
    "numberOfElements": 4,
    "empty": false
}

```




![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Buscapaginada por infrações que contenham '4 pontos na carteira' como penalidade:

GET   {{TrafficFineRecordUrl}}/infracoes/paginacaoQuerySpec?pontosNaCarteira=4

>>>Request Body:
```

```

>>>Response Body:
```
{
    "content": [
        {
            "codigoDaInfracao": 52231,
            "infracaoDeTransito": "Usar veículo para arremessar sobre os pedestres água ou detritos",
            "pontosNaCarteira": "04pts, Infração Média",
            "valorDaMulta": 130.16
        },
        {
            "codigoDaInfracao": 52232,
            "infracaoDeTransito": "Usar veículo para arremessar sobre os veículos água ou detritos",
            "pontosNaCarteira": "04pts, Infração Média",
            "valorDaMulta": 130.16
        },
        {
            "codigoDaInfracao": 52311,
            "infracaoDeTransito": "Atirar do veículo objetos ou substâncias",
            "pontosNaCarteira": "04pts, Infração Média",
            "valorDaMulta": 130.16
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 20,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalElements": 3,
    "totalPages": 1,
    "size": 20,
    "number": 0,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "first": true,
    "numberOfElements": 3,
    "empty": false
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

DEL  {{TrafficFineRecordUrl}}/infracoes/56221

>>>Request Body:
```

```

>>>Response Body:
```
204 No Content
```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

DEL   {{TrafficFineRecordUrl}}/infracoes/12345
>>>Request Body:
```

```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T20:08:19.249053700Z",
    "status": 404,
    "path": "/api-trafficFineRecord/infracoes/12345",
    "message": "Não encontramos o id 12345"
}

```




![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

DEL   {{TrafficFineRecordUrl}}/infracoes/52070

>>>Request Body:
```

```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T20:08:25.685610200Z",
    "status": 409,
    "path": "/api-trafficFineRecord/infracoes/52070",
    "message": "Erro de integridade do bco de dados!"
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

GET   {{TrafficFineRecordUrl}}/infracoes/50291

>>>Request Body:
```

```

>>>Response Body:
```
{
    "codigoDaInfracao": 50291,
    "infracaoDeTransito": "Dirigir veiculo com CNH / PPD / ACC cassada",
    "pontosNaCarteira": "07pts, Infração Gravíssima",
    "valorDaMulta": 880.41
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Efetuando atualização das características de uma infração específica:

PUT  {{TrafficFineRecordUrl}}/infracoes/50291/updatePontosNaCarteiraValorDaMulta


>>>Request Body:
```
{
  "pontosNaCarteira": "x x x 07pts, Infração Gravíssima",
  "valorDaMulta": 0.01
}

```

>>>Response Body:
```
{
    "codigoDaInfracao": 50291,
    "infracaoDeTransito": "Dirigir veiculo com CNH / PPD / ACC cassada",
    "pontosNaCarteira": "x x x 07pts, Infração Gravíssima",
    "valorDaMulta": 0.01
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

PUT  {{TrafficFineRecordUrl}}/infracoes/50291/updatePontosNaCarteiraValorDaMulta

>>>Request Body:
```
{
  "pontosNaCarteira": "  ",
  "valorDaMulta": -0.01
}

```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T20:08:45.875564900Z",
    "status": 422,
    "path": "/api-trafficFineRecord/infracoes/50291/updatePontosNaCarteiraValorDaMulta",
    "message": "Unprocessable Entity",
    "errors": [
        {
            "fieldName": "valorDaMulta",
            "message": "O valor da multa deve ser positivo"
        },
        {
            "fieldName": "pontosNaCarteira",
            "message": "A descrição dos pontos na carteira não deve estar em branco"
        },
        {
            "fieldName": "pontosNaCarteira",
            "message": "tamanho deve ser entre 10 e 40"
        }
    ]
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

GET  {{TrafficFineRecordUrl}}/infracoes/50291

>>>Request Body:
```

```

>>>Response Body:
```
{
    "codigoDaInfracao": 50291,
    "infracaoDeTransito": "Dirigir veiculo com CNH / PPD / ACC cassada",
    "pontosNaCarteira": "x x x 07pts, Infração Gravíssima",
    "valorDaMulta": 0.01
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

PUT   {{TrafficFineRecordUrl}}/infracoes/12345/updatePontosNaCarteiraValorDaMulta

>>>Request Body:
```
{
  "pontosNaCarteira": "x x x 07pts, Infração Gravíssima",
  "valorDaMulta": 0.01
}

```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T20:09:03.962268Z",
    "status": 404,
    "path": "/api-trafficFineRecord/infracoes/12345/updatePontosNaCarteiraValorDaMulta",
    "message": "Não encontramos o id 12345"
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Busca paginada por automóveis:

GET  {{TrafficFineRecordUrl}}/automoveis/paginacaoQuerySpec?page=1&size=2&sort=placa,desc

>>>Request Body:
```

```

>>>Response Body:
```
{
    "content": [
        {
            "placa": "NEH-8723",
            "renavam": 38533649312,
            "marca": "VW - VolksWagen",
            "modelo": "Saveiro CROSSOVER 1.6 Mi Total Flex 8V",
            "ano": 1992,
            "cor": "Vermelho"
        },
        {
            "placa": "MXN-3470",
            "renavam": 64923416516,
            "marca": "VW - VolksWagen",
            "modelo": "up! take 1.0 T. Flex 12V 3p",
            "ano": 1989,
            "cor": "Amarelo"
        }
    ],
    "pageable": {
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "offset": 2,
        "pageNumber": 1,
        "pageSize": 2,
        "unpaged": false,
        "paged": true
    },
    "last": false,
    "totalElements": 10,
    "totalPages": 5,
    "size": 2,
    "number": 1,
    "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
    },
    "first": false,
    "numberOfElements": 2,
    "empty": false
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Busca paginada por automóveis que sejam especificamente da marca 'Volks': 

GET   {{TrafficFineRecordUrl}}/automoveis/paginacaoQuerySpec?marca=Volks

>>>Request Body:
```

```

>>>Response Body:
```
{
    "content": [
        {
            "placa": "NEH-8723",
            "renavam": 38533649312,
            "marca": "VW - VolksWagen",
            "modelo": "Saveiro CROSSOVER 1.6 Mi Total Flex 8V",
            "ano": 1992,
            "cor": "Vermelho"
        },
        {
            "placa": "MXN-3470",
            "renavam": 64923416516,
            "marca": "VW - VolksWagen",
            "modelo": "up! take 1.0 T. Flex 12V 3p",
            "ano": 1989,
            "cor": "Amarelo"
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 20,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalElements": 2,
    "totalPages": 1,
    "size": 20,
    "number": 0,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "first": true,
    "numberOfElements": 2,
    "empty": false
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Inserindo no sistema novo automóvel:

POST   {{TrafficFineRecordUrl}}/automóveis

>>>Request Body:
```
{
  "ano": 1999,
  "cor": "cor X",
  "marca": "marca X",
  "modelo": "modelo X",
  "placa": "xxx-1234",
  "renavam": 1234567890
}

```

>>>Response Body:
```
{
    "placa": "xxx-1234",
    "renavam": 1234567890,
    "marca": "marca X",
    "modelo": "modelo X",
    "ano": 1999,
    "cor": "cor X"
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

POST  {{TrafficFineRecordUrl}}/automóveis

>>>Request Body:
```
{
  "ano": -1899,
  "cor": "  ",
  "marca": " ",
  "modelo": "  ",
  "placa": " ",
  "renavam": -1234567890
}

```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T20:09:30.461783200Z",
    "status": 422,
    "path": "/api-trafficFineRecord/automoveis",
    "message": "Unprocessable Entity",
    "errors": [
        {
            "fieldName": "placa",
            "message": "A placa deve ter 8 caracteres alfanuméricos, incluido o hifen"
        },
        {
            "fieldName": "renavam",
            "message": "O renavam deve ser positivo"
        },
        {
            "fieldName": "ano",
            "message": "O ano deve ser positivo"
        },
        {
            "fieldName": "marca",
            "message": "A marca deve ser preenchida"
        },
        {
            "fieldName": "cor",
            "message": "A cor deve ser digitada"
        },
        {
            "fieldName": "placa",
            "message": "A placa não deve estar em branco"
        },
        {
            "fieldName": "modelo",
            "message": "O modelo deve ter entre 4 e 50 caracteres"
        },
        {
            "fieldName": "ano",
            "message": "deve ser maior que ou igual à 1900"
        },
        {
            "fieldName": "modelo",
            "message": "O modelo deve ser preenchido"
        },
        {
            "fieldName": "marca",
            "message": "A marca deve ter entre 4 e 20 caracteres"
        },
        {
            "fieldName": "cor",
            "message": "A cor deve ter entre 3 e 15 caracteres"
        }
    ]
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

POST  {{TrafficFineRecordUrl}}/automóveis

>>>Request Body:
```
{
  "ano": 2000,
  "cor": "cor Y",
  "marca": "marca Y",
  "modelo": "modelo Y",
  "placa": "xxx-1234",
  "renavam": 12312312311
}

```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T20:09:37.388178400Z",
    "status": 409,
    "path": "/api-trafficFineRecord/automoveis",
    "message": "Erro: já existe automóvel cadastrado com a placa: xxx-1234"
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Atualizando a característica de cor de um automóvel específico:

PUT  {{TrafficFineRecordUrl}}/automoveis/BRG-7406/updateCorDoAutomovel

>>>Request Body:
```
{
  "cor": "update cor"
}

```

>>>Response Body:
```
{
    "placa": "BRG-7406",
    "renavam": 85047151675,
    "marca": "Asia Motors",
    "modelo": "Towner SDX / DLX/ Std.",
    "ano": 1993,
    "cor": "update cor"
}

```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

PUT  {{TrafficFineRecordUrl}}/automoveis/BRG-7406/updateCorDoAutomovel

>>>Request Body:
```
{
  "cor": "  "
}

```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T20:09:49.952442300Z",
    "status": 422,
    "path": "/api-trafficFineRecord/automoveis/BRG-7406/updateCorDoAutomovel",
    "message": "Unprocessable Entity",
    "errors": [
        {
            "fieldName": "cor",
            "message": "A cor deve ser digitada"
        },
        {
            "fieldName": "cor",
            "message": "A cor deve ter entre 3 e 15 caracteres"
        }
    ]
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

PUT  {{TrafficFineRecordUrl}}/automoveis/AAA-7406/updateCorDoAutomovel

>>>Request Body:
```
{
  "cor": "update cor"
}

```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T20:09:56.647851900Z",
    "status": 404,
    "path": "/api-trafficFineRecord/automoveis/AAA-7406/updateCorDoAutomovel",
    "message": "Não encontramos automóvel com a placa AAA-7406"
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)


GET  {{TrafficFineRecordUrl}}/automoveis/ZGT-8643
>>>Request Body:
```

```

>>>Response Body:
```
{
    "placa": "ZGT-8643",
    "renavam": 13134025093,
    "marca": "Suzuki",
    "modelo": "Grand Vitara 2.0 16V 4x2/4x4 5p Aut.",
    "ano": 2010,
    "cor": "Amarelo"
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

GET  {{TrafficFineRecordUrl}}/automoveis/AAA-7406

>>>Request Body:
```

```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T20:10:09.374506500Z",
    "status": 404,
    "path": "/api-trafficFineRecord/automoveis/AAA-7406",
    "message": "Não encontramos automóvel com a placa AAA-7406"
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

DEL {{TrafficFineRecordUrl}}/automoveis/MOK-2136
>>>Request Body:
```

```

>>>Response Body:
```
204 No Content
```



![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

DEL  {{TrafficFineRecordUrl}}/automoveis/MOK-2136

>>>Request Body:
```

```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T20:10:28.094975400Z",
    "status": 404,
    "path": "/api-trafficFineRecord/automoveis/MOK-2136",
    "message": "Não encontramos automóvel com a placa MOK-2136"
}

```


![Badge 1](http://img.shields.io/static/v1?label=**********&message=******************************&color=GREEN&style=for-the-badge)

Teste de validação:

DEL   {{TrafficFineRecordUrl}}/automoveis/ZGT-8643

>>>Request Body:
```

```

>>>Response Body:
```
{
    "timestamp": "2023-06-22T20:10:34.375366600Z",
    "status": 409,
    "path": "/api-trafficFineRecord/automoveis/ZGT-8643",
    "message": "Erro de integridade do bco de dados!"
}

```




