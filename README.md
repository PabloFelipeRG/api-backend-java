## Discover Timesheet API ##
  
Back-end para todas as operações do sistema Discover Timesheet


### Dependências de Software ###

Java 8  
- Instalar JDK 1.8 (sem JRE)  
- Setar variável de ambiente JAVA_HOME apontando para diretório de instalação do java  
- Colocar %JAVA_HOME%\bin no PATH  

Apache Maven  
- Instalar Apache Maven 3.5.3  
- Setar variável de ambiente MAVEN_HOME apontando para diretório de instalação do maven  
- Colocar %MAVEN_HOME%\bin no PATH  


### Construção e Implantação ##

Em desenvolvimento, rodar **mvn jetty:run**. Para testar, acesse a url:  
http://localhost:9000/timesheet-api/v1/usuario/1  

Em homologação ou produção, rodar **mvn clean package** para gerar o pacote.  
Para configurar o Jetty, copie o conteúdo da pasta /config/jetty para o diretório base do Jetty (jetty.base).  


### Documentação ###

Para ver a documentação da api, acesse:  

http://localhost:9000/timesheet-api/v1/swagger.json  
http://localhost:9000/timesheet-api/v1/swagger.yaml  


### Desenvolvimento - Debugging ###

Para habilitar debug remoto, basta passar os seguintes parâmetros na linha de comando do Maven:  

-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=4000,server=y,suspend=n  
